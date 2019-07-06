package com.contextlogic.wish.activity.pricechop;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.PriceChopStatus;
import com.contextlogic.wish.api.model.PriceChopStatuses;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ProfileImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriceChopStatusBarView extends FrameLayout {
    private List<View> mProfileImages;
    private ProgressBar mProgressBar;
    private TextView mStartingPriceText;
    private TextView mToolTip;

    public PriceChopStatusBarView(Context context) {
        super(context);
        init();
    }

    public PriceChopStatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PriceChopStatusBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.price_chop_status_bar, this, true);
        this.mProgressBar = (ProgressBar) findViewById(R.id.price_chop_progress_bar);
        this.mToolTip = (TextView) findViewById(R.id.price_chop_tool_tip);
        this.mProfileImages = new ArrayList();
        setupStartingPriceTextView();
    }

    private void setupStartingPriceTextView() {
        this.mStartingPriceText = createPriceChopTextView(false);
        LayoutParams layoutParams = (LayoutParams) this.mStartingPriceText.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 80;
        layoutParams2.setMargins(0, layoutParams.topMargin, 0, layoutParams.bottomMargin);
        this.mStartingPriceText.setLayoutParams(layoutParams2);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.sixteen_padding);
        this.mStartingPriceText.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        addView(this.mStartingPriceText);
    }

    private void clearProfileImages() {
        for (View removeView : this.mProfileImages) {
            removeView(removeView);
        }
        this.mProfileImages.clear();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = ((MarginLayoutParams) this.mProgressBar.getLayoutParams()).leftMargin;
        int measuredWidth = this.mProgressBar.getMeasuredWidth();
        int i6 = 0;
        while (i6 < this.mProfileImages.size()) {
            View view = (View) this.mProfileImages.get(i6);
            i6++;
            int size = (((measuredWidth / this.mProfileImages.size()) * i6) - (view.getMeasuredWidth() / 2)) + i5;
            view.layout(size, view.getTop(), view.getMeasuredWidth() + size, view.getBottom());
        }
    }

    /* access modifiers changed from: private */
    public void onProfileImageClicked(PriceChopStatus priceChopStatus, View view) {
        if (hasUserProfile(priceChopStatus)) {
            this.mToolTip.setText(priceChopStatus.getPrice().toFormattedString());
            this.mToolTip.measure(0, 0);
            this.mToolTip.setVisibility(0);
            this.mToolTip.setX((view.getX() + ((float) (view.getMeasuredWidth() / 2))) - ((float) (this.mToolTip.getMeasuredWidth() / 2)));
        }
    }

    private ThemedTextView createPriceChopTextView(boolean z) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_twelve));
        themedTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.gray4));
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.four_padding);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.four_padding);
        int dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen.eight_padding);
        if (z) {
            dimensionPixelOffset += dimensionPixelOffset3;
        } else {
            dimensionPixelOffset2 += dimensionPixelOffset3;
        }
        layoutParams.setMargins(0, dimensionPixelOffset, 0, dimensionPixelOffset2);
        themedTextView.setLayoutParams(layoutParams);
        return themedTextView;
    }

    private void addProfileImages(List<PriceChopStatus> list) {
        int i = 0;
        while (i < list.size()) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            final PriceChopStatus priceChopStatus = (PriceChopStatus) list.get(i);
            ThemedTextView createPriceChopTextView = createPriceChopTextView(true);
            ThemedTextView createPriceChopTextView2 = createPriceChopTextView(false);
            if (i == list.size() - 1) {
                createPriceChopTextView2.setText(priceChopStatus.getPrice().toFormattedString());
            } else {
                createPriceChopTextView.setText(priceChopStatus.getPrice().toFormattedString());
            }
            boolean z = i == list.size() - 1 || (!hasUserProfile(priceChopStatus) && (i == 0 || hasUserProfile((PriceChopStatus) list.get(i + -1))));
            int i2 = 4;
            createPriceChopTextView.setVisibility(z ? 0 : 4);
            if (z) {
                i2 = 0;
            }
            createPriceChopTextView2.setVisibility(i2);
            ProfileImageView profileImageView = new ProfileImageView(getContext(), getResources().getDimensionPixelSize(R.dimen.price_chop_status_bar_profile_size), getResources().getDimensionPixelSize(R.dimen.text_size_twelve));
            WishImage wishImage = priceChopStatus.getUserProfilePictureUrl() != null ? new WishImage(priceChopStatus.getUserProfilePictureUrl()) : null;
            profileImageView.setAnotherUserImage(R.drawable.price_chop_profile);
            profileImageView.setup(wishImage, priceChopStatus.getUserName(), true ^ hasUserProfile(priceChopStatus));
            profileImageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setBackgroundResource(R.drawable.white_circle);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.divider);
            frameLayout.setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            frameLayout.addView(profileImageView);
            linearLayout.addView(createPriceChopTextView);
            linearLayout.addView(frameLayout);
            linearLayout.addView(createPriceChopTextView2);
            linearLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PriceChopStatusBarView.this.onProfileImageClicked(priceChopStatus, view);
                }
            });
            addView(linearLayout);
            this.mProfileImages.add(linearLayout);
            i++;
        }
    }

    private static boolean hasUserProfile(PriceChopStatus priceChopStatus) {
        return (priceChopStatus.getUserName() == null && priceChopStatus.getUserProfilePictureUrl() == null) ? false : true;
    }

    private int calculateProgress(List<PriceChopStatus> list) {
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext() && hasUserProfile((PriceChopStatus) it.next())) {
            i++;
        }
        return (int) ((((float) i) / ((float) list.size())) * 100.0f);
    }

    public void setup(PriceChopStatuses priceChopStatuses) {
        clearProfileImages();
        addProfileImages(priceChopStatuses.getPriceChopStatuses());
        this.mProgressBar.setProgress(calculateProgress(priceChopStatuses.getPriceChopStatuses()));
        this.mStartingPriceText.setText(priceChopStatuses.getOriginalPrice().toFormattedString());
    }
}
