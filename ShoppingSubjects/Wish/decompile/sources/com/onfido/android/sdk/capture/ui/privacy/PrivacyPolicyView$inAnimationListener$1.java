package com.onfido.android.sdk.capture.ui.privacy;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import com.onfido.android.sdk.capture.R;

public final class PrivacyPolicyView$inAnimationListener$1 implements AnimationListener {
    final /* synthetic */ PrivacyPolicyView a;

    PrivacyPolicyView$inAnimationListener$1(PrivacyPolicyView privacyPolicyView) {
        this.a = privacyPolicyView;
    }

    public void onAnimationEnd(Animation animation) {
        ((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).layout(((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).getLeft(), ((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).getTop(), ((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).getRight(), ((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).getBottom());
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
