package com.braintree.org.bouncycastle.asn1;

import java.util.Vector;

public class ASN1EncodableVector extends DEREncodableVector {
    Vector v = new Vector();

    public void add(DEREncodable dEREncodable) {
        this.v.addElement(dEREncodable);
    }

    public DEREncodable get(int i) {
        return (DEREncodable) this.v.elementAt(i);
    }

    public int size() {
        return this.v.size();
    }
}
