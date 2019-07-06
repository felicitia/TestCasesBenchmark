package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class LegalInfoFragment extends EtsyFragment {
    private static final String TAG = f.a(LegalInfoFragment.class);
    private View mView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = layoutInflater.inflate(R.layout.fragment_legal_info, viewGroup, false);
        setUpCommonButtons();
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            ((IDialogFragment) getParentFragment()).setTitle(this.mActivity.getString(R.string.legal));
        }
    }

    private void setUpCommonButtons() {
        this.mView.findViewById(R.id.terms_row).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a((FragmentActivity) LegalInfoFragment.this.mActivity).a().b(5);
            }
        });
        this.mView.findViewById(R.id.privacy_row).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a((FragmentActivity) LegalInfoFragment.this.mActivity).a().b(6);
            }
        });
        this.mView.findViewById(R.id.communications_policy_row).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a((FragmentActivity) LegalInfoFragment.this.mActivity).a().b(7);
            }
        });
    }
}
