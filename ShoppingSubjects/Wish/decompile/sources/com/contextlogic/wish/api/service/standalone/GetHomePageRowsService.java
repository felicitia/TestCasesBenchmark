package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishHomePageInfo;
import com.contextlogic.wish.api.service.MultiApiService;
import org.json.JSONException;
import org.json.JSONObject;

public class GetHomePageRowsService extends MultiApiService {

    public interface FailureCallback {
        void onServiceFailed(int i);
    }

    public interface SuccessCallback {
        void onServiceSucceeded(int i, int i2, int i3);
    }

    public void requestService(int i, long j, final int i2, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        ApiRequest apiRequest = new ApiRequest("home-page/get-rows");
        apiRequest.addParameter("row_type", (Object) Integer.toString(i));
        apiRequest.addParameter("row_id", (Object) Long.toString(j));
        apiRequest.addParameter("layout_index", (Object) Integer.toString(i2));
        startService(apiRequest, new ApiCallback() {
            public void handleFailure(ApiResponse apiResponse, String str) {
                if (failureCallback != null) {
                    GetHomePageRowsService.this.postRunnable(new Runnable() {
                        public void run() {
                            failureCallback.onServiceFailed(i2);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                try {
                    JSONObject jSONObject = apiResponse.getData().getJSONObject("home_page_info");
                    final int i = apiResponse.getData().getInt("row_type");
                    final int i2 = apiResponse.getData().getInt("row_id");
                    final int i3 = apiResponse.getData().getInt("layout_index");
                    WishHomePageInfo.getInstance().updateHomePageInfo(jSONObject);
                    if (successCallback != null) {
                        GetHomePageRowsService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onServiceSucceeded(i, i2, i3);
                            }
                        });
                    }
                } catch (JSONException unused) {
                    if (failureCallback != null) {
                        GetHomePageRowsService.this.postRunnable(new Runnable() {
                            public void run() {
                                failureCallback.onServiceFailed(i2);
                            }
                        });
                    }
                }
            }

            public String getCallIdentifier() {
                return Integer.toString(i2);
            }
        });
    }
}
