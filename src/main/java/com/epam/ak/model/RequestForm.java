package com.epam.ak.model;

import java.time.LocalDate;
import java.util.UUID;

public class RequestForm extends NamedEntity {
    private ApartmentType apartmentType;
    private LocalDate residenceTime;
    private int bedsNumber;

    public RequestForm() {
    }

    public RequestForm(UUID uuid, Integer id, String name, ApartmentType apartmentType, LocalDate residenceTime, int bedsNumber) {
        super(uuid, id, name);
        this.apartmentType = apartmentType;
        this.residenceTime = residenceTime;
        this.bedsNumber = bedsNumber;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public LocalDate getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(LocalDate residenceTime) {
        this.residenceTime = residenceTime;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }
}
