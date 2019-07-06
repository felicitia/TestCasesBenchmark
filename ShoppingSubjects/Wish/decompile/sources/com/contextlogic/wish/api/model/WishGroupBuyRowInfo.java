package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishGroupBuyRowInfo extends BaseModel implements Parcelable {
    public static final Creator<WishGroupBuyRowInfo> CREATOR = new Creator<WishGroupBuyRowInfo>() {
        public WishGroupBuyRowInfo createFromParcel(Parcel parcel) {
            return new WishGroupBuyRowInfo(parcel);
        }

        public WishGroupBuyRowInfo[] newArray(int i) {
            return new WishGroupBuyRowInfo[i];
        }
    };
    private String mButtonText;
    private Date mExpiry;
    private String mGroupBuyId;
    private String mMessage;
    private String mTitle;
    private WishImage mUserImage;
    private String mUserName;

    public int describeContents() {
        return 0;
    }

    public WishGroupBuyRowInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public WishGroupBuyRowInfo(Date date, WishImage wishImage, String str, String str2, String str3, String str4, String str5) {
        this.mExpiry = date;
        this.mUserImage = wishImage;
        this.mTitle = str2;
        this.mUserName = str;
        this.mMessage = str3;
        this.mButtonText = str4;
        this.mGroupBuyId = str5;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        String optString = JsonUtil.optString(jSONObject, "user_image");
        if (optString != null) {
            this.mUserImage = new WishImage(optString);
        }
        this.mUserName = jSONObject.getString("user_name");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = JsonUtil.optString(jSONObject, "message");
        this.mButtonText = jSONObject.getString("button_text");
        this.mGroupBuyId = jSONObject.getString("group_buy_id");
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeByte((byte) (this.mExpiry != null ? 1 : 0));
        if (this.mExpiry != null) {
            parcel.writeLong(this.mExpiry.getTime());
        }
        if (this.mUserImage == null) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.mUserImage != null) {
            parcel.writeParcelable(this.mUserImage, 0);
        }
        parcel.writeString(this.mUserName);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mButtonText);
        parcel.writeString(this.mGroupBuyId);
    }

    protected WishGroupBuyRowInfo(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        if (parcel.readByte() != 0) {
            this.mUserImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        }
        this.mUserName = parcel.readString();
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
        this.mButtonText = parcel.readString();
        this.mGroupBuyId = parcel.readString();
    }

    public Date getExpiry() {
        return this.mExpiry;
    }

    public WishImage getUserImage() {
        return this.mUserImage;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public String getGroupBuyId() {
        return this.mGroupBuyId;
    }
}
