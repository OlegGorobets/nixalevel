package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.config.HibernateFactoryUtil;
import com.nixalevel.lesson10.config.JDBCConfig;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HibernateAutoRepository implements CrudRepository<Auto> {
    private static HibernateAutoRepository instance;
    private final SessionFactory sessionFactory;

    public HibernateAutoRepository() {
        sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public static HibernateAutoRepository getInstance() {
        if (instance == null) {
            instance = new HibernateAutoRepository();
        }
        return instance;
    }

    @Override
    public Auto getById(String id) {
        return null;
    }

    @Override
    public Optional<Auto> findById(String id) {
        final String sql = "SELECT * FROM public.\"Auto\" WHERE auto_id = ?";
        return Optional.empty();
    }

    @Override
    public List<Auto> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Auto> result = session.createQuery("from Auto", Auto.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean create(Auto auto) {
        if (auto == null) {
            throw new IllegalArgumentException("Auto must not be null");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(auto);
        transaction.commit();
        session.close();
        return true;
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

        return false;
    }

    @Override
    public boolean delete(String id) {

        return true;
    }

    public void clear() {

    }
}
