package com.epam.ak.model;

import java.util.UUID;

public class Administrator extends NamedEntity implements User {
    private String login;
    private String password;

    public Administrator() {
    }

    public Administrator(UUID uuid, Integer id, String name, String login, String password) {
        super(uuid, id, name);
        this.login = login;
        this.password = password;
    }

    @Override
    public void createRequestForm() {

    }

    @Override
    public void createInvoice() {

    }
}
