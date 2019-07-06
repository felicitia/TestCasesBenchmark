package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.contextlogic.wish.util.PreferenceUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCart extends BaseModel implements Parcelable {
    public static final Creator<WishCart> CREATOR = new Creator<WishCart>() {
        public WishCart createFromParcel(Parcel parcel) {
            return new WishCart(parcel);
        }

        public WishCart[] newArray(int i) {
            return new WishCart[i];
        }
    };
    private WishAddToCartOfferApplied mAddToCartOfferApplied;
    private WishCartAbandonOffer mCartAbandonOffer;
    private WishCheckoutOffer mCheckoutOffer;
    private WishLocalizedCurrencyValue mCredit;
    private ArrayList<WishCartItem> mItems;
    private PaymentProcessor mPaymentProcessor;
    private WishImage mPaymentTabCcImage;
    private String mPromoCodeDeeplink;
    private String mPromoCodeMessage;
    private boolean mRequiresFullBillingAddress;
    private String mShippingOfferText;
    private String mShippingOfferTitle;
    private WishLocalizedCurrencyValue mShippingPrice;
    private WishLocalizedCurrencyValue mSubtotal;
    private HashMap<PaymentMode, ArrayList<WishCartSummaryItem>> mSummaryItemsByPaymentMode;
    private String mTaxText;
    private WishLocalizedCurrencyValue mTotal;
    private int mTotalItemCount;

    public enum PaymentMode implements Parcelable {
        Default(-1),
        CreditCard(1),
        PayPal(2),
        GoogleWallet(3),
        Boleto(4),
        Klarna(5),
        Oxxo(6),
        Ideal(8),
        Paytm(9),
        CommerceLoan(10);
        
        public static final Creator<PaymentMode> CREATOR = null;
        private static Map<Integer, PaymentMode> map;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            int i;
            PaymentMode[] values;
            map = new HashMap();
            for (PaymentMode paymentMode : values()) {
                map.put(Integer.valueOf(paymentMode.getValue()), paymentMode);
            }
            CREATOR = new Creator<PaymentMode>() {
                public PaymentMode createFromParcel(Parcel parcel) {
                    return PaymentMode.values()[parcel.readInt()];
                }

                public PaymentMode[] newArray(int i) {
                    return new PaymentMode[i];
                }
            };
        }

        private PaymentMode(int i) {
            this.mValue = i;
        }

        public static PaymentMode valueOf(int i) {
            return (PaymentMode) map.get(Integer.valueOf(i));
        }

        public int getValue() {
            return this.mValue;
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.contextlogic.wish.api.model.WishCart.PaymentMode getPaymentModeFromPreference(java.lang.String r1) {
            /*
                int r0 = r1.hashCode()
                switch(r0) {
                    case -1951589076: goto L_0x0059;
                    case -1945193992: goto L_0x004f;
                    case -559751798: goto L_0x0045;
                    case -416515006: goto L_0x003b;
                    case -305175410: goto L_0x0031;
                    case -171506468: goto L_0x0027;
                    case -139988556: goto L_0x001c;
                    case 1876906729: goto L_0x0012;
                    case 2053347017: goto L_0x0008;
                    default: goto L_0x0007;
                }
            L_0x0007:
                goto L_0x0063
            L_0x0008:
                java.lang.String r0 = "PaymentModeCC"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 0
                goto L_0x0064
            L_0x0012:
                java.lang.String r0 = "PaymentModeOxxo"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 4
                goto L_0x0064
            L_0x001c:
                java.lang.String r0 = "PaymentModeCommerceLoan"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 8
                goto L_0x0064
            L_0x0027:
                java.lang.String r0 = "PaymentModePayPal"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 1
                goto L_0x0064
            L_0x0031:
                java.lang.String r0 = "PaymentModeKlarna"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 5
                goto L_0x0064
            L_0x003b:
                java.lang.String r0 = "PaymentModeGoogle"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 2
                goto L_0x0064
            L_0x0045:
                java.lang.String r0 = "PaymentModeBoleto"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 3
                goto L_0x0064
            L_0x004f:
                java.lang.String r0 = "PaymentModePaytm"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 7
                goto L_0x0064
            L_0x0059:
                java.lang.String r0 = "PaymentModeIdeal"
                boolean r1 = r1.equals(r0)
                if (r1 == 0) goto L_0x0063
                r1 = 6
                goto L_0x0064
            L_0x0063:
                r1 = -1
            L_0x0064:
                switch(r1) {
                    case 0: goto L_0x0082;
                    case 1: goto L_0x007f;
                    case 2: goto L_0x007c;
                    case 3: goto L_0x0079;
                    case 4: goto L_0x0076;
                    case 5: goto L_0x0073;
                    case 6: goto L_0x0070;
                    case 7: goto L_0x006d;
                    case 8: goto L_0x006a;
                    default: goto L_0x0067;
                }
            L_0x0067:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = Default
                return r1
            L_0x006a:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = CommerceLoan
                return r1
            L_0x006d:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = Paytm
                return r1
            L_0x0070:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = Ideal
                return r1
            L_0x0073:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = Klarna
                return r1
            L_0x0076:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = Oxxo
                return r1
            L_0x0079:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = Boleto
                return r1
            L_0x007c:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = GoogleWallet
                return r1
            L_0x007f:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = PayPal
                return r1
            L_0x0082:
                com.contextlogic.wish.api.model.WishCart$PaymentMode r1 = CreditCard
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.model.WishCart.PaymentMode.getPaymentModeFromPreference(java.lang.String):com.contextlogic.wish.api.model.WishCart$PaymentMode");
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum PaymentProcessor implements Parcelable {
        Unknown(-1),
        PayPal(0),
        Braintree(1),
        Stripe(3),
        Boleto(6),
        Klarna(8),
        Adyen(9),
        Ebanx(10),
        Oxxo(11),
        BraintreePayPal(16),
        CommerceLoan(21);
        
        public static final Creator<PaymentProcessor> CREATOR = null;
        public static List<PaymentProcessor> creditCardPaymentProcessors;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            creditCardPaymentProcessors = Arrays.asList(new PaymentProcessor[]{Braintree, Stripe, Ebanx, Adyen});
            CREATOR = new Creator<PaymentProcessor>() {
                public PaymentProcessor createFromParcel(Parcel parcel) {
                    return PaymentProcessor.values()[parcel.readInt()];
                }

                public PaymentProcessor[] newArray(int i) {
                    return new PaymentProcessor[i];
                }
            };
        }

        private PaymentProcessor(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static PaymentProcessor getCreditCardProcessor(int i) {
            return getCreditCardProcessor(i, Stripe);
        }

        public static PaymentProcessor getCreditCardProcessor(int i, PaymentProcessor paymentProcessor) {
            for (PaymentProcessor paymentProcessor2 : creditCardPaymentProcessors) {
                if (paymentProcessor2.getValue() == i) {
                    return paymentProcessor2;
                }
            }
            return paymentProcessor;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishCart(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mItems = new ArrayList<>();
        this.mSummaryItemsByPaymentMode = new HashMap<>();
        this.mPaymentProcessor = PaymentProcessor.getCreditCardProcessor(jSONObject.getInt("credit_card_processor"));
        if (AuthenticationDataCenter.getInstance().isLoggedIn() && ProfileDataCenter.getInstance().isAdmin()) {
            PaymentProcessor creditCardProcessorPreference = PreferenceUtil.getCreditCardProcessorPreference();
            if (creditCardProcessorPreference != null) {
                this.mPaymentProcessor = creditCardProcessorPreference;
            }
        }
        this.mTotalItemCount = jSONObject.getInt("total_item_quantity");
        this.mShippingPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("shipping"), jSONObject.optJSONObject("localized_shipping"));
        this.mTotal = new WishLocalizedCurrencyValue(jSONObject.getDouble("total"), jSONObject.optJSONObject("localized_total"));
        this.mSubtotal = new WishLocalizedCurrencyValue(jSONObject.getDouble("subtotal"), jSONObject.optJSONObject("localized_subtotal"));
        this.mCredit = new WishLocalizedCurrencyValue(jSONObject.getDouble("credit"), jSONObject.optJSONObject("localized_credit"));
        this.mItems = JsonUtil.parseArray(jSONObject, "items", new DataParser<WishCartItem, JSONObject>() {
            public WishCartItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishCartItem(jSONObject);
            }
        });
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
        if (JsonUtil.hasValue(jSONObject, "shipping_offer_title")) {
            this.mShippingOfferTitle = jSONObject.getString("shipping_offer_title");
        }
        if (JsonUtil.hasValue(jSONObject, "shipping_offer_text")) {
            this.mShippingOfferText = jSONObject.getString("shipping_offer_text");
        }
        if (JsonUtil.hasValue(jSONObject, "tax_text")) {
            this.mTaxText = jSONObject.getString("tax_text");
        }
        this.mRequiresFullBillingAddress = jSONObject.optBoolean("requires_full_billing_address", false);
        if (JsonUtil.hasValue(jSONObject, "add_to_cart_offer")) {
            this.mAddToCartOfferApplied = new WishAddToCartOfferApplied(jSONObject.getJSONObject("add_to_cart_offer"));
        }
        if (JsonUtil.hasValue(jSONObject, "cart_abandon_offer")) {
            this.mCartAbandonOffer = new WishCartAbandonOffer(jSONObject.getJSONObject("cart_abandon_offer"));
        }
        if (JsonUtil.hasValue(jSONObject, "promo_code_message")) {
            this.mPromoCodeMessage = jSONObject.getString("promo_code_message");
        }
        this.mPromoCodeDeeplink = JsonUtil.optString(jSONObject, "promo_code_deeplink");
        if (JsonUtil.hasValue(jSONObject, "payment_tab_cc_image_url")) {
            this.mPaymentTabCcImage = new WishImage(jSONObject.getString("payment_tab_cc_image_url"));
        }
    }

    protected WishCart(Parcel parcel) {
        this.mRequiresFullBillingAddress = parcel.readByte() != 0;
        this.mTotalItemCount = parcel.readInt();
        this.mShippingOfferText = parcel.readString();
        this.mShippingOfferTitle = parcel.readString();
        this.mTaxText = parcel.readString();
        this.mItems = parcel.createTypedArrayList(WishCartItem.CREATOR);
        int readInt = parcel.readInt();
        this.mSummaryItemsByPaymentMode = new HashMap<>();
        for (int i = 0; i < readInt; i++) {
            this.mSummaryItemsByPaymentMode.put((PaymentMode) parcel.readParcelable(PaymentMode.class.getClassLoader()), parcel.createTypedArrayList(WishCartSummaryItem.CREATOR));
        }
        this.mPaymentProcessor = (PaymentProcessor) parcel.readParcelable(PaymentProcessor.class.getClassLoader());
        this.mAddToCartOfferApplied = (WishAddToCartOfferApplied) parcel.readParcelable(WishAddToCartOfferApplied.class.getClassLoader());
        this.mCartAbandonOffer = (WishCartAbandonOffer) parcel.readParcelable(WishCartAbandonOffer.class.getClassLoader());
        this.mCheckoutOffer = (WishCheckoutOffer) parcel.readParcelable(WishCheckoutOffer.class.getClassLoader());
        this.mCredit = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mShippingPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mSubtotal = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mTotal = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mPaymentTabCcImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mPromoCodeDeeplink = parcel.readString();
    }

    public boolean getRequiresFullBillingAddress() {
        return this.mRequiresFullBillingAddress;
    }

    public void setCheckoutOffer(WishCheckoutOffer wishCheckoutOffer) {
        this.mCheckoutOffer = wishCheckoutOffer;
    }

    public WishCheckoutOffer getCheckoutOffer() {
        return this.mCheckoutOffer;
    }

    public WishCartAbandonOffer getCartAbandonOffer() {
        return this.mCartAbandonOffer;
    }

    public int getTotalItemCount() {
        return this.mTotalItemCount;
    }

    public WishAddToCartOfferApplied getAddToCartOfferApplied() {
        return this.mAddToCartOfferApplied;
    }

    public WishLocalizedCurrencyValue getShippingPrice() {
        return this.mShippingPrice;
    }

    public WishLocalizedCurrencyValue getTotal() {
        return this.mTotal;
    }

    public WishLocalizedCurrencyValue getSubtotal() {
        return this.mSubtotal;
    }

    public String getTaxText() {
        return this.mTaxText;
    }

    public ArrayList<WishCartItem> getItems() {
        return this.mItems;
    }

    public ArrayList<WishCartSummaryItem> getSummaryItems(String str) {
        ArrayList<WishCartSummaryItem> arrayList = (ArrayList) this.mSummaryItemsByPaymentMode.get(PaymentMode.getPaymentModeFromPreference(str));
        return arrayList == null ? (ArrayList) this.mSummaryItemsByPaymentMode.get(PaymentMode.Default) : arrayList;
    }

    public WishImage getPaymentTabCcImage() {
        return this.mPaymentTabCcImage;
    }

    public PaymentProcessor getPaymentProcessor() {
        return this.mPaymentProcessor;
    }

    public String getPromoCodeDeeplink() {
        return this.mPromoCodeDeeplink;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mRequiresFullBillingAddress ? (byte) 1 : 0);
        parcel.writeInt(this.mTotalItemCount);
        parcel.writeString(this.mShippingOfferText);
        parcel.writeString(this.mShippingOfferTitle);
        parcel.writeString(this.mTaxText);
        parcel.writeTypedList(this.mItems);
        parcel.writeInt(this.mSummaryItemsByPaymentMode == null ? 0 : this.mSummaryItemsByPaymentMode.size());
        if (this.mSummaryItemsByPaymentMode != null) {
            for (Entry entry : this.mSummaryItemsByPaymentMode.entrySet()) {
                parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                parcel.writeTypedList((List) entry.getValue());
            }
        }
        parcel.writeParcelable(this.mPaymentProcessor, 0);
        parcel.writeParcelable(this.mAddToCartOfferApplied, 0);
        parcel.writeParcelable(this.mCartAbandonOffer, 0);
        parcel.writeParcelable(this.mCheckoutOffer, 0);
        parcel.writeParcelable(this.mCredit, 0);
        parcel.writeParcelable(this.mShippingPrice, 0);
        parcel.writeParcelable(this.mSubtotal, 0);
        parcel.writeParcelable(this.mTotal, 0);
        parcel.writeParcelable(this.mPaymentTabCcImage, 0);
        parcel.writeString(this.mPromoCodeDeeplink);
    }

    public String getPromoCodeMessage() {
        return this.mPromoCodeMessage;
    }

    public WishCartItem getFreeGift() {
        Iterator it = this.mItems.iterator();
        while (it.hasNext()) {
            WishCartItem wishCartItem = (WishCartItem) it.next();
            if (wishCartItem.isFreeGift()) {
                return wishCartItem;
            }
        }
        return null;
    }
}
