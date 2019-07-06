package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzap extends zzga<zzap> {
    public String url;
    public Integer zzge;
    public Long zzgf;
    public Long zzgg;
    public Integer zzgh;
    public Integer zzgi;
    public String zzgj;
    public Long zzgk;
    public Long zzgl;
    public Long zzgm;
    public Long zzgn;
    public zzaq[] zzgo;
    public zzas[] zzgp;

    public zzap() {
        this.url = null;
        this.zzge = null;
        this.zzgf = null;
        this.zzgg = null;
        this.zzgh = null;
        this.zzgi = null;
        this.zzgj = null;
        this.zzgk = null;
        this.zzgl = null;
        this.zzgm = null;
        this.zzgn = null;
        this.zzgo = zzaq.zzaz();
        this.zzgp = zzas.zzba();
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzap)) {
            return false;
        }
        zzap zzap = (zzap) obj;
        if (this.url == null) {
            if (zzap.url != null) {
                return false;
            }
        } else if (!this.url.equals(zzap.url)) {
            return false;
        }
        if (this.zzge == null) {
            if (zzap.zzge != null) {
                return false;
            }
        } else if (!this.zzge.equals(zzap.zzge)) {
            return false;
        }
        if (this.zzgf == null) {
            if (zzap.zzgf != null) {
                return false;
            }
        } else if (!this.zzgf.equals(zzap.zzgf)) {
            return false;
        }
        if (this.zzgg == null) {
            if (zzap.zzgg != null) {
                return false;
            }
        } else if (!this.zzgg.equals(zzap.zzgg)) {
            return false;
        }
        if (this.zzgh == null) {
            if (zzap.zzgh != null) {
                return false;
            }
        } else if (!this.zzgh.equals(zzap.zzgh)) {
            return false;
        }
        if (this.zzgi == null) {
            if (zzap.zzgi != null) {
                return false;
            }
        } else if (!this.zzgi.equals(zzap.zzgi)) {
            return false;
        }
        if (this.zzgj == null) {
            if (zzap.zzgj != null) {
                return false;
            }
        } else if (!this.zzgj.equals(zzap.zzgj)) {
            return false;
        }
        if (this.zzgk == null) {
            if (zzap.zzgk != null) {
                return false;
            }
        } else if (!this.zzgk.equals(zzap.zzgk)) {
            return false;
        }
        if (this.zzgl == null) {
            if (zzap.zzgl != null) {
                return false;
            }
        } else if (!this.zzgl.equals(zzap.zzgl)) {
            return false;
        }
        if (this.zzgm == null) {
            if (zzap.zzgm != null) {
                return false;
            }
        } else if (!this.zzgm.equals(zzap.zzgm)) {
            return false;
        }
        if (this.zzgn == null) {
            if (zzap.zzgn != null) {
                return false;
            }
        } else if (!this.zzgn.equals(zzap.zzgn)) {
            return false;
        }
        if (!zzge.equals((Object[]) this.zzgo, (Object[]) zzap.zzgo) || !zzge.equals((Object[]) this.zzgp, (Object[]) zzap.zzgp)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzap.zzss == null || zzap.zzss.isEmpty();
        }
        return this.zzss.equals(zzap.zzss);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.zzge == null ? 0 : this.zzge.intValue())) * 31) + (this.zzgf == null ? 0 : this.zzgf.hashCode())) * 31) + (this.zzgg == null ? 0 : this.zzgg.hashCode())) * 31) + (this.zzgh == null ? 0 : this.zzgh.intValue())) * 31) + (this.zzgi == null ? 0 : this.zzgi.hashCode())) * 31) + (this.zzgj == null ? 0 : this.zzgj.hashCode())) * 31) + (this.zzgk == null ? 0 : this.zzgk.hashCode())) * 31) + (this.zzgl == null ? 0 : this.zzgl.hashCode())) * 31) + (this.zzgm == null ? 0 : this.zzgm.hashCode())) * 31) + (this.zzgn == null ? 0 : this.zzgn.hashCode())) * 31) + zzge.hashCode((Object[]) this.zzgo)) * 31) + zzge.hashCode((Object[]) this.zzgp)) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i = this.zzss.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.url != null) {
            zzfy.zza(1, this.url);
        }
        if (this.zzge != null) {
            zzfy.zzc(2, this.zzge.intValue());
        }
        if (this.zzgf != null) {
            zzfy.zzi(3, this.zzgf.longValue());
        }
        if (this.zzgg != null) {
            zzfy.zzi(4, this.zzgg.longValue());
        }
        if (this.zzgi != null) {
            zzfy.zzc(5, this.zzgi.intValue());
        }
        if (this.zzgj != null) {
            zzfy.zza(6, this.zzgj);
        }
        if (this.zzgk != null) {
            zzfy.zzi(7, this.zzgk.longValue());
        }
        if (this.zzgl != null) {
            zzfy.zzi(8, this.zzgl.longValue());
        }
        if (this.zzgm != null) {
            zzfy.zzi(9, this.zzgm.longValue());
        }
        if (this.zzgn != null) {
            zzfy.zzi(10, this.zzgn.longValue());
        }
        if (this.zzgh != null) {
            zzfy.zzc(11, this.zzgh.intValue());
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            for (zzaq zzaq : this.zzgo) {
                if (zzaq != null) {
                    zzfy.zza(12, (zzgg) zzaq);
                }
            }
        }
        if (this.zzgp != null && this.zzgp.length > 0) {
            for (zzas zzas : this.zzgp) {
                if (zzas != null) {
                    zzfy.zza(13, (zzgg) zzas);
                }
            }
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.url != null) {
            zzax += zzfy.zzb(1, this.url);
        }
        if (this.zzge != null) {
            zzax += zzfy.zzg(2, this.zzge.intValue());
        }
        if (this.zzgf != null) {
            zzax += zzfy.zzd(3, this.zzgf.longValue());
        }
        if (this.zzgg != null) {
            zzax += zzfy.zzd(4, this.zzgg.longValue());
        }
        if (this.zzgi != null) {
            zzax += zzfy.zzg(5, this.zzgi.intValue());
        }
        if (this.zzgj != null) {
            zzax += zzfy.zzb(6, this.zzgj);
        }
        if (this.zzgk != null) {
            zzax += zzfy.zzd(7, this.zzgk.longValue());
        }
        if (this.zzgl != null) {
            zzax += zzfy.zzd(8, this.zzgl.longValue());
        }
        if (this.zzgm != null) {
            zzax += zzfy.zzd(9, this.zzgm.longValue());
        }
        if (this.zzgn != null) {
            zzax += zzfy.zzd(10, this.zzgn.longValue());
        }
        if (this.zzgh != null) {
            zzax += zzfy.zzg(11, this.zzgh.intValue());
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            int i = zzax;
            for (zzaq zzaq : this.zzgo) {
                if (zzaq != null) {
                    i += zzfy.zzb(12, (zzgg) zzaq);
                }
            }
            zzax = i;
        }
        if (this.zzgp != null && this.zzgp.length > 0) {
            for (zzas zzas : this.zzgp) {
                if (zzas != null) {
                    zzax += zzfy.zzb(13, (zzgg) zzas);
                }
            }
        }
        return zzax;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b9, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: zzc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.firebase-perf.zzap zza(com.google.android.gms.internal.firebase-perf.zzfx r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zzbs()
            r1 = 1
            r2 = 0
            switch(r0) {
                case 0: goto L_0x015d;
                case 10: goto L_0x0155;
                case 16: goto L_0x011e;
                case 24: goto L_0x0112;
                case 32: goto L_0x0106;
                case 40: goto L_0x00fa;
                case 50: goto L_0x00f2;
                case 56: goto L_0x00e6;
                case 64: goto L_0x00da;
                case 72: goto L_0x00ce;
                case 80: goto L_0x00c2;
                case 88: goto L_0x008d;
                case 98: goto L_0x004e;
                case 106: goto L_0x0010;
                default: goto L_0x0009;
            }
        L_0x0009:
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x0010:
            r0 = 106(0x6a, float:1.49E-43)
            int r0 = com.google.android.gms.internal.firebase-perf.zzgj.zzb(r7, r0)
            com.google.android.gms.internal.firebase-perf.zzas[] r3 = r6.zzgp
            if (r3 != 0) goto L_0x001c
            r3 = 0
            goto L_0x001f
        L_0x001c:
            com.google.android.gms.internal.firebase-perf.zzas[] r3 = r6.zzgp
            int r3 = r3.length
        L_0x001f:
            int r0 = r0 + r3
            com.google.android.gms.internal.firebase-perf.zzas[] r0 = new com.google.android.gms.internal.firebase-perf.zzas[r0]
            if (r3 == 0) goto L_0x0029
            com.google.android.gms.internal.firebase-perf.zzas[] r4 = r6.zzgp
            java.lang.System.arraycopy(r4, r2, r0, r2, r3)
        L_0x0029:
            int r2 = r0.length
            int r2 = r2 - r1
            if (r3 >= r2) goto L_0x003f
            com.google.android.gms.internal.firebase-perf.zzas r2 = new com.google.android.gms.internal.firebase-perf.zzas
            r2.<init>()
            r0[r3] = r2
            r2 = r0[r3]
            r7.zza(r2)
            r7.zzbs()
            int r3 = r3 + 1
            goto L_0x0029
        L_0x003f:
            com.google.android.gms.internal.firebase-perf.zzas r1 = new com.google.android.gms.internal.firebase-perf.zzas
            r1.<init>()
            r0[r3] = r1
            r1 = r0[r3]
            r7.zza(r1)
            r6.zzgp = r0
            goto L_0x0000
        L_0x004e:
            r0 = 98
            int r0 = com.google.android.gms.internal.firebase-perf.zzgj.zzb(r7, r0)
            com.google.android.gms.internal.firebase-perf.zzaq[] r3 = r6.zzgo
            if (r3 != 0) goto L_0x005a
            r3 = 0
            goto L_0x005d
        L_0x005a:
            com.google.android.gms.internal.firebase-perf.zzaq[] r3 = r6.zzgo
            int r3 = r3.length
        L_0x005d:
            int r0 = r0 + r3
            com.google.android.gms.internal.firebase-perf.zzaq[] r0 = new com.google.android.gms.internal.firebase-perf.zzaq[r0]
            if (r3 == 0) goto L_0x0067
            com.google.android.gms.internal.firebase-perf.zzaq[] r4 = r6.zzgo
            java.lang.System.arraycopy(r4, r2, r0, r2, r3)
        L_0x0067:
            int r2 = r0.length
            int r2 = r2 - r1
            if (r3 >= r2) goto L_0x007d
            com.google.android.gms.internal.firebase-perf.zzaq r2 = new com.google.android.gms.internal.firebase-perf.zzaq
            r2.<init>()
            r0[r3] = r2
            r2 = r0[r3]
            r7.zza(r2)
            r7.zzbs()
            int r3 = r3 + 1
            goto L_0x0067
        L_0x007d:
            com.google.android.gms.internal.firebase-perf.zzaq r1 = new com.google.android.gms.internal.firebase-perf.zzaq
            r1.<init>()
            r0[r3] = r1
            r1 = r0[r3]
            r7.zza(r1)
            r6.zzgo = r0
            goto L_0x0000
        L_0x008d:
            int r2 = r7.getPosition()
            int r3 = r7.zzck()     // Catch:{ IllegalArgumentException -> 0x00ba }
            if (r3 < 0) goto L_0x00a1
            if (r3 > r1) goto L_0x00a1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)     // Catch:{ IllegalArgumentException -> 0x00ba }
            r6.zzgh = r1     // Catch:{ IllegalArgumentException -> 0x00ba }
            goto L_0x0000
        L_0x00a1:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x00ba }
            r4 = 56
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x00ba }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x00ba }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x00ba }
            java.lang.String r3 = " is not a valid enum NetworkClientErrorReason"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x00ba }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x00ba }
            r1.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x00ba }
            throw r1     // Catch:{ IllegalArgumentException -> 0x00ba }
        L_0x00ba:
            r7.zzax(r2)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x00c2:
            long r0 = r7.zzcl()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.zzgn = r0
            goto L_0x0000
        L_0x00ce:
            long r0 = r7.zzcl()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.zzgm = r0
            goto L_0x0000
        L_0x00da:
            long r0 = r7.zzcl()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.zzgl = r0
            goto L_0x0000
        L_0x00e6:
            long r0 = r7.zzcl()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.zzgk = r0
            goto L_0x0000
        L_0x00f2:
            java.lang.String r0 = r7.readString()
            r6.zzgj = r0
            goto L_0x0000
        L_0x00fa:
            int r0 = r7.zzck()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.zzgi = r0
            goto L_0x0000
        L_0x0106:
            long r0 = r7.zzcl()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.zzgg = r0
            goto L_0x0000
        L_0x0112:
            long r0 = r7.zzcl()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.zzgf = r0
            goto L_0x0000
        L_0x011e:
            int r1 = r7.getPosition()
            int r2 = r7.zzck()     // Catch:{ IllegalArgumentException -> 0x014d }
            if (r2 < 0) goto L_0x0134
            r3 = 9
            if (r2 > r3) goto L_0x0134
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x014d }
            r6.zzge = r2     // Catch:{ IllegalArgumentException -> 0x014d }
            goto L_0x0000
        L_0x0134:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x014d }
            r4 = 42
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x014d }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x014d }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x014d }
            java.lang.String r2 = " is not a valid enum HttpMethod"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x014d }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x014d }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x014d }
            throw r3     // Catch:{ IllegalArgumentException -> 0x014d }
        L_0x014d:
            r7.zzax(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x0155:
            java.lang.String r0 = r7.readString()
            r6.url = r0
            goto L_0x0000
        L_0x015d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzap.zza(com.google.android.gms.internal.firebase-perf.zzfx):com.google.android.gms.internal.firebase-perf.zzap");
    }
}
