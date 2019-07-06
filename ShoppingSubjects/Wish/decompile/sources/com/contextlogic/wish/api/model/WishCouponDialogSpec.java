package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCouponDialogSpec extends BaseModel {
    public static final Creator<WishCouponDialogSpec> CREATOR = new Creator<WishCouponDialogSpec>() {
        public WishCouponDialogSpec createFromParcel(Parcel parcel) {
            return new WishCouponDialogSpec(parcel);
        }

        public WishCouponDialogSpec[] newArray(int i) {
            return new WishCouponDialogSpec[i];
        }
    };
    private String mCouponAmount;
    private String mCouponCode;
    private String mCouponDesc1;
    private String mCouponDesc2;
    private String mCouponExpiryDate;
    private String mCouponTitleText;
    private String mFormattedLocalizedMaxDiscount;

    public int describeContents() {
        return 0;
    }

    public WishCouponDialogSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishCouponDialogSpec(Parcel parcel) {
        this.mCouponCode = parcel.readString();
        this.mCouponAmount = parcel.readString();
        this.mCouponExpiryDate = parcel.readString();
        this.mCouponTitleText = parcel.readString();
        this.mCouponDesc1 = parcel.readString();
        this.mCouponDesc2 = parcel.readString();
        this.mFormattedLocalizedMaxDiscount = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCouponCode);
        parcel.writeString(this.mCouponAmount);
        parcel.writeString(this.mCouponExpiryDate);
        parcel.writeString(this.mCouponTitleText);
        parcel.writeString(this.mCouponDesc1);
        parcel.writeString(this.mCouponDesc2);
        parcel.writeString(this.mFormattedLocalizedMaxDiscount);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mCouponCode = jSONObject.optString("coupon_code");
        this.mCouponAmount = jSONObject.optString("coupon_discount");
        this.mCouponExpiryDate = jSONObject.optString("coupon_expiry_date");
        this.mCouponTitleText = jSONObject.optString("coupon_title_text");
        this.mCouponDesc1 = jSONObject.optString("coupon_desc_1");
        this.mCouponDesc2 = jSONObject.optString("coupon_desc_2");
        this.mFormattedLocalizedMaxDiscount = JsonUtil.optString(jSONObject, "formatted_localized_max_discount_amount", null);
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public String getCouponAmount() {
        return this.mCouponAmount;
    }

    public String getCouponExpiryDate() {
        return this.mCouponExpiryDate;
    }

    public String getCouponTitleText() {
        return this.mCouponTitleText;
    }

    public String getCouponDesc1() {
        return this.mCouponDesc1;
    }

    public String getCouponDesc2() {
        return this.mCouponDesc2;
    }

    public String getFormattedLocalizedMaxDiscount() {
        return this.mFormattedLocalizedMaxDiscount;
    }
}
