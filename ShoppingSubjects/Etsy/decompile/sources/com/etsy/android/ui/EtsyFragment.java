package com.etsy.android.ui;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.util.BOEOptionsMenuItemHelper;

public abstract class EtsyFragment extends NetworkLoaderFragment {
    /* access modifiers changed from: protected */
    public BaseActivity mActivity;

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
    }

    public void onFragmentResume() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        onCreateOptionsMenuWithIcons(menu, menuInflater);
        BOEOptionsMenuItemHelper.a(getContext(), menu);
    }
}
