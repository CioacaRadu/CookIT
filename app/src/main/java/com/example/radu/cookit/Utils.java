package com.example.radu.cookit;

import java.util.List;

/**
 * Created by andreea on 18.03.2017.
 */

public class Utils {

    public static void scaleRecipe(List<Ingredient> ingredients, Integer initialServings, Integer newServings)
    {
        if(initialServings == null || newServings == null)
            return ;

        for (Ingredient ingredient : ingredients)
        {
            Double newQuantity = ingredient.getQuantity();
            if(newQuantity != null) {
                newQuantity = newQuantity * newServings / initialServings;
                ingredient.setQuantity(newQuantity);
            }
        }


    }

    public static int findIngredientIndex(List<Ingredient> ingredients, String name)
    {

        int i = 0;

        for (Ingredient ingredient : ingredients)
        {
            if( ingredient.getName().equals(name))
                return i;

            i++;
        }

        return -1;
    }
}
