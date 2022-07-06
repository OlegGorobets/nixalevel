package com.nixalevel.lesson10;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.service.AutoService;

import java.util.List;

public class Main {
    private static final AutoService AUTO_SERVICE = new AutoService();

    public static void main(String[] args) {
        final List<Auto> autos = AUTO_SERVICE.createAutos(10);
        AUTO_SERVICE.saveAutos(autos);
        AUTO_SERVICE.printAll();
    }
}
