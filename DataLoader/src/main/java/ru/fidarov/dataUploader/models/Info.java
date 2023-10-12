package ru.fidarov.dataUploader.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Info {
    @Id
    @SequenceGenerator(
            name = "info_id_sequence",
            sequenceName = "info_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "info_id_sequence"
    )
    private Long infoId;
    private Long ProductId;
    private Long shipmentId;
    private String network; // from operation_history.network
    private Long categoryId; // from product.categoryId
    private LocalDate date; // from operation_history.shipmentDate
    private Integer amountOfSoldProduct; // from promo.attribute
    private String isPromo; // from promo.attribute
    private String percent;// difference between operation_history.shipmentPrice/amountOfProducts and price.pricePerOneUnit in percents
}
