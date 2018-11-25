package com.br.agenda.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.br.agenda.api.dao.ContactDAOCaracteristics;
import com.br.agenda.api.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends SQLiteOpenHelper implements ContactDAOCaracteristics {

    public ContactDAO(Context context){
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                "Contato(" +
                    "id INTEGER PRIMARY KEY," +
                    "nome TEXT NOT NULL," +
                    "endereco TEXT," +
                    "telefone TEXT," +
                    "site TEXT," +
                    "caminhoFoto TEXT" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";

        switch (oldVersion){     // Nao tem break nos cases pq atualiza de forma cascata de acordo com a versao antiga
            case 1:
                sql = "ALTER TABLE Contato ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql); // Atualizando pra versao 2
        }

    }

    @Override
    public boolean insert(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValues(contact);
        db.insert("Contato", null, contentValues);

        return true;

    }

    @NonNull
    private ContentValues getContentValues(Contact contact) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("nome", contact.getName());
        contentValues.put("endereco", contact.getAddress());
        contentValues.put("telefone", contact.getTelephoneNumber());
        contentValues.put("site", contact.getSite());
        contentValues.put("caminhoFoto", contact.getPathRelativePhoto());

        return contentValues;
    }

    @Override
    public void delete(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        String []params = {contact.getId().toString()};
        db.delete("Contato", "id = ?",params);
    }

    @Override
    public List<Contact> read() {
        String sql = "SELECT * FROM Contato";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Contact> contacts = new ArrayList<Contact>();

        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex("id")));
            contact.setTelephoneNumber(cursor.getString(cursor.getColumnIndex("telefone")));
            contact.setName(cursor.getString(cursor.getColumnIndex("nome")));
            contact.setAddress(cursor.getString(cursor.getColumnIndex("endereco")));
            contact.setSite(cursor.getString(cursor.getColumnIndex("site")));
            contact.setPathRelativePhoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));

            contacts.add(contact);
      }

        return contacts;
    }

    @Override
    public void update(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getContentValues(contact);

        String []params = {contact.getId().toString()};

        db.update("Contato", contentValues,"id = ?", params);

    }
}