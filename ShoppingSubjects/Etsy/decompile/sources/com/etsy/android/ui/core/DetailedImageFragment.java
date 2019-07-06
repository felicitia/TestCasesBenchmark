package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.adapter.FullImagesPagerAdapter;
import com.etsy.android.uikit.adapter.ImagesPagerAdapter.b;
import com.etsy.android.uikit.adapter.ListingThumbnailAdapter;
import com.etsy.android.uikit.view.CustomViewPageIndicator;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.Collection;

public class DetailedImageFragment extends EtsyFragment implements a, b {
    com.etsy.android.lib.util.b.a fileSupport;
    /* access modifiers changed from: private */
    public FullImagesPagerAdapter mAdapter;
    private ArrayList<BaseModelImage> mImageArray;
    private int mInitialPosition;
    private final SimpleOnPageChangeListener mListener = new SimpleOnPageChangeListener() {
        public void onPageScrolled(int i, float f, int i2) {
            super.onPageScrolled(i, f, i2);
            if (f == 0.0f) {
                DetailedImageFragment.this.mAdapter.resetZoomAtPosition(i - 1);
                DetailedImageFragment.this.mAdapter.resetZoomAtPosition(i + 1);
            }
        }
    };
    private CirclePageIndicator mPagerIndicator;
    private boolean mShowThumbnails;
    private ListingThumbnailAdapter mThumbnailAdapter;
    /* access modifiers changed from: private */
    public CustomViewPageIndicator mThumbnailIndicator;
    private ViewPager mViewPager;

    @NonNull
    public String getTrackingName() {
        return "image_zoom";
    }

    public void onImageClick(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mImageArray = new ArrayList<>((ArrayList) arguments.getSerializable("image_list"));
        this.mInitialPosition = arguments.getInt("position");
        this.mShowThumbnails = arguments.getBoolean("SHOW_THUMBNAILS");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_detailed_image, viewGroup, false);
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        this.mViewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.margin_large));
        this.mPagerIndicator = (CirclePageIndicator) inflate.findViewById(R.id.circle_page_indicator);
        this.mThumbnailIndicator = (CustomViewPageIndicator) inflate.findViewById(R.id.custom_page_indicator);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mImageArray == null) {
            this.mActivity.finish();
        }
        if (this.mAdapter == null) {
            this.mAdapter = createAdapter();
        }
        l lVar = new l(this.mActivity);
        this.mAdapter.setImageSizes(lVar.d(), lVar.e());
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setCurrentItem(this.mInitialPosition);
        boolean z = true;
        if (this.mImageArray.size() <= 1) {
            z = false;
        }
        if (!z) {
            this.mPagerIndicator.setVisibility(8);
            this.mThumbnailIndicator.setVisibility(8);
        } else if (this.mShowThumbnails) {
            this.mThumbnailIndicator.setOnPageChangeListener(this.mListener);
            this.mThumbnailIndicator.setViewPager(this.mViewPager);
            if (this.mThumbnailAdapter == null) {
                this.mThumbnailAdapter = new ListingThumbnailAdapter(this.mActivity, getImageBatch());
                this.mThumbnailAdapter.addAll((Collection<? extends T>) this.mImageArray);
            }
            this.mThumbnailIndicator.setAdapter(this.mThumbnailAdapter);
            this.mThumbnailIndicator.setIndicatorClickListener(new CustomViewPageIndicator.b() {
                public void a(int i) {
                    DetailedImageFragment.this.mThumbnailIndicator.setCurrentItem(i);
                }
            });
            this.mThumbnailIndicator.setVisibility(0);
            this.mPagerIndicator.setVisibility(8);
        } else {
            this.mPagerIndicator.setOnPageChangeListener(this.mListener);
            this.mPagerIndicator.setViewPager(this.mViewPager);
            this.mPagerIndicator.setVisibility(0);
            this.mThumbnailIndicator.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public FullImagesPagerAdapter createAdapter() {
        FullImagesPagerAdapter fullImagesPagerAdapter = new FullImagesPagerAdapter(this.mActivity, getImageBatch(), getAnalyticsContext(), this, this.fileSupport);
        fullImagesPagerAdapter.setImages(this.mImageArray);
        return fullImagesPagerAdapter;
    }

    /* access modifiers changed from: protected */
    public void addImage(int i, BaseModelImage baseModelImage) {
        if (this.mImageArray == null) {
            this.mImageArray = new ArrayList<>();
        }
        this.mImageArray.add(i, baseModelImage);
    }
}
