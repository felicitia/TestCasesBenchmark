package io.reactivex.internal.operators.single;

import io.reactivex.functions.g;
import io.reactivex.q;
import io.reactivex.z;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import org.reactivestreams.b;

public final class SingleInternalHelper {

    enum NoSuchElementCallable implements Callable<NoSuchElementException> {
        INSTANCE;

        public NoSuchElementException call() throws Exception {
            return new NoSuchElementException();
        }
    }

    enum ToFlowable implements g<z, b> {
        INSTANCE;

        public b apply(z zVar) {
            return new SingleToFlowable(zVar);
        }
    }

    enum ToObservable implements g<z, q> {
        INSTANCE;

        public q apply(z zVar) {
            return new g(zVar);
        }
    }
}
