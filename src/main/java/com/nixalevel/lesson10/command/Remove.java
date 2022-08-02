package com.nixalevel.lesson10.command;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.VehicleType;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import com.nixalevel.lesson10.utility.UserInputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Remove implements Command {
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final MotorbikeService MOTORBIKE_SERVICE = MotorbikeService.getInstance();

    @Override
    public void execute() {
        final List<String> autoList = AUTO_SERVICE.getAll().stream().map(Auto::toString).collect(Collectors.toList());
        final List<String> busList = BUS_SERVICE.getAll().stream().map(Bus::toString).collect(Collectors.toList());
        final List<String> motorbikeList = MOTORBIKE_SERVICE.getAll().stream().map(Motorbike::toString).collect(Collectors.toList());

        final VehicleType[] values = VehicleType.values();
        final List<String> names = getNames(values);
        final int userInput = UserInputUtil.getUserInput("What you want to remove:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case AUTO ->
                    AUTO_SERVICE.remove(UserInputUtil.getUserInput("What you want to remove from Auto:", autoList));
            case BUS -> BUS_SERVICE.remove(UserInputUtil.getUserInput("What you want to remove from Bus:", busList));
            case MOTORBIKE ->
                    MOTORBIKE_SERVICE.remove(UserInputUtil.getUserInput("What you want to remove from Motorbike:", motorbikeList));
            default -> throw new IllegalArgumentException("Cannot change " + value);
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
