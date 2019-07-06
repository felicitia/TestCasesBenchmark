package com.etsy.android.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.etsy.android.R;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.HashMap;

public class PromoDialogFragment extends EtsyFragment {
    private static final double DIM_AMOUNT = 0.2d;
    private TrackingOnClickListener mDialogClickListener;
    private int mLayoutId;
    /* access modifiers changed from: private */
    public OnClickListener mOnClickListener;
    /* access modifiers changed from: private */
    public IDialogFragment mParentDialog;
    /* access modifiers changed from: private */
    public String mPromoPref;

    public static PromoDialogFragment newInstance(int i, String str, OnClickListener onClickListener) {
        PromoDialogFragment promoDialogFragment = new PromoDialogFragment();
        promoDialogFragment.setLayoutOptions(i, str, onClickListener);
        return promoDialogFragment;
    }

    public void setLayoutOptions(int i, String str, OnClickListener onClickListener) {
        this.mLayoutId = i;
        this.mPromoPref = str;
        this.mOnClickListener = onClickListener;
        this.mDialogClickListener = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (view.getId() == R.id.dismiss_x) {
                    if (!TextUtils.isEmpty(PromoDialogFragment.this.mPromoPref)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("promo_version", PromoDialogFragment.this.mPromoPref);
                        b.a().b("update_beta_dismissed", NotificationCompat.CATEGORY_PROMO, hashMap);
                        SharedPreferencesUtility.a((Context) PromoDialogFragment.this.mActivity, PromoDialogFragment.this.mPromoPref, false);
                    }
                    PromoDialogFragment.this.mParentDialog.dismiss();
                } else if (view.getId() == R.id.layout_frame) {
                    if (PromoDialogFragment.this.mOnClickListener != null) {
                        PromoDialogFragment.this.mOnClickListener.onClick(view);
                    }
                    PromoDialogFragment.this.mParentDialog.dismiss();
                }
            }
        };
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mDialogClickListener;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_promo, null);
        if (this.mLayoutId > 0) {
            View inflate2 = layoutInflater.inflate(this.mLayoutId, null);
            View findViewById = inflate.findViewById(R.id.dismiss_x);
            findViewById.bringToFront();
            findViewById.setOnClickListener(this.mDialogClickListener);
            FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.layout_frame);
            frameLayout.addView(inflate2);
            frameLayout.setOnClickListener(this.mDialogClickListener);
        }
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.hideHeader();
        this.mParentDialog.setDialogGravity(80);
        this.mParentDialog.setWindowAnimation(R.style.DialogAnimBottom);
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mParentDialog.setWindowBackgroundDim(0.2f);
    }
}
