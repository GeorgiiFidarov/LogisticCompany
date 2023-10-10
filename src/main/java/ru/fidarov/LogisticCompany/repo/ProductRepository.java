package ru.fidarov.LogisticCompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.LogisticCompany.models.OperationHistory;
import ru.fidarov.LogisticCompany.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
