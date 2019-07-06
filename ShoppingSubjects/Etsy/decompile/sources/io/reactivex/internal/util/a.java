package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.functions.i;
import org.reactivestreams.c;

/* compiled from: AppendOnlyLinkedArrayList */
public class a<T> {
    final int a;
    final Object[] b;
    Object[] c = this.b;
    int d;

    /* renamed from: io.reactivex.internal.util.a$a reason: collision with other inner class name */
    /* compiled from: AppendOnlyLinkedArrayList */
    public interface C0189a<T> extends i<T> {
        boolean test(T t);
    }

    public a(int i) {
        this.a = i;
        this.b = new Object[(i + 1)];
    }

    public void a(T t) {
        int i = this.a;
        int i2 = this.d;
        if (i2 == i) {
            Object[] objArr = new Object[(i + 1)];
            this.c[i] = objArr;
            this.c = objArr;
            i2 = 0;
        }
        this.c[i2] = t;
        this.d = i2 + 1;
    }

    public void b(T t) {
        this.b[0] = t;
    }

    public void a(C0189a<? super T> aVar) {
        int i = this.a;
        for (Object[] objArr = this.b; objArr != null; objArr = objArr[i]) {
            for (int i2 = 0; i2 < i; i2++) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == null || aVar.test(objArr2)) {
                    break;
                }
            }
        }
    }

    public <U> boolean a(c<? super U> cVar) {
        Object[] objArr = this.b;
        int i = this.a;
        while (true) {
            int i2 = 0;
            if (objArr == null) {
                return false;
            }
            while (i2 < i) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull((Object) objArr2, cVar)) {
                    return true;
                } else {
                    i2++;
                }
            }
            objArr = objArr[i];
        }
    }

    public <U> boolean a(Observer<? super U> observer) {
        Object[] objArr = this.b;
        int i = this.a;
        while (true) {
            int i2 = 0;
            if (objArr == null) {
                return false;
            }
            while (i2 < i) {
                Object[] objArr2 = objArr[i2];
                if (objArr2 == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull((Object) objArr2, observer)) {
                    return true;
                } else {
                    i2++;
                }
            }
            objArr = objArr[i];
        }
    }
}
