package com.solution.dart.rhsolution;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.solution.dart.rhsolution.MyDBHandler;
import com.solution.dart.rhsolution.model.Personne;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    MyDBHandler m;
    private static final String TAG="PERSONNE RH";



    public ApplicationTest() {

        super(Application.class);

           }

    public void textListPersonne(){
        m = new MyDBHandler(this.getContext(),"personne.db",null, 1);
        Log.i(TAG, "liste des personnes");
        List<Personne> liste= m.getAllPersonnes();
        Log.i(TAG, "liste"+liste);
    }
}