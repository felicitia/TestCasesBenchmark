package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

public class DERSequence extends ASN1Sequence {
    public DERSequence() {
    }

    public DERSequence(ASN1EncodableVector aSN1EncodableVector) {
        for (int i = 0; i != aSN1EncodableVector.size(); i++) {
            addObject(aSN1EncodableVector.get(i));
        }
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream2 = new DEROutputStream(byteArrayOutputStream);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            dEROutputStream2.writeObject(objects.nextElement());
        }
        dEROutputStream2.close();
        dEROutputStream.writeEncoded(48, byteArrayOutputStream.toByteArray());
    }
}
