package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLoginActionDeepLink extends BaseModel implements Parcelable {
    public static final Creator<WishLoginActionDeepLink> CREATOR = new Creator<WishLoginActionDeepLink>() {
        public WishLoginActionDeepLink createFromParcel(Parcel parcel) {
            return new WishLoginActionDeepLink(parcel);
        }

        public WishLoginActionDeepLink[] newArray(int i) {
            return new WishLoginActionDeepLink[i];
        }
    };
    private String mDeeplink;

    public int describeContents() {
        return 0;
    }

    protected WishLoginActionDeepLink(Parcel parcel) {
        this.mDeeplink = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDeeplink);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mDeeplink = jSONObject.getString("deep_link");
    }

    public WishLoginActionDeepLink(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public String getDeeplink() {
        return this.mDeeplink;
    }
}
