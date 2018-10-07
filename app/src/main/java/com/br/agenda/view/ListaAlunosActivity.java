package com.br.agenda.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.br.agenda.R;
import com.br.agenda.api.model.Contact;
import com.br.agenda.api.service.ContactServiceCaracteristics;
import com.br.agenda.core.service.ContactService;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView listaAlunos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) listaAlunos.getItemAtPosition(position);
                contact.getName();
                Toast.makeText(ListaAlunosActivity.this, "Aluno " + contact.getName() + " clicado!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);

                intent.putExtra("contato", contact);

                startActivity(intent);
            }
        });

        Button botao = (Button) findViewById(R.id.novo_aluno);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Deletar");  // Cria um menu de contexto e armazena sua referencia
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){   //Cria um comportamento, interessado em saber quando foi clicado
            @Override
            public boolean onMenuItemClick(MenuItem item) { // Criar um comportamento para saber qual o nome do objeto clicado
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo; // o parametro menu info disponibiliza dados adicionais, neste caso, sabendo que a lista é um adapterContextMenuInfo, podemos saber a posicao
                Contact contact = (Contact) listaAlunos.getItemAtPosition(info.position); //sabendo a posicao, perguntamos a lista o nome do elemento clicado

                ContactServiceCaracteristics contactServiceImpl = new ContactService(ListaAlunosActivity.this);

                contactServiceImpl.deleteContact(contact);

                refreshList();

                return false;

            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void refreshList() {
        ContactServiceCaracteristics contactServiceImpl = new ContactService(this);

        List<Contact> contacts = contactServiceImpl.getAllContacts();
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos); //Recebe uma instancia de um atributo view e converte em ListView

        //Pega um array de string e adapata de tal forma que possa ser compativel com o .XML
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);

        listaAlunos.setAdapter(adapter); //Adiciona na instancia do .XML o conteudo do adaptado
    }

}


