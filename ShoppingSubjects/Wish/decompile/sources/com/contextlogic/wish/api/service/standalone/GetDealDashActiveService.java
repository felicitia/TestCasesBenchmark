package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetDealDashActiveService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(boolean z);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("upsell-after-order/dealdash"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetDealDashActiveService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final boolean z = apiResponse.getData().getBoolean("can_start");
                if (successCallback != null) {
                    GetDealDashActiveService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(z);
                        }
                    });
                }
            }
        });
    }
}
