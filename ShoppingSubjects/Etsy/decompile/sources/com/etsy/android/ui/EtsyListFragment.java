package com.etsy.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.ui.core.TrackingBaseListFragment;
import com.etsy.android.util.BOEOptionsMenuItemHelper;

@Deprecated
public abstract class EtsyListFragment extends TrackingBaseListFragment {
    /* access modifiers changed from: protected */
    public BaseActivity mActivity;
    protected Context mContext;

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
    }

    public void onFragmentResume() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity().getApplicationContext();
        this.mActivity = (BaseActivity) getActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (BaseActivity) getActivity();
    }

    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        onCreateOptionsMenuWithIcons(menu, menuInflater);
        BOEOptionsMenuItemHelper.a(getContext(), menu);
    }
}
