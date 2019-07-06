package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public class DERBoolean extends ASN1Object {
    public static final DERBoolean FALSE = new DERBoolean(false);
    public static final DERBoolean TRUE = new DERBoolean(true);
    byte value;

    public DERBoolean(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        }
        this.value = bArr[0];
    }

    public DERBoolean(boolean z) {
        this.value = z ? (byte) -1 : 0;
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(1, new byte[]{this.value});
    }

    /* access modifiers changed from: protected */
    public boolean asn1Equals(DERObject dERObject) {
        boolean z = false;
        if (dERObject == null || !(dERObject instanceof DERBoolean)) {
            return false;
        }
        if (this.value == ((DERBoolean) dERObject).value) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.value;
    }

    public String toString() {
        return this.value != 0 ? "TRUE" : "FALSE";
    }
}
