package com.contextlogic.wish.dialog.promotion;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPromotionRotatingSpec.BannerSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.dialog.promotion.rotating.PromoRotatingNotiDialogFragment;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.OnScrollListener;
import com.contextlogic.wish.util.ColorUtil;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.TabletUtil;

public class PromotionRotatingHeaderView extends BaseFeedHeaderView implements ImageRestorable {
    private TextView mActionText;
    private PromotionRotatingCardListAdapter mAdapter;
    private NetworkImageView mBackgroundImage;
    /* access modifiers changed from: private */
    public HorizontalListView mCardList;
    private TextView mExpiryText;
    private TextView mFeedTitle1;
    private TextView mFeedTitle2;
    private ViewGroup mFeedTitleNotiContainer;
    private TextView mFeedTitleNotiText;
    private BaseFragment mFragment;
    private TextView mTitleText;

    public PromotionRotatingHeaderView(BaseFragment baseFragment) {
        super(baseFragment.getContext());
        this.mFragment = baseFragment;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promotion_rotating_header_view, this);
        this.mBackgroundImage = (NetworkImageView) inflate.findViewById(R.id.promotion_rotating_header_bg);
        this.mExpiryText = (TextView) inflate.findViewById(R.id.promotion_rotating_header_expiry);
        this.mTitleText = (TextView) inflate.findViewById(R.id.promotion_rotating_header_title);
        this.mActionText = (TextView) inflate.findViewById(R.id.promotion_rotating_header_action_text);
        this.mCardList = (HorizontalListView) inflate.findViewById(R.id.promotion_rotating_header_card_list);
        this.mFeedTitle1 = (TextView) inflate.findViewById(R.id.promotion_rotating_header_feed_title_1);
        this.mFeedTitle2 = (TextView) inflate.findViewById(R.id.promotion_rotating_header_feed_title_2);
        this.mFeedTitleNotiContainer = (ViewGroup) inflate.findViewById(R.id.promotion_rotating_header_noti_container);
        this.mFeedTitleNotiText = (TextView) inflate.findViewById(R.id.promotion_rotating_header_feed_noti_text);
    }

    public void setup(BannerSpec bannerSpec, OnClickListener onClickListener) {
        if (bannerSpec == null || this.mFragment == null) {
            setVisibility(8);
            return;
        }
        if (bannerSpec.getExpiryTextSpec() != null) {
            this.mExpiryText.setVisibility(0);
            WishTextViewSpec.applyTextViewSpec(this.mExpiryText, bannerSpec.getExpiryTextSpec());
        }
        if (bannerSpec.getBackgroundImageUrl() != null && !bannerSpec.getBackgroundImageUrl().isEmpty()) {
            this.mBackgroundImage.setImageUrl(bannerSpec.getBackgroundImageUrl());
        }
        int safeParseColor = ColorUtil.safeParseColor(bannerSpec.getBackgroundColorString(), 0);
        if (safeParseColor != 0) {
            this.mBackgroundImage.setBackgroundColor(safeParseColor);
        }
        WishTextViewSpec.applyTextViewSpec(this.mTitleText, bannerSpec.getTitleTextSpec());
        if (!(bannerSpec.getActionTextSpec() == null || onClickListener == null)) {
            this.mActionText.setVisibility(0);
            WishTextViewSpec.applyTextViewSpec(this.mActionText, bannerSpec.getActionTextSpec());
            this.mActionText.setPaintFlags(8);
            this.mActionText.setOnClickListener(onClickListener);
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.chevron_white_right);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.setColorFilter(this.mActionText.getCurrentTextColor(), Mode.SRC_ATOP);
            this.mActionText.setCompoundDrawables(null, null, drawable, null);
            this.mActionText.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
        }
        this.mAdapter = new PromotionRotatingCardListAdapter(getContext(), bannerSpec.getSmallCardSpecs());
        this.mCardList.setAdapter(this.mAdapter);
        if (this.mAdapter.getExpectedListWidth() < DisplayUtil.getDisplayWidth()) {
            LayoutParams layoutParams = this.mCardList.getLayoutParams();
            layoutParams.width = this.mAdapter.getExpectedListWidth();
            this.mCardList.setLayoutParams(layoutParams);
        }
        setupFooter(bannerSpec.getFeedTitleText1(), bannerSpec.getFeedTitleText2(), bannerSpec.getFeedTitleNotiText());
        handleLogging();
    }

    private void setupFooter(WishTextViewSpec wishTextViewSpec, WishTextViewSpec wishTextViewSpec2, WishTextViewSpec wishTextViewSpec3) {
        if (wishTextViewSpec == null && wishTextViewSpec2 == null && wishTextViewSpec3 == null) {
            this.mFeedTitle1.setVisibility(8);
            this.mFeedTitle2.setVisibility(8);
            this.mFeedTitleNotiContainer.setVisibility(8);
            return;
        }
        this.mFeedTitle1.setVisibility(0);
        this.mFeedTitle2.setVisibility(0);
        this.mFeedTitleNotiContainer.setVisibility(0);
        if (wishTextViewSpec != null) {
            WishTextViewSpec.applyTextViewSpec(this.mFeedTitle1, wishTextViewSpec);
        }
        if (wishTextViewSpec2 != null) {
            WishTextViewSpec.applyTextViewSpec(this.mFeedTitle2, wishTextViewSpec2);
        }
        if (wishTextViewSpec3 != null) {
            this.mFeedTitleNotiContainer.setVisibility(0);
            WishTextViewSpec.applyTextViewSpec(this.mFeedTitleNotiText, wishTextViewSpec3);
            this.mFeedTitleNotiContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PromotionRotatingHeaderView.this.showNotiDialog();
                }
            });
        } else {
            this.mFeedTitleNotiContainer.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void showNotiDialog() {
        if (this.mFragment != null) {
            this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
                public void performTask(BaseActivity baseActivity) {
                    baseActivity.startDialog(PromoRotatingNotiDialogFragment.createSelectCategoryDialog());
                }
            });
        }
    }

    public void releaseImages() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.releaseImages();
        }
        if (this.mCardList != null) {
            this.mCardList.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.restoreImages();
        }
        if (this.mCardList != null) {
            this.mCardList.restoreImages();
        }
    }

    private void handleLogging() {
        if (this.mCardList != null && this.mAdapter != null && this.mAdapter.getCount() != 0) {
            if (this.mAdapter.getCount() > ((!DisplayUtil.isLandscape() || !TabletUtil.isTablet()) ? 2 : 4)) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ROTATING_PROMO_INTERACTIVE_BANNER);
            } else {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ROTATING_PROMO_NON_INTERACTIVE_BANNER);
            }
            this.mCardList.setOnScrollListener(new OnScrollListener() {
                public void onScrollChanged(int i, int i2, int i3, int i4) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_ROTATING_PROMO_INTERACTIVE_BANNER_SCROLL);
                    PromotionRotatingHeaderView.this.mCardList.setOnScrollListener(null);
                }
            });
        }
    }
}
