package com.solution.dart.rhsolution.rest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.solution.dart.rhsolution.ListPersonneActivity;
import com.solution.dart.rhsolution.R;
import com.solution.dart.rhsolution.model.Personne;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by socrates on 18/04/15.
 */
public class HttpUrlConnection extends AsyncTask<String, Void, JSONArray> {
    private static final int CONNECTION_TIMEOUT = 10;
    private static final int DATARETRIEVAL_TIMEOUT = 10;
    private JSONArray jsonArray;
    private Context applContext;
    private Activity activity;
    private ListView listView;
    private ListPersonneActivity listPersonneActivity;

    public HttpUrlConnection(Context applContext, Activity activity, ListView listView) {
        //check for null parameters and throw an exception
        this.applContext = applContext;
        this.activity = activity;
        this.listView = listView;

    }

    public static JSONObject requestWebService(String serviceUrl) {
        return null;
    }
    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        String result = new Scanner(inStream).useDelimiter("\\A").next();
        return result;
    }

    @Override
    protected JSONArray doInBackground(String... serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl[0]);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
//            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
//            urlConnection.setReadTimeout(DATARETRIEVAL_TIMEOUT);

            // handle issues
            int statusCode = urlConnection.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            jsonArray = new JSONArray(getResponseText(in));
            return jsonArray;

        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    @Override
    public void onPostExecute(JSONArray jsonArray){
        if (null == jsonArray){
            return;
        } else {
            ArrayList<Personne> listPersonnes = new ArrayList<Personne>();

            try {
                /*JSONArray contacts = jsonArray.getJSONArray("contacts");*/

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    listPersonnes.add(
                            new Personne(obj.getString("nom"),
                                    obj.getString("prenom"),
                                    obj.getString("age")
                                   ));
                }


                Resources res = applContext.getResources();
                listView = (ListView) activity.findViewById(R.id.listPersonne);

                ArrayList<Personne> liste= new ArrayList<Personne>();

                ArrayList<String> data  = convertPersonListToListOfString(liste);
                ArrayAdapter<String> itemsAdapter;

                itemsAdapter = new ArrayAdapter<String>(listPersonneActivity, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(itemsAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> convertPersonListToListOfString(ArrayList<Personne> liste) {
        ArrayList<String> result = new ArrayList<String>(liste.size());
        for (Personne personne : liste) {
            result.add(personne.toString());
        }
        return  result;
    }


}
