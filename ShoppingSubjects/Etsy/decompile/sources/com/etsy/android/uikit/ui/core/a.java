package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.j;
import com.etsy.android.lib.core.v;
import com.etsy.android.uikit.f;

/* compiled from: BaseFragmentDelegate */
public class a implements f {
    private static final String b = com.etsy.android.lib.logger.f.a(a.class);
    protected c a;
    private final Fragment c;

    public boolean handleBackPressed() {
        return false;
    }

    public void onFragmentResume() {
    }

    public a(Fragment fragment) {
        this.c = fragment;
    }

    public void a(Bundle bundle) {
        this.a = new c();
        if (bundle != null ? bundle.getBoolean("HIDDEN") : false) {
            com.etsy.android.lib.logger.f.d(b, "Had to manually hide %s (probably on orientation change) - consider replacing this instead of adding it", this.c.getClass().getSimpleName());
            this.c.getFragmentManager().beginTransaction().hide(this.c).commit();
        }
    }

    public void a() {
        com.etsy.android.lib.toolbar.a.a(this.c.getClass().getSimpleName());
    }

    public void b() {
        d().a((Object) this.c);
        this.a.a();
    }

    public void c() {
        if (!this.c.getRetainInstance()) {
            d().a((Object) this.c);
        }
        this.a.a();
        com.etsy.android.uikit.util.c.a(this.c.getView());
    }

    public void b(Bundle bundle) {
        bundle.putBoolean("HIDDEN", this.c.isHidden());
    }

    public j d() {
        return v.a().j();
    }

    public com.etsy.android.lib.core.posts.a e() {
        return v.a().k();
    }

    public c f() {
        return this.a;
    }
}
