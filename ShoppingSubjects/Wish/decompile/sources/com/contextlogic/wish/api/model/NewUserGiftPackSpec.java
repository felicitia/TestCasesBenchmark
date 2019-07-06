package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.feed.CollapsableFeedHeaderView;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.newusergiftpack.GiftPackFeedCollapsableHeaderView;
import com.contextlogic.wish.activity.feed.newusergiftpack.GiftPackFeedHeaderView;
import com.contextlogic.wish.activity.feed.newusergiftpack.UserGiftPackSplashView;
import com.contextlogic.wish.activity.feed.newusergiftpack.UserGiftPackV3SplashView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPromotionBaseSpec.PromoActionType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.ColorUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.crashlytics.android.Crashlytics;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class NewUserGiftPackSpec extends WishPromotionBaseSpec {
    public static final Creator<NewUserGiftPackSpec> CREATOR = new Creator<NewUserGiftPackSpec>() {
        public NewUserGiftPackSpec createFromParcel(Parcel parcel) {
            return new NewUserGiftPackSpec(parcel);
        }

        public NewUserGiftPackSpec[] newArray(int i) {
            return new NewUserGiftPackSpec[i];
        }
    };
    private WishDailyLoginStampSpec mDailyLoginStampSpec;
    private LargeHeaderSpec mLargeHeaderSpec;
    private SmallHeaderSpec mSmallHeaderSpec;
    private SplashSpec mSplashSpec;

    public static class CardSpec extends BaseModel {
        public static final Creator<CardSpec> CREATOR = new Creator<CardSpec>() {
            public CardSpec createFromParcel(Parcel parcel) {
                return new CardSpec(parcel);
            }

            public CardSpec[] newArray(int i) {
                return new CardSpec[i];
            }
        };
        private int mActionEventId;
        private WishTextViewSpec mActionTextSpec;
        private WishTextViewSpec mBodyTextSpec;
        private String mBorderColor;
        private WishTextViewSpec mCardTitleTextSpec;
        private WishTextViewSpec mCouponTextSpec;
        private String mDeepLink;
        private String mFilterId;
        private WishImage mImage;
        private int mImpressionEventId;
        private boolean mIsDailyLoginCard;
        private String mLockImageUrl;
        private int mNumStamps;
        private WishTextViewSpec mTitleTextSpec;

        public int describeContents() {
            return 0;
        }

        CardSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        CardSpec(Parcel parcel) {
            this.mTitleTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mBodyTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mActionTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mCouponTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
            this.mLockImageUrl = parcel.readString();
            this.mFilterId = parcel.readString();
            this.mDeepLink = parcel.readString();
            this.mImpressionEventId = parcel.readInt();
            this.mActionEventId = parcel.readInt();
            this.mBorderColor = parcel.readString();
            this.mCardTitleTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mNumStamps = parcel.readInt();
            this.mIsDailyLoginCard = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mTitleTextSpec, i);
            parcel.writeParcelable(this.mBodyTextSpec, i);
            parcel.writeParcelable(this.mActionTextSpec, i);
            parcel.writeParcelable(this.mCouponTextSpec, i);
            parcel.writeParcelable(this.mImage, i);
            parcel.writeString(this.mLockImageUrl);
            parcel.writeString(this.mFilterId);
            parcel.writeString(this.mDeepLink);
            parcel.writeInt(this.mImpressionEventId);
            parcel.writeInt(this.mActionEventId);
            parcel.writeString(this.mBorderColor);
            parcel.writeParcelable(this.mCardTitleTextSpec, i);
            parcel.writeInt(this.mNumStamps);
            parcel.writeByte(this.mIsDailyLoginCard ? (byte) 1 : 0);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (jSONObject.has("title_text_spec")) {
                this.mTitleTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("title_text_spec"));
            }
            if (jSONObject.has("body_text_spec")) {
                this.mBodyTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("body_text_spec"));
            }
            if (jSONObject.has("action_text_spec")) {
                this.mActionTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("action_text_spec"));
            }
            if (jSONObject.has("coupon_text_spec")) {
                this.mCouponTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("coupon_text_spec"));
            }
            if (jSONObject.has("card_image_url")) {
                this.mImage = new WishImage(jSONObject.getString("card_image_url"));
            }
            if (jSONObject.has("card_title_spec")) {
                this.mCardTitleTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("card_title_spec"));
            }
            this.mLockImageUrl = jSONObject.optString("lock_image_url", null);
            this.mFilterId = jSONObject.optString("filter_id", null);
            this.mDeepLink = jSONObject.optString("deeplink", null);
            this.mImpressionEventId = jSONObject.optInt("impression_event_id", -1);
            this.mActionEventId = jSONObject.optInt("action_event_id", -1);
            this.mBorderColor = jSONObject.optString("border_color", null);
            this.mNumStamps = jSONObject.optInt("num_stamps", -1);
            this.mIsDailyLoginCard = jSONObject.optBoolean("is_daily_login_bonus", false);
        }

        public WishTextViewSpec getTitleTextSpec() {
            return this.mTitleTextSpec;
        }

        public WishTextViewSpec getBodyTextSpec() {
            return this.mBodyTextSpec;
        }

        public WishTextViewSpec getActionTextSpec() {
            return this.mActionTextSpec;
        }

        public WishTextViewSpec getCouponTextSpec() {
            return this.mCouponTextSpec;
        }

        public WishImage getImage() {
            return this.mImage;
        }

        public String getLockImageUrl() {
            return this.mLockImageUrl;
        }

        public String getFilterId() {
            return this.mFilterId;
        }

        public String getDeepLink() {
            return this.mDeepLink;
        }

        public int getImpressionEventId() {
            return this.mImpressionEventId;
        }

        public int getActionEventId() {
            return this.mActionEventId;
        }

        public String getBorderColor() {
            return this.mBorderColor;
        }

        public PromoActionType getActionType() {
            return NewUserGiftPackSpec.getActionType(this.mFilterId, this.mDeepLink);
        }

        public WishTextViewSpec getCardTitleTextSpec() {
            return this.mCardTitleTextSpec;
        }

        public boolean isDailyLoginCard() {
            return this.mIsDailyLoginCard;
        }

        public int getNumStamps() {
            return this.mNumStamps;
        }
    }

    public static class LargeHeaderSpec extends BaseModel {
        public static final Creator<LargeHeaderSpec> CREATOR = new Creator<LargeHeaderSpec>() {
            public LargeHeaderSpec createFromParcel(Parcel parcel) {
                return new LargeHeaderSpec(parcel);
            }

            public LargeHeaderSpec[] newArray(int i) {
                return new LargeHeaderSpec[i];
            }
        };
        private ArrayList<CardSpec> mCardSpecs;
        private int mCurrentIndex;
        private WishTextViewSpec mTitleTextSpec;

        public int describeContents() {
            return 0;
        }

        public LargeHeaderSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        public LargeHeaderSpec(Parcel parcel) {
            this.mTitleTextSpec = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mCardSpecs = parcel.createTypedArrayList(CardSpec.CREATOR);
            this.mCurrentIndex = parcel.readInt();
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (jSONObject.has("title_text_spec")) {
                this.mTitleTextSpec = new WishTextViewSpec(jSONObject.getJSONObject("title_text_spec"));
            }
            this.mCardSpecs = JsonUtil.parseArray(jSONObject, "card_specs", new DataParser<CardSpec, JSONObject>() {
                public CardSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new CardSpec(jSONObject);
                }
            });
            this.mCurrentIndex = jSONObject.optInt("current_index", 0);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mTitleTextSpec, i);
            parcel.writeTypedList(this.mCardSpecs);
            parcel.writeInt(this.mCurrentIndex);
        }

        public ArrayList<CardSpec> getCardSpecs() {
            return this.mCardSpecs;
        }

        public int getCurrentIndex() {
            return this.mCurrentIndex;
        }
    }

    public static class SmallHeaderSpec extends BaseModel {
        public static final Creator<SmallHeaderSpec> CREATOR = new Creator<SmallHeaderSpec>() {
            public SmallHeaderSpec createFromParcel(Parcel parcel) {
                return new SmallHeaderSpec(parcel);
            }

            public SmallHeaderSpec[] newArray(int i) {
                return new SmallHeaderSpec[i];
            }
        };
        private int mActionEventId;
        private String mActionText;
        private String mDeeplink;
        private String mFilterId;
        private ArrayList<String> mGradientColors;
        private int mImpressionEventId;
        private String mPromoCodeText;
        private boolean mShowLock;
        private String mText;

        public int describeContents() {
            return 0;
        }

        public SmallHeaderSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        public SmallHeaderSpec(Parcel parcel) {
            this.mText = parcel.readString();
            this.mGradientColors = new ArrayList<>();
            parcel.readList(this.mGradientColors, String.class.getClassLoader());
            this.mPromoCodeText = parcel.readString();
            this.mActionText = parcel.readString();
            this.mFilterId = parcel.readString();
            this.mDeeplink = parcel.readString();
            this.mShowLock = parcel.readByte() != 0;
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mText = jSONObject.optString("text", null);
            this.mGradientColors = JsonUtil.parseArray(jSONObject, "gradient_colors", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException, ParseException {
                    return str;
                }
            });
            this.mPromoCodeText = jSONObject.optString("promo_code", null);
            this.mActionText = jSONObject.optString("action_text", null);
            this.mFilterId = jSONObject.optString("filter_id", null);
            this.mDeeplink = jSONObject.optString("deeplink", null);
            this.mShowLock = jSONObject.optBoolean("show_lock");
            this.mImpressionEventId = jSONObject.optInt("impression_event_id", -1);
            this.mActionEventId = jSONObject.optInt("action_event_id", -1);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mText);
            parcel.writeList(this.mGradientColors);
            parcel.writeString(this.mPromoCodeText);
            parcel.writeString(this.mActionText);
            parcel.writeString(this.mFilterId);
            parcel.writeString(this.mDeeplink);
            parcel.writeByte(this.mShowLock ? (byte) 1 : 0);
            parcel.writeInt(this.mImpressionEventId);
            parcel.writeInt(this.mActionEventId);
        }

        public String getText() {
            return this.mText;
        }

        public int[] getGradientColors() {
            int color = ContextCompat.getColor(WishApplication.getInstance(), R.color.main_primary);
            if (this.mGradientColors == null || this.mGradientColors.size() < 2) {
                Crashlytics.logException(new Exception("NewUserGiftPackSpec SmallHeaderSpec gradient_colors keyshould contain at least 2 values"));
                return new int[]{color, color};
            }
            int[] iArr = new int[this.mGradientColors.size()];
            for (int i = 0; i < this.mGradientColors.size(); i++) {
                iArr[i] = ColorUtil.safeParseColor((String) this.mGradientColors.get(i), color);
            }
            return iArr;
        }

        public String getPromoCodeText() {
            return this.mPromoCodeText;
        }

        public String getActionText() {
            return this.mActionText;
        }

        public String getFilterId() {
            return this.mFilterId;
        }

        public String getDeeplink() {
            return this.mDeeplink;
        }

        public boolean showLock() {
            return this.mShowLock;
        }

        public int getImpressionEventId() {
            return this.mImpressionEventId;
        }

        public int getActionEventId() {
            return this.mActionEventId;
        }

        public PromoActionType getActionType() {
            return NewUserGiftPackSpec.getActionType(this.mFilterId, this.mDeeplink);
        }
    }

    public static class SplashSpec extends BaseModel {
        public static final Creator<SplashSpec> CREATOR = new Creator<SplashSpec>() {
            public SplashSpec createFromParcel(Parcel parcel) {
                return new SplashSpec(parcel);
            }

            public SplashSpec[] newArray(int i) {
                return new SplashSpec[i];
            }
        };
        private String mBackgroundColor;
        private String mButtonBackgroundColor;
        private WishTextViewSpec mButtonText;
        private String mCenterImageUrl;
        private CardSpec mDay1CardSpec;
        private CardSpec mDay2CardSpec;
        private String mDeeplink;
        private String mDividerColor;
        private String mDividerText;
        private ArrayList<String> mHeaderGradientColors;
        private WishTextViewSpec mHeaderSubtext;
        private WishTextViewSpec mHeaderText;
        private int mImpressionEventId;
        private String mPromoBorderColor;
        private WishTextViewSpec mPromoText;
        private WishTextViewSpec mSubText;
        private WishTextViewSpec mText;
        private boolean mUseV3Splash;

        public int describeContents() {
            return 0;
        }

        public SplashSpec(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        public SplashSpec(Parcel parcel) {
            this.mHeaderText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mHeaderSubtext = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mSubText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mPromoText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mButtonText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mCenterImageUrl = parcel.readString();
            this.mDividerColor = parcel.readString();
            this.mDeeplink = parcel.readString();
            this.mBackgroundColor = parcel.readString();
            this.mButtonBackgroundColor = parcel.readString();
            this.mPromoBorderColor = parcel.readString();
            this.mHeaderGradientColors = new ArrayList<>();
            parcel.readList(this.mHeaderGradientColors, String.class.getClassLoader());
            this.mImpressionEventId = parcel.readInt();
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            parseCommonFields(jSONObject);
            if (this.mUseV3Splash) {
                parseV3Fields(jSONObject);
            } else {
                parseV1V2Fields(jSONObject);
            }
        }

        private void parseCommonFields(JSONObject jSONObject) throws JSONException, ParseException {
            if (jSONObject.has("header_text")) {
                this.mHeaderText = new WishTextViewSpec(jSONObject.getJSONObject("header_text"));
            }
            if (jSONObject.has("header_subtext")) {
                this.mHeaderSubtext = new WishTextViewSpec(jSONObject.getJSONObject("header_subtext"));
            }
            if (jSONObject.has("button_text")) {
                this.mButtonText = new WishTextViewSpec(jSONObject.getJSONObject("button_text"));
            }
            this.mDeeplink = jSONObject.optString("deeplink", null);
            this.mBackgroundColor = jSONObject.optString("background_color", null);
            this.mButtonBackgroundColor = jSONObject.optString("button_background_color", null);
            this.mImpressionEventId = jSONObject.optInt("impression_event_id", -1);
            this.mUseV3Splash = jSONObject.optBoolean("use_v3_splash", false);
        }

        private void parseV1V2Fields(JSONObject jSONObject) throws JSONException, ParseException {
            if (jSONObject.has("text")) {
                this.mText = new WishTextViewSpec(jSONObject.getJSONObject("text"));
            }
            if (jSONObject.has("subtext")) {
                this.mSubText = new WishTextViewSpec(jSONObject.getJSONObject("subtext"));
            }
            if (jSONObject.has("promo_text")) {
                this.mPromoText = new WishTextViewSpec(jSONObject.getJSONObject("promo_text"));
            }
            this.mCenterImageUrl = jSONObject.optString("center_img_url", null);
            this.mDividerColor = jSONObject.optString("divider_color", null);
            this.mPromoBorderColor = jSONObject.optString("promo_border_color", null);
            this.mHeaderGradientColors = JsonUtil.parseArray(jSONObject, "header_gradient_colors", new DataParser<String, String>() {
                public String parseData(String str) {
                    return str;
                }
            });
        }

        private void parseV3Fields(JSONObject jSONObject) throws JSONException, ParseException {
            this.mDay1CardSpec = new CardSpec(jSONObject.getJSONObject("day_1_card_spec"));
            this.mDay2CardSpec = new CardSpec(jSONObject.getJSONObject("day_2_card_spec"));
            this.mDividerText = jSONObject.optString("v3_divider_text");
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mHeaderText, i);
            parcel.writeParcelable(this.mHeaderSubtext, i);
            parcel.writeParcelable(this.mText, i);
            parcel.writeParcelable(this.mSubText, i);
            parcel.writeParcelable(this.mPromoText, i);
            parcel.writeParcelable(this.mButtonText, i);
            parcel.writeString(this.mCenterImageUrl);
            parcel.writeString(this.mDividerColor);
            parcel.writeString(this.mDeeplink);
            parcel.writeString(this.mBackgroundColor);
            parcel.writeString(this.mButtonBackgroundColor);
            parcel.writeString(this.mPromoBorderColor);
            parcel.writeList(this.mHeaderGradientColors);
            parcel.writeInt(this.mImpressionEventId);
        }

        public WishTextViewSpec getHeaderText() {
            return this.mHeaderText;
        }

        public WishTextViewSpec getHeaderSubtext() {
            return this.mHeaderSubtext;
        }

        public WishTextViewSpec getText() {
            return this.mText;
        }

        public WishTextViewSpec getSubtext() {
            return this.mSubText;
        }

        public WishTextViewSpec getPromoText() {
            return this.mPromoText;
        }

        public WishTextViewSpec getButtonText() {
            return this.mButtonText;
        }

        public String getCenterImageUrl() {
            return this.mCenterImageUrl;
        }

        public String getDividerColor() {
            return this.mDividerColor;
        }

        public String getDeeplink() {
            return this.mDeeplink;
        }

        public String getBackgroundColor() {
            return this.mBackgroundColor;
        }

        public String getButtonBackgroundColor() {
            return this.mButtonBackgroundColor;
        }

        public String getPromoBorderColor() {
            return this.mPromoBorderColor;
        }

        public boolean shouldUseV3Splash() {
            return this.mUseV3Splash;
        }

        public CardSpec getDay1CardSpec() {
            return this.mDay1CardSpec;
        }

        public CardSpec getDay2CardSpec() {
            return this.mDay2CardSpec;
        }

        public String getDividerText() {
            return this.mDividerText;
        }

        public int[] getGradientColors() {
            int color = ContextCompat.getColor(WishApplication.getInstance(), R.color.main_primary);
            if (this.mHeaderGradientColors == null || this.mHeaderGradientColors.size() < 2) {
                Crashlytics.logException(new Exception("NewUserGiftPackSpec SplashSpec gradient_colors keyshould contain at least 2 values"));
                return new int[]{color, color};
            }
            int[] iArr = new int[this.mHeaderGradientColors.size()];
            for (int i = 0; i < this.mHeaderGradientColors.size(); i++) {
                iArr[i] = ColorUtil.safeParseColor((String) this.mHeaderGradientColors.get(i), color);
            }
            return iArr;
        }

        public int getImpressionEventId() {
            return this.mImpressionEventId;
        }
    }

    public int describeContents() {
        return 0;
    }

    public NewUserGiftPackSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public View getSplashView(BaseDialogFragment baseDialogFragment) {
        if (this.mSplashSpec == null) {
            return null;
        }
        if (this.mSplashSpec.shouldUseV3Splash()) {
            UserGiftPackV3SplashView userGiftPackV3SplashView = new UserGiftPackV3SplashView(baseDialogFragment.getContext());
            userGiftPackV3SplashView.setup(baseDialogFragment, this.mSplashSpec);
            return userGiftPackV3SplashView;
        }
        UserGiftPackSplashView userGiftPackSplashView = new UserGiftPackSplashView(baseDialogFragment.getContext());
        userGiftPackSplashView.setup(baseDialogFragment, this.mSplashSpec);
        return userGiftPackSplashView;
    }

    public View getProductOverviewBannerView(BaseFragment baseFragment, WishAnalyticsEvent wishAnalyticsEvent, WishAnalyticsEvent wishAnalyticsEvent2) {
        if (this.mLargeHeaderSpec == null) {
            return null;
        }
        WishAnalyticsLogger.trackEvent(wishAnalyticsEvent);
        GiftPackFeedHeaderView giftPackFeedHeaderView = new GiftPackFeedHeaderView(baseFragment.getContext());
        giftPackFeedHeaderView.setup(this.mLargeHeaderSpec, baseFragment);
        return giftPackFeedHeaderView;
    }

    public CollapsableFeedHeaderView getFeedBannerView(ProductFeedFragment productFeedFragment, WishAnalyticsEvent wishAnalyticsEvent, WishAnalyticsEvent wishAnalyticsEvent2) {
        if (this.mSmallHeaderSpec == null || this.mLargeHeaderSpec == null) {
            return null;
        }
        WishAnalyticsLogger.trackEvent(wishAnalyticsEvent);
        GiftPackFeedCollapsableHeaderView giftPackFeedCollapsableHeaderView = new GiftPackFeedCollapsableHeaderView(productFeedFragment.getContext());
        giftPackFeedCollapsableHeaderView.setup(productFeedFragment, this.mSmallHeaderSpec, this.mLargeHeaderSpec);
        return giftPackFeedCollapsableHeaderView;
    }

    public WishDailyLoginStampSpec getStampSpec() {
        return this.mDailyLoginStampSpec;
    }

    public static PromoActionType getActionType(String str, String str2) {
        if (str != null && !str.isEmpty()) {
            return PromoActionType.FILTER_ID;
        }
        if (str2 == null || str2.isEmpty()) {
            return PromoActionType.UNKNOWN;
        }
        return PromoActionType.DEEP_LINK;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "small_header")) {
            this.mSmallHeaderSpec = new SmallHeaderSpec(jSONObject.getJSONObject("small_header"));
        }
        if (JsonUtil.hasValue(jSONObject, "large_header")) {
            this.mLargeHeaderSpec = new LargeHeaderSpec(jSONObject.getJSONObject("large_header"));
        }
        if (JsonUtil.hasValue(jSONObject, "splash")) {
            this.mSplashSpec = new SplashSpec(jSONObject.getJSONObject("splash"));
        }
        if (JsonUtil.hasValue(jSONObject, "product_details_splash")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("product_details_splash");
            if (!jSONObject2.isNull("daily_login_splash")) {
                this.mDailyLoginStampSpec = new WishDailyLoginStampSpec(jSONObject2.getJSONObject("daily_login_splash"));
            }
        }
    }

    public NewUserGiftPackSpec(Parcel parcel) {
        this.mLargeHeaderSpec = (LargeHeaderSpec) parcel.readParcelable(LargeHeaderSpec.class.getClassLoader());
        this.mSmallHeaderSpec = (SmallHeaderSpec) parcel.readParcelable(SmallHeaderSpec.class.getClassLoader());
        this.mSplashSpec = (SplashSpec) parcel.readParcelable(SplashSpec.class.getClassLoader());
        this.mDailyLoginStampSpec = (WishDailyLoginStampSpec) parcel.readParcelable(WishDailyLoginStampSpec.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mLargeHeaderSpec, i);
        parcel.writeParcelable(this.mSmallHeaderSpec, i);
        parcel.writeParcelable(this.mSplashSpec, i);
        parcel.writeParcelable(this.mDailyLoginStampSpec, i);
    }
}
