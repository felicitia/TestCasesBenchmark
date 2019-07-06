package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

public class DERSet extends ASN1Set {
    public DERSet() {
    }

    DERSet(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        for (int i = 0; i != aSN1EncodableVector.size(); i++) {
            addObject(aSN1EncodableVector.get(i));
        }
        if (z) {
            sort();
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
        dEROutputStream.writeEncoded(49, byteArrayOutputStream.toByteArray());
    }
}
