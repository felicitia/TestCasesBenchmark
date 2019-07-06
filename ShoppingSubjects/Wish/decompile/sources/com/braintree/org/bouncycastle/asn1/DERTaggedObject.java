package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public class DERTaggedObject extends ASN1TaggedObject {
    private static final byte[] ZERO_BYTES = new byte[0];

    public DERTaggedObject(int i, DEREncodable dEREncodable) {
        super(i, dEREncodable);
    }

    public DERTaggedObject(boolean z, int i, DEREncodable dEREncodable) {
        super(z, i, dEREncodable);
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        int i = 160;
        if (!this.empty) {
            byte[] encoded = this.obj.getDERObject().getEncoded("DER");
            if (this.explicit) {
                dEROutputStream.writeEncoded(160, this.tagNo, encoded);
                return;
            }
            if ((encoded[0] & 32) == 0) {
                i = 128;
            }
            dEROutputStream.writeTag(i, this.tagNo);
            dEROutputStream.write(encoded, 1, encoded.length - 1);
            return;
        }
        dEROutputStream.writeEncoded(160, this.tagNo, ZERO_BYTES);
    }
}
