package com.etsy.android.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.img.d;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.v.b;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.convos.ConvoBaseActivity;
import com.etsy.android.ui.favorites.FavoritesActivity;
import com.etsy.android.ui.homescreen.HomescreenTabsActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.user.PurchasesActivity;
import com.etsy.android.ui.user.UserActivity;
import com.etsy.android.uikit.c;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/* compiled from: BOENavigationMenuManager */
public class a extends com.etsy.android.uikit.nav.a implements b {
    private static final SparseArray<Drawable> g = new SparseArray<>();
    private static final Map<String, Integer> h;
    BroadcastReceiver a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            a.this.a(context, v.a().e());
        }
    };
    /* access modifiers changed from: private */
    public BitmapDrawable i;
    private boolean j;

    static {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(HomescreenTabsActivity.class.getName(), Integer.valueOf(R.id.nav_menu_home));
        arrayMap.put(ConvoBaseActivity.class.getName(), Integer.valueOf(R.id.nav_menu_convos));
        arrayMap.put(FavoritesActivity.class.getName(), Integer.valueOf(R.id.nav_menu_favorites));
        arrayMap.put(UserActivity.class.getName(), Integer.valueOf(R.id.nav_menu_profile));
        arrayMap.put(PurchasesActivity.class.getName(), Integer.valueOf(R.id.nav_menu_purchases));
        h = Collections.unmodifiableMap(arrayMap);
    }

    public a(Context context, EtsyNavigationView etsyNavigationView, DrawerLayout drawerLayout) {
        super(context, etsyNavigationView, drawerLayout);
        this.d.addDrawerListener(new DrawerListener() {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View view) {
            }

            public void onDrawerStateChanged(int i) {
            }

            public void onDrawerSlide(View view, float f) {
                if (s.a(view.getContext())) {
                    s.a(view);
                }
            }
        });
    }

    public static void a(Context context) {
        SparseArray<Drawable> sparseArray = g;
        sparseArray.put(R.id.nav_menu_home, c.a(context, R.drawable.sk_ic_home, R.color.sk_white));
        sparseArray.put(R.id.nav_menu_favorites, c.a(context, R.drawable.sk_ic_favorite, R.color.sk_white));
        sparseArray.put(R.id.nav_menu_convos, c.a(context, R.drawable.sk_ic_conversations, R.color.sk_white));
        sparseArray.put(R.id.nav_menu_purchases, c.a(context, R.drawable.sk_ic_items, R.color.sk_white));
        sparseArray.put(R.id.nav_menu_sign_in, c.a(context, R.drawable.sk_ic_userprofile, R.color.sk_white));
        sparseArray.put(R.id.nav_menu_settings, c.a(context, R.drawable.sk_ic_settings, R.color.sk_white));
        sparseArray.put(R.id.nav_menu_profile, c.a(context, R.drawable.sk_ic_user, R.color.sk_white));
    }

    /* access modifiers changed from: protected */
    public Collection<Integer> a() {
        return h.values();
    }

    public void b() {
        super.b();
        v.a().a((b) this);
        a(this.b, v.a().e());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(EtsyApplication.ACTION_INSTALL_STATE_DISCOVERED);
        intentFilter.addAction("com.etsy.android.badge.count.UPDATE");
        LocalBroadcastManager.getInstance(this.b).registerReceiver(this.a, intentFilter);
    }

    public void c() {
        super.c();
        Integer num = (Integer) h.get(this.b.getClass().getName());
        if (num != null) {
            this.f = num.intValue();
            MenuItem findItem = this.c.getMenu().findItem(this.f);
            if (findItem != null) {
                findItem.setChecked(true);
            }
        }
    }

    public void d() {
        super.d();
        v.a().b((b) this);
        LocalBroadcastManager.getInstance(this.b).unregisterReceiver(this.a);
    }

    /* access modifiers changed from: protected */
    public void a(int i2) {
        FragmentActivity fragmentActivity = (FragmentActivity) this.b;
        com.etsy.android.ui.nav.b f = e.a(fragmentActivity).a().a(AnimationMode.NONE).f();
        switch (i2) {
            case R.id.nav_menu_bug_hunt /*2131362632*/:
                com.etsy.android.uikit.ui.bughunt.a.a(fragmentActivity).a();
                return;
            case R.id.nav_menu_configs /*2131362633*/:
                e.a(fragmentActivity).a().a(AnimationMode.NONE).t();
                return;
            case R.id.nav_menu_convos /*2131362634*/:
                f.v();
                return;
            case R.id.nav_menu_favorites /*2131362635*/:
                f.e().a(SharedPreferencesUtility.c(fragmentActivity), 0);
                return;
            case R.id.nav_menu_home /*2131362636*/:
                f.s();
                return;
            case R.id.nav_menu_profile /*2131362637*/:
                f.l();
                return;
            case R.id.nav_menu_purchases /*2131362638*/:
                f.w();
                return;
            case R.id.nav_menu_sell_on_etsy_link /*2131362639*/:
                EtsyApplication etsyApplication = (EtsyApplication) fragmentActivity.getApplicationContext();
                if (etsyApplication.isSOEInstalled()) {
                    fragmentActivity.startActivity(etsyApplication.getSOELaunchIntent());
                    return;
                } else {
                    fragmentActivity.startActivity(EtsyApplication.getSOEDownloadIntent());
                    return;
                }
            case R.id.nav_menu_settings /*2131362640*/:
                e.a(fragmentActivity).a().m();
                return;
            case R.id.nav_menu_sign_in /*2131362641*/:
                f.b(false);
                return;
            case R.id.nav_menu_support_feedback /*2131362642*/:
                e.a(fragmentActivity).a().p();
                return;
            default:
                return;
        }
    }

    public void onSignedInChanged(Context context, boolean z) {
        if (!z) {
            this.i = null;
        }
        a(context, z);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, boolean z) {
        this.j = z;
        a(context, this.j ? R.menu.nav_drawer_signed_in : R.menu.nav_drawer_signed_out, g);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size);
        if (z && this.i == null) {
            this.e.a(SharedPreferencesUtility.f(this.b), (d) new d(null) {
                public void a(Bitmap bitmap, boolean z) {
                    a.this.i = new BitmapDrawable(a.this.b.getResources(), bitmap);
                    if (a.this.b != null && a.this.c != null && a.this.c.getMenu() != null) {
                        MenuItem findItem = a.this.c.getMenu().findItem(R.id.nav_menu_profile);
                        if (findItem != null) {
                            findItem.setIcon(a.this.i);
                        }
                    }
                }
            }, dimensionPixelSize);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Menu menu) {
        if (!com.etsy.android.util.d.b()) {
            menu.removeItem(R.id.nav_menu_configs);
        }
        if (!com.etsy.android.lib.config.a.a().d().c(com.etsy.android.lib.config.b.bD)) {
            menu.removeItem(R.id.nav_menu_support_feedback);
        }
        if (!com.etsy.android.lib.config.a.a().d().b() && !com.etsy.android.util.d.b()) {
            menu.removeItem(R.id.nav_menu_bug_hunt);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.view.MenuItem r3, android.content.Context r4) {
        /*
            r2 = this;
            int r0 = r3.getItemId()
            r1 = 2131362637(0x7f0a034d, float:1.834506E38)
            if (r0 != r1) goto L_0x001e
            boolean r0 = r2.j
            if (r0 == 0) goto L_0x0014
            java.lang.String r4 = com.etsy.android.lib.util.SharedPreferencesUtility.e(r4)
            r3.setTitle(r4)
        L_0x0014:
            android.graphics.drawable.BitmapDrawable r4 = r2.i
            if (r4 == 0) goto L_0x0058
            android.graphics.drawable.BitmapDrawable r4 = r2.i
            r3.setIcon(r4)
            goto L_0x0058
        L_0x001e:
            r1 = 2131362638(0x7f0a034e, float:1.8345062E38)
            if (r0 != r1) goto L_0x002c
            boolean r4 = r2.j
            if (r4 == 0) goto L_0x0058
            int r4 = com.etsy.android.ui.user.i.a()
            goto L_0x0059
        L_0x002c:
            r1 = 2131362634(0x7f0a034a, float:1.8345054E38)
            if (r0 != r1) goto L_0x003a
            boolean r4 = r2.j
            if (r4 == 0) goto L_0x0058
            int r4 = com.etsy.android.ui.user.i.b()
            goto L_0x0059
        L_0x003a:
            r1 = 2131362639(0x7f0a034f, float:1.8345064E38)
            if (r0 != r1) goto L_0x0058
            android.content.Context r4 = r4.getApplicationContext()
            com.etsy.android.lib.core.EtsyApplication r4 = (com.etsy.android.lib.core.EtsyApplication) r4
            boolean r4 = r4.isSOEInstalled()
            if (r4 == 0) goto L_0x0052
            r4 = 2131756503(0x7f1005d7, float:1.9143915E38)
            r3.setTitle(r4)
            goto L_0x0058
        L_0x0052:
            r4 = 2131756492(0x7f1005cc, float:1.9143893E38)
            r3.setTitle(r4)
        L_0x0058:
            r4 = 0
        L_0x0059:
            if (r4 <= 0) goto L_0x0066
            java.lang.CharSequence r0 = r3.getTitle()
            java.lang.String r0 = r0.toString()
            r2.a(r3, r0, r4)
        L_0x0066:
            int r4 = r3.getItemId()
            int r0 = r2.f
            if (r4 != r0) goto L_0x0072
            r4 = 1
            r3.setChecked(r4)
        L_0x0072:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.a.a(android.view.MenuItem, android.content.Context):void");
    }
}
