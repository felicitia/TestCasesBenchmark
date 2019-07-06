package dagger.android.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import dagger.android.b;
import dagger.internal.f;

/* compiled from: AndroidSupportInjection */
public final class a {
    public static void a(Fragment fragment) {
        f.a(fragment, "fragment");
        b b = b(fragment);
        Log.d("dagger.android.support", String.format("An injector for %s was found in %s", new Object[]{fragment.getClass().getCanonicalName(), b.getClass().getCanonicalName()}));
        b supportFragmentInjector = b.supportFragmentInjector();
        f.a(supportFragmentInjector, "%s.supportFragmentInjector() returned null", b.getClass().getCanonicalName());
        supportFragmentInjector.a(fragment);
    }

    private static b b(Fragment fragment) {
        Fragment fragment2 = fragment;
        do {
            fragment2 = fragment2.getParentFragment();
            if (fragment2 == null) {
                FragmentActivity activity = fragment.getActivity();
                if (activity instanceof b) {
                    return (b) activity;
                }
                if (activity.getApplication() instanceof b) {
                    return (b) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", new Object[]{fragment.getClass().getCanonicalName()}));
            }
        } while (!(fragment2 instanceof b));
        return (b) fragment2;
    }
}
