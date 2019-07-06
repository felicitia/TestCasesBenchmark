package com.etsy.android.ui.giftcards;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.GiftCardDesign;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.ArrayList;
import java.util.List;

public class GiftCardDesignPagerAdapter extends PagerAdapter {
    TrackingOnClickListener mDesignOnClickListener;
    c mImageBatch;
    ArrayList<GiftCardDesign> mItems = new ArrayList<>();

    public float getPageWidth(int i) {
        return 0.94f;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public GiftCardDesignPagerAdapter(c cVar, TrackingOnClickListener trackingOnClickListener) {
        this.mImageBatch = cVar;
        this.mDesignOnClickListener = trackingOnClickListener;
    }

    public void addItems(List<GiftCardDesign> list) {
        this.mItems.addAll(list);
    }

    public int getCount() {
        return this.mItems.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ImageView imageView = (ImageView) View.inflate(viewGroup.getContext(), R.layout.view_gift_card_design, null);
        viewGroup.addView(imageView);
        imageView.setTag(Integer.valueOf(i));
        imageView.setOnClickListener(this.mDesignOnClickListener);
        this.mImageBatch.a(((GiftCardDesign) this.mItems.get(i)).getUrlBanner(), imageView);
        return imageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public GiftCardDesign getItem(int i) {
        return (GiftCardDesign) this.mItems.get(i);
    }
}
