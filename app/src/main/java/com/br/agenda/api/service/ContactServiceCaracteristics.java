package com.br.agenda.api.service;

import com.br.agenda.api.model.Contact;

import java.util.List;

public interface ContactServiceCaracteristics {

    public List<Contact> getAllContacts();
    public void insertNewContact(Contact contact);
    public void deleteContact(Contact contact);
    public void updateContact(Contact contact);


}
