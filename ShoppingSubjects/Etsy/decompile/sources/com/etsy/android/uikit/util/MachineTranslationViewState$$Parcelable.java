package com.etsy.android.uikit.util;

import android.os.Parcel;
import android.os.Parcelable;
import org.parceler.ParcelerRuntimeException;
import org.parceler.a;
import org.parceler.c;

public class MachineTranslationViewState$$Parcelable implements Parcelable, c<MachineTranslationViewState> {
    public static final MachineTranslationViewState$$Parcelable$Creator$$246 CREATOR = new MachineTranslationViewState$$Parcelable$Creator$$246();
    private MachineTranslationViewState machineTranslationViewState$$0;

    public int describeContents() {
        return 0;
    }

    public MachineTranslationViewState$$Parcelable(MachineTranslationViewState machineTranslationViewState) {
        this.machineTranslationViewState$$0 = machineTranslationViewState;
    }

    public void writeToParcel(Parcel parcel, int i) {
        write(this.machineTranslationViewState$$0, parcel, i, new a());
    }

    public static void write(MachineTranslationViewState machineTranslationViewState, Parcel parcel, int i, a aVar) {
        int b = aVar.b((Object) machineTranslationViewState);
        if (b != -1) {
            parcel.writeInt(b);
            return;
        }
        parcel.writeInt(aVar.a((Object) machineTranslationViewState));
        parcel.writeInt(machineTranslationViewState.mShowingOriginal ? 1 : 0);
        parcel.writeInt(machineTranslationViewState.mState);
    }

    public MachineTranslationViewState getParcel() {
        return this.machineTranslationViewState$$0;
    }

    public static MachineTranslationViewState read(Parcel parcel, a aVar) {
        int readInt = parcel.readInt();
        if (!aVar.a(readInt)) {
            int a = aVar.a();
            MachineTranslationViewState machineTranslationViewState = new MachineTranslationViewState();
            aVar.a(a, machineTranslationViewState);
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            machineTranslationViewState.mShowingOriginal = z;
            machineTranslationViewState.mState = parcel.readInt();
            return machineTranslationViewState;
        } else if (!aVar.b(readInt)) {
            return (MachineTranslationViewState) aVar.c(readInt);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
