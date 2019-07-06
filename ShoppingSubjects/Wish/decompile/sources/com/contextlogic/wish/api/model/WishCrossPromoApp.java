package com.contextlogic.wish.api.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCrossPromoApp extends BaseModel implements Parcelable {
    public static final Creator<WishCrossPromoApp> CREATOR = new Creator<WishCrossPromoApp>() {
        public WishCrossPromoApp createFromParcel(Parcel parcel) {
            return new WishCrossPromoApp(parcel);
        }

        public WishCrossPromoApp[] newArray(int i) {
            return new WishCrossPromoApp[i];
        }
    };
    private String mAction;
    private int mActionButtonColor;
    private String mActionButtonText;
    private String mImageUrl;
    private String mMessage;
    private String mProductId;
    private String mPromoId;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishCrossPromoApp(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mMessage = JsonUtil.optString(jSONObject, "message");
        this.mPromoId = jSONObject.getString("id");
        this.mImageUrl = jSONObject.getString("image_url");
        this.mActionButtonText = JsonUtil.optString(jSONObject, "action_button_text");
        if (JsonUtil.hasValue(jSONObject, "action_button_color")) {
            this.mActionButtonColor = Color.parseColor(jSONObject.getString("action_button_color"));
        }
        this.mAction = jSONObject.getString("action");
        this.mProductId = JsonUtil.optString(jSONObject, "product_id");
    }

    protected WishCrossPromoApp(Parcel parcel) {
        this.mActionButtonColor = parcel.readInt();
        this.mAction = parcel.readString();
        this.mActionButtonText = parcel.readString();
        this.mImageUrl = parcel.readString();
        this.mMessage = parcel.readString();
        this.mProductId = parcel.readString();
        this.mPromoId = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getPromoId() {
        return this.mPromoId;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getActionButtonText() {
        return this.mActionButtonText;
    }

    public int getActionButtonColor() {
        return this.mActionButtonColor;
    }

    public String getAction() {
        return this.mAction;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mActionButtonColor);
        parcel.writeString(this.mAction);
        parcel.writeString(this.mActionButtonText);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mPromoId);
        parcel.writeString(this.mTitle);
    }
}
