package com.uottawa.vedant.cookhelper;

/**
 * Created by Mac on 2016-11-30.
 */

// contains the instructions of a created recipe.
public class Instruction {

    String instruction;

    public Instruction (String s){
        this.instruction = s;
    }

    public String getInstruction(){
        return this.instruction;
    }

    public void setInstruction(String s){
        this.instruction = s;
    }
}
