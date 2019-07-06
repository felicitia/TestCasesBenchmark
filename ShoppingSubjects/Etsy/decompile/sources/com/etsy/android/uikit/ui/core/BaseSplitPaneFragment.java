package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.uikit.nav.ActivityNavigator;
import com.etsy.android.uikit.nav.FragmentNavigator;
import com.etsy.android.uikit.nav.c;

public abstract class BaseSplitPaneFragment<T, AN extends ActivityNavigator, FN extends FragmentNavigator> extends TrackingBaseFragment {
    private c<T> mIndicator;
    protected boolean mTwoPane;

    public abstract c<AN, FN> getNav();

    public abstract Fragment setListFragment(FN fn);

    public abstract Fragment setSecondaryFragment(FN fn, T t);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(k.activity_split_pane, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        if (view.findViewById(i.detail_container) != null) {
            this.mTwoPane = true;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(i.split_pane_layout);
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(i.list_container);
        if (findFragmentById == null) {
            findFragmentById = setListFragment(getNav().f().b(i.list_container).B());
        }
        if ((findFragmentById instanceof c) && linearLayout != null) {
            this.mIndicator = (c) findFragmentById;
            linearLayout.setShowDividers(0);
        }
    }

    public boolean showSecondaryContent(T t) {
        if (!this.mTwoPane || t == null) {
            return false;
        }
        setSecondaryFragment(getNav().e().B().b(i.detail_container), t);
        if (this.mIndicator != null) {
            this.mIndicator.a(t);
        }
        return true;
    }
}
