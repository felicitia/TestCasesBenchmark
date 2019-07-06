package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.tasks.Task;

@Keep
interface IRpc {
    @Keep
    Task<Void> ackMessage(String str);

    @Keep
    Task<String> buildChannel(String str);

    @Keep
    Task<Void> deleteInstanceId(String str);

    @Keep
    Task<Void> deleteToken(String str, String str2, String str3);

    @Keep
    Task<String> getToken(String str, String str2, String str3);

    @Keep
    Task<Void> subscribeToTopic(String str, String str2, String str3);

    @Keep
    Task<Void> unsubscribeFromTopic(String str, String str2, String str3);
}
