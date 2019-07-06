package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CreditCardHolder;
import org.json.JSONObject;

public class WishCachedBillingInfo extends BaseModel implements Parcelable {
    public static final Creator<WishCachedBillingInfo> CREATOR = new Creator<WishCachedBillingInfo>() {
        public WishCachedBillingInfo createFromParcel(Parcel parcel) {
            return new WishCachedBillingInfo(parcel);
        }

        public WishCachedBillingInfo[] newArray(int i) {
            return new WishCachedBillingInfo[i];
        }
    };
    private WishShippingInfo mBillingAddress;
    private BraintreeErrorListener mBraintreeErrorListener;
    /* access modifiers changed from: private */
    public String mCardNonce;
    private String mLastFourDigits;
    private PaymentMethodNonceCreatedListener mNonceCreatedListener;
    private String mStripeToken;

    public interface CachedBillingInfoSaveCallback {
        void onSaveComplete(WishCachedBillingInfo wishCachedBillingInfo);

        void onSaveFailure(WishCachedBillingInfo wishCachedBillingInfo);
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) {
    }

    public WishCachedBillingInfo(String str, CreditCardHolder creditCardHolder, WishShippingInfo wishShippingInfo) {
        this.mStripeToken = str;
        this.mBillingAddress = wishShippingInfo;
        this.mLastFourDigits = creditCardHolder.getCardNumber().trim().substring(creditCardHolder.getCardNumber().length() - 4);
    }

    public WishCachedBillingInfo(final BraintreeFragment braintreeFragment, CreditCardHolder creditCardHolder, WishShippingInfo wishShippingInfo, final CachedBillingInfoSaveCallback cachedBillingInfoSaveCallback) {
        this.mBillingAddress = wishShippingInfo;
        this.mLastFourDigits = creditCardHolder.getCardNumber().trim().substring(creditCardHolder.getCardNumber().length() - 4);
        this.mNonceCreatedListener = new PaymentMethodNonceCreatedListener() {
            public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
                WishCachedBillingInfo.this.mCardNonce = paymentMethodNonce.getNonce();
                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                cachedBillingInfoSaveCallback.onSaveComplete(WishCachedBillingInfo.this);
            }
        };
        this.mBraintreeErrorListener = new BraintreeErrorListener() {
            public void onError(Exception exc) {
                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                cachedBillingInfoSaveCallback.onSaveFailure(WishCachedBillingInfo.this);
            }
        };
        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, this.mBraintreeErrorListener);
        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, this.mNonceCreatedListener);
        Card.tokenize(braintreeFragment, (CardBuilder) ((CardBuilder) ((CardBuilder) new CardBuilder().cardNumber(creditCardHolder.getCardNumber())).expirationDate(CreditCardUtil.getFormattedExpiryDate(creditCardHolder.getExpiryMonth(), creditCardHolder.getExpiryYear()))).cvv(creditCardHolder.getSecurityCode()));
    }

    protected WishCachedBillingInfo(Parcel parcel) {
        this.mCardNonce = parcel.readString();
        this.mLastFourDigits = parcel.readString();
        this.mBillingAddress = (WishShippingInfo) parcel.readParcelable(WishShippingInfo.class.getClassLoader());
    }

    public void clearNonce() {
        this.mCardNonce = null;
    }

    public String getLastFourDigits() {
        return this.mLastFourDigits;
    }

    public String getCardNonce() {
        return this.mCardNonce;
    }

    public String getStripeToken() {
        return this.mStripeToken;
    }

    public void clearStripeToken() {
        this.mStripeToken = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardNonce);
        parcel.writeString(this.mLastFourDigits);
        parcel.writeParcelable(this.mBillingAddress, 0);
    }
}
