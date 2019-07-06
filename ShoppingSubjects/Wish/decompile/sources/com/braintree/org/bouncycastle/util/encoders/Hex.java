package com.braintree.org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hex {
    private static final Encoder encoder = new HexEncoder();

    public static byte[] encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static byte[] encode(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.encode(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("exception encoding Hex string: ");
            sb.append(e);
            throw new RuntimeException(sb.toString());
        }
    }
}
