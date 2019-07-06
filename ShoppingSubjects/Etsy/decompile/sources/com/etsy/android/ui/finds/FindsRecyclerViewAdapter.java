package com.etsy.android.ui.finds;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.BannerImage;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.models.cardviewelement.BasicSectionHeader;
import com.etsy.android.lib.models.cardviewelement.FindsHeroBanner;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.models.finds.FindsPage;
import com.etsy.android.lib.util.b.a;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.k;
import java.util.ArrayList;
import java.util.List;

public class FindsRecyclerViewAdapter extends BaseViewHolderFactoryRecyclerViewAdapter {
    private static final String SAVE_SIBLING_COUNTS = "SAVE_SIBLING_COUNTS";
    @Nullable
    protected ArrayList<Integer> mSiblingCounts;

    public FindsRecyclerViewAdapter(FragmentActivity fragmentActivity, String str, @NonNull b bVar) {
        super(fragmentActivity, bVar);
    }

    /* access modifiers changed from: protected */
    public c createViewHolderFactory(@Nullable a aVar) {
        return new a((FragmentActivity) this.mContext, this, this.mViewTracker);
    }

    public void setFindsPage(FindsPage findsPage) {
        boolean z;
        this.mSiblingCounts = new ArrayList<>();
        BannerImage bannerImage = findsPage.getBannerImage();
        if (bannerImage != null) {
            FindsHeroBanner findsHeroBanner = new FindsHeroBanner();
            findsHeroBanner.setBannerImage(bannerImage);
            findsHeroBanner.setTitle(findsPage.getTitle());
            findsHeroBanner.setSubtitle(findsPage.getSubtitle());
            findsHeroBanner.setViewType(R.id.view_type_finds_hero_banner_tall);
            addItem(findsHeroBanner);
            this.mSiblingCounts.add(Integer.valueOf(0));
            z = false;
        } else {
            z = true;
        }
        List heroListings = findsPage.getHeroListings();
        if (heroListings != null && !heroListings.isEmpty()) {
            ListSection listSection = new ListSection();
            int size = heroListings.size();
            listSection.setItems(heroListings);
            listSection.setHorizontal(false);
            if (z) {
                listSection.setHeader(new BasicSectionHeader(findsPage.getTitle(), findsPage.getSubtitle()));
                this.mSiblingCounts.add(Integer.valueOf(0));
            }
            addListSection(listSection);
            for (int i = 0; i < heroListings.size(); i++) {
                ((ListingCard) heroListings.get(i)).setViewType(R.id.view_type_finds_hero_listing);
                this.mSiblingCounts.add(Integer.valueOf(size));
            }
        }
        for (FindsModule cardViewElements : findsPage.getModules()) {
            List<k> cardViewElements2 = cardViewElements.getCardViewElements();
            int size2 = cardViewElements2.size();
            for (k kVar : cardViewElements2) {
                this.mSiblingCounts.add(Integer.valueOf(size2));
                addItem(kVar);
            }
        }
        notifyDataSetChanged();
    }

    public int getSiblingCountForPosition(int i) {
        if (this.mSiblingCounts == null || i >= this.mSiblingCounts.size()) {
            return 0;
        }
        return ((Integer) this.mSiblingCounts.get(i)).intValue();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putIntegerArrayList(SAVE_SIBLING_COUNTS, this.mSiblingCounts);
        super.onSaveInstanceState(bundle);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mSiblingCounts = bundle.getIntegerArrayList(SAVE_SIBLING_COUNTS);
        }
        super.onRestoreInstanceState(bundle);
    }
}
