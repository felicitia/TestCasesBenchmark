package com.etsy.android.uikit.util;

import android.content.Context;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.CirclesRequest;
import com.etsy.android.lib.requests.EtsyRequest;

/* compiled from: FollowUtil */
public class d {
    /* access modifiers changed from: protected */
    public Context a;
    protected Object b;
    private String c;

    /* compiled from: FollowUtil */
    private static class a extends i<User> {
        private EtsyId a;
        private b c;

        public a(EtsyId etsyId, b bVar) {
            this.a = etsyId;
            this.c = bVar;
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<User> a() {
            if (!v.a().e()) {
                return null;
            }
            return CirclesRequest.findUserInCircle(this.a);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<User> kVar) {
            if (kVar != null && kVar.a()) {
                if (kVar.e() > 0) {
                    this.c.a();
                } else {
                    this.c.b();
                }
            }
        }
    }

    /* compiled from: FollowUtil */
    public interface b {
        void a();

        void b();
    }

    /* compiled from: FollowUtil */
    protected class c extends i<User> {
        private b a;
        /* access modifiers changed from: protected */
        public EtsyId c;
        protected boolean d;

        public c(EtsyId etsyId, boolean z, b bVar) {
            this.c = etsyId;
            this.d = z;
            this.a = bVar;
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<User> a() {
            if (!v.a().e()) {
                return null;
            }
            if (this.d) {
                return CirclesRequest.connectToUser(String.valueOf(this.c));
            }
            return CirclesRequest.removeFromUser(String.valueOf(this.c));
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k<User> kVar) {
            if (kVar.a() && this.a != null) {
                if (this.d) {
                    this.a.a();
                } else {
                    this.a.b();
                }
            }
        }
    }

    public d(Context context, Object obj, String str) {
        this.a = context.getApplicationContext();
        this.b = obj;
        this.c = str;
    }

    public void a(EtsyId etsyId, b bVar) {
        v.a().j().a(this.b, (g<Result>) new a<Result>(etsyId, bVar));
    }

    public void a(EtsyId etsyId, boolean z, b bVar) {
        v.a().j().a(this.b, (g<Result>) new c<Result>(etsyId, z, bVar));
    }
}
