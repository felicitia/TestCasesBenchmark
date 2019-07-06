package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.util.List;

final class zzbr implements zzei {
    private int tag;
    private final zzbo zzig;
    private int zzih;
    private int zzii = 0;

    public static zzbr zza(zzbo zzbo) {
        if (zzbo.zzia != null) {
            return zzbo.zzia;
        }
        return new zzbr(zzbo);
    }

    private zzbr(zzbo zzbo) {
        this.zzig = (zzbo) zzco.zza(zzbo, "input");
        this.zzig.zzia = this;
    }

    public final int zzcq() throws IOException {
        if (this.zzii != 0) {
            this.tag = this.zzii;
            this.zzii = 0;
        } else {
            this.tag = this.zzig.zzbs();
        }
        if (this.tag == 0 || this.tag == this.zzih) {
            return Integer.MAX_VALUE;
        }
        return this.tag >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzcr() throws IOException {
        if (this.zzig.zzci() || this.tag == this.zzih) {
            return false;
        }
        return this.zzig.zzm(this.tag);
    }

    private final void zzr(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzct.zzeb();
        }
    }

    public final double readDouble() throws IOException {
        zzr(1);
        return this.zzig.readDouble();
    }

    public final float readFloat() throws IOException {
        zzr(5);
        return this.zzig.readFloat();
    }

    public final long zzbt() throws IOException {
        zzr(0);
        return this.zzig.zzbt();
    }

    public final long zzbu() throws IOException {
        zzr(0);
        return this.zzig.zzbu();
    }

    public final int zzbv() throws IOException {
        zzr(0);
        return this.zzig.zzbv();
    }

    public final long zzbw() throws IOException {
        zzr(1);
        return this.zzig.zzbw();
    }

    public final int zzbx() throws IOException {
        zzr(5);
        return this.zzig.zzbx();
    }

    public final boolean zzby() throws IOException {
        zzr(0);
        return this.zzig.zzby();
    }

    public final String readString() throws IOException {
        zzr(2);
        return this.zzig.readString();
    }

    public final String zzbz() throws IOException {
        zzr(2);
        return this.zzig.zzbz();
    }

    public final <T> T zza(zzej<T> zzej, zzbz zzbz) throws IOException {
        zzr(2);
        return zzc(zzej, zzbz);
    }

    public final <T> T zzb(zzej<T> zzej, zzbz zzbz) throws IOException {
        zzr(3);
        return zzd(zzej, zzbz);
    }

    private final <T> T zzc(zzej<T> zzej, zzbz zzbz) throws IOException {
        int zzcb = this.zzig.zzcb();
        if (this.zzig.zzhx >= this.zzig.zzhy) {
            throw zzct.zzec();
        }
        int zzo = this.zzig.zzo(zzcb);
        T newInstance = zzej.newInstance();
        this.zzig.zzhx++;
        zzej.zza(newInstance, this, zzbz);
        zzej.zzc(newInstance);
        this.zzig.zzl(0);
        this.zzig.zzhx--;
        this.zzig.zzp(zzo);
        return newInstance;
    }

