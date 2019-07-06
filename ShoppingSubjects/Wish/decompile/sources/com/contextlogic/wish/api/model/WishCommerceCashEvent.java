package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceCashEvent extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashEvent> CREATOR = new Creator<WishCommerceCashEvent>() {
        public WishCommerceCashEvent createFromParcel(Parcel parcel) {
            return new WishCommerceCashEvent(parcel);
        }

        public WishCommerceCashEvent[] newArray(int i) {
            return new WishCommerceCashEvent[i];
        }
    };
    private int mAmount;
    private String mAmountText;
    private String mDateText;
    private String mImageUrl;
    private String mMainText;
    private String mSubText;
    private String mTransactionId;
    private int mType;

    public int describeContents() {
        return 0;
    }

    public String getMainText() {
        return this.mMainText;
    }

    public String getDateText() {
        return this.mDateText;
    }

    public String getSubText() {
        return this.mSubText;
    }

    public String getAmountText() {
        return this.mAmountText;
    }

    public boolean isNegativeAmount() {
        return this.mAmount < 0;
    }

    public int getType() {
        return this.mType;
    }

    public WishImage getImage() {
        if (this.mImageUrl != null) {
            return new WishImage(this.mImageUrl);
        }
        return null;
    }

    public String getTransactionId() {
        return this.mTransactionId;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMainText);
        parcel.writeString(this.mDateText);
        parcel.writeString(this.mSubText);
        parcel.writeString(this.mAmountText);
        parcel.writeInt(this.mAmount);
        parcel.writeInt(this.mType);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mTransactionId);
    }

    WishCommerceCashEvent(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mMainText = jSONObject.getString("main_text");
        this.mDateText = jSONObject.getString("date_text");
        this.mSubText = jSONObject.getString("sub_text");
        this.mAmountText = jSONObject.getString("amount_text");
        this.mAmount = jSONObject.getInt("amount");
        this.mType = jSONObject.getInt("type");
        this.mImageUrl = JsonUtil.optString(jSONObject, "image_url");
        this.mTransactionId = JsonUtil.optString(jSONObject, "transaction_id");
    }

    private WishCommerceCashEvent(Parcel parcel) {
        this.mMainText = parcel.readString();
        this.mDateText = parcel.readString();
        this.mSubText = parcel.readString();
        this.mAmountText = parcel.readString();
        this.mAmount = parcel.readInt();
        this.mType = parcel.readInt();
        this.mImageUrl = parcel.readString();
        this.mTransactionId = parcel.readString();
    }
}
