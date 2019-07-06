package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UpdateCommerceLoanBillingInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUserBillingInfo wishUserBillingInfo);
    }

    public void requestService(String str, int i, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("billing-info/commerce-loan/add-or-update");
        apiRequest.addParameter("payment_date", (Object) str);
        apiRequest.addParameter("processor_type", i);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UpdateCommerceLoanBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUserBillingInfo wishUserBillingInfo = JsonUtil.hasValue(apiResponse.getData(), "user_billing_details") ? new WishUserBillingInfo(apiResponse.getData().getJSONObject("user_billing_details")) : null;
                if (successCallback != null) {
                    UpdateCommerceLoanBillingInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUserBillingInfo);
                        }
                    });
                }
            }
        });
    }
}
