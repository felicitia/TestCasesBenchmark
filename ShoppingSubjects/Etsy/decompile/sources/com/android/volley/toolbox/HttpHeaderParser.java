package com.android.volley.toolbox;

import com.android.volley.Cache.Entry;
import com.android.volley.NetworkResponse;
import java.util.Map;
import org.apache.http.entity.mime.MIME;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class HttpHeaderParser {
    public static Entry parseCacheHeaders(NetworkResponse networkResponse) {
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        String str = (String) map.get("Date");
        long j2 = 0;
        long parseDateAsEpoch = str != null ? parseDateAsEpoch(str) : 0;
        String str2 = (String) map.get("Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",");
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    j = 0;
                }
                i++;
            }
            i = 1;
        } else {
            j = 0;
        }
        String str3 = (String) map.get("Expires");
        long parseDateAsEpoch2 = str3 != null ? parseDateAsEpoch(str3) : 0;
        String str4 = (String) map.get("ETag");
        if (i != 0) {
            j2 = currentTimeMillis + (j * 1000);
        } else if (parseDateAsEpoch > 0 && parseDateAsEpoch2 >= parseDateAsEpoch) {
            j2 = currentTimeMillis + (parseDateAsEpoch2 - parseDateAsEpoch);
        }
        Entry entry = new Entry();
        entry.data = networkResponse.data;
        entry.etag = str4;
        entry.softTtl = j2;
        entry.ttl = entry.softTtl;
        entry.serverDate = parseDateAsEpoch;
        entry.responseHeaders = map;
        return entry;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0;
        }
    }

    public static String parseCharset(Map<String, String> map) {
        String str = (String) map.get(MIME.CONTENT_TYPE);
        if (str != null) {
            String[] split = str.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return "ISO-8859-1";
    }
}
