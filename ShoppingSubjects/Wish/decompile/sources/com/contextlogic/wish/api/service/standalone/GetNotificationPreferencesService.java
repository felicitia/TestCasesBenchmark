package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishNotificationPreference;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetNotificationPreferencesService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishNotificationPreference> arrayList);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("mobile/grouped-preferences"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetNotificationPreferencesService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "preferences", new DataParser<WishNotificationPreference, JSONObject>() {
                    public WishNotificationPreference parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishNotificationPreference(jSONObject);
                    }
                });
                if (successCallback != null) {
                    GetNotificationPreferencesService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray);
                        }
                    });
                }
            }
        });
    }
}
