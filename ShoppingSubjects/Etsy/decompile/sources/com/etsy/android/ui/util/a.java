package com.etsy.android.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.FavoriteList;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ShopIcon;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.util.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.List;

/* compiled from: EtsyCardUtil */
public class a {
    private final int a = c().getDimensionPixelOffset(R.dimen.card_avatar_small);
    private final int b = c().getDimensionPixelOffset(R.dimen.gen_avatar_corners_small);
    private Drawable c;
    private Drawable d;
    private Reference<Activity> e;
    private c f;
    @NonNull
    private final w g;

    /* renamed from: com.etsy.android.ui.util.a$a reason: collision with other inner class name */
    /* compiled from: EtsyCardUtil */
    public static class C0108a {
        public View a;
        View b;
        TextView c;
        ImageView d;
        TextView e;
        LinearLayout f;
        ImageView g;
        RatingIconView h;
        TextView i;
    }

    public static String a(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("FavoriteListings(listing_id):active:");
        sb.append(i);
        sb.append("/Listing(");
        sb.append("listing_id");
        sb.append(")/");
        sb.append("Images(url_170x135,red,green,blue)");
        return sb.toString();
    }

    public static String b(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("DisplayedFeaturedListings(listing_id):active:");
        sb.append(i);
        sb.append("/");
        sb.append("Images(url_170x135,red,green,blue)");
        return sb.toString();
    }

    public static String c(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Listings(listing_id):active:");
        sb.append(i);
        sb.append("/");
        sb.append("Images(url_170x135,red,green,blue)");
        return sb.toString();
    }

    public a(Activity activity, c cVar, @NonNull w wVar) {
        this.e = new WeakReference(activity);
        this.f = cVar;
        this.g = wVar;
        int dimensionPixelOffset = c().getDimensionPixelOffset(R.dimen.sk_size_icon_smaller);
        int dimensionPixelOffset2 = c().getDimensionPixelOffset(R.dimen.fixed_one);
        this.c = com.etsy.android.uikit.c.a(activity, R.drawable.sk_ic_location, R.color.sk_gray_50);
        this.d = com.etsy.android.uikit.c.a(activity, R.drawable.sk_ic_location, R.color.sk_orange_30);
        int i = dimensionPixelOffset2 + dimensionPixelOffset;
        this.c.setBounds(0, dimensionPixelOffset2, dimensionPixelOffset, i);
        this.d.setBounds(0, dimensionPixelOffset2, dimensionPixelOffset, i);
    }

    private c a() {
        return this.f;
    }

    /* access modifiers changed from: private */
    public Activity b() {
        return (Activity) this.e.get();
    }

    private Resources c() {
        return ((Activity) this.e.get()).getResources();
    }

    public static C0108a a(View view) {
        C0108a aVar = new C0108a();
        aVar.a = view;
        aVar.b = view.findViewById(R.id.click_region);
        aVar.c = (TextView) view.findViewById(R.id.title);
        aVar.d = (ImageView) view.findViewById(R.id.title_icon);
        aVar.e = (TextView) view.findViewById(R.id.subtitle);
        aVar.h = (RatingIconView) view.findViewById(R.id.rating);
        aVar.i = (TextView) view.findViewById(R.id.rating_count);
        aVar.f = (LinearLayout) view.findViewById(R.id.image_layout);
        aVar.g = (ImageView) view.findViewById(R.id.avatar);
        return aVar;
    }

