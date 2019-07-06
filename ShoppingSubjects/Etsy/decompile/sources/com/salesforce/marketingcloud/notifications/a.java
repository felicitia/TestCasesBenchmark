package com.salesforce.marketingcloud.notifications;

import android.os.Bundle;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Sound;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Trigger;
import com.salesforce.marketingcloud.notifications.NotificationMessage.Type;
import java.util.Map;

abstract class a extends C$$AutoValue_NotificationMessage {
    a(String str, String str2, int i, String str3, Sound sound, String str4, int i2, String str5, String str6, Type type, Trigger trigger, String str7, String str8, String str9, Map<String, String> map, String str10, Bundle bundle) {
        super(str, str2, i, str3, sound, str4, i2, str5, str6, type, trigger, str7, str8, str9, map, str10, bundle);
    }

    /* access modifiers changed from: 0000 */
    public final NotificationMessage a(int i) {
        b bVar = new b(id(), regionId(), i, alert(), sound(), soundName(), smallIconResId(), title(), subTitle(), type(), trigger(), url(), mediaUrl(), mediaAltText(), customKeys(), custom(), payload());
        return bVar;
    }
}
