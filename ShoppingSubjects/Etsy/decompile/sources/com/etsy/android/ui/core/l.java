package com.etsy.android.ui.core;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.ShopAboutMember;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.af;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.ui.adapters.a.C0086a;
import com.etsy.android.ui.adapters.b;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ShopCardPresenter */
public class l implements com.etsy.android.ui.adapters.b.a {
    private final int a;
    /* access modifiers changed from: private */
    public FragmentActivity b;
    private c c;
    private String d = "view_listing";
    private String e;
    private final int f;
    private final int g;
    private final b h;
    private List<C0086a<Listing>> i;

    /* compiled from: ShopCardPresenter */
    public static class a {
        ImageView a;
        TextView b;
        CompoundVectorTextView c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        View h;
        View i;
        View j;
        LinearLayout k;
    }

    public l(FragmentActivity fragmentActivity, c cVar, int i2) {
        this.b = fragmentActivity;
        this.c = cVar;
        Resources b2 = b();
        this.h = new b(this.b, this.c, R.layout.list_item_listing_light_redesign);
        this.h.c(i2);
        this.h.b(R.drawable.bg_card_light_transparent);
        this.h.e(b2.getDimensionPixelSize(R.dimen.padding_medium));
        this.h.d(0);
        this.h.f(b2.getDimensionPixelSize(R.dimen.padding_medium));
        this.h.a((com.etsy.android.ui.adapters.b.a) this);
        this.h.b();
        this.f = b2.getDimensionPixelSize(R.dimen.shop_home_main_avatar);
        this.g = b2.getDimensionPixelSize(R.dimen.gen_avatar_corners_small);
        this.a = b2.getDimensionPixelSize(R.dimen.shop_member_avatar);
        this.e = SharedPreferencesUtility.g(fragmentActivity);
        this.i = new ArrayList();
    }

    private c a() {
        return this.c;
    }

    private Resources b() {
        return this.b.getResources();
    }

    public a a(View view) {
        a aVar = new a();
        aVar.a = (ImageView) view.findViewById(R.id.shop_avatar);
        aVar.b = (TextView) view.findViewById(R.id.shop_name);
        aVar.c = (CompoundVectorTextView) view.findViewById(R.id.shop_location);
        aVar.d = (TextView) view.findViewById(R.id.tagline);
        aVar.e = (TextView) view.findViewById(R.id.txt_made_by_shop_name);
        aVar.f = (TextView) view.findViewById(R.id.txt_meet_owners);
        aVar.g = (ImageView) view.findViewById(R.id.img_shop_about_avatar);
        aVar.k = (LinearLayout) view.findViewById(R.id.shop_listing_layout);
        aVar.h = view.findViewById(R.id.panel_shop_card_header);
        aVar.j = view.findViewById(R.id.panel_shop_about);
        aVar.i = view.findViewById(R.id.txt_shop_visit);
        return aVar;
    }

    public void a(a aVar, Shop shop, int i2, int i3, boolean z) {
        String imageUrl75x75;
        a aVar2 = aVar;
        final Shop shop2 = shop;
        int i4 = i2;
        Resources b2 = b();
        if (shop2 != null && shop.getUser() != null) {
            UserProfile profile = shop.getUser().getProfile();
            String shopName = shop.getShopName();
            aVar2.b.setText(shopName);
            if (z) {
                aVar2.e.setText(b2.getString(R.string.made_by, new Object[]{shopName}));
                aVar2.e.setVisibility(0);
            } else {
                aVar2.e.setVisibility(8);
            }
            if (!TextUtils.isEmpty(shop.getTitle())) {
                aVar2.d.setVisibility(0);
                aVar2.d.setText(shop.getTitle());
            } else {
                aVar2.d.setVisibility(8);
            }
            String a2 = af.a(profile);
            if (af.a(a2)) {
                aVar2.c.setText(a2);
            } else {
                aVar2.c.setVisibility(8);
            }
            if (af.a(shop2.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
                imageUrl75x75 = shop2.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
            } else {
                imageUrl75x75 = profile.getImageUrl75x75();
            }
            a().b(imageUrl75x75, aVar2.a, this.g, this.f, this.f);
            AnonymousClass1 r5 = new TrackingOnClickListener(new i[]{shop2}) {
                public void onViewClick(View view) {
                    if (shop2.getUser() == null || !shop2.getUser().getUserId().hasId()) {
                        e.a(l.this.b).a().b(shop2.getShopId());
                    } else {
                        e.a(l.this.b).a().a(shop2.getShopId(), shop2.getUser().getUserId());
                    }
                }
            };
            aVar2.h.setOnClickListener(r5);
            aVar2.i.setOnClickListener(r5);
            if (shop.hasAbout()) {
                aVar2.j.setVisibility(0);
                AnonymousClass2 r52 = new TrackingOnClickListener(new i[]{shop2}) {
                    public void onViewClick(View view) {
                        e.a(l.this.b).a().d(shop2.getShopId(), null);
                    }
                };
                aVar2.g.setImageBitmap(null);
                List members = shop.getAbout().getMembers();
                if (members.size() > 0) {
                    aVar2.g.setVisibility(0);
                    a().b(((ShopAboutMember) members.get(0)).getImageUrl190x190(), aVar2.g, this.a);
                } else {
                    aVar2.g.setVisibility(8);
                }
                aVar2.j.setOnClickListener(r52);
                aVar2.f.setText(b2.getQuantityString(R.plurals.meet_the_owners_of, members.size(), new Object[]{shopName}));
            } else {
                aVar2.j.setVisibility(8);
                aVar2.j.setOnClickListener(null);
            }
            if (i4 > 0) {
                a(aVar2.k, shop.getListings(), i4, i3);
            }
        }
    }

    private void a(LinearLayout linearLayout, List<Listing> list, int i2, int i3) {
        if (list.size() == 0 || i3 == 0) {
            linearLayout.setVisibility(8);
            return;
        }
        int a2 = this.h.a();
        int min = Math.min(i2, list.size() / a2);
        if (min == 0) {
            linearLayout.setVisibility(8);
            return;
        }
        for (int i4 = 0; i4 < min; i4++) {
            int i5 = i4 * a2;
            View a3 = this.h.a((View) null);
            for (int i6 = 0; i6 < a2; i6++) {
                int i7 = i5 + i6;
                if (i7 >= list.size()) {
                    break;
                }
                C0086a a4 = this.h.a(a3.getTag(), (Listing) list.get(i7), i6);
                if (a4 != null) {
                    this.i.add(a4);
                }
            }
            linearLayout.addView(a3);
        }
        linearLayout.setVisibility(0);
    }

    public void a(Listing listing) {
        if (listing != null) {
            e.a((Activity) this.b).a(listing.getListingId());
        }
    }
}
