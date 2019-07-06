package com.google.android.exoplayer2.text;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager.CaptionStyle;
import com.google.android.exoplayer2.util.Util;

public final class CaptionStyleCompat {
    public static final CaptionStyleCompat DEFAULT;
    public final int backgroundColor;
    public final int edgeColor;
    public final int edgeType;
    public final int foregroundColor;
    public final Typeface typeface;
    public final int windowColor;

    static {
        CaptionStyleCompat captionStyleCompat = new CaptionStyleCompat(-1, -16777216, 0, 0, -1, null);
        DEFAULT = captionStyleCompat;
    }

    @TargetApi(19)
    public static CaptionStyleCompat createFromCaptionStyle(CaptionStyle captionStyle) {
        if (Util.SDK_INT >= 21) {
            return createFromCaptionStyleV21(captionStyle);
        }
        return createFromCaptionStyleV19(captionStyle);
    }

    public CaptionStyleCompat(int i, int i2, int i3, int i4, int i5, Typeface typeface2) {
        this.foregroundColor = i;
        this.backgroundColor = i2;
        this.windowColor = i3;
        this.edgeType = i4;
        this.edgeColor = i5;
        this.typeface = typeface2;
    }

    @TargetApi(19)
    private static CaptionStyleCompat createFromCaptionStyleV19(CaptionStyle captionStyle) {
        CaptionStyleCompat captionStyleCompat = new CaptionStyleCompat(captionStyle.foregroundColor, captionStyle.backgroundColor, 0, captionStyle.edgeType, captionStyle.edgeColor, captionStyle.getTypeface());
        return captionStyleCompat;
    }

    @TargetApi(21)
    private static CaptionStyleCompat createFromCaptionStyleV21(CaptionStyle captionStyle) {
        CaptionStyleCompat captionStyleCompat = new CaptionStyleCompat(captionStyle.hasForegroundColor() ? captionStyle.foregroundColor : DEFAULT.foregroundColor, captionStyle.hasBackgroundColor() ? captionStyle.backgroundColor : DEFAULT.backgroundColor, captionStyle.hasWindowColor() ? captionStyle.windowColor : DEFAULT.windowColor, captionStyle.hasEdgeType() ? captionStyle.edgeType : DEFAULT.edgeType, captionStyle.hasEdgeColor() ? captionStyle.edgeColor : DEFAULT.edgeColor, captionStyle.getTypeface());
        return captionStyleCompat;
    }
}
