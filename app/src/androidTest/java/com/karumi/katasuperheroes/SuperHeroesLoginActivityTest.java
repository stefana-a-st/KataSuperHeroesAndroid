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

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.karumi.katasuperheroes.ui.view.SuperHeroesLoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SuperHeroesLoginActivityTest {

    @Rule
    public ActivityTestRule<SuperHeroesLoginActivity> activityRule =
            new ActivityTestRule<>(SuperHeroesLoginActivity.class, true, true);

    @Test
    public void showsErrorMessageIfWrongCredentials() {
        LoginRobot login = new LoginRobot();

        LoginResultRobot result = login
                .username("fail@google.com")
                .password("notapassword")
                .login();

        result.isFailure();
    }

    @Test
    public void openSuperHeroesIfValidCredentials() {
        LoginRobot login = new LoginRobot();

        LoginResultRobot result = login
                .username("ok@google.com")
                .password("securepassword")
                .login();

        result.isSuccess();
    }

}