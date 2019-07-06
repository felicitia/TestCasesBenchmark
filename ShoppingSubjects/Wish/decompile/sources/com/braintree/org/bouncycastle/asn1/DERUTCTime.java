package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public class DERUTCTime extends ASN1Object {
    String time;

    DERUTCTime(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.time = new String(cArr);
    }

    private byte[] getOctets() {
        char[] charArray = this.time.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        return bArr;
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(23, getOctets());
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERUTCTime)) {
            return false;
        }
        return this.time.equals(((DERUTCTime) dERObject).time);
    }

    public int hashCode() {
        return this.time.hashCode();
    }

    public String toString() {
        return this.time;
    }
}
