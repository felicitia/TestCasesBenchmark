package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzbf extends zzar {
    private boolean started;
    private final zzbc zzwz;
    private final zzco zzxa;
    private final zzcn zzxb;
    private final zzax zzxc;
    private long zzxd = Long.MIN_VALUE;
    private final zzbw zzxe;
    private final zzbw zzxf;
    private final zzcz zzxg;
    private long zzxh;
    private boolean zzxi;

    protected zzbf(zzat zzat, zzav zzav) {
        super(zzat);
        Preconditions.checkNotNull(zzav);
        this.zzxb = new zzcn(zzat);
        this.zzwz = new zzbc(zzat);
        this.zzxa = new zzco(zzat);
        this.zzxc = new zzax(zzat);
        this.zzxg = new zzcz(zzbt());
        this.zzxe = new zzbg(this, zzat);
        this.zzxf = new zzbh(this, zzat);
    }

    private final void zza(zzaw zzaw, zzv zzv) {
        Preconditions.checkNotNull(zzaw);
        Preconditions.checkNotNull(zzv);
        zza zza = new zza(zzbs());
        zza.zza(zzaw.zzcp());
        zza.enableAdvertisingIdCollection(zzaw.zzcq());
        zzg zzi = zza.zzi();
        zzad zzad = (zzad) zzi.zzb(zzad.class);
        zzad.zzl("data");
        zzad.zzb(true);
        zzi.zza((zzi) zzv);
        zzy zzy = (zzy) zzi.zzb(zzy.class);
        zzu zzu = (zzu) zzi.zzb(zzu.class);
        for (Entry entry : zzaw.zzcs().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                zzu.setAppName(str2);
            } else if ("av".equals(str)) {
                zzu.setAppVersion(str2);
            } else if ("aid".equals(str)) {
                zzu.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzu.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzad.setUserId(str2);
            } else {
                zzy.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzaw.zzcp(), zzv);
        zzi.zza(zzcb().zzfb());
        zzi.zzs();
    }

    private final long zzcy() {
        zzk.zzab();
        zzch();
        try {
            return this.zzwz.zzcy();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public final void zzdd() {
        zzb((zzca) new zzbj(this));
    }

    /* access modifiers changed from: private */
    public final void zzde() {
        try {
            this.zzwz.zzcx();
            zzdi();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzxf.zzh(86400000);
    }

    private final void zzdf() {
        if (!this.zzxi && zzbu.zzdt() && !this.zzxc.isConnected()) {
            if (this.zzxg.zzj(((Long) zzcc.zzzx.get()).longValue())) {
                this.zzxg.start();
                zzq("Connecting to service");
                if (this.zzxc.connect()) {
                    zzq("Connected to service");
                    this.zzxg.clear();
                    onServiceConnected();
                }
            }
        }
    }

    private final boolean zzdg() {
        zzk.zzab();
        zzch();
        zzq("Dispatching a batch of local hits");
        boolean z = !this.zzxa.zzex();
        if (!(!this.zzxc.isConnected()) || !z) {
            long max = (long) Math.max(zzbu.zzdx(), zzbu.zzdy());
            ArrayList arrayList = new ArrayList();
            long j = 0;
            while (true) {
                try {
                    this.zzwz.beginTransaction();
                    arrayList.clear();
                    try {
                        List<zzch> zzd = this.zzwz.zzd(max);
                        if (zzd.isEmpty()) {
                            zzq("Store is empty, nothing to dispatch");
                            zzdk();
                            try {
                                this.zzwz.setTransactionSuccessful();
                                this.zzwz.endTransaction();
                                return false;
                            } catch (SQLiteException e) {
                                zze("Failed to commit local dispatch transaction", e);
                                zzdk();
                                return false;
                            }
                        } else {
                            zza("Hits loaded from store. count", Integer.valueOf(zzd.size()));
                            for (zzch zzem : zzd) {
                                if (zzem.zzem() == j) {
                                    zzd("Database contains successfully uploaded hit", Long.valueOf(j), Integer.valueOf(zzd.size()));
                                    zzdk();
                                    try {
                                        return false;
                                    } catch (SQLiteException e2) {
                                        zze("Failed to commit local dispatch transaction", e2);
                                        zzdk();
                                        return false;
                                    }
                                }
                            }
                            if (this.zzxc.isConnected()) {
                                zzq("Service connected, sending hits to the service");
                                while (!zzd.isEmpty()) {
                                    zzch zzch = (zzch) zzd.get(0);
                                    if (this.zzxc.zzb(zzch)) {
                                        j = Math.max(j, zzch.zzem());
                                        zzd.remove(zzch);
                                        zzb("Hit sent do device AnalyticsService for delivery", zzch);
                                        this.zzwz.zze(zzch.zzem());
                                        arrayList.add(Long.valueOf(zzch.zzem()));
                                    }
                                }
                            }
                            if (this.zzxa.zzex()) {
                                List<Long> zzb = this.zzxa.zzb(zzd);
                                for (Long longValue : zzb) {
                                    j = Math.max(j, longValue.longValue());
                                }
                                try {
                                    this.zzwz.zza(zzb);
                                    arrayList.addAll(zzb);
                                } catch (SQLiteException e3) {
                                    zze("Failed to remove successfully uploaded hits", e3);
                                    zzdk();
                                    try {
                                        this.zzwz.setTransactionSuccessful();
                                        this.zzwz.endTransaction();
                                        return false;
                                    } catch (SQLiteException e4) {
                                        zze("Failed to commit local dispatch transaction", e4);
                                        zzdk();
                                        return false;
                                    }
                                }
                            }
                            if (arrayList.isEmpty()) {
                                try {
                                    this.zzwz.setTransactionSuccessful();
                                    this.zzwz.endTransaction();
                                    return false;
                                } catch (SQLiteException e5) {
                                    zze("Failed to commit local dispatch transaction", e5);
                                    zzdk();
                                    return false;
                                }
                            } else {
                                try {
                                    this.zzwz.setTransactionSuccessful();
                                    this.zzwz.endTransaction();
                                } catch (SQLiteException e6) {
                                    zze("Failed to commit local dispatch transaction", e6);
                                    zzdk();
                                    return false;
                                }
                            }
                        }
                    } catch (SQLiteException e7) {
                        zzd("Failed to read hits from persisted store", e7);
                        zzdk();
                        try {
                            this.zzwz.setTransactionSuccessful();
                            this.zzwz.endTransaction();
                            return false;
                        } catch (SQLiteException e8) {
                            zze("Failed to commit local dispatch transaction", e8);
                            zzdk();
                            return false;
                        }
                    }
                } catch (SQLiteException e9) {
                    zze("Failed to remove hit that was send for delivery", e9);
                    zzdk();
                    try {
                        return false;
                    } catch (SQLiteException e10) {
                        zze("Failed to commit local dispatch transaction", e10);
                        zzdk();
                        return false;
                    }
                } finally {
                    try {
                        this.zzwz.setTransactionSuccessful();
                        this.zzwz.endTransaction();
                    } catch (SQLiteException e11) {
                        zze("Failed to commit local dispatch transaction", e11);
                        zzdk();
                        return false;
                    }
                }
            }
        } else {
            zzq("No network or service available. Will retry later");
            return false;
        }
    }

    private final void zzdj() {
        zzbz zzbz = zzbz();
        if (zzbz.zzei() && !zzbz.zzef()) {
            long zzcy = zzcy();
            if (zzcy != 0 && Math.abs(zzbt().currentTimeMillis() - zzcy) <= ((Long) zzcc.zzyw.get()).longValue()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzbu.zzdw()));
                zzbz.zzej();
            }
        }
    }

    private final void zzdk() {
        if (this.zzxe.zzef()) {
            zzq("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzxe.cancel();
        zzbz zzbz = zzbz();
        if (zzbz.zzef()) {
            zzbz.cancel();
        }
    }

    private final long zzdl() {
        if (this.zzxd != Long.MIN_VALUE) {
            return this.zzxd;
        }
        long longValue = ((Long) zzcc.zzyr.get()).longValue();
        zzde zzca = zzca();
        zzca.zzch();
        if (zzca.zzace) {
            zzde zzca2 = zzca();
            zzca2.zzch();
            longValue = ((long) zzca2.zzaag) * 1000;
        }
        return longValue;
    }

    private final void zzdm() {
        zzch();
        zzk.zzab();
        this.zzxi = true;
        this.zzxc.disconnect();
        zzdi();
    }

    private final boolean zzx(String str) {
        return Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(str) == 0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044 A[LOOP:1: B:15:0x0044->B:23:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0040 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected() {
        /*
            r5 = this;
            com.google.android.gms.analytics.zzk.zzab()
            com.google.android.gms.analytics.zzk.zzab()
            r5.zzch()
            boolean r0 = com.google.android.gms.internal.measurement.zzbu.zzdt()
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService"
            r5.zzt(r0)
        L_0x0014:
            com.google.android.gms.internal.measurement.zzax r0 = r5.zzxc
            boolean r0 = r0.isConnected()
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "Service not connected"
            r5.zzq(r0)
            return
        L_0x0022:
            com.google.android.gms.internal.measurement.zzbc r0 = r5.zzwz
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x007d
            java.lang.String r0 = "Dispatching local hits to device AnalyticsService"
            r5.zzq(r0)
        L_0x002f:
            com.google.android.gms.internal.measurement.zzbc r0 = r5.zzwz     // Catch:{ SQLiteException -> 0x0074 }
            int r1 = com.google.android.gms.internal.measurement.zzbu.zzdx()     // Catch:{ SQLiteException -> 0x0074 }
            long r1 = (long) r1     // Catch:{ SQLiteException -> 0x0074 }
            java.util.List r0 = r0.zzd(r1)     // Catch:{ SQLiteException -> 0x0074 }
            boolean r1 = r0.isEmpty()     // Catch:{ SQLiteException -> 0x0074 }
            if (r1 == 0) goto L_0x0044
            r5.zzdi()     // Catch:{ SQLiteException -> 0x0074 }
            return
        L_0x0044:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x002f
            r1 = 0
            java.lang.Object r1 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzch r1 = (com.google.android.gms.internal.measurement.zzch) r1
            com.google.android.gms.internal.measurement.zzax r2 = r5.zzxc
            boolean r2 = r2.zzb(r1)
            if (r2 != 0) goto L_0x005d
            r5.zzdi()
            return
        L_0x005d:
            r0.remove(r1)
            com.google.android.gms.internal.measurement.zzbc r2 = r5.zzwz     // Catch:{ SQLiteException -> 0x006a }
            long r3 = r1.zzem()     // Catch:{ SQLiteException -> 0x006a }
            r2.zze(r3)     // Catch:{ SQLiteException -> 0x006a }
            goto L_0x0044
        L_0x006a:
            r0 = move-exception
            java.lang.String r1 = "Failed to remove hit that was send for delivery"
            r5.zze(r1, r0)
            r5.zzdk()
            return
        L_0x0074:
            r0 = move-exception
            java.lang.String r1 = "Failed to read hits from store"
            r5.zze(r1, r0)
            r5.zzdk()
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.onServiceConnected():void");
    }

    /* access modifiers changed from: 0000 */
    public final void start() {
        zzch();
        Preconditions.checkState(!this.started, "Analytics backend already started");
        this.started = true;
        zzbw().zza((Runnable) new zzbi(this));
    }

    public final long zza(zzaw zzaw, boolean z) {
        Preconditions.checkNotNull(zzaw);
        zzch();
        zzk.zzab();
        try {
            this.zzwz.beginTransaction();
            zzbc zzbc = this.zzwz;
            long zzco = zzaw.zzco();
            String zzaz = zzaw.zzaz();
            Preconditions.checkNotEmpty(zzaz);
            zzbc.zzch();
            zzk.zzab();
            int delete = zzbc.getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(zzco), zzaz});
            if (delete > 0) {
                zzbc.zza("Deleted property records", Integer.valueOf(delete));
            }
            long zza = this.zzwz.zza(zzaw.zzco(), zzaw.zzaz(), zzaw.zzcp());
            zzaw.zzb(zza + 1);
            zzbc zzbc2 = this.zzwz;
            Preconditions.checkNotNull(zzaw);
            zzbc2.zzch();
            zzk.zzab();
            SQLiteDatabase writableDatabase = zzbc2.getWritableDatabase();
            Map zzcs = zzaw.zzcs();
            Preconditions.checkNotNull(zzcs);
            Builder builder = new Builder();
            for (Entry entry : zzcs.entrySet()) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            String encodedQuery = builder.build().getEncodedQuery();
            if (encodedQuery == null) {
                encodedQuery = "";
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_uid", Long.valueOf(zzaw.zzco()));
            contentValues.put("cid", zzaw.zzaz());
            contentValues.put("tid", zzaw.zzcp());
            contentValues.put("adid", Integer.valueOf(zzaw.zzcq() ? 1 : 0));
            contentValues.put("hits_count", Long.valueOf(zzaw.zzcr()));
            contentValues.put("params", encodedQuery);
            try {
                if (writableDatabase.insertWithOnConflict("properties", null, contentValues, 5) == -1) {
                    zzbc2.zzu("Failed to insert/update a property (got -1)");
                }
            } catch (SQLiteException e) {
                zzbc2.zze("Error storing a property", e);
            }
            this.zzwz.setTransactionSuccessful();
            try {
                return zza;
            } catch (SQLiteException e2) {
                zze("Failed to end transaction", e2);
                return zza;
            }
        } catch (SQLiteException e3) {
            zze("Failed to update Analytics property", e3);
            try {
                return -1;
            } catch (SQLiteException e4) {
                zze("Failed to end transaction", e4);
                return -1;
            }
        } finally {
            try {
                this.zzwz.endTransaction();
            } catch (SQLiteException e5) {
                zze("Failed to end transaction", e5);
            }
        }
    }

    public final void zza(zzch zzch) {
        Preconditions.checkNotNull(zzch);
        zzk.zzab();
        zzch();
        if (this.zzxi) {
            zzr("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzch);
        }
        if (TextUtils.isEmpty(zzch.zzer())) {
            Pair zzfi = zzcb().zzfg().zzfi();
            if (zzfi != null) {
                Long l = (Long) zzfi.second;
                String str = (String) zzfi.first;
                String valueOf = String.valueOf(l);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
                sb.append(valueOf);
                sb.append(":");
                sb.append(str);
                String sb2 = sb.toString();
                HashMap hashMap = new HashMap(zzch.zzcs());
                hashMap.put("_m", sb2);
                zzch zzch2 = new zzch(this, hashMap, zzch.zzen(), zzch.zzep(), zzch.zzem(), zzch.zzel(), zzch.zzeo());
                zzch = zzch2;
            }
        }
        zzdf();
        if (this.zzxc.zzb(zzch)) {
            zzr("Hit sent to the device AnalyticsService for delivery");
            return;
        }
        try {
            this.zzwz.zzc(zzch);
            zzdi();
        } catch (SQLiteException e) {
            zze("Delivery failed to save hit to a database", e);
            zzbu().zza(zzch, "deliver: failed to insert hit to database");
        }
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        this.zzwz.zzm();
        this.zzxa.zzm();
        this.zzxc.zzm();
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzaw zzaw) {
        zzk.zzab();
        zzb("Sending first hit to property", zzaw.zzcp());
        if (!zzcb().zzfc().zzj(zzbu.zzed())) {
            String zzff = zzcb().zzff();
            if (!TextUtils.isEmpty(zzff)) {
                zzv zza = zzdd.zza(zzbu(), zzff);
                zzb("Found relevant installation campaign", zza);
                zza(zzaw, zza);
            }
        }
    }

    public final void zzb(zzca zzca) {
        long j = this.zzxh;
        zzk.zzab();
        zzch();
        long zzfd = zzcb().zzfd();
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(zzfd != 0 ? Math.abs(zzbt().currentTimeMillis() - zzfd) : -1));
        zzdf();
        try {
            zzdg();
            zzcb().zzfe();
            zzdi();
            if (zzca != null) {
                zzca.zza(null);
            }
            if (this.zzxh != j) {
                this.zzxb.zzew();
            }
        } catch (Exception e) {
            zze("Local dispatch failed", e);
            zzcb().zzfe();
            zzdi();
            if (zzca != null) {
                zzca.zza(e);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzbr() {
        zzk.zzab();
        this.zzxh = zzbt().currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public final void zzdc() {
        zzch();
        zzk.zzab();
        Context context = zzbs().getContext();
        if (!zzct.zza(context)) {
            zzt("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzcu.zze(context)) {
            zzu("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zza(context)) {
            zzt("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzcb().zzfb();
        if (!zzx("android.permission.ACCESS_NETWORK_STATE")) {
            zzu("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdm();
        }
        if (!zzx("android.permission.INTERNET")) {
            zzu("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzdm();
        }
        if (zzcu.zze(getContext())) {
            zzq("AnalyticsService registered in the app manifest and enabled");
        } else {
            zzt("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!this.zzxi && !this.zzwz.isEmpty()) {
            zzdf();
        }
        zzdi();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0074, code lost:
        if (r6 > 0) goto L_0x007f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdi() {
        /*
            r10 = this;
            com.google.android.gms.analytics.zzk.zzab()
            r10.zzch()
            boolean r0 = r10.zzxi
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0017
            long r4 = r10.zzdl()
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0017
            r0 = 1
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 != 0) goto L_0x0023
            com.google.android.gms.internal.measurement.zzcn r0 = r10.zzxb
            r0.unregister()
            r10.zzdk()
            return
        L_0x0023:
            com.google.android.gms.internal.measurement.zzbc r0 = r10.zzwz
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0034
            com.google.android.gms.internal.measurement.zzcn r0 = r10.zzxb
            r0.unregister()
            r10.zzdk()
            return
        L_0x0034:
            com.google.android.gms.internal.measurement.zzcd<java.lang.Boolean> r0 = com.google.android.gms.internal.measurement.zzcc.zzzs
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x004d
            com.google.android.gms.internal.measurement.zzcn r0 = r10.zzxb
            r0.zzeu()
            com.google.android.gms.internal.measurement.zzcn r0 = r10.zzxb
            boolean r1 = r0.isConnected()
        L_0x004d:
            if (r1 == 0) goto L_0x00aa
            r10.zzdj()
            long r0 = r10.zzdl()
            com.google.android.gms.internal.measurement.zzcq r4 = r10.zzcb()
            long r4 = r4.zzfd()
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0077
            com.google.android.gms.common.util.Clock r6 = r10.zzbt()
            long r6 = r6.currentTimeMillis()
            long r8 = r6 - r4
            long r4 = java.lang.Math.abs(r8)
            long r6 = r0 - r4
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0077
            goto L_0x007f
        L_0x0077:
            long r2 = com.google.android.gms.internal.measurement.zzbu.zzdv()
            long r6 = java.lang.Math.min(r2, r0)
        L_0x007f:
            java.lang.String r0 = "Dispatch scheduled (ms)"
            java.lang.Long r1 = java.lang.Long.valueOf(r6)
            r10.zza(r0, r1)
            com.google.android.gms.internal.measurement.zzbw r0 = r10.zzxe
            boolean r0 = r0.zzef()
            if (r0 == 0) goto L_0x00a4
            r0 = 1
            com.google.android.gms.internal.measurement.zzbw r2 = r10.zzxe
            long r2 = r2.zzee()
            long r4 = r6 + r2
            long r0 = java.lang.Math.max(r0, r4)
            com.google.android.gms.internal.measurement.zzbw r2 = r10.zzxe
            r2.zzi(r0)
            return
        L_0x00a4:
            com.google.android.gms.internal.measurement.zzbw r0 = r10.zzxe
            r0.zzh(r6)
            return
        L_0x00aa:
            r10.zzdk()
            r10.zzdj()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbf.zzdi():void");
    }

    public final void zzg(long j) {
        zzk.zzab();
        zzch();
        if (j < 0) {
            j = 0;
        }
        this.zzxd = j;
        zzdi();
    }

    public final void zzy(String str) {
        Preconditions.checkNotEmpty(str);
        zzk.zzab();
        zzv zza = zzdd.zza(zzbu(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzff = zzcb().zzff();
        if (str.equals(zzff)) {
            zzt("Ignoring duplicate install campaign");
        } else if (!TextUtils.isEmpty(zzff)) {
            zzd("Ignoring multiple install campaigns. original, new", zzff, str);
        } else {
            zzcb().zzac(str);
            if (zzcb().zzfc().zzj(zzbu.zzed())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzaw zza2 : this.zzwz.zzf(0)) {
                zza(zza2, zza);
            }
        }
    }
}
