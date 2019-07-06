package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import org.json.JSONException;

public class CashOutWishCashService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("partner/cash-out/wish-cash");
        apiRequest.addParameter("amount", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("success_title");
                final String string2 = apiResponse.getData().getString("success_message");
                if (successCallback != null) {
                    CashOutWishCashService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string, string2);
                        }
                    });
                }
            }
        });
    }
}
