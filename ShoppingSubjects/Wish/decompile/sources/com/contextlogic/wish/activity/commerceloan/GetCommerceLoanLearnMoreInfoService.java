package com.contextlogic.wish.activity.commerceloan;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetCommerceLoanLearnMoreInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCommerceLoanLearnMoreInfo wishCommerceLoanLearnMoreInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("commerce-loan/learn-more"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCommerceLoanLearnMoreInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCommerceLoanLearnMoreInfo wishCommerceLoanLearnMoreInfo = new WishCommerceLoanLearnMoreInfo(apiResponse.getData());
                if (successCallback != null) {
                    GetCommerceLoanLearnMoreInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCommerceLoanLearnMoreInfo);
                        }
                    });
                }
            }
        });
    }
}
