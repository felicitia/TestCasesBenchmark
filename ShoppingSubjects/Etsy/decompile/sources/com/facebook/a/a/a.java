package com.facebook.a.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.RegistrationListener;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import com.etsy.android.lib.models.AppBuild;
import com.facebook.f;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.k;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.EnumMap;
import java.util.HashMap;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceRequestsHelper */
public class a {
    private static HashMap<String, RegistrationListener> a = new HashMap<>();

    public static String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", Build.DEVICE);
            jSONObject.put("model", Build.MODEL);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public static boolean a(String str) {
        if (b()) {
            return d(str);
        }
        return false;
    }

    public static boolean b() {
        return VERSION.SDK_INT >= 16 && k.a(f.j()).g().contains(SmartLoginOption.Enabled);
    }

    public static Bitmap b(String str) {
        EnumMap enumMap = new EnumMap(EncodeHintType.class);
        enumMap.put(EncodeHintType.MARGIN, Integer.valueOf(2));
        try {
            b a2 = new com.google.zxing.b().a(str, BarcodeFormat.QR_CODE, 200, 200, enumMap);
            int c = a2.c();
            int b = a2.b();
            int[] iArr = new int[(c * b)];
            for (int i = 0; i < c; i++) {
                int i2 = i * b;
                for (int i3 = 0; i3 < b; i3++) {
                    iArr[i2 + i3] = a2.a(i3, i) ? ViewCompat.MEASURED_STATE_MASK : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(b, c, Config.ARGB_8888);
            try {
                createBitmap.setPixels(iArr, 0, b, 0, 0, b, c);
                return createBitmap;
            } catch (WriterException unused) {
                return createBitmap;
            }
        } catch (WriterException unused2) {
            return null;
        }
    }

    public static void c(String str) {
        e(str);
    }

    @TargetApi(16)
    private static boolean d(final String str) {
        if (a.containsKey(str)) {
            return true;
        }
        final String format = String.format("%s_%s_%s", new Object[]{"fbsdk", String.format("%s-%s", new Object[]{AppBuild.ANDROID_PLATFORM, f.h().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '|')}), str});
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceType("_fb._tcp.");
        nsdServiceInfo.setServiceName(format);
        nsdServiceInfo.setPort(80);
        NsdManager nsdManager = (NsdManager) f.f().getSystemService("servicediscovery");
        AnonymousClass1 r4 = new RegistrationListener() {
            public void onServiceUnregistered(NsdServiceInfo nsdServiceInfo) {
            }

            public void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
            }

            public void onServiceRegistered(NsdServiceInfo nsdServiceInfo) {
                if (!format.equals(nsdServiceInfo.getServiceName())) {
                    a.c(str);
                }
            }

            public void onRegistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
                a.c(str);
            }
        };
        a.put(str, r4);
        nsdManager.registerService(nsdServiceInfo, 1, r4);
        return true;
    }

    @TargetApi(16)
    private static void e(String str) {
        RegistrationListener registrationListener = (RegistrationListener) a.get(str);
        if (registrationListener != null) {
            ((NsdManager) f.f().getSystemService("servicediscovery")).unregisterService(registrationListener);
            a.remove(str);
        }
    }
}
