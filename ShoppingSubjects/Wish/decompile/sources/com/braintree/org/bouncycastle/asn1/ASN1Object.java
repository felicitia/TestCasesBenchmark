package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1Object extends DERObject {
    /* access modifiers changed from: 0000 */
    public abstract boolean asn1Equals(DERObject dERObject);

    /* access modifiers changed from: 0000 */
    public abstract void encode(DEROutputStream dEROutputStream) throws IOException;

    public abstract int hashCode();

    public final boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DEREncodable) || !asn1Equals(((DEREncodable) obj).getDERObject())) {
            z = false;
        }
        return z;
    }
}
