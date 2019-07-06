package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import java.util.HashMap;

/* compiled from: PhabletsActivity.kt */
public final class PhabletsActivity extends BOENavDrawerActivity implements a {
    private HashMap _$_findViewCache;

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
        setTitle(R.string.phablets);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().w();
        }
    }
}
