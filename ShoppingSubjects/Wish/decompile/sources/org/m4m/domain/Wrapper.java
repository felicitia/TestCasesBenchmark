package org.m4m.domain;

public class Wrapper<T> implements IWrapper {
    private T t;

    public Wrapper(T t2) {
        this.t = t2;
    }

    public T getNativeObject() {
        return this.t;
    }
}
