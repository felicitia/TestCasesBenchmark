package com.contextlogic.wish.api.model;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.ColorUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCartSummaryItem extends BaseModel implements Parcelable {
    public static final Creator<WishCartSummaryItem> CREATOR = new Creator<WishCartSummaryItem>() {
        public WishCartSummaryItem createFromParcel(Parcel parcel) {
            return new WishCartSummaryItem(parcel);
        }

        public WishCartSummaryItem[] newArray(int i) {
            return new WishCartSummaryItem[i];
        }
    };
    private WishLocalizedCurrencyValue mApproxValue;
    private WishLocalizedCurrencyValue mCurrencyValue;
    private int mDisplayColor;
    private String mName;
    private boolean mShowIcon;
    private int mType;

    public int describeContents() {
        return 0;
    }

    public WishCartSummaryItem(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mType = jSONObject.getInt("type");
        this.mName = jSONObject.getString("name");
        this.mShowIcon = jSONObject.optBoolean("icon", false);
        this.mDisplayColor = parseColor(jSONObject.optString("color"));
        this.mCurrencyValue = new WishLocalizedCurrencyValue(jSONObject.optDouble("value"), jSONObject.getJSONObject("localized_value"));
        this.mApproxValue = new WishLocalizedCurrencyValue(jSONObject.optDouble("value"), jSONObject.optJSONObject("approx_value"));
    }

    protected WishCartSummaryItem(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mName = parcel.readString();
        this.mApproxValue = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mCurrencyValue = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
    }

    public String getName() {
        return this.mName;
    }

    public int getType() {
        return this.mType;
    }

    public String getValue() {
        if (this.mCurrencyValue.getValue() <= 0.0d) {
            return WishApplication.getInstance().getString(R.string.free);
        }
        String formattedString = this.mCurrencyValue.toFormattedString(ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency(), false);
        if (this.mType == 2) {
            formattedString = String.format(WishApplication.getInstance().getString(R.string.cart_discount_price), new Object[]{formattedString});
        }
        return formattedString;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeString(this.mName);
        parcel.writeParcelable(this.mApproxValue, 0);
        parcel.writeParcelable(this.mCurrencyValue, 0);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WishCartSummaryItem)) {
            return false;
        }
        WishCartSummaryItem wishCartSummaryItem = (WishCartSummaryItem) obj;
        if (this.mType != wishCartSummaryItem.mType || !this.mName.equals(wishCartSummaryItem.mName) || !this.mApproxValue.equals(wishCartSummaryItem.mApproxValue) || !this.mCurrencyValue.equals(wishCartSummaryItem.mCurrencyValue)) {
            z = false;
        }
        return z;
    }

    private int parseColor(String str) {
        return ColorUtil.safeParseColor(str, WishApplication.getInstance().getResources().getColor(R.color.gray1));
    }

    public int getDisplayColor() {
        return this.mDisplayColor;
    }

    public boolean shouldShowIcon() {
        return this.mShowIcon;
    }

    public Drawable getIcon() {
        if (this.mType == 2) {
            return WishApplication.getInstance().getResources().getDrawable(R.drawable.salestag_14);
        }
        return null;
    }
}
