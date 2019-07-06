package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceLoanBannerSpec extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceLoanBannerSpec> CREATOR = new Creator<WishCommerceLoanBannerSpec>() {
        public WishCommerceLoanBannerSpec createFromParcel(Parcel parcel) {
            return new WishCommerceLoanBannerSpec(parcel);
        }

        public WishCommerceLoanBannerSpec[] newArray(int i) {
            return new WishCommerceLoanBannerSpec[i];
        }
    };
    private String mBackgroundColor;
    private String mBackgroundImageUrl;
    private String mButtonText;
    private String mDescription;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public String getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public String getBackgroundImageUrl() {
        return this.mBackgroundImageUrl;
    }

    public WishCommerceLoanBannerSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishCommerceLoanBannerSpec(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mButtonText = parcel.readString();
        this.mBackgroundColor = parcel.readString();
        this.mBackgroundImageUrl = parcel.readString();
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mDescription = JsonUtil.optString(jSONObject, "description");
        this.mButtonText = JsonUtil.optString(jSONObject, "button_text");
        this.mBackgroundColor = JsonUtil.optString(jSONObject, "background_color");
        this.mBackgroundImageUrl = JsonUtil.optString(jSONObject, "background_image");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mButtonText);
        parcel.writeString(this.mBackgroundColor);
        parcel.writeString(this.mBackgroundImageUrl);
    }
}
