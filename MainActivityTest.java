package com.example.clinic;

import junit.extensions.ActiveTestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActiveTestRule<MainActivity> mrule1= new ActiveTestRule<MainActivity>(MainActivity.class);
    private MainActivity ma=null;
    @Before
    public void setUp() throws Exception {
        ma= mrule.getActivity();

    }
    @Test
    public void startTest(){
        View view=ma.findViewById(R.id.textView);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        ma= null;
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onStart() {
    }
}