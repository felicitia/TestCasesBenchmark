package com.braintree.org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, ASN1InputStream.findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i) {
        this._in = inputStream;
        this._limit = i;
    }

    /* access modifiers changed from: 0000 */
    public DEREncodable readIndef(int i) throws IOException {
        if (i == 4) {
            return new BEROctetStringParser(this);
        }
        if (i == 8) {
            return new DERExternalParser(this);
        }
        switch (i) {
            case 16:
                return new BERSequenceParser(this);
            case 17:
                return new BERSetParser(this);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("unknown BER object encountered: 0x");
                sb.append(Integer.toHexString(i));
                throw new ASN1Exception(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public DERObject readTaggedObject(boolean z, int i) throws IOException {
        if (!z) {
            return new DERTaggedObject(false, i, new DEROctetString(((DefiniteLengthInputStream) this._in).toByteArray()));
        }
        ASN1EncodableVector readVector = readVector();
        if (this._in instanceof IndefiniteLengthInputStream) {
            return readVector.size() == 1 ? new BERTaggedObject(true, i, readVector.get(0)) : new BERTaggedObject(false, i, BERFactory.createSequence(readVector));
        }
        return readVector.size() == 1 ? new DERTaggedObject(true, i, readVector.get(0)) : new DERTaggedObject(false, i, DERFactory.createSequence(readVector));
    }

    public DEREncodable readObject() throws IOException {
        int read = this._in.read();
        if (read == -1) {
            return null;
        }
        boolean z = false;
        set00Check(false);
        int readTagNumber = ASN1InputStream.readTagNumber(this._in, read);
        if ((read & 32) != 0) {
            z = true;
        }
        int readLength = ASN1InputStream.readLength(this._in, this._limit);
        if (readLength >= 0) {
            DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, readLength);
            if ((read & 64) != 0) {
                return new DERApplicationSpecific(z, readTagNumber, definiteLengthInputStream.toByteArray());
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(z, readTagNumber, new ASN1StreamParser(definiteLengthInputStream));
            }
            if (z) {
                if (readTagNumber == 4) {
                    return new BEROctetStringParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                if (readTagNumber == 8) {
                    return new DERExternalParser(new ASN1StreamParser(definiteLengthInputStream));
                }
                switch (readTagNumber) {
                    case 16:
                        return new DERSequenceParser(new ASN1StreamParser(definiteLengthInputStream));
                    case 17:
                        return new DERSetParser(new ASN1StreamParser(definiteLengthInputStream));
                    default:
                        return new DERUnknownTag(true, readTagNumber, definiteLengthInputStream.toByteArray());
                }
            } else if (readTagNumber == 4) {
                return new DEROctetStringParser(definiteLengthInputStream);
            } else {
                try {
                    return ASN1InputStream.createPrimitiveDERObject(readTagNumber, definiteLengthInputStream.toByteArray());
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            }
        } else if (!z) {
            throw new IOException("indefinite length primitive encoding encountered");
        } else {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this._in, this._limit), this._limit);
            if ((read & 64) != 0) {
                return new BERApplicationSpecificParser(readTagNumber, aSN1StreamParser);
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(true, readTagNumber, aSN1StreamParser);
            }
            return aSN1StreamParser.readIndef(readTagNumber);
        }
    }

    private void set00Check(boolean z) {
        if (this._in instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) this._in).setEofOn00(z);
        }
    }

    /* access modifiers changed from: 0000 */
    public ASN1EncodableVector readVector() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            DEREncodable readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            if (readObject instanceof InMemoryRepresentable) {
                aSN1EncodableVector.add(((InMemoryRepresentable) readObject).getLoadedObject());
            } else {
                aSN1EncodableVector.add(readObject.getDERObject());
            }
        }
    }
}
