package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.CardSpec;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.LargeHeaderSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ContainerRestorable;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.viewpager.SafeViewPager;
import com.contextlogic.wish.ui.viewpager.ViewPagerIndicator;
import java.util.ArrayList;

public class GiftPackFeedHeaderView extends LinearLayout implements ImageRestorable {
    /* access modifiers changed from: private */
    public TextView mTitleText;
    /* access modifiers changed from: private */
    public SafeViewPager mViewPager;
    private ViewPagerIndicator mViewPagerIndicator;

    public GiftPackFeedHeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.gift_details_banner_view, this);
        this.mTitleText = (TextView) inflate.findViewById(R.id.gift_pack_details_banner_title);
        this.mViewPager = (SafeViewPager) inflate.findViewById(R.id.gift_pack_details_banner_pager);
        this.mViewPagerIndicator = (ViewPagerIndicator) inflate.findViewById(R.id.gift_pack_details_banner_pager_indicator);
    }

    public void setup(LargeHeaderSpec largeHeaderSpec, BaseFragment baseFragment) {
        final ArrayList cardSpecs = largeHeaderSpec.getCardSpecs();
        WishTextViewSpec.applyTextViewSpec(this.mTitleText, ((CardSpec) cardSpecs.get(0)).getCardTitleTextSpec());
        final GiftPackPagerAdapter giftPackPagerAdapter = new GiftPackPagerAdapter(baseFragment, cardSpecs);
        this.mViewPager.setAdapter(giftPackPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (((CardSpec) cardSpecs.get(i)).getCardTitleTextSpec() != null && !TextUtils.equals(GiftPackFeedHeaderView.this.mTitleText.getText(), ((CardSpec) cardSpecs.get(i)).getCardTitleTextSpec().getText())) {
                    GiftPackFeedHeaderView.this.mTitleText.setText(((CardSpec) cardSpecs.get(i)).getCardTitleTextSpec().getText());
                }
                GiftPackFeedHeaderView.this.logEventForPosition(giftPackPagerAdapter, i);
            }
        });
        final int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.new_user_gift_pack_header_pager_padding);
        this.mViewPager.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        this.mViewPager.setPageTransformer(false, new PageTransformer() {
            public void transformPage(View view, float f) {
                if (GiftPackFeedHeaderView.this.mViewPager.getCurrentItem() == 0) {
                    view.setTranslationX((float) ((-dimensionPixelOffset) / 2));
                } else if (GiftPackFeedHeaderView.this.mViewPager.getCurrentItem() == giftPackPagerAdapter.getCount() - 1) {
                    view.setTranslationX((float) (dimensionPixelOffset / 2));
                } else {
                    view.setTranslationX(0.0f);
                }
            }
        });
        this.mViewPager.setClipToPadding(false);
        if (largeHeaderSpec.getCurrentIndex() == 0) {
            logEventForPosition(giftPackPagerAdapter, 0);
        } else {
            int currentIndex = largeHeaderSpec.getCurrentIndex();
            if (((CardSpec) cardSpecs.get(largeHeaderSpec.getCurrentIndex())).isDailyLoginCard()) {
                currentIndex++;
            }
            this.mViewPager.setCurrentItem(currentIndex, true);
        }
        this.mViewPagerIndicator.setup(this.mViewPager, ContextCompat.getColor(getContext(), R.color.gray2));
    }

    public void releaseImages() {
        ContainerRestorable.releaseChildren(this.mViewPager);
    }

    public void restoreImages() {
        ContainerRestorable.restoreChildren(this.mViewPager);
    }

    /* access modifiers changed from: private */
    public void logEventForPosition(GiftPackPagerAdapter giftPackPagerAdapter, int i) {
        CardSpec item = giftPackPagerAdapter.getItem(i);
        if (item != null && item.getImpressionEventId() != -1) {
            WishAnalyticsLogger.trackEvent(item.getImpressionEventId());
        }
    }

    public static int getTotalHeight() {
        Resources resources = WishApplication.getInstance().getResources();
        return resources.getDimensionPixelSize(R.dimen.new_user_gift_pack_header_pager_height) + resources.getDimensionPixelSize(R.dimen.eight_padding) + resources.getDimensionPixelSize(R.dimen.sixteen_padding);
    }
}
