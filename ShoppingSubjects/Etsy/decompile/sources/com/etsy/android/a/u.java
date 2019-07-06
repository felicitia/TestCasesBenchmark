package com.etsy.android.a;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import com.etsy.android.BOEApplication;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.logger.f;
import dagger.android.support.b;

/* compiled from: AppInjector */
public class u {
    public static void a(BOEApplication bOEApplication) {
        a(bOEApplication, ai.a().a(bOEApplication).a());
    }

    @VisibleForTesting
    public static void a(BOEApplication bOEApplication, t tVar) {
        tVar.a(bOEApplication);
        bOEApplication.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                u.b(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity) {
        if (activity instanceof a) {
            StringBuilder sb = new StringBuilder();
            sb.append("Performing dagger injection on: ");
            sb.append(activity.getClass().getName());
            f.b(sb.toString());
            dagger.android.a.a(activity);
        }
        if ((activity instanceof FragmentActivity) && (activity instanceof b)) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
                public void onFragmentPreCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
                    if (fragment instanceof a) {
                        dagger.android.support.a.a(fragment);
                    }
                }
            }, true);
        }
    }
}
