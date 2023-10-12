package ru.fidarov.dataUploader.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.dataUploader.models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
