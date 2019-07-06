package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.merchantprofile.MerchantProfileActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingBadge;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;

public class MerchantInfoView extends LinearLayout implements ImageRestorable, Recyclable {
    private View mBadgeContainer;
    private NetworkImageView mBadgeImage;
    private TextView mBadgeSubtitle;
    private TextView mBadgeTitle;
    private LinearLayout mMerchantInfoDetailsContainer;
    private View mMerchantInfoDetialsDivider;
    private NetworkImageView mMerchantInfoImage;
    private TextView mMerchantInfoSubtitle;
    private TextView mMerchantInfoTitle;
    private View mMerchantInfoVisitStoreTextButton;
    private View mRootLayout;

    public void recycle() {
    }

    public MerchantInfoView(Context context) {
        this(context, null);
    }

    public MerchantInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MerchantInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void init() {
        this.mRootLayout = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.merchant_info_view, this);
        setOrientation(1);
        setGravity(17);
        this.mMerchantInfoImage = (NetworkImageView) this.mRootLayout.findViewById(R.id.merchant_info_merchant_image);
        this.mMerchantInfoTitle = (TextView) this.mRootLayout.findViewById(R.id.merchant_info_section_title);
        this.mMerchantInfoSubtitle = (TextView) this.mRootLayout.findViewById(R.id.merchant_info_section_subtitle);
        this.mMerchantInfoDetailsContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.merchant_info_details_container);
        this.mBadgeContainer = this.mRootLayout.findViewById(R.id.merchant_info_badge_section_container);
        this.mBadgeTitle = (TextView) this.mRootLayout.findViewById(R.id.merchant_info_badge_section_title);
        this.mBadgeSubtitle = (TextView) this.mRootLayout.findViewById(R.id.merchant_info_badge_section_subtitle);
        this.mBadgeImage = (NetworkImageView) this.mRootLayout.findViewById(R.id.merchant_info_badge_section_badge_img);
        this.mMerchantInfoDetialsDivider = this.mRootLayout.findViewById(R.id.merchant_info_details_divider);
        this.mMerchantInfoVisitStoreTextButton = this.mRootLayout.findViewById(R.id.merchant_info_visit_store);
    }

    public void setup(final ProductDetailsFragment productDetailsFragment, final WishProduct wishProduct) {
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                MerchantInfoView.this.visitStore(productDetailsFragment, wishProduct);
            }
        };
        this.mMerchantInfoVisitStoreTextButton.setOnClickListener(r0);
        this.mMerchantInfoImage.setOnClickListener(r0);
        this.mMerchantInfoTitle.setOnClickListener(r0);
        this.mMerchantInfoSubtitle.setOnClickListener(r0);
        this.mMerchantInfoTitle.setText(wishProduct.getMerchantInfoTitle());
        this.mMerchantInfoSubtitle.setText(wishProduct.getMerchantInfoSubtitle());
        this.mMerchantInfoSubtitle.setVisibility(!TextUtils.isEmpty(wishProduct.getMerchantInfoSubtitle()) ? 0 : 8);
        this.mMerchantInfoImage.setImage(new WishImage(wishProduct.getMerchantInfoImageUrl()));
        this.mMerchantInfoImage.setCircleCrop(true);
        ArrayList merchantInfoDetailTitles = wishProduct.getMerchantInfoDetailTitles();
        ArrayList merchantInfoDetailBodies = wishProduct.getMerchantInfoDetailBodies();
        if (!(merchantInfoDetailTitles == null || merchantInfoDetailBodies == null)) {
            for (int i = 0; i < merchantInfoDetailTitles.size(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append((String) merchantInfoDetailTitles.get(i));
                sb.append(" ");
                sb.append((String) merchantInfoDetailBodies.get(i));
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb.toString());
                spannableStringBuilder.setSpan(new StyleSpan(1), 0, ((String) merchantInfoDetailTitles.get(i)).length(), 33);
                ThemedTextView themedTextView = new ThemedTextView(getContext());
                if (i < merchantInfoDetailTitles.size()) {
                    themedTextView.setPadding(0, 0, 0, 8);
                }
                themedTextView.setText(spannableStringBuilder);
                themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray1));
                themedTextView.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_body));
                this.mMerchantInfoDetailsContainer.addView(themedTextView);
            }
        }
        if (wishProduct.getBadge() != null) {
            WishShippingBadge badge = wishProduct.getBadge();
            this.mBadgeContainer.setVisibility(0);
            this.mBadgeTitle.setText(badge.getTitle());
            this.mBadgeTitle.setTextColor(Color.parseColor(badge.getColorString()));
            this.mBadgeSubtitle.setText(badge.getSubtitle());
            int parseColor = Color.parseColor(badge.getColorString());
            this.mBadgeSubtitle.setTextColor(parseColor);
            this.mBadgeContainer.setBackgroundColor(ColorUtils.setAlphaComponent(parseColor, 13));
            this.mBadgeImage.setImage(new WishImage(badge.getImageUrl()));
        }
        if ((merchantInfoDetailTitles == null || merchantInfoDetailBodies == null) && wishProduct.getBadge() == null) {
            this.mMerchantInfoDetialsDivider.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void visitStore(ProductDetailsFragment productDetailsFragment, final WishProduct wishProduct) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RATINGS_VISIT_STORE);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SELLER_INFO_VISIT_STORE);
        productDetailsFragment.withActivity(new ActivityTask<ProductDetailsActivity>() {
            public void performTask(ProductDetailsActivity productDetailsActivity) {
                Intent intent = new Intent();
                intent.setClass(productDetailsActivity, MerchantProfileActivity.class);
                intent.putExtra(MerchantProfileActivity.EXTRA_MERCHANT, wishProduct.getMerchantName());
                intent.putExtra(MerchantProfileActivity.EXTRA_MERCHANT_ID, wishProduct.getMerchantId());
                productDetailsActivity.startActivity(intent);
            }
        });
    }

    public void releaseImages() {
        if (this.mMerchantInfoImage != null) {
            this.mMerchantInfoImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mMerchantInfoImage != null) {
            this.mMerchantInfoImage.restoreImages();
        }
    }
}
