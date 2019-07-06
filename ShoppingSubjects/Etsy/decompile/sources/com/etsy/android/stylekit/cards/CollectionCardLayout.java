package com.etsy.android.stylekit.cards;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.etsy.android.stylekit.a.e;
import com.etsy.android.stylekit.a.f;
import com.etsy.android.stylekit.a.g;
import com.etsy.android.stylekit.a.j;
import com.etsy.android.stylekit.views.AspectRatioImageView;
import com.etsy.android.stylekit.views.AspectRatioImageView.AspectRatio;
import java.util.ArrayList;
import java.util.List;

public class CollectionCardLayout extends CardView {
    private boolean didLoadDefaultLayout = false;
    private TextView mBadge;
    protected FrameLayout mContentContainer;
    private List<AspectRatioImageView> mGridChildren = new ArrayList();
    private GridLayout mImageGrid;

    public CollectionCardLayout(Context context) {
        super(context);
        init(null);
    }

    public CollectionCardLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public CollectionCardLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(@Nullable AttributeSet attributeSet) {
        setForeground(AppCompatResources.getDrawable(getContext(), e.sk_touch_selector));
        View inflate = inflate(getContext(), g.customview_collection_card, this);
        this.mImageGrid = (GridLayout) findViewById(f.collection_image_grid);
        this.mContentContainer = (FrameLayout) findViewById(f.collection_content);
        this.mBadge = (TextView) inflate.findViewById(f.collection_badge);
        int i = 8;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.sk_CollectionCardLayout);
            setImageGridSize(obtainStyledAttributes.getInt(j.sk_CollectionCardLayout_sk_imageGridColCount, 2), obtainStyledAttributes.getInt(j.sk_CollectionCardLayout_sk_imageGridRowCount, 2));
            if (obtainStyledAttributes.hasValue(j.sk_CollectionCardLayout_sk_contentLayout)) {
                int resourceId = obtainStyledAttributes.getResourceId(j.sk_CollectionCardLayout_sk_contentLayout, -1);
                if (resourceId != -1) {
                    LayoutInflater.from(getContext()).inflate(resourceId, this.mContentContainer, true);
                }
            }
            TextView textView = this.mBadge;
            if (obtainStyledAttributes.getBoolean(j.sk_CollectionCardLayout_sk_showBadge, false)) {
                i = 0;
            }
            textView.setVisibility(i);
            obtainStyledAttributes.recycle();
        } else {
            setImageGridSize(2, 2);
            this.mBadge.setVisibility(8);
        }
        this.didLoadDefaultLayout = true;
        if (isInEditMode()) {
            setImageGridSize(2, 2);
            setImageResources(new int[]{e.listing_image_example, e.listing_image_example_2, e.listing_image_example_3, e.listing_image_example_4});
            setBadgeText("24 items");
        }
    }

    public View setContent(@LayoutRes int i) {
        return setContent(LayoutInflater.from(getContext()).inflate(i, this.mContentContainer, false));
    }

    public View setContent(@NonNull View view) {
        this.mContentContainer.removeAllViews();
        addView(view);
        return view;
    }

    public View getContent() {
        return this.mContentContainer;
    }

    public void addView(View view) {
        if (this.didLoadDefaultLayout) {
            this.mContentContainer.addView(view);
        } else {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (this.didLoadDefaultLayout) {
            this.mContentContainer.addView(view, i);
        } else {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (this.didLoadDefaultLayout) {
            this.mContentContainer.addView(view, i, i2);
        } else {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (this.didLoadDefaultLayout) {
            this.mContentContainer.addView(view, layoutParams);
        } else {
            super.addView(view, layoutParams);
        }
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (this.didLoadDefaultLayout) {
            this.mContentContainer.addView(view, i, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
    }

    public void setImageBackgroundColors(int[] iArr) {
        int min = Math.min(iArr.length, this.mGridChildren.size());
        for (int i = 0; i < min; i++) {
            ((AspectRatioImageView) this.mGridChildren.get(i)).setBackgroundColor(iArr[i]);
        }
    }

    public void setImageResources(int[] iArr) {
        int min = Math.min(iArr.length, this.mGridChildren.size());
        for (int i = 0; i < min; i++) {
            ((AspectRatioImageView) this.mGridChildren.get(i)).setImageDrawable(AppCompatResources.getDrawable(getContext(), iArr[i]));
        }
    }

    public void loadImageUrls(String[] strArr) {
        int min = Math.min(strArr.length, this.mGridChildren.size());
        for (int i = 0; i < min; i++) {
            Glide.b(getContext()).a(strArr[i]).a((ImageView) this.mGridChildren.get(i));
        }
    }

    public void loadImageUrlsWithPlaceholders(String[] strArr, int[] iArr) {
        int min = Math.min(strArr.length, this.mGridChildren.size());
        for (int i = 0; i < min; i++) {
            Glide.b(getContext()).a(strArr[i]).b(iArr[i]).a((ImageView) this.mGridChildren.get(i));
        }
    }

    public void setBadgeText(CharSequence charSequence) {
        this.mBadge.setText(charSequence);
        this.mBadge.setVisibility(0);
    }

    public void setImageGridSize(int i, int i2) {
        if (this.mImageGrid.getColumnCount() != i || this.mImageGrid.getRowCount() != i2) {
            this.mGridChildren.clear();
            this.mImageGrid.removeAllViews();
            this.mImageGrid.setColumnCount(i);
            this.mImageGrid.setRowCount(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    AspectRatioImageView aspectRatioImageView = new AspectRatioImageView(this.mImageGrid.getContext());
                    aspectRatioImageView.setHeightAspectRatio(AspectRatio.STANDARD_4_3);
                    aspectRatioImageView.setScaleType(ScaleType.CENTER_CROP);
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.width = 0;
                    layoutParams.height = -2;
                    layoutParams.rowSpec = GridLayout.spec(i3);
                    layoutParams.columnSpec = GridLayout.spec(i4, 1.0f);
                    this.mImageGrid.addView(aspectRatioImageView, layoutParams);
                    this.mGridChildren.add(aspectRatioImageView);
                }
            }
        }
    }

    public Pair<Integer, Integer> getImageGridSize() {
        return new Pair<>(Integer.valueOf(this.mImageGrid.getRowCount()), Integer.valueOf(this.mImageGrid.getColumnCount()));
    }
}
