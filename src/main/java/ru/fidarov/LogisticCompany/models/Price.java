package ru.fidarov.LogisticCompany.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id
    private Long priceProductId;
    private String network;
    private Double pricePerOneUnit;
}
