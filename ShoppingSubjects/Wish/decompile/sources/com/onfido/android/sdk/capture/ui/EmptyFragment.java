package com.onfido.android.sdk.capture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onfido.android.sdk.capture.R;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class EmptyFragment extends BaseFragment {
    private HashMap a;

    public void _$_clearFindViewByIdCache() {
        if (this.a != null) {
            this.a.clear();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.onfido_fragment_empty, viewGroup, false);
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
