package ru.fidarov.dataUploader.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OperationHistory {
    @Id
    @SequenceGenerator(
            name = "operation_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "operation_id_sequence"
    )
    private Long operationId;
    private LocalDate shipmentDate;
    private Long soldProductId;//FK for Product and Price
    private Long deliveryAddress;//FK for Customers
    private String network;
    private Integer amountOfProducts;
    private Double shipmentPrice;
    @ManyToOne
    @JoinColumn(name = "priceProductId")
    private Price price; // Связь с Price
    @ManyToOne
    @JoinColumn(name = "shipmentAddress")
    private Customer customer; // Связь с Price



}
