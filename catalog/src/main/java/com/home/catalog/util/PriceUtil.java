package com.home.catalog.util;

import com.home.catalog.entity.Product;

import java.util.Set;

public class PriceUtil {

    public int min(Set<Product.Price> prices) {
        return prices.stream().mapToInt(Product.Price::getValue).min().orElse(0);
    }

    public int max(Set<Product.Price> prices) {
        return prices.stream().mapToInt(Product.Price::getValue).max().orElse(0);
    }
}
