package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class EnterDailyRaffleService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2, int i);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("daily-giveaway/enter-raffle");
        apiRequest.addParameter("cid", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    EnterDailyRaffleService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final String string = apiResponse.getData().getString("popup_title");
                    final String string2 = apiResponse.getData().getString("popup_msg");
                    final int i = apiResponse.getData().getInt("raffle_ticket_num");
                    EnterDailyRaffleService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string, string2, i);
                        }
                    });
                }
            }
        });
    }
}
