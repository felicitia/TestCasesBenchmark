package com.threatmetrix.TrustDefender.internal;

import android.content.ContentResolver;
import android.content.Context;
import com.threatmetrix.TrustDefender.NativeGathererHelper;
import com.threatmetrix.TrustDefender.internal.K7.W;
import com.threatmetrix.TrustDefender.internal.P.O;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PH {

    /* renamed from: byte reason: not valid java name */
    private static volatile PH f489byte;

    /* renamed from: int reason: not valid java name */
    static final String f490int = TL.m331if(PH.class);

    /* renamed from: try reason: not valid java name */
    private static final Lock f491try = new ReentrantLock();

    /* renamed from: break reason: not valid java name */
    private boolean f492break = false;

    /* renamed from: case reason: not valid java name */
    private String[] f493case = null;

    /* renamed from: char reason: not valid java name */
    boolean f494char = false;

    /* renamed from: do reason: not valid java name */
    boolean f495do = false;

    /* renamed from: else reason: not valid java name */
    private long f496else = 0;

    /* renamed from: for reason: not valid java name */
    final NativeGathererHelper f497for = new NativeGathererHelper();

    /* renamed from: if reason: not valid java name */
    int f498if = 0;

    /* renamed from: long reason: not valid java name */
    private final Lock f499long = new ReentrantLock();

    /* renamed from: new reason: not valid java name */
    String[] f500new = {"/system/app", "/system/priv-app"};

    /* renamed from: do reason: not valid java name */
    public static PH m275do() {
        if (f489byte == null) {
            try {
                f491try.lock();
                if (f489byte == null) {
                    f489byte = new PH();
                }
            } finally {
                f491try.unlock();
            }
        }
        return f489byte;
    }

    private PH() {
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002c */
    /* renamed from: if reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean m293if(java.lang.String r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            boolean r0 = r3.f494char
            if (r0 == 0) goto L_0x0006
            r4 = 1
            return r4
        L_0x0006:
            java.util.concurrent.locks.Lock r0 = r3.f499long     // Catch:{ all -> 0x0039 }
            r0.lock()     // Catch:{ all -> 0x0039 }
            boolean r0 = r3.f494char     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0017
            boolean r4 = r3.f494char     // Catch:{ all -> 0x0039 }
            java.util.concurrent.locks.Lock r5 = r3.f499long
            r5.unlock()
            return r4
        L_0x0017:
            r0 = 0
            com.threatmetrix.TrustDefender.NativeGathererHelper r1 = r3.f497for     // Catch:{ UnsatisfiedLinkError -> 0x002f, Throwable -> 0x002c }
            java.lang.String r2 = "tdm-5.2-34-jni"
            java.lang.System.loadLibrary(r2)     // Catch:{ UnsatisfiedLinkError -> 0x002f, Throwable -> 0x002c }
            java.lang.Integer r2 = com.threatmetrix.TrustDefender.internal.KF.numeric     // Catch:{ UnsatisfiedLinkError -> 0x002f, Throwable -> 0x002c }
            int r2 = r2.intValue()     // Catch:{ UnsatisfiedLinkError -> 0x002f, Throwable -> 0x002c }
            boolean r4 = r1.init(r2, r4, r5, r6)     // Catch:{ UnsatisfiedLinkError -> 0x002f, Throwable -> 0x002c }
            r3.f494char = r4     // Catch:{ UnsatisfiedLinkError -> 0x002f, Throwable -> 0x002c }
            goto L_0x0031
        L_0x002c:
            r3.f494char = r0     // Catch:{ all -> 0x0039 }
            goto L_0x0031
        L_0x002f:
            r3.f494char = r0     // Catch:{ all -> 0x0039 }
        L_0x0031:
            java.util.concurrent.locks.Lock r4 = r3.f499long
            r4.unlock()
            boolean r4 = r3.f494char
            return r4
        L_0x0039:
            r4 = move-exception
            java.util.concurrent.locks.Lock r5 = r3.f499long
            r5.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.PH.m293if(java.lang.String, boolean, boolean):boolean");
    }

    /* renamed from: int reason: not valid java name */
    public final int m297int(O o, int i, int i2, int i3) throws InterruptedException {
        String[] strArr;
        int i4 = 0;
        try {
            if (this.f494char) {
                String[] strArr2 = null;
                if (!this.f492break || (i & 16) != 0) {
                    Context context = o.f487for;
                    if (this.f493case == null || TimeUnit.SECONDS.convert(System.nanoTime() - this.f496else, TimeUnit.NANOSECONDS) >= 60) {
                        TL.m338new(f490int, "Starting path find for apk");
                        this.f496else = System.nanoTime();
                        ArrayList arrayList = new R(context).m168if();
                        String str = f490int;
                        StringBuilder sb = new StringBuilder("findAPKPaths found : ");
                        sb.append(arrayList.size());
                        TL.m338new(str, sb.toString());
                        this.f493case = (String[]) arrayList.toArray(new String[arrayList.size()]);
                        strArr = this.f493case;
                    } else {
                        strArr = this.f493case;
                    }
                    strArr2 = strArr;
                    this.f500new = strArr2;
                }
                if (!this.f492break) {
                    this.f497for.initPackageManager();
                    this.f492break = true;
                }
                i4 = this.f497for.findPackages(strArr2, i2, i3, i);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return i4;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String[] m289for(String[] strArr) throws InterruptedException {
        String[] strArr2 = null;
        try {
            String str = f490int;
            StringBuilder sb = new StringBuilder();
            sb.append(this.f494char ? " available " : "not available ");
            sb.append(" Found ");
            sb.append(this.f498if);
            TL.m338new(str, sb.toString());
            if (this.f494char && strArr != null) {
                strArr2 = this.f497for.checkURLs(strArr);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return strArr2;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final int m296int() {
        try {
            if (this.f494char) {
                return this.f497for.cancel();
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final int m301new() {
        try {
            if (this.f494char) {
                return this.f497for.waitUntilCancelled();
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String m285for(String str) throws InterruptedException {
        String str2 = null;
        try {
            if (this.f494char && str != null) {
                str2 = this.f497for.hashFile(str);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return str2;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final String m281do(String str) {
        try {
            if (!this.f494char || str == null) {
                return null;
            }
            return this.f497for.md5(str);
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final String m292if(String str) {
        try {
            if (this.f494char) {
                return this.f497for.getConfig(str);
            }
            return null;
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final int m278do(String str, String str2) throws InterruptedException {
        int i = -1;
        try {
            if (this.f494char && str2 != null) {
                i = this.f497for.setConfig(str, str2);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return i;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final String m282do(byte[] bArr) {
        try {
            if (!this.f494char || bArr == null) {
                return null;
            }
            return this.f497for.sha1(bArr);
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final String m298int(byte[] bArr) {
        try {
            if (!this.f494char || bArr == null) {
                return null;
            }
            return this.f497for.sha256(bArr);
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final String m279do(int i) {
        try {
            if (!this.f494char || i <= 0) {
                return null;
            }
            return this.f497for.getRandomString(i);
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final String m302new(String str) {
        try {
            if (!this.f494char || str == null) {
                return null;
            }
            return this.f497for.urlEncode(str);
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final List<String> m299int(String str) throws InterruptedException {
        List<String> list = null;
        try {
            if (this.f494char) {
                String[] fontList = this.f497for.getFontList(str);
                if (fontList != null) {
                    list = Arrays.asList(fontList);
                }
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return list;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final NN[] m300int(O o) throws InterruptedException {
        NN[] nnArr;
        try {
            if (this.f494char) {
                nnArr = (NN[]) this.f497for.findRunningProcs(o.f487for, NN.class, C0012I.f388for);
                if (!Thread.interrupted()) {
                    return nnArr;
                }
                TL.m338new(f490int, "Thread interrupt detected, throwing");
                throw new InterruptedException();
            }
            Random random = new Random();
            if (!this.f492break) {
                return null;
            }
            NN nn = new NN(m279do(1), m279do(1), new short[]{1}, random.nextLong(), random.nextInt());
            return new NN[]{nn};
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            nnArr = null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final NN[] m303new(O o) throws InterruptedException {
        NN[] nnArr = null;
        try {
            if (this.f494char) {
                nnArr = (NN[]) this.f497for.findInstalledProcs(o.f487for, NN.class, C0012I.f388for);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return nnArr;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final NN[] m287for(O o) throws InterruptedException {
        NN[] nnArr = null;
        try {
            if (this.f494char) {
                nnArr = (NN[]) this.f497for.findAllProcs(o.f487for, NN.class, C0012I.f388for);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return nnArr;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final short[] m283do(String[] strArr) {
        try {
            if (this.f494char) {
                return this.f497for.findPermissions(strArr);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final String[] m295if(short[] sArr) {
        try {
            if (this.f494char) {
                return this.f497for.findPermissions(sArr);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String m286for(String str, String str2) {
        try {
            if (!this.f494char || str2 == null || str == null || str2.length() <= 0 || str.isEmpty()) {
                return null;
            }
            return this.f497for.xor(str, str2);
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final String m291if() {
        try {
            if (this.f494char) {
                return this.f497for.getBinaryArch();
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String[] m288for() throws InterruptedException {
        String[] strArr = null;
        try {
            if (this.f494char) {
                strArr = this.f497for.getNetworkInfo();
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return strArr;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: else reason: not valid java name */
    public final W m284else() throws InterruptedException {
        W w = null;
        try {
            if (this.f494char) {
                int selinuxMode = this.f497for.getSelinuxMode();
                if (selinuxMode >= 0 || selinuxMode <= 4) {
                    w = W.values()[selinuxMode];
                }
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        if (!Thread.interrupted()) {
            return w;
        }
        TL.m338new(f490int, "Thread interrupt detected, throwing");
        throw new InterruptedException();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final DN$L m290if(String str, String str2, O o) {
        DN$L dn$l;
        try {
            if (this.f494char) {
                String[] attestStrongID = this.f497for.attestStrongID(str, str2, o.f487for, C0012I.f388for);
                if (attestStrongID.length == 1) {
                    dn$l = new DN$L();
                    dn$l.f140int = attestStrongID[0];
                } else if (attestStrongID.length == 5) {
                    dn$l = new DN$L();
                    dn$l.f139if = attestStrongID[4];
                    dn$l.f141new = attestStrongID[0];
                    dn$l.f138for = attestStrongID[2];
                    dn$l.f137do = attestStrongID[1];
                    dn$l.f136byte = attestStrongID[3];
                    dn$l.f140int = null;
                } else {
                    dn$l = null;
                }
                return dn$l;
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final byte[] m294if(byte[] bArr, ContentResolver contentResolver) {
        try {
            if (this.f494char) {
                return this.f497for.sign(bArr, contentResolver);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: char reason: not valid java name */
    public final int m277char(String str) {
        try {
            if (this.f494char) {
                return this.f497for.validatePackage(str);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* renamed from: try reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String m304try() {
        /*
            r4 = this;
            r0 = -1
            boolean r1 = r4.f494char     // Catch:{ Throwable -> 0x000c }
            if (r1 == 0) goto L_0x0014
            com.threatmetrix.TrustDefender.NativeGathererHelper r1 = r4.f497for     // Catch:{ Throwable -> 0x000c }
            int r1 = r1.jniDetectedDebugStatus()     // Catch:{ Throwable -> 0x000c }
            goto L_0x0015
        L_0x000c:
            r1 = move-exception
            java.lang.String r2 = f490int
            java.lang.String r3 = "Native code:"
            com.threatmetrix.TrustDefender.internal.TL.m337int(r2, r3, r1)
        L_0x0014:
            r1 = -1
        L_0x0015:
            if (r0 != r1) goto L_0x001a
            java.lang.String r0 = ""
            return r0
        L_0x001a:
            if (r1 <= 0) goto L_0x001e
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.PH.m304try():java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: byte reason: not valid java name */
    public final E[] m276byte() {
        try {
            if (this.f494char) {
                return (E[]) this.f497for.getAddresses(E.class);
            }
            if (!this.f492break) {
                return null;
            }
            return new E[]{new E(m279do(1), m279do(1), InetAddress.getAllByName(""))};
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final String m280do(O o) {
        try {
            if (this.f494char) {
                return this.f497for.getConnections(o.f487for);
            }
        } catch (Throwable th) {
            TL.m337int(f490int, "Native code:", th);
        }
        return null;
    }
}
