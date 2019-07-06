package com.etsy.android.uikit.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class TaggableImageView extends FrameLayout {
    private static final int ANIMATION_CREATE_TAG_DELAY = 800;
    private static final int ANIMATION_CREATE_TAG_DURATION = 400;
    private static final float ANIMATION_CREATE_TAG_OVERSHOOT_TENSION = 1.5f;
    private static final int ANIMATION_CREATE_TAG_TRANSLATE_Y = -50;
    private static final int ANIMATION_DELETE_TAG_DURATION = 250;
    private static final int ANIMATION_DELETE_TAG_TRANSLATE_Y = 50;
    private static final float ANIMATION_SCALE_TAG = 1.3f;
    private static final int ANIMATION_SCALE_TAG_DURATION = 300;
    private static final float ANIMATION_SCALE_TAG_OVERSHOOT_TENSION = 1.5f;
    /* access modifiers changed from: private */
    public static final String TAG = f.a(TaggableImageView.class);
    /* access modifiers changed from: private */
    public TaggableAdapter mAdapter;
    /* access modifiers changed from: private */
    public RelativeLayout mContainer;
    /* access modifiers changed from: private */
    public ViewDragHelper mDragHelper;
    private boolean mEditable = false;
    private ImageViewWithOnDrawListener mImage;
    private boolean mIsTagAnimating = false;
    private boolean mIsTagDragging = false;
    /* access modifiers changed from: private */
    public e mListener;
    private DataSetObserver mObserver;

    public static class ImageViewWithOnDrawListener extends FullImageView {
        private a mOnDrawListener;

        interface a {
            void a();
        }

        public ImageViewWithOnDrawListener(Context context) {
            super(context);
        }

        public ImageViewWithOnDrawListener(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public ImageViewWithOnDrawListener(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public void setOnDrawListener(a aVar) {
            this.mOnDrawListener = aVar;
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.mOnDrawListener != null) {
                this.mOnDrawListener.a();
            }
        }
    }

    public interface TaggableAdapter<T extends d> extends Adapter {

        public interface a {
            void a();

            void a(long j);

            void b(long j);
        }

        int getTagHeight();

        int getTagWidth();

        void onEndDrag(ImageView imageView);

        void onStartDrag(ImageView imageView);

        void setListener(a aVar);
    }

    private class a implements AnimatorListener {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        private a() {
        }

        public void onAnimationStart(Animator animator) {
            TaggableImageView.this.setIsTagAnimating(true);
            TaggableImageView.this.mListener.a();
        }

        public void onAnimationEnd(Animator animator) {
            TaggableImageView.this.setIsTagAnimating(false);
            TaggableImageView.this.updateLayout();
            TaggableImageView.this.mListener.b();
        }
    }

    private class b extends Callback {
        public boolean tryCaptureView(View view, int i) {
            return true;
        }

        private b() {
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            view.setX((float) i);
            view.setY((float) i2);
        }

        public void onViewCaptured(View view, int i) {
            view.setPressed(true);
            if (!TaggableImageView.this.isReadOnly()) {
                TaggableImageView.this.setIsTagDragging(true);
                TaggableImageView.this.mAdapter.onStartDrag((ImageView) view);
                view.animate().setDuration(300).scaleX(TaggableImageView.ANIMATION_SCALE_TAG).scaleY(TaggableImageView.ANIMATION_SCALE_TAG).setListener(new a()).start();
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            view.setLayoutParams(TaggableImageView.this.getLayoutParamsAt((int) view.getX(), (int) view.getY()));
            view.setPressed(false);
            if (!TaggableImageView.this.isReadOnly()) {
                TaggableImageView.this.mAdapter.onEndDrag((ImageView) view);
                view.animate().setDuration(300).setInterpolator(new OvershootInterpolator(1.5f)).scaleX(1.0f).scaleY(1.0f).setListener(new a()).start();
                TaggableImageView.this.setIsTagDragging(false);
                if (TaggableImageView.this.mListener != null) {
                    TaggableImageView.this.mListener.a(view, (d) TaggableImageView.this.mAdapter.getItem(TaggableImageView.this.mContainer.indexOfChild(view)), view.getLeft(), view.getTop());
                }
            }
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return TaggableImageView.this.getConstrainedTagXCoordinate(view.getWidth(), i);
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return TaggableImageView.this.getConstrainedTagYCoordinate(view.getHeight(), i);
        }
    }

    private class c extends RelativeLayout {
        public c(Context context) {
            super(context);
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.gravity = 49;
            setLayoutParams(layoutParams);
            setOnClickListener(new OnClickListener(TaggableImageView.this) {
                public void onClick(View view) {
                    if (TaggableImageView.this.mListener != null) {
                        TaggableImageView.this.mListener.c();
                    }
                }
            });
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return TaggableImageView.this.isEditable() && TaggableImageView.this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            try {
                TaggableImageView.this.mDragHelper.processTouchEvent(motionEvent);
            } catch (IllegalArgumentException e) {
                f.c(TaggableImageView.TAG, "exception in touch event of TaggableImageView", (Throwable) e);
            }
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (!TaggableImageView.this.isReadOnly()) {
                if (!TaggableImageView.this.mDragHelper.isCapturedViewUnder(x, y) && motionEvent.getAction() == 0 && TaggableImageView.this.mListener != null) {
                    TaggableImageView.this.mListener.a(TaggableImageView.this.getConstrainedTagXCoordinate(TaggableImageView.this.mAdapter.getTagWidth(), x), TaggableImageView.this.getConstrainedTagYCoordinate(TaggableImageView.this.mAdapter.getTagHeight(), y));
                }
                return true;
            } else if (!TaggableImageView.this.mDragHelper.isCapturedViewUnder(x, y)) {
                return super.onTouchEvent(motionEvent);
            } else {
                return false;
            }
        }
    }

    public interface d {
        int a();

        int b();

        long c();
    }

    public interface e {
        void a();

        void a(int i, int i2);

        void a(View view, d dVar, int i, int i2);

        void a(d dVar);

        void b();

        void c();
    }

    public TaggableImageView(Context context) {
        super(context);
        init(null, 0);
    }

    public TaggableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public TaggableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        inflate(getContext(), k.view_taggable_image_view, this);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.TaggableImageView);
        this.mEditable = obtainStyledAttributes.getBoolean(q.TaggableImageView_editable, false);
        obtainStyledAttributes.recycle();
        this.mObserver = new DataSetObserver() {
            public void onChanged() {
                TaggableImageView.this.updateLayout();
            }

            public void onInvalidated() {
                TaggableImageView.this.updateLayout();
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mImage = (ImageViewWithOnDrawListener) findViewById(i.taggable_image);
        this.mImage.setOnDrawListener(new a() {
            public void a() {
                TaggableImageView.this.updateLayout();
            }
        });
        this.mContainer = new c(getContext());
        addView(this.mContainer);
        this.mDragHelper = ViewDragHelper.create(this.mContainer, new b());
    }

    public void setImageResource(int i) {
        this.mImage.setImageResource(i);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mImage.setImageBitmap(bitmap);
    }

    public FullImageView getImageView() {
        return this.mImage;
    }

    public TaggableAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(TaggableAdapter taggableAdapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = taggableAdapter;
        this.mAdapter.registerDataSetObserver(this.mObserver);
        this.mAdapter.setListener(new a() {
            public void a() {
                TaggableImageView.this.mContainer.setVisibility(8);
            }

            public void a(long j) {
                TaggableImageView.this.animateTagCreation(j);
            }

            public void b(long j) {
                TaggableImageView.this.animateTagDeletion(j);
            }
        });
        updateLayout();
    }

    public e getListener() {
        return this.mListener;
    }

    public void setListener(e eVar) {
        this.mListener = eVar;
    }

    public boolean isReadOnly() {
        return !isEditable();
    }

    public boolean isEditable() {
        return this.mEditable;
    }

    public boolean isTagAnimating() {
        return this.mIsTagAnimating;
    }

    public boolean isTagDragging() {
        return this.mIsTagDragging;
    }

    public void setEditable(boolean z) {
        this.mEditable = z;
    }

    /* access modifiers changed from: private */
    public void setIsTagAnimating(boolean z) {
        this.mIsTagAnimating = z;
    }

    /* access modifiers changed from: private */
    public void setIsTagDragging(boolean z) {
        this.mIsTagDragging = z;
    }

    /* access modifiers changed from: private */
    public void updateLayout() {
        if (!isTagAnimating() && !isTagDragging()) {
            this.mContainer.removeAllViews();
            if (this.mAdapter != null) {
                int i = 0;
                while (i < this.mAdapter.getCount()) {
                    d dVar = (d) this.mAdapter.getItem(i);
                    if (dVar != null) {
                        View view = this.mAdapter.getView(i, null, this);
                        view.setLayoutParams(getLayoutParamsAt(dVar.a(), dVar.b()));
                        if (isReadOnly()) {
                            view.setOnClickListener(new TrackingOnClickListener() {
                                public void onViewClick(View view) {
                                    if (TaggableImageView.this.mListener != null) {
                                        TaggableImageView.this.mListener.a((d) TaggableImageView.this.mAdapter.getItem(TaggableImageView.this.mContainer.indexOfChild(view)));
                                    }
                                }
                            });
                        }
                        this.mContainer.addView(view, i);
                        i++;
                    } else {
                        return;
                    }
                }
                invalidate();
                requestLayout();
            }
        }
    }

    /* access modifiers changed from: private */
    public void animateTagCreation(final long j) {
        this.mImage.postDelayed(new Runnable() {
            public void run() {
                View access$700 = TaggableImageView.this.getTagViewByTagId(j);
                if (access$700 != null) {
                    access$700.setTranslationY(-50.0f);
                    TaggableImageView.this.mContainer.setVisibility(0);
                    access$700.animate().setDuration(400).setInterpolator(new OvershootInterpolator(1.5f)).translationY(0.0f).setListener(new a()).start();
                }
            }
        }, 800);
    }

    /* access modifiers changed from: private */
    public void animateTagDeletion(long j) {
        View tagViewByTagId = getTagViewByTagId(j);
        if (tagViewByTagId != null) {
            tagViewByTagId.animate().setDuration(250).translationY(50.0f).alpha(0.0f).setListener(new a()).start();
        }
    }

    /* access modifiers changed from: private */
    public View getTagViewByTagId(long j) {
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            if (((d) this.mAdapter.getItem(i)).c() == j) {
                return this.mContainer.getChildAt(i);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public int getConstrainedTagXCoordinate(int i, int i2) {
        return Math.min(Math.max(0, i2), this.mContainer.getMeasuredWidth() - i);
    }

    /* access modifiers changed from: private */
    public int getConstrainedTagYCoordinate(int i, int i2) {
        return Math.min(Math.max(0, i2), this.mContainer.getMeasuredHeight() - i);
    }

    /* access modifiers changed from: private */
    public ViewGroup.LayoutParams getLayoutParamsAt(int i, int i2) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(i, i2, 0, 0);
        return layoutParams;
    }
}
