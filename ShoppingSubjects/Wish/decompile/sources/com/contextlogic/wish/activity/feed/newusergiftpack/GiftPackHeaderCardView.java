package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.CardSpec;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.http.ImageHttpManager;
import com.contextlogic.wish.http.ImageHttpManager.ImageRequest;
import com.contextlogic.wish.http.ImageHttpManager.ImageTarget;
import com.contextlogic.wish.http.ImageHttpManager.LoadingSource;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.image.NetworkImageView.NetworkImageViewCallback;
import com.contextlogic.wish.ui.view.WishCardView;
import com.contextlogic.wish.util.ColorUtil;

public class GiftPackHeaderCardView extends WishCardView implements ImageRestorable {
    private TextView mBodyText;
    private TextView mBottomText;
    /* access modifiers changed from: private */
    public NetworkImageView mSideImage;
    /* access modifiers changed from: private */
    public TextView mTitleText;

    /* access modifiers changed from: protected */
    public int getContentLayoutResourceId() {
        return R.layout.gift_pack_feed_header_card_view;
    }

    public GiftPackHeaderCardView(Context context) {
        this(context, null);
    }

    public GiftPackHeaderCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GiftPackHeaderCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void bindViews(View view) {
        this.mSideImage = (NetworkImageView) view.findViewById(R.id.gift_pack_feed_header_card_image);
        this.mTitleText = (TextView) view.findViewById(R.id.gift_pack_feed_header_card_title);
        this.mBodyText = (TextView) view.findViewById(R.id.gift_pack_feed_header_card_body);
        this.mBottomText = (TextView) view.findViewById(R.id.gift_pack_feed_header_card_bottom_text);
    }

    public void setup(CardSpec cardSpec, BaseFragment baseFragment) {
        WishTextViewSpec.applyTextViewSpec(this.mTitleText, cardSpec.getTitleTextSpec());
        WishTextViewSpec.applyTextViewSpec(this.mBodyText, cardSpec.getBodyTextSpec());
        if (cardSpec.getImage() != null) {
            setupSideImage(cardSpec.getImage());
        } else {
            hideSideImage();
        }
        if (!TextUtils.isEmpty(cardSpec.getLockImageUrl()) && this.mTitleText != null && this.mTitleText.getTextSize() > 0.0f) {
            setupLockDrawable(cardSpec.getLockImageUrl(), (int) this.mTitleText.getTextSize());
        }
        if (cardSpec.getActionTextSpec() != null && baseFragment != null) {
            WishTextViewSpec.applyTextViewSpec(this.mBottomText, cardSpec.getActionTextSpec());
            setActionClickListener(cardSpec, baseFragment);
            setupActionText();
        } else if (cardSpec.getCouponTextSpec() != null) {
            WishTextViewSpec.applyTextViewSpec(this.mBottomText, cardSpec.getCouponTextSpec());
            setupCouponText();
        } else {
            this.mBottomText.setVisibility(8);
        }
        int safeParseColor = ColorUtil.safeParseColor(cardSpec.getBorderColor(), 0);
        if (safeParseColor != 0) {
            setStrokeSize(getResources().getDimensionPixelSize(R.dimen.divider));
            setStrokeColor(safeParseColor);
        }
    }

    private void setupCouponText() {
        this.mBottomText.setPadding(getResources().getDimensionPixelSize(R.dimen.coupon_text_horizontal_padding), getResources().getDimensionPixelSize(R.dimen.four_padding), getResources().getDimensionPixelSize(R.dimen.coupon_text_horizontal_padding), getResources().getDimensionPixelSize(R.dimen.four_padding));
        this.mBottomText.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.coupon_background));
    }

    private void setupActionText() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.chevronlink_11x11);
        drawable.setColorFilter(this.mBottomText.getCurrentTextColor(), Mode.SRC_ATOP);
        this.mBottomText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        this.mBottomText.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
        this.mBottomText.setIncludeFontPadding(false);
    }

    private void setupSideImage(WishImage wishImage) {
        this.mSideImage.setImage(wishImage, (NetworkImageViewCallback) new NetworkImageViewCallback() {
            public void onImageLoaded() {
                LayoutParams layoutParams = GiftPackHeaderCardView.this.mSideImage.getLayoutParams();
                if (GiftPackHeaderCardView.this.mSideImage.getDrawable() != null) {
                    layoutParams.width = GiftPackHeaderCardView.this.mSideImage.getDrawable().getIntrinsicWidth();
                    layoutParams.height = GiftPackHeaderCardView.this.mSideImage.getDrawable().getIntrinsicHeight();
                }
                GiftPackHeaderCardView.this.mSideImage.setLayoutParams(layoutParams);
            }

            public void onImageLoadFailed() {
                GiftPackHeaderCardView.this.hideSideImage();
            }
        });
    }

    private void setupLockDrawable(String str, int i) {
        ImageRequest imageRequest = new ImageRequest(str);
        imageRequest.setSize(i, i);
        imageRequest.setImageTarget(new ImageTarget() {
            public void onError() {
            }

            public void onSuccess(Bitmap bitmap, LoadingSource loadingSource) {
                GiftPackHeaderCardView.this.mTitleText.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(GiftPackHeaderCardView.this.getResources(), bitmap), null, null, null);
                GiftPackHeaderCardView.this.mTitleText.setCompoundDrawablePadding(GiftPackHeaderCardView.this.getResources().getDimensionPixelSize(R.dimen.four_padding));
            }
        });
        ImageHttpManager.getInstance().request(imageRequest);
    }

    private void setActionClickListener(final CardSpec cardSpec, final BaseFragment baseFragment) {
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (cardSpec.getActionEventId() != -1) {
                    WishAnalyticsLogger.trackEvent(cardSpec.getActionEventId());
                }
                if (baseFragment instanceof ProductFeedFragment) {
                    GiftPackHeaderCardView.this.handleClickOnFeed((ProductFeedFragment) baseFragment, cardSpec);
                } else {
                    GiftPackHeaderCardView.this.processDeeplink(baseFragment, cardSpec);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleClickOnFeed(ProductFeedFragment productFeedFragment, CardSpec cardSpec) {
        switch (cardSpec.getActionType()) {
            case FILTER_ID:
                int findPositionFromFilterId = productFeedFragment.findPositionFromFilterId(cardSpec.getFilterId());
                if (findPositionFromFilterId >= 0) {
                    productFeedFragment.setSelectedTab(findPositionFromFilterId);
                    return;
                }
                return;
            case DEEP_LINK:
                processDeeplink(productFeedFragment, cardSpec);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void processDeeplink(BaseFragment baseFragment, final CardSpec cardSpec) {
        baseFragment.withActivity(new ActivityTask<BaseActivity>() {
            public void performTask(BaseActivity baseActivity) {
                String deepLink = cardSpec.getDeepLink();
                if (deepLink != null && !deepLink.isEmpty()) {
                    DeepLinkManager.processDeepLink(baseActivity, new DeepLink(deepLink));
                }
            }
        });
    }

    public void releaseImages() {
        if (this.mSideImage != null) {
            this.mSideImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mSideImage != null) {
            this.mSideImage.restoreImages();
        }
    }

    /* access modifiers changed from: private */
    public void hideSideImage() {
        if (this.mSideImage != null) {
            LayoutParams layoutParams = this.mSideImage.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(0, 0);
            } else {
                layoutParams.width = 0;
            }
            this.mSideImage.setLayoutParams(layoutParams);
            this.mSideImage.setVisibility(8);
        }
    }
}
