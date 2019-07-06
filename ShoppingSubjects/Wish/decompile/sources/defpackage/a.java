package defpackage;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

/* renamed from: a reason: default package */
/* compiled from: GA */
public final class a implements ActivityLifecycleCallbacks {
    private o a;

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    private a() {
    }

    public a(o oVar) {
        this.a = oVar;
    }

    public final void onActivityResumed(Activity activity) {
        h hVar = this.a.b;
        hVar.a((Runnable) new Runnable() {
            public final void run() {
                if (h.this.a == null) {
                    h.d(h.this);
                }
            }
        });
    }

    public final void onActivityPaused(Activity activity) {
        o oVar = this.a;
        oVar.b.b();
        h hVar = oVar.b;
        hVar.a((Runnable) new Runnable() {
            public final void run() {
                h.this.a();
            }
        });
    }
}
