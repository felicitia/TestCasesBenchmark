package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.util.Util;

final class SubtitlePainter {
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private final float cornerRadius;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    private float cueSize;
    private CharSequence cueText;
    private Alignment cueTextAlignment;
    private int edgeColor;
    private int edgeType;
    private int foregroundColor;
    private final RectF lineBounds = new RectF();
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private StaticLayout textLayout;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private float textSizePx;
    private int textTop;
    private int windowColor;

    public SubtitlePainter(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{16843287, 16843288}, 0, 0);
        this.spacingAdd = (float) obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        float round = (float) Math.round((((float) context.getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        this.cornerRadius = round;
        this.outlineWidth = round;
        this.shadowRadius = round;
        this.shadowOffset = round;
        this.textPaint = new TextPaint();
        this.textPaint.setAntiAlias(true);
        this.textPaint.setSubpixelText(true);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Style.FILL);
    }

    public void draw(Cue cue, boolean z, boolean z2, CaptionStyleCompat captionStyleCompat, float f, float f2, Canvas canvas, int i, int i2, int i3, int i4) {
        boolean z3 = cue.bitmap == null;
        int i5 = -16777216;
        if (z3) {
            if (!TextUtils.isEmpty(cue.text)) {
                i5 = (!cue.windowColorSet || !z) ? captionStyleCompat.windowColor : cue.windowColor;
            } else {
                return;
            }
        }
        if (areCharSequencesEqual(this.cueText, cue.text) && Util.areEqual(this.cueTextAlignment, cue.textAlignment) && this.cueBitmap == cue.bitmap && this.cueLine == cue.line && this.cueLineType == cue.lineType && Util.areEqual(Integer.valueOf(this.cueLineAnchor), Integer.valueOf(cue.lineAnchor)) && this.cuePosition == cue.position && Util.areEqual(Integer.valueOf(this.cuePositionAnchor), Integer.valueOf(cue.positionAnchor)) && this.cueSize == cue.size && this.cueBitmapHeight == cue.bitmapHeight && this.applyEmbeddedStyles == z && this.applyEmbeddedFontSizes == z2 && this.foregroundColor == captionStyleCompat.foregroundColor && this.backgroundColor == captionStyleCompat.backgroundColor && this.windowColor == i5 && this.edgeType == captionStyleCompat.edgeType && this.edgeColor == captionStyleCompat.edgeColor && Util.areEqual(this.textPaint.getTypeface(), captionStyleCompat.typeface) && this.textSizePx == f && this.bottomPaddingFraction == f2 && this.parentLeft == i && this.parentTop == i2 && this.parentRight == i3 && this.parentBottom == i4) {
            drawLayout(canvas, z3);
            return;
        }
        this.cueText = cue.text;
        this.cueTextAlignment = cue.textAlignment;
        this.cueBitmap = cue.bitmap;
        this.cueLine = cue.line;
        this.cueLineType = cue.lineType;
        this.cueLineAnchor = cue.lineAnchor;
        this.cuePosition = cue.position;
        this.cuePositionAnchor = cue.positionAnchor;
        this.cueSize = cue.size;
        this.cueBitmapHeight = cue.bitmapHeight;
        this.applyEmbeddedStyles = z;
        this.applyEmbeddedFontSizes = z2;
        this.foregroundColor = captionStyleCompat.foregroundColor;
        this.backgroundColor = captionStyleCompat.backgroundColor;
        this.windowColor = i5;
        this.edgeType = captionStyleCompat.edgeType;
        this.edgeColor = captionStyleCompat.edgeColor;
        this.textPaint.setTypeface(captionStyleCompat.typeface);
        this.textSizePx = f;
        this.bottomPaddingFraction = f2;
        this.parentLeft = i;
        this.parentTop = i2;
        this.parentRight = i3;
        this.parentBottom = i4;
        if (z3) {
            setupTextLayout();
        } else {
            setupBitmapLayout();
        }
        drawLayout(canvas, z3);
    }

    /* JADX WARNING: type inference failed for: r6v6, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r17v0, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r9v4, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r6v17, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r6v19 */
    /* JADX WARNING: type inference failed for: r6v20 */
    /* JADX WARNING: type inference failed for: r6v21 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupTextLayout() {
        /*
            r25 = this;
            r0 = r25
            int r1 = r0.parentRight
            int r2 = r0.parentLeft
            int r1 = r1 - r2
            int r2 = r0.parentBottom
            int r3 = r0.parentTop
            int r2 = r2 - r3
            android.text.TextPaint r3 = r0.textPaint
            float r4 = r0.textSizePx
            r3.setTextSize(r4)
            float r3 = r0.textSizePx
            r4 = 1040187392(0x3e000000, float:0.125)
            float r3 = r3 * r4
            r4 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r4
            int r3 = (int) r3
            int r4 = r3 * 2
            int r5 = r1 - r4
            float r6 = r0.cueSize
            r7 = 1
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 == 0) goto L_0x002e
            float r5 = (float) r5
            float r6 = r0.cueSize
            float r5 = r5 * r6
            int r5 = (int) r5
        L_0x002e:
            if (r5 > 0) goto L_0x0038
            java.lang.String r1 = "SubtitlePainter"
            java.lang.String r2 = "Skipped drawing subtitle cue (insufficient space)"
            android.util.Log.w(r1, r2)
            return
        L_0x0038:
            boolean r6 = r0.applyEmbeddedFontSizes
            r15 = 0
            if (r6 == 0) goto L_0x0046
            boolean r6 = r0.applyEmbeddedStyles
            if (r6 == 0) goto L_0x0046
            java.lang.CharSequence r6 = r0.cueText
        L_0x0043:
            r17 = r6
            goto L_0x0084
        L_0x0046:
            boolean r6 = r0.applyEmbeddedStyles
            if (r6 != 0) goto L_0x0051
            java.lang.CharSequence r6 = r0.cueText
            java.lang.String r6 = r6.toString()
            goto L_0x0043
        L_0x0051:
            android.text.SpannableStringBuilder r6 = new android.text.SpannableStringBuilder
            java.lang.CharSequence r8 = r0.cueText
            r6.<init>(r8)
            int r8 = r6.length()
            java.lang.Class<android.text.style.AbsoluteSizeSpan> r9 = android.text.style.AbsoluteSizeSpan.class
            java.lang.Object[] r9 = r6.getSpans(r15, r8, r9)
            android.text.style.AbsoluteSizeSpan[] r9 = (android.text.style.AbsoluteSizeSpan[]) r9
            java.lang.Class<android.text.style.RelativeSizeSpan> r10 = android.text.style.RelativeSizeSpan.class
            java.lang.Object[] r8 = r6.getSpans(r15, r8, r10)
            android.text.style.RelativeSizeSpan[] r8 = (android.text.style.RelativeSizeSpan[]) r8
            int r10 = r9.length
            r11 = 0
        L_0x006e:
            if (r11 >= r10) goto L_0x0078
            r12 = r9[r11]
            r6.removeSpan(r12)
            int r11 = r11 + 1
            goto L_0x006e
        L_0x0078:
            int r9 = r8.length
            r10 = 0
        L_0x007a:
            if (r10 >= r9) goto L_0x0043
            r11 = r8[r10]
            r6.removeSpan(r11)
            int r10 = r10 + 1
            goto L_0x007a
        L_0x0084:
            android.text.Layout$Alignment r6 = r0.cueTextAlignment
            if (r6 != 0) goto L_0x008d
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_CENTER
        L_0x008a:
            r20 = r6
            goto L_0x0090
        L_0x008d:
            android.text.Layout$Alignment r6 = r0.cueTextAlignment
            goto L_0x008a
        L_0x0090:
            android.text.StaticLayout r6 = new android.text.StaticLayout
            android.text.TextPaint r10 = r0.textPaint
            float r13 = r0.spacingMult
            float r14 = r0.spacingAdd
            r16 = 1
            r8 = r6
            r9 = r17
            r11 = r5
            r12 = r20
            r15 = r16
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            r0.textLayout = r6
            android.text.StaticLayout r6 = r0.textLayout
            int r6 = r6.getHeight()
            android.text.StaticLayout r8 = r0.textLayout
            int r8 = r8.getLineCount()
            r9 = 0
            r10 = 0
        L_0x00b5:
            if (r9 >= r8) goto L_0x00ca
            android.text.StaticLayout r11 = r0.textLayout
            float r11 = r11.getLineWidth(r9)
            double r11 = (double) r11
            double r11 = java.lang.Math.ceil(r11)
            int r11 = (int) r11
            int r10 = java.lang.Math.max(r11, r10)
            int r9 = r9 + 1
            goto L_0x00b5
        L_0x00ca:
            float r8 = r0.cueSize
            int r8 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r8 == 0) goto L_0x00d3
            if (r10 >= r5) goto L_0x00d3
            goto L_0x00d4
        L_0x00d3:
            r5 = r10
        L_0x00d4:
            int r5 = r5 + r4
            float r4 = r0.cuePosition
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            r8 = 1
            r9 = 2
            if (r4 == 0) goto L_0x0105
            float r1 = (float) r1
            float r4 = r0.cuePosition
            float r1 = r1 * r4
            int r1 = java.lang.Math.round(r1)
            int r4 = r0.parentLeft
            int r1 = r1 + r4
            int r4 = r0.cuePositionAnchor
            if (r4 != r9) goto L_0x00ef
            int r1 = r1 - r5
            goto L_0x00f7
        L_0x00ef:
            int r4 = r0.cuePositionAnchor
            if (r4 != r8) goto L_0x00f7
            int r1 = r1 * 2
            int r1 = r1 - r5
            int r1 = r1 / r9
        L_0x00f7:
            int r4 = r0.parentLeft
            int r1 = java.lang.Math.max(r1, r4)
            int r5 = r5 + r1
            int r4 = r0.parentRight
            int r4 = java.lang.Math.min(r5, r4)
            goto L_0x0109
        L_0x0105:
            int r1 = r1 - r5
            int r1 = r1 / r9
            int r4 = r1 + r5
        L_0x0109:
            int r19 = r4 - r1
            if (r19 > 0) goto L_0x0115
            java.lang.String r1 = "SubtitlePainter"
            java.lang.String r2 = "Skipped drawing subtitle cue (invalid horizontal positioning)"
            android.util.Log.w(r1, r2)
            return
        L_0x0115:
            float r4 = r0.cueLine
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x017c
            int r4 = r0.cueLineType
            if (r4 != 0) goto L_0x012c
            float r2 = (float) r2
            float r4 = r0.cueLine
            float r2 = r2 * r4
            int r2 = java.lang.Math.round(r2)
            int r4 = r0.parentTop
            int r2 = r2 + r4
            goto L_0x015d
        L_0x012c:
            android.text.StaticLayout r2 = r0.textLayout
            r4 = 0
            int r2 = r2.getLineBottom(r4)
            android.text.StaticLayout r5 = r0.textLayout
            int r4 = r5.getLineTop(r4)
            int r2 = r2 - r4
            float r4 = r0.cueLine
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 < 0) goto L_0x014e
            float r4 = r0.cueLine
            float r2 = (float) r2
            float r4 = r4 * r2
            int r2 = java.lang.Math.round(r4)
            int r4 = r0.parentTop
            int r2 = r2 + r4
            goto L_0x015d
        L_0x014e:
            float r4 = r0.cueLine
            r5 = 1065353216(0x3f800000, float:1.0)
            float r4 = r4 + r5
            float r2 = (float) r2
            float r4 = r4 * r2
            int r2 = java.lang.Math.round(r4)
            int r4 = r0.parentBottom
            int r2 = r2 + r4
        L_0x015d:
            int r4 = r0.cueLineAnchor
            if (r4 != r9) goto L_0x0163
            int r2 = r2 - r6
            goto L_0x016b
        L_0x0163:
            int r4 = r0.cueLineAnchor
            if (r4 != r8) goto L_0x016b
            int r2 = r2 * 2
            int r2 = r2 - r6
            int r2 = r2 / r9
        L_0x016b:
            int r4 = r2 + r6
            int r5 = r0.parentBottom
            if (r4 <= r5) goto L_0x0175
            int r2 = r0.parentBottom
            int r2 = r2 - r6
            goto L_0x0187
        L_0x0175:
            int r4 = r0.parentTop
            if (r2 >= r4) goto L_0x0187
            int r2 = r0.parentTop
            goto L_0x0187
        L_0x017c:
            int r4 = r0.parentBottom
            int r4 = r4 - r6
            float r2 = (float) r2
            float r5 = r0.bottomPaddingFraction
            float r2 = r2 * r5
            int r2 = (int) r2
            int r2 = r4 - r2
        L_0x0187:
            android.text.StaticLayout r4 = new android.text.StaticLayout
            android.text.TextPaint r5 = r0.textPaint
            float r6 = r0.spacingMult
            float r7 = r0.spacingAdd
            r23 = 1
            r16 = r4
            r18 = r5
            r21 = r6
            r22 = r7
            r16.<init>(r17, r18, r19, r20, r21, r22, r23)
            r0.textLayout = r4
            r0.textLeft = r1
            r0.textTop = r2
            r0.textPaddingX = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupTextLayout():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupBitmapLayout() {
        /*
            r7 = this;
            int r0 = r7.parentRight
            int r1 = r7.parentLeft
            int r0 = r0 - r1
            int r1 = r7.parentBottom
            int r2 = r7.parentTop
            int r1 = r1 - r2
            int r2 = r7.parentLeft
            float r2 = (float) r2
            float r0 = (float) r0
            float r3 = r7.cuePosition
            float r3 = r3 * r0
            float r2 = r2 + r3
            int r3 = r7.parentTop
            float r3 = (float) r3
            float r1 = (float) r1
            float r4 = r7.cueLine
            float r4 = r4 * r1
            float r3 = r3 + r4
            float r4 = r7.cueSize
            float r0 = r0 * r4
            int r0 = java.lang.Math.round(r0)
            float r4 = r7.cueBitmapHeight
            r5 = 1
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x0034
            float r4 = r7.cueBitmapHeight
            float r1 = r1 * r4
            int r1 = java.lang.Math.round(r1)
            goto L_0x004a
        L_0x0034:
            float r1 = (float) r0
            android.graphics.Bitmap r4 = r7.cueBitmap
            int r4 = r4.getHeight()
            float r4 = (float) r4
            android.graphics.Bitmap r5 = r7.cueBitmap
            int r5 = r5.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            float r1 = r1 * r4
            int r1 = java.lang.Math.round(r1)
        L_0x004a:
            int r4 = r7.cueLineAnchor
            r5 = 1
            r6 = 2
            if (r4 != r6) goto L_0x0053
            float r4 = (float) r0
        L_0x0051:
            float r2 = r2 - r4
            goto L_0x005b
        L_0x0053:
            int r4 = r7.cueLineAnchor
            if (r4 != r5) goto L_0x005b
            int r4 = r0 / 2
            float r4 = (float) r4
            goto L_0x0051
        L_0x005b:
            int r2 = java.lang.Math.round(r2)
            int r4 = r7.cuePositionAnchor
            if (r4 != r6) goto L_0x0066
            float r4 = (float) r1
        L_0x0064:
            float r3 = r3 - r4
            goto L_0x006e
        L_0x0066:
            int r4 = r7.cuePositionAnchor
            if (r4 != r5) goto L_0x006e
            int r4 = r1 / 2
            float r4 = (float) r4
            goto L_0x0064
        L_0x006e:
            int r3 = java.lang.Math.round(r3)
            android.graphics.Rect r4 = new android.graphics.Rect
            int r0 = r0 + r2
            int r1 = r1 + r3
            r4.<init>(r2, r3, r0, r1)
            r7.bitmapRect = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
        } else {
            drawBitmapLayout(canvas);
        }
    }

    private void drawTextLayout(Canvas canvas) {
        int i;
        StaticLayout staticLayout = this.textLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            canvas.translate((float) this.textLeft, (float) this.textTop);
            if (Color.alpha(this.windowColor) > 0) {
                this.paint.setColor(this.windowColor);
                canvas.drawRect((float) (-this.textPaddingX), 0.0f, (float) (staticLayout.getWidth() + this.textPaddingX), (float) staticLayout.getHeight(), this.paint);
            }
            if (Color.alpha(this.backgroundColor) > 0) {
                this.paint.setColor(this.backgroundColor);
                float lineTop = (float) staticLayout.getLineTop(0);
                int lineCount = staticLayout.getLineCount();
                float f = lineTop;
                for (int i2 = 0; i2 < lineCount; i2++) {
                    this.lineBounds.left = staticLayout.getLineLeft(i2) - ((float) this.textPaddingX);
                    this.lineBounds.right = staticLayout.getLineRight(i2) + ((float) this.textPaddingX);
                    this.lineBounds.top = f;
                    this.lineBounds.bottom = (float) staticLayout.getLineBottom(i2);
                    f = this.lineBounds.bottom;
                    canvas.drawRoundRect(this.lineBounds, this.cornerRadius, this.cornerRadius, this.paint);
                }
            }
            boolean z = true;
            if (this.edgeType == 1) {
                this.textPaint.setStrokeJoin(Join.ROUND);
                this.textPaint.setStrokeWidth(this.outlineWidth);
                this.textPaint.setColor(this.edgeColor);
                this.textPaint.setStyle(Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (this.edgeType == 2) {
                this.textPaint.setShadowLayer(this.shadowRadius, this.shadowOffset, this.shadowOffset, this.edgeColor);
            } else if (this.edgeType == 3 || this.edgeType == 4) {
                if (this.edgeType != 3) {
                    z = false;
                }
                int i3 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.edgeColor;
                }
                if (z) {
                    i3 = this.edgeColor;
                }
                float f2 = this.shadowRadius / 2.0f;
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Style.FILL);
                float f3 = -f2;
                this.textPaint.setShadowLayer(this.shadowRadius, f3, f3, i);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(this.shadowRadius, f2, f2, i3);
            }
            this.textPaint.setColor(this.foregroundColor);
            this.textPaint.setStyle(Style.FILL);
            staticLayout.draw(canvas);
            this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, null, this.bitmapRect, null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
