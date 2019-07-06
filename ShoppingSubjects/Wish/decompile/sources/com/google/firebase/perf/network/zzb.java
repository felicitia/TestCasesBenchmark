package com.google.firebase.perf.network;

import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import java.io.IOException;
import java.io.OutputStream;

public final class zzb extends OutputStream {
    private final zzaa zzdo;
    private zzc zzeb;
    private OutputStream zzee;
    private long zzef = -1;

    public zzb(OutputStream outputStream, zzc zzc, zzaa zzaa) {
        this.zzee = outputStream;
        this.zzeb = zzc;
        this.zzdo = zzaa;
    }

    public final void close() throws IOException {
        if (this.zzef != -1) {
            this.zzeb.zza(this.zzef);
        }
        this.zzeb.zzd(this.zzdo.zzas());
        try {
            this.zzee.close();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void flush() throws IOException {
        try {
            this.zzee.flush();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void write(int i) throws IOException {
        try {
            this.zzee.write(i);
            this.zzef++;
            this.zzeb.zza(this.zzef);
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void write(byte[] bArr) throws IOException {
        try {
            this.zzee.write(bArr);
            this.zzef += (long) bArr.length;
            this.zzeb.zza(this.zzef);
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.zzee.write(bArr, i, i2);
            this.zzef += (long) i2;
            this.zzeb.zza(this.zzef);
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }
}
