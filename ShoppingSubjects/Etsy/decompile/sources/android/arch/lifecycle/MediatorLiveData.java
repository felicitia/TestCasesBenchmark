package android.arch.lifecycle;

import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public class MediatorLiveData<T> extends MutableLiveData<T> {
    private android.arch.core.internal.a<LiveData<?>, a<?>> mSources = new android.arch.core.internal.a<>();

    private static class a<V> implements h<V> {
        final LiveData<V> a;
        final h<V> b;
        int c = -1;

        a(LiveData<V> liveData, h<V> hVar) {
            this.a = liveData;
            this.b = hVar;
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a.observeForever(this);
        }

        /* access modifiers changed from: 0000 */
        public void b() {
            this.a.removeObserver(this);
        }

        public void a(@Nullable V v) {
            if (this.c != this.a.getVersion()) {
                this.c = this.a.getVersion();
                this.b.a(v);
            }
        }
    }

    @MainThread
    public <S> void addSource(@NonNull LiveData<S> liveData, @NonNull h<S> hVar) {
        a aVar = new a(liveData, hVar);
        a aVar2 = (a) this.mSources.putIfAbsent(liveData, aVar);
        if (aVar2 == null || aVar2.b == hVar) {
            if (aVar2 == null && hasActiveObservers()) {
                aVar.a();
            }
            return;
        }
        throw new IllegalArgumentException("This source was already added with the different observer");
    }

    @MainThread
    public <S> void removeSource(@NonNull LiveData<S> liveData) {
        a aVar = (a) this.mSources.remove(liveData);
        if (aVar != null) {
            aVar.b();
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onActive() {
        Iterator it = this.mSources.iterator();
        while (it.hasNext()) {
            ((a) ((Entry) it.next()).getValue()).a();
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onInactive() {
        Iterator it = this.mSources.iterator();
        while (it.hasNext()) {
            ((a) ((Entry) it.next()).getValue()).b();
        }
    }
}
