package com.example.radu.cookit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreea on 18.03.2017.
 */

public class IngredientsCustomAdapter extends BaseAdapter implements SearchView.OnCloseListener {

    private Context context;
    private ArrayList data;
    private static LayoutInflater inflater=null;

    public IngredientsCustomAdapter(Context context, ArrayList data) {
        this.context = context;
        this.data = data;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if(data.size() <= 0)
                return 1;
        return  data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.activity_ingredients_view_item, null);
        final CheckBox checkBox = (CheckBox) vi.findViewById(R.id.checkBox);

        String ingredientName = ((Ingredient)data.get(position)).getName();

        checkBox.setTag(ingredientName);
        checkBox.setText(((Ingredient)data.get(position)).toString());
        checkBox.setChecked(((Ingredient)data.get(position)).getChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int index = Utils.findIngredientIndex(data, checkBox.getTag().toString());
                ((Ingredient)data.get(index)).setChecked(isChecked);
            }
        });
        return vi;
    }

    @Override
    public boolean onClose() {


        return false;
    }
}
