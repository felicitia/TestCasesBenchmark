package com.threatmetrix.TrustDefender.internal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class D2 {

    /* renamed from: for reason: not valid java name */
    private static final String f83for = TL.m331if(D2.class);

    public enum E {
        ALG_PARAMETER_SPEC("java.security.spec.AlgorithmParameterSpec"),
        APPLICATION_INFO("android.content.pm.ApplicationInfo"),
        BUILD("android.os.Build"),
        CDMA_CELL_LOCATION("android.telephony.cdma.CdmaCellLocation"),
        CELL_LOCATION("android.telephony.CellLocation"),
        CELL_IDENTITY_CDMA("android.telephony.CellIdentityCdma"),
        CELL_IDENTITY_GSM("android.telephony.CellIdentityGsm"),
        CELL_IDENTITY_LTE("android.telephony.CellIdentityLte"),
        CELL_IDENTITY_WCDMA("android.telephony.CellIdentityWcdma"),
        CELL_INFO("android.telephony.CellInfo"),
        CELL_INFO_CDMA("android.telephony.CellInfoCdma"),
        CELL_INFO_GSM("android.telephony.CellInfoGsm"),
        CELL_INFO_LTE("android.telephony.CellInfoLte"),
        CELL_INFO_WCDMA("android.telephony.CellInfoWcdma"),
        CELL_SIGNAL_STRENGTH("android.telephony.CellSignalStrength"),
        CERTIFICATE("java.security.cert.Certificate"),
        CIPHER("javax.crypto.Cipher"),
        CONNECTIVITY_MANAGER("android.net.ConnectivityManager"),
        CRITERIA("android.location.Criteria"),
        DEVICE_POLICY_MANAGER("android.app.admin.DevicePolicyManager"),
        EC_GEN_KEY_SPEC("java.security.spec.ECGenParameterSpec"),
        FIREBASE_INSTANCE_ID("com.google.firebase.iid.FirebaseInstanceId"),
        GOOGLE_ADVERTISING_ID("com.google.android.gms.ads.identifier.AdvertisingIdClient"),
        GOOGLE_ADVERTISING_INFO("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info"),
        GOOGLE_API("com.google.android.gms.common.api.Api"),
        GOOGLE_API_CLIENT("com.google.android.gms.common.api.GoogleApiClient"),
        GOOGLE_API_BUILDER("com.google.android.gms.common.api.GoogleApiClient$Builder"),
        GOOGLE_AVAILABILITY("com.google.android.gms.common.GoogleApiAvailability"),
        GOOGLE_CONNECTION_CALL_BACK("com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks"),
        GOOGLE_FAILED_CALL_BACK("com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener"),
        GOOGLE_LOCATION_LISTENER("com.google.android.gms.location.LocationListener"),
        GOOGLE_LOCATION_REQUEST("com.google.android.gms.location.LocationRequest"),
        GOOGLE_LOCATION_SERVICES("com.google.android.gms.location.LocationServices"),
        GOOGLE_PENDING_RESULT("com.google.android.gms.common.api.PendingResult"),
        GOOGLE_PLAY_UTILS("com.google.android.gms.common.GooglePlayServicesUtil"),
        GOOGLE_RESULT_CALL_BACK("com.google.android.gms.common.api.ResultCallback"),
        GOOGLE_SAFETY_NET("com.google.android.gms.safetynet.SafetyNet"),
        GOOGLE_SAFETY_NET_API("com.google.android.gms.safetynet.SafetyNetApi"),
        GOOGLE_SAFETY_NET_ATTEST_RESULT("com.google.android.gms.safetynet.SafetyNetApi$AttestationResult"),
        GOOGLE_STATUS("com.google.android.gms.common.api.Status"),
        GSM_CELL_LOCATION("android.telephony.gsm.GsmCellLocation"),
        JS_RESULT("android.webkit.JsResult"),
        KEY("java.security.Key"),
        KEY_CHAIN("android.security.KeyChain"),
        KEY_FACTORY("java.security.KeyFactory"),
        KEY_ENTRY("java.security.KeyStore$Entry"),
        KEY_GEN_PARAM_SPEC("android.security.keystore.KeyGenParameterSpec"),
        KEY_GEN_PARAM_SPEC_BUILDER("android.security.keystore.KeyGenParameterSpec$Builder"),
        KEY_INFO("android.security.keystore.KeyInfo"),
        KEY_PAIR("java.security.KeyPair"),
        KEY_PAIR_GENERATOR("java.security.KeyPairGenerator"),
        KEY_PAIR_GEN_SPEC("android.security.KeyPairGeneratorSpec"),
        KEY_PAIR_GEN_SPEC_BUILDER("android.security.KeyPairGeneratorSpec$Builder"),
        KEY_SPEC("java.security.spec.KeySpec"),
        KEY_STORE("java.security.KeyStore"),
        LOAD_STORE_PARAM("java.security.KeyStore$LoadStoreParameter"),
        LOCATION("android.location.Location"),
        LOCATION_LISTENER("android.location.LocationListener"),
        LOCATION_PROVIDER("android.location.LocationProvider"),
        NEIGHBOR_CELL_INFO("android.telephony.NeighboringCellInfo"),
        NETWORK_INFO("android.net.NetworkInfo"),
        OKHTTP3("okhttp3.OkHttpClient"),
        OKIO("okio.Okio"),
        PACKAGE_MANAGER("android.content.pm.PackageManager"),
        PACKAGE_INFO("android.content.pm.PackageInfo"),
        PACKAGE_ITEM_INFO("android.content.pm.PackageItemInfo"),
        PKCS8_ENC_KEY_SPEC("java.security.spec.PKCS8EncodedKeySpec"),
        POINT("android.graphics.Point"),
        POWER_MANAGER("android.os.PowerManager"),
        PRIVATE_KEY("java.security.PrivateKey"),
        PRIVATE_KEY_ENTRY("java.security.KeyStore$PrivateKeyEntry"),
        PROTECTION_PARAM("java.security.KeyStore$ProtectionParameter"),
        PUBLIC_KEY("java.security.PublicKey"),
        RSA_GEN_KEY_SPEC("java.security.spec.RSAKeyGenParameterSpec"),
        SETTING_SECURE("android.provider.Settings$Secure"),
        SETTING_GLOBAL("android.provider.Settings$Global"),
        SECRET_KEY_SPEC("javax.crypto.spec.SecretKeySpec"),
        SHARED_PREFERENCES("android.content.SharedPreferences"),
        SHARED_PREFERENCES_EDITOR("android.content.SharedPreferences$Editor"),
        SIGNATURE("java.security.Signature"),
        STAT_FS("android.os.StatFs"),
        STATE("android.net.NetworkInfo$State"),
        SUBSCRIPTION_INFO("android.telephony.SubscriptionInfo"),
        SUBSCRIPTION_MANAGER("android.telephony.SubscriptionManager"),
        SYSTEM_CLOCK("android.os.SystemClock"),
        SYSTEM_PROPERTIES("android.os.SystemProperties"),
        TELEPHONY_MANAGER("android.telephony.TelephonyManager"),
        VERSION("android.os.Build$VERSION"),
        VERSION_CODES("android.os.Build$VERSION_CODES"),
        WEB_CHROME_CLIENT("android.webkit.WebChromeClient"),
        WEB_SETTINGS("android.webkit.WebSettings"),
        WEB_SETTINGS_PLUGIN("android.webkit.WebSettings$PluginState"),
        WEB_VIEW("android.webkit.WebView"),
        WEB_VIEW_CLIENT("android.webkit.WebViewClient"),
        WIFI_INFO("android.net.wifi.WifiInfo"),
        WIFI_MANAGER("android.net.wifi.WifiManager"),
        WINDOW_MANAGER("android.view.WindowManager"),
        X_500_PRINCIPAL("javax.security.auth.x500.X500Principal"),
        X509_ENC_KEY_SPEC("java.security.spec.X509EncodedKeySpec"),
        JUNIT_NOT_FOO("com.threatmetrix.NotFoo"),
        JUNIT_FOO("com.threatmetrix.TrustDefender.WrapperHelperTest$Foo");
        
        final String ad;

        private E(String str) {
            this.ad = str;
        }
    }

    D2() {
    }

    /* renamed from: for reason: not valid java name */
    public static Method m44for(Class cls, String str, Class... clsArr) {
        Method method;
        if (cls == null) {
            return null;
        }
        try {
            method = cls.getMethod(str, clsArr);
        } catch (Throwable unused) {
            method = null;
        }
        return method;
    }

    /* renamed from: do reason: not valid java name */
    static Method m42do(Class cls, String str, Class... clsArr) {
        Method method;
        if (cls == null) {
            return null;
        }
        try {
            method = cls.getDeclaredMethod(str, clsArr);
        } catch (Throwable unused) {
            method = null;
        }
        return method;
    }

    /* renamed from: do reason: not valid java name */
    public static <T> T m39do(Object obj, Method method, Object... objArr) {
        T t;
        if (method != null) {
            boolean z = false;
            try {
                t = method.invoke(obj, objArr);
            } catch (Throwable unused) {
                z = true;
                method.getName();
                t = null;
            }
            if (!z) {
                return t;
            }
        }
        return null;
    }

    /* renamed from: do reason: not valid java name */
    public static Class m38do(E e) {
        try {
            return Class.forName(e.ad);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: do reason: not valid java name */
    static Field m41do(Class cls, String str) {
        Field field;
        if (cls == null) {
            return null;
        }
        try {
            field = cls.getDeclaredField(str);
        } catch (Throwable unused) {
            field = null;
        }
        return field;
    }

    /* renamed from: if reason: not valid java name */
    static Field[] m46if(Class cls) {
        Field[] fieldArr;
        if (cls == null) {
            return null;
        }
        try {
            fieldArr = cls.getDeclaredFields();
        } catch (Throwable unused) {
            fieldArr = null;
        }
        return fieldArr;
    }

    /* renamed from: do reason: not valid java name */
    static Object m40do(Field field) {
        Object obj = null;
        if (field == null) {
            return null;
        }
        try {
            obj = field.get(null);
        } catch (Throwable unused) {
            field.getName();
        }
        return obj;
    }

    /* renamed from: if reason: not valid java name */
    static Object m45if(Class cls, Class[] clsArr, Object[] objArr) {
        if (cls == null) {
            return null;
        }
        if ((clsArr != null && objArr == null) || (clsArr == null && objArr != null)) {
            return null;
        }
        if (clsArr != null && objArr != null && clsArr.length != objArr.length) {
            return null;
        }
        try {
            return cls.getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: new reason: not valid java name */
    static Object m47new(ClassLoader classLoader, Class<?>[] clsArr, InvocationHandler invocationHandler) {
        try {
            return Proxy.newProxyInstance(classLoader, clsArr, invocationHandler);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: for reason: not valid java name */
    static Object m43for(Class cls, String str) {
        Field field = m41do(cls, str);
        if (field != null) {
            return m40do(field);
        }
        return null;
    }
}
