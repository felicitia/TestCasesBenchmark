package com.etsy.android.ui.finds;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.ui.cardview.clickhandlers.e;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.d;

/* compiled from: FindsViewHolderFactory */
public class a extends c {
    private static final String a = f.a(a.class);
    private FindsRecyclerViewAdapter i;
    private FragmentActivity j;

    public a(FragmentActivity fragmentActivity, d dVar, @NonNull b bVar) {
        super(fragmentActivity, bVar);
        this.i = (FindsRecyclerViewAdapter) dVar;
        this.j = fragmentActivity;
        com.etsy.android.ui.finds.cardview.listener.a aVar = new com.etsy.android.ui.finds.cardview.listener.a(this.j, this.f);
        this.e.put(R.id.view_type_finds_category, aVar);
        this.e.put(R.id.view_type_finds_two_titled_footer, aVar);
        com.etsy.android.ui.cardview.clickhandlers.f fVar = new com.etsy.android.ui.cardview.clickhandlers.f(this.j, dVar, this.f);
        this.e.put(R.id.view_type_listing_card, fVar);
        this.e.put(R.id.view_type_finds_two_titled_listing, fVar);
        this.e.put(R.id.view_type_finds_hero_listing, fVar);
        this.e.put(R.id.view_type_shop_card, new m(this.j, this.f));
        e eVar = new e(this.j, this.f);
        this.e.put(R.id.view_type_finds_card, eVar);
        this.e.put(R.id.view_type_finds_card_small, eVar);
        this.e.put(R.id.view_type_finds_hero_banner, eVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r10 = new com.etsy.android.ui.finds.cardview.FindsHeadingViewHolder(r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.etsy.android.vespa.viewholders.BaseViewHolder a(android.view.ViewGroup r9, int r10) {
        /*
            r8 = this;
            r0 = 2131363418(0x7f0a065a, float:1.8346644E38)
            if (r10 == r0) goto L_0x008e
            r0 = 2131363461(0x7f0a0685, float:1.8346731E38)
            if (r10 == r0) goto L_0x0088
            r0 = 2131363463(0x7f0a0687, float:1.8346736E38)
            if (r10 == r0) goto L_0x0077
            switch(r10) {
                case 2131363398: goto L_0x0062;
                case 2131363399: goto L_0x0052;
                case 2131363400: goto L_0x0042;
                case 2131363401: goto L_0x0039;
                case 2131363402: goto L_0x0033;
                default: goto L_0x0012;
            }
        L_0x0012:
            switch(r10) {
                case 2131363404: goto L_0x002b;
                case 2131363405: goto L_0x008e;
                case 2131363406: goto L_0x001c;
                case 2131363407: goto L_0x0033;
                case 2131363408: goto L_0x008e;
                default: goto L_0x0015;
            }
        L_0x0015:
            com.etsy.android.vespa.viewholders.BaseViewHolder r9 = super.a(r9, r10)
            r0 = r9
            goto L_0x009d
        L_0x001c:
            com.etsy.android.ui.finds.cardview.FindsTwoTitledListingFooterViewHolder r0 = new com.etsy.android.ui.finds.cardview.FindsTwoTitledListingFooterViewHolder
            android.support.v4.util.SparseArrayCompat r1 = r8.e
            java.lang.Object r10 = r1.get(r10)
            com.etsy.android.ui.finds.cardview.listener.a r10 = (com.etsy.android.ui.finds.cardview.listener.a) r10
            r0.<init>(r9, r10)
            goto L_0x009d
        L_0x002b:
            com.etsy.android.ui.finds.cardview.listener.FindsHeroBannerViewHolder r10 = new com.etsy.android.ui.finds.cardview.listener.FindsHeroBannerViewHolder
            com.etsy.android.lib.core.img.c r0 = r8.d
            r10.<init>(r9, r0)
            goto L_0x0040
        L_0x0033:
            com.etsy.android.ui.finds.cardview.FindsHeadingViewHolder r10 = new com.etsy.android.ui.finds.cardview.FindsHeadingViewHolder
            r10.<init>(r9)
            goto L_0x0040
        L_0x0039:
            com.etsy.android.ui.cardview.viewholders.GiftCardBannerHolder r10 = new com.etsy.android.ui.cardview.viewholders.GiftCardBannerHolder
            com.etsy.android.lib.core.img.c r0 = r8.d
            r10.<init>(r9, r0)
        L_0x0040:
            r0 = r10
            goto L_0x009d
        L_0x0042:
            com.etsy.android.ui.finds.cardview.FindsCategoryViewHolder r0 = new com.etsy.android.ui.finds.cardview.FindsCategoryViewHolder
            android.support.v4.util.SparseArrayCompat r1 = r8.e
            java.lang.Object r10 = r1.get(r10)
            com.etsy.android.ui.finds.cardview.listener.a r10 = (com.etsy.android.ui.finds.cardview.listener.a) r10
            com.etsy.android.lib.core.img.c r1 = r8.d
            r0.<init>(r9, r10, r1)
            goto L_0x009d
        L_0x0052:
            com.etsy.android.ui.cardview.viewholders.FindsSmallCrosslinkViewHolder r0 = new com.etsy.android.ui.cardview.viewholders.FindsSmallCrosslinkViewHolder
            android.support.v4.util.SparseArrayCompat r1 = r8.e
            java.lang.Object r10 = r1.get(r10)
            com.etsy.android.ui.cardview.clickhandlers.e r10 = (com.etsy.android.ui.cardview.clickhandlers.e) r10
            com.etsy.android.lib.core.img.c r1 = r8.d
            r0.<init>(r9, r10, r1)
            goto L_0x009d
        L_0x0062:
            com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder r0 = new com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder
            android.support.v4.util.SparseArrayCompat r1 = r8.e
            java.lang.Object r10 = r1.get(r10)
            r4 = r10
            com.etsy.android.ui.cardview.clickhandlers.e r4 = (com.etsy.android.ui.cardview.clickhandlers.e) r4
            com.etsy.android.lib.core.img.c r5 = r8.d
            r6 = 0
            r7 = 0
            r2 = r0
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x009d
        L_0x0077:
            com.etsy.android.ui.finds.cardview.GridShopCardViewHolder r0 = new com.etsy.android.ui.finds.cardview.GridShopCardViewHolder
            android.support.v4.util.SparseArrayCompat r1 = r8.e
            java.lang.Object r10 = r1.get(r10)
            com.etsy.android.ui.cardview.clickhandlers.m r10 = (com.etsy.android.ui.cardview.clickhandlers.m) r10
            com.etsy.android.lib.core.img.c r1 = r8.d
            r2 = 0
            r0.<init>(r9, r10, r1, r2)
            goto L_0x009d
        L_0x0088:
            com.etsy.android.vespa.viewholders.ListSectionHeaderViewHolder r10 = new com.etsy.android.vespa.viewholders.ListSectionHeaderViewHolder
            r10.<init>(r9)
            goto L_0x0040
        L_0x008e:
            com.etsy.android.uikit.viewholder.ListingCardViewHolder r0 = new com.etsy.android.uikit.viewholder.ListingCardViewHolder
            android.support.v4.util.SparseArrayCompat r1 = r8.e
            java.lang.Object r10 = r1.get(r10)
            com.etsy.android.ui.cardview.clickhandlers.f r10 = (com.etsy.android.ui.cardview.clickhandlers.f) r10
            com.etsy.android.lib.core.img.c r1 = r8.d
            r0.<init>(r9, r10, r1)
        L_0x009d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.finds.a.a(android.view.ViewGroup, int):com.etsy.android.vespa.viewholders.BaseViewHolder");
    }

    public int a(int i2, int i3) {
        Resources resources = this.j.getResources();
        int c = c();
        int siblingCountForPosition = this.i.getSiblingCountForPosition(i3);
        if (i2 == R.id.view_type_finds_heading) {
            return c;
        }
        if (i2 == R.id.view_type_listing_card) {
            switch (resources.getInteger(R.integer.finds_listing_minimum_in_row)) {
                case 2:
                    if (siblingCountForPosition % 3 == 0) {
                        return resources.getInteger(R.integer.vespa_grid_item_third_span);
                    }
                    return resources.getInteger(R.integer.vespa_grid_item_half_span);
                case 3:
                    if (siblingCountForPosition % 3 == 0) {
                        return resources.getInteger(R.integer.vespa_grid_item_third_span);
                    }
                    return resources.getInteger(R.integer.vespa_grid_item_quarter_span);
                default:
                    return c;
            }
        } else if (i2 == R.id.view_type_shop_card) {
            return resources.getInteger(R.integer.finds_shop_span);
        } else {
            switch (i2) {
                case R.id.view_type_finds_card /*2131363398*/:
                    return resources.getInteger(R.integer.finds_crosslink_span);
                case R.id.view_type_finds_card_small /*2131363399*/:
                    return resources.getInteger(R.integer.finds_small_crosslink_span);
                case R.id.view_type_finds_category /*2131363400*/:
                    if (siblingCountForPosition % 3 == 0) {
                        return resources.getInteger(R.integer.finds_large_category_card_span);
                    }
                    return resources.getInteger(R.integer.finds_category_card_span);
                default:
                    switch (i2) {
                        case R.id.view_type_finds_hero_listing /*2131363405*/:
                            return resources.getInteger(R.integer.finds_hero_listing_span);
                        case R.id.view_type_finds_two_titled_footer /*2131363406*/:
                            return resources.getInteger(R.integer.finds_two_titled_heading_span);
                        case R.id.view_type_finds_two_titled_heading /*2131363407*/:
                            return resources.getInteger(R.integer.finds_two_titled_heading_span);
                        case R.id.view_type_finds_two_titled_listing /*2131363408*/:
                            return resources.getInteger(R.integer.finds_two_titled_listing_span);
                        default:
                            return c;
                    }
            }
        }
    }
}
