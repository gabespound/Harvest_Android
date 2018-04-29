package com.spound.harvest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.ViewHolder>{

    private static final String TAG = "PlantListAdapter";

    private Context mContext;

    public PlantListAdapter(Context c){
        this.mContext = c;
    }

    public String hasContext(){
        if(this.mContext != null){
            return mContext.getPackageResourcePath();
        }
        return "false";
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutplantlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.image.setImageDrawable(MyApplication.myPlantList.get(position).getImage());
        holder.plantName.setText(MyApplication.myPlantList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return MyApplication.myPlantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView plantName;
        RelativeLayout plantListItem;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.plantImage);
            plantName = itemView.findViewById(R.id.plantName);
            plantListItem = itemView.findViewById(R.id.plantListItem);
        }
    }
}
