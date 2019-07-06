package com.salesforce.marketingcloud.analytics.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.e;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@RestrictTo({Scope.LIBRARY})
public class a extends l {
    private static final String a = j.a(a.class);
    private final h b;

    public a(h hVar) {
        this.b = (h) f.a(hVar, "MCStorage may not be null");
    }

    public static void a(h hVar, boolean z) {
        if (z) {
            hVar.g().c(0);
        }
    }

    public void a(@NonNull com.salesforce.marketingcloud.messages.c.a aVar) {
        try {
            this.b.g().a(e.a(new Date(), 0, 14, Collections.singletonList(aVar.j()), aVar.a(), true), this.b.a());
        } catch (Exception unused) {
            j.e(a, "Failed to record message downloaded analytic for message: %s", aVar.j());
        }
    }

    public void a(@NonNull NotificationMessage notificationMessage) {
        try {
            if (!TextUtils.isEmpty(notificationMessage.id()) && !TextUtils.isEmpty(notificationMessage.regionId())) {
                this.b.g().a(e.a(new Date(), 0, 3, Arrays.asList(new String[]{notificationMessage.id(), notificationMessage.regionId()}), true), this.b.a());
            }
        } catch (Exception e) {
            j.c(a, e, "Failed to record EtAnalyticItem for region message displayed.", new Object[0]);
        }
    }

    public void a(boolean z) {
        if (z) {
            this.b.g().c(0);
        }
    }
}
