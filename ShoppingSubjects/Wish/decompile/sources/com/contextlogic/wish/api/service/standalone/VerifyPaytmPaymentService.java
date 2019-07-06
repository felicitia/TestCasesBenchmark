package com.contextlogic.wish.api.service.standalone;

import android.os.Bundle;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.SingleApiService;
import org.json.JSONException;

public class VerifyPaytmPaymentService extends SingleApiService {

    public interface FailureCallback {
        void onFailure(String str, int i);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(Bundle bundle, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("payment/paytm/complete");
        apiRequest.addParameter("MID", bundle.get("MID"));
        apiRequest.addParameter("ORDERID", bundle.get("ORDERID"));
        apiRequest.addParameter("TXNID", bundle.get("TXNID"));
        apiRequest.addParameter("TXNAMOUNT", bundle.get("TXNAMOUNT"));
        apiRequest.addParameter("PAYMENTMODE", bundle.get("PAYMENTMODE"));
        apiRequest.addParameter("CURRENCY", bundle.get("CURRENCY"));
        apiRequest.addParameter("TXNDATE", bundle.get("TXNDATE"));
        apiRequest.addParameter("STATUS", bundle.get("STATUS"));
        apiRequest.addParameter("RESPCODE", bundle.get("RESPCODE"));
        apiRequest.addParameter("RESPMSG", bundle.get("RESPMSG"));
        apiRequest.addParameter("GATEWAYNAME", bundle.get("GATEWAYNAME"));
        apiRequest.addParameter("BANKTXNID", bundle.get("BANKTXNID"));
        apiRequest.addParameter("BANKNAME", bundle.get("BANKNAME"));
        apiRequest.addParameter("CHECKSUMHASH", bundle.get("CHECKSUMHASH"));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (failureCallback != null) {
                    failureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("transaction_id");
                if (successCallback != null) {
                    VerifyPaytmPaymentService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
