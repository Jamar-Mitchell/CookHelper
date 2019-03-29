package com.uottawa.vedant.cookhelper;

import java.io.Serializable;

/**
 * Created by Mac on 2016-11-30.
 */

// Contains the type attributes for a recipe.
public class FoodType implements Serializable{

    String name;

    public FoodType(String s){
        this.name = s;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String s){
        this.name = s;
    }
}
