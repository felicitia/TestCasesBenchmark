package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetSignupFreeGiftsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishSignupFreeGifts wishSignupFreeGifts);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/get-signup-gifts"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetSignupFreeGiftsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishSignupFreeGifts wishSignupFreeGifts = apiResponse.getData() != null ? new WishSignupFreeGifts(apiResponse.getData()) : null;
                if (successCallback != null) {
                    GetSignupFreeGiftsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishSignupFreeGifts);
                        }
                    });
                }
            }
        });
    }
}
