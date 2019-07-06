package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import org.json.JSONException;

public class InitiateBraintreePaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(String str, String str2, String str3, String str4, String str5, int i, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("payment/braintree/complete");
        apiRequest.addParameter("client", (Object) "androidapp");
        apiRequest.addParameter("currency", (Object) str);
        apiRequest.addParameter("cart_type", i);
        if (str2 != null) {
            apiRequest.addParameter("card-nonce", (Object) str2);
        }
        if (str3 != null) {
            apiRequest.addParameter("cvv", (Object) str3);
        }
        if (str4 != null) {
            apiRequest.addParameter("checkout_offer_id", (Object) str4);
        }
        if (str5 != null) {
            apiRequest.addParameter("device_data", (Object) str5);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    InitiateBraintreePaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            int code = apiResponse != null ? apiResponse.getCode() : -1;
                            WishDeclineRedirectInfo wishDeclineRedirectInfo = null;
                            if (!(apiResponse == null || apiResponse.getData() == null || !JsonUtil.hasValue(apiResponse.getData(), "redirect_info"))) {
                                try {
                                    wishDeclineRedirectInfo = new WishDeclineRedirectInfo(apiResponse.getData().getJSONObject("redirect_info"));
                                } catch (Throwable unused) {
                                }
                            }
                            failureCallback.onFailure(str, code, wishDeclineRedirectInfo);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("transaction_id");
                if (successCallback != null) {
                    InitiateBraintreePaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
