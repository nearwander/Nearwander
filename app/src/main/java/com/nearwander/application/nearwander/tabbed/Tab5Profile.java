package com.nearwander.application.nearwander.tabbed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nearwander.application.nearwander.R;

public class Tab5Profile extends Fragment {


    private static final String TAG = "TAB5PROFILE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5profile_page, container, false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        return rootView;
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear(); // clears all menu items..
        inflater.inflate(R.menu.navigation_drawable, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mFirebaseUser = null;
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
                return true;
            case R.id.action_settings:
                //fetchConfig();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
