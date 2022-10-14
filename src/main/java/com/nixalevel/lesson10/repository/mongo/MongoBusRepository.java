package com.nixalevel.lesson10.repository.mongo;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.nixalevel.lesson10.config.GsonUtil;
import com.nixalevel.lesson10.config.MongoUtil;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoBusRepository implements CrudRepository<Bus> {
    private static MongoBusRepository instance;
    private final MongoCollection<Document> buses;
    private final Gson gson;

    private MongoBusRepository() {
        buses = MongoUtil.connect("mongo").getCollection("bus");
        gson = new GsonUtil().getGson();
    }

    public static MongoBusRepository getInstance() {
        if (instance == null) {
            instance = new MongoBusRepository();
        }
        return instance;
    }
    @Override
    public Bus getById(String id) {
        return null;
    }

    @Override
    public Optional<Bus> findById(String id) {
        return Optional.of(buses.find(new Document("_id", id))
                .map(bus -> gson.fromJson(bus.toJson(), Bus.class))
                .into(new ArrayList<>()).get(0));
    }

    @Override
    public List<Bus> getAll() {
        return buses.find()
                .map(bus -> gson.fromJson(bus.toJson(), Bus.class))
                .into(new ArrayList<>());
    }

    @Override
    public boolean create(Bus bus) {
        buses.insertOne(mapToDoc(bus));
        return true;
    }

    @Override
    public boolean create(List<Bus> buses) {
        if (buses == null) {
            return false;
        }
        buses.forEach(this::create);
        return true;
    }

    @Override
    public boolean update(Bus bus) {
        final Document filter = new Document();
        filter.append("_id", bus.getId());
        final Document update = mapToDoc(bus);
        final Document doc = new Document();
        doc.append("$set", update);
        buses.updateOne(filter, doc);
        return true;
    }

    @Override
    public boolean delete(String id) {
        buses.deleteOne(new Document("_id", id));
        return true;
    }

    @Override
    public void clear() {
        buses.drop();
    }

    private Document mapToDoc(Bus bus) {
        return Document.parse(gson.toJson(bus));
    }
}