    private final <T> T zzd(zzej<T> zzej, zzbz zzbz) throws IOException {
        int i = this.zzih;
        this.zzih = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzej.newInstance();
            zzej.zza(newInstance, this, zzbz);
            zzej.zzc(newInstance);
            if (this.tag == this.zzih) {
                return newInstance;
            }
            throw zzct.zzed();
        } finally {
            this.zzih = i;
        }
    }

    public final zzbd zzca() throws IOException {
        zzr(2);
        return this.zzig.zzca();
    }

    public final int zzcb() throws IOException {
        zzr(0);
        return this.zzig.zzcb();
    }

    public final int zzcc() throws IOException {
        zzr(0);
        return this.zzig.zzcc();
    }

    public final int zzcd() throws IOException {
        zzr(5);
        return this.zzig.zzcd();
    }

    public final long zzce() throws IOException {
        zzr(1);
        return this.zzig.zzce();
    }

    public final int zzcf() throws IOException {
        zzr(0);
        return this.zzig.zzcf();
    }

    public final long zzcg() throws IOException {
        zzr(0);
        return this.zzig.zzcg();
    }

    public final void zzb(List<Double> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzbw) {
            zzbw zzbw = (zzbw) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzcb = this.zzig.zzcb();
                    zzs(zzcb);
                    int zzcj = this.zzig.zzcj() + zzcb;
                    do {
                        zzbw.zzc(this.zzig.readDouble());
                    } while (this.zzig.zzcj() < zzcj);
                    return;
                default:
                    throw zzct.zzeb();
            }
            do {
                zzbw.zzc(this.zzig.readDouble());
                if (!this.zzig.zzci()) {
                    zzbs2 = this.zzig.zzbs();
                } else {
                    return;
                }
            } while (zzbs2 == this.tag);
            this.zzii = zzbs2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzcb2 = this.zzig.zzcb();
                zzs(zzcb2);
                int zzcj2 = this.zzig.zzcj() + zzcb2;
                do {
                    list.add(Double.valueOf(this.zzig.readDouble()));
                } while (this.zzig.zzcj() < zzcj2);
                return;
            default:
                throw zzct.zzeb();
        }
        do {
            list.add(Double.valueOf(this.zzig.readDouble()));
            if (!this.zzig.zzci()) {
                zzbs = this.zzig.zzbs();
            } else {
                return;
            }
        } while (zzbs == this.tag);
        this.zzii = zzbs;
    }

    public final void zzc(List<Float> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcj) {
            zzcj zzcj = (zzcj) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzcb = this.zzig.zzcb();
                zzt(zzcb);
                int zzcj2 = this.zzig.zzcj() + zzcb;
                do {
                    zzcj.zzc(this.zzig.readFloat());
                } while (this.zzig.zzcj() < zzcj2);
            } else if (i != 5) {
                throw zzct.zzeb();
            } else {
                do {
                    zzcj.zzc(this.zzig.readFloat());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzcb2 = this.zzig.zzcb();
                zzt(zzcb2);
                int zzcj3 = this.zzig.zzcj() + zzcb2;
                do {
                    list.add(Float.valueOf(this.zzig.readFloat()));
                } while (this.zzig.zzcj() < zzcj3);
            } else if (i2 != 5) {
                throw zzct.zzeb();
            } else {
                do {
                    list.add(Float.valueOf(this.zzig.readFloat()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdh.zzv(this.zzig.zzbt());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzdh.zzv(this.zzig.zzbt());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzig.zzbt()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Long.valueOf(this.zzig.zzbt()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void zze(List<Long> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdh.zzv(this.zzig.zzbu());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzdh.zzv(this.zzig.zzbu());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzig.zzbu()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Long.valueOf(this.zzig.zzbu()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void zzf(List<Integer> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzcn.zzak(this.zzig.zzbv());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzcn.zzak(this.zzig.zzbv());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzig.zzbv()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Integer.valueOf(this.zzig.zzbv()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void zzg(List<Long> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzcb = this.zzig.zzcb();
                    zzs(zzcb);
                    int zzcj = this.zzig.zzcj() + zzcb;
                    do {
                        zzdh.zzv(this.zzig.zzbw());
                    } while (this.zzig.zzcj() < zzcj);
                    return;
                default:
                    throw zzct.zzeb();
            }
            do {
                zzdh.zzv(this.zzig.zzbw());
                if (!this.zzig.zzci()) {
                    zzbs2 = this.zzig.zzbs();
                } else {
                    return;
                }
            } while (zzbs2 == this.tag);
            this.zzii = zzbs2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzcb2 = this.zzig.zzcb();
                zzs(zzcb2);
                int zzcj2 = this.zzig.zzcj() + zzcb2;
                do {
                    list.add(Long.valueOf(this.zzig.zzbw()));
                } while (this.zzig.zzcj() < zzcj2);
                return;
            default:
                throw zzct.zzeb();
        }
        do {
            list.add(Long.valueOf(this.zzig.zzbw()));
            if (!this.zzig.zzci()) {
                zzbs = this.zzig.zzbs();
            } else {
                return;
            }
        } while (zzbs == this.tag);
        this.zzii = zzbs;
    }

    public final void zzh(List<Integer> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzcb = this.zzig.zzcb();
                zzt(zzcb);
                int zzcj = this.zzig.zzcj() + zzcb;
                do {
                    zzcn.zzak(this.zzig.zzbx());
                } while (this.zzig.zzcj() < zzcj);
            } else if (i != 5) {
                throw zzct.zzeb();
            } else {
                do {
                    zzcn.zzak(this.zzig.zzbx());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzcb2 = this.zzig.zzcb();
                zzt(zzcb2);
                int zzcj2 = this.zzig.zzcj() + zzcb2;
                do {
                    list.add(Integer.valueOf(this.zzig.zzbx()));
                } while (this.zzig.zzcj() < zzcj2);
            } else if (i2 != 5) {
                throw zzct.zzeb();
            } else {
                do {
                    list.add(Integer.valueOf(this.zzig.zzbx()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            }
        }
    }

    public final void zzi(List<Boolean> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzbb) {
            zzbb zzbb = (zzbb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzbb.addBoolean(this.zzig.zzby());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzbb.addBoolean(this.zzig.zzby());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzig.zzby()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Boolean.valueOf(this.zzig.zzby()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzj(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzbs;
        int zzbs2;
        if ((this.tag & 7) != 2) {
            throw zzct.zzeb();
        } else if (!(list instanceof zzdc) || z) {
            do {
                list.add(z ? zzbz() : readString());
                if (!this.zzig.zzci()) {
                    zzbs = this.zzig.zzbs();
                } else {
                    return;
                }
            } while (zzbs == this.tag);
            this.zzii = zzbs;
        } else {
            zzdc zzdc = (zzdc) list;
            do {
                zzdc.zzc(zzca());
                if (!this.zzig.zzci()) {
                    zzbs2 = this.zzig.zzbs();
                } else {
                    return;
                }
            } while (zzbs2 == this.tag);
            this.zzii = zzbs2;
        }
    }

    public final <T> void zza(List<T> list, zzej<T> zzej, zzbz zzbz) throws IOException {
        int zzbs;
        if ((this.tag & 7) != 2) {
            throw zzct.zzeb();
        }
        int i = this.tag;
        do {
            list.add(zzc(zzej, zzbz));
            if (!this.zzig.zzci() && this.zzii == 0) {
                zzbs = this.zzig.zzbs();
            } else {
                return;
            }
        } while (zzbs == i);
        this.zzii = zzbs;
    }

    public final <T> void zzb(List<T> list, zzej<T> zzej, zzbz zzbz) throws IOException {
        int zzbs;
        if ((this.tag & 7) != 3) {
            throw zzct.zzeb();
        }
        int i = this.tag;
        do {
            list.add(zzd(zzej, zzbz));
            if (!this.zzig.zzci() && this.zzii == 0) {
                zzbs = this.zzig.zzbs();
            } else {
                return;
            }
        } while (zzbs == i);
        this.zzii = zzbs;
    }

    public final void zzk(List<zzbd> list) throws IOException {
        int zzbs;
        if ((this.tag & 7) != 2) {
            throw zzct.zzeb();
        }
        do {
            list.add(zzca());
            if (!this.zzig.zzci()) {
                zzbs = this.zzig.zzbs();
            } else {
                return;
            }
        } while (zzbs == this.tag);
        this.zzii = zzbs;
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzcn.zzak(this.zzig.zzcb());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzcn.zzak(this.zzig.zzcb());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzig.zzcb()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Integer.valueOf(this.zzig.zzcb()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzcn.zzak(this.zzig.zzcc());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzcn.zzak(this.zzig.zzcc());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzig.zzcc()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Integer.valueOf(this.zzig.zzcc()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void zzn(List<Integer> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzcb = this.zzig.zzcb();
                zzt(zzcb);
                int zzcj = this.zzig.zzcj() + zzcb;
                do {
                    zzcn.zzak(this.zzig.zzcd());
                } while (this.zzig.zzcj() < zzcj);
            } else if (i != 5) {
                throw zzct.zzeb();
            } else {
                do {
                    zzcn.zzak(this.zzig.zzcd());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzcb2 = this.zzig.zzcb();
                zzt(zzcb2);
                int zzcj2 = this.zzig.zzcj() + zzcb2;
                do {
                    list.add(Integer.valueOf(this.zzig.zzcd()));
                } while (this.zzig.zzcj() < zzcj2);
            } else if (i2 != 5) {
                throw zzct.zzeb();
            } else {
                do {
                    list.add(Integer.valueOf(this.zzig.zzcd()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            }
        }
    }

    public final void zzo(List<Long> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzcb = this.zzig.zzcb();
                    zzs(zzcb);
                    int zzcj = this.zzig.zzcj() + zzcb;
                    do {
                        zzdh.zzv(this.zzig.zzce());
                    } while (this.zzig.zzcj() < zzcj);
                    return;
                default:
                    throw zzct.zzeb();
            }
            do {
                zzdh.zzv(this.zzig.zzce());
                if (!this.zzig.zzci()) {
                    zzbs2 = this.zzig.zzbs();
                } else {
                    return;
                }
            } while (zzbs2 == this.tag);
            this.zzii = zzbs2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzcb2 = this.zzig.zzcb();
                zzs(zzcb2);
                int zzcj2 = this.zzig.zzcj() + zzcb2;
                do {
                    list.add(Long.valueOf(this.zzig.zzce()));
                } while (this.zzig.zzcj() < zzcj2);
                return;
            default:
                throw zzct.zzeb();
        }
        do {
            list.add(Long.valueOf(this.zzig.zzce()));
            if (!this.zzig.zzci()) {
                zzbs = this.zzig.zzbs();
            } else {
                return;
            }
        } while (zzbs == this.tag);
        this.zzii = zzbs;
    }

    public final void zzp(List<Integer> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzcn) {
            zzcn zzcn = (zzcn) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzcn.zzak(this.zzig.zzcf());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzcn.zzak(this.zzig.zzcf());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzig.zzcf()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Integer.valueOf(this.zzig.zzcf()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    public final void zzq(List<Long> list) throws IOException {
        int zzbs;
        int zzbs2;
        if (list instanceof zzdh) {
            zzdh zzdh = (zzdh) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdh.zzv(this.zzig.zzcg());
                    if (!this.zzig.zzci()) {
                        zzbs2 = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs2 == this.tag);
                this.zzii = zzbs2;
            } else if (i != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    zzdh.zzv(this.zzig.zzcg());
                } while (this.zzig.zzcj() < zzcj);
                zzu(zzcj);
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzig.zzcg()));
                    if (!this.zzig.zzci()) {
                        zzbs = this.zzig.zzbs();
                    } else {
                        return;
                    }
                } while (zzbs == this.tag);
                this.zzii = zzbs;
            } else if (i2 != 2) {
                throw zzct.zzeb();
            } else {
                int zzcj2 = this.zzig.zzcj() + this.zzig.zzcb();
                do {
                    list.add(Long.valueOf(this.zzig.zzcg()));
                } while (this.zzig.zzcj() < zzcj2);
                zzu(zzcj2);
            }
        }
    }

    private static void zzs(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzct.zzed();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        if (zzcr() == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005b, code lost:
        throw new com.google.android.gms.internal.firebase-perf.zzct("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.firebase-perf.zzdm<K, V> r7, com.google.android.gms.internal.firebase-perf.zzbz r8) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 2
            r5.zzr(r0)
            com.google.android.gms.internal.firebase-perf.zzbo r0 = r5.zzig
            int r0 = r0.zzcb()
            com.google.android.gms.internal.firebase-perf.zzbo r1 = r5.zzig
            int r0 = r1.zzo(r0)
            K r1 = r7.zzoh
            V r2 = r7.zzoj
        L_0x0014:
            int r3 = r5.zzcq()     // Catch:{ all -> 0x0065 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x005c
            com.google.android.gms.internal.firebase-perf.zzbo r4 = r5.zzig     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.zzci()     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x005c
            switch(r3) {
                case 1: goto L_0x003b;
                case 2: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r5.zzcr()     // Catch:{ zzcu -> 0x004e }
            goto L_0x0044
        L_0x002d:
            com.google.android.gms.internal.firebase-perf.zzfq r3 = r7.zzoi     // Catch:{ zzcu -> 0x004e }
            V r4 = r7.zzoj     // Catch:{ zzcu -> 0x004e }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzcu -> 0x004e }
            java.lang.Object r3 = r5.zza(r3, r4, r8)     // Catch:{ zzcu -> 0x004e }
            r2 = r3
            goto L_0x0014
        L_0x003b:
            com.google.android.gms.internal.firebase-perf.zzfq r3 = r7.zzog     // Catch:{ zzcu -> 0x004e }
            r4 = 0
            java.lang.Object r3 = r5.zza(r3, r4, r4)     // Catch:{ zzcu -> 0x004e }
            r1 = r3
            goto L_0x0014
        L_0x0044:
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.firebase-perf.zzct r3 = new com.google.android.gms.internal.firebase-perf.zzct     // Catch:{ zzcu -> 0x004e }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzcu -> 0x004e }
            throw r3     // Catch:{ zzcu -> 0x004e }
        L_0x004e:
            boolean r3 = r5.zzcr()     // Catch:{ all -> 0x0065 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.firebase-perf.zzct r6 = new com.google.android.gms.internal.firebase-perf.zzct     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x0065 }
            throw r6     // Catch:{ all -> 0x0065 }
        L_0x005c:
            r6.put(r1, r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.firebase-perf.zzbo r6 = r5.zzig
            r6.zzp(r0)
            return
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.firebase-perf.zzbo r7 = r5.zzig
            r7.zzp(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzbr.zza(java.util.Map, com.google.android.gms.internal.firebase-perf.zzdm, com.google.android.gms.internal.firebase-perf.zzbz):void");
    }

    private final Object zza(zzfq zzfq, Class<?> cls, zzbz zzbz) throws IOException {
        switch (zzbs.zzij[zzfq.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzby());
            case 2:
                return zzca();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzcc());
            case 5:
                return Integer.valueOf(zzbx());
            case 6:
                return Long.valueOf(zzbw());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzbv());
            case 9:
                return Long.valueOf(zzbu());
            case 10:
                zzr(2);
                return zzc(zzef.zzfa().zzf(cls), zzbz);
            case 11:
                return Integer.valueOf(zzcd());
            case 12:
                return Long.valueOf(zzce());
            case 13:
                return Integer.valueOf(zzcf());
            case 14:
                return Long.valueOf(zzcg());
            case 15:
                return zzbz();
            case 16:
                return Integer.valueOf(zzcb());
            case 17:
                return Long.valueOf(zzbt());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzt(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzct.zzed();
        }
    }

    private final void zzu(int i) throws IOException {
        if (this.zzig.zzcj() != i) {
            throw zzct.zzdx();
        }
    }
}
