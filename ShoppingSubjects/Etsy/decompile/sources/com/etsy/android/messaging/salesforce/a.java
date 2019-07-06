package com.etsy.android.messaging.salesforce;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import com.etsy.android.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.sharedprefs.b;
import com.etsy.android.messaging.g;
import com.google.android.gms.common.ConnectionResult;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* compiled from: PushNotificationPopulator.kt */
public final class a {
    public static final C0083a a = new C0083a(null);
    private final Context b;
    private final com.etsy.android.lib.util.b.a c;
    private final b d;

    /* renamed from: com.etsy.android.messaging.salesforce.a$a reason: collision with other inner class name */
    /* compiled from: PushNotificationPopulator.kt */
    public static final class C0083a {
        private C0083a() {
        }

        public /* synthetic */ C0083a(o oVar) {
            this();
        }
    }

    public a(Context context, com.etsy.android.lib.util.b.a aVar, b bVar) {
        p.b(context, ResponseConstants.CONTEXT);
        p.b(aVar, "fileSupport");
        p.b(bVar, "sharedPreferencesProvider");
        this.b = context;
        this.c = aVar;
        this.d = bVar;
    }

    public final PendingIntent a(Bundle bundle, Context context) {
        p.b(context, ResponseConstants.CONTEXT);
        PendingIntent activity = PendingIntent.getActivity(context, 0, new g(context).a((bundle == null || !bundle.containsKey(ResponseConstants.DEEP_LINK)) ? null : Uri.parse(bundle.getString(ResponseConstants.DEEP_LINK)), bundle), 134217728);
        p.a((Object) activity, "PendingIntent.getActivit…tent.FLAG_UPDATE_CURRENT)");
        return activity;
    }

    public final void a(Builder builder) {
        p.b(builder, "builder");
        if (this.d.a("notification_sound", true)) {
            String a2 = this.d.a("notification_ringtone", (String) null);
            if (a2 == null || m.a(a2, "android.resource://com.etsy.android", false, 2, null)) {
                a2 = this.c.a(this.b, R.raw.notification).toString();
            }
            builder.setSound(Uri.parse(a2));
        }
        if (this.d.a("notification_vibrate", true)) {
            builder.setVibrate(com.etsy.android.lib.messaging.b.a);
        }
        if (this.d.a("notification_led", true)) {
            builder.setLights(ContextCompat.getColor(this.b, R.color.sk_orange_20), ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, 1000);
        }
        builder.setSmallIcon(R.drawable.ic_stat_ic_notification);
        builder.setLargeIcon(null);
    }
}
