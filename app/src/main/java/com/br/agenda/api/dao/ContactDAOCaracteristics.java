package com.br.agenda.api.dao;

import com.br.agenda.api.model.Contact;

import java.util.List;

public interface ContactDAOCaracteristics {
    public boolean insert(Contact contact);
    public List<Contact> read();
    public void update(Contact contact);
    public void delete(Contact contact);
}
