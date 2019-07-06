package com.etsy.android.uikit.listwrapper;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.ScrollDirection;
import com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.a;

@Deprecated
public class QuickReturnEndlessListViewWrapper extends ObservableEndlessListViewWrapper {
    private static final boolean DBG = false;
    private static final int STATE_OFFSCREEN = 1;
    private static final int STATE_ONSCREEN = 0;
    private static final int STATE_RETURNING_BOTTOM = 3;
    private static final int STATE_RETURNING_TOP = 2;
    private static final String TAG = f.a(QuickReturnEndlessListViewWrapper.class);
    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            QuickReturnEndlessListViewWrapper.this.runLayout();
        }
    };
    private int mFooterY;
    private int mHeaderY;
    private boolean mIsFooterPaddingAdded;
    private boolean mIsHeaderPaddingAdded;
    private View mListViewHeaderOffsetView;
    private int mMinRawYFooter = 0;
    private int mMinRawYHeader = 0;
    private a mQuickReturnCallbacks = new a() {
        public void a(int i, int i2, ScrollDirection scrollDirection) {
            if (QuickReturnEndlessListViewWrapper.this.mQuickReturnHeader != null) {
                QuickReturnEndlessListViewWrapper.this.translateHeader(i, i2, scrollDirection);
            }
            if (QuickReturnEndlessListViewWrapper.this.mQuickReturnFooter != null) {
                QuickReturnEndlessListViewWrapper.this.translateFooter(i, i2, scrollDirection);
            }
        }
    };
    /* access modifiers changed from: private */
    public View mQuickReturnFooter;
    /* access modifiers changed from: private */
    public int mQuickReturnFooterHeight;
    /* access modifiers changed from: private */
    public View mQuickReturnHeader;
    /* access modifiers changed from: private */
    public int mQuickReturnHeaderHeight;
    private int mQuickReturnHeaderOffset;
    private int mStateFooter = 0;
    private int mStateHeader = 0;
    /* access modifiers changed from: private */
    public View mViewFooterPadding;
    /* access modifiers changed from: private */
    public View mViewHeaderPadding;

    public QuickReturnEndlessListViewWrapper(ListView listView) {
        super(listView);
        LayoutInflater from = LayoutInflater.from(listView.getContext());
        this.mViewHeaderPadding = from.inflate(k.list_item_padding, null);
        this.mViewFooterPadding = from.inflate(k.list_item_padding, null);
        listView.addHeaderView(this.mViewHeaderPadding);
        this.mIsHeaderPaddingAdded = true;
        addCallbacks(this.mQuickReturnCallbacks);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.mListView.getAdapter() != null) {
            this.mListView.getAdapter().unregisterDataSetObserver(this.mDataSetObserver);
        }
        super.setAdapter(listAdapter);
        if (listAdapter != null) {
            runLayout();
            listAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
    }

    public void onDestroyView() {
        if (this.mListView.getAdapter() != null) {
            this.mListView.getAdapter().unregisterDataSetObserver(this.mDataSetObserver);
        }
    }

    /* access modifiers changed from: private */
    public void runLayout() {
        if (this.mListView.getViewTreeObserver().isAlive()) {
            this.mListView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    if (QuickReturnEndlessListViewWrapper.this.mQuickReturnHeader != null) {
                        QuickReturnEndlessListViewWrapper.this.mQuickReturnHeaderHeight = QuickReturnEndlessListViewWrapper.this.mQuickReturnHeader.getHeight();
                        QuickReturnEndlessListViewWrapper.this.setCachedVerticalScrollRangeToCalculatedHeight();
                        if (QuickReturnEndlessListViewWrapper.this.mQuickReturnHeaderHeight > 0) {
                            QuickReturnEndlessListViewWrapper.this.mViewHeaderPadding.setLayoutParams(new LayoutParams(-1, QuickReturnEndlessListViewWrapper.this.mQuickReturnHeaderHeight));
                        }
                    } else {
                        QuickReturnEndlessListViewWrapper.this.mViewHeaderPadding.setLayoutParams(new LayoutParams(-1, 0));
                    }
                    if (QuickReturnEndlessListViewWrapper.this.mQuickReturnFooter != null) {
                        QuickReturnEndlessListViewWrapper.this.mQuickReturnFooterHeight = QuickReturnEndlessListViewWrapper.this.mQuickReturnFooter.getHeight();
                        if (QuickReturnEndlessListViewWrapper.this.mQuickReturnFooterHeight > 0) {
                            QuickReturnEndlessListViewWrapper.this.mViewFooterPadding.setLayoutParams(new LayoutParams(-1, QuickReturnEndlessListViewWrapper.this.mQuickReturnFooterHeight));
                        }
                    } else {
                        QuickReturnEndlessListViewWrapper.this.mListView.removeFooterView(QuickReturnEndlessListViewWrapper.this.mViewFooterPadding);
                    }
                    QuickReturnEndlessListViewWrapper.this.computeHeaderOffset();
                    QuickReturnEndlessListViewWrapper.this.computeScrollY();
                    QuickReturnEndlessListViewWrapper.this.removeViewTree(QuickReturnEndlessListViewWrapper.this.mListView, this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void removeViewTree(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public void startEndless() {
        super.startEndless();
        if (this.mQuickReturnFooter != null && this.mIsFooterPaddingAdded) {
            this.mListView.removeFooterView(this.mViewFooterPadding);
            this.mIsFooterPaddingAdded = false;
            computeScrollY();
        }
    }

    public void stopEndless() {
        super.stopEndless();
        if (this.mQuickReturnFooter != null && !this.mIsFooterPaddingAdded) {
            this.mIsFooterPaddingAdded = true;
            this.mListView.addFooterView(this.mViewFooterPadding);
            computeScrollY();
        }
    }

    public void addHeaderViewAndOffset(View view) {
        this.mListView.addHeaderView(view);
        this.mListViewHeaderOffsetView = view;
    }

    /* access modifiers changed from: private */
    public void computeHeaderOffset() {
        if (this.mListViewHeaderOffsetView != null) {
            this.mQuickReturnHeaderOffset = this.mListViewHeaderOffsetView.getHeight();
        }
    }

    public void setQuickReturnHeader(View view) {
        this.mQuickReturnHeader = view;
    }

    public void setQuickReturnFooter(View view) {
        this.mQuickReturnFooter = view;
    }

    public void reset() {
        this.mStateFooter = 0;
        this.mMinRawYFooter = 0;
        this.mStateHeader = 0;
        this.mMinRawYHeader = 0;
        super.reset();
    }

    /* access modifiers changed from: protected */
    public int calculateMeasuredHeight(View view) {
        if (this.mIsHeaderPaddingAdded && view == this.mViewHeaderPadding) {
            return this.mQuickReturnHeaderHeight;
        }
        if (!this.mIsFooterPaddingAdded || view != this.mViewFooterPadding) {
            return super.calculateMeasuredHeight(view);
        }
        return this.mQuickReturnFooterHeight;
    }

    /* access modifiers changed from: private */
    public void translateHeader(int i, int i2, ScrollDirection scrollDirection) {
        int i3 = 0;
        int top = this.mQuickReturnHeader.getTop() - Math.min(getCachedVerticalScrollRange() - (this.mListView.getHeight() < 0 ? this.mListView.getHeight() : 0), i);
        if (top > 0) {
            top = 0;
        }
        switch (this.mStateHeader) {
            case 0:
                if (top >= (-this.mQuickReturnHeaderHeight) - this.mQuickReturnHeaderOffset) {
                    top += this.mQuickReturnHeaderOffset;
                    break;
                } else {
                    this.mStateHeader = 1;
                    this.mMinRawYHeader = top;
                    break;
                }
            case 1:
                if (top > this.mMinRawYHeader) {
                    if (i2 > this.mQuickReturnHeaderHeight) {
                        this.mStateHeader = 2;
                        break;
                    } else {
                        this.mStateHeader = 3;
                        break;
                    }
                } else {
                    this.mMinRawYHeader = top;
                    if (i2 <= this.mQuickReturnHeaderHeight) {
                        this.mStateHeader = 3;
                        break;
                    }
                }
                break;
            case 2:
                int i4 = (top - this.mMinRawYHeader) - this.mQuickReturnHeaderHeight;
                if (i2 <= this.mQuickReturnHeaderHeight && scrollDirection == ScrollDirection.DOWN) {
                    this.mStateHeader = 3;
                    i3 = i4;
                    break;
                } else {
                    if (i4 > 0) {
                        this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                        i4 = 0;
                    }
                    if (top >= 0 - this.mQuickReturnHeaderOffset) {
                        this.mStateHeader = 0;
                        i4 = this.mQuickReturnHeaderOffset + top;
                    }
                    i3 = i4;
                    if (i3 < (-this.mQuickReturnHeaderHeight)) {
                        this.mStateHeader = 1;
                        this.mMinRawYHeader = top;
                        break;
                    }
                }
                break;
            case 3:
                int i5 = (top - this.mMinRawYHeader) - this.mQuickReturnHeaderHeight;
                if (i2 <= this.mQuickReturnHeaderHeight || scrollDirection != ScrollDirection.UP) {
                    if (scrollDirection != ScrollDirection.DOWN) {
                        if (i5 <= 0 && i2 != 0) {
                            i3 = i5;
                        }
                        this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                        break;
                    } else {
                        if (i2 < this.mQuickReturnHeaderHeight) {
                            i5 = -i2;
                        }
                        if (i5 > 0 || i2 == 0) {
                            this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                            i5 = 0;
                        }
                        if (top < 0 - this.mQuickReturnHeaderOffset) {
                            i3 = i5;
                        }
                        if (i3 < (-this.mQuickReturnHeaderHeight)) {
                            this.mMinRawYHeader = top;
                            break;
                        }
                    }
                } else {
                    this.mStateHeader = 2;
                    this.mMinRawYHeader = top - this.mQuickReturnHeaderHeight;
                    break;
                }
                break;
        }
        i3 = top;
        if (this.mHeaderY != i3) {
            this.mHeaderY = i3;
            this.mQuickReturnHeader.setTranslationY((float) i3);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0085, code lost:
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008f, code lost:
        r0 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0092, code lost:
        if (r6.mFooterY == r0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0094, code lost:
        r6.mFooterY = r0;
        r6.mQuickReturnFooter.setTranslationY((float) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void translateFooter(int r7, int r8, com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.ScrollDirection r9) {
        /*
            r6 = this;
            int r0 = r6.mStateFooter
            r1 = 2
            r2 = 3
            r3 = 1
            r4 = 0
            switch(r0) {
                case 0: goto L_0x0073;
                case 1: goto L_0x005c;
                case 2: goto L_0x0034;
                case 3: goto L_0x000c;
                default: goto L_0x0009;
            }
        L_0x0009:
            r0 = r4
            goto L_0x0090
        L_0x000c:
            int r0 = r6.mMinRawYFooter
            int r0 = r7 - r0
            int r2 = r6.mQuickReturnFooterHeight
            int r0 = r0 + r2
            int r0 = java.lang.Math.min(r8, r0)
            int r2 = r6.mQuickReturnFooterHeight
            if (r8 < r2) goto L_0x001d
            r2 = r3
            goto L_0x001e
        L_0x001d:
            r2 = r4
        L_0x001e:
            com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper$ScrollDirection r5 = com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.ScrollDirection.UP
            if (r9 != r5) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r3 = r4
        L_0x0024:
            r9 = r2 & r3
            if (r9 == 0) goto L_0x002a
            r6.mStateFooter = r1
        L_0x002a:
            if (r0 < 0) goto L_0x002e
            if (r8 != 0) goto L_0x0090
        L_0x002e:
            int r8 = r6.mQuickReturnFooterHeight
            int r7 = r7 + r8
            r6.mMinRawYFooter = r7
            goto L_0x0009
        L_0x0034:
            int r0 = r6.mMinRawYFooter
            int r0 = r7 - r0
            int r1 = r6.mQuickReturnFooterHeight
            int r0 = r0 + r1
            int r1 = r6.mQuickReturnFooterHeight
            if (r8 > r1) goto L_0x0046
            com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper$ScrollDirection r8 = com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.ScrollDirection.DOWN
            if (r9 != r8) goto L_0x0046
            r6.mStateFooter = r2
            goto L_0x0090
        L_0x0046:
            if (r0 >= 0) goto L_0x004e
            int r8 = r6.mQuickReturnFooterHeight
            int r8 = r8 + r7
            r6.mMinRawYFooter = r8
            r0 = r4
        L_0x004e:
            if (r7 != 0) goto L_0x0053
            r6.mStateFooter = r4
            r0 = r4
        L_0x0053:
            int r8 = r6.mQuickReturnFooterHeight
            if (r0 <= r8) goto L_0x0090
            r6.mStateFooter = r3
            r6.mMinRawYFooter = r7
            goto L_0x0090
        L_0x005c:
            int r9 = r6.mMinRawYFooter
            if (r7 < r9) goto L_0x0069
            r6.mMinRawYFooter = r7
            int r9 = r6.mQuickReturnFooterHeight
            if (r8 > r9) goto L_0x008f
            r6.mStateFooter = r2
            goto L_0x0085
        L_0x0069:
            int r9 = r6.mQuickReturnFooterHeight
            if (r8 > r9) goto L_0x0070
            r6.mStateFooter = r2
            goto L_0x0085
        L_0x0070:
            r6.mStateFooter = r1
            goto L_0x008f
        L_0x0073:
            int r0 = r6.mQuickReturnFooterHeight
            if (r8 >= r0) goto L_0x0087
            com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper$ScrollDirection r0 = com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper.ScrollDirection.UP
            if (r9 != r0) goto L_0x0083
            int r8 = r6.mQuickReturnFooterHeight
            int r7 = r7 + r8
            r6.mMinRawYFooter = r7
            r6.mStateFooter = r1
            goto L_0x0009
        L_0x0083:
            r6.mStateFooter = r2
        L_0x0085:
            r0 = r8
            goto L_0x0090
        L_0x0087:
            int r8 = r6.mQuickReturnFooterHeight
            if (r7 <= r8) goto L_0x008f
            r6.mStateFooter = r3
            r6.mMinRawYFooter = r7
        L_0x008f:
            r0 = r7
        L_0x0090:
            int r7 = r6.mFooterY
            if (r7 == r0) goto L_0x009c
            r6.mFooterY = r0
            android.view.View r7 = r6.mQuickReturnFooter
            float r8 = (float) r0
            r7.setTranslationY(r8)
        L_0x009c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.uikit.listwrapper.QuickReturnEndlessListViewWrapper.translateFooter(int, int, com.etsy.android.uikit.listwrapper.ObservableEndlessListViewWrapper$ScrollDirection):void");
    }
}
