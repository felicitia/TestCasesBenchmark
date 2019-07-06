package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPartnerInfoSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPartnerInfoSpec> CREATOR = new Creator<WishPartnerInfoSpec>() {
        public WishPartnerInfoSpec createFromParcel(Parcel parcel) {
            return new WishPartnerInfoSpec(parcel);
        }

        public WishPartnerInfoSpec[] newArray(int i) {
            return new WishPartnerInfoSpec[i];
        }
    };
    private String mCouponCode;
    private String mDashboardLinkText;
    private String mInstructionsHeader;
    private String mMenuItemLable;
    private String mShareMessage;
    private String mStepOneText;
    private String mStepThreeText;
    private String mStepTwoText;
    private String mSubtitle;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishPartnerInfoSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mCouponCode = JsonUtil.optString(jSONObject, "coupon_code");
        this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
        this.mSubtitle = JsonUtil.optString(jSONObject, "subtitle");
        this.mInstructionsHeader = JsonUtil.optString(jSONObject, "instructions_header");
        this.mStepOneText = JsonUtil.optString(jSONObject, "step_1_text");
        this.mStepTwoText = JsonUtil.optString(jSONObject, "step_2_text");
        this.mStepThreeText = JsonUtil.optString(jSONObject, "step_3_text");
        this.mShareMessage = JsonUtil.optString(jSONObject, "share_text");
        this.mMenuItemLable = JsonUtil.optString(jSONObject, "menu_item_label");
        this.mDashboardLinkText = JsonUtil.optString(jSONObject, "dashboard_link_text");
    }

    public String getDashboardLinkText() {
        return this.mDashboardLinkText;
    }

    public String getMenuItemLable() {
        return this.mMenuItemLable;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getInstructionsHeader() {
        return this.mInstructionsHeader;
    }

    public String getStepOneText() {
        return this.mStepOneText;
    }

    public String getStepTwoText() {
        return this.mStepTwoText;
    }

    public String getStepThreeText() {
        return this.mStepThreeText;
    }

    public String getShareMessage() {
        return this.mShareMessage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCouponCode);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mInstructionsHeader);
        parcel.writeString(this.mStepOneText);
        parcel.writeString(this.mStepTwoText);
        parcel.writeString(this.mStepThreeText);
        parcel.writeString(this.mShareMessage);
        parcel.writeString(this.mMenuItemLable);
        parcel.writeString(this.mDashboardLinkText);
    }

    protected WishPartnerInfoSpec(Parcel parcel) {
        this.mCouponCode = parcel.readString();
        this.mTitle = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mInstructionsHeader = parcel.readString();
        this.mStepOneText = parcel.readString();
        this.mStepTwoText = parcel.readString();
        this.mStepThreeText = parcel.readString();
        this.mShareMessage = parcel.readString();
        this.mMenuItemLable = parcel.readString();
        this.mDashboardLinkText = parcel.readString();
    }
}
