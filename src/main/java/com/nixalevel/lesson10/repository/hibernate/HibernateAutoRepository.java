package com.nixalevel.lesson10.repository.hibernate;

import com.nixalevel.lesson10.config.HibernateFactoryUtil;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Auto auto = session.get(Auto.class, id);
        transaction.commit();
        session.close();
        if (auto != null) {
            return Optional.of(auto);
        } else {
            return Optional.empty();
        }
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(auto.getId()).isPresent()) {
            Auto newAuto = session.get(Auto.class, auto.getId());
            session.update(newAuto);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No auto.");
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(id).isPresent()) {
            Auto auto = session.get(Auto.class, id);
            session.delete(auto);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No auto.");
            return false;
        }
    }

    public void clear() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Auto")
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
