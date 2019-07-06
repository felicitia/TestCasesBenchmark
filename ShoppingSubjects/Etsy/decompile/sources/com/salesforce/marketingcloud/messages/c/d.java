package com.salesforce.marketingcloud.messages.c;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.a.a.C0160a;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.c.e;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.e.g;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c.b.C0170b;
import com.salesforce.marketingcloud.messages.c.b.a;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class d implements b {
    private static final String a = j.a(d.class);
    private final Set<C0170b> b = new ArraySet();
    private final b c;
    private final h d;
    private final String e;
    private final com.salesforce.marketingcloud.a.b f;
    private final f g;
    private final com.salesforce.marketingcloud.analytics.j h;
    private a i;
    private boolean j = false;

    d(b bVar, h hVar, String str, com.salesforce.marketingcloud.a.b bVar2, f fVar, com.salesforce.marketingcloud.analytics.j jVar) {
        this.c = bVar;
        this.d = hVar;
        this.e = str;
        this.f = bVar2;
        this.g = fVar;
        this.h = jVar;
    }

    private JSONArray a(Map<String, Integer> map) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceId", this.e);
            String a2 = g.a(new Date());
            for (Entry entry : map.entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    int intValue = ((Integer) entry.getValue()).intValue();
                    jSONObject2.put("messageId", entry.getKey());
                    String str = ResponseConstants.ACTION;
                    String str2 = intValue == 2 ? "Deleted" : intValue == 1 ? "Viewed" : "Unread";
                    jSONObject2.put(str, str2);
                    jSONObject2.put("actionDate", a2);
                    jSONObject2.put("actionParameters", jSONObject);
                    jSONArray.put(jSONObject2);
                } catch (JSONException e2) {
                    j.c(a, e2, "Failed to add message %s to InboxMessageStatusUpdate payload.", entry);
                }
            }
        } catch (JSONException e3) {
            j.c(a, e3, "DeviceID failed to convert to JSON and is required by this REST call.", new Object[0]);
        }
        return jSONArray;
    }

    static void a(h hVar, com.salesforce.marketingcloud.a.b bVar, boolean z) {
        bVar.c(C0160a.FETCH_INBOX_MESSAGES, C0160a.UPDATE_INBOX_MESSAGE_STATUS);
        if (z) {
            hVar.m().a();
            hVar.n().b();
        }
    }

    private void a(boolean z) {
        if (this.i != null) {
            try {
                this.i.a(z);
            } catch (Exception e2) {
                j.c(a, e2, "InboxRefreshListener threw an exception", new Object[0]);
            }
            this.i = null;
        }
    }

    private static boolean c(a aVar) {
        return aVar.m() == 1 && aVar.n() == 2;
    }

    private void d(@NonNull a aVar) {
        try {
            if (this.d.m().b(aVar, this.d.a()) > 0 && !c(aVar)) {
                this.d.n().a(aVar);
            }
        } catch (Exception unused) {
            j.d(a, "Failed to update local storage for message: %s", aVar.j());
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.j = true;
        c();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(int i2, String str) {
        j.c(a, "Request failed: %d - %s", Integer.valueOf(i2), str);
        a(false);
        this.f.b(C0160a.FETCH_INBOX_MESSAGES);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void a(long j2) {
        this.d.e().edit().putLong("_sfmc_last_inbox_message_refresh_request_timestamp", j2).apply();
    }

    /* access modifiers changed from: 0000 */
    public void a(e eVar) {
        if (eVar.j() != null) {
            String[] split = eVar.j().split("\\s*,\\s*");
            this.f.d(C0160a.UPDATE_INBOX_MESSAGE_STATUS);
            this.d.n().a(split);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(com.salesforce.marketingcloud.c.g gVar) {
        try {
            JSONArray optJSONArray = new JSONObject(gVar.a()).optJSONArray(ResponseConstants.MESSAGES);
            List emptyList = Collections.emptyList();
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                if (length > 0) {
                    emptyList = new ArrayList(length);
                    for (int i2 = 0; i2 < length; i2++) {
                        try {
                            emptyList.add(com.salesforce.marketingcloud.messages.a.b.a(optJSONArray.getJSONObject(i2)));
                        } catch (Exception e2) {
                            j.c(a, e2, "Failed to parse inbox message", new Object[0]);
                        }
                    }
                }
            }
            a(emptyList);
            a(true);
        } catch (Exception e3) {
            j.c(a, e3, "Failed to parse inbox messages response", new Object[0]);
            a(-1, "Failed to parse response");
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar) {
        this.d.m().a(aVar, this.d.a());
        if (this.j) {
            c();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(NotificationMessage notificationMessage) {
        a a2 = this.d.m().a(notificationMessage.id(), this.d.a());
        if (a2 != null) {
            b(a2);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d5  */
    @android.support.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@android.support.annotation.NonNull java.util.List<com.salesforce.marketingcloud.messages.c.a> r10) {
        /*
            r9 = this;
            com.salesforce.marketingcloud.a.b r0 = r9.f
            r1 = 1
            com.salesforce.marketingcloud.a.a$a[] r2 = new com.salesforce.marketingcloud.a.a.C0160a[r1]
            com.salesforce.marketingcloud.a.a$a r3 = com.salesforce.marketingcloud.a.a.C0160a.FETCH_INBOX_MESSAGES
            r4 = 0
            r2[r4] = r3
            r0.d(r2)
            com.salesforce.marketingcloud.d.h r0 = r9.d
            com.salesforce.marketingcloud.d.f r0 = r0.m()
            com.salesforce.marketingcloud.d.h r2 = r9.d
            com.salesforce.marketingcloud.e.a r2 = r2.a()
            java.util.List r2 = r0.a(r2)
            java.util.Iterator r2 = r2.iterator()
        L_0x0021:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x006e
            java.lang.Object r3 = r2.next()
            com.salesforce.marketingcloud.messages.c.a r3 = (com.salesforce.marketingcloud.messages.c.a) r3
            java.util.Iterator r5 = r10.iterator()
        L_0x0031:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x004d
            java.lang.Object r6 = r5.next()
            com.salesforce.marketingcloud.messages.c.a r6 = (com.salesforce.marketingcloud.messages.c.a) r6
            java.lang.String r7 = r3.j()
            java.lang.String r6 = r6.j()
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0031
            r5 = r1
            goto L_0x004e
        L_0x004d:
            r5 = r4
        L_0x004e:
            if (r5 != 0) goto L_0x0021
            r3.b(r1)
            com.salesforce.marketingcloud.d.h r5 = r9.d     // Catch:{ Exception -> 0x005d }
            com.salesforce.marketingcloud.e.a r5 = r5.a()     // Catch:{ Exception -> 0x005d }
            r0.a(r3, r5)     // Catch:{ Exception -> 0x005d }
            goto L_0x0021
        L_0x005d:
            r5 = move-exception
            java.lang.String r6 = a
            java.lang.String r7 = "CloudPageMessage %s could not be marked as deleted."
            java.lang.Object[] r8 = new java.lang.Object[r1]
            java.lang.String r3 = r3.j()
            r8[r4] = r3
            com.salesforce.marketingcloud.j.c(r6, r5, r7, r8)
            goto L_0x0021
        L_0x006e:
            boolean r2 = r10.isEmpty()
            if (r2 != 0) goto L_0x00f5
            java.util.Iterator r2 = r10.iterator()
        L_0x0078:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00f5
            java.lang.Object r3 = r2.next()
            com.salesforce.marketingcloud.messages.c.a r3 = (com.salesforce.marketingcloud.messages.c.a) r3
            java.lang.String r5 = r3.j()
            com.salesforce.marketingcloud.d.h r6 = r9.d
            com.salesforce.marketingcloud.e.a r6 = r6.a()
            com.salesforce.marketingcloud.messages.c.a r5 = r0.a(r5, r6)
            if (r5 == 0) goto L_0x00d2
            java.lang.String r6 = r5.b()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x00ad
            boolean r6 = r5.r()
            r3.a(r6)
            boolean r5 = r5.s()
            r3.b(r5)
            goto L_0x00d2
        L_0x00ad:
            java.lang.String r6 = r5.b()
            java.lang.String r7 = r3.b()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00d2
            boolean r6 = r5.r()
            r3.a(r6)
            boolean r6 = r5.s()
            r3.b(r6)
            java.util.Date r5 = r5.k()
            if (r5 != 0) goto L_0x00d0
            goto L_0x00d2
        L_0x00d0:
            r5 = r4
            goto L_0x00d3
        L_0x00d2:
            r5 = r1
        L_0x00d3:
            if (r5 == 0) goto L_0x00da
            com.salesforce.marketingcloud.analytics.j r5 = r9.h
            r5.a(r3)
        L_0x00da:
            com.salesforce.marketingcloud.d.h r5 = r9.d     // Catch:{ Exception -> 0x00e4 }
            com.salesforce.marketingcloud.e.a r5 = r5.a()     // Catch:{ Exception -> 0x00e4 }
            r0.a(r3, r5)     // Catch:{ Exception -> 0x00e4 }
            goto L_0x0078
        L_0x00e4:
            r5 = move-exception
            java.lang.String r6 = a
            java.lang.String r7 = "Failed to persist state for message %s"
            java.lang.Object[] r8 = new java.lang.Object[r1]
            java.lang.String r3 = r3.j()
            r8[r4] = r3
            com.salesforce.marketingcloud.j.c(r6, r5, r7, r8)
            goto L_0x0078
        L_0x00f5:
            java.util.Set<com.salesforce.marketingcloud.messages.c.b$b> r0 = r9.b
            monitor-enter(r0)
            java.util.Set<com.salesforce.marketingcloud.messages.c.b$b> r2 = r9.b     // Catch:{ all -> 0x012f }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x012f }
            if (r2 != 0) goto L_0x012d
            java.util.Set<com.salesforce.marketingcloud.messages.c.b$b> r2 = r9.b     // Catch:{ all -> 0x012f }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x012f }
        L_0x0106:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x012d
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x012f }
            com.salesforce.marketingcloud.messages.c.b$b r3 = (com.salesforce.marketingcloud.messages.c.b.C0170b) r3     // Catch:{ all -> 0x012f }
            if (r3 == 0) goto L_0x0106
            r3.a(r10)     // Catch:{ Exception -> 0x0118 }
            goto L_0x0106
        L_0x0118:
            r5 = move-exception
            java.lang.String r6 = a     // Catch:{ all -> 0x012f }
            java.lang.String r7 = "%s threw an exception while processing the inbox messages response"
            java.lang.Object[] r8 = new java.lang.Object[r1]     // Catch:{ all -> 0x012f }
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x012f }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x012f }
            r8[r4] = r3     // Catch:{ all -> 0x012f }
            com.salesforce.marketingcloud.j.c(r6, r5, r7, r8)     // Catch:{ all -> 0x012f }
            goto L_0x0106
        L_0x012d:
            monitor-exit(r0)     // Catch:{ all -> 0x012f }
            return
        L_0x012f:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x012f }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.c.d.a(java.util.List):void");
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.j = false;
        d();
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2, String str) {
        j.c(a, "Request failed: %d - %s", Integer.valueOf(i2), str);
        this.f.b(C0160a.UPDATE_INBOX_MESSAGE_STATUS);
    }

    public void b(@Nullable a aVar) {
        if (aVar == null) {
            j.e(a, "InboxMessage was null and could not be updated.  Call to setMessageRead() ignored.", new Object[0]);
            return;
        }
        aVar.a(true);
        d(aVar);
    }

    /* access modifiers changed from: 0000 */
    public void c() {
        this.g.a(com.salesforce.marketingcloud.c.d.INBOX_MESSAGE.a(this.c, com.salesforce.marketingcloud.c.d.b(this.c.b(), this.e)));
        a(System.currentTimeMillis());
    }

    /* access modifiers changed from: 0000 */
    public void d() {
        Map a2 = this.d.n().a();
        if (a2.size() > 0) {
            this.g.a(com.salesforce.marketingcloud.c.d.INBOX_STATUS.a(this.c, com.salesforce.marketingcloud.c.d.a(this.c.b()), a(a2).toString()).a(TextUtils.join(",", a2.keySet())));
        }
    }
}
