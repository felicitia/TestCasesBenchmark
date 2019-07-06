package com.otaliastudios.cameraview;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.ByteArrayOutputStream;

class CropHelper {
    static byte[] cropToJpeg(YuvImage yuvImage, AspectRatio aspectRatio, int i) {
        Rect computeCrop = computeCrop(yuvImage.getWidth(), yuvImage.getHeight(), aspectRatio);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(computeCrop, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    static byte[] cropToJpeg(byte[] bArr, AspectRatio aspectRatio, int i) {
        Bitmap decodeBitmap = CameraUtils.decodeBitmap(bArr, Integer.MAX_VALUE, Integer.MAX_VALUE);
        Rect computeCrop = computeCrop(decodeBitmap.getWidth(), decodeBitmap.getHeight(), aspectRatio);
        Bitmap createBitmap = Bitmap.createBitmap(decodeBitmap, computeCrop.left, computeCrop.top, computeCrop.width(), computeCrop.height());
        decodeBitmap.recycle();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        createBitmap.recycle();
        return byteArrayOutputStream.toByteArray();
    }

    private static Rect computeCrop(int i, int i2, AspectRatio aspectRatio) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (AspectRatio.of(i, i2).toFloat() > aspectRatio.toFloat()) {
            i3 = (int) (((float) i2) * aspectRatio.toFloat());
            i5 = (i - i3) / 2;
            i6 = i2;
            i4 = 0;
        } else {
            int i7 = (int) (((float) i) / aspectRatio.toFloat());
            i4 = (i2 - i7) / 2;
            i6 = i7;
            i3 = i;
            i5 = 0;
        }
        return new Rect(i5, i4, i3 + i5, i6 + i4);
    }
}
