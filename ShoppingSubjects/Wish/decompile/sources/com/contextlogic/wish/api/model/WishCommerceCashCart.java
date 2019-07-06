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

public class WishCommerceCashCart extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceCashCart> CREATOR = new Creator<WishCommerceCashCart>() {
        public WishCommerceCashCart createFromParcel(Parcel parcel) {
            return new WishCommerceCashCart(parcel);
        }

        public WishCommerceCashCart[] newArray(int i) {
            return new WishCommerceCashCart[i];
        }
    };
    private WishLocalizedCurrencyValue mAmount;
    private String mBoletoPurchaseSuccessMessage;
    private WishLocalizedCurrencyValue mBonus;
    private String mBonusMessage;
    private boolean mHideTermsOfCondition;
    private WishImage mImage;
    private PaymentProcessor mPaymentProcessor;
    private String mRefundPolicy;
    private boolean mRequiresFullBillingAddress;
    private String mSuccessMessage;
    private HashMap<PaymentMode, ArrayList<WishCartSummaryItem>> mSummaryItemsByPaymentMode;
    private ArrayList<PaymentMode> mSupportedPaymentModes;
    private String mTaxText;

    public int describeContents() {
        return 0;
    }

    public WishCommerceCashCart(JSONObject jSONObject) throws JSONException, ParseException {
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
        this.mImage = new WishImage(jSONObject.getString("image_url"));
        this.mBonusMessage = jSONObject.optString("message");
        this.mSuccessMessage = jSONObject.optString("purchase_success_message");
        this.mBoletoPurchaseSuccessMessage = jSONObject.optString("boleto_purchase_success_message");
        this.mRefundPolicy = jSONObject.optString("fine_print_message");
        this.mTaxText = jSONObject.optString("tax_text");
        this.mAmount = new WishLocalizedCurrencyValue(jSONObject.optDouble("amount"), jSONObject.optJSONObject("localized_amount"));
        this.mBonus = new WishLocalizedCurrencyValue(jSONObject.optDouble("bonus"), jSONObject.optJSONObject("localized_amount"));
        this.mRequiresFullBillingAddress = jSONObject.optBoolean("requires_full_billing_address", false);
        this.mHideTermsOfCondition = jSONObject.optBoolean("hide_terms");
        this.mSupportedPaymentModes = JsonUtil.parseArray(jSONObject, "supported_payment_modes", new DataParser<PaymentMode, Long>() {
            public PaymentMode parseData(Long l) {
                return PaymentMode.valueOf(l.intValue());
            }
        });
    }

    protected WishCommerceCashCart(Parcel parcel) {
        int readInt = parcel.readInt();
        this.mSummaryItemsByPaymentMode = new HashMap<>();
        for (int i = 0; i < readInt; i++) {
            this.mSummaryItemsByPaymentMode.put((PaymentMode) parcel.readParcelable(PaymentMode.class.getClassLoader()), parcel.createTypedArrayList(WishCartSummaryItem.CREATOR));
        }
        this.mPaymentProcessor = (PaymentProcessor) parcel.readParcelable(PaymentProcessor.class.getClassLoader());
        this.mImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mBonusMessage = parcel.readString();
        this.mSuccessMessage = parcel.readString();
        this.mBoletoPurchaseSuccessMessage = parcel.readString();
        this.mRefundPolicy = parcel.readString();
        this.mTaxText = parcel.readString();
        this.mAmount = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mBonus = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        boolean z = true;
        this.mRequiresFullBillingAddress = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.mHideTermsOfCondition = z;
        this.mSupportedPaymentModes = new ArrayList<>();
        for (int valueOf : parcel.createIntArray()) {
            this.mSupportedPaymentModes.add(PaymentMode.valueOf(valueOf));
        }
    }

    public String getBonusMessage() {
        return this.mBonusMessage;
    }

    public String getSuccessMessage() {
        return this.mSuccessMessage;
    }

    public String getBoletoSuccessMessage() {
        return this.mBoletoPurchaseSuccessMessage;
    }

    public String getRefundPolicy() {
        return this.mRefundPolicy;
    }

    public String getTaxText() {
        return this.mTaxText;
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

    public boolean shouldHideTermsOfCondition() {
        return this.mHideTermsOfCondition;
    }

    public ArrayList<WishCartSummaryItem> getSummaryItems(String str) {
        ArrayList<WishCartSummaryItem> arrayList = (ArrayList) this.mSummaryItemsByPaymentMode.get(PaymentMode.getPaymentModeFromPreference(str));
        return arrayList == null ? (ArrayList) this.mSummaryItemsByPaymentMode.get(PaymentMode.Default) : arrayList;
    }

    public ArrayList<PaymentMode> getSupportedPaymentModes() {
        return this.mSupportedPaymentModes;
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
        parcel.writeString(this.mBonusMessage);
        parcel.writeString(this.mSuccessMessage);
        parcel.writeString(this.mBoletoPurchaseSuccessMessage);
        parcel.writeString(this.mRefundPolicy);
        parcel.writeString(this.mTaxText);
        parcel.writeParcelable(this.mAmount, 0);
        parcel.writeParcelable(this.mBonus, 0);
        parcel.writeByte(this.mRequiresFullBillingAddress ? (byte) 1 : 0);
        parcel.writeByte(this.mHideTermsOfCondition ? (byte) 1 : 0);
        int[] iArr = new int[this.mSupportedPaymentModes.size()];
        for (int i2 = 0; i2 < this.mSupportedPaymentModes.size(); i2++) {
            iArr[i2] = ((PaymentMode) this.mSupportedPaymentModes.get(i2)).getValue();
        }
        parcel.writeIntArray(iArr);
    }
}
