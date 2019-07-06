package jp.wasabeef.glide.transformations;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

public class CropTransformation implements f<Bitmap> {
    private c a;
    private int b;
    private int c;
    private CropType d;

    public enum CropType {
        TOP,
        CENTER,
        BOTTOM
    }

    public i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        Bitmap bitmap = (Bitmap) iVar.b();
        this.b = this.b == 0 ? bitmap.getWidth() : this.b;
        this.c = this.c == 0 ? bitmap.getHeight() : this.c;
        Config config = bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888;
        Bitmap a2 = this.a.a(this.b, this.c, config);
        if (a2 == null) {
            a2 = Bitmap.createBitmap(this.b, this.c, config);
        }
        float max = Math.max(((float) this.b) / ((float) bitmap.getWidth()), ((float) this.c) / ((float) bitmap.getHeight()));
        float width = ((float) bitmap.getWidth()) * max;
        float height = max * ((float) bitmap.getHeight());
        float f = (((float) this.b) - width) / 2.0f;
        float a3 = a(height);
        new Canvas(a2).drawBitmap(bitmap, null, new RectF(f, a3, width + f, height + a3), null);
        return com.bumptech.glide.load.resource.bitmap.c.a(a2, this.a);
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("CropTransformation(width=");
        sb.append(this.b);
        sb.append(", height=");
        sb.append(this.c);
        sb.append(", cropType=");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }

    private float a(float f) {
        switch (this.d) {
            case TOP:
                return 0.0f;
            case CENTER:
                return (((float) this.c) - f) / 2.0f;
            case BOTTOM:
                return ((float) this.c) - f;
            default:
                return 0.0f;
        }
    }
}
