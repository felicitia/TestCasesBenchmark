package com.etsy.android.ui.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.util.g;
import com.etsy.android.lib.util.g.b;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.promos.VersionPromo;
import com.etsy.android.ui.util.c;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.etsy.android.util.d;
import com.etsy.android.util.f;

public abstract class BaseLaunchActivity extends BOENavDrawerActivity implements a {
    private g mCheckAlphaUpdate;
    private boolean mHasSyncedUser;
    protected boolean mIsFirstView;
    private VersionPromo mPendingPromo;
    f sessionManager;

    @NonNull
    public String getTrackingName() {
        return "home";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sessionManager.a(getIntent(), (Activity) this);
        if (bundle == null) {
            this.mIsFirstView = true;
            setupPromos();
        }
        ((EtsyApplication) getApplicationContext()).setLaunchedForUI(true);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mIsFirstView = false;
        if (intent.getBooleanExtra("HOME_RESET", false) && intent.getBooleanExtra("FORCED_SIGNOUT", false)) {
            showForcedSignOutDialog();
        }
        closeDrawer();
        intent.removeExtra("HOME_RESET");
        intent.removeExtra("HOME_INDEX");
        intent.removeExtra("FORCED_SIGNOUT");
    }

    private void showForcedSignOutDialog() {
        e.a((FragmentActivity) this).d().a((EtsyTrioDialogFragment.a) null, (int) R.string.ok, 0, 0, getString(R.string.auth_forced_signout_dialog_title)).setSubTitle(getString(R.string.auth_forced_signout_dialog));
    }

    private void setupPromos() {
        if (this.mIsFirstView) {
            final View decorView = getWindow().getDecorView();
            ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        j.b(decorView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                        BaseLaunchActivity.this.showUpdateOrVersionPromo();
                    }
                });
            }
        }
        this.mIsFirstView = false;
    }

    /* access modifiers changed from: private */
    public void showUpdateOrVersionPromo() {
        if (d.a() && !d.c()) {
            showAlphaUpgradePromo();
        }
        if (!c.c(getApplicationContext()) || d.a()) {
            getVersionPromo();
        } else {
            c.a(getFragmentActivity());
        }
    }

    private void getVersionPromo() {
        VersionPromo b = c.b(getFragmentActivity());
        if (b == null) {
            return;
        }
        if (!b.requiresUserSync() || this.mHasSyncedUser) {
            showVersionPromo(b);
        } else {
            this.mPendingPromo = b;
        }
    }

    private void showVersionPromo(VersionPromo versionPromo) {
        if (versionPromo != null) {
            if (isDrawerOpen()) {
                closeDrawer();
            }
            e.a((Activity) getFragmentActivity()).a(versionPromo);
        }
    }

    /* access modifiers changed from: protected */
    public void onUserSignedIn() {
        this.mHasSyncedUser = true;
        showUpdateOrVersionPromo();
    }

    /* access modifiers changed from: protected */
    public void onUserPrefsUpdated() {
        this.mHasSyncedUser = true;
        if (this.mPendingPromo != null) {
            showVersionPromo(this.mPendingPromo);
            this.mPendingPromo = null;
        }
    }

    private void showAlphaUpgradePromo() {
        if (c.b()) {
            createCheckAlphaUtil();
            this.mCheckAlphaUpdate.a((g.a) new g.a() {
                public void a(AppBuild appBuild) {
                    if (c.a((Context) BaseLaunchActivity.this, appBuild)) {
                        c.a((FragmentActivity) BaseLaunchActivity.this, (OnClickListener) new TrackingOnClickListener(appBuild) {
                            public void onViewClick(View view) {
                                BaseLaunchActivity.this.requestAlphaUpgrade();
                            }
                        }, appBuild.getVersion());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void requestAlphaUpgrade() {
        if (c.b()) {
            createCheckAlphaUtil();
            this.mCheckAlphaUpdate.a((b) null);
        }
    }

    private void createCheckAlphaUtil() {
        if (this.mCheckAlphaUpdate == null) {
            this.mCheckAlphaUpdate = new g(this);
        }
    }
}
