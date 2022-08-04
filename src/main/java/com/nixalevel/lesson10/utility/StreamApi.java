package com.nixalevel.lesson10.utility;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;
import com.nixalevel.lesson10.model.Vehicle;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamApi {
    public List<String> findVehiclesPriceExpensiveThan(Number x, List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(v -> v.getPrice().compareTo(new BigDecimal(x.toString())) > 0)
                .map(Vehicle::getModel)
                .collect(Collectors.toList());
    }

    public String calculateVehiclesPriceSum(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(Vehicle::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .toString();
    }

    public Map<String, String> sortVehiclesByModel(List<Vehicle> vehicles) {
        return vehicles.stream()
                .collect(Collectors.toMap(Vehicle::getId, Vehicle::getModel))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, LinkedHashMap::new));
    }

    public String checkDetail(List<Vehicle> vehicles, String checkDetail) {
        return "Detail available for all vehicles: " + vehicles.stream()
                .map(Vehicle::getDetails)
                .anyMatch(x -> x != null && x.contains(checkDetail));
    }

    public DoubleSummaryStatistics getStatistics(List<Vehicle> vehicles) {
        return vehicles.stream()
                .collect(Collectors.summarizingDouble(value -> value.getPrice().doubleValue()));
    }

    public String checkPriceForZeroAndNull(List<Vehicle> vehicles) {
        Predicate<BigDecimal> nonNullOrZero = x -> x != null && !x.equals(BigDecimal.ZERO);
        return "All vehicles have a price: " + vehicles.stream()
                .allMatch(vehicle -> nonNullOrZero.test(vehicle.getPrice()));
    }

    @FunctionalInterface
    public interface Function {
        Auto getAuto(String model, AutoManufacturer autoManufacturer, BigDecimal price, String bodyType, List<String> details);
    }

    public List<Auto> getAuto(Map<String, Auto> map) {
        List<Auto> autos = new LinkedList<>();
        Function function = Auto::new;
        for (Map.Entry<String, Auto> pair : map.entrySet()) {
            autos.add(function.getAuto(pair.getValue().getModel(),
                    pair.getValue().getAutoManufacturer(),
                    pair.getValue().getPrice(),
                    pair.getValue().getBodyType(),
                    pair.getValue().getDetails()));
        }
        return autos;
    }
}
