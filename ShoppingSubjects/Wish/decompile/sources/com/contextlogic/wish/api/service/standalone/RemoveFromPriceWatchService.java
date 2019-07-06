package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPriceWatchChangeInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class RemoveFromPriceWatchService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishPriceWatchChangeInfo wishPriceWatchChangeInfo);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("price-watch/remove");
        apiRequest.addParameter("contest_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    RemoveFromPriceWatchService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishPriceWatchChangeInfo wishPriceWatchChangeInfo = new WishPriceWatchChangeInfo(apiResponse.getData().getJSONObject("price_watch_text"));
                if (successCallback != null) {
                    RemoveFromPriceWatchService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishPriceWatchChangeInfo);
                        }
                    });
                }
            }
        });
    }
}
