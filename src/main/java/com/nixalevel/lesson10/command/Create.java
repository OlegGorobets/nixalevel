package com.nixalevel.lesson10.command;

import com.nixalevel.lesson10.model.VehicleType;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import com.nixalevel.lesson10.utility.UserInputUtil;

import java.util.ArrayList;
import java.util.List;

public class Create implements Command{
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final MotorbikeService MOTORBIKE_SERVICE = MotorbikeService.getInstance();
    @Override
    public void execute() {
        final VehicleType[] values = VehicleType.values();
        final List<String> names = getNames(values);
        final int userInput = UserInputUtil.getUserInput("What you want to create:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case AUTO -> AUTO_SERVICE.createVehicles(1);
            case BUS -> BUS_SERVICE.createVehicles(1);
            case MOTORBIKE -> MOTORBIKE_SERVICE.createVehicles(1);
            default -> throw new IllegalArgumentException("Cannot create " + value);
        }
    }

    private static List<String> getNames(VehicleType[] values) {
        final List<String> names = new ArrayList<>(values.length);
        for (VehicleType type : values) {
            names.add(type.name());
        }
        return names;
    }
}
