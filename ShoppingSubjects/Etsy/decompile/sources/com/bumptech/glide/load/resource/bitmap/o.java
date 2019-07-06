package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.etsy.android.ui.dialog.EtsyDialogFragment;

/* compiled from: TransformationUtils */
public final class o {
    public static int a(int i) {
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

    public static Bitmap a(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        float f;
        float f2;
        if (bitmap2 == null) {
            return null;
        }
        if (bitmap2.getWidth() == i && bitmap2.getHeight() == i2) {
            return bitmap2;
        }
        Matrix matrix = new Matrix();
        float f3 = 0.0f;
        if (bitmap2.getWidth() * i2 > bitmap2.getHeight() * i) {
            f2 = ((float) i2) / ((float) bitmap2.getHeight());
            f = (((float) i) - (((float) bitmap2.getWidth()) * f2)) * 0.5f;
        } else {
            f2 = ((float) i) / ((float) bitmap2.getWidth());
            f3 = (((float) i2) - (((float) bitmap2.getHeight()) * f2)) * 0.5f;
            f = 0.0f;
        }
        matrix.setScale(f2, f2);
        matrix.postTranslate((float) ((int) (f + 0.5f)), (float) ((int) (f3 + 0.5f)));
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(i, i2, a(bitmap2));
        }
        a(bitmap2, bitmap);
        new Canvas(bitmap).drawBitmap(bitmap2, matrix, new Paint(6));
        return bitmap;
    }

    public static Bitmap a(Bitmap bitmap, c cVar, int i, int i2) {
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size matches input, returning input");
            }
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        int width = (int) (((float) bitmap.getWidth()) * min);
        int height = (int) (((float) bitmap.getHeight()) * min);
        if (bitmap.getWidth() == width && bitmap.getHeight() == height) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "adjusted target size matches input, returning input");
            }
            return bitmap;
        }
        Config a = a(bitmap);
        Bitmap a2 = cVar.a(width, height, a);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(width, height, a);
        }
        a(bitmap, a2);
        if (Log.isLoggable("TransformationUtils", 2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("request: ");
            sb.append(i);
            sb.append(EtsyDialogFragment.OPT_X_BUTTON);
            sb.append(i2);
            Log.v("TransformationUtils", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("toFit:   ");
            sb2.append(bitmap.getWidth());
            sb2.append(EtsyDialogFragment.OPT_X_BUTTON);
            sb2.append(bitmap.getHeight());
            Log.v("TransformationUtils", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("toReuse: ");
            sb3.append(a2.getWidth());
            sb3.append(EtsyDialogFragment.OPT_X_BUTTON);
            sb3.append(a2.getHeight());
            Log.v("TransformationUtils", sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append("minPct:   ");
            sb4.append(min);
            Log.v("TransformationUtils", sb4.toString());
        }
        Canvas canvas = new Canvas(a2);
        Matrix matrix = new Matrix();
        matrix.setScale(min, min);
        canvas.drawBitmap(bitmap, matrix, new Paint(6));
        return a2;
    }

    @TargetApi(12)
    public static void a(Bitmap bitmap, Bitmap bitmap2) {
        if (VERSION.SDK_INT >= 12 && bitmap2 != null) {
            bitmap2.setHasAlpha(bitmap.hasAlpha());
        }
    }

    public static Bitmap a(Bitmap bitmap, c cVar, int i) {
        Matrix matrix = new Matrix();
        a(i, matrix);
        if (matrix.isIdentity()) {
            return bitmap;
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        matrix.mapRect(rectF);
        int round = Math.round(rectF.width());
        int round2 = Math.round(rectF.height());
        Config a = a(bitmap);
        Bitmap a2 = cVar.a(round, round2, a);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(round, round2, a);
        }
        matrix.postTranslate(-rectF.left, -rectF.top);
        new Canvas(a2).drawBitmap(bitmap, matrix, new Paint(6));
        return a2;
    }

    private static Config a(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888;
    }

    static void a(int i, Matrix matrix) {
        switch (i) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return;
            case 3:
                matrix.setRotate(180.0f);
                return;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 6:
                matrix.setRotate(90.0f);
                return;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return;
            case 8:
                matrix.setRotate(-90.0f);
                return;
            default:
                return;
        }
    }
}
