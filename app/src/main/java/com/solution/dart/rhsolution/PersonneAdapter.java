package com.solution.dart.rhsolution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.solution.dart.rhsolution.model.Personne;

import java.util.List;

/**
 * Created by socrates on 09/04/15.
 */
public class PersonneAdapter extends ArrayAdapter<Personne> {
    public PersonneAdapter(Context context, List<Personne> personnes) {
        super(context, 0, personnes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Personne personne = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_personne, parent, false);
        }
        // Lookup view for data population
        TextView nom = (TextView) convertView.findViewById(R.id.editText);
        TextView prenom = (TextView) convertView.findViewById(R.id.editText2);
        TextView age = (TextView) convertView.findViewById(R.id.editText3);
        // Populate the data into the template view using the data object
        nom.setText(personne.getNom());
        prenom.setText(personne.getPrenom());
       /* age.setText(personne.getAge(obj.getString("age")));*/
        // Return the completed view to render on screen
        return convertView;
    }

}
