package com.example.radu.cookit;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreea on 18.03.2017.
 */

public class Recipe implements Serializable{

    private String title;

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private String subTitle;
    private String description;

    private String image;
    private ArrayList ingredients;
    private List<Stage> stages;

    public boolean isActive = false;

    public Recipe(String title, String description, String image, ArrayList ingredients, List<Stage> stages) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.stages = stages;
    }

    private Integer servings;
    private Integer preparationTime;

    private Category category;

    private Double rating;

    public Recipe(String title, String subTitle, String description, String image, ArrayList ingredients, List<Stage> stages, Integer servings, Integer preparationTime, Category category, Double rating) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.stages = stages;
        this.servings = servings;
        this.preparationTime = preparationTime;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getDescription() {
        return description;
    }

    public  Drawable getDrawable() {
        try {
            ImageAsyncTask task = new ImageAsyncTask();
            Drawable d = task.execute(getImage()).get();
            return d;
        } catch (Exception e) {
            Log.e("Error", e.toString());
            return null;
        }
    }
}
