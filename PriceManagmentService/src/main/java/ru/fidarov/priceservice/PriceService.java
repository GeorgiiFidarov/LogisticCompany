package ru.fidarov.priceservice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class PriceService {

    public List<PriceResponse> getAllPrices() {
        List<PriceResponse> priceResponses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/logisticdb",
                "logisticcompany",
                "password")) {
            String query = "SELECT price_product_id, network, price_per_one_unit FROM price";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long priceProductId = resultSet.getLong("price_product_id");
                    String network = resultSet.getString("network");
                    double pricePerOneUnit = resultSet.getDouble("price_per_one_unit");

                    PriceResponse priceResponse = new PriceResponse(priceProductId, network, pricePerOneUnit);
                    priceResponses.add(priceResponse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return priceResponses;
    }

    public void createPrice(PriceResponse priceResponse) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/logisticdb",
                "logisticcompany",
                "password")) {
            String insertQuery = "INSERT INTO price (price_product_id, network, price_per_one_unit) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setLong(1, priceResponse.priceProductId());
                statement.setString(2, priceResponse.network());
                statement.setDouble(3, priceResponse.pricePerOneUnit());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(PriceResponse priceResponse) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/logisticdb",
                "logisticcompany",
                "password")) {
            String updateQuery = "UPDATE price SET network = ?, price_per_one_unit = ? WHERE price_product_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setString(1, priceResponse.network());
                statement.setDouble(2, priceResponse.pricePerOneUnit());
                statement.setLong(3, priceResponse.priceProductId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePrice(Long priceProductId) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/logisticdb",
                "logisticcompany",
                "password")) {
            String deleteQuery = "DELETE FROM price WHERE price_product_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.setLong(1, priceProductId);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
