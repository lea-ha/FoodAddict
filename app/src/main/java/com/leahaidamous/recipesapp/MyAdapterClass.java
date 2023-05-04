package com.leahaidamous.recipesapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAdapterClass extends RecyclerView.Adapter<MyViewClass> implements Filterable {
    Context context;
    List<ItemClass> items;
    List<ItemClass> filteredItems;
    int selectedPosition = RecyclerView.NO_POSITION; // initialize to no selection

    private Picasso picasso;

    public MyAdapterClass(Context context, List<ItemClass> items) {
        this.context = context;
        this.items = items;
        this.filteredItems = new ArrayList<>(items); // Initialize the filtered list with all items
        picasso = Picasso.get();
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewClass(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        ItemClass currentItem = filteredItems.get(position); // Get the item from the filtered list
        // Bind the data to the view
        holder.calories.setText(currentItem.getEnglishName());
        holder.name.setText(currentItem.getArabicName());
        String imageUrl = currentItem.getImageURL();
        picasso.load(imageUrl).into(holder.imgView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("recipeName", currentItem.getEnglishName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItems.size(); // Return the size of the filtered list
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchString = constraint.toString().toLowerCase();

                if (searchString.isEmpty()) {
                    filteredItems = new ArrayList<>(items); // If search string is empty, show all items
                } else {
                    List<ItemClass> tempFilteredList = new ArrayList<>();

                    for (ItemClass item : items) {
                        if (item.getEnglishName().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(item);
                        }
                    }

                    filteredItems = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                filterResults.count = filteredItems.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (List<ItemClass>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<String> ingrArray = new ArrayList<>();

    // Method to handle selection of an item
    public void selectItem(int position) {
        // Get the item at the selected position
        ItemClass selectedItem = filteredItems.get(position);

        // Add the ingredient field to the list
        ingrArray.add(selectedItem.getArabicName());

        // Update the selected position and notify the adapter
        setSelectedPosition(position);
    }

    // Getter method for ingrArray
    public List<String> getIngrArray() {
        return ingrArray;
    }



    // Method to update the selected position and notify the adapter
    public void setSelectedPosition(int position) {
        int previousPosition = selectedPosition;
        selectedPosition = position;

        // Notify the adapter of the change in selection
        if (previousPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(previousPosition);
        }
        if (selectedPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(selectedPosition);
        }
    }

    // Method to get the item at the selected position
    public ItemClass getSelectedItem() {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            return filteredItems.get(selectedPosition);
        } else {
            return null;
        }
    }
}
