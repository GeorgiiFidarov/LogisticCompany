package ru.fidarov.priceservice;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    private final PriceService priceService;

    @GetMapping("getAll/")
    public ResponseEntity<List<PriceResponse>> getAll() {
        log.info("getting all prices");
        List<PriceResponse> priceResponses = priceService.getAllPrices();
        return new ResponseEntity<>(priceResponses, HttpStatus.OK);
    }
    @PostMapping("create/")
    public ResponseEntity<Void> createPrice(@RequestBody PriceResponse priceResponse) {
        log.info("creating a new price");
        priceService.createPrice(priceResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("update/")
    public ResponseEntity<Void> updatePrice(@RequestBody PriceResponse priceResponse) {
        log.info("updating price");
        priceService.updatePrice(priceResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{priceProductId}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long priceProductId) {
        log.info("deleting price with ID: " + priceProductId);
        priceService.deletePrice(priceProductId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