    private void a(LinearLayout linearLayout, List<? extends ListingLike> list, int i) {
        linearLayout.removeAllViews();
        for (int i2 = 0; i2 < i; i2++) {
            BaseModelImage baseModelImage = null;
            if (list.size() > i2) {
                baseModelImage = ((ListingLike) list.get(i2)).getListingImage();
            }
            ListingFullImageView listingFullImageView = new ListingFullImageView(b());
            listingFullImageView.setScaleType(ScaleType.CENTER_CROP);
            listingFullImageView.setUseStandardRatio(true);
            listingFullImageView.setLayoutParams(new LayoutParams(0, 0, 1.0f));
            if (baseModelImage != null) {
                listingFullImageView.setImageInfo(baseModelImage, a());
            } else if (i2 == i - 1) {
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image);
            } else {
                listingFullImageView.setBackgroundResource(R.drawable.bg_empty_image_right_divider);
            }
            linearLayout.addView(listingFullImageView);
        }
    }

    public void a(C0108a aVar, final FavoriteList favoriteList, int i) {
        if (favoriteList != null) {
            aVar.c.setText(b().getString(R.string.list_items_i_love));
            AnonymousClass1 r0 = new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a(a.this.b()).a(favoriteList.getUserId(), 0, favoriteList.getLoginName());
                }
            };
            StringBuilder sb = new StringBuilder();
            sb.append(af.a((double) favoriteList.getNumFavorites()));
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(b().getString(R.string.items));
            aVar.e.setText(sb.toString());
            aVar.b.setOnClickListener(r0);
            if (i > 0) {
                a(aVar.f, favoriteList.getListings(), i);
            }
        }
    }

    public static String a(Context context, int i) {
        String string = context.getString(R.string.shops_found, new Object[]{NumberFormat.getInstance().format((long) i)});
        if (i == 0) {
            return context.getString(R.string.shops_found_none);
        }
        return i == 1 ? context.getString(R.string.shops_found_single) : string;
    }

    public void a(C0108a aVar, ShopLike shopLike, int i, boolean z) {
        String avatarUrl;
        if (shopLike != null) {
            String shopName = shopLike.getShopName();
            boolean z2 = shopLike.hasUpcomingLocalEvent() && (d.b() || this.g.c().c(b.K));
            aVar.c.setText(shopName);
            final boolean z3 = z2;
            final ShopLike shopLike2 = shopLike;
            AnonymousClass2 r4 = new TrackingOnClickListener(AnalyticsLogAttribute.SHOP_ID, shopLike.getShopId()) {
                public void onViewClick(View view) {
                    if (z3) {
                        com.etsy.android.lib.logger.legacy.b.a().b("local_shop_tapped", "your_favorite_shops", new EtsyCardUtil$2$1(this));
                    }
                    if (shopLike2.getUserId() == null || !shopLike2.getUserId().hasId()) {
                        e.a(a.this.b()).b(shopLike2.getShopId());
                    } else {
                        e.a(a.this.b()).a(shopLike2.getShopId(), shopLike2.getUserId());
                    }
                }
            };
            aVar.b.setOnClickListener(r4);
            if (i > 0) {
                a(aVar.f, shopLike.getCardListings(), i);
            }
            String location = shopLike.getLocation();
            aVar.g.setVisibility(0);
            if (af.a(shopLike.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue()))) {
                avatarUrl = shopLike.getIconUrl(((Integer) ShopIcon.IMG_SIZE_75.first).intValue());
            } else {
                avatarUrl = shopLike.getAvatarUrl();
            }
            a().b(avatarUrl, aVar.g, this.b, this.a, this.a);
            if (z) {
                aVar.e.setVisibility(8);
                if (shopLike.hasRatings() && shopLike.getAverageRating() > 0.0d) {
                    aVar.i.setVisibility(0);
                    aVar.h.setVisibility(0);
                    aVar.h.setRating((float) shopLike.getAverageRating());
                    TextView textView = aVar.i;
                    StringBuilder sb = new StringBuilder();
                    sb.append("(");
                    sb.append(af.a((double) shopLike.getNumRatings()));
                    sb.append(")");
                    textView.setText(sb.toString());
                } else if (shopLike.hasRatings()) {
                    aVar.i.setVisibility(0);
                    aVar.h.setVisibility(8);
                    aVar.i.setText(c().getQuantityString(R.plurals.reviews_plurals_no_brackets, shopLike.getNumRatings(), new Object[]{af.a((double) shopLike.getNumRatings())}));
                } else {
                    aVar.h.setVisibility(8);
                    aVar.i.setVisibility(8);
                }
            } else {
                aVar.h.setVisibility(8);
                aVar.i.setVisibility(8);
                if (z2) {
                    aVar.e.setText(shopLike.getUpcomingLocalEvent().getSellingStatusString(c()));
                    aVar.e.setTextColor(c().getColor(R.color.sk_orange_30));
                    aVar.e.setCompoundDrawables(this.d, null, null, null);
                    aVar.e.setVisibility(0);
                } else if (af.a(location)) {
                    aVar.e.setText(location);
                    aVar.e.setTextColor(c().getColor(R.color.sk_gray_50));
                    aVar.e.setCompoundDrawables(this.c, null, null, null);
                    aVar.e.setVisibility(0);
                } else {
                    aVar.e.setVisibility(8);
                }
            }
        }
    }

    public static String d(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Profile(image_url_75x75,first_name,last_name,login_name,num_favorites),");
        sb.append(a(i));
        return sb.toString();
    }

    public void a(C0108a aVar, final User user, int i) {
        if (user != null) {
            UserProfile profile = user.getProfile();
            String str = null;
            if (profile != null) {
                aVar.c.setText(af.a(user));
                str = profile.getImageUrl75x75();
            }
            TextView textView = aVar.e;
            StringBuilder sb = new StringBuilder();
            sb.append(c().getString(R.string.followers));
            sb.append(": ");
            sb.append(af.a((double) user.getFollowerCount()));
            textView.setText(sb.toString());
            aVar.g.setBackgroundResource(R.drawable.bg_avatar_circle_small_borderless);
            aVar.g.setVisibility(0);
            a().b(str, aVar.g, this.a);
            aVar.b.setOnClickListener(new TrackingOnClickListener(new i[]{user}) {
                public void onViewClick(View view) {
                    e.a(a.this.b()).c(user.getUserId());
                }
            });
            a(aVar.f, user.getFavoritesAsListings(), i);
        }
    }

    public void a(C0108a aVar, final Collection collection, int i, final Fragment fragment) {
        if (collection != null) {
            aVar.c.setText(collection.getName());
            if (collection.isPrivate()) {
                aVar.d.setVisibility(0);
            } else {
                aVar.d.setVisibility(8);
            }
            AnonymousClass4 r0 = new TrackingOnClickListener(new i[]{collection}) {
                public void onViewClick(View view) {
                    e.a(a.this.b()).a(600, fragment).a(collection);
                }
            };
            int listingsCount = collection.getListingsCount();
            aVar.e.setText(c().getQuantityString(R.plurals.item_titlecase_quantity, listingsCount, new Object[]{af.a((double) listingsCount)}));
            aVar.b.setOnClickListener(r0);
            if (i > 0) {
                a(aVar.f, collection.getRepresentativeListings(), i);
            }
        }
    }

    @CallSuper
    public void a(Activity activity) {
        this.e = new WeakReference(activity);
    }
}
