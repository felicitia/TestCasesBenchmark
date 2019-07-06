package com.klarna.checkout.internal.a;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public final class b extends AsyncTask<String, Void, String> {
    private final String a;
    private final HashMap<String, String> b;
    private final String c;
    private final c d;
    private final Network e;
    private Map<String, List<String>> f;
    private String g;
    private int h;

    public b(c cVar, String str, String str2) {
        this(cVar, str, str2, null);
    }

    @TargetApi(21)
    public b(c cVar, String str, String str2, Network network) {
        this.a = str;
        this.b = cVar.e;
        this.c = str2;
        this.d = cVar;
        this.e = network;
    }

    private String a() {
        new StringBuilder("HTTP: in Background, url = ").append(this.a);
        try {
            URL url = new URL(this.a);
            StringBuilder sb = new StringBuilder("Performing ");
            sb.append(this.c);
            sb.append(" request");
            if (url.getProtocol().equals("http")) {
                if (this.c.equals("GET")) {
                    HttpURLConnection d2 = d(url);
                    d2.setInstanceFollowRedirects(false);
                    return a(d2);
                } else if (this.c.equals("PUT") || this.c.equals("POST")) {
                    HttpURLConnection e2 = e(url);
                    e2.setInstanceFollowRedirects(false);
                    return b(e2);
                }
            } else if (this.c.equals("GET")) {
                HttpsURLConnection a2 = a(url);
                a2.setInstanceFollowRedirects(false);
                return a(a2);
            } else if (this.c.equals("PUT") || this.c.equals("POST")) {
                HttpsURLConnection b2 = b(url);
                b2.setInstanceFollowRedirects(false);
                return b(b2);
            }
        } catch (Exception e3) {
            e3.getMessage();
        }
        return "";
    }

    private static String a(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
        return stringBuffer.toString();
    }

    private String a(HttpURLConnection httpURLConnection) {
        try {
            this.h = httpURLConnection.getResponseCode();
            new StringBuilder(" status ").append(this.h);
            StringBuilder sb = new StringBuilder("HTTP\\/1.1 ");
            sb.append(httpURLConnection.getResponseCode());
            sb.append(" ");
            sb.append(httpURLConnection.getResponseMessage());
            this.g = sb.toString();
            this.f = httpURLConnection.getHeaderFields();
            try {
                return a(httpURLConnection.getInputStream());
            } catch (Exception e2) {
                e2.getMessage();
                return a(httpURLConnection.getErrorStream());
            }
        } catch (Exception e3) {
            e3.getMessage();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.h);
            return sb2.toString();
        }
    }

    private String a(HttpsURLConnection httpsURLConnection) {
        try {
            this.h = httpsURLConnection.getResponseCode();
            new StringBuilder(" status ").append(this.h);
            StringBuilder sb = new StringBuilder("HTTP\\/1.1 ");
            sb.append(httpsURLConnection.getResponseCode());
            sb.append(" ");
            sb.append(httpsURLConnection.getResponseMessage());
            this.g = sb.toString();
            this.f = httpsURLConnection.getHeaderFields();
            try {
                return a(httpsURLConnection.getInputStream());
            } catch (Exception e2) {
                e2.getMessage();
                return a(httpsURLConnection.getErrorStream());
            }
        } catch (Exception e3) {
            e3.getMessage();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.h);
            return sb2.toString();
        }
    }

    private HttpsURLConnection a(URL url) {
        HttpsURLConnection c2 = c(url);
        c2.setRequestMethod("GET");
        c2.setDoInput(true);
        c2.setDoOutput(false);
        if (this.b != null) {
            for (String str : this.b.keySet()) {
                String str2 = ((String) this.b.get(str)).toString();
                StringBuilder sb = new StringBuilder("   ...adding header ");
                sb.append(str);
                sb.append(" : ");
                sb.append(str2);
                c2.setRequestProperty(str, str2);
            }
        }
        return c2;
    }

    private String b(HttpURLConnection httpURLConnection) {
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.flush();
            bufferedWriter.write(this.d.g);
            bufferedWriter.close();
            outputStream.close();
            try {
                this.h = httpURLConnection.getResponseCode();
                new StringBuilder(" status ").append(this.h);
                this.f = httpURLConnection.getHeaderFields();
                StringBuilder sb = new StringBuilder("HTTP\\/1.1 ");
                sb.append(this.h);
                sb.append(" ");
                sb.append(httpURLConnection.getResponseMessage());
                this.g = sb.toString();
                return a(httpURLConnection.getInputStream());
            } catch (Exception e2) {
                e2.getMessage();
                return a(httpURLConnection.getErrorStream());
            }
        } catch (Exception e3) {
            e3.getMessage();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.h);
            return sb2.toString();
        }
    }

    private String b(HttpsURLConnection httpsURLConnection) {
        try {
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.flush();
            bufferedWriter.write(this.d.g);
            bufferedWriter.close();
            outputStream.close();
            try {
                this.h = httpsURLConnection.getResponseCode();
                new StringBuilder(" status ").append(this.h);
                this.f = httpsURLConnection.getHeaderFields();
                StringBuilder sb = new StringBuilder("HTTP\\/1.1 ");
                sb.append(this.h);
                sb.append(" ");
                sb.append(httpsURLConnection.getResponseMessage());
                this.g = sb.toString();
                return a(httpsURLConnection.getInputStream());
            } catch (Exception e2) {
                e2.getMessage();
                return a(httpsURLConnection.getErrorStream());
            }
        } catch (Exception e3) {
            e3.getMessage();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.h);
            return sb2.toString();
        }
    }

    private HttpsURLConnection b(URL url) {
        HttpsURLConnection c2 = c(url);
        c2.setRequestMethod(this.c);
        if (this.b != null) {
            for (String str : this.b.keySet()) {
                String str2 = ((String) this.b.get(str)).toString();
                StringBuilder sb = new StringBuilder("   ...adding header ");
                sb.append(str);
                sb.append(" : ");
                sb.append(str2);
                c2.setRequestProperty(str, str2);
            }
        }
        return c2;
    }

    private HttpsURLConnection c(URL url) {
        URLConnection openConnection;
        if (this.e != null && VERSION.SDK_INT >= 22) {
            new StringBuilder("Making HTTP request over cellular  ").append(((ConnectivityManager) this.d.a.j.getApplication().getSystemService("connectivity")).getLinkProperties(this.e).getInterfaceName());
            openConnection = this.e.openConnection(url);
        } else {
            openConnection = (URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection());
        }
        return (HttpsURLConnection) openConnection;
    }

    private HttpURLConnection d(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
        if (this.e != null && VERSION.SDK_INT >= 22) {
            httpURLConnection = (HttpURLConnection) this.e.openConnection(url);
        }
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(false);
        if (this.b != null) {
            for (String str : this.b.keySet()) {
                String str2 = ((String) this.b.get(str)).toString();
                StringBuilder sb = new StringBuilder("   ...adding header ");
                sb.append(str);
                sb.append(" : ");
                sb.append(str2);
                httpURLConnection.setRequestProperty(str, str2);
            }
        }
        return httpURLConnection;
    }

    private HttpURLConnection e(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
        httpURLConnection.setRequestMethod(this.c);
        if (this.b != null) {
            for (String str : this.b.keySet()) {
                String str2 = ((String) this.b.get(str)).toString();
                StringBuilder sb = new StringBuilder("   ...adding header ");
                sb.append(str);
                sb.append(" : ");
                sb.append(str2);
                httpURLConnection.setRequestProperty(str, str2);
            }
        }
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        super.onPostExecute(str);
        new StringBuilder("Http Response :").append(this.h);
        a aVar = this.d.j;
        Bundle bundle = new Bundle();
        bundle.putString("STATUS_CODE", this.g);
        bundle.putInt("STATUS_INT", this.h);
        bundle.putString("RESPONSE", str);
        bundle.putString("URL", this.a);
        bundle.putString("id", this.d.d);
        bundle.putString("callingEvent", this.d.h);
        bundle.putString("requestingURL", this.d.i);
        bundle.putBoolean("wasRequestFromOverlay", this.d.f);
        aVar.a(bundle, this.f);
    }
}
