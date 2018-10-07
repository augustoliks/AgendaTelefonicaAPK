package com.br.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.br.agenda.api.dao.ContactDAOCaracteristics;
import com.br.agenda.api.model.Contact;
import com.br.agenda.api.service.ContactServiceCaracteristics;
import com.br.agenda.core.dao.ContactDAO;
import com.br.agenda.core.service.ContactService;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        ContactServiceCaracteristics contactServiceImpl = new ContactService(this);

        List<Contact> contacts = contactServiceImpl.getAllContacts();

        //Recebe uma instancia de um atributo view e converte em ListView
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        //Pega um array de string e adapata de tal forma que possa ser compativel com o .XML
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);

        //Adiciona na instancia do .XML o conteudo do adaptado
        listaAlunos.setAdapter(adapter);


        Button botao = (Button) findViewById(R.id.novo_aluno);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

    }
}



