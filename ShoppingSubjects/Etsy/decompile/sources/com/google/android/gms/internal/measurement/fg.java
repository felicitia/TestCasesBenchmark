package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.measurement.AppMeasurement.a;
import com.google.android.gms.measurement.AppMeasurement.e;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class fg extends cp {
    private static final String[] a = {"firebase_", "google_", "ga_"};
    private SecureRandom b;
    private final AtomicLong c = new AtomicLong(0);
    private int d;
    private Integer e = null;

    fg(bu buVar) {
        super(buVar);
    }

    @VisibleForTesting
    static long a(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i = 0;
        Preconditions.checkState(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            i += 8;
            length--;
            j += (((long) bArr[length]) & 255) << i;
        }
        return j;
    }

    private static Object a(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return a(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String a(String str, int i, boolean z) {
        if (str.codePointCount(0, str.length()) > i) {
            if (z) {
                return String.valueOf(str.substring(0, str.offsetByCodePoints(0, i))).concat("...");
            }
            str = null;
        }
        return str;
    }

    @Nullable
    public static String a(String str, String[] strArr, String[] strArr2) {
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        int min = Math.min(strArr.length, strArr2.length);
        for (int i = 0; i < min; i++) {
            if (b(str, strArr[i])) {
                return strArr2[i];
            }
        }
        return null;
    }

    private static void a(Bundle bundle, Object obj) {
        Preconditions.checkNotNull(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    static boolean a(Context context, boolean z) {
        Preconditions.checkNotNull(context);
        return b(context, VERSION.SDK_INT >= 24 ? "com.google.android.gms.measurement.AppMeasurementJobService" : "com.google.android.gms.measurement.AppMeasurementService");
    }

    static boolean a(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    private static boolean a(Bundle bundle, int i) {
        if (bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    static boolean a(String str) {
        Preconditions.checkNotEmpty(str);
        return str.charAt(0) != '_' || str.equals("_ep");
    }

    private final boolean a(String str, String str2, int i, Object obj, boolean z) {
        Parcelable[] parcelableArr;
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
            String valueOf = String.valueOf(obj);
            if (valueOf.codePointCount(0, valueOf.length()) > i) {
                r().i().a("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
                return false;
            }
            return true;
        } else if ((obj instanceof Bundle) && z) {
            return true;
        } else {
            if ((obj instanceof Parcelable[]) && z) {
                for (Parcelable parcelable : (Parcelable[]) obj) {
                    if (!(parcelable instanceof Bundle)) {
                        r().i().a("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable.getClass(), str2);
                        return false;
                    }
                }
                return true;
            } else if (!(obj instanceof ArrayList) || !z) {
                return false;
            } else {
                ArrayList arrayList = (ArrayList) obj;
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj2 = arrayList.get(i2);
                    i2++;
                    if (!(obj2 instanceof Bundle)) {
                        r().i().a("All ArrayList elements must be of type Bundle. Value type, name", obj2.getClass(), str2);
                        return false;
                    }
                }
                return true;
            }
        }
    }

    static byte[] a(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    static Bundle[] a(Object obj) {
        Object[] array;
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            array = Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            array = arrayList.toArray(new Bundle[arrayList.size()]);
        }
        return (Bundle[]) array;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0037 A[Catch:{ IOException | ClassNotFoundException -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c A[Catch:{ IOException | ClassNotFoundException -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object b(java.lang.Object r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0032 }
            r2.<init>(r1)     // Catch:{ all -> 0x0032 }
            r2.writeObject(r4)     // Catch:{ all -> 0x002f }
            r2.flush()     // Catch:{ all -> 0x002f }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ all -> 0x002f }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002f }
            byte[] r1 = r1.toByteArray()     // Catch:{ all -> 0x002f }
            r3.<init>(r1)     // Catch:{ all -> 0x002f }
            r4.<init>(r3)     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r4.readObject()     // Catch:{ all -> 0x002d }
            r2.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
            r4.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
            return r1
        L_0x002d:
            r1 = move-exception
            goto L_0x0035
        L_0x002f:
            r1 = move-exception
            r4 = r0
            goto L_0x0035
        L_0x0032:
            r1 = move-exception
            r4 = r0
            r2 = r4
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x003a:
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x003f:
            throw r1     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.fg.b(java.lang.Object):java.lang.Object");
    }

    private static boolean b(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
            return false;
        } catch (NameNotFoundException unused) {
        }
    }

    static boolean b(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    @VisibleForTesting
    private final boolean c(Context context, String str) {
        as asVar;
        String str2;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0)) {
                return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
            }
        } catch (CertificateException e2) {
            e = e2;
            asVar = r().h_();
            str2 = "Error obtaining certificate";
            asVar.a(str2, e);
            return true;
        } catch (NameNotFoundException e3) {
            e = e3;
            asVar = r().h_();
            str2 = "Package name not found";
            asVar.a(str2, e);
            return true;
        }
        return true;
    }

    private final boolean c(String str, String str2) {
        if (str2 == null) {
            r().h_().a("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            r().h_().a("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        r().h_().a("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            r().h_().a("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    static boolean g(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    private static int i(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        return "_id".equals(str) ? 256 : 36;
    }

    static MessageDigest i() {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final long a(Context context, String str) {
        d();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest i = i();
        if (i == null) {
            r().h_().a("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!c(context, str)) {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(n().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return a(i.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    r().i().a("Could not get signatures");
                    return -1;
                }
            } catch (NameNotFoundException e2) {
                r().h_().a("Package name not found", e2);
            }
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public final Bundle a(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                str4 = uri.getQueryParameter("utm_campaign");
                str3 = uri.getQueryParameter("utm_source");
                str2 = uri.getQueryParameter("utm_medium");
                str = uri.getQueryParameter("gclid");
            } else {
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString("campaign", str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("source", str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString("medium", str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("gclid", str);
            }
            String queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("term", queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(ResponseConstants.CONTENT, queryParameter2);
            }
            String queryParameter3 = uri.getQueryParameter("aclid");
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString("aclid", queryParameter3);
            }
            String queryParameter4 = uri.getQueryParameter("cp1");
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString("cp1", queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("anid", queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e2) {
            r().i().a("Install referrer url isn't a hierarchical URI", e2);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object a2 = a(str, bundle.get(str));
                if (a2 == null) {
                    r().i().a("Param value can't be null", o().b(str));
                } else {
                    a(bundle2, str, a2);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (a("event param", 40, r14) == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0070, code lost:
        if (a("event param", 40, r14) == false) goto L_0x005f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle a(java.lang.String r19, java.lang.String r20, android.os.Bundle r21, @android.support.annotation.Nullable java.util.List<java.lang.String> r22, boolean r23, boolean r24) {
        /*
            r18 = this;
            r6 = r18
            r7 = r21
            r8 = r22
            r9 = 0
            if (r7 == 0) goto L_0x0167
            android.os.Bundle r10 = new android.os.Bundle
            r10.<init>(r7)
            java.util.Set r0 = r21.keySet()
            java.util.Iterator r11 = r0.iterator()
            r12 = 0
            r13 = r12
        L_0x0018:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x0168
            java.lang.Object r0 = r11.next()
            r14 = r0
            java.lang.String r14 = (java.lang.String) r14
            r15 = 40
            r0 = 3
            if (r8 == 0) goto L_0x0033
            boolean r1 = r8.contains(r14)
            if (r1 != 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r1 = r12
            goto L_0x0074
        L_0x0033:
            r1 = 14
            if (r23 == 0) goto L_0x0054
            java.lang.String r2 = "event param"
            boolean r2 = r6.a(r2, r14)
            if (r2 != 0) goto L_0x0041
        L_0x003f:
            r2 = r0
            goto L_0x0055
        L_0x0041:
            java.lang.String r2 = "event param"
            boolean r2 = r6.a(r2, r9, r14)
            if (r2 != 0) goto L_0x004b
            r2 = r1
            goto L_0x0055
        L_0x004b:
            java.lang.String r2 = "event param"
            boolean r2 = r6.a(r2, r15, r14)
            if (r2 != 0) goto L_0x0054
            goto L_0x003f
        L_0x0054:
            r2 = r12
        L_0x0055:
            if (r2 != 0) goto L_0x0073
            java.lang.String r2 = "event param"
            boolean r2 = r6.c(r2, r14)
            if (r2 != 0) goto L_0x0061
        L_0x005f:
            r1 = r0
            goto L_0x0074
        L_0x0061:
            java.lang.String r2 = "event param"
            boolean r2 = r6.a(r2, r9, r14)
            if (r2 != 0) goto L_0x006a
            goto L_0x0074
        L_0x006a:
            java.lang.String r1 = "event param"
            boolean r1 = r6.a(r1, r15, r14)
            if (r1 != 0) goto L_0x0031
            goto L_0x005f
        L_0x0073:
            r1 = r2
        L_0x0074:
            r5 = 1
            if (r1 == 0) goto L_0x008d
            boolean r2 = a(r10, r1)
            if (r2 == 0) goto L_0x0160
            java.lang.String r2 = a(r14, r15, r5)
            java.lang.String r3 = "_ev"
            r10.putString(r3, r2)
            if (r1 != r0) goto L_0x0160
            a(r10, r14)
            goto L_0x0160
        L_0x008d:
            java.lang.Object r4 = r7.get(r14)
            r18.d()
            if (r24 == 0) goto L_0x00ca
            java.lang.String r0 = "param"
            boolean r1 = r4 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x00a1
            r1 = r4
            android.os.Parcelable[] r1 = (android.os.Parcelable[]) r1
            int r1 = r1.length
            goto L_0x00ac
        L_0x00a1:
            boolean r1 = r4 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x00c3
            r1 = r4
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r1 = r1.size()
        L_0x00ac:
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 <= r2) goto L_0x00c3
            com.google.android.gms.internal.measurement.aq r2 = r18.r()
            com.google.android.gms.internal.measurement.as r2 = r2.i()
            java.lang.String r3 = "Parameter array is too long; discarded. Value kind, name, array length"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2.a(r3, r0, r14, r1)
            r0 = r12
            goto L_0x00c4
        L_0x00c3:
            r0 = r5
        L_0x00c4:
            if (r0 != 0) goto L_0x00ca
            r0 = 17
            r9 = r5
            goto L_0x00ff
        L_0x00ca:
            com.google.android.gms.internal.measurement.w r0 = r18.t()
            r3 = r19
            boolean r0 = r0.f(r3)
            if (r0 == 0) goto L_0x00dc
            boolean r0 = g(r20)
            if (r0 != 0) goto L_0x00e2
        L_0x00dc:
            boolean r0 = g(r14)
            if (r0 == 0) goto L_0x00f2
        L_0x00e2:
            java.lang.String r1 = "param"
            r16 = 256(0x100, float:3.59E-43)
            r0 = r6
            r2 = r14
            r3 = r16
            r9 = r5
        L_0x00eb:
            r5 = r24
            boolean r0 = r0.a(r1, r2, r3, r4, r5)
            goto L_0x00fa
        L_0x00f2:
            r9 = r5
            java.lang.String r1 = "param"
            r3 = 100
            r0 = r6
            r2 = r14
            goto L_0x00eb
        L_0x00fa:
            if (r0 == 0) goto L_0x00fe
            r0 = r12
            goto L_0x00ff
        L_0x00fe:
            r0 = 4
        L_0x00ff:
            if (r0 == 0) goto L_0x0123
            java.lang.String r1 = "_ev"
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x0123
            boolean r0 = a(r10, r0)
            if (r0 == 0) goto L_0x0160
            java.lang.String r0 = a(r14, r15, r9)
            java.lang.String r1 = "_ev"
            r10.putString(r1, r0)
            java.lang.Object r0 = r7.get(r14)
            a(r10, r0)
            goto L_0x0160
        L_0x0120:
            r9 = 0
            goto L_0x0018
        L_0x0123:
            boolean r0 = a(r14)
            if (r0 == 0) goto L_0x0164
            int r13 = r13 + 1
            r0 = 25
            if (r13 <= r0) goto L_0x0164
            r0 = 48
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Event can't contain more than 25 params"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.google.android.gms.internal.measurement.aq r1 = r18.r()
            com.google.android.gms.internal.measurement.as r1 = r1.h_()
            com.google.android.gms.internal.measurement.ao r2 = r18.o()
            r3 = r20
            java.lang.String r2 = r2.a(r3)
            com.google.android.gms.internal.measurement.ao r4 = r18.o()
            java.lang.String r4 = r4.a(r7)
            r1.a(r0, r2, r4)
            r0 = 5
            a(r10, r0)
        L_0x0160:
            r10.remove(r14)
            goto L_0x0120
        L_0x0164:
            r3 = r20
            goto L_0x0120
        L_0x0167:
            r10 = 0
        L_0x0168:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.fg.a(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean, boolean):android.os.Bundle");
    }

    /* access modifiers changed from: 0000 */
    public final zzex a(String str, String str2, Bundle bundle, String str3, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (b(str2) != 0) {
            r().h_().a("Invalid conditional property event name", o().c(str2));
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str3);
        zzex zzex = new zzex(str2, new zzeu(a(a(str, str2, bundle2, CollectionUtils.listOf("_o"), false, false))), str3, j);
        return zzex;
    }

    /* access modifiers changed from: 0000 */
    public final Object a(String str, Object obj) {
        boolean z;
        int i = 256;
        if ("_ev".equals(str)) {
            z = true;
        } else {
            if (!g(str)) {
                i = 100;
            }
            z = false;
        }
        return a(i, obj, z);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(int i, String str, String str2, int i2) {
        a((String) null, i, str, str2, i2);
    }

    /* access modifiers changed from: 0000 */
    public final void a(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else {
                if (str != null) {
                    r().j().a("Not putting event parameter. Invalid value type. name, type", o().b(str), obj != null ? obj.getClass().getSimpleName() : null);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        a(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.q.u();
        this.q.h().a("auto", "_err", bundle);
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(String str, int i, String str2) {
        if (str2 == null) {
            r().h_().a("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i) {
            return true;
        } else {
            r().h_().a("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(String str, String str2) {
        if (str2 == null) {
            r().h_().a("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            r().h_().a("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                r().h_().a("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    r().h_().a("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean a(String str, String[] strArr, String str2) {
        boolean z;
        boolean z2;
        if (str2 == null) {
            r().h_().a("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        String[] strArr2 = a;
        int length = strArr2.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (str2.startsWith(strArr2[i])) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            r().h_().a("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        }
        if (strArr != null) {
            Preconditions.checkNotNull(strArr);
            int length2 = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    z2 = false;
                    break;
                } else if (b(str2, strArr[i2])) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2) {
                r().h_().a("Name is reserved. Type, name", str, str2);
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final int b(String str) {
        if (!c(NotificationCompat.CATEGORY_EVENT, str)) {
            return 2;
        }
        if (!a(NotificationCompat.CATEGORY_EVENT, a.a, str)) {
            return 13;
        }
        return !a(NotificationCompat.CATEGORY_EVENT, 40, str) ? 2 : 0;
    }

    /* access modifiers changed from: 0000 */
    public final int b(String str, Object obj) {
        return "_ldl".equals(str) ? a("user property referrer", str, i(str), obj, false) : a("user property", str, i(str), obj, false) ? 0 : 7;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final int c(String str) {
        if (!a("user property", str)) {
            return 6;
        }
        if (!a("user property", e.a, str)) {
            return 15;
        }
        return !a("user property", 24, str) ? 6 : 0;
    }

    /* access modifiers changed from: 0000 */
    public final Object c(String str, Object obj) {
        int i;
        boolean z;
        if ("_ldl".equals(str)) {
            i = i(str);
            z = true;
        } else {
            i = i(str);
            z = false;
        }
        return a(i, obj, z);
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    /* access modifiers changed from: 0000 */
    public final int d(String str) {
        if (!c("user property", str)) {
            return 6;
        }
        if (!a("user property", e.a, str)) {
            return 15;
        }
        return !a("user property", 24, str) ? 6 : 0;
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            r().h_().a("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        }
        Preconditions.checkNotNull(str);
        if (str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$")) {
            return true;
        }
        r().h_().a("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
        return false;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void f() {
        d();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                r().i().a("Utils falling back to Random for random id");
            }
        }
        this.c.set(nextLong);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final boolean f(String str) {
        d();
        if (Wrappers.packageManager(n()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        r().v().a("Permission not granted", str);
        return false;
    }

    public final long g() {
        long andIncrement;
        long j;
        if (this.c.get() == 0) {
            synchronized (this.c) {
                long nextLong = new Random(System.nanoTime() ^ m().currentTimeMillis()).nextLong();
                int i = this.d + 1;
                this.d = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.c) {
            this.c.compareAndSet(-1, 1);
            andIncrement = this.c.getAndIncrement();
        }
        return andIncrement;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final SecureRandom h() {
        d();
        if (this.b == null) {
            this.b = new SecureRandom();
        }
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public final boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String v = t().v();
        u();
        return v.equals(str);
    }

    public final int j() {
        if (this.e == null) {
            this.e = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(n()) / 1000);
        }
        return this.e.intValue();
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }
}
