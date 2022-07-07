package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Motorbike;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MotorbikeRepository implements MotorbikeCrudRepository {
    private final List<Motorbike> motorbikes;

    public MotorbikeRepository() {
        motorbikes = new LinkedList<>();
    }

    @Override
    public Motorbike getById(String id) {
        for (Motorbike motorbike : motorbikes) {
            if (motorbike.getId().equals(id)) {
                return motorbike;
            }
        }
        return null;
    }

    @Override
    public List<Motorbike> getAll() {
        return motorbikes;
    }

    @Override
    public boolean create(Motorbike motorbike) {
        motorbikes.add(motorbike);
        return true;
    }

    @Override
    public boolean create(List<Motorbike> motorbike) {
        return motorbikes.addAll(motorbike);
    }

    @Override
    public boolean update(Motorbike motorbike) {
        final Motorbike founded = getById(motorbike.getId());
        if (founded != null) {
            MotorbikeCopy.copy(motorbike, founded);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Motorbike> iterator = motorbikes.iterator();
        while (iterator.hasNext()) {
            final Motorbike motorbike = iterator.next();
            if (motorbike.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private static class MotorbikeCopy {
        static void copy(final Motorbike from, final Motorbike to) {
            to.setAutoManufacturer(from.getAutoManufacturer());
            to.setModel(from.getModel());
            to.setMaxSpeed(from.getMaxSpeed());
            to.setPrice(from.getPrice());
        }
    }
}
