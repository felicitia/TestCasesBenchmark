package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishShippingOption extends BaseModel implements Parcelable {
    public static final Creator<WishShippingOption> CREATOR = new Creator<WishShippingOption>() {
        public WishShippingOption createFromParcel(Parcel parcel) {
            return new WishShippingOption(parcel);
        }

        public WishShippingOption[] newArray(int i) {
            return new WishShippingOption[i];
        }
    };
    private boolean mIsExpressType;
    private String mName;
    private String mOptionId;
    private WishLocalizedCurrencyValue mPrice;
    private boolean mSelected;
    private String mShippingTimeString;

    public int describeContents() {
        return 0;
    }

    public WishShippingOption(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mOptionId = jSONObject.getString("option_id");
        this.mName = jSONObject.getString("name");
        this.mSelected = jSONObject.getBoolean("selected");
        this.mShippingTimeString = jSONObject.getString("shipping_time_string");
        if (JsonUtil.hasValue(jSONObject, "price")) {
            this.mPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("price"), jSONObject.optJSONObject("localized_price"));
        }
        this.mIsExpressType = jSONObject.optBoolean("is_express_type", false);
    }

    protected WishShippingOption(Parcel parcel) {
        this.mOptionId = parcel.readString();
        this.mName = parcel.readString();
        this.mShippingTimeString = parcel.readString();
        boolean z = false;
        this.mSelected = parcel.readByte() != 0;
        this.mPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsExpressType = z;
    }

    public String getOptionId() {
        return this.mOptionId;
    }

    public String getName() {
        return this.mName;
    }

    public String getShippingTimeString() {
        return this.mShippingTimeString;
    }

    public WishLocalizedCurrencyValue getPrice() {
        return this.mPrice;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public boolean isExpressType() {
        return this.mIsExpressType || getOptionId().equals("wish_express");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mOptionId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mShippingTimeString);
        parcel.writeByte(this.mSelected ? (byte) 1 : 0);
        parcel.writeParcelable(this.mPrice, 0);
        parcel.writeByte(this.mIsExpressType ? (byte) 1 : 0);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishShippingOption wishShippingOption = (WishShippingOption) obj;
        if (this.mSelected != wishShippingOption.mSelected) {
            return false;
        }
        if (this.mOptionId == null ? wishShippingOption.mOptionId != null : !this.mOptionId.equals(wishShippingOption.mOptionId)) {
            return false;
        }
        if (this.mName == null ? wishShippingOption.mName != null : !this.mName.equals(wishShippingOption.mName)) {
            return false;
        }
        if (this.mShippingTimeString == null ? wishShippingOption.mShippingTimeString != null : !this.mShippingTimeString.equals(wishShippingOption.mShippingTimeString)) {
            return false;
        }
        if (this.mPrice == null ? wishShippingOption.mPrice != null : this.mPrice.equals(wishShippingOption.mPrice)) {
            return false;
        }
        if (this.mIsExpressType != wishShippingOption.mIsExpressType) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.mOptionId != null ? this.mOptionId.hashCode() : 0) * 31) + (this.mName != null ? this.mName.hashCode() : 0)) * 31) + (this.mShippingTimeString != null ? this.mShippingTimeString.hashCode() : 0)) * 31;
        if (this.mPrice != null) {
            i = this.mPrice.hashCode();
        }
        return ((((hashCode + i) * 31) + (this.mSelected ? 1 : 0)) * 31) + (this.mIsExpressType ? 1 : 0);
    }
}
