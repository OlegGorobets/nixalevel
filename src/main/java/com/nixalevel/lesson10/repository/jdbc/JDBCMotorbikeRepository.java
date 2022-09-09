/*
package com.nixalevel.lesson10.repository.jdbc;

import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.MotorbikeManufacturer;
import com.nixalevel.lesson10.repository.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCMotorbikeRepository implements CrudRepository<Motorbike> {
    private static JDBCMotorbikeRepository instance;
    private final Connection connection;

    public JDBCMotorbikeRepository() {
        connection = JDBCConfig.getConnection();
    }

    public static JDBCMotorbikeRepository getInstance() {
        if (instance == null) {
            instance = new JDBCMotorbikeRepository();
        }
        return instance;
    }

    @Override
    public Motorbike getById(String id) {
        return null;
    }

    private Motorbike mapRowToObject(ResultSet resultSet) throws SQLException {
        return new Motorbike(
                resultSet.getString("motorbike_id"),
                resultSet.getString("motorbike_model"),
                MotorbikeManufacturer.valueOf(resultSet.getString("motorbike_manufacturer")),
                resultSet.getBigDecimal("motorbike_price"),
                resultSet.getInt("motorbike_max_speed"),
                Collections.singletonList(resultSet.getString("motorbike_details")),
                resultSet.getInt("motorbike_count"),
                resultSet.getDate("motorbike_created")
        );
    }

    private void mapObjectToRow(final PreparedStatement preparedStatement, final Motorbike motorbike) throws SQLException {
        preparedStatement.setString(1, motorbike.getId());
        preparedStatement.setString(2, motorbike.getModel());
        preparedStatement.setString(3, motorbike.getMotorbikeManufacturer().toString());
        preparedStatement.setBigDecimal(4, motorbike.getPrice());
        preparedStatement.setInt(5, motorbike.getMaxSpeed());
        preparedStatement.setString(6, motorbike.getDetails().toString());
        preparedStatement.setInt(7, motorbike.getCount());
        preparedStatement.setDate(8, new Date(motorbike.getCreated().getTime()));
    }

    @Override
    public Optional<Motorbike> findById(String id) {
        final String sql = "SELECT * FROM public.\"Motorbike\" WHERE motorbike_id = ?";
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
    public List<Motorbike> getAll() {
        final List<Motorbike> result = new ArrayList<>();
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Motorbike\"");
            while (resultSet.next()) {
                result.add(mapRowToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean create(Motorbike motorbike) {
        if (motorbike == null) {
            throw new IllegalArgumentException("Motorbike must not be null");
        }
        final String sql = "INSERT INTO public.\"Motorbike\"(motorbike_id, motorbike_model, " +
                "motorbike_manufacturer, motorbike_price, motorbike_max_speed, motorbike_details, " +
                "motorbike_count, motorbike_created) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            mapObjectToRow(preparedStatement, motorbike);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(List<Motorbike> motorbike) {
        if (motorbike == null) {
            return false;
        }
        motorbike.forEach(this::create);
        return true;
    }

    @Override
    public boolean update(Motorbike motorbike) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"Motorbike\" SET " +
                "(motorbike_id, motorbike_model, motorbike_manufacturer,motorbike_ price, motorbike_max_speed, " +
                "motorbike_details, motorbike_count, motorbike_created) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) WHERE motorbike_id = ?")) {
            preparedStatement.setString(1, motorbike.getId());
            preparedStatement.setString(2, motorbike.getModel());
            preparedStatement.setString(3, motorbike.getMotorbikeManufacturer().toString());
            preparedStatement.setBigDecimal(4, motorbike.getPrice());
            preparedStatement.setInt(5, motorbike.getMaxSpeed());
            preparedStatement.setString(6, motorbike.getDetails().toString());
            preparedStatement.setInt(7, motorbike.getCount());
            preparedStatement.setDate(8, new Date(motorbike.getCreated().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try (final PreparedStatement statement = connection.prepareStatement("DELETE * FROM public.\"Motorbike\" " +
                "WHERE motorbike_id = ?")) {
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

    public int getCountRowsMotorbikeWithoutInvoiceId() {
        int count = 0;
        final String sql = "SELECT COUNT(*) FROM public.\"Motorbike\" WHERE \"Motorbike\".motorbike_invoice_id ISNULL";
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
            statement.executeUpdate("DELETE FROM public.\"Motorbike\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMotorbikeInvoiceId(String uuid) {
        final String sql = "UPDATE public.\"Motorbike\" SET motorbike_invoice_id = ? WHERE motorbike_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, getRandomRowsMotorbikeWithoutInvoiceId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRandomRowsMotorbikeWithoutInvoiceId() {
        final String sql = "SELECT * FROM public.\"Motorbike\" WHERE \"Motorbike\".motorbike_invoice_id ISNULL " +
                "ORDER BY RANDOM() LIMIT 1";
        String motorbikeId = "";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                motorbikeId = resultSet.getString("motorbike_id");
            }
            return motorbikeId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCountRowsMotorbikeWithInvoiceId() {
        int count = 0;
        final String sql = "SELECT COUNT(*) FROM public.\"Motorbike\" WHERE \"Motorbike\".motorbike_invoice_id NOTNULL";
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
