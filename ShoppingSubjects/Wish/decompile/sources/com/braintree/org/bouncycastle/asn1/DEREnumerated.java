package com.braintree.org.bouncycastle.asn1;

import com.braintree.org.bouncycastle.util.Arrays;
import java.io.IOException;

public class DEREnumerated extends ASN1Object {
    byte[] bytes;

    public DEREnumerated(byte[] bArr) {
        this.bytes = bArr;
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(10, this.bytes);
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DEREnumerated)) {
            return false;
        }
        return Arrays.areEqual(this.bytes, ((DEREnumerated) dERObject).bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }
}
