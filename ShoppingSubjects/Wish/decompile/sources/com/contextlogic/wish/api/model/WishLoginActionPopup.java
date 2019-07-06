package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLoginActionPopup extends BaseModel implements Parcelable {
    public static final Creator<WishLoginActionPopup> CREATOR = new Creator<WishLoginActionPopup>() {
        public WishLoginActionPopup createFromParcel(Parcel parcel) {
            return new WishLoginActionPopup(parcel);
        }

        public WishLoginActionPopup[] newArray(int i) {
            return new WishLoginActionPopup[i];
        }
    };
    private String mConfirmButtonText;
    private String mDescription;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    protected WishLoginActionPopup(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mConfirmButtonText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mConfirmButtonText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mDescription = jSONObject.optString("description");
        this.mConfirmButtonText = jSONObject.optString("confirm_button");
    }

    public WishLoginActionPopup(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getConfirmButtonText() {
        return this.mConfirmButtonText;
    }
}
