package com.etsy.android.ui;

import android.content.Intent;
import android.net.MailTo;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.etsy.android.R;
import com.etsy.android.lib.config.b.e;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.n;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.cart.googlewallet.c;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.webview.EtsyWebViewClient;
import com.etsy.android.uikit.webview.WebChromeProgressBarClient;
import com.etsy.android.util.d;
import com.google.android.gms.wallet.MaskedWallet;
import java.util.HashMap;

public class EtsyWebFragment extends EtsyFragment {
    /* access modifiers changed from: private */
    public static final String TAG = f.a(EtsyWebFragment.class);
    /* access modifiers changed from: private */
    public String mCartId;
    /* access modifiers changed from: private */
    public AndroidPayDataContract mDataContract;
    /* access modifiers changed from: private */
    public View mErrorView;
    /* access modifiers changed from: private */
    public c mGoogleWalletWebViewHelper;
    /* access modifiers changed from: private */
    public boolean mIsPayPalCheckout;
    private MaskedWallet mMaskedWallet;
    private HashMap<String, String> mParameters;
    /* access modifiers changed from: private */
    public int mRedirectId;
    /* access modifiers changed from: private */
    public WebView mWebView;
    private View mWebViewCover;

    private class a extends EtsyWebViewClient {
        public a(com.etsy.android.lib.config.c cVar, ProgressBar progressBar) {
            super(cVar, progressBar);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            boolean z = true;
            if (shouldReplaceHostnameForGCP(str)) {
                webView.loadUrl(replaceHostnameInUrlIfGCP(str));
                return true;
            }
            Uri parse = Uri.parse(str);
            boolean c = EtsyWebFragment.this.getConfigMap().c(e.a);
            boolean c2 = EtsyWebFragment.this.getConfigMap().c(e.b);
            if (str.startsWith("tel:")) {
                EtsyWebFragment.this.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(str)));
                return true;
            } else if (str.startsWith("sms:")) {
                EtsyWebFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (MailTo.isMailTo(str)) {
                a(str);
                return true;
            } else if (str.startsWith("data:")) {
                b(str);
                return true;
            } else {
                if (EtsyWebFragment.this.mRedirectId == 13 && f(parse)) {
                    EtsyWebFragment.this.mCartId = (String) parse.getPathSegments().get(1);
                }
                if (EtsyWebFragment.this.isAndroidPayCart() && EtsyWebFragment.this.mGoogleWalletWebViewHelper != null && ((c || c2) && !str.contains("supports_android_pay"))) {
                    parse = parse.buildUpon().appendQueryParameter("supports_android_pay", String.valueOf(true)).build();
                }
                if (g(parse)) {
                    EtsyWebFragment.this.mIsPayPalCheckout = true;
                }
                if (EtsyWebFragment.this.isAndroidPayCart() && d(parse)) {
                    b.a().a("cart_payment");
                    if (EtsyWebFragment.this.mGoogleWalletWebViewHelper != null) {
                        EtsyWebFragment.this.mGoogleWalletWebViewHelper.b(EtsyWebFragment.this.mDataContract);
                    }
                } else if (!EtsyWebFragment.this.isAndroidPayCart() || !e(parse)) {
                    if (h(parse)) {
                        EtsyId i = i(parse);
                        String queryParameter = parse.getQueryParameter(NotificationCompat.CATEGORY_MESSAGE);
                        Uri parse2 = TextUtils.isEmpty(queryParameter) ? null : Uri.parse(queryParameter);
                        if (j(parse2)) {
                            k(parse2);
                        } else {
                            a(i, queryParameter);
                        }
                    } else if (j(parse)) {
                        k(parse);
                    } else if (c(parse)) {
                        EtsyWebFragment.this.mActivity.finish();
                    }
                } else if (EtsyWebFragment.this.mGoogleWalletWebViewHelper != null) {
                    EtsyWebFragment.this.mGoogleWalletWebViewHelper.e();
                }
                if (!b(parse) && !super.shouldOverrideUrlLoading(webView, str)) {
                    z = false;
                }
                return z;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            boolean c = EtsyWebFragment.this.getConfigMap().c(e.a);
            boolean c2 = EtsyWebFragment.this.getConfigMap().c(e.b);
            if (EtsyWebFragment.this.isAndroidPayCart() && EtsyWebFragment.this.mGoogleWalletWebViewHelper != null) {
                if (c || c2) {
                    EtsyWebFragment.this.mGoogleWalletWebViewHelper.g();
                }
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            EtsyWebFragment.this.mWebView.setVisibility(8);
            EtsyWebFragment.this.mErrorView.setVisibility(0);
        }

        private boolean a(Uri uri) {
            return n.b(uri.getScheme());
        }

        private boolean b(Uri uri) {
            return n.a(uri.getScheme());
        }

        private boolean c(Uri uri) {
            return b(uri) && "cart".equals(uri.getHost());
        }

        private boolean d(Uri uri) {
            if (!c(uri) || uri.getPathSegments().size() != 2 || !ResponseConstants.PAYMENT.equals(uri.getPathSegments().get(1))) {
                return false;
            }
            return true;
        }

        private boolean e(Uri uri) {
            if (!c(uri) || uri.getPathSegments().size() != 2 || !"purchase".equals(uri.getPathSegments().get(1))) {
                return false;
            }
            return true;
        }

        private boolean f(Uri uri) {
            if (!a(uri) || uri.getPathSegments().size() != 3 || !"cart".equals(uri.getPathSegments().get(0)) || !"review".equals(uri.getPathSegments().get(2))) {
                return false;
            }
            return true;
        }

        private boolean g(Uri uri) {
            return a(uri) && uri.getHost().endsWith("paypal.com");
        }

        private boolean h(Uri uri) {
            if ((!c(uri) || uri.getPathSegments().size() != 2 || !"cancel".equals(uri.getPathSegments().get(1))) && (uri.getPathSegments().size() != 1 || !"cancel".equals(uri.getPathSegments().get(0)))) {
                return false;
            }
            return true;
        }

        private EtsyId i(Uri uri) {
            if (!h(uri) || uri.getPathSegments().size() != 2) {
                return new EtsyId();
            }
            return new EtsyId((String) uri.getPathSegments().get(0));
        }

        private boolean j(Uri uri) {
            if (uri == null || !c(uri) || uri.getPathSegments().size() < 1 || !"thanks".equals(uri.getPathSegments().get(0))) {
                return false;
            }
            return true;
        }

        private void a(EtsyId etsyId, String str) {
            b.a().a("cart_cancel", (HashMap<String, Object>) new EtsyWebFragment$CartOverrideWebViewClient$1<String,Object>(this, etsyId, str));
            String str2 = "Processed cancel URI for cart %s with message: %s";
            Object[] objArr = new Object[2];
            objArr[0] = etsyId.getId();
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr[1] = str;
            b.a().b("cart_checkout", String.format(str2, objArr));
            EtsyWebFragment.this.mCartId = etsyId.getId();
            EtsyWebFragment.this.setActivityResultCheckoutCanceled();
            EtsyWebFragment.this.mActivity.finish();
        }

        private void k(Uri uri) {
            com.etsy.android.ui.nav.e.a(EtsyWebFragment.this.getActivity()).a().f().a(EtsyWebFragment.this.mDataContract, new EtsyId((String) uri.getPathSegments().get(1)), "1".equals(uri.getQueryParameter(ResponseConstants.SOCIAL_INVITES_FLAG)));
            EtsyWebFragment.this.mActivity.finish();
        }

        private void a(String str) {
            try {
                MailTo parse = MailTo.parse(str);
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra("android.intent.extra.EMAIL", new String[]{parse.getTo()});
                intent.putExtra("android.intent.extra.SUBJECT", parse.getSubject());
                intent.putExtra("android.intent.extra.TEXT", parse.getBody());
                intent.putExtra("android.intent.extra.CC", parse.getCc());
                if (intent.resolveActivity(EtsyWebFragment.this.mActivity.getPackageManager()) != null) {
                    EtsyWebFragment.this.startActivity(intent);
                } else {
                    b.a().b(EtsyWebFragment.TAG, "Unable to find application to handle email Intent");
                }
            } catch (ParseException unused) {
                b(str);
            }
        }

        private void b(String str) {
            StringBuilder sb = new StringBuilder("Attempt to load invalid URL: ");
            sb.append(str);
            WebBackForwardList copyBackForwardList = EtsyWebFragment.this.mWebView.copyBackForwardList();
            if (copyBackForwardList != null && copyBackForwardList.getSize() > 0) {
                sb.append(" from previous page: ");
                sb.append(copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex()).getOriginalUrl());
            }
            b.a().b(EtsyWebFragment.TAG, sb.toString());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mRedirectId = getArguments().getInt("redirect_id");
        this.mParameters = (HashMap) getArguments().getSerializable("parameters");
        this.mDataContract = (AndroidPayDataContract) getArguments().getSerializable(CartWithSavedActivity.CHECKED_OUT_CART);
        if (getArguments().containsKey("android_pay_masked_wallet")) {
            this.mMaskedWallet = (MaskedWallet) getArguments().getParcelable("android_pay_masked_wallet");
        }
        getActivity().getWindow().setSoftInputMode(16);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_webview, viewGroup, false);
        this.mWebView = (WebView) inflate.findViewById(R.id.webview);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.progress_bar);
        com.etsy.android.uikit.webview.b.a(this.mWebView, new a(getConfigMap(), progressBar), new WebChromeProgressBarClient(progressBar));
        this.mErrorView = inflate.findViewById(R.id.no_internet);
        this.mWebViewCover = inflate.findViewById(R.id.webview_cover);
        this.mErrorView.findViewById(R.id.btn_retry_internet).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                EtsyWebFragment.this.loadWebView();
            }
        });
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mGoogleWalletWebViewHelper = ((EtsyWebActivity) getActivity()).getGoogleWalletHelper();
        if (this.mGoogleWalletWebViewHelper != null) {
            this.mGoogleWalletWebViewHelper.a(this.mWebView, this.mWebViewCover);
        }
        loadWebView();
    }

    /* access modifiers changed from: private */
    public void loadWebView() {
        this.mWebView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mWebView.clearView();
        if (this.mParameters == null || !this.mParameters.containsKey("url")) {
            if (!d.b() && this.mDataContract != null && this.mDataContract.getLastPaymentMethod() != null && this.mDataContract.getLastPaymentMethod().isDirectCheckout()) {
                getActivity().getWindow().setFlags(8192, 8192);
            }
            boolean c = getConfigMap().c(e.a);
            boolean c2 = getConfigMap().c(e.b);
            if (isAndroidPayCart() && this.mMaskedWallet != null && (c || c2)) {
                this.mWebViewCover.setVisibility(0);
                this.mParameters.put("supports_android_pay", String.valueOf(true));
                this.mParameters.put("message_to_seller", this.mDataContract.getMessageToSeller());
            }
            if (this.mRedirectId == 1 || this.mRedirectId == 13 || this.mRedirectId == 12) {
                String a2 = com.usebutton.merchant.e.a(getContext());
                if (!TextUtils.isEmpty(a2)) {
                    this.mParameters.put("btn_ref", a2);
                }
            }
            this.mWebView.postUrl(com.etsy.android.uikit.webview.b.a(), com.etsy.android.uikit.webview.b.a(this.mParameters, this.mRedirectId));
            return;
        }
        this.mWebView.loadUrl(new com.etsy.android.uikit.webview.a(getConfigMap()).b((String) this.mParameters.get("url")));
    }

    public void onStop() {
        super.onStop();
        if (this.mRedirectId == 1 || this.mRedirectId == 13) {
            com.etsy.android.ui.cart.b.b();
            com.etsy.android.ui.cart.b.a(this.mActivity.getApplicationContext(), getRequestQueue());
        }
    }

    public void onDestroy() {
        getActivity().getWindow().clearFlags(8192);
        super.onDestroy();
        this.mGoogleWalletWebViewHelper = null;
    }

    public boolean handleBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        } else if (super.handleBackPressed()) {
            return true;
        } else {
            setActivityResultCheckoutCanceled();
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCartId() {
        return this.mCartId;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPayPalCheckout() {
        return this.mIsPayPalCheckout;
    }

    public void handleGoogleWalletResult(int i, int i2, Intent intent) {
        switch (i) {
            case PointerIconCompat.TYPE_HAND /*1002*/:
                handleMaskedWalletChanged(i2, intent);
                return;
            case PointerIconCompat.TYPE_HELP /*1003*/:
                handleFullWalletLoad(i2, intent);
                return;
            default:
                return;
        }
    }

    private void handleFullWalletLoad(int i, Intent intent) {
        com.etsy.android.ui.cart.a.a((com.etsy.android.lib.logger.b) getAnalyticsContext(), this.mDataContract, i);
        if (i != -1) {
            this.mGoogleWalletWebViewHelper.a(this.mGoogleWalletWebViewHelper.b(intent), getActivity(), this.mDataContract != null ? this.mDataContract.getCartId() : 0);
            return;
        }
        this.mGoogleWalletWebViewHelper.c(intent);
    }

    private void handleMaskedWalletChanged(int i, Intent intent) {
        com.etsy.android.ui.cart.a.b(getAnalyticsContext(), this.mDataContract, i);
        switch (i) {
            case -1:
                this.mMaskedWallet = c.a(intent);
                this.mGoogleWalletWebViewHelper.b(this.mMaskedWallet);
                this.mWebView.postUrl(com.etsy.android.uikit.webview.b.a(this.mDataContract), com.etsy.android.uikit.webview.b.a(c.a(this.mMaskedWallet)));
                return;
            case 0:
                this.mGoogleWalletWebViewHelper.f();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public boolean isAndroidPayCart() {
        return (this.mDataContract == null || this.mDataContract.getLastPaymentMethod() == null || !this.mDataContract.getLastPaymentMethod().isAndroidPay()) ? false : true;
    }

    /* access modifiers changed from: private */
    public void setActivityResultCheckoutCanceled() {
        Intent intent = new Intent();
        intent.putExtra("cart_id", this.mCartId);
        intent.putExtra("is_paypal", this.mIsPayPalCheckout);
        this.mActivity.setResult(0, intent);
    }
}
