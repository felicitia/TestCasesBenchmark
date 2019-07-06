package com.etsy.android.uikit.nav;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.etsy.android.lib.a.C0051a;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.ui.nav.a;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.nav.FragmentNavigator;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;

public abstract class FragmentNavigator<NavigatorClass extends FragmentNavigator<NavigatorClass>> extends a<NavigatorClass> {
    private static final String m = f.a(FragmentNavigator.class);
    @NonNull
    protected final FragmentActivity a;
    protected final FragmentTransactionMode b;
    protected boolean c;
    protected FragmentManager d;
    protected String e;
    protected boolean f = true;
    protected boolean g = false;
    @NonNull
    protected Bundle h = new Bundle();
    protected int i;
    protected int j;
    protected AnimationMode k;
    protected SavedState l;

    public enum AnimationMode {
        SLIDING,
        FADE,
        NONE,
        SLIDE_BOTTOM
    }

    public enum FragmentTransactionMode {
        ADD,
        REPLACE
    }

    public abstract NavigatorClass B();

    public abstract NavigatorClass b(@IdRes int i2);

    public FragmentNavigator(@NonNull FragmentActivity fragmentActivity, FragmentManager fragmentManager, FragmentTransactionMode fragmentTransactionMode) {
        this.a = fragmentActivity;
        this.d = fragmentManager;
        this.b = fragmentTransactionMode;
    }

    /* access modifiers changed from: protected */
    public Activity a() {
        return this.a;
    }

    public BaseDialogFragment i(Bundle bundle) {
        String string = bundle.getString("dialog_fragment");
        try {
            if (Class.forName(string) != null) {
                Object newInstance = Class.forName(string).newInstance();
                if (newInstance instanceof BaseDialogFragment) {
                    BaseDialogFragment baseDialogFragment = (BaseDialogFragment) newInstance;
                    this.h.putAll(bundle.getBundle("dialog_fragment_bundle"));
                    baseDialogFragment.setArguments(this.h);
                    a((DialogFragment) baseDialogFragment);
                    return baseDialogFragment;
                }
            }
        } catch (InstantiationException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Problem showing DialogFragment ");
            sb.append(e2.getMessage());
            f.f(sb.toString());
        } catch (IllegalAccessException e3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Problem showing DialogFragment ");
            sb2.append(e3.getMessage());
            f.f(sb2.toString());
        } catch (ClassNotFoundException e4) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Problem showing DialogFragment ");
            sb3.append(e4.getMessage());
            f.f(sb3.toString());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void a(DialogFragment dialogFragment) {
        FragmentManager fragmentManager;
        this.e = "dialog";
        if (l.c((Activity) this.a)) {
            if (this.d != null) {
                fragmentManager = this.d;
            } else {
                fragmentManager = this.a.getSupportFragmentManager();
            }
            dialogFragment.show(fragmentManager, this.e);
            return;
        }
        a((Fragment) dialogFragment);
    }

    /* access modifiers changed from: protected */
    public void a(Fragment fragment) {
        FragmentManager fragmentManager;
        String str;
        int i2;
        if (this.d != null) {
            fragmentManager = this.d;
        } else {
            fragmentManager = this.a.getSupportFragmentManager();
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (this.e != null) {
            str = this.e;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(fragment.getClass().getSimpleName());
            sb.append(fragment.hashCode());
            str = sb.toString();
        }
        if (!this.c || fragmentManager.findFragmentByTag(str) == null) {
            int i3 = 16908290;
            if (this.i > 0) {
                i2 = this.i;
            } else if (this.a.findViewById(this.j) != null) {
                i2 = this.j;
                this.g = true;
            } else {
                i2 = 16908290;
            }
            if (this.l != null) {
                fragment.setInitialSavedState(this.l);
            }
            int i4 = 0;
            if (this.b == FragmentTransactionMode.ADD) {
                Fragment findFragmentById = fragmentManager.findFragmentById(i2);
                if (findFragmentById == null) {
                    if (!this.g) {
                        i3 = this.j;
                    }
                    findFragmentById = fragmentManager.findFragmentById(i3);
                }
                if (findFragmentById == null || findFragmentById.isHidden()) {
                    this.f = false;
                } else {
                    beginTransaction.hide(findFragmentById);
                }
                a(beginTransaction);
                beginTransaction.add(i2, fragment, str);
            } else if (this.b == FragmentTransactionMode.REPLACE) {
                a(beginTransaction);
                String str2 = m;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("commitFragment: calling replace for container: ");
                sb2.append(i2);
                sb2.append(", with new fragment: ");
                sb2.append(fragment.getClass().getSimpleName());
                sb2.append(", on fragment container: ");
                sb2.append(fragment.getId());
                f.c(str2, sb2.toString());
                beginTransaction.replace(i2, fragment, str);
            }
            if (this.f) {
                beginTransaction.addToBackStack(str);
            }
            beginTransaction.commitAllowingStateLoss();
            String str3 = m;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("commitFragment: after commit, manager count is: ");
            if (fragmentManager.getFragments() != null) {
                i4 = fragmentManager.getFragments().size();
            }
            sb3.append(i4);
            f.c(str3, sb3.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void a(FragmentTransaction fragmentTransaction) {
        switch (this.k) {
            case SLIDING:
                fragmentTransaction.setCustomAnimations(C0051a.nav_frag_right_enter, C0051a.nav_frag_right_exit, C0051a.nav_frag_right_pop_enter, C0051a.nav_frag_right_pop_exit);
                return;
            case SLIDE_BOTTOM:
                fragmentTransaction.setCustomAnimations(C0051a.nav_frag_bottom_enter, C0051a.nav_frag_bottom_exit, C0051a.nav_frag_bottom_pop_enter, C0051a.nav_frag_bottom_pop_exit);
                return;
            case FADE:
                fragmentTransaction.setCustomAnimations(C0051a.fade_in, C0051a.fade_out, C0051a.fade_in, C0051a.fade_out);
                return;
            default:
                fragmentTransaction.setTransition(-1);
                return;
        }
    }
}
