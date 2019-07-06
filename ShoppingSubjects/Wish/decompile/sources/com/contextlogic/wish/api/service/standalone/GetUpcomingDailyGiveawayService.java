package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUpcomingDailyGiveawayInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetUpcomingDailyGiveawayService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("daily-giveaway/upcoming"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUpcomingDailyGiveawayService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final WishUpcomingDailyGiveawayInfo wishUpcomingDailyGiveawayInfo = new WishUpcomingDailyGiveawayInfo(apiResponse.getData());
                    GetUpcomingDailyGiveawayService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUpcomingDailyGiveawayInfo);
                        }
                    });
                }
            }
        });
    }
}
