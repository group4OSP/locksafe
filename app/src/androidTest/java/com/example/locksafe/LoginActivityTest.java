package com.example.locksafe;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;
import android.provider.Telephony.Mms.Intents;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class LoginActivityTest {


  @Rule
  public IntentsTestRule<Login> intentsTestRule =
      new IntentsTestRule<>(Login.class);


  @Test
  public void checkIntent(){
    onView(withId(R.id.txtEdit_email)).perform(typeText("londonparis123@gmail.com"),closeSoftKeyboard());
    onView(withId(R.id.txtEdit_password)).perform(typeText("londonparis123"),closeSoftKeyboard());
    onView(withId(R.id.loginbtn)).perform(click());
    intented(hasComponent(Encrypt.class.getName()));



  }

  private void intented(Matcher<Intent> hasComponent) {
  }


}
