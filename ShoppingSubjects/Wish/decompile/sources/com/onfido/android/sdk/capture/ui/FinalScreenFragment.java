package com.onfido.android.sdk.capture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onfido.android.sdk.capture.common.SdkController;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class FinalScreenFragment extends MessageFragment {
    public static final Companion Companion = new Companion(null);
    private HashMap a;
    public FinalScreenPresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MessageFragment createInstance(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, StrongAuth.AUTH_TITLE);
            Intrinsics.checkParameterIsNotNull(str2, "subtitle");
            FinalScreenFragment finalScreenFragment = new FinalScreenFragment();
            finalScreenFragment.setInfo(str, str2);
            return finalScreenFragment;
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this.a != null) {
            this.a.clear();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        SdkController.getSdkComponent$default(SdkController.Companion.getInstance(), null, 1, null).inject(this);
        return onCreateView;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onStart() {
        super.onStart();
        FinalScreenPresenter finalScreenPresenter = this.presenter;
        if (finalScreenPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        finalScreenPresenter.trackFinalScreen(true);
    }

    public void onStop() {
        super.onStop();
        FinalScreenPresenter finalScreenPresenter = this.presenter;
        if (finalScreenPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        finalScreenPresenter.trackFinalScreen(false);
    }
}
