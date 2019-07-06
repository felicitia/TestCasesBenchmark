package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1TaggedObject extends ASN1Object implements ASN1TaggedObjectParser {
    boolean empty;
    boolean explicit;
    DEREncodable obj;
    int tagNo;

    /* access modifiers changed from: 0000 */
    public abstract void encode(DEROutputStream dEROutputStream) throws IOException;

    public ASN1TaggedObject(int i, DEREncodable dEREncodable) {
        this.empty = false;
        this.explicit = true;
        this.obj = null;
        this.explicit = true;
        this.tagNo = i;
        this.obj = dEREncodable;
    }

    public ASN1TaggedObject(boolean z, int i, DEREncodable dEREncodable) {
        this.empty = false;
        this.explicit = true;
        this.obj = null;
        if (dEREncodable instanceof ASN1Choice) {
            this.explicit = true;
        } else {
            this.explicit = z;
        }
        this.tagNo = i;
        this.obj = dEREncodable;
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) dERObject;
        if (this.tagNo != aSN1TaggedObject.tagNo || this.empty != aSN1TaggedObject.empty || this.explicit != aSN1TaggedObject.explicit) {
            return false;
        }
        if (this.obj == null) {
            if (aSN1TaggedObject.obj != null) {
                return false;
            }
        } else if (!this.obj.getDERObject().equals(aSN1TaggedObject.obj.getDERObject())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.tagNo;
        return this.obj != null ? i ^ this.obj.hashCode() : i;
    }

    public int getTagNo() {
        return this.tagNo;
    }

    public DERObject getObject() {
        if (this.obj != null) {
            return this.obj.getDERObject();
        }
        return null;
    }

    public DERObject getLoadedObject() {
        return getDERObject();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.tagNo);
        sb.append("]");
        sb.append(this.obj);
        return sb.toString();
    }
}
