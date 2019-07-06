package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSignupVideoPopupSpec extends WishVideoPopupSpec {
    public static final Creator<WishSignupVideoPopupSpec> CREATOR = new Creator<WishSignupVideoPopupSpec>() {
        public WishSignupVideoPopupSpec createFromParcel(Parcel parcel) {
            return new WishSignupVideoPopupSpec(parcel);
        }

        public WishSignupVideoPopupSpec[] newArray(int i) {
            return new WishSignupVideoPopupSpec[i];
        }
    };
    private String mButtonText;
    private String mSubtitleText;
    private String mTitleText;

    private WishSignupVideoPopupSpec(Parcel parcel) {
        super(parcel);
        this.mTitleText = parcel.readString();
        this.mSubtitleText = parcel.readString();
        this.mButtonText = parcel.readString();
    }

    public WishSignupVideoPopupSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public boolean equals(Object obj) {
        WishSignupVideoPopupSpec wishSignupVideoPopupSpec = (WishSignupVideoPopupSpec) obj;
        return super.equals(obj) && this.mTitleText.equals(wishSignupVideoPopupSpec.getTitleText()) && this.mSubtitleText.equals(wishSignupVideoPopupSpec.getSubtitleText()) && this.mButtonText.equals(wishSignupVideoPopupSpec.getButtonText());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mTitleText);
        parcel.writeString(this.mSubtitleText);
        parcel.writeString(this.mButtonText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        super.parseJson(jSONObject);
        this.mTitleText = JsonUtil.optString(jSONObject, "title_text");
        this.mSubtitleText = jSONObject.optString("subtitle_text");
        this.mButtonText = jSONObject.optString("button_text");
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public String getVideoResource() {
        return this.mVideoResource;
    }
}
