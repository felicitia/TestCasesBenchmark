package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.payments.CartContext.CartType;
import org.json.JSONException;

public class InitiatePayNearMePaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(double d, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("payment/paynearme/initiate");
        apiRequest.addParameter("amount", d);
        apiRequest.addParameter("cart_type", CartType.COMMERCE_CASH.getValue());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    InitiatePayNearMePaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("buy_url");
                if (successCallback != null) {
                    InitiatePayNearMePaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
