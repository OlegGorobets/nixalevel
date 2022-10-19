package com.nixalevel.lesson10.repository.mongo;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.nixalevel.lesson10.config.GsonUtil;
import com.nixalevel.lesson10.config.MongoUtil;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoMotorbikeRepository implements CrudRepository<Motorbike> {
    private static MongoMotorbikeRepository instance;
    private final MongoCollection<Document> motorbikes;
    private final Gson gson;

    private MongoMotorbikeRepository() {
        motorbikes = MongoUtil.connect("mongo").getCollection("motorbike");
        gson = new GsonUtil().getGson();
    }

    public static MongoMotorbikeRepository getInstance() {
        if (instance == null) {
            instance = new MongoMotorbikeRepository();
        }
        return instance;
    }

    @Override
    public Motorbike getById(String id) {
        return null;
    }

    @Override
    public Optional<Motorbike> findById(String id) {
        return Optional.of(motorbikes.find(new Document("_id", id))
                .map(motorbike -> gson.fromJson(motorbike.toJson(), Motorbike.class))
                .into(new ArrayList<>()).get(0));
    }

    @Override
    public List<Motorbike> getAll() {
        return motorbikes.find()
                .map(motorbike -> gson.fromJson(motorbike.toJson(), Motorbike.class))
                .into(new ArrayList<>());
    }

    @Override
    public boolean create(Motorbike motorbike) {
        motorbikes.insertOne(mapToDoc(motorbike));
        return true;
    }

    @Override
    public boolean create(List<Motorbike> motorbikes) {
        if (motorbikes == null) {
            return false;
        }
        motorbikes.forEach(this::create);
        return true;
    }

    @Override
    public boolean update(Motorbike motorbike) {
        final Document filter = new Document();
        filter.append("_id", motorbike.getId());
        final Document update = mapToDoc(motorbike);
        final Document doc = new Document();
        doc.append("$set", update);
        motorbikes.updateOne(filter, doc);
        return true;
    }

    @Override
    public boolean delete(String id) {
        motorbikes.deleteOne(new Document("_id", id));
        return true;
    }

    @Override
    public void clear() {
        motorbikes.drop();
    }

    private Document mapToDoc(Motorbike motorbike) {
        return Document.parse(gson.toJson(motorbike));
    }
}
