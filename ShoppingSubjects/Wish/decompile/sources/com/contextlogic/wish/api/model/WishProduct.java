package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventBundle;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventCallback;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WishProduct extends BaseModel implements Parcelable, ApplicationEventCallback {
    public static final Creator<WishProduct> CREATOR = new Creator<WishProduct>() {
        public WishProduct createFromParcel(Parcel parcel) {
            return new WishProduct(parcel);
        }

        public WishProduct[] newArray(int i) {
            return new WishProduct[i];
        }
    };
    public static int MAX_PRICE_RANGE_INDEX = 1;
    public static int MIN_PRICE_RANGE_INDEX;
    private WishAddToCartOffer mAddToCartOffer;
    private double mAspectRatio;
    private WishBrand mAuthorizedBrand;
    private double mAverageRating;
    private WishShippingBadge mBadge;
    private String mBrand;
    private ArrayList<String> mBundledProductIds;
    private String mBundledProductsTitle;
    private String mCommerceProductId;
    private WishLocalizedCurrencyValue mCommerceValue;
    private WishCredit mCredit;
    private String mCreditId;
    private int mDailyGiveawayInventory;
    private int mDailyGiveawayQuantity;
    private int mDailyRaffleDiscountQuantity;
    private int mDailyRaffleEntryNumber;
    private String mDefaultCommerceVariationId;
    private WishShippingOption mDefaultExpressShippingOption;
    private WishShippingOption mDefaultStandardShippingOption;
    private String mDescription;
    private ArrayList<WishProductExtraImage> mExtraPhotos;
    private String mFeedTileText;
    private String mGroupBuyAddToCartModalText;
    private String mGroupBuyExtraDescription;
    private String mGroupBuyJoinDescription;
    private WishLocalizedCurrencyValue mGroupBuyPrice;
    private WishLocalizedCurrencyValue mGroupBuyRebate;
    private String mGroupBuyRebateText;
    private ArrayList<String> mHiddenVariationColors;
    private ArrayList<String> mHiddenVariationSizes;
    private WishImage mImage;
    private boolean mInStock;
    private boolean mIsAlreadyWishing;
    private boolean mIsCommerceProduct;
    private boolean mIsDailyGiveaway;
    private boolean mIsDealDash;
    private boolean mIsNew;
    private boolean mIsPreview;
    private boolean mIsPriceWatched;
    private boolean mIsWishlistNewProduct;
    private boolean mLoadDetailsOverviewExpressItems;
    private boolean mLoadDetailsRelatedExpressItems;
    private HashMap<String, String> mLoggingFields;
    private int mMaxShippingTime;
    private String mMerchantId;
    private ArrayList<String> mMerchantInfoDetailBodies;
    private ArrayList<String> mMerchantInfoDetailTitles;
    private String mMerchantInfoImageUrl;
    private String mMerchantInfoSubtitle;
    private String mMerchantInfoTitle;
    private String mMerchantName;
    private double mMerchantRating;
    private int mMerchantRatingCount;
    private String mMerchantUniqueName;
    private String mMerchantUserId;
    private int mMinShippingTime;
    private String mName;
    private int mNumBought;
    private int mNumWishes;
    private double mOriginalImageHeight;
    private double mOriginalImageWidth;
    private String mPartnerBuyButtonPromoMessage;
    private PriceChopProductDetail mPriceChopProductDetail;
    private String mPriceReplacementSubText;
    private String mPriceReplacementText;
    private ArrayList<WishProductBadge> mProductBadges;
    private WishProductBoostFeedTileLabelSpec mProductBoostFeedTileLabelSpec;
    private ArrayList<WishPromotionProductDetailsStripeSpec> mProductDetailsDiscountStripeSpecs;
    private String mProductId;
    private WishPromotionSpec mPromotionSpec;
    private int mRatingCount;
    private WishRatingSizeSummary mRatingSizeSummary;
    private String mReturnPolicyLongString;
    private String mReturnPolicyShortString;
    private WishScreenshotShareInfo mScreenshotShareInfo;
    private String mShareMessage;
    private String mShareSubject;
    private String mShippingCountriesString;
    private String mShippingOfferText;
    private String mShippingOfferTitle;
    private Map<String, List<WishShippingOption>> mShippingOptionToPriceRanges;
    private WishLocalizedCurrencyValue mShippingPrice;
    private String mShippingPriceCountry;
    private String mShippingTimeString;
    private String mShipsFrom;
    private WishLocalizedCurrencyValue mSignupGiftPrice;
    private String mSizingChartUrl;
    private WishImageSlideshow mSlideshow;
    private String mTaxText;
    private int mTileBarMaxValue;
    private String mTileBarText;
    private int mTileBarValue;
    private VideoStatus mTileVideoStatus;
    private ArrayList<WishRating> mTopMerchantRatings;
    private ArrayList<WishRating> mTopRatings;
    private int mTotalInventory;
    private String mUrgencyText;
    private WishUser mUserCreator;
    private WishUsersCurrentlyViewing mUsersCurrentlyViewing;
    private WishLocalizedCurrencyValue mValue;
    private ArrayList<String> mVariationColors;
    private HashMap<String, String> mVariationColorsToHexes;
    private HashMap<String, WishLocalizedCurrencyValue> mVariationGroupPriceMapping;
    private HashMap<String, WishProductVariation> mVariationIdMapping;
    private HashMap<String, WishProductExtraImage> mVariationImageMapping;
    private HashMap<String, WishLocalizedCurrencyValue> mVariationPriceBeforeDiscountsMapping;
    private HashMap<String, WishLocalizedCurrencyValue> mVariationPriceMapping;
    private HashMap<String, Integer> mVariationQuantityMapping;
    private HashMap<String, WishLocalizedCurrencyValue> mVariationRetailPriceMapping;
    private HashMap<String, ArrayList<WishShippingOption>> mVariationShippingOptionsMapping;
    private ArrayList<String> mVariationSizes;
    private WishProductVideoInfo mVideoInfo;
    private String mWishGuaranteeText;
    private WishPartnerProductDetailInfo mWishPartnerInfo;
    private String mWishlistTooltipText;

    public enum VideoStatus implements Parcelable {
        PLAYED(1),
        SKIPPED(2),
        NO_VIDEO(3);
        
        public static final Creator<VideoStatus> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<VideoStatus>() {
                public VideoStatus createFromParcel(Parcel parcel) {
                    return VideoStatus.values()[parcel.readInt()];
                }

                public VideoStatus[] newArray(int i) {
                    return new VideoStatus[i];
                }
            };
        }

        private VideoStatus(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    private static class WishProductSize implements Parcelable {
        public static final Creator<WishProductSize> CREATOR = new Creator<WishProductSize>() {
            public WishProductSize createFromParcel(Parcel parcel) {
                return new WishProductSize(parcel);
            }

            public WishProductSize[] newArray(int i) {
                return new WishProductSize[i];
            }
        };
        public String ordering;
        public Double orderingValue;
        public String size;

        public int describeContents() {
            return 0;
        }

        public WishProductSize() {
        }

        public WishProductSize(String str, String str2) {
            this.size = str;
            this.ordering = str2;
            try {
                this.orderingValue = Double.valueOf(str2);
            } catch (Throwable unused) {
            }
        }

        protected WishProductSize(Parcel parcel) {
            this.orderingValue = Double.valueOf(parcel.readDouble());
            this.ordering = parcel.readString();
            this.size = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(this.orderingValue.doubleValue());
            parcel.writeString(this.ordering);
            parcel.writeString(this.size);
        }
    }

    private static class WishProductVariation implements Parcelable {
        public static final Creator<WishProductVariation> CREATOR = new Creator<WishProductVariation>() {
            public WishProductVariation createFromParcel(Parcel parcel) {
                return new WishProductVariation(parcel);
            }

            public WishProductVariation[] newArray(int i) {
                return new WishProductVariation[i];
            }
        };
        public String color;
        public String colorHex;
        public String size;
        public int valueInPoints;

        public int describeContents() {
            return 0;
        }

        public WishProductVariation() {
        }

        protected WishProductVariation(Parcel parcel) {
            this.color = parcel.readString();
            this.size = parcel.readString();
            this.colorHex = parcel.readString();
            this.valueInPoints = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.color);
            parcel.writeString(this.size);
            parcel.writeString(this.colorHex);
            parcel.writeInt(this.valueInPoints);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WishProductVariation wishProductVariation = (WishProductVariation) obj;
            if (this.color == null ? wishProductVariation.color != null : !this.color.equals(wishProductVariation.color)) {
                return false;
            }
            if (this.colorHex == null ? wishProductVariation.colorHex != null : !this.colorHex.equals(wishProductVariation.colorHex)) {
                return false;
            }
            if (this.size != null) {
                z = this.size.equals(wishProductVariation.size);
            } else if (wishProductVariation.size != null) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (this.color != null ? this.color.hashCode() : 0) * 31;
            if (this.size != null) {
                i = this.size.hashCode();
            }
            return hashCode + i;
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishProduct(String str) {
        this(str, null);
    }

    public WishProduct(String str, String str2) {
        this.mVariationColors = new ArrayList<>();
        this.mVariationSizes = new ArrayList<>();
        this.mHiddenVariationColors = new ArrayList<>();
        this.mHiddenVariationSizes = new ArrayList<>();
        this.mVariationColorsToHexes = new HashMap<>();
        this.mVariationIdMapping = new HashMap<>();
        this.mVariationQuantityMapping = new HashMap<>();
        this.mVariationPriceBeforeDiscountsMapping = new HashMap<>();
        this.mVariationPriceMapping = new HashMap<>();
        this.mVariationGroupPriceMapping = new HashMap<>();
        this.mVariationRetailPriceMapping = new HashMap<>();
        this.mVariationImageMapping = new HashMap<>();
        this.mLoggingFields = new HashMap<>();
        this.mValue = new WishLocalizedCurrencyValue(0.0d);
        this.mProductBadges = new ArrayList<>();
        this.mVariationShippingOptionsMapping = new HashMap<>();
        this.mDefaultExpressShippingOption = null;
        this.mDefaultStandardShippingOption = null;
        this.mShippingOptionToPriceRanges = new HashMap();
        this.mProductId = str;
        if (str2 != null) {
            this.mImage = new WishImage(str2);
        }
        this.mIsPreview = true;
        this.mTileVideoStatus = VideoStatus.NO_VIDEO;
    }

    public WishProduct(String str, WishImage wishImage, WishLocalizedCurrencyValue wishLocalizedCurrencyValue, double d) {
        this(str);
        this.mImage = wishImage;
        this.mValue = wishLocalizedCurrencyValue;
        this.mAspectRatio = d;
    }

    public WishProduct(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        int i;
        JSONArray jSONArray;
        String str;
        ArrayList arrayList;
        String str2;
        JSONObject jSONObject2;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        HashMap hashMap;
        JSONObject jSONObject3 = jSONObject;
        this.mVariationColors = new ArrayList<>();
        this.mVariationSizes = new ArrayList<>();
        this.mHiddenVariationColors = new ArrayList<>();
        this.mHiddenVariationSizes = new ArrayList<>();
        this.mShippingOptionToPriceRanges = new HashMap();
        this.mVariationColorsToHexes = new HashMap<>();
        this.mVariationPriceBeforeDiscountsMapping = new HashMap<>();
        this.mVariationIdMapping = new HashMap<>();
        this.mVariationQuantityMapping = new HashMap<>();
        this.mVariationPriceMapping = new HashMap<>();
        this.mVariationGroupPriceMapping = new HashMap<>();
        this.mVariationRetailPriceMapping = new HashMap<>();
        this.mVariationImageMapping = new HashMap<>();
        this.mLoggingFields = new HashMap<>();
        this.mValue = new WishLocalizedCurrencyValue(0.0d);
        this.mVariationShippingOptionsMapping = new HashMap<>();
        this.mTileVideoStatus = VideoStatus.NO_VIDEO;
        boolean z = true;
        if (!jSONObject3.has("picture") || jSONObject3.has("display_picture")) {
            WishImage wishImage = new WishImage(jSONObject3.getString("display_picture"), jSONObject3.getString("small_picture"), jSONObject3.getString("display_picture"), jSONObject3.getString("contest_page_picture"), null);
            this.mImage = wishImage;
            this.mIsPreview = false;
        } else {
            this.mImage = new WishImage(jSONObject3.getString("picture"));
            this.mIsPreview = true;
        }
        if (jSONObject3.has("cache_buster") && !jSONObject3.isNull("cache_buster")) {
            this.mImage.setCacheBuster(jSONObject3.getString("cache_buster"));
        }
        this.mProductId = jSONObject3.getString("id");
        this.mName = jSONObject3.getString("name");
        ApplicationEventManager.getInstance().addCallback(EventType.PRODUCT_WISH, this.mProductId, this);
        ApplicationEventManager.getInstance().addCallback(EventType.PRODUCT_UNWISH, this.mProductId, this);
        if (JsonUtil.hasValue(jSONObject3, "value")) {
            this.mValue = new WishLocalizedCurrencyValue(jSONObject3.getDouble("value"), jSONObject3.optJSONObject("localized_value"));
        }
        if (JsonUtil.hasValue(jSONObject3, "urgency_text")) {
            this.mUrgencyText = jSONObject3.getString("urgency_text");
        }
        if (JsonUtil.hasValue(jSONObject3, "feed_tile_text")) {
            this.mFeedTileText = jSONObject3.getString("feed_tile_text");
        }
        if (JsonUtil.hasValue(jSONObject3, "price_replacement_text")) {
            this.mPriceReplacementText = jSONObject3.getString("price_replacement_text");
        }
        if (JsonUtil.hasValue(jSONObject3, "price_replacement_sub_text")) {
            this.mPriceReplacementSubText = jSONObject3.getString("price_replacement_sub_text");
        }
        if (JsonUtil.hasValue(jSONObject3, "is_wishlist_new_product")) {
            this.mIsWishlistNewProduct = jSONObject3.getBoolean("is_wishlist_new_product");
        }
        if (!this.mIsPreview) {
            this.mAspectRatio = jSONObject3.getDouble("aspect_ratio");
            this.mIsAlreadyWishing = jSONObject3.has("user_in_active_sweep") && jSONObject3.getBoolean("user_in_active_sweep");
            this.mTopRatings = JsonUtil.parseArray(jSONObject3, "top_ratings", new DataParser<WishRating, JSONObject>() {
                public WishRating parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishRating(jSONObject);
                }
            });
            this.mTopMerchantRatings = JsonUtil.parseArray(jSONObject3, "top_merchant_ratings", new DataParser<WishRating, JSONObject>() {
                public WishRating parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishRating(jSONObject);
                }
            });
            this.mProductBadges = JsonUtil.parseArray(jSONObject3, "product_badges", new DataParser<WishProductBadge, JSONObject>() {
                public WishProductBadge parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishProductBadge(jSONObject);
                }
            });
            this.mTileBarMaxValue = jSONObject3.optInt("tile_bar_max_value");
            this.mTileBarValue = jSONObject3.optInt("tile_bar_value");
            if (JsonUtil.hasValue(jSONObject3, "tile_bar_text")) {
                this.mTileBarText = jSONObject3.getString("tile_bar_text");
            }
            this.mExtraPhotos = new ArrayList<>();
            HashMap hashMap2 = new HashMap();
            if (jSONObject3.has("extra_photo_urls")) {
                JSONObject jSONObject4 = jSONObject3.getJSONObject("extra_photo_urls");
                Iterator keys = jSONObject4.keys();
                String string = jSONObject3.has("extra_photo_cache_bust") ? jSONObject3.getString("extra_photo_cache_bust") : null;
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    int intValue = Integer.valueOf(str3).intValue();
                    String string2 = jSONObject4.getString(str3);
                    if (!string2.isEmpty()) {
                        WishProductExtraImage wishProductExtraImage = new WishProductExtraImage(intValue, string2, string, string2.contains("video"));
                        wishProductExtraImage.setIsUgc(true);
                        this.mExtraPhotos.add(wishProductExtraImage);
                        hashMap2.put(Integer.valueOf(intValue), wishProductExtraImage);
                    }
                }
                if (this.mExtraPhotos.size() > 0) {
                    Collections.sort(this.mExtraPhotos, new Comparator<WishProductExtraImage>() {
                        public int compare(WishProductExtraImage wishProductExtraImage, WishProductExtraImage wishProductExtraImage2) {
                            return wishProductExtraImage.getSequenceId() - wishProductExtraImage2.getSequenceId();
                        }
                    });
                }
            }
            if (JsonUtil.hasValue(jSONObject3, "extra_photo_details")) {
                JSONObject jSONObject5 = jSONObject3.getJSONObject("extra_photo_details");
                Iterator keys2 = jSONObject5.keys();
                while (keys2.hasNext()) {
                    String str4 = (String) keys2.next();
                    WishProductExtraImage wishProductExtraImage2 = (WishProductExtraImage) hashMap2.get(Integer.valueOf(str4));
                    if (wishProductExtraImage2 != null) {
                        try {
                            JSONObject jSONObject6 = jSONObject5.getJSONObject(str4);
                            wishProductExtraImage2.setUploader(new WishUser(jSONObject6.getJSONObject("user")));
                            if (jSONObject6.optBoolean("is_video") && JsonUtil.hasValue(jSONObject6, "ext_urls")) {
                                wishProductExtraImage2.setVideoInfoQuality(jSONObject6.getJSONObject("ext_urls"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "size")) {
                                wishProductExtraImage2.setSize(jSONObject6.getString("size"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "timestamp")) {
                                wishProductExtraImage2.setTimestamp(DateUtil.parseIsoDate(jSONObject6.getString("timestamp")));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "rating")) {
                                wishProductExtraImage2.setRating(jSONObject6.getInt("rating"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "comment")) {
                                wishProductExtraImage2.setComment(jSONObject6.getString("comment"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "upvote_count")) {
                                wishProductExtraImage2.setUpvoteCount(jSONObject6.getInt("upvote_count"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "user_upvoted")) {
                                wishProductExtraImage2.setHasUserUpvoted(jSONObject6.getBoolean("user_upvoted"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "rating_id")) {
                                wishProductExtraImage2.setRatingId(jSONObject6.getString("rating_id"));
                            }
                            if (JsonUtil.hasValue(jSONObject6, "thumbnail")) {
                                wishProductExtraImage2.setVideoThumbnail(jSONObject6.getString("thumbnail"));
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            if (jSONObject3.has("num_entered")) {
                this.mNumWishes = jSONObject3.getInt("num_entered");
            } else if (jSONObject3.has("num_wishes")) {
                this.mNumWishes = jSONObject3.getInt("num_wishes");
            } else {
                this.mNumWishes = 0;
            }
            this.mNumBought = jSONObject3.optInt("num_bought", 0);
            if (jSONObject3.has("user_creator")) {
                this.mUserCreator = new WishUser(jSONObject3.getJSONObject("user_creator"));
            }
            if (JsonUtil.hasValue(jSONObject3, "orig_width")) {
                this.mOriginalImageWidth = jSONObject3.getDouble("orig_width");
            } else {
                this.mOriginalImageWidth = -1.0d;
            }
            if (JsonUtil.hasValue(jSONObject3, "orig_height")) {
                this.mOriginalImageHeight = jSONObject3.getDouble("orig_height");
            } else {
                this.mOriginalImageHeight = -1.0d;
            }
            if (JsonUtil.hasValue(jSONObject3, "brand")) {
                this.mBrand = jSONObject3.getString("brand");
            } else {
                this.mBrand = null;
            }
            if (JsonUtil.hasValue(jSONObject3, "description")) {
                this.mDescription = jSONObject3.getString("description");
                if (this.mDescription.trim().equals("")) {
                    this.mDescription = null;
                }
            }
            if (JsonUtil.hasValue(jSONObject3, "sizing_chart_url")) {
                this.mSizingChartUrl = jSONObject3.getString("sizing_chart_url");
                if (this.mSizingChartUrl.trim().equals("")) {
                    this.mSizingChartUrl = null;
                }
            }
            if (JsonUtil.hasValue(jSONObject3, "shipping_offer_title")) {
                this.mShippingOfferTitle = jSONObject3.getString("shipping_offer_title");
            }
            if (JsonUtil.hasValue(jSONObject3, "shipping_offer_text")) {
                this.mShippingOfferText = jSONObject3.getString("shipping_offer_text");
            }
            if (JsonUtil.hasValue(jSONObject3, "wish_guarantee")) {
                this.mWishGuaranteeText = jSONObject3.getString("wish_guarantee");
            }
            if (JsonUtil.hasValue(jSONObject3, "share_subject")) {
                this.mShareSubject = jSONObject3.getString("share_subject");
            }
            if (JsonUtil.hasValue(jSONObject3, "share_message")) {
                this.mShareMessage = jSONObject3.getString("share_message");
            }
            if (JsonUtil.hasValue(jSONObject3, "signup_gift_price")) {
                this.mSignupGiftPrice = new WishLocalizedCurrencyValue(jSONObject3.optDouble("signup_gift_price"), jSONObject3.optJSONObject("localized_signup_gift_price"));
            }
            if (JsonUtil.hasValue(jSONObject3, "add_to_cart_offer")) {
                this.mAddToCartOffer = new WishAddToCartOffer(jSONObject3.getJSONObject("add_to_cart_offer"));
            }
            this.mPartnerBuyButtonPromoMessage = JsonUtil.optString(jSONObject3, "partner_buy_button_promo_message");
            if (JsonUtil.hasValue(jSONObject3, "partner_info")) {
                this.mWishPartnerInfo = new WishPartnerProductDetailInfo(jSONObject3.getJSONObject("partner_info"));
            }
            if (JsonUtil.hasValue(jSONObject3, "rating_size_summary")) {
                this.mRatingSizeSummary = new WishRatingSizeSummary(jSONObject3.getJSONObject("rating_size_summary"));
            }
            if (JsonUtil.hasValue(jSONObject3, "authorized_brand")) {
                this.mAuthorizedBrand = new WishBrand(jSONObject3.getJSONObject("authorized_brand"));
            }
            if (jSONObject3.has("product_rating")) {
                JSONObject jSONObject7 = jSONObject3.getJSONObject("product_rating");
                this.mRatingCount = jSONObject7.getInt("rating_count");
                this.mAverageRating = jSONObject7.getDouble("rating");
            } else {
                this.mRatingCount = 0;
                this.mAverageRating = 5.0d;
            }
            this.mIsNew = jSONObject3.optBoolean("is_new", false);
            this.mCommerceProductId = this.mProductId;
            if (JsonUtil.hasValue(jSONObject3, "commerce_product_info")) {
                this.mIsCommerceProduct = true;
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                JSONObject jSONObject8 = jSONObject3.getJSONObject("commerce_product_info");
                this.mCommerceProductId = jSONObject8.getString("id");
                this.mTotalInventory = jSONObject8.optInt("total_inventory", 0);
                if (this.mTotalInventory <= 0) {
                    z = false;
                }
                this.mInStock = z;
                this.mIsDailyGiveaway = jSONObject8.optBoolean("is_daily_giveaway", false);
                this.mDailyGiveawayInventory = jSONObject8.optInt("daily_giveaway_inventory", 0);
                this.mDailyGiveawayQuantity = jSONObject8.optInt("daily_giveaway_quantity", 0);
                this.mDailyRaffleDiscountQuantity = jSONObject8.optInt("daily_giveaway_discount_quantity", 0);
                this.mDailyRaffleEntryNumber = jSONObject8.optInt("daily_raffle_entry_number", 0);
                if (JsonUtil.hasValue(jSONObject8, "aggregated_shipping_ranges")) {
                    JSONObject jSONObject9 = jSONObject8.getJSONObject("aggregated_shipping_ranges");
                    Iterator keys3 = jSONObject9.keys();
                    while (keys3.hasNext()) {
                        String str5 = (String) keys3.next();
                        ArrayList arrayList7 = new ArrayList();
                        arrayList7.add(MIN_PRICE_RANGE_INDEX, new WishShippingOption(jSONObject9.getJSONObject(str5).getJSONObject("min")));
                        arrayList7.add(MAX_PRICE_RANGE_INDEX, new WishShippingOption(jSONObject9.getJSONObject(str5).getJSONObject("max")));
                        this.mShippingOptionToPriceRanges.put(str5, arrayList7);
                    }
                }
                if (JsonUtil.hasValue(jSONObject8, "variations")) {
                    JSONArray jSONArray2 = jSONObject8.getJSONArray("variations");
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        JSONObject jSONObject10 = jSONArray2.getJSONObject(i2);
                        String str6 = "0";
                        ArrayList arrayList8 = new ArrayList();
                        if (JsonUtil.hasValue(jSONObject10, "size_ordering")) {
                            str6 = jSONObject10.getString("size_ordering");
                        }
                        String string3 = JsonUtil.hasValue(jSONObject10, "size") ? jSONObject10.getString("size") : null;
                        String string4 = JsonUtil.hasValue(jSONObject10, "color") ? jSONObject10.getString("color") : null;
                        String string5 = JsonUtil.hasValue(jSONObject10, "color_hex") ? jSONObject10.getString("color_hex") : null;
                        String string6 = JsonUtil.hasValue(jSONObject10, "hidden_size") ? jSONObject10.getString("hidden_size") : null;
                        if (JsonUtil.hasValue(jSONObject10, "hidden_color")) {
                            str = jSONObject10.getString("hidden_color");
                            jSONArray = jSONArray2;
                        } else {
                            jSONArray = jSONArray2;
                            str = null;
                        }
                        if (JsonUtil.hasValue(jSONObject10, "variation_id")) {
                            str2 = jSONObject10.getString("variation_id");
                            arrayList = arrayList8;
                        } else {
                            arrayList = arrayList8;
                            str2 = null;
                        }
                        int i3 = JsonUtil.hasValue(jSONObject10, "value_in_points") ? jSONObject10.getInt("value_in_points") : -1;
                        if (JsonUtil.hasValue(jSONObject10, "shipping_options")) {
                            jSONObject2 = jSONObject8;
                            arrayList2 = JsonUtil.parseArray(jSONObject10, "shipping_options", new DataParser<WishShippingOption, JSONObject>() {
                                public WishShippingOption parseData(JSONObject jSONObject) throws JSONException, ParseException {
                                    return new WishShippingOption(jSONObject);
                                }
                            });
                        } else {
                            jSONObject2 = jSONObject8;
                            arrayList2 = arrayList;
                        }
                        int i4 = i2;
                        int optInt = jSONObject10.optInt("sequence_id", -1);
                        if (str2 != null) {
                            if (string4 != null && !this.mVariationColors.contains(string4)) {
                                this.mVariationColors.add(string4);
                            }
                            if (string3 != null) {
                                arrayList6.add(new WishProductSize(string3, str6));
                            }
                            if (string5 != null) {
                                this.mVariationColorsToHexes.put(string4, string5);
                            }
                            if (str != null && !this.mHiddenVariationColors.contains(str)) {
                                this.mHiddenVariationColors.add(str);
                            }
                            if (string6 != null) {
                                arrayList5.add(new WishProductSize(string6, str6));
                            }
                            WishProductVariation wishProductVariation = new WishProductVariation();
                            wishProductVariation.size = string3;
                            wishProductVariation.color = string4;
                            wishProductVariation.colorHex = string5;
                            wishProductVariation.valueInPoints = i3;
                            this.mVariationIdMapping.put(str2, wishProductVariation);
                            if (optInt != -1 && hashMap2.containsKey(Integer.valueOf(optInt))) {
                                this.mVariationImageMapping.put(str2, hashMap2.get(Integer.valueOf(optInt)));
                            }
                            int i5 = jSONObject10.getInt("inventory");
                            WishLocalizedCurrencyValue wishLocalizedCurrencyValue = new WishLocalizedCurrencyValue(jSONObject10.getDouble("price"), jSONObject10.optJSONObject("localized_price"));
                            WishLocalizedCurrencyValue wishLocalizedCurrencyValue2 = JsonUtil.hasValue(jSONObject10, "group_buy_price") ? new WishLocalizedCurrencyValue(jSONObject10.getDouble("group_buy_price"), jSONObject10.optJSONObject("localized_group_buy_price")) : null;
                            WishLocalizedCurrencyValue wishLocalizedCurrencyValue3 = JsonUtil.hasValue(jSONObject10, "group_buy_rebate_amount") ? new WishLocalizedCurrencyValue(jSONObject10.getDouble("group_buy_rebate_amount"), jSONObject10.getJSONObject("localized_group_buy_rebate_amount")) : null;
                            String string7 = JsonUtil.hasValue(jSONObject10, "group_buy_rebate_text") ? jSONObject10.getString("group_buy_rebate_text") : null;
                            String string8 = JsonUtil.hasValue(jSONObject10, "group_buy_join_description_text") ? jSONObject10.getString("group_buy_join_description_text") : null;
                            String string9 = JsonUtil.hasValue(jSONObject10, "group_buy_create_description_text") ? jSONObject10.getString("group_buy_create_description_text") : null;
                            hashMap = hashMap2;
                            ArrayList arrayList9 = arrayList2;
                            arrayList4 = arrayList6;
                            int i6 = i5;
                            String string10 = JsonUtil.hasValue(jSONObject10, "group_buy_add_to_cart_modal_create_text") ? jSONObject10.getString("group_buy_add_to_cart_modal_create_text") : null;
                            WishLocalizedCurrencyValue wishLocalizedCurrencyValue4 = new WishLocalizedCurrencyValue(jSONObject10.getDouble("price_before_personal_price"), jSONObject10.optJSONObject("localized_price_before_personal_price"));
                            WishLocalizedCurrencyValue wishLocalizedCurrencyValue5 = new WishLocalizedCurrencyValue(jSONObject10.optDouble("retail_price", 0.0d), jSONObject10.optJSONObject("localized_retail_price"));
                            if (wishLocalizedCurrencyValue5.getValue() == 0.0d) {
                                wishLocalizedCurrencyValue5 = wishLocalizedCurrencyValue;
                            }
                            if (this.mDefaultCommerceVariationId == null) {
                                String optString = JsonUtil.optString(jSONObject10, "merchant");
                                String optString2 = JsonUtil.optString(jSONObject10, "merchant_name");
                                String optString3 = JsonUtil.optString(jSONObject10, "merchant_id");
                                String userId = (!jSONObject10.optBoolean("is_c2c", false) || this.mUserCreator == null) ? null : this.mUserCreator.getUserId();
                                if (JsonUtil.hasValue(jSONObject10, "tax_text")) {
                                    this.mTaxText = jSONObject10.getString("tax_text");
                                }
                                if (JsonUtil.hasValue(jSONObject10, "return_policy_short")) {
                                    this.mReturnPolicyShortString = jSONObject10.getString("return_policy_short");
                                }
                                this.mReturnPolicyLongString = jSONObject10.optString("return_policy_long");
                                if (JsonUtil.hasValue(jSONObject10, "shipping_price_country_code")) {
                                    this.mShippingPriceCountry = AddressUtil.getCountryName(jSONObject10.getString("shipping_price_country_code"));
                                }
                                arrayList3 = arrayList5;
                                this.mMinShippingTime = jSONObject10.optInt("min_shipping_time", 7);
                                this.mMaxShippingTime = jSONObject10.optInt("max_shipping_time", 21);
                                String str7 = string8;
                                String str8 = string9;
                                this.mShippingPrice = new WishLocalizedCurrencyValue(jSONObject10.getDouble("shipping"), jSONObject10.optJSONObject("localized_shipping"));
                                if (JsonUtil.hasValue(jSONObject10, "shipping_countries_string")) {
                                    this.mShippingCountriesString = jSONObject10.getString("shipping_countries_string");
                                }
                                if (JsonUtil.hasValue(jSONObject10, "shipping_time_string")) {
                                    this.mShippingTimeString = jSONObject10.getString("shipping_time_string");
                                }
                                if (JsonUtil.hasValue(jSONObject10, "ships_from")) {
                                    this.mShipsFrom = jSONObject10.getString("ships_from");
                                }
                                this.mIsPriceWatched = jSONObject10.optBoolean("user_is_watching_price", false);
                                this.mIsDealDash = jSONObject10.optBoolean("is_deal_dash", false);
                                this.mValue = wishLocalizedCurrencyValue5;
                                this.mDefaultCommerceVariationId = str2;
                                this.mCommerceValue = wishLocalizedCurrencyValue;
                                this.mGroupBuyPrice = wishLocalizedCurrencyValue2;
                                this.mMerchantName = optString;
                                this.mMerchantUniqueName = optString2;
                                this.mMerchantUserId = userId;
                                this.mMerchantRating = jSONObject10.optDouble("merchant_rating");
                                this.mMerchantRatingCount = jSONObject10.optInt("merchant_rating_count");
                                this.mMerchantId = optString3;
                                this.mGroupBuyRebateText = string7;
                                this.mGroupBuyRebate = wishLocalizedCurrencyValue3;
                                this.mGroupBuyJoinDescription = str7;
                                this.mGroupBuyExtraDescription = str8;
                                this.mGroupBuyAddToCartModalText = string10;
                            } else {
                                arrayList3 = arrayList5;
                            }
                            this.mVariationPriceMapping.put(str2, wishLocalizedCurrencyValue);
                            if (wishLocalizedCurrencyValue2 != null) {
                                this.mVariationGroupPriceMapping.put(str2, wishLocalizedCurrencyValue2);
                            }
                            this.mVariationPriceBeforeDiscountsMapping.put(str2, wishLocalizedCurrencyValue4);
                            this.mVariationRetailPriceMapping.put(str2, wishLocalizedCurrencyValue5);
                            this.mVariationQuantityMapping.put(str2, Integer.valueOf(i6));
                            if (arrayList9 != null) {
                                ArrayList arrayList10 = arrayList9;
                                if (arrayList10.size() > 0) {
                                    this.mVariationShippingOptionsMapping.put(str2, arrayList10);
                                }
                            }
                        } else {
                            hashMap = hashMap2;
                            arrayList3 = arrayList5;
                            arrayList4 = arrayList6;
                        }
                        i2 = i4 + 1;
                        jSONArray2 = jSONArray;
                        jSONObject8 = jSONObject2;
                        hashMap2 = hashMap;
                        arrayList6 = arrayList4;
                        arrayList5 = arrayList3;
                        JSONObject jSONObject11 = jSONObject;
                    }
                }
                ArrayList arrayList11 = arrayList6;
                JSONObject jSONObject12 = jSONObject8;
                i = 0;
                ArrayList arrayList12 = arrayList5;
                final boolean areSizesAllNumeric = areSizesAllNumeric(arrayList12);
                Collections.sort(arrayList12, new Comparator<WishProductSize>() {
                    public int compare(WishProductSize wishProductSize, WishProductSize wishProductSize2) {
                        if (areSizesAllNumeric) {
                            return wishProductSize.orderingValue.compareTo(wishProductSize2.orderingValue);
                        }
                        return wishProductSize.ordering.compareTo(wishProductSize2.ordering);
                    }
                });
                Iterator it = arrayList12.iterator();
                while (it.hasNext()) {
                    WishProductSize wishProductSize = (WishProductSize) it.next();
                    if (!this.mHiddenVariationSizes.contains(wishProductSize.size)) {
                        this.mHiddenVariationSizes.add(wishProductSize.size);
                    }
                }
                ArrayList arrayList13 = arrayList11;
                final boolean areSizesAllNumeric2 = areSizesAllNumeric(arrayList13);
                Collections.sort(arrayList13, new Comparator<WishProductSize>() {
                    public int compare(WishProductSize wishProductSize, WishProductSize wishProductSize2) {
                        if (areSizesAllNumeric2) {
                            return wishProductSize.orderingValue.compareTo(wishProductSize2.orderingValue);
                        }
                        return wishProductSize.ordering.compareTo(wishProductSize2.ordering);
                    }
                });
                Iterator it2 = arrayList13.iterator();
                while (it2.hasNext()) {
                    WishProductSize wishProductSize2 = (WishProductSize) it2.next();
                    if (!this.mVariationSizes.contains(wishProductSize2.size)) {
                        this.mVariationSizes.add(wishProductSize2.size);
                    }
                }
                if (this.mVariationColors != null) {
                    Collections.sort(this.mVariationColors);
                }
                if (this.mHiddenVariationColors != null) {
                    Collections.sort(this.mHiddenVariationColors);
                }
                JSONObject jSONObject13 = jSONObject12;
                if (JsonUtil.hasValue(jSONObject13, "logging_fields")) {
                    this.mLoggingFields = (HashMap) new Gson().fromJson(jSONObject13.getJSONObject("logging_fields").toString(), new TypeToken<HashMap<String, String>>() {
                    }.getType());
                }
            } else {
                i = 0;
            }
            JSONObject jSONObject14 = jSONObject;
            if (JsonUtil.hasValue(jSONObject14, "credit")) {
                this.mCredit = new WishCredit(jSONObject14.getJSONObject("credit"));
            }
            if (JsonUtil.hasValue(jSONObject14, "currently_viewing")) {
                this.mUsersCurrentlyViewing = new WishUsersCurrentlyViewing(jSONObject14.getJSONObject("currently_viewing"));
            }
            this.mBundledProductIds = JsonUtil.parseArray(jSONObject14, "bundled_product_ids", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException, ParseException {
                    return str;
                }
            });
            if (JsonUtil.hasValue(jSONObject14, "bundled_products_title")) {
                this.mBundledProductsTitle = jSONObject14.getString("bundled_products_title");
            }
            if (JsonUtil.hasValue(jSONObject14, "screenshot_share_info")) {
                this.mScreenshotShareInfo = new WishScreenshotShareInfo(jSONObject14.getJSONObject("screenshot_share_info"));
            }
            if (JsonUtil.hasValue(jSONObject14, "video_info")) {
                this.mVideoInfo = new WishProductVideoInfo(jSONObject14.getJSONObject("video_info"));
            }
            if (JsonUtil.hasValue(jSONObject14, "slideshow")) {
                this.mSlideshow = new WishImageSlideshow(jSONObject14.getJSONObject("slideshow"));
            }
            if (JsonUtil.hasValue(jSONObject14, "merchant_info")) {
                JSONObject jSONObject15 = jSONObject14.getJSONObject("merchant_info");
                this.mMerchantInfoImageUrl = jSONObject15.getString("image_url");
                this.mMerchantInfoTitle = jSONObject15.getString(StrongAuth.AUTH_TITLE);
                this.mMerchantInfoSubtitle = jSONObject15.getString("subtitle");
                if (JsonUtil.hasValue(jSONObject15, "details_titles") && JsonUtil.hasValue(jSONObject15, "details_bodies")) {
                    JSONArray jSONArray3 = jSONObject15.getJSONArray("details_titles");
                    JSONArray jSONArray4 = jSONObject15.getJSONArray("details_bodies");
                    this.mMerchantInfoDetailBodies = new ArrayList<>();
                    this.mMerchantInfoDetailTitles = new ArrayList<>();
                    while (i < jSONArray3.length()) {
                        this.mMerchantInfoDetailTitles.add(jSONArray3.getString(i));
                        this.mMerchantInfoDetailBodies.add(jSONArray4.getString(i));
                        i++;
                    }
                }
                if (JsonUtil.hasValue(jSONObject15, "badge")) {
                    this.mBadge = new WishShippingBadge(jSONObject15.getJSONObject("badge"));
                }
            }
            if (JsonUtil.hasValue(jSONObject14, "promo_deal_spec")) {
                this.mPromotionSpec = new WishPromotionSpec(jSONObject14.getJSONObject("promo_deal_spec"));
            }
            if (JsonUtil.hasValue(jSONObject14, "product_details_discount_stripes")) {
                this.mProductDetailsDiscountStripeSpecs = JsonUtil.parseArray(jSONObject14, "product_details_discount_stripes", new DataParser<WishPromotionProductDetailsStripeSpec, JSONObject>() {
                    public WishPromotionProductDetailsStripeSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishPromotionProductDetailsStripeSpec(jSONObject);
                    }
                });
            }
            if (JsonUtil.hasValue(jSONObject14, "product_boost_feed_tile_label_spec")) {
                this.mProductBoostFeedTileLabelSpec = new WishProductBoostFeedTileLabelSpec(jSONObject14.getJSONObject("product_boost_feed_tile_label_spec"));
            }
            if (JsonUtil.hasValue(jSONObject14, "wishlist_tooltip_text")) {
                this.mWishlistTooltipText = JsonUtil.optString(jSONObject14, "wishlist_tooltip_text");
            }
            this.mLoadDetailsOverviewExpressItems = jSONObject14.optBoolean("load_overview_express_row");
            this.mLoadDetailsRelatedExpressItems = jSONObject14.optBoolean("load_related_express_row");
            if (JsonUtil.hasValue(jSONObject14, "price_chop_details")) {
                this.mPriceChopProductDetail = new PriceChopProductDetail(jSONObject14.getJSONObject("price_chop_details"));
            }
        }
    }

    protected WishProduct(Parcel parcel) {
        boolean z = false;
        this.mInStock = parcel.readByte() != 0;
        this.mIsAlreadyWishing = parcel.readByte() != 0;
        this.mIsCommerceProduct = parcel.readByte() != 0;
        this.mIsPreview = parcel.readByte() != 0;
        this.mIsDailyGiveaway = parcel.readByte() != 0;
        this.mLoadDetailsOverviewExpressItems = parcel.readByte() != 0;
        this.mLoadDetailsRelatedExpressItems = parcel.readByte() != 0;
        this.mIsWishlistNewProduct = parcel.readByte() != 0;
        this.mAspectRatio = parcel.readDouble();
        this.mAverageRating = parcel.readDouble();
        this.mOriginalImageHeight = parcel.readDouble();
        this.mOriginalImageWidth = parcel.readDouble();
        this.mMerchantRating = parcel.readDouble();
        this.mNumWishes = parcel.readInt();
        this.mNumBought = parcel.readInt();
        this.mMaxShippingTime = parcel.readInt();
        this.mMerchantRatingCount = parcel.readInt();
        this.mMinShippingTime = parcel.readInt();
        this.mRatingCount = parcel.readInt();
        this.mTileBarMaxValue = parcel.readInt();
        this.mTileBarValue = parcel.readInt();
        this.mTotalInventory = parcel.readInt();
        this.mDailyGiveawayInventory = parcel.readInt();
        this.mDailyGiveawayQuantity = parcel.readInt();
        this.mDailyRaffleDiscountQuantity = parcel.readInt();
        this.mDailyRaffleEntryNumber = parcel.readInt();
        this.mIsNew = parcel.readByte() == 1;
        this.mHiddenVariationColors = parcel.createStringArrayList();
        this.mHiddenVariationSizes = parcel.createStringArrayList();
        this.mVariationColors = parcel.createStringArrayList();
        this.mVariationSizes = parcel.createStringArrayList();
        this.mExtraPhotos = parcel.createTypedArrayList(WishProductExtraImage.CREATOR);
        this.mTopRatings = parcel.createTypedArrayList(WishRating.CREATOR);
        this.mTopMerchantRatings = parcel.createTypedArrayList(WishRating.CREATOR);
        this.mProductBadges = parcel.createTypedArrayList(WishProductBadge.CREATOR);
        int readInt = parcel.readInt();
        this.mVariationQuantityMapping = new HashMap<>();
        for (int i = 0; i < readInt; i++) {
            this.mVariationQuantityMapping.put(parcel.readString(), Integer.valueOf(parcel.readInt()));
        }
        this.mLoggingFields = new HashMap<>();
        int readInt2 = parcel.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.mLoggingFields.put(parcel.readString(), parcel.readString());
        }
        int readInt3 = parcel.readInt();
        this.mVariationPriceBeforeDiscountsMapping = new HashMap<>();
        for (int i3 = 0; i3 < readInt3; i3++) {
            this.mVariationPriceBeforeDiscountsMapping.put(parcel.readString(), (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader()));
        }
        int readInt4 = parcel.readInt();
        this.mVariationPriceMapping = new HashMap<>();
        for (int i4 = 0; i4 < readInt4; i4++) {
            this.mVariationPriceMapping.put(parcel.readString(), (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader()));
        }
        int readInt5 = parcel.readInt();
        this.mVariationGroupPriceMapping = new HashMap<>();
        for (int i5 = 0; i5 < readInt5; i5++) {
            this.mVariationGroupPriceMapping.put(parcel.readString(), (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader()));
        }
        int readInt6 = parcel.readInt();
        this.mVariationRetailPriceMapping = new HashMap<>();
        for (int i6 = 0; i6 < readInt6; i6++) {
            this.mVariationRetailPriceMapping.put(parcel.readString(), (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader()));
        }
        int readInt7 = parcel.readInt();
        this.mVariationIdMapping = new HashMap<>();
        for (int i7 = 0; i7 < readInt7; i7++) {
            this.mVariationIdMapping.put(parcel.readString(), (WishProductVariation) parcel.readParcelable(WishProductVariation.class.getClassLoader()));
        }
        int readInt8 = parcel.readInt();
        this.mVariationImageMapping = new HashMap<>();
        for (int i8 = 0; i8 < readInt8; i8++) {
            this.mVariationImageMapping.put(parcel.readString(), (WishProductExtraImage) parcel.readParcelable(WishProductExtraImage.class.getClassLoader()));
        }
        int readInt9 = parcel.readInt();
        this.mVariationShippingOptionsMapping = new HashMap<>();
        for (int i9 = 0; i9 < readInt9; i9++) {
            String readString = parcel.readString();
            int readInt10 = parcel.readInt();
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < readInt10; i10++) {
                arrayList.add((WishShippingOption) parcel.readParcelable(WishShippingOption.class.getClassLoader()));
            }
            this.mVariationShippingOptionsMapping.put(readString, arrayList);
        }
        int readInt11 = parcel.readInt();
        this.mVariationColorsToHexes = new HashMap<>();
        for (int i11 = 0; i11 < readInt11; i11++) {
            this.mVariationColorsToHexes.put(parcel.readString(), parcel.readString());
        }
        this.mBrand = parcel.readString();
        this.mCommerceProductId = parcel.readString();
        this.mCreditId = parcel.readString();
        this.mDefaultCommerceVariationId = parcel.readString();
        this.mDescription = parcel.readString();
        this.mProductId = parcel.readString();
        this.mMerchantId = parcel.readString();
        this.mMerchantName = parcel.readString();
        this.mMerchantUniqueName = parcel.readString();
        this.mMerchantUserId = parcel.readString();
        this.mName = parcel.readString();
        this.mReturnPolicyShortString = parcel.readString();
        this.mReturnPolicyLongString = parcel.readString();
        this.mShareMessage = parcel.readString();
        this.mShareSubject = parcel.readString();
        this.mShippingCountriesString = parcel.readString();
        this.mShippingOfferTitle = parcel.readString();
        this.mShippingOfferText = parcel.readString();
        this.mShippingPriceCountry = parcel.readString();
        this.mShippingTimeString = parcel.readString();
        this.mShipsFrom = parcel.readString();
        this.mSizingChartUrl = parcel.readString();
        this.mTaxText = parcel.readString();
        this.mTileBarText = parcel.readString();
        this.mUrgencyText = parcel.readString();
        this.mWishGuaranteeText = parcel.readString();
        this.mFeedTileText = parcel.readString();
        this.mPriceReplacementText = parcel.readString();
        this.mPriceReplacementSubText = parcel.readString();
        int readInt12 = parcel.readInt();
        this.mShippingOptionToPriceRanges = new HashMap();
        for (int i12 = 0; i12 < readInt12; i12++) {
            this.mShippingOptionToPriceRanges.put(parcel.readString(), parcel.createTypedArrayList(WishShippingOption.CREATOR));
        }
        this.mAddToCartOffer = (WishAddToCartOffer) parcel.readParcelable(WishAddToCartOffer.class.getClassLoader());
        this.mCredit = (WishCredit) parcel.readParcelable(WishCredit.class.getClassLoader());
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mCommerceValue = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mShippingPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mSignupGiftPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mValue = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mRatingSizeSummary = (WishRatingSizeSummary) parcel.readParcelable(WishRatingSizeSummary.class.getClassLoader());
        this.mUserCreator = (WishUser) parcel.readParcelable(WishUser.class.getClassLoader());
        this.mUsersCurrentlyViewing = (WishUsersCurrentlyViewing) parcel.readParcelable(WishUsersCurrentlyViewing.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.mBundledProductIds = new ArrayList<>();
            parcel.readList(this.mBundledProductIds, String.class.getClassLoader());
        } else {
            this.mBundledProductIds = null;
        }
        this.mBundledProductsTitle = parcel.readString();
        this.mAuthorizedBrand = (WishBrand) parcel.readParcelable(WishBrand.class.getClassLoader());
        this.mScreenshotShareInfo = (WishScreenshotShareInfo) parcel.readParcelable(WishScreenshotShareInfo.class.getClassLoader());
        this.mVideoInfo = (WishProductVideoInfo) parcel.readParcelable(WishProductVideoInfo.class.getClassLoader());
        this.mGroupBuyPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mGroupBuyRebateText = parcel.readString();
        this.mGroupBuyRebate = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mGroupBuyJoinDescription = parcel.readString();
        this.mGroupBuyExtraDescription = parcel.readString();
        this.mGroupBuyAddToCartModalText = parcel.readString();
        this.mSlideshow = (WishImageSlideshow) parcel.readParcelable(WishImageSlideshow.class.getClassLoader());
        this.mMerchantInfoImageUrl = parcel.readString();
        this.mMerchantInfoTitle = parcel.readString();
        this.mMerchantInfoSubtitle = parcel.readString();
        this.mMerchantInfoDetailTitles = parcel.createStringArrayList();
        this.mMerchantInfoDetailBodies = parcel.createStringArrayList();
        this.mBadge = (WishShippingBadge) parcel.readParcelable(WishShippingBadge.class.getClassLoader());
        this.mPromotionSpec = (WishPromotionSpec) parcel.readParcelable(WishPromotionSpec.class.getClassLoader());
        this.mProductDetailsDiscountStripeSpecs = parcel.createTypedArrayList(WishPromotionProductDetailsStripeSpec.CREATOR);
        this.mProductBoostFeedTileLabelSpec = (WishProductBoostFeedTileLabelSpec) parcel.readParcelable(WishProductBoostFeedTileLabelSpec.class.getClassLoader());
        this.mTileVideoStatus = (VideoStatus) parcel.readParcelable(VideoStatus.class.getClassLoader());
        this.mWishlistTooltipText = parcel.readString();
        this.mPriceChopProductDetail = (PriceChopProductDetail) parcel.readParcelable(PriceChopProductDetail.class.getClassLoader());
        this.mPartnerBuyButtonPromoMessage = parcel.readString();
        this.mWishPartnerInfo = (WishPartnerProductDetailInfo) parcel.readParcelable(WishPartnerProductDetailInfo.class.getClassLoader());
        this.mIsPriceWatched = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsDealDash = z;
    }

    private boolean areSizesAllNumeric(ArrayList<WishProductSize> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((WishProductSize) it.next()).orderingValue == null) {
                return false;
            }
        }
        return true;
    }

    public void setPriceChopProductDetail(PriceChopProductDetail priceChopProductDetail) {
        this.mPriceChopProductDetail = priceChopProductDetail;
    }

    public PriceChopProductDetail getPriceChopProductDetail() {
        return this.mPriceChopProductDetail;
    }

    public WishScreenshotShareInfo getScreenshotShareInfo() {
        return this.mScreenshotShareInfo;
    }

    public WishProductVideoInfo getVideoInfo() {
        return this.mVideoInfo;
    }

    public WishImageSlideshow getSlideshow() {
        return this.mSlideshow;
    }

    public String getUrgencyText() {
        return this.mUrgencyText;
    }

    public String getNumPurchasedText() {
        return this.mFeedTileText;
    }

    public String getPriceReplacementText() {
        return this.mPriceReplacementText;
    }

    public String getPriceReplacementSubText() {
        return this.mPriceReplacementSubText;
    }

    public WishImage getImage() {
        return this.mImage;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getTaxText() {
        return this.mTaxText;
    }

    public String getCommerceProductId() {
        return this.mCommerceProductId;
    }

    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getSizingChartUrl() {
        return this.mSizingChartUrl;
    }

    public double getAspectRatio() {
        return this.mAspectRatio;
    }

    public WishLocalizedCurrencyValue getValue() {
        return this.mValue;
    }

    public int getValueInPointsForVariation(String str) {
        WishProductVariation wishProductVariation = (WishProductVariation) this.mVariationIdMapping.get(str);
        if (wishProductVariation == null) {
            return -1;
        }
        return wishProductVariation.valueInPoints;
    }

    public ArrayList<WishRating> getTopRatings() {
        return this.mTopRatings;
    }

    public ArrayList<WishRating> getTopMerchantRatings() {
        return this.mTopMerchantRatings;
    }

    public ArrayList<WishProductBadge> getProductBadges() {
        return this.mProductBadges;
    }

    public List<WishProductExtraImage> getExtraPhotos() {
        return this.mExtraPhotos != null ? this.mExtraPhotos : Collections.emptyList();
    }

    public boolean isAlreadyWishing() {
        return this.mIsAlreadyWishing;
    }

    public double getOriginalImageHeight() {
        return this.mOriginalImageHeight;
    }

    public WishLocalizedCurrencyValue getSignupGiftPrice() {
        return this.mSignupGiftPrice;
    }

    public boolean isDailyGiveaway() {
        return this.mIsDailyGiveaway;
    }

    public int getDailyGiveawayInventory() {
        return this.mDailyGiveawayInventory;
    }

    public int getDailyGiveawayQuantity() {
        return this.mDailyGiveawayQuantity;
    }

    public int getRaffleEntryNumber() {
        return this.mDailyRaffleEntryNumber;
    }

    public boolean isNew() {
        return this.mIsNew;
    }

    public String getMerchantName() {
        return this.mMerchantUniqueName;
    }

    public boolean isCommerceProduct() {
        return this.mIsCommerceProduct;
    }

    public WishLocalizedCurrencyValue getCommerceValue() {
        return this.mCommerceValue;
    }

    public WishLocalizedCurrencyValue getGroupBuyPrice() {
        return this.mGroupBuyPrice;
    }

    public String getGroupBuyRebateText() {
        return this.mGroupBuyRebateText;
    }

    public String getGroupBuyJoinDescription() {
        return this.mGroupBuyJoinDescription;
    }

    public String getGroupBuyExtraDescription() {
        return this.mGroupBuyExtraDescription;
    }

    public String getGroupBuyAddToCartModalText() {
        return this.mGroupBuyAddToCartModalText;
    }

    public boolean isInStock() {
        if (!isDailyGiveaway()) {
            return this.mInStock;
        }
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            return this.mInStock;
        }
        return this.mInStock && this.mDailyGiveawayInventory > 0;
    }

    public String getCommerceDefaultVariationId() {
        return this.mDefaultCommerceVariationId;
    }

    public ArrayList<String> getVariationColors() {
        return this.mVariationColors;
    }

    public ArrayList<String> getVariationSizes() {
        return this.mVariationSizes;
    }

    public HashMap<String, String> getVariationColorHexes() {
        return this.mVariationColorsToHexes;
    }

    public ArrayList<String> getHiddenVariationColors() {
        return this.mHiddenVariationColors;
    }

    public ArrayList<String> getHiddenVariationSizes() {
        return this.mHiddenVariationSizes;
    }

    public void setDefaultCommerceVariationId(String str) {
        this.mDefaultCommerceVariationId = str;
    }

    public boolean hasMultipleVariations() {
        if (this.mVariationQuantityMapping.size() <= 1 || (this.mVariationSizes.size() <= 0 && this.mVariationColors.size() <= 0)) {
            return false;
        }
        return true;
    }

    public boolean hasSelectableOptions() {
        return hasMultipleVariations();
    }

    public boolean canShowAddToCartModal() {
        return hasSelectableOptions();
    }

    private void setDefaultShippingOptions() {
        for (Entry value : this.mVariationShippingOptionsMapping.entrySet()) {
            ArrayList arrayList = (ArrayList) value.getValue();
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    WishShippingOption wishShippingOption = (WishShippingOption) it.next();
                    if (wishShippingOption.getOptionId().equals("standard") && this.mDefaultStandardShippingOption == null) {
                        this.mDefaultStandardShippingOption = wishShippingOption;
                    } else if (wishShippingOption.isExpressType() && this.mDefaultExpressShippingOption == null) {
                        this.mDefaultExpressShippingOption = wishShippingOption;
                    } else if (!(this.mDefaultStandardShippingOption == null || this.mDefaultExpressShippingOption == null)) {
                        return;
                    }
                }
                continue;
            }
        }
    }

    public Map<String, List<WishShippingOption>> getShippingOptionToPriceRanges() {
        return this.mShippingOptionToPriceRanges;
    }

    public WishShippingOption getDefaultStandardShippingOption() {
        if (this.mDefaultExpressShippingOption == null && this.mDefaultStandardShippingOption == null) {
            setDefaultShippingOptions();
        }
        return this.mDefaultStandardShippingOption;
    }

    public WishShippingOption getDefaultExpressShippingOption() {
        if (this.mDefaultExpressShippingOption == null && this.mDefaultStandardShippingOption == null) {
            setDefaultShippingOptions();
        }
        return this.mDefaultExpressShippingOption;
    }

    public WishLocalizedCurrencyValue getDefaultVariationPriceBeforeDiscount() {
        if (this.mVariationPriceBeforeDiscountsMapping == null || !this.mVariationPriceBeforeDiscountsMapping.containsKey(this.mDefaultCommerceVariationId)) {
            return null;
        }
        return (WishLocalizedCurrencyValue) this.mVariationPriceBeforeDiscountsMapping.get(this.mDefaultCommerceVariationId);
    }

    public String getVariationId(String str, String str2) {
        for (Entry entry : this.mVariationIdMapping.entrySet()) {
            String str3 = (String) entry.getKey();
            WishProductVariation wishProductVariation = (WishProductVariation) entry.getValue();
            boolean z = false;
            boolean z2 = (wishProductVariation.color == null && str2 == null) || !(wishProductVariation.color == null || str2 == null || !wishProductVariation.color.equals(str2));
            if ((wishProductVariation.size == null && str == null) || !(wishProductVariation.size == null || str == null || !wishProductVariation.size.equals(str))) {
                z = true;
            }
            if (z2 && z) {
                return str3;
            }
        }
        return null;
    }

    public ArrayList<String> getAllVariationIdsBySize(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Entry entry : this.mVariationIdMapping.entrySet()) {
            String str2 = (String) entry.getKey();
            WishProductVariation wishProductVariation = (WishProductVariation) entry.getValue();
            if (!(wishProductVariation.size == null || str == null || !wishProductVariation.size.equals(str))) {
                arrayList.add(str2);
            }
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    public ArrayList<String> getAllVariationIdsByColor(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Entry entry : this.mVariationIdMapping.entrySet()) {
            String str2 = (String) entry.getKey();
            WishProductVariation wishProductVariation = (WishProductVariation) entry.getValue();
            if (!(wishProductVariation.color == null || str == null || !wishProductVariation.color.equals(str))) {
                arrayList.add(str2);
            }
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    public boolean isSizeInStock(String str) {
        Iterator it = getAllVariationIdsBySize(str).iterator();
        while (it.hasNext()) {
            if (isInStock((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isColorInStock(String str) {
        Iterator it = getAllVariationIdsByColor(str).iterator();
        while (it.hasNext()) {
            if (isInStock((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isSatisfyingVariationInStock(String str, String str2) {
        if (str != null && str2 != null) {
            return isInStock(str, str2);
        }
        if (str != null) {
            return isSizeInStock(str);
        }
        if (str2 != null) {
            return isColorInStock(str2);
        }
        return false;
    }

    public boolean isInStock(String str, String str2) {
        return isInStock(getVariationId(str, str2));
    }

    public boolean isInStock(String str) {
        return getNumInStock(str) > 0;
    }

    public int getNumInStock(String str) {
        Integer num = (Integer) this.mVariationQuantityMapping.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public boolean isSizeExpressShippingEligible(String str) {
        Iterator it = getAllVariationIdsBySize(str).iterator();
        while (it.hasNext()) {
            if (isExpressShippingEligible((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isColorExpressShippingEligible(String str) {
        Iterator it = getAllVariationIdsByColor(str).iterator();
        while (it.hasNext()) {
            if (isExpressShippingEligible((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isExpressShippingEligible(String str, String str2) {
        return isExpressShippingEligible(getVariationId(str, str2));
    }

    public boolean isSatisfyingVariationExpressShippingEligible(String str, String str2) {
        if (str != null && str2 != null) {
            return isExpressShippingEligible(str, str2);
        }
        if (str != null) {
            return isSizeExpressShippingEligible(str);
        }
        if (str2 != null) {
            return isColorExpressShippingEligible(str2);
        }
        return false;
    }

    public boolean isExpressShippingEligible(String str) {
        ArrayList arrayList = (ArrayList) this.mVariationShippingOptionsMapping.get(str);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((WishShippingOption) it.next()).isExpressType()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getDefaultShippingOptionId(String str) {
        ArrayList arrayList = (ArrayList) this.mVariationShippingOptionsMapping.get(str);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                WishShippingOption wishShippingOption = (WishShippingOption) it.next();
                if (wishShippingOption.isSelected()) {
                    return wishShippingOption.getOptionId();
                }
            }
            Crashlytics.logException(new Exception("No default WishShippingOption!"));
        }
        return "standard";
    }

    public WishLocalizedCurrencyValue getVariationPrice(String str) {
        return (WishLocalizedCurrencyValue) this.mVariationPriceMapping.get(str);
    }

    public WishLocalizedCurrencyValue getVariationGroupPrice(String str) {
        return (WishLocalizedCurrencyValue) this.mVariationGroupPriceMapping.get(str);
    }

    public WishLocalizedCurrencyValue getVariationRetailPrice(String str) {
        return (WishLocalizedCurrencyValue) this.mVariationRetailPriceMapping.get(str);
    }

    public ArrayList<WishShippingOption> getShippingOptions(String str) {
        return (ArrayList) this.mVariationShippingOptionsMapping.get(str);
    }

    public ArrayList<WishShippingOption> getAllShippingOptions() {
        HashMap hashMap = new HashMap();
        for (String shippingOptions : this.mVariationShippingOptionsMapping.keySet()) {
            Iterator it = getShippingOptions(shippingOptions).iterator();
            while (it.hasNext()) {
                WishShippingOption wishShippingOption = (WishShippingOption) it.next();
                hashMap.put(wishShippingOption.getOptionId(), wishShippingOption);
            }
        }
        return new ArrayList<>(hashMap.values());
    }

    public void setCredit(WishCredit wishCredit) {
        this.mCredit = wishCredit;
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public WishBrand getAuthorizedBrand() {
        return this.mAuthorizedBrand;
    }

    public double getMerchantRating() {
        return this.mMerchantRating;
    }

    public int getMerchantRatingCount() {
        return this.mMerchantRatingCount;
    }

    public int getProductRatingCount() {
        return this.mRatingCount;
    }

    public double getProductRating() {
        return this.mAverageRating;
    }

    public String getDefaultCommerceVariationId() {
        return this.mDefaultCommerceVariationId;
    }

    public String getShippingOfferTitle() {
        return this.mShippingOfferTitle;
    }

    public String getShippingOfferText() {
        return this.mShippingOfferText;
    }

    public String getReturnPolicyShortString() {
        return this.mReturnPolicyShortString;
    }

    public String getShareSubject() {
        return this.mShareSubject;
    }

    public String getShareMessage() {
        return this.mShareMessage;
    }

    public WishAddToCartOffer getAddToCartOffer() {
        return this.mAddToCartOffer;
    }

    public String getAddToCartOfferId() {
        if (this.mAddToCartOffer == null || this.mAddToCartOffer.isExpired()) {
            return null;
        }
        return this.mAddToCartOffer.getOfferId();
    }

    public WishRatingSizeSummary getRatingSizeSummary() {
        return this.mRatingSizeSummary;
    }

    public HashMap<String, String> getLoggingFields() {
        return this.mLoggingFields;
    }

    public WishUsersCurrentlyViewing getUsersCurrentlyViewingInfo() {
        return this.mUsersCurrentlyViewing;
    }

    public ArrayList<String> getBundledProductIds() {
        return this.mBundledProductIds != null ? this.mBundledProductIds : new ArrayList<>();
    }

    public String getBundledProductsHeaderText() {
        return this.mBundledProductsTitle;
    }

    public boolean isUserWatchingPrice() {
        return this.mIsPriceWatched;
    }

    public boolean isDealDash() {
        return this.mIsDealDash;
    }

    public void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
        if (eventType == EventType.PRODUCT_UNWISH) {
            this.mIsAlreadyWishing = false;
        } else if (eventType == EventType.PRODUCT_WISH) {
            this.mIsAlreadyWishing = true;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        parcel.writeByte(this.mInStock ? (byte) 1 : 0);
        parcel.writeByte(this.mIsAlreadyWishing ? (byte) 1 : 0);
        parcel.writeByte(this.mIsCommerceProduct ? (byte) 1 : 0);
        parcel.writeByte(this.mIsPreview ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDailyGiveaway ? (byte) 1 : 0);
        parcel.writeByte(this.mLoadDetailsOverviewExpressItems ? (byte) 1 : 0);
        parcel.writeByte(this.mLoadDetailsRelatedExpressItems ? (byte) 1 : 0);
        parcel.writeByte(this.mIsWishlistNewProduct ? (byte) 1 : 0);
        parcel.writeDouble(this.mAspectRatio);
        parcel.writeDouble(this.mAverageRating);
        parcel.writeDouble(this.mOriginalImageHeight);
        parcel.writeDouble(this.mOriginalImageWidth);
        parcel.writeDouble(this.mMerchantRating);
        parcel.writeInt(this.mNumWishes);
        parcel.writeInt(this.mNumBought);
        parcel.writeInt(this.mMaxShippingTime);
        parcel.writeInt(this.mMerchantRatingCount);
        parcel.writeInt(this.mMinShippingTime);
        parcel.writeInt(this.mRatingCount);
        parcel.writeInt(this.mTileBarMaxValue);
        parcel.writeInt(this.mTileBarValue);
        parcel.writeInt(this.mTotalInventory);
        parcel.writeInt(this.mDailyGiveawayInventory);
        parcel.writeInt(this.mDailyGiveawayQuantity);
        parcel.writeInt(this.mDailyRaffleDiscountQuantity);
        parcel.writeInt(this.mDailyRaffleEntryNumber);
        parcel.writeByte(this.mIsNew ? (byte) 1 : 0);
        parcel.writeStringList(this.mHiddenVariationColors);
        parcel.writeStringList(this.mHiddenVariationSizes);
        parcel.writeStringList(this.mVariationColors);
        parcel.writeStringList(this.mVariationSizes);
        parcel.writeTypedList(this.mExtraPhotos);
        parcel.writeTypedList(this.mTopRatings);
        parcel.writeTypedList(this.mTopMerchantRatings);
        parcel.writeTypedList(this.mProductBadges);
        parcel.writeInt(this.mVariationQuantityMapping == null ? 0 : this.mVariationQuantityMapping.size());
        if (this.mVariationQuantityMapping != null) {
            for (Entry entry : this.mVariationQuantityMapping.entrySet()) {
                parcel.writeString((String) entry.getKey());
                parcel.writeInt(((Integer) entry.getValue()).intValue());
            }
        }
        parcel.writeInt(this.mLoggingFields == null ? 0 : this.mLoggingFields.size());
        if (this.mLoggingFields != null) {
            for (Entry entry2 : this.mLoggingFields.entrySet()) {
                parcel.writeString((String) entry2.getKey());
                parcel.writeString((String) entry2.getValue());
            }
        }
        if (this.mVariationPriceBeforeDiscountsMapping == null) {
            i2 = 0;
        } else {
            i2 = this.mVariationPriceBeforeDiscountsMapping.size();
        }
        parcel.writeInt(i2);
        if (this.mVariationPriceBeforeDiscountsMapping != null) {
            for (Entry entry3 : this.mVariationPriceBeforeDiscountsMapping.entrySet()) {
                parcel.writeString((String) entry3.getKey());
                parcel.writeParcelable((Parcelable) entry3.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVariationPriceMapping == null ? 0 : this.mVariationPriceMapping.size());
        if (this.mVariationPriceMapping != null) {
            for (Entry entry4 : this.mVariationPriceMapping.entrySet()) {
                parcel.writeString((String) entry4.getKey());
                parcel.writeParcelable((Parcelable) entry4.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVariationGroupPriceMapping == null ? 0 : this.mVariationGroupPriceMapping.size());
        if (this.mVariationGroupPriceMapping != null) {
            for (Entry entry5 : this.mVariationGroupPriceMapping.entrySet()) {
                parcel.writeString((String) entry5.getKey());
                parcel.writeParcelable((Parcelable) entry5.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVariationRetailPriceMapping == null ? 0 : this.mVariationRetailPriceMapping.size());
        if (this.mVariationRetailPriceMapping != null) {
            for (Entry entry6 : this.mVariationRetailPriceMapping.entrySet()) {
                parcel.writeString((String) entry6.getKey());
                parcel.writeParcelable((Parcelable) entry6.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVariationIdMapping == null ? 0 : this.mVariationIdMapping.size());
        if (this.mVariationIdMapping != null) {
            for (Entry entry7 : this.mVariationIdMapping.entrySet()) {
                parcel.writeString((String) entry7.getKey());
                parcel.writeParcelable((Parcelable) entry7.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVariationImageMapping == null ? 0 : this.mVariationImageMapping.size());
        if (this.mVariationImageMapping != null) {
            for (Entry entry8 : this.mVariationImageMapping.entrySet()) {
                parcel.writeString((String) entry8.getKey());
                parcel.writeParcelable((Parcelable) entry8.getValue(), 0);
            }
        }
        parcel.writeInt(this.mVariationShippingOptionsMapping == null ? 0 : this.mVariationShippingOptionsMapping.size());
        if (this.mVariationShippingOptionsMapping != null) {
            for (Entry entry9 : this.mVariationShippingOptionsMapping.entrySet()) {
                parcel.writeString((String) entry9.getKey());
                parcel.writeInt(((ArrayList) entry9.getValue()).size());
                Iterator it = ((ArrayList) entry9.getValue()).iterator();
                while (it.hasNext()) {
                    parcel.writeParcelable((WishShippingOption) it.next(), 0);
                }
            }
        }
        parcel.writeInt(this.mVariationColorsToHexes == null ? 0 : this.mVariationColorsToHexes.size());
        if (this.mVariationColorsToHexes != null) {
            for (Entry entry10 : this.mVariationColorsToHexes.entrySet()) {
                parcel.writeString((String) entry10.getKey());
                parcel.writeString((String) entry10.getValue());
            }
        }
        parcel.writeString(this.mBrand);
        parcel.writeString(this.mCommerceProductId);
        parcel.writeString(this.mCreditId);
        parcel.writeString(this.mDefaultCommerceVariationId);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mMerchantId);
        parcel.writeString(this.mMerchantName);
        parcel.writeString(this.mMerchantUniqueName);
        parcel.writeString(this.mMerchantUserId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mReturnPolicyShortString);
        parcel.writeString(this.mReturnPolicyLongString);
        parcel.writeString(this.mShareMessage);
        parcel.writeString(this.mShareSubject);
        parcel.writeString(this.mShippingCountriesString);
        parcel.writeString(this.mShippingOfferTitle);
        parcel.writeString(this.mShippingOfferText);
        parcel.writeString(this.mShippingPriceCountry);
        parcel.writeString(this.mShippingTimeString);
        parcel.writeString(this.mShipsFrom);
        parcel.writeString(this.mSizingChartUrl);
        parcel.writeString(this.mTaxText);
        parcel.writeString(this.mTileBarText);
        parcel.writeString(this.mUrgencyText);
        parcel.writeString(this.mWishGuaranteeText);
        parcel.writeString(this.mFeedTileText);
        parcel.writeString(this.mPriceReplacementText);
        parcel.writeString(this.mPriceReplacementSubText);
        parcel.writeInt(this.mShippingOptionToPriceRanges == null ? 0 : this.mShippingOptionToPriceRanges.size());
        if (this.mShippingOptionToPriceRanges != null) {
            for (Entry entry11 : this.mShippingOptionToPriceRanges.entrySet()) {
                parcel.writeString((String) entry11.getKey());
                parcel.writeTypedList((List) entry11.getValue());
            }
        }
        parcel.writeParcelable(this.mAddToCartOffer, 0);
        parcel.writeParcelable(this.mCredit, 0);
        parcel.writeParcelable(this.mImage, 0);
        parcel.writeParcelable(this.mCommerceValue, 0);
        parcel.writeParcelable(this.mShippingPrice, 0);
        parcel.writeParcelable(this.mSignupGiftPrice, 0);
        parcel.writeParcelable(this.mValue, 0);
        parcel.writeParcelable(this.mRatingSizeSummary, 0);
        parcel.writeParcelable(this.mUserCreator, 0);
        parcel.writeParcelable(this.mUsersCurrentlyViewing, 0);
        if (this.mBundledProductIds == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.mBundledProductIds);
        }
        parcel.writeString(this.mBundledProductsTitle);
        parcel.writeParcelable(this.mAuthorizedBrand, 0);
        parcel.writeParcelable(this.mScreenshotShareInfo, 0);
        parcel.writeParcelable(this.mVideoInfo, 0);
        parcel.writeParcelable(this.mGroupBuyPrice, 0);
        parcel.writeString(this.mGroupBuyRebateText);
        parcel.writeParcelable(this.mGroupBuyRebate, 0);
        parcel.writeString(this.mGroupBuyJoinDescription);
        parcel.writeString(this.mGroupBuyExtraDescription);
        parcel.writeString(this.mGroupBuyAddToCartModalText);
        parcel.writeParcelable(this.mSlideshow, 0);
        parcel.writeString(this.mMerchantInfoImageUrl);
        parcel.writeString(this.mMerchantInfoTitle);
        parcel.writeString(this.mMerchantInfoSubtitle);
        parcel.writeStringList(this.mMerchantInfoDetailTitles);
        parcel.writeStringList(this.mMerchantInfoDetailBodies);
        parcel.writeParcelable(this.mBadge, 0);
        parcel.writeParcelable(this.mPromotionSpec, 0);
        parcel.writeTypedList(this.mProductDetailsDiscountStripeSpecs);
        parcel.writeParcelable(this.mProductBoostFeedTileLabelSpec, 0);
        parcel.writeParcelable(this.mTileVideoStatus, 0);
        parcel.writeString(this.mWishlistTooltipText);
        parcel.writeParcelable(this.mPriceChopProductDetail, 0);
        parcel.writeString(this.mPartnerBuyButtonPromoMessage);
        parcel.writeParcelable(this.mWishPartnerInfo, 0);
        parcel.writeByte(this.mIsPriceWatched ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDealDash ? (byte) 1 : 0);
    }

    public boolean hasMerchantInfo() {
        return this.mMerchantInfoTitle != null;
    }

    public WishPartnerProductDetailInfo getWishPartnerInfo() {
        return this.mWishPartnerInfo;
    }

    public String getMerchantInfoImageUrl() {
        return this.mMerchantInfoImageUrl;
    }

    public String getMerchantInfoTitle() {
        return this.mMerchantInfoTitle;
    }

    public String getMerchantInfoSubtitle() {
        return this.mMerchantInfoSubtitle;
    }

    public VideoStatus getVideoStatus() {
        return this.mTileVideoStatus == null ? VideoStatus.NO_VIDEO : this.mTileVideoStatus;
    }

    public void changeVideoStatus(VideoStatus videoStatus) {
        this.mTileVideoStatus = videoStatus;
    }

    public ArrayList<String> getMerchantInfoDetailTitles() {
        return this.mMerchantInfoDetailTitles;
    }

    public ArrayList<String> getMerchantInfoDetailBodies() {
        return this.mMerchantInfoDetailBodies;
    }

    public WishShippingBadge getBadge() {
        return this.mBadge;
    }

    public boolean getLoadDetailsOverviewExpressItems() {
        return this.mLoadDetailsOverviewExpressItems;
    }

    public boolean getLoadDetailsRelatedExpressItems() {
        return this.mLoadDetailsRelatedExpressItems;
    }

    public boolean getIsWishlistNewItem() {
        return this.mIsWishlistNewProduct;
    }

    public WishPromotionBaseSpec getPromotionSpec() {
        if (this.mPromotionSpec != null) {
            return this.mPromotionSpec.getWishPromotionDeal();
        }
        return null;
    }

    public ArrayList<WishPromotionProductDetailsStripeSpec> getPromotionProductDetailsStripeSpecs() {
        return this.mProductDetailsDiscountStripeSpecs;
    }

    public WishProductBoostFeedTileLabelSpec getProductBoostFeedTileLabelSpec() {
        return this.mProductBoostFeedTileLabelSpec;
    }

    public String getWishlistTooltipText() {
        return this.mWishlistTooltipText;
    }

    public String getPartnerBuyButtonPromoMessage() {
        return this.mPartnerBuyButtonPromoMessage;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishProduct wishProduct = (WishProduct) obj;
        if (this.mInStock != wishProduct.mInStock || this.mIsAlreadyWishing != wishProduct.mIsAlreadyWishing || this.mIsCommerceProduct != wishProduct.mIsCommerceProduct || this.mIsPreview != wishProduct.mIsPreview || this.mIsDailyGiveaway != wishProduct.mIsDailyGiveaway || Double.compare(wishProduct.mAspectRatio, this.mAspectRatio) != 0 || Double.compare(wishProduct.mAverageRating, this.mAverageRating) != 0 || Double.compare(wishProduct.mOriginalImageHeight, this.mOriginalImageHeight) != 0 || Double.compare(wishProduct.mOriginalImageWidth, this.mOriginalImageWidth) != 0 || Double.compare(wishProduct.mMerchantRating, this.mMerchantRating) != 0 || this.mNumWishes != wishProduct.mNumWishes || this.mNumBought != wishProduct.mNumBought || this.mMaxShippingTime != wishProduct.mMaxShippingTime || this.mMerchantRatingCount != wishProduct.mMerchantRatingCount || this.mMinShippingTime != wishProduct.mMinShippingTime || this.mRatingCount != wishProduct.mRatingCount || this.mTileBarMaxValue != wishProduct.mTileBarMaxValue || this.mTileBarValue != wishProduct.mTileBarValue || this.mTotalInventory != wishProduct.mTotalInventory || this.mDailyGiveawayInventory != wishProduct.mDailyGiveawayInventory || this.mDailyGiveawayQuantity != wishProduct.mDailyGiveawayQuantity) {
            return false;
        }
        if (this.mHiddenVariationColors == null ? wishProduct.mHiddenVariationColors != null : !this.mHiddenVariationColors.equals(wishProduct.mHiddenVariationColors)) {
            return false;
        }
        if (this.mHiddenVariationSizes == null ? wishProduct.mHiddenVariationSizes != null : !this.mHiddenVariationSizes.equals(wishProduct.mHiddenVariationSizes)) {
            return false;
        }
        if (this.mVariationColors == null ? wishProduct.mVariationColors != null : !this.mVariationColors.equals(wishProduct.mVariationColors)) {
            return false;
        }
        if (this.mVariationSizes == null ? wishProduct.mVariationSizes != null : !this.mVariationSizes.equals(wishProduct.mVariationSizes)) {
            return false;
        }
        if (this.mExtraPhotos == null ? wishProduct.mExtraPhotos != null : !this.mExtraPhotos.equals(wishProduct.mExtraPhotos)) {
            return false;
        }
        if (this.mTopRatings == null ? wishProduct.mTopRatings != null : !this.mTopRatings.equals(wishProduct.mTopRatings)) {
            return false;
        }
        if (this.mTopMerchantRatings == null ? wishProduct.mTopMerchantRatings != null : !this.mTopMerchantRatings.equals(wishProduct.mTopMerchantRatings)) {
            return false;
        }
        if (this.mProductBadges == null ? wishProduct.mProductBadges != null : !this.mProductBadges.equals(wishProduct.mProductBadges)) {
            return false;
        }
        if (this.mVariationQuantityMapping == null ? wishProduct.mVariationQuantityMapping != null : !this.mVariationQuantityMapping.equals(wishProduct.mVariationQuantityMapping)) {
            return false;
        }
        if (this.mLoggingFields == null ? wishProduct.mLoggingFields != null : !this.mLoggingFields.equals(wishProduct.mLoggingFields)) {
            return false;
        }
        if (this.mVariationPriceBeforeDiscountsMapping == null ? wishProduct.mVariationPriceBeforeDiscountsMapping != null : !this.mVariationPriceBeforeDiscountsMapping.equals(wishProduct.mVariationPriceBeforeDiscountsMapping)) {
            return false;
        }
        if (this.mVariationPriceMapping == null ? wishProduct.mVariationPriceMapping != null : !this.mVariationPriceMapping.equals(wishProduct.mVariationPriceMapping)) {
            return false;
        }
        if (this.mVariationRetailPriceMapping == null ? wishProduct.mVariationRetailPriceMapping != null : !this.mVariationRetailPriceMapping.equals(wishProduct.mVariationRetailPriceMapping)) {
            return false;
        }
        if (this.mVariationGroupPriceMapping == null ? wishProduct.mVariationGroupPriceMapping != null : !this.mVariationGroupPriceMapping.equals(wishProduct.mVariationGroupPriceMapping)) {
            return false;
        }
        if (this.mVariationIdMapping == null ? wishProduct.mVariationIdMapping != null : !this.mVariationIdMapping.equals(wishProduct.mVariationIdMapping)) {
            return false;
        }
        if (this.mVariationImageMapping == null ? wishProduct.mVariationImageMapping != null : !this.mVariationImageMapping.equals(wishProduct.mVariationImageMapping)) {
            return false;
        }
        if (this.mVariationShippingOptionsMapping == null ? wishProduct.mVariationShippingOptionsMapping != null : !this.mVariationShippingOptionsMapping.equals(wishProduct.mVariationShippingOptionsMapping)) {
            return false;
        }
        if (this.mVariationColorsToHexes == null ? wishProduct.mVariationColorsToHexes != null : !this.mVariationColorsToHexes.equals(wishProduct.mVariationColorsToHexes)) {
            return false;
        }
        if (this.mBrand == null ? wishProduct.mBrand != null : !this.mBrand.equals(wishProduct.mBrand)) {
            return false;
        }
        if (this.mCommerceProductId == null ? wishProduct.mCommerceProductId != null : !this.mCommerceProductId.equals(wishProduct.mCommerceProductId)) {
            return false;
        }
        if (this.mCreditId == null ? wishProduct.mCreditId != null : !this.mCreditId.equals(wishProduct.mCreditId)) {
            return false;
        }
        if (this.mDefaultCommerceVariationId == null ? wishProduct.mDefaultCommerceVariationId != null : !this.mDefaultCommerceVariationId.equals(wishProduct.mDefaultCommerceVariationId)) {
            return false;
        }
        if (this.mDescription == null ? wishProduct.mDescription != null : !this.mDescription.equals(wishProduct.mDescription)) {
            return false;
        }
        if (this.mProductId == null ? wishProduct.mProductId != null : !this.mProductId.equals(wishProduct.mProductId)) {
            return false;
        }
        if (this.mMerchantId == null ? wishProduct.mMerchantId != null : !this.mMerchantId.equals(wishProduct.mMerchantId)) {
            return false;
        }
        if (this.mMerchantName == null ? wishProduct.mMerchantName != null : !this.mMerchantName.equals(wishProduct.mMerchantName)) {
            return false;
        }
        if (this.mMerchantUniqueName == null ? wishProduct.mMerchantUniqueName != null : !this.mMerchantUniqueName.equals(wishProduct.mMerchantUniqueName)) {
            return false;
        }
        if (this.mMerchantUserId == null ? wishProduct.mMerchantUserId != null : !this.mMerchantUserId.equals(wishProduct.mMerchantUserId)) {
            return false;
        }
        if (this.mName == null ? wishProduct.mName != null : !this.mName.equals(wishProduct.mName)) {
            return false;
        }
        if (this.mReturnPolicyShortString == null ? wishProduct.mReturnPolicyShortString != null : !this.mReturnPolicyShortString.equals(wishProduct.mReturnPolicyShortString)) {
            return false;
        }
        if (this.mReturnPolicyLongString == null ? wishProduct.mReturnPolicyLongString != null : !this.mReturnPolicyLongString.equals(wishProduct.mReturnPolicyLongString)) {
            return false;
        }
        if (this.mShareMessage == null ? wishProduct.mShareMessage != null : !this.mShareMessage.equals(wishProduct.mShareMessage)) {
            return false;
        }
        if (this.mShareSubject == null ? wishProduct.mShareSubject != null : !this.mShareSubject.equals(wishProduct.mShareSubject)) {
            return false;
        }
        if (this.mShippingCountriesString == null ? wishProduct.mShippingCountriesString != null : !this.mShippingCountriesString.equals(wishProduct.mShippingCountriesString)) {
            return false;
        }
        if (this.mShippingOfferTitle == null ? wishProduct.mShippingOfferTitle != null : !this.mShippingOfferTitle.equals(wishProduct.mShippingOfferTitle)) {
            return false;
        }
        if (this.mShippingOfferText == null ? wishProduct.mShippingOfferText != null : !this.mShippingOfferText.equals(wishProduct.mShippingOfferText)) {
            return false;
        }
        if (this.mShippingPriceCountry == null ? wishProduct.mShippingPriceCountry != null : !this.mShippingPriceCountry.equals(wishProduct.mShippingPriceCountry)) {
            return false;
        }
        if (this.mShippingTimeString == null ? wishProduct.mShippingTimeString != null : !this.mShippingTimeString.equals(wishProduct.mShippingTimeString)) {
            return false;
        }
        if (this.mShipsFrom == null ? wishProduct.mShipsFrom != null : !this.mShipsFrom.equals(wishProduct.mShipsFrom)) {
            return false;
        }
        if (this.mSizingChartUrl == null ? wishProduct.mSizingChartUrl != null : !this.mSizingChartUrl.equals(wishProduct.mSizingChartUrl)) {
            return false;
        }
        if (this.mTaxText == null ? wishProduct.mTaxText != null : !this.mTaxText.equals(wishProduct.mTaxText)) {
            return false;
        }
        if (this.mTileBarText == null ? wishProduct.mTileBarText != null : !this.mTileBarText.equals(wishProduct.mTileBarText)) {
            return false;
        }
        if (this.mUrgencyText == null ? wishProduct.mUrgencyText != null : !this.mUrgencyText.equals(wishProduct.mUrgencyText)) {
            return false;
        }
        if (this.mWishGuaranteeText == null ? wishProduct.mWishGuaranteeText != null : !this.mWishGuaranteeText.equals(wishProduct.mWishGuaranteeText)) {
            return false;
        }
        if (this.mFeedTileText == null ? wishProduct.mFeedTileText != null : !this.mFeedTileText.equals(wishProduct.mFeedTileText)) {
            return false;
        }
        if (this.mPriceReplacementText == null ? wishProduct.mPriceReplacementText != null : !this.mPriceReplacementText.equals(wishProduct.mPriceReplacementText)) {
            return false;
        }
        if (this.mPriceReplacementSubText == null ? wishProduct.mPriceReplacementSubText != null : !this.mPriceReplacementSubText.equals(wishProduct.mPriceReplacementSubText)) {
            return false;
        }
        if (this.mDefaultStandardShippingOption == null ? wishProduct.mDefaultStandardShippingOption != null : !this.mDefaultStandardShippingOption.equals(wishProduct.mDefaultStandardShippingOption)) {
            return false;
        }
        if (this.mDefaultExpressShippingOption == null ? wishProduct.mDefaultExpressShippingOption != null : !this.mDefaultExpressShippingOption.equals(wishProduct.mDefaultExpressShippingOption)) {
            return false;
        }
        if (this.mShippingOptionToPriceRanges == null ? wishProduct.mShippingOptionToPriceRanges != null : !this.mShippingOptionToPriceRanges.equals(wishProduct.mShippingOptionToPriceRanges)) {
            return false;
        }
        if (this.mAddToCartOffer == null ? wishProduct.mAddToCartOffer != null : !this.mAddToCartOffer.equals(wishProduct.mAddToCartOffer)) {
            return false;
        }
        if (this.mCredit == null ? wishProduct.mCredit != null : !this.mCredit.equals(wishProduct.mCredit)) {
            return false;
        }
        if (this.mImage == null ? wishProduct.mImage != null : !this.mImage.equals(wishProduct.mImage)) {
            return false;
        }
        if (this.mCommerceValue == null ? wishProduct.mCommerceValue != null : !this.mCommerceValue.equals(wishProduct.mCommerceValue)) {
            return false;
        }
        if (this.mShippingPrice == null ? wishProduct.mShippingPrice != null : !this.mShippingPrice.equals(wishProduct.mShippingPrice)) {
            return false;
        }
        if (this.mSignupGiftPrice == null ? wishProduct.mSignupGiftPrice != null : !this.mSignupGiftPrice.equals(wishProduct.mSignupGiftPrice)) {
            return false;
        }
        if (this.mValue == null ? wishProduct.mValue != null : !this.mValue.equals(wishProduct.mValue)) {
            return false;
        }
        if (this.mGroupBuyPrice == null ? wishProduct.mGroupBuyPrice != null : !this.mGroupBuyPrice.equals(wishProduct.mGroupBuyPrice)) {
            return false;
        }
        if (this.mRatingSizeSummary == null ? wishProduct.mRatingSizeSummary != null : !this.mRatingSizeSummary.equals(wishProduct.mRatingSizeSummary)) {
            return false;
        }
        if (this.mUserCreator == null ? wishProduct.mUserCreator != null : !this.mUserCreator.equals(wishProduct.mUserCreator)) {
            return false;
        }
        if (this.mUsersCurrentlyViewing == null ? wishProduct.mUsersCurrentlyViewing != null : !this.mUsersCurrentlyViewing.equals(wishProduct.mUsersCurrentlyViewing)) {
            return false;
        }
        if (this.mAuthorizedBrand == null ? wishProduct.mAuthorizedBrand != null : !this.mAuthorizedBrand.equals(wishProduct.mAuthorizedBrand)) {
            return false;
        }
        if (this.mBundledProductIds == null ? wishProduct.mBundledProductIds != null : !this.mBundledProductIds.equals(wishProduct.mBundledProductIds)) {
            return false;
        }
        if (this.mBundledProductsTitle == null ? wishProduct.mBundledProductsTitle != null : !this.mBundledProductsTitle.equals(wishProduct.mBundledProductsTitle)) {
            return false;
        }
        if (this.mScreenshotShareInfo == null ? wishProduct.mScreenshotShareInfo != null : !this.mScreenshotShareInfo.equals(wishProduct.mScreenshotShareInfo)) {
            return false;
        }
        if (this.mVideoInfo == null ? wishProduct.mVideoInfo != null : !this.mVideoInfo.equals(wishProduct.mVideoInfo)) {
            return false;
        }
        if (this.mGroupBuyRebateText == null ? wishProduct.mGroupBuyRebateText != null : !this.mGroupBuyRebateText.equals(wishProduct.mGroupBuyRebateText)) {
            return false;
        }
        if (this.mGroupBuyRebate == null ? wishProduct.mGroupBuyRebate != null : !this.mGroupBuyRebate.equals(wishProduct.mGroupBuyRebate)) {
            return false;
        }
        if (this.mGroupBuyJoinDescription == null ? wishProduct.mGroupBuyJoinDescription != null : !this.mGroupBuyJoinDescription.equals(wishProduct.mGroupBuyJoinDescription)) {
            return false;
        }
        if (this.mGroupBuyExtraDescription == null ? wishProduct.mGroupBuyExtraDescription != null : !this.mGroupBuyExtraDescription.equals(wishProduct.mGroupBuyExtraDescription)) {
            return false;
        }
        if (this.mGroupBuyAddToCartModalText == null ? wishProduct.mGroupBuyAddToCartModalText != null : !this.mGroupBuyAddToCartModalText.equals(wishProduct.mGroupBuyAddToCartModalText)) {
            return false;
        }
        if (this.mSlideshow == null ? wishProduct.mSlideshow != null : !this.mSlideshow.equals(wishProduct.mSlideshow)) {
            return false;
        }
        if (this.mMerchantInfoImageUrl == null ? wishProduct.mMerchantInfoImageUrl != null : !this.mMerchantInfoImageUrl.equals(wishProduct.mMerchantInfoImageUrl)) {
            return false;
        }
        if (this.mMerchantInfoTitle == null ? wishProduct.mMerchantInfoTitle != null : !this.mMerchantInfoTitle.equals(wishProduct.mMerchantInfoTitle)) {
            return false;
        }
        if (this.mMerchantInfoSubtitle == null ? wishProduct.mMerchantInfoSubtitle != null : !this.mMerchantInfoSubtitle.equals(wishProduct.mMerchantInfoSubtitle)) {
            return false;
        }
        if (this.mMerchantInfoDetailTitles == null ? wishProduct.mMerchantInfoDetailTitles != null : !this.mMerchantInfoDetailTitles.equals(wishProduct.mMerchantInfoDetailTitles)) {
            return false;
        }
        if (this.mMerchantInfoDetailBodies == null ? wishProduct.mMerchantInfoDetailBodies != null : !this.mMerchantInfoDetailBodies.equals(wishProduct.mMerchantInfoDetailBodies)) {
            return false;
        }
        if (this.mBadge == null ? wishProduct.mBadge != null : !this.mBadge.equals(wishProduct.mBadge)) {
            return false;
        }
        if (this.mProductDetailsDiscountStripeSpecs == null ? wishProduct.mProductDetailsDiscountStripeSpecs != null : !this.mProductDetailsDiscountStripeSpecs.equals(wishProduct.mProductDetailsDiscountStripeSpecs)) {
            return false;
        }
        if (this.mWishlistTooltipText == null ? wishProduct.mWishlistTooltipText != null : !this.mWishlistTooltipText.equals(wishProduct.mWishlistTooltipText)) {
            return false;
        }
        if (this.mProductBoostFeedTileLabelSpec != null) {
            z = this.mProductBoostFeedTileLabelSpec.equals(wishProduct.mProductBoostFeedTileLabelSpec);
        } else if (wishProduct.mProductBoostFeedTileLabelSpec != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = ((((((((this.mInStock ? 1 : 0) * true) + (this.mIsAlreadyWishing ? 1 : 0)) * 31) + (this.mIsCommerceProduct ? 1 : 0)) * 31) + (this.mIsPreview ? 1 : 0)) * 31) + (this.mIsDailyGiveaway ? 1 : 0);
        long doubleToLongBits = Double.doubleToLongBits(this.mAspectRatio);
        int i2 = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.mAverageRating);
        int i3 = (i2 * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(this.mOriginalImageHeight);
        int i4 = (i3 * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.mOriginalImageWidth);
        int i5 = (i4 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)));
        long doubleToLongBits5 = Double.doubleToLongBits(this.mMerchantRating);
        int i6 = 0;
        int hashCode = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((i5 * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + this.mNumWishes) * 31) + this.mNumBought) * 31) + this.mMaxShippingTime) * 31) + this.mMerchantRatingCount) * 31) + this.mMinShippingTime) * 31) + this.mRatingCount) * 31) + this.mTileBarMaxValue) * 31) + this.mTileBarValue) * 31) + this.mTotalInventory) * 31) + this.mDailyGiveawayInventory) * 31) + this.mDailyGiveawayQuantity) * 31) + (this.mHiddenVariationColors != null ? this.mHiddenVariationColors.hashCode() : 0)) * 31) + (this.mHiddenVariationSizes != null ? this.mHiddenVariationSizes.hashCode() : 0)) * 31) + (this.mVariationColors != null ? this.mVariationColors.hashCode() : 0)) * 31) + (this.mVariationSizes != null ? this.mVariationSizes.hashCode() : 0)) * 31) + (this.mExtraPhotos != null ? this.mExtraPhotos.hashCode() : 0)) * 31) + (this.mTopRatings != null ? this.mTopRatings.hashCode() : 0)) * 31) + (this.mTopMerchantRatings != null ? this.mTopMerchantRatings.hashCode() : 0)) * 31) + (this.mProductBadges != null ? this.mProductBadges.hashCode() : 0)) * 31) + (this.mVariationQuantityMapping != null ? this.mVariationQuantityMapping.hashCode() : 0)) * 31) + (this.mLoggingFields != null ? this.mLoggingFields.hashCode() : 0)) * 31) + (this.mVariationPriceBeforeDiscountsMapping != null ? this.mVariationPriceBeforeDiscountsMapping.hashCode() : 0)) * 31) + (this.mVariationPriceMapping != null ? this.mVariationPriceMapping.hashCode() : 0)) * 31) + (this.mVariationRetailPriceMapping != null ? this.mVariationRetailPriceMapping.hashCode() : 0)) * 31) + (this.mVariationGroupPriceMapping != null ? this.mVariationGroupPriceMapping.hashCode() : 0)) * 31) + (this.mVariationIdMapping != null ? this.mVariationIdMapping.hashCode() : 0)) * 31) + (this.mVariationImageMapping != null ? this.mVariationImageMapping.hashCode() : 0)) * 31) + (this.mVariationShippingOptionsMapping != null ? this.mVariationShippingOptionsMapping.hashCode() : 0)) * 31) + (this.mBrand != null ? this.mBrand.hashCode() : 0)) * 31) + (this.mCommerceProductId != null ? this.mCommerceProductId.hashCode() : 0)) * 31) + (this.mCreditId != null ? this.mCreditId.hashCode() : 0)) * 31) + (this.mDefaultCommerceVariationId != null ? this.mDefaultCommerceVariationId.hashCode() : 0)) * 31) + (this.mDescription != null ? this.mDescription.hashCode() : 0)) * 31) + (this.mProductId != null ? this.mProductId.hashCode() : 0)) * 31) + (this.mMerchantId != null ? this.mMerchantId.hashCode() : 0)) * 31) + (this.mMerchantName != null ? this.mMerchantName.hashCode() : 0)) * 31) + (this.mMerchantUniqueName != null ? this.mMerchantUniqueName.hashCode() : 0)) * 31) + (this.mMerchantUserId != null ? this.mMerchantUserId.hashCode() : 0)) * 31) + (this.mName != null ? this.mName.hashCode() : 0)) * 31) + (this.mReturnPolicyShortString != null ? this.mReturnPolicyShortString.hashCode() : 0)) * 31) + (this.mReturnPolicyLongString != null ? this.mReturnPolicyLongString.hashCode() : 0)) * 31) + (this.mShareMessage != null ? this.mShareMessage.hashCode() : 0)) * 31) + (this.mShareSubject != null ? this.mShareSubject.hashCode() : 0)) * 31) + (this.mShippingCountriesString != null ? this.mShippingCountriesString.hashCode() : 0)) * 31) + (this.mShippingOfferTitle != null ? this.mShippingOfferTitle.hashCode() : 0)) * 31) + (this.mShippingOfferText != null ? this.mShippingOfferText.hashCode() : 0)) * 31) + (this.mShippingPriceCountry != null ? this.mShippingPriceCountry.hashCode() : 0)) * 31) + (this.mShippingTimeString != null ? this.mShippingTimeString.hashCode() : 0)) * 31) + (this.mShipsFrom != null ? this.mShipsFrom.hashCode() : 0)) * 31) + (this.mSizingChartUrl != null ? this.mSizingChartUrl.hashCode() : 0)) * 31) + (this.mTaxText != null ? this.mTaxText.hashCode() : 0)) * 31) + (this.mTileBarText != null ? this.mTileBarText.hashCode() : 0)) * 31) + (this.mUrgencyText != null ? this.mUrgencyText.hashCode() : 0)) * 31) + (this.mWishGuaranteeText != null ? this.mWishGuaranteeText.hashCode() : 0)) * 31) + (this.mFeedTileText != null ? this.mFeedTileText.hashCode() : 0)) * 31) + (this.mPriceReplacementText != null ? this.mPriceReplacementText.hashCode() : 0)) * 31) + (this.mPriceReplacementSubText != null ? this.mPriceReplacementSubText.hashCode() : 0)) * 31) + (this.mDefaultStandardShippingOption != null ? this.mDefaultStandardShippingOption.hashCode() : 0)) * 31) + (this.mDefaultExpressShippingOption != null ? this.mDefaultExpressShippingOption.hashCode() : 0)) * 31) + (this.mShippingOptionToPriceRanges != null ? this.mShippingOptionToPriceRanges.hashCode() : 0)) * 31) + (this.mAddToCartOffer != null ? this.mAddToCartOffer.hashCode() : 0)) * 31) + (this.mCredit != null ? this.mCredit.hashCode() : 0)) * 31) + (this.mImage != null ? this.mImage.hashCode() : 0)) * 31) + (this.mCommerceValue != null ? this.mCommerceValue.hashCode() : 0)) * 31) + (this.mShippingPrice != null ? this.mShippingPrice.hashCode() : 0)) * 31) + (this.mSignupGiftPrice != null ? this.mSignupGiftPrice.hashCode() : 0)) * 31) + (this.mValue != null ? this.mValue.hashCode() : 0)) * 31) + (this.mGroupBuyPrice != null ? this.mGroupBuyPrice.hashCode() : 0)) * 31) + (this.mRatingSizeSummary != null ? this.mRatingSizeSummary.hashCode() : 0)) * 31) + (this.mUserCreator != null ? this.mUserCreator.hashCode() : 0)) * 31) + (this.mUsersCurrentlyViewing != null ? this.mUsersCurrentlyViewing.hashCode() : 0)) * 31) + (this.mAuthorizedBrand != null ? this.mAuthorizedBrand.hashCode() : 0)) * 31) + (this.mBundledProductIds != null ? this.mBundledProductIds.hashCode() : 0)) * 31) + (this.mBundledProductsTitle != null ? this.mBundledProductsTitle.hashCode() : 0)) * 31) + (this.mScreenshotShareInfo != null ? this.mScreenshotShareInfo.hashCode() : 0)) * 31) + (this.mVideoInfo != null ? this.mVideoInfo.hashCode() : 0)) * 31) + (this.mGroupBuyRebateText != null ? this.mGroupBuyRebateText.hashCode() : 0)) * 31) + (this.mGroupBuyRebate != null ? this.mGroupBuyRebate.hashCode() : 0)) * 31) + (this.mGroupBuyJoinDescription != null ? this.mGroupBuyJoinDescription.hashCode() : 0)) * 31) + (this.mGroupBuyExtraDescription != null ? this.mGroupBuyExtraDescription.hashCode() : 0)) * 31) + (this.mGroupBuyAddToCartModalText != null ? this.mGroupBuyAddToCartModalText.hashCode() : 0)) * 31) + (this.mSlideshow != null ? this.mSlideshow.hashCode() : 0)) * 31) + (this.mMerchantInfoImageUrl != null ? this.mMerchantInfoImageUrl.hashCode() : 0)) * 31) + (this.mMerchantInfoTitle != null ? this.mMerchantInfoTitle.hashCode() : 0)) * 31) + (this.mMerchantInfoSubtitle != null ? this.mMerchantInfoSubtitle.hashCode() : 0)) * 31) + (this.mMerchantInfoDetailTitles != null ? this.mMerchantInfoDetailTitles.hashCode() : 0)) * 31) + (this.mMerchantInfoDetailBodies != null ? this.mMerchantInfoDetailBodies.hashCode() : 0)) * 31) + (this.mBadge != null ? this.mBadge.hashCode() : 0)) * 31) + (this.mProductDetailsDiscountStripeSpecs != null ? this.mProductDetailsDiscountStripeSpecs.hashCode() : 0)) * 31) + (this.mProductBoostFeedTileLabelSpec != null ? this.mProductBoostFeedTileLabelSpec.hashCode() : 0)) * 31;
        if (this.mWishlistTooltipText != null) {
            i6 = this.mWishlistTooltipText.hashCode();
        }
        return hashCode + i6;
    }
}
