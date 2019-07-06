package com.braintree.org.bouncycastle.asn1;

import com.braintree.org.bouncycastle.util.io.Streams;
import java.io.IOException;
import java.io.InputStream;

public class BEROctetStringParser implements ASN1OctetStringParser {
    private ASN1StreamParser _parser;

    BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    public InputStream getOctetStream() {
        return new ConstructedOctetStream(this._parser);
    }

    public DERObject getLoadedObject() throws IOException {
        return new BERConstructedOctetString(Streams.readAll(getOctetStream()));
    }

    public DERObject getDERObject() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("IOException converting stream to byte array: ");
            sb.append(e.getMessage());
            throw new ASN1ParsingException(sb.toString(), e);
        }
    }
}
