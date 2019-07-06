package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

class LazyDERConstructionEnumeration implements Enumeration {
    private ASN1InputStream aIn;
    private Object nextObj = readObject();

    public LazyDERConstructionEnumeration(byte[] bArr) {
        this.aIn = new ASN1InputStream(bArr, true);
    }

    public boolean hasMoreElements() {
        return this.nextObj != null;
    }

    public Object nextElement() {
        Object obj = this.nextObj;
        this.nextObj = readObject();
        return obj;
    }

    private Object readObject() {
        try {
            return this.aIn.readObject();
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("malformed DER construction: ");
            sb.append(e);
            throw new ASN1ParsingException(sb.toString(), e);
        }
    }
}
