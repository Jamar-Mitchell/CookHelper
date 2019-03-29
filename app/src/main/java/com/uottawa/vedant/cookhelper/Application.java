package com.uottawa.vedant.cookhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import static com.uottawa.vedant.cookhelper.RecipeStorage.loadRecipes;

/**
 * Created by Mac on 2016-12-01.
 */

// this class loads the recipes from the database
public class Application extends android.app.Application {

    Context ctx;
    ArrayList<Recipe> recipes = new  ArrayList<Recipe>();
    @Override
    public void onCreate() {
        ctx = this.getApplicationContext();
        super.onCreate();
        recipes = loadRecipes(ctx);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            /*databaseSetup();*/

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }
}

