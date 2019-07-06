package com.etsy.android.ui.convos.convoredesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.convos.ConvoSentBroadcastReceiver;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.f;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.nav.b;
import java.util.HashMap;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: ConvoActivity.kt */
public final class ConvoActivity extends TrackingBaseActivity implements a {
    private HashMap _$_findViewCache;
    private ConvoSentBroadcastReceiver receiver;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.app_bar_with_content);
        Bundle bundle2 = null;
        Conversation3 conversation3 = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle2 = intent.getExtras();
        }
        long j = 0;
        if (bundle2 != null) {
            j = bundle2.getLong("convo_id", 0);
        }
        boolean z = true;
        if (bundle2 != null) {
            z = bundle2.getBoolean("convo_change_read_state", true);
        }
        e.a((FragmentActivity) this).f().a("convo_thread_fragment").a(j, z);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.receiver = com.etsy.android.lib.convos.a.a();
        registerReceiver(this.receiver, com.etsy.android.lib.convos.a.a(10));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        try {
            unregisterReceiver(this.receiver);
        } catch (IllegalArgumentException e) {
            CrashUtil.a().a((Throwable) e);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        p.b(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        com.etsy.android.uikit.util.e.a(getSupportFragmentManager(), getIntent(), b.b(this));
        return true;
    }

    public void onBackPressed() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("convo_thread_fragment");
        if (findFragmentByTag == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.uikit.IEtsyFragment");
        }
        f fVar = (f) findFragmentByTag;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        p.a((Object) supportFragmentManager, "supportFragmentManager");
        if (supportFragmentManager.getBackStackEntryCount() > 0 || fVar == null || !fVar.handleBackPressed()) {
            super.onBackPressed();
        }
    }
}
