package com.etsy.android.uikit.ui.bughunt;

import android.animation.Animator;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.util.s;
import com.etsy.android.uikit.a.b;
import com.etsy.android.uikit.c;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.HardwareAnimatorListener;

public class BugHuntActivity extends TrackingBaseActivity implements OnClickListener {
    private static final String ARG_MODE = "mode";
    private static final int MODE_LEADERBOARD = 0;
    private static final int MODE_SUBMIT_BUG = 1;
    FloatingActionButton mFab;
    int mMode;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(k.activity_bughunt);
        final Toolbar toolbar = (Toolbar) findViewById(i.app_bar_toolbar);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BugHuntActivity.this.mMode == 1) {
                    BugHuntActivity.this.setupLeaderboard();
                    s.a((View) toolbar);
                }
            }
        });
        this.mFab = (FloatingActionButton) findViewById(i.fab_bug);
        this.mFab.setOnClickListener(this);
        if (bundle != null) {
            this.mMode = bundle.getInt(ARG_MODE, 0);
            switch (this.mMode) {
                case 0:
                    setupLeaderboard();
                    return;
                case 1:
                    setupSubmitBug(null);
                    return;
                default:
                    return;
            }
        } else if (getIntent() == null || !getIntent().hasExtra("image_uri")) {
            setupLeaderboard();
        } else {
            setupSubmitBug(getIntent().getStringExtra("image_uri"));
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(ARG_MODE, this.mMode);
        super.onSaveInstanceState(bundle);
    }

    public void setupLeaderboard() {
        this.mMode = 0;
        getAppBarHelper().setTitle((CharSequence) "Leaderboard");
        getAppBarHelper().setNavigationIcon(getResources().getDrawable(g.ic_bug));
        this.mFab.setImageResource(g.ic_add);
        displayFab(true);
        a.a((FragmentActivity) this).b();
    }

    public void setupSubmitBug(String str) {
        this.mMode = 1;
        getAppBarHelper().setTitle((CharSequence) "Submit Bug");
        getAppBarHelper().setNavigationIcon(c.a(this, g.sk_ic_close, e.sk_white));
        Drawable drawable = getResources().getDrawable(g.ic_menu_send);
        drawable.setColorFilter(-1, Mode.SRC_IN);
        this.mFab.setImageDrawable(drawable);
        displayFab(true);
        a.a((FragmentActivity) this).b(str);
    }

    public void onBackPressed() {
        if (this.mMode == 1) {
            setupLeaderboard();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (this.mMode) {
            case 0:
                setupSubmitBug(null);
                return;
            case 1:
                a.a((FragmentActivity) this).c().send();
                return;
            default:
                return;
        }
    }

    public void displayFab(final boolean z) {
        float f = 1.0f;
        b b = b.a((View) this.mFab).b(z ? 0.1f : 1.0f, z ? 1.0f : 0.1f);
        float f2 = z ? 0.1f : 1.0f;
        if (!z) {
            f = 0.1f;
        }
        b.c(f2, f).a((HardwareAnimatorListener) new HardwareAnimatorListener(this.mFab) {
            public void onAnimationStart(Animator animator) {
                if (z) {
                    BugHuntActivity.this.mFab.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    BugHuntActivity.this.mFab.setVisibility(8);
                }
            }
        }).c();
    }
}
