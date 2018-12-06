package com.li.mvpprogram.patternLock;

public class PatternLockPresenter implements PatternLockContract.Presenter {

    private PatternLockContract.View view;

    public PatternLockPresenter(PatternLockContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
