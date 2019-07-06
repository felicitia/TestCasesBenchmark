package com.etsy.android.uikit.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewParent;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.w;
import java.util.HashMap;
import java.util.List;

@Deprecated
/* compiled from: AdHocEventCompatBuilder */
public class a {
    private static final String a = f.a(a.class);

    @Nullable
    public static w a(@NonNull View view) {
        f.c(a, "findViewTracker start");
        Context context = view.getContext();
        HashMap hashMap = new HashMap();
        if (context instanceof FragmentActivity) {
            a(((FragmentActivity) context).getSupportFragmentManager(), hashMap);
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof View) {
                    Fragment fragment = (Fragment) hashMap.get(parent);
                    if (fragment != null && (fragment instanceof j)) {
                        return ((j) fragment).getAnalyticsContext();
                    }
                }
            }
        }
        if (context instanceof j) {
            return ((j) view.getContext()).getAnalyticsContext();
        }
        return null;
    }

    @NonNull
    public static b b(@Nullable View view) {
        w a2 = view != null ? a(view) : null;
        if (a2 != null) {
            return a2;
        }
        return EtsyApplication.get().getAnalyticsTracker();
    }

    private static void a(@NonNull FragmentManager fragmentManager, @NonNull HashMap<View, Fragment> hashMap) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    if (fragment.getView() != null) {
                        hashMap.put(fragment.getView(), fragment);
                    }
                    try {
                        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                        if (!(childFragmentManager == null || childFragmentManager.getFragments() == null)) {
                            a(childFragmentManager, hashMap);
                        }
                    } catch (IllegalStateException unused) {
                        f.f("Trying to get Child FragmentManager for Fragment not attached yet");
                    }
                }
            }
        }
    }
}
