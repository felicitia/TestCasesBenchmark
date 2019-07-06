package com.contextlogic.wish.activity.managepayments;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.UiFragment;

public class ManagePaymentsFragment extends UiFragment<ManagePaymentsActivity> {
    private ManagePaymentsView mManagePaymentsView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.manage_payments_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mManagePaymentsView = (ManagePaymentsView) findViewById(R.id.manage_payments_view);
        if (this.mManagePaymentsView != null) {
            this.mManagePaymentsView.initializeUi();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mManagePaymentsView != null) {
            this.mManagePaymentsView.cancelAllRequests();
        }
    }
}
