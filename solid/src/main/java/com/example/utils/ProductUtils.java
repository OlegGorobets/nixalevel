package com.example.utils;

import com.example.model.Product;
import com.example.model.ProductNotifiable;

import java.util.List;

public class ProductUtils {
    public long filterNotifiableProductsAndSendNotifications(List<Product> products) {
        return products.stream()
                .filter(it -> it.getClass().equals(ProductNotifiable.class))
                .count();
    }
}
