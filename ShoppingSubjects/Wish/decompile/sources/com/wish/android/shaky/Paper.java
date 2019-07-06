package com.wish.android.shaky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import java.util.ArrayList;
import java.util.List;

public class Paper extends AppCompatImageView {
    private Path currentBrush;
    private List<PathEvent> pathEvents = new ArrayList();
    private Paint thickPaint = new Paint();
    private Path thickPath = new Path();
    private Paint thinPaint = new Paint();
    private Path thinPath = new Path();

    private static class PaperSavedState extends BaseSavedState {
        public static final Creator<PaperSavedState> CREATOR = new Creator<PaperSavedState>() {
            public PaperSavedState createFromParcel(Parcel parcel) {
                return new PaperSavedState(parcel);
            }

            public PaperSavedState[] newArray(int i) {
                return new PaperSavedState[i];
            }
        };
        List<PathEvent> pathEvents;

        public PaperSavedState(Parcel parcel) {
            super(parcel);
            int readInt = parcel.readInt();
            this.pathEvents = new ArrayList(readInt);
            for (int i = 0; i < readInt; i++) {
                List<PathEvent> list = this.pathEvents;
                PathEvent pathEvent = new PathEvent(parcel.readInt() == 0, parcel.readInt() == 0, parcel.readFloat(), parcel.readFloat());
                list.add(pathEvent);
            }
        }

        public PaperSavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.pathEvents.size());
            for (PathEvent pathEvent : this.pathEvents) {
                parcel.writeInt(pathEvent.isThinPath ^ true ? 1 : 0);
                parcel.writeInt(pathEvent.isMove ^ true ? 1 : 0);
                parcel.writeFloat(pathEvent.x);
                parcel.writeFloat(pathEvent.y);
            }
        }
    }

    private static class PathEvent {
        public final boolean isMove;
        public final boolean isThinPath;
        public final float x;
        public final float y;

        private PathEvent(boolean z, boolean z2, float f, float f2) {
            this.isThinPath = z;
            this.isMove = z2;
            this.x = f;
            this.y = f2;
        }
    }

    public Paper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.thinPaint.setColor(-65536);
        this.thinPaint.setAntiAlias(true);
        this.thinPaint.setStrokeWidth(12.0f);
        this.thinPaint.setStyle(Style.STROKE);
        this.thinPaint.setStrokeJoin(Join.ROUND);
        this.thinPaint.setPathEffect(new CornerPathEffect(50.0f));
        this.thickPaint = new Paint(this.thinPaint);
        this.thickPaint.setStrokeWidth(48.0f);
        this.thickPaint.setColor(-1);
        this.currentBrush = this.thinPath;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        PaperSavedState paperSavedState = new PaperSavedState(super.onSaveInstanceState());
        paperSavedState.pathEvents = this.pathEvents;
        return paperSavedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        PaperSavedState paperSavedState = (PaperSavedState) parcelable;
        super.onRestoreInstanceState(paperSavedState.getSuperState());
        this.pathEvents = paperSavedState.pathEvents;
        applyEvents();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.thickPath, this.thickPaint);
        canvas.drawPath(this.thinPath, this.thinPaint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.currentBrush.moveTo(x, y);
            List<PathEvent> list = this.pathEvents;
            PathEvent pathEvent = new PathEvent(isThinBrush(), true, x, y);
            list.add(pathEvent);
        } else if (action != 2) {
            return false;
        } else {
            this.currentBrush.lineTo(x, y);
            List<PathEvent> list2 = this.pathEvents;
            PathEvent pathEvent2 = new PathEvent(isThinBrush(), false, x, y);
            list2.add(pathEvent2);
        }
        invalidate();
        return true;
    }

    public void clear() {
        this.thinPath.reset();
        this.thickPath.reset();
        this.pathEvents.clear();
        invalidate();
    }

    public void toggleBrush() {
        this.currentBrush = this.currentBrush == this.thinPath ? this.thickPath : this.thinPath;
    }

    public boolean isThinBrush() {
        return this.currentBrush == this.thinPath;
    }

    public void undo() {
        int size = this.pathEvents.size() - 1;
        while (size >= 0 && !((PathEvent) this.pathEvents.remove(size)).isMove) {
            size--;
        }
        applyEvents();
        invalidate();
    }

    public Bitmap capture() {
        return Utils.capture(this);
    }

    private void applyEvents() {
        this.thickPath.reset();
        this.thinPath.reset();
        for (PathEvent pathEvent : this.pathEvents) {
            Path path = pathEvent.isThinPath ? this.thinPath : this.thickPath;
            if (pathEvent.isMove) {
                path.moveTo(pathEvent.x, pathEvent.y);
            } else {
                path.lineTo(pathEvent.x, pathEvent.y);
            }
        }
    }
}
