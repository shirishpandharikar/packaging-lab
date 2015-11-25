package com.ideas.service;

import com.ideas.model.Box;
import com.ideas.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface PackageService {

    /**
     * Creates a package with given maximum weight and items.
     *
     * @param weight maximum allowed weight for the package.
     * @param items  possible list of items that the package can contain.
     * @return {@link Box} instance with cost optimized combination of items.
     */
    Box createPackage(BigDecimal weight, List<Item> items);

}
