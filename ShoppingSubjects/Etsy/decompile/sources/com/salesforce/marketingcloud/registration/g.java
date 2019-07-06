package com.salesforce.marketingcloud.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.Size;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.b.b;
import com.salesforce.marketingcloud.b.c;
import com.salesforce.marketingcloud.c.d;
import com.salesforce.marketingcloud.c.e;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.h;
import com.salesforce.marketingcloud.j;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public class g implements com.salesforce.marketingcloud.a.b.a, b, com.salesforce.marketingcloud.c.f.a, h, d, b {
    private static final EnumSet<com.salesforce.marketingcloud.b.a> a = EnumSet.of(com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_PACKAGE_REPLACED, new com.salesforce.marketingcloud.b.a[]{com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_TIME_ZONE_CHANGED, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED, com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_TOKEN_REFRESHED});
    private static final String b = j.a(g.class);
    private final Context c;
    private final com.salesforce.marketingcloud.b d;
    private final com.salesforce.marketingcloud.d.h e;
    private final String f;
    private final c g;
    private final com.salesforce.marketingcloud.a.b h;
    private final f i;
    private final com.salesforce.marketingcloud.messages.push.a j;
    private final com.salesforce.marketingcloud.messages.f k;
    private h l;

    /* renamed from: com.salesforce.marketingcloud.registration.g$1 reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] b = new int[C0160a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0053 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0027 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0032 */
        static {
            /*
                com.salesforce.marketingcloud.a.a$a[] r0 = com.salesforce.marketingcloud.a.a.C0160a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r0 = 1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.salesforce.marketingcloud.a.a$a r2 = com.salesforce.marketingcloud.a.a.C0160a.REGISTRATION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                com.salesforce.marketingcloud.b.a[] r1 = com.salesforce.marketingcloud.b.a.values()
                int r1 = r1.length
                int[] r1 = new int[r1]
                a = r1
                int[] r1 = a     // Catch:{ NoSuchFieldError -> 0x0027 }
                com.salesforce.marketingcloud.b.a r2 = com.salesforce.marketingcloud.b.a.BEHAVIOR_APP_PACKAGE_REPLACED     // Catch:{ NoSuchFieldError -> 0x0027 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0027 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0027 }
            L_0x0027:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0032 }
                com.salesforce.marketingcloud.b.a r1 = com.salesforce.marketingcloud.b.a.BEHAVIOR_DEVICE_TIME_ZONE_CHANGED     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003d }
                com.salesforce.marketingcloud.b.a r1 = com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0048 }
                com.salesforce.marketingcloud.b.a r1 = com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0053 }
                com.salesforce.marketingcloud.b.a r1 = com.salesforce.marketingcloud.b.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED     // Catch:{ NoSuchFieldError -> 0x0053 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0053 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0053 }
            L_0x0053:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x005e }
                com.salesforce.marketingcloud.b.a r1 = com.salesforce.marketingcloud.b.a.BEHAVIOR_SDK_TOKEN_REFRESHED     // Catch:{ NoSuchFieldError -> 0x005e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.g.AnonymousClass1.<clinit>():void");
        }
    }

    private static class a implements com.salesforce.marketingcloud.registration.d.a {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 r1) {
            this();
        }

        @NonNull
        public com.salesforce.marketingcloud.registration.d.a a(@NonNull String str) {
            return this;
        }

        public boolean a() {
            return false;
        }
    }

    public g(@NonNull Context context, @NonNull com.salesforce.marketingcloud.b bVar, @NonNull com.salesforce.marketingcloud.d.h hVar, @NonNull String str, @NonNull c cVar, @NonNull com.salesforce.marketingcloud.a.b bVar2, @NonNull f fVar, @NonNull com.salesforce.marketingcloud.messages.push.a aVar, @NonNull com.salesforce.marketingcloud.messages.f fVar2) {
        this.c = (Context) com.salesforce.marketingcloud.e.f.a(context, "Context may not be null.");
        this.d = (com.salesforce.marketingcloud.b) com.salesforce.marketingcloud.e.f.a(bVar, "Config may not be null.");
        this.e = (com.salesforce.marketingcloud.d.h) com.salesforce.marketingcloud.e.f.a(hVar, "Storage may not be null.");
        this.f = (String) com.salesforce.marketingcloud.e.f.a(str, "DeviceID must not be null or empty.");
        this.g = (c) com.salesforce.marketingcloud.e.f.a(cVar, "BehaviorManager may not be null.");
        this.h = (com.salesforce.marketingcloud.a.b) com.salesforce.marketingcloud.e.f.a(bVar2, "AlarmScheduler may not be null.");
        this.i = (f) com.salesforce.marketingcloud.e.f.a(fVar, "RequestManager may not be null.");
        this.j = (com.salesforce.marketingcloud.messages.push.a) com.salesforce.marketingcloud.e.f.a(aVar, "PushMessageManager is null.");
        this.k = (com.salesforce.marketingcloud.messages.f) com.salesforce.marketingcloud.e.f.a(fVar2, "RegionMessageManager is null.");
    }

    public static com.salesforce.marketingcloud.c.g a(@NonNull com.salesforce.marketingcloud.b bVar, @NonNull Context context, @Size(min = 1) @NonNull String str) {
        return d.REGISTRATION.a(bVar, c.s().a(bVar, context, str).a(Collections.emptyMap()).a(Collections.emptySet()).d(false).b(false).b().v()).l();
    }

    public static boolean a(@NonNull com.salesforce.marketingcloud.d.h hVar) {
        return h.a(hVar);
    }

    @Nullable
    public String a() {
        if (this.l != null) {
            return this.l.a();
        }
        return null;
    }

    public void a(int i2) {
        if (com.salesforce.marketingcloud.d.b(i2, 2)) {
            this.l = null;
            h.a(this.e, this.h, com.salesforce.marketingcloud.d.c(i2, 2));
            this.g.a((b) this);
            this.h.a(C0160a.REGISTRATION);
            this.i.a(d.REGISTRATION);
            return;
        }
        if (this.l == null) {
            a((com.salesforce.marketingcloud.InitializationStatus.a) null);
            this.l.b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(com.salesforce.marketingcloud.InitializationStatus.a aVar) {
        this.g.a((b) this, a);
        this.h.a((com.salesforce.marketingcloud.a.b.a) this, C0160a.REGISTRATION);
        this.i.a(d.REGISTRATION, (com.salesforce.marketingcloud.c.f.a) this);
        try {
            h hVar = new h(this.c, this.d, this.e, this.f, this.h, this.i, this.j, this.k);
            this.l = hVar;
        } catch (Exception e2) {
            if (aVar != null) {
                aVar.a((Throwable) e2);
            }
        }
    }

    public void a(com.salesforce.marketingcloud.InitializationStatus.a aVar, int i2) {
        if (com.salesforce.marketingcloud.d.a(i2, 2)) {
            a(aVar);
        }
    }

    public final void a(@NonNull C0160a aVar) {
        if (AnonymousClass1.b[aVar.ordinal()] == 1 && this.l != null) {
            this.l.e();
        }
    }

    public final void a(com.salesforce.marketingcloud.b.a aVar, Bundle bundle) {
        if (this.l != null) {
            switch (aVar) {
                case BEHAVIOR_APP_PACKAGE_REPLACED:
                    this.l.f();
                    break;
                case BEHAVIOR_DEVICE_TIME_ZONE_CHANGED:
                    this.l.g();
                    return;
                case BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED:
                    this.l.a(bundle.getBoolean(com.salesforce.marketingcloud.messages.push.a.a));
                    return;
                case BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED:
                    this.l.b(bundle.getBoolean("com.salesforce.marketingcloud.messaging.ENABLED"));
                    return;
                case BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED:
                    this.l.c(bundle.getBoolean("com.salesforce.marketingcloud.messaging.ENABLED"));
                    return;
                case BEHAVIOR_SDK_TOKEN_REFRESHED:
                    this.l.a(bundle.getString(com.salesforce.marketingcloud.messages.push.a.b, ""));
                    return;
                default:
                    j.b(b, "Unhandled behavior: %s", aVar);
                    return;
            }
        }
    }

    public void a(e eVar, com.salesforce.marketingcloud.c.g gVar) {
        if (this.l != null) {
            if (gVar.h()) {
                try {
                    this.l.a(c.a(new JSONObject(eVar.b())));
                } catch (Exception unused) {
                    this.l.a(-1, "Failed to convert our Response Body into a Registration.");
                }
            } else {
                this.l.a(gVar.c(), gVar.b());
            }
        }
    }

    public void a(String str, String str2, Map<String, String> map, Collection<String> collection) {
        if (this.l != null) {
            try {
                this.l.a(str, str2, map, collection);
            } catch (Exception e2) {
                j.c(b, e2, "Error encountered while saving registration", new Object[0]);
            }
        }
    }

    public void a(boolean z) {
        this.h.c(C0160a.REGISTRATION);
        this.h.a(C0160a.REGISTRATION);
        this.g.a((b) this);
    }

    @NonNull
    public final String b() {
        return "RegistrationManager";
    }

    @NonNull
    public String c() {
        return this.l != null ? this.l.c() : "";
    }

    public com.salesforce.marketingcloud.registration.d.a d() {
        return this.l != null ? this.l.a((b) this) : new a(null);
    }
}
