package com.salesforce.marketingcloud.analytics;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.b.a;
import com.salesforce.marketingcloud.b.b;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.d;
import com.salesforce.marketingcloud.h;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.e;
import com.salesforce.marketingcloud.messages.f.c;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.EnumSet;

@RestrictTo({Scope.LIBRARY})
public final class k implements d, j, m, b, h, c {
    private static final String a = j.a(k.class);
    private final com.salesforce.marketingcloud.b.c b;
    private final EnumSet<a> c = EnumSet.of(a.BEHAVIOR_APP_BACKGROUNDED, a.BEHAVIOR_APP_FOREGROUNDED, a.BEHAVIOR_DEVICE_SHUTDOWN, a.BEHAVIOR_DEVICE_BOOT_COMPLETE);
    private final com.salesforce.marketingcloud.d.h d;
    private final f e;
    private final String f;
    private final com.salesforce.marketingcloud.b g;
    private final com.salesforce.marketingcloud.a.b h;
    private final Context i;
    private com.salesforce.marketingcloud.analytics.a.c j;
    private com.salesforce.marketingcloud.analytics.a.b k;
    private com.salesforce.marketingcloud.analytics.a.a l;
    private com.salesforce.marketingcloud.analytics.b.j m;

    public k(@NonNull Context context, @NonNull com.salesforce.marketingcloud.b bVar, @NonNull com.salesforce.marketingcloud.d.h hVar, @NonNull String str, @NonNull com.salesforce.marketingcloud.a.b bVar2, @NonNull com.salesforce.marketingcloud.b.c cVar, @NonNull f fVar) {
        this.d = (com.salesforce.marketingcloud.d.h) com.salesforce.marketingcloud.e.f.a(hVar, "MCStorage may not be null.");
        this.b = (com.salesforce.marketingcloud.b.c) com.salesforce.marketingcloud.e.f.a(cVar, "BehaviorManager may not be null.");
        this.e = fVar;
        this.f = str;
        this.g = bVar;
        this.h = bVar2;
        this.i = context;
    }

    private void a(Bundle bundle) {
        long j2 = bundle.getLong("timestamp", System.currentTimeMillis());
        if (this.l != null) {
            this.l.a(j2);
        }
        if (this.k != null) {
            this.k.a(j2);
        }
        if (this.m != null) {
            this.m.a(j2);
        }
    }

    private void b(Bundle bundle) {
        long j2 = bundle.getLong("timestamp", 0);
        if (this.l != null) {
            this.l.b(j2);
        }
        if (this.k != null) {
            this.k.b(j2);
        }
        if (this.m != null) {
            this.m.b(j2);
        }
        if (this.j != null) {
            this.h.b(C0160a.ET_ANALYTICS);
        }
    }

    private void c(Bundle bundle) {
        long j2 = bundle.getLong("timestamp", 0);
        if (this.l != null) {
            this.l.c(j2);
        }
        if (this.k != null) {
            this.k.c(j2);
        }
        if (this.m != null) {
            this.m.c(j2);
        }
    }

    public void a(int i2) {
        if (d.b(i2, 2048)) {
            if (this.l != null) {
                this.l.a(false);
                this.l = null;
            }
            com.salesforce.marketingcloud.analytics.a.a aVar = this.l;
            com.salesforce.marketingcloud.analytics.a.a.a(this.d, d.c(i2, 2048));
        } else {
            this.l = new com.salesforce.marketingcloud.analytics.a.a(this.d);
        }
        if (d.b(i2, 256)) {
            if (this.k != null) {
                this.k.a(false);
                this.k = null;
            }
            com.salesforce.marketingcloud.analytics.a.b.a(this.d, d.c(i2, 256));
        } else if (this.k == null && this.g.e()) {
            this.k = new com.salesforce.marketingcloud.analytics.a.b(this.d);
        }
        if (d.b(i2, 512)) {
            if (this.m != null) {
                this.m.a(false);
                this.m = null;
            }
            com.salesforce.marketingcloud.analytics.b.j.a(this.d, this.h, this.e, d.c(i2, 512));
        } else if (this.m == null && this.g.f()) {
            com.salesforce.marketingcloud.analytics.b.j jVar = new com.salesforce.marketingcloud.analytics.b.j(this.i, this.g, this.d, this.h, this.e);
            this.m = jVar;
        }
        if (this.l == null && this.k == null) {
            this.h.c(C0160a.ET_ANALYTICS);
            if (this.j != null) {
                this.j.a();
                this.j = null;
            }
        } else if (this.j == null) {
            com.salesforce.marketingcloud.analytics.a.c cVar = new com.salesforce.marketingcloud.analytics.a.c(this.g, this.f, this.d, this.e, this.h);
            this.j = cVar;
        }
    }

