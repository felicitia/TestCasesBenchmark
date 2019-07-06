package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetNotificationsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishNotification> arrayList, int i);
    }

    public void requestService(int i, String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("notifications/get");
        apiRequest.addParameter("bucket", i);
        apiRequest.addParameter("category", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetNotificationsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "notifications", new DataParser<WishNotification, JSONObject>() {
                    public WishNotification parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishNotification(jSONObject);
                    }
                });
                final int i = apiResponse.getData().getInt("count");
                if (successCallback != null) {
                    GetNotificationsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, i);
                        }
                    });
                }
            }
        });
    }
}
