package com.example.radu.cookit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ListViewController {

    private ListView mListView;
    public ArrayList<Recipe> listItems;
    public ArrayList<Recipe> activelistItems;
    private RecipeAdapter adapter;



    public ListViewController( Context context ){

        final Activity activ = (Activity) context;

        ArrayList<Ingredient> IngredientList = new ArrayList<Ingredient>();

        ArrayList<Stage> stagelist = new ArrayList<Stage>();

        stagelist.add(new Stage("Pune apa"));
        stagelist.add(new Stage("Pune sare, mâncațiaș gurița ta de bucătar"));
        stagelist.add(new Stage("Toarnă mălaiul drăguțule!"));
        IngredientList.add(new Ingredient("sare"));

        activelistItems = new ArrayList<>();
        listItems = new ArrayList<>();


        mListView = (ListView) activ.findViewById(R.id.listview);

        JSONAsyncTask task  = new JSONAsyncTask();
        try {
            listItems = task.execute("http://54.171.190.119:3000/").get();
            Log.w("Warn","TestList "+listItems.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        adapter = new RecipeAdapter(context, listItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterView, View view, int position, long id) {

                Recipe recipe = (Recipe) adapter.getItem(position);
                activelistItems.add(recipe);

                Intent intent = new Intent(activ, PrepareActivity.class);

                Log.w("Warn",recipe.getTitle());
                intent.putExtra("Recipe", recipe);
                activ.startActivity(intent);


            }
        });



    }



}
