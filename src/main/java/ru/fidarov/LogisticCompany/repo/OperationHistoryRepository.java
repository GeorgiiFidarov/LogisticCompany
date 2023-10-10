package ru.fidarov.LogisticCompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fidarov.LogisticCompany.models.OperationHistory;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory,Long> {
    @Query("SELECT " +
            "p.pricePerOneUnit, " +
            "oh.deliveryAddress, " +
            "oh.shipmentPrice, " +
            "oh.amountOfProducts," +
            "oh.operationId " +
            "FROM Price p " +
            "JOIN OperationHistory oh ON p.priceProductId = oh.soldProductId ")
    List<Object[]> getPriceShipmentAmount();

}
