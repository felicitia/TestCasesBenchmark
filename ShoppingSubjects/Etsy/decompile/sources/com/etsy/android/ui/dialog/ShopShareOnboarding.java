package com.etsy.android.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.etsy.android.R;
import com.etsy.android.lib.logger.s;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aa;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ShopShareOnboarding extends EtsyFragment {
    private static final double DIM_AMOUNT = 0.8d;
    /* access modifiers changed from: private */
    public EtsyDialogFragment parentDialog;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_shop_share_onboarding, null);
        s.a("feed.onboarding.displayed");
        ((Button) inflate.findViewById(R.id.dismiss)).setOnClickListener(getOnClickListener());
        this.parentDialog = (EtsyDialogFragment) getParentFragment();
        this.parentDialog.hideHeaderAndClearBackground();
        this.parentDialog.setWindowMode(WindowMode.WRAP);
        this.parentDialog.setWindowBackgroundDim(0.8f);
        return inflate;
    }

    public OnClickListener getOnClickListener() {
        return new TrackingOnClickListener() {
            public void onViewClick(View view) {
                s.a("feed.onboarding.dismissed");
                SharedPreferencesUtility.c((Context) ShopShareOnboarding.this.getActivity(), "shop-share-mobile-onboarding");
                aa.b("shop-share-mobile-onboarding");
                ShopShareOnboarding.this.parentDialog.dismiss();
            }
        };
    }
}
