package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public abstract class ey implements nq {
    public abstract mr a(amf<?> amf, Map<String, String> map) throws IOException, zza;

    @Deprecated
    public final HttpResponse b(amf<?> amf, Map<String, String> map) throws IOException, zza {
        mr a = a(amf, map);
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), a.a(), ""));
        ArrayList arrayList = new ArrayList();
        for (ajj ajj : a.b()) {
            arrayList.add(new BasicHeader(ajj.a(), ajj.b()));
        }
        basicHttpResponse.setHeaders((Header[]) arrayList.toArray(new Header[arrayList.size()]));
        InputStream d = a.d();
        if (d != null) {
            BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
            basicHttpEntity.setContent(d);
            basicHttpEntity.setContentLength((long) a.c());
            basicHttpResponse.setEntity(basicHttpEntity);
        }
        return basicHttpResponse;
    }
}
