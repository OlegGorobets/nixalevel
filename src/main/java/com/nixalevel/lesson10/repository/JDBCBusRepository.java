package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.BusManufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCBusRepository implements CrudRepository<Bus> {
    private static JDBCBusRepository instance;
    private final Connection connection;

    public JDBCBusRepository() {
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
                resultSet.getString("id"),
                resultSet.getString("model"),
                BusManufacturer.valueOf(resultSet.getString("bus_manufacturer")),
                resultSet.getBigDecimal("price"),
                resultSet.getInt("number_of_seats"),
                Collections.singletonList(resultSet.getString("details")),
                resultSet.getInt("count"),
                resultSet.getDate("created")
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
        final String sql = "SELECT * FROM public.\"Bus\" WHERE id = ?";
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
        final String sql = "INSERT INTO public.\"Bus\"(id, model, bus_manufacturer, price, number_of_seats, details, " +
                "count, created) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
                "(id, model, bus_manufacturer, price, number_of_seats, details, count, created) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) WHERE id = ?")) {
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
                "WHERE id = ?")) {
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

    public void clear() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM public.\"Bus\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
