package com.jakewharton.rxrelay2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.u;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ReplayRelay<T> extends c<T> {
    static final ReplayDisposable[] c = new ReplayDisposable[0];
    private static final Object[] d = new Object[0];
    final a<T> a;
    final AtomicReference<ReplayDisposable<T>[]> b;

    static final class Node<T> extends AtomicReference<Node<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final T value;

        Node(T t) {
            this.value = t;
        }
    }

    static final class ReplayDisposable<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 466549804534799122L;
        final Observer<? super T> actual;
        volatile boolean cancelled;
        Object index;
        final ReplayRelay<T> state;

        ReplayDisposable(Observer<? super T> observer, ReplayRelay<T> replayRelay) {
            this.actual = observer;
            this.state = replayRelay;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b(this);
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends AtomicReference<Object> implements a<T> {
        private static final long serialVersionUID = -8056260896137901749L;
        volatile TimedNode<T> head;
        final long maxAge;
        final int maxSize;
        final u scheduler;
        int size;
        TimedNode<T> tail;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, u uVar) {
            if (i <= 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("maxSize > 0 required but it was ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            } else if (j <= 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("maxAge > 0 required but it was ");
                sb2.append(j);
                throw new IllegalArgumentException(sb2.toString());
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else if (uVar == null) {
                throw new NullPointerException("scheduler == null");
            } else {
                this.maxSize = i;
                this.maxAge = j;
                this.unit = timeUnit;
                this.scheduler = uVar;
                TimedNode<T> timedNode = new TimedNode<>(null, 0);
                this.tail = timedNode;
                this.head = timedNode;
            }
        }

        /* access modifiers changed from: 0000 */
        public void trim() {
            if (this.size > this.maxSize) {
                this.size--;
                this.head = (TimedNode) this.head.get();
            }
            long a = this.scheduler.a(this.unit) - this.maxAge;
            TimedNode<T> timedNode = this.head;
            while (true) {
                TimedNode<T> timedNode2 = (TimedNode) timedNode.get();
                if (timedNode2 == null) {
                    this.head = timedNode;
                    return;
                } else if (timedNode2.time > a) {
                    this.head = timedNode;
                    return;
                } else {
                    timedNode = timedNode2;
                }
            }
        }

        public void add(T t) {
            TimedNode<T> timedNode = new TimedNode<>(t, this.scheduler.a(this.unit));
            TimedNode<T> timedNode2 = this.tail;
            this.tail = timedNode;
            this.size++;
            timedNode2.set(timedNode);
            trim();
        }

        public T getValue() {
            TimedNode<T> timedNode = this.head;
            while (true) {
                TimedNode<T> timedNode2 = (TimedNode) timedNode.get();
                if (timedNode2 == null) {
                    return timedNode.value;
                }
                timedNode = timedNode2;
            }
        }

        public T[] getValues(T[] tArr) {
            TimedNode<T> timedNode = this.head;
            int size2 = size();
            if (size2 != 0) {
                if (tArr.length < size2) {
                    tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size2);
                }
                for (int i = 0; i != size2; i++) {
                    timedNode = (TimedNode) timedNode.get();
                    tArr[i] = timedNode.value;
                }
                if (tArr.length > size2) {
                    tArr[size2] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        public void replay(ReplayDisposable<T> replayDisposable) {
            if (replayDisposable.getAndIncrement() == 0) {
                int i = 1;
                Observer<? super T> observer = replayDisposable.actual;
                TimedNode timedNode = (TimedNode) replayDisposable.index;
                if (timedNode == null) {
                    timedNode = this.head;
                    long a = this.scheduler.a(this.unit) - this.maxAge;
                    TimedNode timedNode2 = (TimedNode) timedNode.get();
                    while (timedNode2 != null && timedNode2.time <= a) {
                        TimedNode timedNode3 = timedNode2;
                        timedNode2 = (TimedNode) timedNode2.get();
                        timedNode = timedNode3;
                    }
                }
                while (!replayDisposable.cancelled) {
                    while (!replayDisposable.cancelled) {
                        TimedNode timedNode4 = (TimedNode) timedNode.get();
                        if (timedNode4 != null) {
                            observer.onNext(timedNode4.value);
                            timedNode = timedNode4;
                        } else if (timedNode.get() == null) {
                            replayDisposable.index = timedNode;
                            i = replayDisposable.addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    }
                    replayDisposable.index = null;
                    return;
                }
                replayDisposable.index = null;
            }
        }

        public int size() {
            TimedNode<T> timedNode = this.head;
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                timedNode = (TimedNode) timedNode.get();
                if (timedNode == null) {
                    break;
                }
                i++;
            }
            return i;
        }
    }

    static final class SizeBoundReplayBuffer<T> extends AtomicReference<Object> implements a<T> {
        private static final long serialVersionUID = 1107649250281456395L;
        volatile Node<T> head;
        final int maxSize;
        int size;
        Node<T> tail;

        SizeBoundReplayBuffer(int i) {
            if (i <= 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("maxSize > 0 required but it was ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            this.maxSize = i;
            Node<T> node = new Node<>(null);
            this.tail = node;
            this.head = node;
        }

        /* access modifiers changed from: 0000 */
        public void trim() {
            if (this.size > this.maxSize) {
                this.size--;
                this.head = (Node) this.head.get();
            }
        }

        public void add(T t) {
            Node<T> node = new Node<>(t);
            Node<T> node2 = this.tail;
            this.tail = node;
            this.size++;
            node2.set(node);
            trim();
        }

        public T getValue() {
            Node<T> node = this.head;
            while (true) {
                Node<T> node2 = (Node) node.get();
                if (node2 == null) {
                    return node.value;
                }
                node = node2;
            }
        }

        public T[] getValues(T[] tArr) {
            Node<T> node = this.head;
            int size2 = size();
            if (size2 != 0) {
                if (tArr.length < size2) {
                    tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size2);
                }
                for (int i = 0; i != size2; i++) {
                    node = (Node) node.get();
                    tArr[i] = node.value;
                }
                if (tArr.length > size2) {
                    tArr[size2] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        public void replay(ReplayDisposable<T> replayDisposable) {
            if (replayDisposable.getAndIncrement() == 0) {
                int i = 1;
                Observer<? super T> observer = replayDisposable.actual;
                Node<T> node = (Node) replayDisposable.index;
                if (node == null) {
                    node = this.head;
                }
                while (!replayDisposable.cancelled) {
                    Node<T> node2 = (Node) node.get();
                    if (node2 != null) {
                        observer.onNext(node2.value);
                        node = node2;
                    } else if (node.get() != null) {
                        continue;
                    } else {
                        replayDisposable.index = node;
                        i = replayDisposable.addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                }
                replayDisposable.index = null;
            }
        }

        public int size() {
            Node<T> node = this.head;
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                node = (Node) node.get();
                if (node == null) {
                    break;
                }
                i++;
            }
            return i;
        }
    }

    static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final long time;
        final T value;

        TimedNode(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    static final class UnboundedReplayBuffer<T> extends AtomicReference<Object> implements a<T> {
        private static final long serialVersionUID = -733876083048047795L;
        final List<T> buffer;
        volatile int size;

        UnboundedReplayBuffer(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("capacityHint <= 0");
            }
            this.buffer = new ArrayList(i);
        }

        public void add(T t) {
            this.buffer.add(t);
            this.size++;
        }

        public T getValue() {
            int i = this.size;
            if (i != 0) {
                return this.buffer.get(i - 1);
            }
            return null;
        }

        public T[] getValues(T[] tArr) {
            int i = this.size;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            if (tArr.length < i) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
            }
            List<T> list = this.buffer;
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = list.get(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        public void replay(ReplayDisposable<T> replayDisposable) {
            if (replayDisposable.getAndIncrement() == 0) {
                List<T> list = this.buffer;
                Observer<? super T> observer = replayDisposable.actual;
                Integer num = (Integer) replayDisposable.index;
                int i = 0;
                int i2 = 1;
                if (num != null) {
                    i = num.intValue();
                } else {
                    replayDisposable.index = Integer.valueOf(0);
                }
                while (!replayDisposable.cancelled) {
                    int i3 = this.size;
                    while (i3 != i) {
                        if (replayDisposable.cancelled) {
                            replayDisposable.index = null;
                            return;
                        } else {
                            observer.onNext(list.get(i));
                            i++;
                        }
                    }
                    if (i == this.size) {
                        replayDisposable.index = Integer.valueOf(i);
                        i2 = replayDisposable.addAndGet(-i2);
                        if (i2 == 0) {
                            return;
                        }
                    }
                }
                replayDisposable.index = null;
            }
        }

        public int size() {
            int i = this.size;
            if (i != 0) {
                return i;
            }
            return 0;
        }
    }

    interface a<T> {
        void add(T t);

        void replay(ReplayDisposable<T> replayDisposable);
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        ReplayDisposable replayDisposable = new ReplayDisposable(observer, this);
        observer.onSubscribe(replayDisposable);
        if (!replayDisposable.cancelled) {
            if (!a(replayDisposable) || !replayDisposable.cancelled) {
                this.a.replay(replayDisposable);
            } else {
                b(replayDisposable);
            }
        }
    }

    public void accept(T t) {
        if (t == null) {
            throw new NullPointerException("value == null");
        }
        a<T> aVar = this.a;
        aVar.add(t);
        for (ReplayDisposable replay : (ReplayDisposable[]) this.b.get()) {
            aVar.replay(replay);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(ReplayDisposable<T> replayDisposable) {
        ReplayDisposable[] replayDisposableArr;
        ReplayDisposable[] replayDisposableArr2;
        do {
            replayDisposableArr = (ReplayDisposable[]) this.b.get();
            int length = replayDisposableArr.length;
            replayDisposableArr2 = new ReplayDisposable[(length + 1)];
            System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
            replayDisposableArr2[length] = replayDisposable;
        } while (!this.b.compareAndSet(replayDisposableArr, replayDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(ReplayDisposable<T> replayDisposable) {
        ReplayDisposable<T>[] replayDisposableArr;
        ReplayDisposable[] replayDisposableArr2;
        do {
            replayDisposableArr = (ReplayDisposable[]) this.b.get();
            if (replayDisposableArr != c) {
                int length = replayDisposableArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (replayDisposableArr[i2] == replayDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        replayDisposableArr2 = c;
                    } else {
                        ReplayDisposable[] replayDisposableArr3 = new ReplayDisposable[(length - 1)];
                        System.arraycopy(replayDisposableArr, 0, replayDisposableArr3, 0, i);
                        System.arraycopy(replayDisposableArr, i + 1, replayDisposableArr3, i, (length - i) - 1);
                        replayDisposableArr2 = replayDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.b.compareAndSet(replayDisposableArr, replayDisposableArr2));
    }
}
