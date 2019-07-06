package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public abstract class DERObject extends ASN1Encodable {
    /* access modifiers changed from: 0000 */
    public abstract void encode(DEROutputStream dEROutputStream) throws IOException;

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public DERObject toASN1Object() {
        return this;
    }
}
