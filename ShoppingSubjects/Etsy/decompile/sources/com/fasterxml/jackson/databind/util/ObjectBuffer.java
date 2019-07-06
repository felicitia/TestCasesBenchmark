package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    private a _bufferHead;
    private a _bufferTail;
    private int _bufferedEntryCount;
    private Object[] _freeBuffer;

    static final class a {
        final Object[] a;
        a b;

        public a(Object[] objArr) {
            this.a = objArr;
        }

        public Object[] a() {
            return this.a;
        }

        public a b() {
            return this.b;
        }

        public void a(a aVar) {
            if (this.b != null) {
                throw new IllegalStateException();
            }
            this.b = aVar;
        }
    }

    public Object[] resetAndStart() {
        _reset();
        if (this._freeBuffer == null) {
            return new Object[12];
        }
        return this._freeBuffer;
    }

    public Object[] appendCompletedChunk(Object[] objArr) {
        a aVar = new a(objArr);
        if (this._bufferHead == null) {
            this._bufferTail = aVar;
            this._bufferHead = aVar;
        } else {
            this._bufferTail.a(aVar);
            this._bufferTail = aVar;
        }
        int length = objArr.length;
        this._bufferedEntryCount += length;
        return new Object[(length < 16384 ? length + length : length + (length >> 2))];
    }

    public Object[] completeAndClearBuffer(Object[] objArr, int i) {
        int i2 = this._bufferedEntryCount + i;
        Object[] objArr2 = new Object[i2];
        _copyTo(objArr2, i2, objArr, i);
        return objArr2;
    }

    public <T> T[] completeAndClearBuffer(Object[] objArr, int i, Class<T> cls) {
        int i2 = this._bufferedEntryCount + i;
        T[] tArr = (Object[]) Array.newInstance(cls, i2);
        _copyTo(tArr, i2, objArr, i);
        _reset();
        return tArr;
    }

    public void completeAndClearBuffer(Object[] objArr, int i, List<Object> list) {
        int i2;
        a aVar = this._bufferHead;
        while (true) {
            i2 = 0;
            if (aVar == null) {
                break;
            }
            Object[] a2 = aVar.a();
            int length = a2.length;
            while (i2 < length) {
                list.add(a2[i2]);
                i2++;
            }
            aVar = aVar.b();
        }
        while (i2 < i) {
            list.add(objArr[i2]);
            i2++;
        }
    }

    public int initialCapacity() {
        if (this._freeBuffer == null) {
            return 0;
        }
        return this._freeBuffer.length;
    }

    public int bufferedSize() {
        return this._bufferedEntryCount;
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

    /* access modifiers changed from: protected */
    public final void _copyTo(Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        for (a aVar = this._bufferHead; aVar != null; aVar = aVar.b()) {
            Object[] a2 = aVar.a();
            int length = a2.length;
            System.arraycopy(a2, 0, obj, i3, length);
            i3 += length;
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Should have gotten ");
            sb.append(i);
            sb.append(" entries, got ");
            sb.append(i4);
            throw new IllegalStateException(sb.toString());
        }
    }
}
