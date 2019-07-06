package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceCashSpecs extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashSpecs> CREATOR = new Creator<WishCommerceCashSpecs>() {
        public WishCommerceCashSpecs createFromParcel(Parcel parcel) {
            return new WishCommerceCashSpecs(parcel);
        }

        public WishCommerceCashSpecs[] newArray(int i) {
            return new WishCommerceCashSpecs[i];
        }
    };
    private String mBonusDescription;
    private String mBonusTitle;
    private String mMainDescription;
    private ArrayList<PurchaseOption> mPurchaseOptions;

    public static class PurchaseOption implements Parcelable {
        public static final Creator<PurchaseOption> CREATOR = new Creator<PurchaseOption>() {
            public PurchaseOption createFromParcel(Parcel parcel) {
                return new PurchaseOption(parcel);
            }

            public PurchaseOption[] newArray(int i) {
                return new PurchaseOption[i];
            }
        };
        private WishLocalizedCurrencyValue mAmount;
        private WishLocalizedCurrencyValue mBonus;

        public int describeContents() {
            return 0;
        }

        public WishLocalizedCurrencyValue getAmount() {
            return this.mAmount;
        }

        public WishLocalizedCurrencyValue getBonus() {
            return this.mBonus;
        }

        public boolean hasBonus() {
            return ((int) this.mBonus.getValue()) != 0;
        }

        public PurchaseOption(WishLocalizedCurrencyValue wishLocalizedCurrencyValue, WishLocalizedCurrencyValue wishLocalizedCurrencyValue2) {
            this.mAmount = wishLocalizedCurrencyValue;
            this.mBonus = wishLocalizedCurrencyValue2;
        }

        protected PurchaseOption(Parcel parcel) {
            this.mAmount = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            this.mBonus = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mAmount, 0);
            parcel.writeParcelable(this.mBonus, 0);
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getMainDescription() {
        return this.mMainDescription;
    }

    public String getBonusTitle() {
        return this.mBonusTitle;
    }

    public String getBonusDescription() {
        return this.mBonusDescription;
    }

    public boolean hasBonus() {
        return (this.mBonusTitle == null || this.mBonusDescription == null) ? false : true;
    }

    public ArrayList<PurchaseOption> getPurchaseOptions() {
        return this.mPurchaseOptions;
    }

    public WishCommerceCashSpecs(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishCommerceCashSpecs(Parcel parcel) {
        this.mMainDescription = parcel.readString();
        this.mBonusTitle = parcel.readString();
        this.mBonusDescription = parcel.readString();
        this.mPurchaseOptions = parcel.createTypedArrayList(PurchaseOption.CREATOR);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mMainDescription = JsonUtil.optString(jSONObject, "main_description");
        this.mBonusTitle = JsonUtil.optString(jSONObject, "bonus_title");
        this.mBonusDescription = JsonUtil.optString(jSONObject, "bonus_description");
        this.mPurchaseOptions = JsonUtil.parseArray(jSONObject, "purchase_options", new DataParser<PurchaseOption, JSONObject>() {
            public PurchaseOption parseData(JSONObject jSONObject) throws JSONException {
                return new PurchaseOption(new WishLocalizedCurrencyValue(jSONObject.optDouble("amount"), jSONObject.optJSONObject("localized_amount")), new WishLocalizedCurrencyValue(jSONObject.optDouble("bonus"), jSONObject.optJSONObject("localized_bonus")));
            }
        });
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMainDescription);
        parcel.writeString(this.mBonusTitle);
        parcel.writeString(this.mBonusDescription);
        parcel.writeTypedList(this.mPurchaseOptions);
    }
}
