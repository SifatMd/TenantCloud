package com.example.dell.offline1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TenantHomePage extends AppCompatActivity {

    public TextView LoggedInAsTenant;
    public TextView SearchVacant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_home_page);

        LoggedInAsTenant = (TextView) findViewById(R.id.LoggedInAsTenant);
        SearchVacant = (TextView) findViewById(R.id.SearchVacant);

        LoggedInAsTenant.setText("You are logged in as "+ ConstantClass.personName.toString());

        SearchVacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methodtenant();
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    public void methodtenant(){
        Intent intent = new Intent(this, SearchVacantHouse.class);
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
        return super.onOptionsItemSelected(item);
    }




}
