package com.leahaidamous.recipesapp;

import static com.leahaidamous.recipesapp.R.id.recipesSearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recipes = findViewById(recipesSearch);

        recipes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, CatalogActivity.class));
            }
        });

    }
}