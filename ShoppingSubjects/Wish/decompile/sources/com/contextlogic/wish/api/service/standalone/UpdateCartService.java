package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class UpdateCartService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec);
    }

    public void requestService(String str, String str2, String str3, int i, boolean z, boolean z2, String str4, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("cart/update");
        final String str5 = str;
        apiRequest.addParameter("product_id", (Object) str5);
        final String str6 = str2;
        apiRequest.addParameter("variation_id", (Object) str6);
        apiRequest.addParameter("shipping_option_id", (Object) str3);
        apiRequest.addParameter("quantity", i);
        final boolean z3 = z;
        apiRequest.addParameter("add_to_cart", z3);
        apiRequest.addParameter("add_to_cart_offer_id", (Object) str4);
        if (z2) {
            apiRequest.addParameter("should_clear_cart", (Object) "true");
        }
        final DefaultFailureCallback defaultFailureCallback2 = defaultFailureCallback;
        final SuccessCallback successCallback2 = successCallback;
        AnonymousClass1 r2 = new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback2 != null) {
                    UpdateCartService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback2.onFailure(str);
                        }
                    });
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:24:0x00b5 A[SYNTHETIC, Splitter:B:24:0x00b5] */
            /* JADX WARNING: Removed duplicated region for block: B:31:0x00d3 A[SYNTHETIC, Splitter:B:31:0x00d3] */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x00f1  */
            /* JADX WARNING: Removed duplicated region for block: B:37:0x0102  */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x010f  */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x0120  */
            /* JADX WARNING: Removed duplicated region for block: B:44:0x0125  */
            /* JADX WARNING: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleSuccess(com.contextlogic.wish.api.ApiResponse r9) throws org.json.JSONException, java.text.ParseException {
                /*
                    r8 = this;
                    com.contextlogic.wish.api.model.WishCart r2 = new com.contextlogic.wish.api.model.WishCart
                    org.json.JSONObject r0 = r9.getData()
                    java.lang.String r1 = "cart_info"
                    org.json.JSONObject r0 = r0.getJSONObject(r1)
                    r2.<init>(r0)
                    boolean r0 = r5
                    r1 = 0
                    if (r0 == 0) goto L_0x0080
                    java.util.ArrayList r0 = r2.getItems()     // Catch:{ Throwable -> 0x0080 }
                    java.util.Iterator r0 = r0.iterator()     // Catch:{ Throwable -> 0x0080 }
                L_0x001c:
                    boolean r3 = r0.hasNext()     // Catch:{ Throwable -> 0x0080 }
                    if (r3 == 0) goto L_0x0041
                    java.lang.Object r3 = r0.next()     // Catch:{ Throwable -> 0x0080 }
                    com.contextlogic.wish.api.model.WishCartItem r3 = (com.contextlogic.wish.api.model.WishCartItem) r3     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r4 = r3.getProductId()     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r5 = r6     // Catch:{ Throwable -> 0x0080 }
                    boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x0080 }
                    if (r4 == 0) goto L_0x001c
                    java.lang.String r4 = r3.getVariationId()     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r5 = r7     // Catch:{ Throwable -> 0x0080 }
                    boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x0080 }
                    if (r4 == 0) goto L_0x001c
                    goto L_0x0042
                L_0x0041:
                    r3 = r1
                L_0x0042:
                    if (r3 == 0) goto L_0x0080
                    android.os.Bundle r0 = new android.os.Bundle     // Catch:{ Throwable -> 0x0080 }
                    r0.<init>()     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r4 = "fb_currency"
                    com.contextlogic.wish.api.model.WishLocalizedCurrencyValue r5 = r3.getProductSubtotal()     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r5 = r5.getLocalizedCurrencyCode()     // Catch:{ Throwable -> 0x0080 }
                    r0.putString(r4, r5)     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r4 = "fb_content_type"
                    java.lang.String r5 = "product"
                    r0.putString(r4, r5)     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r4 = "fb_content_id"
                    java.lang.String r5 = r6     // Catch:{ Throwable -> 0x0080 }
                    r0.putString(r4, r5)     // Catch:{ Throwable -> 0x0080 }
                    com.contextlogic.wish.social.facebook.FacebookManager r4 = com.contextlogic.wish.social.facebook.FacebookManager.getInstance()     // Catch:{ Throwable -> 0x0080 }
                    com.facebook.appevents.AppEventsLogger r4 = r4.getLogger()     // Catch:{ Throwable -> 0x0080 }
                    java.lang.String r5 = "fb_mobile_add_to_cart"
                    com.contextlogic.wish.api.model.WishLocalizedCurrencyValue r6 = r3.getProductSubtotal()     // Catch:{ Throwable -> 0x0080 }
                    double r6 = r6.getValue()     // Catch:{ Throwable -> 0x0080 }
                    r4.logEvent(r5, r6, r0)     // Catch:{ Throwable -> 0x0080 }
                    com.contextlogic.wish.analytics.GoogleAnalyticsLogger r0 = com.contextlogic.wish.analytics.GoogleAnalyticsLogger.getInstance()     // Catch:{ Throwable -> 0x0080 }
                    r0.logAddToCart(r3)     // Catch:{ Throwable -> 0x0080 }
                L_0x0080:
                    com.contextlogic.wish.api.datacenter.StatusDataCenter r0 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
                    int r3 = r2.getTotalItemCount()
                    r0.updateCartCount(r3)
                    org.json.JSONObject r0 = r9.getData()
                    java.lang.String r3 = "shipping_info"
                    boolean r0 = com.contextlogic.wish.util.JsonUtil.hasValue(r0, r3)
                    if (r0 == 0) goto L_0x00a8
                    com.contextlogic.wish.api.model.WishShippingInfo r0 = new com.contextlogic.wish.api.model.WishShippingInfo     // Catch:{ Throwable -> 0x00a8 }
                    org.json.JSONObject r3 = r9.getData()     // Catch:{ Throwable -> 0x00a8 }
                    java.lang.String r4 = "shipping_info"
                    org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ Throwable -> 0x00a8 }
                    r0.<init>(r3)     // Catch:{ Throwable -> 0x00a8 }
                    r3 = r0
                    goto L_0x00a9
                L_0x00a8:
                    r3 = r1
                L_0x00a9:
                    org.json.JSONObject r0 = r9.getData()
                    java.lang.String r4 = "user_billing_details"
                    boolean r0 = com.contextlogic.wish.util.JsonUtil.hasValue(r0, r4)
                    if (r0 == 0) goto L_0x00c6
                    com.contextlogic.wish.api.model.WishUserBillingInfo r0 = new com.contextlogic.wish.api.model.WishUserBillingInfo     // Catch:{ Throwable -> 0x00c6 }
                    org.json.JSONObject r4 = r9.getData()     // Catch:{ Throwable -> 0x00c6 }
                    java.lang.String r5 = "user_billing_details"
                    org.json.JSONObject r4 = r4.getJSONObject(r5)     // Catch:{ Throwable -> 0x00c6 }
                    r0.<init>(r4)     // Catch:{ Throwable -> 0x00c6 }
                    r4 = r0
                    goto L_0x00c7
                L_0x00c6:
                    r4 = r1
                L_0x00c7:
                    org.json.JSONObject r0 = r9.getData()
                    java.lang.String r5 = "checkout_offer"
                    boolean r0 = com.contextlogic.wish.util.JsonUtil.hasValue(r0, r5)
                    if (r0 == 0) goto L_0x00e5
                    com.contextlogic.wish.api.model.WishCheckoutOffer r0 = new com.contextlogic.wish.api.model.WishCheckoutOffer     // Catch:{ Throwable -> 0x00e5 }
                    org.json.JSONObject r5 = r9.getData()     // Catch:{ Throwable -> 0x00e5 }
                    java.lang.String r6 = "checkout_offer"
                    org.json.JSONObject r5 = r5.getJSONObject(r6)     // Catch:{ Throwable -> 0x00e5 }
                    r0.<init>(r5)     // Catch:{ Throwable -> 0x00e5 }
                    r2.setCheckoutOffer(r0)     // Catch:{ Throwable -> 0x00e5 }
                L_0x00e5:
                    org.json.JSONObject r0 = r9.getData()
                    java.lang.String r5 = "commerce_loan_tab_spec"
                    boolean r0 = com.contextlogic.wish.util.JsonUtil.hasValue(r0, r5)
                    if (r0 == 0) goto L_0x0102
                    com.contextlogic.wish.api.model.WishCommerceLoanTabSpec r0 = new com.contextlogic.wish.api.model.WishCommerceLoanTabSpec
                    org.json.JSONObject r5 = r9.getData()
                    java.lang.String r6 = "commerce_loan_tab_spec"
                    org.json.JSONObject r5 = r5.getJSONObject(r6)
                    r0.<init>(r5)
                    r5 = r0
                    goto L_0x0103
                L_0x0102:
                    r5 = r1
                L_0x0103:
                    org.json.JSONObject r0 = r9.getData()
                    java.lang.String r6 = "payment_structure_spec"
                    boolean r0 = com.contextlogic.wish.util.JsonUtil.hasValue(r0, r6)
                    if (r0 == 0) goto L_0x0120
                    com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec r0 = new com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec
                    org.json.JSONObject r9 = r9.getData()
                    java.lang.String r1 = "payment_structure_spec"
                    org.json.JSONObject r9 = r9.getJSONObject(r1)
                    r0.<init>(r9)
                    r6 = r0
                    goto L_0x0121
                L_0x0120:
                    r6 = r1
                L_0x0121:
                    com.contextlogic.wish.api.service.standalone.UpdateCartService$SuccessCallback r9 = r8
                    if (r9 == 0) goto L_0x0131
                    com.contextlogic.wish.api.service.standalone.UpdateCartService r9 = com.contextlogic.wish.api.service.standalone.UpdateCartService.this
                    com.contextlogic.wish.api.service.standalone.UpdateCartService$1$2 r7 = new com.contextlogic.wish.api.service.standalone.UpdateCartService$1$2
                    r0 = r7
                    r1 = r8
                    r0.<init>(r2, r3, r4, r5, r6)
                    r9.postRunnable(r7)
                L_0x0131:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.UpdateCartService.AnonymousClass1.handleSuccess(com.contextlogic.wish.api.ApiResponse):void");
            }
        };
        startService(apiRequest, r2);
    }
}
