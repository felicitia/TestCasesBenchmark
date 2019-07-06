package com.etsy.android.uikit.viewholder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.util.l;

public class ItemDividerDecoration extends ItemDecoration {
    private static a sDefaultConfiguration = new a() {
        public boolean b(int i, int i2) {
            return true;
        }

        public Alignment a(int i, int i2) {
            return Alignment.ALIGN_PARENT;
        }
    };
    private final int mAlpha;
    private int mColor;
    private a mConfiguration;
    private final Drawable mDrawable;
    private int mGridDividerPadding;
    private int mWidth;

    public enum Alignment {
        ALIGN_CHILD,
        ALIGN_PARENT,
        ALIGN_SCREEN
    }

    public static abstract class a {
        public abstract Alignment a(int i, int i2);

        public abstract boolean b(int i, int i2);
    }

    public ItemDividerDecoration(Drawable drawable, a aVar) {
        this.mColor = 0;
        this.mDrawable = drawable;
        this.mAlpha = DrawableCompat.getAlpha(this.mDrawable);
        this.mConfiguration = aVar;
        this.mGridDividerPadding = EtsyApplication.get().getResources().getDimensionPixelSize(f.sk_space_4);
    }

    public ItemDividerDecoration(Drawable drawable) {
        this(drawable, sDefaultConfiguration);
    }

    public ItemDividerDecoration(Context context) {
        this(context, sDefaultConfiguration);
    }

    public ItemDividerDecoration(Context context, a aVar) {
        this(ActivityCompat.getDrawable(context, g.list_divider), aVar);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        if (this.mColor != 0) {
            canvas.save();
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            canvas.clipRect(new Rect(0, recyclerView.getChildAt(0).getTop() + ((int) recyclerView.getChildAt(0).getTranslationY()), canvas.getWidth(), layoutManager.getChildAt(layoutManager.getChildCount() - 1).getBottom()));
            canvas.drawColor(this.mColor);
            canvas.restore();
        }
    }

    public void setBackgroundColor(@ColorInt int i) {
        this.mColor = i;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Canvas canvas2 = canvas;
        RecyclerView recyclerView2 = recyclerView;
        if (this.mWidth == 0) {
            this.mWidth = new l(recyclerView.getContext()).d();
        }
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i7 = this.mWidth;
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        GridLayoutManager gridLayoutManager = null;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            i = gridLayoutManager.getSpanCount();
        } else {
            i = -1;
        }
        int childCount = recyclerView.getChildCount();
        int i8 = 0;
        int i9 = 0;
        while (i8 < childCount) {
            View childAt = recyclerView2.getChildAt(i8);
            int itemViewType = recyclerView2.getChildViewHolder(childAt).getItemViewType();
            int childAdapterPosition = recyclerView2.getChildAdapterPosition(childAt);
            if (!this.mConfiguration.b(itemViewType, childAdapterPosition)) {
                i4 = i7;
                i3 = paddingLeft;
                i2 = width;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                switch (this.mConfiguration.a(itemViewType, childAdapterPosition)) {
                    case ALIGN_CHILD:
                        i6 = childAt.getLeft() + layoutManager.getLeftDecorationWidth(childAt);
                        i5 = childAt.getRight() + layoutManager.getRightDecorationWidth(childAt);
                        break;
                    case ALIGN_SCREEN:
                        i5 = i7;
                        i6 = 0;
                        break;
                    default:
                        i6 = paddingLeft;
                        i5 = width;
                        break;
                }
                i4 = i7;
                int bottom = childAt.getBottom() + layoutParams.bottomMargin + layoutManager.getBottomDecorationHeight(childAt) + ((int) childAt.getTranslationY());
                i3 = paddingLeft;
                int intrinsicHeight = this.mDrawable.getIntrinsicHeight() + bottom;
                if (gridLayoutManager != null) {
                    i2 = width;
                    i6 += this.mGridDividerPadding;
                    i5 -= this.mGridDividerPadding;
                } else {
                    i2 = width;
                }
                this.mDrawable.setBounds(i6, bottom, i5, intrinsicHeight);
                this.mDrawable.setAlpha((int) (((float) this.mAlpha) * childAt.getAlpha()));
                this.mDrawable.draw(canvas2);
                if (gridLayoutManager != null) {
                    if (childAdapterPosition != -1) {
                        int max = Math.max(this.mDrawable.getIntrinsicWidth() / 2, 1);
                        i9 += gridLayoutManager.getSpanSizeLookup().getSpanSize(childAdapterPosition);
                        if (i9 % i != 0) {
                            this.mDrawable.setBounds(childAt.getRight() - max, childAt.getTop() + layoutParams.topMargin + layoutManager.getTopDecorationHeight(childAt) + ((int) childAt.getTranslationY()) + this.mGridDividerPadding, childAt.getRight() + max, bottom - this.mGridDividerPadding);
                            this.mDrawable.setAlpha((int) (((float) this.mAlpha) * childAt.getAlpha()));
                            this.mDrawable.draw(canvas2);
                        }
                    }
                    i8++;
                    i7 = i4;
                    paddingLeft = i3;
                    width = i2;
                    recyclerView2 = recyclerView;
                }
            }
            i8++;
            i7 = i4;
            paddingLeft = i3;
            width = i2;
            recyclerView2 = recyclerView;
        }
    }
}
