package io.branch.referral;

import com.etsy.android.lib.models.ResponseConstants;
import org.apache.commons.math3.dfp.Dfp;

/* compiled from: ApkParser */
class a {
    public static int a = 1048833;
    public static int b = 1048834;
    public static int c = 1048835;

    a() {
    }

    public String a(byte[] bArr) {
        int i;
        String str;
        int b2 = (b(bArr, 16) * 4) + 36;
        int b3 = b(bArr, 12);
        int i2 = b3;
        while (true) {
            if (i2 >= bArr.length - 4) {
                break;
            } else if (b(bArr, i2) == b) {
                b3 = i2;
                break;
            } else {
                i2 += 4;
            }
        }
        while (true) {
            if (i >= bArr.length) {
                break;
            }
            int b4 = b(bArr, i);
            if (b4 != b) {
                if (b4 != c) {
                    int i3 = a;
                    break;
                }
                i += 24;
            } else {
                int b5 = b(bArr, i + 28);
                i += 36;
                for (int i4 = 0; i4 < b5; i4++) {
                    int b6 = b(bArr, i + 4);
                    int b7 = b(bArr, i + 8);
                    int b8 = b(bArr, i + 16);
                    i += 20;
                    if (a(bArr, 36, b2, b6).equals("scheme")) {
                        if (b7 != -1) {
                            str = a(bArr, 36, b2, b7);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("resourceID 0x");
                            sb.append(Integer.toHexString(b8));
                            str = sb.toString();
                        }
                        if (a(str)) {
                            return str;
                        }
                    }
                }
                continue;
            }
        }
        return "bnc_no_value";
    }

    private boolean a(String str) {
        return str != null && !str.equals("http") && !str.equals("https") && !str.equals("geo") && !str.equals("*") && !str.equals("package") && !str.equals("sms") && !str.equals("smsto") && !str.equals("mms") && !str.equals("mmsto") && !str.equals("tel") && !str.equals("voicemail") && !str.equals(ResponseConstants.FILE) && !str.equals(ResponseConstants.CONTENT) && !str.equals("mailto");
    }

    public String a(byte[] bArr, int i, int i2, int i3) {
        if (i3 < 0) {
            return null;
        }
        return a(bArr, i2 + b(bArr, i + (i3 * 4)));
    }

    public String a(byte[] bArr, int i) {
        byte b2 = ((bArr[i + 1] << 8) & Dfp.FINITE) | (bArr[i] & 255);
        byte[] bArr2 = new byte[b2];
        for (int i2 = 0; i2 < b2; i2++) {
            bArr2[i2] = bArr[i + 2 + (i2 * 2)];
        }
        return new String(bArr2);
    }

    public int b(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 3] << 24) & Dfp.FINITE) | ((bArr[i + 2] << 16) & Dfp.FINITE) | ((bArr[i + 1] << 8) & Dfp.FINITE);
    }
}
