package com.salesforce.marketingcloud.registration;

import android.content.Context;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.stats.Filter;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.c.d;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.d.c;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.lang3.time.DateUtils;

class h implements d {
    /* access modifiers changed from: private */
    public static final String b = j.a(g.class);
    @VisibleForTesting
    final Set<String> a;
    private final Context c;
    private final com.salesforce.marketingcloud.b d;
    private final com.salesforce.marketingcloud.d.h e;
    private final String f;
    private final com.salesforce.marketingcloud.a.b g;
    private final f h;
    private final Set<com.salesforce.marketingcloud.registration.d.b> i = new ArraySet();
    private Map<String, String> j = new HashMap();
    private Set<String> k = new TreeSet();
    private boolean l;
    private boolean m;
    private boolean n;
    private String o;
    private String p;
    private String q;

    static class a implements com.salesforce.marketingcloud.registration.d.a {
        private static final List<String> a;
        private final Object b = new Object();
        @GuardedBy("lock")
        private final Map<String, String> c = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        @GuardedBy("lock")
        private final Set<String> d = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        @GuardedBy("lock")
        private String e;
        @GuardedBy("lock")
        private b f;
        @GuardedBy("lock")
        private String g;
        @GuardedBy("lock")
        private Map<String, String> h;
        @GuardedBy("lock")
        private boolean i;

        static {
            String[] strArr = {"addressId", "alias", "apId", "backgroundRefreshEnabled", "badge", Filter.FILTER_FIELD_NAME_CHANNEL, "contactId", "contactKey", "createdBy", "createdDate", "customObjectKey", "device", "deviceId", "deviceType", "gcmSenderId", "hardwareId", "isHonorDst", "lastAppOpen", "lastMessageOpen", "lastSend", "locationEnabled", "messageOpenCount", "modifiedBy", "modifiedDate", "optInDate", "optInMethodId", "optInStatusId", "optOutDate", "optOutMethodId", "optOutStatusId", ResponseConstants.PLATFORM, "platformVersion", "providerToken", "proximityEnabled", "pushAddressExtensionId", "pushApplicationId", "sdkVersion", "sendCount", "source", "sourceObjectId", "status", "systemToken", "timezone", "utcOffset", "signedString"};
            ArrayList arrayList = new ArrayList();
            for (String lowerCase : strArr) {
                arrayList.add(lowerCase.toLowerCase(Locale.ENGLISH));
            }
            a = Collections.unmodifiableList(arrayList);
        }

        a(b bVar, String str, String str2, Map<String, String> map, Set<String> set, Set<String> set2) {
            this.f = bVar;
            this.e = str;
            this.g = str2;
            this.h = new e(map);
            for (String str3 : set) {
                this.c.put(str3, str3);
            }
            this.d.addAll(set2);
        }

        @Nullable
        private String b(String str) {
            if (!TextUtils.isEmpty(str) && TextUtils.getTrimmedLength(str) != 0) {
                return str.trim();
            }
            j.d(h.b, "An empty or NULL ContactKey will not be transmitted to the Marketing Cloud and was NOT updated with the provided value.", new Object[0]);
            return null;
        }

