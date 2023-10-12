package ru.fidarov.priceservice;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fidarov.dataUploader.models.Price;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getAll(){
        System.out.println("Hi");
        log.info("getting all prices");
        return ResponseEntity.ok(priceService.getAllPrices());
    }
}
