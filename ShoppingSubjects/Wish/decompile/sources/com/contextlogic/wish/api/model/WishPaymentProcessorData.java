package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPaymentProcessorData extends BaseModel implements Parcelable {
    public static final Creator<WishPaymentProcessorData> CREATOR = new Creator<WishPaymentProcessorData>() {
        public WishPaymentProcessorData createFromParcel(Parcel parcel) {
            return new WishPaymentProcessorData(parcel);
        }

        public WishPaymentProcessorData[] newArray(int i) {
            return new WishPaymentProcessorData[i];
        }
    };
    private String mAdyenKey;
    private String mBraintreeCollectorMerchantId;
    private String mBraintreeCollectorUrl;
    private String mBraintreeKey;
    private String mEbanxApiUrl;
    private String mEbanxKey;
    private String mPaypalKey;
    private String mStripeKey;
    private boolean mUseTestMode;

    public int describeContents() {
        return 0;
    }

    public WishPaymentProcessorData(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mPaypalKey = jSONObject.getString("paypal_key");
        this.mStripeKey = jSONObject.getString("stripe_key");
        this.mBraintreeCollectorUrl = jSONObject.getString("braintree_collector_url");
        this.mBraintreeCollectorMerchantId = jSONObject.getString("braintree_collector_merchant_id");
        this.mBraintreeKey = jSONObject.getString("braintree_key");
        this.mEbanxKey = jSONObject.getString("ebanx_key");
        this.mEbanxApiUrl = jSONObject.getString("ebanx_api_url");
        this.mAdyenKey = jSONObject.getString("adyen_key");
        this.mUseTestMode = jSONObject.getBoolean("use_test_mode");
    }

    protected WishPaymentProcessorData(Parcel parcel) {
        this.mUseTestMode = parcel.readByte() != 0;
        this.mAdyenKey = parcel.readString();
        this.mBraintreeCollectorMerchantId = parcel.readString();
        this.mBraintreeCollectorUrl = parcel.readString();
        this.mBraintreeKey = parcel.readString();
        this.mEbanxApiUrl = parcel.readString();
        this.mEbanxKey = parcel.readString();
        this.mPaypalKey = parcel.readString();
        this.mStripeKey = parcel.readString();
    }

    public String getStripeKey() {
        return this.mStripeKey;
    }

    public String getBraintreeKey() {
        return this.mBraintreeKey;
    }

    public String getAdyenKey() {
        return this.mAdyenKey;
    }

    public String getEbanxKey() {
        return this.mEbanxKey;
    }

    public String getEbanxApiUrl() {
        return this.mEbanxApiUrl;
    }

    public JSONObject getJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("paypal_key", this.mPaypalKey);
            jSONObject.put("stripe_key", this.mStripeKey);
            jSONObject.put("braintree_collector_url", this.mBraintreeCollectorUrl);
            jSONObject.put("braintree_collector_merchant_id", this.mBraintreeCollectorMerchantId);
            jSONObject.put("braintree_key", this.mBraintreeKey);
            jSONObject.put("ebanx_key", this.mEbanxKey);
            jSONObject.put("ebanx_api_url", this.mEbanxApiUrl);
            jSONObject.put("adyen_key", this.mAdyenKey);
            jSONObject.put("use_test_mode", this.mUseTestMode);
            return jSONObject;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mUseTestMode ? (byte) 1 : 0);
        parcel.writeString(this.mAdyenKey);
        parcel.writeString(this.mBraintreeCollectorMerchantId);
        parcel.writeString(this.mBraintreeCollectorUrl);
        parcel.writeString(this.mBraintreeKey);
        parcel.writeString(this.mEbanxApiUrl);
        parcel.writeString(this.mEbanxKey);
        parcel.writeString(this.mPaypalKey);
        parcel.writeString(this.mStripeKey);
    }
}
