package com.br.agenda.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.br.agenda.BuildConfig;
import com.br.agenda.R;
import com.br.agenda.api.model.Contact;
import com.br.agenda.api.service.ContactServiceCaracteristics;
import com.br.agenda.core.service.ContactService;
import com.br.agenda.core.service.HelperFormulario;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class FormularioActivity extends AppCompatActivity {
    private static final String PACKAGE_PROVIDER = BuildConfig.APPLICATION_ID + ".provider"; // Pega o nome do pacote, e concatena com .provider
    private static final int COGIDO_CAMERA = 2;
    ContactService contactService;
    HelperFormulario helperFormulario;
    private String pathPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Preparando a instancia
        setContentView(R.layout.activity_formulario);   //  Instanciando o conteudo .XML em memoria
        final Intent intent = getIntent();

        Contact contact = (Contact) intent.getSerializableExtra("contato");
        helperFormulario = new HelperFormulario(this);

        if(contact != null){ // Null eh quando o usuario clica pra adicionar aluno,
            helperFormulario.preencheFormulario(contact);
        }

        Button btnPhoto = (Button) findViewById(R.id.formulario_btn_camera);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //  intenção de acionar camera

                if(intentCamera.resolveActivity(getPackageManager()) != null){
                    //Create a file to store the image
                    File photoFile = null;

                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }

                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(
                                FormularioActivity.this,
                                PACKAGE_PROVIDER,
                                photoFile);

                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(intentCamera, COGIDO_CAMERA); // roda a camera, tem q ser assim, pq temos q ter um codigo de retorna de sucesso da operacao, o resultado eh captado na funcao 'onActivityResult'
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {   // Verifica se o codigo da acao da activity foi OK, (botao 'X' cancelar, nao entra nesse if)
            if (requestCode == COGIDO_CAMERA) {  // verica se a acao eh oriunda da acao de acionamento da camera

                if (data != null && data.getExtras() != null) {
                    helperFormulario.loadImage(pathPhoto);
                }
            }

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


    private File createImageFile() throws IOException {
        String imageFileName = "IMG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        pathPhoto = image.getAbsolutePath();
        System.out.println("CARLOS:   "+ image.getAbsolutePath());
        System.out.println("NETO:   "+ PACKAGE_PROVIDER);
        return image;
    }
}
