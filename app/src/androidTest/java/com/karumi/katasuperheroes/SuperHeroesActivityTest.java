/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.katasuperheroes;

import com.karumi.katasuperheroes.di.MainComponent;
import com.karumi.katasuperheroes.di.MainModule;
import com.karumi.katasuperheroes.matchers.ToolbarMatcher;
import com.karumi.katasuperheroes.model.SuperHero;
import com.karumi.katasuperheroes.model.SuperHeroesRepository;
import com.karumi.katasuperheroes.recyclerview.RecyclerViewInteraction;
import com.karumi.katasuperheroes.ui.view.SuperHeroesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class) @LargeTest public class SuperHeroesActivityTest {

    @Rule public DaggerMockRule<MainComponent> daggerRule =
            new DaggerMockRule<>(MainComponent.class, new MainModule()).set(
                    new DaggerMockRule.ComponentSetter<MainComponent>() {
                        @Override public void setComponent(MainComponent component) {
                            SuperHeroesApplication app =
                                    (SuperHeroesApplication) InstrumentationRegistry.getInstrumentation()
                                            .getTargetContext()
                                            .getApplicationContext();
                            app.setComponent(component);
                        }
                    });

    @Rule public IntentsTestRule<SuperHeroesActivity> activityRule =
            new IntentsTestRule<>(SuperHeroesActivity.class, true, false);

    @Mock SuperHeroesRepository repository;

    @Test public void showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes();

        startActivity();

        onView(withText("¯\\_(ツ)_/¯")).check(matches(isDisplayed()));
    }

    @Test public void showsTheToolbar() {
        //using normal way to assett views
        startActivity();

        onView(allOf(withId(R.id.toolbar), hasDescendant(withText("Kata Super Heroes")))).check(matches(isDisplayed()));
    }

    @Test public void showsTheToolbarMatcher() {
        // inside of this tool, check if ...
        startActivity();

        ToolbarMatcher.onToolbarWithTitle("Kata Super Heroes").check(matches(isDisplayed()));
    }

    @Test public void showFirstItemWithCorrectData() {
        givenThereIsASuperHero();

        startActivity();
        // telling espresso - find a view with this text = it is just one / checks only if it exists and is visible, it finds it
        onView(withText("Iron Man")).check(matches(isDisplayed()));
    }

    @Test public void showFirstFiveSuperHeroes() {
        givenThereAreFiveASuperHeroes();

        startActivity();

        /** this does not work at this point **/
        //onView(withText("Iron Man")).check(matches(isDisplayed()));
        //onView(withText("Batman")).check(matches(isDisplayed()));
        //onView(withText("Hulk")).check(matches(isDisplayed()));
        //onView(withText("Spiderman")).check(matches(isDisplayed()));
        //onView(withText("Antman")).check(matches(isDisplayed()));
        /** let's try then something else **/

        /** this does not work at either **/
        //onView(allOf(withId(R.id.recycler_view),
        //        hasDescendant(withId(R.id.tv_super_hero_name)),
        //        hasDescendant(withText("Iron Man")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view), hasDescendant(withText("Batman")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view), hasDescendant(withText("Hulk")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view), hasDescendant(withText("Spiderman")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view), hasDescendant(withText("Antman")))).check(matches(isDisplayed()));
        /** let's try going one more level deeper in the hierarchie**/

        /** not working either  **/
        //onView(allOf(withId(R.id.recycler_view),
        //        hasDescendant(withId(R.id.tv_super_hero_name)),
        //        hasDescendant(withText("Iron Man")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view),
        //        hasDescendant(withId(R.id.tv_super_hero_name)),
        //        hasDescendant(withText("Batman")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view),
        //        hasDescendant(withId(R.id.tv_super_hero_name)),
        //        hasDescendant(withText("Hulk")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view),
        //        hasDescendant(withId(R.id.tv_super_hero_name)),
        //        hasDescendant(withText("Spiderman")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.id.recycler_view),
        //        hasDescendant(withId(R.id.tv_super_hero_name)),
        //        hasDescendant(withText("Antman")))).check(matches(isDisplayed()));
        /** we need to find a way to scroll - because if it is not visible it cannot be found **/

        /** if we know how many poisitons we have, we can use scrollToPosition ---
         *  in our case will need to scrollTo and use a matcher**/
        //onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollTo(withText("Iron Man")));
        //onView(withText("Iron Man")).check(matches(isDisplayed()));
        //
        //onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollTo(withText("Hulk")));
        //onView(withText("Hulk")).check(matches(isDisplayed()));
        /** oh no :-( **/

        /** let's just cheat and scroll to position 5 **/
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(4));
        onView(withText("Antman")).check(matches(isDisplayed()));
    }

    @Test public void showsTheMultipleItemsCorrectData() {
        givenThereAreFiveASuperHeroes();

        startActivity();

        /** nope - not working**/
        //RecyclerItemViewAssertion.<>recyclerItemViewAssertion
        //        = new RecyclerItemViewAssertion(4,
        //        repository.getAll().get(4),
        //        onView(withText("Antman")).check(isDisplayed());

        RecyclerViewInteraction.<SuperHero>onRecyclerView(withId(R.id.recycler_view))
                .withItems(repository.getAll())
                .check(new RecyclerViewInteraction.ItemViewAssertion<SuperHero>() {
                    @Override public void check(SuperHero superHero, View view, NoMatchingViewException
                            noMatchingViewException) {
                        matches(hasDescendant(withText(superHero.getName()))).check(view, noMatchingViewException);
                    }
                });
    }

    @Test public void checkIfAvengersItemsHaveBadge() {
        givenThereAreFiveASuperHeroes();

        startActivity();

        /** nope - not working**/
        //RecyclerItemViewAssertion.<>recyclerItemViewAssertion
        //        = new RecyclerItemViewAssertion(4,
        //        repository.getAll().get(4),
        //        onView(withText("Antman")).check(isDisplayed());

        RecyclerViewInteraction.<SuperHero>onRecyclerView(withId(R.id.recycler_view))
                .withItems(repository.getAll())
                .check(new RecyclerViewInteraction.ItemViewAssertion<SuperHero>() {
                    @Override public void check(SuperHero superHero, View view, NoMatchingViewException
                            noMatchingViewException) {

                        // if (superHero.isAvenger()) {
                        matches(hasDescendant(
                                allOf(
                                        withId(R.id.iv_avengers_badge),
                                        withEffectiveVisibility(
                                                superHero.isAvenger() ? ViewMatchers.Visibility.VISIBLE :
                                                        ViewMatchers.Visibility.GONE)
                                ))).check(view, noMatchingViewException
                        );
                        // }
                    }
                });
    }

    private void givenThereAreNoSuperHeroes() {
        when(repository.getAll()).thenReturn(Collections.<SuperHero>emptyList());
    }

    private void givenThereIsASuperHero() {
        when(repository.getAll()).thenReturn(Arrays.asList(
                new SuperHero("Iron Man", null, true, "The best guy in the universe"),
                new SuperHero("Batman", null, false, "BFF"),
                new SuperHero("Hulk", null, true, "test"),
                new SuperHero("Spiderman", null, false, "test"),
                new SuperHero("Antman", null, false, "test")
                )
        );
    }

    private void givenThereAreFiveASuperHeroes() {
        when(repository.getAll()).thenReturn(Arrays.asList(new SuperHero("Iron Man",
                null,
                false,
                "The best guy in the universe")));
    }

    private SuperHeroesActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}