package com.contextlogic.wish.dialog.promotion;

import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPromotionRotatingSpec.LargeCardSpec;
import com.contextlogic.wish.api.model.WishPromotionRotatingSpec.SplashSpec;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.image.ContainerRestorable;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ColorUtil;
import java.util.Iterator;

public class SplashPromotionRotatingView extends RelativeLayout implements ImageRestorable {
    private Button mActionButton;
    private NetworkImageView mBackgroundImage;
    private View mCloseButton;
    private LinearLayout mContentContainer;
    /* access modifiers changed from: private */
    public BaseDialogFragment mFragment;
    private LinearLayout mTitleContainer;

    public SplashPromotionRotatingView(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment.getContext());
        this.mFragment = baseDialogFragment;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promotion_dialog_rotating_splash_view, this);
        this.mTitleContainer = (LinearLayout) inflate.findViewById(R.id.promotion_rotating_splash_title_container);
        this.mBackgroundImage = (NetworkImageView) inflate.findViewById(R.id.promotion_rotating_splash_background);
        this.mContentContainer = (LinearLayout) inflate.findViewById(R.id.promotion_rotating_splash_content_container);
        this.mCloseButton = inflate.findViewById(R.id.promotion_rotating_splash_x);
        this.mActionButton = (Button) inflate.findViewById(R.id.promotion_rotating_splash_button);
    }

    public void setup(final SplashSpec splashSpec) {
        if (splashSpec == null) {
            dismiss();
            return;
        }
        if (splashSpec.getBackgroundImageUrl() != null && !splashSpec.getBackgroundImageUrl().isEmpty()) {
            this.mBackgroundImage.setImageUrl(splashSpec.getBackgroundImageUrl());
        }
        if (splashSpec.getBackgroundColorString() != null && !splashSpec.getBackgroundColorString().isEmpty()) {
            int safeParseColor = ColorUtil.safeParseColor(splashSpec.getBackgroundColorString(), 0);
            if (safeParseColor != 0) {
                setBackgroundColor(safeParseColor);
            }
        }
        this.mTitleContainer.removeAllViews();
        Iterator it = splashSpec.getTitleTexts().iterator();
        while (it.hasNext()) {
            createAndAddTextView(this.mTitleContainer, (WishTextViewSpec) it.next());
        }
        this.mContentContainer.removeAllViews();
        createAndAddCard(this.mContentContainer, splashSpec.getCurrentCategorySpec());
        createAndAddCard(this.mContentContainer, splashSpec.getNextCategorySpec());
        WishTextViewSpec.applyTextViewSpec(this.mActionButton, splashSpec.getButtonTextSpec());
        if (!(splashSpec.getButtonColorString() == null || splashSpec.getButtonColorString().isEmpty() || this.mActionButton.getBackground() == null)) {
            int safeParseColor2 = ColorUtil.safeParseColor(splashSpec.getButtonColorString(), 0);
            if (safeParseColor2 != 0) {
                this.mActionButton.getBackground().setColorFilter(safeParseColor2, Mode.MULTIPLY);
            }
        }
        this.mCloseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SplashPromotionRotatingView.this.dismiss();
            }
        });
        this.mActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SplashPromotionRotatingView.this.mFragment != null) {
                    SplashPromotionRotatingView.this.mFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            String deeplink = splashSpec.getDeeplink();
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROMO_SPLASH_BUTTON);
                            if (deeplink == null || deeplink.trim().length() <= 0) {
                                SplashPromotionRotatingView.this.mFragment.dismiss();
                            } else {
                                DeepLinkManager.processDeepLink(baseActivity, new DeepLink(deeplink));
                            }
                        }
                    });
                }
            }
        });
    }

    private void createAndAddTextView(ViewGroup viewGroup, WishTextViewSpec wishTextViewSpec) {
        if (viewGroup != null && wishTextViewSpec != null) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            WishTextViewSpec.applyTextViewSpec(themedTextView, wishTextViewSpec);
            themedTextView.setLayoutParams(new LayoutParams(-2, -2));
            themedTextView.setGravity(17);
            viewGroup.addView(themedTextView);
        }
    }

    private void createAndAddCard(ViewGroup viewGroup, LargeCardSpec largeCardSpec) {
        if (viewGroup != null && largeCardSpec != null) {
            PromotionRotatingLargeCardView promotionRotatingLargeCardView = new PromotionRotatingLargeCardView(getContext());
            promotionRotatingLargeCardView.setup(largeCardSpec);
            viewGroup.addView(promotionRotatingLargeCardView);
        }
    }

    /* access modifiers changed from: private */
    public void dismiss() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PROMO_SPLASH_X);
        if (this.mFragment != null) {
            this.mFragment.dismiss();
        }
    }

    public void releaseImages() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.releaseImages();
        }
        ContainerRestorable.releaseChildren(this.mContentContainer);
    }

    public void restoreImages() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.restoreImages();
        }
        ContainerRestorable.restoreChildren(this.mContentContainer);
    }
}
