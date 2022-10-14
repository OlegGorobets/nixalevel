/*
package com.nixalevel.lesson10.repository.jdbc;

import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;
import com.nixalevel.lesson10.repository.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCBusRepository implements CrudRepository<Bus> {
    private static JDBCBusRepository instance;
    private final Connection connection;

    private JDBCBusRepository() {
        connection = JDBCConfig.getConnection();
    }

    public static JDBCBusRepository getInstance() {
        if (instance == null) {
            instance = new JDBCBusRepository();
        }
        return instance;
    }

    @Override
    public Bus getById(String id) {
        return null;
    }


    private Bus mapRowToObject(ResultSet resultSet) throws SQLException {
        return new Bus(
                resultSet.getString("bus_id"),
                resultSet.getString("bus_model"),
                BusManufacturer.valueOf(resultSet.getString("bus_manufacturer")),
                resultSet.getBigDecimal("bus_price"),
                resultSet.getInt("bus_number_of_seats"),
                Collections.singletonList(resultSet.getString("bus_details")),
                resultSet.getInt("bus_count"),
                resultSet.getDate("bus_created")
        );
    }

    private void mapObjectToRow(final PreparedStatement preparedStatement, final Bus bus) throws SQLException {
        preparedStatement.setString(1, bus.getId());
        preparedStatement.setString(2, bus.getModel());
        preparedStatement.setString(3, bus.getBusManufacturer().toString());
        preparedStatement.setBigDecimal(4, bus.getPrice());
        preparedStatement.setInt(5, bus.getNumberOfSeats());
        preparedStatement.setString(6, bus.getDetails().toString());
        preparedStatement.setInt(7, bus.getCount());
        preparedStatement.setDate(8, new Date(bus.getCreated().getTime()));
    }

    @Override
    public Optional<Bus> findById(String id) {
        final String sql = "SELECT * FROM public.\"Bus\" WHERE bus_id = ?";
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
    }

    @Override
    public List<Bus> getAll() {
        final List<Bus> result = new ArrayList<>();
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Bus\"");
            while (resultSet.next()) {
                result.add(mapRowToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean create(Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("Auto must not be null");
        }
        final String sql = "INSERT INTO public.\"Bus\"(bus_id, bus_model, bus_manufacturer, bus_price, " +
                "bus_number_of_seats, bus_details, bus_count, bus_created) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            mapObjectToRow(preparedStatement, bus);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(List<Bus> bus) {
        if (bus == null) {
            return false;
        }
        bus.forEach(this::create);
        return true;
    }

    @Override
    public boolean update(Bus bus) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"Bus\" SET " +
                "(bus_id, bus_model, bus_manufacturer, bus_price, bus_number_of_seats, bus_details, bus_count, " +
                "bus_created) VALUES (?, ?, ?, ?, ?, ?, ?, ?) WHERE bus_id = ?")) {
            preparedStatement.setString(1, bus.getId());
            preparedStatement.setString(2, bus.getModel());
            preparedStatement.setString(3, bus.getBusManufacturer().toString());
            preparedStatement.setBigDecimal(4, bus.getPrice());
            preparedStatement.setInt(5, bus.getNumberOfSeats());
            preparedStatement.setString(6, bus.getDetails().toString());
            preparedStatement.setInt(7, bus.getCount());
            preparedStatement.setDate(8, new Date(bus.getCreated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (final PreparedStatement statement = connection.prepareStatement("DELETE * FROM public.\"Bus\" " +
                "WHERE bus_id = ?")) {
            statement.setString(1, id);
            if (findById(id).isPresent()) {
                statement.executeUpdate();
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getCountRowsBusWithoutInvoiceId() {
        int count = 0;
        final String sql = "SELECT COUNT(*) FROM public.\"Bus\" WHERE \"Bus\".bus_invoice_id ISNULL";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count += resultSet.getInt("count");
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM public.\"Bus\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBusInvoiceId(String uuid) {
        final String sql = "UPDATE public.\"Bus\" SET bus_invoice_id = ? WHERE bus_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, getRandomRowsBusWithoutInvoiceId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRandomRowsBusWithoutInvoiceId() {
        final String sql = "SELECT * FROM public.\"Bus\" WHERE \"Bus\".bus_invoice_id ISNULL ORDER BY RANDOM() LIMIT 1";
        String busId = "";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                busId = resultSet.getString("bus_id");
            }
            return busId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCountRowsBusWithInvoiceId() {
        int count = 0;
        final String sql = "SELECT COUNT(*) FROM public.\"Bus\" WHERE \"Bus\".bus_invoice_id NOTNULL";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                count += resultSet.getInt("count");
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
*/
