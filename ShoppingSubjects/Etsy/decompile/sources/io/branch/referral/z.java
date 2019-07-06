package io.branch.referral;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ServerResponse */
public class z {
    private int a;
    private String b;
    private Object c;

    public z(String str, int i) {
        this.b = str;
        this.a = i;
    }

    public int a() {
        return this.a;
    }

    public void a(Object obj) {
        this.c = obj;
    }

    public JSONObject b() {
        if (this.c instanceof JSONObject) {
            return (JSONObject) this.c;
        }
        return new JSONObject();
    }

    public JSONArray c() {
        if (this.c instanceof JSONArray) {
            return (JSONArray) this.c;
        }
        return null;
    }

    public String d() {
        String str = "";
        try {
            JSONObject b2 = b();
            if (b2 == null || !b2.has("error") || !b2.getJSONObject("error").has("message")) {
                return str;
            }
            String string = b2.getJSONObject("error").getString("message");
            if (string != null) {
                try {
                    if (string.trim().length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append(".");
                        return sb.toString();
                    }
                } catch (Exception unused) {
                }
            }
            return string;
        } catch (Exception unused2) {
            return str;
        }
    }
}
