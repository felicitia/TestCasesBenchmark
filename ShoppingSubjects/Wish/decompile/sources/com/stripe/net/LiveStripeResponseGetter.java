package com.stripe.net;

import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.net.APIResource.RequestMethod;
import com.stripe.net.APIResource.RequestType;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.Security;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class LiveStripeResponseGetter implements StripeResponseGetter {
    private static final SSLSocketFactory socketFactory = new StripeSSLSocketFactory();

    private static class Error {
        String charge;
        String code;
        String decline_code;
        String message;
        String param;
    }

    private static class ErrorContainer {
        /* access modifiers changed from: private */
        public Error error;

        private ErrorContainer() {
        }
    }

    private static final class Parameter {
        public final String key;
        public final String value;

        public Parameter(String str, String str2) {
            this.key = str;
            this.value = str2;
        }
    }

    public <T> T request(RequestMethod requestMethod, String str, Map<String, Object> map, Class<T> cls, RequestType requestType, RequestOptions requestOptions) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return _request(requestMethod, str, map, cls, requestType, requestOptions);
    }

    private static String urlEncodePair(String str, String str2) throws UnsupportedEncodingException {
        return String.format("%s=%s", new Object[]{APIResource.urlEncode(str), APIResource.urlEncode(str2)});
    }

    static Map<String, String> getHeaders(RequestOptions requestOptions) {
        HashMap hashMap = new HashMap();
        String stripeVersion = requestOptions.getStripeVersion();
        hashMap.put("Accept-Charset", "UTF-8");
        hashMap.put("Accept", "application/json");
        hashMap.put("User-Agent", String.format("Stripe/v1 JavaBindings/%s", new Object[]{"1.40.0"}));
        hashMap.put("Authorization", String.format("Bearer %s", new Object[]{requestOptions.getApiKey()}));
        String[] strArr = {"os.name", "os.version", "os.arch", "java.version", "java.vendor", "java.vm.version", "java.vm.vendor"};
        HashMap hashMap2 = new HashMap();
        for (String str : strArr) {
            hashMap2.put(str, System.getProperty(str));
        }
        hashMap2.put("bindings.version", "1.40.0");
        hashMap2.put("lang", "Java");
        hashMap2.put("publisher", "Stripe");
        hashMap.put("X-Stripe-Client-User-Agent", APIResource.GSON.toJson((Object) hashMap2));
        if (stripeVersion != null) {
            hashMap.put("Stripe-Version", stripeVersion);
        }
        if (requestOptions.getIdempotencyKey() != null) {
            hashMap.put("Idempotency-Key", requestOptions.getIdempotencyKey());
        }
        if (requestOptions.getStripeAccount() != null) {
            hashMap.put("Stripe-Account", requestOptions.getStripeAccount());
        }
        return hashMap;
    }

    private static HttpURLConnection createStripeConnection(String str, RequestOptions requestOptions) throws IOException {
        URL url;
        HttpURLConnection httpURLConnection;
        String property = System.getProperty("com.stripe.net.customURLStreamHandler", null);
        if (property != null) {
            try {
                url = new URL(null, str, (URLStreamHandler) Class.forName(property).getConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            } catch (SecurityException e2) {
                throw new IOException(e2);
            } catch (NoSuchMethodException e3) {
                throw new IOException(e3);
            } catch (IllegalArgumentException e4) {
                throw new IOException(e4);
            } catch (InstantiationException e5) {
                throw new IOException(e5);
            } catch (IllegalAccessException e6) {
                throw new IOException(e6);
            } catch (InvocationTargetException e7) {
                throw new IOException(e7);
            }
        } else {
            url = new URL(str);
        }
        if (Stripe.getConnectionProxy() != null) {
            httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection(Stripe.getConnectionProxy())));
            Authenticator.setDefault(new Authenticator() {
                /* access modifiers changed from: protected */
                public PasswordAuthentication getPasswordAuthentication() {
                    return Stripe.getProxyCredential();
                }
            });
        } else {
            httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection()));
        }
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(80000);
        httpURLConnection.setUseCaches(false);
        for (Entry entry : getHeaders(requestOptions).entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(socketFactory);
        }
        return httpURLConnection;
    }

    private static String formatURL(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return str;
        }
        return String.format("%s%s%s", new Object[]{str, str.contains("?") ? "&" : "?", str2});
    }

    private static HttpURLConnection createGetConnection(String str, String str2, RequestOptions requestOptions) throws IOException {
        HttpURLConnection createStripeConnection = createStripeConnection(formatURL(str, str2), requestOptions);
        createStripeConnection.setRequestMethod("GET");
        return createStripeConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.net.HttpURLConnection createPostConnection(java.lang.String r4, java.lang.String r5, com.stripe.net.RequestOptions r6) throws java.io.IOException {
        /*
            java.net.HttpURLConnection r4 = createStripeConnection(r4, r6)
            r6 = 1
            r4.setDoOutput(r6)
            java.lang.String r0 = "POST"
            r4.setRequestMethod(r0)
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = "application/x-www-form-urlencoded;charset=%s"
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r2 = "UTF-8"
            r3 = 0
            r6[r3] = r2
            java.lang.String r6 = java.lang.String.format(r1, r6)
            r4.setRequestProperty(r0, r6)
            java.io.OutputStream r6 = r4.getOutputStream()     // Catch:{ all -> 0x0034 }
            java.lang.String r0 = "UTF-8"
            byte[] r5 = r5.getBytes(r0)     // Catch:{ all -> 0x0032 }
            r6.write(r5)     // Catch:{ all -> 0x0032 }
            if (r6 == 0) goto L_0x0031
            r6.close()
        L_0x0031:
            return r4
        L_0x0032:
            r4 = move-exception
            goto L_0x0036
        L_0x0034:
            r4 = move-exception
            r6 = 0
        L_0x0036:
            if (r6 == 0) goto L_0x003b
            r6.close()
        L_0x003b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.net.LiveStripeResponseGetter.createPostConnection(java.lang.String, java.lang.String, com.stripe.net.RequestOptions):java.net.HttpURLConnection");
    }

    private static HttpURLConnection createDeleteConnection(String str, String str2, RequestOptions requestOptions) throws IOException {
        HttpURLConnection createStripeConnection = createStripeConnection(formatURL(str, str2), requestOptions);
        createStripeConnection.setRequestMethod("DELETE");
        return createStripeConnection;
    }

    static String createQuery(Map<String, Object> map) throws UnsupportedEncodingException, InvalidRequestException {
        StringBuilder sb = new StringBuilder();
        for (Parameter parameter : flattenParams(map)) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(urlEncodePair(parameter.key, parameter.value));
        }
        return sb.toString();
    }

    private static List<Parameter> flattenParams(Map<String, Object> map) throws InvalidRequestException {
        return flattenParamsMap(map, null);
    }

    private static List<Parameter> flattenParamsList(List<Object> list, String str) throws InvalidRequestException {
        LinkedList linkedList = new LinkedList();
        String format = String.format("%s[]", new Object[]{str});
        if (list.isEmpty()) {
            linkedList.add(new Parameter(str, ""));
        } else {
            for (Object flattenParamsValue : list) {
                linkedList.addAll(flattenParamsValue(flattenParamsValue, format));
            }
        }
        return linkedList;
    }

    private static List<Parameter> flattenParamsMap(Map<String, Object> map, String str) throws InvalidRequestException {
        LinkedList linkedList = new LinkedList();
        if (map == null) {
            return linkedList;
        }
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            Object value = entry.getValue();
            if (str != null) {
                str2 = String.format("%s[%s]", new Object[]{str, str2});
            }
            linkedList.addAll(flattenParamsValue(value, str2));
        }
        return linkedList;
    }

    private static List<Parameter> flattenParamsValue(Object obj, String str) throws InvalidRequestException {
        new LinkedList();
        if (obj instanceof Map) {
            return flattenParamsMap((Map) obj, str);
        }
        if (obj instanceof List) {
            return flattenParamsList((List) obj, str);
        }
        if ("".equals(obj)) {
            StringBuilder sb = new StringBuilder();
            sb.append("You cannot set '");
            sb.append(str);
            sb.append("' to an empty string. ");
            sb.append("We interpret empty strings as null in requests. ");
            sb.append("You may set '");
            sb.append(str);
            sb.append("' to null to delete the property.");
            InvalidRequestException invalidRequestException = new InvalidRequestException(sb.toString(), str, null, Integer.valueOf(0), null);
            throw invalidRequestException;
        } else if (obj == null) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(new Parameter(str, ""));
            return linkedList;
        } else {
            LinkedList linkedList2 = new LinkedList();
            linkedList2.add(new Parameter(str, obj.toString()));
            return linkedList2;
        }
    }

    private static String getResponseBody(InputStream inputStream) throws IOException {
        String next = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
        inputStream.close();
        return next;
    }

    private static StripeResponse makeURLConnectionRequest(RequestMethod requestMethod, String str, String str2, RequestOptions requestOptions) throws APIConnectionException {
        HttpURLConnection httpURLConnection;
        String str3;
        HttpURLConnection httpURLConnection2 = null;
        try {
            switch (requestMethod) {
                case GET:
                    httpURLConnection = createGetConnection(str, str2, requestOptions);
                    break;
                case POST:
                    httpURLConnection = createPostConnection(str, str2, requestOptions);
                    break;
                case DELETE:
                    httpURLConnection = createDeleteConnection(str, str2, requestOptions);
                    break;
                default:
                    throw new APIConnectionException(String.format("Unrecognized HTTP method %s. This indicates a bug in the Stripe bindings. Please contact support@stripe.com for assistance.", new Object[]{requestMethod}));
            }
            HttpURLConnection httpURLConnection3 = httpURLConnection;
            int responseCode = httpURLConnection3.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                str3 = getResponseBody(httpURLConnection3.getErrorStream());
            } else {
                str3 = getResponseBody(httpURLConnection3.getInputStream());
            }
            StripeResponse stripeResponse = new StripeResponse(responseCode, str3, httpURLConnection3.getHeaderFields());
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            return stripeResponse;
        } catch (IOException e) {
            throw new APIConnectionException(String.format("IOException during API request to Stripe (%s): %s Please check your internet connection and try again. If this problem persists,you should check Stripe's service status at https://twitter.com/stripestatus, or let us know at support@stripe.com.", new Object[]{Stripe.getApiBase(), e.getMessage()}), e);
        } catch (Throwable th) {
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private static <T> T _request(RequestMethod requestMethod, String str, Map<String, Object> map, Class<T> cls, RequestType requestType, RequestOptions requestOptions) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        String str2;
        String apiKey;
        StripeResponse stripeResponse;
        List list;
        if (requestOptions == null) {
            requestOptions = RequestOptions.getDefault();
        }
        Boolean valueOf = Boolean.valueOf(true);
        String str3 = null;
        try {
            str2 = Security.getProperty("networkaddress.cache.ttl");
            try {
                Security.setProperty("networkaddress.cache.ttl", "0");
            } catch (SecurityException unused) {
            }
        } catch (SecurityException unused2) {
            str2 = null;
            valueOf = Boolean.valueOf(false);
            apiKey = requestOptions.getApiKey();
            if (apiKey != null) {
            }
            throw new AuthenticationException("No API key provided. (HINT: set your API key using 'Stripe.apiKey = <API-KEY>'. You can generate API keys from the Stripe web interface. See https://stripe.com/api for details or email support@stripe.com if you have questions.", null, Integer.valueOf(0));
        }
        apiKey = requestOptions.getApiKey();
        if (apiKey != null || apiKey.trim().isEmpty()) {
            throw new AuthenticationException("No API key provided. (HINT: set your API key using 'Stripe.apiKey = <API-KEY>'. You can generate API keys from the Stripe web interface. See https://stripe.com/api for details or email support@stripe.com if you have questions.", null, Integer.valueOf(0));
        }
        try {
            switch (requestType) {
                case NORMAL:
                    stripeResponse = getStripeResponse(requestMethod, str, map, requestOptions);
                    break;
                case MULTIPART:
                    stripeResponse = getMultipartStripeResponse(requestMethod, str, map, requestOptions);
                    break;
                default:
                    throw new RuntimeException("Invalid APIResource request type. This indicates a bug in the Stripe bindings. Please contact support@stripe.com for assistance.");
            }
            int i = stripeResponse.responseCode;
            String str4 = stripeResponse.responseBody;
            Map responseHeaders = stripeResponse.getResponseHeaders();
            if (responseHeaders == null) {
                list = null;
            } else {
                list = (List) responseHeaders.get("Request-Id");
            }
            if (list != null && list.size() > 0) {
                str3 = (String) list.get(0);
            }
            if (i < 200 || i >= 300) {
                handleAPIError(str4, i, str3);
            }
            T fromJson = APIResource.GSON.fromJson(str4, cls);
            return fromJson;
        } finally {
            if (valueOf.booleanValue()) {
                if (str2 == null) {
                    Security.setProperty("networkaddress.cache.ttl", "-1");
                } else {
                    Security.setProperty("networkaddress.cache.ttl", str2);
                }
            }
        }
    }

    private static StripeResponse getStripeResponse(RequestMethod requestMethod, String str, Map<String, Object> map, RequestOptions requestOptions) throws InvalidRequestException, APIConnectionException, APIException {
        try {
            String createQuery = createQuery(map);
            try {
                return makeURLConnectionRequest(requestMethod, str, createQuery, requestOptions);
            } catch (ClassCastException e) {
                if (System.getProperty("com.google.appengine.runtime.environment", null) != null) {
                    return makeAppEngineRequest(requestMethod, str, createQuery, requestOptions);
                }
                throw e;
            }
        } catch (UnsupportedEncodingException e2) {
            InvalidRequestException invalidRequestException = new InvalidRequestException("Unable to encode parameters to UTF-8. Please contact support@stripe.com for assistance.", null, null, Integer.valueOf(0), e2);
            throw invalidRequestException;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0123 A[SYNTHETIC, Splitter:B:48:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.stripe.net.StripeResponse getMultipartStripeResponse(com.stripe.net.APIResource.RequestMethod r11, java.lang.String r12, java.util.Map<java.lang.String, java.lang.Object> r13, com.stripe.net.RequestOptions r14) throws com.stripe.exception.InvalidRequestException, com.stripe.exception.APIConnectionException, com.stripe.exception.APIException {
        /*
            com.stripe.net.APIResource$RequestMethod r0 = com.stripe.net.APIResource.RequestMethod.POST
            r1 = 0
            if (r11 == r0) goto L_0x0015
            com.stripe.exception.InvalidRequestException r11 = new com.stripe.exception.InvalidRequestException
            java.lang.String r3 = "Multipart requests for HTTP methods other than POST are currently not supported."
            r4 = 0
            r5 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)
            r7 = 0
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7)
            throw r11
        L_0x0015:
            r11 = 0
            r0 = 1
            java.net.HttpURLConnection r12 = createStripeConnection(r12, r14)     // Catch:{ IOException -> 0x0130, all -> 0x012b }
            java.lang.String r14 = com.stripe.net.MultipartProcessor.getBoundary()     // Catch:{ IOException -> 0x0129 }
            r12.setDoOutput(r0)     // Catch:{ IOException -> 0x0129 }
            java.lang.String r2 = "POST"
            r12.setRequestMethod(r2)     // Catch:{ IOException -> 0x0129 }
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "multipart/form-data; boundary=%s"
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x0129 }
            r4[r1] = r14     // Catch:{ IOException -> 0x0129 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ IOException -> 0x0129 }
            r12.setRequestProperty(r2, r3)     // Catch:{ IOException -> 0x0129 }
            com.stripe.net.MultipartProcessor r2 = new com.stripe.net.MultipartProcessor     // Catch:{ all -> 0x011e }
            java.lang.String r3 = "UTF-8"
            r2.<init>(r12, r14, r3)     // Catch:{ all -> 0x011e }
            java.util.Set r11 = r13.entrySet()     // Catch:{ all -> 0x011c }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x011c }
        L_0x0045:
            boolean r13 = r11.hasNext()     // Catch:{ all -> 0x011c }
            if (r13 == 0) goto L_0x00eb
            java.lang.Object r13 = r11.next()     // Catch:{ all -> 0x011c }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ all -> 0x011c }
            java.lang.Object r14 = r13.getKey()     // Catch:{ all -> 0x011c }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x011c }
            java.lang.Object r13 = r13.getValue()     // Catch:{ all -> 0x011c }
            boolean r3 = r13 instanceof java.io.File     // Catch:{ all -> 0x011c }
            if (r3 == 0) goto L_0x00e4
            java.io.File r13 = (java.io.File) r13     // Catch:{ all -> 0x011c }
            boolean r3 = r13.exists()     // Catch:{ all -> 0x011c }
            if (r3 != 0) goto L_0x008b
            com.stripe.exception.InvalidRequestException r11 = new com.stripe.exception.InvalidRequestException     // Catch:{ all -> 0x011c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x011c }
            r13.<init>()     // Catch:{ all -> 0x011c }
            java.lang.String r3 = "File for key "
            r13.append(r3)     // Catch:{ all -> 0x011c }
            r13.append(r14)     // Catch:{ all -> 0x011c }
            java.lang.String r14 = " must exist."
            r13.append(r14)     // Catch:{ all -> 0x011c }
            java.lang.String r5 = r13.toString()     // Catch:{ all -> 0x011c }
            r6 = 0
            r7 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            r9 = 0
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x011c }
            throw r11     // Catch:{ all -> 0x011c }
        L_0x008b:
            boolean r3 = r13.isFile()     // Catch:{ all -> 0x011c }
            if (r3 != 0) goto L_0x00b5
            com.stripe.exception.InvalidRequestException r11 = new com.stripe.exception.InvalidRequestException     // Catch:{ all -> 0x011c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x011c }
            r13.<init>()     // Catch:{ all -> 0x011c }
            java.lang.String r3 = "File for key "
            r13.append(r3)     // Catch:{ all -> 0x011c }
            r13.append(r14)     // Catch:{ all -> 0x011c }
            java.lang.String r14 = " must be a file and not a directory."
            r13.append(r14)     // Catch:{ all -> 0x011c }
            java.lang.String r5 = r13.toString()     // Catch:{ all -> 0x011c }
            r6 = 0
            r7 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            r9 = 0
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x011c }
            throw r11     // Catch:{ all -> 0x011c }
        L_0x00b5:
            boolean r3 = r13.canRead()     // Catch:{ all -> 0x011c }
            if (r3 != 0) goto L_0x00df
            com.stripe.exception.InvalidRequestException r11 = new com.stripe.exception.InvalidRequestException     // Catch:{ all -> 0x011c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x011c }
            r13.<init>()     // Catch:{ all -> 0x011c }
            java.lang.String r3 = "Must have read permissions on file for key "
            r13.append(r3)     // Catch:{ all -> 0x011c }
            r13.append(r14)     // Catch:{ all -> 0x011c }
            java.lang.String r14 = "."
            r13.append(r14)     // Catch:{ all -> 0x011c }
            java.lang.String r5 = r13.toString()     // Catch:{ all -> 0x011c }
            r6 = 0
            r7 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x011c }
            r9 = 0
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x011c }
            throw r11     // Catch:{ all -> 0x011c }
        L_0x00df:
            r2.addFileField(r14, r13)     // Catch:{ all -> 0x011c }
            goto L_0x0045
        L_0x00e4:
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x011c }
            r2.addFormField(r14, r13)     // Catch:{ all -> 0x011c }
            goto L_0x0045
        L_0x00eb:
            if (r2 == 0) goto L_0x00f0
            r2.finish()     // Catch:{ IOException -> 0x0129 }
        L_0x00f0:
            int r11 = r12.getResponseCode()     // Catch:{ IOException -> 0x0129 }
            r13 = 200(0xc8, float:2.8E-43)
            if (r11 < r13) goto L_0x0105
            r13 = 300(0x12c, float:4.2E-43)
            if (r11 >= r13) goto L_0x0105
            java.io.InputStream r13 = r12.getInputStream()     // Catch:{ IOException -> 0x0129 }
            java.lang.String r13 = getResponseBody(r13)     // Catch:{ IOException -> 0x0129 }
            goto L_0x010d
        L_0x0105:
            java.io.InputStream r13 = r12.getErrorStream()     // Catch:{ IOException -> 0x0129 }
            java.lang.String r13 = getResponseBody(r13)     // Catch:{ IOException -> 0x0129 }
        L_0x010d:
            java.util.Map r14 = r12.getHeaderFields()     // Catch:{ IOException -> 0x0129 }
            com.stripe.net.StripeResponse r2 = new com.stripe.net.StripeResponse     // Catch:{ IOException -> 0x0129 }
            r2.<init>(r11, r13, r14)     // Catch:{ IOException -> 0x0129 }
            if (r12 == 0) goto L_0x011b
            r12.disconnect()
        L_0x011b:
            return r2
        L_0x011c:
            r11 = move-exception
            goto L_0x0121
        L_0x011e:
            r13 = move-exception
            r2 = r11
            r11 = r13
        L_0x0121:
            if (r2 == 0) goto L_0x0126
            r2.finish()     // Catch:{ IOException -> 0x0129 }
        L_0x0126:
            throw r11     // Catch:{ IOException -> 0x0129 }
        L_0x0127:
            r11 = move-exception
            goto L_0x014f
        L_0x0129:
            r11 = move-exception
            goto L_0x0134
        L_0x012b:
            r12 = move-exception
            r10 = r12
            r12 = r11
            r11 = r10
            goto L_0x014f
        L_0x0130:
            r12 = move-exception
            r10 = r12
            r12 = r11
            r11 = r10
        L_0x0134:
            com.stripe.exception.APIConnectionException r13 = new com.stripe.exception.APIConnectionException     // Catch:{ all -> 0x0127 }
            java.lang.String r14 = "IOException during API request to Stripe (%s): %s Please check your internet connection and try again. If this problem persists,you should check Stripe's service status at https://twitter.com/stripestatus, or let us know at support@stripe.com."
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0127 }
            java.lang.String r3 = com.stripe.Stripe.getApiBase()     // Catch:{ all -> 0x0127 }
            r2[r1] = r3     // Catch:{ all -> 0x0127 }
            java.lang.String r1 = r11.getMessage()     // Catch:{ all -> 0x0127 }
            r2[r0] = r1     // Catch:{ all -> 0x0127 }
            java.lang.String r14 = java.lang.String.format(r14, r2)     // Catch:{ all -> 0x0127 }
            r13.<init>(r14, r11)     // Catch:{ all -> 0x0127 }
            throw r13     // Catch:{ all -> 0x0127 }
        L_0x014f:
            if (r12 == 0) goto L_0x0154
            r12.disconnect()
        L_0x0154:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.net.LiveStripeResponseGetter.getMultipartStripeResponse(com.stripe.net.APIResource$RequestMethod, java.lang.String, java.util.Map, com.stripe.net.RequestOptions):com.stripe.net.StripeResponse");
    }

    private static void handleAPIError(String str, int i, String str2) throws InvalidRequestException, AuthenticationException, CardException, APIException {
        Error access$000 = ((ErrorContainer) APIResource.GSON.fromJson(str, ErrorContainer.class)).error;
        if (i == 404) {
            InvalidRequestException invalidRequestException = new InvalidRequestException(access$000.message, access$000.param, str2, Integer.valueOf(i), null);
            throw invalidRequestException;
        } else if (i != 429) {
            switch (i) {
                case 400:
                    InvalidRequestException invalidRequestException2 = new InvalidRequestException(access$000.message, access$000.param, str2, Integer.valueOf(i), null);
                    throw invalidRequestException2;
                case 401:
                    throw new AuthenticationException(access$000.message, str2, Integer.valueOf(i));
                case 402:
                    CardException cardException = new CardException(access$000.message, str2, access$000.code, access$000.param, access$000.decline_code, access$000.charge, Integer.valueOf(i), null);
                    throw cardException;
                default:
                    throw new APIException(access$000.message, str2, Integer.valueOf(i), null);
            }
        } else {
            RateLimitException rateLimitException = new RateLimitException(access$000.message, access$000.param, str2, Integer.valueOf(i), null);
            throw rateLimitException;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        java.lang.System.err.println("Warning: this App Engine SDK version does not allow verification of SSL certificates;this exposes you to a MITM attack. Please upgrade your App Engine SDK to >=1.5.0. If you have questions, contact support@stripe.com.");
        r12 = r11.getDeclaredMethod("withDefaults", new java.lang.Class[0]).invoke(null, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x017d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0188, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0189, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0194, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0195, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x01a0, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x01a1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01ac, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01ad, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01b8, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01c5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01d0, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01d1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01dc, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01dd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01e8, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01e9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01f4, code lost:
        throw new com.stripe.exception.APIException(r2, null, java.lang.Integer.valueOf(0), r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004c */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00ab A[Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d4 A[Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }, LOOP:0: B:19:0x00ce->B:21:0x00d4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x017d A[ExcHandler: UnsupportedEncodingException (r0v9 'e' java.io.UnsupportedEncodingException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0189 A[ExcHandler: InstantiationException (r0v8 'e' java.lang.InstantiationException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0195 A[ExcHandler: IllegalAccessException (r0v7 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x01a1 A[ExcHandler: IllegalArgumentException (r0v6 'e' java.lang.IllegalArgumentException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01ad A[ExcHandler: ClassNotFoundException (r0v5 'e' java.lang.ClassNotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01c5 A[ExcHandler: SecurityException (r0v3 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01d1 A[ExcHandler: NoSuchFieldException (r0v2 'e' java.lang.NoSuchFieldException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01dd A[ExcHandler: MalformedURLException (r0v1 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01e9 A[ExcHandler: InvocationTargetException (r0v0 'e' java.lang.reflect.InvocationTargetException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.stripe.net.StripeResponse makeAppEngineRequest(com.stripe.net.APIResource.RequestMethod r17, java.lang.String r18, java.lang.String r19, com.stripe.net.RequestOptions r20) throws com.stripe.exception.APIException {
        /*
            r1 = r17
            java.lang.String r2 = "Sorry, an unknown error occurred while trying to use the Google App Engine runtime. Please contact support@stripe.com for assistance."
            r3 = 0
            r4 = 0
            com.stripe.net.APIResource$RequestMethod r5 = com.stripe.net.APIResource.RequestMethod.GET     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r6 = 2
            r7 = 1
            if (r1 == r5) goto L_0x0014
            com.stripe.net.APIResource$RequestMethod r5 = com.stripe.net.APIResource.RequestMethod.DELETE     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            if (r1 != r5) goto L_0x0011
            goto L_0x0014
        L_0x0011:
            r5 = r18
            goto L_0x0020
        L_0x0014:
            java.lang.String r5 = "%s?%s"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r8[r4] = r18     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r8[r7] = r19     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r5 = java.lang.String.format(r5, r8)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
        L_0x0020:
            java.net.URL r8 = new java.net.URL     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r8.<init>(r5)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r5 = "com.google.appengine.api.urlfetch.HTTPMethod"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r10 = r17.name()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Field r10 = r5.getDeclaredField(r10)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r10 = r10.get(r3)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r11 = "com.google.appengine.api.urlfetch.FetchOptions$Builder"
            java.lang.Class r11 = java.lang.Class.forName(r11)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r12 = "validateCertificate"
            java.lang.Class[] r13 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x004c, InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r12 = r11.getDeclaredMethod(r12, r13)     // Catch:{ NoSuchMethodException -> 0x004c, InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r13 = new java.lang.Object[r4]     // Catch:{ NoSuchMethodException -> 0x004c, InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r12 = r12.invoke(r3, r13)     // Catch:{ NoSuchMethodException -> 0x004c, InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            goto L_0x0061
        L_0x004c:
            java.io.PrintStream r12 = java.lang.System.err     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r13 = "Warning: this App Engine SDK version does not allow verification of SSL certificates;this exposes you to a MITM attack. Please upgrade your App Engine SDK to >=1.5.0. If you have questions, contact support@stripe.com."
            r12.println(r13)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r12 = "withDefaults"
            java.lang.Class[] r13 = new java.lang.Class[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r11 = r11.getDeclaredMethod(r12, r13)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r12 = new java.lang.Object[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r12 = r11.invoke(r3, r12)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
        L_0x0061:
            java.lang.String r11 = "com.google.appengine.api.urlfetch.FetchOptions"
            java.lang.Class r11 = java.lang.Class.forName(r11)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r13 = "setDeadline"
            java.lang.Class[] r14 = new java.lang.Class[r7]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class<java.lang.Double> r15 = java.lang.Double.class
            r14[r4] = r15     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r13 = r11.getDeclaredMethod(r13, r14)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r14 = new java.lang.Object[r7]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Double r15 = new java.lang.Double     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r6 = 4632937379169042432(0x404b800000000000, double:55.0)
            r15.<init>(r6)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r14[r4] = r15     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r13.invoke(r12, r14)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r6 = "com.google.appengine.api.urlfetch.HTTPRequest"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r7 = 3
            java.lang.Class[] r13 = new java.lang.Class[r7]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class<java.net.URL> r14 = java.net.URL.class
            r13[r4] = r14     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r14 = 1
            r13[r14] = r5     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r5 = 2
            r13[r5] = r11     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Constructor r11 = r6.getDeclaredConstructor(r13)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r7[r4] = r8     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r7[r14] = r10     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r7[r5] = r12     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r5 = r11.newInstance(r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            com.stripe.net.APIResource$RequestMethod r7 = com.stripe.net.APIResource.RequestMethod.POST     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            if (r1 != r7) goto L_0x00c2
            java.lang.String r1 = "setPayload"
            java.lang.Class[] r7 = new java.lang.Class[r14]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class<byte[]> r8 = byte[].class
            r7[r4] = r8     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r1 = r6.getDeclaredMethod(r1, r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r7 = new java.lang.Object[r14]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            byte[] r8 = r19.getBytes()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r7[r4] = r8     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r1.invoke(r5, r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
        L_0x00c2:
            java.util.Map r1 = getHeaders(r20)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.util.Set r1 = r1.entrySet()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
        L_0x00ce:
            boolean r7 = r1.hasNext()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            if (r7 == 0) goto L_0x0116
            java.lang.Object r7 = r1.next()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r8 = "com.google.appengine.api.urlfetch.HTTPHeader"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r9 = 2
            java.lang.Class[] r10 = new java.lang.Class[r9]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            r10[r4] = r9     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            r11 = 1
            r10[r11] = r9     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Constructor r9 = r8.getDeclaredConstructor(r10)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r10 = 2
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r12 = r7.getKey()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r11[r4] = r12     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r7 = r7.getValue()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r12 = 1
            r11[r12] = r7     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r7 = r9.newInstance(r11)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r9 = "setHeader"
            java.lang.Class[] r11 = new java.lang.Class[r12]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r11[r4] = r8     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r8 = r6.getDeclaredMethod(r9, r11)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r9 = new java.lang.Object[r12]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r9[r4] = r7     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r8.invoke(r5, r9)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            goto L_0x00ce
        L_0x0116:
            java.lang.String r1 = "com.google.appengine.api.urlfetch.URLFetchServiceFactory"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r7 = "getURLFetchService"
            java.lang.Class[] r8 = new java.lang.Class[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r7, r8)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r1 = r1.invoke(r3, r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class r7 = r1.getClass()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r8 = "fetch"
            r9 = 1
            java.lang.Class[] r10 = new java.lang.Class[r9]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r10[r4] = r6     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r6 = r7.getDeclaredMethod(r8, r10)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r6.setAccessible(r9)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r7 = new java.lang.Object[r9]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r7[r4] = r5     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r1 = r6.invoke(r1, r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class r5 = r1.getClass()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r6 = "getResponseCode"
            java.lang.Class[] r7 = new java.lang.Class[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r5 = r5.getDeclaredMethod(r6, r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r5 = r5.invoke(r1, r6)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            int r5 = r5.intValue()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r6 = new java.lang.String     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Class r7 = r1.getClass()     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r8 = "getContent"
            java.lang.Class[] r9 = new java.lang.Class[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.reflect.Method r7 = r7.getDeclaredMethod(r8, r9)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.Object r1 = r7.invoke(r1, r8)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            byte[] r1 = (byte[]) r1     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            java.lang.String r7 = "UTF-8"
            r6.<init>(r1, r7)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            com.stripe.net.StripeResponse r1 = new com.stripe.net.StripeResponse     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            r1.<init>(r5, r6)     // Catch:{ InvocationTargetException -> 0x01e9, MalformedURLException -> 0x01dd, NoSuchFieldException -> 0x01d1, SecurityException -> 0x01c5, NoSuchMethodException -> 0x01b9, ClassNotFoundException -> 0x01ad, IllegalArgumentException -> 0x01a1, IllegalAccessException -> 0x0195, InstantiationException -> 0x0189, UnsupportedEncodingException -> 0x017d }
            return r1
        L_0x017d:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x0189:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x0195:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01a1:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01ad:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01b9:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01c5:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01d1:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01dd:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        L_0x01e9:
            r0 = move-exception
            r1 = r0
            com.stripe.exception.APIException r5 = new com.stripe.exception.APIException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.<init>(r2, r3, r4, r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.net.LiveStripeResponseGetter.makeAppEngineRequest(com.stripe.net.APIResource$RequestMethod, java.lang.String, java.lang.String, com.stripe.net.RequestOptions):com.stripe.net.StripeResponse");
    }
}
