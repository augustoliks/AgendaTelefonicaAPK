package com.br.agenda.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.br.agenda.R;
import com.br.agenda.api.model.Contact;
import com.br.agenda.api.service.ContactServiceCaracteristics;
import com.br.agenda.core.service.ContactService;
import com.br.agenda.core.service.HelperFormulario;

public class FormularioActivity extends AppCompatActivity {

    ContactService contactService;
    HelperFormulario helperFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Preparando a instancia
        setContentView(R.layout.activity_formulario);   //  Instanciando o conteudo .XML em memoria
        Intent intent = getIntent();

        Contact contact = (Contact) intent.getSerializableExtra("contato");
        helperFormulario = new HelperFormulario(this);

        if(contact != null){ // Null eh quando o usuario clica pra adicionar aluno,
            helperFormulario.preencheFormulario(contact);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     // Metodo que carrega o topbar
        MenuInflater menuInflater = getMenuInflater();       //carrega o top bar que esta vazio
        menuInflater.inflate(R.menu.menu_formulario, menu);  // insere o .XML do menu_formulario, agr temos uma configuracao de layout do top bar isolada
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // Metodo que gerencia o listener de click de botao
        switch (item.getItemId()) {         //verifica o que foi clicado
            case R.id.menu_formulario_ok:   //se for o menu_formulario

                Contact contact = helperFormulario.getContact();

                ContactServiceCaracteristics contactServiceImpl = new ContactService(this);

                if(contact.getId() == null){
                    contactServiceImpl.insertNewContact(contact);
                    Toast.makeText(FormularioActivity.this,"Contato " + contact.getName() + " Salvo", Toast.LENGTH_LONG).show(); //Criar um popup
                }
                else{
                    contactServiceImpl.updateContact(contact);
                    Toast.makeText(FormularioActivity.this,"Contato Alterado !", Toast.LENGTH_LONG).show(); //Criar um popup
                }

                finish();   //Finaliza a instancia
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
