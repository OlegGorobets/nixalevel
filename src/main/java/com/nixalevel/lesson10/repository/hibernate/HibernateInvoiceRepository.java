package com.nixalevel.lesson10.repository.hibernate;

import com.nixalevel.lesson10.config.HibernateFactoryUtil;
import com.nixalevel.lesson10.model.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class HibernateInvoiceRepository {
    private static HibernateInvoiceRepository instance;
    private final SessionFactory sessionFactory;

    private HibernateInvoiceRepository() {
        sessionFactory = HibernateFactoryUtil.getSessionFactory();
    }

    public static HibernateInvoiceRepository getInstance() {
        if (instance == null) {
            instance = new HibernateInvoiceRepository();
        }
        return instance;
    }

    public List<Invoice> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Invoice> result = session.createQuery("from Invoice", Invoice.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    public int getCount() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int result = session.createQuery("from Invoice", Invoice.class).list().size();
        transaction.commit();
        session.close();
        return result;
    }

    public boolean create(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice must not be null");
        }
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(invoice);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean create(List<Invoice> invoice) {
        if (invoice == null) {
            return false;
        }
        invoice.forEach(this::create);
        return true;
    }

    public Optional<Invoice> findById(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Invoice invoice = session.get(Invoice.class, id);
        transaction.commit();
        session.close();
        if (invoice != null) {
            return Optional.of(invoice);
        } else {
            return Optional.empty();
        }
    }

    public boolean update(Invoice invoice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(invoice.getId()).isPresent()) {
            Invoice newInvoice = session.get(Invoice.class, invoice.getId());
            session.update(newInvoice);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No invoice.");
            return false;
        }
    }

    public boolean updateCreated(String id, Date date) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(id).isPresent()) {
            Invoice invoice = session.get(Invoice.class, id);
            invoice.setCreated(date);
            session.update(invoice);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No invoice.");
            return false;
        }
    }

    public boolean delete(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (findById(id).isPresent()) {
            Invoice invoice = session.get(Invoice.class, id);
            session.delete(invoice);
            transaction.commit();
            session.close();
            return true;
        } else {
            System.out.println("No invoice.");
            return false;
        }
    }

    public void clear() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Invoice").executeUpdate();
        transaction.commit();
        session.close();
    }
}
