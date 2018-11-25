package com.br.agenda.core.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ImageWriter;
import android.widget.EditText;
import android.widget.ImageView;

import com.br.agenda.view.FormularioActivity;
import com.br.agenda.R;
import com.br.agenda.api.model.Contact;
import com.br.agenda.view.ListaAlunosActivity;

public class HelperFormulario{

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoSite;
    private final EditText campoTelefone;
    private final ImageView campoFoto;

    private Contact contact;

    public HelperFormulario(FormularioActivity activity) {
        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
        this.campoSite = activity.findViewById(R.id.formulario_site);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
        this.campoFoto = activity.findViewById(R.id.formulario_foto);

        contact = new Contact();
    }

    public Contact getContact() {

        contact.setName(campoNome.getText().toString());
        contact.setAddress(campoEndereco.getText().toString());
        contact.setSite(campoSite.getText().toString());
        contact.setTelephoneNumber(campoTelefone.getText().toString());
        contact.setPathRelativePhoto( (String) campoFoto.getTag() );

        return contact;
    }

    public void preencheFormulario(Contact contact)  {
        campoNome.setText(contact.getName());
        campoEndereco.setText(contact.getAddress());
        campoTelefone.setText(contact.getTelephoneNumber());
        campoSite.setText(contact.getSite());
        loadImage(contact.getPathRelativePhoto());
        this.contact = contact;
    }

    public void loadImage(String pathPhoto) {
        if(pathPhoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapReduced = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduced);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(pathPhoto);
        }
    }
}
