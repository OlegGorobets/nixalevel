package com.nixalevel.lesson10.repository.mongo;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.nixalevel.lesson10.config.GsonUtil;
import com.nixalevel.lesson10.config.MongoUtil;
import com.nixalevel.lesson10.model.Invoice;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoInvoiceRepository {
    private final MongoCollection<Document> invoices;
    private final Gson gson;

    private static MongoInvoiceRepository instance;

    private MongoInvoiceRepository() {
        invoices = MongoUtil.connect("mongo").getCollection("invoice");
        gson = new GsonUtil().getGson();
    }

    public static MongoInvoiceRepository getInstance() {
        if (instance == null) {
            instance = new MongoInvoiceRepository();
        }
        return instance;
    }

    public List<Invoice> getAll() {
        return invoices.find()
                .map(invoice -> gson.fromJson(invoice.toJson(), Invoice.class))
                .into(new ArrayList<>());
    }

    public int getCount() {
        return (int) invoices.countDocuments();
    }

    public boolean create(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice must be not null");
        }
        Document parse = mapToDoc(invoice);
        final List<Document> vehicles = invoice.getVehicles()
                .stream()
                .map(v -> Document.parse(gson.toJson(v)))
                .toList();
        parse.append("vehicles", vehicles);
        invoices.insertOne(parse);
        return true;
    }

    public boolean create(List<Invoice> invoice) {
        if (invoice == null) {
            return false;
        }
        invoice.forEach(this::create);
        return true;
    }

    public boolean update(Invoice invoice) {
        final Document filter = new Document();
        filter.append("_id", invoice.getId());
        final Document update = mapToDoc(invoice);
        final Document doc = new Document();
        doc.append("$set", update);
        invoices.updateOne(filter, doc);
        return true;
    }

    public boolean updateCreated(String id, Date created) {
        final Document filter = new Document();
        filter.append("_id", id);
        final Document update = new Document();
        update.append("created", created.toString());
        final Document doc = new Document();
        doc.append("$set", update);
        invoices.updateOne(filter, doc);
        return true;
    }

    public boolean delete(String id) {
        invoices.deleteOne(new Document("_id", id));
        return true;
    }

    public void clear() {
        invoices.drop();
    }

    private Document mapToDoc(Invoice invoice) {
        return Document.parse(gson.toJson(invoice));
    }
}
