package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.BillingDetailsResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class DeleteCreditCardService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(BillingDetailsResponse billingDetailsResponse);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("credit-card/delete");
        apiRequest.addParameter("card_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    DeleteCreditCardService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final BillingDetailsResponse billingDetailsResponse = new BillingDetailsResponse(apiResponse.getData());
                    DeleteCreditCardService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(billingDetailsResponse);
                        }
                    });
                }
            }
        });
    }
}
