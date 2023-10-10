package ru.fidarov.LogisticCompany.models;

import jakarta.persistence.*;
import lombok.*;

import java.beans.ConstructorProperties;
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
    @Column(name = "shipment_date")
    private LocalDate shipmentDate;
    @Column(name = "sold_product_id")
    private Long soldProductId;//FK for Product and Price
    @Column(name = "delivery_address")
    private Long deliveryAddress;//FK for Customers
    @Column(name = "network")
    private String network;
    @Column(name = "amount_of_products")
    private Integer amountOfProducts;
    @Column(name = "shipment_price")
    private Double shipmentPrice;
    @ManyToOne
    @JoinColumn(name = "priceProductId")
    private Price price; // Связь с Price
    @ManyToOne
    @JoinColumn(name = "shipmentAddress")
    private Customer customer; // Связь с Price



}
