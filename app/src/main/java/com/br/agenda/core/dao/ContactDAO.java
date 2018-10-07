package com.br.agenda.core.dao;

import com.br.agenda.api.dao.ContactDAOCaracteristics;
import com.br.agenda.api.model.Contact;

public class ContactDAO implements ContactDAOCaracteristics {

    @Override
    public boolean insert(Contact contact) {
        return false;
    }

}