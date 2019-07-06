package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.i;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: MultiTransformation */
public class c<T> implements f<T> {
    private final Collection<? extends f<T>> a;
    private String b;

    @SafeVarargs
    public c(f<T>... fVarArr) {
        if (fVarArr.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.a = Arrays.asList(fVarArr);
    }

    public i<T> a(i<T> iVar, int i, int i2) {
        i<T> iVar2 = iVar;
        for (f a2 : this.a) {
            i<T> a3 = a2.a(iVar2, i, i2);
            if (iVar2 != null && !iVar2.equals(iVar) && !iVar2.equals(a3)) {
                iVar2.d();
            }
            iVar2 = a3;
        }
        return iVar2;
    }

    public String a() {
        if (this.b == null) {
            StringBuilder sb = new StringBuilder();
            for (f a2 : this.a) {
                sb.append(a2.a());
            }
            this.b = sb.toString();
        }
        return this.b;
    }
}
