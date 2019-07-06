package com.jakewharton.rxrelay2;

import io.reactivex.functions.i;

/* compiled from: AppendOnlyLinkedArrayList */
class a<T> {
    private final int a;
    private final Object[] b;
    private Object[] c = this.b;
    private int d;

    /* renamed from: com.jakewharton.rxrelay2.a$a reason: collision with other inner class name */
    /* compiled from: AppendOnlyLinkedArrayList */
    public interface C0156a<T> extends i<T> {
        boolean test(T t);
    }

    a(int i) {
        this.a = i;
        this.b = new Object[(i + 1)];
    }

    /* access modifiers changed from: 0000 */
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

    /* access modifiers changed from: 0000 */
    public void a(C0156a<? super T> aVar) {
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
}
