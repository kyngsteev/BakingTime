package com.stephenomoarukhe.android.bakingtime.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Omoarukhe on 12/06/2017.
 */

public class Bake {

    private ArrayList<Recipe> recipe;

    public Bake(String bake_jason) throws JSONException {
        JSONArray bakeArray = new JSONArray(bake_jason);
        this.recipe = new ArrayList<>();
        for (int i = 0; i < bakeArray.length(); i++) {
            recipe.add(new Recipe(bakeArray.getJSONObject(i)));
        }
    }

    public void setRecipe(ArrayList<Recipe> recipe) {
        this.recipe = recipe;
    }

    public ArrayList<Recipe> getRecipe() {
        return recipe;
    }

}
