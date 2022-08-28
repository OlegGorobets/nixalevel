package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCAutoRepository implements CrudRepository<Auto> {
    private static JDBCAutoRepository instance;
    private final Connection connection;

    public JDBCAutoRepository() {
        connection = JDBCConfig.getConnection();
    }

    public static JDBCAutoRepository getInstance() {
        if (instance == null) {
            instance = new JDBCAutoRepository();
        }
        return instance;
    }

    @Override
    public Auto getById(String id) {
        return null;
    }

    private Auto mapRowToObject(ResultSet resultSet) throws SQLException {
        return new Auto(
                resultSet.getString("id"),
                resultSet.getString("model"),
                AutoManufacturer.valueOf(resultSet.getString("auto_manufacturer")),
                resultSet.getBigDecimal("price"),
                resultSet.getString("body_type"),
                Collections.singletonList(resultSet.getString("details")),
                resultSet.getInt("count"),
                resultSet.getDate("created")
        );
    }

    private void mapObjectToRow(final PreparedStatement preparedStatement, final Auto auto) throws SQLException {
        preparedStatement.setString(1, auto.getId());
        preparedStatement.setString(2, auto.getModel());
        preparedStatement.setString(3, auto.getAutoManufacturer().toString());
        preparedStatement.setBigDecimal(4, auto.getPrice());
        preparedStatement.setString(5, auto.getBodyType());
        preparedStatement.setString(6, auto.getDetails().toString());
        preparedStatement.setInt(7, auto.getCount());
        preparedStatement.setDate(8, new Date(auto.getCreated().getTime()));
    }

    @Override
    public Optional<Auto> findById(String id) {
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
    }

    @Override
    public List<Auto> getAll() {
        final List<Auto> result = new ArrayList<>();
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Auto\"");
            while (resultSet.next()) {
                result.add(mapRowToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean create(Auto auto) {
        if (auto == null) {
            throw new IllegalArgumentException("Auto must not be null");
        }
        final String sql = "INSERT INTO public.\"Auto\"(id, model, auto_manufacturer, price, body_type, details, " +
                "count, created) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            mapObjectToRow(preparedStatement, auto);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(List<Auto> auto) {
        if (auto == null) {
            return false;
        }
        auto.forEach(this::create);
        return true;
    }

    @Override
    public boolean update(Auto auto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"Auto\" SET " +
                "(id, model, auto_manufacturer, price, body_type, details, count, created) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) WHERE id = ?")) {
            preparedStatement.setString(1, auto.getId());
            preparedStatement.setString(2, auto.getModel());
            preparedStatement.setString(3, auto.getAutoManufacturer().toString());
            preparedStatement.setBigDecimal(4, auto.getPrice());
            preparedStatement.setString(5, auto.getBodyType());
            preparedStatement.setString(6, auto.getDetails().toString());
            preparedStatement.setInt(7, auto.getCount());
            preparedStatement.setDate(8, new Date(auto.getCreated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (final PreparedStatement statement = connection.prepareStatement("DELETE * FROM public.\"Auto\" " +
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
            statement.executeUpdate("DELETE FROM public.\"Auto\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
