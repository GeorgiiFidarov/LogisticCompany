package ru.fidarov.dataUploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.dataUploader.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
