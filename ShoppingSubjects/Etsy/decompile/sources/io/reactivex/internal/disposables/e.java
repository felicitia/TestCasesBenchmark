package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ListCompositeDisposable */
public final class e implements Disposable, a {
    List<Disposable> a;
    volatile boolean b;

    public void dispose() {
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    this.b = true;
                    List<Disposable> list = this.a;
                    this.a = null;
                    a(list);
                }
            }
        }
    }

    public boolean isDisposed() {
        return this.b;
    }

    public boolean a(Disposable disposable) {
        a.a(disposable, "d is null");
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    List list = this.a;
                    if (list == null) {
                        list = new LinkedList();
                        this.a = list;
                    }
                    list.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    public boolean b(Disposable disposable) {
        if (!c(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(io.reactivex.disposables.Disposable r3) {
        /*
            r2 = this;
            java.lang.String r0 = "Disposable item is null"
            io.reactivex.internal.functions.a.a(r3, r0)
            boolean r0 = r2.b
            r1 = 0
            if (r0 == 0) goto L_0x000b
            return r1
        L_0x000b:
            monitor-enter(r2)
            boolean r0 = r2.b     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0012
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            return r1
        L_0x0012:
            java.util.List<io.reactivex.disposables.Disposable> r0 = r2.a     // Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0020
            boolean r3 = r0.remove(r3)     // Catch:{ all -> 0x0022 }
            if (r3 != 0) goto L_0x001d
            goto L_0x0020
        L_0x001d:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            r3 = 1
            return r3
        L_0x0020:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            return r1
        L_0x0022:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.disposables.e.c(io.reactivex.disposables.Disposable):boolean");
    }

    /* access modifiers changed from: 0000 */
    public void a(List<Disposable> list) {
        if (list != null) {
            ArrayList arrayList = null;
            for (Disposable dispose : list) {
                try {
                    dispose.dispose();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
            if (arrayList == null) {
                return;
            }
            if (arrayList.size() == 1) {
                throw ExceptionHelper.a((Throwable) arrayList.get(0));
            }
            throw new CompositeException((Iterable<? extends Throwable>) arrayList);
        }
    }
}
