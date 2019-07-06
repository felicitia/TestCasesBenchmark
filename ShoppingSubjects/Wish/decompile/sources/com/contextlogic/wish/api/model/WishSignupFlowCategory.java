package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSignupFlowCategory extends BaseModel implements Parcelable {
    public static final Creator<WishSignupFlowCategory> CREATOR = new Creator<WishSignupFlowCategory>() {
        public WishSignupFlowCategory createFromParcel(Parcel parcel) {
            return new WishSignupFlowCategory(parcel);
        }

        public WishSignupFlowCategory[] newArray(int i) {
            return new WishSignupFlowCategory[i];
        }
    };
    private WishImage mImage;
    private String mTitle;
    private String mTrueTag;

    public int describeContents() {
        return 0;
    }

    public WishSignupFlowCategory(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishSignupFlowCategory(Parcel parcel) {
        this.mTrueTag = parcel.readString();
        this.mTitle = parcel.readString();
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        if (JsonUtil.hasValue(jSONObject, "image_url")) {
            this.mImage = new WishImage(jSONObject.getString("image_url"));
        }
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mTrueTag = jSONObject.getString("true_tag");
    }

    public String getId() {
        return this.mTrueTag;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public WishImage getImg() {
        return this.mImage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTrueTag);
        parcel.writeString(this.mTitle);
        parcel.writeParcelable(this.mImage, 0);
    }
}
