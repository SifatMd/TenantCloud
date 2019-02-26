package com.example.dell.offline1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class LandlordHomapage extends AppCompatActivity {
    public TextView loggedInAs;
    public TextView houses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord_homapage);

        loggedInAs = (TextView) findViewById(R.id.LoggedInAs);
        houses = (TextView) findViewById(R.id.houses);

        String temp = ConstantClass.personName.toString();

        loggedInAs.setText("You are logged in as "+temp);
        houses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method();

            }
        });


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    public void method(){
        Intent intent = new Intent(this, ListOfFlats.class);
        startActivity(intent);
    }

    public void LogoutClicked(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to Log Out?");
        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newmethod();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void newmethod(){
        Intent intent = new Intent(this,Homepage.class);
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        Intent intent = new Intent(this,ChoicePage.class);
        intent.putExtra("name",LoginPage.s1);
        intent.putExtra("isLandlord",LoginPage.s2);
        intent.putExtra("NIDNumber",LoginPage.s3);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }







}
