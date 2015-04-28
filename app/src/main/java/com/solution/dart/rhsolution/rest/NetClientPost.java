package com.solution.dart.rhsolution.rest;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;


import com.solution.dart.rhsolution.SecondActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by socrates on 23/04/15.
 */
public class NetClientPost {

    private Context aplContext;
    private Activity activity;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private SecondActivity secondActivity;

    private static final String targetURL = "http://10.0.2.2:8080/rhsolutionproject/rest/personnes";

   /* public NetClientPost(Context aplContext, Activity activity, EditText editText, EditText editText2, EditText editText3) throws IOException {
        this.aplContext = aplContext;
        this.activity = activity;
        this.editText = editText;
        this.editText2 = editText2;
        this.editText3 = editText3;
    }*/


    public void envoyer(){

        try {

            URL targetUrl = new URL(targetURL);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":1,\"nom\":\"tchuiatcheu\",\"prenom\":\"socrates\",\"age\":24\"}";

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:\n");
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
            }

            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}