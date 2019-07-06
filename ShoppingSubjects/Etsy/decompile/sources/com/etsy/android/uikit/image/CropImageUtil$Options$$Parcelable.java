package com.etsy.android.uikit.image;

import android.os.Parcel;
import android.os.Parcelable;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import org.parceler.ParcelerRuntimeException;
import org.parceler.a;
import org.parceler.c;

public class CropImageUtil$Options$$Parcelable implements Parcelable, c<Options> {
    public static final CropImageUtil$Options$$Parcelable$Creator$$247 CREATOR = new CropImageUtil$Options$$Parcelable$Creator$$247();
    private Options options$$0;

    public int describeContents() {
        return 0;
    }

    public CropImageUtil$Options$$Parcelable(Options options) {
        this.options$$0 = options;
    }

    public void writeToParcel(Parcel parcel, int i) {
        write(this.options$$0, parcel, i, new a());
    }

    public static void write(Options options, Parcel parcel, int i, a aVar) {
        int b = aVar.b((Object) options);
        if (b != -1) {
            parcel.writeInt(b);
            return;
        }
        parcel.writeInt(aVar.a((Object) options));
        parcel.writeInt(options.mLayoutId);
        parcel.writeInt(options.mUseOvalViewport ? 1 : 0);
        parcel.writeInt(options.mMaxWidth);
        parcel.writeFloat(options.mAspectRatioX);
        parcel.writeFloat(options.mAspectRatioY);
        parcel.writeInt(options.mHelpTextId);
        parcel.writeInt(options.mMaxHeight);
        parcel.writeInt(options.mMinHeight);
        parcel.writeInt(options.mMinWidth);
    }

    public Options getParcel() {
        return this.options$$0;
    }

    public static Options read(Parcel parcel, a aVar) {
        int readInt = parcel.readInt();
        if (!aVar.a(readInt)) {
            int a = aVar.a();
            Options options = new Options();
            aVar.a(a, options);
            options.mLayoutId = parcel.readInt();
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            options.mUseOvalViewport = z;
            options.mMaxWidth = parcel.readInt();
            options.mAspectRatioX = parcel.readFloat();
            options.mAspectRatioY = parcel.readFloat();
            options.mHelpTextId = parcel.readInt();
            options.mMaxHeight = parcel.readInt();
            options.mMinHeight = parcel.readInt();
            options.mMinWidth = parcel.readInt();
            return options;
        } else if (!aVar.b(readInt)) {
            return (Options) aVar.c(readInt);
        } else {
            throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
        }
    }
}
