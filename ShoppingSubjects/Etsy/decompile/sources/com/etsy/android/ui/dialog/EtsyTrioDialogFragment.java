package com.etsy.android.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class EtsyTrioDialogFragment extends EtsyFragment {
    /* access modifiers changed from: private */
    public a mCallback;
    private TrackingOnClickListener mClickListener;
    private int mNeutralText;
    private int mNoText;
    /* access modifiers changed from: private */
    public IDialogFragment mParentDialog;
    private int mYesText;

    public interface a {
        void a();

        void b();

        void c();
    }

    public static abstract class b implements a {
        public void a() {
        }

        public void b() {
        }

        public void c() {
        }
    }

    public static EtsyTrioDialogFragment newInstance(a aVar, int i, int i2, int i3) {
        EtsyTrioDialogFragment etsyTrioDialogFragment = new EtsyTrioDialogFragment();
        etsyTrioDialogFragment.init(aVar, i, i2, i3);
        return etsyTrioDialogFragment;
    }

    public void init(a aVar, int i, int i2, int i3) {
        this.mCallback = aVar;
        this.mYesText = i;
        this.mNoText = i2;
        this.mNeutralText = i3;
        this.mClickListener = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                switch (view.getId()) {
                    case R.id.dialog_neutral /*2131362152*/:
                        if (EtsyTrioDialogFragment.this.mCallback != null) {
                            EtsyTrioDialogFragment.this.mCallback.b();
                        }
                        EtsyTrioDialogFragment.this.mParentDialog.dismissAllowingStateLoss(false);
                        return;
                    case R.id.dialog_no /*2131362153*/:
                        if (EtsyTrioDialogFragment.this.mCallback != null) {
                            EtsyTrioDialogFragment.this.mCallback.c();
                        }
                        EtsyTrioDialogFragment.this.mParentDialog.dismissAllowingStateLoss(false);
                        return;
                    case R.id.dialog_yes /*2131362155*/:
                        if (EtsyTrioDialogFragment.this.mCallback != null) {
                            EtsyTrioDialogFragment.this.mCallback.a();
                        }
                        EtsyTrioDialogFragment.this.mParentDialog.dismissAllowingStateLoss(false);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_dialog_trio, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (IDialogFragment) getParentFragment();
        this.mParentDialog.setOkButtonVisibility(8);
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mParentDialog.setWindowBackgroundDim(0.6f);
        TextView textView = (TextView) getView().findViewById(R.id.dialog_no);
        textView.setOnClickListener(this.mClickListener);
        if (this.mNoText != 0) {
            textView.setText(this.mNoText);
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) getView().findViewById(R.id.dialog_yes);
        textView2.setOnClickListener(this.mClickListener);
        if (this.mYesText != 0) {
            textView2.setText(this.mYesText);
        } else {
            textView2.setVisibility(8);
        }
        TextView textView3 = (TextView) getView().findViewById(R.id.dialog_neutral);
        textView3.setOnClickListener(this.mClickListener);
        if (this.mNeutralText > 0) {
            textView3.setText(this.mNeutralText);
        } else {
            textView3.setVisibility(8);
        }
    }
}
