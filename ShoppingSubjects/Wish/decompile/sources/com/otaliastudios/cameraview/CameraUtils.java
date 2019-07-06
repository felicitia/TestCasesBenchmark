package com.otaliastudios.cameraview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Handler;

public class CameraUtils {

    public interface BitmapCallback {
        void onBitmapReady(Bitmap bitmap);
    }

    public static boolean hasCameras(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature("android.hardware.camera") || packageManager.hasSystemFeature("android.hardware.camera.front");
    }

    public static boolean hasCameraFacing(Context context, Facing facing) {
        int intValue = ((Integer) new Mapper1().map(facing)).intValue();
        CameraInfo cameraInfo = new CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == intValue) {
                return true;
            }
        }
        return false;
    }

    public static void decodeBitmap(byte[] bArr, int i, int i2, BitmapCallback bitmapCallback) {
        final Handler handler = new Handler();
        final byte[] bArr2 = bArr;
        final int i3 = i;
        final int i4 = i2;
        final BitmapCallback bitmapCallback2 = bitmapCallback;
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                final Bitmap decodeBitmap = CameraUtils.decodeBitmap(bArr2, i3, i4);
                handler.post(new Runnable() {
                    public void run() {
                        bitmapCallback2.onBitmapReady(decodeBitmap);
                    }
                });
            }
        };
        WorkerHandler.run(r0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0071 A[SYNTHETIC, Splitter:B:40:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0078 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00cc A[SYNTHETIC, Splitter:B:58:0x00cc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Bitmap decodeBitmap(byte[] r17, int r18, int r19) {
        /*
            r1 = r17
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r18 > 0) goto L_0x000b
            r4 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x000d
        L_0x000b:
            r4 = r18
        L_0x000d:
            if (r19 > 0) goto L_0x0013
            r3 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0015
        L_0x0013:
            r3 = r19
        L_0x0015:
            r5 = 0
            r6 = 1
            r7 = 0
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0069, all -> 0x0065 }
            r8.<init>(r1)     // Catch:{ IOException -> 0x0069, all -> 0x0065 }
            android.support.media.ExifInterface r5 = new android.support.media.ExifInterface     // Catch:{ IOException -> 0x0063 }
            r5.<init>(r8)     // Catch:{ IOException -> 0x0063 }
            java.lang.String r9 = "Orientation"
            int r5 = r5.getAttributeInt(r9, r6)     // Catch:{ IOException -> 0x0063 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x0063 }
            int r9 = r5.intValue()     // Catch:{ IOException -> 0x0063 }
            switch(r9) {
                case 1: goto L_0x0033;
                case 2: goto L_0x0033;
                case 3: goto L_0x003b;
                case 4: goto L_0x003b;
                case 5: goto L_0x0038;
                case 6: goto L_0x0038;
                case 7: goto L_0x0035;
                case 8: goto L_0x0035;
                default: goto L_0x0033;
            }     // Catch:{ IOException -> 0x0063 }
        L_0x0033:
            r9 = 0
            goto L_0x003d
        L_0x0035:
            r9 = 270(0x10e, float:3.78E-43)
            goto L_0x003d
        L_0x0038:
            r9 = 90
            goto L_0x003d
        L_0x003b:
            r9 = 180(0xb4, float:2.52E-43)
        L_0x003d:
            int r10 = r5.intValue()     // Catch:{ IOException -> 0x0063 }
            r11 = 2
            if (r10 == r11) goto L_0x005c
            int r10 = r5.intValue()     // Catch:{ IOException -> 0x0063 }
            r11 = 4
            if (r10 == r11) goto L_0x005c
            int r10 = r5.intValue()     // Catch:{ IOException -> 0x0063 }
            r11 = 5
            if (r10 == r11) goto L_0x005c
            int r5 = r5.intValue()     // Catch:{ IOException -> 0x0063 }
            r10 = 7
            if (r5 != r10) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            r5 = 0
            goto L_0x005d
        L_0x005c:
            r5 = 1
        L_0x005d:
            if (r8 == 0) goto L_0x0076
            r8.close()     // Catch:{ Exception -> 0x0076 }
            goto L_0x0076
        L_0x0063:
            r0 = move-exception
            goto L_0x006b
        L_0x0065:
            r0 = move-exception
            r1 = r0
            r8 = r5
            goto L_0x00ca
        L_0x0069:
            r0 = move-exception
            r8 = r5
        L_0x006b:
            r5 = r0
            r5.printStackTrace()     // Catch:{ all -> 0x00c8 }
            if (r8 == 0) goto L_0x0074
            r8.close()     // Catch:{ Exception -> 0x0074 }
        L_0x0074:
            r5 = 0
            r9 = 0
        L_0x0076:
            if (r4 < r2) goto L_0x0081
            if (r3 >= r2) goto L_0x007b
            goto L_0x0081
        L_0x007b:
            int r2 = r1.length
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r7, r2)
            goto L_0x00a5
        L_0x0081:
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options
            r2.<init>()
            r2.inJustDecodeBounds = r6
            int r6 = r1.length
            android.graphics.BitmapFactory.decodeByteArray(r1, r7, r6, r2)
            int r6 = r2.outHeight
            int r8 = r2.outWidth
            int r10 = r9 % 180
            if (r10 == 0) goto L_0x0098
            int r6 = r2.outWidth
            int r8 = r2.outHeight
        L_0x0098:
            int r3 = computeSampleSize(r8, r6, r4, r3)
            r2.inSampleSize = r3
            r2.inJustDecodeBounds = r7
            int r3 = r1.length
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r1, r7, r3, r2)
        L_0x00a5:
            if (r9 != 0) goto L_0x00a9
            if (r5 == 0) goto L_0x00c7
        L_0x00a9:
            android.graphics.Matrix r15 = new android.graphics.Matrix
            r15.<init>()
            float r2 = (float) r9
            r15.setRotate(r2)
            r11 = 0
            r12 = 0
            int r13 = r1.getWidth()
            int r14 = r1.getHeight()
            r16 = 1
            r10 = r1
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r10, r11, r12, r13, r14, r15, r16)
            r1.recycle()
            r1 = r2
        L_0x00c7:
            return r1
        L_0x00c8:
            r0 = move-exception
            r1 = r0
        L_0x00ca:
            if (r8 == 0) goto L_0x00cf
            r8.close()     // Catch:{ Exception -> 0x00cf }
        L_0x00cf:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.otaliastudios.cameraview.CameraUtils.decodeBitmap(byte[], int, int):android.graphics.Bitmap");
    }

    private static int computeSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            while (true) {
                if (i2 / i5 < i4 && i / i5 < i3) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }
}
