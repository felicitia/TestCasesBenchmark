package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public abstract class ASN1Sequence extends ASN1Object {
    private Vector seq = new Vector();

    /* access modifiers changed from: 0000 */
    public abstract void encode(DEROutputStream dEROutputStream) throws IOException;

    public Enumeration getObjects() {
        return this.seq.elements();
    }

    public int size() {
        return this.seq.size();
    }

    public int hashCode() {
        Enumeration objects = getObjects();
        int size = size();
        while (objects.hasMoreElements()) {
            size = (size * 17) ^ getNext(objects).hashCode();
        }
        return size;
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Sequence aSN1Sequence = (ASN1Sequence) dERObject;
        if (size() != aSN1Sequence.size()) {
            return false;
        }
        Enumeration objects = getObjects();
        Enumeration objects2 = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            DEREncodable next = getNext(objects);
            DEREncodable next2 = getNext(objects2);
            DERObject dERObject2 = next.getDERObject();
            DERObject dERObject3 = next2.getDERObject();
            if (dERObject2 != dERObject3 && !dERObject2.equals(dERObject3)) {
                return false;
            }
        }
        return true;
    }

    private DEREncodable getNext(Enumeration enumeration) {
        DEREncodable dEREncodable = (DEREncodable) enumeration.nextElement();
        return dEREncodable == null ? DERNull.INSTANCE : dEREncodable;
    }

    /* access modifiers changed from: protected */
    public void addObject(DEREncodable dEREncodable) {
        this.seq.addElement(dEREncodable);
    }

    public String toString() {
        return this.seq.toString();
    }
}
