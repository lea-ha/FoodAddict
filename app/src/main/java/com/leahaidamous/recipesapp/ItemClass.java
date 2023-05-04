package com.leahaidamous.recipesapp;
public class ItemClass {
    private String recipe, calories;
    private String imageURL;

    private int position;

    public ItemClass(String recipe, String calories, String imageURL) {
        this.recipe = recipe;
        this.calories = calories;
        this.imageURL = imageURL;
    }

    public String getEnglishName() {
        return recipe;
    }

    public String getArabicName() {
        return calories;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setEnglishName(String recipe) {
        this.recipe = recipe;
    }

    public void setArabicName(String calories) {
        this.calories = calories;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
