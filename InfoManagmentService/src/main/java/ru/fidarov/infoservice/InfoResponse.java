package ru.fidarov.infoservice;

import java.time.LocalDate;

public record InfoResponse(
        Long infoId,
        Long ProductId,
        Long shipmentId,
        String network,
        Long categoryId,
        LocalDate date,
        Integer amountOfSoldProduct,
        String isPromo,
        String percent) {
}
