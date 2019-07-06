package com.contextlogic.wish.activity.commercecash;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCommerceCashTerms;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetCommerceCashTermsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCommerceCashTerms wishCommerceCashTerms);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("commerce-cash-terms/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetCommerceCashTermsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCommerceCashTerms wishCommerceCashTerms = new WishCommerceCashTerms(apiResponse.getData());
                if (successCallback != null) {
                    GetCommerceCashTermsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCommerceCashTerms);
                        }
                    });
                }
            }
        });
    }
}
