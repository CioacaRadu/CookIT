package com.example.radu.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrepareActivity extends AppCompatActivity {

    Recipe recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
        setTitle("ShopList");

        recipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        recipe.isActive = true;

        TextView titleView = (TextView) findViewById(R.id.prepare_title);

        TextView descriptionView= (TextView) findViewById(R.id.prepare_description);
        ImageView imageView = (ImageView) findViewById(R.id.prepare_image);


        titleView.setText(recipe.getTitle());

        descriptionView.setText(recipe.getDescription());
        descriptionView.setMovementMethod(new ScrollingMovementMethod());
        imageView.setImageDrawable(recipe.getDrawable());
        //set image

        ListView listView = (ListView) findViewById(R.id.prepare_listview);

        listView.setAdapter(new IngredientsCustomAdapter(this, recipe.getIngredients()));

    }

    public void startCook(View v) {

        boolean hasAll = true;

        ArrayList<Ingredient> ingredientList = recipe.getIngredients();

        for(Ingredient i:ingredientList) {

            if(i.getChecked() == false) hasAll = false;
        }

        if(hasAll) {
            Intent intent = new Intent(this, StageActivity.class);
            intent.putExtra("Recipe", recipe);
            startActivity(intent);
        }
        else {

            Toast.makeText(this, "Trebuie să ai toate ingredientele",
                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        if (ShopItems.activeList.contains(recipe) ) return;



        ShopItems.activeList.add(recipe);
        finish();
    }

}
