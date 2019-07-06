package com.contextlogic.wish.activity;

public abstract class FullScreenActivity extends DrawerActivity {
    public final boolean canHaveMenu() {
        return false;
    }
}
