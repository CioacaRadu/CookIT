package com.example.radu.cookit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class RecipeAdapter extends BaseAdapter {



    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;


    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }


    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            // Inflate the custom row layout from your XML.
        convertView = mInflater.inflate(R.layout.list_item_recipe2, parent, false);




        // Get relevant subviews of row view
        TextView titleTextView = (TextView) convertView.findViewById(R.id.recipe_list_title);

        TextView detailTextView = (TextView) convertView.findViewById(R.id.recipe_list_detail);
        ImageView thumbnailImageView = (ImageView) convertView.findViewById(R.id.recipe_list_thumbnail);

        //Get corresponding recipe for row
        Recipe recipe = (Recipe) getItem(position);

        // Update row view's textviews to display recipe information
        titleTextView.setText(recipe.getTitle());
        detailTextView.setText(recipe.getDescription());
        thumbnailImageView.setImageDrawable(recipe.getDrawable());



        return convertView;
    }


}
