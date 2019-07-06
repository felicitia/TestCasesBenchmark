package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishBoletoInfo extends BaseModel implements Parcelable {
    public static final Creator<WishBoletoInfo> CREATOR = new Creator<WishBoletoInfo>() {
        public WishBoletoInfo createFromParcel(Parcel parcel) {
            return new WishBoletoInfo(parcel);
        }

        public WishBoletoInfo[] newArray(int i) {
            return new WishBoletoInfo[i];
        }
    };
    private String mIdentityNumber;
    private String mName;

    public int describeContents() {
        return 0;
    }

    public WishBoletoInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mName = jSONObject.getString("name");
        this.mIdentityNumber = jSONObject.getString("identity_number");
    }

    protected WishBoletoInfo(Parcel parcel) {
        this.mIdentityNumber = parcel.readString();
        this.mName = parcel.readString();
    }

    public String getLastFourDigits() {
        if (this.mIdentityNumber == null) {
            return null;
        }
        return this.mIdentityNumber.substring(Math.max(0, this.mIdentityNumber.length() - 4));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentityNumber);
        parcel.writeString(this.mName);
    }
}
