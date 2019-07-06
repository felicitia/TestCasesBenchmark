package com.threatmetrix.TrustDefender;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.Keep;
import com.threatmetrix.TrustDefender.internal.TL;

@Keep
public class NativeGathererHelper {

    /* renamed from: for reason: not valid java name */
    private final String f10for = TL.m331if(NativeGathererHelper.class);

    private native void finit();

    private native String[] getPackageInfo(String[] strArr);

    public final native String[] attestStrongID(String str, String str2, Context context, int i);

    public final native int cancel();

    public final native String[] checkURLs(String[] strArr);

    public final native Object findAllProcs(Context context, Class cls, int i);

    public final native Object findInstalledProcs(Context context, Class cls, int i);

    public final native int findPackages(String[] strArr, int i, int i2, int i3);

    public final native String[] findPermissions(short[] sArr);

    public final native short[] findPermissions(String[] strArr);

    public final native Object findRunningProcs(Context context, Class cls, int i);

    public final native Object getAddresses(Class cls);

    public final native String getBinaryArch();

    public final native String getConfig(String str);

    public final native String getConnections(Context context);

    public final native String[] getFontList(String str);

    public final native String[] getNetworkInfo();

    public final native String getRandomString(int i);

    public final native int getSelinuxMode();

    public final native String hashFile(String str);

    public final native boolean init(int i, String str, boolean z, boolean z2);

    public final native void initPackageManager();

    public final native int jniDetectedDebugStatus();

    public final native String md5(String str);

    public final native int setConfig(String str, String str2);

    public final native void setInfoLogging(int i);

    public final native String sha1(byte[] bArr);

    public final native String sha256(byte[] bArr);

    public final native byte[] sign(byte[] bArr, ContentResolver contentResolver);

    public final native String urlEncode(String str);

    public final native int validatePackage(String str);

    public final native int waitUntilCancelled();

    public final native String xor(String str, String str2);

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        finit();
    }
}
