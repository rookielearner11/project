package com.example.clinic;
import android.view.View;
import android.widget.TextView;
import static org.junit.Assert.*;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ProfileTest {
    @Rule
    public ActivityTestRule<Profile> myProfileTestRule= new ActivityTestRule(Profile.class);

    public Profile myProfile =null;

    @Before
    private void setUp() throws Exception {
        myProfile= myProfileTestRule.getActivity();
    }

    public void checkOnScreen(){
        View view=myProfile.findViewById(R.id.textView);
        assertNotNull(view);
    }
}