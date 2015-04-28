package com.solution.dart.rhsolution.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.solution.dart.rhsolution.MainActivity;


/**
 * Created by socrates on 28/03/15.
 */
public class BtnHomeListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }
}
