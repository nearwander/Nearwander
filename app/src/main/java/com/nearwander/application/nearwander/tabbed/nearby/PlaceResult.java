package com.nearwander.application.nearwander.tabbed.nearby;

import android.support.v7.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearwander.application.nearwander.tabbed.PlaceDetail.PlaceDetail;

import java.util.List;

import com.nearwander.application.nearwander.tabbed.Utility.FetchFromServerTask;
import com.nearwander.application.nearwander.tabbed.Utility.FetchFromServerUser;
import com.nearwander.application.nearwander.tabbed.Utility.RecyclerItemClickListener;

import com.nearwander.application.nearwander.R;

public class PlaceResult extends FragmentActivity implements FetchFromServerUser{

    private static final String KEY = "AIzaSyA4YZWrcAoVVMxF28Z12tCOVn8DJMgty_w";
    ErrorFragment errorFragment;
    Context context = this;
    RecyclerView listOfPlaces;
    ProgressDialog progressDialog;
    GetLocation loc;
    String kind;
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list);

        loc = new GetLocation(this);

        ImageView back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceResult.this.finish();
            }
        });

        ImageView search = (ImageView)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceResult.this, Search.class);
                startActivity(intent);
            }
        });

        kind = getIntent().getStringExtra("Place_id");
        TextView placeKind = (TextView)findViewById(R.id.namePlaceHolder);
        placeKind.setText(kind.replace("_", " "));

        url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + loc.latitude+","+loc.longitude+"&rankby=distance&types="+kind+"&key=" + KEY;;
        Log.e("PlaceResult", url);
        new FetchFromServerTask(this, 0).execute(url);
    }

    @Override
    public void onPreFetch() {
        progressDialog = new ProgressDialog(PlaceResult.this);
        progressDialog.setMessage("Fetching Results");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onFetchCompletion(String string, int id) {
        if(progressDialog != null)
            progressDialog.dismiss();
        if(errorFragment != null)
            getSupportFragmentManager().beginTransaction().remove(errorFragment).commit();
        if(string == null || string.equals("")){
            errorFragment = new ErrorFragment();
            Bundle msg = new Bundle();
            msg.putString("msg", "No or poor internet connection.");
            errorFragment.setArguments(msg);
            getSupportFragmentManager().beginTransaction().replace(R.id.message, errorFragment).commit();
        }else {
            JSONParser parser = new JSONParser(string, kind);
            try {
                final List<PlaceBean> list = parser.getPlaceBeanList();
                if (list != null && list.size() > 0) {
                    PlaceListAdapter Places_adapter = new PlaceListAdapter(context, list, loc);
                    listOfPlaces = (RecyclerView) findViewById(R.id.list);
                    listOfPlaces.setHasFixedSize(true);
                    listOfPlaces.setLayoutManager(new LinearLayoutManager(context));
                    listOfPlaces.setAdapter(Places_adapter);

                    listOfPlaces.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent detailActivity = new Intent(context, PlaceDetail.class);
                            detailActivity.putExtra("placeId", list.get(position).getPlaceref());
                            detailActivity.putExtra("kind", kind);
                            startActivity(detailActivity);
                        }
                    }));
                }
            }catch (Exception ex){
                errorFragment = new ErrorFragment();
                Bundle msg = new Bundle();
                msg.putString("msg", ex.getMessage());
                errorFragment.setArguments(msg);
                getSupportFragmentManager().beginTransaction().replace(R.id.message, errorFragment).commit();
            }
        }
    }

    public void retry(View view){
        new FetchFromServerTask(this, 0).execute(url);
    }
}