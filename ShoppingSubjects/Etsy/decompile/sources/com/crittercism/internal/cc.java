package com.crittercism.internal;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.requests.HttpUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.http.entity.mime.MIME;

public final class cc extends bz {
    private final String d;
    private final List<b> e;

    public static class a {
        public URL a;
        public Map<String, String> b;
        private String c = BaseHttpRequest.POST;
        private List<b> d = new ArrayList();

        public final a a(String str, String str2) {
            this.d.add(new c(str, str2));
            return this;
        }

        public final cc a() {
            cc ccVar = new cc(this.c, this.a, this.d, this.b, 0);
            return ccVar;
        }
    }

    public static class b {
        String a;
        String b;
        String c;
        String d;

        /* synthetic */ b(String str, String str2, String str3, String str4, byte b2) {
            this(str, str2, str3, str4);
        }

        private b(String str, String str2, String str3, String str4) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }
    }

    public static class c extends b {
        public c(String str, String str2) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(".json");
            super(str, sb.toString(), HttpUtil.JSON_CONTENT_TYPE, str2, 0);
        }
    }

    /* synthetic */ cc(String str, URL url, List list, Map map, byte b2) {
        this(str, url, list, map);
    }

    private cc(String str, URL url, List<b> list, Map<String, String> map) {
        super(str, url, null, map);
        this.e = list;
        this.d = UUID.randomUUID().toString();
        Map<String, String> map2 = this.c;
        String str2 = MIME.CONTENT_TYPE;
        StringBuilder sb = new StringBuilder("multipart/form-data; boundary=");
        sb.append(this.d);
        map2.put(str2, sb.toString());
    }

    public final byte[] a() {
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(this.d);
        for (int i = 0; i < this.e.size(); i++) {
            sb.append("\r\n");
            b bVar = (b) this.e.get(i);
            new Formatter(sb).format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: %s\r\n\r\n%s\r\n", new Object[]{bVar.a, bVar.b, bVar.c, bVar.d});
            sb.append("--");
            sb.append(this.d);
        }
        sb.append("--");
        sb.append("\r\n");
        return sb.toString().getBytes();
    }
}
