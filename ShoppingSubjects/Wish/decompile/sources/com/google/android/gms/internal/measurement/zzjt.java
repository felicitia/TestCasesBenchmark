package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzjt implements zzhk {
    private static volatile zzjt zzarr;
    private final zzgn zzacv;
    private zzgh zzars;
    private zzfm zzart;
    private zzek zzaru;
    private zzfr zzarv;
    private zzjp zzarw;
    private zzed zzarx;
    private final zzjz zzary;
    private boolean zzarz;
    private long zzasa;
    private List<Runnable> zzasb;
    private int zzasc;
    private int zzasd;
    private boolean zzase;
    private boolean zzasf;
    private boolean zzasg;
    private FileLock zzash;
    private FileChannel zzasi;
    private List<Long> zzasj;
    private List<Long> zzask;
    private long zzasl;
    private boolean zzvn;

    class zza implements zzem {
        zzku zzasp;
        List<Long> zzasq;
        List<zzkr> zzasr;
        private long zzass;

        private zza() {
        }

        /* synthetic */ zza(zzjt zzjt, zzju zzju) {
            this();
        }

        private static long zza(zzkr zzkr) {
            return ((zzkr.zzavb.longValue() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzkr zzkr) {
            Preconditions.checkNotNull(zzkr);
            if (this.zzasr == null) {
                this.zzasr = new ArrayList();
            }
            if (this.zzasq == null) {
                this.zzasq = new ArrayList();
            }
            if (this.zzasr.size() > 0 && zza((zzkr) this.zzasr.get(0)) != zza(zzkr)) {
                return false;
            }
            long zzwb = this.zzass + ((long) zzkr.zzwb());
            if (zzwb >= ((long) Math.max(0, ((Integer) zzez.zzaim.get()).intValue()))) {
                return false;
            }
            this.zzass = zzwb;
            this.zzasr.add(zzkr);
            this.zzasq.add(Long.valueOf(j));
            return this.zzasr.size() < Math.max(1, ((Integer) zzez.zzain.get()).intValue());
        }

        public final void zzb(zzku zzku) {
            Preconditions.checkNotNull(zzku);
            this.zzasp = zzku;
        }
    }

    private zzjt(zzjy zzjy) {
        this(zzjy, null);
    }

    private zzjt(zzjy zzjy, zzgn zzgn) {
        this.zzvn = false;
        Preconditions.checkNotNull(zzjy);
        this.zzacv = zzgn.zza(zzjy.zzqx, null, null);
        this.zzasl = -1;
        zzjz zzjz = new zzjz(this);
        zzjz.zzm();
        this.zzary = zzjz;
        zzfm zzfm = new zzfm(this);
        zzfm.zzm();
        this.zzart = zzfm;
        zzgh zzgh = new zzgh(this);
        zzgh.zzm();
        this.zzars = zzgh;
        this.zzacv.zzgh().zzc((Runnable) new zzju(this, zzjy));
    }

    private final int zza(FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzacv.zzgi().zziv().log("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzacv.zzgi().zziy().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            this.zzacv.zzgi().zziv().zzg("Failed to read from channel", e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057 A[Catch:{ NameNotFoundException -> 0x00bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzeb zza(android.content.Context r26, java.lang.String r27, java.lang.String r28, boolean r29, boolean r30, boolean r31, long r32) {
        /*
            r25 = this;
            r0 = r25
            r2 = r27
            java.lang.String r1 = "Unknown"
            java.lang.String r3 = "Unknown"
            java.lang.String r4 = "Unknown"
            android.content.pm.PackageManager r5 = r26.getPackageManager()
            r6 = 0
            if (r5 != 0) goto L_0x0021
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()
            java.lang.String r2 = "PackageManager is null, can not log app install information"
            r1.log(r2)
            return r6
        L_0x0021:
            java.lang.String r5 = r5.getInstallerPackageName(r2)     // Catch:{ IllegalArgumentException -> 0x0026 }
            goto L_0x003a
        L_0x0026:
            com.google.android.gms.internal.measurement.zzgn r5 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()
            java.lang.String r7 = "Error retrieving installer package name. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r27)
            r5.zzg(r7, r8)
            r5 = r1
        L_0x003a:
            if (r5 != 0) goto L_0x0040
            java.lang.String r1 = "manual_install"
        L_0x003e:
            r7 = r1
            goto L_0x004c
        L_0x0040:
            java.lang.String r1 = "com.android.vending"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x004b
            java.lang.String r1 = ""
            goto L_0x003e
        L_0x004b:
            r7 = r5
        L_0x004c:
            com.google.android.gms.common.wrappers.PackageManagerWrapper r1 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r26)     // Catch:{ NameNotFoundException -> 0x00bb }
            r5 = 0
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r5)     // Catch:{ NameNotFoundException -> 0x00bb }
            if (r1 == 0) goto L_0x006f
            com.google.android.gms.common.wrappers.PackageManagerWrapper r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r26)     // Catch:{ NameNotFoundException -> 0x00bb }
            java.lang.CharSequence r3 = r3.getApplicationLabel(r2)     // Catch:{ NameNotFoundException -> 0x00bb }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ NameNotFoundException -> 0x00bb }
            if (r5 != 0) goto L_0x006a
            java.lang.String r3 = r3.toString()     // Catch:{ NameNotFoundException -> 0x00bb }
            r4 = r3
        L_0x006a:
            java.lang.String r3 = r1.versionName     // Catch:{ NameNotFoundException -> 0x00bb }
            int r1 = r1.versionCode     // Catch:{ NameNotFoundException -> 0x00bb }
            goto L_0x0071
        L_0x006f:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0071:
            r4 = r3
            r16 = 0
            com.google.android.gms.internal.measurement.zzgn r3 = r0.zzacv
            r3.zzgl()
            r5 = 0
            com.google.android.gms.internal.measurement.zzgn r3 = r0.zzacv
            com.google.android.gms.internal.measurement.zzeh r3 = r3.zzgk()
            boolean r3 = r3.zzbd(r2)
            if (r3 == 0) goto L_0x008a
            r18 = r32
            goto L_0x008c
        L_0x008a:
            r18 = r5
        L_0x008c:
            com.google.android.gms.internal.measurement.zzeb r24 = new com.google.android.gms.internal.measurement.zzeb
            long r5 = (long) r1
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzeh r1 = r1.zzgk()
            long r8 = r1.zzgw()
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzkd r1 = r1.zzgg()
            r3 = r26
            long r10 = r1.zzd(r3, r2)
            r12 = 0
            r14 = 0
            java.lang.String r15 = ""
            r20 = 0
            r23 = 0
            r1 = r24
            r3 = r28
            r13 = r29
            r21 = r30
            r22 = r31
            r1.<init>(r2, r3, r4, r5, r7, r8, r10, r12, r13, r14, r15, r16, r18, r20, r21, r22, r23)
            return r24
        L_0x00bb:
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()
            java.lang.String r3 = "Error retrieving newly installed package info. appId, appName"
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzfi.zzbp(r27)
            r1.zze(r3, r2, r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zza(android.content.Context, java.lang.String, java.lang.String, boolean, boolean, boolean, long):com.google.android.gms.internal.measurement.zzeb");
    }

    private static void zza(zzjs zzjs) {
        if (zzjs == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzjs.isInitialized()) {
            String valueOf = String.valueOf(zzjs.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzjy zzjy) {
        this.zzacv.zzgh().zzab();
        zzek zzek = new zzek(this);
        zzek.zzm();
        this.zzaru = zzek;
        this.zzacv.zzgk().zza(this.zzars);
        zzed zzed = new zzed(this);
        zzed.zzm();
        this.zzarx = zzed;
        zzjp zzjp = new zzjp(this);
        zzjp.zzm();
        this.zzarw = zzjp;
        this.zzarv = new zzfr(this);
        if (this.zzasc != this.zzasd) {
            this.zzacv.zzgi().zziv().zze("Not all upload components initialized", Integer.valueOf(this.zzasc), Integer.valueOf(this.zzasd));
        }
        this.zzvn = true;
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzacv.zzgi().zziv().log("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzacv.zzgi().zziv().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzacv.zzgi().zziv().zzg("Failed to write to channel", e);
            return false;
        }
    }

    private final boolean zza(String str, zzex zzex) {
        long j;
        zzkc zzkc;
        String string = zzex.zzahg.getString("currency");
        if ("ecommerce_purchase".equals(zzex.name)) {
            double doubleValue = zzex.zzahg.zzbk("value").doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) zzex.zzahg.getLong("value").longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                this.zzacv.zzgi().zziy().zze("Data lost. Currency value is too big. appId", zzfi.zzbp(str), Double.valueOf(doubleValue));
                return false;
            }
            j = Math.round(doubleValue);
        } else {
            j = zzex.zzahg.getLong("value").longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                String valueOf2 = String.valueOf(upperCase);
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                zzkc zzh = zzjh().zzh(str, concat);
                if (zzh == null || !(zzh.value instanceof Long)) {
                    zzek zzjh = zzjh();
                    int zzb = this.zzacv.zzgk().zzb(str, zzez.zzaji) - 1;
                    Preconditions.checkNotEmpty(str);
                    zzjh.zzab();
                    zzjh.zzch();
                    try {
                        zzjh.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzjh.zzgi().zziv().zze("Error pruning currencies. appId", zzfi.zzbp(str), e);
                    }
                    zzkc = new zzkc(str, zzex.origin, concat, this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(j));
                } else {
                    zzkc zzkc2 = new zzkc(str, zzex.origin, concat, this.zzacv.zzbt().currentTimeMillis(), Long.valueOf(((Long) zzh.value).longValue() + j));
                    zzkc = zzkc2;
                }
                if (!zzjh().zza(zzkc)) {
                    this.zzacv.zzgi().zziv().zzd("Too many unique user properties are set. Ignoring user property. appId", zzfi.zzbp(str), this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                    this.zzacv.zzgg().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    private final zzkp[] zza(String str, zzkx[] zzkxArr, zzkr[] zzkrArr) {
        Preconditions.checkNotEmpty(str);
        return zzjg().zza(str, zzkrArr, zzkxArr);
    }

    private final void zzab() {
        this.zzacv.zzgh().zzab();
    }

    private final void zzb(zzea zzea) {
        Map map;
        zzab();
        if (TextUtils.isEmpty(zzea.getGmpAppId())) {
            zzb(zzea.zzah(), 204, null, null, null);
            return;
        }
        zzeh zzgk = this.zzacv.zzgk();
        String gmpAppId = zzea.getGmpAppId();
        String appInstanceId = zzea.getAppInstanceId();
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) zzez.zzaii.get()).encodedAuthority((String) zzez.zzaij.get());
        String str = "config/app/";
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", String.valueOf(zzgk.zzgw()));
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            this.zzacv.zzgi().zzjc().zzg("Fetching remote configuration", zzea.zzah());
            zzkn zzbx = zzky().zzbx(zzea.zzah());
            String zzby = zzky().zzby(zzea.zzah());
            if (zzbx == null || TextUtils.isEmpty(zzby)) {
                map = null;
            } else {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put("If-Modified-Since", zzby);
                map = arrayMap;
            }
            this.zzase = true;
            zzfm zzkz = zzkz();
            String zzah = zzea.zzah();
            zzjw zzjw = new zzjw(this);
            zzkz.zzab();
            zzkz.zzch();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzjw);
            zzgi zzgh = zzkz.zzgh();
            zzfq zzfq = new zzfq(zzkz, zzah, url, null, map, zzjw);
            zzgh.zzd((Runnable) zzfq);
        } catch (MalformedURLException unused) {
            this.zzacv.zzgi().zziv().zze("Failed to parse config URL. Not fetching. appId", zzfi.zzbp(zzea.zzah()), uri);
        }
    }

    private final Boolean zzc(zzea zzea) {
        try {
            if (zzea.zzgu() != -2147483648L) {
                if (zzea.zzgu() == ((long) Wrappers.packageManager(this.zzacv.getContext()).getPackageInfo(zzea.zzah(), 0).versionCode)) {
                    return Boolean.valueOf(true);
                }
            } else {
                String str = Wrappers.packageManager(this.zzacv.getContext()).getPackageInfo(zzea.zzah(), 0).versionName;
                if (zzea.zzag() != null && zzea.zzag().equals(str)) {
                    return Boolean.valueOf(true);
                }
            }
            return Boolean.valueOf(false);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:144:0x05c9 A[Catch:{ IOException -> 0x05cc, all -> 0x063d }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x05f7 A[Catch:{ IOException -> 0x05cc, all -> 0x063d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(com.google.android.gms.internal.measurement.zzex r32, com.google.android.gms.internal.measurement.zzeb r33) {
        /*
            r31 = this;
            r1 = r31
            r2 = r32
            r3 = r33
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)
            java.lang.String r4 = r3.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            long r4 = java.lang.System.nanoTime()
            r31.zzab()
            r31.zzlc()
            java.lang.String r15 = r3.packageName
            com.google.android.gms.internal.measurement.zzjz r6 = r31.zzjf()
            boolean r6 = r6.zzd(r2, r3)
            if (r6 != 0) goto L_0x0025
            return
        L_0x0025:
            boolean r6 = r3.zzafk
            if (r6 != 0) goto L_0x002d
            r1.zzg(r3)
            return
        L_0x002d:
            com.google.android.gms.internal.measurement.zzgh r6 = r31.zzky()
            java.lang.String r7 = r2.name
            boolean r6 = r6.zzn(r15, r7)
            r14 = 0
            r13 = 1
            if (r6 == 0) goto L_0x00d8
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziy()
            java.lang.String r4 = "Dropping blacklisted event. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r15)
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfg r6 = r6.zzgf()
            java.lang.String r7 = r2.name
            java.lang.String r6 = r6.zzbm(r7)
            r3.zze(r4, r5, r6)
            com.google.android.gms.internal.measurement.zzgh r3 = r31.zzky()
            boolean r3 = r3.zzcb(r15)
            if (r3 != 0) goto L_0x0070
            com.google.android.gms.internal.measurement.zzgh r3 = r31.zzky()
            boolean r3 = r3.zzcc(r15)
            if (r3 == 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r13 = 0
        L_0x0070:
            if (r13 != 0) goto L_0x008d
            java.lang.String r3 = "_err"
            java.lang.String r4 = r2.name
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x008d
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv
            com.google.android.gms.internal.measurement.zzkd r6 = r3.zzgg()
            r8 = 11
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name
            r11 = 0
            r7 = r15
            r6.zza(r7, r8, r9, r10, r11)
        L_0x008d:
            if (r13 == 0) goto L_0x00d7
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            com.google.android.gms.internal.measurement.zzea r2 = r2.zzbf(r15)
            if (r2 == 0) goto L_0x00d7
            long r3 = r2.zzha()
            long r5 = r2.zzgz()
            long r3 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv
            com.google.android.gms.common.util.Clock r5 = r5.zzbt()
            long r5 = r5.currentTimeMillis()
            long r7 = r5 - r3
            long r3 = java.lang.Math.abs(r7)
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r5 = com.google.android.gms.internal.measurement.zzez.zzajd
            java.lang.Object r5 = r5.get()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x00d7
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zzjb()
            java.lang.String r4 = "Fetching config for blacklisted app"
            r3.log(r4)
            r1.zzb(r2)
        L_0x00d7:
            return
        L_0x00d8:
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzgi()
            r12 = 2
            boolean r6 = r6.isLoggable(r12)
            if (r6 == 0) goto L_0x00fe
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zzjc()
            java.lang.String r7 = "Logging event"
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfg r8 = r8.zzgf()
            java.lang.String r8 = r8.zzb(r2)
            r6.zzg(r7, r8)
        L_0x00fe:
            com.google.android.gms.internal.measurement.zzek r6 = r31.zzjh()
            r6.beginTransaction()
            r1.zzg(r3)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = "_iap"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x063d }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x011c
            java.lang.String r6 = "ecommerce_purchase"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x063d }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x0131
        L_0x011c:
            boolean r6 = r1.zza(r15, r2)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x0131
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            r2.endTransaction()
            return
        L_0x0131:
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x063d }
            boolean r16 = com.google.android.gms.internal.measurement.zzkd.zzcg(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = "_err"
            java.lang.String r7 = r2.name     // Catch:{ all -> 0x063d }
            boolean r17 = r6.equals(r7)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r6 = r31.zzjh()     // Catch:{ all -> 0x063d }
            long r7 = r31.zzld()     // Catch:{ all -> 0x063d }
            r10 = 1
            r18 = 0
            r19 = 0
            r9 = r15
            r11 = r16
            r12 = r18
            r13 = r17
            r20 = r4
            r4 = 0
            r14 = r19
            com.google.android.gms.internal.measurement.zzel r5 = r6.zza(r7, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x063d }
            long r6 = r5.zzagu     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.zzez.zzaio     // Catch:{ all -> 0x063d }
            java.lang.Object r8 = r8.get()     // Catch:{ all -> 0x063d }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x063d }
            int r8 = r8.intValue()     // Catch:{ all -> 0x063d }
            long r8 = (long) r8     // Catch:{ all -> 0x063d }
            long r10 = r6 - r8
            r13 = 0
            int r6 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            r7 = 1000(0x3e8, double:4.94E-321)
            r13 = 1
            if (r6 <= 0) goto L_0x01a4
            long r10 = r10 % r7
            int r2 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x0195
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r15)     // Catch:{ all -> 0x063d }
            long r5 = r5.zzagu     // Catch:{ all -> 0x063d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x063d }
            r2.zze(r3, r4, r5)     // Catch:{ all -> 0x063d }
        L_0x0195:
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            r2.endTransaction()
            return
        L_0x01a4:
            if (r16 == 0) goto L_0x01fc
            long r9 = r5.zzagt     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r6 = com.google.android.gms.internal.measurement.zzez.zzaiq     // Catch:{ all -> 0x063d }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x063d }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x063d }
            int r6 = r6.intValue()     // Catch:{ all -> 0x063d }
            long r11 = (long) r6     // Catch:{ all -> 0x063d }
            long r18 = r9 - r11
            r9 = 0
            int r6 = (r18 > r9 ? 1 : (r18 == r9 ? 0 : -1))
            if (r6 <= 0) goto L_0x01fc
            long r18 = r18 % r7
            int r3 = (r18 > r13 ? 1 : (r18 == r13 ? 0 : -1))
            if (r3 != 0) goto L_0x01dc
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ all -> 0x063d }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfi.zzbp(r15)     // Catch:{ all -> 0x063d }
            long r7 = r5.zzagt     // Catch:{ all -> 0x063d }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x063d }
            r3.zze(r4, r6, r5)     // Catch:{ all -> 0x063d }
        L_0x01dc:
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkd r6 = r3.zzgg()     // Catch:{ all -> 0x063d }
            r8 = 16
            java.lang.String r9 = "_ev"
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x063d }
            r11 = 0
            r7 = r15
            r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            r2.endTransaction()
            return
        L_0x01fc:
            if (r17 == 0) goto L_0x024e
            long r6 = r5.zzagw     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzeh r8 = r8.zzgk()     // Catch:{ all -> 0x063d }
            java.lang.String r9 = r3.packageName     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r10 = com.google.android.gms.internal.measurement.zzez.zzaip     // Catch:{ all -> 0x063d }
            int r8 = r8.zzb(r9, r10)     // Catch:{ all -> 0x063d }
            r9 = 1000000(0xf4240, float:1.401298E-39)
            int r8 = java.lang.Math.min(r9, r8)     // Catch:{ all -> 0x063d }
            int r8 = java.lang.Math.max(r4, r8)     // Catch:{ all -> 0x063d }
            long r8 = (long) r8     // Catch:{ all -> 0x063d }
            long r10 = r6 - r8
            r6 = 0
            int r8 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x024e
            int r2 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x023f
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r15)     // Catch:{ all -> 0x063d }
            long r5 = r5.zzagw     // Catch:{ all -> 0x063d }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x063d }
            r2.zze(r3, r4, r5)     // Catch:{ all -> 0x063d }
        L_0x023f:
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            r2.endTransaction()
            return
        L_0x024e:
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x063d }
            android.os.Bundle r5 = r5.zzin()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkd r6 = r6.zzgg()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "_o"
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x063d }
            r6.zza(r5, r7, r8)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkd r6 = r6.zzgg()     // Catch:{ all -> 0x063d }
            boolean r6 = r6.zzcn(r15)     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x028b
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkd r6 = r6.zzgg()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "_dbg"
            java.lang.Long r8 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x063d }
            r6.zza(r5, r7, r8)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkd r6 = r6.zzgg()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "_r"
            java.lang.Long r8 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x063d }
            r6.zza(r5, r7, r8)     // Catch:{ all -> 0x063d }
        L_0x028b:
            com.google.android.gms.internal.measurement.zzek r6 = r31.zzjh()     // Catch:{ all -> 0x063d }
            long r6 = r6.zzbg(r15)     // Catch:{ all -> 0x063d }
            r13 = 0
            int r8 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r8 <= 0) goto L_0x02b0
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziy()     // Catch:{ all -> 0x063d }
            java.lang.String r9 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfi.zzbp(r15)     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
            r8.zze(r9, r10, r6)     // Catch:{ all -> 0x063d }
        L_0x02b0:
            com.google.android.gms.internal.measurement.zzes r11 = new com.google.android.gms.internal.measurement.zzes     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r7 = r1.zzacv     // Catch:{ all -> 0x063d }
            java.lang.String r8 = r2.origin     // Catch:{ all -> 0x063d }
            java.lang.String r10 = r2.name     // Catch:{ all -> 0x063d }
            long r13 = r2.zzahr     // Catch:{ all -> 0x063d }
            r17 = 0
            r6 = r11
            r9 = r15
            r2 = r11
            r11 = r13
            r13 = r17
            r4 = r15
            r15 = r5
            r6.<init>(r7, r8, r9, r10, r11, r13, r15)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r5 = r31.zzjh()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzet r5 = r5.zzf(r4, r6)     // Catch:{ all -> 0x063d }
            if (r5 != 0) goto L_0x0338
            com.google.android.gms.internal.measurement.zzek r5 = r31.zzjh()     // Catch:{ all -> 0x063d }
            long r5 = r5.zzbj(r4)     // Catch:{ all -> 0x063d }
            r7 = 500(0x1f4, double:2.47E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x031f
            if (r16 == 0) goto L_0x031f
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()     // Catch:{ all -> 0x063d }
            java.lang.String r5 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfi.zzbp(r4)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r7 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfg r7 = r7.zzgf()     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r2.name     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r7.zzbm(r2)     // Catch:{ all -> 0x063d }
            r7 = 500(0x1f4, float:7.0E-43)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x063d }
            r3.zzd(r5, r6, r2, r7)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkd r6 = r2.zzgg()     // Catch:{ all -> 0x063d }
            r8 = 8
            r9 = 0
            r10 = 0
            r11 = 0
            r7 = r4
            r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            r2.endTransaction()
            return
        L_0x031f:
            com.google.android.gms.internal.measurement.zzet r5 = new com.google.android.gms.internal.measurement.zzet     // Catch:{ all -> 0x063d }
            java.lang.String r8 = r2.name     // Catch:{ all -> 0x063d }
            r9 = 0
            r11 = 0
            long r13 = r2.timestamp     // Catch:{ all -> 0x063d }
            r15 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r6 = r5
            r7 = r4
            r6.<init>(r7, r8, r9, r11, r13, r15, r17, r18, r19)     // Catch:{ all -> 0x063d }
            r11 = r2
            goto L_0x0346
        L_0x0338:
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv     // Catch:{ all -> 0x063d }
            long r6 = r5.zzahj     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzes r11 = r2.zza(r4, r6)     // Catch:{ all -> 0x063d }
            long r6 = r11.timestamp     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzet r5 = r5.zzah(r6)     // Catch:{ all -> 0x063d }
        L_0x0346:
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r2.zza(r5)     // Catch:{ all -> 0x063d }
            r31.zzab()     // Catch:{ all -> 0x063d }
            r31.zzlc()     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r11.zzth     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x063d }
            java.lang.String r2 = r11.zzth     // Catch:{ all -> 0x063d }
            java.lang.String r4 = r3.packageName     // Catch:{ all -> 0x063d }
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x063d }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzku r2 = new com.google.android.gms.internal.measurement.zzku     // Catch:{ all -> 0x063d }
            r2.<init>()     // Catch:{ all -> 0x063d }
            r4 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x063d }
            r2.zzavh = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = "android"
            r2.zzavp = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r3.packageName     // Catch:{ all -> 0x063d }
            r2.zzth = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r3.zzafh     // Catch:{ all -> 0x063d }
            r2.zzafh = r5     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r3.zztg     // Catch:{ all -> 0x063d }
            r2.zztg = r5     // Catch:{ all -> 0x063d }
            long r5 = r3.zzafg     // Catch:{ all -> 0x063d }
            r7 = -2147483648(0xffffffff80000000, double:NaN)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r5 = 0
            if (r9 != 0) goto L_0x0391
            r6 = r5
            goto L_0x0398
        L_0x0391:
            long r6 = r3.zzafg     // Catch:{ all -> 0x063d }
            int r6 = (int) r6     // Catch:{ all -> 0x063d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x063d }
        L_0x0398:
            r2.zzawb = r6     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafi     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
            r2.zzavt = r6     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafa     // Catch:{ all -> 0x063d }
            r2.zzafa = r6     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafj     // Catch:{ all -> 0x063d }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x03b0
            r6 = r5
            goto L_0x03b6
        L_0x03b0:
            long r6 = r3.zzafj     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
        L_0x03b6:
            r2.zzavx = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzft r6 = r6.zzgj()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x063d }
            android.util.Pair r6 = r6.zzbr(r7)     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x03e1
            java.lang.Object r7 = r6.first     // Catch:{ all -> 0x063d }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x063d }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x063d }
            if (r7 != 0) goto L_0x03e1
            boolean r7 = r3.zzafm     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x043e
            java.lang.Object r7 = r6.first     // Catch:{ all -> 0x063d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x063d }
            r2.zzavv = r7     // Catch:{ all -> 0x063d }
            java.lang.Object r6 = r6.second     // Catch:{ all -> 0x063d }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x063d }
            r2.zzavw = r6     // Catch:{ all -> 0x063d }
            goto L_0x043e
        L_0x03e1:
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzer r6 = r6.zzge()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r7 = r1.zzacv     // Catch:{ all -> 0x063d }
            android.content.Context r7 = r7.getContext()     // Catch:{ all -> 0x063d }
            boolean r6 = r6.zzf(r7)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x043e
            boolean r6 = r3.zzafn     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x043e
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            android.content.Context r6 = r6.getContext()     // Catch:{ all -> 0x063d }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "android_id"
            java.lang.String r6 = android.provider.Settings.Secure.getString(r6, r7)     // Catch:{ all -> 0x063d }
            if (r6 != 0) goto L_0x0421
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zziy()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = "null secure ID. appId"
            java.lang.String r10 = r2.zzth     // Catch:{ all -> 0x063d }
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfi.zzbp(r10)     // Catch:{ all -> 0x063d }
            r6.zzg(r7, r10)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = "null"
            goto L_0x043c
        L_0x0421:
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x043c
            com.google.android.gms.internal.measurement.zzgn r7 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r7 = r7.zziy()     // Catch:{ all -> 0x063d }
            java.lang.String r10 = "empty secure ID. appId"
            java.lang.String r12 = r2.zzth     // Catch:{ all -> 0x063d }
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12)     // Catch:{ all -> 0x063d }
            r7.zzg(r10, r12)     // Catch:{ all -> 0x063d }
        L_0x043c:
            r2.zzawe = r6     // Catch:{ all -> 0x063d }
        L_0x043e:
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzer r6 = r6.zzge()     // Catch:{ all -> 0x063d }
            r6.zzch()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = android.os.Build.MODEL     // Catch:{ all -> 0x063d }
            r2.zzavr = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzer r6 = r6.zzge()     // Catch:{ all -> 0x063d }
            r6.zzch()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x063d }
            r2.zzavq = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzer r6 = r6.zzge()     // Catch:{ all -> 0x063d }
            long r6 = r6.zzik()     // Catch:{ all -> 0x063d }
            int r6 = (int) r6     // Catch:{ all -> 0x063d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x063d }
            r2.zzavs = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzer r6 = r6.zzge()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r6.zzil()     // Catch:{ all -> 0x063d }
            r2.zzahd = r6     // Catch:{ all -> 0x063d }
            r2.zzavu = r5     // Catch:{ all -> 0x063d }
            r2.zzavk = r5     // Catch:{ all -> 0x063d }
            r2.zzavl = r5     // Catch:{ all -> 0x063d }
            r2.zzavm = r5     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafl     // Catch:{ all -> 0x063d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x063d }
            r2.zzawg = r6     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            boolean r6 = r6.isEnabled()     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x0495
            boolean r6 = com.google.android.gms.internal.measurement.zzeh.zzht()     // Catch:{ all -> 0x063d }
            if (r6 == 0) goto L_0x0495
            r2.zzawh = r5     // Catch:{ all -> 0x063d }
        L_0x0495:
            com.google.android.gms.internal.measurement.zzek r5 = r31.zzjh()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.packageName     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzea r5 = r5.zzbf(r6)     // Catch:{ all -> 0x063d }
            if (r5 != 0) goto L_0x0503
            com.google.android.gms.internal.measurement.zzea r5 = new com.google.android.gms.internal.measurement.zzea     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x063d }
            r5.<init>(r6, r7)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfd r6 = r6.zzfz()     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r6.zzir()     // Catch:{ all -> 0x063d }
            r5.zzam(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafc     // Catch:{ all -> 0x063d }
            r5.zzap(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafa     // Catch:{ all -> 0x063d }
            r5.zzan(r6)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzft r6 = r6.zzgj()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r3.packageName     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r6.zzbs(r7)     // Catch:{ all -> 0x063d }
            r5.zzao(r6)     // Catch:{ all -> 0x063d }
            r5.zzw(r8)     // Catch:{ all -> 0x063d }
            r5.zzr(r8)     // Catch:{ all -> 0x063d }
            r5.zzs(r8)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zztg     // Catch:{ all -> 0x063d }
            r5.setAppVersion(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafg     // Catch:{ all -> 0x063d }
            r5.zzt(r6)     // Catch:{ all -> 0x063d }
            java.lang.String r6 = r3.zzafh     // Catch:{ all -> 0x063d }
            r5.zzaq(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafi     // Catch:{ all -> 0x063d }
            r5.zzu(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafj     // Catch:{ all -> 0x063d }
            r5.zzv(r6)     // Catch:{ all -> 0x063d }
            boolean r6 = r3.zzafk     // Catch:{ all -> 0x063d }
            r5.setMeasurementEnabled(r6)     // Catch:{ all -> 0x063d }
            long r6 = r3.zzafl     // Catch:{ all -> 0x063d }
            r5.zzaf(r6)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r6 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r6.zza(r5)     // Catch:{ all -> 0x063d }
        L_0x0503:
            java.lang.String r6 = r5.getAppInstanceId()     // Catch:{ all -> 0x063d }
            r2.zzaez = r6     // Catch:{ all -> 0x063d }
            java.lang.String r5 = r5.zzgr()     // Catch:{ all -> 0x063d }
            r2.zzafc = r5     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r5 = r31.zzjh()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = r3.packageName     // Catch:{ all -> 0x063d }
            java.util.List r3 = r5.zzbe(r3)     // Catch:{ all -> 0x063d }
            int r5 = r3.size()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkx[] r5 = new com.google.android.gms.internal.measurement.zzkx[r5]     // Catch:{ all -> 0x063d }
            r2.zzavj = r5     // Catch:{ all -> 0x063d }
            r5 = 0
        L_0x0522:
            int r6 = r3.size()     // Catch:{ all -> 0x063d }
            if (r5 >= r6) goto L_0x055b
            com.google.android.gms.internal.measurement.zzkx r6 = new com.google.android.gms.internal.measurement.zzkx     // Catch:{ all -> 0x063d }
            r6.<init>()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkx[] r7 = r2.zzavj     // Catch:{ all -> 0x063d }
            r7[r5] = r6     // Catch:{ all -> 0x063d }
            java.lang.Object r7 = r3.get(r5)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkc r7 = (com.google.android.gms.internal.measurement.zzkc) r7     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r7.name     // Catch:{ all -> 0x063d }
            r6.name = r7     // Catch:{ all -> 0x063d }
            java.lang.Object r7 = r3.get(r5)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkc r7 = (com.google.android.gms.internal.measurement.zzkc) r7     // Catch:{ all -> 0x063d }
            long r12 = r7.zzast     // Catch:{ all -> 0x063d }
            java.lang.Long r7 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x063d }
            r6.zzaws = r7     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzjz r7 = r31.zzjf()     // Catch:{ all -> 0x063d }
            java.lang.Object r10 = r3.get(r5)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzkc r10 = (com.google.android.gms.internal.measurement.zzkc) r10     // Catch:{ all -> 0x063d }
            java.lang.Object r10 = r10.value     // Catch:{ all -> 0x063d }
            r7.zza(r6, r10)     // Catch:{ all -> 0x063d }
            int r5 = r5 + 1
            goto L_0x0522
        L_0x055b:
            com.google.android.gms.internal.measurement.zzek r3 = r31.zzjh()     // Catch:{ IOException -> 0x05cc }
            long r5 = r3.zza(r2)     // Catch:{ IOException -> 0x05cc }
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzeu r3 = r11.zzahg     // Catch:{ all -> 0x063d }
            if (r3 == 0) goto L_0x05c2
            com.google.android.gms.internal.measurement.zzeu r3 = r11.zzahg     // Catch:{ all -> 0x063d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x063d }
        L_0x0571:
            boolean r7 = r3.hasNext()     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x0586
            java.lang.Object r7 = r3.next()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x063d }
            java.lang.String r10 = "_r"
            boolean r7 = r10.equals(r7)     // Catch:{ all -> 0x063d }
            if (r7 == 0) goto L_0x0571
            goto L_0x05c3
        L_0x0586:
            com.google.android.gms.internal.measurement.zzgh r3 = r31.zzky()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r11.zzth     // Catch:{ all -> 0x063d }
            java.lang.String r10 = r11.name     // Catch:{ all -> 0x063d }
            boolean r3 = r3.zzo(r7, r10)     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzek r22 = r31.zzjh()     // Catch:{ all -> 0x063d }
            long r23 = r31.zzld()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r11.zzth     // Catch:{ all -> 0x063d }
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r25 = r7
            com.google.android.gms.internal.measurement.zzel r7 = r22.zza(r23, r25, r26, r27, r28, r29, r30)     // Catch:{ all -> 0x063d }
            if (r3 == 0) goto L_0x05c2
            long r12 = r7.zzagx     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x063d }
            java.lang.String r7 = r11.zzth     // Catch:{ all -> 0x063d }
            int r3 = r3.zzas(r7)     // Catch:{ all -> 0x063d }
            long r14 = (long) r3     // Catch:{ all -> 0x063d }
            int r3 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r3 >= 0) goto L_0x05c2
            goto L_0x05c3
        L_0x05c2:
            r4 = 0
        L_0x05c3:
            boolean r2 = r2.zza(r11, r5, r4)     // Catch:{ all -> 0x063d }
            if (r2 == 0) goto L_0x05e3
            r1.zzasa = r8     // Catch:{ all -> 0x063d }
            goto L_0x05e3
        L_0x05cc:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x063d }
            java.lang.String r5 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r2 = r2.zzth     // Catch:{ all -> 0x063d }
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzfi.zzbp(r2)     // Catch:{ all -> 0x063d }
            r4.zze(r5, r2, r3)     // Catch:{ all -> 0x063d }
        L_0x05e3:
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()     // Catch:{ all -> 0x063d }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x063d }
            r3 = 2
            boolean r2 = r2.isLoggable(r3)     // Catch:{ all -> 0x063d }
            if (r2 == 0) goto L_0x0610
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjc()     // Catch:{ all -> 0x063d }
            java.lang.String r3 = "Event recorded"
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv     // Catch:{ all -> 0x063d }
            com.google.android.gms.internal.measurement.zzfg r4 = r4.zzgf()     // Catch:{ all -> 0x063d }
            java.lang.String r4 = r4.zza(r11)     // Catch:{ all -> 0x063d }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x063d }
        L_0x0610:
            com.google.android.gms.internal.measurement.zzek r2 = r31.zzjh()
            r2.endTransaction()
            r31.zzlg()
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjc()
            java.lang.String r3 = "Background event processing time, ms"
            long r4 = java.lang.System.nanoTime()
            long r6 = r4 - r20
            r4 = 500000(0x7a120, double:2.47033E-318)
            long r8 = r6 + r4
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r8 = r8 / r4
            java.lang.Long r4 = java.lang.Long.valueOf(r8)
            r2.zzg(r3, r4)
            return
        L_0x063d:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.zzek r3 = r31.zzjh()
            r3.endTransaction()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzc(com.google.android.gms.internal.measurement.zzex, com.google.android.gms.internal.measurement.zzeb):void");
    }

    private final zzeb zzce(String str) {
        zzfk zzjb;
        String str2;
        Object obj;
        String str3 = str;
        zzea zzbf = zzjh().zzbf(str3);
        if (zzbf == null || TextUtils.isEmpty(zzbf.zzag())) {
            zzjb = this.zzacv.zzgi().zzjb();
            str2 = "No app data available; dropping";
            obj = str3;
        } else {
            Boolean zzc = zzc(zzbf);
            if (zzc == null || zzc.booleanValue()) {
                zzeb zzeb = new zzeb(str3, zzbf.getGmpAppId(), zzbf.zzag(), zzbf.zzgu(), zzbf.zzgv(), zzbf.zzgw(), zzbf.zzgx(), (String) null, zzbf.isMeasurementEnabled(), false, zzbf.zzgr(), zzbf.zzhk(), 0, 0, zzbf.zzhl(), zzbf.zzhm(), false);
                return zzeb;
            }
            zzjb = this.zzacv.zzgi().zziv();
            str2 = "App version does not match; dropping. appId";
            obj = zzfi.zzbp(str);
        }
        zzjb.zzg(str2, obj);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0223, code lost:
        if (r5 != null) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0246, code lost:
        if (r6 == null) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r2 = r0;
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0263, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0264, code lost:
        r6 = r3;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0282, code lost:
        if (r6 != null) goto L_0x0248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r6 = null;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        if (r3 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01d5, code lost:
        if (r5 != null) goto L_0x01d7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (r0v20 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r3 
      PHI: (r3v48 android.database.Cursor) = (r3v42 android.database.Cursor), (r3v51 android.database.Cursor), (r3v51 android.database.Cursor), (r3v51 android.database.Cursor), (r3v51 android.database.Cursor), (r3v1 android.database.Cursor), (r3v1 android.database.Cursor) binds: [B:47:0x00e3, B:24:0x0080, B:30:0x008d, B:32:0x0091, B:33:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0289 A[Catch:{ SQLiteException -> 0x0b5b, all -> 0x0b96 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0297 A[Catch:{ SQLiteException -> 0x0b5b, all -> 0x0b96 }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x05a7 A[Catch:{ SQLiteException -> 0x0b5b, all -> 0x0b96 }] */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x0690 A[Catch:{ SQLiteException -> 0x0b5b, all -> 0x0b96 }] */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x06aa A[Catch:{ SQLiteException -> 0x0b5b, all -> 0x0b96 }] */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x06ca A[Catch:{ SQLiteException -> 0x0b5b, all -> 0x0b96 }] */
    /* JADX WARNING: Removed duplicated region for block: B:451:0x0b7e A[SYNTHETIC, Splitter:B:451:0x0b7e] */
    /* JADX WARNING: Removed duplicated region for block: B:458:0x0b92 A[SYNTHETIC, Splitter:B:458:0x0b92] */
    /* JADX WARNING: Removed duplicated region for block: B:480:0x06a7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0123 A[SYNTHETIC, Splitter:B:60:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0145 A[SYNTHETIC, Splitter:B:69:0x0145] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:145:0x0285=Splitter:B:145:0x0285, B:98:0x01d7=Splitter:B:98:0x01d7, B:28:0x0088=Splitter:B:28:0x0088, B:124:0x0248=Splitter:B:124:0x0248} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r60, long r61) {
        /*
            r59 = this;
            r1 = r59
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()
            r2.beginTransaction()
            com.google.android.gms.internal.measurement.zzjt$zza r2 = new com.google.android.gms.internal.measurement.zzjt$zza     // Catch:{ all -> 0x0b96 }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzek r4 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            long r5 = r1.zzasl     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0b96 }
            r4.zzab()     // Catch:{ all -> 0x0b96 }
            r4.zzch()     // Catch:{ all -> 0x0b96 }
            r7 = -1
            r9 = 2
            r10 = 0
            r11 = 1
            android.database.sqlite.SQLiteDatabase r15 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            boolean r12 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            if (r12 == 0) goto L_0x00a1
            int r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x004b
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            r12[r10] = r13     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            java.lang.String r13 = java.lang.String.valueOf(r61)     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            r12[r11] = r13     // Catch:{ SQLiteException -> 0x0045, all -> 0x0040 }
            goto L_0x0053
        L_0x0040:
            r0 = move-exception
            r2 = r0
            r6 = r3
            goto L_0x0b90
        L_0x0045:
            r0 = move-exception
            r6 = r3
            r12 = r6
        L_0x0048:
            r3 = r0
            goto L_0x0271
        L_0x004b:
            java.lang.String[] r12 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r13 = java.lang.String.valueOf(r61)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r12[r10] = r13     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
        L_0x0053:
            int r13 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r13 == 0) goto L_0x005a
            java.lang.String r13 = "rowid <= ? and "
            goto L_0x005c
        L_0x005a:
            java.lang.String r13 = ""
        L_0x005c:
            java.lang.String r14 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            int r14 = r14.length()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            int r14 = r14 + 148
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r3.<init>(r14)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r14 = "select app_id, metadata_fingerprint from raw_events where "
            r3.append(r14)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r3.append(r13)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r13 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r3.append(r13)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            android.database.Cursor r3 = r15.rawQuery(r3, r12)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            boolean r12 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0263, all -> 0x0040 }
            if (r12 != 0) goto L_0x008d
            if (r3 == 0) goto L_0x0285
        L_0x0088:
            r3.close()     // Catch:{ all -> 0x0b96 }
            goto L_0x0285
        L_0x008d:
            java.lang.String r12 = r3.getString(r10)     // Catch:{ SQLiteException -> 0x0263, all -> 0x0040 }
            java.lang.String r13 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x009e, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x009e, all -> 0x0040 }
            r22 = r3
            r3 = r12
            r21 = r13
            goto L_0x00f8
        L_0x009e:
            r0 = move-exception
            r6 = r3
            goto L_0x0048
        L_0x00a1:
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x00b1
            java.lang.String[] r3 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r12 = 0
            r3[r10] = r12     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r12 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r3[r11] = r12     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            goto L_0x00b6
        L_0x00b1:
            java.lang.String[] r3 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r12 = 0
            r3[r10] = r12     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
        L_0x00b6:
            int r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x00bd
            java.lang.String r12 = " and rowid <= ?"
            goto L_0x00bf
        L_0x00bd:
            java.lang.String r12 = ""
        L_0x00bf:
            java.lang.String r13 = java.lang.String.valueOf(r12)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            int r13 = r13.length()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            int r13 = r13 + 84
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r14.<init>(r13)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r13 = "select metadata_fingerprint from raw_events where app_id = ?"
            r14.append(r13)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            r14.append(r12)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r12 = " order by rowid limit 1;"
            r14.append(r12)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            java.lang.String r12 = r14.toString()     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            android.database.Cursor r3 = r15.rawQuery(r12, r3)     // Catch:{ SQLiteException -> 0x026d, all -> 0x0268 }
            boolean r12 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0263, all -> 0x0040 }
            if (r12 != 0) goto L_0x00ec
            if (r3 == 0) goto L_0x0285
            goto L_0x0088
        L_0x00ec:
            java.lang.String r13 = r3.getString(r10)     // Catch:{ SQLiteException -> 0x0263, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x0263, all -> 0x0040 }
            r22 = r3
            r21 = r13
            r3 = 0
        L_0x00f8:
            java.lang.String r13 = "raw_events_metadata"
            java.lang.String[] r14 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            java.lang.String r12 = "metadata"
            r14[r10] = r12     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            java.lang.String r16 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r12[r10] = r3     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r12[r11] = r21     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "2"
            r23 = r12
            r12 = r15
            r24 = r15
            r15 = r16
            r16 = r23
            android.database.Cursor r15 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x025d, all -> 0x0257 }
            boolean r12 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            if (r12 != 0) goto L_0x0145
            com.google.android.gms.internal.measurement.zzfi r5 = r4.zzgi()     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            java.lang.String r6 = "Raw event metadata record is missing. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            r5.zzg(r6, r12)     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            if (r15 == 0) goto L_0x0285
            r15.close()     // Catch:{ all -> 0x0b96 }
            goto L_0x0285
        L_0x013b:
            r0 = move-exception
            r2 = r0
            r6 = r15
            goto L_0x0b90
        L_0x0140:
            r0 = move-exception
            r12 = r3
            r6 = r15
            goto L_0x0048
        L_0x0145:
            byte[] r12 = r15.getBlob(r10)     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            int r13 = r12.length     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            com.google.android.gms.internal.measurement.zzaca r12 = com.google.android.gms.internal.measurement.zzaca.zza(r12, r10, r13)     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            com.google.android.gms.internal.measurement.zzku r13 = new com.google.android.gms.internal.measurement.zzku     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r13.<init>()     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r13.zzb(r12)     // Catch:{ IOException -> 0x0233 }
            boolean r12 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            if (r12 == 0) goto L_0x016d
            com.google.android.gms.internal.measurement.zzfi r12 = r4.zzgi()     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            com.google.android.gms.internal.measurement.zzfk r12 = r12.zziy()     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            java.lang.String r14 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            r12.zzg(r14, r9)     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
        L_0x016d:
            r15.close()     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r2.zzb(r13)     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r14 = 3
            if (r9 == 0) goto L_0x018b
            java.lang.String r9 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            r12[r10] = r3     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            r12[r11] = r21     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            r6 = 2
            r12[r6] = r5     // Catch:{ SQLiteException -> 0x0140, all -> 0x013b }
            r5 = r9
            r16 = r12
            goto L_0x0196
        L_0x018b:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r9[r10] = r3     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r9[r11] = r21     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r16 = r9
        L_0x0196:
            java.lang.String r13 = "raw_events"
            r6 = 4
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            java.lang.String r9 = "rowid"
            r6[r10] = r9     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            java.lang.String r9 = "name"
            r6[r11] = r9     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            java.lang.String r9 = "timestamp"
            r12 = 2
            r6[r12] = r9     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            java.lang.String r9 = "data"
            r6[r14] = r9     // Catch:{ SQLiteException -> 0x0252, all -> 0x024e }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            r20 = 0
            r12 = r24
            r9 = 3
            r14 = r6
            r6 = r15
            r15 = r5
            android.database.Cursor r5 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x024c }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            if (r6 != 0) goto L_0x01dc
            com.google.android.gms.internal.measurement.zzfi r6 = r4.zzgi()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zziy()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            java.lang.String r9 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r6.zzg(r9, r12)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            if (r5 == 0) goto L_0x0285
        L_0x01d7:
            r5.close()     // Catch:{ all -> 0x0b96 }
            goto L_0x0285
        L_0x01dc:
            long r12 = r5.getLong(r10)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            byte[] r6 = r5.getBlob(r9)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            int r14 = r6.length     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            com.google.android.gms.internal.measurement.zzaca r6 = com.google.android.gms.internal.measurement.zzaca.zza(r6, r10, r14)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            com.google.android.gms.internal.measurement.zzkr r14 = new com.google.android.gms.internal.measurement.zzkr     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r14.<init>()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r14.zzb(r6)     // Catch:{ IOException -> 0x020b }
            java.lang.String r6 = r5.getString(r11)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r14.name = r6     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r6 = 2
            long r7 = r5.getLong(r6)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            java.lang.Long r6 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r14.zzavb = r6     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            boolean r6 = r2.zza(r12, r14)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            if (r6 != 0) goto L_0x021d
            if (r5 == 0) goto L_0x0285
            goto L_0x01d7
        L_0x020b:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfi r6 = r4.zzgi()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zziv()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            java.lang.String r7 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            r6.zze(r7, r8, r0)     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
        L_0x021d:
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x022e, all -> 0x0229 }
            if (r6 != 0) goto L_0x0226
            if (r5 == 0) goto L_0x0285
            goto L_0x01d7
        L_0x0226:
            r7 = -1
            goto L_0x01dc
        L_0x0229:
            r0 = move-exception
            r2 = r0
            r6 = r5
            goto L_0x0b90
        L_0x022e:
            r0 = move-exception
            r12 = r3
            r6 = r5
            goto L_0x0048
        L_0x0233:
            r0 = move-exception
            r6 = r15
            com.google.android.gms.internal.measurement.zzfi r5 = r4.zzgi()     // Catch:{ SQLiteException -> 0x024c }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ SQLiteException -> 0x024c }
            java.lang.String r7 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)     // Catch:{ SQLiteException -> 0x024c }
            r5.zze(r7, r8, r0)     // Catch:{ SQLiteException -> 0x024c }
            if (r6 == 0) goto L_0x0285
        L_0x0248:
            r6.close()     // Catch:{ all -> 0x0b96 }
            goto L_0x0285
        L_0x024c:
            r0 = move-exception
            goto L_0x0254
        L_0x024e:
            r0 = move-exception
            r6 = r15
            goto L_0x0b8f
        L_0x0252:
            r0 = move-exception
            r6 = r15
        L_0x0254:
            r12 = r3
            goto L_0x0048
        L_0x0257:
            r0 = move-exception
            r2 = r0
            r6 = r22
            goto L_0x0b90
        L_0x025d:
            r0 = move-exception
            r12 = r3
            r6 = r22
            goto L_0x0048
        L_0x0263:
            r0 = move-exception
            r6 = r3
            r12 = 0
            goto L_0x0048
        L_0x0268:
            r0 = move-exception
            r2 = r0
            r6 = 0
            goto L_0x0b90
        L_0x026d:
            r0 = move-exception
            r3 = r0
            r6 = 0
            r12 = 0
        L_0x0271:
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzgi()     // Catch:{ all -> 0x0b8e }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x0b8e }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r12)     // Catch:{ all -> 0x0b8e }
            r4.zze(r5, r7, r3)     // Catch:{ all -> 0x0b8e }
            if (r6 == 0) goto L_0x0285
            goto L_0x0248
        L_0x0285:
            java.util.List<com.google.android.gms.internal.measurement.zzkr> r3 = r2.zzasr     // Catch:{ all -> 0x0b96 }
            if (r3 == 0) goto L_0x0294
            java.util.List<com.google.android.gms.internal.measurement.zzkr> r3 = r2.zzasr     // Catch:{ all -> 0x0b96 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0b96 }
            if (r3 == 0) goto L_0x0292
            goto L_0x0294
        L_0x0292:
            r3 = 0
            goto L_0x0295
        L_0x0294:
            r3 = 1
        L_0x0295:
            if (r3 != 0) goto L_0x0b7e
            com.google.android.gms.internal.measurement.zzku r3 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.util.List<com.google.android.gms.internal.measurement.zzkr> r4 = r2.zzasr     // Catch:{ all -> 0x0b96 }
            int r4 = r4.size()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r4 = new com.google.android.gms.internal.measurement.zzkr[r4]     // Catch:{ all -> 0x0b96 }
            r3.zzavi = r4     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r3.zzth     // Catch:{ all -> 0x0b96 }
            boolean r4 = r4.zzaw(r5)     // Catch:{ all -> 0x0b96 }
            r7 = 0
            r8 = 0
            r9 = 0
            r12 = 0
        L_0x02b4:
            java.util.List<com.google.android.gms.internal.measurement.zzkr> r14 = r2.zzasr     // Catch:{ all -> 0x0b96 }
            int r14 = r14.size()     // Catch:{ all -> 0x0b96 }
            if (r7 >= r14) goto L_0x05fe
            java.util.List<com.google.android.gms.internal.measurement.zzkr> r14 = r2.zzasr     // Catch:{ all -> 0x0b96 }
            java.lang.Object r14 = r14.get(r7)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr r14 = (com.google.android.gms.internal.measurement.zzkr) r14     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgh r15 = r59.zzky()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r11 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r11 = r11.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r14.name     // Catch:{ all -> 0x0b96 }
            boolean r5 = r15.zzn(r11, r5)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x0340
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.zzku r11 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r11 = r11.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r11)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r15 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfg r15 = r15.zzgf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r14.name     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r15.zzbm(r10)     // Catch:{ all -> 0x0b96 }
            r5.zze(r6, r11, r10)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgh r5 = r59.zzky()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r6 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r6.zzth     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.zzcb(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x0316
            com.google.android.gms.internal.measurement.zzgh r5 = r59.zzky()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r6 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r6.zzth     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.zzcc(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x0314
            goto L_0x0316
        L_0x0314:
            r5 = 0
            goto L_0x0317
        L_0x0316:
            r5 = 1
        L_0x0317:
            if (r5 != 0) goto L_0x033c
            java.lang.String r5 = "_err"
            java.lang.String r6 = r14.name     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x033c
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkd r15 = r5.zzgg()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r5 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r5.zzth     // Catch:{ all -> 0x0b96 }
            r17 = 11
            java.lang.String r18 = "_ev"
            java.lang.String r6 = r14.name     // Catch:{ all -> 0x0b96 }
            r20 = 0
            r16 = r5
            r19 = r6
            r15.zza(r16, r17, r18, r19, r20)     // Catch:{ all -> 0x0b96 }
        L_0x033c:
            r28 = r7
            goto L_0x05f8
        L_0x0340:
            com.google.android.gms.internal.measurement.zzgh r5 = r59.zzky()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r6 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r6.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r14.name     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.zzo(r6, r10)     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x0397
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r14.name     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x0b96 }
            r10 = -1
            int r11 = r6.hashCode()     // Catch:{ all -> 0x0b96 }
            r15 = 94660(0x171c4, float:1.32647E-40)
            if (r11 == r15) goto L_0x0381
            r15 = 95025(0x17331, float:1.33158E-40)
            if (r11 == r15) goto L_0x0377
            r15 = 95027(0x17333, float:1.33161E-40)
            if (r11 == r15) goto L_0x036d
            goto L_0x038a
        L_0x036d:
            java.lang.String r11 = "_ui"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0b96 }
            if (r6 == 0) goto L_0x038a
            r10 = 1
            goto L_0x038a
        L_0x0377:
            java.lang.String r11 = "_ug"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0b96 }
            if (r6 == 0) goto L_0x038a
            r10 = 2
            goto L_0x038a
        L_0x0381:
            java.lang.String r11 = "_in"
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0b96 }
            if (r6 == 0) goto L_0x038a
            r10 = 0
        L_0x038a:
            switch(r10) {
                case 0: goto L_0x038f;
                case 1: goto L_0x038f;
                case 2: goto L_0x038f;
                default: goto L_0x038d;
            }     // Catch:{ all -> 0x0b96 }
        L_0x038d:
            r6 = 0
            goto L_0x0390
        L_0x038f:
            r6 = 1
        L_0x0390:
            if (r6 == 0) goto L_0x0393
            goto L_0x0397
        L_0x0393:
            r28 = r7
            goto L_0x0597
        L_0x0397:
            com.google.android.gms.internal.measurement.zzks[] r6 = r14.zzava     // Catch:{ all -> 0x0b96 }
            if (r6 != 0) goto L_0x03a0
            r6 = 0
            com.google.android.gms.internal.measurement.zzks[] r10 = new com.google.android.gms.internal.measurement.zzks[r6]     // Catch:{ all -> 0x0b96 }
            r14.zzava = r10     // Catch:{ all -> 0x0b96 }
        L_0x03a0:
            com.google.android.gms.internal.measurement.zzks[] r6 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r10 = r6.length     // Catch:{ all -> 0x0b96 }
            r11 = 0
            r15 = 0
            r16 = 0
        L_0x03a7:
            if (r11 >= r10) goto L_0x03e8
            r25 = r10
            r10 = r6[r11]     // Catch:{ all -> 0x0b96 }
            r26 = r6
            java.lang.String r6 = "_c"
            r27 = r8
            java.lang.String r8 = r10.name     // Catch:{ all -> 0x0b96 }
            boolean r6 = r6.equals(r8)     // Catch:{ all -> 0x0b96 }
            if (r6 == 0) goto L_0x03c7
            r28 = r7
            r6 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r10.zzave = r8     // Catch:{ all -> 0x0b96 }
            r15 = 1
            goto L_0x03dd
        L_0x03c7:
            r28 = r7
            java.lang.String r6 = "_r"
            java.lang.String r7 = r10.name     // Catch:{ all -> 0x0b96 }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x0b96 }
            if (r6 == 0) goto L_0x03dd
            r6 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r10.zzave = r8     // Catch:{ all -> 0x0b96 }
            r16 = 1
        L_0x03dd:
            int r11 = r11 + 1
            r10 = r25
            r6 = r26
            r8 = r27
            r7 = r28
            goto L_0x03a7
        L_0x03e8:
            r28 = r7
            r27 = r8
            if (r15 != 0) goto L_0x0430
            if (r5 == 0) goto L_0x0430
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zzjc()     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = "Marking event as conversion"
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfg r8 = r8.zzgf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r14.name     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = r8.zzbm(r10)     // Catch:{ all -> 0x0b96 }
            r6.zzg(r7, r8)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r6 = r14.zzava     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r7 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r6 = (com.google.android.gms.internal.measurement.zzks[]) r6     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks r7 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x0b96 }
            r7.<init>()     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = "_c"
            r7.name = r8     // Catch:{ all -> 0x0b96 }
            r10 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0b96 }
            r7.zzave = r8     // Catch:{ all -> 0x0b96 }
            int r8 = r6.length     // Catch:{ all -> 0x0b96 }
            r10 = 1
            int r8 = r8 - r10
            r6[r8] = r7     // Catch:{ all -> 0x0b96 }
            r14.zzava = r6     // Catch:{ all -> 0x0b96 }
        L_0x0430:
            if (r16 != 0) goto L_0x0472
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r6 = r6.zzjc()     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = "Marking event as real-time"
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfg r8 = r8.zzgf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r14.name     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = r8.zzbm(r10)     // Catch:{ all -> 0x0b96 }
            r6.zzg(r7, r8)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r6 = r14.zzava     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r7 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r6 = (com.google.android.gms.internal.measurement.zzks[]) r6     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks r7 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x0b96 }
            r7.<init>()     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = "_r"
            r7.name = r8     // Catch:{ all -> 0x0b96 }
            r10 = 1
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0b96 }
            r7.zzave = r8     // Catch:{ all -> 0x0b96 }
            int r8 = r6.length     // Catch:{ all -> 0x0b96 }
            r10 = 1
            int r8 = r8 - r10
            r6[r8] = r7     // Catch:{ all -> 0x0b96 }
            r14.zzava = r6     // Catch:{ all -> 0x0b96 }
        L_0x0472:
            com.google.android.gms.internal.measurement.zzek r29 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            long r30 = r59.zzld()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r6 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r6.zzth     // Catch:{ all -> 0x0b96 }
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 1
            r32 = r6
            com.google.android.gms.internal.measurement.zzel r6 = r29.zza(r30, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x0b96 }
            long r6 = r6.zzagx     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzeh r8 = r8.zzgk()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r10 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r10.zzth     // Catch:{ all -> 0x0b96 }
            int r8 = r8.zzas(r10)     // Catch:{ all -> 0x0b96 }
            long r10 = (long) r8     // Catch:{ all -> 0x0b96 }
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 <= 0) goto L_0x04db
            r6 = 0
        L_0x04a4:
            com.google.android.gms.internal.measurement.zzks[] r7 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            if (r6 >= r7) goto L_0x04d8
            java.lang.String r7 = "_r"
            com.google.android.gms.internal.measurement.zzks[] r8 = r14.zzava     // Catch:{ all -> 0x0b96 }
            r8 = r8[r6]     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x0b96 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0b96 }
            if (r7 == 0) goto L_0x04d5
            com.google.android.gms.internal.measurement.zzks[] r7 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            r8 = 1
            int r7 = r7 - r8
            com.google.android.gms.internal.measurement.zzks[] r7 = new com.google.android.gms.internal.measurement.zzks[r7]     // Catch:{ all -> 0x0b96 }
            if (r6 <= 0) goto L_0x04c6
            com.google.android.gms.internal.measurement.zzks[] r8 = r14.zzava     // Catch:{ all -> 0x0b96 }
            r10 = 0
            java.lang.System.arraycopy(r8, r10, r7, r10, r6)     // Catch:{ all -> 0x0b96 }
        L_0x04c6:
            int r8 = r7.length     // Catch:{ all -> 0x0b96 }
            if (r6 >= r8) goto L_0x04d2
            com.google.android.gms.internal.measurement.zzks[] r8 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r10 = r6 + 1
            int r11 = r7.length     // Catch:{ all -> 0x0b96 }
            int r11 = r11 - r6
            java.lang.System.arraycopy(r8, r10, r7, r6, r11)     // Catch:{ all -> 0x0b96 }
        L_0x04d2:
            r14.zzava = r7     // Catch:{ all -> 0x0b96 }
            goto L_0x04d8
        L_0x04d5:
            int r6 = r6 + 1
            goto L_0x04a4
        L_0x04d8:
            r8 = r27
            goto L_0x04dc
        L_0x04db:
            r8 = 1
        L_0x04dc:
            java.lang.String r6 = r14.name     // Catch:{ all -> 0x0b96 }
            boolean r6 = com.google.android.gms.internal.measurement.zzkd.zzcg(r6)     // Catch:{ all -> 0x0b96 }
            if (r6 == 0) goto L_0x0597
            if (r5 == 0) goto L_0x0597
            com.google.android.gms.internal.measurement.zzek r29 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            long r30 = r59.zzld()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r5 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r5.zzth     // Catch:{ all -> 0x0b96 }
            r33 = 0
            r34 = 0
            r35 = 1
            r36 = 0
            r37 = 0
            r32 = r5
            com.google.android.gms.internal.measurement.zzel r5 = r29.zza(r30, r32, r33, r34, r35, r36, r37)     // Catch:{ all -> 0x0b96 }
            long r5 = r5.zzagv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r7 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzeh r7 = r7.zzgk()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r10 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r10.zzth     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r11 = com.google.android.gms.internal.measurement.zzez.zzair     // Catch:{ all -> 0x0b96 }
            int r7 = r7.zzb(r10, r11)     // Catch:{ all -> 0x0b96 }
            long r10 = (long) r7     // Catch:{ all -> 0x0b96 }
            int r7 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x0597
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzku r7 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = r7.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x0b96 }
            r5.zzg(r6, r7)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r5 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r6 = r5.length     // Catch:{ all -> 0x0b96 }
            r7 = 0
            r10 = 0
            r11 = 0
        L_0x0536:
            if (r7 >= r6) goto L_0x055c
            r15 = r5[r7]     // Catch:{ all -> 0x0b96 }
            r38 = r5
            java.lang.String r5 = "_c"
            r39 = r6
            java.lang.String r6 = r15.name     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x054a
            r11 = r15
            goto L_0x0555
        L_0x054a:
            java.lang.String r5 = "_err"
            java.lang.String r6 = r15.name     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x0555
            r10 = 1
        L_0x0555:
            int r7 = r7 + 1
            r5 = r38
            r6 = r39
            goto L_0x0536
        L_0x055c:
            if (r10 == 0) goto L_0x0571
            if (r11 == 0) goto L_0x0571
            com.google.android.gms.internal.measurement.zzks[] r5 = r14.zzava     // Catch:{ all -> 0x0b96 }
            r6 = 1
            com.google.android.gms.internal.measurement.zzks[] r7 = new com.google.android.gms.internal.measurement.zzks[r6]     // Catch:{ all -> 0x0b96 }
            r6 = 0
            r7[r6] = r11     // Catch:{ all -> 0x0b96 }
            java.lang.Object[] r5 = com.google.android.gms.common.util.ArrayUtils.removeAll(r5, r7)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r5 = (com.google.android.gms.internal.measurement.zzks[]) r5     // Catch:{ all -> 0x0b96 }
            r14.zzava = r5     // Catch:{ all -> 0x0b96 }
            goto L_0x0597
        L_0x0571:
            if (r11 == 0) goto L_0x0580
            java.lang.String r5 = "_err"
            r11.name = r5     // Catch:{ all -> 0x0b96 }
            r5 = 10
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0b96 }
            r11.zzave = r5     // Catch:{ all -> 0x0b96 }
            goto L_0x0597
        L_0x0580:
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzku r7 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = r7.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x0b96 }
            r5.zzg(r6, r7)     // Catch:{ all -> 0x0b96 }
        L_0x0597:
            if (r4 == 0) goto L_0x05ef
            java.lang.String r5 = "_e"
            java.lang.String r6 = r14.name     // Catch:{ all -> 0x0b96 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x05ef
            com.google.android.gms.internal.measurement.zzks[] r5 = r14.zzava     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x05da
            com.google.android.gms.internal.measurement.zzks[] r5 = r14.zzava     // Catch:{ all -> 0x0b96 }
            int r5 = r5.length     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x05ad
            goto L_0x05da
        L_0x05ad:
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = "_et"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzjz.zzb(r14, r5)     // Catch:{ all -> 0x0b96 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x05d2
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.zzku r7 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = r7.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x0b96 }
        L_0x05ce:
            r5.zzg(r6, r7)     // Catch:{ all -> 0x0b96 }
            goto L_0x05ef
        L_0x05d2:
            long r5 = r5.longValue()     // Catch:{ all -> 0x0b96 }
            r7 = 0
            long r10 = r12 + r5
            goto L_0x05f0
        L_0x05da:
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.zzku r7 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = r7.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x0b96 }
            goto L_0x05ce
        L_0x05ef:
            r10 = r12
        L_0x05f0:
            com.google.android.gms.internal.measurement.zzkr[] r5 = r3.zzavi     // Catch:{ all -> 0x0b96 }
            int r6 = r9 + 1
            r5[r9] = r14     // Catch:{ all -> 0x0b96 }
            r9 = r6
            r12 = r10
        L_0x05f8:
            int r7 = r28 + 1
            r10 = 0
            r11 = 1
            goto L_0x02b4
        L_0x05fe:
            r27 = r8
            java.util.List<com.google.android.gms.internal.measurement.zzkr> r5 = r2.zzasr     // Catch:{ all -> 0x0b96 }
            int r5 = r5.size()     // Catch:{ all -> 0x0b96 }
            if (r9 >= r5) goto L_0x0612
            com.google.android.gms.internal.measurement.zzkr[] r5 = r3.zzavi     // Catch:{ all -> 0x0b96 }
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r9)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r5 = (com.google.android.gms.internal.measurement.zzkr[]) r5     // Catch:{ all -> 0x0b96 }
            r3.zzavi = r5     // Catch:{ all -> 0x0b96 }
        L_0x0612:
            if (r4 == 0) goto L_0x06e2
            com.google.android.gms.internal.measurement.zzek r4 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r3.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "_lte"
            com.google.android.gms.internal.measurement.zzkc r4 = r4.zzh(r5, r6)     // Catch:{ all -> 0x0b96 }
            if (r4 == 0) goto L_0x064e
            java.lang.Object r5 = r4.value     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x0627
            goto L_0x064e
        L_0x0627:
            com.google.android.gms.internal.measurement.zzkc r5 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ all -> 0x0b96 }
            java.lang.String r15 = r3.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r16 = "auto"
            java.lang.String r17 = "_lte"
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbt()     // Catch:{ all -> 0x0b96 }
            long r18 = r6.currentTimeMillis()     // Catch:{ all -> 0x0b96 }
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x0b96 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x0b96 }
            long r6 = r4.longValue()     // Catch:{ all -> 0x0b96 }
            r4 = 0
            long r8 = r6 + r12
            java.lang.Long r20 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0b96 }
            r14 = r5
            r14.<init>(r15, r16, r17, r18, r20)     // Catch:{ all -> 0x0b96 }
            r4 = r5
            goto L_0x066b
        L_0x064e:
            com.google.android.gms.internal.measurement.zzkc r4 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r3.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r30 = "auto"
            java.lang.String r31 = "_lte"
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbt()     // Catch:{ all -> 0x0b96 }
            long r32 = r6.currentTimeMillis()     // Catch:{ all -> 0x0b96 }
            java.lang.Long r34 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0b96 }
            r28 = r4
            r29 = r5
            r28.<init>(r29, r30, r31, r32, r34)     // Catch:{ all -> 0x0b96 }
        L_0x066b:
            com.google.android.gms.internal.measurement.zzkx r5 = new com.google.android.gms.internal.measurement.zzkx     // Catch:{ all -> 0x0b96 }
            r5.<init>()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "_lte"
            r5.name = r6     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.common.util.Clock r6 = r6.zzbt()     // Catch:{ all -> 0x0b96 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x0b96 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r5.zzaws = r6     // Catch:{ all -> 0x0b96 }
            java.lang.Object r6 = r4.value     // Catch:{ all -> 0x0b96 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x0b96 }
            r5.zzave = r6     // Catch:{ all -> 0x0b96 }
            r6 = 0
        L_0x068b:
            com.google.android.gms.internal.measurement.zzkx[] r7 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            if (r6 >= r7) goto L_0x06a7
            java.lang.String r7 = "_lte"
            com.google.android.gms.internal.measurement.zzkx[] r8 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            r8 = r8[r6]     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x0b96 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0b96 }
            if (r7 == 0) goto L_0x06a4
            com.google.android.gms.internal.measurement.zzkx[] r7 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            r7[r6] = r5     // Catch:{ all -> 0x0b96 }
            r6 = 1
            goto L_0x06a8
        L_0x06a4:
            int r6 = r6 + 1
            goto L_0x068b
        L_0x06a7:
            r6 = 0
        L_0x06a8:
            if (r6 != 0) goto L_0x06c4
            com.google.android.gms.internal.measurement.zzkx[] r6 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkx[] r7 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkx[] r6 = (com.google.android.gms.internal.measurement.zzkx[]) r6     // Catch:{ all -> 0x0b96 }
            r3.zzavj = r6     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkx[] r6 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r7 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkx[] r7 = r7.zzavj     // Catch:{ all -> 0x0b96 }
            int r7 = r7.length     // Catch:{ all -> 0x0b96 }
            r8 = 1
            int r7 = r7 - r8
            r6[r7] = r5     // Catch:{ all -> 0x0b96 }
        L_0x06c4:
            r5 = 0
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x06e2
            com.google.android.gms.internal.measurement.zzek r5 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            r5.zza(r4)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zzjb()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Updated lifetime engagement user property with value. Value"
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x0b96 }
            r5.zzg(r6, r4)     // Catch:{ all -> 0x0b96 }
        L_0x06e2:
            java.lang.String r4 = r3.zzth     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkx[] r5 = r3.zzavj     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r6 = r3.zzavi     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkp[] r4 = r1.zza(r4, r5, r6)     // Catch:{ all -> 0x0b96 }
            r3.zzawa = r4     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzeh r4 = r4.zzgk()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r5 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r5.zzth     // Catch:{ all -> 0x0b96 }
            boolean r4 = r4.zzav(r5)     // Catch:{ all -> 0x0b96 }
            if (r4 == 0) goto L_0x09bb
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0b96 }
            r4.<init>()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r5 = r3.zzavi     // Catch:{ all -> 0x0b96 }
            int r5 = r5.length     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r5 = new com.google.android.gms.internal.measurement.zzkr[r5]     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgn r6 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkd r6 = r6.zzgg()     // Catch:{ all -> 0x0b96 }
            java.security.SecureRandom r6 = r6.zzlo()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r7 = r3.zzavi     // Catch:{ all -> 0x0b96 }
            int r8 = r7.length     // Catch:{ all -> 0x0b96 }
            r9 = 0
            r10 = 0
        L_0x0717:
            if (r9 >= r8) goto L_0x0989
            r11 = r7[r9]     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0b96 }
            java.lang.String r13 = "_ep"
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0b96 }
            if (r12 == 0) goto L_0x07a4
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = "_en"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzjz.zzb(r11, r12)     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0b96 }
            java.lang.Object r13 = r4.get(r12)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r13 = (com.google.android.gms.internal.measurement.zzet) r13     // Catch:{ all -> 0x0b96 }
            if (r13 != 0) goto L_0x0747
            com.google.android.gms.internal.measurement.zzek r13 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r14 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r14 = r14.zzth     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r13 = r13.zzf(r14, r12)     // Catch:{ all -> 0x0b96 }
            r4.put(r12, r13)     // Catch:{ all -> 0x0b96 }
        L_0x0747:
            java.lang.Long r12 = r13.zzahl     // Catch:{ all -> 0x0b96 }
            if (r12 != 0) goto L_0x079b
            java.lang.Long r12 = r13.zzahm     // Catch:{ all -> 0x0b96 }
            long r14 = r12.longValue()     // Catch:{ all -> 0x0b96 }
            r16 = 1
            int r12 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r12 <= 0) goto L_0x0766
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r12 = r11.zzava     // Catch:{ all -> 0x0b96 }
            java.lang.String r14 = "_sr"
            java.lang.Long r15 = r13.zzahm     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r12 = com.google.android.gms.internal.measurement.zzjz.zza(r12, r14, r15)     // Catch:{ all -> 0x0b96 }
            r11.zzava = r12     // Catch:{ all -> 0x0b96 }
        L_0x0766:
            java.lang.Boolean r12 = r13.zzahn     // Catch:{ all -> 0x0b96 }
            if (r12 == 0) goto L_0x0788
            java.lang.Boolean r12 = r13.zzahn     // Catch:{ all -> 0x0b96 }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x0b96 }
            if (r12 == 0) goto L_0x0788
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r12 = r11.zzava     // Catch:{ all -> 0x0b96 }
            java.lang.String r13 = "_efs"
            r40 = r7
            r14 = 1
            java.lang.Long r7 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r7 = com.google.android.gms.internal.measurement.zzjz.zza(r12, r13, r7)     // Catch:{ all -> 0x0b96 }
            r11.zzava = r7     // Catch:{ all -> 0x0b96 }
            goto L_0x078a
        L_0x0788:
            r40 = r7
        L_0x078a:
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b96 }
            r57 = r2
            r58 = r3
            r56 = r6
            r10 = r7
        L_0x0795:
            r41 = r8
        L_0x0797:
            r14 = 1
            goto L_0x097b
        L_0x079b:
            r40 = r7
            r57 = r2
            r58 = r3
            r56 = r6
            goto L_0x0795
        L_0x07a4:
            r40 = r7
            java.lang.String r7 = "_dbg"
            r12 = 1
            java.lang.Long r14 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0b96 }
            boolean r12 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0b96 }
            if (r12 != 0) goto L_0x07f8
            if (r14 != 0) goto L_0x07b7
            goto L_0x07f8
        L_0x07b7:
            com.google.android.gms.internal.measurement.zzks[] r12 = r11.zzava     // Catch:{ all -> 0x0b96 }
            int r13 = r12.length     // Catch:{ all -> 0x0b96 }
            r15 = 0
        L_0x07bb:
            if (r15 >= r13) goto L_0x07f8
            r41 = r8
            r8 = r12[r15]     // Catch:{ all -> 0x0b96 }
            r42 = r12
            java.lang.String r12 = r8.name     // Catch:{ all -> 0x0b96 }
            boolean r12 = r7.equals(r12)     // Catch:{ all -> 0x0b96 }
            if (r12 == 0) goto L_0x07f1
            boolean r7 = r14 instanceof java.lang.Long     // Catch:{ all -> 0x0b96 }
            if (r7 == 0) goto L_0x07d7
            java.lang.Long r7 = r8.zzave     // Catch:{ all -> 0x0b96 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x0b96 }
            if (r7 != 0) goto L_0x07ef
        L_0x07d7:
            boolean r7 = r14 instanceof java.lang.String     // Catch:{ all -> 0x0b96 }
            if (r7 == 0) goto L_0x07e3
            java.lang.String r7 = r8.zzale     // Catch:{ all -> 0x0b96 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x0b96 }
            if (r7 != 0) goto L_0x07ef
        L_0x07e3:
            boolean r7 = r14 instanceof java.lang.Double     // Catch:{ all -> 0x0b96 }
            if (r7 == 0) goto L_0x07fa
            java.lang.Double r7 = r8.zzasw     // Catch:{ all -> 0x0b96 }
            boolean r7 = r14.equals(r7)     // Catch:{ all -> 0x0b96 }
            if (r7 == 0) goto L_0x07fa
        L_0x07ef:
            r7 = 1
            goto L_0x07fb
        L_0x07f1:
            int r15 = r15 + 1
            r8 = r41
            r12 = r42
            goto L_0x07bb
        L_0x07f8:
            r41 = r8
        L_0x07fa:
            r7 = 0
        L_0x07fb:
            if (r7 != 0) goto L_0x080c
            com.google.android.gms.internal.measurement.zzgh r7 = r59.zzky()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r8 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r8 = r8.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0b96 }
            int r7 = r7.zzp(r8, r12)     // Catch:{ all -> 0x0b96 }
            goto L_0x080d
        L_0x080c:
            r7 = 1
        L_0x080d:
            if (r7 > 0) goto L_0x0831
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = "Sample rate must be positive. event, rate"
            java.lang.String r13 = r11.name     // Catch:{ all -> 0x0b96 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0b96 }
            r8.zze(r12, r13, r7)     // Catch:{ all -> 0x0b96 }
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b96 }
        L_0x0828:
            r57 = r2
            r58 = r3
            r56 = r6
            r10 = r7
            goto L_0x0797
        L_0x0831:
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0b96 }
            java.lang.Object r8 = r4.get(r8)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r8 = (com.google.android.gms.internal.measurement.zzet) r8     // Catch:{ all -> 0x0b96 }
            if (r8 != 0) goto L_0x0883
            com.google.android.gms.internal.measurement.zzek r8 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r12 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = r12.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r13 = r11.name     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r8 = r8.zzf(r12, r13)     // Catch:{ all -> 0x0b96 }
            if (r8 != 0) goto L_0x0883
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzku r13 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r13 = r13.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r14 = r11.name     // Catch:{ all -> 0x0b96 }
            r8.zze(r12, r13, r14)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r8 = new com.google.android.gms.internal.measurement.zzet     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r12 = r2.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = r12.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.String r13 = r11.name     // Catch:{ all -> 0x0b96 }
            r45 = 1
            r47 = 1
            java.lang.Long r14 = r11.zzavb     // Catch:{ all -> 0x0b96 }
            long r49 = r14.longValue()     // Catch:{ all -> 0x0b96 }
            r51 = 0
            r53 = 0
            r54 = 0
            r55 = 0
            r42 = r8
            r43 = r12
            r44 = r13
            r42.<init>(r43, r44, r45, r47, r49, r51, r53, r54, r55)     // Catch:{ all -> 0x0b96 }
        L_0x0883:
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            java.lang.String r12 = "_eid"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzjz.zzb(r11, r12)     // Catch:{ all -> 0x0b96 }
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ all -> 0x0b96 }
            if (r12 == 0) goto L_0x0892
            r13 = 1
            goto L_0x0893
        L_0x0892:
            r13 = 0
        L_0x0893:
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)     // Catch:{ all -> 0x0b96 }
            r14 = 1
            if (r7 != r14) goto L_0x08bc
            int r7 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b96 }
            boolean r10 = r13.booleanValue()     // Catch:{ all -> 0x0b96 }
            if (r10 == 0) goto L_0x0828
            java.lang.Long r10 = r8.zzahl     // Catch:{ all -> 0x0b96 }
            if (r10 != 0) goto L_0x08b0
            java.lang.Long r10 = r8.zzahm     // Catch:{ all -> 0x0b96 }
            if (r10 != 0) goto L_0x08b0
            java.lang.Boolean r10 = r8.zzahn     // Catch:{ all -> 0x0b96 }
            if (r10 == 0) goto L_0x0828
        L_0x08b0:
            r10 = 0
            com.google.android.gms.internal.measurement.zzet r8 = r8.zza(r10, r10, r10)     // Catch:{ all -> 0x0b96 }
            java.lang.String r10 = r11.name     // Catch:{ all -> 0x0b96 }
            r4.put(r10, r8)     // Catch:{ all -> 0x0b96 }
            goto L_0x0828
        L_0x08bc:
            int r14 = r6.nextInt(r7)     // Catch:{ all -> 0x0b96 }
            if (r14 != 0) goto L_0x08ff
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r12 = r11.zzava     // Catch:{ all -> 0x0b96 }
            java.lang.String r14 = "_sr"
            r56 = r6
            long r6 = (long) r7     // Catch:{ all -> 0x0b96 }
            java.lang.Long r15 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r12 = com.google.android.gms.internal.measurement.zzjz.zza(r12, r14, r15)     // Catch:{ all -> 0x0b96 }
            r11.zzava = r12     // Catch:{ all -> 0x0b96 }
            int r12 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b96 }
            boolean r10 = r13.booleanValue()     // Catch:{ all -> 0x0b96 }
            if (r10 == 0) goto L_0x08e9
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r7 = 0
            com.google.android.gms.internal.measurement.zzet r8 = r8.zza(r7, r6, r7)     // Catch:{ all -> 0x0b96 }
        L_0x08e9:
            java.lang.String r6 = r11.name     // Catch:{ all -> 0x0b96 }
            java.lang.Long r7 = r11.zzavb     // Catch:{ all -> 0x0b96 }
            long r10 = r7.longValue()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r7 = r8.zzai(r10)     // Catch:{ all -> 0x0b96 }
            r4.put(r6, r7)     // Catch:{ all -> 0x0b96 }
            r57 = r2
            r58 = r3
            r10 = r12
            goto L_0x0797
        L_0x08ff:
            r56 = r6
            long r14 = r8.zzahk     // Catch:{ all -> 0x0b96 }
            java.lang.Long r6 = r11.zzavb     // Catch:{ all -> 0x0b96 }
            long r16 = r6.longValue()     // Catch:{ all -> 0x0b96 }
            r6 = 0
            r57 = r2
            r58 = r3
            long r2 = r16 - r14
            long r2 = java.lang.Math.abs(r2)     // Catch:{ all -> 0x0b96 }
            r14 = 86400000(0x5265c00, double:4.2687272E-316)
            int r6 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r6 < 0) goto L_0x0969
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r2 = r11.zzava     // Catch:{ all -> 0x0b96 }
            java.lang.String r3 = "_efs"
            r14 = 1
            java.lang.Long r6 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r2 = com.google.android.gms.internal.measurement.zzjz.zza(r2, r3, r6)     // Catch:{ all -> 0x0b96 }
            r11.zzava = r2     // Catch:{ all -> 0x0b96 }
            r59.zzjf()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r2 = r11.zzava     // Catch:{ all -> 0x0b96 }
            java.lang.String r3 = "_sr"
            long r6 = (long) r7     // Catch:{ all -> 0x0b96 }
            java.lang.Long r12 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzks[] r2 = com.google.android.gms.internal.measurement.zzjz.zza(r2, r3, r12)     // Catch:{ all -> 0x0b96 }
            r11.zzava = r2     // Catch:{ all -> 0x0b96 }
            int r2 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0b96 }
            boolean r3 = r13.booleanValue()     // Catch:{ all -> 0x0b96 }
            if (r3 == 0) goto L_0x0958
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r6 = 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r6 = 0
            com.google.android.gms.internal.measurement.zzet r8 = r8.zza(r6, r3, r7)     // Catch:{ all -> 0x0b96 }
        L_0x0958:
            java.lang.String r3 = r11.name     // Catch:{ all -> 0x0b96 }
            java.lang.Long r6 = r11.zzavb     // Catch:{ all -> 0x0b96 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r6 = r8.zzai(r6)     // Catch:{ all -> 0x0b96 }
            r4.put(r3, r6)     // Catch:{ all -> 0x0b96 }
            r10 = r2
            goto L_0x097b
        L_0x0969:
            r14 = 1
            boolean r2 = r13.booleanValue()     // Catch:{ all -> 0x0b96 }
            if (r2 == 0) goto L_0x097b
            java.lang.String r2 = r11.name     // Catch:{ all -> 0x0b96 }
            r3 = 0
            com.google.android.gms.internal.measurement.zzet r6 = r8.zza(r12, r3, r3)     // Catch:{ all -> 0x0b96 }
            r4.put(r2, r6)     // Catch:{ all -> 0x0b96 }
        L_0x097b:
            int r9 = r9 + 1
            r7 = r40
            r8 = r41
            r6 = r56
            r2 = r57
            r3 = r58
            goto L_0x0717
        L_0x0989:
            r57 = r2
            r2 = r3
            com.google.android.gms.internal.measurement.zzkr[] r3 = r2.zzavi     // Catch:{ all -> 0x0b96 }
            int r3 = r3.length     // Catch:{ all -> 0x0b96 }
            if (r10 >= r3) goto L_0x0999
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r5, r10)     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkr[] r3 = (com.google.android.gms.internal.measurement.zzkr[]) r3     // Catch:{ all -> 0x0b96 }
            r2.zzavi = r3     // Catch:{ all -> 0x0b96 }
        L_0x0999:
            java.util.Set r3 = r4.entrySet()     // Catch:{ all -> 0x0b96 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0b96 }
        L_0x09a1:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0b96 }
            if (r4 == 0) goto L_0x09be
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0b96 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzek r5 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzet r4 = (com.google.android.gms.internal.measurement.zzet) r4     // Catch:{ all -> 0x0b96 }
            r5.zza(r4)     // Catch:{ all -> 0x0b96 }
            goto L_0x09a1
        L_0x09bb:
            r57 = r2
            r2 = r3
        L_0x09be:
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0b96 }
            r2.zzavl = r3     // Catch:{ all -> 0x0b96 }
            r3 = -9223372036854775808
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0b96 }
            r2.zzavm = r3     // Catch:{ all -> 0x0b96 }
            r3 = 0
        L_0x09d2:
            com.google.android.gms.internal.measurement.zzkr[] r4 = r2.zzavi     // Catch:{ all -> 0x0b96 }
            int r4 = r4.length     // Catch:{ all -> 0x0b96 }
            if (r3 >= r4) goto L_0x0a06
            com.google.android.gms.internal.measurement.zzkr[] r4 = r2.zzavi     // Catch:{ all -> 0x0b96 }
            r4 = r4[r3]     // Catch:{ all -> 0x0b96 }
            java.lang.Long r5 = r4.zzavb     // Catch:{ all -> 0x0b96 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0b96 }
            java.lang.Long r7 = r2.zzavl     // Catch:{ all -> 0x0b96 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0b96 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x09ef
            java.lang.Long r5 = r4.zzavb     // Catch:{ all -> 0x0b96 }
            r2.zzavl = r5     // Catch:{ all -> 0x0b96 }
        L_0x09ef:
            java.lang.Long r5 = r4.zzavb     // Catch:{ all -> 0x0b96 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0b96 }
            java.lang.Long r7 = r2.zzavm     // Catch:{ all -> 0x0b96 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0b96 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0a03
            java.lang.Long r4 = r4.zzavb     // Catch:{ all -> 0x0b96 }
            r2.zzavm = r4     // Catch:{ all -> 0x0b96 }
        L_0x0a03:
            int r3 = r3 + 1
            goto L_0x09d2
        L_0x0a06:
            r3 = r57
            com.google.android.gms.internal.measurement.zzku r4 = r3.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r4 = r4.zzth     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzek r5 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzea r5 = r5.zzbf(r4)     // Catch:{ all -> 0x0b96 }
            if (r5 != 0) goto L_0x0a2e
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziv()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzku r7 = r3.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = r7.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x0b96 }
            r5.zzg(r6, r7)     // Catch:{ all -> 0x0b96 }
            goto L_0x0a8a
        L_0x0a2e:
            com.google.android.gms.internal.measurement.zzkr[] r6 = r2.zzavi     // Catch:{ all -> 0x0b96 }
            int r6 = r6.length     // Catch:{ all -> 0x0b96 }
            if (r6 <= 0) goto L_0x0a8a
            long r6 = r5.zzgt()     // Catch:{ all -> 0x0b96 }
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L_0x0a42
            java.lang.Long r8 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            goto L_0x0a43
        L_0x0a42:
            r8 = 0
        L_0x0a43:
            r2.zzavo = r8     // Catch:{ all -> 0x0b96 }
            long r8 = r5.zzgs()     // Catch:{ all -> 0x0b96 }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0a50
            goto L_0x0a51
        L_0x0a50:
            r6 = r8
        L_0x0a51:
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0a5a
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            goto L_0x0a5b
        L_0x0a5a:
            r6 = 0
        L_0x0a5b:
            r2.zzavn = r6     // Catch:{ all -> 0x0b96 }
            r5.zzhb()     // Catch:{ all -> 0x0b96 }
            long r6 = r5.zzgy()     // Catch:{ all -> 0x0b96 }
            int r6 = (int) r6     // Catch:{ all -> 0x0b96 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0b96 }
            r2.zzavy = r6     // Catch:{ all -> 0x0b96 }
            java.lang.Long r6 = r2.zzavl     // Catch:{ all -> 0x0b96 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0b96 }
            r5.zzr(r6)     // Catch:{ all -> 0x0b96 }
            java.lang.Long r6 = r2.zzavm     // Catch:{ all -> 0x0b96 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0b96 }
            r5.zzs(r6)     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r5.zzhj()     // Catch:{ all -> 0x0b96 }
            r2.zzafy = r6     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzek r6 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            r6.zza(r5)     // Catch:{ all -> 0x0b96 }
        L_0x0a8a:
            com.google.android.gms.internal.measurement.zzkr[] r5 = r2.zzavi     // Catch:{ all -> 0x0b96 }
            int r5 = r5.length     // Catch:{ all -> 0x0b96 }
            if (r5 <= 0) goto L_0x0add
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            r5.zzgl()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzgh r5 = r59.zzky()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzku r6 = r3.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = r6.zzth     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzkn r5 = r5.zzbx(r6)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x0aac
            java.lang.Long r6 = r5.zzaum     // Catch:{ all -> 0x0b96 }
            if (r6 != 0) goto L_0x0aa7
            goto L_0x0aac
        L_0x0aa7:
            java.lang.Long r5 = r5.zzaum     // Catch:{ all -> 0x0b96 }
        L_0x0aa9:
            r2.zzawf = r5     // Catch:{ all -> 0x0b96 }
            goto L_0x0ad4
        L_0x0aac:
            com.google.android.gms.internal.measurement.zzku r5 = r3.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = r5.zzafa     // Catch:{ all -> 0x0b96 }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0b96 }
            if (r5 == 0) goto L_0x0abd
            r5 = -1
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0b96 }
            goto L_0x0aa9
        L_0x0abd:
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzku r7 = r3.zzasp     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = r7.zzth     // Catch:{ all -> 0x0b96 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x0b96 }
            r5.zzg(r6, r7)     // Catch:{ all -> 0x0b96 }
        L_0x0ad4:
            com.google.android.gms.internal.measurement.zzek r5 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            r10 = r27
            r5.zza(r2, r10)     // Catch:{ all -> 0x0b96 }
        L_0x0add:
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            java.util.List<java.lang.Long> r3 = r3.zzasq     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0b96 }
            r2.zzab()     // Catch:{ all -> 0x0b96 }
            r2.zzch()     // Catch:{ all -> 0x0b96 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "rowid in ("
            r5.<init>(r6)     // Catch:{ all -> 0x0b96 }
            r6 = 0
        L_0x0af4:
            int r7 = r3.size()     // Catch:{ all -> 0x0b96 }
            if (r6 >= r7) goto L_0x0b11
            if (r6 == 0) goto L_0x0b01
            java.lang.String r7 = ","
            r5.append(r7)     // Catch:{ all -> 0x0b96 }
        L_0x0b01:
            java.lang.Object r7 = r3.get(r6)     // Catch:{ all -> 0x0b96 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x0b96 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0b96 }
            r5.append(r7)     // Catch:{ all -> 0x0b96 }
            int r6 = r6 + 1
            goto L_0x0af4
        L_0x0b11:
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch:{ all -> 0x0b96 }
            android.database.sqlite.SQLiteDatabase r6 = r2.getWritableDatabase()     // Catch:{ all -> 0x0b96 }
            java.lang.String r7 = "raw_events"
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0b96 }
            r8 = 0
            int r5 = r6.delete(r7, r5, r8)     // Catch:{ all -> 0x0b96 }
            int r6 = r3.size()     // Catch:{ all -> 0x0b96 }
            if (r5 == r6) goto L_0x0b44
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x0b96 }
            java.lang.String r6 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0b96 }
            int r3 = r3.size()     // Catch:{ all -> 0x0b96 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0b96 }
            r2.zze(r6, r5, r3)     // Catch:{ all -> 0x0b96 }
        L_0x0b44:
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            android.database.sqlite.SQLiteDatabase r3 = r2.getWritableDatabase()     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r6 = 2
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0b5b }
            r7 = 0
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x0b5b }
            r7 = 1
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x0b5b }
            r3.execSQL(r5, r6)     // Catch:{ SQLiteException -> 0x0b5b }
            goto L_0x0b6e
        L_0x0b5b:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()     // Catch:{ all -> 0x0b96 }
            java.lang.String r5 = "Failed to remove unused event metadata. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfi.zzbp(r4)     // Catch:{ all -> 0x0b96 }
            r2.zze(r5, r4, r3)     // Catch:{ all -> 0x0b96 }
        L_0x0b6e:
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()
            r2.endTransaction()
            r2 = 1
            return r2
        L_0x0b7e:
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()     // Catch:{ all -> 0x0b96 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x0b96 }
            com.google.android.gms.internal.measurement.zzek r2 = r59.zzjh()
            r2.endTransaction()
            r2 = 0
            return r2
        L_0x0b8e:
            r0 = move-exception
        L_0x0b8f:
            r2 = r0
        L_0x0b90:
            if (r6 == 0) goto L_0x0b95
            r6.close()     // Catch:{ all -> 0x0b96 }
        L_0x0b95:
            throw r2     // Catch:{ all -> 0x0b96 }
        L_0x0b96:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.zzek r3 = r59.zzjh()
            r3.endTransaction()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzd(java.lang.String, long):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzea zzg(com.google.android.gms.internal.measurement.zzeb r9) {
        /*
            r8 = this;
            r8.zzab()
            r8.zzlc()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            java.lang.String r0 = r9.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            com.google.android.gms.internal.measurement.zzek r0 = r8.zzjh()
            java.lang.String r1 = r9.packageName
            com.google.android.gms.internal.measurement.zzea r0 = r0.zzbf(r1)
            com.google.android.gms.internal.measurement.zzgn r1 = r8.zzacv
            com.google.android.gms.internal.measurement.zzft r1 = r1.zzgj()
            java.lang.String r2 = r9.packageName
            java.lang.String r1 = r1.zzbs(r2)
            r2 = 1
            if (r0 != 0) goto L_0x0042
            com.google.android.gms.internal.measurement.zzea r0 = new com.google.android.gms.internal.measurement.zzea
            com.google.android.gms.internal.measurement.zzgn r3 = r8.zzacv
            java.lang.String r4 = r9.packageName
            r0.<init>(r3, r4)
            com.google.android.gms.internal.measurement.zzgn r3 = r8.zzacv
            com.google.android.gms.internal.measurement.zzfd r3 = r3.zzfz()
            java.lang.String r3 = r3.zzir()
            r0.zzam(r3)
            r0.zzao(r1)
        L_0x0040:
            r1 = 1
            goto L_0x005e
        L_0x0042:
            java.lang.String r3 = r0.zzgq()
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x005d
            r0.zzao(r1)
            com.google.android.gms.internal.measurement.zzgn r1 = r8.zzacv
            com.google.android.gms.internal.measurement.zzfd r1 = r1.zzfz()
            java.lang.String r1 = r1.zzir()
            r0.zzam(r1)
            goto L_0x0040
        L_0x005d:
            r1 = 0
        L_0x005e:
            java.lang.String r3 = r9.zzafa
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0078
            java.lang.String r3 = r9.zzafa
            java.lang.String r4 = r0.getGmpAppId()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0078
            java.lang.String r1 = r9.zzafa
            r0.zzan(r1)
            r1 = 1
        L_0x0078:
            java.lang.String r3 = r9.zzafc
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0092
            java.lang.String r3 = r9.zzafc
            java.lang.String r4 = r0.zzgr()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0092
            java.lang.String r1 = r9.zzafc
            r0.zzap(r1)
            r1 = 1
        L_0x0092:
            long r3 = r9.zzafi
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00aa
            long r3 = r9.zzafi
            long r5 = r0.zzgw()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00aa
            long r3 = r9.zzafi
            r0.zzu(r3)
            r1 = 1
        L_0x00aa:
            java.lang.String r3 = r9.zztg
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00c4
            java.lang.String r3 = r9.zztg
            java.lang.String r4 = r0.zzag()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00c4
            java.lang.String r1 = r9.zztg
            r0.setAppVersion(r1)
            r1 = 1
        L_0x00c4:
            long r3 = r9.zzafg
            long r5 = r0.zzgu()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00d4
            long r3 = r9.zzafg
            r0.zzt(r3)
            r1 = 1
        L_0x00d4:
            java.lang.String r3 = r9.zzafh
            if (r3 == 0) goto L_0x00ea
            java.lang.String r3 = r9.zzafh
            java.lang.String r4 = r0.zzgv()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00ea
            java.lang.String r1 = r9.zzafh
            r0.zzaq(r1)
            r1 = 1
        L_0x00ea:
            long r3 = r9.zzafj
            long r5 = r0.zzgx()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00fa
            long r3 = r9.zzafj
            r0.zzv(r3)
            r1 = 1
        L_0x00fa:
            boolean r3 = r9.zzafk
            boolean r4 = r0.isMeasurementEnabled()
            if (r3 == r4) goto L_0x0108
            boolean r1 = r9.zzafk
            r0.setMeasurementEnabled(r1)
            r1 = 1
        L_0x0108:
            java.lang.String r3 = r9.zzafy
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0122
            java.lang.String r3 = r9.zzafy
            java.lang.String r4 = r0.zzhi()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0122
            java.lang.String r1 = r9.zzafy
            r0.zzar(r1)
            r1 = 1
        L_0x0122:
            long r3 = r9.zzafl
            long r5 = r0.zzhk()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0132
            long r3 = r9.zzafl
            r0.zzaf(r3)
            r1 = 1
        L_0x0132:
            boolean r3 = r9.zzafm
            boolean r4 = r0.zzhl()
            if (r3 == r4) goto L_0x0140
            boolean r1 = r9.zzafm
            r0.zzd(r1)
            r1 = 1
        L_0x0140:
            boolean r3 = r9.zzafn
            boolean r4 = r0.zzhm()
            if (r3 == r4) goto L_0x014e
            boolean r9 = r9.zzafn
            r0.zze(r9)
            r1 = 1
        L_0x014e:
            if (r1 == 0) goto L_0x0157
            com.google.android.gms.internal.measurement.zzek r9 = r8.zzjh()
            r9.zza(r0)
        L_0x0157:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzg(com.google.android.gms.internal.measurement.zzeb):com.google.android.gms.internal.measurement.zzea");
    }

    public static zzjt zzg(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzarr == null) {
            synchronized (zzjt.class) {
                if (zzarr == null) {
                    zzarr = new zzjt(new zzjy(context));
                }
            }
        }
        return zzarr;
    }

    private final zzgh zzky() {
        zza((zzjs) this.zzars);
        return this.zzars;
    }

    private final zzfr zzla() {
        if (this.zzarv != null) {
            return this.zzarv;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjp zzlb() {
        zza((zzjs) this.zzarw);
        return this.zzarw;
    }

    private final long zzld() {
        long currentTimeMillis = this.zzacv.zzbt().currentTimeMillis();
        zzft zzgj = this.zzacv.zzgj();
        zzgj.zzch();
        zzgj.zzab();
        long j = zzgj.zzalx.get();
        if (j == 0) {
            long nextInt = ((long) zzgj.zzgg().zzlo().nextInt(86400000)) + 1;
            zzgj.zzalx.set(nextInt);
            j = nextInt;
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzlf() {
        zzab();
        zzlc();
        return zzjh().zzia() || !TextUtils.isEmpty(zzjh().zzhv());
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzlg() {
        /*
            r20 = this;
            r0 = r20
            r20.zzab()
            r20.zzlc()
            boolean r1 = r20.zzlk()
            if (r1 != 0) goto L_0x000f
            return
        L_0x000f:
            long r1 = r0.zzasa
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0056
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.common.util.Clock r1 = r1.zzbt()
            long r1 = r1.elapsedRealtime()
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r0.zzasa
            long r9 = r1 - r7
            long r1 = java.lang.Math.abs(r9)
            long r7 = r5 - r1
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0054
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Upload has been suspended. Will update scheduling later in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            r1.zzg(r2, r3)
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzla()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzjp r1 = r20.zzlb()
            r1.cancel()
            return
        L_0x0054:
            r0.zzasa = r3
        L_0x0056:
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            boolean r1 = r1.zzkg()
            if (r1 == 0) goto L_0x0249
            boolean r1 = r20.zzlf()
            if (r1 != 0) goto L_0x0066
            goto L_0x0249
        L_0x0066:
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.common.util.Clock r1 = r1.zzbt()
            long r1 = r1.currentTimeMillis()
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r5 = com.google.android.gms.internal.measurement.zzez.zzaje
            java.lang.Object r5 = r5.get()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.measurement.zzek r7 = r20.zzjh()
            boolean r7 = r7.zzib()
            if (r7 != 0) goto L_0x0097
            com.google.android.gms.internal.measurement.zzek r7 = r20.zzjh()
            boolean r7 = r7.zzhw()
            if (r7 == 0) goto L_0x0095
            goto L_0x0097
        L_0x0095:
            r7 = 0
            goto L_0x0098
        L_0x0097:
            r7 = 1
        L_0x0098:
            if (r7 == 0) goto L_0x00b8
            com.google.android.gms.internal.measurement.zzgn r9 = r0.zzacv
            com.google.android.gms.internal.measurement.zzeh r9 = r9.zzgk()
            java.lang.String r9 = r9.zzhs()
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 != 0) goto L_0x00b5
            java.lang.String r10 = ".none."
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00b5
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r9 = com.google.android.gms.internal.measurement.zzez.zzaiz
            goto L_0x00ba
        L_0x00b5:
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r9 = com.google.android.gms.internal.measurement.zzez.zzaiy
            goto L_0x00ba
        L_0x00b8:
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r9 = com.google.android.gms.internal.measurement.zzez.zzaix
        L_0x00ba:
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            long r9 = r9.longValue()
            long r9 = java.lang.Math.max(r3, r9)
            com.google.android.gms.internal.measurement.zzgn r11 = r0.zzacv
            com.google.android.gms.internal.measurement.zzft r11 = r11.zzgj()
            com.google.android.gms.internal.measurement.zzfw r11 = r11.zzalt
            long r11 = r11.get()
            com.google.android.gms.internal.measurement.zzgn r13 = r0.zzacv
            com.google.android.gms.internal.measurement.zzft r13 = r13.zzgj()
            com.google.android.gms.internal.measurement.zzfw r13 = r13.zzalu
            long r13 = r13.get()
            com.google.android.gms.internal.measurement.zzek r15 = r20.zzjh()
            r16 = r9
            long r8 = r15.zzhy()
            com.google.android.gms.internal.measurement.zzek r10 = r20.zzjh()
            r18 = r5
            long r5 = r10.zzhz()
            long r5 = java.lang.Math.max(r8, r5)
            int r8 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x00ff
        L_0x00fc:
            r5 = r3
            goto L_0x017c
        L_0x00ff:
            r8 = 0
            long r8 = r5 - r1
            long r5 = java.lang.Math.abs(r8)
            long r8 = r1 - r5
            long r5 = r11 - r1
            long r5 = java.lang.Math.abs(r5)
            long r10 = r1 - r5
            long r5 = r13 - r1
            long r5 = java.lang.Math.abs(r5)
            long r12 = r1 - r5
            long r1 = java.lang.Math.max(r10, r12)
            long r5 = r8 + r18
            if (r7 == 0) goto L_0x012b
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x012b
            long r5 = java.lang.Math.min(r8, r1)
            long r10 = r5 + r16
            r5 = r10
        L_0x012b:
            com.google.android.gms.internal.measurement.zzjz r7 = r20.zzjf()
            r10 = r16
            boolean r7 = r7.zza(r1, r10)
            if (r7 != 0) goto L_0x0139
            long r5 = r1 + r10
        L_0x0139:
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x017c
            int r1 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x017c
            r1 = 0
        L_0x0142:
            r2 = 20
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzez.zzajg
            java.lang.Object r7 = r7.get()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r8 = 0
            int r7 = java.lang.Math.max(r8, r7)
            int r2 = java.lang.Math.min(r2, r7)
            if (r1 >= r2) goto L_0x00fc
            r9 = 1
            long r9 = r9 << r1
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r2 = com.google.android.gms.internal.measurement.zzez.zzajf
            java.lang.Object r2 = r2.get()
            java.lang.Long r2 = (java.lang.Long) r2
            long r14 = r2.longValue()
            long r14 = java.lang.Math.max(r3, r14)
            long r14 = r14 * r9
            long r9 = r5 + r14
            int r2 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r2 <= 0) goto L_0x0178
            r5 = r9
            goto L_0x017c
        L_0x0178:
            int r1 = r1 + 1
            r5 = r9
            goto L_0x0142
        L_0x017c:
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x019e
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Next upload time is 0"
            r1.log(r2)
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzla()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzjp r1 = r20.zzlb()
            r1.cancel()
            return
        L_0x019e:
            com.google.android.gms.internal.measurement.zzfm r1 = r20.zzkz()
            boolean r1 = r1.zzex()
            if (r1 != 0) goto L_0x01c6
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "No network"
            r1.log(r2)
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzla()
            r1.zzeu()
            com.google.android.gms.internal.measurement.zzjp r1 = r20.zzlb()
            r1.cancel()
            return
        L_0x01c6:
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzft r1 = r1.zzgj()
            com.google.android.gms.internal.measurement.zzfw r1 = r1.zzalv
            long r1 = r1.get()
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r7 = com.google.android.gms.internal.measurement.zzez.zzaiv
            java.lang.Object r7 = r7.get()
            java.lang.Long r7 = (java.lang.Long) r7
            long r7 = r7.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.internal.measurement.zzjz r9 = r20.zzjf()
            boolean r9 = r9.zza(r1, r7)
            if (r9 != 0) goto L_0x01f2
            long r9 = r1 + r7
            long r5 = java.lang.Math.max(r5, r9)
        L_0x01f2:
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzla()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.common.util.Clock r1 = r1.zzbt()
            long r1 = r1.currentTimeMillis()
            long r7 = r5 - r1
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x022e
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Long> r1 = com.google.android.gms.internal.measurement.zzez.zzaja
            java.lang.Object r1 = r1.get()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r7 = java.lang.Math.max(r3, r1)
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzft r1 = r1.zzgj()
            com.google.android.gms.internal.measurement.zzfw r1 = r1.zzalt
            com.google.android.gms.internal.measurement.zzgn r2 = r0.zzacv
            com.google.android.gms.common.util.Clock r2 = r2.zzbt()
            long r2 = r2.currentTimeMillis()
            r1.set(r2)
        L_0x022e:
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Upload scheduled in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            r1.zzg(r2, r3)
            com.google.android.gms.internal.measurement.zzjp r1 = r20.zzlb()
            r1.zzh(r7)
            return
        L_0x0249:
            com.google.android.gms.internal.measurement.zzgn r1 = r0.zzacv
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.log(r2)
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzla()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzjp r1 = r20.zzlb()
            r1.cancel()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzlg():void");
    }

    private final void zzlh() {
        zzab();
        if (this.zzase || this.zzasf || this.zzasg) {
            this.zzacv.zzgi().zzjc().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzase), Boolean.valueOf(this.zzasf), Boolean.valueOf(this.zzasg));
            return;
        }
        this.zzacv.zzgi().zzjc().log("Stopping uploading service(s)");
        if (this.zzasb != null) {
            for (Runnable run : this.zzasb) {
                run.run();
            }
            this.zzasb.clear();
        }
    }

    private final boolean zzli() {
        String str;
        zzfk zzfk;
        zzab();
        try {
            this.zzasi = new RandomAccessFile(new File(this.zzacv.getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzash = this.zzasi.tryLock();
            if (this.zzash != null) {
                this.zzacv.zzgi().zzjc().log("Storage concurrent access okay");
                return true;
            }
            this.zzacv.zzgi().zziv().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            e = e;
            zzfk = this.zzacv.zzgi().zziv();
            str = "Failed to acquire storage lock";
            zzfk.zzg(str, e);
            return false;
        } catch (IOException e2) {
            e = e2;
            zzfk = this.zzacv.zzgi().zziv();
            str = "Failed to access storage lock file";
            zzfk.zzg(str, e);
            return false;
        }
    }

    private final boolean zzlk() {
        zzab();
        zzlc();
        return this.zzarz;
    }

    public final Context getContext() {
        return this.zzacv.getContext();
    }

    /* access modifiers changed from: protected */
    public final void start() {
        this.zzacv.zzgh().zzab();
        zzjh().zzhx();
        if (this.zzacv.zzgj().zzalt.get() == 0) {
            this.zzacv.zzgj().zzalt.set(this.zzacv.zzbt().currentTimeMillis());
        }
        zzlg();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzek zzjh;
        zzab();
        zzlc();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzasf = false;
                zzlh();
                throw th2;
            }
        }
        List<Long> list = this.zzasj;
        this.zzasj = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                this.zzacv.zzgj().zzalt.set(this.zzacv.zzbt().currentTimeMillis());
                this.zzacv.zzgj().zzalu.set(0);
                zzlg();
                this.zzacv.zzgi().zzjc().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzjh().beginTransaction();
                try {
                    for (Long l : list) {
                        try {
                            zzjh = zzjh();
                            long longValue = l.longValue();
                            zzjh.zzab();
                            zzjh.zzch();
                            if (zzjh.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzjh.zzgi().zziv().zzg("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzask == null || !this.zzask.contains(l)) {
                                throw e2;
                            }
                        }
                    }
                    zzjh().setTransactionSuccessful();
                    zzjh().endTransaction();
                    this.zzask = null;
                    if (!zzkz().zzex() || !zzlf()) {
                        this.zzasl = -1;
                        zzlg();
                    } else {
                        zzle();
                    }
                    this.zzasa = 0;
                } catch (Throwable th3) {
                    zzjh().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                this.zzacv.zzgi().zziv().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzasa = this.zzacv.zzbt().elapsedRealtime();
                this.zzacv.zzgi().zzjc().zzg("Disable upload, time", Long.valueOf(this.zzasa));
            }
        } else {
            this.zzacv.zzgi().zzjc().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            this.zzacv.zzgj().zzalu.set(this.zzacv.zzbt().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                this.zzacv.zzgj().zzalv.set(this.zzacv.zzbt().currentTimeMillis());
            }
            if (this.zzacv.zzgk().zzay(str)) {
                zzjh().zzc(list);
            }
            zzlg();
        }
        this.zzasf = false;
        zzlh();
    }

    /* JADX WARNING: type inference failed for: r18v1 */
    /* JADX WARNING: type inference failed for: r18v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zza(com.google.android.gms.internal.measurement.zzex r33, java.lang.String r34) {
        /*
            r32 = this;
            r1 = r32
            r2 = r33
            r15 = r34
            r32.zzlc()
            r32.zzab()
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv
            r3.zzfu()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            com.google.android.gms.internal.measurement.zzkt r14 = new com.google.android.gms.internal.measurement.zzkt
            r14.<init>()
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()
            r3.beginTransaction()
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzea r12 = r3.zzbf(r15)     // Catch:{ all -> 0x04f7 }
            r13 = 0
            if (r12 != 0) goto L_0x0047
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjb()     // Catch:{ all -> 0x04f7 }
            java.lang.String r3 = "Log and bundle not available. package_name"
            r2.zzg(r3, r15)     // Catch:{ all -> 0x04f7 }
        L_0x003d:
            byte[] r2 = new byte[r13]     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()
            r3.endTransaction()
            return r2
        L_0x0047:
            boolean r3 = r12.isMeasurementEnabled()     // Catch:{ all -> 0x04f7 }
            if (r3 != 0) goto L_0x005d
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzgi()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjb()     // Catch:{ all -> 0x04f7 }
            java.lang.String r3 = "Log and bundle disabled. package_name"
            r2.zzg(r3, r15)     // Catch:{ all -> 0x04f7 }
            goto L_0x003d
        L_0x005d:
            java.lang.String r3 = "_iap"
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f7 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04f7 }
            if (r3 != 0) goto L_0x0071
            java.lang.String r3 = "ecommerce_purchase"
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f7 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04f7 }
            if (r3 == 0) goto L_0x008a
        L_0x0071:
            boolean r3 = r1.zza(r15, r2)     // Catch:{ all -> 0x04f7 }
            if (r3 != 0) goto L_0x008a
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziy()     // Catch:{ all -> 0x04f7 }
            java.lang.String r4 = "Failed to handle purchase event at single event bundle creation. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r34)     // Catch:{ all -> 0x04f7 }
            r3.zzg(r4, r5)     // Catch:{ all -> 0x04f7 }
        L_0x008a:
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzeh r3 = r3.zzgk()     // Catch:{ all -> 0x04f7 }
            boolean r3 = r3.zzaw(r15)     // Catch:{ all -> 0x04f7 }
            r10 = 0
            java.lang.Long r4 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x04f7 }
            if (r3 == 0) goto L_0x00eb
            java.lang.String r5 = "_e"
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x04f7 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x04f7 }
            if (r5 == 0) goto L_0x00eb
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x04f7 }
            if (r5 == 0) goto L_0x00da
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x04f7 }
            int r5 = r5.size()     // Catch:{ all -> 0x04f7 }
            if (r5 != 0) goto L_0x00b3
            goto L_0x00da
        L_0x00b3:
            com.google.android.gms.internal.measurement.zzeu r5 = r2.zzahg     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = "_et"
            java.lang.Long r5 = r5.getLong(r6)     // Catch:{ all -> 0x04f7 }
            if (r5 != 0) goto L_0x00d1
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = "The engagement event does not include duration. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r34)     // Catch:{ all -> 0x04f7 }
        L_0x00cd:
            r5.zzg(r6, r7)     // Catch:{ all -> 0x04f7 }
            goto L_0x00eb
        L_0x00d1:
            com.google.android.gms.internal.measurement.zzeu r4 = r2.zzahg     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = "_et"
            java.lang.Long r4 = r4.getLong(r5)     // Catch:{ all -> 0x04f7 }
            goto L_0x00eb
        L_0x00da:
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfk r5 = r5.zziy()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = "The engagement event does not contain any parameters. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r34)     // Catch:{ all -> 0x04f7 }
            goto L_0x00cd
        L_0x00eb:
            com.google.android.gms.internal.measurement.zzku r8 = new com.google.android.gms.internal.measurement.zzku     // Catch:{ all -> 0x04f7 }
            r8.<init>()     // Catch:{ all -> 0x04f7 }
            r9 = 1
            com.google.android.gms.internal.measurement.zzku[] r5 = new com.google.android.gms.internal.measurement.zzku[r9]     // Catch:{ all -> 0x04f7 }
            r5[r13] = r8     // Catch:{ all -> 0x04f7 }
            r14.zzavf = r5     // Catch:{ all -> 0x04f7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x04f7 }
            r8.zzavh = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = "android"
            r8.zzavp = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r12.zzah()     // Catch:{ all -> 0x04f7 }
            r8.zzth = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r12.zzgv()     // Catch:{ all -> 0x04f7 }
            r8.zzafh = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r12.zzag()     // Catch:{ all -> 0x04f7 }
            r8.zztg = r5     // Catch:{ all -> 0x04f7 }
            long r5 = r12.zzgu()     // Catch:{ all -> 0x04f7 }
            r16 = -2147483648(0xffffffff80000000, double:NaN)
            int r7 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            r23 = r14
            r14 = 0
            if (r7 != 0) goto L_0x0123
            r5 = r14
            goto L_0x0128
        L_0x0123:
            int r5 = (int) r5     // Catch:{ all -> 0x04f7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x04f7 }
        L_0x0128:
            r8.zzawb = r5     // Catch:{ all -> 0x04f7 }
            long r5 = r12.zzgw()     // Catch:{ all -> 0x04f7 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04f7 }
            r8.zzavt = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r12.getGmpAppId()     // Catch:{ all -> 0x04f7 }
            r8.zzafa = r5     // Catch:{ all -> 0x04f7 }
            long r5 = r12.zzgx()     // Catch:{ all -> 0x04f7 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04f7 }
            r8.zzavx = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            boolean r5 = r5.isEnabled()     // Catch:{ all -> 0x04f7 }
            if (r5 == 0) goto L_0x0162
            boolean r5 = com.google.android.gms.internal.measurement.zzeh.zzht()     // Catch:{ all -> 0x04f7 }
            if (r5 == 0) goto L_0x0162
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzeh r5 = r5.zzgk()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = r8.zzth     // Catch:{ all -> 0x04f7 }
            boolean r5 = r5.zzau(r6)     // Catch:{ all -> 0x04f7 }
            if (r5 == 0) goto L_0x0162
            r8.zzawh = r14     // Catch:{ all -> 0x04f7 }
        L_0x0162:
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzft r5 = r5.zzgj()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = r12.zzah()     // Catch:{ all -> 0x04f7 }
            android.util.Pair r5 = r5.zzbr(r6)     // Catch:{ all -> 0x04f7 }
            boolean r6 = r12.zzhl()     // Catch:{ all -> 0x04f7 }
            if (r6 == 0) goto L_0x018e
            if (r5 == 0) goto L_0x018e
            java.lang.Object r6 = r5.first     // Catch:{ all -> 0x04f7 }
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x04f7 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x04f7 }
            if (r6 != 0) goto L_0x018e
            java.lang.Object r6 = r5.first     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x04f7 }
            r8.zzavv = r6     // Catch:{ all -> 0x04f7 }
            java.lang.Object r5 = r5.second     // Catch:{ all -> 0x04f7 }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x04f7 }
            r8.zzavw = r5     // Catch:{ all -> 0x04f7 }
        L_0x018e:
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzer r5 = r5.zzge()     // Catch:{ all -> 0x04f7 }
            r5.zzch()     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = android.os.Build.MODEL     // Catch:{ all -> 0x04f7 }
            r8.zzavr = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzer r5 = r5.zzge()     // Catch:{ all -> 0x04f7 }
            r5.zzch()     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x04f7 }
            r8.zzavq = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzer r5 = r5.zzge()     // Catch:{ all -> 0x04f7 }
            long r5 = r5.zzik()     // Catch:{ all -> 0x04f7 }
            int r5 = (int) r5     // Catch:{ all -> 0x04f7 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x04f7 }
            r8.zzavs = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzer r5 = r5.zzge()     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r5.zzil()     // Catch:{ all -> 0x04f7 }
            r8.zzahd = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r12.getAppInstanceId()     // Catch:{ all -> 0x04f7 }
            r8.zzaez = r5     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r12.zzgr()     // Catch:{ all -> 0x04f7 }
            r8.zzafc = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r5 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = r12.zzah()     // Catch:{ all -> 0x04f7 }
            java.util.List r5 = r5.zzbe(r6)     // Catch:{ all -> 0x04f7 }
            int r6 = r5.size()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r6 = new com.google.android.gms.internal.measurement.zzkx[r6]     // Catch:{ all -> 0x04f7 }
            r8.zzavj = r6     // Catch:{ all -> 0x04f7 }
            if (r3 == 0) goto L_0x024c
            com.google.android.gms.internal.measurement.zzek r6 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            java.lang.String r7 = r8.zzth     // Catch:{ all -> 0x04f7 }
            java.lang.String r13 = "_lte"
            com.google.android.gms.internal.measurement.zzkc r6 = r6.zzh(r7, r13)     // Catch:{ all -> 0x04f7 }
            if (r6 == 0) goto L_0x0230
            java.lang.Object r7 = r6.value     // Catch:{ all -> 0x04f7 }
            if (r7 != 0) goto L_0x01fa
            goto L_0x0230
        L_0x01fa:
            long r16 = r4.longValue()     // Catch:{ all -> 0x04f7 }
            int r7 = (r16 > r10 ? 1 : (r16 == r10 ? 0 : -1))
            if (r7 <= 0) goto L_0x024d
            com.google.android.gms.internal.measurement.zzkc r7 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ all -> 0x04f7 }
            java.lang.String r13 = r8.zzth     // Catch:{ all -> 0x04f7 }
            java.lang.String r18 = "auto"
            java.lang.String r19 = "_lte"
            com.google.android.gms.internal.measurement.zzgn r14 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.common.util.Clock r14 = r14.zzbt()     // Catch:{ all -> 0x04f7 }
            long r20 = r14.currentTimeMillis()     // Catch:{ all -> 0x04f7 }
            java.lang.Object r6 = r6.value     // Catch:{ all -> 0x04f7 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x04f7 }
            long r16 = r6.longValue()     // Catch:{ all -> 0x04f7 }
            long r24 = r4.longValue()     // Catch:{ all -> 0x04f7 }
            r6 = 0
            long r10 = r16 + r24
            java.lang.Long r22 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x04f7 }
            r16 = r7
            r17 = r13
            r16.<init>(r17, r18, r19, r20, r22)     // Catch:{ all -> 0x04f7 }
            r6 = r7
            goto L_0x024d
        L_0x0230:
            com.google.android.gms.internal.measurement.zzkc r6 = new com.google.android.gms.internal.measurement.zzkc     // Catch:{ all -> 0x04f7 }
            java.lang.String r7 = r8.zzth     // Catch:{ all -> 0x04f7 }
            java.lang.String r18 = "auto"
            java.lang.String r19 = "_lte"
            com.google.android.gms.internal.measurement.zzgn r10 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.common.util.Clock r10 = r10.zzbt()     // Catch:{ all -> 0x04f7 }
            long r20 = r10.currentTimeMillis()     // Catch:{ all -> 0x04f7 }
            r16 = r6
            r17 = r7
            r22 = r4
            r16.<init>(r17, r18, r19, r20, r22)     // Catch:{ all -> 0x04f7 }
            goto L_0x024d
        L_0x024c:
            r6 = 0
        L_0x024d:
            r7 = 0
            r10 = 0
        L_0x024f:
            int r11 = r5.size()     // Catch:{ all -> 0x04f7 }
            if (r7 >= r11) goto L_0x02ab
            com.google.android.gms.internal.measurement.zzkx r11 = new com.google.android.gms.internal.measurement.zzkx     // Catch:{ all -> 0x04f7 }
            r11.<init>()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r13 = r8.zzavj     // Catch:{ all -> 0x04f7 }
            r13[r7] = r11     // Catch:{ all -> 0x04f7 }
            java.lang.Object r13 = r5.get(r7)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkc r13 = (com.google.android.gms.internal.measurement.zzkc) r13     // Catch:{ all -> 0x04f7 }
            java.lang.String r13 = r13.name     // Catch:{ all -> 0x04f7 }
            r11.name = r13     // Catch:{ all -> 0x04f7 }
            java.lang.Object r13 = r5.get(r7)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkc r13 = (com.google.android.gms.internal.measurement.zzkc) r13     // Catch:{ all -> 0x04f7 }
            long r13 = r13.zzast     // Catch:{ all -> 0x04f7 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x04f7 }
            r11.zzaws = r13     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzjz r13 = r32.zzjf()     // Catch:{ all -> 0x04f7 }
            java.lang.Object r14 = r5.get(r7)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkc r14 = (com.google.android.gms.internal.measurement.zzkc) r14     // Catch:{ all -> 0x04f7 }
            java.lang.Object r14 = r14.value     // Catch:{ all -> 0x04f7 }
            r13.zza(r11, r14)     // Catch:{ all -> 0x04f7 }
            if (r3 == 0) goto L_0x02a8
            java.lang.String r13 = "_lte"
            java.lang.String r14 = r11.name     // Catch:{ all -> 0x04f7 }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x04f7 }
            if (r13 == 0) goto L_0x02a8
            java.lang.Object r10 = r6.value     // Catch:{ all -> 0x04f7 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x04f7 }
            r11.zzave = r10     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r10 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.common.util.Clock r10 = r10.zzbt()     // Catch:{ all -> 0x04f7 }
            long r13 = r10.currentTimeMillis()     // Catch:{ all -> 0x04f7 }
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x04f7 }
            r11.zzaws = r10     // Catch:{ all -> 0x04f7 }
            r10 = r11
        L_0x02a8:
            int r7 = r7 + 1
            goto L_0x024f
        L_0x02ab:
            if (r3 == 0) goto L_0x02e4
            if (r10 != 0) goto L_0x02e4
            com.google.android.gms.internal.measurement.zzkx r3 = new com.google.android.gms.internal.measurement.zzkx     // Catch:{ all -> 0x04f7 }
            r3.<init>()     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = "_lte"
            r3.name = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r5 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.common.util.Clock r5 = r5.zzbt()     // Catch:{ all -> 0x04f7 }
            long r10 = r5.currentTimeMillis()     // Catch:{ all -> 0x04f7 }
            java.lang.Long r5 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x04f7 }
            r3.zzaws = r5     // Catch:{ all -> 0x04f7 }
            java.lang.Object r5 = r6.value     // Catch:{ all -> 0x04f7 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x04f7 }
            r3.zzave = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r5 = r8.zzavj     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r7 = r8.zzavj     // Catch:{ all -> 0x04f7 }
            int r7 = r7.length     // Catch:{ all -> 0x04f7 }
            int r7 = r7 + r9
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r7)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r5 = (com.google.android.gms.internal.measurement.zzkx[]) r5     // Catch:{ all -> 0x04f7 }
            r8.zzavj = r5     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r5 = r8.zzavj     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r7 = r8.zzavj     // Catch:{ all -> 0x04f7 }
            int r7 = r7.length     // Catch:{ all -> 0x04f7 }
            int r7 = r7 - r9
            r5[r7] = r3     // Catch:{ all -> 0x04f7 }
        L_0x02e4:
            long r3 = r4.longValue()     // Catch:{ all -> 0x04f7 }
            r10 = 0
            int r5 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x02f5
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            r3.zza(r6)     // Catch:{ all -> 0x04f7 }
        L_0x02f5:
            com.google.android.gms.internal.measurement.zzeu r3 = r2.zzahg     // Catch:{ all -> 0x04f7 }
            android.os.Bundle r14 = r3.zzin()     // Catch:{ all -> 0x04f7 }
            java.lang.String r3 = "_iap"
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f7 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x04f7 }
            r4 = 1
            if (r3 == 0) goto L_0x0320
            java.lang.String r3 = "_c"
            r14.putLong(r3, r4)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zzjb()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = "Marking in-app purchase as real-time"
            r3.log(r6)     // Catch:{ all -> 0x04f7 }
            java.lang.String r3 = "_r"
            r14.putLong(r3, r4)     // Catch:{ all -> 0x04f7 }
        L_0x0320:
            java.lang.String r3 = "_o"
            java.lang.String r6 = r2.origin     // Catch:{ all -> 0x04f7 }
            r14.putString(r3, r6)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkd r3 = r3.zzgg()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = r8.zzth     // Catch:{ all -> 0x04f7 }
            boolean r3 = r3.zzcn(r6)     // Catch:{ all -> 0x04f7 }
            if (r3 == 0) goto L_0x0353
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkd r3 = r3.zzgg()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = "_dbg"
            java.lang.Long r7 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x04f7 }
            r3.zza(r14, r6, r7)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkd r3 = r3.zzgg()     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = "_r"
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x04f7 }
            r3.zza(r14, r6, r4)     // Catch:{ all -> 0x04f7 }
        L_0x0353:
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            java.lang.String r4 = r2.name     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzet r3 = r3.zzf(r15, r4)     // Catch:{ all -> 0x04f7 }
            if (r3 != 0) goto L_0x039e
            com.google.android.gms.internal.measurement.zzet r13 = new com.google.android.gms.internal.measurement.zzet     // Catch:{ all -> 0x04f7 }
            java.lang.String r5 = r2.name     // Catch:{ all -> 0x04f7 }
            r6 = 1
            r16 = 0
            long r3 = r2.zzahr     // Catch:{ all -> 0x04f7 }
            r18 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r24 = r3
            r3 = r13
            r4 = r15
            r28 = r8
            r8 = r16
            r26 = r10
            r10 = r24
            r29 = r12
            r30 = r13
            r12 = r18
            r17 = r14
            r31 = r23
            r18 = 0
            r14 = r20
            r15 = r21
            r16 = r22
            r3.<init>(r4, r5, r6, r8, r10, r12, r14, r15, r16)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            r4 = r30
            r3.zza(r4)     // Catch:{ all -> 0x04f7 }
            r9 = r26
            goto L_0x03be
        L_0x039e:
            r28 = r8
            r26 = r10
            r29 = r12
            r17 = r14
            r31 = r23
            r18 = 0
            long r4 = r3.zzahj     // Catch:{ all -> 0x04f7 }
            long r6 = r2.zzahr     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzet r3 = r3.zzah(r6)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzet r3 = r3.zzim()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r6 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            r6.zza(r3)     // Catch:{ all -> 0x04f7 }
            r9 = r4
        L_0x03be:
            com.google.android.gms.internal.measurement.zzes r12 = new com.google.android.gms.internal.measurement.zzes     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            java.lang.String r4 = r2.origin     // Catch:{ all -> 0x04f7 }
            java.lang.String r6 = r2.name     // Catch:{ all -> 0x04f7 }
            long r7 = r2.zzahr     // Catch:{ all -> 0x04f7 }
            r2 = r12
            r5 = r34
            r11 = r17
            r2.<init>(r3, r4, r5, r6, r7, r9, r11)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkr r2 = new com.google.android.gms.internal.measurement.zzkr     // Catch:{ all -> 0x04f7 }
            r2.<init>()     // Catch:{ all -> 0x04f7 }
            r3 = 1
            com.google.android.gms.internal.measurement.zzkr[] r3 = new com.google.android.gms.internal.measurement.zzkr[r3]     // Catch:{ all -> 0x04f7 }
            r4 = 0
            r3[r4] = r2     // Catch:{ all -> 0x04f7 }
            r5 = r28
            r5.zzavi = r3     // Catch:{ all -> 0x04f7 }
            long r6 = r12.timestamp     // Catch:{ all -> 0x04f7 }
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f7 }
            r2.zzavb = r3     // Catch:{ all -> 0x04f7 }
            java.lang.String r3 = r12.name     // Catch:{ all -> 0x04f7 }
            r2.name = r3     // Catch:{ all -> 0x04f7 }
            long r6 = r12.zzahf     // Catch:{ all -> 0x04f7 }
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f7 }
            r2.zzavc = r3     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzeu r3 = r12.zzahg     // Catch:{ all -> 0x04f7 }
            int r3 = r3.size()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzks[] r3 = new com.google.android.gms.internal.measurement.zzks[r3]     // Catch:{ all -> 0x04f7 }
            r2.zzava = r3     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzeu r3 = r12.zzahg     // Catch:{ all -> 0x04f7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x04f7 }
            r6 = 0
        L_0x0404:
            boolean r7 = r3.hasNext()     // Catch:{ all -> 0x04f7 }
            if (r7 == 0) goto L_0x042c
            java.lang.Object r7 = r3.next()     // Catch:{ all -> 0x04f7 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzks r8 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x04f7 }
            r8.<init>()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzks[] r9 = r2.zzava     // Catch:{ all -> 0x04f7 }
            int r10 = r6 + 1
            r9[r6] = r8     // Catch:{ all -> 0x04f7 }
            r8.name = r7     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzeu r6 = r12.zzahg     // Catch:{ all -> 0x04f7 }
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzjz r7 = r32.zzjf()     // Catch:{ all -> 0x04f7 }
            r7.zza(r8, r6)     // Catch:{ all -> 0x04f7 }
            r6 = r10
            goto L_0x0404
        L_0x042c:
            r3 = r29
            java.lang.String r6 = r3.zzah()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkx[] r7 = r5.zzavj     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkr[] r8 = r5.zzavi     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzkp[] r6 = r1.zza(r6, r7, r8)     // Catch:{ all -> 0x04f7 }
            r5.zzawa = r6     // Catch:{ all -> 0x04f7 }
            java.lang.Long r6 = r2.zzavb     // Catch:{ all -> 0x04f7 }
            r5.zzavl = r6     // Catch:{ all -> 0x04f7 }
            java.lang.Long r2 = r2.zzavb     // Catch:{ all -> 0x04f7 }
            r5.zzavm = r2     // Catch:{ all -> 0x04f7 }
            long r6 = r3.zzgt()     // Catch:{ all -> 0x04f7 }
            int r2 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1))
            if (r2 == 0) goto L_0x0451
            java.lang.Long r14 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f7 }
            goto L_0x0453
        L_0x0451:
            r14 = r18
        L_0x0453:
            r5.zzavo = r14     // Catch:{ all -> 0x04f7 }
            long r8 = r3.zzgs()     // Catch:{ all -> 0x04f7 }
            int r2 = (r8 > r26 ? 1 : (r8 == r26 ? 0 : -1))
            if (r2 != 0) goto L_0x045e
            goto L_0x045f
        L_0x045e:
            r6 = r8
        L_0x045f:
            int r2 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1))
            if (r2 == 0) goto L_0x0468
            java.lang.Long r14 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f7 }
            goto L_0x046a
        L_0x0468:
            r14 = r18
        L_0x046a:
            r5.zzavn = r14     // Catch:{ all -> 0x04f7 }
            r3.zzhb()     // Catch:{ all -> 0x04f7 }
            long r6 = r3.zzgy()     // Catch:{ all -> 0x04f7 }
            int r2 = (int) r6     // Catch:{ all -> 0x04f7 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x04f7 }
            r5.zzavy = r2     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzeh r2 = r2.zzgk()     // Catch:{ all -> 0x04f7 }
            long r6 = r2.zzgw()     // Catch:{ all -> 0x04f7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f7 }
            r5.zzavu = r2     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.common.util.Clock r2 = r2.zzbt()     // Catch:{ all -> 0x04f7 }
            long r6 = r2.currentTimeMillis()     // Catch:{ all -> 0x04f7 }
            java.lang.Long r2 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04f7 }
            r5.zzavk = r2     // Catch:{ all -> 0x04f7 }
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x04f7 }
            r5.zzavz = r2     // Catch:{ all -> 0x04f7 }
            java.lang.Long r2 = r5.zzavl     // Catch:{ all -> 0x04f7 }
            long r6 = r2.longValue()     // Catch:{ all -> 0x04f7 }
            r3.zzr(r6)     // Catch:{ all -> 0x04f7 }
            java.lang.Long r2 = r5.zzavm     // Catch:{ all -> 0x04f7 }
            long r5 = r2.longValue()     // Catch:{ all -> 0x04f7 }
            r3.zzs(r5)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r2 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            r2.zza(r3)     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r2 = r32.zzjh()     // Catch:{ all -> 0x04f7 }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x04f7 }
            com.google.android.gms.internal.measurement.zzek r2 = r32.zzjh()
            r2.endTransaction()
            r2 = r31
            int r3 = r2.zzwb()     // Catch:{ IOException -> 0x04e1 }
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x04e1 }
            int r5 = r3.length     // Catch:{ IOException -> 0x04e1 }
            com.google.android.gms.internal.measurement.zzacb r4 = com.google.android.gms.internal.measurement.zzacb.zzb(r3, r4, r5)     // Catch:{ IOException -> 0x04e1 }
            r2.zza(r4)     // Catch:{ IOException -> 0x04e1 }
            r4.zzvt()     // Catch:{ IOException -> 0x04e1 }
            com.google.android.gms.internal.measurement.zzjz r2 = r32.zzjf()     // Catch:{ IOException -> 0x04e1 }
            byte[] r2 = r2.zzb(r3)     // Catch:{ IOException -> 0x04e1 }
            return r2
        L_0x04e1:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()
            java.lang.String r4 = "Data loss. Failed to bundle and serialize. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfi.zzbp(r34)
            r3.zze(r4, r5, r2)
            return r18
        L_0x04f7:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.zzek r3 = r32.zzjh()
            r3.endTransaction()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zza(com.google.android.gms.internal.measurement.zzex, java.lang.String):byte[]");
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzef zzef, zzeb zzeb) {
        zzfk zziv;
        String str;
        Object zzbp;
        String zzbo;
        Object value;
        zzfk zziv2;
        String str2;
        Object zzbp2;
        String zzbo2;
        Object obj;
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.origin);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            zzef zzef2 = new zzef(zzef);
            boolean z = false;
            zzef2.active = false;
            zzjh().beginTransaction();
            try {
                zzef zzi = zzjh().zzi(zzef2.packageName, zzef2.zzage.name);
                if (zzi != null && !zzi.origin.equals(zzef2.origin)) {
                    this.zzacv.zzgi().zziy().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzacv.zzgf().zzbo(zzef2.zzage.name), zzef2.origin, zzi.origin);
                }
                if (zzi != null && zzi.active) {
                    zzef2.origin = zzi.origin;
                    zzef2.creationTimestamp = zzi.creationTimestamp;
                    zzef2.triggerTimeout = zzi.triggerTimeout;
                    zzef2.triggerEventName = zzi.triggerEventName;
                    zzef2.zzagg = zzi.zzagg;
                    zzef2.active = zzi.active;
                    zzka zzka = new zzka(zzef2.zzage.name, zzi.zzage.zzast, zzef2.zzage.getValue(), zzi.zzage.origin);
                    zzef2.zzage = zzka;
                } else if (TextUtils.isEmpty(zzef2.triggerEventName)) {
                    zzka zzka2 = new zzka(zzef2.zzage.name, zzef2.creationTimestamp, zzef2.zzage.getValue(), zzef2.zzage.origin);
                    zzef2.zzage = zzka2;
                    zzef2.active = true;
                    z = true;
                }
                if (zzef2.active) {
                    zzka zzka3 = zzef2.zzage;
                    zzkc zzkc = new zzkc(zzef2.packageName, zzef2.origin, zzka3.name, zzka3.zzast, zzka3.getValue());
                    if (zzjh().zza(zzkc)) {
                        zziv2 = this.zzacv.zzgi().zzjb();
                        str2 = "User property updated immediately";
                        zzbp2 = zzef2.packageName;
                        zzbo2 = this.zzacv.zzgf().zzbo(zzkc.name);
                        obj = zzkc.value;
                    } else {
                        zziv2 = this.zzacv.zzgi().zziv();
                        str2 = "(2)Too many active user properties, ignoring";
                        zzbp2 = zzfi.zzbp(zzef2.packageName);
                        zzbo2 = this.zzacv.zzgf().zzbo(zzkc.name);
                        obj = zzkc.value;
                    }
                    zziv2.zzd(str2, zzbp2, zzbo2, obj);
                    if (z && zzef2.zzagg != null) {
                        zzc(new zzex(zzef2.zzagg, zzef2.creationTimestamp), zzeb);
                    }
                }
                if (zzjh().zza(zzef2)) {
                    zziv = this.zzacv.zzgi().zzjb();
                    str = "Conditional property added";
                    zzbp = zzef2.packageName;
                    zzbo = this.zzacv.zzgf().zzbo(zzef2.zzage.name);
                    value = zzef2.zzage.getValue();
                } else {
                    zziv = this.zzacv.zzgi().zziv();
                    str = "Too many conditional properties, ignoring";
                    zzbp = zzfi.zzbp(zzef2.packageName);
                    zzbo = this.zzacv.zzgf().zzbo(zzef2.zzage.name);
                    value = zzef2.zzage.getValue();
                }
                zziv.zzd(str, zzbp, zzbo, value);
                zzjh().setTransactionSuccessful();
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzex zzex, zzeb zzeb) {
        List<zzef> list;
        List<zzef> list2;
        List list3;
        zzfk zziv;
        String str;
        Object zzbp;
        String zzbo;
        Object obj;
        zzex zzex2 = zzex;
        zzeb zzeb2 = zzeb;
        Preconditions.checkNotNull(zzeb);
        Preconditions.checkNotEmpty(zzeb2.packageName);
        zzab();
        zzlc();
        String str2 = zzeb2.packageName;
        long j = zzex2.zzahr;
        if (zzjf().zzd(zzex2, zzeb2)) {
            if (!zzeb2.zzafk) {
                zzg(zzeb2);
                return;
            }
            zzjh().beginTransaction();
            try {
                zzek zzjh = zzjh();
                Preconditions.checkNotEmpty(str2);
                zzjh.zzab();
                zzjh.zzch();
                if (j < 0) {
                    zzjh.zzgi().zziy().zze("Invalid time querying timed out conditional properties", zzfi.zzbp(str2), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzjh.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzef zzef : list) {
                    if (zzef != null) {
                        this.zzacv.zzgi().zzjb().zzd("User property timed out", zzef.packageName, this.zzacv.zzgf().zzbo(zzef.zzage.name), zzef.zzage.getValue());
                        if (zzef.zzagf != null) {
                            zzc(new zzex(zzef.zzagf, j), zzeb2);
                        }
                        zzjh().zzj(str2, zzef.zzage.name);
                    }
                }
                zzek zzjh2 = zzjh();
                Preconditions.checkNotEmpty(str2);
                zzjh2.zzab();
                zzjh2.zzch();
                if (j < 0) {
                    zzjh2.zzgi().zziy().zze("Invalid time querying expired conditional properties", zzfi.zzbp(str2), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzjh2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzef zzef2 : list2) {
                    if (zzef2 != null) {
                        this.zzacv.zzgi().zzjb().zzd("User property expired", zzef2.packageName, this.zzacv.zzgf().zzbo(zzef2.zzage.name), zzef2.zzage.getValue());
                        zzjh().zzg(str2, zzef2.zzage.name);
                        if (zzef2.zzagh != null) {
                            arrayList.add(zzef2.zzagh);
                        }
                        zzjh().zzj(str2, zzef2.zzage.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    zzc(new zzex((zzex) obj2, j), zzeb2);
                }
                zzek zzjh3 = zzjh();
                String str3 = zzex2.name;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzjh3.zzab();
                zzjh3.zzch();
                if (j < 0) {
                    zzjh3.zzgi().zziy().zzd("Invalid time querying triggered conditional properties", zzfi.zzbp(str2), zzjh3.zzgf().zzbm(str3), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzjh3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    zzef zzef3 = (zzef) it.next();
                    if (zzef3 != null) {
                        zzka zzka = zzef3.zzage;
                        zzkc zzkc = r5;
                        Iterator it2 = it;
                        zzef zzef4 = zzef3;
                        zzkc zzkc2 = new zzkc(zzef3.packageName, zzef3.origin, zzka.name, j, zzka.getValue());
                        if (zzjh().zza(zzkc)) {
                            zziv = this.zzacv.zzgi().zzjb();
                            str = "User property triggered";
                            zzbp = zzef4.packageName;
                            zzbo = this.zzacv.zzgf().zzbo(zzkc.name);
                            obj = zzkc.value;
                        } else {
                            zziv = this.zzacv.zzgi().zziv();
                            str = "Too many active user properties, ignoring";
                            zzbp = zzfi.zzbp(zzef4.packageName);
                            zzbo = this.zzacv.zzgf().zzbo(zzkc.name);
                            obj = zzkc.value;
                        }
                        zziv.zzd(str, zzbp, zzbo, obj);
                        if (zzef4.zzagg != null) {
                            arrayList3.add(zzef4.zzagg);
                        }
                        zzef4.zzage = new zzka(zzkc);
                        zzef4.active = true;
                        zzjh().zza(zzef4);
                        it = it2;
                    }
                }
                zzc(zzex, zzeb);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj3 = arrayList4.get(i2);
                    i2++;
                    zzc(new zzex((zzex) obj3, j), zzeb2);
                }
                zzjh().setTransactionSuccessful();
                zzjh().endTransaction();
            } catch (Throwable th) {
                Throwable th2 = th;
                zzjh().endTransaction();
                throw th2;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzjs zzjs) {
        this.zzasc++;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(zzka zzka, zzeb zzeb) {
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            int zzcj = this.zzacv.zzgg().zzcj(zzka.name);
            if (zzcj != 0) {
                this.zzacv.zzgg();
                this.zzacv.zzgg().zza(zzeb.packageName, zzcj, "_ev", zzkd.zza(zzka.name, 24, true), zzka.name != null ? zzka.name.length() : 0);
                return;
            }
            int zzi = this.zzacv.zzgg().zzi(zzka.name, zzka.getValue());
            if (zzi != 0) {
                this.zzacv.zzgg();
                String zza2 = zzkd.zza(zzka.name, 24, true);
                Object value = zzka.getValue();
                this.zzacv.zzgg().zza(zzeb.packageName, zzi, "_ev", zza2, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
                return;
            }
            Object zzj = this.zzacv.zzgg().zzj(zzka.name, zzka.getValue());
            if (zzj != null) {
                zzkc zzkc = new zzkc(zzeb.packageName, zzka.origin, zzka.name, zzka.zzast, zzj);
                this.zzacv.zzgi().zzjb().zze("Setting user property", this.zzacv.zzgf().zzbo(zzkc.name), zzj);
                zzjh().beginTransaction();
                try {
                    zzg(zzeb);
                    boolean zza3 = zzjh().zza(zzkc);
                    zzjh().setTransactionSuccessful();
                    if (zza3) {
                        this.zzacv.zzgi().zzjb().zze("User property set", this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                    } else {
                        this.zzacv.zzgi().zziv().zze("Too many unique user properties are set. Ignoring user property", this.zzacv.zzgf().zzbo(zzkc.name), zzkc.value);
                        this.zzacv.zzgg().zza(zzeb.packageName, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzjh().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0136 A[Catch:{ all -> 0x017d, all -> 0x000f }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0146 A[Catch:{ all -> 0x017d, all -> 0x000f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzab()
            r6.zzlc()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0012
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x000f }
            goto L_0x0012
        L_0x000f:
            r7 = move-exception
            goto L_0x0186
        L_0x0012:
            com.google.android.gms.internal.measurement.zzgn r1 = r6.zzacv     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzgi()     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zzjc()     // Catch:{ all -> 0x000f }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x000f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x000f }
            r1.zzg(r2, r3)     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzek r1 = r6.zzjh()     // Catch:{ all -> 0x000f }
            r1.beginTransaction()     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzek r1 = r6.zzjh()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzea r1 = r1.zzbf(r7)     // Catch:{ all -> 0x017d }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 1
            r4 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x0042
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0042
            if (r8 != r4) goto L_0x0046
        L_0x0042:
            if (r9 != 0) goto L_0x0046
            r2 = 1
            goto L_0x0047
        L_0x0046:
            r2 = 0
        L_0x0047:
            if (r1 != 0) goto L_0x005e
            com.google.android.gms.internal.measurement.zzgn r8 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziy()     // Catch:{ all -> 0x017d }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfi.zzbp(r7)     // Catch:{ all -> 0x017d }
            r8.zzg(r9, r7)     // Catch:{ all -> 0x017d }
            goto L_0x0171
        L_0x005e:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00ce
            if (r8 != r5) goto L_0x0065
            goto L_0x00ce
        L_0x0065:
            com.google.android.gms.internal.measurement.zzgn r10 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r10 = r10.zzbt()     // Catch:{ all -> 0x017d }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r1.zzy(r10)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzek r10 = r6.zzjh()     // Catch:{ all -> 0x017d }
            r10.zza(r1)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzgn r10 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfi r10 = r10.zzgi()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfk r10 = r10.zzjc()     // Catch:{ all -> 0x017d }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x017d }
            r10.zze(r11, r1, r9)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzgh r9 = r6.zzky()     // Catch:{ all -> 0x017d }
            r9.zzbz(r7)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzgn r7 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzft r7 = r7.zzgj()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfw r7 = r7.zzalu     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzgn r9 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r9 = r9.zzbt()     // Catch:{ all -> 0x017d }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r7.set(r9)     // Catch:{ all -> 0x017d }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00b2
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00b1
            goto L_0x00b2
        L_0x00b1:
            r3 = 0
        L_0x00b2:
            if (r3 == 0) goto L_0x00c9
            com.google.android.gms.internal.measurement.zzgn r7 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzft r7 = r7.zzgj()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfw r7 = r7.zzalv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzgn r8 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r8 = r8.zzbt()     // Catch:{ all -> 0x017d }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r7.set(r8)     // Catch:{ all -> 0x017d }
        L_0x00c9:
            r6.zzlg()     // Catch:{ all -> 0x017d }
            goto L_0x0171
        L_0x00ce:
            r9 = 0
            if (r11 == 0) goto L_0x00da
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x017d }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x017d }
            goto L_0x00db
        L_0x00da:
            r11 = r9
        L_0x00db:
            if (r11 == 0) goto L_0x00ea
            int r2 = r11.size()     // Catch:{ all -> 0x017d }
            if (r2 <= 0) goto L_0x00ea
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x017d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x017d }
            goto L_0x00eb
        L_0x00ea:
            r11 = r9
        L_0x00eb:
            if (r8 == r5) goto L_0x0107
            if (r8 != r4) goto L_0x00f0
            goto L_0x0107
        L_0x00f0:
            com.google.android.gms.internal.measurement.zzgh r9 = r6.zzky()     // Catch:{ all -> 0x017d }
            boolean r9 = r9.zza(r7, r10, r11)     // Catch:{ all -> 0x017d }
            if (r9 != 0) goto L_0x0120
            com.google.android.gms.internal.measurement.zzek r7 = r6.zzjh()     // Catch:{ all -> 0x000f }
        L_0x00fe:
            r7.endTransaction()     // Catch:{ all -> 0x000f }
            r6.zzase = r0
            r6.zzlh()
            return
        L_0x0107:
            com.google.android.gms.internal.measurement.zzgh r11 = r6.zzky()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzkn r11 = r11.zzbx(r7)     // Catch:{ all -> 0x017d }
            if (r11 != 0) goto L_0x0120
            com.google.android.gms.internal.measurement.zzgh r11 = r6.zzky()     // Catch:{ all -> 0x017d }
            boolean r9 = r11.zza(r7, r9, r9)     // Catch:{ all -> 0x017d }
            if (r9 != 0) goto L_0x0120
            com.google.android.gms.internal.measurement.zzek r7 = r6.zzjh()     // Catch:{ all -> 0x000f }
            goto L_0x00fe
        L_0x0120:
            com.google.android.gms.internal.measurement.zzgn r9 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.common.util.Clock r9 = r9.zzbt()     // Catch:{ all -> 0x017d }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x017d }
            r1.zzx(r2)     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzek r9 = r6.zzjh()     // Catch:{ all -> 0x017d }
            r9.zza(r1)     // Catch:{ all -> 0x017d }
            if (r8 != r5) goto L_0x0146
            com.google.android.gms.internal.measurement.zzgn r8 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziz()     // Catch:{ all -> 0x017d }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zzg(r9, r7)     // Catch:{ all -> 0x017d }
            goto L_0x015e
        L_0x0146:
            com.google.android.gms.internal.measurement.zzgn r7 = r6.zzacv     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzgi()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzfk r7 = r7.zzjc()     // Catch:{ all -> 0x017d }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x017d }
            int r10 = r10.length     // Catch:{ all -> 0x017d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x017d }
            r7.zze(r9, r8, r10)     // Catch:{ all -> 0x017d }
        L_0x015e:
            com.google.android.gms.internal.measurement.zzfm r7 = r6.zzkz()     // Catch:{ all -> 0x017d }
            boolean r7 = r7.zzex()     // Catch:{ all -> 0x017d }
            if (r7 == 0) goto L_0x00c9
            boolean r7 = r6.zzlf()     // Catch:{ all -> 0x017d }
            if (r7 == 0) goto L_0x00c9
            r6.zzle()     // Catch:{ all -> 0x017d }
        L_0x0171:
            com.google.android.gms.internal.measurement.zzek r7 = r6.zzjh()     // Catch:{ all -> 0x017d }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x017d }
            com.google.android.gms.internal.measurement.zzek r7 = r6.zzjh()     // Catch:{ all -> 0x000f }
            goto L_0x00fe
        L_0x017d:
            r7 = move-exception
            com.google.android.gms.internal.measurement.zzek r8 = r6.zzjh()     // Catch:{ all -> 0x000f }
            r8.endTransaction()     // Catch:{ all -> 0x000f }
            throw r7     // Catch:{ all -> 0x000f }
        L_0x0186:
            r6.zzase = r0
            r6.zzlh()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    public final Clock zzbt() {
        return this.zzacv.zzbt();
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotEmpty(zzef.packageName);
        Preconditions.checkNotNull(zzef.zzage);
        Preconditions.checkNotEmpty(zzef.zzage.name);
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            zzjh().beginTransaction();
            try {
                zzg(zzeb);
                zzef zzi = zzjh().zzi(zzef.packageName, zzef.zzage.name);
                if (zzi != null) {
                    this.zzacv.zzgi().zzjb().zze("Removing conditional user property", zzef.packageName, this.zzacv.zzgf().zzbo(zzef.zzage.name));
                    zzjh().zzj(zzef.packageName, zzef.zzage.name);
                    if (zzi.active) {
                        zzjh().zzg(zzef.packageName, zzef.zzage.name);
                    }
                    if (zzef.zzagh != null) {
                        Bundle bundle = null;
                        if (zzef.zzagh.zzahg != null) {
                            bundle = zzef.zzagh.zzahg.zzin();
                        }
                        Bundle bundle2 = bundle;
                        zzc(this.zzacv.zzgg().zza(zzef.packageName, zzef.zzagh.name, bundle2, zzi.origin, zzef.zzagh.zzahr, true, false), zzeb);
                    }
                } else {
                    this.zzacv.zzgi().zziy().zze("Conditional user property doesn't exist", zzfi.zzbp(zzef.packageName), this.zzacv.zzgf().zzbo(zzef.zzage.name));
                }
                zzjh().setTransactionSuccessful();
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(zzex zzex, String str) {
        zzex zzex2 = zzex;
        String str2 = str;
        zzea zzbf = zzjh().zzbf(str2);
        if (zzbf == null || TextUtils.isEmpty(zzbf.zzag())) {
            this.zzacv.zzgi().zzjb().zzg("No app data available; dropping event", str2);
            return;
        }
        Boolean zzc = zzc(zzbf);
        if (zzc == null) {
            if (!"_ui".equals(zzex2.name)) {
                this.zzacv.zzgi().zziy().zzg("Could not find package. appId", zzfi.zzbp(str));
            }
        } else if (!zzc.booleanValue()) {
            this.zzacv.zzgi().zziv().zzg("App version does not match; dropping event. appId", zzfi.zzbp(str));
            return;
        }
        zzeb zzeb = r2;
        zzeb zzeb2 = new zzeb(str2, zzbf.getGmpAppId(), zzbf.zzag(), zzbf.zzgu(), zzbf.zzgv(), zzbf.zzgw(), zzbf.zzgx(), (String) null, zzbf.isMeasurementEnabled(), false, zzbf.zzgr(), zzbf.zzhk(), 0, 0, zzbf.zzhl(), zzbf.zzhm(), false);
        zzb(zzex2, zzeb);
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(zzka zzka, zzeb zzeb) {
        zzab();
        zzlc();
        if (!TextUtils.isEmpty(zzeb.zzafa)) {
            if (!zzeb.zzafk) {
                zzg(zzeb);
                return;
            }
            this.zzacv.zzgi().zzjb().zzg("Removing user property", this.zzacv.zzgf().zzbo(zzka.name));
            zzjh().beginTransaction();
            try {
                zzg(zzeb);
                zzjh().zzg(zzeb.packageName, zzka.name);
                zzjh().setTransactionSuccessful();
                this.zzacv.zzgi().zzjb().zzg("User property removed", this.zzacv.zzgf().zzbo(zzka.name));
            } finally {
                zzjh().endTransaction();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(zzeb zzeb) {
        if (this.zzasj != null) {
            this.zzask = new ArrayList();
            this.zzask.addAll(this.zzasj);
        }
        zzek zzjh = zzjh();
        String str = zzeb.packageName;
        Preconditions.checkNotEmpty(str);
        zzjh.zzab();
        zzjh.zzch();
        try {
            SQLiteDatabase writableDatabase = zzjh.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete("events", "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzjh.zzgi().zzjc().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzjh.zzgi().zziv().zze("Error resetting analytics data. appId, error", zzfi.zzbp(str), e);
        }
        zzeb zza2 = zza(this.zzacv.getContext(), zzeb.packageName, zzeb.zzafa, zzeb.zzafk, zzeb.zzafm, zzeb.zzafn, zzeb.zzaga);
        if (!this.zzacv.zzgk().zzbc(zzeb.packageName) || zzeb.zzafk) {
            zzf(zza2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zze(zzeb zzeb) {
        zzab();
        zzlc();
        Preconditions.checkNotEmpty(zzeb.packageName);
        zzg(zzeb);
    }

    /* access modifiers changed from: 0000 */
    public final void zze(zzef zzef) {
        zzeb zzce = zzce(zzef.packageName);
        if (zzce != null) {
            zzb(zzef, zzce);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x03b6 A[Catch:{ SQLiteException -> 0x0144, all -> 0x03df }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01ea A[Catch:{ SQLiteException -> 0x0144, all -> 0x03df }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf(com.google.android.gms.internal.measurement.zzeb r21) {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            r20.zzab()
            r20.zzlc()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            java.lang.String r3 = r2.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            java.lang.String r3 = r2.zzafa
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x001b
            return
        L_0x001b:
            com.google.android.gms.internal.measurement.zzek r3 = r20.zzjh()
            java.lang.String r4 = r2.packageName
            com.google.android.gms.internal.measurement.zzea r3 = r3.zzbf(r4)
            r4 = 0
            if (r3 == 0) goto L_0x004e
            java.lang.String r6 = r3.getGmpAppId()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x004e
            java.lang.String r6 = r2.zzafa
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x004e
            r3.zzx(r4)
            com.google.android.gms.internal.measurement.zzek r6 = r20.zzjh()
            r6.zza(r3)
            com.google.android.gms.internal.measurement.zzgh r3 = r20.zzky()
            java.lang.String r6 = r2.packageName
            r3.zzca(r6)
        L_0x004e:
            boolean r3 = r2.zzafk
            if (r3 != 0) goto L_0x0056
            r20.zzg(r21)
            return
        L_0x0056:
            long r6 = r2.zzaga
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x0066
            com.google.android.gms.internal.measurement.zzgn r3 = r1.zzacv
            com.google.android.gms.common.util.Clock r3 = r3.zzbt()
            long r6 = r3.currentTimeMillis()
        L_0x0066:
            int r3 = r2.zzagb
            r14 = 0
            r15 = 1
            if (r3 == 0) goto L_0x0088
            if (r3 == r15) goto L_0x0088
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziy()
            java.lang.String r9 = "Incorrect app type, assuming installed app. appId, appType"
            java.lang.String r10 = r2.packageName
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfi.zzbp(r10)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r8.zze(r9, r10, r3)
            r3 = 0
        L_0x0088:
            com.google.android.gms.internal.measurement.zzek r8 = r20.zzjh()
            r8.beginTransaction()
            com.google.android.gms.internal.measurement.zzek r8 = r20.zzjh()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzea r8 = r8.zzbf(r9)     // Catch:{ all -> 0x03df }
            r16 = 0
            if (r8 == 0) goto L_0x0159
            java.lang.String r9 = r8.getGmpAppId()     // Catch:{ all -> 0x03df }
            if (r9 == 0) goto L_0x0159
            java.lang.String r9 = r8.getGmpAppId()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = r2.zzafa     // Catch:{ all -> 0x03df }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x03df }
            if (r9 != 0) goto L_0x0159
            com.google.android.gms.internal.measurement.zzgn r9 = r1.zzacv     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfi r9 = r9.zzgi()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziy()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r11 = r8.zzah()     // Catch:{ all -> 0x03df }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r11)     // Catch:{ all -> 0x03df }
            r9.zzg(r10, r11)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzek r9 = r20.zzjh()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = r8.zzah()     // Catch:{ all -> 0x03df }
            r9.zzch()     // Catch:{ all -> 0x03df }
            r9.zzab()     // Catch:{ all -> 0x03df }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x03df }
            android.database.sqlite.SQLiteDatabase r10 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0144 }
            java.lang.String[] r11 = new java.lang.String[r15]     // Catch:{ SQLiteException -> 0x0144 }
            r11[r14] = r8     // Catch:{ SQLiteException -> 0x0144 }
            java.lang.String r12 = "events"
            java.lang.String r13 = "app_id=?"
            int r12 = r10.delete(r12, r13, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r14
            java.lang.String r13 = "user_attributes"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "conditional_properties"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "apps"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "raw_events"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "raw_events_metadata"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "event_filters"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "property_filters"
            java.lang.String r14 = "app_id=?"
            int r13 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r13
            java.lang.String r13 = "audience_filter_values"
            java.lang.String r14 = "app_id=?"
            int r10 = r10.delete(r13, r14, r11)     // Catch:{ SQLiteException -> 0x0144 }
            int r12 = r12 + r10
            if (r12 <= 0) goto L_0x0157
            com.google.android.gms.internal.measurement.zzfi r10 = r9.zzgi()     // Catch:{ SQLiteException -> 0x0144 }
            com.google.android.gms.internal.measurement.zzfk r10 = r10.zzjc()     // Catch:{ SQLiteException -> 0x0144 }
            java.lang.String r11 = "Deleted application data. app, records"
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ SQLiteException -> 0x0144 }
            r10.zze(r11, r8, r12)     // Catch:{ SQLiteException -> 0x0144 }
            goto L_0x0157
        L_0x0144:
            r0 = move-exception
            r10 = r0
            com.google.android.gms.internal.measurement.zzfi r9 = r9.zzgi()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "Error deleting application data. appId, error"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r8)     // Catch:{ all -> 0x03df }
            r9.zze(r11, r8, r10)     // Catch:{ all -> 0x03df }
        L_0x0157:
            r8 = r16
        L_0x0159:
            if (r8 == 0) goto L_0x01c9
            long r9 = r8.zzgu()     // Catch:{ all -> 0x03df }
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x0195
            long r9 = r8.zzgu()     // Catch:{ all -> 0x03df }
            long r11 = r2.zzafg     // Catch:{ all -> 0x03df }
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x01c9
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r9.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_pv"
            java.lang.String r8 = r8.zzag()     // Catch:{ all -> 0x03df }
            r9.putString(r10, r8)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_au"
            com.google.android.gms.internal.measurement.zzeu r11 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r11.<init>(r9)     // Catch:{ all -> 0x03df }
            java.lang.String r12 = "auto"
            r8 = r14
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
        L_0x0191:
            r1.zzb(r14, r2)     // Catch:{ all -> 0x03df }
            goto L_0x01c9
        L_0x0195:
            java.lang.String r9 = r8.zzag()     // Catch:{ all -> 0x03df }
            if (r9 == 0) goto L_0x01c9
            java.lang.String r9 = r8.zzag()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = r2.zztg     // Catch:{ all -> 0x03df }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x03df }
            if (r9 != 0) goto L_0x01c9
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r9.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_pv"
            java.lang.String r8 = r8.zzag()     // Catch:{ all -> 0x03df }
            r9.putString(r10, r8)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_au"
            com.google.android.gms.internal.measurement.zzeu r11 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r11.<init>(r9)     // Catch:{ all -> 0x03df }
            java.lang.String r12 = "auto"
            r8 = r14
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
            goto L_0x0191
        L_0x01c9:
            r20.zzg(r21)     // Catch:{ all -> 0x03df }
            if (r3 != 0) goto L_0x01db
            com.google.android.gms.internal.measurement.zzek r8 = r20.zzjh()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_f"
        L_0x01d6:
            com.google.android.gms.internal.measurement.zzet r8 = r8.zzf(r9, r10)     // Catch:{ all -> 0x03df }
            goto L_0x01e8
        L_0x01db:
            if (r3 != r15) goto L_0x01e6
            com.google.android.gms.internal.measurement.zzek r8 = r20.zzjh()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_v"
            goto L_0x01d6
        L_0x01e6:
            r8 = r16
        L_0x01e8:
            if (r8 != 0) goto L_0x03b6
            r8 = 3600000(0x36ee80, double:1.7786363E-317)
            long r10 = r6 / r8
            r13 = 1
            long r17 = r10 + r13
            long r8 = r8 * r17
            if (r3 != 0) goto L_0x0345
            com.google.android.gms.internal.measurement.zzka r3 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_fot"
            java.lang.Long r12 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03df }
            java.lang.String r17 = "auto"
            r8 = r3
            r9 = r10
            r10 = r6
            r4 = r13
            r13 = r17
            r8.<init>(r9, r10, r12, r13)     // Catch:{ all -> 0x03df }
            r1.zzb(r3, r2)     // Catch:{ all -> 0x03df }
            r20.zzab()     // Catch:{ all -> 0x03df }
            r20.zzlc()     // Catch:{ all -> 0x03df }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_c"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_r"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_uwa"
            r9 = 0
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_pfo"
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_sys"
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_sysu"
            r3.putLong(r8, r9)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzeh r8 = r8.zzgk()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            boolean r8 = r8.zzbc(r9)     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x024f
            boolean r8 = r2.zzagc     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x024f
            java.lang.String r8 = "_dac"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
        L_0x024f:
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x03df }
            android.content.Context r8 = r8.getContext()     // Catch:{ all -> 0x03df }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x03df }
            if (r8 != 0) goto L_0x0272
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziv()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r10 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.Object r10 = com.google.android.gms.internal.measurement.zzfi.zzbp(r10)     // Catch:{ all -> 0x03df }
            r8.zzg(r9, r10)     // Catch:{ all -> 0x03df }
            goto L_0x0311
        L_0x0272:
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ NameNotFoundException -> 0x0284 }
            android.content.Context r8 = r8.getContext()     // Catch:{ NameNotFoundException -> 0x0284 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r8 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r8)     // Catch:{ NameNotFoundException -> 0x0284 }
            java.lang.String r9 = r2.packageName     // Catch:{ NameNotFoundException -> 0x0284 }
            r10 = 0
            android.content.pm.PackageInfo r8 = r8.getPackageInfo(r9, r10)     // Catch:{ NameNotFoundException -> 0x0284 }
            goto L_0x029d
        L_0x0284:
            r0 = move-exception
            r8 = r0
            com.google.android.gms.internal.measurement.zzgn r9 = r1.zzacv     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfi r9 = r9.zzgi()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r11 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r11)     // Catch:{ all -> 0x03df }
            r9.zze(r10, r11, r8)     // Catch:{ all -> 0x03df }
            r8 = r16
        L_0x029d:
            if (r8 == 0) goto L_0x02cf
            long r9 = r8.firstInstallTime     // Catch:{ all -> 0x03df }
            r11 = 0
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x02cf
            long r9 = r8.firstInstallTime     // Catch:{ all -> 0x03df }
            long r11 = r8.lastUpdateTime     // Catch:{ all -> 0x03df }
            int r8 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r8 == 0) goto L_0x02b6
            java.lang.String r8 = "_uwa"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            r8 = 0
            goto L_0x02b7
        L_0x02b6:
            r8 = 1
        L_0x02b7:
            com.google.android.gms.internal.measurement.zzka r14 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_fi"
            if (r8 == 0) goto L_0x02bf
            r10 = r4
            goto L_0x02c1
        L_0x02bf:
            r10 = 0
        L_0x02c1:
            java.lang.Long r12 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x03df }
            java.lang.String r13 = "auto"
            r8 = r14
            r10 = r6
            r8.<init>(r9, r10, r12, r13)     // Catch:{ all -> 0x03df }
            r1.zzb(r14, r2)     // Catch:{ all -> 0x03df }
        L_0x02cf:
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ NameNotFoundException -> 0x02e1 }
            android.content.Context r8 = r8.getContext()     // Catch:{ NameNotFoundException -> 0x02e1 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r8 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r8)     // Catch:{ NameNotFoundException -> 0x02e1 }
            java.lang.String r9 = r2.packageName     // Catch:{ NameNotFoundException -> 0x02e1 }
            r10 = 0
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo(r9, r10)     // Catch:{ NameNotFoundException -> 0x02e1 }
            goto L_0x02fa
        L_0x02e1:
            r0 = move-exception
            r8 = r0
            com.google.android.gms.internal.measurement.zzgn r9 = r1.zzacv     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfi r9 = r9.zzgi()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r11 = r2.packageName     // Catch:{ all -> 0x03df }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfi.zzbp(r11)     // Catch:{ all -> 0x03df }
            r9.zze(r10, r11, r8)     // Catch:{ all -> 0x03df }
            r8 = r16
        L_0x02fa:
            if (r8 == 0) goto L_0x0311
            int r9 = r8.flags     // Catch:{ all -> 0x03df }
            r9 = r9 & r15
            if (r9 == 0) goto L_0x0306
            java.lang.String r9 = "_sys"
            r3.putLong(r9, r4)     // Catch:{ all -> 0x03df }
        L_0x0306:
            int r8 = r8.flags     // Catch:{ all -> 0x03df }
            r8 = r8 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x0311
            java.lang.String r8 = "_sysu"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
        L_0x0311:
            com.google.android.gms.internal.measurement.zzek r8 = r20.zzjh()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)     // Catch:{ all -> 0x03df }
            r8.zzab()     // Catch:{ all -> 0x03df }
            r8.zzch()     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "first_open_count"
            long r8 = r8.zzm(r9, r10)     // Catch:{ all -> 0x03df }
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x0331
            java.lang.String r10 = "_pfo"
            r3.putLong(r10, r8)     // Catch:{ all -> 0x03df }
        L_0x0331:
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_f"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r14
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
        L_0x0341:
            r1.zzb(r14, r2)     // Catch:{ all -> 0x03df }
            goto L_0x0398
        L_0x0345:
            r4 = r13
            if (r3 != r15) goto L_0x0398
            com.google.android.gms.internal.measurement.zzka r3 = new com.google.android.gms.internal.measurement.zzka     // Catch:{ all -> 0x03df }
            java.lang.String r10 = "_fvt"
            java.lang.Long r12 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x03df }
            java.lang.String r13 = "auto"
            r8 = r3
            r9 = r10
            r10 = r6
            r8.<init>(r9, r10, r12, r13)     // Catch:{ all -> 0x03df }
            r1.zzb(r3, r2)     // Catch:{ all -> 0x03df }
            r20.zzab()     // Catch:{ all -> 0x03df }
            r20.zzlc()     // Catch:{ all -> 0x03df }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_c"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_r"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzgn r8 = r1.zzacv     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzeh r8 = r8.zzgk()     // Catch:{ all -> 0x03df }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03df }
            boolean r8 = r8.zzbc(r9)     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x0387
            boolean r8 = r2.zzagc     // Catch:{ all -> 0x03df }
            if (r8 == 0) goto L_0x0387
            java.lang.String r8 = "_dac"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
        L_0x0387:
            com.google.android.gms.internal.measurement.zzex r14 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_v"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r14
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
            goto L_0x0341
        L_0x0398:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            java.lang.String r8 = "_et"
            r3.putLong(r8, r4)     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r4 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_e"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r4
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
        L_0x03b2:
            r1.zzb(r4, r2)     // Catch:{ all -> 0x03df }
            goto L_0x03d0
        L_0x03b6:
            boolean r3 = r2.zzafz     // Catch:{ all -> 0x03df }
            if (r3 == 0) goto L_0x03d0
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x03df }
            r3.<init>()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzex r4 = new com.google.android.gms.internal.measurement.zzex     // Catch:{ all -> 0x03df }
            java.lang.String r9 = "_cd"
            com.google.android.gms.internal.measurement.zzeu r10 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03df }
            r10.<init>(r3)     // Catch:{ all -> 0x03df }
            java.lang.String r11 = "auto"
            r8 = r4
            r12 = r6
            r8.<init>(r9, r10, r11, r12)     // Catch:{ all -> 0x03df }
            goto L_0x03b2
        L_0x03d0:
            com.google.android.gms.internal.measurement.zzek r2 = r20.zzjh()     // Catch:{ all -> 0x03df }
            r2.setTransactionSuccessful()     // Catch:{ all -> 0x03df }
            com.google.android.gms.internal.measurement.zzek r2 = r20.zzjh()
            r2.endTransaction()
            return
        L_0x03df:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.measurement.zzek r3 = r20.zzjh()
            r3.endTransaction()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzf(com.google.android.gms.internal.measurement.zzeb):void");
    }

    /* access modifiers changed from: 0000 */
    public final void zzf(zzef zzef) {
        zzeb zzce = zzce(zzef.packageName);
        if (zzce != null) {
            zzc(zzef, zzce);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzg(Runnable runnable) {
        zzab();
        if (this.zzasb == null) {
            this.zzasb = new ArrayList();
        }
        this.zzasb.add(runnable);
    }

    public final zzfg zzgf() {
        return this.zzacv.zzgf();
    }

    public final zzkd zzgg() {
        return this.zzacv.zzgg();
    }

    public final zzgi zzgh() {
        return this.zzacv.zzgh();
    }

    public final zzfi zzgi() {
        return this.zzacv.zzgi();
    }

    public final zzeh zzgk() {
        return this.zzacv.zzgk();
    }

    public final zzee zzgl() {
        return this.zzacv.zzgl();
    }

    /* access modifiers changed from: 0000 */
    public final String zzh(zzeb zzeb) {
        try {
            return (String) this.zzacv.zzgh().zzb((Callable<V>) new zzjx<V>(this, zzeb)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzacv.zzgi().zziv().zze("Failed to get app instance id. appId", zzfi.zzbp(zzeb.packageName), e);
            return null;
        }
    }

    public final zzjz zzjf() {
        zza((zzjs) this.zzary);
        return this.zzary;
    }

    public final zzed zzjg() {
        zza((zzjs) this.zzarx);
        return this.zzarx;
    }

    public final zzek zzjh() {
        zza((zzjs) this.zzaru);
        return this.zzaru;
    }

    public final zzfm zzkz() {
        zza((zzjs) this.zzart);
        return this.zzart;
    }

    /* access modifiers changed from: 0000 */
    public final void zzlc() {
        if (!this.zzvn) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:77|78) */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r14.zzacv.zzgi().zziv().zze("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.internal.measurement.zzfi.zzbp(r4), r5);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x027e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzle() {
        /*
            r14 = this;
            r14.zzab()
            r14.zzlc()
            r0 = 1
            r14.zzasg = r0
            r1 = 0
            com.google.android.gms.internal.measurement.zzgn r2 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            r2.zzgl()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzgn r2 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzik r2 = r2.zzga()     // Catch:{ all -> 0x02b9 }
            java.lang.Boolean r2 = r2.zzkr()     // Catch:{ all -> 0x02b9 }
            if (r2 != 0) goto L_0x0030
            com.google.android.gms.internal.measurement.zzgn r0 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziy()     // Catch:{ all -> 0x02b9 }
            java.lang.String r2 = "Upload data called on the client side before use of service was decided"
        L_0x0027:
            r0.log(r2)     // Catch:{ all -> 0x02b9 }
        L_0x002a:
            r14.zzasg = r1
            r14.zzlh()
            return
        L_0x0030:
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x02b9 }
            if (r2 == 0) goto L_0x0043
            com.google.android.gms.internal.measurement.zzgn r0 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziv()     // Catch:{ all -> 0x02b9 }
            java.lang.String r2 = "Upload called in the client side when service should be used"
            goto L_0x0027
        L_0x0043:
            long r2 = r14.zzasa     // Catch:{ all -> 0x02b9 }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x004f
        L_0x004b:
            r14.zzlg()     // Catch:{ all -> 0x02b9 }
            goto L_0x002a
        L_0x004f:
            r14.zzab()     // Catch:{ all -> 0x02b9 }
            java.util.List<java.lang.Long> r2 = r14.zzasj     // Catch:{ all -> 0x02b9 }
            if (r2 == 0) goto L_0x0058
            r2 = 1
            goto L_0x0059
        L_0x0058:
            r2 = 0
        L_0x0059:
            if (r2 == 0) goto L_0x0068
            com.google.android.gms.internal.measurement.zzgn r0 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjc()     // Catch:{ all -> 0x02b9 }
            java.lang.String r2 = "Uploading requested multiple times"
            goto L_0x0027
        L_0x0068:
            com.google.android.gms.internal.measurement.zzfm r2 = r14.zzkz()     // Catch:{ all -> 0x02b9 }
            boolean r2 = r2.zzex()     // Catch:{ all -> 0x02b9 }
            if (r2 != 0) goto L_0x0082
            com.google.android.gms.internal.measurement.zzgn r0 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjc()     // Catch:{ all -> 0x02b9 }
            java.lang.String r2 = "Network not connected, ignoring upload request"
            r0.log(r2)     // Catch:{ all -> 0x02b9 }
            goto L_0x004b
        L_0x0082:
            com.google.android.gms.internal.measurement.zzgn r2 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.common.util.Clock r2 = r2.zzbt()     // Catch:{ all -> 0x02b9 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x02b9 }
            long r6 = com.google.android.gms.internal.measurement.zzeh.zzhr()     // Catch:{ all -> 0x02b9 }
            r8 = 0
            long r8 = r2 - r6
            r6 = 0
            r14.zzd(r6, r8)     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzgn r7 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzft r7 = r7.zzgj()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfw r7 = r7.zzalt     // Catch:{ all -> 0x02b9 }
            long r7 = r7.get()     // Catch:{ all -> 0x02b9 }
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00c1
            com.google.android.gms.internal.measurement.zzgn r4 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzgi()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zzjb()     // Catch:{ all -> 0x02b9 }
            java.lang.String r5 = "Uploading events. Elapsed time since last upload attempt (ms)"
            r9 = 0
            long r9 = r2 - r7
            long r7 = java.lang.Math.abs(r9)     // Catch:{ all -> 0x02b9 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x02b9 }
            r4.zzg(r5, r7)     // Catch:{ all -> 0x02b9 }
        L_0x00c1:
            com.google.android.gms.internal.measurement.zzek r4 = r14.zzjh()     // Catch:{ all -> 0x02b9 }
            java.lang.String r4 = r4.zzhv()     // Catch:{ all -> 0x02b9 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x02b9 }
            r7 = -1
            if (r5 != 0) goto L_0x0293
            long r9 = r14.zzasl     // Catch:{ all -> 0x02b9 }
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x00e1
            com.google.android.gms.internal.measurement.zzek r5 = r14.zzjh()     // Catch:{ all -> 0x02b9 }
            long r7 = r5.zzic()     // Catch:{ all -> 0x02b9 }
            r14.zzasl = r7     // Catch:{ all -> 0x02b9 }
        L_0x00e1:
            com.google.android.gms.internal.measurement.zzgn r5 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzeh r5 = r5.zzgk()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzez.zzaik     // Catch:{ all -> 0x02b9 }
            int r5 = r5.zzb(r4, r7)     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzgn r7 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzeh r7 = r7.zzgk()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.zzez.zzail     // Catch:{ all -> 0x02b9 }
            int r7 = r7.zzb(r4, r8)     // Catch:{ all -> 0x02b9 }
            int r7 = java.lang.Math.max(r1, r7)     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzek r8 = r14.zzjh()     // Catch:{ all -> 0x02b9 }
            java.util.List r5 = r8.zzb(r4, r5, r7)     // Catch:{ all -> 0x02b9 }
            boolean r7 = r5.isEmpty()     // Catch:{ all -> 0x02b9 }
            if (r7 != 0) goto L_0x002a
            java.util.Iterator r7 = r5.iterator()     // Catch:{ all -> 0x02b9 }
        L_0x010f:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x02b9 }
            if (r8 == 0) goto L_0x012a
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x02b9 }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x02b9 }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku r8 = (com.google.android.gms.internal.measurement.zzku) r8     // Catch:{ all -> 0x02b9 }
            java.lang.String r9 = r8.zzavv     // Catch:{ all -> 0x02b9 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x02b9 }
            if (r9 != 0) goto L_0x010f
            java.lang.String r7 = r8.zzavv     // Catch:{ all -> 0x02b9 }
            goto L_0x012b
        L_0x012a:
            r7 = r6
        L_0x012b:
            if (r7 == 0) goto L_0x0156
            r8 = 0
        L_0x012e:
            int r9 = r5.size()     // Catch:{ all -> 0x02b9 }
            if (r8 >= r9) goto L_0x0156
            java.lang.Object r9 = r5.get(r8)     // Catch:{ all -> 0x02b9 }
            android.util.Pair r9 = (android.util.Pair) r9     // Catch:{ all -> 0x02b9 }
            java.lang.Object r9 = r9.first     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku r9 = (com.google.android.gms.internal.measurement.zzku) r9     // Catch:{ all -> 0x02b9 }
            java.lang.String r10 = r9.zzavv     // Catch:{ all -> 0x02b9 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x02b9 }
            if (r10 != 0) goto L_0x0153
            java.lang.String r9 = r9.zzavv     // Catch:{ all -> 0x02b9 }
            boolean r9 = r9.equals(r7)     // Catch:{ all -> 0x02b9 }
            if (r9 != 0) goto L_0x0153
            java.util.List r5 = r5.subList(r1, r8)     // Catch:{ all -> 0x02b9 }
            goto L_0x0156
        L_0x0153:
            int r8 = r8 + 1
            goto L_0x012e
        L_0x0156:
            com.google.android.gms.internal.measurement.zzkt r7 = new com.google.android.gms.internal.measurement.zzkt     // Catch:{ all -> 0x02b9 }
            r7.<init>()     // Catch:{ all -> 0x02b9 }
            int r8 = r5.size()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku[] r8 = new com.google.android.gms.internal.measurement.zzku[r8]     // Catch:{ all -> 0x02b9 }
            r7.zzavf = r8     // Catch:{ all -> 0x02b9 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02b9 }
            int r9 = r5.size()     // Catch:{ all -> 0x02b9 }
            r8.<init>(r9)     // Catch:{ all -> 0x02b9 }
            boolean r9 = com.google.android.gms.internal.measurement.zzeh.zzht()     // Catch:{ all -> 0x02b9 }
            if (r9 == 0) goto L_0x0180
            com.google.android.gms.internal.measurement.zzgn r9 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzeh r9 = r9.zzgk()     // Catch:{ all -> 0x02b9 }
            boolean r9 = r9.zzau(r4)     // Catch:{ all -> 0x02b9 }
            if (r9 == 0) goto L_0x0180
            r9 = 1
            goto L_0x0181
        L_0x0180:
            r9 = 0
        L_0x0181:
            r10 = 0
        L_0x0182:
            com.google.android.gms.internal.measurement.zzku[] r11 = r7.zzavf     // Catch:{ all -> 0x02b9 }
            int r11 = r11.length     // Catch:{ all -> 0x02b9 }
            if (r10 >= r11) goto L_0x01da
            com.google.android.gms.internal.measurement.zzku[] r11 = r7.zzavf     // Catch:{ all -> 0x02b9 }
            java.lang.Object r12 = r5.get(r10)     // Catch:{ all -> 0x02b9 }
            android.util.Pair r12 = (android.util.Pair) r12     // Catch:{ all -> 0x02b9 }
            java.lang.Object r12 = r12.first     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku r12 = (com.google.android.gms.internal.measurement.zzku) r12     // Catch:{ all -> 0x02b9 }
            r11[r10] = r12     // Catch:{ all -> 0x02b9 }
            java.lang.Object r11 = r5.get(r10)     // Catch:{ all -> 0x02b9 }
            android.util.Pair r11 = (android.util.Pair) r11     // Catch:{ all -> 0x02b9 }
            java.lang.Object r11 = r11.second     // Catch:{ all -> 0x02b9 }
            java.lang.Long r11 = (java.lang.Long) r11     // Catch:{ all -> 0x02b9 }
            r8.add(r11)     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku[] r11 = r7.zzavf     // Catch:{ all -> 0x02b9 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzgn r12 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzeh r12 = r12.zzgk()     // Catch:{ all -> 0x02b9 }
            long r12 = r12.zzgw()     // Catch:{ all -> 0x02b9 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x02b9 }
            r11.zzavu = r12     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku[] r11 = r7.zzavf     // Catch:{ all -> 0x02b9 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b9 }
            java.lang.Long r12 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x02b9 }
            r11.zzavk = r12     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzku[] r11 = r7.zzavf     // Catch:{ all -> 0x02b9 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzgn r12 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            r12.zzgl()     // Catch:{ all -> 0x02b9 }
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x02b9 }
            r11.zzavz = r12     // Catch:{ all -> 0x02b9 }
            if (r9 != 0) goto L_0x01d7
            com.google.android.gms.internal.measurement.zzku[] r11 = r7.zzavf     // Catch:{ all -> 0x02b9 }
            r11 = r11[r10]     // Catch:{ all -> 0x02b9 }
            r11.zzawh = r6     // Catch:{ all -> 0x02b9 }
        L_0x01d7:
            int r10 = r10 + 1
            goto L_0x0182
        L_0x01da:
            com.google.android.gms.internal.measurement.zzgn r5 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzgi()     // Catch:{ all -> 0x02b9 }
            r9 = 2
            boolean r5 = r5.isLoggable(r9)     // Catch:{ all -> 0x02b9 }
            if (r5 == 0) goto L_0x01ef
            com.google.android.gms.internal.measurement.zzjz r5 = r14.zzjf()     // Catch:{ all -> 0x02b9 }
            java.lang.String r6 = r5.zzb(r7)     // Catch:{ all -> 0x02b9 }
        L_0x01ef:
            com.google.android.gms.internal.measurement.zzjz r5 = r14.zzjf()     // Catch:{ all -> 0x02b9 }
            byte[] r11 = r5.zza(r7)     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzez$zza<java.lang.String> r5 = com.google.android.gms.internal.measurement.zzez.zzaiu     // Catch:{ all -> 0x02b9 }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x02b9 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x02b9 }
            java.net.URL r10 = new java.net.URL     // Catch:{ MalformedURLException -> 0x027e }
            r10.<init>(r5)     // Catch:{ MalformedURLException -> 0x027e }
            boolean r9 = r8.isEmpty()     // Catch:{ MalformedURLException -> 0x027e }
            r9 = r9 ^ r0
            com.google.android.gms.common.internal.Preconditions.checkArgument(r9)     // Catch:{ MalformedURLException -> 0x027e }
            java.util.List<java.lang.Long> r9 = r14.zzasj     // Catch:{ MalformedURLException -> 0x027e }
            if (r9 == 0) goto L_0x0220
            com.google.android.gms.internal.measurement.zzgn r8 = r14.zzacv     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzgi()     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziv()     // Catch:{ MalformedURLException -> 0x027e }
            java.lang.String r9 = "Set uploading progress before finishing the previous upload"
            r8.log(r9)     // Catch:{ MalformedURLException -> 0x027e }
            goto L_0x0227
        L_0x0220:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x027e }
            r9.<init>(r8)     // Catch:{ MalformedURLException -> 0x027e }
            r14.zzasj = r9     // Catch:{ MalformedURLException -> 0x027e }
        L_0x0227:
            com.google.android.gms.internal.measurement.zzgn r8 = r14.zzacv     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzft r8 = r8.zzgj()     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfw r8 = r8.zzalu     // Catch:{ MalformedURLException -> 0x027e }
            r8.set(r2)     // Catch:{ MalformedURLException -> 0x027e }
            java.lang.String r2 = "?"
            com.google.android.gms.internal.measurement.zzku[] r3 = r7.zzavf     // Catch:{ MalformedURLException -> 0x027e }
            int r3 = r3.length     // Catch:{ MalformedURLException -> 0x027e }
            if (r3 <= 0) goto L_0x023f
            com.google.android.gms.internal.measurement.zzku[] r2 = r7.zzavf     // Catch:{ MalformedURLException -> 0x027e }
            r2 = r2[r1]     // Catch:{ MalformedURLException -> 0x027e }
            java.lang.String r2 = r2.zzth     // Catch:{ MalformedURLException -> 0x027e }
        L_0x023f:
            com.google.android.gms.internal.measurement.zzgn r3 = r14.zzacv     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzgi()     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zzjc()     // Catch:{ MalformedURLException -> 0x027e }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r11.length     // Catch:{ MalformedURLException -> 0x027e }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x027e }
            r3.zzd(r7, r2, r8, r6)     // Catch:{ MalformedURLException -> 0x027e }
            r14.zzasf = r0     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfm r8 = r14.zzkz()     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzjv r13 = new com.google.android.gms.internal.measurement.zzjv     // Catch:{ MalformedURLException -> 0x027e }
            r13.<init>(r14, r4)     // Catch:{ MalformedURLException -> 0x027e }
            r8.zzab()     // Catch:{ MalformedURLException -> 0x027e }
            r8.zzch()     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzgi r0 = r8.zzgh()     // Catch:{ MalformedURLException -> 0x027e }
            com.google.android.gms.internal.measurement.zzfq r2 = new com.google.android.gms.internal.measurement.zzfq     // Catch:{ MalformedURLException -> 0x027e }
            r12 = 0
            r7 = r2
            r9 = r4
            r7.<init>(r8, r9, r10, r11, r12, r13)     // Catch:{ MalformedURLException -> 0x027e }
            r0.zzd(r2)     // Catch:{ MalformedURLException -> 0x027e }
            goto L_0x002a
        L_0x027e:
            com.google.android.gms.internal.measurement.zzgn r0 = r14.zzacv     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziv()     // Catch:{ all -> 0x02b9 }
            java.lang.String r2 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r4)     // Catch:{ all -> 0x02b9 }
            r0.zze(r2, r3, r5)     // Catch:{ all -> 0x02b9 }
            goto L_0x002a
        L_0x0293:
            r14.zzasl = r7     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzek r0 = r14.zzjh()     // Catch:{ all -> 0x02b9 }
            long r4 = com.google.android.gms.internal.measurement.zzeh.zzhr()     // Catch:{ all -> 0x02b9 }
            r6 = 0
            long r6 = r2 - r4
            java.lang.String r0 = r0.zzag(r6)     // Catch:{ all -> 0x02b9 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x02b9 }
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.internal.measurement.zzek r2 = r14.zzjh()     // Catch:{ all -> 0x02b9 }
            com.google.android.gms.internal.measurement.zzea r0 = r2.zzbf(r0)     // Catch:{ all -> 0x02b9 }
            if (r0 == 0) goto L_0x002a
            r14.zzb(r0)     // Catch:{ all -> 0x02b9 }
            goto L_0x002a
        L_0x02b9:
            r0 = move-exception
            r14.zzasg = r1
            r14.zzlh()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjt.zzle():void");
    }

    /* access modifiers changed from: 0000 */
    public final void zzlj() {
        zzfk zziv;
        String str;
        zzab();
        zzlc();
        if (!this.zzarz) {
            this.zzacv.zzgi().zzja().log("This instance being marked as an uploader");
            zzab();
            zzlc();
            if (zzlk() && zzli()) {
                int zza2 = zza(this.zzasi);
                int zzis = this.zzacv.zzfz().zzis();
                zzab();
                if (zza2 > zzis) {
                    zziv = this.zzacv.zzgi().zziv();
                    str = "Panic: can't downgrade version. Previous, current version";
                } else if (zza2 < zzis) {
                    if (zza(zzis, this.zzasi)) {
                        zziv = this.zzacv.zzgi().zzjc();
                        str = "Storage version upgraded. Previous, current version";
                    } else {
                        zziv = this.zzacv.zzgi().zziv();
                        str = "Storage version upgrade failed. Previous, current version";
                    }
                }
                zziv.zze(str, Integer.valueOf(zza2), Integer.valueOf(zzis));
            }
            this.zzarz = true;
            zzlg();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzll() {
        this.zzasd++;
    }

    /* access modifiers changed from: 0000 */
    public final zzgn zzlm() {
        return this.zzacv;
    }

    /* access modifiers changed from: 0000 */
    public final void zzm(boolean z) {
        zzlg();
    }
}
