package com.braintreepayments.browserswitch;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;

public abstract class BrowserSwitchFragment extends Fragment {
    protected Context mContext;
    protected int mRequestCode;

    public enum BrowserSwitchResult {
        OK,
        CANCELED,
        ERROR;
        
        private String mErrorMessage;

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        /* access modifiers changed from: private */
        public BrowserSwitchResult setErrorMessage(String str) {
            this.mErrorMessage = str;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name());
            sb.append(" ");
            sb.append(getErrorMessage());
            return sb.toString();
        }
    }

    public abstract void onBrowserSwitchResult(int i, BrowserSwitchResult browserSwitchResult, Uri uri);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mContext == null) {
            this.mContext = getActivity().getApplicationContext();
        }
        if (bundle != null) {
            this.mRequestCode = bundle.getInt("com.braintreepayments.browserswitch.EXTRA_REQUEST_CODE");
        } else {
            this.mRequestCode = Integer.MIN_VALUE;
        }
    }

    public void onResume() {
        super.onResume();
        if (isBrowserSwitching()) {
            Uri returnUri = BrowserSwitchActivity.getReturnUri();
            int i = this.mRequestCode;
            this.mRequestCode = Integer.MIN_VALUE;
            BrowserSwitchActivity.clearReturnUri();
            if (returnUri != null) {
                onBrowserSwitchResult(i, BrowserSwitchResult.OK, returnUri);
            } else {
                onBrowserSwitchResult(i, BrowserSwitchResult.CANCELED, null);
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("com.braintreepayments.browserswitch.EXTRA_REQUEST_CODE", this.mRequestCode);
    }

    public String getReturnUrlScheme() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName().toLowerCase().replace("_", ""));
        sb.append(".browserswitch");
        return sb.toString();
    }

    public void browserSwitch(int i, Intent intent) {
        if (i == Integer.MIN_VALUE) {
            onBrowserSwitchResult(i, BrowserSwitchResult.ERROR.setErrorMessage("Request code cannot be Integer.MIN_VALUE"), null);
        } else if (!isReturnUrlSetup()) {
            onBrowserSwitchResult(i, BrowserSwitchResult.ERROR.setErrorMessage("The return url scheme was not set up, incorrectly set up, or more than one Activity on this device defines the same url scheme in it's Android Manifest. See https://github.com/braintree/browser-switch-android for more information on setting up a return url scheme."), null);
        } else {
            this.mRequestCode = i;
            this.mContext.startActivity(intent);
        }
    }

    private boolean isBrowserSwitching() {
        return this.mRequestCode != Integer.MIN_VALUE;
    }

    private boolean isReturnUrlSetup() {
        Intent intent = new Intent("android.intent.action.VIEW");
        StringBuilder sb = new StringBuilder();
        sb.append(getReturnUrlScheme());
        sb.append("://");
        List queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent.setData(Uri.parse(sb.toString())).addCategory("android.intent.category.DEFAULT").addCategory("android.intent.category.BROWSABLE"), 0);
        if (queryIntentActivities == null || queryIntentActivities.size() != 1) {
            return false;
        }
        return true;
    }
}
