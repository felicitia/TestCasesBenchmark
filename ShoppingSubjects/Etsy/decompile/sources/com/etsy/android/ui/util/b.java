package com.etsy.android.ui.util;

import android.content.Context;
import android.os.AsyncTask;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.util.d;

/* compiled from: EtsyFollowUtil */
public class b extends d {

    /* compiled from: EtsyFollowUtil */
    protected class a extends c {
        public a(EtsyId etsyId, boolean z, com.etsy.android.uikit.util.d.b bVar) {
            super(etsyId, z, bVar);
        }

        /* access modifiers changed from: protected */
        public void b_() {
            a(this.d, false);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<User> kVar) {
            super.a(kVar);
            if (kVar != null && kVar.a() && kVar.e() > 0) {
                a(this.d, true);
            }
        }

        private void a(final boolean z, boolean z2) {
            z.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    com.etsy.android.contentproviders.a.d(b.this.a, a.this.c, z);
                    return null;
                }
            }, new Void[0]);
        }
    }

    public b(Context context, Object obj, String str) {
        super(context, obj, str);
    }

    public void a(EtsyId etsyId, boolean z, com.etsy.android.uikit.util.d.b bVar) {
        v.a().j().a(this.b, (g<Result>) new a<Result>(etsyId, z, bVar));
    }
}
