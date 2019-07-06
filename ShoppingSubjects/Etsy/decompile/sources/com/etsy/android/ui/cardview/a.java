package com.etsy.android.ui.cardview;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.home.onboarding.DividerViewHolder;
import com.etsy.android.home.onboarding.SearchBarViewHolder;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.Button;
import com.etsy.android.lib.models.apiv3.Segment;
import com.etsy.android.ui.cardview.clickhandlers.SavedCartClickHandler;
import com.etsy.android.ui.cardview.clickhandlers.e;
import com.etsy.android.ui.cardview.clickhandlers.g;
import com.etsy.android.ui.cardview.clickhandlers.m;
import com.etsy.android.ui.cardview.clickhandlers.n;
import com.etsy.android.ui.cardview.viewholders.ActionableHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.CategoryCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.CategoryRecCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.FindsCrosslinkViewHolder;
import com.etsy.android.ui.cardview.viewholders.FindsSmallCrosslinkViewHolder;
import com.etsy.android.ui.cardview.viewholders.GiftCardBannerHolder;
import com.etsy.android.ui.cardview.viewholders.HorizontalCardListSectionViewHolder;
import com.etsy.android.ui.cardview.viewholders.LeftAlignedAllCapsHeaderViewHolder;
import com.etsy.android.ui.cardview.viewholders.ListingCollectionViewHolder;
import com.etsy.android.ui.cardview.viewholders.NestedListSectionViewHolder;
import com.etsy.android.ui.cardview.viewholders.SavedCartListingCardViewHolder2;
import com.etsy.android.ui.cardview.viewholders.ShopCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.ShopShareCardViewHolder;
import com.etsy.android.ui.cardview.viewholders.TaxonomyCategoryRowViewHolder;
import com.etsy.android.ui.cardview.viewholders.WideShopCardViewHolder;
import com.etsy.android.ui.onboarding.ExploreBannerViewHolder;
import com.etsy.android.uikit.viewholder.AnchorListingCardViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardMiniViewHolder;
import com.etsy.android.uikit.viewholder.ListingCardViewHolder;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.d;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import com.etsy.android.vespa.viewholders.ButtonViewHolder;
import com.etsy.android.vespa.viewholders.DeepLinkSegmentListViewHolder;
import com.etsy.android.vespa.viewholders.ExploreHeaderViewHolder;
import com.etsy.android.vespa.viewholders.ExploreResultsHeaderViewHolder;
import com.etsy.android.vespa.viewholders.ExploreSegmentListViewHolder;
import com.etsy.android.vespa.viewholders.ImageBannerViewHolder;
import com.etsy.android.vespa.viewholders.ListSectionLinkFooterViewHolder;
import com.etsy.android.vespa.viewholders.LoadingCardViewHolder;
import com.etsy.android.vespa.viewholders.SizeableTextViewHolder;

/* compiled from: CardViewHolderFactory */
public class a extends c<FragmentActivity> {
    final String a = f.a(a.class);
    private boolean i = false;

    public a(FragmentActivity fragmentActivity, d dVar, @NonNull b bVar) {
        super(fragmentActivity, bVar, dVar);
    }

    public a(FragmentActivity fragmentActivity, d dVar, @NonNull b bVar, @Nullable com.etsy.android.vespa.f fVar) {
        super(fragmentActivity, bVar, dVar, fVar);
    }

    /* access modifiers changed from: protected */
    public void a() {
        com.etsy.android.ui.cardview.clickhandlers.f fVar = new com.etsy.android.ui.cardview.clickhandlers.f((FragmentActivity) this.c, this.h, this.f);
        this.e.put(R.id.view_type_listing_card, fVar);
        this.e.put(R.id.view_type_anchor_listing_card, fVar);
        m mVar = new m((FragmentActivity) this.c, this.f);
        this.e.put(R.id.view_type_shop_card, mVar);
        this.e.put(R.id.view_type_wide_shop_card, mVar);
        this.e.put(R.id.view_type_category_recommendation_card, new com.etsy.android.ui.cardview.clickhandlers.c((FragmentActivity) this.c, this.f));
        this.e.put(R.id.view_type_section_link_footer, new com.etsy.android.ui.cardview.clickhandlers.a((FragmentActivity) this.c, this.f));
        e eVar = new e((FragmentActivity) this.c, this.f);
        this.e.put(R.id.view_type_finds_card, eVar);
        this.e.put(R.id.view_type_finds_card_small, eVar);
        this.e.put(R.id.view_type_shop_share_landing_page_card, new n((FragmentActivity) this.c, this.h, this.f));
        this.e.put(R.id.view_type_shop_share_homescreen_card, new n((FragmentActivity) this.c, this.h, this.f));
        this.e.put(R.id.view_type_category_card, new com.etsy.android.ui.cardview.clickhandlers.b((FragmentActivity) this.c, this.f));
        this.e.put(R.id.view_type_button, new com.etsy.android.vespa.a.b((FragmentActivity) this.c, this.f) {
            public void a(Button button) {
                if (button != null) {
                    com.etsy.android.ui.nav.e.a((FragmentActivity) a.this.c).a().j(button.link);
                }
            }
        });
        com.etsy.android.vespa.a.f fVar2 = new com.etsy.android.vespa.a.f((FragmentActivity) this.c, this.f, this.g);
        this.e.put(R.id.view_type_actionable_header, fVar2);
        this.e.put(R.id.view_type_explore_segment_list, fVar2);
        this.e.put(R.id.view_type_explore_results_header, fVar2);
        this.e.put(R.id.view_type_explore_banner, new com.etsy.android.ui.cardview.clickhandlers.d((FragmentActivity) this.c, this.f));
        this.e.put(R.id.view_type_deep_link_segment_list, new com.etsy.android.vespa.a.c((FragmentActivity) this.c, this.f) {
            public void a(Segment segment) {
                if (segment != null) {
                    com.etsy.android.ui.nav.e.a((FragmentActivity) a.this.c).a().j(segment.getDeepLink());
                }
            }
        });
    }

