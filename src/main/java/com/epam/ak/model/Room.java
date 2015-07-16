package com.epam.ak.model;

import org.joda.money.Money;

import java.util.UUID;

public class Room extends BaseEntity {
    private int bedsCount;
    private ApartmentType apartmentType;
    private Money dailyCost;

    public Room() {
    }

    public Room(UUID uuid, int id, int bedsCount, ApartmentType apartmentType) {
        super(uuid, id);
        this.bedsCount = bedsCount;
        this.apartmentType = apartmentType;
    }

    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        this.bedsCount = bedsCount;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

}
