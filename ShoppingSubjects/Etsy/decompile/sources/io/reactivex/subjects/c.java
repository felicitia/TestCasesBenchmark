package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.q;

/* compiled from: Subject */
public abstract class c<T> extends q<T> implements Observer<T> {
    public final c<T> c() {
        if (this instanceof b) {
            return this;
        }
        return new b(this);
    }
}
