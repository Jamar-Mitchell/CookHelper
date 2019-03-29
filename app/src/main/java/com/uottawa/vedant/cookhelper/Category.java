package com.uottawa.vedant.cookhelper;

import java.io.Serializable;

/**
 * Created by Mac on 2016-11-30.
 */

// this class contains category attribute for a recipe
public class Category implements Serializable{
    String name;

    public Category (String s){
        this.name = s;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
