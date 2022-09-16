package com.nixalevel.lesson10.repository.hibernate;

import com.nixalevel.lesson10.config.HibernateFactoryUtil;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateBusRepository implements CrudRepository<Bus> {
    private static HibernateBusRepository instance;
    private final SessionFactory sessionFactory;

    public HibernateBusRepository() {
        sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public static HibernateBusRepository getInstance() {
        if (instance == null) {
            instance = new HibernateBusRepository();
        }
        return instance;
    }

    @Override
    public Bus getById(String id) {
        return null;
    }

    @Override
    public Optional<Bus> findById(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Bus bus = session.get(Bus.class, id);
        transaction.commit();
        session.close();
        if (bus != null) {
            return Optional.of(bus);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Bus> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Bus> result = session.createQuery("from Bus", Bus.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean create(Bus bus) {
        if (bus == null) {
            throw new IllegalArgumentException("Bus must not be null");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bus);
        transaction.commit();
        session.close();
        return true;
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(bus.getId()).isPresent()) {
            Bus newBus = session.get(Bus.class, bus.getId());
            session.update(newBus);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No bus.");
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(id).isPresent()) {
            Bus bus = session.get(Bus.class, id);
            session.delete(bus);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No bus.");
            return false;
        }
    }

    public void clear() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Bus").executeUpdate();
        transaction.commit();
        session.close();
    }
}
