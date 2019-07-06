package com.onfido.android.sdk.capture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;

public class LoadingFragment extends BaseFragment {
    public static LoadingFragment createInstance(String str) {
        LoadingFragment loadingFragment = new LoadingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", str);
        loadingFragment.setArguments(bundle);
        return loadingFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.onfido_fragment_loading, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.tv_message)).setText(getArguments().getString("message"));
        return inflate;
    }
}
