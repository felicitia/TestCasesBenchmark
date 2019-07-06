package com.braintree.org.bouncycastle.asn1;

import com.braintree.org.bouncycastle.util.Arrays;
import java.io.IOException;

public class DERUnknownTag extends DERObject {
    private byte[] data;
    private boolean isConstructed;
    private int tag;

    public DERUnknownTag(boolean z, int i, byte[] bArr) {
        this.isConstructed = z;
        this.tag = i;
        this.data = bArr;
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(this.isConstructed ? 32 : 0, this.tag, this.data);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof DERUnknownTag)) {
            return false;
        }
        DERUnknownTag dERUnknownTag = (DERUnknownTag) obj;
        if (this.isConstructed == dERUnknownTag.isConstructed && this.tag == dERUnknownTag.tag && Arrays.areEqual(this.data, dERUnknownTag.data)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return ((this.isConstructed ? -1 : 0) ^ this.tag) ^ Arrays.hashCode(this.data);
    }
}
