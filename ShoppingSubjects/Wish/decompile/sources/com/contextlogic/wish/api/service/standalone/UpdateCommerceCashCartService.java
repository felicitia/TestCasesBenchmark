package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;

public class UpdateCommerceCashCartService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCommerceCashCart wishCommerceCashCart, WishUserBillingInfo wishUserBillingInfo, WishShippingInfo wishShippingInfo);
    }

    public void requestService(double d, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("commerce-cash-cart/update");
        apiRequest.addParameter("amount", d);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateCommerceCashCartService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            /* JADX WARNING: Removed duplicated region for block: B:12:0x004c  */
            /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
            /* JADX WARNING: Removed duplicated region for block: B:9:0x0039  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void handleSuccess(com.contextlogic.wish.api.ApiResponse r6) throws org.json.JSONException, java.text.ParseException {
                /*
                    r5 = this;
                    com.contextlogic.wish.api.model.WishCommerceCashCart r0 = new com.contextlogic.wish.api.model.WishCommerceCashCart
                    org.json.JSONObject r1 = r6.getData()
                    java.lang.String r2 = "cart_info"
                    org.json.JSONObject r1 = r1.getJSONObject(r2)
                    r0.<init>(r1)
                    r1 = 0
                    org.json.JSONObject r2 = r6.getData()     // Catch:{ Throwable -> 0x002c }
                    java.lang.String r3 = "user_billing_details"
                    boolean r2 = com.contextlogic.wish.util.JsonUtil.hasValue(r2, r3)     // Catch:{ Throwable -> 0x002c }
                    if (r2 == 0) goto L_0x002c
                    com.contextlogic.wish.api.model.WishUserBillingInfo r2 = new com.contextlogic.wish.api.model.WishUserBillingInfo     // Catch:{ Throwable -> 0x002c }
                    org.json.JSONObject r3 = r6.getData()     // Catch:{ Throwable -> 0x002c }
                    java.lang.String r4 = "user_billing_details"
                    org.json.JSONObject r3 = r3.getJSONObject(r4)     // Catch:{ Throwable -> 0x002c }
                    r2.<init>(r3)     // Catch:{ Throwable -> 0x002c }
                    goto L_0x002d
                L_0x002c:
                    r2 = r1
                L_0x002d:
                    org.json.JSONObject r3 = r6.getData()
                    java.lang.String r4 = "shipping_info"
                    boolean r3 = com.contextlogic.wish.util.JsonUtil.hasValue(r3, r4)
                    if (r3 == 0) goto L_0x0048
                    com.contextlogic.wish.api.model.WishShippingInfo r1 = new com.contextlogic.wish.api.model.WishShippingInfo
                    org.json.JSONObject r6 = r6.getData()
                    java.lang.String r3 = "shipping_info"
                    org.json.JSONObject r6 = r6.getJSONObject(r3)
                    r1.<init>(r6)
                L_0x0048:
                    com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService$SuccessCallback r6 = r5
                    if (r6 == 0) goto L_0x0056
                    com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService r6 = com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService.this
                    com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService$1$2 r3 = new com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService$1$2
                    r3.<init>(r0, r2, r1)
                    r6.postRunnable(r3)
                L_0x0056:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService.AnonymousClass1.handleSuccess(com.contextlogic.wish.api.ApiResponse):void");
            }
        });
    }
}
