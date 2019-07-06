package defpackage;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;

/* renamed from: y reason: default package */
/* compiled from: GA */
public final class y extends q {
    private final Context b;
    private final int c;

    public y(Context context, int i) {
        this.b = context;
        this.c = i;
    }

    public final void c() {
        long j;
        long j2;
        long j3;
        ActivityManager activityManager = this.b != null ? (ActivityManager) this.b.getSystemService("activity") : null;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            j = statFs.getBlockSizeLong();
        } else {
            j = (long) statFs.getBlockSize();
        }
        switch (this.c) {
            case 0:
                super.a(Build.HARDWARE);
                return;
            case 1:
                if (activityManager != null) {
                    MemoryInfo memoryInfo = new MemoryInfo();
                    activityManager.getMemoryInfo(memoryInfo);
                    if (VERSION.SDK_INT >= 16) {
                        super.a(String.valueOf(memoryInfo.totalMem));
                    }
                    return;
                }
                break;
            case 2:
                super.a(Build.BOARD);
                return;
            case 3:
                if (VERSION.SDK_INT >= 18) {
                    j2 = statFs.getBlockCountLong();
                } else {
                    j2 = (long) statFs.getBlockCount();
                }
                super.a(String.valueOf(j2 * j));
                return;
            case 4:
                if (activityManager != null) {
                    MemoryInfo memoryInfo2 = new MemoryInfo();
                    activityManager.getMemoryInfo(memoryInfo2);
                    super.a(String.valueOf(memoryInfo2.availMem));
                    return;
                }
                break;
            case 5:
                if (VERSION.SDK_INT >= 18) {
                    j3 = statFs.getAvailableBlocksLong();
                } else {
                    j3 = (long) statFs.getAvailableBlocks();
                }
                super.a(String.valueOf(j3 * j));
                break;
        }
    }
}
