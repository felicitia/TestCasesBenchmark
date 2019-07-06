package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class BERConstructedOctetString extends DEROctetString {
    private Vector octs;

    private static byte[] toBytes(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i != vector.size()) {
            try {
                byteArrayOutputStream.write(((DEROctetString) vector.elementAt(i)).getOctets());
                i++;
            } catch (ClassCastException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append(vector.elementAt(i).getClass().getName());
                sb.append(" found in input should only contain DEROctetString");
                throw new IllegalArgumentException(sb.toString());
            } catch (IOException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("exception converting octets ");
                sb2.append(e.toString());
                throw new IllegalArgumentException(sb2.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BERConstructedOctetString(byte[] bArr) {
        super(bArr);
    }

    public BERConstructedOctetString(Vector vector) {
        super(toBytes(vector));
        this.octs = vector;
    }

    public byte[] getOctets() {
        return this.string;
    }

    public Enumeration getObjects() {
        if (this.octs == null) {
            return generateOcts().elements();
        }
        return this.octs.elements();
    }

    private Vector generateOcts() {
        Vector vector = new Vector();
        int i = 0;
        while (i < this.string.length) {
            int i2 = i + 1000;
            byte[] bArr = new byte[((i2 > this.string.length ? this.string.length : i2) - i)];
            System.arraycopy(this.string, i, bArr, 0, bArr.length);
            vector.addElement(new DEROctetString(bArr));
            i = i2;
        }
        return vector;
    }

    public void encode(DEROutputStream dEROutputStream) throws IOException {
        if ((dEROutputStream instanceof ASN1OutputStream) || (dEROutputStream instanceof BEROutputStream)) {
            dEROutputStream.write(36);
            dEROutputStream.write(128);
            Enumeration objects = getObjects();
            while (objects.hasMoreElements()) {
                dEROutputStream.writeObject(objects.nextElement());
            }
            dEROutputStream.write(0);
            dEROutputStream.write(0);
            return;
        }
        super.encode(dEROutputStream);
    }
}
