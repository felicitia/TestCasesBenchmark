package rx_activity_result2;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.Nullable;
import io.reactivex.functions.g;
import io.reactivex.functions.i;
import io.reactivex.q;
import java.util.concurrent.TimeUnit;

/* compiled from: ActivitiesLifecycleCallbacks */
class a {
    final Application a;
    volatile Activity b;
    ActivityLifecycleCallbacks c;
    volatile boolean d = false;

    public a(Application application) {
        this.a = application;
        c();
    }

    private void c() {
        if (this.c != null) {
            this.a.unregisterActivityLifecycleCallbacks(this.c);
        }
        this.c = new ActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                a.this.b = activity;
            }

            public void onActivityResumed(Activity activity) {
                a.this.b = activity;
            }

            public void onActivityPaused(Activity activity) {
                a.this.b = null;
            }
        };
        this.a.registerActivityLifecycleCallbacks(this.c);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Activity a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public q<Activity> b() {
        this.d = false;
        return q.a(50, 50, TimeUnit.MILLISECONDS).b((g<? super T, ? extends R>) new g<Long, Object>() {
            /* renamed from: a */
            public Object apply(Long l) throws Exception {
                if (a.this.b == null) {
                    return Integer.valueOf(0);
                }
                return a.this.b;
            }
        }).b((i<? super T>) new i<Object>() {
            public boolean test(Object obj) throws Exception {
                boolean z = !a.this.d;
                if (obj instanceof Activity) {
                    a.this.d = true;
                }
                return z;
            }
        }).a((i<? super T>) new i<Object>() {
            public boolean test(Object obj) throws Exception {
                return obj instanceof Activity;
            }
        }).b((g<? super T, ? extends R>) new g<Object, Activity>() {
            /* renamed from: a */
            public Activity apply(Object obj) throws Exception {
                return (Activity) obj;
            }
        });
    }
}
