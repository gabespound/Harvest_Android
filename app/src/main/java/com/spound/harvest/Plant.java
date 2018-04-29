package com.spound.harvest;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Plant implements Serializable{
    private String name;
    private String date;
    private String imageLoc;
    private transient Drawable image;
    private String waterFrequency;



    public Plant(String n, Context c, String wF){
        this.name = n;
        this.imageLoc = n + "img";
        refresh(c);
        Date time = Calendar.getInstance().getTime();
        this.date = MyApplication.gson.toJson(time);
        this.waterFrequency = wF;
    }

    public void refresh(Context c) {
        try {
            FileInputStream fIS = c.openFileInput(this.imageLoc);
            ObjectInputStream oIS = new ObjectInputStream(fIS);
            this.image = new BitmapDrawable(c.getResources(), BitmapFactory.decodeStream(oIS));
            oIS.close();
            fIS.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n){
        this.name = n;
    }

    public Drawable getImage() {
        return this.image;
    }

    public void setImage(Drawable d){
        this.image = d;
    }

    public String toString(){
        return " " + name + " " + date + " " + waterFrequency;
    }
}