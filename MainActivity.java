package com.example.dell.offline1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    public SQLiteDatabase database;

    public ImageView imageView;
    public EditText description;
    public EditText rate;
    public TextView flatname1;
    public String filename;
    public ImageView image;
    public TextView address;
    public EditText rent;
    public Button PostAd;

    public int index;


    public String a;
    public String b;
    public String c;

    public String t1, t2, t3, t4, t5, t6, t7, t8;

    public String PostedString="0";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //System.out.println("What the hell");

        Bundle flatdata = getIntent().getExtras();
        filename = flatdata.getString("flatnamedone");
        index = flatdata.getInt("indexno");

        //System.out.println("index is " + index);

        //Toast.makeText(MainActivity.this,index,Toast.LENGTH_SHORT).show();

        String dummy1="";


        dummy1="";


        String line1="", line2="", line3="", line4="", line5="", line6="";


        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);
        int houseid = ConstantClass.HouseId;
        String ss1 = String.valueOf(houseid);

        Cursor cursor = database.rawQuery("select * from housetable",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("HouseId"))!=null){
                int one = cursor.getInt(cursor.getColumnIndex("HouseId"));
                if(one==houseid) {
                    line1 = cursor.getString(cursor.getColumnIndex("FlatName"));
                    line2 = cursor.getString(cursor.getColumnIndex("Address"));
                    line3 = cursor.getString(cursor.getColumnIndex("Rent"));
                    line4 = cursor.getString(cursor.getColumnIndex("GenDesc"));
                    line5 = cursor.getString(cursor.getColumnIndex("IsPosted"));
                    break;
                }

                cursor.moveToNext();
            }
        }

        cursor = database.rawQuery("select * from houseimagetable",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("HouseId"))!=null){
                int one = cursor.getInt(cursor.getColumnIndex("HouseId"));
                if(one==houseid){
                    line6 = cursor.getString(cursor.getColumnIndex("Images"));
                    break;
                }
                cursor.moveToNext();
            }
        }



        description = (EditText) findViewById(R.id.Description);
        rate = (EditText) findViewById(R.id.Rate);
        flatname1 = (TextView) findViewById(R.id.flatname1);
        imageView = (ImageView) findViewById(R.id.imagev);
        image = (ImageView) findViewById(R.id.imagetemp);
        address = (TextView) findViewById(R.id.address);
        PostAd = (Button) findViewById(R.id.postad);


        image.setVisibility(View.INVISIBLE);

        PostedString = line5;


        flatname1.setText(line1);
        address.setText(line2);
        rate.setText(line3);
        description.setText(line4);
        if(line5.equals("1")){
            PostAd.setText("UPDATE");
        }
        else{
            PostAd.setText("POST AD");
        }

        a = filename+"Description.txt";
        b = filename+"Rate.txt";
        c = filename+"Images.txt";

        a=a.substring(0,4)+'_'+a.substring(6);
        b=b.substring(0,4)+'_'+b.substring(6);
        c=c.substring(0,4)+'_'+c.substring(6);


        AnimationDrawable animation = new AnimationDrawable();

        String textin="";


        if(FlatRowClass.variable==1){
            FlatRowClass.allImageString=textin;
        }

        textin = line6;


        if(textin.equals("0")==true) {
            imageView.setImageResource(R.drawable.addimage);
        }
        else{
            String[] totallines = textin.split("\\r?\\n");
            //Toast.makeText(MainActivity.this,"Inside block" , Toast.LENGTH_SHORT).show();
            for(String line: totallines){
                if(line.equals("")==false){
                    try {

                        image.setImageBitmap(BitmapFactory.decodeFile(line));

                        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        //animation.addFrame(getResources().getDrawable(R.drawable.background2), 2000);
                        animation.addFrame(d,2000);


                        //Toast.makeText(MainActivity.this, "DB", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this, "Cannot show image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        //animation.addFrame(getResources().getDrawable(R.drawable.background2), 2000);
        //animation.addFrame(getResources().getDrawable(R.drawable.background3), 2000);
        animation.setOneShot(false);


        imageView.setBackground(animation);

        // start the animation!
        animation.start();

        database.close();


        //String xx = readFile(a);
       // String yy = readFile(b);

        //description.setText(xx);
        //rate.setText(yy);


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    public void EditImageClicked(View v){
        Intent i = new Intent(this,EditImageActivity.class);
        i.putExtra("flatname",c);
        i.putExtra("indexno",index);
        i.putExtra("fltnm",filename);
        startActivity(i);
    }

    public void PostAdClicked(View v) {
        savingfunction();

        //saveFile(a,x);
        //saveFile(b,y);
        Intent i = new Intent(this,ListOfFlats.class);
        startActivity(i);

    }

    public void savingfunction(){
        String x = description.getText().toString();
        String y = rate.getText().toString();
        String z = "1";

        if(PostedString.equals("0")) Toast.makeText(MainActivity.this,"Ad Posted!",Toast.LENGTH_LONG).show();
        else if(PostedString.equals("1")) Toast.makeText(MainActivity.this,"Updated!",Toast.LENGTH_LONG).show();

        int houseid = ConstantClass.HouseId;
        String ss1 = String.valueOf(houseid);

        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);
        String ss = "update housetable set GenDesc = '"+x+"', Rent = '"+y+"', IsPosted = '"+z+"' where HouseId = "+ss1;
        database.execSQL(ss);




        database.close();

    }


    public void saveFile(String file, String text){
        try{
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();

            Toast.makeText(MainActivity.this,"Ad Posted!",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(MainActivity.this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }


    public String readFile(String file){
        String text="";
        try{
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(MainActivity.this,"Error reading file",Toast.LENGTH_SHORT).show();
        }
        return text;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            try{
                String temporary = FlatRowClass.allImageString;
                FileOutputStream fos = openFileOutput(c, Context.MODE_PRIVATE);
                fos.write(temporary.getBytes());
                fos.close();

                //Toast.makeText(MainActivity.this,"Opened file",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "Error saving file", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
        Intent intent = new Intent(this, ListOfFlats.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this, ListOfFlats.class);
        startActivity(intent);
        finish();

    }


}
