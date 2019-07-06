package com.contextlogic.wish.activity.managepayments;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;

public class ManagePaymentsActivity extends DrawerActivity {
    public String getActionBarTitle() {
        return getString(R.string.manage_payments);
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ManagePaymentsFragment();
    }
}
