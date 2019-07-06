package com.google.firebase.perf.network;

import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zzc;
import java.io.IOException;
import java.io.InputStream;

public final class zza extends InputStream {
    private final zzaa zzdo;
    private final InputStream zzea;
    private final zzc zzeb;
    private long zzec = -1;
    private long zzed = -1;
    private long zzs;

    public zza(InputStream inputStream, zzc zzc, zzaa zzaa) {
        this.zzdo = zzaa;
        this.zzea = inputStream;
        this.zzeb = zzc;
        this.zzs = this.zzeb.zze();
    }

    public final int available() throws IOException {
        try {
            return this.zzea.available();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void close() throws IOException {
        long zzas = this.zzdo.zzas();
        if (this.zzed == -1) {
            this.zzed = zzas;
        }
        try {
            this.zzea.close();
            if (this.zzec != -1) {
                this.zzeb.zzb(this.zzec);
            }
            if (this.zzs != -1) {
                this.zzeb.zze(this.zzs);
            }
            this.zzeb.zzf(this.zzed);
            this.zzeb.zzf();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void mark(int i) {
        this.zzea.mark(i);
    }

    public final boolean markSupported() {
        return this.zzea.markSupported();
    }

    public final int read() throws IOException {
        try {
            int read = this.zzea.read();
            long zzas = this.zzdo.zzas();
            if (this.zzs == -1) {
                this.zzs = zzas;
            }
            if (read == -1 && this.zzed == -1) {
                this.zzed = zzas;
                this.zzeb.zzf(this.zzed);
                this.zzeb.zzf();
            } else {
                this.zzec++;
                this.zzeb.zzb(this.zzec);
            }
            return read;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int read = this.zzea.read(bArr, i, i2);
            long zzas = this.zzdo.zzas();
            if (this.zzs == -1) {
                this.zzs = zzas;
            }
            if (read == -1 && this.zzed == -1) {
                this.zzed = zzas;
                this.zzeb.zzf(this.zzed);
                this.zzeb.zzf();
            } else {
                this.zzec += (long) read;
                this.zzeb.zzb(this.zzec);
            }
            return read;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final int read(byte[] bArr) throws IOException {
        try {
            int read = this.zzea.read(bArr);
            long zzas = this.zzdo.zzas();
            if (this.zzs == -1) {
                this.zzs = zzas;
            }
            if (read == -1 && this.zzed == -1) {
                this.zzed = zzas;
                this.zzeb.zzf(this.zzed);
                this.zzeb.zzf();
            } else {
                this.zzec += (long) read;
                this.zzeb.zzb(this.zzec);
            }
            return read;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final void reset() throws IOException {
        try {
            this.zzea.reset();
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }

    public final long skip(long j) throws IOException {
        try {
            long skip = this.zzea.skip(j);
            long zzas = this.zzdo.zzas();
            if (this.zzs == -1) {
                this.zzs = zzas;
            }
            if (skip == -1 && this.zzed == -1) {
                this.zzed = zzas;
                this.zzeb.zzf(this.zzed);
            } else {
                this.zzec += skip;
                this.zzeb.zzb(this.zzec);
            }
            return skip;
        } catch (IOException e) {
            this.zzeb.zzf(this.zzdo.zzas());
            zzh.zzb(this.zzeb);
            throw e;
        }
    }
}
