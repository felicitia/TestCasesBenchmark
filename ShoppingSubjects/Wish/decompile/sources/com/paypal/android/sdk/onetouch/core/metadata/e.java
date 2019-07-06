package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.ByteArrayInputStream;
import java.util.Properties;

public final class e {
    /* JADX INFO: finally extract failed */
    public static String a(String str) {
        Properties properties = new Properties();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("suFileName=/system/xbin/su\nsuperUserApk=/system/app/Superuser.apk\nemptyIp=0.0.0.0".getBytes());
        try {
            properties.load(byteArrayInputStream);
            byteArrayInputStream.close();
            return properties.getProperty(str);
        } catch (Throwable th) {
            byteArrayInputStream.close();
            throw th;
        }
    }
}
