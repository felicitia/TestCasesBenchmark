package com.contextlogic.wish.activity.returnpolicy;

import android.os.Bundle;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;

public class ReturnPolicyActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ReturnPolicyFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ReturnPolicyServiceFragment();
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return ActivityAnimationTypes.SLIDE_UP;
    }
}
