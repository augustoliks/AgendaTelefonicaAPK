package com.br.agenda.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.br.agenda.api.dao.ContactDAOCaracteristics;
import com.br.agenda.api.model.Contact;
import com.br.agenda.core.service.ContactService;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends SQLiteOpenHelper implements ContactDAOCaracteristics {

    public ContactDAO(Context context){
        super(context, "Agenda", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contato(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "endereco TEXT," +
                "telefone TEXT," +
                "site TEXT," +
            //    "nota REAL" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Contato";
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public boolean insert(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", contact.getName());
        contentValues.put("endereco", contact.getAddress());
        contentValues.put("telefone", contact.getTelephoneNumber());
        contentValues.put("site", contact.getSite());
//        contentValues.put("nota", contact.getRating());

        db.insert("Contato", null, contentValues);

        return true;

    }

    @Override
    public List<Contact> readAllContacts() {
        String sql = "SELECT * FROM Contato";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Contact> contacts = new ArrayList<Contact>();

        while (cursor.moveToNext()){
            Contact contact = new Contact();

            contact.setTelephoneNumber(cursor.getString(cursor.getColumnIndex("telefone")));
//            contact.setRating();
            contact.setName(cursor.getString(cursor.getColumnIndex("nome")));
            contact.setAddress(cursor.getString(cursor.getColumnIndex("endereco")));
            contact.setSite(cursor.getString(cursor.getColumnIndex("site")));

            contacts.add(contact);
      }

        return contacts;
    }
}