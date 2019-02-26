package com.example.dell.offline1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 13-May-17.
 */
public class CustomAdapter extends ArrayAdapter<FlatRowClass> {
    public CustomAdapter(Context context, ArrayList<FlatRowClass> flats) {
        super(context,R.layout.customrowflat ,flats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myinflator = LayoutInflater.from(getContext());
        View customview = myinflator.inflate(R.layout.customrowflat, parent, false);

        FlatRowClass singleflattext = getItem(position);
        TextView flatno = (TextView) customview.findViewById(R.id.flatno);
        ImageView housecondition = (ImageView) customview.findViewById(R.id.housecondition);
        TextView rating = (TextView) customview.findViewById(R.id.rating);
        TextView flataddress = (TextView) customview.findViewById(R.id.flataddress);

        flatno.setText(singleflattext.getText());
        flataddress.setText(singleflattext.getAddress());

        housecondition.setImageResource(singleflattext.getImageView());

        return customview;


    }
}
