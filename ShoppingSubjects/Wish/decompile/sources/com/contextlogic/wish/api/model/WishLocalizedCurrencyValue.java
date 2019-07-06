package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.service.standalone.LogErrorService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class WishLocalizedCurrencyValue extends BaseModel implements Parcelable {
    public static final Creator<WishLocalizedCurrencyValue> CREATOR = new Creator<WishLocalizedCurrencyValue>() {
        public WishLocalizedCurrencyValue createFromParcel(Parcel parcel) {
            return new WishLocalizedCurrencyValue(parcel);
        }

        public WishLocalizedCurrencyValue[] newArray(int i) {
            return new WishLocalizedCurrencyValue[i];
        }
    };
    private String mLocalizedCurrencyCode;
    private double mLocalizedValue;
    private double mUsdValue;

    public int describeContents() {
        return 0;
    }

    public WishLocalizedCurrencyValue(double d) {
        this(d, Double.MIN_VALUE, null);
    }

    public WishLocalizedCurrencyValue(double d, double d2, String str) {
        this.mUsdValue = d;
        this.mLocalizedValue = d2;
        this.mLocalizedCurrencyCode = str;
    }

    public WishLocalizedCurrencyValue(double d, JSONObject jSONObject) throws JSONException {
        try {
            this.mUsdValue = d;
            this.mLocalizedValue = Double.MIN_VALUE;
            this.mLocalizedCurrencyCode = null;
            boolean canLocalizeCurrency = ExperimentDataCenter.getInstance().canLocalizeCurrency();
            if (jSONObject != null) {
                this.mLocalizedValue = jSONObject.optDouble("localized_value", Double.MIN_VALUE);
                if (JsonUtil.hasValue(jSONObject, "currency_code")) {
                    this.mLocalizedCurrencyCode = jSONObject.getString("currency_code");
                }
                if (this.mLocalizedValue == Double.MIN_VALUE) {
                    this.mLocalizedCurrencyCode = null;
                } else if (this.mLocalizedCurrencyCode != null && this.mLocalizedCurrencyCode.toLowerCase().equals("usd")) {
                    this.mUsdValue = this.mLocalizedValue;
                    this.mLocalizedValue = Double.MIN_VALUE;
                    this.mLocalizedCurrencyCode = null;
                } else if (!canLocalizeCurrency) {
                    this.mLocalizedValue = Double.MIN_VALUE;
                    this.mLocalizedCurrencyCode = null;
                }
            }
        } catch (JSONException e) {
            LogErrorService.logModelParseError(this, e);
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) {
        this.mUsdValue = jSONObject.optDouble("total_usd");
        this.mLocalizedValue = JsonUtil.hasValue(jSONObject, "total_localized") ? jSONObject.optDouble("total_localized") : Double.MIN_VALUE;
        this.mLocalizedCurrencyCode = JsonUtil.hasValue(jSONObject, "localized_currency_code") ? jSONObject.optString("localized_currency_code") : null;
    }

    protected WishLocalizedCurrencyValue(Parcel parcel) {
        this.mLocalizedValue = parcel.readDouble();
        this.mUsdValue = parcel.readDouble();
        this.mLocalizedCurrencyCode = parcel.readString();
    }

    public WishLocalizedCurrencyValue add(WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        if (isLocalizedManipulationAllowed(wishLocalizedCurrencyValue)) {
            WishLocalizedCurrencyValue wishLocalizedCurrencyValue2 = new WishLocalizedCurrencyValue(this.mUsdValue + wishLocalizedCurrencyValue.getUsdValue(), this.mLocalizedValue + wishLocalizedCurrencyValue.getLocalizedValue(), this.mLocalizedCurrencyCode);
            return wishLocalizedCurrencyValue2;
        }
        WishLocalizedCurrencyValue wishLocalizedCurrencyValue3 = new WishLocalizedCurrencyValue(this.mUsdValue + wishLocalizedCurrencyValue.getUsdValue(), Double.MIN_VALUE, null);
        return wishLocalizedCurrencyValue3;
    }

    public WishLocalizedCurrencyValue subtract(WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        if (isLocalizedManipulationAllowed(wishLocalizedCurrencyValue)) {
            WishLocalizedCurrencyValue wishLocalizedCurrencyValue2 = new WishLocalizedCurrencyValue(this.mUsdValue - wishLocalizedCurrencyValue.getUsdValue(), this.mLocalizedValue - wishLocalizedCurrencyValue.getLocalizedValue(), this.mLocalizedCurrencyCode);
            return wishLocalizedCurrencyValue2;
        }
        WishLocalizedCurrencyValue wishLocalizedCurrencyValue3 = new WishLocalizedCurrencyValue(this.mUsdValue - wishLocalizedCurrencyValue.getUsdValue(), Double.MIN_VALUE, null);
        return wishLocalizedCurrencyValue3;
    }

    public double divide(WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        if (isLocalizedManipulationAllowed(wishLocalizedCurrencyValue)) {
            return this.mLocalizedValue / wishLocalizedCurrencyValue.getLocalizedValue();
        }
        return this.mUsdValue / wishLocalizedCurrencyValue.getUsdValue();
    }

    public WishLocalizedCurrencyValue multiply(double d) {
        WishLocalizedCurrencyValue wishLocalizedCurrencyValue = new WishLocalizedCurrencyValue(this.mUsdValue * d, this.mLocalizedValue * d, this.mLocalizedCurrencyCode);
        return wishLocalizedCurrencyValue;
    }

    private boolean isLocalizedManipulationAllowed(WishLocalizedCurrencyValue wishLocalizedCurrencyValue) {
        return isLocalized() && wishLocalizedCurrencyValue.isLocalized() && getLocalizedCurrencyCode().equals(wishLocalizedCurrencyValue.getLocalizedCurrencyCode());
    }

    public String toFormattedString() {
        return toFormattedString(false, true, false);
    }

    public String toFormattedString(boolean z, boolean z2) {
        return toFormattedString(z, z2, false);
    }

    public String toAbsFormattedString() {
        return toFormattedString(false, true, true);
    }

    public String toFormattedString(boolean z, boolean z2, boolean z3) {
        if (ExperimentDataCenter.getInstance().canLocalizeCurrency()) {
            Currency instance = Currency.getInstance((z || !isLocalized()) ? "USD" : this.mLocalizedCurrencyCode);
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.getDefault());
            currencyInstance.setCurrency(instance);
            currencyInstance.setMaximumFractionDigits(instance.getDefaultFractionDigits());
            double d = (z || !isLocalized()) ? this.mUsdValue : this.mLocalizedValue;
            if (z3) {
                d = Math.abs(d);
            }
            if (z2 && d == ((double) ((int) d))) {
                currencyInstance.setMaximumFractionDigits(0);
            }
            return currencyInstance.format(d);
        } else if (!z2 || this.mUsdValue != ((double) ((int) this.mUsdValue))) {
            return String.format("$%.2f", new Object[]{Double.valueOf(z3 ? Math.abs(this.mUsdValue) : this.mUsdValue)});
        } else {
            return String.format("$%.0f", new Object[]{Double.valueOf(z3 ? Math.abs(this.mUsdValue) : this.mUsdValue)});
        }
    }

    public double getValue() {
        return isLocalized() ? this.mLocalizedValue : this.mUsdValue;
    }

    public boolean isLocalized() {
        return this.mLocalizedCurrencyCode != null;
    }

    public String getLocalizedCurrencyCode() {
        return isLocalized() ? this.mLocalizedCurrencyCode : "USD";
    }

    public double getLocalizedValue() {
        return this.mLocalizedValue;
    }

    public double getUsdValue() {
        return this.mUsdValue;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLocalizedValue);
        parcel.writeDouble(this.mUsdValue);
        parcel.writeString(this.mLocalizedCurrencyCode);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishLocalizedCurrencyValue wishLocalizedCurrencyValue = (WishLocalizedCurrencyValue) obj;
        if (Double.compare(wishLocalizedCurrencyValue.mLocalizedValue, this.mLocalizedValue) != 0 || Double.compare(wishLocalizedCurrencyValue.mUsdValue, this.mUsdValue) != 0) {
            return false;
        }
        if (this.mLocalizedCurrencyCode != null) {
            z = this.mLocalizedCurrencyCode.equals(wishLocalizedCurrencyValue.mLocalizedCurrencyCode);
        } else if (wishLocalizedCurrencyValue.mLocalizedCurrencyCode != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.mLocalizedValue);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.mUsdValue);
        return (((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + (this.mLocalizedCurrencyCode != null ? this.mLocalizedCurrencyCode.hashCode() : 0);
    }
}
