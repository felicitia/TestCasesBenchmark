package com.klarna.checkout.internal.b;

import android.content.Context;
import com.klarna.checkout.internal.g;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a {
    private final c a;
    private final CookieManager b = new CookieManager();

    a(c cVar) {
        this.a = cVar;
        this.b.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    private static String a(String str, String str2) {
        String[] split;
        if (str != null) {
            for (String str3 : str.split(";")) {
                if (str3.toLowerCase().contains(str2.toLowerCase())) {
                    return str3;
                }
            }
        }
        return "";
    }

    public final String a(URI uri) {
        StringBuilder sb = new StringBuilder();
        List list = this.b.getCookieStore().get(uri);
        for (int i = 0; i < list.size(); i++) {
            HttpCookie httpCookie = (HttpCookie) list.get(i);
            if (i != 0) {
                sb.append("; ");
            }
            sb.append(httpCookie.toString());
        }
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        for (URI uri : this.b.getCookieStore().getURIs()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uri", uri.toString());
            List<HttpCookie> list = this.b.getCookieStore().get(uri);
            JSONArray jSONArray2 = new JSONArray();
            for (HttpCookie httpCookie : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("name", httpCookie.getName());
                jSONObject2.put("value", httpCookie.getValue());
                jSONObject2.put("version", httpCookie.getVersion());
                jSONObject2.put("path", httpCookie.getPath());
                jSONObject2.put("comment", httpCookie.getComment());
                jSONObject2.put("commentUrl", httpCookie.getCommentURL());
                jSONObject2.put("domain", httpCookie.getDomain());
                jSONObject2.put("maxAge", httpCookie.getMaxAge());
                jSONObject2.put("portList", httpCookie.getPortlist());
                jSONObject2.put("secure", httpCookie.getSecure());
                jSONObject2.put("maxAge", httpCookie.getMaxAge());
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put("cookie_data", jSONArray2);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public final void a(List<String> list, URI uri, Context context, g gVar) {
        if (list != null) {
            ArrayList<HttpCookie> arrayList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String str = (String) list.get(i);
                String a2 = a(str, "expires=");
                if (str.toLowerCase().contains("httponly")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(a2);
                    sb.append(";httponly");
                    a2 = sb.toString();
                }
                HttpCookie httpCookie = (HttpCookie) HttpCookie.parse(str).get(0);
                httpCookie.setComment(a2);
                arrayList.add(httpCookie);
            }
            for (HttpCookie add : arrayList) {
                this.b.getCookieStore().add(uri, add);
            }
            StringBuilder sb2 = new StringBuilder("Success attempting to save :  ");
            sb2.append(arrayList.size());
            sb2.append(" cookies");
            this.a.a(context, gVar);
        }
    }

    public final void a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            URI uri = new URI(jSONObject.getString("uri"));
            JSONArray jSONArray2 = jSONObject.getJSONArray("cookie_data");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                HttpCookie httpCookie = new HttpCookie(jSONObject2.getString("name"), jSONObject2.getString("value"));
                if (jSONObject2.has("version")) {
                    httpCookie.setVersion(jSONObject2.getInt("version"));
                }
                if (jSONObject2.has("path")) {
                    httpCookie.setPath(jSONObject2.getString("path"));
                }
                if (jSONObject2.has("comment")) {
                    httpCookie.setComment(jSONObject2.getString("comment"));
                }
                if (jSONObject2.has("commentUrl")) {
                    httpCookie.setCommentURL(jSONObject2.getString("commentUrl"));
                }
                if (jSONObject2.has("domain")) {
                    httpCookie.setDomain(jSONObject2.getString("domain"));
                }
                if (jSONObject2.has("maxAge")) {
                    httpCookie.setMaxAge(jSONObject2.getLong("maxAge"));
                }
                if (jSONObject2.has("portList")) {
                    httpCookie.setPortlist(jSONObject2.getString("portList"));
                }
                if (jSONObject2.has("secure")) {
                    httpCookie.setSecure(jSONObject2.getBoolean("secure"));
                }
                this.b.getCookieStore().add(uri, httpCookie);
                StringBuilder sb = new StringBuilder("Loaded cookie:");
                sb.append(httpCookie.toString());
                sb.append(" for url ");
                sb.append(uri.toString());
            }
        }
    }

    public final JSONArray b(URI uri) {
        new StringBuilder("attempting to get set-cookie json for ").append(uri.toString());
        List list = this.b.getCookieStore().get(uri);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            HttpCookie httpCookie = (HttpCookie) list.get(i);
            if (httpCookie.getComment() != null && !httpCookie.getComment().toLowerCase().contains("httponly")) {
                StringBuilder sb = new StringBuilder();
                sb.append(httpCookie.toString());
                sb.append("; Domain=");
                sb.append(httpCookie.getDomain());
                sb.append("; Path=");
                sb.append(httpCookie.getPath());
                sb.append("; Max-Age=");
                sb.append(httpCookie.getMaxAge());
                sb.append("; Version=");
                sb.append(httpCookie.getVersion());
                sb.append("; Comment=");
                if (httpCookie.getComment() != null) {
                    sb.append("; ");
                    sb.append(httpCookie.getComment());
                }
                if (httpCookie.getSecure()) {
                    sb.append("; secure");
                }
                jSONArray.put(sb.toString());
            }
        }
        return jSONArray;
    }
}
