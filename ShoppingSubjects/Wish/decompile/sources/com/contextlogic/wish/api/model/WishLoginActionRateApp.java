package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLoginActionRateApp extends BaseModel implements Parcelable {
    public static final Creator<WishLoginActionRateApp> CREATOR = new Creator<WishLoginActionRateApp>() {
        public WishLoginActionRateApp createFromParcel(Parcel parcel) {
            return new WishLoginActionRateApp(parcel);
        }

        public WishLoginActionRateApp[] newArray(int i) {
            return new WishLoginActionRateApp[i];
        }
    };
    private String mDescription;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    protected WishLoginActionRateApp(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mDescription = jSONObject.optString("description");
    }

    public WishLoginActionRateApp(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }
}
