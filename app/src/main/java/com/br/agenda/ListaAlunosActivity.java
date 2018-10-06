package com.br.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_alunos);

        String[] alunos ={"Daniel","Ronaldo","Jeferson","Felipe"};

        //Recebe uma instancia de um atributo view e converte em ListView
        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        //Pega um array de string e adapata de tal forma que possa ser compativel com o .XML
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);

        //Adiciona na instancia do .XML o conteudo do adaptado
        listaAlunos.setAdapter(adapter);

    }
}



