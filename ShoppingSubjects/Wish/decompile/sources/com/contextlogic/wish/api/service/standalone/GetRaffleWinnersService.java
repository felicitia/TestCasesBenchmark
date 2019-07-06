package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRaffleWinnersService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishUser> arrayList, ArrayList<WishUser> arrayList2);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("daily-giveaway/get-raffle-winners");
        apiRequest.addParameter("product_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRaffleWinnersService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "winners", new DataParser<WishUser, JSONObject>() {
                        public WishUser parseData(JSONObject jSONObject) throws JSONException, ParseException {
                            return new WishUser(jSONObject);
                        }
                    });
                    final ArrayList parseArray2 = JsonUtil.parseArray(apiResponse.getData(), "discount_winners", new DataParser<WishUser, JSONObject>() {
                        public WishUser parseData(JSONObject jSONObject) throws JSONException, ParseException {
                            return new WishUser(jSONObject);
                        }
                    });
                    GetRaffleWinnersService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, parseArray2);
                        }
                    });
                }
            }
        });
    }
}
