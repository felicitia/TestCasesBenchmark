package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.google.android.gms.common.util.CollectionUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class WishUserBillingInfo extends BaseModel implements Parcelable {
    public static final Creator<WishUserBillingInfo> CREATOR = new Creator<WishUserBillingInfo>() {
        public WishUserBillingInfo createFromParcel(Parcel parcel) {
            return new WishUserBillingInfo(parcel);
        }

        public WishUserBillingInfo[] newArray(int i) {
            return new WishUserBillingInfo[i];
        }
    };
    private ArrayList<WishBoletoInfo> mBoletoBillingInfoArray;
    private ArrayList<WishBraintreePayPalInfo> mBraintreePaypalBillingInfoArray;
    private ArrayList<WishCommerceLoanInfo> mCommerceLoanInfoArray;
    private HashMap<PaymentProcessor, ArrayList<WishCreditCardInfo>> mCreditCardBillingInfo;
    private String mDefaultCardId;
    private ArrayList<WishKlarnaInfo> mKlarnaBillingInfoArray;
    private ArrayList<WishOxxoInfo> mOxxoBillingInfoArray;

    public int describeContents() {
        return 0;
    }

    public WishUserBillingInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mDefaultCardId = jSONObject.optString("default_card_id");
        this.mCreditCardBillingInfo = new HashMap<>();
        this.mBoletoBillingInfoArray = new ArrayList<>();
        this.mBraintreePaypalBillingInfoArray = new ArrayList<>();
        this.mOxxoBillingInfoArray = new ArrayList<>();
        this.mKlarnaBillingInfoArray = new ArrayList<>();
        this.mCommerceLoanInfoArray = new ArrayList<>();
        if (ExperimentDataCenter.getInstance().canCheckoutWithCreditCard(null)) {
            for (PaymentProcessor paymentProcessor : PaymentProcessor.creditCardPaymentProcessors) {
                this.mCreditCardBillingInfo.put(paymentProcessor, JsonUtil.parseArray(jSONObject, Integer.toString(paymentProcessor.getValue()), new DataParser<WishCreditCardInfo, JSONObject>() {
                    public WishCreditCardInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishCreditCardInfo(jSONObject);
                    }
                }));
            }
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithBoleto(null)) {
            this.mBoletoBillingInfoArray = JsonUtil.parseArray(jSONObject, Integer.toString(PaymentProcessor.Boleto.getValue()), new DataParser<WishBoletoInfo, JSONObject>() {
                public WishBoletoInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishBoletoInfo(jSONObject);
                }
            });
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithOxxo(null)) {
            this.mOxxoBillingInfoArray = JsonUtil.parseArray(jSONObject, Integer.toString(PaymentProcessor.Oxxo.getValue()), new DataParser<WishOxxoInfo, JSONObject>() {
                public WishOxxoInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishOxxoInfo(jSONObject);
                }
            });
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithKlarnaNative(null)) {
            this.mKlarnaBillingInfoArray = JsonUtil.parseArray(jSONObject, Integer.toString(PaymentProcessor.Klarna.getValue()), new DataParser<WishKlarnaInfo, JSONObject>() {
                public WishKlarnaInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishKlarnaInfo(jSONObject);
                }
            });
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithFuturePayPal(null)) {
            this.mBraintreePaypalBillingInfoArray = JsonUtil.parseArray(jSONObject, Integer.toString(PaymentProcessor.BraintreePayPal.getValue()), new DataParser<WishBraintreePayPalInfo, JSONObject>() {
                public WishBraintreePayPalInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishBraintreePayPalInfo(jSONObject);
                }
            });
        }
        if (ExperimentDataCenter.getInstance().canSeeCommerceLoanBillingOption() || ExperimentDataCenter.getInstance().canSeePayHalfBillingOption()) {
            this.mCommerceLoanInfoArray = JsonUtil.parseArray(jSONObject, Integer.toString(PaymentProcessor.CommerceLoan.getValue()), new DataParser<WishCommerceLoanInfo, JSONObject>() {
                public WishCommerceLoanInfo parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishCommerceLoanInfo(jSONObject);
                }
            });
        }
    }

    protected WishUserBillingInfo(Parcel parcel) {
        this.mDefaultCardId = parcel.readString();
        this.mBoletoBillingInfoArray = parcel.createTypedArrayList(WishBoletoInfo.CREATOR);
        this.mBraintreePaypalBillingInfoArray = parcel.createTypedArrayList(WishBraintreePayPalInfo.CREATOR);
        this.mOxxoBillingInfoArray = parcel.createTypedArrayList(WishOxxoInfo.CREATOR);
        this.mKlarnaBillingInfoArray = parcel.createTypedArrayList(WishKlarnaInfo.CREATOR);
        this.mCommerceLoanInfoArray = parcel.createTypedArrayList(WishCommerceLoanInfo.CREATOR);
        this.mCreditCardBillingInfo = new HashMap<>();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.mCreditCardBillingInfo.put((PaymentProcessor) parcel.readParcelable(PaymentProcessor.class.getClassLoader()), parcel.createTypedArrayList(WishCreditCardInfo.CREATOR));
        }
    }

    public String getDefaultCardId() {
        return this.mDefaultCardId;
    }

    public WishCreditCardInfo getDefaultCreditCardInfo(PaymentProcessor paymentProcessor) {
        List<WishCreditCardInfo> creditCardInfoList = getCreditCardInfoList(paymentProcessor);
        if (CollectionUtils.isEmpty(creditCardInfoList)) {
            return null;
        }
        if (this.mDefaultCardId != null) {
            for (WishCreditCardInfo wishCreditCardInfo : creditCardInfoList) {
                if (TextUtils.equals(wishCreditCardInfo.getId(), this.mDefaultCardId)) {
                    return wishCreditCardInfo;
                }
            }
        }
        return (WishCreditCardInfo) creditCardInfoList.get(0);
    }

    public List<WishCreditCardInfo> getCreditCardInfoList(PaymentProcessor paymentProcessor) {
        List<WishCreditCardInfo> list = (List) this.mCreditCardBillingInfo.get(paymentProcessor);
        return list != null ? list : Collections.emptyList();
    }

    public WishBoletoInfo getBoletoInfo() {
        if (this.mBoletoBillingInfoArray.size() > 0) {
            return (WishBoletoInfo) this.mBoletoBillingInfoArray.get(0);
        }
        return null;
    }

    public WishOxxoInfo getOxxoInfo() {
        if (this.mOxxoBillingInfoArray.size() > 0) {
            return (WishOxxoInfo) this.mOxxoBillingInfoArray.get(0);
        }
        return null;
    }

    public WishKlarnaInfo getKlarnaInfo() {
        if (this.mKlarnaBillingInfoArray.size() > 0) {
            return (WishKlarnaInfo) this.mKlarnaBillingInfoArray.get(0);
        }
        return null;
    }

    public WishBraintreePayPalInfo getBraintreePayPalInfo() {
        if (this.mBraintreePaypalBillingInfoArray.size() > 0) {
            return (WishBraintreePayPalInfo) this.mBraintreePaypalBillingInfoArray.get(0);
        }
        return null;
    }

    public WishCommerceLoanInfo getCommerceLoanInfo() {
        if (this.mCommerceLoanInfoArray.size() > 0) {
            return (WishCommerceLoanInfo) this.mCommerceLoanInfoArray.get(0);
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDefaultCardId);
        parcel.writeTypedList(this.mBoletoBillingInfoArray);
        parcel.writeTypedList(this.mBraintreePaypalBillingInfoArray);
        parcel.writeTypedList(this.mOxxoBillingInfoArray);
        parcel.writeTypedList(this.mKlarnaBillingInfoArray);
        parcel.writeTypedList(this.mCommerceLoanInfoArray);
        parcel.writeInt(this.mCreditCardBillingInfo == null ? 0 : this.mCreditCardBillingInfo.size());
        if (this.mCreditCardBillingInfo != null) {
            for (Entry entry : this.mCreditCardBillingInfo.entrySet()) {
                parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                parcel.writeTypedList((List) entry.getValue());
            }
        }
    }
}
