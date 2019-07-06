package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DERExternal extends ASN1Object {
    private ASN1Object dataValueDescriptor;
    private DERObjectIdentifier directReference;
    private int encoding;
    private DERObject externalContent;
    private DERInteger indirectReference;

    public DERExternal(ASN1EncodableVector aSN1EncodableVector) {
        int i = 0;
        DERObject objFromVector = getObjFromVector(aSN1EncodableVector, 0);
        if (objFromVector instanceof DERObjectIdentifier) {
            this.directReference = (DERObjectIdentifier) objFromVector;
            objFromVector = getObjFromVector(aSN1EncodableVector, 1);
            i = 1;
        }
        if (objFromVector instanceof DERInteger) {
            this.indirectReference = (DERInteger) objFromVector;
            i++;
            objFromVector = getObjFromVector(aSN1EncodableVector, i);
        }
        if (!(objFromVector instanceof DERTaggedObject)) {
            this.dataValueDescriptor = (ASN1Object) objFromVector;
            i++;
            objFromVector = getObjFromVector(aSN1EncodableVector, i);
        }
        if (aSN1EncodableVector.size() != i + 1) {
            throw new IllegalArgumentException("input vector too large");
        } else if (!(objFromVector instanceof DERTaggedObject)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        } else {
            DERTaggedObject dERTaggedObject = (DERTaggedObject) objFromVector;
            setEncoding(dERTaggedObject.getTagNo());
            this.externalContent = dERTaggedObject.getObject();
        }
    }

    private DERObject getObjFromVector(ASN1EncodableVector aSN1EncodableVector, int i) {
        if (aSN1EncodableVector.size() > i) {
            return aSN1EncodableVector.get(i).getDERObject();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public int hashCode() {
        int hashCode = this.directReference != null ? this.directReference.hashCode() : 0;
        if (this.indirectReference != null) {
            hashCode ^= this.indirectReference.hashCode();
        }
        if (this.dataValueDescriptor != null) {
            hashCode ^= this.dataValueDescriptor.hashCode();
        }
        return hashCode ^ this.externalContent.hashCode();
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.directReference != null) {
            byteArrayOutputStream.write(this.directReference.getDEREncoded());
        }
        if (this.indirectReference != null) {
            byteArrayOutputStream.write(this.indirectReference.getDEREncoded());
        }
        if (this.dataValueDescriptor != null) {
            byteArrayOutputStream.write(this.dataValueDescriptor.getDEREncoded());
        }
        byteArrayOutputStream.write(new DERTaggedObject(this.encoding, this.externalContent).getDEREncoded());
        dEROutputStream.writeEncoded(32, 8, byteArrayOutputStream.toByteArray());
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERExternal)) {
            return false;
        }
        if (this == dERObject) {
            return true;
        }
        DERExternal dERExternal = (DERExternal) dERObject;
        if (this.directReference != null && (dERExternal.directReference == null || !dERExternal.directReference.equals(this.directReference))) {
            return false;
        }
        if (this.indirectReference != null && (dERExternal.indirectReference == null || !dERExternal.indirectReference.equals(this.indirectReference))) {
            return false;
        }
        if (this.dataValueDescriptor == null || (dERExternal.dataValueDescriptor != null && dERExternal.dataValueDescriptor.equals(this.dataValueDescriptor))) {
            return this.externalContent.equals(dERExternal.externalContent);
        }
        return false;
    }

    private void setEncoding(int i) {
        if (i < 0 || i > 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid encoding value: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        this.encoding = i;
    }
}
