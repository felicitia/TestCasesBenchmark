package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import java.util.ArrayList;
import java.util.List;

public final class SubtitleView extends View implements TextOutput {
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private float bottomPaddingFraction;
    private List<Cue> cues;
    private final List<SubtitlePainter> painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    public SubtitleView(Context context) {
        this(context, null);
    }

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.applyEmbeddedStyles = true;
        this.applyEmbeddedFontSizes = true;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    public void onCues(List<Cue> list) {
        setCues(list);
    }

    public void setCues(List<Cue> list) {
        int i;
        if (this.cues != list) {
            this.cues = list;
            if (list == null) {
                i = 0;
            } else {
                i = list.size();
            }
            while (this.painters.size() < i) {
                this.painters.add(new SubtitlePainter(getContext()));
            }
            invalidate();
        }
    }

    public void setFractionalTextSize(float f) {
        setFractionalTextSize(f, false);
    }

    public void setFractionalTextSize(float f, boolean z) {
        setTextSize(z ? 1 : 0, f);
    }

    private void setTextSize(int i, float f) {
        if (this.textSizeType != i || this.textSize != f) {
            this.textSizeType = i;
            this.textSize = f;
            invalidate();
        }
    }

    public void setApplyEmbeddedStyles(boolean z) {
        if (this.applyEmbeddedStyles != z || this.applyEmbeddedFontSizes != z) {
            this.applyEmbeddedStyles = z;
            this.applyEmbeddedFontSizes = z;
            invalidate();
        }
    }

    public void setApplyEmbeddedFontSizes(boolean z) {
        if (this.applyEmbeddedFontSizes != z) {
            this.applyEmbeddedFontSizes = z;
            invalidate();
        }
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style != captionStyleCompat) {
            this.style = captionStyleCompat;
            invalidate();
        }
    }

    public void setBottomPaddingFraction(float f) {
        if (this.bottomPaddingFraction != f) {
            this.bottomPaddingFraction = f;
            invalidate();
        }
    }

    public void dispatchDraw(Canvas canvas) {
        float f;
        int i = 0;
        int size = this.cues == null ? 0 : this.cues.size();
        int top = getTop();
        int bottom = getBottom();
        int left = getLeft() + getPaddingLeft();
        int paddingTop = getPaddingTop() + top;
        int right = getRight() + getPaddingRight();
        int paddingBottom = bottom - getPaddingBottom();
        if (paddingBottom > paddingTop && right > left) {
            if (this.textSizeType == 2) {
                f = this.textSize;
            } else {
                f = this.textSize * ((float) (this.textSizeType == 0 ? paddingBottom - paddingTop : bottom - top));
            }
            if (f > 0.0f) {
                while (i < size) {
                    int i2 = paddingBottom;
                    int i3 = right;
                    ((SubtitlePainter) this.painters.get(i)).draw((Cue) this.cues.get(i), this.applyEmbeddedStyles, this.applyEmbeddedFontSizes, this.style, f, this.bottomPaddingFraction, canvas, left, paddingTop, i3, i2);
                    i++;
                    paddingBottom = i2;
                    right = i3;
                }
            }
        }
    }

    @TargetApi(19)
    private float getUserCaptionFontScaleV19() {
        return ((CaptioningManager) getContext().getSystemService("captioning")).getFontScale();
    }

    @TargetApi(19)
    private CaptionStyleCompat getUserCaptionStyleV19() {
        return CaptionStyleCompat.createFromCaptionStyle(((CaptioningManager) getContext().getSystemService("captioning")).getUserStyle());
    }
}
