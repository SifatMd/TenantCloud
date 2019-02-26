package com.example.dell.offline1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ListOfFlats extends AppCompatActivity {
    public SQLiteDatabase database;

    ListView mylistviewofflats;
    ImageView housecondition;
    ImageView icon;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_flats);

        housecondition = (ImageView) findViewById(R.id.housecondition);
        icon = (ImageView) findViewById(R.id.icon);
        mylistviewofflats = (ListView) findViewById(R.id.MyFlats);

        icon.setImageResource(R.drawable.finalicon);

        String text1="";

        int cnt=0;

        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);
        Cursor c1 = database.rawQuery("select * from housetable",null);
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            if(c1.getString(c1.getColumnIndex("HouseId"))!=null){
                cnt++;
                c1.moveToNext();
            }
        }



        ArrayList<FlatRowClass> listofFlats = new ArrayList<>(cnt);




        Cursor c = database.rawQuery("select * from housetable",null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            FlatRowClass obj1;
            if(c.getString(c.getColumnIndex("HouseId"))!=null){
                String line1 = c.getString(c.getColumnIndex("FlatName"));
                String line2 = c.getString(c.getColumnIndex("OccupiedBy"));
                String line3 = c.getString(c.getColumnIndex("Address"));
                String line4 = line3.substring(0,8)+line3.substring(9);
                if(line2.equals("1")) obj1 = new FlatRowClass(line1.substring(0,8),R.drawable.houseunlocked, line4);
                else obj1 = new FlatRowClass(line1.substring(0,8),R.drawable.houselocked, line4);
                listofFlats.add(obj1);
                c.moveToNext();
            }
        }


        database.close();


        ListAdapter flatAdapter = new CustomAdapter(this,listofFlats);
        mylistviewofflats.setAdapter(flatAdapter);

        mylistviewofflats.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FlatRowClass a = (FlatRowClass) parent.getItemAtPosition(position);
                        if(a.getImageView()==R.drawable.houseunlocked) {
                            ConstantClass.HouseId = position+1;
                            callmethod(a.getText(), position);
                        }
                    }

                }

        );



        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



    }

    public void callmethod(String name, int pos){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("flatnamedone",name);
        intent.putExtra("indexno",pos);
        startActivity(intent);
    }




    public void BackClicked(View v){
        Intent intent = new Intent(this,LandlordHomapage.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            FlatRowClass.variable=0;
            finish();
        }
        Intent intent = new Intent(this,LandlordHomapage.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,LandlordHomapage.class);
        startActivity(intent);
    }



}
