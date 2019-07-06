package com.klarna.checkout.internal.b;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONException;

public final class b {
    private final RSAPublicKey a;
    private final RSAPrivateKey b;

    b() {
        RSAPrivateKey rSAPrivateKey;
        RSAPublicKey rSAPublicKey = null;
        try {
            rSAPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA", "BC").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode("MIICXAIBAAKBgQCuxM3P7CWLStM0N3GRdk1ds5JJxbyf6ssfae6kuamT9Vb4A7mawOjuhUXqk11GJ0VqkeSsH3Hz1eGpFiDb1rxGUnTtqSJw9hW+sVzpXzwjC1Saq6ayY/hTh85O5m6bxoirbU2TtiGyS3iF2A4JuDy+svWuCahpYrOUQk0Fhwhn/QIDAQABAoGAER99HrL/Vq9lFAeem+bgW57dZVb3TR+++PvzAraiE7oE5FXRGIVwDPaHzBzWFaqBVZins+l8y6wX6O4fGAro9T1Z8SRVq+fhpmh1sTKgcaMGb74lF+7V927ewKeeZBICUZkso3HZdAA0PrDFOzilHYj5ZLV3krWhMO/r7Eur+KECQQDfkbzHP89XapxUD8wWCvj7sm/hth+8rWXsskrEq1mEtg+jjk/9MfbbezaF8HeZMljXnYmIVBA8yOUysJPzfwo1AkEAyB7a/j1dLqdf/3H3mMkD96EZlP//xzteUpR62PW0qmyE7GJOB3FkiUvvolXdzEI/VvENtKE/tBgcslzX+f1fqQJAVGWLwhvQhct1IDamcFvMChgeLDK4YF2E8ImNuBiHPpYL6U472/UUn14wAvf37hkXeK270X8KcifUfgIyl+UJUQJBAKhavOUx/phnGq2t1Rxlz76dewYMr8OOQHW8j/TCEDFnhhPPR9GfJXMTvQ+WqTdTbDHaLSuOlCcQOeQbk16uN7ECQHcs9ZRcIhSoCKSREb0UmcAF0wLZJL8DynJcIBK/cBT3Jla47WiQEuRo3Y4eVlOs1OrAJWRUEF9O1T0hKcUw6gc=".getBytes("utf-8"), 0)));
            rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuxM3P7CWLStM0N3GRdk1ds5JJxbyf6ssfae6kuamT9Vb4A7mawOjuhUXqk11GJ0VqkeSsH3Hz1eGpFiDb1rxGUnTtqSJw9hW+sVzpXzwjC1Saq6ayY/hTh85O5m6bxoirbU2TtiGyS3iF2A4JuDy+svWuCahpYrOUQk0Fhwhn/QIDAQAB".getBytes("utf-8"), 0)));
        } catch (Exception e) {
            e.getMessage();
            rSAPrivateKey = null;
        }
        this.a = rSAPublicKey;
        this.b = rSAPrivateKey;
    }

    public final String a(String str) {
        int bitLength = ((this.a.getModulus().bitLength() + 7) >> 3) / 2;
        int ceil = (int) Math.ceil(((double) str.length()) / ((double) bitLength));
        String[] strArr = new String[ceil];
        int i = ceil - 1;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i3 + bitLength;
            strArr[i2] = str.substring(i3, i4);
            i2++;
            i3 = i4;
        }
        strArr[i] = str.substring(i3);
        JSONArray jSONArray = new JSONArray();
        for (String str2 : strArr) {
            try {
                RSAPublicKey rSAPublicKey = this.a;
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance.init(1, rSAPublicKey);
                jSONArray.put(Base64.encodeToString(instance.doFinal(str2.getBytes()), 2));
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return jSONArray.toString();
    }

    public final String b(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jSONArray.length(); i++) {
                byte[] decode = Base64.decode(jSONArray.getString(i).replace("\n", "").getBytes(), 2);
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance.init(2, this.b);
                sb.append(new String(instance.doFinal(decode), "UTF-8"));
            }
            return sb.toString();
        } catch (JSONException e) {
            e.getMessage();
            return null;
        } catch (Exception e2) {
            e2.getMessage();
            return null;
        }
    }
}
