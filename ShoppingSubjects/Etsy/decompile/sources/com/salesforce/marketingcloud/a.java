package com.salesforce.marketingcloud;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public interface a {

    /* renamed from: com.salesforce.marketingcloud.a$a reason: collision with other inner class name */
    public static class C0159a implements a {
        public void a(int i, @NonNull String str, @NonNull String str2, @Nullable Throwable th) {
            switch (i) {
                case 2:
                    if (th == null) {
                        Log.v(str, str2);
                        return;
                    } else {
                        Log.v(str, str2, th);
                        return;
                    }
                case 3:
                    if (th == null) {
                        Log.d(str, str2);
                        return;
                    } else {
                        Log.d(str, str2, th);
                        return;
                    }
                case 4:
                    if (th == null) {
                        Log.i(str, str2);
                        return;
                    } else {
                        Log.i(str, str2, th);
                        return;
                    }
                case 5:
                    if (th == null) {
                        Log.w(str, str2);
                        return;
                    } else {
                        Log.w(str, str2, th);
                        return;
                    }
                case 6:
                    if (th == null) {
                        Log.e(str, str2);
                        return;
                    } else {
                        Log.e(str, str2, th);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    void a(int i, @NonNull String str, @NonNull String str2, @Nullable Throwable th);
}
