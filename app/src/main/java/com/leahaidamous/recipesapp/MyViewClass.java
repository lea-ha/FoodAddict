package com.leahaidamous.recipesapp;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewClass extends RecyclerView.ViewHolder{

    public ImageView imgView;
    public TextView name, calories;




    public MyViewClass(@NonNull View itemView){
        super(itemView);
        imgView  = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.ingredient);
        calories = itemView.findViewById(R.id.arabic);


    }

}
