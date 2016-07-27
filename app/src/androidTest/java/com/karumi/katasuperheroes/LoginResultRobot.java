package com.karumi.katasuperheroes;

import com.karumi.katasuperheroes.ui.view.SuperHeroesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LoginResultRobot {
    public LoginResultRobot isSuccess() {
        intended(hasComponent(SuperHeroesActivity.class.getCanonicalName()));
        return this;
    }

    public LoginResultRobot isFailure() {
        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(isDisplayed()));
        return this;
    }
}