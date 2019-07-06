package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSignupFreeGiftsAbandon extends BaseModel implements Parcelable {
    public static final Creator<WishSignupFreeGiftsAbandon> CREATOR = new Creator<WishSignupFreeGiftsAbandon>() {
        public WishSignupFreeGiftsAbandon createFromParcel(Parcel parcel) {
            return new WishSignupFreeGiftsAbandon(parcel);
        }

        public WishSignupFreeGiftsAbandon[] newArray(int i) {
            return new WishSignupFreeGiftsAbandon[i];
        }
    };
    private String mActionButtonText;
    private String mCancelButtonText;
    private String mMessage;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishSignupFreeGiftsAbandon(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.getString("message");
        this.mActionButtonText = jSONObject.getString("action_button_text");
        this.mCancelButtonText = jSONObject.getString("cancel_button_text");
    }

    protected WishSignupFreeGiftsAbandon(Parcel parcel) {
        this.mActionButtonText = parcel.readString();
        this.mCancelButtonText = parcel.readString();
        this.mMessage = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getActionButtonText() {
        return this.mActionButtonText;
    }

    public String getCancelButtonText() {
        return this.mCancelButtonText;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mActionButtonText);
        parcel.writeString(this.mCancelButtonText);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mTitle);
    }
}
