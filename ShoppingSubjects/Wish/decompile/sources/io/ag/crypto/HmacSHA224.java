package io.ag.crypto;

import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.MacSpi;
import javax.crypto.SecretKey;

/* compiled from: GA */
public final class HmacSHA224 extends MacSpi implements Cloneable {
    private byte[] a;
    private byte[] b;
    private boolean c;
    private MessageDigest d;

    private HmacSHA224(MessageDigest messageDigest) {
        this.d = messageDigest;
        this.a = new byte[64];
        this.b = new byte[64];
        this.c = true;
    }

    public HmacSHA224() throws NoSuchAlgorithmException {
        this(MessageDigest.getInstance("SHA-224"));
    }

    /* access modifiers changed from: protected */
    public final int engineGetMacLength() {
        return this.d.getDigestLength();
    }

    /* access modifiers changed from: protected */
    public final void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameterSpec != null) {
            throw new InvalidAlgorithmParameterException("Invalid parameters");
        } else if (key == null || !(key instanceof SecretKey)) {
            throw new InvalidKeyException("Key is null");
        } else {
            byte[] encoded = key.getEncoded();
            if (encoded == null) {
                throw new InvalidKeyException("Key data is missing");
            }
            if (encoded.length > 64) {
                byte[] digest = this.d.digest(encoded);
                Arrays.fill(encoded, 0);
                encoded = digest;
            }
            int i = 0;
            while (i < 64) {
                byte b2 = i < encoded.length ? encoded[i] : 0;
                this.a[i] = (byte) (b2 ^ 54);
                this.b[i] = (byte) (b2 ^ 92);
                i++;
            }
            Arrays.fill(encoded, 0);
            engineReset();
        }
    }

    /* access modifiers changed from: protected */
    public final void engineUpdate(byte b2) {
        if (this.c) {
            this.c = false;
            this.d.update(this.a);
        }
        this.d.update(b2);
    }

    /* access modifiers changed from: protected */
    public final void engineUpdate(byte[] bArr, int i, int i2) {
        if (this.c) {
            this.c = false;
            this.d.update(this.a);
        }
        this.d.update(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public final void engineUpdate(ByteBuffer byteBuffer) {
        if (this.c) {
            this.d.update(this.a);
            this.c = false;
        }
        this.d.update(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public final byte[] engineDoFinal() {
        if (this.c) {
            this.d.update(this.a);
        } else {
            this.c = true;
        }
        try {
            byte[] digest = this.d.digest();
            this.d.update(this.b);
            this.d.update(digest);
            this.d.digest(digest, 0, digest.length);
            return digest;
        } catch (DigestException e) {
            throw new ProviderException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final void engineReset() {
        if (!this.c) {
            this.d.reset();
            this.c = true;
        }
    }

    public final Object clone() throws CloneNotSupportedException {
        HmacSHA224 hmacSHA224 = (HmacSHA224) super.clone();
        hmacSHA224.d = (MessageDigest) this.d.clone();
        hmacSHA224.a = (byte[]) this.a.clone();
        hmacSHA224.b = (byte[]) this.b.clone();
        return hmacSHA224;
    }
}
