package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishScreenshotShareInfo extends BaseModel implements Parcelable {
    public static final Creator<WishScreenshotShareInfo> CREATOR = new Creator<WishScreenshotShareInfo>() {
        public WishScreenshotShareInfo createFromParcel(Parcel parcel) {
            return new WishScreenshotShareInfo(parcel);
        }

        public WishScreenshotShareInfo[] newArray(int i) {
            return new WishScreenshotShareInfo[i];
        }
    };
    private String mCancelText;
    private String mMessage;
    private String mSendText;
    private String mShareText;

    public int describeContents() {
        return 0;
    }

    public WishScreenshotShareInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mMessage = jSONObject.getString("message");
        this.mShareText = jSONObject.getString("share_text");
        this.mSendText = jSONObject.getString("send_text");
        this.mCancelText = jSONObject.getString("cancel_text");
    }

    protected WishScreenshotShareInfo(Parcel parcel) {
        this.mMessage = parcel.readString();
        this.mShareText = parcel.readString();
        this.mSendText = parcel.readString();
        this.mCancelText = parcel.readString();
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getShareText() {
        return this.mShareText;
    }

    public String getSendText() {
        return this.mSendText;
    }

    public String getCancelText() {
        return this.mCancelText;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mShareText);
        parcel.writeString(this.mSendText);
        parcel.writeString(this.mCancelText);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishScreenshotShareInfo wishScreenshotShareInfo = (WishScreenshotShareInfo) obj;
        if (this.mMessage == null ? wishScreenshotShareInfo.mMessage != null : !this.mMessage.equals(wishScreenshotShareInfo.mMessage)) {
            return false;
        }
        if (this.mShareText == null ? wishScreenshotShareInfo.mShareText != null : !this.mShareText.equals(wishScreenshotShareInfo.mShareText)) {
            return false;
        }
        if (this.mSendText == null ? wishScreenshotShareInfo.mSendText != null : !this.mSendText.equals(wishScreenshotShareInfo.mSendText)) {
            return false;
        }
        if (this.mCancelText != null) {
            z = this.mCancelText.equals(wishScreenshotShareInfo.mCancelText);
        } else if (wishScreenshotShareInfo.mCancelText != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.mMessage != null ? this.mMessage.hashCode() : 0) * 31) + (this.mShareText != null ? this.mShareText.hashCode() : 0)) * 31) + (this.mSendText != null ? this.mSendText.hashCode() : 0)) * 31;
        if (this.mCancelText != null) {
            i = this.mCancelText.hashCode();
        }
        return hashCode + i;
    }
}
