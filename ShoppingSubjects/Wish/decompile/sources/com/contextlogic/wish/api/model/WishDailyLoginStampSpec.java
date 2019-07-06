package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishDailyLoginStampSpec extends BaseModel implements Parcelable {
    public static final Creator<WishDailyLoginStampSpec> CREATOR = new Creator<WishDailyLoginStampSpec>() {
        public WishDailyLoginStampSpec createFromParcel(Parcel parcel) {
            return new WishDailyLoginStampSpec(parcel);
        }

        public WishDailyLoginStampSpec[] newArray(int i) {
            return new WishDailyLoginStampSpec[i];
        }
    };
    private static int DEFAULT_STAMP_DELAY = 2000;
    private WishCouponDialogSpec mCouponDialogSpec;
    private String mCouponWonText;
    private String mDescription;
    private String mDiscountText;
    private String mDiscountTitleText;
    private String mExpiryDate;
    private String mFinalStampText;
    private String mFormattedLocalizedMaxDiscount;
    private String mMenuPrompt;
    private boolean mNotShownOnMainFeed;
    private String mRowTitle;
    private boolean mShowStampRow;
    private int mStampFadeDelay;
    private int mStampNumber;
    private String mStampsCompletedText1;
    private String mStampsCompletedText2;
    private String mTitleText;

    public int describeContents() {
        return 0;
    }

    public WishDailyLoginStampSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishDailyLoginStampSpec(Parcel parcel) {
        this.mDiscountTitleText = parcel.readString();
        this.mDiscountText = parcel.readString();
        this.mStampNumber = parcel.readInt();
        this.mStampFadeDelay = parcel.readInt();
        this.mExpiryDate = parcel.readString();
        this.mTitleText = parcel.readString();
        this.mCouponWonText = parcel.readString();
        this.mStampsCompletedText1 = parcel.readString();
        this.mStampsCompletedText2 = parcel.readString();
        this.mRowTitle = parcel.readString();
        this.mFinalStampText = parcel.readString();
        this.mDescription = parcel.readString();
        boolean z = false;
        this.mShowStampRow = parcel.readByte() != 0;
        this.mCouponDialogSpec = (WishCouponDialogSpec) parcel.readParcelable(WishCouponDialogSpec.class.getClassLoader());
        this.mFormattedLocalizedMaxDiscount = parcel.readString();
        this.mMenuPrompt = parcel.readString();
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mNotShownOnMainFeed = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDiscountTitleText);
        parcel.writeString(this.mDiscountText);
        parcel.writeInt(this.mStampNumber);
        parcel.writeInt(this.mStampFadeDelay);
        parcel.writeString(this.mExpiryDate);
        parcel.writeString(this.mTitleText);
        parcel.writeString(this.mCouponWonText);
        parcel.writeString(this.mStampsCompletedText1);
        parcel.writeString(this.mStampsCompletedText2);
        parcel.writeString(this.mRowTitle);
        parcel.writeString(this.mFinalStampText);
        parcel.writeString(this.mDescription);
        parcel.writeByte(this.mShowStampRow ? (byte) 1 : 0);
        parcel.writeParcelable(this.mCouponDialogSpec, i);
        parcel.writeString(this.mFormattedLocalizedMaxDiscount);
        parcel.writeString(this.mMenuPrompt);
        parcel.writeByte(this.mNotShownOnMainFeed ? (byte) 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mDiscountTitleText = jSONObject.optString("discount_title_text");
        this.mStampNumber = jSONObject.optInt("stamp_number");
        this.mExpiryDate = jSONObject.optString("expiry_date");
        this.mDiscountText = jSONObject.optString("discount_text");
        this.mTitleText = jSONObject.optString("title_text");
        this.mCouponWonText = jSONObject.optString("coupon_won_text");
        this.mRowTitle = jSONObject.optString("row_title");
        this.mCouponDialogSpec = new WishCouponDialogSpec(jSONObject);
        this.mStampsCompletedText1 = jSONObject.optString("stamps_completed_text_1");
        this.mStampsCompletedText2 = jSONObject.optString("stamps_completed_text_2");
        this.mFinalStampText = jSONObject.optString("final_stamp_text");
        this.mDescription = jSONObject.optString("description_text");
        this.mMenuPrompt = jSONObject.optString("menu_prompt");
        this.mShowStampRow = jSONObject.optBoolean("show_stamp_row");
        this.mNotShownOnMainFeed = jSONObject.optBoolean("not_shown_on_main_feed", false);
        this.mStampFadeDelay = jSONObject.optInt("stamp_fade_delay", DEFAULT_STAMP_DELAY);
    }

    public int getStampNumber() {
        return this.mStampNumber;
    }

    public int getStampFadeDelay() {
        return this.mStampFadeDelay;
    }

    public String getDiscountTitleText() {
        return this.mDiscountTitleText;
    }

    public String getDiscountText() {
        return this.mDiscountText;
    }

    public String getRowTitle() {
        return this.mRowTitle;
    }

    public String getExpiryDate() {
        return this.mExpiryDate;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    public boolean getCouponWon() {
        return !TextUtils.isEmpty(getCouponDialogSpec().getCouponCode()) && !TextUtils.isEmpty(getCouponDialogSpec().getCouponExpiryDate()) && !TextUtils.isEmpty(getCouponDialogSpec().getCouponAmount());
    }

    public String getCouponWonText() {
        return this.mCouponWonText;
    }

    public String getFinalStampText() {
        return this.mFinalStampText;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getMenuPrompt() {
        return this.mMenuPrompt;
    }

    public boolean showStampRow() {
        return this.mShowStampRow;
    }

    public WishCouponDialogSpec getCouponDialogSpec() {
        return this.mCouponDialogSpec;
    }

    public boolean notShownOnMainFeed() {
        return this.mNotShownOnMainFeed;
    }
}
