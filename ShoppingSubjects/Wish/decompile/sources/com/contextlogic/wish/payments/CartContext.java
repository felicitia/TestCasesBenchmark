package com.contextlogic.wish.payments;

import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishCartSummaryItem;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.api.model.WishCommerceLoanBannerSpec;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishSavedForLaterProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager;
import com.contextlogic.wish.payments.checkout.DataEntryCheckoutActionManager;
import com.contextlogic.wish.payments.checkout.GooglePayCheckoutActionManager;
import com.contextlogic.wish.payments.checkout.KlarnaCheckoutActionManager;
import com.contextlogic.wish.payments.checkout.PlaceOrderCheckoutActionManager;
import com.contextlogic.wish.payments.checkout.SignupFlowCheckoutActionManager;
import com.contextlogic.wish.social.google.GoogleManager;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.google.android.gms.wallet.PaymentData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CartContext {
    private WishCart mCart;
    private CartCheckoutActionManager mCartCheckSkippedCheckoutActionManager;
    private CartCheckoutActionManager mCheckoutActionManager;
    private WishCommerceLoanBannerSpec mCommerceLoanBannerSpec;
    private WishCommerceLoanTabSpec mCommerceLoanTabSpec;
    private boolean mIsInCheckoutFlow;
    private WishLoanRepaymentBannerSpec mLoanRepaymentBannerSpec;
    private PaymentData mPaymentData;
    private WishPaymentStructureSelectionSpec mPaymentStructureSelectionSpec;
    private boolean mReloadCartOnReenter;
    private ArrayList<WishSavedForLaterProduct> mSavedForLaterProducts;
    private boolean mSelectedPayHalfLater;
    private WishShippingInfo mShippingInfo;
    private CartContextUpdatedCallback mUpdatedCallback;
    private WishUserBillingInfo mUserBillingInfo;

    public interface CartContextUpdatedCallback {
        void onCartContextUpdated(CartContext cartContext);
    }

    public enum CartType {
        COMMERCE_GOODS(1),
        COMMERCE_CASH(2),
        COMMERCE_LOAN(3);
        
        private int mValue;

        private CartType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public WishCommerceCashCart getCommerceCashCart() {
        return null;
    }

    public WishCommerceLoanCart getCommerceLoanCart() {
        return null;
    }

    public ArrayList<PaymentMode> getSupportedPaymentModes() {
        return null;
    }

    public boolean isCommerceLoanCartContext() {
        return false;
    }

    public boolean shouldReloadCartOnReenter() {
        return this.mReloadCartOnReenter;
    }

    public void setReloadCartOnReenter(boolean z) {
        this.mReloadCartOnReenter = z;
    }

    public void updateSavedForLater(ArrayList<WishSavedForLaterProduct> arrayList, boolean z) {
        this.mSavedForLaterProducts = arrayList;
        if (this.mUpdatedCallback != null && z) {
            this.mUpdatedCallback.onCartContextUpdated(this);
        }
    }

    public ArrayList<WishSavedForLaterProduct> getSavedForLaterProducts() {
        return this.mSavedForLaterProducts;
    }

    public boolean hasDefaultedLoan() {
        return this.mLoanRepaymentBannerSpec != null;
    }

    public void updateData(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, boolean z) {
        this.mCart = wishCart;
        this.mShippingInfo = wishShippingInfo;
        this.mUserBillingInfo = wishUserBillingInfo;
        boolean z2 = (this.mCommerceLoanTabSpec != null && this.mCommerceLoanTabSpec.hasOutstandingLoan()) || isCommerceLoanCartContext();
        if (getEffectivePaymentMode().equals("PaymentModeCommerceLoan") && z2 && hasCreditCardBillingInfo()) {
            PreferenceUtil.setString("payment_mode", "PaymentModeCC");
        }
        this.mCheckoutActionManager = createCheckoutActionManager(false, z);
        this.mCartCheckSkippedCheckoutActionManager = createCheckoutActionManager(false, z);
        if (this.mUpdatedCallback != null) {
            this.mUpdatedCallback.onCartContextUpdated(this);
        }
    }

    public void updateData(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo) {
        updateData(wishCart, wishShippingInfo, wishUserBillingInfo, false);
    }

    public void updateData(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
        this.mLoanRepaymentBannerSpec = wishLoanRepaymentBannerSpec;
        updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
    }

    public void setUpdatedCallback(CartContextUpdatedCallback cartContextUpdatedCallback) {
        this.mUpdatedCallback = cartContextUpdatedCallback;
    }

    public WishCart getCart() {
        return this.mCart;
    }

    public WishShippingInfo getShippingInfo() {
        return this.mShippingInfo;
    }

    public WishUserBillingInfo getUserBillingInfo() {
        return this.mUserBillingInfo;
    }

    public boolean isInCheckoutFlow() {
        return this.mIsInCheckoutFlow;
    }

    public void updatePreferredPaymentMode(String str) {
        PreferenceUtil.setString("payment_mode", str);
        updateData(this.mCart, this.mShippingInfo, this.mUserBillingInfo);
    }

    public void updateGooglePayPaymentData(PaymentData paymentData) {
        this.mPaymentData = paymentData;
        updateData(this.mCart, this.mShippingInfo, this.mUserBillingInfo);
    }

    public PaymentData getGooglePayPaymentData() {
        return this.mPaymentData;
    }

    public void updatePayHalfLaterFlag(boolean z) {
        this.mSelectedPayHalfLater = z;
    }

    public boolean getPayHalfLaterFlag() {
        return this.mSelectedPayHalfLater;
    }

    public String getCurrencyCode() {
        return (this.mCart == null || ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency()) ? "USD" : this.mCart.getTotal().getLocalizedCurrencyCode();
    }

    public String getCheckoutOfferId() {
        if (this.mCart == null || this.mCart.getCheckoutOffer() == null) {
            return null;
        }
        return this.mCart.getCheckoutOffer().getOfferId();
    }

    public boolean hasCreditCardBillingInfo() {
        return (this.mUserBillingInfo == null || this.mUserBillingInfo.getDefaultCreditCardInfo(getPaymentProcessor()) == null) ? false : true;
    }

    public CartType getCartType() {
        return CartType.COMMERCE_GOODS;
    }

    public PaymentProcessor getPaymentProcessor() {
        if (this.mCart != null) {
            return this.mCart.getPaymentProcessor();
        }
        return PaymentProcessor.Unknown;
    }

    public boolean getRequiresFullBillingAddress() {
        if (this.mCart != null) {
            return this.mCart.getRequiresFullBillingAddress();
        }
        return false;
    }

    public ArrayList<WishCartSummaryItem> getSummaryItems(String str) {
        if (this.mCart != null) {
            return this.mCart.getSummaryItems(str);
        }
        return null;
    }

    public WishLocalizedCurrencyValue getTotal() {
        if (this.mCart != null) {
            return this.mCart.getTotal();
        }
        return null;
    }

    public WishShippingInfo getBillingAddress() {
        if (hasCreditCardBillingInfo()) {
            return this.mUserBillingInfo.getDefaultCreditCardInfo(getPaymentProcessor()).getBillingAddress();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean hasBoletoBillingInfo() {
        return (this.mUserBillingInfo == null || this.mUserBillingInfo.getBoletoInfo() == null) ? false : true;
    }

    private boolean hasOxxoBillingInfo() {
        return (this.mUserBillingInfo == null || this.mUserBillingInfo.getOxxoInfo() == null) ? false : true;
    }

    private boolean hasKlarnaBillingInfo() {
        return (this.mUserBillingInfo == null || this.mUserBillingInfo.getKlarnaInfo() == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public boolean hasGooglePayBillingInfo() {
        return this.mPaymentData != null;
    }

    public boolean hasCommerceLoanBillingInfo(boolean z) {
        boolean z2 = false;
        boolean z3 = (this.mUserBillingInfo == null || this.mUserBillingInfo.getCommerceLoanInfo() == null || this.mUserBillingInfo.getCommerceLoanInfo().getCreditCardInfo() == null || this.mCommerceLoanTabSpec == null) ? false : true;
        if (z) {
            return z3;
        }
        if (z3 && this.mUserBillingInfo.getCommerceLoanInfo().getPreferredDueDate() != null && this.mUserBillingInfo.getCommerceLoanInfo().getPreferredDueDate().after(Calendar.getInstance().getTime())) {
            z2 = true;
        }
        return z2;
    }

    public boolean hasValidShippingInfo() {
        return hasValidShippingInfo(false);
    }

    public boolean hasValidShippingInfo(boolean z) {
        String effectivePaymentMode = getEffectivePaymentMode(z);
        return (effectivePaymentMode.equals("PaymentModeGoogle") && this.mShippingInfo != null) || effectivePaymentMode.equals("PaymentModeKlarna") || (effectivePaymentMode.equals("PaymentModePayPal") && this.mShippingInfo != null) || ((effectivePaymentMode.equals("PaymentModeIdeal") && this.mShippingInfo != null) || ((effectivePaymentMode.equals("PaymentModeBoleto") && this.mShippingInfo != null) || ((effectivePaymentMode.equals("PaymentModeOxxo") && this.mShippingInfo != null) || ((effectivePaymentMode.equals("PaymentModeCC") && this.mShippingInfo != null) || ((effectivePaymentMode.equals("PaymentModeCommerceLoan") && this.mShippingInfo != null) || (effectivePaymentMode.equals("PaymentModePaytm") && this.mShippingInfo != null))))));
    }

    public boolean hasValidBillingInfo() {
        return hasValidBillingInfo(false);
    }

    public boolean hasValidBillingInfo(boolean z) {
        String effectivePaymentMode = getEffectivePaymentMode(z);
        CartContext cartContext = z ? null : this;
        if ((effectivePaymentMode.equals("PaymentModePayPal") || effectivePaymentMode.equals("PaymentModeIdeal") || effectivePaymentMode.equals("PaymentModePaytm") || ((effectivePaymentMode.equals("PaymentModeCC") && hasCreditCardBillingInfo()) || ((effectivePaymentMode.equals("PaymentModeBoleto") && hasBoletoBillingInfo()) || ((effectivePaymentMode.equals("PaymentModeOxxo") && hasOxxoBillingInfo()) || ((effectivePaymentMode.equals("PaymentModeKlarna") && hasKlarnaBillingInfo()) || (effectivePaymentMode.equals("PaymentModeGoogle") && hasGooglePayBillingInfo())))))) && supportsPaymentMode(PaymentMode.getPaymentModeFromPreference(effectivePaymentMode), cartContext)) {
            return true;
        }
        if (!effectivePaymentMode.equals("PaymentModeCommerceLoan") || !hasCommerceLoanBillingInfo(true)) {
            return false;
        }
        return true;
    }

    public String getEffectivePaymentMode() {
        return getEffectivePaymentMode(false);
    }

    public String getEffectivePaymentMode(boolean z) {
        return getEffectivePaymentMode(z ? null : this);
    }

    public String getPaymentCredentialsDescriptionText() {
        return getPaymentCredentialsDescriptionText(false);
    }

    public String getPaymentCredentialsDescriptionText(boolean z) {
        String effectivePaymentMode = getEffectivePaymentMode(z);
        if (effectivePaymentMode.equals("PaymentModeGoogle")) {
            String string = WishApplication.getInstance().getString(R.string.google);
            if (this.mPaymentData == null) {
                return string;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("\n");
            sb.append(this.mPaymentData.getEmail());
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append("\n");
            sb3.append(this.mPaymentData.getCardInfo().getCardDescription());
            return sb3.toString();
        } else if (effectivePaymentMode.equals("PaymentModePayPal")) {
            return WishApplication.getInstance().getString(R.string.paypal);
        } else {
            if (effectivePaymentMode.equals("PaymentModeKlarna")) {
                return WishApplication.getInstance().getString(R.string.klarna);
            }
            if (effectivePaymentMode.equals("PaymentModeIdeal")) {
                return WishApplication.getInstance().getString(R.string.ideal);
            }
            if (effectivePaymentMode.equals("PaymentModePaytm")) {
                return WishApplication.getInstance().getString(R.string.paytm);
            }
            if (effectivePaymentMode.equals("PaymentModeCommerceLoan")) {
                String string2 = WishApplication.getInstance().getString(R.string.buy_now_commerce_loan);
                WishCreditCardInfo defaultCreditCardInfo = this.mUserBillingInfo.getDefaultCreditCardInfo(getPaymentProcessor());
                if (defaultCreditCardInfo == null) {
                    return string2;
                }
                String creditCardTypeDisplayString = CreditCardUtil.getCreditCardTypeDisplayString(defaultCreditCardInfo.getCardType());
                String lastFourDigits = defaultCreditCardInfo.getLastFourDigits();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(string2);
                sb4.append("\n");
                sb4.append(String.format(Locale.getDefault(), WishApplication.getInstance().getString(R.string.ending), new Object[]{creditCardTypeDisplayString, lastFourDigits}));
                return sb4.toString();
            }
            if (this.mUserBillingInfo != null) {
                if (effectivePaymentMode.equals("PaymentModeBoleto")) {
                    String string3 = WishApplication.getInstance().getString(R.string.boleto);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(string3);
                    sb5.append("\n");
                    sb5.append(String.format(WishApplication.getInstance().getString(R.string.cpf_colon_display), new Object[]{this.mUserBillingInfo.getBoletoInfo().getLastFourDigits()}));
                    return sb5.toString();
                } else if (effectivePaymentMode.equals("PaymentModeOxxo")) {
                    String string4 = WishApplication.getInstance().getString(R.string.oxxo);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(string4);
                    sb6.append("\n");
                    sb6.append(String.format(WishApplication.getInstance().getString(R.string.email_colon_display), new Object[]{this.mUserBillingInfo.getOxxoInfo().getEmail()}));
                    return sb6.toString();
                } else if (effectivePaymentMode.equals("PaymentModeCC")) {
                    WishCreditCardInfo defaultCreditCardInfo2 = this.mUserBillingInfo.getDefaultCreditCardInfo(getPaymentProcessor());
                    if (defaultCreditCardInfo2 != null) {
                        String creditCardTypeDisplayString2 = CreditCardUtil.getCreditCardTypeDisplayString(defaultCreditCardInfo2.getCardType());
                        String lastFourDigits2 = defaultCreditCardInfo2.getLastFourDigits();
                        return String.format(Locale.getDefault(), WishApplication.getInstance().getString(R.string.ending), new Object[]{creditCardTypeDisplayString2, lastFourDigits2});
                    }
                }
            }
            return null;
        }
    }

    public String getShippingDescriptionText() {
        return getShippingDescriptionText(false);
    }

    public String getShippingDescriptionText(boolean z) {
        getEffectivePaymentMode(z);
        if (this.mShippingInfo != null) {
            return this.mShippingInfo.getStreetAddressLineOne();
        }
        return null;
    }

    public CartCheckoutActionManager getCheckoutActionManager() {
        return getCheckoutActionManager(false);
    }

    public CartCheckoutActionManager getCheckoutActionManager(boolean z) {
        return z ? this.mCartCheckSkippedCheckoutActionManager : this.mCheckoutActionManager;
    }

    public boolean isCommerceCashMissingValidShippingInfo() {
        return getEffectivePaymentMode().equals("PaymentModeBoleto") && getCartType() == CartType.COMMERCE_CASH && !hasValidShippingInfo();
    }

    private CartCheckoutActionManager createCheckoutActionManager(boolean z, boolean z2) {
        boolean hasValidBillingInfo = hasValidBillingInfo(z);
        boolean hasValidShippingInfo = hasValidShippingInfo(z);
        String effectivePaymentMode = getEffectivePaymentMode(z);
        this.mIsInCheckoutFlow = false;
        if (effectivePaymentMode.equals("PaymentModeGoogle") && !hasValidBillingInfo && hasValidShippingInfo) {
            return new GooglePayCheckoutActionManager(this);
        }
        if (!ExperimentDataCenter.getInstance().canCheckoutWithKlarnaNative(this) && effectivePaymentMode.equals("PaymentModeKlarna")) {
            return new KlarnaCheckoutActionManager(this);
        }
        if (effectivePaymentMode.equals("PaymentModeCommerceLoan") && (!ExperimentDataCenter.getInstance().canCheckoutWithCommerceLoan(this) || !hasCommerceLoanBillingInfo(false))) {
            return new DataEntryCheckoutActionManager(this);
        }
        if ((hasValidBillingInfo || isFreeOrder()) && hasValidShippingInfo) {
            return new PlaceOrderCheckoutActionManager(this);
        }
        if ((hasValidBillingInfo || isFreeOrder()) && getCartType() == CartType.COMMERCE_CASH && !getEffectivePaymentMode().equals("PaymentModeBoleto")) {
            return new PlaceOrderCheckoutActionManager(this);
        }
        if ((hasValidBillingInfo || isFreeOrder()) && getCartType() == CartType.COMMERCE_LOAN) {
            return new PlaceOrderCheckoutActionManager(this);
        }
        if (z2) {
            return new SignupFlowCheckoutActionManager(this);
        }
        this.mIsInCheckoutFlow = true;
        return new DataEntryCheckoutActionManager(this);
    }

    public static String getEffectivePaymentMode(CartContext cartContext) {
        String string = PreferenceUtil.getString("payment_mode", "PaymentModeCC");
        if (ExperimentDataCenter.getInstance().canCheckoutWithKlarnaPaypal() && string.equalsIgnoreCase("PaymentModePayPal") && supportsPaymentMode(PaymentMode.PayPal, cartContext)) {
            return "PaymentModePayPal";
        }
        if (ExperimentDataCenter.getInstance().canCheckoutWithKlarnaOnly(cartContext)) {
            return "PaymentModeKlarna";
        }
        if (string.equals("PaymentModeKlarna") && supportsPaymentMode(PaymentMode.Klarna, cartContext)) {
            return "PaymentModeKlarna";
        }
        if (string.equals("PaymentModeBoleto") && supportsPaymentMode(PaymentMode.Boleto, cartContext)) {
            return "PaymentModeBoleto";
        }
        if (string.equals("PaymentModeOxxo") && supportsPaymentMode(PaymentMode.Oxxo, cartContext)) {
            return "PaymentModeOxxo";
        }
        if (string.equals("PaymentModeIdeal") && supportsPaymentMode(PaymentMode.Ideal, cartContext)) {
            return "PaymentModeIdeal";
        }
        if (string.equals("PaymentModePayPal") && supportsPaymentMode(PaymentMode.PayPal, cartContext)) {
            return "PaymentModePayPal";
        }
        if (string.equals("PaymentModePaytm") && supportsPaymentMode(PaymentMode.Paytm, cartContext)) {
            return "PaymentModePaytm";
        }
        if (!string.equals("PaymentModeGoogle") || !supportsPaymentMode(PaymentMode.GoogleWallet, cartContext)) {
            return (!string.equals("PaymentModeCommerceLoan") || !ExperimentDataCenter.getInstance().canSeeCommerceLoanBillingOption()) ? "PaymentModeCC" : "PaymentModeCommerceLoan";
        }
        return "PaymentModeGoogle";
    }

    public boolean hasFreeGift() {
        return (this.mCart == null || this.mCart.getFreeGift() == null) ? false : true;
    }

    public WishCartItem getFreeGift() {
        if (this.mCart != null) {
            return this.mCart.getFreeGift();
        }
        return null;
    }

    public boolean isFreeOrder() {
        return (getCartType() == CartType.COMMERCE_CASH || this.mCart == null || this.mCart.getItems() == null || this.mCart.getItems().size() <= 0 || getTotal() == null || getTotal().getUsdValue() != 0.0d) ? false : true;
    }

    public void setCommerceLoanBannerSpec(WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec) {
        this.mCommerceLoanBannerSpec = wishCommerceLoanBannerSpec;
    }

    public void setCommerceLoanTabSpec(WishCommerceLoanTabSpec wishCommerceLoanTabSpec) {
        this.mCommerceLoanTabSpec = wishCommerceLoanTabSpec;
    }

    public void setPaymentStructureSelectionSpec(WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
        this.mPaymentStructureSelectionSpec = wishPaymentStructureSelectionSpec;
    }

    public void setLoanRepaymentBannerSpec(WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
        this.mLoanRepaymentBannerSpec = wishLoanRepaymentBannerSpec;
    }

    public WishCommerceLoanBannerSpec getCommerceLoanBannerSpec() {
        return this.mCommerceLoanBannerSpec;
    }

    public WishCommerceLoanTabSpec getCommerceLoanTabSpec() {
        return this.mCommerceLoanTabSpec;
    }

    public WishPaymentStructureSelectionSpec getPaymentStructureSelectionSpec() {
        return this.mPaymentStructureSelectionSpec;
    }

    public WishLoanRepaymentBannerSpec getLoanRepaymentBannerSpec() {
        return this.mLoanRepaymentBannerSpec;
    }

    public static boolean supportsPaymentMode(PaymentMode paymentMode, CartContext cartContext) {
        boolean z = false;
        if (cartContext == null || cartContext.getCartType() != CartType.COMMERCE_CASH) {
            ExperimentDataCenter instance = ExperimentDataCenter.getInstance();
            if (paymentMode == PaymentMode.CreditCard) {
                return instance.canCheckoutWithCreditCard(cartContext);
            }
            if (paymentMode == PaymentMode.PayPal) {
                return instance.canCheckoutWithPayPal(cartContext);
            }
            if (paymentMode == PaymentMode.GoogleWallet) {
                return instance.canCheckoutWithGooglePay(cartContext);
            }
            if (paymentMode == PaymentMode.Boleto) {
                return instance.canCheckoutWithBoleto(cartContext);
            }
            if (paymentMode == PaymentMode.Klarna) {
                return instance.canCheckoutWithKlarnaNative(cartContext);
            }
            if (paymentMode == PaymentMode.Oxxo) {
                return instance.canCheckoutWithOxxo(cartContext);
            }
            if (paymentMode == PaymentMode.Ideal) {
                return instance.canCheckoutWithIdeal(cartContext);
            }
            if (paymentMode == PaymentMode.Paytm) {
                return instance.canCheckoutWithPaytm(cartContext);
            }
            return false;
        } else if (paymentMode == PaymentMode.GoogleWallet && !GoogleManager.getInstance().isPlayServicesAvailable()) {
            return false;
        } else {
            if (cartContext.getSupportedPaymentModes() != null && cartContext.getSupportedPaymentModes().contains(paymentMode)) {
                z = true;
            }
            return z;
        }
    }
}
