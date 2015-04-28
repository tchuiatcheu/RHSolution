package com.solution.dart.rhsolution;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.solution.dart.rhsolution.model.Personne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by socrates on 28/03/15.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "personne.db";
    public static final String PERSONNE_TABLE_NAME = "personnes";
    public static final String PERSONNE_COLUMN_ID = "id";
    public static final String PERSONNE_COLUMN_NOM = "nom";
    public static final String PERSONNE_COLUMN_PRENOM = "prenom";
    public static final String PERSONNE_COLUMN_AGE = "age";

    private static final String TAG = "MonApplication";

    public MyDBHandler(Context context, String  DATABASE_NAME, Object o, int i) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "CREATION DE LA TABLE");

        db.execSQL("create table personnes " + "(id integer primary key , nom text, prenom text, age text)");

        Log.i(TAG, " LA TABLE A ETE CREER");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personnes");
        onCreate(db);
    }

    // insertion des elements dans la base de données

    public boolean insertPersonne (String nom,String prenom,String age){
        Log.i(TAG, "INSERTION D'UNE PERSONNE DANS LA TABLE");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", nom);
        contentValues.put("prenom", prenom);
        contentValues.put("age", age);
        db.insert("personnes", null, contentValues);
        return true;


    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from personnes where id="+id+"", null );
        return res;
    }


    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PERSONNE_TABLE_NAME);
        return numRows;
    }

    // udapte des elements dans la base de données

    public boolean updatePersonne (Integer id, String nom,String prenom, String age)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", nom);
        contentValues.put("prenom", prenom);
        contentValues.put("age", age);
        db.update("personnes", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    // delete un element
    public Integer deletePersonne (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("personnes", "id = ? ", new String[] { Integer.toString(id) });
    }

    public List<Personne> getAllPersonnes() {
        List<Personne> result = new ArrayList<Personne>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from personnes", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            int columnIndex = res.getColumnIndex(PERSONNE_COLUMN_ID);
            int idPersonne = res.getInt(columnIndex);

            int columnNom = res.getColumnIndex(PERSONNE_COLUMN_NOM);
            String nomPersonne = res.getString(columnNom);

            int columnPrenom = res.getColumnIndex(PERSONNE_COLUMN_PRENOM);
            String prenomPersonne = res.getString(columnPrenom);

            int columnAge = res.getColumnIndex(PERSONNE_COLUMN_AGE);
            String agePersonne = res.getString(columnAge);

            Personne personne = new Personne();
            personne.setId(idPersonne);
            personne.setNom(nomPersonne);
            personne.setPrenom(prenomPersonne);
            personne.setAge(agePersonne);
            result.add(personne);
            res.moveToNext();
            Log.i(TAG,"Personne de la bd" +personne);
        }
        return result;
    }

}
