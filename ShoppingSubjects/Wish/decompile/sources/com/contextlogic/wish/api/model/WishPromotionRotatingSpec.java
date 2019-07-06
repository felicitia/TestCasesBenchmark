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
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.promotion.PromotionRotatingHeaderView;
import com.contextlogic.wish.dialog.promotion.SplashPromotionRotatingView;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPromotionRotatingSpec extends WishPromotionBaseSpec implements Parcelable {
    public static final Creator<WishPromotionRotatingSpec> CREATOR = new Creator<WishPromotionRotatingSpec>() {
        public WishPromotionRotatingSpec createFromParcel(Parcel parcel) {
            return new WishPromotionRotatingSpec(parcel);
        }

        public WishPromotionRotatingSpec[] newArray(int i) {
            return new WishPromotionRotatingSpec[i];
        }
    };
    private BannerSpec mBannerSmallSpec;
    /* access modifiers changed from: private */
    public BannerSpec mBannerSpec;
    private SplashSpec mSplashSpec;

    /* renamed from: com.contextlogic.wish.api.model.WishPromotionRotatingSpec$4 reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.model.WishPromotionRotatingSpec.AnonymousClass4.<clinit>():void");
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
        private WishTextViewSpec mActionTextSpec;
        private String mBackgroundColorString;
        private String mBackgroundImageUrl;
        private String mDeepLink;
        private WishTextViewSpec mExpiryTextSpec;
        private WishTextViewSpec mFeedTitleNotiText;
        private WishTextViewSpec mFeedTitleText1;
        private WishTextViewSpec mFeedTitleText2;
        private String mFilterId;
        private ArrayList<SmallCardSpec> mSmallCardSpecs;
        private WishTextViewSpec mTitleTextSpec;

        public int describeContents() {
            return 0;
        }

        BannerSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        BannerSpec(Parcel parcel) {
            this.mExpiryTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mTitleTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mActionTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mFilterId = parcel.readString();
            this.mDeepLink = parcel.readString();
            this.mSmallCardSpecs = parcel.readArrayList(SmallCardSpec.class.getClassLoader());
            this.mBackgroundImageUrl = parcel.readString();
            this.mBackgroundColorString = parcel.readString();
            this.mFeedTitleText1 = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mFeedTitleText2 = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mActionTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mExpiryTextSpec, i);
            parcel.writeParcelable(this.mTitleTextSpec, i);
            parcel.writeParcelable(this.mActionTextSpec, i);
            parcel.writeString(this.mFilterId);
            parcel.writeString(this.mDeepLink);
            parcel.writeList(this.mSmallCardSpecs);
            parcel.writeString(this.mBackgroundImageUrl);
            parcel.writeString(this.mBackgroundColorString);
            parcel.writeParcelable(this.mFeedTitleText1, i);
            parcel.writeParcelable(this.mFeedTitleText2, i);
            parcel.writeParcelable(this.mFeedTitleNotiText, i);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (JsonUtil.hasValue(jSONObject, "expiry_text_spec")) {
                this.mExpiryTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("expiry_text_spec"));
            }
            this.mTitleTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("title_text_spec"));
            if (JsonUtil.hasValue(jSONObject, "action_text_spec")) {
                this.mActionTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("action_text_spec"));
            }
            this.mFilterId = JsonUtil.optString(jSONObject, "filter_id");
            this.mDeepLink = JsonUtil.optString(jSONObject, "deeplink");
            this.mSmallCardSpecs = JsonUtil.parseArray(jSONObject, "small_card_specs", new DataParser<SmallCardSpec, JSONObject>() {
                public SmallCardSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new SmallCardSpec(jSONObject);
                }
            });
            this.mBackgroundImageUrl = JsonUtil.optString(jSONObject, "background_image_url");
            this.mBackgroundColorString = JsonUtil.optString(jSONObject, "background_color");
            if (JsonUtil.hasValue(jSONObject, "feed_title_text_spec_1")) {
                this.mFeedTitleText1 = new WishTextViewSpec(jSONObject.getJSONObject("feed_title_text_spec_1"));
            }
            if (JsonUtil.hasValue(jSONObject, "feed_title_text_spec_2")) {
                this.mFeedTitleText2 = new WishTextViewSpec(jSONObject.getJSONObject("feed_title_text_spec_2"));
            }
            if (JsonUtil.hasValue(jSONObject, "feed_title_noti_text_spec")) {
                this.mFeedTitleNotiText = new WishTextViewSpec(jSONObject.getJSONObject("feed_title_noti_text_spec"));
            }
        }

        public WishTextViewSpec getExpiryTextSpec() {
            return this.mExpiryTextSpec;
        }

        public WishTextViewSpec getTitleTextSpec() {
            return this.mTitleTextSpec;
        }

        public WishTextViewSpec getActionTextSpec() {
            return this.mActionTextSpec;
        }

        public String getFilterId() {
            return this.mFilterId;
        }

        public String getDeepLink() {
            return this.mDeepLink;
        }

        public ArrayList<SmallCardSpec> getSmallCardSpecs() {
            return this.mSmallCardSpecs;
        }

        public String getBackgroundImageUrl() {
            return this.mBackgroundImageUrl;
        }

        public String getBackgroundColorString() {
            return this.mBackgroundColorString;
        }

        public PromoActionType getActionType() {
            if (this.mFilterId != null && !this.mFilterId.isEmpty()) {
                return PromoActionType.FILTER_ID;
            }
            if (this.mDeepLink == null || this.mDeepLink.isEmpty()) {
                return PromoActionType.UNKNOWN;
            }
            return PromoActionType.DEEP_LINK;
        }

        public WishTextViewSpec getFeedTitleText1() {
            return this.mFeedTitleText1;
        }

        public WishTextViewSpec getFeedTitleText2() {
            return this.mFeedTitleText2;
        }

        public WishTextViewSpec getFeedTitleNotiText() {
            return this.mFeedTitleNotiText;
        }
    }

    public static class LargeCardSpec extends SmallCardSpec {
        public static final Creator<LargeCardSpec> CREATOR = new Creator<LargeCardSpec>() {
            public LargeCardSpec createFromParcel(Parcel parcel) {
                return new LargeCardSpec(parcel);
            }

            public LargeCardSpec[] newArray(int i) {
                return new LargeCardSpec[i];
            }
        };
        private ArrayList<String> mProductImageUrls;

        LargeCardSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        LargeCardSpec(Parcel parcel) {
            super(parcel);
            this.mProductImageUrls = parcel.createStringArrayList();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeStringList(this.mProductImageUrls);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            super.parseJson(jSONObject);
            this.mProductImageUrls = JsonUtil.parseArray(jSONObject, "product_urls", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException, ParseException {
                    return str;
                }
            });
        }

        public ArrayList<String> getProductImageUrls() {
            return this.mProductImageUrls;
        }
    }

    public static class SmallCardSpec extends BaseModel implements Parcelable {
        public static final Creator<SmallCardSpec> CREATOR = new Creator<SmallCardSpec>() {
            public SmallCardSpec createFromParcel(Parcel parcel) {
                return new SmallCardSpec(parcel);
            }

            public SmallCardSpec[] newArray(int i) {
                return new SmallCardSpec[i];
            }
        };
        private String mBackgroundColorString;
        private WishTextViewSpec mCategoryTextSpec;
        private String mDividerColorString;
        private boolean mDrawShadow;
        private String mHighlightColorString;
        private WishTimerTextViewSpec mTimerTextSpec;
        private WishTextViewSpec mTitleTextSpec;

        public int describeContents() {
            return 0;
        }

        SmallCardSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        SmallCardSpec(Parcel parcel) {
            this.mBackgroundColorString = parcel.readString();
            this.mHighlightColorString = parcel.readString();
            this.mDividerColorString = parcel.readString();
            this.mTitleTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mTimerTextSpec = (WishTimerTextViewSpec) parcel.readParcelable(WishTimerTextViewSpec.class.getClassLoader());
            this.mCategoryTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mDrawShadow = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mBackgroundColorString);
            parcel.writeString(this.mHighlightColorString);
            parcel.writeString(this.mDividerColorString);
            parcel.writeParcelable(this.mTitleTextSpec, i);
            parcel.writeParcelable(this.mTimerTextSpec, i);
            parcel.writeParcelable(this.mCategoryTextSpec, i);
            parcel.writeByte(this.mDrawShadow ? (byte) 1 : 0);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mBackgroundColorString = JsonUtil.optString(jSONObject, "background_color");
            this.mHighlightColorString = JsonUtil.optString(jSONObject, "highlight_color");
            this.mDividerColorString = JsonUtil.optString(jSONObject, "divider_color");
            this.mTitleTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("title_text_spec"));
            if (JsonUtil.hasValue(jSONObject, "timer_text_spec")) {
                this.mTimerTextSpec = new WishTimerTextViewSpec(jSONObject.getJSONObject("timer_text_spec"));
            }
            if (JsonUtil.hasValue(jSONObject, "category_text_spec")) {
                this.mCategoryTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("category_text_spec"));
            }
            this.mDrawShadow = jSONObject.optBoolean("draw_shadow");
        }

        public String getBackgroundColorString() {
            return this.mBackgroundColorString;
        }

        public String getHighlightColorString() {
            return this.mHighlightColorString;
        }

        public String getDividerColorString() {
            return this.mDividerColorString;
        }

        public WishTextViewSpec getTitleTextSpec() {
            return this.mTitleTextSpec;
        }

        public WishTimerTextViewSpec getTimerTextSpec() {
            return this.mTimerTextSpec;
        }

        public WishTextViewSpec getCategoryTextSpec() {
            return this.mCategoryTextSpec;
        }

        public boolean shouldDrawShadow() {
            return this.mDrawShadow;
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
        private String mBackgroundColorString;
        private String mBackgroundImageUrl;
        private String mButtonColorString;
        private WishTextViewSpec mButtonTextSpec;
        private LargeCardSpec mCurrentCategorySpec;
        private String mDeeplink;
        private LargeCardSpec mNextCategorySpec;
        private ArrayList<WishTextViewSpec> mTitleTexts;

        public int describeContents() {
            return 0;
        }

        private SplashSpec(Parcel parcel) {
            this.mTitleTexts = parcel.createTypedArrayList(WishTextViewSpec.CREATOR);
            this.mCurrentCategorySpec = (LargeCardSpec) parcel.readParcelable(LargeCardSpec.class.getClassLoader());
            this.mNextCategorySpec = (LargeCardSpec) parcel.readParcelable(LargeCardSpec.class.getClassLoader());
            this.mButtonTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mButtonColorString = parcel.readString();
            this.mDeeplink = parcel.readString();
            this.mBackgroundImageUrl = parcel.readString();
            this.mBackgroundColorString = parcel.readString();
        }

        SplashSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(this.mTitleTexts);
            parcel.writeParcelable(this.mCurrentCategorySpec, i);
            parcel.writeParcelable(this.mNextCategorySpec, i);
            parcel.writeParcelable(this.mButtonTextSpec, i);
            parcel.writeString(this.mButtonColorString);
            parcel.writeString(this.mDeeplink);
            parcel.writeString(this.mBackgroundImageUrl);
            parcel.writeString(this.mBackgroundColorString);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mTitleTexts = JsonUtil.parseArray(jSONObject, "title_text_specs", new DataParser<WishTextViewSpec, JSONObject>() {
                public WishTextViewSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishTextViewSpec(jSONObject);
                }
            });
            this.mCurrentCategorySpec = new LargeCardSpec(jSONObject.getJSONObject("current_category_large_card_spec"));
            if (JsonUtil.hasValue(jSONObject, "next_category_large_card_spec")) {
                this.mNextCategorySpec = new LargeCardSpec(jSONObject.getJSONObject("next_category_large_card_spec"));
            }
            this.mButtonTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("button_text_spec"));
            this.mButtonColorString = JsonUtil.optString(jSONObject, "button_color");
            this.mDeeplink = JsonUtil.optString(jSONObject, "button_deeplink");
            this.mBackgroundImageUrl = JsonUtil.optString(jSONObject, "background_image_url");
            this.mBackgroundColorString = JsonUtil.optString(jSONObject, "background_color");
        }

        public ArrayList<WishTextViewSpec> getTitleTexts() {
            return this.mTitleTexts;
        }

        public LargeCardSpec getCurrentCategorySpec() {
            return this.mCurrentCategorySpec;
        }

        public LargeCardSpec getNextCategorySpec() {
            return this.mNextCategorySpec;
        }

        public WishTextViewSpec getButtonTextSpec() {
            return this.mButtonTextSpec;
        }

        public String getButtonColorString() {
            return this.mButtonColorString;
        }

        public String getDeeplink() {
            return this.mDeeplink;
        }

        public String getBackgroundImageUrl() {
            return this.mBackgroundImageUrl;
        }

        public String getBackgroundColorString() {
            return this.mBackgroundColorString;
        }
    }

    public int describeContents() {
        return 0;
    }

    private WishPromotionRotatingSpec(Parcel parcel) {
        this.mSplashSpec = (SplashSpec) parcel.readParcelable(SplashSpec.class.getClassLoader());
        this.mBannerSpec = (BannerSpec) parcel.readParcelable(BannerSpec.class.getClassLoader());
        this.mBannerSmallSpec = (BannerSpec) parcel.readParcelable(BannerSpec.class.getClassLoader());
    }

    WishPromotionRotatingSpec(JSONObject jSONObject) throws JSONException, ParseException {
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
            this.mBannerSmallSpec = new BannerSpec(jSONObject.getJSONObject("banner_small"));
        }
    }

    public View getSplashView(BaseDialogFragment baseDialogFragment) {
        if (this.mSplashSpec == null || baseDialogFragment == null || !baseDialogFragment.isAdded()) {
            return null;
        }
        SplashPromotionRotatingView splashPromotionRotatingView = new SplashPromotionRotatingView(baseDialogFragment);
        splashPromotionRotatingView.setup(this.mSplashSpec);
        return splashPromotionRotatingView;
    }

    public View getProductOverviewBannerView(final BaseFragment baseFragment, WishAnalyticsEvent wishAnalyticsEvent, final WishAnalyticsEvent wishAnalyticsEvent2) {
        if (this.mBannerSpec == null || baseFragment == null || !baseFragment.isAdded()) {
            return null;
        }
        AnonymousClass2 r0 = new OnClickListener() {
            public void onClick(View view) {
                if (wishAnalyticsEvent2 != null) {
                    WishAnalyticsLogger.trackEvent(wishAnalyticsEvent2);
                }
                if (WishPromotionRotatingSpec.this.mBannerSpec.getDeepLink() != null && !WishPromotionRotatingSpec.this.mBannerSpec.getDeepLink().isEmpty()) {
                    baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            DeepLinkManager.processDeepLink(baseActivity, new DeepLink(WishPromotionRotatingSpec.this.mBannerSpec.getDeepLink()));
                        }
                    });
                }
            }
        };
        WishAnalyticsLogger.trackEvent(wishAnalyticsEvent);
        PromotionRotatingHeaderView promotionRotatingHeaderView = new PromotionRotatingHeaderView(baseFragment);
        promotionRotatingHeaderView.setup(this.mBannerSpec, r0);
        return promotionRotatingHeaderView;
    }

    public BaseFeedHeaderView getFeedBannerView(final ProductFeedFragment productFeedFragment, WishAnalyticsEvent wishAnalyticsEvent, final WishAnalyticsEvent wishAnalyticsEvent2) {
        if (this.mBannerSpec == null) {
            return null;
        }
        AnonymousClass3 r0 = new OnClickListener() {
            public void onClick(View view) {
                if (wishAnalyticsEvent2 != null) {
                    WishAnalyticsLogger.trackEvent(wishAnalyticsEvent2);
                }
                switch (AnonymousClass4.$SwitchMap$com$contextlogic$wish$api$model$WishPromotionBaseSpec$PromoActionType[WishPromotionRotatingSpec.this.mBannerSpec.getActionType().ordinal()]) {
                    case 1:
                        int findPositionFromFilterId = productFeedFragment.findPositionFromFilterId(WishPromotionRotatingSpec.this.mBannerSpec.getFilterId());
                        if (findPositionFromFilterId >= 0) {
                            productFeedFragment.setSelectedTab(findPositionFromFilterId);
                            return;
                        }
                        return;
                    case 2:
                        productFeedFragment.withActivity(new ActivityTask<BaseActivity>() {
                            public void performTask(BaseActivity baseActivity) {
                                String deepLink = WishPromotionRotatingSpec.this.mBannerSpec.getDeepLink();
                                if (deepLink != null && !deepLink.isEmpty()) {
                                    DeepLinkManager.processDeepLink(baseActivity, new DeepLink(deepLink));
                                }
                            }
                        });
                        return;
                    default:
                        return;
                }
            }
        };
        WishAnalyticsLogger.trackEvent(wishAnalyticsEvent);
        PromotionRotatingHeaderView promotionRotatingHeaderView = new PromotionRotatingHeaderView(productFeedFragment);
        promotionRotatingHeaderView.setup(this.mBannerSpec, r0);
        return promotionRotatingHeaderView;
    }

    public BaseFeedHeaderView getFeedBannerSmallHeaderView(BaseFragment baseFragment) {
        if (this.mBannerSmallSpec == null) {
            return null;
        }
        PromotionRotatingHeaderView promotionRotatingHeaderView = new PromotionRotatingHeaderView(baseFragment);
        promotionRotatingHeaderView.setup(this.mBannerSmallSpec, null);
        return promotionRotatingHeaderView;
    }

    public String getFilterId() {
        if (this.mBannerSpec == null) {
            return null;
        }
        return this.mBannerSpec.getFilterId();
    }
}
