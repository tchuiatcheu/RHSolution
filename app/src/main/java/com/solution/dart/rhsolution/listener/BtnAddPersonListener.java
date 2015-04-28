package com.solution.dart.rhsolution.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.solution.dart.rhsolution.SecondActivity;


/**
 * Created by socrates on 29/03/15.
 */
public class BtnAddPersonListener implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}
