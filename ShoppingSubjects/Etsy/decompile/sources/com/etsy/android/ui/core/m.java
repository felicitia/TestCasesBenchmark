package com.etsy.android.ui.core;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.f;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.adapters.a.C0086a;
import com.etsy.android.ui.core.SimilarItemsLayout.a;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SimilarListingsPresenter */
public class m implements a {
    /* access modifiers changed from: private */
    public BOENavDrawerActivity a;
    private c b;
    /* access modifiers changed from: private */
    public View c;
    /* access modifiers changed from: private */
    public SimilarItemsLayout d;
    /* access modifiers changed from: private */
    public List<C0086a<ListingCard>> e;
    private boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;

    public m(@NonNull EtsyFragment etsyFragment, View view, c cVar) {
        this.a = (BOENavDrawerActivity) etsyFragment.getActivity();
        this.b = cVar;
        this.d = (SimilarItemsLayout) view.findViewById(R.id.similar_items_scrollview);
        this.c = view.findViewById(R.id.similar_items_text_description);
        this.e = new ArrayList();
        this.f = etsyFragment.getConfigMap().c(b.z);
    }

    public void b(Listing listing) {
        if (this.f) {
            this.d.setListing(listing, this);
        } else {
            a(listing);
        }
    }

    public void a() {
        this.a.getRequestQueue().a((Object) this);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = true;
    }

    public void a(final Listing listing) {
        this.a.getResources();
        final com.etsy.android.ui.adapters.c cVar = new com.etsy.android.ui.adapters.c(this.a, this.b);
        cVar.b(R.drawable.bg_card_light_transparent);
        cVar.a((com.etsy.android.ui.adapters.c.a) new com.etsy.android.ui.adapters.c.a() {
            public void a(ListingCard listingCard) {
                com.etsy.android.lib.logger.legacy.b.a().d("similar_listing_selected", "view_listing");
                e.a((FragmentActivity) m.this.a).a().a(listingCard.getListingId());
            }
        });
        this.a.getRequestQueue().a((Object) this, (g<Result>) com.etsy.android.lib.core.m.a(ListingCard.class, v.a().e() ? "/etsyapps/v3/member/personalization/similar-listings" : "/etsyapps/v3/public/personalization/similar-listings").a(ResponseConstants.LISTING_IDS, listing.getListingId().getId()).a("limit", "6").a("variant", "RecommenderSystems_SimilarListingsTfidf_Strings").a((f.c<Result>) new f.c<ListingCard>() {
            public void a(List<ListingCard> list, int i, k<ListingCard> kVar) {
                if (!m.this.g) {
                    m.this.c.setVisibility(0);
                    m.this.d.setVisibility(0);
                    int size = list.size();
                    ViewGroup viewGroup = (ViewGroup) cVar.a(null, size);
                    for (int i2 = 0; i2 < size; i2++) {
                        C0086a a2 = cVar.a(viewGroup.getTag(), list.get(i2), i2);
                        if (a2 != null) {
                            m.this.e.add(a2);
                        }
                    }
                    View inflate = LayoutInflater.from(m.this.a).inflate(R.layout.list_item_see_all_horizontal, viewGroup, false);
                    inflate.setOnClickListener(new TrackingOnClickListener(listing) {
                        public void onViewClick(View view) {
                            e.a((FragmentActivity) m.this.a).a().d(listing.getListingId().getId());
                        }
                    });
                    viewGroup.addView(inflate);
                    m.this.d.removeAllViews();
                    m.this.d.addView(viewGroup);
                }
            }
        }).a((f.a) new f.a() {
            public void a(k kVar) {
                if (!m.this.g) {
                    m.this.c.setVisibility(8);
                    m.this.d.setVisibility(8);
                }
            }
        }).a((f.b) new f.b() {
            public void a(int i, String str, k kVar) {
                if (!m.this.g) {
                    m.this.c.setVisibility(8);
                    m.this.d.setVisibility(8);
                }
            }
        }).a());
    }
}
