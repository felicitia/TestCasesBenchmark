package com.facebook.internal;

import android.app.Activity;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.d;
import com.facebook.e;
import com.facebook.f;
import java.util.Iterator;
import java.util.List;

/* compiled from: FacebookDialogBase */
public abstract class g<CONTENT, RESULT> {
    protected static final Object a = new Object();
    private final Activity b;
    private final m c;
    private List<a> d;
    private int e;

    /* compiled from: FacebookDialogBase */
    protected abstract class a {
        public abstract a a(CONTENT content);

        public abstract boolean a(CONTENT content, boolean z);

        protected a() {
        }

        public Object a() {
            return g.a;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(CallbackManagerImpl callbackManagerImpl, e<RESULT> eVar);

    /* access modifiers changed from: protected */
    public abstract List<a> c();

    /* access modifiers changed from: protected */
    public abstract a d();

    protected g(Activity activity, int i) {
        aa.a((Object) activity, "activity");
        this.b = activity;
        this.c = null;
        this.e = i;
    }

    protected g(m mVar, int i) {
        aa.a((Object) mVar, "fragmentWrapper");
        this.c = mVar;
        this.b = null;
        this.e = i;
        if (mVar.c() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    public final void a(d dVar, e<RESULT> eVar) {
        if (!(dVar instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        a((CallbackManagerImpl) dVar, eVar);
    }

    public final void a(d dVar, e<RESULT> eVar, int i) {
        a(i);
        a(dVar, eVar);
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (f.a(i)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Request code ");
            sb.append(i);
            sb.append(" cannot be within the range reserved by the Facebook SDK.");
            throw new IllegalArgumentException(sb.toString());
        }
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public boolean a(CONTENT content) {
        return a(content, a);
    }

    /* access modifiers changed from: protected */
    public boolean a(CONTENT content, Object obj) {
        boolean z = obj == a;
        for (a aVar : e()) {
            if ((z || z.a(aVar.a(), obj)) && aVar.a(content, false)) {
                return true;
            }
        }
        return false;
    }

    public void b(CONTENT content) {
        b(content, a);
    }

    /* access modifiers changed from: protected */
    public void b(CONTENT content, Object obj) {
        a c2 = c(content, obj);
        if (c2 == null) {
            String str = "No code path should ever result in a null appCall";
            Log.e("FacebookDialog", str);
            if (f.b()) {
                throw new IllegalStateException(str);
            }
        } else if (this.c != null) {
            f.a(c2, this.c);
        } else {
            f.a(c2, this.b);
        }
    }

    /* access modifiers changed from: protected */
    public Activity b() {
        if (this.b != null) {
            return this.b;
        }
        if (this.c != null) {
            return this.c.c();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Intent r3, int r4) {
        /*
            r2 = this;
            android.app.Activity r0 = r2.b
            if (r0 == 0) goto L_0x000a
            android.app.Activity r0 = r2.b
            r0.startActivityForResult(r3, r4)
            goto L_0x0031
        L_0x000a:
            com.facebook.internal.m r0 = r2.c
            if (r0 == 0) goto L_0x0036
            com.facebook.internal.m r0 = r2.c
            android.app.Fragment r0 = r0.a()
            if (r0 == 0) goto L_0x0020
            com.facebook.internal.m r0 = r2.c
            android.app.Fragment r0 = r0.a()
            r0.startActivityForResult(r3, r4)
            goto L_0x0031
        L_0x0020:
            com.facebook.internal.m r0 = r2.c
            android.support.v4.app.Fragment r0 = r0.b()
            if (r0 == 0) goto L_0x0033
            com.facebook.internal.m r0 = r2.c
            android.support.v4.app.Fragment r0 = r0.b()
            r0.startActivityForResult(r3, r4)
        L_0x0031:
            r3 = 0
            goto L_0x0038
        L_0x0033:
            java.lang.String r3 = "Failed to find Activity or Fragment to startActivityForResult "
            goto L_0x0038
        L_0x0036:
            java.lang.String r3 = "Failed to find Activity or Fragment to startActivityForResult "
        L_0x0038:
            if (r3 == 0) goto L_0x0048
            com.facebook.LoggingBehavior r4 = com.facebook.LoggingBehavior.DEVELOPER_ERRORS
            r0 = 6
            java.lang.Class r1 = r2.getClass()
            java.lang.String r1 = r1.getName()
            com.facebook.internal.t.a(r4, r0, r1, r3)
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.g.a(android.content.Intent, int):void");
    }

    private a c(CONTENT content, Object obj) {
        boolean z = obj == a;
        a aVar = null;
        Iterator it = e().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            a aVar2 = (a) it.next();
            if ((z || z.a(aVar2.a(), obj)) && aVar2.a(content, true)) {
                try {
                    aVar = aVar2.a(content);
                    break;
                } catch (FacebookException e2) {
                    aVar = d();
                    f.a(aVar, e2);
                }
            }
        }
        if (aVar != null) {
            return aVar;
        }
        a d2 = d();
        f.a(d2);
        return d2;
    }

    private List<a> e() {
        if (this.d == null) {
            this.d = c();
        }
        return this.d;
    }
}
