package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@bu
@Class(creator = "LargeParcelTeleporterCreator")
@Reserved({1})
public final class zzaev extends AbstractSafeParcelable {
    public static final Creator<zzaev> CREATOR = new zzaex();
    @Field(id = 2)
    private ParcelFileDescriptor zzcft;
    private Parcelable zzcfu;
    private boolean zzcfv;

    @Constructor
    public zzaev(@Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzcft = parcelFileDescriptor;
        this.zzcfu = null;
        this.zzcfv = true;
    }

    public zzaev(SafeParcelable safeParcelable) {
        this.zzcft = null;
        this.zzcfu = safeParcelable;
        this.zzcfv = false;
    }

    private final <T> ParcelFileDescriptor zze(byte[] bArr) {
        AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new cs(this, autoCloseOutputStream, bArr)).start();
                return createPipe[0];
            } catch (IOException e) {
                e = e;
                gv.b("Error transporting the ad response", e);
                ao.i().a((Throwable) e, "LargeParcelTeleporter.pipeData.2");
                IOUtils.closeQuietly((Closeable) autoCloseOutputStream);
                return null;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = null;
            gv.b("Error transporting the ad response", e);
            ao.i().a((Throwable) e, "LargeParcelTeleporter.pipeData.2");
            IOUtils.closeQuietly((Closeable) autoCloseOutputStream);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    private final ParcelFileDescriptor zzoc() {
        if (this.zzcft == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzcfu.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzcft = zze(marshall);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
        return this.zzcft;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzoc();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcft, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: finally extract failed */
    public final <T extends SafeParcelable> T zza(Creator<T> creator) {
        if (this.zzcfv) {
            if (this.zzcft == null) {
                gv.c("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzcft));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                IOUtils.closeQuietly((Closeable) dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzcfu = (SafeParcelable) creator.createFromParcel(obtain);
                    obtain.recycle();
                    this.zzcfv = false;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (IOException e) {
                gv.b("Could not read from parcel file descriptor", e);
                IOUtils.closeQuietly((Closeable) dataInputStream);
                return null;
            } catch (Throwable th2) {
                IOUtils.closeQuietly((Closeable) dataInputStream);
                throw th2;
            }
        }
        return (SafeParcelable) this.zzcfu;
    }
}
