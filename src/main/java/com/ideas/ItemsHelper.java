package com.ideas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ideas.model.Combination;
import com.ideas.model.Item;

public class ItemsHelper {

    private ItemsHelper() {
    }

    public static List<Item> createItems(String input) {
        // do early exception handling
        if (input == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }

        if (input.length() == 0) {
            throw new IllegalArgumentException("Input data cannot be empty");
        }

        String[] inputData = input.split(Constants.SPACE.getValue());

        List<Item> data = new ArrayList<Item>(inputData.length);
        for (String itemData : inputData) {
            String[] idv = itemData.split(Constants.COMMA.getValue());
            data.add(createItem(idv));
        }
        return data;
    }

    private static Item createItem(String[] itemDatavalues) {
        int id = Integer.parseInt(itemDatavalues[0].substring(1));
        BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(itemDatavalues[1]));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(itemDatavalues[2].substring(1,
                itemDatavalues[2].length() - 1)));

        if (weight.doubleValue() > 100) {
            throw new IllegalArgumentException("Maximum allowed weight for item is 100");
        }
        if (price.doubleValue() > 100) {
            throw new IllegalArgumentException("Maximum allowed price for item is 100");
        }
        return new Item(id, weight, price);
    }
}
