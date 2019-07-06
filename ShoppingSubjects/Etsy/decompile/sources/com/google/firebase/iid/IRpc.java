package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.f;

@Keep
@KeepForSdk
interface IRpc {
    @Keep
    @KeepForSdk
    f<Void> ackMessage(String str);

    @Keep
    @KeepForSdk
    f<String> buildChannel(String str);

    @Keep
    @KeepForSdk
    f<Void> deleteInstanceId(String str);

    @Keep
    @KeepForSdk
    f<Void> deleteToken(String str, String str2, String str3);

    @Keep
    @KeepForSdk
    f<String> getToken(String str, String str2, String str3);

    @Keep
    @KeepForSdk
    f<Void> subscribeToTopic(String str, String str2, String str3);

    @Keep
    @KeepForSdk
    f<Void> unsubscribeFromTopic(String str, String str2, String str3);
}
