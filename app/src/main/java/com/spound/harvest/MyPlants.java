package com.spound.harvest;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyPlants extends android.support.v4.app.Fragment {


    View v;
    Context c;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.myplants, container, false);
        c = container.getContext();
        MyApplication.initPlantList(v, c);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.initPlantList(v, c);
    }

    public MyPlants() {

    }




}
