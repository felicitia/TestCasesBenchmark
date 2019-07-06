package io.reactivex.internal.queue;

import io.reactivex.internal.a.f;
import java.util.concurrent.atomic.AtomicReference;

public final class MpscLinkedQueue<T> implements f<T> {
    private final AtomicReference<LinkedQueueNode<T>> a = new AtomicReference<>();
    private final AtomicReference<LinkedQueueNode<T>> b = new AtomicReference<>();

    static final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        LinkedQueueNode() {
        }

        LinkedQueueNode(E e) {
            spValue(e);
        }

        public E getAndNullValue() {
            E lpValue = lpValue();
            spValue(null);
            return lpValue;
        }

        public E lpValue() {
            return this.value;
        }

        public void spValue(E e) {
            this.value = e;
        }

        public void soNext(LinkedQueueNode<E> linkedQueueNode) {
            lazySet(linkedQueueNode);
        }

        public LinkedQueueNode<E> lvNext() {
            return (LinkedQueueNode) get();
        }
    }

    public MpscLinkedQueue() {
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode();
        b(linkedQueueNode);
        a(linkedQueueNode);
    }

    public boolean offer(T t) {
        if (t == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode(t);
        a(linkedQueueNode).soNext(linkedQueueNode);
        return true;
    }

    public T poll() {
        LinkedQueueNode lvNext;
        LinkedQueueNode c = c();
        LinkedQueueNode lvNext2 = c.lvNext();
        if (lvNext2 != null) {
            T andNullValue = lvNext2.getAndNullValue();
            b(lvNext2);
            return andNullValue;
        } else if (c == a()) {
            return null;
        } else {
            do {
                lvNext = c.lvNext();
            } while (lvNext == null);
            T andNullValue2 = lvNext.getAndNullValue();
            b(lvNext);
            return andNullValue2;
        }
    }

    public void clear() {
        while (poll() != null) {
            if (isEmpty()) {
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public LinkedQueueNode<T> a() {
        return (LinkedQueueNode) this.a.get();
    }

    /* access modifiers changed from: 0000 */
    public LinkedQueueNode<T> a(LinkedQueueNode<T> linkedQueueNode) {
        return (LinkedQueueNode) this.a.getAndSet(linkedQueueNode);
    }

    /* access modifiers changed from: 0000 */
    public LinkedQueueNode<T> b() {
        return (LinkedQueueNode) this.b.get();
    }

    /* access modifiers changed from: 0000 */
    public LinkedQueueNode<T> c() {
        return (LinkedQueueNode) this.b.get();
    }

    /* access modifiers changed from: 0000 */
    public void b(LinkedQueueNode<T> linkedQueueNode) {
        this.b.lazySet(linkedQueueNode);
    }

    public boolean isEmpty() {
        return b() == a();
    }
}
