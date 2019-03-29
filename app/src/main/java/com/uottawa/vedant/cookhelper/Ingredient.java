package com.uottawa.vedant.cookhelper;

import java.io.Serializable;

/**
 * Created by Mac on 2016-11-30.
 */

// contains the ingredients of a created recipe.
public class Ingredient implements Serializable {

    String name;

    public Ingredient (String s){
        this.name = s;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String s){
        this.name = s;
    }

    @Override
    public String toString() { return this.name; }

}
