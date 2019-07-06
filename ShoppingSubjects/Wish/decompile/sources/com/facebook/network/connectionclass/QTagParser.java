package com.facebook.network.connectionclass;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

class QTagParser {
    public static QTagParser sInstance = null;
    private static final ThreadLocal<byte[]> sLineBuffer = new ThreadLocal<byte[]>() {
        public byte[] initialValue() {
            return new byte[512];
        }
    };
    private static long sPreviousBytes = -1;
    private static ByteArrayScanner sScanner = new ByteArrayScanner();
    private static LineBufferReader sStatsReader = new LineBufferReader();
    private String mPath;

    public static synchronized QTagParser getInstance() {
        QTagParser qTagParser;
        synchronized (QTagParser.class) {
            if (sInstance == null) {
                sInstance = new QTagParser("/proc/net/xt_qtaguid/stats");
            }
            qTagParser = sInstance;
        }
        return qTagParser;
    }

    public QTagParser(String str) {
        this.mPath = str;
    }

    public long parseDataUsageForUidAndTag(int i) {
        int i2;
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        long j = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(this.mPath);
            sStatsReader.setFileStream(fileInputStream);
            byte[] bArr = (byte[]) sLineBuffer.get();
            try {
                sStatsReader.skipLine();
                i2 = 2;
                while (true) {
                    int readLine = sStatsReader.readLine(bArr);
                    if (readLine == -1) {
                        break;
                    }
                    sScanner.reset(bArr, readLine);
                    sScanner.useDelimiter(' ');
                    sScanner.skip();
                    if (!sScanner.nextStringEquals("lo")) {
                        sScanner.skip();
                        if (sScanner.nextInt() == i) {
                            sScanner.skip();
                            i2++;
                            j += (long) sScanner.nextInt();
                        }
                    }
                }
                fileInputStream.close();
                if (sPreviousBytes == -1) {
                    sPreviousBytes = j;
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    return -1;
                }
                long j2 = j - sPreviousBytes;
                sPreviousBytes = j;
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return j2;
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Cannot parse byte count at line");
                sb.append(i2);
                sb.append(".");
                Log.e("QTagParser", sb.toString());
            } catch (NoSuchElementException unused2) {
                String str = "QTagParser";
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid number of tokens on line ");
                sb2.append(i2);
                sb2.append(".");
                Log.e(str, sb2.toString());
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        } catch (IOException unused3) {
            try {
                Log.e("QTagParser", "Error reading from /proc/net/xt_qtaguid/stats. Please check if this file exists.");
                return -1;
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
    }
}
