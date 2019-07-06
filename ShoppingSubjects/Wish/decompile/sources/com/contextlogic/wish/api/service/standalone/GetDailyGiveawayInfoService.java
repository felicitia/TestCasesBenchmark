package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetDailyGiveawayInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("daily-giveaway/info"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetDailyGiveawayInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final WishInfoDailyGiveawayInfo wishInfoDailyGiveawayInfo = new WishInfoDailyGiveawayInfo(apiResponse.getData());
                    GetDailyGiveawayInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishInfoDailyGiveawayInfo);
                        }
                    });
                }
            }
        });
    }
}
