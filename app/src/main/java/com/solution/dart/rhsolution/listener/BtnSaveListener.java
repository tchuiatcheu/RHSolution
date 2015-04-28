package com.solution.dart.rhsolution.listener;

        import android.content.Context;
        import android.content.Intent;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.solution.dart.rhsolution.ListPersonneActivity;
        import com.solution.dart.rhsolution.MyDBHandler;


/**
 * Created by socrates on 26/03/15.
 */
public class BtnSaveListener implements View.OnClickListener{


    private Context appContext;
    private MyDBHandler mydb;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;



    public BtnSaveListener(Context appContext, EditText editText, EditText editText2, EditText editText3){
        this.appContext=appContext;
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



        if(mydb.insertPersonne(nom, prenom, age)){

            editText.setText("");
            editText2.setText("");
            editText3.setText("");


            Toast.makeText(appContext, "Enregistrement réussi", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(appContext, "Echec d'enregistrement", Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(appContext,ListPersonneActivity.class);
        view.getContext().startActivity(intent);


        }
}
