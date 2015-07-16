package com.epam.ak.model;

import java.util.List;

public class Hotel extends NamedEntity {
    private List<Room> rooms;
    private List<Administrator> administrators;
    private List<Customer> customers;
}
