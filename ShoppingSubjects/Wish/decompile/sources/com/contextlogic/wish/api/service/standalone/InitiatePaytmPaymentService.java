package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class InitiatePaytmPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i);
    }

    public interface SuccessCallback {
        void onSuccess(Map<String, String> map);
    }

    public void requestService(final SuccessCallback successCallback, final FailureCallback failureCallback) {
        startService(new ApiRequest("payment/paytm/initiate"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (failureCallback != null) {
                    InitiatePaytmPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                JSONObject data = apiResponse.getData();
                final HashMap hashMap = new HashMap();
                hashMap.put("MID", data.getString("MID"));
                hashMap.put("ORDER_ID", data.getString("ORDER_ID"));
                hashMap.put("CUST_ID", data.getString("CUST_ID"));
                hashMap.put("INDUSTRY_TYPE_ID", data.getString("INDUSTRY_TYPE_ID"));
                hashMap.put("CHANNEL_ID", data.getString("CHANNEL_ID"));
                hashMap.put("TXN_AMOUNT", data.getString("TXN_AMOUNT"));
                hashMap.put("WEBSITE", data.getString("WEBSITE"));
                hashMap.put("CALLBACK_URL", data.getString("CALLBACK_URL"));
                hashMap.put("CHECKSUMHASH", data.getString("CHECKSUMHASH"));
                hashMap.put("is_prod", data.getString("is_prod"));
                if (successCallback != null) {
                    InitiatePaytmPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(hashMap);
                        }
                    });
                }
            }
        });
    }
}
