package com.contextlogic.wish.activity.webview;

import android.content.Intent;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.CartActivity;

public class KlarnaWebViewActivity extends WebViewActivity {
    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (chosePayPalFromKlarna(intent) || hasPayPalFromKlarnaError(intent)) {
            Intent intent2 = new Intent();
            intent2.setClass(this, CartActivity.class);
            intent2.putExtra(CartActivity.EXTRA_CHOSE_PAYPAL_FROM_KLARNA, chosePayPalFromKlarna(intent));
            intent2.putExtra(CartActivity.EXTRA_SHOW_CART_ERROR, hasPayPalFromKlarnaError(intent));
            startActivity(intent2);
            finishActivity();
        }
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new WebViewServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new WebViewFragment();
    }

    private boolean chosePayPalFromKlarna(Intent intent) {
        return intent != null && intent.getBooleanExtra(CartActivity.EXTRA_CHOSE_PAYPAL_FROM_KLARNA, false);
    }

    private boolean hasPayPalFromKlarnaError(Intent intent) {
        return intent != null && intent.getBooleanExtra(CartActivity.EXTRA_SHOW_CART_ERROR, false);
    }
}
