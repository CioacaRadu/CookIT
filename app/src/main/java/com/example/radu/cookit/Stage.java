package com.example.radu.cookit;

import java.io.Serializable;

/**
 * Created by andreea on 18.03.2017.
 */

public class Stage implements Serializable {

    private String instruction;


    public Stage(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }
}
