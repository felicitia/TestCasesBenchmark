package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public class DERIA5String extends ASN1Object {
    String string;

    public DERIA5String(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.string = new String(cArr);
    }

    public String getString() {
        return this.string;
    }

    public String toString() {
        return this.string;
    }

    public byte[] getOctets() {
        char[] charArray = this.string.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        return bArr;
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(22, getOctets());
    }

    public int hashCode() {
        return getString().hashCode();
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERIA5String)) {
            return false;
        }
        return getString().equals(((DERIA5String) dERObject).getString());
    }
}
