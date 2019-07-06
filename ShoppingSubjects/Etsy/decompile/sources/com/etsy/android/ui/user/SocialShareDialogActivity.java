package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class SocialShareDialogActivity extends TransparentActivity {
    private Fragment mFragment;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.mFragment = e.a((FragmentActivity) this).f().b((int) R.id.window_container).a(getIntent().getExtras()).x();
        }
        View rootView = getWindow().getDecorView().getRootView();
        if (rootView != null) {
            rootView.setBackgroundColor(getResources().getColor(R.color.sk_bg_gray));
        }
    }

    public void onBackPressed() {
        dismiss();
    }

    /* access modifiers changed from: protected */
    public void dismiss() {
        getAnalyticsContext().a("share_sheet_dismissed", null);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(0, R.anim.nav_frag_bottom_pop_exit).hide(this.mFragment).commit();
        goBackDelayed();
    }
}
