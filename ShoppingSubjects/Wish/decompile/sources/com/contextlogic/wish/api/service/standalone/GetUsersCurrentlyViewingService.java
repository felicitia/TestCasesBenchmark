package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUsersCurrentlyViewing;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetUsersCurrentlyViewingService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUsersCurrentlyViewing wishUsersCurrentlyViewing);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("product/current-users/get");
        apiRequest.addParameter("cid", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUsersCurrentlyViewingService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final WishUsersCurrentlyViewing wishUsersCurrentlyViewing = new WishUsersCurrentlyViewing(apiResponse.getData());
                    GetUsersCurrentlyViewingService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUsersCurrentlyViewing);
                        }
                    });
                }
            }
        });
    }
}
