package ru.fidarov.dataUploader.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promo {

    @Id
    @SequenceGenerator(
            name = "promo_id_sequence",
            sequenceName = "promo_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "promo_id_sequence"
    )
    private Long promoId;
    private Long deliveryAddress;
    private String attribute;
    private Double percent;
    private Long operationId;
    private Double soldPrice;
    @ManyToOne
    @JoinColumn(name = "shipmentAddress")
    private Customer customer;
}
