package com.br.agenda.core.service;

import android.content.ContentValues;
import android.content.Context;
import android.widget.EditText;

import com.br.agenda.FormularioActivity;
import com.br.agenda.R;
import com.br.agenda.api.dao.ContactDAOCaracteristics;
import com.br.agenda.api.model.Contact;
import com.br.agenda.api.service.ContactServiceCaracteristics;
import com.br.agenda.core.dao.ContactDAO;

import java.util.List;

public class ContactService implements ContactServiceCaracteristics {

    private Context context;

    public ContactService(Context context) {
        this.context = context;
    }

    @Override
    public List<Contact> getAllContacts() {
        ContactDAO contactDAO = new ContactDAO(this.context);
        List<Contact> contacts = contactDAO.read();
        contactDAO.close();
        return contacts;
    }

    @Override
    public void insertNewContact(Contact contact) {

        ContactDAO contactDAO = new ContactDAO(this.context);
        contactDAO.insert(contact);
        contactDAO.close();

    }

    @Override
    public void deleteContact(Contact contact) {
        ContactDAO contactDAO = new ContactDAO(this.context);
        contactDAO.delete(contact);
        contactDAO.close();
    }

    @Override
    public void updateContact(Contact contact) {
        ContactDAO contactDAO = new ContactDAO(this.context);
        contactDAO.update(contact);
        contactDAO.close();

    }

}
