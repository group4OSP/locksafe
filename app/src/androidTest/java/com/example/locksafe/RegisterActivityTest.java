package com.example.locksafe;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.locksafe.R.string.register_successful;


import android.widget.Toast;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

  @Rule
  public ActivityTestRule<Register> activityRule = new ActivityTestRule<>(Register.class);

  @Test
  public void regSuccess(){
    onView(withId(R.id.txtEdit_name)).perform(typeText("Meghan"), closeSoftKeyboard());
    onView(withId(R.id.txtEdit_email)).perform(typeText("meghan@gmail.com") , closeSoftKeyboard());
    onView(withId(R.id.txtEdit_password)).perform(typeText("meghan123") , closeSoftKeyboard());
    onView(withId(R.id.txtEdit_cnfpassword)).perform(typeText("meghan123") , closeSoftKeyboard());
    onView(withId(R.id.registerbtn)).perform(click());
    onView(withText(register_successful)).inRoot(new ToastMatcher())
        .check(matches(withText(register_successful)));


  }

  @Test
  public void emailAlreadyExists(){
    onView(withId(R.id.txtEdit_name)).perform(typeText("Paris London"), closeSoftKeyboard());
    onView(withId(R.id.txtEdit_email)).perform(typeText("parislondon@gmail.com") , closeSoftKeyboard());
    onView(withId(R.id.txtEdit_password)).perform(typeText("londonparis123") , closeSoftKeyboard());
    onView(withId(R.id.txtEdit_cnfpassword)).perform(typeText("londonparis123") , closeSoftKeyboard());
    onView(withId(R.id.registerbtn)).perform(click());
    onView(withText(R.string.error_email)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

  }


}
