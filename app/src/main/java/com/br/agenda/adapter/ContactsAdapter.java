package com.br.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.agenda.R;
import com.br.agenda.api.model.Contact;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {

    private final List<Contact> contacts;
    private final Context context;

    public ContactsAdapter(Context context, List<Contact> contacts){
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {     //Quantos itens vao ser apresentados na lista
        return contacts.size(); // todos da lista
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // vai de 0 ateh 'getCount()', e usa o numero como parametro para o position, e faz esta funcao
// Inflar eh transformar uma xml em view, isso q este metodo faz, e ao clicar em sua posicao, ele pega o id e chama dao para update
        Contact contact = contacts.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);     // Recebe uma referencia (contexto)
        View view = convertView;    // convertView eh o que o user esta vendo, reaproveitamos a convertView caso ela exista

        if (view == null) { // se o user desceu o scroll, e nao esta vendo mais nada, comeca a carregar mais itens... carrega apenas o necessario
            view = inflater.inflate(R.layout.list_item, parent, false); // Pega o 'list_item.xml' e transforma em uma view
        }

        TextView fieldName = (TextView) view.findViewById(R.id.item_nome);  // Pega o id 'item_nome' do view, que eh do (list_item.xml)
        fieldName.setText(contact.getName());   // seta o nome

        TextView fieldTelephone = (TextView) view.findViewById(R.id.item_numero); // Pega o id 'item_numero' do view, que eh do (list_item.xml)
        fieldTelephone.setText(contact.getTelephoneNumber()); // seta o numero

        String pathRelativePhoto = contact.getPathRelativePhoto(); // Pega o caminho da foto

        ImageView fieldPhoto = (ImageView) view.findViewById(R.id.item_foto); // Pega o id 'item_foto' do view, que eh do (list_item.xml)

        if (pathRelativePhoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(pathRelativePhoto);    // Cria um objeto bitmap em memoria a partir da uri do arquivo da foto
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);  // Reduz a resulucao pra fins de retrocompatibilidade
            fieldPhoto.setImageBitmap(bitmapReduzido);  // Seta na view da list_item.xml -> item_foto.xml, a foto
            fieldPhoto.setScaleType(ImageView.ScaleType.FIT_XY);    // redimensiona
        }
        return view;
    }

 /*
        TextView view = new TextView(context);      // Cria uma TextView
        Contact contact = contacts.get(position);   // Recupera o objeto 'contact'
        view.setText(contact.toString());           // Seta o conteudo do textView criado anteriormente
        return view;                                // Retorna a view com o valor setado
*/

    }

