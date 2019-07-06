package com.etsy.android.ui.convos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.convos.k;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;

public class ConvoComposeActivity extends BOENavDrawerActivity implements k, a {
    public boolean isTopLevelActivity() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            e.a((FragmentActivity) this).f().a(AnimationMode.FADE).a("convoFragment").a(getIntent().getExtras()).a((k) this);
        }
        setNavStyleModal();
    }

    public void onMessageSent() {
        finish();
    }
}
