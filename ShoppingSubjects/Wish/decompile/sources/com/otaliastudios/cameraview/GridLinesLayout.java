package com.otaliastudios.cameraview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

class GridLinesLayout extends View {
    Task<Integer> drawTask;
    private Grid gridMode;
    private Drawable horiz;
    private Drawable vert;
    private final float width;

    public GridLinesLayout(Context context) {
        this(context, null);
    }

    public GridLinesLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawTask = new Task<>();
        this.horiz = new ColorDrawable(-1);
        this.horiz.setAlpha(160);
        this.vert = new ColorDrawable(-1);
        this.vert.setAlpha(160);
        this.width = TypedValue.applyDimension(1, 0.9f, context.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.horiz.setBounds(i, 0, i3, (int) this.width);
        this.vert.setBounds(0, i2, (int) this.width, i4);
    }

    public Grid getGridMode() {
        return this.gridMode;
    }

    public void setGridMode(Grid grid) {
        this.gridMode = grid;
        postInvalidate();
    }

    private int getLineCount() {
        switch (this.gridMode) {
            case OFF:
                return 0;
            case DRAW_3X3:
                return 2;
            case DRAW_PHI:
                return 2;
            case DRAW_4X4:
                return 3;
            default:
                return 0;
        }
    }

    private float getLinePosition(int i) {
        int lineCount = getLineCount();
        if (this.gridMode != Grid.DRAW_PHI) {
            return (1.0f / ((float) (lineCount + 1))) * (((float) i) + 1.0f);
        }
        float f = 0.38196602f;
        if (i != 1) {
            f = 0.618034f;
        }
        return f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.drawTask.start();
        int lineCount = getLineCount();
        for (int i = 0; i < lineCount; i++) {
            float linePosition = getLinePosition(i);
            canvas.translate(0.0f, ((float) getHeight()) * linePosition);
            this.horiz.draw(canvas);
            float f = -linePosition;
            canvas.translate(0.0f, ((float) getHeight()) * f);
            canvas.translate(linePosition * ((float) getWidth()), 0.0f);
            this.vert.draw(canvas);
            canvas.translate(f * ((float) getWidth()), 0.0f);
        }
        this.drawTask.end(Integer.valueOf(lineCount));
    }
}
