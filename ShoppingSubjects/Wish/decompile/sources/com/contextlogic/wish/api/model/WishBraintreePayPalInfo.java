package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishBraintreePayPalInfo extends BaseModel implements Parcelable {
    public static final Creator<WishBraintreePayPalInfo> CREATOR = new Creator<WishBraintreePayPalInfo>() {
        public WishBraintreePayPalInfo createFromParcel(Parcel parcel) {
            return new WishBraintreePayPalInfo(parcel);
        }

        public WishBraintreePayPalInfo[] newArray(int i) {
            return new WishBraintreePayPalInfo[i];
        }
    };
    private String mPaymentMethodToken;

    public int describeContents() {
        return 0;
    }

    public WishBraintreePayPalInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) {
        this.mPaymentMethodToken = JsonUtil.optString(jSONObject, "payment_method_token");
    }

    protected WishBraintreePayPalInfo(Parcel parcel) {
        this.mPaymentMethodToken = parcel.readString();
    }

    public String getPaymentMethodToken() {
        return this.mPaymentMethodToken;
    }

    public void revokePaymentMethodToken() {
        this.mPaymentMethodToken = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPaymentMethodToken);
    }
}
