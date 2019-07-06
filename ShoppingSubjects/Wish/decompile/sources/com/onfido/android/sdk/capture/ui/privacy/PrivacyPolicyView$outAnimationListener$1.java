package com.onfido.android.sdk.capture.ui.privacy;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import com.onfido.android.sdk.capture.R;

public final class PrivacyPolicyView$outAnimationListener$1 implements AnimationListener {
    final /* synthetic */ PrivacyPolicyView a;

    PrivacyPolicyView$outAnimationListener$1(PrivacyPolicyView privacyPolicyView) {
        this.a = privacyPolicyView;
    }

    public void onAnimationEnd(Animation animation) {
        ((LinearLayout) this.a._$_findCachedViewById(R.id.webViewContainer)).layout(0, 0, 0, 0);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
