package com.etsy.android.ui.giftcards;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class GiftCardDesignSelecterView extends LinearLayout implements OnPageChangeListener {
    private TrackingOnClickListener mDesignOnClickListener = new TrackingOnClickListener() {
        public void onViewClick(View view) {
            Object tag = view.getTag();
            if (tag != null) {
                GiftCardDesignSelecterView.this.mViewPagerGiftCardDesigns.setCurrentItem(((Integer) tag).intValue());
            }
        }
    };
    private GiftCardDesignPagerAdapter mGiftCardDesignAdapter;
    private c mImageBatch;
    /* access modifiers changed from: private */
    public ViewPager mViewPagerGiftCardDesigns;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public GiftCardDesignSelecterView(Context context) {
        super(context);
        initViews(context);
    }

    public GiftCardDesignSelecterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context);
    }

    public GiftCardDesignSelecterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews(context);
    }

    private void initViews(Context context) {
        this.mImageBatch = new c();
        inflate(context, R.layout.view_gift_card_design_selector, this);
        this.mViewPagerGiftCardDesigns = (ViewPager) findViewById(R.id.view_pager_card_designs);
    }

    public void setGiftCardDesigns(List<GiftCardDesign> list) {
        this.mGiftCardDesignAdapter = new GiftCardDesignPagerAdapter(this.mImageBatch, this.mDesignOnClickListener);
        this.mGiftCardDesignAdapter.addItems(list);
        this.mViewPagerGiftCardDesigns.addOnPageChangeListener(this);
        this.mViewPagerGiftCardDesigns.setAdapter(this.mGiftCardDesignAdapter);
        for (int i = 0; i < list.size(); i++) {
            if (((GiftCardDesign) list.get(i)).getId() == 4) {
                this.mViewPagerGiftCardDesigns.setCurrentItem(i);
            }
        }
    }

    public int getSelectedGiftCardId() {
        return this.mGiftCardDesignAdapter.getItem(this.mViewPagerGiftCardDesigns.getCurrentItem()).getId();
    }

    public void onPageSelected(int i) {
        this.mGiftCardDesignAdapter.getItem(this.mViewPagerGiftCardDesigns.getCurrentItem());
    }
}
