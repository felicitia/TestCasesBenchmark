package com.crittercism.app;

import android.content.Context;
import com.crittercism.internal.cm;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CrittercismNDK {
    private static final String ASSET_SO_FILE_NAME = "lib64libcrittercism-v3.crt";
    private static final String DST_SO_FILE_NAME = "lib64libcrittercism-v3.so";
    private static final String[] LEGACY_SO_FILE_NAMES = {"libcrittercism-ndk.so", "libcrittercism-v3.so"};
    private static final String LIBRARY_NAME = "64libcrittercism-v3";
    private static boolean isNdkInstalled = false;

    public static native boolean installNdk(String str);

    public static File getInstalledLibraryFile(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().getAbsolutePath());
        sb.append("/com.crittercism/lib/");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(DST_SO_FILE_NAME);
        return new File(sb3.toString());
    }

    public static File crashDumpDirectory(Context context) {
        return new File(context.getFilesDir().getAbsolutePath(), "/com.crittercism/dumps");
    }

    public static void installNdkLib(Context context) {
        boolean z;
        if (doNdkSharedLibrariesExist(context)) {
            z = loadLibraryFromAssets(context);
        } else {
            try {
                System.loadLibrary(LIBRARY_NAME);
                z = true;
            } catch (Throwable unused) {
                z = false;
            }
        }
        if (!z) {
            cm.d("did not load NDK library.");
            return;
        }
        cm.d("loaded NDK library.");
        try {
            File crashDumpDirectory = crashDumpDirectory(context);
            if (installNdk(crashDumpDirectory.getAbsolutePath())) {
                crashDumpDirectory.mkdirs();
                isNdkInstalled = true;
                cm.c("initialized NDK crash reporting.");
                return;
            }
            cm.b("Unable to initialize NDK crash reporting.");
        } catch (Throwable th) {
            cm.a(th);
        }
    }

    public static boolean loadLibraryFromAssets(Context context) {
        File file = new File(context.getFilesDir(), "/com.crittercism/lib/");
        File file2 = new File(file, DST_SO_FILE_NAME);
        boolean z = false;
        if (!file2.exists()) {
            if (!copyLibFromAssets(context, file2)) {
                file2.delete();
                return false;
            }
            for (String file3 : LEGACY_SO_FILE_NAMES) {
                File file4 = new File(file, file3);
                String str = file4.exists() ? "deleting" : "not found";
                StringBuilder sb = new StringBuilder("legacy lib: ");
                sb.append(file4.getAbsolutePath());
                sb.append(": ");
                sb.append(str);
                cm.d(sb.toString());
                file4.delete();
            }
        }
        try {
            System.load(file2.getAbsolutePath());
            z = true;
        } catch (Throwable th) {
            StringBuilder sb2 = new StringBuilder("Unable to install NDK library: ");
            sb2.append(th.getMessage());
            cm.a(sb2.toString());
            cm.a(th);
            file2.delete();
        }
        return z;
    }

    public static boolean doNdkSharedLibrariesExist(Context context) {
        try {
            getJarredLibFileStream(context);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static InputStream getJarredLibFileStream(Context context) {
        String str = "armeabi";
        String property = System.getProperty("os.arch");
        StringBuilder sb = new StringBuilder("getJarredLibFileStream: os.arch: ");
        sb.append(property);
        cm.d(sb.toString());
        if (property.contains("v7")) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("-v7a");
            str = sb2.toString();
        } else if (property.equals("aarch64")) {
            str = "arm64-v8a";
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append("/lib64libcrittercism-v3.crt");
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder("getJarredLibFileStream: openning input stream from: ");
        sb5.append(sb4);
        cm.d(sb5.toString());
        return context.getAssets().open(sb4);
    }

    public static boolean copyLibFromAssets(Context context, File file) {
        try {
            File parentFile = file.getParentFile();
            StringBuilder sb = new StringBuilder("copyLibFromAssets: creating dir: ");
            sb.append(parentFile.getAbsolutePath());
            cm.d(sb.toString());
            parentFile.mkdirs();
            StringBuilder sb2 = new StringBuilder("copyLibFromAssets: installing library into: ");
            sb2.append(file.getAbsolutePath());
            cm.d(sb2.toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream jarredLibFileStream = getJarredLibFileStream(context);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = jarredLibFileStream.read(bArr);
                if (read >= 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    jarredLibFileStream.close();
                    fileOutputStream.close();
                    cm.d("copyLibFromAssets: successful");
                    return true;
                }
            }
        } catch (Exception e) {
            StringBuilder sb3 = new StringBuilder("Could not install breakpad library: ");
            sb3.append(e.toString());
            cm.a(sb3.toString());
            return false;
        }
    }

    public static boolean isNdkCrashReportingInstalled() {
        return isNdkInstalled;
    }
}
