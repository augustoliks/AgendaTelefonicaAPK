package com.br.agenda.core.service;

import android.widget.EditText;

import com.br.agenda.view.FormularioActivity;
import com.br.agenda.R;
import com.br.agenda.api.model.Contact;

public class HelperFormulario{

    private final EditText campoNome;
    private final EditText campoEndereco;
   // private final EditText campoNota;
    private final EditText campoSite;
    private final EditText campoTelefone;
    private Contact contact;

    public HelperFormulario(FormularioActivity activity) {
        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
       // this.campoNota = activity.findViewById(R.id.formulario_nota);
        this.campoSite = activity.findViewById(R.id.formulario_site);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
        contact = new Contact();
    }

    public Contact getContact() {

        contact.setName(campoNome.getText().toString());
        contact.setAddress(campoEndereco.getText().toString());
        //String rating = campoNota.getText().toString();

        contact.setSite(campoSite.getText().toString());
        contact.setSite(campoTelefone.getText().toString());

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
