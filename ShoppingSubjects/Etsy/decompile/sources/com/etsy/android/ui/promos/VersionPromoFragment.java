package com.etsy.android.ui.promos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.util.c;

public abstract class VersionPromoFragment extends EtsyFragment implements OnClickListener {
    private VersionPromo mPromo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPromo = (VersionPromo) getArguments().getSerializable("version_promo");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            EtsyDialogFragment etsyDialogFragment = (EtsyDialogFragment) parentFragment;
            etsyDialogFragment.setWindowAnimation(R.style.DialogAnimBottom);
            etsyDialogFragment.enableTouchInterceptPadding(getResources().getDimensionPixelSize(R.dimen.signin_dialog_padding));
            etsyDialogFragment.setDividerShown(false);
        }
    }

    public boolean handleBackPressed() {
        onPromoDismissed();
        return super.handleBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onPromoDismissed() {
        c.a((b) getAnalyticsContext(), (Context) getActivity(), this.mPromo);
    }
}
