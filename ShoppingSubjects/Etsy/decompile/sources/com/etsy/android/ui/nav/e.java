package com.etsy.android.ui.nav;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.etsy.android.uikit.nav.FragmentNavigator.FragmentTransactionMode;
import com.etsy.android.uikit.nav.b;
import com.etsy.android.uikit.nav.c;

/* compiled from: Nav */
public class e extends b implements c<b, c> {
    e(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    e(FragmentActivity fragmentActivity, FragmentManager fragmentManager) {
        super(fragmentActivity, fragmentManager);
    }

    public static e a(FragmentActivity fragmentActivity) {
        return new e(fragmentActivity);
    }

    public static e a(Fragment fragment) {
        return new e(fragment.getActivity(), fragment.getChildFragmentManager());
    }

    public b a() {
        return new b(this.b);
    }

    public static b a(Activity activity) {
        return new b(activity);
    }

    /* renamed from: b */
    public c f() {
        return new c(this.b, this.a, FragmentTransactionMode.ADD);
    }

    /* renamed from: c */
    public c e() {
        return new c(this.b, this.a, FragmentTransactionMode.REPLACE);
    }

    public a d() {
        return new a(this.b);
    }
}
