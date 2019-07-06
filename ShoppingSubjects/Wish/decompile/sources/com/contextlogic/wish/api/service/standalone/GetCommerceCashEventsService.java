package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCommerceCashHistory;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetCommerceCashEventsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCommerceCashHistory wishCommerceCashHistory);
    }

    public void requestService(int i, int i2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("commerce-cash-events/get");
        apiRequest.addParameter("offset", i);
        apiRequest.addParameter("count", i2);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCommerceCashEventsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCommerceCashHistory wishCommerceCashHistory = new WishCommerceCashHistory(apiResponse.getData());
                if (successCallback != null) {
                    GetCommerceCashEventsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCommerceCashHistory);
                        }
                    });
                }
            }
        });
    }
}
