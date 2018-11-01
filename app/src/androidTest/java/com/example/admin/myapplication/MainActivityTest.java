package com.example.admin.myapplication;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.admin.myapplication.fragments.Dashboard;
import com.example.admin.myapplication.fragments.Received;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    //get the activity
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdealingResource(){
        IdlingRegistry.getInstance().register(EspressoTestingIdlingResource.getIdlingResource());
    }

    @After
    public void unRegisterIdealingResourece(){
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlingResource.getIdlingResource());
    }



    @Test
    public void movetoReceivedFragment(){
        //move to received fragment
        onView(withId(R.id.navigation_received)).perform(click());
//        EspressoTestingIdlingResource.increment();

        //check for recycler view visble
        onView(withId(R.id.rv_grievance)).check(matches(isDisplayed()));


        onView(withId(R.id.tv_submit)).perform(click());
//        EspressoTestingIdlingResource.decrement();
        //if recycler view visible
        onView(withId(R.id.action_filter)).perform(click());
        //if filter opens check bottom sheet opens or not
        onView(withId(R.id.bottom_sheet)).check(matches((isDisplayed())));
        //if bottom sheet opens
        onView(withId(R.id.tv_low)).perform(click());
        onView(withId(R.id.tv_apply)).perform(click());



    }
    @Test
    public void movetootherfragments(){
        //move to received fragment
        onView(withId(R.id.navigation_pending)).perform(click());
        //move to received fragment
        onView(withId(R.id.navigation_completed)).perform(click());
//        EspressoTestingIdlingResource.increment();

    }

}