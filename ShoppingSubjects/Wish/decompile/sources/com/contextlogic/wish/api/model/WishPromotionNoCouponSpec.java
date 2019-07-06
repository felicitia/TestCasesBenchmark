package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.feed.BaseFeedHeaderView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.promotion.PromotionBannerNoCouponHeaderView;
import com.contextlogic.wish.activity.feed.promotion.PromotionBannerSmallNoCouponHeaderView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.promotion.SplashPromotionNoCouponView;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPromotionNoCouponSpec extends WishPromotionBaseSpec implements Parcelable {
    public static final Creator<WishPromotionNoCouponSpec> CREATOR = new Creator<WishPromotionNoCouponSpec>() {
        public WishPromotionNoCouponSpec createFromParcel(Parcel parcel) {
            return new WishPromotionNoCouponSpec(parcel);
        }

        public WishPromotionNoCouponSpec[] newArray(int i) {
            return new WishPromotionNoCouponSpec[i];
        }
    };
    private BannerSmallSpec mBannerSmallSpec;
    /* access modifiers changed from: private */
    public BannerSpec mBannerSpec;
    private SplashSpec mSplashSpec;

    /* renamed from: com.contextlogic.wish.api.model.WishPromotionNoCouponSpec$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.model.WishPromotionNoCouponSpec.AnonymousClass4.<clinit>():void");
        }
    }

    public static class BannerSmallSpec extends BaseModel implements Parcelable {
        public static final Creator<BannerSmallSpec> CREATOR = new Creator<BannerSmallSpec>() {
            public BannerSmallSpec createFromParcel(Parcel parcel) {
                return new BannerSmallSpec(parcel);
            }

            public BannerSmallSpec[] newArray(int i) {
                return new BannerSmallSpec[i];
            }
        };
        private String mBackgroundColor;
        private String mBackgroundImageUrl;
        private String mTextColor;
        private WishTextViewSpec mTitle;

        public int describeContents() {
            return 0;
        }

        public BannerSmallSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        protected BannerSmallSpec(Parcel parcel) {
            this.mTitle = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mTextColor = parcel.readString();
            this.mBackgroundImageUrl = parcel.readString();
            this.mBackgroundColor = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mTitle, i);
            parcel.writeString(this.mTextColor);
            parcel.writeString(this.mBackgroundImageUrl);
            parcel.writeString(this.mBackgroundColor);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (JsonUtil.hasValue(jSONObject, StrongAuth.AUTH_TITLE)) {
                this.mTitle = new WishTextViewSpec(jSONObject.getJSONObject(StrongAuth.AUTH_TITLE));
            }
            this.mTextColor = JsonUtil.optString(jSONObject, "text_color");
            this.mBackgroundImageUrl = JsonUtil.optString(jSONObject, "background_image_url");
            this.mBackgroundColor = JsonUtil.optString(jSONObject, "background_color");
        }

        public WishTextViewSpec getTitle() {
            return this.mTitle;
        }

        public String getTextColor() {
            return this.mTextColor;
        }

        public String getBackgroundImageUrl() {
            return this.mBackgroundImageUrl;
        }

        public String getBackgroundColor() {
            return this.mBackgroundColor;
        }
    }

    public static class BannerSpec extends BaseModel implements Parcelable {
        public static final Creator<BannerSpec> CREATOR = new Creator<BannerSpec>() {
            public BannerSpec createFromParcel(Parcel parcel) {
                return new BannerSpec(parcel);
            }

            public BannerSpec[] newArray(int i) {
                return new BannerSpec[i];
            }
        };
        private WishTextViewSpec mActionText;
        private String mBackgroundColor;
        private String mBackgroundImageUrl;
        private String mDeepLink;
        private WishTextViewSpec mExpiryText;
        private String mFilterId;
        private WishTextViewSpec mSubtitle;
        private String mTextColor;
        private WishTextViewSpec mTitle;

        public int describeContents() {
            return 0;
        }

        public BannerSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        protected BannerSpec(Parcel parcel) {
            this.mBackgroundImageUrl = parcel.readString();
            this.mBackgroundColor = parcel.readString();
            this.mTextColor = parcel.readString();
            this.mDeepLink = parcel.readString();
            this.mFilterId = parcel.readString();
            this.mTitle = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mSubtitle = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mExpiryText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mActionText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mBackgroundImageUrl);
            parcel.writeString(this.mBackgroundColor);
            parcel.writeString(this.mTextColor);
            parcel.writeString(this.mDeepLink);
            parcel.writeString(this.mFilterId);
            parcel.writeParcelable(this.mTitle, i);
            parcel.writeParcelable(this.mSubtitle, i);
            parcel.writeParcelable(this.mExpiryText, i);
            parcel.writeParcelable(this.mActionText, i);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mBackgroundImageUrl = JsonUtil.optString(jSONObject, "background_image_url");
            this.mBackgroundColor = JsonUtil.optString(jSONObject, "background_color");
            this.mTextColor = JsonUtil.optString(jSONObject, "text_color");
            this.mDeepLink = JsonUtil.optString(jSONObject, "deeplink");
            this.mFilterId = JsonUtil.optString(jSONObject, "filter_id");
            if (JsonUtil.hasValue(jSONObject, StrongAuth.AUTH_TITLE)) {
                this.mTitle = new WishTextViewSpec(jSONObject.getJSONObject(StrongAuth.AUTH_TITLE));
            }
            if (JsonUtil.hasValue(jSONObject, "subtitle")) {
                this.mSubtitle = new WishTextViewSpec(jSONObject.getJSONObject("subtitle"));
            }
            if (JsonUtil.hasValue(jSONObject, "expiry_text")) {
                this.mExpiryText = new WishTextViewSpec(jSONObject.getJSONObject("expiry_text"));
            }
            if (JsonUtil.hasValue(jSONObject, "action_text")) {
                this.mActionText = new WishTextViewSpec(jSONObject.getJSONObject("action_text"));
            }
        }

        public String getBackgroundImageUrl() {
            return this.mBackgroundImageUrl;
        }

        public String getBackgroundColor() {
            return this.mBackgroundColor;
        }

        public String getTextColor() {
            return this.mTextColor;
        }

        public String getDeepLink() {
            return this.mDeepLink;
        }

        public String getFilterId() {
            return this.mFilterId;
        }

        public PromoActionType getActionType() {
            if (this.mFilterId != null) {
                return PromoActionType.FILTER_ID;
            }
            if (this.mDeepLink != null) {
                return PromoActionType.DEEP_LINK;
            }
            return PromoActionType.UNKNOWN;
        }

        public WishTextViewSpec getTitle() {
            return this.mTitle;
        }

        public WishTextViewSpec getSubtitle() {
            return this.mSubtitle;
        }

        public WishTextViewSpec getExpiryText() {
            return this.mExpiryText;
        }

        public WishTextViewSpec getActionText() {
            return this.mActionText;
        }
    }

    public static class SplashSpec extends BaseModel implements Parcelable {
        public static final Creator<SplashSpec> CREATOR = new Creator<SplashSpec>() {
            public SplashSpec createFromParcel(Parcel parcel) {
                return new SplashSpec(parcel);
            }

            public SplashSpec[] newArray(int i) {
                return new SplashSpec[i];
            }
        };
        private boolean isDividerVisible;
        private String mBackgroundColor;
        private String mBackgroundImageUrl;
        private String mButtonColor;
        private String mButtonDeeplink;
        private WishTextViewSpec mMainButton;
        private WishTextViewSpec mPromoHeader;
        private WishTextViewSpec mPromoSubtext;
        private WishTextViewSpec mPromoText;
        private WishTextViewSpec mSubtitle;
        private String mTextColor;
        private WishTextViewSpec mTitle;

        public int describeContents() {
            return 0;
        }

        private SplashSpec(Parcel parcel) {
            this.isDividerVisible = parcel.readByte() != 0;
            this.mButtonDeeplink = parcel.readString();
            this.mBackgroundImageUrl = parcel.readString();
            this.mBackgroundColor = parcel.readString();
            this.mTextColor = parcel.readString();
            this.mButtonColor = parcel.readString();
            this.mTitle = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mSubtitle = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mPromoHeader = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mPromoText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mPromoSubtext = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mMainButton = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
        }

        SplashSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.isDividerVisible ? (byte) 1 : 0);
            parcel.writeString(this.mButtonDeeplink);
            parcel.writeString(this.mBackgroundImageUrl);
            parcel.writeString(this.mBackgroundColor);
            parcel.writeString(this.mTextColor);
            parcel.writeString(this.mButtonColor);
            parcel.writeParcelable(this.mTitle, i);
            parcel.writeParcelable(this.mSubtitle, i);
            parcel.writeParcelable(this.mPromoHeader, i);
            parcel.writeParcelable(this.mPromoText, i);
            parcel.writeParcelable(this.mPromoSubtext, i);
            parcel.writeParcelable(this.mMainButton, i);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.isDividerVisible = jSONObject.optBoolean("divider", false);
            this.mButtonDeeplink = JsonUtil.optString(jSONObject, "deeplink");
            this.mBackgroundImageUrl = JsonUtil.optString(jSONObject, "background_image_url");
            this.mBackgroundColor = JsonUtil.optString(jSONObject, "background_color");
            this.mTextColor = JsonUtil.optString(jSONObject, "text_color");
            this.mButtonColor = JsonUtil.optString(jSONObject, "button_color");
            if (JsonUtil.hasValue(jSONObject, StrongAuth.AUTH_TITLE)) {
                this.mTitle = new WishTextViewSpec(jSONObject.getJSONObject(StrongAuth.AUTH_TITLE));
            }
            if (JsonUtil.hasValue(jSONObject, "subtitle")) {
                this.mSubtitle = new WishTextViewSpec(jSONObject.getJSONObject("subtitle"));
            }
            if (JsonUtil.hasValue(jSONObject, "promo_header")) {
                this.mPromoHeader = new WishTextViewSpec(jSONObject.getJSONObject("promo_header"));
            }
            if (JsonUtil.hasValue(jSONObject, "promo_text")) {
                this.mPromoText = new WishTextViewSpec(jSONObject.getJSONObject("promo_text"));
            }
            if (JsonUtil.hasValue(jSONObject, "promo_subtext")) {
                this.mPromoSubtext = new WishTextViewSpec(jSONObject.getJSONObject("promo_subtext"));
            }
            if (JsonUtil.hasValue(jSONObject, "main_button")) {
                this.mMainButton = new WishTextViewSpec(jSONObject.getJSONObject("main_button"));
            }
        }

        public boolean isDividerVisible() {
            return this.isDividerVisible;
        }

        public String getBackgroundImageUrl() {
            return this.mBackgroundImageUrl;
        }

        public String getBackgroundColor() {
            return this.mBackgroundColor;
        }

        public String getTextColor() {
            return this.mTextColor;
        }

        public String getButtonColor() {
            return this.mButtonColor;
        }

        public WishTextViewSpec getTitle() {
            return this.mTitle;
        }

        public WishTextViewSpec getSubtitle() {
            return this.mSubtitle;
        }

        public WishTextViewSpec getPromoHeader() {
            return this.mPromoHeader;
        }

        public WishTextViewSpec getPromoText() {
            return this.mPromoText;
        }

        public WishTextViewSpec getPromoSubtext() {
            return this.mPromoSubtext;
        }

        public WishTextViewSpec getMainButton() {
            return this.mMainButton;
        }

        public String getButtonDeeplink() {
            return this.mButtonDeeplink;
        }
    }

    public int describeContents() {
        return 0;
    }

    private WishPromotionNoCouponSpec(Parcel parcel) {
        this.mSplashSpec = (SplashSpec) parcel.readParcelable(SplashSpec.class.getClassLoader());
        this.mBannerSpec = (BannerSpec) parcel.readParcelable(BannerSpec.class.getClassLoader());
        this.mBannerSmallSpec = (BannerSmallSpec) parcel.readParcelable(BannerSmallSpec.class.getClassLoader());
    }

    WishPromotionNoCouponSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mSplashSpec, i);
        parcel.writeParcelable(this.mBannerSpec, i);
        parcel.writeParcelable(this.mBannerSmallSpec, i);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "splash")) {
            this.mSplashSpec = new SplashSpec(jSONObject.getJSONObject("splash"));
        }
        if (JsonUtil.hasValue(jSONObject, "banner")) {
            this.mBannerSpec = new BannerSpec(jSONObject.getJSONObject("banner"));
        }
        if (JsonUtil.hasValue(jSONObject, "banner_small")) {
            this.mBannerSmallSpec = new BannerSmallSpec(jSONObject.getJSONObject("banner_small"));
        }
    }

    public View getSplashView(BaseDialogFragment baseDialogFragment) {
        SplashPromotionNoCouponView splashPromotionNoCouponView = new SplashPromotionNoCouponView(baseDialogFragment);
        splashPromotionNoCouponView.setup(this.mSplashSpec);
        return splashPromotionNoCouponView;
    }

    public View getProductOverviewBannerView(final BaseFragment baseFragment, WishAnalyticsEvent wishAnalyticsEvent, final WishAnalyticsEvent wishAnalyticsEvent2) {
        PromotionBannerNoCouponHeaderView promotionBannerNoCouponHeaderView = new PromotionBannerNoCouponHeaderView(baseFragment.getContext());
        promotionBannerNoCouponHeaderView.setup(this.mBannerSpec, wishAnalyticsEvent);
        promotionBannerNoCouponHeaderView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (wishAnalyticsEvent2 != null) {
                    WishAnalyticsLogger.trackEvent(wishAnalyticsEvent2);
                }
                if (WishPromotionNoCouponSpec.this.mBannerSpec.getDeepLink() != null && !WishPromotionNoCouponSpec.this.mBannerSpec.getDeepLink().isEmpty()) {
                    baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            DeepLinkManager.processDeepLink(baseActivity, new DeepLink(WishPromotionNoCouponSpec.this.mBannerSpec.getDeepLink()));
                        }
                    });
                }
            }
        });
        return promotionBannerNoCouponHeaderView;
    }

    public BaseFeedHeaderView getFeedBannerView(final ProductFeedFragment productFeedFragment, WishAnalyticsEvent wishAnalyticsEvent, final WishAnalyticsEvent wishAnalyticsEvent2) {
        if (this.mBannerSpec == null) {
            return null;
        }
        PromotionBannerNoCouponHeaderView promotionBannerNoCouponHeaderView = new PromotionBannerNoCouponHeaderView(productFeedFragment.getContext());
        promotionBannerNoCouponHeaderView.setup(this.mBannerSpec, wishAnalyticsEvent);
        promotionBannerNoCouponHeaderView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (wishAnalyticsEvent2 != null) {
                    WishAnalyticsLogger.trackEvent(wishAnalyticsEvent2);
                }
                switch (AnonymousClass4.$SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType[WishPromotionNoCouponSpec.this.mBannerSpec.getActionType().ordinal()]) {
                    case 1:
                        int findPositionFromFilterId = productFeedFragment.findPositionFromFilterId(WishPromotionNoCouponSpec.this.mBannerSpec.getFilterId());
                        if (findPositionFromFilterId >= 0) {
                            productFeedFragment.setSelectedTab(findPositionFromFilterId);
                            return;
                        }
                        return;
                    case 2:
                        productFeedFragment.withActivity(new ActivityTask<BaseActivity>() {
                            public void performTask(BaseActivity baseActivity) {
                                DeepLinkManager.processDeepLink(baseActivity, new DeepLink(WishPromotionNoCouponSpec.this.mBannerSpec.getDeepLink()));
                            }
                        });
                        return;
                    default:
                        return;
                }
            }
        });
        return promotionBannerNoCouponHeaderView;
    }

    public BaseFeedHeaderView getFeedBannerSmallHeaderView(BaseFragment baseFragment) {
        PromotionBannerSmallNoCouponHeaderView promotionBannerSmallNoCouponHeaderView = new PromotionBannerSmallNoCouponHeaderView(baseFragment.getContext());
        promotionBannerSmallNoCouponHeaderView.setup(this.mBannerSmallSpec);
        return promotionBannerSmallNoCouponHeaderView;
    }

    public String getFilterId() {
        return this.mBannerSpec.getFilterId();
    }
}
