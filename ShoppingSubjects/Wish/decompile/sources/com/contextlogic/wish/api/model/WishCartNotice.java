package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCartNotice extends BaseModel {
    public static final Creator<WishCartNotice> CREATOR = new Creator<WishCartNotice>() {
        public WishCartNotice createFromParcel(Parcel parcel) {
            return new WishCartNotice(parcel);
        }

        public WishCartNotice[] newArray(int i) {
            return new WishCartNotice[i];
        }
    };
    private String mIconURL;
    private String mMessage;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    protected WishCartNotice(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mIconURL = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mIconURL);
    }

    public WishCartNotice(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.getString("message");
        this.mIconURL = JsonUtil.optString(jSONObject, "icon");
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getIconURL() {
        return this.mIconURL;
    }
}
