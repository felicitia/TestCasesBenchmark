package com.etsy.android.uikit.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.nav.b;
import java.util.List;

/* compiled from: FragmentBackstackUtil */
public class e {
    private static final String a = f.a(e.class);

    public static void a(FragmentManager fragmentManager) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 1) {
            try {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                for (int i = backStackEntryCount - 2; i >= 0; i--) {
                    Fragment findFragmentByTag = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(i).getName());
                    if (findFragmentByTag != null) {
                        beginTransaction.hide(findFragmentByTag);
                    }
                }
                beginTransaction.commit();
            } catch (Exception e) {
                f.e(a, "cleanUpFragmentBackStack error", e);
            }
        }
    }

    public static void b(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            try {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                for (Fragment fragment : fragments) {
                    if (fragment != null) {
                        beginTransaction.detach(fragment);
                    }
                }
                beginTransaction.commitAllowingStateLoss();
            } catch (Exception e) {
                f.e(a, "cleanUpNonRetainedFragments error", e);
            }
        }
    }

    public static void a(FragmentManager fragmentManager, b bVar) {
        com.etsy.android.uikit.f c = c(fragmentManager);
        if (c == null || !c.handleBackPressed()) {
            b(fragmentManager, bVar);
        }
    }

    public static com.etsy.android.uikit.f c(FragmentManager fragmentManager) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            return (com.etsy.android.uikit.f) fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
        }
        if (fragmentManager.getFragments() == null || fragmentManager.getFragments().size() <= 0) {
            return null;
        }
        return (com.etsy.android.uikit.f) fragmentManager.getFragments().get(0);
    }

    public static boolean b(FragmentManager fragmentManager, b bVar) {
        if (fragmentManager.getBackStackEntryCount() <= 0) {
            bVar.h();
        } else {
            fragmentManager.popBackStack();
        }
        return true;
    }

    public static boolean a(Activity activity, FragmentManager fragmentManager, b bVar) {
        Intent parentActivityIntent = NavUtils.getParentActivityIntent(activity);
        if (fragmentManager.getBackStackEntryCount() > 1 || parentActivityIntent == null) {
            return b(fragmentManager, bVar);
        }
        bVar.g();
        return true;
    }

    public static boolean a(FragmentManager fragmentManager, Intent intent, b bVar) {
        if (fragmentManager.getBackStackEntryCount() > 1 || intent == null || !intent.getBooleanExtra("NAV_UP_TO_PARENT", false)) {
            return b(fragmentManager, bVar);
        }
        bVar.a(true);
        return true;
    }

    public static int a(Activity activity, FragmentManager fragmentManager, int i) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount < i) {
            if (fragmentManager.getBackStackEntryCount() > 0) {
                com.etsy.android.uikit.f fVar = (com.etsy.android.uikit.f) fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
                if (fVar != null) {
                    fVar.onFragmentResume();
                }
            }
        } else if (backStackEntryCount == i) {
            com.etsy.android.lib.logger.legacy.b.a().a(activity.getClass().getSimpleName(), (Throwable) new Exception("UserActivity: current backstack count equals previous count"), false);
        }
        return backStackEntryCount;
    }
}
