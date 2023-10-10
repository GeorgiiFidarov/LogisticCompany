package ru.fidarov.LogisticCompany.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fidarov.LogisticCompany.models.Info;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

    @Query("SELECT " +
            "oh.soldProductId," +
            "oh.deliveryAddress," +
            "oh.network," +
            "p.categoryId," +
            "oh.shipmentDate," +
            "oh.amountOfProducts," +
            "pr.attribute, " +
            "pr.percent " +
            "FROM OperationHistory oh " +
            "Inner join Promo pr ON oh.operationId = pr.operationId " +
            "LEFT JOIN Product p ON p.productId = oh.soldProductId")
    List<Object[]> makeInfoTable();
}
