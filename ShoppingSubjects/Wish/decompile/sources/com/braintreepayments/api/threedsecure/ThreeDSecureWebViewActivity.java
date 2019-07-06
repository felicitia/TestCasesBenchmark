package com.braintreepayments.api.threedsecure;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.braintreepayments.api.models.ThreeDSecureAuthenticationResponse;
import com.braintreepayments.api.models.ThreeDSecureLookup;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Stack;

public class ThreeDSecureWebViewActivity extends Activity {
    private ActionBar mActionBar;
    private FrameLayout mRootView;
    private Stack<ThreeDSecureWebView> mThreeDSecureWebViews;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(2);
        ThreeDSecureLookup threeDSecureLookup = (ThreeDSecureLookup) getIntent().getParcelableExtra("com.braintreepayments.api.EXTRA_THREE_D_SECURE_LOOKUP");
        if (threeDSecureLookup == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("A ThreeDSecureLookup must be specified with ");
            sb.append(ThreeDSecureLookup.class.getSimpleName());
            sb.append(".EXTRA_THREE_D_SECURE_LOOKUP extra");
            throw new IllegalArgumentException(sb.toString());
        }
        setupActionBar();
        this.mThreeDSecureWebViews = new Stack<>();
        this.mRootView = (FrameLayout) findViewById(16908290);
        StringBuilder sb2 = new StringBuilder();
        try {
            sb2.append("PaReq=");
            sb2.append(URLEncoder.encode(threeDSecureLookup.getPareq(), "UTF-8"));
            sb2.append("&MD=");
            sb2.append(URLEncoder.encode(threeDSecureLookup.getMd(), "UTF-8"));
            sb2.append("&TermUrl=");
            sb2.append(URLEncoder.encode(threeDSecureLookup.getTermUrl(), "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            finish();
        }
        ThreeDSecureWebView threeDSecureWebView = new ThreeDSecureWebView(this);
        threeDSecureWebView.init(this);
        threeDSecureWebView.postUrl(threeDSecureLookup.getAcsUrl(), sb2.toString().getBytes());
        pushNewWebView(threeDSecureWebView);
    }

    /* access modifiers changed from: protected */
    public void pushNewWebView(ThreeDSecureWebView threeDSecureWebView) {
        this.mThreeDSecureWebViews.push(threeDSecureWebView);
        this.mRootView.removeAllViews();
        this.mRootView.addView(threeDSecureWebView);
    }

    /* access modifiers changed from: protected */
    public void popCurrentWebView() {
        this.mThreeDSecureWebViews.pop();
        pushNewWebView((ThreeDSecureWebView) this.mThreeDSecureWebViews.pop());
    }

    /* access modifiers changed from: protected */
    public void finishWithResult(ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse) {
        setResult(-1, new Intent().putExtra("com.braintreepayments.api.EXTRA_THREE_D_SECURE_RESULT", threeDSecureAuthenticationResponse));
        finish();
    }

    public void onBackPressed() {
        if (((ThreeDSecureWebView) this.mThreeDSecureWebViews.peek()).canGoBack()) {
            ((ThreeDSecureWebView) this.mThreeDSecureWebViews.peek()).goBack();
        } else if (this.mThreeDSecureWebViews.size() > 1) {
            popCurrentWebView();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void setActionBarTitle(String str) {
        if (this.mActionBar != null) {
            this.mActionBar.setTitle(str);
        }
    }

    private void setupActionBar() {
        this.mActionBar = getActionBar();
        if (this.mActionBar != null) {
            setActionBarTitle("");
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        setResult(0);
        finish();
        return true;
    }
}
