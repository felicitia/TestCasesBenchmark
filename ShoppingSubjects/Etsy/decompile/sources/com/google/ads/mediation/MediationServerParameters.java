package com.google.ads.mediation;

import com.google.android.gms.internal.ads.ka;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Deprecated
public class MediationServerParameters {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface a {
        String a();

        boolean b() default true;
    }

    public static final class MappingException extends Exception {
        public MappingException(String str) {
            super(str);
        }
    }

    public void a(Map<String, String> map) throws MappingException {
        Field[] fields;
        StringBuilder sb;
        String str;
        HashMap hashMap = new HashMap();
        for (Field field : getClass().getFields()) {
            a aVar = (a) field.getAnnotation(a.class);
            if (aVar != null) {
                hashMap.put(aVar.a(), field);
            }
        }
        if (hashMap.isEmpty()) {
            ka.e("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for (Entry entry : map.entrySet()) {
            Field field2 = (Field) hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException unused) {
                    String str2 = (String) entry.getKey();
                    sb = new StringBuilder(49 + String.valueOf(str2).length());
                    sb.append("Server option \"");
                    sb.append(str2);
                    str = "\" could not be set: Illegal Access";
                } catch (IllegalArgumentException unused2) {
                    String str3 = (String) entry.getKey();
                    sb = new StringBuilder(43 + String.valueOf(str3).length());
                    sb.append("Server option \"");
                    sb.append(str3);
                    str = "\" could not be set: Bad Type";
                }
            } else {
                String str4 = (String) entry.getKey();
                String str5 = (String) entry.getValue();
                StringBuilder sb2 = new StringBuilder(31 + String.valueOf(str4).length() + String.valueOf(str5).length());
                sb2.append("Unexpected server option: ");
                sb2.append(str4);
                sb2.append(" = \"");
                sb2.append(str5);
                sb2.append("\"");
                ka.b(sb2.toString());
            }
        }
        StringBuilder sb3 = new StringBuilder();
        for (Field field3 : hashMap.values()) {
            if (((a) field3.getAnnotation(a.class)).b()) {
                String str6 = "Required server option missing: ";
                String valueOf = String.valueOf(((a) field3.getAnnotation(a.class)).a());
                ka.e(valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6));
                if (sb3.length() > 0) {
                    sb3.append(", ");
                }
                sb3.append(((a) field3.getAnnotation(a.class)).a());
            }
        }
        if (sb3.length() > 0) {
            String str7 = "Required server option(s) missing: ";
            String valueOf2 = String.valueOf(sb3.toString());
            throw new MappingException(valueOf2.length() != 0 ? str7.concat(valueOf2) : new String(str7));
        }
        return;
        sb.append(str);
        ka.e(sb.toString());
    }
}
