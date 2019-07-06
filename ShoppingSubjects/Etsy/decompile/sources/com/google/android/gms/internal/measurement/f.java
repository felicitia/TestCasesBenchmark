package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.e;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class f<M extends e<M>, T> {
    protected final Class<T> a;
    public final int b;
    protected final boolean c;
    private final int d;

    private final Object a(c cVar) {
        Class<T> componentType = this.c ? this.a.getComponentType() : this.a;
        try {
            switch (this.d) {
                case 10:
                    j jVar = (j) componentType.newInstance();
                    cVar.a(jVar, this.b >>> 3);
                    return jVar;
                case 11:
                    j jVar2 = (j) componentType.newInstance();
                    cVar.a(jVar2);
                    return jVar2;
                default:
                    int i = this.d;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (InstantiationException e) {
            String valueOf = String.valueOf(componentType);
            StringBuilder sb2 = new StringBuilder(33 + String.valueOf(valueOf).length());
            sb2.append("Error creating instance of class ");
            sb2.append(valueOf);
            throw new IllegalArgumentException(sb2.toString(), e);
        } catch (IllegalAccessException e2) {
            String valueOf2 = String.valueOf(componentType);
            StringBuilder sb3 = new StringBuilder(33 + String.valueOf(valueOf2).length());
            sb3.append("Error creating instance of class ");
            sb3.append(valueOf2);
            throw new IllegalArgumentException(sb3.toString(), e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: protected */
    public final int a(Object obj) {
        int i = this.b >>> 3;
        switch (this.d) {
            case 10:
                return (d.b(i) << 1) + ((j) obj).d();
            case 11:
                return d.b(i, (j) obj);
            default:
                int i2 = this.d;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Unknown type ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public final T a(List<l> list) {
        if (list == null) {
            return null;
        }
        if (this.c) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                l lVar = (l) list.get(i);
                if (lVar.b.length != 0) {
                    arrayList.add(a(c.a(lVar.b)));
                }
            }
            int size = arrayList.size();
            if (size == 0) {
                return null;
            }
            T cast = this.a.cast(Array.newInstance(this.a.getComponentType(), size));
            for (int i2 = 0; i2 < size; i2++) {
                Array.set(cast, i2, arrayList.get(i2));
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.a.cast(a(c.a(((l) list.get(list.size() - 1)).b)));
        }
    }

    /* access modifiers changed from: protected */
    public final void a(Object obj, d dVar) {
        try {
            dVar.c(this.b);
            switch (this.d) {
                case 10:
                    int i = this.b >>> 3;
                    ((j) obj).a(dVar);
                    dVar.c(i, 4);
                    return;
                case 11:
                    dVar.a((j) obj);
                    return;
                default:
                    int i2 = this.d;
                    StringBuilder sb = new StringBuilder(24);
                    sb.append("Unknown type ");
                    sb.append(i2);
                    throw new IllegalArgumentException(sb.toString());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.d == fVar.d && this.a == fVar.a && this.b == fVar.b && this.c == fVar.c;
    }

    public final int hashCode() {
        return ((((((1147 + this.d) * 31) + this.a.hashCode()) * 31) + this.b) * 31) + (this.c ? 1 : 0);
    }
}