    public BaseViewHolder a(ViewGroup viewGroup, int i2) {
        boolean a2 = a(viewGroup);
        switch (i2) {
            case R.id.view_type_actionable_header /*2131363380*/:
                return new ActionableHeaderViewHolder(viewGroup, (com.etsy.android.vespa.a.f) this.e.get(i2));
            case R.id.view_type_anchor_listing_card /*2131363381*/:
                return new AnchorListingCardViewHolder(viewGroup, (com.etsy.android.ui.cardview.clickhandlers.f) this.e.get(i2), this.d);
            case R.id.view_type_button /*2131363384*/:
                return new ButtonViewHolder(viewGroup, (com.etsy.android.vespa.a.b) this.e.get(i2));
            case R.id.view_type_category_card /*2131363385*/:
                return new CategoryCardViewHolder(viewGroup, (com.etsy.android.vespa.b) this.e.get(i2), this.d, a2);
            case R.id.view_type_category_recommendation_card /*2131363386*/:
                return new CategoryRecCardViewHolder(viewGroup, (com.etsy.android.vespa.b) this.e.get(i2), this.d);
            case R.id.view_type_deep_link_segment_list /*2131363387*/:
                return new DeepLinkSegmentListViewHolder(viewGroup, (com.etsy.android.vespa.a.c) this.e.get(i2));
            case R.id.view_type_divider /*2131363389*/:
                return new DividerViewHolder(viewGroup);
            case R.id.view_type_explore_banner /*2131363394*/:
                return new ExploreBannerViewHolder(viewGroup, (com.etsy.android.ui.cardview.clickhandlers.d) this.e.get(i2), d());
            case R.id.view_type_explore_header /*2131363395*/:
                return new ExploreHeaderViewHolder(viewGroup, this.d);
            case R.id.view_type_explore_results_header /*2131363396*/:
                return new ExploreResultsHeaderViewHolder(viewGroup, (com.etsy.android.vespa.a.f) this.e.get(i2));
            case R.id.view_type_explore_segment_list /*2131363397*/:
                return new ExploreSegmentListViewHolder(viewGroup, (com.etsy.android.vespa.a.f) this.e.get(i2));
            case R.id.view_type_finds_card /*2131363398*/:
                FindsCrosslinkViewHolder findsCrosslinkViewHolder = new FindsCrosslinkViewHolder(viewGroup, (e) this.e.get(i2), this.d, a2, false);
                return findsCrosslinkViewHolder;
            case R.id.view_type_finds_card_small /*2131363399*/:
                return new FindsSmallCrosslinkViewHolder(viewGroup, (e) this.e.get(i2), this.d);
            case R.id.view_type_finds_gift_card_banner /*2131363401*/:
                return new GiftCardBannerHolder(viewGroup, this.d);
            case R.id.view_type_horizontal_list_section /*2131363413*/:
                HorizontalCardListSectionViewHolder horizontalCardListSectionViewHolder = new HorizontalCardListSectionViewHolder((FragmentActivity) this.c, viewGroup, this.f, true, this);
                return horizontalCardListSectionViewHolder;
            case R.id.view_type_image_banner /*2131363416*/:
                return new ImageBannerViewHolder(viewGroup, d());
            case R.id.view_type_left_aligned_all_caps_header /*2131363417*/:
                return new LeftAlignedAllCapsHeaderViewHolder(viewGroup);
            case R.id.view_type_listing_card /*2131363418*/:
                if (a2) {
                    ListingCardMiniViewHolder listingCardMiniViewHolder = new ListingCardMiniViewHolder(viewGroup, (com.etsy.android.ui.cardview.clickhandlers.f) this.e.get(i2), this.d, a2, b());
                    return listingCardMiniViewHolder;
                }
                ListingCardViewHolder listingCardViewHolder = new ListingCardViewHolder(viewGroup, (com.etsy.android.ui.cardview.clickhandlers.f) this.e.get(i2), this.d, a2, b());
                return listingCardViewHolder;
            case R.id.view_type_listing_collection /*2131363421*/:
                return new ListingCollectionViewHolder(viewGroup, (g) this.e.get(i2), this.d);
            case R.id.view_type_loading /*2131363422*/:
                return new LoadingCardViewHolder(viewGroup);
            case R.id.view_type_nested_list_section /*2131363454*/:
                return new NestedListSectionViewHolder((FragmentActivity) this.c, viewGroup, this.f, this);
            case R.id.view_type_saved_cart_listing_card /*2131363458*/:
                return new SavedCartListingCardViewHolder2(viewGroup, (SavedCartClickHandler) this.e.get(i2), this.d);
            case R.id.view_type_search_bar /*2131363459*/:
                return new SearchBarViewHolder(viewGroup);
            case R.id.view_type_section_link_footer /*2131363462*/:
                return new ListSectionLinkFooterViewHolder(viewGroup, (com.etsy.android.vespa.b) this.e.get(i2), a2);
            case R.id.view_type_shop_card /*2131363463*/:
                return new ShopCardViewHolder(viewGroup, (m) this.e.get(i2), this.d, a2);
            case R.id.view_type_shop_share_homescreen_card /*2131363507*/:
                ShopShareCardViewHolder shopShareCardViewHolder = new ShopShareCardViewHolder(viewGroup, (n) this.e.get(i2), this.d, R.layout.list_item_card_view_shop_share_homescreen, null);
                return shopShareCardViewHolder.setShowMenuIcon(false);
            case R.id.view_type_shop_share_landing_page_card /*2131363508*/:
                ShopShareCardViewHolder shopShareCardViewHolder2 = new ShopShareCardViewHolder(viewGroup, (n) this.e.get(i2), this.d, R.layout.list_item_card_view_shop_share_landing_page, (com.etsy.android.ui.cardview.clickhandlers.f) this.e.get(R.id.view_type_listing_card));
                return shopShareCardViewHolder2;
            case R.id.view_type_sizeable_text /*2131363510*/:
                return new SizeableTextViewHolder(viewGroup);
            case R.id.view_type_taxonomy_category /*2131363516*/:
                return new TaxonomyCategoryRowViewHolder(viewGroup, false, (com.etsy.android.vespa.b) this.e.get(i2), this.d);
            case R.id.view_type_wide_shop_card /*2131363520*/:
                return new WideShopCardViewHolder(viewGroup, (m) b(i2), this.d);
            default:
                return null;
        }
    }

