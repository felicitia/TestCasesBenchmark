package com.etsy.android.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.qualtrics.b;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class PurchasesActivity extends BOENavDrawerActivity implements b {
    public Context getContextForQualtricsPrompt() {
        return this;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            requireSignIn(EtsyAction.VIEW_PURCHASES);
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        QualtricsController.a((b) this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        QualtricsController.b((b) this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        return navigateUpAsBack();
    }

    /* access modifiers changed from: protected */
    public void onUserSignedInForAction(EtsyAction etsyAction) {
        super.onUserSignedInForAction(etsyAction);
        e.a((FragmentActivity) this).f().v();
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) findViewById(16908290);
    }
}
