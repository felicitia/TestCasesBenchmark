package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.dialog.cartexpiry.CartExpiryItem;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCartItem extends BaseModel implements Parcelable, CartExpiryItem {
    public static final Creator<WishCartItem> CREATOR = new Creator<WishCartItem>() {
        public WishCartItem createFromParcel(Parcel parcel) {
            return new WishCartItem(parcel);
        }

        public WishCartItem[] newArray(int i) {
            return new WishCartItem[i];
        }
    };
    private double mAverageRating;
    private ArrayList<WishCartNotice> mCartNotices;
    private String mColor;
    private String mFulfilledByText;
    private String mGroupBuyId;
    private WishImage mImage;
    private int mInventory;
    private boolean mIsAvailable;
    private boolean mIsCToCItem;
    private boolean mIsDailyGiveaway;
    private boolean mIsFreeGift;
    private int mMaxOrderQuantity;
    private int mMaxShippingTime;
    private String mMerchantName;
    private int mMinShippingTime;
    private String mName;
    private int mNumBought;
    private WishLocalizedCurrencyValue mOriginalShippingPrice;
    private WishLocalizedCurrencyValue mPrice;
    private WishLocalizedCurrencyValue mPriceBeforeDiscounts;
    private WishPriceExpiryInfo mPriceExpiryInfo;
    private String mProductId;
    private WishLocalizedCurrencyValue mProductSubtotal;
    private WishPromotionAddToCartSpec mPromotionAddToCartSpec;
    private WishPromotionCartSpec mPromotionCartSpec;
    private int mQuantity;
    private int mRatingCount;
    private WishLocalizedCurrencyValue mRetailPrice;
    private String mReturnPolicyLongString;
    private String mReturnPolicyShortString;
    private String mShippingCountriesString;
    private String mShippingOptionId;
    private ArrayList<WishShippingOption> mShippingOptions;
    private WishLocalizedCurrencyValue mShippingPrice;
    private String mShippingPriceCountry;
    private String mShippingTimeString;
    private String mSize;
    private String mUrgencyText;
    private String mVariationId;
    private boolean mWasJustAdded;

    public int describeContents() {
        return 0;
    }

    public WishCartItem(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("price"), jSONObject.optJSONObject("localized_price"));
        this.mProductId = jSONObject.getString("product_id");
        this.mName = jSONObject.getString("name");
        this.mVariationId = jSONObject.getString("variation_id");
        this.mImage = new WishImage(jSONObject.getString("image_url"));
        this.mQuantity = jSONObject.getInt("quantity");
        this.mMaxOrderQuantity = jSONObject.optInt("max_quantity_allowed", 15);
        this.mInventory = jSONObject.getInt("inventory");
        this.mRetailPrice = new WishLocalizedCurrencyValue(jSONObject.optDouble("product_retail_subtotal"), jSONObject.optJSONObject("localized_product_retail_subtotal"));
        this.mNumBought = jSONObject.optInt("num_bought", 0);
        if (jSONObject.has("product_rating")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("product_rating");
            this.mRatingCount = jSONObject2.getInt("rating_count");
            this.mAverageRating = jSONObject2.getDouble("rating");
        } else {
            this.mRatingCount = 0;
            this.mAverageRating = 5.0d;
        }
        if (JsonUtil.hasValue(jSONObject, "size")) {
            this.mSize = jSONObject.getString("size");
        }
        if (JsonUtil.hasValue(jSONObject, "color")) {
            this.mColor = jSONObject.getString("color");
        }
        this.mMerchantName = jSONObject.getString("merchant_display_name");
        this.mShippingCountriesString = jSONObject.getString("shipping_countries_string");
        this.mShippingTimeString = jSONObject.getString("shipping_time_string");
        this.mIsCToCItem = jSONObject.optBoolean("is_c2c", false);
        this.mIsFreeGift = jSONObject.optBoolean("is_free_gift", false);
        this.mIsDailyGiveaway = jSONObject.optBoolean("is_daily_giveaway", false);
        this.mShippingPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("shipping"), jSONObject.optJSONObject("localized_shipping"));
        if (JsonUtil.hasValue(jSONObject, "shipping_before_discount")) {
            this.mOriginalShippingPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("shipping_before_discount"));
        }
        if (JsonUtil.hasValue(jSONObject, "shipping_price_country_code")) {
            this.mShippingPriceCountry = AddressUtil.getCountryName(jSONObject.getString("shipping_price_country_code"));
        }
        if (JsonUtil.hasValue(jSONObject, "return_policy_short")) {
            this.mReturnPolicyShortString = jSONObject.getString("return_policy_short");
        }
        if (JsonUtil.hasValue(jSONObject, "urgency_text")) {
            this.mUrgencyText = jSONObject.getString("urgency_text");
        }
        if (JsonUtil.hasValue(jSONObject, "return_policy_long")) {
            this.mReturnPolicyLongString = jSONObject.getString("return_policy_long");
        }
        if (JsonUtil.hasValue(jSONObject, "fulfilled_by_text")) {
            this.mFulfilledByText = jSONObject.getString("fulfilled_by_text");
        }
        if (JsonUtil.hasValue(jSONObject, "price_expiry_info")) {
            this.mPriceExpiryInfo = new WishPriceExpiryInfo(jSONObject.getJSONObject("price_expiry_info"));
        }
        this.mPriceBeforeDiscounts = new WishLocalizedCurrencyValue(jSONObject.getDouble("price_before_personal_price"), jSONObject.optJSONObject("localized_price_before_personal_price"));
        this.mProductSubtotal = new WishLocalizedCurrencyValue(jSONObject.getDouble("product_subtotal"), jSONObject.optJSONObject("localized_product_subtotal"));
        this.mMinShippingTime = jSONObject.optInt("min_shipping_time", 7);
        this.mMaxShippingTime = jSONObject.optInt("max_shipping_time", 21);
        if (JsonUtil.hasValue(jSONObject, "shipping_options")) {
            this.mShippingOptions = JsonUtil.parseArray(jSONObject, "shipping_options", new DataParser<WishShippingOption, JSONObject>() {
                public WishShippingOption parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishShippingOption(jSONObject);
                }
            });
        }
        if (JsonUtil.hasValue(jSONObject, "cart_notices")) {
            this.mCartNotices = JsonUtil.parseArray(jSONObject, "cart_notices", new DataParser<WishCartNotice, JSONObject>() {
                public WishCartNotice parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishCartNotice(jSONObject);
                }
            });
        }
        this.mGroupBuyId = JsonUtil.optString(jSONObject, "group_buy_id");
        this.mWasJustAdded = jSONObject.optBoolean("was_just_added", false);
        this.mIsAvailable = jSONObject.optBoolean("is_available", true);
        if (JsonUtil.hasValue(jSONObject, "promotion_cart_spec")) {
            this.mPromotionCartSpec = new WishPromotionCartSpec(jSONObject.getJSONObject("promotion_cart_spec"));
        }
        if (JsonUtil.hasValue(jSONObject, "promotion_add_to_cart_spec")) {
            this.mPromotionAddToCartSpec = new WishPromotionAddToCartSpec(jSONObject.getJSONObject("promotion_add_to_cart_spec"));
        }
    }

    public String createSizeAndColorText() {
        String str;
        String str2 = "";
        if (getSize() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(getSize());
            str2 = sb.toString();
        }
        if (getColor() == null) {
            return str2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        if (str2.equals("")) {
            str = getColor();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(", ");
            sb3.append(getColor());
            str = sb3.toString();
        }
        sb2.append(str);
        return sb2.toString();
    }

    public String getUrgencyText() {
        return this.mUrgencyText;
    }

    public WishLocalizedCurrencyValue getProductSubtotal() {
        return this.mProductSubtotal;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getName() {
        return this.mName;
    }

    public String getVariationId() {
        return this.mVariationId;
    }

    public ArrayList<WishShippingOption> getShippingOptions() {
        return this.mShippingOptions;
    }

    public WishShippingOption getSelectedShippingOption() {
        if (this.mShippingOptions != null) {
            Iterator it = this.mShippingOptions.iterator();
            while (it.hasNext()) {
                WishShippingOption wishShippingOption = (WishShippingOption) it.next();
                if (wishShippingOption.isSelected()) {
                    return wishShippingOption;
                }
            }
        }
        return null;
    }

    public WishImage getImage() {
        return this.mImage;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public int getMaxOrderQuantity() {
        return this.mMaxOrderQuantity;
    }

    public int getInventory() {
        return this.mInventory;
    }

    public String getColor() {
        return this.mColor;
    }

    public String getSize() {
        return this.mSize;
    }

    public String getShippingTimeString() {
        return this.mShippingTimeString;
    }

    public ArrayList<WishCartNotice> getCartNotices() {
        return this.mCartNotices;
    }

    public WishLocalizedCurrencyValue getPrice() {
        return this.mPrice;
    }

    public WishLocalizedCurrencyValue getRetailPrice() {
        return this.mRetailPrice;
    }

    public WishLocalizedCurrencyValue getShippingPrice() {
        return this.mShippingPrice;
    }

    public WishPriceExpiryInfo getPriceExpiryInfo() {
        return this.mPriceExpiryInfo;
    }

    public boolean wasJustAdded() {
        return this.mWasJustAdded;
    }

    public boolean isAvailable() {
        return this.mIsAvailable;
    }

    public boolean isFreeGift() {
        return this.mIsFreeGift;
    }

    public boolean isDailyGiveaway() {
        return this.mIsDailyGiveaway;
    }

    public WishPromotionCartSpec getPromotionCartSpec() {
        return this.mPromotionCartSpec;
    }

    public WishPromotionAddToCartSpec getPromotionAddToCartSpec() {
        return this.mPromotionAddToCartSpec;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsCToCItem ? (byte) 1 : 0);
        parcel.writeByte(this.mIsFreeGift ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDailyGiveaway ? (byte) 1 : 0);
        parcel.writeDouble(this.mAverageRating);
        parcel.writeInt(this.mInventory);
        parcel.writeInt(this.mMaxShippingTime);
        parcel.writeInt(this.mMinShippingTime);
        parcel.writeInt(this.mNumBought);
        parcel.writeInt(this.mQuantity);
        parcel.writeInt(this.mMaxOrderQuantity);
        parcel.writeInt(this.mRatingCount);
        parcel.writeTypedList(this.mCartNotices);
        parcel.writeString(this.mColor);
        parcel.writeString(this.mFulfilledByText);
        parcel.writeString(this.mMerchantName);
        parcel.writeString(this.mName);
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mReturnPolicyLongString);
        parcel.writeString(this.mReturnPolicyShortString);
        parcel.writeString(this.mShippingCountriesString);
        parcel.writeString(this.mShippingOptionId);
        parcel.writeString(this.mShippingPriceCountry);
        parcel.writeString(this.mShippingTimeString);
        parcel.writeString(this.mSize);
        parcel.writeString(this.mUrgencyText);
        parcel.writeString(this.mVariationId);
        parcel.writeTypedList(this.mShippingOptions);
        parcel.writeParcelable(this.mImage, i);
        parcel.writeParcelable(this.mProductSubtotal, i);
        parcel.writeParcelable(this.mPrice, i);
        parcel.writeParcelable(this.mPriceBeforeDiscounts, i);
        parcel.writeParcelable(this.mRetailPrice, i);
        parcel.writeParcelable(this.mShippingPrice, i);
        parcel.writeParcelable(this.mOriginalShippingPrice, i);
        parcel.writeParcelable(this.mPriceExpiryInfo, i);
        parcel.writeString(this.mGroupBuyId);
        parcel.writeByte(this.mWasJustAdded ? (byte) 1 : 0);
        parcel.writeByte(this.mIsAvailable ? (byte) 1 : 0);
        parcel.writeParcelable(this.mPromotionCartSpec, i);
        parcel.writeParcelable(this.mPromotionAddToCartSpec, i);
    }

    protected WishCartItem(Parcel parcel) {
        boolean z = false;
        this.mIsCToCItem = parcel.readByte() != 0;
        this.mIsFreeGift = parcel.readByte() != 0;
        this.mIsDailyGiveaway = parcel.readByte() != 0;
        this.mAverageRating = parcel.readDouble();
        this.mInventory = parcel.readInt();
        this.mMaxShippingTime = parcel.readInt();
        this.mMinShippingTime = parcel.readInt();
        this.mNumBought = parcel.readInt();
        this.mQuantity = parcel.readInt();
        this.mMaxOrderQuantity = parcel.readInt();
        this.mRatingCount = parcel.readInt();
        this.mCartNotices = parcel.createTypedArrayList(WishCartNotice.CREATOR);
        this.mColor = parcel.readString();
        this.mFulfilledByText = parcel.readString();
        this.mMerchantName = parcel.readString();
        this.mName = parcel.readString();
        this.mProductId = parcel.readString();
        this.mReturnPolicyLongString = parcel.readString();
        this.mReturnPolicyShortString = parcel.readString();
        this.mShippingCountriesString = parcel.readString();
        this.mShippingOptionId = parcel.readString();
        this.mShippingPriceCountry = parcel.readString();
        this.mShippingTimeString = parcel.readString();
        this.mSize = parcel.readString();
        this.mUrgencyText = parcel.readString();
        this.mVariationId = parcel.readString();
        this.mShippingOptions = parcel.createTypedArrayList(WishShippingOption.CREATOR);
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mProductSubtotal = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mPriceBeforeDiscounts = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mRetailPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mShippingPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mOriginalShippingPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mPriceExpiryInfo = (WishPriceExpiryInfo) parcel.readParcelable(WishPriceExpiryInfo.class.getClassLoader());
        this.mGroupBuyId = parcel.readString();
        this.mWasJustAdded = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsAvailable = z;
        this.mPromotionCartSpec = (WishPromotionCartSpec) parcel.readParcelable(WishPromotionCartSpec.class.getClassLoader());
        this.mPromotionAddToCartSpec = (WishPromotionAddToCartSpec) parcel.readParcelable(WishPromotionAddToCartSpec.class.getClassLoader());
    }
}
