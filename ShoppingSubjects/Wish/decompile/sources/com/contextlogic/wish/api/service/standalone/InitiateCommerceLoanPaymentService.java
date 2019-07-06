package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.payments.processing.CommerceLoanPaymentProcessor.LoanType;
import org.json.JSONException;

public class InitiateCommerceLoanPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(String str, String str2, int i, LoanType loanType, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("payment/commerce-loan/complete");
        apiRequest.addParameter("client", (Object) "androidapp");
        apiRequest.addParameter("currency", (Object) str);
        apiRequest.addParameter("cart_type", i);
        apiRequest.addParameter("loan_type", loanType.getValue());
        if (str2 != null) {
            apiRequest.addParameter("checkout_offer_id", (Object) str2);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    InitiateCommerceLoanPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("transaction_id");
                if (successCallback != null) {
                    InitiateCommerceLoanPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
