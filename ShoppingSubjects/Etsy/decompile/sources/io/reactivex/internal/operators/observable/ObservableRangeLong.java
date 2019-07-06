package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.q;

public final class ObservableRangeLong extends q<Long> {
    private final long a;
    private final long b;

    static final class RangeDisposable extends BasicIntQueueDisposable<Long> {
        private static final long serialVersionUID = 396518478098735504L;
        final Observer<? super Long> actual;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(Observer<? super Long> observer, long j, long j2) {
            this.actual = observer;
            this.index = j;
            this.end = j2;
        }

        /* access modifiers changed from: 0000 */
        public void run() {
            if (!this.fused) {
                Observer<? super Long> observer = this.actual;
                long j = this.end;
                for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                    observer.onNext(Long.valueOf(j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    observer.onComplete();
                }
            }
        }

        public Long poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = j + 1;
                return Long.valueOf(j);
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
    public void a(Observer<? super Long> observer) {
        RangeDisposable rangeDisposable = new RangeDisposable(observer, this.a, this.a + this.b);
        observer.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }
}
