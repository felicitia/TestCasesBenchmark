package com.contextlogic.wish.dialog;

import android.widget.Toast;
import com.contextlogic.wish.activity.BaseActivity;

public class ToastManager {
    private static ToastManager sInstance = new ToastManager();
    private Toast mToast;

    private ToastManager() {
    }

    public static ToastManager getInstance() {
        return sInstance;
    }

    public void showToast(BaseActivity baseActivity, String str) {
        showToast(baseActivity, str, false);
    }

    public void showToast(BaseActivity baseActivity, String str, boolean z) {
        cancelCurrentToast();
        this.mToast = Toast.makeText(baseActivity, str, z ? 1 : 0);
        this.mToast.show();
    }

    public void cancelCurrentToast() {
        if (this.mToast != null) {
            this.mToast.cancel();
            this.mToast = null;
        }
    }
}
