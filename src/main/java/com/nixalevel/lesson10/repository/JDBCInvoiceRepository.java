package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.model.Invoice;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCInvoiceRepository {
    private static JDBCInvoiceRepository instance;
    private final Connection connection;

    public JDBCInvoiceRepository() {
        connection = JDBCConfig.getConnection();
    }

    public static JDBCInvoiceRepository getInstance() {
        if (instance == null) {
            instance = new JDBCInvoiceRepository();
        }
        return instance;
    }

    /*private Invoice mapRowToObject(ResultSet resultSet) throws SQLException {
        return new Invoice(
                resultSet.getString("id"),
                resultSet.getDate("created"),
                resultSet.getString("vehicles")
                );
    }*/
    private void mapObjectToRow(final PreparedStatement preparedStatement, final Invoice invoice) throws SQLException {
        preparedStatement.setString(1, invoice.getId());
        preparedStatement.setDate(2, new Date(invoice.getCreated().getTime()));
        preparedStatement.setString(3, invoice.getVehicles().toString());
    }

    /*public Optional<Invoice> findById(String id) {
        final String sql = "SELECT * FROM public.\"Auto\" WHERE id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRowToObject(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public boolean create(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice must not be null");
        }
        final String sql = "INSERT INTO public.\"Invoice\"(id, created, vehicles) VALUES (?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            mapObjectToRow(preparedStatement, invoice);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public boolean create(List<Auto> auto) {
        if (auto == null) {
            return false;
        }
        auto.forEach(this::create);
        return true;
    }*/
}
