package com.onfido.android.sdk.capture.ui.privacy;

import android.support.v7.widget.Toolbar;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;

public final class PrivacyPolicyView$getClickableSpan$1 extends ClickableSpan {
    final /* synthetic */ PrivacyPolicyView a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    PrivacyPolicyView$getClickableSpan$1(PrivacyPolicyView privacyPolicyView, String str, String str2) {
        this.a = privacyPolicyView;
        this.b = str;
        this.c = str2;
    }

    public void onClick(View view) {
        ((Toolbar) this.a._$_findCachedViewById(R.id.toolbar)).setTitle((CharSequence) this.b);
        ((WebView) this.a._$_findCachedViewById(R.id.webView)).loadUrl(this.c);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, (float) this.a.getHeight(), 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setAnimationListener(this.a.getInAnimationListener());
        ((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).startAnimation(translateAnimation);
        ViewExtensionsKt.toVisible((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer));
    }
}
