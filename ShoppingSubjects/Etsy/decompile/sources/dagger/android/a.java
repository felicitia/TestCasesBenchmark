package dagger.android;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.etsy.android.lib.models.ResponseConstants;
import dagger.internal.f;

/* compiled from: AndroidInjection */
public final class a {
    public static void a(Activity activity) {
        f.a(activity, "activity");
        Application application = activity.getApplication();
        if (!(application instanceof d)) {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), d.class.getCanonicalName()}));
        }
        b activityInjector = ((d) application).activityInjector();
        f.a(activityInjector, "%s.activityInjector() returned null", application.getClass().getCanonicalName());
        activityInjector.a(activity);
    }

    public static void a(Fragment fragment) {
        f.a(fragment, "fragment");
        g b = b(fragment);
        Log.d("dagger.android", String.format("An injector for %s was found in %s", new Object[]{fragment.getClass().getCanonicalName(), b.getClass().getCanonicalName()}));
        b fragmentInjector = b.fragmentInjector();
        f.a(fragmentInjector, "%s.fragmentInjector() returned null", b.getClass().getCanonicalName());
        fragmentInjector.a(fragment);
    }

    private static g b(Fragment fragment) {
        Fragment fragment2 = fragment;
        do {
            fragment2 = fragment2.getParentFragment();
            if (fragment2 == null) {
                Activity activity = fragment.getActivity();
                if (activity instanceof g) {
                    return (g) activity;
                }
                if (activity.getApplication() instanceof g) {
                    return (g) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", new Object[]{fragment.getClass().getCanonicalName()}));
            }
        } while (!(fragment2 instanceof g));
        return (g) fragment2;
    }

    public static void a(Service service) {
        f.a(service, NotificationCompat.CATEGORY_SERVICE);
        Application application = service.getApplication();
        if (!(application instanceof h)) {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), h.class.getCanonicalName()}));
        }
        b serviceInjector = ((h) application).serviceInjector();
        f.a(serviceInjector, "%s.serviceInjector() returned null", application.getClass().getCanonicalName());
        serviceInjector.a(service);
    }

    public static void a(BroadcastReceiver broadcastReceiver, Context context) {
        f.a(broadcastReceiver, "broadcastReceiver");
        f.a(context, ResponseConstants.CONTEXT);
        Application application = (Application) context.getApplicationContext();
        if (!(application instanceof e)) {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), e.class.getCanonicalName()}));
        }
        b broadcastReceiverInjector = ((e) application).broadcastReceiverInjector();
        f.a(broadcastReceiverInjector, "%s.broadcastReceiverInjector() returned null", application.getClass().getCanonicalName());
        broadcastReceiverInjector.a(broadcastReceiver);
    }

    public static void a(ContentProvider contentProvider) {
        f.a(contentProvider, "contentProvider");
        Application application = (Application) contentProvider.getContext().getApplicationContext();
        if (!(application instanceof f)) {
            throw new RuntimeException(String.format("%s does not implement %s", new Object[]{application.getClass().getCanonicalName(), f.class.getCanonicalName()}));
        }
        b contentProviderInjector = ((f) application).contentProviderInjector();
        f.a(contentProviderInjector, "%s.contentProviderInjector() returned null", application.getClass().getCanonicalName());
        contentProviderInjector.a(contentProvider);
    }
}
