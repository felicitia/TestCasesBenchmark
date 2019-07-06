package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.util.Assertions;

public final class TrackEncryptionBox {
    public final CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final int initializationVectorSize;
    public final boolean isEncrypted;
    public final String schemeType;

    public TrackEncryptionBox(boolean z, String str, int i, byte[] bArr, int i2, int i3, byte[] bArr2) {
        boolean z2 = false;
        boolean z3 = i == 0;
        if (bArr2 == null) {
            z2 = true;
        }
        Assertions.checkArgument(z2 ^ z3);
        this.isEncrypted = z;
        this.schemeType = str;
        this.initializationVectorSize = i;
        this.defaultInitializationVector = bArr2;
        this.cryptoData = new CryptoData(schemeToCryptoMode(str), bArr, i2, i3);
    }

    private static int schemeToCryptoMode(String str) {
        if (str == null) {
            return 1;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 3046605) {
            if (hashCode != 3046671) {
                if (hashCode != 3049879) {
                    if (hashCode == 3049895 && str.equals("cens")) {
                        c = 1;
                    }
                } else if (str.equals("cenc")) {
                    c = 0;
                }
            } else if (str.equals("cbcs")) {
                c = 3;
            }
        } else if (str.equals("cbc1")) {
            c = 2;
        }
        switch (c) {
            case 0:
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unsupported protection scheme type '");
                sb.append(str);
                sb.append("'. Assuming AES-CTR crypto mode.");
                Log.w("TrackEncryptionBox", sb.toString());
                return 1;
        }
    }
}
