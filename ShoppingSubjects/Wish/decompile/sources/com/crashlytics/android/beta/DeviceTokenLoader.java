package com.crashlytics.android.beta;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import io.fabric.sdk.android.services.cache.ValueLoader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DeviceTokenLoader implements ValueLoader<String> {
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0047 A[SYNTHETIC, Splitter:B:24:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0059 A[SYNTHETIC, Splitter:B:30:0x0059] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006b A[SYNTHETIC, Splitter:B:36:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00aa A[SYNTHETIC, Splitter:B:43:0x00aa] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x003a=Splitter:B:21:0x003a, B:27:0x004c=Splitter:B:27:0x004c, B:33:0x005e=Splitter:B:33:0x005e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String load(android.content.Context r9) throws java.lang.Exception {
        /*
            r8 = this;
            long r0 = java.lang.System.nanoTime()
            java.lang.String r2 = ""
            r3 = 0
            java.util.zip.ZipInputStream r9 = r8.getZipInputStreamOfAppApkFrom(r9)     // Catch:{ NameNotFoundException -> 0x005d, FileNotFoundException -> 0x004b, IOException -> 0x0039 }
            java.lang.String r3 = r8.determineDeviceToken(r9)     // Catch:{ NameNotFoundException -> 0x0032, FileNotFoundException -> 0x002d, IOException -> 0x0028, all -> 0x0024 }
            if (r9 == 0) goto L_0x0021
            r9.close()     // Catch:{ IOException -> 0x0015 }
            goto L_0x0021
        L_0x0015:
            r9 = move-exception
            io.fabric.sdk.android.Logger r2 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r4 = "Beta"
            java.lang.String r5 = "Failed to close the APK file"
            r2.e(r4, r5, r9)
        L_0x0021:
            r2 = r3
            goto L_0x007b
        L_0x0024:
            r0 = move-exception
            r3 = r9
            goto L_0x00a8
        L_0x0028:
            r3 = move-exception
            r7 = r3
            r3 = r9
            r9 = r7
            goto L_0x003a
        L_0x002d:
            r3 = move-exception
            r7 = r3
            r3 = r9
            r9 = r7
            goto L_0x004c
        L_0x0032:
            r3 = move-exception
            r7 = r3
            r3 = r9
            r9 = r7
            goto L_0x005e
        L_0x0037:
            r0 = move-exception
            goto L_0x00a8
        L_0x0039:
            r9 = move-exception
        L_0x003a:
            io.fabric.sdk.android.Logger r4 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0037 }
            java.lang.String r5 = "Beta"
            java.lang.String r6 = "Failed to read the APK file"
            r4.e(r5, r6, r9)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x007b
            r3.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x007b
        L_0x004b:
            r9 = move-exception
        L_0x004c:
            io.fabric.sdk.android.Logger r4 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0037 }
            java.lang.String r5 = "Beta"
            java.lang.String r6 = "Failed to find the APK file"
            r4.e(r5, r6, r9)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x007b
            r3.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x007b
        L_0x005d:
            r9 = move-exception
        L_0x005e:
            io.fabric.sdk.android.Logger r4 = io.fabric.sdk.android.Fabric.getLogger()     // Catch:{ all -> 0x0037 }
            java.lang.String r5 = "Beta"
            java.lang.String r6 = "Failed to find this app in the PackageManager"
            r4.e(r5, r6, r9)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x007b
            r3.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x007b
        L_0x006f:
            r9 = move-exception
            io.fabric.sdk.android.Logger r3 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r4 = "Beta"
            java.lang.String r5 = "Failed to close the APK file"
            r3.e(r4, r5, r9)
        L_0x007b:
            long r3 = java.lang.System.nanoTime()
            long r5 = r3 - r0
            double r0 = (double) r5
            r3 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r0 = r0 / r3
            io.fabric.sdk.android.Logger r9 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r3 = "Beta"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Beta device token load took "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = "ms"
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r9.d(r3, r0)
            return r2
        L_0x00a8:
            if (r3 == 0) goto L_0x00ba
            r3.close()     // Catch:{ IOException -> 0x00ae }
            goto L_0x00ba
        L_0x00ae:
            r9 = move-exception
            io.fabric.sdk.android.Logger r1 = io.fabric.sdk.android.Fabric.getLogger()
            java.lang.String r2 = "Beta"
            java.lang.String r3 = "Failed to close the APK file"
            r1.e(r2, r3, r9)
        L_0x00ba:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.beta.DeviceTokenLoader.load(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: 0000 */
    public ZipInputStream getZipInputStreamOfAppApkFrom(Context context) throws NameNotFoundException, FileNotFoundException {
        return new ZipInputStream(new FileInputStream(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir));
    }

    /* access modifiers changed from: 0000 */
    public String determineDeviceToken(ZipInputStream zipInputStream) throws IOException {
        String name;
        do {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return "";
            }
            name = nextEntry.getName();
        } while (!name.startsWith("assets/com.crashlytics.android.beta/dirfactor-device-token="));
        return name.substring("assets/com.crashlytics.android.beta/dirfactor-device-token=".length(), name.length() - 1);
    }
}
