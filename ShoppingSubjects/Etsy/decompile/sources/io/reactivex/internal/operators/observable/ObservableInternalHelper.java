package io.reactivex.internal.operators.observable;

import io.reactivex.functions.g;
import io.reactivex.functions.i;
import io.reactivex.p;

public final class ObservableInternalHelper {

    enum ErrorMapperFilter implements g<p<Object>, Throwable>, i<p<Object>> {
        INSTANCE;

        public Throwable apply(p<Object> pVar) throws Exception {
            return pVar.b();
        }

        public boolean test(p<Object> pVar) throws Exception {
            return pVar.a();
        }
    }

    enum MapToInt implements g<Object, Object> {
        INSTANCE;

        public Object apply(Object obj) throws Exception {
            return Integer.valueOf(0);
        }
    }
}
