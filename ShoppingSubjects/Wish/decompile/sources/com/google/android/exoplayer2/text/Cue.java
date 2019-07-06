package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout.Alignment;

public class Cue {
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final int positionAnchor;
    public final float size;
    public final CharSequence text;
    public final Alignment textAlignment;
    public final int windowColor;
    public final boolean windowColorSet;

    public Cue(Bitmap bitmap2, float f, int i, float f2, int i2, float f3, float f4) {
        this(null, null, bitmap2, f2, 0, i2, f, i, f3, f4, false, -16777216);
    }

    public Cue(CharSequence charSequence) {
        this(charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public Cue(CharSequence charSequence, Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        this(charSequence, alignment, f, i, i2, f2, i3, f3, false, -16777216);
    }

    public Cue(CharSequence charSequence, Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4) {
        this(charSequence, alignment, null, f, i, i2, f2, i3, f3, Float.MIN_VALUE, z, i4);
    }

    private Cue(CharSequence charSequence, Alignment alignment, Bitmap bitmap2, float f, int i, int i2, float f2, int i3, float f3, float f4, boolean z, int i4) {
        this.text = charSequence;
        this.textAlignment = alignment;
        this.bitmap = bitmap2;
        this.line = f;
        this.lineType = i;
        this.lineAnchor = i2;
        this.position = f2;
        this.positionAnchor = i3;
        this.size = f3;
        this.bitmapHeight = f4;
        this.windowColorSet = z;
        this.windowColor = i4;
    }
}
