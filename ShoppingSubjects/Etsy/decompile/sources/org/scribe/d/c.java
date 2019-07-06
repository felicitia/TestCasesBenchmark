package org.scribe.d;

import javax.xml.bind.DatatypeConverter;

/* compiled from: DatatypeConverterEncoder */
public class c extends a {
    public String a(byte[] bArr) {
        return DatatypeConverter.printBase64Binary(bArr);
    }
}
