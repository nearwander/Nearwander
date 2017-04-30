package com.nearwander.application.nearwander.tabbed.nearby;

/**
 * Created by Rafli on 4/26/17.
 */

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nearwander.application.nearwander.R;

public class PlacesGrid extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.places_grid, container, false);

        final Constants constants = new Constants();
        final Context context = getActivity();

        GridView gridview = (GridView)view.findViewById(R.id.places);
        gridview.setAdapter(new TilesFormatter(context));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, PlaceResult.class);
                String place = constants.places_list[position];
                intent.putExtra("Place_id", place);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}