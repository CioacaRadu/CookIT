package com.example.radu.cookit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        setTitle("ShopList");

        ListView listView = (ListView) findViewById(R.id.shop_listview);

        ArrayList<Recipe> recipelist = ShopItems.activeList;
        ArrayList<Ingredient> fingredients = new ArrayList<Ingredient>();



        for(Recipe r:recipelist) {
            ArrayList<Ingredient> inglist = r.getIngredients();

                for (Ingredient i : inglist) {
                    if (!i.getChecked()) fingredients.add(i);

                }

        }

        if(fingredients.size() != 0) listView.setAdapter(new IngredientsCustomAdapter(this, fingredients));

    }
}
