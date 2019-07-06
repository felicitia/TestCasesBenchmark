package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectTimeoutException;

final class ej extends ey {
    private final nq a;

    ej(nq nqVar) {
        this.a = nqVar;
    }

    public final mr a(amf<?> amf, Map<String, String> map) throws IOException, zza {
        try {
            HttpResponse b = this.a.b(amf, map);
            int statusCode = b.getStatusLine().getStatusCode();
            Header[] allHeaders = b.getAllHeaders();
            ArrayList arrayList = new ArrayList(allHeaders.length);
            for (Header header : allHeaders) {
                arrayList.add(new ajj(header.getName(), header.getValue()));
            }
            if (b.getEntity() == null) {
                return new mr(statusCode, arrayList);
            }
            long contentLength = b.getEntity().getContentLength();
            if (((long) ((int) contentLength)) == contentLength) {
                return new mr(statusCode, arrayList, (int) b.getEntity().getContentLength(), b.getEntity().getContent());
            }
            StringBuilder sb = new StringBuilder(40);
            sb.append("Response too large: ");
            sb.append(contentLength);
            throw new IOException(sb.toString());
        } catch (ConnectTimeoutException e) {
            throw new SocketTimeoutException(e.getMessage());
        }
    }
}
