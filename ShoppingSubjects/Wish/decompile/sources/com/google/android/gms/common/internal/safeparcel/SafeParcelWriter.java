package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class SafeParcelWriter {
    public static int beginObjectHeader(Parcel parcel) {
        return zza(parcel, 20293);
    }

    public static void finishObjectHeader(Parcel parcel, int i) {
        zzb(parcel, i);
    }

    public static void writeBoolean(Parcel parcel, int i, boolean z) {
        zzb(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void writeBundle(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeBundle(bundle);
        zzb(parcel, zza);
    }

    public static void writeByteArray(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeByteArray(bArr);
        zzb(parcel, zza);
    }

    public static void writeByteArrayArray(Parcel parcel, int i, byte[][] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeInt(r5);
        for (byte[] writeByteArray : bArr) {
            parcel.writeByteArray(writeByteArray);
        }
        zzb(parcel, zza);
    }

    public static void writeDouble(Parcel parcel, int i, double d) {
        zzb(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void writeDoubleObject(Parcel parcel, int i, Double d, boolean z) {
        if (d == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        zzb(parcel, i, 8);
        parcel.writeDouble(d.doubleValue());
    }

    public static void writeFloat(Parcel parcel, int i, float f) {
        zzb(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void writeFloatObject(Parcel parcel, int i, Float f, boolean z) {
        if (f == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        zzb(parcel, i, 4);
        parcel.writeFloat(f.floatValue());
    }

    public static void writeIBinder(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeStrongBinder(iBinder);
        zzb(parcel, zza);
    }

    public static void writeInt(Parcel parcel, int i, int i2) {
        zzb(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void writeIntArray(Parcel parcel, int i, int[] iArr, boolean z) {
        if (iArr == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeIntArray(iArr);
        zzb(parcel, zza);
    }

    public static void writeIntegerList(Parcel parcel, int i, List<Integer> list, boolean z) {
        if (list == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeInt(((Integer) list.get(i2)).intValue());
        }
        zzb(parcel, zza);
    }

    public static void writeIntegerObject(Parcel parcel, int i, Integer num, boolean z) {
        if (num == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        zzb(parcel, i, 4);
        parcel.writeInt(num.intValue());
    }

    public static void writeLong(Parcel parcel, int i, long j) {
        zzb(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void writeLongObject(Parcel parcel, int i, Long l, boolean z) {
        if (l == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        zzb(parcel, i, 8);
        parcel.writeLong(l.longValue());
    }

    public static void writeParcelable(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcelable.writeToParcel(parcel, i2);
        zzb(parcel, zza);
    }

    public static void writeString(Parcel parcel, int i, String str, boolean z) {
        if (str == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeString(str);
        zzb(parcel, zza);
    }

    public static void writeStringArray(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeStringArray(strArr);
        zzb(parcel, zza);
    }

    public static void writeStringList(Parcel parcel, int i, List<String> list, boolean z) {
        if (list == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeStringList(list);
        zzb(parcel, zza);
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        parcel.writeInt(r7);
        for (T t : tArr) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                zza(parcel, t, i2);
            }
        }
        zzb(parcel, zza);
    }

    public static <T extends Parcelable> void writeTypedList(Parcel parcel, int i, List<T> list, boolean z) {
        if (list == null) {
            if (z) {
                zzb(parcel, i, 0);
            }
            return;
        }
        int zza = zza(parcel, i);
        int size = list.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            Parcelable parcelable = (Parcelable) list.get(i2);
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                zza(parcel, parcelable, 0);
            }
        }
        zzb(parcel, zza);
    }

    private static int zza(Parcel parcel, int i) {
        parcel.writeInt(i | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static <T extends Parcelable> void zza(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    private static void zzb(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    private static void zzb(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(i | -65536);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt(i | (i2 << 16));
    }
}
