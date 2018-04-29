package com.spound.harvest;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class newPlant extends AppCompatActivity implements OnClickListener {

    private ImageButton plantCam;
    private FloatingActionButton savePlant;
    private EditText newPlantName;
    private EditText newPlantWF;
    private Bitmap bMP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newplant);
        plantCam = (ImageButton)findViewById(R.id.newPlantImage);
        savePlant = (FloatingActionButton)findViewById(R.id.savePlant);
        newPlantName = findViewById(R.id.newPlantName);
        newPlantWF = findViewById(R.id.newPlantWF);
        plantCam.setOnClickListener(this);
        savePlant.setOnClickListener(this);
        this.bMP = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bMP = (Bitmap)data.getExtras().get("data");
        plantCam.setImageBitmap(bMP);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.newPlantImage:{
                Log.d("plantCam", "clicked");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                break;
            }case R.id.savePlant:{
                try {
                     Log.d("SavePlant", "clicked");
                    String imgString = newPlantName.getText().toString() + "img";
                    FileOutputStream fOS = this.openFileOutput(imgString, this.MODE_PRIVATE);
                    ObjectOutputStream oOS = new ObjectOutputStream(fOS);
                    bMP.compress(Bitmap.CompressFormat.JPEG, 12, oOS);
                    oOS.close();
                    fOS.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
                Plant temp = new Plant(newPlantName.getText().toString(), this, newPlantWF.getText().toString());
                Log.d("plantBeingAdded", temp.toString());
                MyApplication.myPlantList.add(temp);
                MyApplication.savePlants(this);
                Log.d("SavePlant", "finished saving plant" + MyApplication.myPlantList.toString() + " end list");
                finish();
                break;
            }
        }
    }
}
