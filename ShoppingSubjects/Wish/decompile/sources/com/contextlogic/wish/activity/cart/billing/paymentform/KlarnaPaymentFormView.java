package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.activity.webview.BaseWebViewClient;
import com.contextlogic.wish.activity.webview.WebViewSetupTask;
import com.contextlogic.wish.activity.webview.WebViewSetupTask.WebViewSetupCallback;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor;
import com.contextlogic.wish.payments.processing.KlarnaPaymentProcessor;

public class KlarnaPaymentFormView extends PaymentFormView {
    /* access modifiers changed from: private */
    public FrameLayout mContainerView;
    /* access modifiers changed from: private */
    public WebView mKlarnaWebView;
    /* access modifiers changed from: private */
    public View mKlarnaWebViewLoadingView;

    public boolean populateAndValidateParameters(Bundle bundle) {
        return true;
    }

    public void refreshUi() {
    }

    public void releaseImages() {
    }

    public boolean requiresNextButton() {
        return false;
    }

    public void restoreState(Bundle bundle) {
    }

    public KlarnaPaymentFormView(Context context) {
        super(context);
    }

    public KlarnaPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public KlarnaPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_payment_form_klarna, this);
        this.mContainerView = (FrameLayout) inflate.findViewById(R.id.cart_fragment_payment_form_klarna_container);
        this.mKlarnaWebViewLoadingView = inflate.findViewById(R.id.cart_fragment_payment_form_klarna_webview_loading_view);
    }

    /* access modifiers changed from: private */
    public void attachToWebView() {
        this.mKlarnaWebView.setWebViewClient(new BaseWebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return KlarnaPaymentFormView.this.processWebViewUrl(str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                KlarnaPaymentFormView.this.mKlarnaWebViewLoadingView.setVisibility(0);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                KlarnaPaymentFormView.this.mKlarnaWebViewLoadingView.setVisibility(8);
                KlarnaPaymentFormView.this.getUiConnector().showErrorMessage(KlarnaPaymentFormView.this.getContext().getString(R.string.webview_loading_error));
            }

            public void onPageFinished(WebView webView, String str) {
                KlarnaPaymentFormView.this.mKlarnaWebViewLoadingView.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean processWebViewUrl(String str) {
        if (str == null) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if (parse == null) {
            return false;
        }
        String host = parse.getHost();
        if (host != null && host.equalsIgnoreCase("close-browser")) {
            getUiConnector().showItemsView();
            return true;
        } else if (host != null && host.equalsIgnoreCase("klarna-loading-spinner")) {
            getUiConnector().showLoadingDialog();
            return true;
        } else if (parse.getPath() == null || !parse.getPath().equalsIgnoreCase(KlarnaPaymentProcessor.getKlarnaPurchaseConfirmationPath())) {
            return false;
        } else {
            getUiConnector().hideLoadingDialog();
            CartPaymentProcessor.performSuccessfulPaymentActions();
            getUiConnector().getCartContext().updatePreferredPaymentMode("PaymentModeKlarna");
            getUiConnector().showBrowser(str, true, false);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void resumeWebView() {
        if (this.mKlarnaWebView != null) {
            this.mKlarnaWebView.setVisibility(0);
            this.mKlarnaWebView.setFocusable(true);
            this.mKlarnaWebView.requestFocus(130);
            this.mKlarnaWebView.onResume();
        }
    }

    public void onAvailableContentHeightChanged(int i) {
        LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = i;
        setLayoutParams(layoutParams);
    }

    public void restoreImages() {
        resumeWebView();
    }

    public void recycle() {
        getUiConnector().withServiceProvider(new PaymentFormServiceProviderTask() {
            public void performTask(PaymentFormServiceProvider paymentFormServiceProvider) {
                paymentFormServiceProvider.cleanupCachedEmbeddedBillingWebView();
            }
        });
    }

    public void prepareToShow(PaymentFormShownContext paymentFormShownContext) {
        if (this.mKlarnaWebView == null) {
            getUiConnector().withServiceProvider(new PaymentFormServiceProviderTask() {
                public void performTask(PaymentFormServiceProvider paymentFormServiceProvider) {
                    KlarnaPaymentFormView.this.mKlarnaWebView = paymentFormServiceProvider.getCachedEmbeddedBillingWebView();
                    if (KlarnaPaymentFormView.this.mKlarnaWebView != null) {
                        if (KlarnaPaymentFormView.this.mKlarnaWebView.getParent() != null) {
                            ((ViewGroup) KlarnaPaymentFormView.this.mKlarnaWebView.getParent()).removeView(KlarnaPaymentFormView.this.mKlarnaWebView);
                        }
                        KlarnaPaymentFormView.this.attachToWebView();
                    } else {
                        KlarnaPaymentFormView.this.mKlarnaWebView = new WebView(KlarnaPaymentFormView.this.getContext());
                        paymentFormServiceProvider.setCachedEmbeddedBillingWebView(KlarnaPaymentFormView.this.mKlarnaWebView);
                        new WebViewSetupTask(KlarnaPaymentFormView.this.mKlarnaWebView, new WebViewSetupCallback() {
                            public void onWebviewReady() {
                                KlarnaPaymentFormView.this.attachToWebView();
                                KlarnaPaymentFormView.this.mKlarnaWebView.loadUrl(KlarnaPaymentProcessor.getKlarnaCheckoutUrl(KlarnaPaymentFormView.this.getUiConnector().getCartContext(), false));
                            }
                        }).execute(new Void[0]);
                    }
                    KlarnaPaymentFormView.this.mContainerView.addView(KlarnaPaymentFormView.this.mKlarnaWebView, 0, new FrameLayout.LayoutParams(-1, -1));
                    KlarnaPaymentFormView.this.resumeWebView();
                }
            });
        }
    }

    public String getPaymentModeName() {
        return CartBillingSection.KLARNA.name();
    }
}
