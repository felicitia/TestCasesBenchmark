package com.etsy.android.uikit.nav;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: BaseNavigationMenuManager */
public abstract class a implements com.etsy.android.uikit.navigationview.EtsyNavigationView.a {
    private static final SparseArray<String> a = new SparseArray<>(0);
    private static final ArrayList<Integer> g = new ArrayList<>();
    /* access modifiers changed from: protected */
    public Context b;
    /* access modifiers changed from: protected */
    public EtsyNavigationView c;
    protected DrawerLayout d;
    protected c e;
    protected int f;
    private final Handler h = new Handler();

    /* access modifiers changed from: protected */
    public abstract Collection<Integer> a();

    /* access modifiers changed from: protected */
    public abstract void a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(Menu menu);

    /* access modifiers changed from: protected */
    public abstract void a(MenuItem menuItem, Context context);

    public void c() {
    }

    public void d() {
    }

    public a(Context context, EtsyNavigationView etsyNavigationView, DrawerLayout drawerLayout) {
        this.b = context;
        this.c = etsyNavigationView;
        this.d = drawerLayout;
        this.e = new c();
    }

    /* access modifiers changed from: protected */
    public void a(Context context, int i, SparseArray<Drawable> sparseArray) {
        Menu menu = this.c.getMenu();
        menu.clear();
        this.c.inflateMenu(i);
        a(menu);
        int size = menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menu.getItem(i2);
            Drawable drawable = (Drawable) sparseArray.get(item.getItemId());
            if (drawable != null) {
                item.setIcon(drawable);
            }
            a(item, context);
            b(item);
        }
    }

    public boolean a(MenuItem menuItem) {
        menuItem.setChecked(true);
        final int itemId = menuItem.getItemId();
        if (a().contains(Integer.valueOf(itemId))) {
            this.d.closeDrawer((View) this.c);
            if (itemId != this.f) {
                this.h.postDelayed(new Runnable() {
                    public void run() {
                        a.this.d(itemId);
                    }
                }, 300);
            }
            this.f = itemId;
        } else {
            d(itemId);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(MenuItem menuItem, String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("#");
        sb.append(i);
        sb.append("#");
        menuItem.setTitle(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void a(MenuItem menuItem, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("#");
        sb.append(str2);
        sb.append("#");
        menuItem.setTitle(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void b(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        String str = (String) a.get(itemId);
        if (str != null) {
            if (!SharedPreferencesUtility.b(this.b, str, false)) {
                a(menuItem, menuItem.getTitle().toString(), this.b.getResources().getString(o.new_nav_badge));
            } else {
                a.remove(itemId);
                g.add(Integer.valueOf(itemId));
            }
        }
    }

    private void c(int i) {
        String str = (String) a.get(i);
        if (str != null) {
            SharedPreferencesUtility.c(this.b, str, true);
            a.remove(i);
            g.add(Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: private */
    public void d(int i) {
        a(i);
        b(i);
    }

    /* access modifiers changed from: protected */
    public void b(int i) {
        c(i);
    }

    public void b() {
        if (v.a().o()) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                c(a.keyAt(i));
            }
        }
    }
}
