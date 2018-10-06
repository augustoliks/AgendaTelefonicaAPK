package com.br.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.BitSet;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Preparando a instancia
        setContentView(R.layout.activity_formulario);   //  Instanciando o conteudo .XML em memoria
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
                Toast.makeText(FormularioActivity.this, "Aluno Salvo", Toast.LENGTH_LONG).show(); //Criar um popup
                finish();   //Finaliza a instancia
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
