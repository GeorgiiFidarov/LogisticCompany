package ru.fidarov.LogisticCompany.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.LogisticCompany.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
