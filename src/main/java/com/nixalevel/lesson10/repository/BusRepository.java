package com.nixalevel.lesson10.repository;

import com.nixalevel.lesson10.model.Bus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BusRepository implements BusCrudRepository {
    private final List<Bus> buses;

    public BusRepository() {
        buses = new LinkedList<>();
    }

    @Override
    public Bus getById(String id) {
        for (Bus bus : buses) {
            if (bus.getId().equals(id)) {
                return bus;
            }
        }
        return null;
    }

    @Override
    public Optional<Bus> findById(String id) {
        for (Bus bus : buses) {
            if (bus.getId().equals(id)) {
                return Optional.of(bus);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Bus> getAll() {
        return buses;
    }

    @Override
    public boolean create(Bus bus) {
        if (bus == null) {
            return false;
        } else {
            buses.add(bus);
            return true;
        }
    }

    @Override
    public boolean create(List<Bus> bus) {
        if (bus == null) {
            return false;
        } else {
            return buses.addAll(bus);
        }
    }

    @Override
    public boolean update(Bus bus) {
        final Bus founded = getById(bus.getId());
        if (founded != null) {
            BusCopy.copy(bus, founded);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        final Iterator<Bus> iterator = buses.iterator();
        while (iterator.hasNext()) {
            final Bus bus = iterator.next();
            if (bus.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private static class BusCopy {
        static void copy(final Bus from, final Bus to) {
            to.setBusManufacturer(from.getBusManufacturer());
            to.setModel(from.getModel());
            to.setNumberOfSeats(from.getNumberOfSeats());
            to.setPrice(from.getPrice());
        }
    }
}
