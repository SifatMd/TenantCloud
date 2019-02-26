package com.example.dell.offline1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Homepage extends AppCompatActivity {

    public SQLiteDatabase database;

    public ImageView imageIcon;
    public ImageView animateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        imageIcon = (ImageView) findViewById(R.id.imageIcon);
        animateView = (ImageView) findViewById(R.id.animateview);

        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.house1), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.house2), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.house3), 3000);


        String name1 = "12345678912345678";
        //String name1="a";
        String name2 = "23456789123456789";
        //String name2 = "b";
        String pass1 = "pass1";
        String pass2 = "pass2";

        String text1 = name1 + "\n" + name2;
        String text2 = pass1 + "\n" + pass2;
        String text3 = "Mukit Rashid\nShehab Sarar";
        String text4 = "1\n0";



        File databases = getApplicationContext().getDatabasePath("project.db");

        if (!databases.exists()) {
            database = openOrCreateDatabase("project.db", MODE_PRIVATE, null);
            database.execSQL("create table if not exists housetable(HouseId integer, FlatName text, Area text, Address text, LandlordNID text, OccupiedBy text, IsPosted text, Rent text, GenDesc text, IsRequestSent text)");
            database.execSQL("create table if not exists houseimagetable(HouseId integer, Images text)");
            database.execSQL("create table if not exists signincheckuptable(NIDno text, Password text, Name text, IsLandlord text)");


            String flats = "Flat #1A, House:22\nFlat #1B, House:22\nFlat #2A, House:22\nFlat #2B, House:22\nFlat #3A, House:22\nFlat #3B, House:22\nFlat #4A, House:22\nFlat #4B, House:22";
            String addresses = "Road-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka\nRoad-11, Gulshan-2,Dhaka";
            String ratingsall = "3.5\n3.5\n3.5\n3.5\n3.5\n3.5\n3.5\n3.5";
            String rents = "20,000\n30,000\n22,000\n23,000\n26,000\n28,000\n24,000\n27,000";
            String gendescs = "Best00000Best00000Best00000Best00000Best00000Best00000Best00000Best";
            String posted = "0\n0\n0\n0\n0\n0\n0\n0";
            String vacant = "12345678998765432\n12345678998765432\n1\n1\n1\n12345678998765432\n1\n12345678998765432";
            String imagetext = "1000001000001000001000001000001000001000001";
            String isrequestsent = "0\n0\n0\n0\n0\n0\n0\n0";

            String fl1[] = flats.split("\\r?\\n");
            String fl2[] = addresses.split("\\r?\\n");
            String fl3[] = rents.split("\\r?\\n");
            String fl4[] = gendescs.split("00000");
            String fl5[] = posted.split("\\r?\\n");
            String fl6[] = vacant.split("\\r?\\n");
            String fl7[] = isrequestsent.split("\\r?\\n");

            String t1[] = text1.split("\\r?\\n");
            String t2[] = text2.split("\\r?\\n");
            String t3[] = text3.split("\\r?\\n");
            String t4[] = text4.split("\\r?\\n");

            String dummynid = "12345678998765432";


            for (int i = 0; i < fl1.length; i++) {
                int j = i + 1;
                String ee = String.valueOf(j);
                String ss = "insert into housetable values(" + ee + ", '" + fl1[i] + "', 'Gulshan', '" + fl2[i] + "', '" + t1[0] + "', '" + fl6[i] + "', '" + fl5[i] + "', '" + fl3[i] + "', '" + fl4[i] + "', '" + fl7[i] + "')";
                database.execSQL(ss);
                String ss2 = "insert into houseimagetable values(" + ee + ", '0')";
                database.execSQL(ss2);
            }


            String s1 = "insert into signincheckuptable values('" + t1[0] + "', '" + t2[0] + "', '" + t3[0] + "', '" + t4[0] + "')";
            String s2 = "insert into signincheckuptable values('" + t1[1] + "', '" + t2[1] + "', '" + t3[1] + "', '" + t4[1] + "')";

            database.execSQL(s1);
            database.execSQL(s2);
            database.close();

        }


        animation.setOneShot(false);
        animateView.setBackground(animation);

        // start the animation!
        animation.start();


    }



    public void LoginClicked(View v){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void CreateClicked(View v){
        Toast.makeText(Homepage.this,"No Functionality",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
