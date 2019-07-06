package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLoginActionUpdateApp extends BaseModel implements Parcelable {
    public static final Creator<WishLoginActionUpdateApp> CREATOR = new Creator<WishLoginActionUpdateApp>() {
        public WishLoginActionUpdateApp createFromParcel(Parcel parcel) {
            return new WishLoginActionUpdateApp(parcel);
        }

        public WishLoginActionUpdateApp[] newArray(int i) {
            return new WishLoginActionUpdateApp[i];
        }
    };
    private String mDescription;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    protected WishLoginActionUpdateApp(Parcel parcel) {
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

    public WishLoginActionUpdateApp(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }
}
