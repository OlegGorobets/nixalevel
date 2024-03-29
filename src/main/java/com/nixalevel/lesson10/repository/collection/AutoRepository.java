package com.nixalevel.lesson10.repository.collection;

import com.nixalevel.lesson10.annotation.Autowired;
import com.nixalevel.lesson10.annotation.Singleton;
import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.repository.CrudRepository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Singleton
public class AutoRepository implements CrudRepository<Auto> {
    private final List<Auto> autos;

    private static AutoRepository instance;

    @Autowired
    private AutoRepository() {
        autos = new LinkedList<>();
    }

    public static AutoRepository getInstance() {
        if (instance == null) {
            instance = new AutoRepository();
        }
        return instance;
    }

    @Override
    public Auto getById(String id) {
        for (Auto auto : autos) {
            if (auto.getId().equals(id)) {
                return auto;
            }
        }
        return null;
    }

    @Override
    public Optional<Auto> findById(String id) {
        for (Auto auto : autos) {
            if (auto.getId().equals(id)) {
                return Optional.of(auto);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Auto> getAll() {
        return autos;
    }

    @Override
    public boolean create(Auto auto) {
        if (auto == null) {
            return false;
        } else {
            autos.add(auto);
            return true;
        }
    }

    @Override
    public boolean create(List<Auto> auto) {
        if (auto == null) {
            return false;
        } else {
            return autos.addAll(auto);
        }
    }

    @Override
    public boolean update(Auto auto) {
        final Auto founded = getById(auto.getId());
        if (founded != null) {
            AutoCopy.copy(auto, founded);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Auto> iterator = autos.iterator();
        while (iterator.hasNext()) {
            final Auto auto = iterator.next();
            if (auto.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {

    }

    private static class AutoCopy {
        static void copy(final Auto from, final Auto to) {
            to.setAutoManufacturer(from.getAutoManufacturer());
            to.setModel(from.getModel());
            to.setBodyType(from.getBodyType());
            to.setPrice(from.getPrice());
        }
    }
}
