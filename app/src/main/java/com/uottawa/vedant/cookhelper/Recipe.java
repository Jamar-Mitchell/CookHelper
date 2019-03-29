package com.uottawa.vedant.cookhelper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mac on 2016-11-30.
 */

// this class contains all attributes of a recipe.

public class Recipe implements Serializable{

    private String name;
    private String description;
    private ArrayList<Ingredient> ingredients;
    private Category category;
    private FoodType type;

    public Recipe (String s, String d, Category c, FoodType t) {
        this.name = s;
        this.description = d;
        this.ingredients = new ArrayList<>();
        this.category = c;
        this.type = t;
    }

    /*public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    public void readObject(ObjectInputStream ois) throws IOException {

    }*/

    public String getName(){
        return this.name;
    }

    public String getDescription(){ return this.description; }

    public ArrayList<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public Category getCategory(){
        return this.category;
    }

    public FoodType getType() {
        return this.type;
    }

    public void setName(String s){
        this.name = s;
    }

    public void setIngredients(ArrayList<Ingredient> s){
        this.ingredients = s;
    }

    public void setDescription(String s) {
        this.description = s;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setType(FoodType type) {
        this.type = type;
    }
}
