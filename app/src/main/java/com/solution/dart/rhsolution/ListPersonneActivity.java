package com.solution.dart.rhsolution;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.solution.dart.rhsolution.listener.BtnAddPersonListener;
import com.solution.dart.rhsolution.model.Personne;
import com.solution.dart.rhsolution.rest.HttpUrlConnection;



import java.util.ArrayList;


public class ListPersonneActivity extends Activity {

    Button addperson;

    private ListView listView;
    MyDBHandler mydb;
    public Context applContext;
    ListPersonneActivity listPersonneActivity = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personne);
        addperson= (Button) findViewById(R.id.button4);
        addperson.setOnClickListener(new BtnAddPersonListener());


        applContext = this;
        listPersonneActivity = this;
        mydb = new MyDBHandler(applContext, "personne.db", null, 1);

        // Construct the data source

        new HttpUrlConnection(applContext,listPersonneActivity,listView).execute("http://10.0.2.2:8080/api/rhsolutionproject");


       /*
        ArrayList<Personne> liste= new ArrayList<Personne>();

        liste= (ArrayList<Personne>) mydb.getAllPersonnes();

        ArrayList<String> data  = convertPersonListToListOfString(liste);
        ArrayAdapter<String> itemsAdapter;

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        ListView listView2 = (ListView) findViewById(R.id.listPersonne);
        listView2.setAdapter(itemsAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                RestHttpUrlConnection.getPersonnes();
            }
        }).start();*/

    }


   /* private ArrayList<String> convertPersonListToListOfString(ArrayList<Personne> liste) {
        ArrayList<String> result = new ArrayList<String>(liste.size());
        for (Personne personne : liste) {
            result.add(personne.toString());
        }
        return  result;
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_personne, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
