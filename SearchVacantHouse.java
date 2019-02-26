package com.example.dell.offline1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SearchVacantHouse extends AppCompatActivity {

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vacant_house);



        String areas[] = {"Ajimpur","Badda","Banani","Dhanmondi","Gulshan","Shantinagar","Zigatala"};
        ListAdapter AreaAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, areas);
        listView = (ListView) findViewById(R.id.listViewArea);
        listView.setAdapter(AreaAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a = String.valueOf(parent.getItemAtPosition(position));
                if(a.equals("Gulshan")){
                    method();
                }
            }
        });




        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }



    public void method(){
        Intent intent = new Intent(this,ListOfVacantHouse.class);
        startActivity(intent);
    }


    public void BackClicked(View v){
        Intent intent = new Intent(this,TenantHomePage.class);
        startActivity(intent);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            FlatRowClass.variable=0;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
