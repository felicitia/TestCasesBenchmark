package com.contextlogic.wish.api.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NullableNonNullAnnotationRequired"})
public class PriceChopStatuses extends BaseModel {
    public static final Creator<PriceChopStatuses> CREATOR = new Creator<PriceChopStatuses>() {
        public PriceChopStatuses createFromParcel(Parcel parcel) {
            return new PriceChopStatuses(parcel);
        }

        public PriceChopStatuses[] newArray(int i) {
            return new PriceChopStatuses[i];
        }
    };
    private WishLocalizedCurrencyValue mOriginalPrice;
    private List<PriceChopStatus> mPriceChopStatuses;

    public int describeContents() {
        return 0;
    }

    public PriceChopStatuses(JSONObject jSONObject) throws JSONException, ParseException {
        parseJson(jSONObject);
    }

    public WishLocalizedCurrencyValue getOriginalPrice() {
        return this.mOriginalPrice;
    }

    public List<PriceChopStatus> getPriceChopStatuses() {
        return this.mPriceChopStatuses;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mOriginalPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("original_price"), jSONObject.getJSONObject("original_price_localized"));
        this.mPriceChopStatuses = JsonUtil.parseArray(jSONObject, "status", new DataParser<PriceChopStatus, JSONObject>() {
            public PriceChopStatus parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new PriceChopStatus(jSONObject);
            }
        });
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mOriginalPrice, i);
        parcel.writeTypedList(this.mPriceChopStatuses);
    }

    protected PriceChopStatuses(Parcel parcel) {
        this.mOriginalPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mPriceChopStatuses = parcel.createTypedArrayList(PriceChopStatus.CREATOR);
    }
}
