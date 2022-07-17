package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Motorbike;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Motorbike> findById(String id) {
        for (Motorbike motorbike : motorbikes) {
            if (motorbike.getId().equals(id)) {
                return Optional.of(motorbike);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Motorbike> getAll() {
        return motorbikes;
    }

    @Override
    public boolean create(Motorbike motorbike) {
        if (motorbike == null) {
            return false;
        } else {
            motorbikes.add(motorbike);
            return true;
        }
    }

    @Override
    public boolean create(List<Motorbike> motorbike) {
        if (motorbike == null) {
            return false;
        } else {
            return motorbikes.addAll(motorbike);
        }
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
            to.setMotorbikeManufacturer(from.getMotorbikeManufacturer());
            to.setModel(from.getModel());
            to.setMaxSpeed(from.getMaxSpeed());
            to.setPrice(from.getPrice());
        }
    }
}
