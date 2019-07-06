package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/* compiled from: FragmentWrapper */
public class m {
    private Fragment a;
    private android.app.Fragment b;

    public m(Fragment fragment) {
        aa.a((Object) fragment, "fragment");
        this.a = fragment;
    }

    public m(android.app.Fragment fragment) {
        aa.a((Object) fragment, "fragment");
        this.b = fragment;
    }

    public android.app.Fragment a() {
        return this.b;
    }

    public Fragment b() {
        return this.a;
    }

    public void a(Intent intent, int i) {
        if (this.a != null) {
            this.a.startActivityForResult(intent, i);
        } else {
            this.b.startActivityForResult(intent, i);
        }
    }

    public final Activity c() {
        if (this.a != null) {
            return this.a.getActivity();
        }
        return this.b.getActivity();
    }
}
