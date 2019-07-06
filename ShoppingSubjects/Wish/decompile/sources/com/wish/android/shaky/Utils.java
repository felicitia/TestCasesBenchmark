package com.wish.android.shaky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.util.Locale;

final class Utils {
    private static final String TAG = "Utils";

    private Utils() {
    }

    static String createUniqueFilename(String str) {
        return String.format(Locale.US, "%s_%s.jpg", new Object[]{str, Long.toString(System.currentTimeMillis())});
    }

    static File writeBitmapToDirectory(Bitmap bitmap, File file) {
        if (file.mkdirs() || file.exists()) {
            return writeBitmapToFile(bitmap, new File(file, createUniqueFilename("bitmap")));
        }
        Log.e(TAG, "Failed to create directory for bitmap.");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A[SYNTHETIC, Splitter:B:18:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004d A[SYNTHETIC, Splitter:B:26:0x004d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.io.File writeBitmapToFile(android.graphics.Bitmap r4, java.io.File r5) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x002d, all -> 0x002b }
            r1.<init>()     // Catch:{ IOException -> 0x002d, all -> 0x002b }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ IOException -> 0x002d, all -> 0x002b }
            r3 = 0
            r4.compress(r2, r3, r1)     // Catch:{ IOException -> 0x002d, all -> 0x002b }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002d, all -> 0x002b }
            r4.<init>(r5)     // Catch:{ IOException -> 0x002d, all -> 0x002b }
            byte[] r1 = r1.toByteArray()     // Catch:{ IOException -> 0x0029 }
            r4.write(r1)     // Catch:{ IOException -> 0x0029 }
            if (r4 == 0) goto L_0x0028
            r4.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x0028
        L_0x001e:
            r4 = move-exception
            java.lang.String r0 = TAG
            java.lang.String r1 = r4.getMessage()
            android.util.Log.e(r0, r1, r4)
        L_0x0028:
            return r5
        L_0x0029:
            r5 = move-exception
            goto L_0x002f
        L_0x002b:
            r5 = move-exception
            goto L_0x004b
        L_0x002d:
            r5 = move-exception
            r4 = r0
        L_0x002f:
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = r5.getMessage()     // Catch:{ all -> 0x0049 }
            android.util.Log.e(r1, r2, r5)     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x0048
            r4.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0048
        L_0x003e:
            r4 = move-exception
            java.lang.String r5 = TAG
            java.lang.String r1 = r4.getMessage()
            android.util.Log.e(r5, r1, r4)
        L_0x0048:
            return r0
        L_0x0049:
            r5 = move-exception
            r0 = r4
        L_0x004b:
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ IOException -> 0x0051 }
            goto L_0x005b
        L_0x0051:
            r4 = move-exception
            java.lang.String r0 = TAG
            java.lang.String r1 = r4.getMessage()
            android.util.Log.e(r0, r1, r4)
        L_0x005b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wish.android.shaky.Utils.writeBitmapToFile(android.graphics.Bitmap, java.io.File):java.io.File");
    }

    static Bitmap capture(View view) {
        if (view.getWidth() == 0 || view.getHeight() == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    static Uri getProviderUri(Context context, File file) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        sb.append(".fileprovider");
        return FileProvider.getUriForFile(context, sb.toString(), file);
    }

    static Uri getProviderUri(Context context, Uri uri) {
        return getProviderUri(context, new File(uri.getPath()));
    }
}
