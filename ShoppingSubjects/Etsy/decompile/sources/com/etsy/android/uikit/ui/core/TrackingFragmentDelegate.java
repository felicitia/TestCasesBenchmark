package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.config.bucketing.d;
import com.etsy.android.lib.config.h;
import com.etsy.android.lib.logger.a.a;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.w;

public class TrackingFragmentDelegate implements j {
    private static final String a = f.a(TrackingFragmentDelegate.class);
    @NonNull
    private final Fragment b;
    @NonNull
    private final j c;
    private w d;
    @Nullable
    private String e;
    private boolean f = true;
    private boolean g = false;

    public boolean g() {
        return true;
    }

    public TrackingFragmentDelegate(@NonNull Fragment fragment) {
        this.b = fragment;
        this.c = (j) fragment;
    }

    public void a(Bundle bundle) {
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("onCreate: isVisible (");
        sb.append(this.b.isVisible());
        sb.append(") ");
        sb.append(this.b.getClass().getSimpleName());
        f.a(str, sb.toString());
        if (bundle != null) {
            this.f = bundle.getBoolean("Tracking.IsVisibleHint", this.f);
        }
        Bundle arguments = this.b.getArguments();
        if (arguments != null) {
            this.e = arguments.getString("TRACKING_NAME");
        }
        this.d = w.a(this.c, this.f, arguments);
        if (g() && this.d != null) {
            this.d.a(this.c);
        }
    }

    public void a() {
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("onResume: isVisible (");
        sb.append(this.b.isVisible());
        sb.append(") ");
        sb.append(this.b.getClass().getSimpleName());
        f.a(str, sb.toString());
        if (g() && this.d != null) {
            this.d.b(this.c);
        }
        Bundle arguments = this.b.getArguments();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("performance.fragment_launch.");
        sb2.append(this.b.getClass().getSimpleName());
        a.a(arguments, sb2.toString());
    }

    public void b() {
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("onPause: isVisible (");
        sb.append(this.b.isVisible());
        sb.append(") ");
        sb.append(this.b.getClass().getSimpleName());
        f.a(str, sb.toString());
        if (g() && this.d != null) {
            this.d.i();
        }
    }

    public void c() {
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("onStop: isVisible (");
        sb.append(this.b.isVisible());
        sb.append(") ");
        sb.append(this.b.getClass().getSimpleName());
        f.a(str, sb.toString());
        if (g() && this.d != null) {
            this.d.j();
        }
    }

    public void d() {
        if (g() && this.d != null) {
            this.d.k();
        }
    }

    public void a(Activity activity) {
        if (g() && this.d != null) {
            this.d.h();
        }
    }

    public void e() {
        if (g() && this.d != null) {
            this.d.g();
        }
    }

    public void b(Bundle bundle) {
        if (this.g) {
            this.f = false;
        }
        bundle.putBoolean("Tracking.IsVisibleHint", this.f);
    }

    public void a(boolean z) {
        this.g = true;
        String str = a;
        StringBuilder sb = new StringBuilder();
        sb.append("setUserVisibleHint (");
        sb.append(z);
        sb.append(") ");
        sb.append(this.b.getClass().getSimpleName());
        f.a(str, sb.toString());
        this.f = z;
        if (g() && this.d != null) {
            this.d.b(this.c, z);
        }
    }

    public h f() {
        return this.d.c();
    }

    public boolean a(@NonNull d dVar) {
        return this.d.a(dVar);
    }

    @NonNull
    public String getTrackingName() {
        if (this.e != null) {
            return this.e;
        }
        return getDefaultName();
    }

    @NonNull
    public final String getDefaultName() {
        return this.b.getClass().getSimpleName();
    }

    @Nullable
    public j getTrackingParent() {
        if (this.b.getParentFragment() != null && (this.b.getParentFragment() instanceof j)) {
            return (j) this.b.getParentFragment();
        }
        if (this.b.getActivity() == null || !(this.b.getActivity() instanceof j)) {
            return null;
        }
        return (j) this.b.getActivity();
    }

    public w getAnalyticsContext() {
        return this.d;
    }

    @Nullable
    public Context getAndroidContext() {
        return this.b.getActivity();
    }
}
