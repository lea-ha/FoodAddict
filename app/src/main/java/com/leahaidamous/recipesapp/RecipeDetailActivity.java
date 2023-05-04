package com.leahaidamous.recipesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView descriptionTextView;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        nameTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.detailsTextView);
        imgView = findViewById(R.id.imageView);

        String itemId = getIntent().getStringExtra("recipeName");

        FirebaseFirestore.getInstance().collection("recipes").document(itemId)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            DocumentSnapshot document = task.getResult();
                            String name = document.getString("name");
                            String instructions = document.getString("instructions");

                            nameTextView.setText(name);
                            descriptionTextView.setText(instructions);
                            String imageUrl = document.getString("link");
                            Picasso.get().load(imageUrl).into(imgView);



                        }
                    }
                });
    }

}
