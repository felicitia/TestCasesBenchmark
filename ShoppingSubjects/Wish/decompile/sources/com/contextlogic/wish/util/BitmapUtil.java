package com.contextlogic.wish.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Base64;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtil {

    private interface BitmapDecoderProvider {
        InputStream getNewInputStream() throws IOException;

        int getRotation();
    }

    public static int getDominantColor(Bitmap bitmap) {
        try {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
            int pixel = createScaledBitmap.getPixel(0, 0);
            createScaledBitmap.recycle();
            return pixel;
        } catch (Throwable unused) {
            return WishApplication.getInstance().getResources().getColor(R.color.transparent);
        }
    }

    public static String getBase64EncodedBitmap(Bitmap bitmap) {
        return getBase64EncodedBitmap(bitmap, 90);
    }

    public static String getBase64EncodedBitmap(Bitmap bitmap, int i) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static int getInSampleValue(int i, int i2, float f, float f2) {
        float f3 = (float) i;
        int i3 = 1;
        if (f3 < f && ((float) i2) < f2) {
            return 1;
        }
        float f4 = (float) i2;
        if (((float) ((int) ((f / f3) * f4))) >= f2) {
            i3 = Math.max(1, roundToPowerOfTwo(f3 / f));
        } else if (((float) ((int) (f3 * (f2 / f4)))) >= f) {
            i3 = Math.max(1, roundToPowerOfTwo(f4 / f2));
        }
        return i3;
    }

    private static int roundToPowerOfTwo(float f) {
        return (int) Math.pow(2.0d, (double) ((int) Math.floor(Math.log(((double) f) / Math.log(2.0d)))));
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, float f, float f2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f3 = (float) width;
        if (f >= f3 && f2 >= ((float) height)) {
            return bitmap;
        }
        float f4 = (float) height;
        int i = (int) ((f / f3) * f4);
        if (((float) i) <= f2) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) f, i, true);
            if (createScaledBitmap != bitmap) {
                bitmap.recycle();
            }
            return createScaledBitmap;
        }
        int i2 = (int) (f3 * (f2 / f4));
        if (((float) i2) > f) {
            return bitmap;
        }
        Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(bitmap, i2, (int) f2, true);
        if (createScaledBitmap2 != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap2;
    }

    public static Bitmap decodeBitmapBytes(byte[] bArr, int i, int i2) {
        return decodeBitmapBytes(bArr, 0, i, i2);
    }

    public static Bitmap decodeBitmapBytes(final byte[] bArr, final int i, int i2, int i3) {
        return decodeBitmap(new BitmapDecoderProvider() {
            public InputStream getNewInputStream() throws IOException {
                return new ByteArrayInputStream(bArr);
            }

            public int getRotation() {
                return i;
            }
        }, i2, i3);
    }

    public static Bitmap decodeBitmapFile(final String str, int i, int i2) {
        return decodeBitmap(new BitmapDecoderProvider() {
            public InputStream getNewInputStream() throws IOException {
                return BitmapUtil.openInputStream(str);
            }

            public int getRotation() {
                try {
                    int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
                    if (attributeInt == 3) {
                        return 180;
                    }
                    if (attributeInt == 6) {
                        return 90;
                    }
                    if (attributeInt != 8) {
                        return 0;
                    }
                    return 270;
                } catch (Exception unused) {
                    return 0;
                }
            }
        }, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0106, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0063, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0064, code lost:
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0067, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        java.lang.System.gc();
        java.lang.Runtime.getRuntime().gc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        r14 = android.graphics.Bitmap.createBitmap(r3, 0, 0, r3.getWidth(), r3.getHeight(), r14, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00e6, code lost:
        if (r14 != r3) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00e8, code lost:
        r3.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00eb, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00ed, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00ee, code lost:
        r2 = r13;
        r13 = r14;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x00f2, code lost:
        r2 = r13;
        r3 = r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:80:0x00cb */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0106 A[ExcHandler: all (th java.lang.Throwable), PHI: r2 r3 
      PHI: (r2v6 java.io.InputStream) = (r2v4 java.io.InputStream), (r2v4 java.io.InputStream), (r2v7 java.io.InputStream), (r2v4 java.io.InputStream), (r2v4 java.io.InputStream), (r2v4 java.io.InputStream), (r2v4 java.io.InputStream) binds: [B:10:0x0019, B:11:?, B:22:0x004e, B:18:0x004a, B:19:?, B:13:0x0044, B:14:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v8 java.io.BufferedInputStream) = (r3v6 java.io.BufferedInputStream), (r3v6 java.io.BufferedInputStream), (r3v9 java.io.BufferedInputStream), (r3v9 java.io.BufferedInputStream), (r3v9 java.io.BufferedInputStream), (r3v6 java.io.BufferedInputStream), (r3v6 java.io.BufferedInputStream) binds: [B:10:0x0019, B:11:?, B:22:0x004e, B:18:0x004a, B:19:?, B:13:0x0044, B:14:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0112 A[SYNTHETIC, Splitter:B:113:0x0112] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0117 A[SYNTHETIC, Splitter:B:117:0x0117] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x011f A[SYNTHETIC, Splitter:B:125:0x011f] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0124 A[SYNTHETIC, Splitter:B:129:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063 A[ExcHandler: all (th java.lang.Throwable), PHI: r2 r4 
      PHI: (r2v19 java.io.BufferedInputStream) = (r2v10 java.io.BufferedInputStream), (r2v10 java.io.BufferedInputStream), (r2v10 java.io.BufferedInputStream), (r2v14 java.io.BufferedInputStream), (r2v14 java.io.BufferedInputStream), (r2v14 java.io.BufferedInputStream), (r2v10 java.io.BufferedInputStream), (r2v10 java.io.BufferedInputStream), (r2v10 java.io.BufferedInputStream) binds: [B:26:0x0057, B:38:0x006a, B:39:?, B:50:0x0080, B:46:0x007c, B:47:?, B:41:0x0076, B:42:?, B:29:0x005d] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v13 java.io.InputStream) = (r4v3 java.io.InputStream), (r4v3 java.io.InputStream), (r4v3 java.io.InputStream), (r4v10 java.io.InputStream), (r4v3 java.io.InputStream), (r4v3 java.io.InputStream), (r4v3 java.io.InputStream), (r4v3 java.io.InputStream), (r4v3 java.io.InputStream) binds: [B:26:0x0057, B:38:0x006a, B:39:?, B:50:0x0080, B:46:0x007c, B:47:?, B:41:0x0076, B:42:?, B:29:0x005d] A[DONT_GENERATE, DONT_INLINE], Splitter:B:26:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x00ed A[ExcHandler: all (r14v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r4 r13 
      PHI: (r4v6 java.io.BufferedInputStream) = (r4v7 java.io.BufferedInputStream), (r4v7 java.io.BufferedInputStream), (r4v7 java.io.BufferedInputStream), (r4v7 java.io.BufferedInputStream), (r4v7 java.io.BufferedInputStream), (r4v7 java.io.BufferedInputStream), (r4v11 java.io.BufferedInputStream), (r4v11 java.io.BufferedInputStream) binds: [B:72:0x00aa, B:73:?, B:75:0x00b5, B:80:0x00cb, B:81:?, B:83:0x00d7, B:54:0x0089, B:57:0x008f] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v7 java.io.InputStream) = (r13v9 java.io.InputStream), (r13v9 java.io.InputStream), (r13v9 java.io.InputStream), (r13v9 java.io.InputStream), (r13v9 java.io.InputStream), (r13v9 java.io.InputStream), (r13v11 java.io.InputStream), (r13v11 java.io.InputStream) binds: [B:72:0x00aa, B:73:?, B:75:0x00b5, B:80:0x00cb, B:81:?, B:83:0x00d7, B:54:0x0089, B:57:0x008f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:54:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00f8 A[SYNTHETIC, Splitter:B:95:0x00f8] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x00fd A[SYNTHETIC, Splitter:B:99:0x00fd] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap decodeBitmap(com.contextlogic.wish.util.BitmapUtil.BitmapDecoderProvider r13, int r14, int r15) {
        /*
            int r0 = r13.getRotation()
            r1 = 90
            if (r0 == r1) goto L_0x000c
            r1 = 270(0x10e, float:3.78E-43)
            if (r0 != r1) goto L_0x000f
        L_0x000c:
            r12 = r15
            r15 = r14
            r14 = r12
        L_0x000f:
            r1 = 0
            java.io.InputStream r2 = r13.getNewInputStream()     // Catch:{ Throwable -> 0x011b, all -> 0x010d }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x010b, all -> 0x0108 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x010b, all -> 0x0108 }
            android.graphics.BitmapFactory$Options r4 = new android.graphics.BitmapFactory$Options     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r4.<init>()     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r5 = 1
            r4.inJustDecodeBounds = r5     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            android.graphics.BitmapFactory.decodeStream(r3, r1, r4)     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            int r6 = r4.outHeight     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            int r4 = r4.outWidth     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            android.graphics.BitmapFactory$Options r7 = new android.graphics.BitmapFactory$Options     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r7.<init>()     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r7.inPurgeable = r5     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r7.inInputShareable = r5     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r5 = 0
            r7.inDither = r5     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r7.inScaled = r5     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            android.graphics.Bitmap$Config r5 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r7.inPreferredConfig = r5     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            float r14 = (float) r14     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            float r15 = (float) r15     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            int r4 = getInSampleValue(r4, r6, r14, r15)     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            r7.inSampleSize = r4     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            if (r3 == 0) goto L_0x0048
            r3.close()     // Catch:{ Throwable -> 0x0047, all -> 0x0106 }
        L_0x0047:
            r3 = r1
        L_0x0048:
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ Throwable -> 0x004d, all -> 0x0106 }
        L_0x004d:
            r2 = r1
        L_0x004e:
            java.io.InputStream r4 = r13.getNewInputStream()     // Catch:{ Throwable -> 0x011d, all -> 0x0106 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0104, all -> 0x0101 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0104, all -> 0x0101 }
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r2, r1, r7)     // Catch:{ Throwable -> 0x0067, all -> 0x0063 }
            if (r3 == 0) goto L_0x0068
            android.graphics.Bitmap r5 = resizeBitmap(r3, r14, r15)     // Catch:{ Throwable -> 0x0068, all -> 0x0063 }
            r3 = r5
            goto L_0x0068
        L_0x0063:
            r13 = move-exception
            r3 = r2
            goto L_0x0102
        L_0x0067:
            r3 = r1
        L_0x0068:
            if (r3 != 0) goto L_0x00a4
            java.lang.System.gc()     // Catch:{ Throwable -> 0x00a1, all -> 0x0063 }
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x00a1, all -> 0x0063 }
            r5.gc()     // Catch:{ Throwable -> 0x00a1, all -> 0x0063 }
            if (r2 == 0) goto L_0x007a
            r2.close()     // Catch:{ Throwable -> 0x0079, all -> 0x0063 }
        L_0x0079:
            r2 = r1
        L_0x007a:
            if (r4 == 0) goto L_0x0080
            r4.close()     // Catch:{ Throwable -> 0x007f, all -> 0x0063 }
        L_0x007f:
            r4 = r1
        L_0x0080:
            java.io.InputStream r13 = r13.getNewInputStream()     // Catch:{ Throwable -> 0x00a1, all -> 0x0063 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x009d, all -> 0x0097 }
            r4.<init>(r13)     // Catch:{ Throwable -> 0x009d, all -> 0x0097 }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r13, r1, r7)     // Catch:{ Throwable -> 0x00a6, all -> 0x00ed }
            if (r2 == 0) goto L_0x0095
            android.graphics.Bitmap r14 = resizeBitmap(r2, r14, r15)     // Catch:{ Throwable -> 0x0095, all -> 0x00ed }
            r3 = r14
            goto L_0x00a6
        L_0x0095:
            r3 = r2
            goto L_0x00a6
        L_0x0097:
            r14 = move-exception
            r3 = r2
            r2 = r13
            r13 = r14
            goto L_0x0110
        L_0x009d:
            r3 = r2
            r2 = r13
            goto L_0x011d
        L_0x00a1:
            r3 = r2
            goto L_0x0104
        L_0x00a4:
            r13 = r4
            r4 = r2
        L_0x00a6:
            if (r3 == 0) goto L_0x00f5
            if (r0 <= 0) goto L_0x00f5
            android.graphics.Matrix r14 = new android.graphics.Matrix     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            r14.<init>()     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            float r15 = (float) r0     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            r14.postRotate(r15)     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            r6 = 0
            r7 = 0
            int r8 = r3.getWidth()     // Catch:{ Throwable -> 0x00cb, all -> 0x00ed }
            int r9 = r3.getHeight()     // Catch:{ Throwable -> 0x00cb, all -> 0x00ed }
            r11 = 1
            r5 = r3
            r10 = r14
            android.graphics.Bitmap r15 = android.graphics.Bitmap.createBitmap(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x00cb, all -> 0x00ed }
            if (r15 == r3) goto L_0x00c9
            r3.recycle()     // Catch:{ Throwable -> 0x00cb, all -> 0x00ed }
        L_0x00c9:
            r1 = r15
            goto L_0x00f6
        L_0x00cb:
            java.lang.System.gc()     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            java.lang.Runtime r15 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            r15.gc()     // Catch:{ Throwable -> 0x00f2, all -> 0x00ed }
            r6 = 0
            r7 = 0
            int r8 = r3.getWidth()     // Catch:{ Throwable -> 0x00f5, all -> 0x00ed }
            int r9 = r3.getHeight()     // Catch:{ Throwable -> 0x00f5, all -> 0x00ed }
            r11 = 1
            r5 = r3
            r10 = r14
            android.graphics.Bitmap r14 = android.graphics.Bitmap.createBitmap(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x00f5, all -> 0x00ed }
            if (r14 == r3) goto L_0x00eb
            r3.recycle()     // Catch:{ Throwable -> 0x00f5, all -> 0x00ed }
        L_0x00eb:
            r1 = r14
            goto L_0x00f6
        L_0x00ed:
            r14 = move-exception
            r2 = r13
            r13 = r14
            r3 = r4
            goto L_0x0110
        L_0x00f2:
            r2 = r13
            r3 = r4
            goto L_0x011d
        L_0x00f5:
            r1 = r3
        L_0x00f6:
            if (r4 == 0) goto L_0x00fb
            r4.close()     // Catch:{ Throwable -> 0x00fb }
        L_0x00fb:
            if (r13 == 0) goto L_0x0127
            r13.close()     // Catch:{ Throwable -> 0x0127 }
            goto L_0x0127
        L_0x0101:
            r13 = move-exception
        L_0x0102:
            r2 = r4
            goto L_0x0110
        L_0x0104:
            r2 = r4
            goto L_0x011d
        L_0x0106:
            r13 = move-exception
            goto L_0x0110
        L_0x0108:
            r13 = move-exception
            r3 = r1
            goto L_0x0110
        L_0x010b:
            r3 = r1
            goto L_0x011d
        L_0x010d:
            r13 = move-exception
            r2 = r1
            r3 = r2
        L_0x0110:
            if (r3 == 0) goto L_0x0115
            r3.close()     // Catch:{ Throwable -> 0x0115 }
        L_0x0115:
            if (r2 == 0) goto L_0x011a
            r2.close()     // Catch:{ Throwable -> 0x011a }
        L_0x011a:
            throw r13
        L_0x011b:
            r2 = r1
            r3 = r2
        L_0x011d:
            if (r3 == 0) goto L_0x0122
            r3.close()     // Catch:{ Throwable -> 0x0122 }
        L_0x0122:
            if (r2 == 0) goto L_0x0127
            r2.close()     // Catch:{ Throwable -> 0x0127 }
        L_0x0127:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.util.BitmapUtil.decodeBitmap(com.contextlogic.wish.util.BitmapUtil$BitmapDecoderProvider, int, int):android.graphics.Bitmap");
    }

    /* access modifiers changed from: private */
    public static InputStream openInputStream(String str) throws FileNotFoundException {
        try {
            return new FileInputStream(str);
        } catch (Throwable unused) {
            return WishApplication.getInstance().getContentResolver().openInputStream(Uri.parse(str));
        }
    }

    public static Bitmap openImageUri(Uri uri) {
        return openImageUri(uri, 1024, 1024);
    }

    public static Bitmap openImageUri(Uri uri, int i, int i2) {
        if (uri != null) {
            try {
                return decodeBitmapFile(FileUtil.getPath(uri), i, i2);
            } catch (Throwable unused) {
            }
        }
        return null;
    }
}
