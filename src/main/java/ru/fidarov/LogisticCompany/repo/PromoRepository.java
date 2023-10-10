package ru.fidarov.LogisticCompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.LogisticCompany.models.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
}
