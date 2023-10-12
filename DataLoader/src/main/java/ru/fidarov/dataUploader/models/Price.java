package ru.fidarov.dataUploader.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

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
