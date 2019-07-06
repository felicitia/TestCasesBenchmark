package com.braintree.org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64 {
    private static final Encoder encoder = new Base64Encoder();

    public static byte[] encode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((bArr.length + 2) / 3) * 4);
        try {
            encoder.encode(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("exception encoding base64 string: ");
            sb.append(e);
            throw new RuntimeException(sb.toString());
        }
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((str.length() / 4) * 3);
        try {
            encoder.decode(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("exception decoding base64 string: ");
            sb.append(e);
            throw new RuntimeException(sb.toString());
        }
    }
}
