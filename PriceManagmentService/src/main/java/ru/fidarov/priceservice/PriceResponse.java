package ru.fidarov.priceservice;

public record PriceResponse(
        Long priceProductId,
        String network,
        Double pricePerOneUnit) {
}
