package com.etsy.android.ui.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class CoreActivity extends BOENavDrawerActivity implements a {
    l logCat;

    /* renamed from: com.etsy.android.ui.core.CoreActivity$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[EtsyAction.values().length];

        static {
            try {
                a[EtsyAction.VIEW_ORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            launchFragment();
        }
    }

    private void launchFragment() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("referral_args");
            if (extras.containsKey("shop_id")) {
                if (bundle != null) {
                    extras.putAll(bundle);
                }
                e.a((FragmentActivity) this).f().a(extras).a((EtsyId) extras.getSerializable("shop_id"), (EtsyId) extras.getSerializable("user_id"));
            } else if (extras.containsKey(ResponseConstants.SHOP_NAME)) {
                if (bundle != null) {
                    extras.putAll(bundle);
                }
                e.a((FragmentActivity) this).f().a(extras).b(extras.getString(ResponseConstants.SHOP_NAME));
            } else if (extras.containsKey("listing_id")) {
                getGraphiteTimerManager().a("view_listing.time_to_listing_loaded");
                e.a((FragmentActivity) this).f().a(bundle).a((EtsyId) extras.getSerializable("listing_id"));
            } else if (extras.containsKey(Collection.TYPE_COLLECTION)) {
                e.a((FragmentActivity) this).f().a(extras).b((Collection) extras.getSerializable(Collection.TYPE_COLLECTION));
            } else if (extras.containsKey("user_id")) {
                e.a((FragmentActivity) this).f().a(bundle).c((EtsyId) extras.getSerializable("user_id"));
            } else if (extras.containsKey(ResponseConstants.RECEIPT_ID) || extras.containsKey("receipt_transaction_id")) {
                requireSignIn(EtsyAction.VIEW_ORDER);
            } else {
                this.logCat.d("Started CoreActivity without valid fragment arguments.");
                finish();
            }
        } else {
            this.logCat.d("Started CoreActivity with no launch fragment arguments.");
            finish();
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.explore_action_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        return navigateUpAsBack();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("listing");
        if (findFragmentByTag != null) {
            findFragmentByTag.onActivityResult(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onUserSignedInForAction(EtsyAction etsyAction) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("referral_args");
            if (AnonymousClass1.a[etsyAction.ordinal()] == 1) {
                if (extras.containsKey(ResponseConstants.RECEIPT_ID)) {
                    e.a((FragmentActivity) this).f().a(bundle).d((EtsyId) extras.getSerializable(ResponseConstants.RECEIPT_ID));
                } else {
                    e.a((FragmentActivity) this).f().a(bundle).e((EtsyId) extras.getSerializable("receipt_transaction_id"));
                }
            }
        }
    }
}
