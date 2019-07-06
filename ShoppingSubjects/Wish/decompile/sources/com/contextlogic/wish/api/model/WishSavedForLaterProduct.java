package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.dialog.cartexpiry.CartExpiryItem;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSavedForLaterProduct extends BaseModel implements CartExpiryItem {
    public static final Creator<WishCartItem> CREATOR = new Creator<WishCartItem>() {
        public WishCartItem createFromParcel(Parcel parcel) {
            return new WishCartItem(parcel);
        }

        public WishCartItem[] newArray(int i) {
            return new WishCartItem[i];
        }
    };
    private String mColor;
    private WishImage mImage;
    private String mName;
    private WishLocalizedCurrencyValue mPrice;
    private WishPriceExpiryInfo mPriceExpiryInfo;
    private String mProductId;
    private WishLocalizedCurrencyValue mProductSubtotal;
    private WishLocalizedCurrencyValue mRetailPrice;
    private String mShippingOptionId;
    private ArrayList<WishShippingOption> mShippingOptions;
    private String mSize;
    private String mVariationId;

    public int describeContents() {
        return 0;
    }

    public WishSavedForLaterProduct(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("price"), jSONObject.optJSONObject("localized_price"));
        this.mProductId = jSONObject.getString("product_id");
        this.mName = jSONObject.getString("name");
        this.mVariationId = jSONObject.getString("variation_id");
        this.mImage = new WishImage(jSONObject.getString("image_url"));
        this.mRetailPrice = new WishLocalizedCurrencyValue(jSONObject.optDouble("product_retail_subtotal"), jSONObject.optJSONObject("localized_product_retail_subtotal"));
        if (JsonUtil.hasValue(jSONObject, "size")) {
            this.mSize = jSONObject.getString("size");
        }
        if (JsonUtil.hasValue(jSONObject, "color")) {
            this.mColor = jSONObject.getString("color");
        }
        if (JsonUtil.hasValue(jSONObject, "price_expiry_info")) {
            this.mPriceExpiryInfo = new WishPriceExpiryInfo(jSONObject.getJSONObject("price_expiry_info"));
        }
        this.mProductSubtotal = new WishLocalizedCurrencyValue(jSONObject.getDouble("product_subtotal"), jSONObject.optJSONObject("localized_product_subtotal"));
        if (JsonUtil.hasValue(jSONObject, "shipping_options")) {
            this.mShippingOptions = JsonUtil.parseArray(jSONObject, "shipping_options", new DataParser<WishShippingOption, JSONObject>() {
                public WishShippingOption parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishShippingOption(jSONObject);
                }
            });
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

    public WishLocalizedCurrencyValue getProductSubtotal() {
        return this.mProductSubtotal;
    }

    public WishPriceExpiryInfo getPriceExpiryInfo() {
        return this.mPriceExpiryInfo;
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

    public WishImage getImage() {
        return this.mImage;
    }

    public String getColor() {
        return this.mColor;
    }

    public String getSize() {
        return this.mSize;
    }

    public WishLocalizedCurrencyValue getRetailPrice() {
        return this.mRetailPrice;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mColor);
        parcel.writeString(this.mName);
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mShippingOptionId);
        parcel.writeString(this.mSize);
        parcel.writeString(this.mVariationId);
        parcel.writeParcelable(this.mImage, 0);
        parcel.writeParcelable(this.mProductSubtotal, 0);
        parcel.writeParcelable(this.mPrice, 0);
        parcel.writeParcelable(this.mRetailPrice, 0);
        parcel.writeParcelable(this.mPriceExpiryInfo, 0);
        if (this.mShippingOptions != null) {
            int size = this.mShippingOptions.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeParcelable((Parcelable) this.mShippingOptions.get(i2), 0);
            }
            return;
        }
        parcel.writeInt(0);
    }
}
