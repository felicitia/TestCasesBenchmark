package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.SmallHeaderSpec;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;

public class GiftPackFeedSmallHeaderView extends FrameLayout {
    private TextView mActionText;
    private View mLockImg;
    private View mRoot;
    private TextView mText;

    /* renamed from: com.contextlogic.wish.activity.feed.newusergiftpack.GiftPackFeedSmallHeaderView$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType = new int[PromoActionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.contextlogic.wish.api.model.WishPromotionBaseSpec$PromoActionType[] r0 = com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.api.model.WishPromotionBaseSpec$PromoActionType r1 = com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType.FILTER_ID     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.api.model.WishPromotionBaseSpec$PromoActionType r1 = com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType.DEEP_LINK     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.feed.newusergiftpack.GiftPackFeedSmallHeaderView.AnonymousClass2.<clinit>():void");
        }
    }

    public GiftPackFeedSmallHeaderView(Context context) {
        this(context, null);
    }

    public GiftPackFeedSmallHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mRoot = LayoutInflater.from(getContext()).inflate(R.layout.gift_pack_feed_small_header_view, this, true);
        this.mLockImg = this.mRoot.findViewById(R.id.gift_pack_small_header_lock);
        this.mText = (TextView) this.mRoot.findViewById(R.id.gift_pack_small_header_text);
        this.mActionText = (TextView) this.mRoot.findViewById(R.id.gift_pack_small_header_action_text);
    }

    public void setup(SmallHeaderSpec smallHeaderSpec, ProductFeedFragment<DrawerActivity> productFeedFragment) {
        this.mText.setText(smallHeaderSpec.getText());
        setupBackground(smallHeaderSpec.getGradientColors());
        if (!TextUtils.isEmpty(smallHeaderSpec.getActionText())) {
            setupActionText(smallHeaderSpec, productFeedFragment);
        } else if (!TextUtils.isEmpty(smallHeaderSpec.getPromoCodeText())) {
            setupPromoCodeText(smallHeaderSpec.getPromoCodeText());
        }
        this.mLockImg.setVisibility(smallHeaderSpec.showLock() ? 0 : 8);
        if (smallHeaderSpec.getImpressionEventId() != -1) {
            WishAnalyticsLogger.trackEvent(smallHeaderSpec.getImpressionEventId());
        }
    }

    private void setupBackground(int[] iArr) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColors(iArr);
        gradientDrawable.setOrientation(Orientation.TL_BR);
        setBackground(gradientDrawable);
    }

    private void setupActionText(final SmallHeaderSpec smallHeaderSpec, final ProductFeedFragment<DrawerActivity> productFeedFragment) {
        this.mActionText.setText(smallHeaderSpec.getActionText());
        this.mActionText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getContext(), R.drawable.white_arrow_right_11x12), null);
        this.mActionText.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (smallHeaderSpec.getActionEventId() != -1) {
                    WishAnalyticsLogger.trackEvent(smallHeaderSpec.getActionEventId());
                }
                switch (AnonymousClass2.$SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType[smallHeaderSpec.getActionType().ordinal()]) {
                    case 1:
                        int findPositionFromFilterId = productFeedFragment.findPositionFromFilterId(smallHeaderSpec.getFilterId());
                        if (findPositionFromFilterId != -1) {
                            productFeedFragment.setSelectedTab(findPositionFromFilterId);
                            return;
                        }
                        return;
                    case 2:
                        productFeedFragment.withActivity(new ActivityTask<DrawerActivity>() {
                            public void performTask(DrawerActivity drawerActivity) {
                                String deeplink = smallHeaderSpec.getDeeplink();
                                if (deeplink != null && !deeplink.isEmpty()) {
                                    DeepLinkManager.processDeepLink(drawerActivity, new DeepLink(deeplink));
                                }
                            }
                        });
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void setupPromoCodeText(String str) {
        this.mActionText.setText(str);
        this.mActionText.setBackgroundResource(R.drawable.coupon_small_border);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.four_padding);
        int i = dimensionPixelSize * 2;
        this.mActionText.setPadding(i, dimensionPixelSize, i, dimensionPixelSize);
    }
}
