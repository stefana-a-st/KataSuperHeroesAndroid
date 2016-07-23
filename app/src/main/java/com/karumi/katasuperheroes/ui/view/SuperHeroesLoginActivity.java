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
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karumi.katasuperheroes.R;
import com.karumi.katasuperheroes.SuperHeroesApplication;
import com.karumi.katasuperheroes.ui.presenter.SuperHeroLoginPresenter;

import javax.inject.Inject;

import butterknife.Bind;

public class SuperHeroesLoginActivity extends BaseActivity implements SuperHeroLoginPresenter.View {

    @Inject
    SuperHeroLoginPresenter presenter;
    @Bind(R.id.login_email)
    EditText emailView;
    @Bind(R.id.login_password)
    EditText passwordView;
    @Bind(R.id.action_login)
    Button loginAction;

    @Override
    public int getLayoutId() {
        return R.layout.super_heroes_login_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        initializePresenter();
        bindListener();
        presenter.initialize();
    }

    private void initializeDagger() {
        SuperHeroesApplication app = (SuperHeroesApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void bindListener() {
        loginAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(emailView.getText().toString(), passwordView.getText().toString());
            }
        });
    }

    @Override
    public void showWrongCredentials() {
        Snackbar.make(loginAction, "Wrong credentials", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openSuperHeroesScreen() {
        SuperHeroesActivity.open(this);
    }
}
