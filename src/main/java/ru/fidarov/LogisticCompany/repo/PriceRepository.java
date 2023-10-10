package ru.fidarov.LogisticCompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.LogisticCompany.models.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
