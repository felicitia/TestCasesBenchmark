package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@bu
final class aoc {
    final zzjj a;
    final String b;
    final int c;

    aoc(anz anz) {
        this(anz.a(), anz.c(), anz.b());
    }

    @VisibleForTesting
    private aoc(zzjj zzjj, String str, int i) {
        this.a = zzjj;
        this.b = str;
        this.c = i;
    }

    static aoc a(String str) throws IOException {
        String[] split = str.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel obtain = Parcel.obtain();
        try {
            String str2 = new String(Base64.decode(split[0], 0), "UTF-8");
            int parseInt = Integer.parseInt(split[1]);
            byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            aoc aoc = new aoc((zzjj) zzjj.CREATOR.createFromParcel(obtain), str2, parseInt);
            obtain.recycle();
            return aoc;
        } catch (ParseException | IllegalArgumentException | IllegalStateException e) {
            ao.i().a(e, "QueueSeed.decode");
            throw new IOException("Malformed QueueSeed encoding.", e);
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public final String a() {
        Parcel obtain = Parcel.obtain();
        try {
            String encodeToString = Base64.encodeToString(this.b.getBytes("UTF-8"), 0);
            String num = Integer.toString(this.c);
            this.a.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            StringBuilder sb = new StringBuilder(2 + String.valueOf(encodeToString).length() + String.valueOf(num).length() + String.valueOf(encodeToString2).length());
            sb.append(encodeToString);
            sb.append("\u0000");
            sb.append(num);
            sb.append("\u0000");
            sb.append(encodeToString2);
            String sb2 = sb.toString();
            obtain.recycle();
            return sb2;
        } catch (UnsupportedEncodingException unused) {
            gv.c("QueueSeed encode failed because UTF-8 is not available.");
            obtain.recycle();
            return "";
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }
}
