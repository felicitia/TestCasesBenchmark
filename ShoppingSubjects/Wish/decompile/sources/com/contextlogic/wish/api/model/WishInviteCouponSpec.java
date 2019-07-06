package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishInviteCouponSpec extends BaseModel implements Parcelable {
    public static final Creator<WishInviteCouponSpec> CREATOR = new Creator<WishInviteCouponSpec>() {
        public WishInviteCouponSpec createFromParcel(Parcel parcel) {
            return new WishInviteCouponSpec(parcel);
        }

        public WishInviteCouponSpec[] newArray(int i) {
            return new WishInviteCouponSpec[i];
        }
    };
    private String mCode;
    private String mMenuTitle;
    private String mMessage;
    private String mShareButtonText;
    private String mShareSubject;
    private String mShareText;
    private String mShortMessage;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishInviteCouponSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mMenuTitle = jSONObject.getString("menu_title");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.getString("message");
        this.mShortMessage = jSONObject.getString("short_message");
        this.mCode = jSONObject.getString("code");
        this.mShareButtonText = jSONObject.getString("share_button_text");
        this.mShareSubject = jSONObject.getString("share_subject");
        this.mShareText = jSONObject.getString("share_text");
    }

    protected WishInviteCouponSpec(Parcel parcel) {
        this.mMenuTitle = parcel.readString();
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mShortMessage = parcel.readString();
        this.mCode = parcel.readString();
        this.mShareButtonText = parcel.readString();
        this.mShareSubject = parcel.readString();
        this.mShareText = parcel.readString();
    }

    public String getMenuTitle() {
        return this.mMenuTitle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getShortMessage() {
        return this.mShortMessage;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getShareButtonText() {
        return this.mShareButtonText;
    }

    public String getShareSubject() {
        return this.mShareSubject;
    }

    public String getShareText() {
        return this.mShareText;
    }

    public JSONObject getJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("menu_title", this.mMenuTitle);
            jSONObject.put(StrongAuth.AUTH_TITLE, this.mTitle);
            jSONObject.put("message", this.mMessage);
            jSONObject.put("short_message", this.mShortMessage);
            jSONObject.put("code", this.mCode);
            jSONObject.put("share_button_text", this.mShareButtonText);
            jSONObject.put("share_subject", this.mShareSubject);
            jSONObject.put("share_text", this.mShareText);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMenuTitle);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mShortMessage);
        parcel.writeString(this.mCode);
        parcel.writeString(this.mShareButtonText);
        parcel.writeString(this.mShareSubject);
        parcel.writeString(this.mShareText);
    }
}
