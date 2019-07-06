package com.salesforce.marketingcloud.messages.c;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.a.b.a;
import com.salesforce.marketingcloud.b.b;
import com.salesforce.marketingcloud.c.d;
import com.salesforce.marketingcloud.c.e;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.c.g;
import com.salesforce.marketingcloud.h;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.EnumSet;

public class c implements a, b, f.a, h, b {
    private static final String a = j.a(c.class);
    private final com.salesforce.marketingcloud.b b;
    private final com.salesforce.marketingcloud.d.h c;
    private final String d;
    private final com.salesforce.marketingcloud.b.c e;
    private final com.salesforce.marketingcloud.a.b f;
    private final f g;
    private final com.salesforce.marketingcloud.analytics.j h;
    private d i;

    public c(com.salesforce.marketingcloud.b bVar, com.salesforce.marketingcloud.d.h hVar, String str, com.salesforce.marketingcloud.b.c cVar, com.salesforce.marketingcloud.a.b bVar2, f fVar, com.salesforce.marketingcloud.analytics.j jVar) {
        this.b = (com.salesforce.marketingcloud.b) com.salesforce.marketingcloud.e.f.a(bVar, "MarketingCloudConfig is null.");
        this.c = (com.salesforce.marketingcloud.d.h) com.salesforce.marketingcloud.e.f.a(hVar, "Storage is null.");
        this.d = (String) com.salesforce.marketingcloud.e.f.a(str, "You must provide the Device ID.");
        this.e = (com.salesforce.marketingcloud.b.c) com.salesforce.marketingcloud.e.f.a(cVar, "BehaviorManager is null.");
        this.f = (com.salesforce.marketingcloud.a.b) com.salesforce.marketingcloud.e.f.a(bVar2, "AlarmScheduler is null.");
        this.g = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager is null.");
        this.h = (com.salesforce.marketingcloud.analytics.j) com.salesforce.marketingcloud.e.f.a(jVar, "InboxAnalyticEventListener is null.");
    }

    public static boolean a(@NonNull Bundle bundle) {
        return String.valueOf(8).equals(bundle.getString("_mt"));
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        d dVar = new d(this.b, this.c, this.d, this.f, this.g, this.h);
        this.i = dVar;
        this.g.a(d.INBOX_MESSAGE, (f.a) this);
        this.g.a(d.INBOX_STATUS, (f.a) this);
        this.f.a((a) this, C0160a.FETCH_INBOX_MESSAGES, C0160a.UPDATE_INBOX_MESSAGE_STATUS);
        this.e.a((b) this, EnumSet.of(com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_PUSH_RECEIVED, com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_BACKGROUNDED, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_NOTIFICATION_OPENED));
    }

    public void a(int i2) {
        if (com.salesforce.marketingcloud.d.b(i2, 128)) {
            this.i = null;
            d.a(this.c, this.f, com.salesforce.marketingcloud.d.c(i2, 128));
            this.e.a((b) this);
            this.f.a(C0160a.FETCH_INBOX_MESSAGES, C0160a.UPDATE_INBOX_MESSAGE_STATUS);
            this.g.a(d.INBOX_MESSAGE);
            this.g.a(d.INBOX_STATUS);
            return;
        }
        if (this.i == null && this.b.i()) {
            a();
        }
    }

    public void a(InitializationStatus.a aVar, int i2) {
        if (com.salesforce.marketingcloud.d.a(i2, 128) && this.b.i()) {
            a();
        }
    }

    public void a(@NonNull C0160a aVar) {
        if (this.i != null) {
            switch (aVar) {
                case FETCH_INBOX_MESSAGES:
                    this.i.c();
                    break;
                case UPDATE_INBOX_MESSAGE_STATUS:
                    this.i.d();
                    return;
                default:
                    return;
            }
        }
    }

    public void a(com.salesforce.marketingcloud.b.a aVar, @NonNull Bundle bundle) {
        if (this.i != null) {
            switch (aVar) {
                case BEHAVIOR_APP_FOREGROUNDED:
                    this.i.a();
                    break;
                case BEHAVIOR_APP_BACKGROUNDED:
                    this.i.b();
                    return;
                case BEHAVIOR_SDK_PUSH_RECEIVED:
                    if (a(bundle)) {
                        try {
                            this.i.a((a) com.salesforce.marketingcloud.messages.a.b.a(bundle));
                            return;
                        } catch (Exception e2) {
                            j.c(a, e2, "Failed to seed inbox_messages table with message: %s.", bundle.getString(NotificationMessage.a));
                            return;
                        }
                    }
                    break;
                case BEHAVIOR_SDK_NOTIFICATION_OPENED:
                    NotificationMessage notificationMessage = (NotificationMessage) bundle.get("com.salesforce.marketingcloud.notifications.EXTRA_MESSAGE");
                    if (notificationMessage != null) {
                        this.i.a(notificationMessage);
                        return;
                    }
                    break;
                default:
                    return;
            }
        }
    }

    public void a(e eVar, g gVar) {
        if (this.i != null) {
            if (d.INBOX_MESSAGE == eVar.h()) {
                if (gVar.h()) {
                    this.i.a(gVar);
                } else {
                    this.i.a(gVar.c(), gVar.b());
                }
            } else if (d.INBOX_STATUS == eVar.h()) {
                if (gVar.h()) {
                    this.i.a(eVar);
                    return;
                }
                this.i.b(gVar.c(), gVar.b());
            }
        }
    }

    public void a(boolean z) {
        if (this.f != null) {
            this.f.a(C0160a.FETCH_INBOX_MESSAGES, C0160a.UPDATE_INBOX_MESSAGE_STATUS);
        }
        if (this.e != null) {
            this.e.a((b) this);
        }
    }

    @NonNull
    public final String b() {
        return "InboxMessageManager";
    }
}
