package com.nixalevel.module;

import com.nixalevel.module.repository.ShopRepository;
import com.nixalevel.module.service.PersonService;
import com.nixalevel.module.service.ShopService;
import com.nixalevel.module.utility.AnalyticalData;

public class Main {
    private static final AnalyticalData ANALYTICAL_DATA = new AnalyticalData();
    private static final ShopRepository SHOP_REPOSITORY = new ShopRepository();
    private static final ShopService SHOP_SERVICE = new ShopService(SHOP_REPOSITORY);
    private static final PersonService PERSON_SERVICE = new PersonService();

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            SHOP_SERVICE.add(SHOP_SERVICE.generateRandomInvoice(PERSON_SERVICE.generateRandomPerson()));
        }
        SHOP_SERVICE.printAll();
        ANALYTICAL_DATA.getAnalytics(SHOP_SERVICE.getAll());
        SHOP_SERVICE.printAll();
    }
}
