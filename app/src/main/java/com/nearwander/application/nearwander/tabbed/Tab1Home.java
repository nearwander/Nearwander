package com.nearwander.application.nearwander.tabbed;

/**
 * Created by Rafli on 3/12/17.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nearwander.application.nearwander.R;

public class Tab1Home extends Fragment {

    private Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1home_page, container, false);
        return rootView;

    }
}
