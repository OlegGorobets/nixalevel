/*
package com.nixalevel.lesson10.repository.jdbc;

import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JDBCInvoiceRepository {
    private static final JDBCAutoRepository JDBC_AUTO_REPOSITORY = new JDBCAutoRepository();
    private static final JDBCBusRepository JDBC_BUS_REPOSITORY = new JDBCBusRepository();
    private static final JDBCMotorbikeRepository JDBC_MOTORBIKE_REPOSITORY = new JDBCMotorbikeRepository();
    private static JDBCInvoiceRepository instance;

    private final Connection connection;

    private JDBCInvoiceRepository() {
        connection = JDBCConfig.getConnection();
    }

    public static JDBCInvoiceRepository getInstance() {
        if (instance == null) {
            instance = new JDBCInvoiceRepository();
        }
        return instance;
    }

    public boolean create(String uuid, java.util.Date date) {
        final String sql = "INSERT INTO public.\"Invoice\"(id, created) VALUES (?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, uuid);
            preparedStatement.setDate(2, new Date(date.getTime()));
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM public.\"Invoice\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCountRowsWithoutInvoiceId() {
        return JDBC_AUTO_REPOSITORY.getCountRowsAutoWithoutInvoiceId() +
                JDBC_BUS_REPOSITORY.getCountRowsBusWithoutInvoiceId() +
                JDBC_MOTORBIKE_REPOSITORY.getCountRowsMotorbikeWithoutInvoiceId();
    }

    public void setInvoiceId(String uuid, int vehicleNumber) {
        for (int i = 0; i < vehicleNumber; i++) {
            if (JDBC_AUTO_REPOSITORY.getCountRowsAutoWithoutInvoiceId() != 0) {
                JDBC_AUTO_REPOSITORY.setAutoInvoiceId(uuid);
            } else if (JDBC_BUS_REPOSITORY.getCountRowsBusWithoutInvoiceId() != 0) {
                JDBC_BUS_REPOSITORY.setBusInvoiceId(uuid);
            } else if (JDBC_MOTORBIKE_REPOSITORY.getCountRowsMotorbikeWithoutInvoiceId() != 0) {
                JDBC_MOTORBIKE_REPOSITORY.setMotorbikeInvoiceId(uuid);
            }
        }
    }

    public int getCountRowsWithInvoiceId() {
        return JDBC_AUTO_REPOSITORY.getCountRowsAutoWithInvoiceId() +
                JDBC_BUS_REPOSITORY.getCountRowsBusWithInvoiceId() +
                JDBC_MOTORBIKE_REPOSITORY.getCountRowsMotorbikeWithInvoiceId();
    }

    public boolean updateCreatedInvoice(String id) {
        String sql = "UPDATE public.\"Invoice\" SET created = ? WHERE id = ?";
        java.util.Date date = new java.util.Date();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new Date(date.getTime()));
            preparedStatement.setString(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private Auto mapAutoRowToObject(ResultSet resultSet) throws SQLException {
        return new Auto(
                resultSet.getString("auto_id"),
                resultSet.getString("auto_model"),
                AutoManufacturer.valueOf(resultSet.getString("auto_manufacturer")),
                resultSet.getBigDecimal("auto_price"),
                resultSet.getString("auto_body_type"),
                Collections.singletonList(resultSet.getString("auto_details")),
                resultSet.getInt("auto_count"),
                resultSet.getDate("auto_created")
        );
    }

    private Bus mapBusRowToObject(ResultSet resultSet) throws SQLException {
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

    private Motorbike mapMotorbikeRowToObject(ResultSet resultSet) throws SQLException {
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

    private List<Invoice> getAllAuto() {
        final List<Invoice> result = new ArrayList<>();
        final String sql = "SELECT * FROM public.\"Invoice\" LEFT JOIN public.\"Auto\" ON \"Auto\".auto_invoice_id = " +
                "\"Invoice\".id WHERE \"Auto\".auto_id NOTNULL";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new Invoice(
                        resultSet.getString("id"),
                        resultSet.getDate("created"),
                        List.of(mapAutoRowToObject(resultSet))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private List<Invoice> getAllBus() {
        final List<Invoice> result = new ArrayList<>();
        final String sql = "SELECT * FROM public.\"Invoice\" LEFT JOIN public.\"Bus\" ON \"Bus\".bus_invoice_id = " +
                "\"Invoice\".id WHERE \"Bus\".bus_id NOTNULL";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new Invoice(
                        resultSet.getString("id"),
                        resultSet.getDate("created"),
                        List.of(mapBusRowToObject(resultSet))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private List<Invoice> getAllMotorbike() {
        final List<Invoice> result = new ArrayList<>();
        final String sql = "SELECT * FROM public.\"Invoice\" LEFT JOIN public.\"Motorbike\" ON " +
                "\"Motorbike\".motorbike_invoice_id = \"Invoice\".id WHERE \"Motorbike\".motorbike_id NOTNULL";
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new Invoice(
                        resultSet.getString("id"),
                        resultSet.getDate("created"),
                        List.of(mapMotorbikeRowToObject(resultSet))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Invoice> getAll() {
        return Stream.of(getAllAuto(), getAllBus(), getAllMotorbike())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Map<String, BigDecimal> getInvoiceWithAmount() {
        Map<String, BigDecimal> map = new HashMap<>();
        BigDecimal[] sum = new BigDecimal[getAllInvoiceId().size()];
        final String sql = "SELECT * FROM public.\"Invoice\" LEFT JOIN public.\"Auto\" ON \"Auto\".auto_invoice_id = " +
                "\"Invoice\".id LEFT JOIN public.\"Bus\" ON \"Bus\".bus_invoice_id = \"Invoice\".id LEFT JOIN " +
                "public.\"Motorbike\" ON \"Motorbike\".motorbike_invoice_id = \"Invoice\".id WHERE \"Invoice\".id = ?";
        for (int i = 0; i < getAllInvoiceId().size(); i++) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, getAllInvoiceId().get(i));
                final ResultSet resultSet = preparedStatement.executeQuery();
                sum[i] = BigDecimal.ZERO;
                while (resultSet.next()) {
                    if (resultSet.getBigDecimal("auto_price") != null) {
                        sum[i] = sum[i].add(resultSet.getBigDecimal("auto_price"));
                    }
                    if (resultSet.getBigDecimal("bus_price") != null) {
                        sum[i] = sum[i].add(resultSet.getBigDecimal("bus_price"));
                    }
                    if (resultSet.getBigDecimal("motorbike_price") != null) {
                        sum[i] = sum[i].add(resultSet.getBigDecimal("motorbike_price"));
                    }
                }
                map.put(getAllInvoiceId().get(i), sum[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public Map<String, BigDecimal> groupInvoiceByAmount() {
        Map<String, BigDecimal> map = new HashMap<>();
        BigDecimal[] sum = new BigDecimal[getAllInvoiceId().size()];
        final String sql = "SELECT \"Invoice\".id, SUM(\"Auto\".auto_price) AS auto_price, SUM(\"Bus\".bus_price) " +
                "AS bus_price, SUM(\"Motorbike\".motorbike_price) AS motorbike_price FROM public.\"Invoice\" LEFT " +
                "JOIN public.\"Auto\" ON \"Auto\".auto_invoice_id = \"Invoice\".id LEFT JOIN public.\"Bus\" ON " +
                "\"Bus\".bus_invoice_id = \"Invoice\".id LEFT JOIN public.\"Motorbike\" ON " +
                "\"Motorbike\".motorbike_invoice_id = \"Invoice\".id WHERE \"Invoice\".id = ? GROUP BY \"Invoice\".id";
        for (int i = 0; i < getAllInvoiceId().size(); i++) {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, getAllInvoiceId().get(i));
                final ResultSet resultSet = preparedStatement.executeQuery();
                sum[i] = BigDecimal.ZERO;
                while (resultSet.next()) {
                    if (resultSet.getBigDecimal("auto_price") != null) {
                        sum[i] = sum[i].add(resultSet.getBigDecimal("auto_price"));
                    }
                    if (resultSet.getBigDecimal("bus_price") != null) {
                        sum[i] = sum[i].add(resultSet.getBigDecimal("bus_price"));
                    }
                    if (resultSet.getBigDecimal("motorbike_price") != null) {
                        sum[i] = sum[i].add(resultSet.getBigDecimal("motorbike_price"));
                    }
                }
                map.put(getAllInvoiceId().get(i), sum[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private List<String> getAllInvoiceId() {
        final String sql = "SELECT * FROM public.\"Invoice\"";
        List<String> invoiceId = new ArrayList<>();
        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                invoiceId.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoiceId;
    }
}
*/
