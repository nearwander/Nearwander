package com.nearwander.application.nearwander.tabbed.PlaceDetail;

/**
 * Created by Rafli on 4/26/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nearwander.application.nearwander.R;

public class ReviewAdapter extends BaseAdapter {

    PlaceDetailBean.Review []reviews;
    Context context;
    LayoutInflater inflater;

    String baseURL = "https://www.googleapis.com/plus/v1/people/";
    String key = "AIzaSyBg-iwzAjavEUVV9hOQUr0JljZHL7XFRkQ";

    String colors = "#005968";

    public ReviewAdapter(PlaceDetailBean.Review []reviews, Context context){
        this.reviews = reviews;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return reviews.length;
    }

    @Override
    public Object getItem(int position) {
        return reviews[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(R.layout.review_fragment_item, parent, false);

        ImageView icon = (ImageView)convertView.findViewById(R.id.author_icon);
        TextView author_name = (TextView)convertView.findViewById(R.id.author_name);
        TextView author_text = (TextView)convertView.findViewById(R.id.author_text);
        RatingBar author_rating = (RatingBar)convertView.findViewById(R.id.author_rating);

        author_name.setText(reviews[position].getAuthor_name());
        author_text.setText(reviews[position].getAuthor_text());
        author_rating.setRating(reviews[position].getRating());
        icon.setScaleType(ImageView.ScaleType.CENTER);
        GradientDrawable gd = (GradientDrawable) icon.getBackground().getCurrent();
        gd.setColor(Color.parseColor(colors));

        if(reviews[position].getAuthor_url() != null && reviews[position].getAuthor_url().length() > 24) {
            String author_url = baseURL + reviews[position].getAuthor_url().substring(24) + "?fields=image&key=" + key;
            try {
                new ImageLoader(context, author_url, icon).loadThumbnailImage();
            }catch (Exception ex){
                Log.e("ReviewAdapter", ex.getMessage());
            }
        }
        return convertView;
    }
}
