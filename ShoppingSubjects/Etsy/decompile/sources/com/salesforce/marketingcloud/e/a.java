package com.salesforce.marketingcloud.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Base64;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity;
import com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity.CipherTextIvMac;
import com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity.SecretKeys;
import java.security.GeneralSecurityException;

@RestrictTo({Scope.LIBRARY})
public class a extends AesCbcWithIntegrity {
    private final SecretKeys a;

    public a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3) {
        this(context, str, str2, str3, AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
    }

    protected a(Context context, String str, String str2, String str3, int i) {
        this.a = AesCbcWithIntegrity.generateKeyFromPassword(a(str, str2, str3), a(context, str), i);
        a();
    }

    private String a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("--");
        sb.append(g.b(str2));
        sb.append("__");
        sb.append(g.b(str3));
        return Base64.encodeToString(g.b(sb.toString()).getBytes(), 2);
    }

    private void a() {
        if (!b(a("F6389234-1024-481F-9173-37D9D7F5051F")).equals("F6389234-1024-481F-9173-37D9D7F5051F")) {
            throw new GeneralSecurityException("Encryption/decryption test failed");
        }
    }

    /* access modifiers changed from: protected */
    public String a(@NonNull Context context, @NonNull String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.salesforce.marketingcloud.storagePrefs", 0);
        String string = sharedPreferences.getString("install_date_enc", null);
        if (string != null) {
            return string;
        }
        String saltString = saltString(generateSalt());
        sharedPreferences.edit().putString("install_date_enc", saltString).apply();
        return saltString;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        return encrypt(str, this.a).toString();
    }

    public String b(String str) {
        if (str == null) {
            return null;
        }
        return decryptString(new CipherTextIvMac(str), this.a);
    }
}
