package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRotatingSaleNotificationSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetRotatingPromoNotificationSpecService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishRotatingSaleNotificationSpec wishRotatingSaleNotificationSpec);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("notifications/get_rotating_sale_notification_spec"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRotatingPromoNotificationSpecService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishRotatingSaleNotificationSpec wishRotatingSaleNotificationSpec = new WishRotatingSaleNotificationSpec(apiResponse.getData());
                if (successCallback != null) {
                    GetRotatingPromoNotificationSpecService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishRotatingSaleNotificationSpec);
                        }
                    });
                } else {
                    handleFailure(apiResponse, null);
                }
            }
        });
    }
}
