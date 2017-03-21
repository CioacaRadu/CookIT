package com.example.radu.cookit;

import android.os.AsyncTask;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreea on 18.03.2017.
 */

public class JSONAsyncTask extends AsyncTask<String, Void, ArrayList<Recipe>>{

    InputStream inputStream = null;
    String result = "";
    ArrayList<Recipe> recipes;



    @Override
    protected ArrayList<Recipe> doInBackground(String... params) {

        try {
            return RecipeJSONBuilder.readRecipesJSON(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> list) {

    }


}
