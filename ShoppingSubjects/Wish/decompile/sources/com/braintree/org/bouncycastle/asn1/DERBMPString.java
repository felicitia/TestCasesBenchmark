package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public class DERBMPString extends ASN1Object {
    String string;

    public DERBMPString(byte[] bArr) {
        char[] cArr = new char[(bArr.length / 2)];
        for (int i = 0; i != cArr.length; i++) {
            int i2 = i * 2;
            cArr[i] = (char) ((bArr[i2 + 1] & 255) | (bArr[i2] << 8));
        }
        this.string = new String(cArr);
    }

    public String getString() {
        return this.string;
    }

    public String toString() {
        return this.string;
    }

    public int hashCode() {
        return getString().hashCode();
    }

    /* access modifiers changed from: protected */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERBMPString)) {
            return false;
        }
        return getString().equals(((DERBMPString) dERObject).getString());
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        char[] charArray = this.string.toCharArray();
        byte[] bArr = new byte[(charArray.length * 2)];
        for (int i = 0; i != charArray.length; i++) {
            int i2 = i * 2;
            bArr[i2] = (byte) (charArray[i] >> 8);
            bArr[i2 + 1] = (byte) charArray[i];
        }
        dEROutputStream.writeEncoded(30, bArr);
    }
}
