package com.karumi.katasuperheroes.ui.presenter;

public class SuperHeroLoginPresenter extends Presenter<SuperHeroLoginPresenter.View> {

    public void login(String username, String password) {

    }

    public interface View extends Presenter.View {

        void showWrongCredentials();

        void openSuperHeroesScreen();
    }

}
