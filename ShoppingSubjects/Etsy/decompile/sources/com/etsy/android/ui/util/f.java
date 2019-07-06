package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.interfaces.BasicShopLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.util.e.b;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.d;
import com.etsy.android.uikit.view.RatingIconView;
import java.lang.ref.WeakReference;

/* compiled from: OverlapHeaderHelper */
public class f {
    private final int a;
    private final int b;
    private final String c;
    private final boolean d;
    /* access modifiers changed from: private */
    public WeakReference<Activity> e = null;
    private c f;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public e h;
    /* access modifiers changed from: private */
    public final b i = new b() {
        public void a() {
            f.this.g = true;
            a(R.drawable.ic_favorited_selector);
        }

        public void b() {
            f.this.g = false;
            a(R.drawable.ic_favorite_selector);
        }

        private void a(int i) {
            if (f.this.w != null) {
                f.this.w.setImageResource(i);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public d k;
    /* access modifiers changed from: private */
    public final d.b l = new d.b() {
        public void a() {
            f.this.j = true;
            if (f.this.x != null) {
                f.this.x.setText(R.string.unfollow);
            }
        }

        public void b() {
            f.this.j = false;
            if (f.this.x != null) {
                f.this.x.setText(R.string.follow);
            }
        }
    };
    private View m;
    private View n;
    private ImageView o;
    private TextView p;
    private TextView q;
    private View r;
    private TextView s;
    private RatingIconView t;
    private View u;
    private View v;
    /* access modifiers changed from: private */
    public ImageView w;
    /* access modifiers changed from: private */
    public Button x;

    public f(Resources resources, String str, boolean z) {
        this.c = str;
        this.d = z;
        this.a = resources.getDimensionPixelSize(R.dimen.gen_avatar_corners_small);
        this.b = resources.getDimensionPixelSize(R.dimen.shop_listing_header_avatar);
    }

    public void a(@NonNull View view, @NonNull View view2) {
        this.m = view;
        this.n = view2;
        this.o = (ImageView) view.findViewById(R.id.shop_header_avatar);
        this.p = (TextView) view.findViewById(R.id.shop_header_name);
        this.q = (TextView) view.findViewById(R.id.shop_header_location);
        this.v = view.findViewById(R.id.header_favorite_button);
        this.x = (Button) view.findViewById(R.id.header_follow_button);
        this.w = (ImageView) view.findViewById(R.id.heart_image);
        this.u = view.findViewById(R.id.header_contact_button);
        this.r = view.findViewById(R.id.rating_layout);
        this.s = (TextView) view.findViewById(R.id.rating_count);
        this.t = (RatingIconView) view.findViewById(R.id.shop_rating);
    }

    public void a(Activity activity, c cVar, @NonNull w wVar) {
        this.e = new WeakReference<>(activity);
        this.f = cVar;
        this.h = new e(activity, this, wVar);
        this.k = new d(activity, this, this.c);
    }

    private c c() {
        return this.f;
    }

    public void a(User user, Shop shop, String str, String str2) {
        b(user, shop);
        if (this.d) {
            b(user, str, str2);
        }
    }

    public void a(User user, Shop shop) {
        b(user, shop);
        this.q.setVisibility(8);
        this.r.setVisibility(0);
        this.s.setText(((Activity) this.e.get()).getResources().getQuantityString(R.plurals.reviews_plurals_no_brackets, shop.getNumRatings(), new Object[]{af.a((double) shop.getNumRatings())}));
        if (shop.getAverageRating() > 0.0d) {
            this.t.setRating((float) shop.getAverageRating());
        } else {
            this.t.setVisibility(8);
        }
    }

    public void b(final User user, final Shop shop) {
        this.n.setVisibility(0);
        this.m.setVisibility(0);
        a(shop, user, false);
        this.p.setText(shop.getShopName());
        b(user);
        if (shop.getShopId().hasId()) {
            this.n.setOnClickListener(new TrackingOnClickListener(new i[]{shop}) {
                public void onViewClick(View view) {
                    if (user == null || !user.getUserId().hasId()) {
                        e.a((Activity) f.this.e.get()).b(shop.getShopId());
                    } else {
                        e.a((Activity) f.this.e.get()).a(shop.getShopId(), user.getUserId());
                    }
                }
            });
        }
        if (this.d) {
            a(shop);
        }
    }

    private void a(final Shop shop) {
        if (shop.getUser() == null || !shop.getUser().getUserId().hasId()) {
            this.v.setVisibility(8);
            return;
        }
        this.v.setVisibility(0);
        this.v.setOnClickListener(new TrackingOnClickListener(new i[]{shop}) {
            public void onViewClick(View view) {
                if (v.a().e()) {
                    f.this.h.a(f.this.w, R.drawable.ic_favorite_selector, R.drawable.ic_favorited_selector, f.this.g);
                    f.this.h.a((BasicShopLike) shop, f.this.i, f.this.g);
                    return;
                }
                ((com.etsy.android.ui.nav.b) e.a((Activity) f.this.e.get()).a(view)).a(EtsyAction.FAVORITE, String.valueOf(shop.getUser().getUserId()));
            }
        });
    }

    public void a(final User user, String str, String str2) {
        this.n.setVisibility(0);
        this.m.setVisibility(0);
        String a2 = af.a(user);
        a((Shop) null, user, true);
        this.p.setText(a2);
        b(user);
        if (user.getUserId().hasId()) {
            this.n.setOnClickListener(new TrackingOnClickListener(new i[]{user}) {
                public void onViewClick(View view) {
                    e.a((Activity) f.this.e.get()).c(user.getUserId());
                }
            });
        }
        if (this.d) {
            a(user);
            b(user, str, str2);
        }
    }

    private void a(final User user) {
        this.v.setVisibility(8);
        if (user != null && user.getUserId().hasId()) {
            this.x.setVisibility(0);
            this.k.a(user.getUserId(), this.l);
            this.x.setOnClickListener(new TrackingOnClickListener(new i[]{user}) {
                public void onViewClick(View view) {
                    if (v.a().e()) {
                        f.this.x.setText(f.this.j ? R.string.follow : R.string.unfollow);
                        f.this.k.a(user.getUserId(), !f.this.j, f.this.l);
                        return;
                    }
                    ((com.etsy.android.ui.nav.b) e.a((Activity) f.this.e.get()).a(view)).a(EtsyAction.FOLLOW, user.getUserId().getId());
                }
            });
        }
    }

    private void a(Shop shop, User user, boolean z) {
        String str = (shop == null || !af.a(shop.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) ? (user == null || user.getProfile() == null) ? null : user.getProfile().getImageUrl75x75() : shop.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
        String str2 = str;
        if (str2 == null) {
            return;
        }
        if (z) {
            this.o.setBackgroundResource(R.drawable.bg_avatar_circle_small_borderless);
            c().b(str2, this.o, this.b);
            return;
        }
        c().b(str2, this.o, this.a, this.b, this.b);
    }

    private void b(User user) {
        String str = "";
        if (!(user == null || user.getProfile() == null)) {
            str = af.a(user.getProfile());
        }
        if (af.a(str)) {
            this.q.setText(str);
        } else {
            this.q.setVisibility(8);
        }
    }

    public void a() {
        this.u.setVisibility(4);
        this.n.setOnClickListener(null);
    }

    private void b(User user, String str, String str2) {
        this.u.setVisibility(0);
        final String loginName = user.getLoginName();
        View view = this.u;
        final String str3 = str;
        final String str4 = str2;
        AnonymousClass7 r2 = new TrackingOnClickListener(new i[]{user}) {
            public void onViewClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(ResponseConstants.USERNAME, loginName);
                bundle.putString(ResponseConstants.SUBJECT, str3);
                bundle.putString("message", str4);
                if (!v.a().e()) {
                    ((com.etsy.android.ui.nav.b) e.a((Activity) f.this.e.get()).a(view)).a(EtsyAction.CONTACT_USER, bundle);
                } else {
                    e.a((Activity) f.this.e.get()).e(bundle);
                }
            }
        };
        view.setOnClickListener(r2);
    }

    public void b() {
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.v = null;
        this.x = null;
        this.w = null;
        this.u = null;
        this.r = null;
        this.s = null;
        this.t = null;
    }
}
