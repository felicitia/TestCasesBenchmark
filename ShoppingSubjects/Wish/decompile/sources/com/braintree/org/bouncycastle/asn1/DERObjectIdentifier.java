package com.braintree.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class DERObjectIdentifier extends ASN1Object {
    String identifier;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        r10 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    DERObjectIdentifier(byte[] r17) {
        /*
            r16 = this;
            r0 = r17
            r16.<init>()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r2 = 0
            r3 = 1
            r3 = 0
            r7 = 0
            r9 = 0
            r10 = 1
        L_0x0011:
            int r11 = r0.length
            if (r3 == r11) goto L_0x0083
            byte r11 = r0[r3]
            r11 = r11 & 255(0xff, float:3.57E-43)
            r12 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            int r14 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            r12 = 46
            if (r14 >= 0) goto L_0x005b
            r13 = 128(0x80, double:6.32E-322)
            long r7 = r7 * r13
            r13 = r11 & 127(0x7f, float:1.78E-43)
            long r13 = (long) r13
            long r4 = r7 + r13
            r6 = r11 & 128(0x80, float:1.794E-43)
            if (r6 != 0) goto L_0x0059
            if (r10 == 0) goto L_0x0050
            int r6 = (int) r4
            int r6 = r6 / 40
            switch(r6) {
                case 0: goto L_0x004a;
                case 1: goto L_0x0040;
                default: goto L_0x0035;
            }
        L_0x0035:
            r6 = 50
            r1.append(r6)
            r6 = 80
            long r10 = r4 - r6
        L_0x003e:
            r4 = r10
            goto L_0x004f
        L_0x0040:
            r6 = 49
            r1.append(r6)
            r6 = 40
            long r10 = r4 - r6
            goto L_0x003e
        L_0x004a:
            r6 = 48
            r1.append(r6)
        L_0x004f:
            r10 = 0
        L_0x0050:
            r1.append(r12)
            r1.append(r4)
            r7 = 0
            goto L_0x0080
        L_0x0059:
            r7 = r4
            goto L_0x0080
        L_0x005b:
            if (r9 != 0) goto L_0x0061
            java.math.BigInteger r9 = java.math.BigInteger.valueOf(r7)
        L_0x0061:
            r4 = 7
            java.math.BigInteger r4 = r9.shiftLeft(r4)
            r5 = r11 & 127(0x7f, float:1.78E-43)
            long r5 = (long) r5
            java.math.BigInteger r5 = java.math.BigInteger.valueOf(r5)
            java.math.BigInteger r4 = r4.or(r5)
            r5 = r11 & 128(0x80, float:1.794E-43)
            if (r5 != 0) goto L_0x007f
            r1.append(r12)
            r1.append(r4)
            r7 = 0
            r9 = 0
            goto L_0x0080
        L_0x007f:
            r9 = r4
        L_0x0080:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x0083:
            java.lang.String r0 = r1.toString()
            r1 = r16
            r1.identifier = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintree.org.bouncycastle.asn1.DERObjectIdentifier.<init>(byte[]):void");
    }

    public String getId() {
        return this.identifier;
    }

    private void writeField(OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) ((((int) j) & 127) | 128);
        }
        outputStream.write(bArr, i, 9 - i);
    }

    private void writeField(OutputStream outputStream, BigInteger bigInteger) throws IOException {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            outputStream.write(0);
            return;
        }
        byte[] bArr = new byte[bitLength];
        int i = bitLength - 1;
        BigInteger bigInteger2 = bigInteger;
        for (int i2 = i; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((bigInteger2.intValue() & 127) | 128);
            bigInteger2 = bigInteger2.shiftRight(7);
        }
        bArr[i] = (byte) (bArr[i] & Byte.MAX_VALUE);
        outputStream.write(bArr);
    }

    /* access modifiers changed from: 0000 */
    public void encode(DEROutputStream dEROutputStream) throws IOException {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream2 = new DEROutputStream(byteArrayOutputStream);
        writeField((OutputStream) byteArrayOutputStream, (long) ((Integer.parseInt(oIDTokenizer.nextToken()) * 40) + Integer.parseInt(oIDTokenizer.nextToken())));
        while (oIDTokenizer.hasMoreTokens()) {
            String nextToken = oIDTokenizer.nextToken();
            if (nextToken.length() < 18) {
                writeField((OutputStream) byteArrayOutputStream, Long.parseLong(nextToken));
            } else {
                writeField((OutputStream) byteArrayOutputStream, new BigInteger(nextToken));
            }
        }
        dEROutputStream2.close();
        dEROutputStream.writeEncoded(6, byteArrayOutputStream.toByteArray());
    }

    public int hashCode() {
        return this.identifier.hashCode();
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(DERObject dERObject) {
        if (!(dERObject instanceof DERObjectIdentifier)) {
            return false;
        }
        return this.identifier.equals(((DERObjectIdentifier) dERObject).identifier);
    }

    public String toString() {
        return getId();
    }
}
