package com.facebook;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Pair;
import com.android.volley.toolbox.BasicNetwork;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.BaseMessage;
import com.facebook.internal.aa;
import com.facebook.internal.r;
import com.facebook.internal.t;
import com.facebook.internal.x;
import com.facebook.internal.z;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.http.entity.mime.MIME;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphRequest {
    public static final String a = "GraphRequest";
    private static String b;
    private static Pattern c = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    private static volatile String q;
    private AccessToken d;
    private HttpMethod e;
    private String f;
    private JSONObject g;
    private String h;
    private String i;
    private boolean j;
    private Bundle k;
    private b l;
    private String m;
    private Object n;
    private String o;
    private boolean p;

    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR = new Creator<ParcelableResourceWithMimeType>() {
            /* renamed from: a */
            public ParcelableResourceWithMimeType createFromParcel(Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel);
            }

            /* renamed from: a */
            public ParcelableResourceWithMimeType[] newArray(int i) {
                return new ParcelableResourceWithMimeType[i];
            }
        };
        private final String mimeType;
        private final RESOURCE resource;

        public int describeContents() {
            return 1;
        }

        public String getMimeType() {
            return this.mimeType;
        }

        public RESOURCE getResource() {
            return this.resource;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mimeType);
            parcel.writeParcelable(this.resource, i);
        }

        public ParcelableResourceWithMimeType(RESOURCE resource2, String str) {
            this.mimeType = str;
            this.resource = resource2;
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.mimeType = parcel.readString();
            this.resource = parcel.readParcelable(f.f().getClassLoader());
        }
    }

    private static class a {
        private final GraphRequest a;
        private final Object b;

        public a(GraphRequest graphRequest, Object obj) {
            this.a = graphRequest;
            this.b = obj;
        }

        public GraphRequest a() {
            return this.a;
        }

        public Object b() {
            return this.b;
        }
    }

    public interface b {
        void a(GraphResponse graphResponse);
    }

    public interface c {
        void a(JSONObject jSONObject, GraphResponse graphResponse);
    }

    private interface d {
        void a(String str, String str2) throws IOException;
    }

    public interface e extends b {
        void a(long j, long j2);
    }

    private static class f implements d {
        private final OutputStream a;
        private final t b;
        private boolean c = true;
        private boolean d = false;

        public f(OutputStream outputStream, t tVar, boolean z) {
            this.a = outputStream;
            this.b = tVar;
            this.d = z;
        }

        public void a(String str, Object obj, GraphRequest graphRequest) throws IOException {
            if (this.a instanceof m) {
                ((m) this.a).a(graphRequest);
            }
            if (GraphRequest.e(obj)) {
                a(str, GraphRequest.f(obj));
            } else if (obj instanceof Bitmap) {
                a(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                a(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                a(str, (Uri) obj, (String) null);
            } else if (obj instanceof ParcelFileDescriptor) {
                a(str, (ParcelFileDescriptor) obj, (String) null);
            } else if (obj instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                Parcelable resource = parcelableResourceWithMimeType.getResource();
                String mimeType = parcelableResourceWithMimeType.getMimeType();
                if (resource instanceof ParcelFileDescriptor) {
                    a(str, (ParcelFileDescriptor) resource, mimeType);
                } else if (resource instanceof Uri) {
                    a(str, (Uri) resource, mimeType);
                } else {
                    throw b();
                }
            } else {
                throw b();
            }
        }

        private RuntimeException b() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public void a(String str, JSONArray jSONArray, Collection<GraphRequest> collection) throws IOException, JSONException {
            if (!(this.a instanceof m)) {
                a(str, jSONArray.toString());
                return;
            }
            m mVar = (m) this.a;
            a(str, (String) null, (String) null);
            a("[", new Object[0]);
            int i = 0;
            for (GraphRequest graphRequest : collection) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                mVar.a(graphRequest);
                if (i > 0) {
                    a(",%s", jSONObject.toString());
                } else {
                    a("%s", jSONObject.toString());
                }
                i++;
            }
            a("]", new Object[0]);
            if (this.b != null) {
                t tVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("    ");
                sb.append(str);
                tVar.a(sb.toString(), (Object) jSONArray.toString());
            }
        }

        public void a(String str, String str2) throws IOException {
            a(str, (String) null, (String) null);
            b("%s", str2);
            a();
            if (this.b != null) {
                t tVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("    ");
                sb.append(str);
                tVar.a(sb.toString(), (Object) str2);
            }
        }

        public void a(String str, Bitmap bitmap) throws IOException {
            a(str, str, "image/png");
            bitmap.compress(CompressFormat.PNG, 100, this.a);
            b("", new Object[0]);
            a();
            if (this.b != null) {
                t tVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("    ");
                sb.append(str);
                tVar.a(sb.toString(), (Object) "<Image>");
            }
        }

        public void a(String str, byte[] bArr) throws IOException {
            a(str, str, "content/unknown");
            this.a.write(bArr);
            b("", new Object[0]);
            a();
            if (this.b != null) {
                t tVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("    ");
                sb.append(str);
                tVar.a(sb.toString(), (Object) String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(bArr.length)}));
            }
        }

        public void a(String str, Uri uri, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            a(str, str, str2);
            if (this.a instanceof k) {
                ((k) this.a).a(z.e(uri));
                i = 0;
            } else {
                i = z.a(f.f().getContentResolver().openInputStream(uri), this.a) + 0;
            }
            b("", new Object[0]);
            a();
            if (this.b != null) {
                t tVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("    ");
                sb.append(str);
                tVar.a(sb.toString(), (Object) String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        public void a(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            a(str, str, str2);
            if (this.a instanceof k) {
                ((k) this.a).a(parcelFileDescriptor.getStatSize());
                i = 0;
            } else {
                i = z.a((InputStream) new AutoCloseInputStream(parcelFileDescriptor), this.a) + 0;
            }
            b("", new Object[0]);
            a();
            if (this.b != null) {
                t tVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append("    ");
                sb.append(str);
                tVar.a(sb.toString(), (Object) String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        public void a() throws IOException {
            if (!this.d) {
                b("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
                return;
            }
            this.a.write("&".getBytes());
        }

        public void a(String str, String str2, String str3) throws IOException {
            if (!this.d) {
                a("Content-Disposition: form-data; name=\"%s\"", str);
                if (str2 != null) {
                    a("; filename=\"%s\"", str2);
                }
                b("", new Object[0]);
                if (str3 != null) {
                    b("%s: %s", MIME.CONTENT_TYPE, str3);
                }
                b("", new Object[0]);
                return;
            }
            this.a.write(String.format("%s=", new Object[]{str}).getBytes());
        }

        public void a(String str, Object... objArr) throws IOException {
            if (!this.d) {
                if (this.c) {
                    this.a.write("--".getBytes());
                    this.a.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                    this.a.write("\r\n".getBytes());
                    this.c = false;
                }
                this.a.write(String.format(str, objArr).getBytes());
                return;
            }
            this.a.write(URLEncoder.encode(String.format(Locale.US, str, objArr), "UTF-8").getBytes());
        }

        public void b(String str, Object... objArr) throws IOException {
            a(str, objArr);
            if (!this.d) {
                a("\r\n", new Object[0]);
            }
        }
    }

    public GraphRequest() {
        this(null, null, null, null, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod) {
        this(accessToken, str, bundle, httpMethod, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod, b bVar) {
        this(accessToken, str, bundle, httpMethod, bVar, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod, b bVar, String str2) {
        this.j = true;
        this.p = false;
        this.d = accessToken;
        this.f = str;
        this.o = str2;
        a(bVar);
        a(httpMethod);
        if (bundle != null) {
            this.k = new Bundle(bundle);
        } else {
            this.k = new Bundle();
        }
        if (this.o == null) {
            this.o = f.g();
        }
    }

    public static GraphRequest a(AccessToken accessToken, final c cVar) {
        AccessToken accessToken2 = accessToken;
        GraphRequest graphRequest = new GraphRequest(accessToken2, "me", null, null, new b() {
            public void a(GraphResponse graphResponse) {
                if (cVar != null) {
                    cVar.a(graphResponse.b(), graphResponse);
                }
            }
        });
        return graphRequest;
    }

    public static GraphRequest a(AccessToken accessToken, String str, JSONObject jSONObject, b bVar) {
        GraphRequest graphRequest = new GraphRequest(accessToken, str, null, HttpMethod.POST, bVar);
        graphRequest.a(jSONObject);
        return graphRequest;
    }

    public static GraphRequest a(AccessToken accessToken, String str, b bVar) {
        GraphRequest graphRequest = new GraphRequest(accessToken, str, null, null, bVar);
        return graphRequest;
    }

    public final JSONObject a() {
        return this.g;
    }

    public final void a(JSONObject jSONObject) {
        this.g = jSONObject;
    }

    public final String b() {
        return this.f;
    }

    public final HttpMethod c() {
        return this.e;
    }

    public final void a(HttpMethod httpMethod) {
        if (this.m == null || httpMethod == HttpMethod.GET) {
            if (httpMethod == null) {
                httpMethod = HttpMethod.GET;
            }
            this.e = httpMethod;
            return;
        }
        throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }

    public final String d() {
        return this.o;
    }

    public final void a(String str) {
        this.o = str;
    }

    public final void a(boolean z) {
        this.p = z;
    }

    public final Bundle e() {
        return this.k;
    }

    public final void a(Bundle bundle) {
        this.k = bundle;
    }

    public final AccessToken f() {
        return this.d;
    }

    public final b g() {
        return this.l;
    }

    public final void a(final b bVar) {
        if (f.a(LoggingBehavior.GRAPH_API_DEBUG_INFO) || f.a(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.l = new b() {
                public void a(GraphResponse graphResponse) {
                    JSONObject b2 = graphResponse.b();
                    JSONObject optJSONObject = b2 != null ? b2.optJSONObject("__debug__") : null;
                    JSONArray optJSONArray = optJSONObject != null ? optJSONObject.optJSONArray(ResponseConstants.MESSAGES) : null;
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                            String optString = optJSONObject2 != null ? optJSONObject2.optString("message") : null;
                            String optString2 = optJSONObject2 != null ? optJSONObject2.optString("type") : null;
                            String optString3 = optJSONObject2 != null ? optJSONObject2.optString("link") : null;
                            if (!(optString == null || optString2 == null)) {
                                LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                                if (optString2.equals(BaseMessage.TYPE_WARNING)) {
                                    loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                                }
                                if (!z.a(optString3)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(optString);
                                    sb.append(" Link: ");
                                    sb.append(optString3);
                                    optString = sb.toString();
                                }
                                t.a(loggingBehavior, GraphRequest.a, optString);
                            }
                        }
                    }
                    if (bVar != null) {
                        bVar.a(graphResponse);
                    }
                }
            };
        } else {
            this.l = bVar;
        }
    }

    public final void a(Object obj) {
        this.n = obj;
    }

    public final Object h() {
        return this.n;
    }

    public final GraphResponse i() {
        return a(this);
    }

    public final GraphRequestAsyncTask j() {
        return b(this);
    }

    public static HttpURLConnection a(g gVar) {
        URL url;
        d(gVar);
        try {
            if (gVar.size() == 1) {
                url = new URL(gVar.get(0).l());
            } else {
                url = new URL(x.b());
            }
            HttpURLConnection httpURLConnection = null;
            try {
                HttpURLConnection a2 = a(url);
                try {
                    a(gVar, a2);
                    return a2;
                } catch (IOException | JSONException e2) {
                    e = e2;
                    httpURLConnection = a2;
                    z.a((URLConnection) httpURLConnection);
                    throw new FacebookException("could not construct request body", e);
                }
            } catch (IOException | JSONException e3) {
                e = e3;
                z.a((URLConnection) httpURLConnection);
                throw new FacebookException("could not construct request body", e);
            }
        } catch (MalformedURLException e4) {
            throw new FacebookException("could not construct URL for request", (Throwable) e4);
        }
    }

    public static GraphResponse a(GraphRequest graphRequest) {
        List a2 = a(graphRequest);
        if (a2 != null && a2.size() == 1) {
            return (GraphResponse) a2.get(0);
        }
        throw new FacebookException("invalid state: expected a single response");
    }

    public static List<GraphResponse> a(GraphRequest... graphRequestArr) {
        aa.a((Object) graphRequestArr, "requests");
        return a((Collection<GraphRequest>) Arrays.asList(graphRequestArr));
    }

    public static List<GraphResponse> a(Collection<GraphRequest> collection) {
        return b(new g(collection));
    }

    public static List<GraphResponse> b(g gVar) {
        aa.c(gVar, "requests");
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection a2 = a(gVar);
            try {
                List<GraphResponse> a3 = a(a2, gVar);
                z.a((URLConnection) a2);
                return a3;
            } catch (Throwable th) {
                th = th;
                httpURLConnection = a2;
                z.a((URLConnection) httpURLConnection);
                throw th;
            }
        } catch (Exception e2) {
            List<GraphResponse> a4 = GraphResponse.a(gVar.d(), (HttpURLConnection) null, new FacebookException((Throwable) e2));
            a(gVar, a4);
            z.a((URLConnection) null);
            return a4;
        } catch (Throwable th2) {
            th = th2;
            z.a((URLConnection) httpURLConnection);
            throw th;
        }
    }

    public static GraphRequestAsyncTask b(GraphRequest... graphRequestArr) {
        aa.a((Object) graphRequestArr, "requests");
        return b((Collection<GraphRequest>) Arrays.asList(graphRequestArr));
    }

    public static GraphRequestAsyncTask b(Collection<GraphRequest> collection) {
        return c(new g(collection));
    }

    public static GraphRequestAsyncTask c(g gVar) {
        aa.c(gVar, "requests");
        GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequestAsyncTask(gVar);
        graphRequestAsyncTask.executeOnExecutor(f.d(), new Void[0]);
        return graphRequestAsyncTask;
    }

    public static List<GraphResponse> a(HttpURLConnection httpURLConnection, g gVar) {
        List<GraphResponse> a2 = GraphResponse.a(httpURLConnection, gVar);
        z.a((URLConnection) httpURLConnection);
        int size = gVar.size();
        if (size != a2.size()) {
            throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", new Object[]{Integer.valueOf(a2.size()), Integer.valueOf(size)}));
        }
        a(gVar, a2);
        b.a().e();
        return a2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{Request: ");
        sb.append(" accessToken: ");
        sb.append(this.d == null ? "null" : this.d);
        sb.append(", graphPath: ");
        sb.append(this.f);
        sb.append(", graphObject: ");
        sb.append(this.g);
        sb.append(", httpMethod: ");
        sb.append(this.e);
        sb.append(", parameters: ");
        sb.append(this.k);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    static void a(final g gVar, List<GraphResponse> list) {
        int size = gVar.size();
        final ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            GraphRequest a2 = gVar.get(i2);
            if (a2.l != null) {
                arrayList.add(new Pair(a2.l, list.get(i2)));
            }
        }
        if (arrayList.size() > 0) {
            AnonymousClass3 r7 = new Runnable() {
                public void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        ((b) pair.first).a((GraphResponse) pair.second);
                    }
                    for (com.facebook.g.a a2 : gVar.e()) {
                        a2.a(gVar);
                    }
                }
            };
            Handler c2 = gVar.c();
            if (c2 == null) {
                r7.run();
            } else {
                c2.post(r7);
            }
        }
    }

    private static HttpURLConnection a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", p());
        httpURLConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }

    private void m() {
        if (this.d != null) {
            if (!this.k.containsKey(AccessToken.ACCESS_TOKEN_KEY)) {
                String token = this.d.getToken();
                t.a(token);
                this.k.putString(AccessToken.ACCESS_TOKEN_KEY, token);
            }
        } else if (!this.p && !this.k.containsKey(AccessToken.ACCESS_TOKEN_KEY)) {
            String j2 = f.j();
            String l2 = f.l();
            if (z.a(j2) || z.a(l2)) {
                z.b(a, "Warning: Request without access token missing application ID or client token.");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(j2);
                sb.append("|");
                sb.append(l2);
                this.k.putString(AccessToken.ACCESS_TOKEN_KEY, sb.toString());
            }
        }
        this.k.putString("sdk", AppBuild.ANDROID_PLATFORM);
        this.k.putString(ResponseConstants.FORMAT, "json");
        if (f.a(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            this.k.putString("debug", "info");
        } else if (f.a(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.k.putString("debug", BaseMessage.TYPE_WARNING);
        }
    }

    private String a(String str, Boolean bool) {
        if (!bool.booleanValue() && this.e == HttpMethod.POST) {
            return str;
        }
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (String str2 : this.k.keySet()) {
            Object obj = this.k.get(str2);
            if (obj == null) {
                obj = "";
            }
            if (e(obj)) {
                buildUpon.appendQueryParameter(str2, f(obj).toString());
            } else if (this.e == HttpMethod.GET) {
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", new Object[]{obj.getClass().getSimpleName()}));
            }
        }
        return buildUpon.toString();
    }

    /* access modifiers changed from: 0000 */
    public final String k() {
        if (this.m != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        String format = String.format("%s/%s", new Object[]{x.b(), n()});
        m();
        Uri parse = Uri.parse(a(format, Boolean.valueOf(true)));
        return String.format("%s?%s", new Object[]{parse.getPath(), parse.getQuery()});
    }

    /* access modifiers changed from: 0000 */
    public final String l() {
        String str;
        if (this.m != null) {
            return this.m.toString();
        }
        if (c() != HttpMethod.POST || this.f == null || !this.f.endsWith("/videos")) {
            str = x.b();
        } else {
            str = x.c();
        }
        String format = String.format("%s/%s", new Object[]{str, n()});
        m();
        return a(format, Boolean.valueOf(false));
    }

    private String n() {
        if (c.matcher(this.f).matches()) {
            return this.f;
        }
        return String.format("%s/%s", new Object[]{this.o, this.f});
    }

    private void a(JSONArray jSONArray, Map<String, a> map) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        if (this.h != null) {
            jSONObject.put(ResponseConstants.NAME, this.h);
            jSONObject.put("omit_response_on_success", this.j);
        }
        if (this.i != null) {
            jSONObject.put("depends_on", this.i);
        }
        String k2 = k();
        jSONObject.put("relative_url", k2);
        jSONObject.put(ResponseConstants.METHOD, this.e);
        if (this.d != null) {
            t.a(this.d.getToken());
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.k.keySet()) {
            Object obj = this.k.get(str);
            if (d(obj)) {
                String format = String.format(Locale.ROOT, "%s%d", new Object[]{ResponseConstants.FILE, Integer.valueOf(map.size())});
                arrayList.add(format);
                map.put(format, new a(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put("attached_files", TextUtils.join(",", arrayList));
        }
        if (this.g != null) {
            final ArrayList arrayList2 = new ArrayList();
            a(this.g, k2, (d) new d() {
                public void a(String str, String str2) throws IOException {
                    arrayList2.add(String.format(Locale.US, "%s=%s", new Object[]{str, URLEncoder.encode(str2, "UTF-8")}));
                }
            });
            jSONObject.put("body", TextUtils.join("&", arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    private static boolean e(g gVar) {
        for (com.facebook.g.a aVar : gVar.e()) {
            if (aVar instanceof com.facebook.g.b) {
                return true;
            }
        }
        Iterator it = gVar.iterator();
        while (it.hasNext()) {
            if (((GraphRequest) it.next()).g() instanceof e) {
                return true;
            }
        }
        return false;
    }

    private static void a(HttpURLConnection httpURLConnection, boolean z) {
        if (z) {
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty(BasicNetwork.HEADER_CONTENT_ENCODING, BasicNetwork.ENCODING_GZIP);
            return;
        }
        httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, o());
    }

    private static boolean f(g gVar) {
        Iterator it = gVar.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            Iterator it2 = graphRequest.k.keySet().iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (d(graphRequest.k.get((String) it2.next()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static final boolean b(GraphRequest graphRequest) {
        String d2 = graphRequest.d();
        if (z.a(d2)) {
            return true;
        }
        if (d2.startsWith("v")) {
            d2 = d2.substring(1);
        }
        String[] split = d2.split("\\.");
        boolean z = false;
        if ((split.length >= 2 && Integer.parseInt(split[0]) > 2) || (Integer.parseInt(split[0]) >= 2 && Integer.parseInt(split[1]) >= 4)) {
            z = true;
        }
        return z;
    }

    static final void d(g gVar) {
        Iterator it = gVar.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            if (HttpMethod.GET.equals(graphRequest.c()) && b(graphRequest)) {
                Bundle e2 = graphRequest.e();
                if (!e2.containsKey("fields") || z.a(e2.getString("fields"))) {
                    t.a(LoggingBehavior.DEVELOPER_ERRORS, 5, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.b());
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r14v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r14v2 */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r14v4 */
    /* JADX WARNING: type inference failed for: r14v5, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r8v7, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r14v6 */
    /* JADX WARNING: type inference failed for: r14v7 */
    /* JADX WARNING: type inference failed for: r14v8 */
    /* JADX WARNING: type inference failed for: r14v9, types: [java.util.zip.GZIPOutputStream] */
    /* JADX WARNING: type inference failed for: r14v10 */
    /* JADX WARNING: type inference failed for: r14v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d0  */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final void a(com.facebook.g r13, java.net.HttpURLConnection r14) throws java.io.IOException, org.json.JSONException {
        /*
            com.facebook.internal.t r6 = new com.facebook.internal.t
            com.facebook.LoggingBehavior r0 = com.facebook.LoggingBehavior.REQUESTS
            java.lang.String r1 = "Request"
            r6.<init>(r0, r1)
            int r2 = r13.size()
            boolean r5 = f(r13)
            r0 = 0
            r1 = 1
            if (r2 != r1) goto L_0x001c
            com.facebook.GraphRequest r3 = r13.get(r0)
            com.facebook.HttpMethod r3 = r3.e
            goto L_0x001e
        L_0x001c:
            com.facebook.HttpMethod r3 = com.facebook.HttpMethod.POST
        L_0x001e:
            java.lang.String r4 = r3.name()
            r14.setRequestMethod(r4)
            a(r14, r5)
            java.net.URL r4 = r14.getURL()
            java.lang.String r7 = "Request:\n"
            r6.c(r7)
            java.lang.String r7 = "Id"
            java.lang.String r8 = r13.b()
            r6.a(r7, r8)
            java.lang.String r7 = "URL"
            r6.a(r7, r4)
            java.lang.String r7 = "Method"
            java.lang.String r8 = r14.getRequestMethod()
            r6.a(r7, r8)
            java.lang.String r7 = "User-Agent"
            java.lang.String r8 = "User-Agent"
            java.lang.String r8 = r14.getRequestProperty(r8)
            r6.a(r7, r8)
            java.lang.String r7 = "Content-Type"
            java.lang.String r8 = "Content-Type"
            java.lang.String r8 = r14.getRequestProperty(r8)
            r6.a(r7, r8)
            int r7 = r13.a()
            r14.setConnectTimeout(r7)
            int r7 = r13.a()
            r14.setReadTimeout(r7)
            com.facebook.HttpMethod r7 = com.facebook.HttpMethod.POST
            if (r3 != r7) goto L_0x0071
            r0 = r1
        L_0x0071:
            if (r0 != 0) goto L_0x0077
            r6.a()
            return
        L_0x0077:
            r14.setDoOutput(r1)
            r0 = 0
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00cc }
            java.io.OutputStream r14 = r14.getOutputStream()     // Catch:{ all -> 0x00cc }
            r1.<init>(r14)     // Catch:{ all -> 0x00cc }
            if (r5 == 0) goto L_0x008f
            java.util.zip.GZIPOutputStream r14 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x008c }
            r14.<init>(r1)     // Catch:{ all -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r13 = move-exception
            r14 = r1
            goto L_0x00ce
        L_0x008f:
            r14 = r1
        L_0x0090:
            boolean r0 = e(r13)     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x00ba
            com.facebook.k r0 = new com.facebook.k     // Catch:{ all -> 0x00ca }
            android.os.Handler r1 = r13.c()     // Catch:{ all -> 0x00ca }
            r0.<init>(r1)     // Catch:{ all -> 0x00ca }
            r8 = 0
            r7 = r13
            r9 = r2
            r10 = r4
            r11 = r0
            r12 = r5
            a(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00ca }
            int r1 = r0.a()     // Catch:{ all -> 0x00ca }
            java.util.Map r10 = r0.b()     // Catch:{ all -> 0x00ca }
            com.facebook.l r0 = new com.facebook.l     // Catch:{ all -> 0x00ca }
            long r11 = (long) r1     // Catch:{ all -> 0x00ca }
            r7 = r0
            r8 = r14
            r9 = r13
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x00ca }
            r14 = r0
        L_0x00ba:
            r0 = r13
            r1 = r6
            r3 = r4
            r4 = r14
            a(r0, r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00ca }
            if (r14 == 0) goto L_0x00c6
            r14.close()
        L_0x00c6:
            r6.a()
            return
        L_0x00ca:
            r13 = move-exception
            goto L_0x00ce
        L_0x00cc:
            r13 = move-exception
            r14 = r0
        L_0x00ce:
            if (r14 == 0) goto L_0x00d3
            r14.close()
        L_0x00d3:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.a(com.facebook.g, java.net.HttpURLConnection):void");
    }

    private static void a(g gVar, t tVar, int i2, URL url, OutputStream outputStream, boolean z) throws IOException, JSONException {
        f fVar = new f(outputStream, tVar, z);
        if (i2 == 1) {
            GraphRequest a2 = gVar.get(0);
            HashMap hashMap = new HashMap();
            for (String str : a2.k.keySet()) {
                Object obj = a2.k.get(str);
                if (d(obj)) {
                    hashMap.put(str, new a(a2, obj));
                }
            }
            if (tVar != null) {
                tVar.c("  Parameters:\n");
            }
            a(a2.k, fVar, a2);
            if (tVar != null) {
                tVar.c("  Attachments:\n");
            }
            a((Map<String, a>) hashMap, fVar);
            if (a2.g != null) {
                a(a2.g, url.getPath(), (d) fVar);
                return;
            }
            return;
        }
        String g2 = g(gVar);
        if (z.a(g2)) {
            throw new FacebookException("App ID was not specified at the request or Settings.");
        }
        fVar.a("batch_app_id", g2);
        HashMap hashMap2 = new HashMap();
        a(fVar, (Collection<GraphRequest>) gVar, (Map<String, a>) hashMap2);
        if (tVar != null) {
            tVar.c("  Attachments:\n");
        }
        a((Map<String, a>) hashMap2, fVar);
    }

    private static boolean b(String str) {
        Matcher matcher = c.matcher(str);
        if (matcher.matches()) {
            str = matcher.group(1);
        }
        if (str.startsWith("me/") || str.startsWith("/me/")) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(org.json.JSONObject r6, java.lang.String r7, com.facebook.GraphRequest.d r8) throws java.io.IOException {
        /*
            boolean r0 = b(r7)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = ":"
            int r0 = r7.indexOf(r0)
            java.lang.String r3 = "?"
            int r7 = r7.indexOf(r3)
            r3 = 3
            if (r0 <= r3) goto L_0x001e
            r3 = -1
            if (r7 == r3) goto L_0x001c
            if (r0 >= r7) goto L_0x001e
        L_0x001c:
            r7 = r1
            goto L_0x001f
        L_0x001e:
            r7 = r2
        L_0x001f:
            java.util.Iterator r0 = r6.keys()
        L_0x0023:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0044
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.opt(r3)
            if (r7 == 0) goto L_0x003f
            java.lang.String r5 = "image"
            boolean r5 = r3.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x003f
            r5 = r1
            goto L_0x0040
        L_0x003f:
            r5 = r2
        L_0x0040:
            a(r3, r4, r8, r5)
            goto L_0x0023
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphRequest.a(org.json.JSONObject, java.lang.String, com.facebook.GraphRequest$d):void");
    }

    private static void a(String str, Object obj, d dVar, boolean z) throws IOException {
        Class cls = obj.getClass();
        if (JSONObject.class.isAssignableFrom(cls)) {
            JSONObject jSONObject = (JSONObject) obj;
            if (z) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    a(String.format("%s[%s]", new Object[]{str, str2}), jSONObject.opt(str2), dVar, z);
                }
            } else if (jSONObject.has("id")) {
                a(str, (Object) jSONObject.optString("id"), dVar, z);
            } else if (jSONObject.has("url")) {
                a(str, (Object) jSONObject.optString("url"), dVar, z);
            } else if (jSONObject.has("fbsdk:create_object")) {
                a(str, (Object) jSONObject.toString(), dVar, z);
            }
        } else if (JSONArray.class.isAssignableFrom(cls)) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                a(String.format(Locale.ROOT, "%s[%d]", new Object[]{str, Integer.valueOf(i2)}), jSONArray.opt(i2), dVar, z);
            }
        } else if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
            dVar.a(str, obj.toString());
        } else if (Date.class.isAssignableFrom(cls)) {
            dVar.a(str, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date) obj));
        }
    }

    private static void a(Bundle bundle, f fVar, GraphRequest graphRequest) throws IOException {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (e(obj)) {
                fVar.a(str, obj, graphRequest);
            }
        }
    }

    private static void a(Map<String, a> map, f fVar) throws IOException {
        for (String str : map.keySet()) {
            a aVar = (a) map.get(str);
            if (d(aVar.b())) {
                fVar.a(str, aVar.b(), aVar.a());
            }
        }
    }

    private static void a(f fVar, Collection<GraphRequest> collection, Map<String, a> map) throws JSONException, IOException {
        JSONArray jSONArray = new JSONArray();
        for (GraphRequest a2 : collection) {
            a2.a(jSONArray, map);
        }
        fVar.a("batch", jSONArray, collection);
    }

    private static String o() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{"3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"});
    }

    private static String p() {
        if (q == null) {
            q = String.format("%s.%s", new Object[]{"FBAndroidSDK", "4.35.0"});
            String a2 = r.a();
            if (!z.a(a2)) {
                q = String.format(Locale.ROOT, "%s/%s", new Object[]{q, a2});
            }
        }
        return q;
    }

    private static String g(g gVar) {
        if (!z.a(gVar.f())) {
            return gVar.f();
        }
        Iterator it = gVar.iterator();
        while (it.hasNext()) {
            AccessToken accessToken = ((GraphRequest) it.next()).d;
            if (accessToken != null) {
                String applicationId = accessToken.getApplicationId();
                if (applicationId != null) {
                    return applicationId;
                }
            }
        }
        if (!z.a(b)) {
            return b;
        }
        return f.j();
    }

    private static boolean d(Object obj) {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
    }

    /* access modifiers changed from: private */
    public static boolean e(Object obj) {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    /* access modifiers changed from: private */
    public static String f(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
}
