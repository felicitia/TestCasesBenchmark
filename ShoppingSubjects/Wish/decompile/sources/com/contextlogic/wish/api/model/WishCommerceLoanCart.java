package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.contextlogic.wish.util.PreferenceUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceLoanCart extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceLoanCart> CREATOR = new Creator<WishCommerceLoanCart>() {
        public WishCommerceLoanCart createFromParcel(Parcel parcel) {
            return new WishCommerceLoanCart(parcel);
        }

        public WishCommerceLoanCart[] newArray(int i) {
            return new WishCommerceLoanCart[i];
        }
    };
    private WishLocalizedCurrencyValue mAmount;
    private WishImage mImage;
    private boolean mIsDefault;
    private LoanType mLoanType;
    private PaymentProcessor mPaymentProcessor;
    private boolean mRequiresFullBillingAddress;
    private String mSuccessMessage;
    private HashMap<PaymentMode, ArrayList<WishCartSummaryItem>> mSummaryItemsByPaymentMode;

    public enum LoanType {
        PAY_LATER(1),
        TWO_PAYMENTS(2);
        
        private int mValue;

        private LoanType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static LoanType fromValue(int i) {
            if (i != 2) {
                return PAY_LATER;
            }
            return TWO_PAYMENTS;
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishCommerceLoanCart(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mSummaryItemsByPaymentMode = new HashMap<>();
        this.mPaymentProcessor = PaymentProcessor.getCreditCardProcessor(jSONObject.getInt("credit_card_processor"));
        if (AuthenticationDataCenter.getInstance().isLoggedIn() && ProfileDataCenter.getInstance().isAdmin()) {
            PaymentProcessor creditCardProcessorPreference = PreferenceUtil.getCreditCardProcessorPreference();
            if (creditCardProcessorPreference != null) {
                this.mPaymentProcessor = creditCardProcessorPreference;
            }
        }
        if (jSONObject.has("cart_summary_by_payment_mode")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("cart_summary_by_payment_mode");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    PaymentMode valueOf = PaymentMode.valueOf(Integer.parseInt(str));
                    this.mSummaryItemsByPaymentMode.put(valueOf, JsonUtil.parseArray(jSONObject2, str, new DataParser<WishCartSummaryItem, JSONObject>() {
                        public WishCartSummaryItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                            return new WishCartSummaryItem(jSONObject);
                        }
                    }));
                } catch (NumberFormatException unused) {
                }
            }
        }
        String optString = jSONObject.optString("image_url");
        if (optString != null) {
            this.mImage = new WishImage(optString);
        }
        this.mSuccessMessage = jSONObject.optString("purchase_success_message");
        this.mAmount = new WishLocalizedCurrencyValue(jSONObject.optDouble("amount"), jSONObject.optJSONObject("localized_amount"));
        this.mRequiresFullBillingAddress = jSONObject.optBoolean("requires_full_billing_address", false);
        this.mIsDefault = jSONObject.optBoolean("is_default_loan", true);
        this.mLoanType = LoanType.fromValue(jSONObject.optInt("loan_type", LoanType.PAY_LATER.getValue()));
    }

    protected WishCommerceLoanCart(Parcel parcel) {
        int readInt = parcel.readInt();
        this.mSummaryItemsByPaymentMode = new HashMap<>();
        boolean z = false;
        for (int i = 0; i < readInt; i++) {
            this.mSummaryItemsByPaymentMode.put((PaymentMode) parcel.readParcelable(PaymentMode.class.getClassLoader()), parcel.createTypedArrayList(WishCartSummaryItem.CREATOR));
        }
        this.mPaymentProcessor = (PaymentProcessor) parcel.readParcelable(PaymentProcessor.class.getClassLoader());
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mSuccessMessage = parcel.readString();
        this.mAmount = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mRequiresFullBillingAddress = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsDefault = z;
        this.mLoanType = LoanType.fromValue(parcel.readInt());
    }

    public WishLocalizedCurrencyValue getAmount() {
        return this.mAmount;
    }

    public PaymentProcessor getPaymentProcessor() {
        return this.mPaymentProcessor;
    }

    public boolean getRequiresFullBillingAddress() {
        return this.mRequiresFullBillingAddress;
    }

    public WishImage getImage() {
        return this.mImage;
    }

    public ArrayList<WishCartSummaryItem> getSummaryItems(String str) {
        ArrayList<WishCartSummaryItem> arrayList = (ArrayList) this.mSummaryItemsByPaymentMode.get(PaymentMode.getPaymentModeFromPreference(str));
        return arrayList == null ? (ArrayList) this.mSummaryItemsByPaymentMode.get(PaymentMode.Default) : arrayList;
    }

    public boolean isDefaultLoan() {
        return this.mIsDefault;
    }

    public boolean isPayHalfLoan() {
        return this.mLoanType == LoanType.TWO_PAYMENTS;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSummaryItemsByPaymentMode == null ? 0 : this.mSummaryItemsByPaymentMode.size());
        if (this.mSummaryItemsByPaymentMode != null) {
            for (Entry entry : this.mSummaryItemsByPaymentMode.entrySet()) {
                parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                parcel.writeTypedList((List) entry.getValue());
            }
        }
        parcel.writeParcelable(this.mPaymentProcessor, 0);
        parcel.writeParcelable(this.mImage, 0);
        parcel.writeString(this.mSuccessMessage);
        parcel.writeParcelable(this.mAmount, 0);
        parcel.writeByte(this.mRequiresFullBillingAddress ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDefault ? (byte) 1 : 0);
        parcel.writeInt(this.mLoanType.getValue());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WishCommerceLoanCart)) {
            return false;
        }
        WishCommerceLoanCart wishCommerceLoanCart = (WishCommerceLoanCart) obj;
        if (!this.mSummaryItemsByPaymentMode.equals(wishCommerceLoanCart.mSummaryItemsByPaymentMode) || !this.mPaymentProcessor.equals(wishCommerceLoanCart.mPaymentProcessor) || !this.mImage.equals(wishCommerceLoanCart.mImage) || !this.mSuccessMessage.equals(wishCommerceLoanCart.mSuccessMessage) || !this.mAmount.equals(wishCommerceLoanCart.mAmount) || this.mRequiresFullBillingAddress != wishCommerceLoanCart.mRequiresFullBillingAddress) {
            z = false;
        }
        return z;
    }
}
