package com.wish.android.shakedelegate;

import android.app.Activity;
import com.wish.android.shaky.Result;
import com.wish.android.shaky.ShakeDelegate;

public class ShakeDelegateWrapper extends ShakeDelegate {
    private ShakeDelegate delegate;

    public ShakeDelegateWrapper(ShakeDelegate shakeDelegate) {
        this.delegate = shakeDelegate;
    }

    public void collectData(Activity activity, Result result) {
        this.delegate.collectData(activity, result);
    }

    public void submit(Activity activity, Result result) {
        this.delegate.submit(activity, result);
    }

    public int getSensitivityLevel() {
        return this.delegate.getSensitivityLevel();
    }
}
