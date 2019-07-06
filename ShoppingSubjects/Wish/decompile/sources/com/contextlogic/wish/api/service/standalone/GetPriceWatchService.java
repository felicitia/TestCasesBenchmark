package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPriceWatchSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetPriceWatchService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishPriceWatchSpec wishPriceWatchSpec);
    }

    public void requestService(SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        requestService(false, successCallback, defaultFailureCallback);
    }

    public void requestService(boolean z, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("price-watch/summary");
        apiRequest.addParameter("sort", z);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetPriceWatchService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishPriceWatchSpec wishPriceWatchSpec = new WishPriceWatchSpec(apiResponse.getData());
                if (successCallback != null) {
                    GetPriceWatchService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishPriceWatchSpec);
                        }
                    });
                }
            }
        });
    }
}
