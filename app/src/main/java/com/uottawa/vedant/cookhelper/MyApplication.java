package com.uottawa.vedant.cookhelper;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Mac on 2016-12-03.
 */

public class MyApplication extends Application {
    private ArrayList<Recipe> recipeList;

    public ArrayList<Recipe> getRecipeList() {
        if (this.recipeList == null) {
            recipeList = new ArrayList<Recipe>();
        }
        return recipeList;
    }

    public void setRecipeList(ArrayList<Recipe> someVariable) {
        this.recipeList = someVariable;
    }
}

