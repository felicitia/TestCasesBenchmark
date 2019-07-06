package defpackage;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.apiguard.AGCallbackInterface;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: o reason: default package */
/* compiled from: GA */
public final class o {
    public l a = new l();
    public h b = null;
    private n c = new n();

    public o() {
        a();
        this.b = new h(this.a, this.c);
    }

    public final boolean a(Context context, AGCallbackInterface aGCallbackInterface, Map<String, Object> map) {
        boolean a2 = this.a.a(context, aGCallbackInterface, map);
        if (this.a.a() == null) {
            return false;
        }
        m e = this.a.e();
        if (e != null) {
            this.a.b(this.a.a(e));
            if (this.a.c().a()) {
                LocalBroadcastManager.getInstance(this.a.a()).sendBroadcast(new Intent("com.apiguard.action.VLA_LOADED"));
            }
        } else {
            this.a.d();
        }
        h hVar = this.b;
        hVar.a();
        hVar.a = new Timer();
        long j = hVar.b.j;
        hVar.a.scheduleAtFixedRate(new TimerTask() {
            public final void run() {
                h.this.a(false, "20");
            }
        }, j, j);
        hVar.a(true, "10");
        return a2;
    }

    public final void a(int i, boolean z) {
        if (i == this.a.f || z) {
            a();
            if (this.a.a() != null) {
                LocalBroadcastManager.getInstance(this.a.a()).sendBroadcast(new Intent("com.apiguard.action.VLA_INVALIDATED"));
            }
            this.b.a(true, "40");
            al.b();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(com.apiguard.APIGuard.AG_HTTP_COMMAND r5, java.lang.String r6, byte[] r7) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            l r1 = r4.a
            m r1 = r1.c()
            com.apiguard.APIGuard$AG_HTTP_COMMAND r2 = com.apiguard.APIGuard.AG_HTTP_COMMAND.POST
            boolean r2 = r5.equals(r2)
            r3 = 28
            if (r2 != 0) goto L_0x00a6
            com.apiguard.APIGuard$AG_HTTP_COMMAND r2 = com.apiguard.APIGuard.AG_HTTP_COMMAND.PUT
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x001c
            goto L_0x00a6
        L_0x001c:
            com.apiguard.APIGuard$AG_HTTP_COMMAND r7 = com.apiguard.APIGuard.AG_HTTP_COMMAND.GET
            boolean r7 = r5.equals(r7)
            if (r7 != 0) goto L_0x0034
            com.apiguard.APIGuard$AG_HTTP_COMMAND r7 = com.apiguard.APIGuard.AG_HTTP_COMMAND.HEAD
            boolean r7 = r5.equals(r7)
            if (r7 != 0) goto L_0x0034
            com.apiguard.APIGuard$AG_HTTP_COMMAND r7 = com.apiguard.APIGuard.AG_HTTP_COMMAND.DELETE
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x00d6
        L_0x0034:
            n r5 = r4.c
            boolean r7 = r1.a()
            if (r7 == 0) goto L_0x009e
            byte[] r7 = r1.sk2
            int r7 = r7.length
            if (r7 == r3) goto L_0x0042
            goto L_0x009e
        L_0x0042:
            android.net.Uri r6 = android.net.Uri.parse(r6)
            if (r6 == 0) goto L_0x0097
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = r6.getEncodedPath()
            if (r0 != 0) goto L_0x0056
            java.lang.String r0 = ""
            goto L_0x005a
        L_0x0056:
            java.lang.String r0 = r6.getEncodedPath()
        L_0x005a:
            r7.append(r0)
            java.lang.String r0 = r6.getQuery()
            if (r0 != 0) goto L_0x0066
            java.lang.String r6 = ""
            goto L_0x0078
        L_0x0066:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "?"
            r0.<init>(r2)
            java.lang.String r6 = r6.getQuery()
            r0.append(r6)
            java.lang.String r6 = r0.toString()
        L_0x0078:
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            byte[] r6 = r6.getBytes()
            byte[] r7 = r1.sk2
            byte[] r6 = defpackage.al.a(r6, r7)
            if (r6 == 0) goto L_0x0097
            int r7 = r6.length
            if (r7 <= 0) goto L_0x0097
            h r5 = r4.b
            byte[] r7 = r1.uuid
            java.lang.String r5 = r5.a(r6, r7)
            goto L_0x00a4
        L_0x0097:
            h r6 = r4.b
            java.lang.String r5 = r6.a(r5, r1)
            goto L_0x00a4
        L_0x009e:
            h r6 = r4.b
            java.lang.String r5 = r6.a(r5, r1)
        L_0x00a4:
            r0 = r5
            goto L_0x00d6
        L_0x00a6:
            n r5 = r4.c
            boolean r6 = r1.a()
            if (r6 == 0) goto L_0x00cf
            byte[] r6 = r1.sk2
            int r6 = r6.length
            if (r6 == r3) goto L_0x00b4
            goto L_0x00cf
        L_0x00b4:
            byte[] r6 = r1.sk2
            byte[] r6 = defpackage.al.a(r7, r6)
            if (r6 == 0) goto L_0x00c8
            int r7 = r6.length
            if (r7 <= 0) goto L_0x00c8
            h r5 = r4.b
            byte[] r7 = r1.uuid
            java.lang.String r5 = r5.a(r6, r7)
            goto L_0x00a4
        L_0x00c8:
            h r6 = r4.b
            java.lang.String r5 = r6.a(r5, r1)
            goto L_0x00a4
        L_0x00cf:
            h r6 = r4.b
            java.lang.String r5 = r6.a(r5, r1)
            goto L_0x00a4
        L_0x00d6:
            int r5 = r0.length()
            if (r5 != 0) goto L_0x00e4
            h r5 = r4.b
            n r6 = r4.c
            java.lang.String r0 = r5.a(r6, r1)
        L_0x00e4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o.a(com.apiguard.APIGuard$AG_HTTP_COMMAND, java.lang.String, byte[]):java.lang.String");
    }

    private void a() {
        byte[] bArr = this.a.c().uuid;
        if (bArr == null) {
            bArr = new byte[45];
            new SecureRandom().nextBytes(bArr);
        }
        this.a.a(new m(null, bArr, System.currentTimeMillis()));
        this.a.d();
        this.a.b(false);
    }
}
