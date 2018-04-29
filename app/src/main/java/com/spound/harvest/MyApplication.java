package com.spound.harvest;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class MyApplication{

    private static MyApplication app;
    static ArrayList<Plant> myPlantList = new ArrayList<Plant>();
    static Gson gson = new Gson();

    private MyApplication() {}

    public static void savePlants(Context c){
        try {
            FileOutputStream fOS = c.openFileOutput("plantArrList", c.MODE_PRIVATE);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(myPlantList);
            oOS.close();
            fOS.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void loadPlants(Context c) {
        try {
            FileInputStream fIS = c.openFileInput("plantArrList");
            ObjectInputStream oIS = new ObjectInputStream(fIS);
            myPlantList = (ArrayList<Plant>)oIS.readObject();

            oIS.close();
            fIS.close();
        } catch (IOException|ClassNotFoundException ie) {
            ie.printStackTrace();
        }
        for (int i = 0; i < myPlantList.size(); i++){
            myPlantList.get(i).refresh(c);
        }
    }

    public static void initPlantList(View v, Context c){
        RecyclerView rV = v.findViewById(R.id.plantsRecycler);
        PlantListAdapter pLA = new PlantListAdapter(c);
        Log.d("PLA", pLA.hasContext());
        rV.setAdapter(pLA);
        rV.setLayoutManager(new LinearLayoutManager(c));
    }


}


