package com.solution.dart.rhsolution.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.solution.dart.rhsolution.ListPersonneActivity;

/**
 * Created by socrates on 29/03/15.
 */
public class BtnListListener implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, ListPersonneActivity.class);
        context.startActivity(intent);
    }
}
