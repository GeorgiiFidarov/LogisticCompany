package ru.fidarov.infoservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class InfoService {

    public List<InfoResponse> getAllInfo() {
        List<InfoResponse> infoResponses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/logisticdb",
                "logisticcompany",
                "password")) {
            String query = "SELECT (*) FROM info";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long infoId = resultSet.getLong("info_id");
                    long productId = resultSet.getLong("product_id");
                    long shipmentId = resultSet.getLong("shipment_id");
                    String network = resultSet.getString("network");
                    long categoryId = resultSet.getLong("category_id");
                    LocalDate date = resultSet.getDate("date").toLocalDate();
                    int amountOfSoldProduct = resultSet.getInt("amount_of_sold_product");
                    String isPromo = resultSet.getString("is_promo");
                    String percent = resultSet.getString("percent");

                    InfoResponse infoResponse = new InfoResponse(infoId, productId, shipmentId, network, categoryId, date, amountOfSoldProduct, isPromo, percent);
                    infoResponses.add(infoResponse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return infoResponses;
    }

}
