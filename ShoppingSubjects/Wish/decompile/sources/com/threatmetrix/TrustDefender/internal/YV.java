package com.threatmetrix.TrustDefender.internal;

import android.os.Process;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class YV {

    /* renamed from: if reason: not valid java name */
    private static final String f709if = TL.m331if(YV.class);

    YV() {
    }

    /* renamed from: int reason: not valid java name */
    private static int m452int(char[] cArr, int i) {
        while (cArr[i] != ' ') {
            i++;
        }
        return i + 1;
    }

    /* renamed from: for reason: not valid java name */
    private static int m451for(char[] cArr, int i, int i2, FileReader fileReader) {
        if (i > 0) {
            System.arraycopy(cArr, i, cArr, 0, i2);
        }
        try {
            int read = fileReader.read(cArr, i2, 8192 - i2);
            if (read > 0) {
                return read;
            }
            fileReader.close();
            return -1;
        } catch (IOException unused) {
            return -1;
        }
    }

    /* renamed from: int reason: not valid java name */
    static I m453int() {
        String str = "libtdm-5.2-34-jni.so";
        char[] cArr = new char[8192];
        StringBuilder sb = new StringBuilder("/proc/");
        sb.append(Process.myPid());
        sb.append("/maps");
        try {
            FileReader fileReader = new FileReader(sb.toString());
            int i = 0;
            boolean z = false;
            int i2 = -1;
            int i3 = -1;
            int i4 = -1;
            int i5 = 0;
            int i6 = -1;
            boolean z2 = false;
            int i7 = 0;
            while (true) {
                if (fileReader == null && i < 0) {
                    break;
                }
                if (z) {
                    while (cArr[i5] != 10) {
                        try {
                            i5++;
                        } catch (ArrayIndexOutOfBoundsException unused) {
                        }
                    }
                    i5++;
                }
                i -= i5 - i7;
                i7 = i5;
                while (fileReader != null && i < 1024) {
                    int i8 = m451for(cArr, i7, i, fileReader);
                    if (i8 < 0) {
                        fileReader = null;
                    } else {
                        i += i8;
                    }
                    i7 = 0;
                }
                int i9 = m452int(cArr, i7);
                boolean z3 = false;
                while (cArr[i9] != ' ') {
                    if (cArr[i9] == 'x') {
                        z3 = true;
                    }
                    i9++;
                }
                int i10 = i9 + 1;
                if (z3) {
                    i5 = m452int(cArr, m452int(cArr, i10));
                    if (!(cArr[i5] == '0' && cArr[i5 + 1] == ' ')) {
                        int i11 = m452int(cArr, i5);
                        while (cArr[i11] == ' ') {
                            i11++;
                        }
                        int i12 = i11;
                        while (cArr[i12] != 10) {
                            i12++;
                        }
                        i5 = i12 + 1;
                        String str2 = new String(cArr, i11, i12 - i11);
                        if (str2.startsWith("/system")) {
                            i2++;
                            i4 = (int) (((long) i4) + new File(str2).length());
                        } else if (str2.startsWith("/dev")) {
                            i3++;
                        } else if (str2.endsWith(str)) {
                            z = false;
                            z2 = true;
                        } else {
                            i3++;
                            i6 = (int) (((long) i6) + new File(str2).length());
                            str = str;
                            cArr = cArr;
                        }
                        z = false;
                    }
                } else {
                    i5 = i10;
                }
                z = true;
            }
            I i13 = new I(i2, i4, i3, i6, z2);
            return i13;
        } catch (FileNotFoundException unused2) {
            I i14 = new I(-1, -1, -1, -1, false);
            return i14;
        }
    }
}
