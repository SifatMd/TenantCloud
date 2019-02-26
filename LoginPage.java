package com.example.dell.offline1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LoginPage extends AppCompatActivity {

    public SQLiteDatabase database;

    public EditText NIDInput;
    public EditText passwordinput;

    public String filenid;
    public String filepass;

    public String names;
    public String pass;

    public String name;
    public String passinput;

    public static String s1,s2,s3;

    public int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        NIDInput = (EditText) findViewById(R.id.NIDNumberinput);
        passwordinput = (EditText) findViewById(R.id.passwordinput);

        filenid = "NIDNumbers.txt";
        filepass = "Passwords.txt";




        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


    }


    public void LoginClicked(View v){
        name = NIDInput.getText().toString();
        passinput = passwordinput.getText().toString();



        String extra1="", extra2="";


        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);
        Cursor c = database.rawQuery("select * from signincheckuptable",null);

        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("NIDno"))!=null){
                String line1 = c.getString(c.getColumnIndex("NIDno"));

                String line2 = c.getString(c.getColumnIndex("Password"));
                String line3 = c.getString(c.getColumnIndex("Name"));

                String line4 = c.getString(c.getColumnIndex("IsLandlord"));

                if(line1.equals(name) && line2.equals(passinput)){
                    flag=1;
                    s3=line1;
                    s1=line3;
                    s2=line4;
                    methoddb(line3, line4, line1);
                }

                c.moveToNext();
            }
        }

        if(flag==0) Toast.makeText(LoginPage.this,"Incorrent NID or Password",Toast.LENGTH_SHORT).show();
        database.close();

    }

    public void methoddb(String l1, String l2, String ex){
        database.close();
        Intent intent = new Intent(this,ChoicePage.class);
        intent.putExtra("name",l1);
        intent.putExtra("isLandlord",l2);
        intent.putExtra("NIDNumber",ex);
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