    public int a(int i2, int i3) {
        Resources resources = ((FragmentActivity) this.c).getResources();
        int integer = resources.getInteger(R.integer.vespa_grid_layout_max_span);
        switch (i2) {
            case R.id.view_type_button /*2131363384*/:
                return resources.getInteger(R.integer.vespa_grid_item_half_span);
            case R.id.view_type_category_card /*2131363385*/:
                return resources.getInteger(R.integer.vespa_category_card_item_span);
            case R.id.view_type_finds_card /*2131363398*/:
                return resources.getInteger(R.integer.vespa_finds_card_span);
            case R.id.view_type_finds_card_small /*2131363399*/:
                return resources.getInteger(R.integer.vespa_finds_card_small_span);
            case R.id.view_type_listing_card /*2131363418*/:
                return resources.getInteger(R.integer.vespa_listing_card_span);
            case R.id.view_type_shop_card /*2131363463*/:
                return resources.getInteger(R.integer.vespa_shop_card_span);
            case R.id.view_type_shop_share_homescreen_card /*2131363507*/:
                return resources.getInteger(R.integer.vespa_share_card_span);
            default:
                return integer;
        }
    }

    public void a(boolean z) {
        this.i = z;
    }

    public boolean b() {
        return this.i;
    }

    public int a(int i2) {
        if (i2 == R.id.view_type_finds_card || i2 == R.id.view_type_listing_card || i2 == R.id.view_type_shop_card || i2 == R.id.view_type_taxonomy_category) {
            return -2;
        }
        return ((FragmentActivity) this.c).getResources().getDimensionPixelSize(R.dimen.horizontal_section_default_item_height);
    }
}
