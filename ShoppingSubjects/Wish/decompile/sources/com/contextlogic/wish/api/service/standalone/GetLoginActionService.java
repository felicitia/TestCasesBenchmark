package com.contextlogic.wish.api.service.standalone;

import android.content.ClipData.Item;
import android.content.ClipboardManager;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishLoginAction;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.util.PreferenceUtil;
import com.crashlytics.android.Crashlytics;
import java.text.ParseException;
import org.json.JSONException;

public class GetLoginActionService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishLoginAction wishLoginAction);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/get-login-action");
        if (DeepLinkManager.getInstance() != null && !DeepLinkManager.getInstance().hasAppLaunchDeepLinkSent()) {
            DeepLink lastAppLaunchDeepLinkToSend = DeepLinkManager.getInstance().getLastAppLaunchDeepLinkToSend();
            if (!(lastAppLaunchDeepLinkToSend == null || lastAppLaunchDeepLinkToSend.getUri() == null)) {
                apiRequest.addParameter("deeplink_url", (Object) lastAppLaunchDeepLinkToSend.getUri());
            }
        }
        ClipboardManager clipboardManager = (ClipboardManager) WishApplication.getInstance().getSystemService("clipboard");
        final String str = null;
        if (clipboardManager != null) {
            try {
                if (clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClip().getItemCount() > 0) {
                    Item itemAt = clipboardManager.getPrimaryClip().getItemAt(0);
                    if (itemAt.getText() != null) {
                        str = itemAt.getText().toString();
                    }
                    String string = PreferenceUtil.getString("LastClipboard", "");
                    if (str != null && !str.equals(string)) {
                        apiRequest.addParameter("clipboard", (Object) str);
                    }
                }
            } catch (NullPointerException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Clipboard internal exception: ");
                sb.append(e.toString());
                Crashlytics.logException(new Exception(sb.toString()));
            } catch (IllegalArgumentException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(e2.getMessage());
                sb2.append("\nclipboard content:");
                sb2.append(null);
                Crashlytics.logException(new IllegalArgumentException(sb2.toString()));
            }
        }
        apiRequest.addParameter("foreground_count", ForegroundWatcher.getInstance().getGlobalForegroundCount());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetLoginActionService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishLoginAction wishLoginAction = new WishLoginAction(apiResponse.getData());
                if (successCallback != null) {
                    GetLoginActionService.this.postRunnable(new Runnable() {
                        public void run() {
                            if (str != null) {
                                PreferenceUtil.setString("LastClipboard", str);
                            }
                            successCallback.onSuccess(wishLoginAction);
                        }
                    });
                }
            }
        });
    }
}
