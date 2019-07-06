package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class LazyDERSequence extends DERSequence {
    private byte[] encoded;
    private boolean parsed = false;
    private int size = -1;

    LazyDERSequence(byte[] bArr) throws IOException {
        this.encoded = bArr;
    }

    public synchronized Enumeration getObjects() {
        if (this.parsed) {
            return super.getObjects();
        }
        return new LazyDERConstructionEnumeration(this.encoded);
    }

    public int size() {
        if (this.size < 0) {
            LazyDERConstructionEnumeration lazyDERConstructionEnumeration = new LazyDERConstructionEnumeration(this.encoded);
            this.size = 0;
            while (lazyDERConstructionEnumeration.hasMoreElements()) {
                lazyDERConstructionEnumeration.nextElement();
                this.size++;
            }
        }
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(48, this.encoded);
    }
}
