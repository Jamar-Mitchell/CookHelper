package com.uottawa.vedant.cookhelper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mac on 2016-11-30.
 */

//stores and loads all recipes from a file.
public class RecipeStorage {

    public static void saveRecipes(ArrayList<Recipe> recipes, Context ctx) {
        String recipeJSON = serializeRecipes(recipes);
        try {
            FileOutputStream outputStream = ctx.openFileOutput("recipes.json", ctx.MODE_PRIVATE);
            outputStream.write(recipeJSON.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Recipe> loadRecipes(Context ctx) {
        StringBuilder recipeJSON = new StringBuilder();
        try {
            FileInputStream inputStream = ctx.openFileInput("recipes.json");
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while(null != (line = r.readLine())) {
                recipeJSON.append(line);
            }
            r.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deserializeRecipes(recipeJSON.toString());
    }

    private static String serializeRecipes(ArrayList<Recipe> recipes) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(recipes, new TypeToken<ArrayList<Recipe>>(){}.getType());
    }

    private static ArrayList<Recipe> deserializeRecipes(String stuff) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(stuff, new TypeToken<ArrayList<Recipe>>(){}.getType());
    }

}
