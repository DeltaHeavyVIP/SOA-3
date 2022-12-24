package com.example.basic.utils;

import com.example.basic.model.Coordinates;
import com.example.basic.model.Location;
import com.example.basic.model.Person;
import com.example.basic.model.Product;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Filter {
    private static final List<String> columnsNames = new ArrayList<>();

    static {
        for (Field f : Product.class.getDeclaredFields()) {
            if (Person.class.isAssignableFrom(f.getType())) {
                for (Field fieldPerson : Person.class.getDeclaredFields()) {
                    if (Location.class.isAssignableFrom(fieldPerson.getType())) {
                        for (Field fieldLocation : Location.class.getDeclaredFields()) {
                            columnsNames.add("owner_location_" + fieldLocation.getName());
                        }
                    } else {
                        columnsNames.add("owner_" + fieldPerson.getName());
                    }
                }
            } else if (Coordinates.class.isAssignableFrom(f.getType())) {
                for (Field fieldCoordinates : Coordinates.class.getDeclaredFields()) {
                    columnsNames.add("coordinates_" + fieldCoordinates.getName());
                }
            } else {
                columnsNames.add(f.getName());
            }
        }
    }

    public static List<Sort.Order> getOrdersByNames(Map<String, String> namePropertyMap) {
        if (namePropertyMap == null || namePropertyMap.isEmpty()) {
            return Collections.emptyList();
        }

        List<Sort.Order> orders = new ArrayList<>();
        namePropertyMap.entrySet().stream()
                .filter(e -> columnsNames.contains(e.getKey()))
                .forEach(e -> {
                    if (e.getValue().equalsIgnoreCase("desc")) {
                        orders.add(Sort.Order.desc(e.getKey()));
                    } else {
                        orders.add(Sort.Order.asc(e.getKey()));
                    }
                });

        return orders;
    }
}
