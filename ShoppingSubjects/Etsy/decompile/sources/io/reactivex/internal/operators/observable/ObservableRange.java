package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.q;

public final class ObservableRange extends q<Integer> {
    private final int a;
    private final long b;

    static final class RangeDisposable extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Integer> actual;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(Observer<? super Integer> observer, long j, long j2) {
            this.actual = observer;
            this.index = j;
            this.end = j2;
        }

        /* access modifiers changed from: 0000 */
        public void run() {
            if (!this.fused) {
                Observer<? super Integer> observer = this.actual;
                long j = this.end;
                for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                    observer.onNext(Integer.valueOf((int) j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    observer.onComplete();
                }
            }
        }

        public Integer poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = j + 1;
                return Integer.valueOf((int) j);
            }
            lazySet(1);
            return null;
        }

        public boolean isEmpty() {
            return this.index == this.end;
        }

        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        public void dispose() {
            set(1);
        }

        public boolean isDisposed() {
            return get() != 0;
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.fused = true;
            return 1;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super Integer> observer) {
        RangeDisposable rangeDisposable = new RangeDisposable(observer, (long) this.a, this.b);
        observer.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }
}
