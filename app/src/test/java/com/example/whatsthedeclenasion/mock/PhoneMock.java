package com.example.whatsthedeclenasion.mock;

import com.example.whatsthedeclenasion.External;

public class PhoneMock implements External {

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    boolean debug = false;

    int randomCount = 0;
    public int random(int i){
        try {
            if(debug){
                int[] numbers = { 0, 1, 0, 1, 1, 0, 0, 1, 0, 1 };
                return numbers[randomCount]; //return i % numbers[randomCount%numbers.length];
            }

            int[] numbers = { 2, 1, 5, 0, 1, 4 };
            return numbers[randomCount]; //return i % numbers[randomCount%numbers.length];
        } finally {
            randomCount++;
        }

    }

    public void click(){

    }

    public void showText(String text){

    }

    public void showList(String[] list){

    }

}
