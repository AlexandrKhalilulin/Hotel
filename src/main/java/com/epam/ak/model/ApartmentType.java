package com.epam.ak.model;

import java.util.UUID;

public class ApartmentType extends NamedEntity{
    public ApartmentType() {
    }

    public ApartmentType(UUID uuid, Integer id, String name) {
        super(uuid, id, name);
    }
}
