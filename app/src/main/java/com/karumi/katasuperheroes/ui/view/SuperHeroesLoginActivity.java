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

package com.karumi.katasuperheroes.ui.view;

import android.os.Bundle;

import com.karumi.katasuperheroes.R;
import com.karumi.katasuperheroes.SuperHeroesApplication;
import com.karumi.katasuperheroes.ui.presenter.SuperHeroLoginPresenter;

import javax.inject.Inject;

public class SuperHeroesLoginActivity extends BaseActivity implements SuperHeroLoginPresenter.View {

    @Inject
    SuperHeroLoginPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        initializePresenter();
        presenter.initialize();
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void initializeDagger() {
        SuperHeroesApplication app = (SuperHeroesApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    @Override
    public void showWrongCredentials() {

    }

    @Override
    public void openSuperHeroesScreen() {

    }
}
