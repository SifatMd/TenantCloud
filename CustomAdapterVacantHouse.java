package com.example.dell.offline1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 01-Jun-17.
 */
public class CustomAdapterVacantHouse extends ArrayAdapter<VacantHousesClass> {
    public CustomAdapterVacantHouse(Context context, ArrayList<VacantHousesClass> houses) {
        super(context,R.layout.customrowvacant, houses);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myinflator = LayoutInflater.from(getContext());
        View customView = myinflator.inflate(R.layout.customrowvacant,parent,false);

        VacantHousesClass singleflattext = getItem(position);

        TextView flatdesc = (TextView) customView.findViewById(R.id.flatdesc);
        TextView addressdesc = (TextView) customView.findViewById(R.id.addressdesc);
        TextView rent = (TextView) customView.findViewById(R.id.Rent);

        flatdesc.setText(singleflattext.getFlatdesc());
        addressdesc.setText(singleflattext.getAddressdesc());
        rent.setText(singleflattext.getRent());

        return customView;



    }
}
