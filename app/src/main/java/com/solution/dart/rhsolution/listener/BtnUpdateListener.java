package com.solution.dart.rhsolution.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.solution.dart.rhsolution.ListPersonneActivity;
import com.solution.dart.rhsolution.MyDBHandler;

/**
 * Created by socrates on 28/03/15.
 */
public class BtnUpdateListener implements View.OnClickListener{

    private Context appContext;
    private MyDBHandler mydb;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    int id;

    public BtnUpdateListener(Context appContext, int id, EditText editText, EditText editText2, EditText editText3){
        this.appContext=appContext;
        this.id=id;
        this.editText=editText;
        this.editText2=editText2;
        this.editText3=editText3;
        mydb = new MyDBHandler(appContext, "personne.db", null, 1);
    }

    @Override
    public void onClick(View view) {
        String nom = editText.getText().toString();
        String prenom = editText2.getText().toString();
        String age = editText3.getText().toString();
        if (mydb.updatePersonne(id, nom, prenom, age)){
            Intent intent = new Intent(appContext, ListPersonneActivity.class);
            view.getContext().startActivity(intent);
            Toast.makeText(appContext, "Mise a jour réussite", Toast.LENGTH_LONG);
        } else {
            Toast.makeText(appContext, "Echec de mise a jour", Toast.LENGTH_LONG);
        }
    }
}