    public void a(int i2, @NonNull e eVar) {
        switch (i2) {
            case 1:
                if (this.l != null) {
                    this.l.a(eVar);
                }
                if (this.k != null) {
                    this.k.a(eVar);
                }
                if (this.m != null) {
                    this.m.a(eVar);
                    break;
                }
                break;
            case 2:
                if (this.l != null) {
                    this.l.b(eVar);
                }
                if (this.k != null) {
                    this.k.b(eVar);
                }
                if (this.m != null) {
                    this.m.b(eVar);
                    return;
                }
                break;
            default:
                return;
        }
    }

    public void a(InitializationStatus.a aVar, int i2) {
        if (d.a(i2, 2048)) {
            this.l = new com.salesforce.marketingcloud.analytics.a.a(this.d);
        }
        if (d.a(i2, 256) && this.g.e()) {
            this.k = new com.salesforce.marketingcloud.analytics.a.b(this.d);
        }
        if (d.a(i2, 512) && this.g.f()) {
            com.salesforce.marketingcloud.analytics.b.j jVar = new com.salesforce.marketingcloud.analytics.b.j(this.i, this.g, this.d, this.h, this.e);
            this.m = jVar;
        }
        if (!(this.l == null && this.k == null)) {
            com.salesforce.marketingcloud.analytics.a.c cVar = new com.salesforce.marketingcloud.analytics.a.c(this.g, this.f, this.d, this.e, this.h);
            this.j = cVar;
        }
        this.b.a((b) this, this.c);
    }

    public void a(a aVar, Bundle bundle) {
        switch (aVar) {
            case BEHAVIOR_APP_BACKGROUNDED:
                b(bundle);
                return;
            case BEHAVIOR_APP_FOREGROUNDED:
                a(bundle);
                return;
            case BEHAVIOR_DEVICE_SHUTDOWN:
            case BEHAVIOR_DEVICE_BOOT_COMPLETE:
                c(bundle);
                return;
            default:
                return;
        }
    }

    public void a(@NonNull com.salesforce.marketingcloud.messages.c.a aVar) {
        if (aVar == null) {
            j.d(a, "CloudPageMessage is null.  Call to onMessageDownloaded() ignored.", new Object[0]);
            return;
        }
        if (this.l != null) {
            this.l.a(aVar);
        }
        if (this.k != null) {
            this.k.a(aVar);
        }
        if (this.m != null) {
            this.m.a(aVar);
        }
    }

    public void a(@NonNull NotificationMessage notificationMessage) {
        if (this.l != null) {
            this.l.a(notificationMessage);
        }
        if (this.k != null) {
            this.k.a(notificationMessage);
        }
        if (this.m != null) {
            this.m.a(notificationMessage);
        }
    }

    public void a(boolean z) {
        this.b.a((b) this);
        if (this.l != null) {
            this.l.a(z);
            this.l = null;
        }
        if (this.k != null) {
            this.k.a(z);
            this.k = null;
        }
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
        if (this.m != null) {
            this.m.a(z);
            this.m = null;
        }
    }

    public String b() {
        return "AnalyticsManager";
    }

    public void b(@NonNull NotificationMessage notificationMessage) {
        if (this.l != null) {
            this.l.b(notificationMessage);
        }
        if (this.k != null) {
            this.k.b(notificationMessage);
        }
        if (this.m != null) {
            this.m.b(notificationMessage);
        }
    }
}
