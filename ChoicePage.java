package com.example.dell.offline1;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChoicePage extends AppCompatActivity {
    public ImageView animateView;
    public TextView loggedin;

    public String a;
    public String b;
    public String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_page);

        ConstantClass.Flag=1;

        animateView = (ImageView) findViewById(R.id.animateView);
        loggedin = (TextView) findViewById(R.id.loggedinas);

        Bundle data = getIntent().getExtras();
        a = data.getString("name");
        b = data.getString("isLandlord");
        c = data.getString("NIDNumber");

        ConstantClass.personName = a;
        ConstantClass.isLandlord = b;
        ConstantClass.NIDNumber = c;

        loggedin.setText("You are logged in as "+a);


        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.house1), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.house2), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.house3), 3000);


        animation.setOneShot(false);
        animateView.setBackground(animation);

        // start the animation!
        animation.start();

        //Toast.makeText(ChoicePage.this,a+" "+b,Toast.LENGTH_SHORT).show();


    }



    public void LandlordClicked(View v){
        if(b.equals("1")){
            Intent intent = new Intent(this,LandlordHomapage.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(ChoicePage.this,"Logged In As Tenant",Toast.LENGTH_SHORT).show();
        }
    }

    public void TenantClicked(View v){
        if(b.equals("0")){
            Intent intent = new Intent(this,TenantHomePage.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(ChoicePage.this,"Logged In As Landlord",Toast.LENGTH_SHORT).show();
        }
    }






}
