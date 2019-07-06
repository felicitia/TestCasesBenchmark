package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCreditCardInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCreditCardInfo> CREATOR = new Creator<WishCreditCardInfo>() {
        public WishCreditCardInfo createFromParcel(Parcel parcel) {
            return new WishCreditCardInfo(parcel);
        }

        public WishCreditCardInfo[] newArray(int i) {
            return new WishCreditCardInfo[i];
        }
    };
    private WishShippingInfo mBillingAddress;
    private CardType mCardType;
    private int mExpiryMonth;
    private int mExpiryYear;
    private String mId;
    private String mLastFourDigits;
    private PaymentProcessor mPaymentProcessor;
    private String mToken;

    public int describeContents() {
        return 0;
    }

    public WishCreditCardInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mId = jSONObject.getString("id");
        this.mCardType = CreditCardUtil.getCardTypeFromString(jSONObject.getString("card_type").toLowerCase());
        this.mToken = jSONObject.optString("token");
        this.mLastFourDigits = jSONObject.optString("last_4_digits");
        this.mExpiryMonth = jSONObject.optInt("expiry_month");
        this.mExpiryYear = jSONObject.optInt("expiry_year");
        if (this.mExpiryYear > 2000) {
            this.mExpiryYear -= 2000;
        }
        this.mPaymentProcessor = PaymentProcessor.getCreditCardProcessor(jSONObject.optInt("payment_type"));
        if (JsonUtil.hasValue(jSONObject, "billing_address")) {
            try {
                this.mBillingAddress = new WishShippingInfo(jSONObject.getJSONObject("billing_address"));
                if (this.mBillingAddress.getStreetAddressLineOne() == null || this.mBillingAddress.getStreetAddressLineOne().trim().equals("")) {
                    this.mBillingAddress = null;
                }
            } catch (Throwable unused) {
            }
        }
    }

    protected WishCreditCardInfo(Parcel parcel) {
        this.mId = parcel.readString();
        this.mExpiryMonth = parcel.readInt();
        this.mExpiryYear = parcel.readInt();
        this.mToken = parcel.readString();
        this.mLastFourDigits = parcel.readString();
        this.mCardType = (CardType) parcel.readParcelable(CardType.class.getClassLoader());
        this.mPaymentProcessor = (PaymentProcessor) parcel.readParcelable(PaymentProcessor.class.getClassLoader());
        this.mBillingAddress = (WishShippingInfo) parcel.readParcelable(WishShippingInfo.class.getClassLoader());
    }

    public String getId() {
        return this.mId;
    }

    public CardType getCardType() {
        return this.mCardType;
    }

    public WishShippingInfo getBillingAddress() {
        return this.mBillingAddress;
    }

    public String getToken() {
        return this.mToken;
    }

    public String getLastFourDigits() {
        return this.mLastFourDigits;
    }

    public PaymentProcessor getPaymentProcessor() {
        return this.mPaymentProcessor;
    }

    public int getExpiryMonth() {
        return this.mExpiryMonth;
    }

    public int getExpiryYear() {
        return this.mExpiryYear;
    }

    public boolean hasExpiryMonth() {
        return this.mExpiryMonth != 0;
    }

    public boolean hasExpiryYear() {
        return this.mExpiryYear != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeInt(this.mExpiryMonth);
        parcel.writeInt(this.mExpiryYear);
        parcel.writeString(this.mToken);
        parcel.writeString(this.mLastFourDigits);
        parcel.writeParcelable(this.mCardType, 0);
        parcel.writeParcelable(this.mPaymentProcessor, 0);
        parcel.writeParcelable(this.mBillingAddress, 0);
    }
}
