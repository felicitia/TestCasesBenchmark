package defpackage;

import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import com.apiguard.AGCallbackInterface;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: al reason: default package */
/* compiled from: GA */
public final class al {
    private static l a = new l();

    public static void a() {
    }

    public static void a(l lVar) {
        a = lVar;
    }

    public static String a(byte[] bArr) {
        try {
            return Base64.encodeToString(bArr, 10);
        } catch (Exception unused) {
            return "";
        }
    }

    public static byte[] a(String str) {
        try {
            return Base64.decode(str, 10);
        } catch (Exception unused) {
            return c.e;
        }
    }

    public static void b(String str) {
        if (a.b() != null) {
            AGCallbackInterface b = a.b();
            StringBuilder sb = new StringBuilder();
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(new Date()));
            sb.append(" :: ");
            sb.append(str);
            b.logAGMessage(sb.toString());
        }
    }

    public static void b() {
        if (a.b() != null) {
            a.b().reauthenticate();
        }
    }

    public static void a(List<Certificate> list, String str) throws IOException {
        if (a.b() != null) {
            a.b().checkCertificates(list, str);
        }
    }

    public static String c(String str) {
        String str2 = "";
        if (str != null) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                str2 = (!parse.isAbsolute() || parse.getHost() == null) ? parse.toString() : parse.getHost();
            }
        }
        return str2.toLowerCase(Locale.ROOT);
    }

    public static boolean c() {
        File d = d();
        return d != null && d.exists();
    }

    public static File d() {
        if (a.a() == null) {
            return null;
        }
        if (VERSION.SDK_INT >= 21) {
            return new File(a.a().getNoBackupFilesDir(), c.i);
        }
        return a.a().getFileStreamPath(c.i);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null) {
            return c.e;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, c.d);
            Mac instance = Mac.getInstance(c.d);
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (InvalidKeyException e) {
            StringBuilder sb = new StringBuilder("M10: ");
            sb.append(e.getLocalizedMessage());
            b(sb.toString());
            return c.e;
        } catch (NoSuchAlgorithmException e2) {
            StringBuilder sb2 = new StringBuilder("M11: ");
            sb2.append(e2.getLocalizedMessage());
            b(sb2.toString());
            return c.e;
        } catch (Exception e3) {
            StringBuilder sb3 = new StringBuilder("M100: ");
            sb3.append(e3.getLocalizedMessage());
            b(sb3.toString());
            return c.e;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        if (r0 != false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean d(java.lang.String r4) {
        /*
            int r0 = r4.length()
            r1 = 0
            r2 = 1
            if (r0 <= r2) goto L_0x006c
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r4 = r4.toLowerCase(r0)
            l r0 = a
            java.util.Map<java.lang.String, java.lang.Boolean> r0 = r0.d
            java.lang.Object r0 = r0.get(r4)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            if (r0 == 0) goto L_0x001f
            boolean r4 = r0.booleanValue()
            return r4
        L_0x001f:
            l r0 = a
            java.util.Map<java.lang.String, java.lang.Boolean> r0 = r0.d
            int r0 = r0.size()
            r3 = 50
            if (r0 < r3) goto L_0x0032
            l r0 = a
            java.util.Map<java.lang.String, java.lang.Boolean> r0 = r0.d
            r0.clear()
        L_0x0032:
            l r0 = a
            java.util.Set<java.lang.String> r0 = r0.b
            boolean r0 = r0.contains(r4)
            if (r0 != 0) goto L_0x005b
            l r0 = a
            java.util.Set<java.lang.String> r0 = r0.c
            java.util.Iterator r0 = r0.iterator()
        L_0x0044:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0058
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r4.endsWith(r3)
            if (r3 == 0) goto L_0x0044
            r0 = 1
            goto L_0x0059
        L_0x0058:
            r0 = 0
        L_0x0059:
            if (r0 == 0) goto L_0x005c
        L_0x005b:
            r1 = 1
        L_0x005c:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            l r1 = a
            java.util.Map<java.lang.String, java.lang.Boolean> r1 = r1.d
            r1.put(r4, r0)
            boolean r4 = r0.booleanValue()
            return r4
        L_0x006c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.al.d(java.lang.String):boolean");
    }

    public static String e() {
        return String.format(Locale.US, "%s (%s; TMAG-%s/%s; %s; %s)", new Object[]{Build.MODEL, VERSION.RELEASE, c.r, "2.6.2", UUID.randomUUID().toString(), Locale.getDefault()});
    }

    public static String a(char c) {
        if (c < 16) {
            StringBuilder sb = new StringBuilder("\\u000");
            sb.append(Integer.toHexString(c).toUpperCase());
            return sb.toString();
        } else if (c < 256) {
            StringBuilder sb2 = new StringBuilder("\\u00");
            sb2.append(Integer.toHexString(c).toUpperCase());
            return sb2.toString();
        } else if (c < 4096) {
            StringBuilder sb3 = new StringBuilder("\\u0");
            sb3.append(Integer.toHexString(c).toUpperCase());
            return sb3.toString();
        } else {
            StringBuilder sb4 = new StringBuilder("\\u");
            sb4.append(Integer.toHexString(c).toUpperCase());
            return sb4.toString();
        }
    }

    public static boolean a(m mVar) {
        return (mVar == null || mVar.uuid == null || mVar.timeOfStateCreation == 0 || mVar.uuid.length <= 0 || mVar.uuid.length > 4096) ? false : true;
    }
}
