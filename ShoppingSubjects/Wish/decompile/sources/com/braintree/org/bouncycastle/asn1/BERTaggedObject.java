package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class BERTaggedObject extends DERTaggedObject {
    public BERTaggedObject(boolean z, int i, DEREncodable dEREncodable) {
        super(z, i, dEREncodable);
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        Enumeration enumeration;
        if ((dEROutputStream instanceof ASN1OutputStream) || (dEROutputStream instanceof BEROutputStream)) {
            dEROutputStream.writeTag(160, this.tagNo);
            dEROutputStream.write(128);
            if (!this.empty) {
                if (!this.explicit) {
                    if (this.obj instanceof ASN1OctetString) {
                        if (this.obj instanceof BERConstructedOctetString) {
                            enumeration = ((BERConstructedOctetString) this.obj).getObjects();
                        } else {
                            enumeration = new BERConstructedOctetString(((ASN1OctetString) this.obj).getOctets()).getObjects();
                        }
                    } else if (this.obj instanceof ASN1Sequence) {
                        enumeration = ((ASN1Sequence) this.obj).getObjects();
                    } else if (this.obj instanceof ASN1Set) {
                        enumeration = ((ASN1Set) this.obj).getObjects();
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("not implemented: ");
                        sb.append(this.obj.getClass().getName());
                        throw new RuntimeException(sb.toString());
                    }
                    while (enumeration.hasMoreElements()) {
                        dEROutputStream.writeObject(enumeration.nextElement());
                    }
                } else {
                    dEROutputStream.writeObject(this.obj);
                }
            }
            dEROutputStream.write(0);
            dEROutputStream.write(0);
            return;
        }
        super.encode(dEROutputStream);
    }
}
