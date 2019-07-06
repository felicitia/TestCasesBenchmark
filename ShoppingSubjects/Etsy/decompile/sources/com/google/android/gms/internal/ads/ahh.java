package com.google.android.gms.internal.ads;

import android.os.Environment;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@bu
public final class ahh {
    private final ahl a;
    private final ahx b;
    private final boolean c;

    private ahh() {
        this.c = false;
        this.a = new ahl();
        this.b = new ahx();
        b();
    }

    public ahh(ahl ahl) {
        this.a = ahl;
        this.c = ((Boolean) ajh.f().a(akl.db)).booleanValue();
        this.b = new ahx();
        b();
    }

    public static ahh a() {
        return new ahh();
    }

    private final synchronized void b() {
        this.b.d = new ahq();
        this.b.d.b = new aht();
        this.b.c = new ahv();
    }

    private final synchronized void b(zzb zzb) {
        this.b.b = c();
        this.a.a(aar.a((aar) this.b)).b(zzb.zzhq()).a();
        String str = "Logging Event with event code : ";
        String valueOf = String.valueOf(Integer.toString(zzb.zzhq(), 10));
        gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    private final synchronized void c(zzb zzb) {
        FileOutputStream fileOutputStream;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            try {
                fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
                try {
                    fileOutputStream.write(d(zzb).getBytes());
                    fileOutputStream.write(10);
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        gv.a("Could not close Clearcut output stream.");
                    }
                } catch (IOException unused2) {
                    gv.a("Could not write Clearcut to file.");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                        gv.a("Could not close Clearcut output stream.");
                    }
                }
            } catch (FileNotFoundException unused4) {
                gv.a("Could not find file for Clearcut");
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused5) {
                    gv.a("Could not close Clearcut output stream.");
                }
                throw th;
            }
        }
    }

    private static long[] c() {
        int i;
        List b2 = akl.b();
        ArrayList arrayList = new ArrayList();
        Iterator it = b2.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String[] split = ((String) it.next()).split(",");
            int length = split.length;
            while (i < length) {
                try {
                    arrayList.add(Long.valueOf(split[i]));
                } catch (NumberFormatException unused) {
                    gv.a("Experiment ID is not a number");
                }
                i++;
            }
        }
        long[] jArr = new long[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            jArr[i2] = ((Long) obj).longValue();
            i2++;
        }
        return jArr;
    }

    private final synchronized String d(zzb zzb) {
        return String.format("id=%s,timestamp=%s,event=%s", new Object[]{this.b.a, Long.valueOf(ao.l().elapsedRealtime()), Integer.valueOf(zzb.zzhq())});
    }

    public final synchronized void a(ahi ahi) {
        if (this.c) {
            try {
                ahi.a(this.b);
            } catch (NullPointerException e) {
                ao.i().a((Throwable) e, "AdMobClearcutLogger.modify");
            }
        }
    }

    public final synchronized void a(zzb zzb) {
        if (this.c) {
            if (((Boolean) ajh.f().a(akl.dc)).booleanValue()) {
                c(zzb);
            } else {
                b(zzb);
            }
        }
    }
}
