package com.etsy.android.ui.convos.convolistredesign;

import android.view.View;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.ui.BOENavDrawerActivity;
import java.util.HashMap;

/* compiled from: ConvoListActivity.kt */
public final class ConvoListActivity extends BOENavDrawerActivity implements a {
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

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r4) {
        /*
            r3 = this;
            super.onCreate(r4)
            r0 = 2131755486(0x7f1001de, float:1.9141853E38)
            r3.setTitle(r0)
            if (r4 != 0) goto L_0x0019
            r4 = r3
            android.support.v4.app.FragmentActivity r4 = (android.support.v4.app.FragmentActivity) r4
            com.etsy.android.ui.nav.e r4 = com.etsy.android.ui.nav.e.a(r4)
            com.etsy.android.ui.nav.c r4 = r4.f()
            r4.s()
        L_0x0019:
            android.content.Intent r4 = r3.getIntent()
            if (r4 == 0) goto L_0x002c
            android.os.Bundle r4 = r4.getExtras()
            if (r4 == 0) goto L_0x002c
            java.lang.String r0 = "convo_id"
            java.lang.String r4 = r4.getString(r0)
            goto L_0x002d
        L_0x002c:
            r4 = 0
        L_0x002d:
            if (r4 == 0) goto L_0x0042
            r0 = r3
            android.support.v4.app.FragmentActivity r0 = (android.support.v4.app.FragmentActivity) r0
            com.etsy.android.ui.nav.e r0 = com.etsy.android.ui.nav.e.a(r0)
            com.etsy.android.ui.nav.b r0 = r0.a()
            long r1 = java.lang.Long.parseLong(r4)
            r4 = 1
            r0.a(r1, r4)
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convolistredesign.ConvoListActivity.onCreate(android.os.Bundle):void");
    }
}
