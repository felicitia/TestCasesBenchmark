package com.google.android.gms.internal.icing;

final class ba extends ax {
    private ba() {
        super();
    }

    private static <E> ao<E> b(Object obj, long j) {
        return (ao) cy.f(obj, j);
    }

    /* access modifiers changed from: 0000 */
    public final void a(Object obj, long j) {
        b(obj, j).b();
    }

    /* access modifiers changed from: 0000 */
    public final <E> void a(Object obj, Object obj2, long j) {
        ao b = b(obj, j);
        ao b2 = b(obj2, j);
        int size = b.size();
        int size2 = b2.size();
        if (size > 0 && size2 > 0) {
            if (!b.a()) {
                b = b.a(size2 + size);
            }
            b.addAll(b2);
        }
        if (size > 0) {
            b2 = b;
        }
        cy.a(obj, j, (Object) b2);
    }
}
