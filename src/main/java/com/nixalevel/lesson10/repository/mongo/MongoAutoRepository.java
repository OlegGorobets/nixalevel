package com.nixalevel.lesson10.repository.mongo;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.nixalevel.lesson10.config.GsonUtil;
import com.nixalevel.lesson10.config.MongoUtil;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.repository.CrudRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoAutoRepository implements CrudRepository<Auto> {
    private static MongoAutoRepository instance;
    private final MongoCollection<Document> autos;
    private final Gson gson;

    private MongoAutoRepository() {
        autos = MongoUtil.connect("mongo").getCollection("auto");
        gson = new GsonUtil().getGson();
    }

    public static MongoAutoRepository getInstance() {
        if (instance == null) {
            instance = new MongoAutoRepository();
        }
        return instance;
    }

    @Override
    public Auto getById(String id) {
        return null;
    }

    @Override
    public Optional<Auto> findById(String id) {
        return Optional.of(autos.find(new Document("_id", id))
                .map(auto -> gson.fromJson(auto.toJson(), Auto.class))
                .into(new ArrayList<>()).get(0));
    }

    @Override
    public List<Auto> getAll() {
        return autos.find()
                .map(auto -> gson.fromJson(auto.toJson(), Auto.class))
                .into(new ArrayList<>());
    }

    @Override
    public boolean create(Auto auto) {
        autos.insertOne(mapToDoc(auto));
        return true;
    }

    @Override
    public boolean create(List<Auto> autos) {
        if (autos == null) {
            return false;
        }
        autos.forEach(this::create);
        return true;
    }

    @Override
    public boolean update(Auto auto) {
        final Document filter = new Document();
        filter.append("_id", auto.getId());
        final Document update = mapToDoc(auto);
        final Document doc = new Document();
        doc.append("$set", update);
        autos.updateOne(filter, doc);
        return true;
    }

    @Override
    public boolean delete(String id) {
        autos.deleteOne(new Document("_id", id));
        return true;
    }

    @Override
    public void clear() {
        autos.drop();
    }

    private Document mapToDoc(Auto auto) {
        return Document.parse(gson.toJson(auto));
    }
}
