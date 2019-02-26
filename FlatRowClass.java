package com.example.dell.offline1;

import android.widget.ImageView;

/**
 * Created by DELL on 13-May-17.
 */
public class FlatRowClass {
    private String text;
    private String address;



    private int imageView;
    public static int variable;
    public static String allImageString="";

    public FlatRowClass(String a, int b, String c){
        text=a;
        imageView=b;
        address = c;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
