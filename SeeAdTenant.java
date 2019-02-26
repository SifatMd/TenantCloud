package com.example.dell.offline1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SeeAdTenant extends AppCompatActivity {
    public SQLiteDatabase database;

    public TextView flatname;
    public TextView houseaddress;
    public TextView rentofhouse;
    public TextView description;
    public ImageView adimage;
    public ImageView extraimage;
    public Button hirebutton;



    public String filename;
    public int position, pp;
    public String t1, t2, t3, t4, t5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_ad_tenant);

        flatname = (TextView) findViewById(R.id.textView20);
        houseaddress = (TextView) findViewById(R.id.textView21);
        rentofhouse = (TextView) findViewById(R.id.rentofhouse);
        description = (TextView) findViewById(R.id.Description);
        adimage = (ImageView) findViewById(R.id.adimage);
        extraimage = (ImageView) findViewById(R.id.extraimage);
        hirebutton = (Button) findViewById(R.id.hirebutton);

        hirebutton.setEnabled(true);
        hirebutton.setText("REQUEST TO HIRE");

        extraimage.setVisibility(View.INVISIBLE);

        Bundle data = getIntent().getExtras();
        filename = data.getString("filename");

        t1 = filename;
        t2 = t1.substring(5,7);
        t3 = t2.substring(0,1); //number
        t4 = t2.substring(1,2); //A or B
        pp = Integer.valueOf(t3);

        position = (pp*2)-2;
        if(t4.equals("B")) position = position+1;



        String text="";

        String line1="", line2="", line3="", line4="", line5="", line6="";
        int houseid = position+1;
        String ss1 = String.valueOf(houseid);

        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);

        Cursor cursor = database.rawQuery("select * from housetable",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("HouseId"))!=null){
                int one = cursor.getInt(cursor.getColumnIndex("HouseId"));
                line1 = cursor.getString(cursor.getColumnIndex("FlatName"));
                line2 = cursor.getString(cursor.getColumnIndex("Address"));
                line3 = cursor.getString(cursor.getColumnIndex("Rent"));
                line4 = cursor.getString(cursor.getColumnIndex("GenDesc"));
                line6 = cursor.getString(cursor.getColumnIndex("IsRequestSent"));
                if(one==houseid) {
                    break;
                }

                cursor.moveToNext();
            }
        }
        t5 = line6;

        if(line6.equals("0")==false){
            hirebutton.setEnabled(false);
            hirebutton.setText("Requested");
        }


        Cursor cursor1 = database.rawQuery("select * from houseimagetable",null);
        cursor1.moveToFirst();
        while(!cursor1.isAfterLast()){
            if(cursor1.getString(cursor1.getColumnIndex("HouseId"))!=null){
                int one = cursor1.getInt(cursor.getColumnIndex("HouseId"));
                if(one==houseid){
                    line5 = cursor1.getString(cursor1.getColumnIndex("Images"));
                    break;
                }
                cursor1.moveToNext();
            }
        }

        String AllImages[] = line5.split("\\r?\\n");



        flatname.setText(line1);
        houseaddress.setText(line2);
        rentofhouse.setText(line3);
        description.setText(line4);

        AnimationDrawable animation = new AnimationDrawable();

        String textin="";

        textin = line5;

        if(textin.equals("0")==true) {
            adimage.setImageResource(R.drawable.noimage);
        }
        else{
            String[] totallines = textin.split("\\r?\\n");
            //Toast.makeText(MainActivity.this,"Inside block" , Toast.LENGTH_SHORT).show();
            for(String line: totallines){
                if(line.equals("")==false){
                    try {

                        extraimage.setImageBitmap(BitmapFactory.decodeFile(line));

                        BitmapDrawable drawable = (BitmapDrawable) extraimage.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        Drawable d = new BitmapDrawable(getResources(), bitmap);
                        //animation.addFrame(getResources().getDrawable(R.drawable.background2), 2000);
                        animation.addFrame(d,2000);


                        //Toast.makeText(MainActivity.this, "Show Image", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this, "Cannot show image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }




        animation.setOneShot(false);


        adimage.setBackground(animation);

        // start the animation!
        animation.start();

        database.close();

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }



    public void RequestClicked(View v){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure you want to Send Request?");
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
        Toast.makeText(SeeAdTenant.this,"Request Sent!",Toast.LENGTH_SHORT).show();
        //String ss = ConstantClass.NIDNumber;
        //isvacantcollection[position] = ss;
        method();

        Intent intent = new Intent(this,ListOfVacantHouse.class);
        startActivity(intent);
    }



    public void method(){
        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);
        String ss = ConstantClass.NIDNumber;
        int houseid = position+1;
        String ss1 = String.valueOf(houseid);

        System.out.println("REQUEST SENT BY: "+ t5);

        String query = "update housetable set IsRequestSent = '"+ss+"' where HouseId = "+ss1;
        System.out.println("REQUEST SENT BY: "+ ss);
        database.execSQL(query);

        database.close();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
