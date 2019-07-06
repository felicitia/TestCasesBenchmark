package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPriceWatchChangeInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;

public class AddToPriceWatchService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishPriceWatchChangeInfo wishPriceWatchChangeInfo, boolean z);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("price-watch/add");
        apiRequest.addParameter("contest_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    AddToPriceWatchService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishPriceWatchChangeInfo wishPriceWatchChangeInfo = JsonUtil.hasValue(apiResponse.getData(), "price_watch_text") ? new WishPriceWatchChangeInfo(apiResponse.getData().getJSONObject("price_watch_text")) : null;
                final boolean optBoolean = apiResponse.getData().optBoolean("limit_reached", false);
                if (successCallback != null) {
                    AddToPriceWatchService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishPriceWatchChangeInfo, optBoolean);
                        }
                    });
                }
            }
        });
    }
}
