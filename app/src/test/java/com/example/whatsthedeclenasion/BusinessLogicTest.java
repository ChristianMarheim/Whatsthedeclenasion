package com.example.whatsthedeclenasion;

import com.example.whatsthedeclenasion.mock.PhoneMock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BusinessLogicTest {

    FullscreenActivity logic;
    PhoneMock mock;

    // int[] numbers = { 2, 1, 0, 1, 1, 0 };

    @Before
    public void setup(){
        logic = new FullscreenActivity();
        mock = new PhoneMock();
        logic.setExternal(mock);
    }

    @Test
    public void getCategories() {
        mock.setDebug(true);
        logic.debug = true;
        String[] categories = logic.getCategories();
        int a = 0;
        int b = a;
    }

    @Test
    public void init() {

    }

    @Test
    public void draw() {

    }

    @Test
    public void click() {

    }

    @Test
    public void check() {

    }

    @Test
    public void wrong() {

    }

    @Test
    public void right() {

    }

    @Test
    public void drawAgain() {

    }

    @Test
    public void drawClickDrawAgainRepeat200() {

    }
}