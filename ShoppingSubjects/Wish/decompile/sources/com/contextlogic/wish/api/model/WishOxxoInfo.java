package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishOxxoInfo extends BaseModel implements Parcelable {
    public static final Creator<WishOxxoInfo> CREATOR = new Creator<WishOxxoInfo>() {
        public WishOxxoInfo createFromParcel(Parcel parcel) {
            return new WishOxxoInfo(parcel);
        }

        public WishOxxoInfo[] newArray(int i) {
            return new WishOxxoInfo[i];
        }
    };
    private String mEmail;
    private String mName;

    public int describeContents() {
        return 0;
    }

    public WishOxxoInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mEmail = jSONObject.getString("email");
        this.mName = jSONObject.getString("name");
    }

    protected WishOxxoInfo(Parcel parcel) {
        this.mName = parcel.readString();
        this.mEmail = parcel.readString();
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mName);
    }
}
