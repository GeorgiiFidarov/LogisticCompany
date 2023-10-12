package ru.fidarov.dataUploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.dataUploader.models.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
}
