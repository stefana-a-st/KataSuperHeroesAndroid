package com.karumi.katasuperheroes;

import android.support.test.espresso.matcher.ViewMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginRobot {

    public LoginRobot username(String username) {
        onView(ViewMatchers.withHint(R.string.hint_email))
                .perform(typeText(username), closeSoftKeyboard());
        return this;
    }

    public LoginRobot password(String password) {
        onView(withHint(R.string.hint_password))
                .perform(typeText(password), closeSoftKeyboard());

        return this;
    }

    public LoginResultRobot login() {
        onView(withText(R.string.action_login)).perform(click());
        return new LoginResultRobot();
    }

}
