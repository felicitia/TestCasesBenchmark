package com.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder<T> {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    protected a<T> _bufferHead;
    protected a<T> _bufferTail;
    protected int _bufferedEntryCount;
    protected T _freeBuffer;

    static final class a<T> {
        final T a;
        final int b;
        a<T> c;

        public a(T t, int i) {
            this.a = t;
            this.b = i;
        }

        public T a() {
            return this.a;
        }

        public int a(T t, int i) {
            System.arraycopy(this.a, 0, t, i, this.b);
            return i + this.b;
        }

        public a<T> b() {
            return this.c;
        }

        public void a(a<T> aVar) {
            if (this.c != null) {
                throw new IllegalStateException();
            }
            this.c = aVar;
        }
    }

    /* access modifiers changed from: protected */
    public abstract T _constructArray(int i);

    protected PrimitiveArrayBuilder() {
    }

    public T resetAndStart() {
        _reset();
        return this._freeBuffer == null ? _constructArray(12) : this._freeBuffer;
    }

    public final T appendCompletedChunk(T t, int i) {
        a<T> aVar = new a<>(t, i);
        if (this._bufferHead == null) {
            this._bufferTail = aVar;
            this._bufferHead = aVar;
        } else {
            this._bufferTail.a(aVar);
            this._bufferTail = aVar;
        }
        this._bufferedEntryCount += i;
        return _constructArray(i < 16384 ? i + i : i + (i >> 2));
    }

    public T completeAndClearBuffer(T t, int i) {
        int i2 = this._bufferedEntryCount + i;
        T _constructArray = _constructArray(i2);
        int i3 = 0;
        for (a<T> aVar = this._bufferHead; aVar != null; aVar = aVar.b()) {
            i3 = aVar.a(_constructArray, i3);
        }
        System.arraycopy(t, 0, _constructArray, i3, i);
        int i4 = i3 + i;
        if (i4 == i2) {
            return _constructArray;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Should have gotten ");
        sb.append(i2);
        sb.append(" entries, got ");
        sb.append(i4);
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void _reset() {
        if (this._bufferTail != null) {
            this._freeBuffer = this._bufferTail.a();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }
}
