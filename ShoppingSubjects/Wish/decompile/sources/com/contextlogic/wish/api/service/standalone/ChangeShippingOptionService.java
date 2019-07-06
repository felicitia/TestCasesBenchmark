package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class ChangeShippingOptionService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(String str, String str2, String str3, int i, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("cart/change-shipping-option");
        apiRequest.addParameter("product_id", (Object) str);
        apiRequest.addParameter("variation_id", (Object) str2);
        apiRequest.addParameter("shipping_option_id", (Object) str3);
        apiRequest.addParameter("quantity", i);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    ChangeShippingOptionService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:14:0x0060 A[SYNTHETIC, Splitter:B:14:0x0060] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0076  */
            /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0044 A[SYNTHETIC, Splitter:B:8:0x0044] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleSuccess(com.contextlogic.wish.api.ApiResponse r7) throws org.json.JSONException, java.text.ParseException {
                /*
                    r6 = this;
                    com.contextlogic.wish.api.model.WishCart r0 = new com.contextlogic.wish.api.model.WishCart
                    org.json.JSONObject r1 = r7.getData()
                    java.lang.String r2 = "cart_info"
                    org.json.JSONObject r1 = r1.getJSONObject(r2)
                    r0.<init>(r1)
                    com.contextlogic.wish.api.datacenter.StatusDataCenter r1 = com.contextlogic.wish.api.datacenter.StatusDataCenter.getInstance()
                    int r2 = r0.getTotalItemCount()
                    r1.updateCartCount(r2)
                    org.json.JSONObject r1 = r7.getData()
                    java.lang.String r2 = "shipping_info"
                    boolean r1 = com.contextlogic.wish.util.JsonUtil.hasValue(r1, r2)
                    r2 = 0
                    if (r1 == 0) goto L_0x0037
                    com.contextlogic.wish.api.model.WishShippingInfo r1 = new com.contextlogic.wish.api.model.WishShippingInfo     // Catch:{ Throwable -> 0x0037 }
                    org.json.JSONObject r3 = r7.getData()     // Catch:{ Throwable -> 0x0037 }
                    java.lang.String r4 = "shipping_info"
                    org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ Throwable -> 0x0037 }
                    r1.<init>(r3)     // Catch:{ Throwable -> 0x0037 }
                    goto L_0x0038
                L_0x0037:
                    r1 = r2
                L_0x0038:
                    org.json.JSONObject r3 = r7.getData()
                    java.lang.String r4 = "user_billing_details"
                    boolean r3 = com.contextlogic.wish.util.JsonUtil.hasValue(r3, r4)
                    if (r3 == 0) goto L_0x0054
                    com.contextlogic.wish.api.model.WishUserBillingInfo r3 = new com.contextlogic.wish.api.model.WishUserBillingInfo     // Catch:{ Throwable -> 0x0054 }
                    org.json.JSONObject r4 = r7.getData()     // Catch:{ Throwable -> 0x0054 }
                    java.lang.String r5 = "user_billing_details"
                    org.json.JSONObject r4 = r4.getJSONObject(r5)     // Catch:{ Throwable -> 0x0054 }
                    r3.<init>(r4)     // Catch:{ Throwable -> 0x0054 }
                    r2 = r3
                L_0x0054:
                    org.json.JSONObject r3 = r7.getData()
                    java.lang.String r4 = "checkout_offer"
                    boolean r3 = com.contextlogic.wish.util.JsonUtil.hasValue(r3, r4)
                    if (r3 == 0) goto L_0x0072
                    com.contextlogic.wish.api.model.WishCheckoutOffer r3 = new com.contextlogic.wish.api.model.WishCheckoutOffer     // Catch:{ Throwable -> 0x0072 }
                    org.json.JSONObject r7 = r7.getData()     // Catch:{ Throwable -> 0x0072 }
                    java.lang.String r4 = "checkout_offer"
                    org.json.JSONObject r7 = r7.getJSONObject(r4)     // Catch:{ Throwable -> 0x0072 }
                    r3.<init>(r7)     // Catch:{ Throwable -> 0x0072 }
                    r0.setCheckoutOffer(r3)     // Catch:{ Throwable -> 0x0072 }
                L_0x0072:
                    com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService$SuccessCallback r7 = r7
                    if (r7 == 0) goto L_0x0080
                    com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService r7 = com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService.this
                    com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService$1$2 r3 = new com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService$1$2
                    r3.<init>(r0, r1, r2)
                    r7.postRunnable(r3)
                L_0x0080:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService.AnonymousClass1.handleSuccess(com.contextlogic.wish.api.ApiResponse):void");
            }
        });
    }
}
