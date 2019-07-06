package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishShippingInfo extends BaseModel implements Parcelable {
    public static final Creator<WishShippingInfo> CREATOR = new Creator<WishShippingInfo>() {
        public WishShippingInfo createFromParcel(Parcel parcel) {
            return new WishShippingInfo(parcel);
        }

        public WishShippingInfo[] newArray(int i) {
            return new WishShippingInfo[i];
        }
    };
    private String mCity;
    private String mCountryCode;
    private String mId;
    private String mName;
    private String mPhoneNumber;
    private String mState;
    private String mStreetAddressLineOne;
    private String mStreetAddressLineTwo;
    private String mZipCode;

    public int describeContents() {
        return 0;
    }

    public WishShippingInfo() {
    }

    public WishShippingInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        if (JsonUtil.hasValue(jSONObject, "id")) {
            this.mId = jSONObject.getString("id");
        }
        if (JsonUtil.hasValue(jSONObject, "name")) {
            this.mName = jSONObject.getString("name");
        }
        if (JsonUtil.hasValue(jSONObject, "zipcode")) {
            this.mZipCode = jSONObject.getString("zipcode");
        }
        if (JsonUtil.hasValue(jSONObject, "state")) {
            this.mState = jSONObject.getString("state");
        }
        if (JsonUtil.hasValue(jSONObject, "country_code")) {
            this.mCountryCode = jSONObject.getString("country_code");
        }
        if (JsonUtil.hasValue(jSONObject, "phone_number")) {
            this.mPhoneNumber = jSONObject.getString("phone_number");
        }
        if (JsonUtil.hasValue(jSONObject, "city")) {
            this.mCity = jSONObject.getString("city");
        }
        if (JsonUtil.hasValue(jSONObject, "street_address1")) {
            this.mStreetAddressLineOne = jSONObject.getString("street_address1");
        }
        if (JsonUtil.hasValue(jSONObject, "street_address2")) {
            this.mStreetAddressLineTwo = jSONObject.getString("street_address2");
        }
    }

    protected WishShippingInfo(Parcel parcel) {
        this.mId = parcel.readString();
        this.mCity = parcel.readString();
        this.mCountryCode = parcel.readString();
        this.mName = parcel.readString();
        this.mPhoneNumber = parcel.readString();
        this.mState = parcel.readString();
        this.mStreetAddressLineOne = parcel.readString();
        this.mStreetAddressLineTwo = parcel.readString();
        this.mZipCode = parcel.readString();
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setName(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        this.mName = sb.toString();
    }

    public void setZipCode(String str) {
        this.mZipCode = str;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public void setCountryCode(String str) {
        this.mCountryCode = str;
    }

    public void setPhoneNumber(String str) {
        this.mPhoneNumber = str;
    }

    public void setCity(String str) {
        this.mCity = str;
    }

    public void setStreetAddressLineOne(String str) {
        this.mStreetAddressLineOne = str;
    }

    public void setStreetAddressLineTwo(String str) {
        this.mStreetAddressLineTwo = str;
    }

    public String getCityAndState() {
        if (getCity() == null || getState() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getCity());
        sb.append(", ");
        sb.append(getState());
        return sb.toString();
    }

    public String getName() {
        return this.mName;
    }

    public String getZipCode() {
        return this.mZipCode;
    }

    public String getState() {
        return this.mState;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getStreetAddressLineOne() {
        return this.mStreetAddressLineOne;
    }

    public String getStreetAddressLineTwo() {
        return this.mStreetAddressLineTwo;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getId() {
        return this.mId;
    }

    public String getFormattedString() {
        return getFormattedString(true);
    }

    public String getFormattedString(boolean z) {
        StringBuilder sb = new StringBuilder();
        if (getName() != null) {
            sb.append(getName());
            if (z) {
                sb.append("\n");
            } else {
                sb.append(", ");
            }
        }
        if (getStreetAddressLineOne() != null) {
            sb.append(getStreetAddressLineOne());
            if (z) {
                sb.append("\n");
            } else {
                sb.append(", ");
            }
        }
        if (getStreetAddressLineTwo() != null) {
            sb.append(getStreetAddressLineTwo());
            if (z) {
                sb.append("\n");
            } else {
                sb.append(", ");
            }
        }
        if (!(getCity() == null && getState() == null && getZipCode() == null)) {
            boolean z2 = false;
            if (getCity() != null) {
                sb.append(getCity());
                z2 = true;
            }
            if (getState() != null) {
                if (z2) {
                    sb.append(", ");
                }
                sb.append(getState());
                z2 = true;
            }
            if (getZipCode() != null) {
                if (z2) {
                    sb.append(", ");
                }
                sb.append(getZipCode());
            }
            if (z) {
                sb.append("\n");
            }
        }
        if (getCountryCode() != null) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(AddressUtil.getCountryName(getCountryCode()));
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mCity);
        parcel.writeString(this.mCountryCode);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPhoneNumber);
        parcel.writeString(this.mState);
        parcel.writeString(this.mStreetAddressLineOne);
        parcel.writeString(this.mStreetAddressLineTwo);
        parcel.writeString(this.mZipCode);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishShippingInfo wishShippingInfo = (WishShippingInfo) obj;
        if (this.mId == null ? wishShippingInfo.mId != null : !this.mId.equals(wishShippingInfo.mId)) {
            return false;
        }
        if (this.mCity == null ? wishShippingInfo.mCity != null : !this.mCity.equals(wishShippingInfo.mCity)) {
            return false;
        }
        if (this.mCountryCode == null ? wishShippingInfo.mCountryCode != null : !this.mCountryCode.equals(wishShippingInfo.mCountryCode)) {
            return false;
        }
        if (this.mName == null ? wishShippingInfo.mName != null : !this.mName.equals(wishShippingInfo.mName)) {
            return false;
        }
        if (this.mPhoneNumber == null ? wishShippingInfo.mPhoneNumber != null : !this.mPhoneNumber.equals(wishShippingInfo.mPhoneNumber)) {
            return false;
        }
        if (this.mState == null ? wishShippingInfo.mState != null : !this.mState.equals(wishShippingInfo.mState)) {
            return false;
        }
        if (this.mStreetAddressLineOne == null ? wishShippingInfo.mStreetAddressLineOne != null : !this.mStreetAddressLineOne.equals(wishShippingInfo.mStreetAddressLineOne)) {
            return false;
        }
        if (this.mStreetAddressLineTwo == null ? wishShippingInfo.mStreetAddressLineTwo != null : !this.mStreetAddressLineTwo.equals(wishShippingInfo.mStreetAddressLineTwo)) {
            return false;
        }
        if (this.mZipCode != null) {
            z = this.mZipCode.equals(wishShippingInfo.mZipCode);
        } else if (wishShippingInfo.mZipCode != null) {
            z = false;
        }
        return z;
    }
}
