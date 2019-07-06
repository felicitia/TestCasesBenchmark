package com.etsy.android.uikit.ui.core;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public abstract class TransparentActivity extends TrackingBaseActivity {
    View mRootView;

    public enum Tint {
        LIGHT,
        NORMAL,
        DARK
    }

    /* access modifiers changed from: protected */
    public boolean allowDismiss() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        this.mRootView = findViewById(i.window_container);
        if (allowDismiss() && this.mRootView != null) {
            this.mRootView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (view.getId() == i.window_container) {
                        TransparentActivity.this.goBack();
                    }
                }
            });
        }
    }

    public void goBackDelayed() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                TransparentActivity.this.goBack();
            }
        }, 200);
    }

    /* access modifiers changed from: protected */
    public void goBack() {
        finish();
        applyExitAnimation();
    }

    public void applyExitAnimation() {
        Intent intent = getIntent();
        if (intent != null) {
            overridePendingTransition(intent.getIntExtra("NAV_ANIM_BOTTOM_ENTER", 0), intent.getIntExtra("NAV_ANIM_TOP_EXIT", 0));
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return k.activity_transparent_overlay_frame;
    }

    /* access modifiers changed from: protected */
    public void setTint(int i, Tint tint) {
        Drawable background = getWindow().getDecorView().getBackground();
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(i));
        switch (tint) {
            case LIGHT:
                background.setAlpha(0);
                return;
            case DARK:
                background.setAlpha(255);
                return;
            default:
                background.setAlpha(140);
                return;
        }
    }
}
