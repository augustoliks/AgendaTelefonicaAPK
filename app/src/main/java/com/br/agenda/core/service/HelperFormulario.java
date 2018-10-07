package com.br.agenda.core.service;

import android.widget.EditText;

import com.br.agenda.FormularioActivity;
import com.br.agenda.R;
import com.br.agenda.api.dao.ContactDAOCaracteristics;
import com.br.agenda.api.model.Contact;
import com.br.agenda.api.service.ContactServiceCaracteristics;
import com.br.agenda.core.dao.ContactDAO;

import java.util.List;

public class HelperFormulario{

    private final EditText campoNome;
    private final EditText campoEndereco;
   // private final EditText campoNota;
    private final EditText campoSite;
    private final EditText campoTelefone;
    Contact contact;

    public HelperFormulario(FormularioActivity activity) {
        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
       // this.campoNota = activity.findViewById(R.id.formulario_nota);
        this.campoSite = activity.findViewById(R.id.formulario_site);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
    }

    public Contact getContact() {
        Contact contact = new Contact();

        String name = campoNome.getText().toString();
        String address = campoEndereco.getText().toString();
        //String rating = campoNota.getText().toString();
        String site = campoSite.getText().toString();
        String telephoneNumber = campoTelefone.getText().toString();

        contact.setAddress(address);
        contact.setName(name);
        //contact.setRating(rating);
        contact.setSite(site);
        contact.setTelephoneNumber(telephoneNumber);

        return contact;
    }

    public void preencheFormulario(Contact contact)  {
        campoNome.setText(contact.getName());
        campoEndereco.setText(contact.getAddress());
        campoTelefone.setText(contact.getTelephoneNumber());
        campoSite.setText(contact.getSite());
        //campoNota.setProgress(aluno.getNota().intValue());
        this.contact = contact;
    }

}
