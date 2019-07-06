package com.etsy.android.androidpay;

import android.support.annotation.Nullable;
import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.PaymentMethod;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayLineItemContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class LocalAndroidPayData implements i, AndroidPayDataContract {
    private static final long serialVersionUID = 1420529739868958103L;
    protected int cartId;
    protected List<LocalAndroidPayLineItem> lineItems;
    protected PaymentMethod paymentMethod;
    protected String shopName;
    protected EtsyMoney total;

    public String getMessageToSeller() {
        return "";
    }

    public boolean hasGiftCardApplied() {
        return false;
    }

    public LocalAndroidPayData() {
        this("");
    }

    public LocalAndroidPayData(String str) {
        this.shopName = str;
        this.cartId = 0;
        this.total = new EtsyMoney();
        this.lineItems = new ArrayList();
    }

    public List<? extends AndroidPayLineItemContract> getLineItems() {
        return this.lineItems;
    }

    public void setLineItems(List<LocalAndroidPayLineItem> list) {
        this.lineItems = list;
    }

    public int getCartId() {
        return this.cartId;
    }

    public void setCartId(int i) {
        this.cartId = i;
    }

    public EtsyMoney getTotal() {
        return this.total;
    }

    public void setTotal(EtsyMoney etsyMoney) {
        this.total = etsyMoney;
    }

    public String getShopName() {
        return this.shopName;
    }

    public PaymentMethod getLastPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod2) {
        this.paymentMethod = paymentMethod2;
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap<>(1);
        hashMap.put(AnalyticsLogAttribute.CART_ID, Integer.valueOf(this.cartId));
        return hashMap;
    }
}
