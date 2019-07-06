package com.etsy.android.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.devconfigs.PrefsFragment;
import com.etsy.android.lib.devconfigs.a;
import com.etsy.android.lib.devconfigs.a.C0072a;
import com.etsy.android.lib.logger.k;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.qualtrics.b;
import com.etsy.android.uikit.BasePreferenceActivity;
import com.etsy.android.util.d;

public class EtsyPreferenceActivity extends BasePreferenceActivity implements C0072a, b {
    public Context getContextForQualtricsPrompt() {
        return this;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.preference_content, new PrefsFragment(), null);
            beginTransaction.commit();
        }
    }

    public a getBuildConfigs() {
        return d.d();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        com.etsy.android.lib.toolbar.a.b(getClass().getSimpleName());
        QualtricsController.a((b) this);
    }

    public void onStart() {
        super.onStart();
        if (!EtsyApplication.get().shouldUseNewMonitor()) {
            k.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        QualtricsController.b((b) this);
    }

    public void onStop() {
        if (!EtsyApplication.get().shouldUseNewMonitor()) {
            k.b();
        }
        super.onStop();
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) findViewById(16908290);
    }
}
