package ru.fidarov.priceservice;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fidarov.dataUploader.models.Price;
import ru.fidarov.dataUploader.repo.PriceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public List<Price> getAllPrices(){
        return priceRepository.findAll();
    }
}
