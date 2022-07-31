package com.nixalevel.lesson10.command;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.Bus;
import com.nixalevel.lesson10.model.Motorbike;
import com.nixalevel.lesson10.model.VehicleType;
import com.nixalevel.lesson10.repository.AutoRepository;
import com.nixalevel.lesson10.repository.BusRepository;
import com.nixalevel.lesson10.repository.MotorbikeRepository;
import com.nixalevel.lesson10.service.AutoService;
import com.nixalevel.lesson10.service.BusService;
import com.nixalevel.lesson10.service.MotorbikeService;
import com.nixalevel.lesson10.utility.UserInputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Change implements Command{
    private static final AutoService AUTO_SERVICE = AutoService.getInstance();
    private static final BusService BUS_SERVICE = BusService.getInstance();
    private static final MotorbikeService MOTORBIKE_SERVICE = MotorbikeService.getInstance();

    private static final AutoRepository AUTO_REPOSITORY = AutoRepository.getInstance();
    private static final BusRepository BUS_REPOSITORY = BusRepository.getInstance();
    private static final MotorbikeRepository MOTORBIKE_REPOSITORY = MotorbikeRepository.getInstance();
    @Override
    public void execute() {
        final List<String> autoList = AUTO_REPOSITORY.getAll().stream().map(Auto::toString).collect(Collectors.toList());
        final List<String> busList = BUS_REPOSITORY.getAll().stream().map(Bus::toString).collect(Collectors.toList());
        final List<String> motorbikeList = MOTORBIKE_REPOSITORY.getAll().stream().map(Motorbike::toString).collect(Collectors.toList());

        final VehicleType[] values = VehicleType.values();
        final List<String> names = getNames(values);
        final int userInput = UserInputUtil.getUserInput("What you want to change:", names);
        final VehicleType value = values[userInput];

        switch (value) {
            case AUTO -> AUTO_SERVICE.change(UserInputUtil.getUserInput("What you want to change from Auto:", autoList),
                    UserInputUtil.getUserInputChangeString("Enter bodyType:"));
            case BUS -> BUS_SERVICE.change(UserInputUtil.getUserInput("What you want to change from Bus:", busList),
                    UserInputUtil.getUserInputChangeInt("Enter numberOfSeats:"));
            case MOTORBIKE -> MOTORBIKE_SERVICE.change(UserInputUtil.getUserInput("What you want to change from Motorbike:", motorbikeList),
                    UserInputUtil.getUserInputChangeInt("Enter maxSpeed:"));
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
