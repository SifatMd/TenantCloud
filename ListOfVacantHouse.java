package com.example.dell.offline1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListOfVacantHouse extends AppCompatActivity {

    public SQLiteDatabase database;

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_vacant_house);

        String text="";


        String line1="", line2="", line3="", line4="";

        int cnt=0;

        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);
        Cursor cursor2 = database.rawQuery("select * from housetable",null);
        cursor2.moveToFirst();
        while(!cursor2.isAfterLast()){
            if(cursor2.getString(cursor2.getColumnIndex("FlatName"))!=null){
                line1 = cursor2.getString(cursor2.getColumnIndex("IsPosted"));
                if(line1.equals("1")){
                    cnt++;
                }
                cursor2.moveToNext();
            }
        }

        String count = String.valueOf(cnt);
        //Toast.makeText(ListOfVacantHouse.this,count,Toast.LENGTH_SHORT).show();


        ArrayList<VacantHousesClass> listofvachouses = new ArrayList<>(cnt);


        Cursor cursor = database.rawQuery("select * from housetable",null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("FlatName"))!=null){
                line1 = cursor.getString(cursor.getColumnIndex("IsPosted"));
                line2 = cursor.getString(cursor.getColumnIndex("FlatName"));
                line3 = cursor.getString(cursor.getColumnIndex("Address"));
                line4 = cursor.getString(cursor.getColumnIndex("Rent"));

                if(line1.equals("1")){
                    VacantHousesClass a = new VacantHousesClass();
                    a.setFlatdesc(line2);
                    a.setAddressdesc(line3);
                    a.setRent(line4);
                    listofvachouses.add(a);
                    //Toast.makeText(ListOfVacantHouse.this,"What",Toast.LENGTH_SHORT).show();
                }

                cursor.moveToNext();
            }
        }


        database.close();

        String tt = String.valueOf(cnt);
        //Toast.makeText(ListOfVacantHouse.this,tt,Toast.LENGTH_SHORT).show();

        listView = (ListView) findViewById(R.id.listVacant);




        if(cnt>0) {
            ListAdapter AreaAdapter = new CustomAdapterVacantHouse(this, listofvachouses);

            listView.setAdapter(AreaAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    VacantHousesClass a = (VacantHousesClass) parent.getItemAtPosition(position);
                    String h1 = a.getFlatdesc();
                    String h2 = h1.substring(0,8);
                    h2 +="Images.txt";
                    h2=h2.substring(0,4)+'_'+h2.substring(6);
                    //Toast.makeText(ListOfVacantHouse.this,h2,Toast.LENGTH_SHORT).show();
                    call(h2);
                }
            });
        }



        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }


    public void call(String filename){
        Intent intent = new Intent(this,SeeAdTenant.class);
        intent.putExtra("filename",filename);
        startActivity(intent);

    }


    public void BackBtnClicked(View v){
        Intent intent = new Intent(this,SearchVacantHouse.class);
        startActivity(intent);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent(this,SearchVacantHouse.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




}
