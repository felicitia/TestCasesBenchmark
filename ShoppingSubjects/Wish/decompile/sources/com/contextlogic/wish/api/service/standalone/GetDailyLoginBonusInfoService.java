package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetDailyLoginBonusInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishDailyLoginStampSpec wishDailyLoginStampSpec);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("daily-login/stamp-summary"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetDailyLoginBonusInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishDailyLoginStampSpec wishDailyLoginStampSpec = new WishDailyLoginStampSpec(apiResponse.getData());
                if (successCallback != null) {
                    GetDailyLoginBonusInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishDailyLoginStampSpec);
                        }
                    });
                }
            }
        });
    }
}
