package ru.fidarov.dataUploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.dataUploader.models.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
