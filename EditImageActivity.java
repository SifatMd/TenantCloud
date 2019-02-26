package com.example.dell.offline1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class EditImageActivity extends AppCompatActivity {
    public SQLiteDatabase database;

    public LinearLayout layout;
    private static final int SELECTED_PIC=1;
    public ImageView imageView1;
    public String imgDecodableString;
    public int clicked;
    public String filename;
    public int indx;
    public String fltnm;

    public int collection[] = new int[5];
    public String decodableStrings[] = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        imgDecodableString="";

        decodableStrings[0]="";
        decodableStrings[1]="";
        decodableStrings[2]="";
        decodableStrings[3]="";
        decodableStrings[4]="";
        collection[0]=0;
        collection[1]=0;
        collection[2]=0;
        collection[3]=0;
        collection[4]=0;

        layout = (LinearLayout) findViewById(R.id.layoutimage);
        imageView1 = (ImageView) findViewById(R.id.imageViewedit1);


        Bundle flatdata = getIntent().getExtras();
        filename = flatdata.getString("flatname");
        indx = flatdata.getInt("indexno");
        fltnm = flatdata.getString("fltnm");



        clicked=0;

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=1;
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECTED_PIC);
            }
        });



        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    public void OkClicked(View v){
        if(imgDecodableString.equals("")==false ) saveFile(filename,imgDecodableString);

        //Intent intent = new Intent(this,MainActivity.class);
        //startActivity(intent);
        //Toast.makeText(EditImageActivity.this,"Images Saved",Toast.LENGTH_SHORT).show();

    }

    public void AddImagesClicked(View v){

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PIC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == SELECTED_PIC && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                System.out.println("till this");
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                System.out.println("what the hell");
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                if(true) {
                    imageView1.setImageBitmap(BitmapFactory
                            .decodeFile(imgDecodableString));
                    clicked=0;
                    collection[0]=1;
                    decodableStrings[0]=imgDecodableString;
                }




            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }


    public void saveFile(String file, String text){
        int houseid = ConstantClass.HouseId;
        String ss1 = String.valueOf(houseid);
        String line1="", line2="";
        database = openOrCreateDatabase("project.db",MODE_PRIVATE,null);

        Cursor cursor = database.rawQuery("select * from houseimagetable",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("HouseId"))!=null){
                int one = cursor.getInt(cursor.getColumnIndex("HouseId"));
                if(one==houseid) {
                    line1 = cursor.getString(cursor.getColumnIndex("Images"));
                    break;
                }

                cursor.moveToNext();
            }
        }

        if(line1.equals("0")==true) line1 = "";
        line1+=text+"\n";
        String ss = "update houseimagetable set Images = '"+line1+"' where HouseId = "+ss1;
        database.execSQL(ss);

        Toast.makeText(EditImageActivity.this,"Image added!",Toast.LENGTH_SHORT).show();
        onBackPressed();
        database.close();

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* if(item.getItemId()==android.R.id.home){
            FlatRowClass.variable=0;
            finish();
        }
        return super.onOptionsItemSelected(item);*/

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("flatnamedone",fltnm);
        intent.putExtra("indexno",indx);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("flatnamedone",fltnm);
        intent.putExtra("indexno",indx);
        startActivity(intent);
        finish();
    }



}
