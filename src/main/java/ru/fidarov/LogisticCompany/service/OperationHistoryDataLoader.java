package ru.fidarov.LogisticCompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.LogisticCompany.models.*;
import ru.fidarov.LogisticCompany.repo.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Service
public class OperationHistoryDataLoader {

    private final OperationHistoryRepository historyRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final CustomerRepository customerRepository;
    private final PromoRepository promoRepository;
    private final InfoRepository infoRepository;

    @Autowired
    public OperationHistoryDataLoader(OperationHistoryRepository historyRepository, ProductRepository productRepository, PriceRepository priceRepository, CustomerRepository customerRepository, PromoRepository promoRepository, InfoRepository infoRepository) {
        this.historyRepository = historyRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.customerRepository = customerRepository;
        this.promoRepository = promoRepository;
        this.infoRepository = infoRepository;
    }

    @Transactional
    public void loadDataFromCSV() {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        executor.submit(() ->
                loadData(
                        "/home/georgii/Downloads/LogisticCompany/src/main/resources/data/OperationHistory.csv",
                        this::createOperationHistory,
                        historyRepository,
                        countDownLatch
                ));
        executor.submit(() ->
                loadData(
                        "/home/georgii/Downloads/LogisticCompany/src/main/resources/data/Product.csv",
                        this::createProduct,
                        productRepository,
                        countDownLatch
                ));
        executor.submit(() ->
                loadData(
                        "/home/georgii/Downloads/LogisticCompany/src/main/resources/data/Price.csv",
                        this::createPrice,
                        priceRepository,
                        countDownLatch
                ));
        executor.submit(() ->
                loadData(
                        "/home/georgii/Downloads/LogisticCompany/src/main/resources/data/Customer.csv",
                        this::createCustomer,
                        customerRepository,
                        countDownLatch
                ));
        executor.shutdown();

        try {
            countDownLatch.countDown();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calculateAndSavePromo();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getAndSaveInfoTable();
    }


    @Transactional
    public <T> void loadData(
            String csvFilePath,
            Function<String[], T> entityCreator,
            JpaRepository<T, Long> repository,
            CountDownLatch latch) {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            List<T> entityBatch = new ArrayList<>();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                T entity = entityCreator.apply(data);
                entityBatch.add(entity);

                if (entityBatch.size() >= 1000) {
                    repository.saveAll(entityBatch);
                    entityBatch.clear();
                }
            }

            if (!entityBatch.isEmpty()) {
                repository.saveAll(entityBatch);
            }
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OperationHistory createOperationHistory(String[] data) {
        return OperationHistory.builder()
                .shipmentDate(LocalDate.parse(data[0]))
                .soldProductId(Long.parseLong(data[1]))
                .deliveryAddress(Long.parseLong(data[2]))
                .network(data[3])
                .amountOfProducts(Integer.parseInt(data[4]))
                .shipmentPrice(Double.parseDouble(data[5]))
                .build();
    }

    private Product createProduct(String[] data) {
        return Product.builder()
                .productId(Long.parseLong(data[0]))
                .productName(data[1])
                .categoryId(Long.parseLong(data[2]))
                .brand(data[3])
                .build();

    }

    private Price createPrice(String[] data) {
        return Price.builder()
                .network(data[0])
                .priceProductId(Long.parseLong(data[1]))
                .pricePerOneUnit(Double.parseDouble(data[2]))
                .build();

    }

    private Customer createCustomer(String[] data) {
        return Customer.builder()
                .shipmentAddress(Long.parseLong(data[0]))
                .network(data[1])
                .build();

    }

    private void calculateAndSavePromo(){

        List<Object[]> result = historyRepository.getPriceShipmentAmount();

        for (Object[] row : result) {
            Double pricePerOneUnit = (Double) row[0];
            Long deliveryAddress = (Long) row[1];
            Double shipmentPrice = (Double) row[2];
            Integer amountOfProducts = (Integer) row[3];
            Long operationId = (Long) row[4];


            String promoText;


            double calculatedPrice = shipmentPrice / amountOfProducts;
            if (calculatedPrice < pricePerOneUnit) {
                promoText = "Promo";
            } else if (calculatedPrice == pricePerOneUnit) {
                promoText = "Original Price";
            } else {
                promoText = "Higher than Original";
            }


            double percent = 100-((calculatedPrice/shipmentPrice)*100);
            String formattedNumber = String.format("%.2f", percent);
            if (Double.isNaN(percent)){
                formattedNumber = String.valueOf(0.0);
            }
            Promo promo = Promo.builder()
                    .deliveryAddress(deliveryAddress)
                    .attribute(promoText)
                    .percent(Double.parseDouble(formattedNumber))
                    .operationId(operationId)
                    .build();
            promoRepository.save(promo);
        }
    }

    private void getAndSaveInfoTable(){
        List<Object[]> result = infoRepository.makeInfoTable();
        List<Info> infoList = new ArrayList<>();


        for (Object[] row : result) {
            Long soldProductId = (Long) row[0];
            Long deliveryAddress = (Long) row[1];
            String network = (String) row[2];
            Long categoryId = (Long) row[3];
            LocalDate shipmentDate = (LocalDate) row[4];
            Integer amountOfProducts = (Integer) row[5];
            String attribute = (String) row[6];
            Double percent = (Double) row[7];

            Info info = Info.builder()
                    .ProductId(soldProductId)
                    .shipmentId(deliveryAddress)
                    .network(network)
                    .categoryId(categoryId)
                    .date(shipmentDate)
                    .amountOfSoldProduct(amountOfProducts)
                    .isPromo(attribute)
                    .percent(String.format("%.2f", percent))
                    .build();

            infoList.add(info);
        }
        infoRepository.saveAllAndFlush(infoList);
    }
}