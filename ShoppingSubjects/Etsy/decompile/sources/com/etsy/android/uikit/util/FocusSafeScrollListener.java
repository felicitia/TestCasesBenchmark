package com.etsy.android.uikit.util;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.s;
import java.lang.ref.WeakReference;

public class FocusSafeScrollListener implements OnScrollListener {
    private final WeakReference<Activity> mActivityRef;

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public FocusSafeScrollListener(Activity activity) {
        this.mActivityRef = new WeakReference<>(activity);
    }

    @CallSuper
    public void onScrollStateChanged(AbsListView absListView, int i) {
        Activity activity = (Activity) this.mActivityRef.get();
        if (activity != null && (1 == i || 2 == i)) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                try {
                    currentFocus.clearFocus();
                    s.a(currentFocus);
                } catch (IllegalStateException e) {
                    CrashUtil.a().a((Throwable) e);
                }
            }
        }
    }
}
