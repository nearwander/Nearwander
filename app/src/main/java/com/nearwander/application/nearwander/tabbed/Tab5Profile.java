package com.nearwander.application.nearwander.tabbed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nearwander.application.nearwander.R;

/**
 * Created by Rafli on 3/12/17.
 */

public class Tab5Profile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5profile_page, container, false);
        return rootView;
    }
}
