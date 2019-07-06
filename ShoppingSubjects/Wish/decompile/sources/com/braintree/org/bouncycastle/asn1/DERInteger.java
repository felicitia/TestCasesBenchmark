package com.braintree.org.bouncycastle.asn1;

import com.braintree.org.bouncycastle.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;

public class DERInteger extends ASN1Object {
    byte[] bytes;

    public static DERInteger getInstance(Object obj) {
        if (obj == null || (obj instanceof DERInteger)) {
            return (DERInteger) obj;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("illegal object in getInstance: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public DERInteger(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public DERInteger(byte[] bArr) {
        this.bytes = bArr;
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(2, this.bytes);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 != this.bytes.length; i2++) {
            i ^= (this.bytes[i2] & 255) << (i2 % 4);
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERInteger)) {
            return false;
        }
        return Arrays.areEqual(this.bytes, ((DERInteger) dERObject).bytes);
    }

    public String toString() {
        return getValue().toString();
    }
}
