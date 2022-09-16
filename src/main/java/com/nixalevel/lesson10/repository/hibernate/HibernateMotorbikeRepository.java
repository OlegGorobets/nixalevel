package com.nixalevel.lesson10.repository.hibernate;

import com.nixalevel.lesson10.config.HibernateFactoryUtil;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateMotorbikeRepository implements CrudRepository<Motorbike> {
    private static HibernateMotorbikeRepository instance;
    private final SessionFactory sessionFactory;

    public HibernateMotorbikeRepository() {
        sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public static HibernateMotorbikeRepository getInstance() {
        if (instance == null) {
            instance = new HibernateMotorbikeRepository();
        }
        return instance;
    }


    @Override
    public Motorbike getById(String id) {
        return null;
    }

    @Override
    public Optional<Motorbike> findById(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Motorbike motorbike = session.get(Motorbike.class, id);
        transaction.commit();
        session.close();
        if (motorbike != null) {
            return Optional.of(motorbike);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Motorbike> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Motorbike> result = session.createQuery("from Motorbike", Motorbike.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean create(Motorbike motorbike) {
        if (motorbike == null) {
            throw new IllegalArgumentException("Motorbike must not be null");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(motorbike);
        transaction.commit();
        session.close();
        return true;
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(motorbike.getId()).isPresent()) {
            Motorbike newMotorbike = session.get(Motorbike.class, motorbike.getId());
            session.update(newMotorbike);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No motorbike.");
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(id).isPresent()) {
            Motorbike motorbike = session.get(Motorbike.class, id);
            session.delete(motorbike);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No motorbike.");
            return false;
        }
    }

    public void clear() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Motorbike").executeUpdate();
        transaction.commit();
        session.close();
    }
}
