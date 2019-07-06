package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    /* access modifiers changed from: private */
    public static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    /* access modifiers changed from: private */
    public int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    /* access modifiers changed from: private */
    public final Object mDataLock = new Object();
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private android.arch.core.internal.a<h<T>, b> mObservers = new android.arch.core.internal.a<>();
    /* access modifiers changed from: private */
    public volatile Object mPendingData = NOT_SET;
    private final Runnable mPostValueRunnable = new Runnable() {
        public void run() {
            Object access$100;
            synchronized (LiveData.this.mDataLock) {
                access$100 = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(access$100);
        }
    };
    private int mVersion = -1;

    class LifecycleBoundObserver extends b implements GenericLifecycleObserver {
        @NonNull
        final e a;

        LifecycleBoundObserver(e eVar, @NonNull h<T> hVar) {
            super(hVar);
            this.a = eVar;
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            return this.a.getLifecycle().getCurrentState().isAtLeast(State.STARTED);
        }

        public void onStateChanged(e eVar, Event event) {
            if (this.a.getLifecycle().getCurrentState() == State.DESTROYED) {
                LiveData.this.removeObserver(this.c);
            } else {
                a(a());
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a(e eVar) {
            return this.a == eVar;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.a.getLifecycle().removeObserver(this);
        }
    }

    private class a extends b {
        /* access modifiers changed from: 0000 */
        public boolean a() {
            return true;
        }

        a(h<T> hVar) {
            super(hVar);
        }
    }

    private abstract class b {
        final h<T> c;
        boolean d;
        int e = -1;

        /* access modifiers changed from: 0000 */
        public abstract boolean a();

        /* access modifiers changed from: 0000 */
        public boolean a(e eVar) {
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void b() {
        }

        b(h<T> hVar) {
            this.c = hVar;
        }

        /* access modifiers changed from: 0000 */
        public void a(boolean z) {
            if (z != this.d) {
                this.d = z;
                int i = 1;
                boolean z2 = LiveData.this.mActiveCount == 0;
                LiveData liveData = LiveData.this;
                int access$300 = liveData.mActiveCount;
                if (!this.d) {
                    i = -1;
                }
                liveData.mActiveCount = access$300 + i;
                if (z2 && this.d) {
                    LiveData.this.onActive();
                }
                if (LiveData.this.mActiveCount == 0 && !this.d) {
                    LiveData.this.onInactive();
                }
                if (this.d) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActive() {
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
    }

    private void considerNotify(b bVar) {
        if (bVar.d) {
            if (!bVar.a()) {
                bVar.a(false);
            } else if (bVar.e < this.mVersion) {
                bVar.e = this.mVersion;
                bVar.c.a(this.mData);
            }
        }
    }

    /* access modifiers changed from: private */
    public void dispatchingValue(@Nullable b bVar) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (bVar == null) {
                d iteratorWithAdditions = this.mObservers.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    considerNotify((b) ((Entry) iteratorWithAdditions.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            } else {
                considerNotify(bVar);
                bVar = null;
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @MainThread
    public void observe(@NonNull e eVar, @NonNull h<T> hVar) {
        if (eVar.getLifecycle().getCurrentState() != State.DESTROYED) {
            LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(eVar, hVar);
            b bVar = (b) this.mObservers.putIfAbsent(hVar, lifecycleBoundObserver);
            if (bVar != null && !bVar.a(eVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (bVar == null) {
                eVar.getLifecycle().addObserver(lifecycleBoundObserver);
            }
        }
    }

    @MainThread
    public void observeForever(@NonNull h<T> hVar) {
        a aVar = new a(hVar);
        b bVar = (b) this.mObservers.putIfAbsent(hVar, aVar);
        if (bVar != null && (bVar instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        } else if (bVar == null) {
            aVar.a(true);
        }
    }

    @MainThread
    public void removeObserver(@NonNull h<T> hVar) {
        assertMainThread("removeObserver");
        b bVar = (b) this.mObservers.remove(hVar);
        if (bVar != null) {
            bVar.b();
            bVar.a(false);
        }
    }

    @MainThread
    public void removeObservers(@NonNull e eVar) {
        assertMainThread("removeObservers");
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (((b) entry.getValue()).a(eVar)) {
                removeObserver((h) entry.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void postValue(T t) {
        boolean z;
        synchronized (this.mDataLock) {
            z = this.mPendingData == NOT_SET;
            this.mPendingData = t;
        }
        if (z) {
            ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
        }
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void setValue(T t) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        dispatchingValue(null);
    }

    @Nullable
    public T getValue() {
        T t = this.mData;
        if (t != NOT_SET) {
            return t;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public int getVersion() {
        return this.mVersion;
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    private static void assertMainThread(String str) {
        if (!ArchTaskExecutor.getInstance().isMainThread()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot invoke ");
            sb.append(str);
            sb.append(" on a background");
            sb.append(" thread");
            throw new IllegalStateException(sb.toString());
        }
    }
}
