package com.braintree.org.bouncycastle.asn1;

import com.braintree.org.bouncycastle.util.Strings;
import java.io.IOException;

public class DERUTF8String extends ASN1Object {
    String string;

    public DERUTF8String(byte[] bArr) {
        try {
            this.string = Strings.fromUTF8ByteArray(bArr);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("UTF8 encoding invalid");
        }
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

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERUTF8String)) {
            return false;
        }
        return getString().equals(((DERUTF8String) dERObject).getString());
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(12, Strings.toUTF8ByteArray(this.string));
    }
}
