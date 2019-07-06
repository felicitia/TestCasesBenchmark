package com.contextlogic.wish.notifications.push;

import android.util.Log;
import com.contextlogic.wish.api.service.standalone.PushRegistrationService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class PushInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "com.contextlogic.wish.notifications.push.PushInstanceIDService";

    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Refreshed token: ");
        sb.append(token);
        Log.d(str, sb.toString());
        PushRegistrationService.registerPushNotificationToken();
    }
}
