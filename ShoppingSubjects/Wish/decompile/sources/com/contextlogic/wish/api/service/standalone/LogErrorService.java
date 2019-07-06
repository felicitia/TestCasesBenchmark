package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

public class LogErrorService extends SingleApiService {
    public static void logApiRequestError(String str, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("exception", exc.getMessage());
        new LogErrorService().requestService(hashMap, null, null);
    }

    private static String getStacktrace(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static void logModelParseError(Object obj, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("model", obj.getClass().getSimpleName());
        hashMap.put("exception", exc.getMessage());
        hashMap.put("stacktrace", getStacktrace(exc));
        new LogErrorService().requestService(hashMap, null, null);
    }

    public void requestService(Map<String, String> map, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/log-error");
        apiRequest.addParameter("error", (Object) new Gson().toJson((Object) map));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    LogErrorService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                if (defaultSuccessCallback != null) {
                    LogErrorService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
