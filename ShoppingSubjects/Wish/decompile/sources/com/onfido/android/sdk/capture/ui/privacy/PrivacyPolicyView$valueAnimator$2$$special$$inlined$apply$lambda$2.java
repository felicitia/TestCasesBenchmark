package com.onfido.android.sdk.capture.ui.privacy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.LinearLayout;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;

public final class PrivacyPolicyView$valueAnimator$2$$special$$inlined$apply$lambda$2 extends AnimatorListenerAdapter {
    final /* synthetic */ c a;

    PrivacyPolicyView$valueAnimator$2$$special$$inlined$apply$lambda$2(c cVar) {
        this.a = cVar;
    }

    public void onAnimationEnd(Animator animator) {
        ViewExtensionsKt.animateToAlpha$default((LinearLayout) this.a.a._$_findCachedViewById(R.id.container), 1.0f, 0, 2, null);
    }
}