        @NonNull
        public com.salesforce.marketingcloud.registration.d.a a(@NonNull String str) {
            if (b(str) == null) {
                return this;
            }
            synchronized (this.b) {
                this.i = true;
                this.g = str;
            }
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a() {
            /*
                r6 = this;
                java.lang.Object r0 = r6.b
                monitor-enter(r0)
                com.salesforce.marketingcloud.registration.h$b r1 = r6.f     // Catch:{ all -> 0x0022 }
                if (r1 == 0) goto L_0x001f
                boolean r1 = r6.i     // Catch:{ all -> 0x0022 }
                if (r1 == 0) goto L_0x001f
                com.salesforce.marketingcloud.registration.h$b r1 = r6.f     // Catch:{ all -> 0x0022 }
                java.lang.String r2 = r6.e     // Catch:{ all -> 0x0022 }
                java.lang.String r3 = r6.g     // Catch:{ all -> 0x0022 }
                java.util.Map<java.lang.String, java.lang.String> r4 = r6.h     // Catch:{ all -> 0x0022 }
                java.util.Map<java.lang.String, java.lang.String> r5 = r6.c     // Catch:{ all -> 0x0022 }
                java.util.Collection r5 = r5.values()     // Catch:{ all -> 0x0022 }
                r1.a(r2, r3, r4, r5)     // Catch:{ all -> 0x0022 }
                r1 = 1
                monitor-exit(r0)     // Catch:{ all -> 0x0022 }
                return r1
            L_0x001f:
                monitor-exit(r0)     // Catch:{ all -> 0x0022 }
                r0 = 0
                return r0
            L_0x0022:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0022 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.h.a.a():boolean");
        }
    }

    interface b {
        void a(String str, String str2, Map<String, String> map, Collection<String> collection);
    }

    h(@NonNull Context context, @NonNull com.salesforce.marketingcloud.b bVar, @NonNull com.salesforce.marketingcloud.d.h hVar, @NonNull String str, @NonNull com.salesforce.marketingcloud.a.b bVar2, @NonNull f fVar, @NonNull com.salesforce.marketingcloud.messages.push.a aVar, @NonNull com.salesforce.marketingcloud.messages.f fVar2) {
        this.c = context;
        this.d = bVar;
        this.e = hVar;
        this.f = str;
        this.g = bVar2;
        this.h = fVar;
        TreeSet treeSet = new TreeSet();
        treeSet.add(Rule.ALL);
        treeSet.add("Android");
        if (g.b(context)) {
            treeSet.add("DEBUG");
        }
        this.a = Collections.unmodifiableSet(treeSet);
        this.n = aVar.c();
        this.m = fVar2.a();
        this.l = fVar2.c();
        this.p = aVar.a();
        try {
            c a2 = hVar.l().a(hVar.a());
            if (a2 == null) {
                c d2 = hVar.d();
                this.j = g.c(d2.b("et_attributes_cache", ""));
                this.o = d2.b("et_subscriber_cache", null);
                Set<String> d3 = g.d(d2.b("et_tags_cache", ""));
                if (d3.isEmpty()) {
                    d3 = new TreeSet<>(this.a);
                }
                this.k = d3;
                this.q = null;
                hVar.l().a(j(), hVar.a());
            } else {
                this.q = a2.a();
                this.o = a2.l();
                this.j = new HashMap(a2.r());
                this.k = a2.q();
                hVar.l().b(j(), hVar.a());
                i();
            }
        } catch (Exception e2) {
            j.c(b, e2, "Error trying to get, update or add a registration to local storage.", new Object[0]);
            this.k = new TreeSet(this.a);
            this.j = new HashMap();
            this.p = null;
            this.o = null;
            this.q = null;
            hVar.l().a(j(), hVar.a());
        }
        bVar2.b(C0160a.REGISTRATION);
    }

    static void a(com.salesforce.marketingcloud.d.h hVar, com.salesforce.marketingcloud.a.b bVar, boolean z) {
        if (z) {
            hVar.l().b();
        }
        bVar.c(C0160a.REGISTRATION);
    }

    static boolean a(@NonNull com.salesforce.marketingcloud.d.h hVar) {
        boolean z = false;
        try {
            c a2 = hVar.l().a(hVar.a());
            if (a2 != null && a(a2, hVar)) {
                z = true;
            }
            return z;
        } catch (Exception e2) {
            j.c(b, e2, "Failed to get Registration from local storage, we do not have a push token from Google or we can not determine if this Registration contains any changes.", new Object[0]);
            return false;
        }
    }

    private boolean a(@NonNull com.salesforce.marketingcloud.d.h hVar, @IntRange(from = 0) long j2) {
        return j2 < hVar.e().getLong("_sfmc_last_registration_request_timestamp", 0) + DateUtils.MILLIS_PER_MINUTE;
    }

    private static boolean a(c cVar, @NonNull com.salesforce.marketingcloud.d.h hVar) {
        boolean z = false;
        if (cVar == null || !b(cVar)) {
            return false;
        }
        String string = hVar.e().getString("previousRegistrationHash", null);
        if (string == null || !g.f(cVar.u().toString()).equals(string)) {
            z = true;
        }
        return z;
    }

    private static boolean b(c cVar) {
        return !TextUtils.isEmpty(cVar.c());
    }

    private void i() {
        if (!this.k.containsAll(this.a)) {
            this.k.addAll(this.a);
            b();
        }
    }

    private c j() {
        return c.s().a(this.q).g(this.o).a(this.j).a(this.k).b(this.f).d(this.n).b(this.l || this.m).c(this.m).c(this.p).a(this.d, this.c, this.f).b();
    }

    /* access modifiers changed from: 0000 */
    public com.salesforce.marketingcloud.registration.d.a a(b bVar) {
        a aVar = new a(bVar, this.q, this.o, this.j, this.k, this.a);
        return aVar;
    }

    @Nullable
    public String a() {
        return this.o;
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, String str) {
        j.b(b, "%s: %s", Integer.valueOf(i2), str);
        a(System.currentTimeMillis());
        this.g.b(C0160a.REGISTRATION);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(long j2) {
        this.e.e().edit().putLong("_sfmc_last_registration_request_timestamp", j2).apply();
    }

    /* access modifiers changed from: 0000 */
    public void a(@NonNull c cVar) {
        this.g.d(C0160a.REGISTRATION);
        a(System.currentTimeMillis());
        synchronized (this.i) {
            for (com.salesforce.marketingcloud.registration.d.b bVar : this.i) {
                if (bVar != null) {
                    try {
                        bVar.a(cVar);
                    } catch (Exception e2) {
                        j.c(b, e2, "%s threw an exception while processing the registration response", bVar.getClass().getName());
                    }
                }
            }
        }
        String jSONObject = cVar.u().toString();
        this.e.d().a("mc_last_sent_registration", jSONObject);
        this.e.e().edit().putLong("lastRegistrationSendTimestamp", System.currentTimeMillis()).putString("previousRegistrationHash", g.f(jSONObject)).apply();
        this.e.l().a();
    }

    /* access modifiers changed from: 0000 */
    public void a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.p)) {
            this.p = str;
            b();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(String str, String str2, Map<String, String> map, Collection<String> collection) {
        this.q = str;
        this.o = str2;
        this.j.clear();
        this.j.putAll(map);
        this.k.clear();
        this.k.addAll(collection);
        b();
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.n = z;
        b();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void b() {
        try {
            this.e.l().a(j(), this.e.a());
            if (a(this.e, System.currentTimeMillis())) {
                this.g.d(C0160a.REGISTRATION);
                this.g.b(C0160a.REGISTRATION);
                return;
            }
            this.g.c(C0160a.REGISTRATION);
            e();
        } catch (Exception e2) {
            j.c(b, e2, "An error occurred trying to save our Registration.", new Object[0]);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(boolean z) {
        this.l = z;
        b();
    }

    @NonNull
    public String c() {
        return this.f;
    }

    /* access modifiers changed from: 0000 */
    public void c(boolean z) {
        this.m = z;
        b();
    }

    @NonNull
    public com.salesforce.marketingcloud.registration.d.a d() {
        j.b(b, "Changes with this editor will not be saved.", new Object[0]);
        a aVar = new a(null, this.q, this.o, this.j, this.k, this.a);
        return aVar;
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        try {
            c a2 = this.e.l().a(this.e.a());
            if (a2 != null && a(a2, this.e)) {
                this.h.a(d.REGISTRATION.a(this.d, a2.v()));
            }
        } catch (Exception e2) {
            j.c(b, e2, "Failed to get our Registration from local storage.", new Object[0]);
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        this.g.c(C0160a.REGISTRATION);
        b();
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        b();
    }
}
