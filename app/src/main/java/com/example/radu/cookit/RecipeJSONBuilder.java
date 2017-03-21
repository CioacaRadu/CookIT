package com.example.radu.cookit;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by andreea on 18.03.2017.
 */

public class RecipeJSONBuilder {

    public static ArrayList readRecipesJSON(String url) {

        ArrayList<Recipe> recipeList = new ArrayList<>();

        try {

            String jsonString = readUrl(url);
            JSONArray recipes = new JSONArray(jsonString);


            for (int i = 0; i < recipes.length(); i++) {

                JSONObject recipe = recipes.getJSONObject(i);


                if(recipe != null) {

                    String title = recipe.getString("name");

                    ArrayList<Ingredient> ingredientsList = new ArrayList<>();

                    JSONArray ingredients = recipe.getJSONArray("ingredients");
                    if(ingredients != null ) {
                        for (int j = 0; j < recipes.length(); j++) {

                            try {


                                if (ingredients.getJSONObject(j) != null) {
                                    String string = ingredients.getJSONObject(j).getString("quantity");
                                    Double quantity = null;
                                    String unit = null;


                                    if (string != null) {
                                        String[] strings = string.split(" ");
                                        if (strings.length >= 1) {
                                            try {
                                                Log.w("Warn","Q: " +  strings[0]);
                                                quantity = Double.parseDouble(
                                                        strings[0]);
                                            } catch (Exception e) {
                                                quantity = 1.00;
                                            }


                                        }
                                        if (strings.length >= 2) {

                                            unit = strings[1];
                                        }

                                    }

                                    String name = ingredients.getJSONObject(j).getString("name");

                                    ingredientsList.add(new Ingredient(name, quantity, unit));
                                }
                            }
                            catch (Exception e)
                            {

                            }
                        }

                    }

                    String description = recipes.getJSONObject(i).getString("description");
                    ArrayList<Stage> stageList = new ArrayList<>();
                    JSONArray stage = recipe.getJSONArray("steps");

                    for (int j = 0; j < stage.length(); j++) {

                        if(stage.get(j) != null)
                        {
                            Log.w("Warn", "Stage: " + stage.get(j).toString());
                            stageList.add(new Stage(stage.get(j).toString()));
                        }
                    }

                    String imageUrl = recipes.getJSONObject(i).getString("imageURL");

                    recipeList.add(new Recipe(title,description, imageUrl, ingredientsList, stageList ));
                }


                Log.w("Warn", recipeList.toString());
                Log.w("Warn", recipeList.get(i).getIngredients().get(0).toString());

            }

            Log.w("Warn","TestList "+ recipeList.size());
            return recipeList;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            Log.w("Warn", "2?????????????????????????????????????");
            return buffer.toString();
        }
        catch (Exception e)
        {
            Log.w("Warn", "2******************************************************");
            Log.w("Warn", e.toString());
            return  null;
        } finally {
            if (reader != null)
                reader.close();
        }
    }



}
