package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishGenderedSignupFreeGifts;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetSignupFreeGiftsService.SuccessCallback;
import java.text.ParseException;
import org.json.JSONException;

public class GetSignupGenderedFreeGiftsService extends GetSignupFreeGiftsService {
    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/get-gendered-signup-gifts"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetSignupGenderedFreeGiftsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishGenderedSignupFreeGifts wishGenderedSignupFreeGifts = apiResponse.getData() != null ? new WishGenderedSignupFreeGifts(apiResponse.getData()) : null;
                if (successCallback != null) {
                    GetSignupGenderedFreeGiftsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishGenderedSignupFreeGifts);
                        }
                    });
                }
            }
        });
    }
}
