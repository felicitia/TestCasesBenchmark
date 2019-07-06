package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPriceWatchSpec extends BaseModel {
    public static final Creator<WishPriceWatchSpec> CREATOR = new Creator<WishPriceWatchSpec>() {
        public WishPriceWatchSpec createFromParcel(Parcel parcel) {
            return new WishPriceWatchSpec(parcel);
        }

        public WishPriceWatchSpec[] newArray(int i) {
            return new WishPriceWatchSpec[i];
        }
    };
    private String mDescription;
    private List<PriceWatchItem> mItems;
    private String mTitle;

    public static class PriceWatchItem extends BaseModel {
        public static final Creator<PriceWatchItem> CREATOR = new Creator<PriceWatchItem>() {
            public PriceWatchItem createFromParcel(Parcel parcel) {
                return new PriceWatchItem(parcel);
            }

            public PriceWatchItem[] newArray(int i) {
                return new PriceWatchItem[i];
            }
        };
        private WishLocalizedCurrencyValue mCurrentPrice;
        private WishImage mImage;
        private Date mLastUpdated;
        private WishLocalizedCurrencyValue mPreviousPrice;
        private String mProductId;
        private String mTitle;

        public int describeContents() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (jSONObject != null) {
                this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
                this.mProductId = JsonUtil.optString(jSONObject, "contest_id");
                if (JsonUtil.hasValue(jSONObject, "last_updated")) {
                    this.mLastUpdated = DateUtil.parseIsoDate(jSONObject.getString("last_updated"));
                }
                this.mCurrentPrice = new WishLocalizedCurrencyValue(jSONObject.optDouble("current_price"), jSONObject.optJSONObject("localized_current_price"));
                this.mPreviousPrice = new WishLocalizedCurrencyValue(jSONObject.optDouble("previous_price"), jSONObject.optJSONObject("localized_previous_price"));
                String optString = JsonUtil.optString(jSONObject, "image_url");
                this.mImage = optString != null ? new WishImage(optString) : null;
            }
        }

        private PriceWatchItem(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        public String getProductId() {
            return this.mProductId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getFormattedTimestamp() {
            return DateUtil.getFuzzyDateFromNow(this.mLastUpdated);
        }

        public WishLocalizedCurrencyValue getCurrentPrice() {
            return this.mCurrentPrice;
        }

        public WishLocalizedCurrencyValue getPreviousPrice() {
            return this.mPreviousPrice;
        }

        public double getPriceDifference() {
            return this.mCurrentPrice.getValue() - this.mPreviousPrice.getValue();
        }

        public double getUsdPriceDifference() {
            return this.mCurrentPrice.getUsdValue() - this.mPreviousPrice.getUsdValue();
        }

        public String getFormattedPriceDiff() {
            String str;
            double d;
            if (!getCurrentPrice().isLocalized() || !getPreviousPrice().isLocalized() || !getCurrentPrice().getLocalizedCurrencyCode().equals(getPreviousPrice().getLocalizedCurrencyCode())) {
                str = "USD";
            } else {
                str = getCurrentPrice().getLocalizedCurrencyCode();
            }
            Currency instance = Currency.getInstance(str);
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(Locale.getDefault());
            currencyInstance.setCurrency(instance);
            currencyInstance.setMaximumFractionDigits(instance.getDefaultFractionDigits());
            if (!getCurrentPrice().isLocalized() || !getPreviousPrice().isLocalized()) {
                d = Math.abs(getUsdPriceDifference());
            } else {
                d = Math.abs(getPriceDifference());
            }
            if (d == ((double) ((int) d))) {
                currencyInstance.setMaximumFractionDigits(0);
            }
            return currencyInstance.format(d);
        }

        public WishImage getImage() {
            return this.mImage;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitle);
            parcel.writeParcelable(this.mPreviousPrice, i);
            parcel.writeParcelable(this.mCurrentPrice, i);
            parcel.writeLong(this.mLastUpdated != null ? this.mLastUpdated.getTime() : -1);
            parcel.writeParcelable(this.mImage, i);
            parcel.writeString(this.mProductId);
        }

        protected PriceWatchItem(Parcel parcel) {
            Date date;
            this.mTitle = parcel.readString();
            this.mPreviousPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            this.mCurrentPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
            long readLong = parcel.readLong();
            if (readLong == -1) {
                date = null;
            } else {
                date = new Date(readLong);
            }
            this.mLastUpdated = date;
            this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
            this.mProductId = parcel.readString();
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishPriceWatchSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mDescription = JsonUtil.optString(jSONObject, "description");
        if (JsonUtil.hasValue(jSONObject, "price_watch_items")) {
            this.mItems = JsonUtil.parseArray(jSONObject, "price_watch_items", new DataParser<PriceWatchItem, JSONObject>() {
                public PriceWatchItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new PriceWatchItem(jSONObject);
                }
            });
        }
        this.mTitle = JsonUtil.optString(jSONObject, "title_text");
    }

    public String getDescription() {
        return this.mDescription;
    }

    public List<PriceWatchItem> getItems() {
        return this.mItems;
    }

    public String getTitleText() {
        return this.mTitle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDescription);
        parcel.writeTypedList(this.mItems);
        parcel.writeString(this.mTitle);
    }

    protected WishPriceWatchSpec(Parcel parcel) {
        this.mDescription = parcel.readString();
        this.mItems = parcel.createTypedArrayList(PriceWatchItem.CREATOR);
        this.mTitle = parcel.readString();
    }
}
