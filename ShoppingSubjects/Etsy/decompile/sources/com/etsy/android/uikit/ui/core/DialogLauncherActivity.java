package com.etsy.android.uikit.ui.core;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.toolbar.a;
import com.etsy.android.uikit.c;
import com.etsy.android.uikit.nav.TrackingBaseActivity;

public abstract class DialogLauncherActivity extends TrackingBaseActivity {
    private BaseDialogFragment mDialogFragment;

    public enum Tint {
        LIGHT,
        NORMAL,
        DARK
    }

    public BaseDialogFragment onStartDialogFragment(Bundle bundle) {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(k.activity_transparent_overlay_frame);
        setTitle(getIntent().getStringExtra("title"));
        getAppBarHelper().setNavigationIcon(c.a(this, g.sk_ic_close, e.sk_gray_70));
        if (bundle != null) {
            this.mDialogFragment = (BaseDialogFragment) getSupportFragmentManager().findFragmentByTag("dialog");
        }
        if (this.mDialogFragment == null) {
            this.mDialogFragment = onStartDialogFragment(getIntent().getExtras());
        }
        if (this.mDialogFragment == null) {
            Toast.makeText(this, o.whoops_somethings_wrong, 0).show();
            finish();
        }
    }

    public void onStart() {
        super.onStart();
        if (!EtsyApplication.get().shouldUseNewMonitor()) {
            com.etsy.android.lib.logger.k.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        a.b(getClass().getSimpleName());
    }

    public void onStop() {
        if (!EtsyApplication.get().shouldUseNewMonitor()) {
            com.etsy.android.lib.logger.k.b();
        }
        super.onStop();
    }

    public void finish() {
        super.finish();
        applyExitAnimation();
    }

    public void onBackPressed() {
        if (this.mDialogFragment == null || !this.mDialogFragment.handleBackPressed()) {
            if (this.mDialogFragment != null) {
                this.mDialogFragment.onCancel(this.mDialogFragment.getDialog());
            }
            super.onBackPressed();
        }
    }

    private void applyExitAnimation() {
        Intent intent = getIntent();
        if (intent != null) {
            overridePendingTransition(intent.getIntExtra("NAV_ANIM_BOTTOM_ENTER", 0), intent.getIntExtra("NAV_ANIM_TOP_EXIT", 0));
        }
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
