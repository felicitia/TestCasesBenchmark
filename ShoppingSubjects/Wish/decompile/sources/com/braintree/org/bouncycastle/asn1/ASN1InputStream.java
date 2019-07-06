package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASN1InputStream extends FilterInputStream {
    private final boolean lazyEvaluate;
    private final int limit;

    static int findLimit(InputStream inputStream) {
        if (inputStream instanceof LimitedInputStream) {
            return ((LimitedInputStream) inputStream).getRemaining();
        }
        if (inputStream instanceof ByteArrayInputStream) {
            return ((ByteArrayInputStream) inputStream).available();
        }
        return Integer.MAX_VALUE;
    }

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, findLimit(inputStream));
    }

    public ASN1InputStream(byte[] bArr) {
        this((InputStream) new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.limit = i;
        this.lazyEvaluate = z;
    }

    /* access modifiers changed from: protected */
    public int readLength() throws IOException {
        return readLength(this, this.limit);
    }

    /* access modifiers changed from: protected */
    public DERObject buildObject(int i, int i2, int i3) throws IOException {
        boolean z = (i & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i3);
        if ((i & 64) != 0) {
            return new DERApplicationSpecific(z, i2, definiteLengthInputStream.toByteArray());
        }
        if ((i & 128) != 0) {
            return new ASN1StreamParser(definiteLengthInputStream).readTaggedObject(z, i2);
        }
        if (!z) {
            return createPrimitiveDERObject(i2, definiteLengthInputStream.toByteArray());
        }
        if (i2 == 4) {
            return new BERConstructedOctetString(buildDEREncodableVector(definiteLengthInputStream).v);
        }
        if (i2 == 8) {
            return new DERExternal(buildDEREncodableVector(definiteLengthInputStream));
        }
        switch (i2) {
            case 16:
                if (this.lazyEvaluate) {
                    return new LazyDERSequence(definiteLengthInputStream.toByteArray());
                }
                return DERFactory.createSequence(buildDEREncodableVector(definiteLengthInputStream));
            case 17:
                return DERFactory.createSet(buildDEREncodableVector(definiteLengthInputStream), false);
            default:
                return new DERUnknownTag(true, i2, definiteLengthInputStream.toByteArray());
        }
    }

    /* access modifiers changed from: 0000 */
    public ASN1EncodableVector buildEncodableVector() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            DERObject readObject = readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(readObject);
        }
    }

    /* access modifiers changed from: 0000 */
    public ASN1EncodableVector buildDEREncodableVector(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        return new ASN1InputStream((InputStream) definiteLengthInputStream).buildEncodableVector();
    }

    public DERObject readObject() throws IOException {
        int read = read();
        if (read > 0) {
            int readTagNumber = readTagNumber(this, read);
            boolean z = (read & 32) != 0;
            int readLength = readLength();
            if (readLength >= 0) {
                try {
                    return buildObject(read, readTagNumber, readLength);
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            } else if (!z) {
                throw new IOException("indefinite length primitive encoding encountered");
            } else {
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit);
                if ((read & 64) != 0) {
                    return new BERApplicationSpecificParser(readTagNumber, aSN1StreamParser).getLoadedObject();
                }
                if ((read & 128) != 0) {
                    return new BERTaggedObjectParser(true, readTagNumber, aSN1StreamParser).getLoadedObject();
                }
                if (readTagNumber == 4) {
                    return new BEROctetStringParser(aSN1StreamParser).getLoadedObject();
                }
                if (readTagNumber == 8) {
                    return new DERExternalParser(aSN1StreamParser).getLoadedObject();
                }
                switch (readTagNumber) {
                    case 16:
                        return new BERSequenceParser(aSN1StreamParser).getLoadedObject();
                    case 17:
                        return new BERSetParser(aSN1StreamParser).getLoadedObject();
                    default:
                        throw new IOException("unknown BER object encountered");
                }
            }
        } else if (read != 0) {
            return null;
        } else {
            throw new IOException("unexpected end-of-contents marker");
        }
    }

    static int readTagNumber(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 != 31) {
            return i2;
        }
        int i3 = 0;
        int read = inputStream.read();
        if ((read & 127) == 0) {
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        while (read >= 0 && (read & 128) != 0) {
            i3 = (i3 | (read & 127)) << 7;
            read = inputStream.read();
        }
        if (read >= 0) {
            return i3 | (read & 127);
        }
        throw new EOFException("EOF found inside tag value.");
    }

    static int readLength(InputStream inputStream, int i) throws IOException {
        int read = inputStream.read();
        if (read < 0) {
            throw new EOFException("EOF found when length expected");
        } else if (read == 128) {
            return -1;
        } else {
            if (read > 127) {
                int i2 = read & 127;
                if (i2 > 4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("DER length more than 4 bytes: ");
                    sb.append(i2);
                    throw new IOException(sb.toString());
                }
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found reading length");
                    }
                    i3 = (i3 << 8) + read2;
                }
                if (i3 < 0) {
                    throw new IOException("corrupted stream - negative length found");
                } else if (i3 >= i) {
                    throw new IOException("corrupted stream - out of bounds length found");
                } else {
                    read = i3;
                }
            }
            return read;
        }
    }

    static DERObject createPrimitiveDERObject(int i, byte[] bArr) {
        switch (i) {
            case 1:
                return new ASN1Boolean(bArr);
            case 2:
                return new ASN1Integer(bArr);
            case 3:
                return DERBitString.fromOctetString(bArr);
            case 4:
                return new DEROctetString(bArr);
            case 5:
                return DERNull.INSTANCE;
            case 6:
                return new ASN1ObjectIdentifier(bArr);
            case 10:
                return new ASN1Enumerated(bArr);
            case 12:
                return new DERUTF8String(bArr);
            case 18:
                return new DERNumericString(bArr);
            case 19:
                return new DERPrintableString(bArr);
            case 20:
                return new DERT61String(bArr);
            case 22:
                return new DERIA5String(bArr);
            case 23:
                return new ASN1UTCTime(bArr);
            case 24:
                return new ASN1GeneralizedTime(bArr);
            case 26:
                return new DERVisibleString(bArr);
            case 27:
                return new DERGeneralString(bArr);
            case 28:
                return new DERUniversalString(bArr);
            case 30:
                return new DERBMPString(bArr);
            default:
                return new DERUnknownTag(false, i, bArr);
        }
    }
}
