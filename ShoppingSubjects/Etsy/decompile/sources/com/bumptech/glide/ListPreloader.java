package com.bumptech.glide;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.bumptech.glide.request.target.h;
import com.bumptech.glide.request.target.i;
import java.util.List;
import java.util.Queue;

public class ListPreloader<T> implements OnScrollListener {
    private boolean isIncreasing;
    private int lastEnd;
    private int lastFirstVisible;
    private int lastStart;
    private final int maxPreload;
    private final b<T> preloadDimensionProvider;
    private final a<T> preloadModelProvider;
    private final d preloadTargetQueue;
    private int totalItemCount;

    public interface a<U> {
        e a(U u);

        List<U> a(int i);
    }

    public interface b<T> {
        int[] a(T t, int i, int i2);
    }

    private static class c extends com.bumptech.glide.request.target.a<Object> {
        /* access modifiers changed from: private */
        public int a;
        /* access modifiers changed from: private */
        public int b;

        public void a(Object obj, com.bumptech.glide.request.a.c<? super Object> cVar) {
        }

        private c() {
        }

        public void a(h hVar) {
            hVar.a(this.b, this.a);
        }
    }

    private static final class d {
        private final Queue<c> a;

        public d(int i) {
            this.a = com.bumptech.glide.g.h.a(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.a.offer(new c());
            }
        }

        public c a(int i, int i2) {
            c cVar = (c) this.a.poll();
            this.a.offer(cVar);
            cVar.b = i;
            cVar.a = i2;
            return cVar;
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Deprecated
    public ListPreloader(int i) {
        this.isIncreasing = true;
        this.preloadModelProvider = new a<T>() {
            public List<T> a(int i) {
                return ListPreloader.this.getItems(i, i + 1);
            }

            public e a(T t) {
                return ListPreloader.this.getRequestBuilder(t);
            }
        };
        this.preloadDimensionProvider = new b<T>() {
            public int[] a(T t, int i, int i2) {
                return ListPreloader.this.getDimensions(t);
            }
        };
        this.maxPreload = i;
        this.preloadTargetQueue = new d(i + 1);
    }

    public ListPreloader(a<T> aVar, b<T> bVar, int i) {
        this.isIncreasing = true;
        this.preloadModelProvider = aVar;
        this.preloadDimensionProvider = bVar;
        this.maxPreload = i;
        this.preloadTargetQueue = new d(i + 1);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.totalItemCount = i3;
        if (i > this.lastFirstVisible) {
            preload(i2 + i, true);
        } else if (i < this.lastFirstVisible) {
            preload(i, false);
        }
        this.lastFirstVisible = i;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public int[] getDimensions(T t) {
        throw new IllegalStateException("You must either provide a PreloadDimensionProvider or override getDimensions()");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public List<T> getItems(int i, int i2) {
        throw new IllegalStateException("You must either provide a PreloadModelProvider or override getItems()");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public e getRequestBuilder(T t) {
        throw new IllegalStateException("You must either provide a PreloadModelProvider, or override getRequestBuilder()");
    }

    private void preload(int i, boolean z) {
        if (this.isIncreasing != z) {
            this.isIncreasing = z;
            cancelAll();
        }
        preload(i, (z ? this.maxPreload : -this.maxPreload) + i);
    }

    private void preload(int i, int i2) {
        int i3;
        int i4;
        if (i < i2) {
            i3 = Math.max(this.lastEnd, i);
            i4 = i2;
        } else {
            i4 = Math.min(this.lastStart, i);
            i3 = i2;
        }
        int min = Math.min(this.totalItemCount, i4);
        int min2 = Math.min(this.totalItemCount, Math.max(0, i3));
        if (i < i2) {
            for (int i5 = min2; i5 < min; i5++) {
                preloadAdapterPosition(this.preloadModelProvider.a(i5), i5, true);
            }
        } else {
            for (int i6 = min - 1; i6 >= min2; i6--) {
                preloadAdapterPosition(this.preloadModelProvider.a(i6), i6, false);
            }
        }
        this.lastStart = min2;
        this.lastEnd = min;
    }

    private void preloadAdapterPosition(List<T> list, int i, boolean z) {
        int size = list.size();
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                preloadItem(list.get(i2), i, i2);
            }
            return;
        }
        for (int i3 = size - 1; i3 >= 0; i3--) {
            preloadItem(list.get(i3), i, i3);
        }
    }

    private void preloadItem(T t, int i, int i2) {
        int[] a2 = this.preloadDimensionProvider.a(t, i, i2);
        if (a2 != null) {
            this.preloadModelProvider.a(t).a(this.preloadTargetQueue.a(a2[0], a2[1]));
        }
    }

    private void cancelAll() {
        for (int i = 0; i < this.maxPreload; i++) {
            Glide.a((i<?>) this.preloadTargetQueue.a(0, 0));
        }
    }
}
