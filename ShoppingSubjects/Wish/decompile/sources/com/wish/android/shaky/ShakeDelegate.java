package com.wish.android.shaky;

import android.app.Activity;

public abstract class ShakeDelegate {
    public void collectData(Activity activity, Result result) {
    }

    public int getSensitivityLevel() {
        return 23;
    }

    public boolean isEnabled() {
        return true;
    }

    public abstract void submit(Activity activity, Result result);
}
