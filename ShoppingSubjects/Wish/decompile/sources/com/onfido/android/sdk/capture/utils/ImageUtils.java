package com.onfido.android.sdk.capture.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.onfido.android.sdk.capture.ui.camera.Exif;
import kotlin.jvm.internal.Intrinsics;

public class ImageUtils {
    private final int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private final Matrix a(int i) {
        Matrix matrix = new Matrix();
        switch (i) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return matrix;
            case 3:
                matrix.setRotate(180.0f);
                return matrix;
            case 4:
                matrix.setRotate(180.0f);
                break;
            case 5:
                matrix.setRotate(90.0f);
                break;
            case 6:
                matrix.setRotate(90.0f);
                return matrix;
            case 7:
                matrix.setRotate(-90.0f);
                break;
            case 8:
                matrix.setRotate(-90.0f);
                return matrix;
            default:
                return matrix;
        }
        matrix.postScale(-1.0f, 1.0f);
        return matrix;
    }

    public Bitmap decodeScaledResource(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        byte[] bArr2 = bArr;
        Intrinsics.checkParameterIsNotNull(bArr2, "data");
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.RGB_565;
        int exifOrientationIdentifier = Exif.exifOrientationIdentifier(bArr2);
        int exifOrientationDegrees = getExifOrientationDegrees(exifOrientationIdentifier);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length, options);
        Matrix a = a(exifOrientationIdentifier);
        if (exifOrientationDegrees == 90 || exifOrientationDegrees == 270) {
            i3 = i;
            i4 = i2;
        } else {
            i4 = i;
            i3 = i2;
        }
        options.inSampleSize = a(options, i4, i3);
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), a, true);
        if (decodeByteArray != createBitmap) {
            decodeByteArray.recycle();
        }
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "rotatedBitmap");
        return createBitmap;
    }

    public int getExifOrientationDegrees(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public int[] getPixelsForByteArray(byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        Bitmap decodeScaledResource = decodeScaledResource(bArr, i, i2);
        int[] iArr = new int[(decodeScaledResource.getWidth() * decodeScaledResource.getHeight())];
        decodeScaledResource.getPixels(iArr, 0, decodeScaledResource.getWidth(), 0, 0, decodeScaledResource.getWidth(), decodeScaledResource.getHeight());
        return iArr;
    }
}
