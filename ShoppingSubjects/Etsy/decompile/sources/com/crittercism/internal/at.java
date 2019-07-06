package com.crittercism.internal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class at implements bi {
    public String a;
    String b;
    public int c;
    Object d;

    public static class a implements com.crittercism.internal.az.b<at> {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }

        public final /* synthetic */ bi a(File file) {
            return b(file);
        }

        public final /* synthetic */ void a(bi biVar, OutputStream outputStream) {
            at atVar = (at) biVar;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("timestamp", atVar.b);
                jSONObject.put("filename", atVar.a);
                jSONObject.put("type", atVar.c - 1);
                jSONObject.put("payload", atVar.d);
                outputStream.write(jSONObject.toString().getBytes());
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }

        private static at b(File file) {
            try {
                JSONObject jSONObject = new JSONObject(cn.b(file));
                at atVar = new at(jSONObject.getString("filename"), jSONObject.getString("timestamp"), b.a()[jSONObject.getInt("type")], jSONObject.has("payload") ? jSONObject.get("payload") : null, 0);
                return atVar;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            } catch (IndexOutOfBoundsException e2) {
                throw new IOException(e2.getMessage());
            }
        }
    }

    public enum b {
        ;

        static {
            i = new int[]{a, b, c, d, e, f, g, h};
        }

        public static int[] a() {
            return (int[]) i.clone();
        }
    }

    public enum c {
        ;

        static {
            f = new int[]{a, b, c, d, e};
        }
    }

    public enum d {
        ;

        static {
            c = new int[]{a, b};
        }
    }

    /* synthetic */ at(String str, String str2, int i, Object obj, byte b2) {
        this(str, str2, i, obj);
    }

    public at(int i, Object obj) {
        this(bh.a.a(), cp.a.a(), i, obj);
    }

    public at(Date date, int i, Object obj) {
        this(bh.a.a(), cp.a.a(date), i, obj);
    }

    private at(String str, String str2, int i, Object obj) {
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = obj;
    }

    public static at a(Date date) {
        return new at(date, b.a, null);
    }

    public static at a(String str) {
        if (str.length() > 140) {
            str = str.substring(0, 140);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("text", str);
        hashMap.put("level", Integer.valueOf(0));
        return new at(b.b, new JSONObject(hashMap));
    }

    public static at a(int i, String str) {
        if (i != c.c) {
            int i2 = c.d;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("change", Integer.valueOf(i - 1));
        hashMap.put("type", str);
        return new at(b.e, new JSONObject(hashMap));
    }

    public final String f() {
        return this.a;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public JSONArray g() {
        JSONArray put = new JSONArray().put(this.b).put(this.c - 1);
        if (this.d != null) {
            put.put(this.d);
        }
        return put;
    }

    public final String toString() {
        try {
            return g().toString(4);
        } catch (JSONException e) {
            return e.toString();
        }
    }
}
