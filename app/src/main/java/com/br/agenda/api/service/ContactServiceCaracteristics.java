package com.br.agenda.api.service;

import com.br.agenda.FormularioActivity;
import com.br.agenda.api.model.Contact;

import java.util.List;

public interface ContactServiceCaracteristics {

    public List<Contact> getAllContacts();
    public boolean insertNewContact(Contact contact);


}
