package com.google.android.gms.internal.measurement;

final class zzfj implements Runnable {
    private final /* synthetic */ int zzakw;
    private final /* synthetic */ String zzakx;
    private final /* synthetic */ Object zzaky;
    private final /* synthetic */ Object zzakz;
    private final /* synthetic */ Object zzala;
    private final /* synthetic */ zzfi zzalb;

    zzfj(zzfi zzfi, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzalb = zzfi;
        this.zzakw = i;
        this.zzakx = str;
        this.zzaky = obj;
        this.zzakz = obj2;
        this.zzala = obj3;
    }

    public final void run() {
        zzfi zzfi;
        char c;
        zzft zzgj = this.zzalb.zzacv.zzgj();
        if (!zzgj.isInitialized()) {
            this.zzalb.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzalb.zzakl == 0) {
            if (this.zzalb.zzgk().zzds()) {
                zzfi = this.zzalb;
                this.zzalb.zzgl();
                c = 'C';
            } else {
                zzfi = this.zzalb;
                this.zzalb.zzgl();
                c = 'c';
            }
            zzfi.zzakl = c;
        }
        if (this.zzalb.zzafi < 0) {
            this.zzalb.zzafi = this.zzalb.zzgk().zzgw();
        }
        char charAt = "01VDIWEA?".charAt(this.zzakw);
        char zza = this.zzalb.zzakl;
        long zzb = this.zzalb.zzafi;
        String zza2 = zzfi.zza(true, this.zzakx, this.zzaky, this.zzakz, this.zzala);
        StringBuilder sb = new StringBuilder(String.valueOf(zza2).length() + 24);
        sb.append("2");
        sb.append(charAt);
        sb.append(zza);
        sb.append(zzb);
        sb.append(":");
        sb.append(zza2);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = this.zzakx.substring(0, 1024);
        }
        zzgj.zzals.zzc(sb2, 1);
    }
}
