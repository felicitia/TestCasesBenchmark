package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.Clock;

public final class zzfe extends zzdz {
    private final zzff zzakf = new zzff(this, getContext(), "google_app_measurement_local.db");
    private boolean zzakg;

    zzfe(zzgn zzgn) {
        super(zzgn);
    }

    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzakg) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzakf.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzakg = true;
        return null;
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r12v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r9v2 */
    /* JADX WARNING: type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: type inference failed for: r9v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: type inference failed for: r12v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v7 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r9v8 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r9v9, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r12v8, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r3v19 */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: type inference failed for: r7v12 */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* JADX WARNING: type inference failed for: r7v14 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r9v12 */
    /* JADX WARNING: type inference failed for: r7v15 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* JADX WARNING: type inference failed for: r9v14 */
    /* JADX WARNING: type inference failed for: r7v17 */
    /* JADX WARNING: type inference failed for: r7v18 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r9v15 */
    /* JADX WARNING: type inference failed for: r9v16 */
    /* JADX WARNING: type inference failed for: r9v17 */
    /* JADX WARNING: type inference failed for: r9v18 */
    /* JADX WARNING: type inference failed for: r9v19 */
    /* JADX WARNING: type inference failed for: r9v20 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d6, code lost:
        r2 = r0;
        r12 = 0;
        r9 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00db, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00dc, code lost:
        r3 = r0;
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e1, code lost:
        r7 = 0;
        r9 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f8, code lost:
        if (r7.inTransaction() != false) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00fa, code lost:
        r7.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00fe, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ff, code lost:
        r2 = r0;
        r9 = r7;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0114, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0119, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0127, code lost:
        r7.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v1, types: [boolean, int]
      assigns: []
      uses: [?[int, short, byte, char], int, boolean]
      mth insns count: 182
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x014c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x014c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x014c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:9:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f4 A[SYNTHETIC, Splitter:B:60:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x015c  */
    /* JADX WARNING: Unknown variable types count: 19 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r20, byte[] r21) {
        /*
            r19 = this;
            r1 = r19
            r19.zzfv()
            r19.zzab()
            boolean r2 = r1.zzakg
            r3 = 0
            if (r2 == 0) goto L_0x000e
            return r3
        L_0x000e:
            android.content.ContentValues r2 = new android.content.ContentValues
            r2.<init>()
            java.lang.String r4 = "type"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r20)
            r2.put(r4, r5)
            java.lang.String r4 = "entry"
            r5 = r21
            r2.put(r4, r5)
            r4 = 5
            r5 = 0
            r6 = 5
        L_0x0026:
            if (r5 >= r4) goto L_0x0160
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r19.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x0130, SQLiteDatabaseLockedException -> 0x011d, SQLiteException -> 0x00ee, all -> 0x00e7 }
            if (r9 != 0) goto L_0x0045
            r1.zzakg = r8     // Catch:{ SQLiteFullException -> 0x0041, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x0038 }
            if (r9 == 0) goto L_0x0037
            r9.close()
        L_0x0037:
            return r3
        L_0x0038:
            r0 = move-exception
            r3 = r0
            r12 = r7
        L_0x003b:
            r7 = r9
            goto L_0x00f2
        L_0x003e:
            r4 = r7
            goto L_0x00e1
        L_0x0041:
            r0 = move-exception
        L_0x0042:
            r3 = r0
            goto L_0x0134
        L_0x0045:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00e3, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x00db, all -> 0x00d5 }
            r10 = 0
            java.lang.String r12 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r12, r7)     // Catch:{ SQLiteFullException -> 0x00e3, SQLiteDatabaseLockedException -> 0x003e, SQLiteException -> 0x00db, all -> 0x00d5 }
            if (r12 == 0) goto L_0x0069
            boolean r13 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            if (r13 == 0) goto L_0x0069
            long r10 = r12.getLong(r3)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            goto L_0x0069
        L_0x005d:
            r0 = move-exception
            r2 = r0
            goto L_0x0155
        L_0x0061:
            r0 = move-exception
            r3 = r0
            goto L_0x003b
        L_0x0064:
            r0 = move-exception
            r3 = r0
            r7 = r12
            goto L_0x0134
        L_0x0069:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r15 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r15 < 0) goto L_0x00ba
            com.google.android.gms.internal.measurement.zzfi r15 = r19.zzgi()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            com.google.android.gms.internal.measurement.zzfk r15 = r15.zziv()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            java.lang.String r4 = "Data loss, local db full"
            r15.log(r4)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            r4 = 0
            long r16 = r13 - r10
            r10 = 1
            long r13 = r16 + r10
            java.lang.String r4 = "messages"
            java.lang.String r10 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            java.lang.String r15 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            r11[r3] = r15     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            int r4 = r9.delete(r4, r10, r11)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            long r10 = (long) r4     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            int r4 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x00ba
            com.google.android.gms.internal.measurement.zzfi r4 = r19.zzgi()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            r16 = 0
            r18 = r8
            long r7 = r13 - r10
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            r8 = r18
            r4.zzd(r15, r3, r8, r7)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
        L_0x00ba:
            java.lang.String r3 = "messages"
            r4 = 0
            r9.insertOrThrow(r3, r4, r2)     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0064, SQLiteDatabaseLockedException -> 0x00d2, SQLiteException -> 0x0061, all -> 0x005d }
            if (r12 == 0) goto L_0x00cb
            r12.close()
        L_0x00cb:
            if (r9 == 0) goto L_0x00d0
            r9.close()
        L_0x00d0:
            r2 = 1
            return r2
        L_0x00d2:
            r7 = r12
            goto L_0x011f
        L_0x00d5:
            r0 = move-exception
            r4 = r7
            r2 = r0
            r12 = r4
            goto L_0x0155
        L_0x00db:
            r0 = move-exception
            r4 = r7
            r3 = r0
            r12 = r4
            goto L_0x003b
        L_0x00e1:
            r7 = r4
            goto L_0x011f
        L_0x00e3:
            r0 = move-exception
            r4 = r7
            goto L_0x0042
        L_0x00e7:
            r0 = move-exception
            r4 = r7
            r2 = r0
            r9 = r4
            r12 = r9
            goto L_0x0155
        L_0x00ee:
            r0 = move-exception
            r4 = r7
            r3 = r0
            r12 = r7
        L_0x00f2:
            if (r7 == 0) goto L_0x0102
            boolean r4 = r7.inTransaction()     // Catch:{ all -> 0x00fe }
            if (r4 == 0) goto L_0x0102
            r7.endTransaction()     // Catch:{ all -> 0x00fe }
            goto L_0x0102
        L_0x00fe:
            r0 = move-exception
            r2 = r0
            r9 = r7
            goto L_0x0155
        L_0x0102:
            com.google.android.gms.internal.measurement.zzfi r4 = r19.zzgi()     // Catch:{ all -> 0x00fe }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x00fe }
            java.lang.String r8 = "Error writing entry to local database"
            r4.zzg(r8, r3)     // Catch:{ all -> 0x00fe }
            r3 = 1
            r1.zzakg = r3     // Catch:{ all -> 0x00fe }
            if (r12 == 0) goto L_0x0117
            r12.close()
        L_0x0117:
            if (r7 == 0) goto L_0x014c
            r7.close()
            goto L_0x014c
        L_0x011d:
            r4 = r7
            r9 = r7
        L_0x011f:
            long r3 = (long) r6
            android.os.SystemClock.sleep(r3)     // Catch:{ all -> 0x0152 }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x012a
            r7.close()
        L_0x012a:
            if (r9 == 0) goto L_0x014c
        L_0x012c:
            r9.close()
            goto L_0x014c
        L_0x0130:
            r0 = move-exception
            r4 = r7
            r3 = r0
            r9 = r7
        L_0x0134:
            com.google.android.gms.internal.measurement.zzfi r4 = r19.zzgi()     // Catch:{ all -> 0x0152 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x0152 }
            java.lang.String r8 = "Error writing entry to local database"
            r4.zzg(r8, r3)     // Catch:{ all -> 0x0152 }
            r3 = 1
            r1.zzakg = r3     // Catch:{ all -> 0x0152 }
            if (r7 == 0) goto L_0x0149
            r7.close()
        L_0x0149:
            if (r9 == 0) goto L_0x014c
            goto L_0x012c
        L_0x014c:
            int r5 = r5 + 1
            r3 = 0
            r4 = 5
            goto L_0x0026
        L_0x0152:
            r0 = move-exception
            r2 = r0
            r12 = r7
        L_0x0155:
            if (r12 == 0) goto L_0x015a
            r12.close()
        L_0x015a:
            if (r9 == 0) goto L_0x015f
            r9.close()
        L_0x015f:
            throw r2
        L_0x0160:
            com.google.android.gms.internal.measurement.zzfi r2 = r19.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziy()
            java.lang.String r3 = "Failed to write entry to local database"
            r2.log(r3)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfe.zza(int, byte[]):boolean");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final void resetAnalyticsData() {
        zzfv();
        zzab();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzgi().zzjc().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgi().zziv().zzg("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzex zzex) {
        Parcel obtain = Parcel.obtain();
        zzex.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzgi().zziy().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzka zzka) {
        Parcel obtain = Parcel.obtain();
        zzka.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzgi().zziy().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzc(zzef zzef) {
        zzgg();
        byte[] zza = zzkd.zza((Parcelable) zzef);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzgi().zziy().log("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzdu zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzhm zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzfd zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzik zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzih zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzfe zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzjj zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>] */
    /* JADX WARNING: type inference failed for: r9v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r9v2 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r9v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v4 */
    /* JADX WARNING: type inference failed for: r3v8, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r9v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v7 */
    /* JADX WARNING: type inference failed for: r9v8 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r9v9 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r9v17, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r9v18 */
    /* JADX WARNING: type inference failed for: r9v19 */
    /* JADX WARNING: type inference failed for: r9v20 */
    /* JADX WARNING: type inference failed for: r9v21 */
    /* JADX WARNING: type inference failed for: r9v22 */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r9v23 */
    /* JADX WARNING: type inference failed for: r9v24 */
    /* JADX WARNING: type inference failed for: r3v27 */
    /* JADX WARNING: type inference failed for: r9v25 */
    /* JADX WARNING: type inference failed for: r9v26 */
    /* JADX WARNING: type inference failed for: r9v27 */
    /* JADX WARNING: type inference failed for: r3v28 */
    /* JADX WARNING: type inference failed for: r9v28 */
    /* JADX WARNING: type inference failed for: r9v29 */
    /* JADX WARNING: type inference failed for: r9v30 */
    /* JADX WARNING: type inference failed for: r3v29 */
    /* JADX WARNING: type inference failed for: r9v31 */
    /* JADX WARNING: type inference failed for: r9v32 */
    /* JADX WARNING: type inference failed for: r9v33 */
    /* JADX WARNING: type inference failed for: r9v34 */
    /* JADX WARNING: type inference failed for: r9v35 */
    /* JADX WARNING: type inference failed for: r9v36 */
    /* JADX WARNING: type inference failed for: r9v37 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:61|62|63|64) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:77|78|79|80) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:47|48|49|50|175) */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0189, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x018a, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x018d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x018e, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0195, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0196, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01a6, code lost:
        r9 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01aa, code lost:
        if (r15.inTransaction() != false) goto L_0x01ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01ac, code lost:
        r15.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01c0, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x01d0, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x01d5, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x01f3, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0201, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0202, code lost:
        r2 = r0;
        r9 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0206, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x020b, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        zzgi().zziv().log("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        zzgi().zziv().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r12.recycle();
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        zzgi().zziv().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        r12.recycle();
        r13 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00b2 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00e3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x0117 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r9v1
      assigns: []
      uses: []
      mth insns count: 254
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01a6 A[SYNTHETIC, Splitter:B:125:0x01a6] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x01fb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x01fb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x01fb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0031] */
    /* JADX WARNING: Unknown variable types count: 17 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzp(int r22) {
        /*
            r21 = this;
            r1 = r21
            r21.zzab()
            r21.zzfv()
            boolean r2 = r1.zzakg
            r3 = 0
            if (r2 == 0) goto L_0x000e
            return r3
        L_0x000e:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.content.Context r4 = r21.getContext()
            java.lang.String r5 = "google_app_measurement_local.db"
            java.io.File r4 = r4.getDatabasePath(r5)
            boolean r4 = r4.exists()
            if (r4 != 0) goto L_0x0024
            return r2
        L_0x0024:
            r4 = 5
            r5 = 0
            r6 = 0
            r7 = 5
        L_0x0028:
            if (r6 >= r4) goto L_0x020f
            r8 = 1
            android.database.sqlite.SQLiteDatabase r15 = r21.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x01de, SQLiteDatabaseLockedException -> 0x01c6, SQLiteException -> 0x01a0, all -> 0x019a }
            if (r15 != 0) goto L_0x004b
            r1.zzakg = r8     // Catch:{ SQLiteFullException -> 0x0046, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x003e, all -> 0x0039 }
            if (r15 == 0) goto L_0x0038
            r15.close()
        L_0x0038:
            return r3
        L_0x0039:
            r0 = move-exception
            r2 = r0
            r9 = r3
            goto L_0x0203
        L_0x003e:
            r0 = move-exception
            r9 = r3
        L_0x0040:
            r3 = r0
            goto L_0x01a4
        L_0x0043:
            r3 = r15
            goto L_0x0192
        L_0x0046:
            r0 = move-exception
            r9 = r3
        L_0x0048:
            r3 = r0
            goto L_0x01e2
        L_0x004b:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0195, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r10 = "messages"
            r9 = 3
            java.lang.String[] r11 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0195, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r9 = "rowid"
            r11[r5] = r9     // Catch:{ SQLiteFullException -> 0x0195, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r9 = "type"
            r11[r8] = r9     // Catch:{ SQLiteFullException -> 0x0195, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r9 = "entry"
            r14 = 2
            r11[r14] = r9     // Catch:{ SQLiteFullException -> 0x0195, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x018d, all -> 0x0189 }
            r12 = 0
            r13 = 0
            r16 = 0
            r17 = 0
            java.lang.String r18 = "rowid asc"
            r9 = 100
            java.lang.String r19 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x0195, SQLiteDatabaseLockedException -> 0x0043, SQLiteException -> 0x018d, all -> 0x0189 }
            r9 = r15
            r4 = 2
            r14 = r16
            r3 = r15
            r15 = r17
            r16 = r18
            r17 = r19
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteFullException -> 0x0186, SQLiteDatabaseLockedException -> 0x0192, SQLiteException -> 0x0183, all -> 0x0181 }
            r10 = -1
        L_0x007f:
            boolean r12 = r9.moveToNext()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            if (r12 == 0) goto L_0x013e
            long r10 = r9.getLong(r5)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            int r12 = r9.getInt(r8)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            byte[] r13 = r9.getBlob(r4)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            if (r12 != 0) goto L_0x00c7
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            int r14 = r13.length     // Catch:{ ParseException -> 0x00b2 }
            r12.unmarshall(r13, r5, r14)     // Catch:{ ParseException -> 0x00b2 }
            r12.setDataPosition(r5)     // Catch:{ ParseException -> 0x00b2 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzex> r13 = com.google.android.gms.internal.measurement.zzex.CREATOR     // Catch:{ ParseException -> 0x00b2 }
            java.lang.Object r13 = r13.createFromParcel(r12)     // Catch:{ ParseException -> 0x00b2 }
            com.google.android.gms.internal.measurement.zzex r13 = (com.google.android.gms.internal.measurement.zzex) r13     // Catch:{ ParseException -> 0x00b2 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            if (r13 == 0) goto L_0x007f
        L_0x00ab:
            r2.add(r13)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            goto L_0x007f
        L_0x00af:
            r0 = move-exception
            r4 = r0
            goto L_0x00c3
        L_0x00b2:
            com.google.android.gms.internal.measurement.zzfi r13 = r21.zzgi()     // Catch:{ all -> 0x00af }
            com.google.android.gms.internal.measurement.zzfk r13 = r13.zziv()     // Catch:{ all -> 0x00af }
            java.lang.String r14 = "Failed to load event from local database"
            r13.log(r14)     // Catch:{ all -> 0x00af }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            goto L_0x007f
        L_0x00c3:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            throw r4     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
        L_0x00c7:
            if (r12 != r8) goto L_0x00fb
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            int r14 = r13.length     // Catch:{ ParseException -> 0x00e3 }
            r12.unmarshall(r13, r5, r14)     // Catch:{ ParseException -> 0x00e3 }
            r12.setDataPosition(r5)     // Catch:{ ParseException -> 0x00e3 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzka> r13 = com.google.android.gms.internal.measurement.zzka.CREATOR     // Catch:{ ParseException -> 0x00e3 }
            java.lang.Object r13 = r13.createFromParcel(r12)     // Catch:{ ParseException -> 0x00e3 }
            com.google.android.gms.internal.measurement.zzka r13 = (com.google.android.gms.internal.measurement.zzka) r13     // Catch:{ ParseException -> 0x00e3 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            goto L_0x00f4
        L_0x00e0:
            r0 = move-exception
            r4 = r0
            goto L_0x00f7
        L_0x00e3:
            com.google.android.gms.internal.measurement.zzfi r13 = r21.zzgi()     // Catch:{ all -> 0x00e0 }
            com.google.android.gms.internal.measurement.zzfk r13 = r13.zziv()     // Catch:{ all -> 0x00e0 }
            java.lang.String r14 = "Failed to load user property from local database"
            r13.log(r14)     // Catch:{ all -> 0x00e0 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            r13 = 0
        L_0x00f4:
            if (r13 == 0) goto L_0x007f
            goto L_0x00ab
        L_0x00f7:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            throw r4     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
        L_0x00fb:
            if (r12 != r4) goto L_0x012f
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            int r14 = r13.length     // Catch:{ ParseException -> 0x0117 }
            r12.unmarshall(r13, r5, r14)     // Catch:{ ParseException -> 0x0117 }
            r12.setDataPosition(r5)     // Catch:{ ParseException -> 0x0117 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzef> r13 = com.google.android.gms.internal.measurement.zzef.CREATOR     // Catch:{ ParseException -> 0x0117 }
            java.lang.Object r13 = r13.createFromParcel(r12)     // Catch:{ ParseException -> 0x0117 }
            com.google.android.gms.internal.measurement.zzef r13 = (com.google.android.gms.internal.measurement.zzef) r13     // Catch:{ ParseException -> 0x0117 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            goto L_0x0128
        L_0x0114:
            r0 = move-exception
            r4 = r0
            goto L_0x012b
        L_0x0117:
            com.google.android.gms.internal.measurement.zzfi r13 = r21.zzgi()     // Catch:{ all -> 0x0114 }
            com.google.android.gms.internal.measurement.zzfk r13 = r13.zziv()     // Catch:{ all -> 0x0114 }
            java.lang.String r14 = "Failed to load user property from local database"
            r13.log(r14)     // Catch:{ all -> 0x0114 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            r13 = 0
        L_0x0128:
            if (r13 == 0) goto L_0x007f
            goto L_0x00ab
        L_0x012b:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            throw r4     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
        L_0x012f:
            com.google.android.gms.internal.measurement.zzfi r12 = r21.zzgi()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            com.google.android.gms.internal.measurement.zzfk r12 = r12.zziv()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            java.lang.String r13 = "Unknown record type in local database"
            r12.log(r13)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            goto L_0x007f
        L_0x013e:
            java.lang.String r4 = "messages"
            java.lang.String r12 = "rowid <= ?"
            java.lang.String[] r13 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            java.lang.String r10 = java.lang.Long.toString(r10)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            r13[r5] = r10     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            int r4 = r3.delete(r4, r12, r13)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            int r10 = r2.size()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            if (r4 >= r10) goto L_0x0161
            com.google.android.gms.internal.measurement.zzfi r4 = r21.zzgi()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            java.lang.String r10 = "Fewer entries removed from local database than expected"
            r4.log(r10)     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
        L_0x0161:
            r3.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            r3.endTransaction()     // Catch:{ SQLiteFullException -> 0x017d, SQLiteDatabaseLockedException -> 0x017a, SQLiteException -> 0x0176, all -> 0x0172 }
            if (r9 == 0) goto L_0x016c
            r9.close()
        L_0x016c:
            if (r3 == 0) goto L_0x0171
            r3.close()
        L_0x0171:
            return r2
        L_0x0172:
            r0 = move-exception
            r2 = r0
            goto L_0x0204
        L_0x0176:
            r0 = move-exception
            r15 = r3
            goto L_0x0040
        L_0x017a:
            r4 = r3
            r3 = r9
            goto L_0x01c8
        L_0x017d:
            r0 = move-exception
            r15 = r3
            goto L_0x0048
        L_0x0181:
            r0 = move-exception
            goto L_0x018b
        L_0x0183:
            r0 = move-exception
            r15 = r3
            goto L_0x018f
        L_0x0186:
            r0 = move-exception
            r15 = r3
            goto L_0x0197
        L_0x0189:
            r0 = move-exception
            r3 = r15
        L_0x018b:
            r2 = r0
            goto L_0x019d
        L_0x018d:
            r0 = move-exception
            r3 = r15
        L_0x018f:
            r9 = 0
            goto L_0x0040
        L_0x0192:
            r4 = r3
            r3 = 0
            goto L_0x01c8
        L_0x0195:
            r0 = move-exception
            r3 = r15
        L_0x0197:
            r9 = 0
            goto L_0x0048
        L_0x019a:
            r0 = move-exception
            r2 = r0
            r3 = 0
        L_0x019d:
            r9 = 0
            goto L_0x0204
        L_0x01a0:
            r0 = move-exception
            r3 = r0
            r9 = 0
            r15 = 0
        L_0x01a4:
            if (r15 == 0) goto L_0x01af
            boolean r4 = r15.inTransaction()     // Catch:{ all -> 0x0201 }
            if (r4 == 0) goto L_0x01af
            r15.endTransaction()     // Catch:{ all -> 0x0201 }
        L_0x01af:
            com.google.android.gms.internal.measurement.zzfi r4 = r21.zzgi()     // Catch:{ all -> 0x0201 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x0201 }
            java.lang.String r10 = "Error reading entries from local database"
            r4.zzg(r10, r3)     // Catch:{ all -> 0x0201 }
            r1.zzakg = r8     // Catch:{ all -> 0x0201 }
            if (r9 == 0) goto L_0x01c3
            r9.close()
        L_0x01c3:
            if (r15 == 0) goto L_0x01fb
            goto L_0x01f8
        L_0x01c6:
            r3 = 0
            r4 = 0
        L_0x01c8:
            long r8 = (long) r7
            android.os.SystemClock.sleep(r8)     // Catch:{ all -> 0x01d9 }
            int r7 = r7 + 20
            if (r3 == 0) goto L_0x01d3
            r3.close()
        L_0x01d3:
            if (r4 == 0) goto L_0x01fb
            r4.close()
            goto L_0x01fb
        L_0x01d9:
            r0 = move-exception
            r2 = r0
            r9 = r3
            r3 = r4
            goto L_0x0204
        L_0x01de:
            r0 = move-exception
            r3 = r0
            r9 = 0
            r15 = 0
        L_0x01e2:
            com.google.android.gms.internal.measurement.zzfi r4 = r21.zzgi()     // Catch:{ all -> 0x0201 }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()     // Catch:{ all -> 0x0201 }
            java.lang.String r10 = "Error reading entries from local database"
            r4.zzg(r10, r3)     // Catch:{ all -> 0x0201 }
            r1.zzakg = r8     // Catch:{ all -> 0x0201 }
            if (r9 == 0) goto L_0x01f6
            r9.close()
        L_0x01f6:
            if (r15 == 0) goto L_0x01fb
        L_0x01f8:
            r15.close()
        L_0x01fb:
            int r6 = r6 + 1
            r3 = 0
            r4 = 5
            goto L_0x0028
        L_0x0201:
            r0 = move-exception
            r2 = r0
        L_0x0203:
            r3 = r15
        L_0x0204:
            if (r9 == 0) goto L_0x0209
            r9.close()
        L_0x0209:
            if (r3 == 0) goto L_0x020e
            r3.close()
        L_0x020e:
            throw r2
        L_0x020f:
            com.google.android.gms.internal.measurement.zzfi r2 = r21.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziy()
            java.lang.String r3 = "Failed to read events from database in reasonable time"
            r2.log(r3)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfe.zzp(int):java.util.List");
    }
}
