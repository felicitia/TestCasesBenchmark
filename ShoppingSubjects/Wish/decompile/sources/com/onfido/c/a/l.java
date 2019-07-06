package com.onfido.c.a;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

abstract class l implements Closeable {

    interface a {
        boolean a(InputStream inputStream, int i);
    }

    static class b extends l {
        final LinkedList<byte[]> a = new LinkedList<>();

        b() {
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.a.size();
        }

        /* access modifiers changed from: 0000 */
        public void a(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                this.a.remove();
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar) {
            int i = 0;
            while (i < this.a.size()) {
                byte[] bArr = (byte[]) this.a.get(i);
                if (aVar.a(new ByteArrayInputStream(bArr), bArr.length)) {
                    i++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(byte[] bArr) {
            this.a.add(bArr);
        }

        public void close() {
        }
    }

    static class c extends l {
        final o a;

        c(o oVar) {
            this.a = oVar;
        }

        /* access modifiers changed from: 0000 */
        public int a() {
            return this.a.b();
        }

        /* access modifiers changed from: 0000 */
        public void a(int i) {
            try {
                this.a.b(i);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IOException(e);
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(a aVar) {
            this.a.a(aVar);
        }

        /* access modifiers changed from: 0000 */
        public void a(byte[] bArr) {
            this.a.a(bArr);
        }

        public void close() {
            this.a.close();
        }
    }

    l() {
    }

    /* access modifiers changed from: 0000 */
    public abstract int a();

    /* access modifiers changed from: 0000 */
    public abstract void a(int i);

    /* access modifiers changed from: 0000 */
    public abstract void a(a aVar);

    /* access modifiers changed from: 0000 */
    public abstract void a(byte[] bArr);
}
