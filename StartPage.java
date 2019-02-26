package com.example.dell.offline1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartPage extends AppCompatActivity {

    public Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        mHandler.postDelayed(new Runnable() {
                public void run() {
                    function();
                }
            }, 1800);



    }

    public void function(){
        Intent intent = new Intent(this,Homepage.class);
        startActivity(intent);
    }


}
