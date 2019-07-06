package com.facebook;

import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GraphResponse {
    private final HttpURLConnection connection;
    private final FacebookRequestError error;
    private final JSONObject graphObject;
    private final JSONArray graphObjectArray;
    private final String rawResponse;
    private final GraphRequest request;

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, null, null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, null, jSONArray, null);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }

    GraphResponse(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        this.request = graphRequest;
        this.connection = httpURLConnection;
        this.rawResponse = str;
        this.graphObject = jSONObject;
        this.graphObjectArray = jSONArray;
        this.error = facebookRequestError;
    }

    public final FacebookRequestError getError() {
        return this.error;
    }

    public final JSONObject getJSONObject() {
        return this.graphObject;
    }

    public String toString() {
        String str;
        try {
            Locale locale = Locale.US;
            String str2 = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.connection != null ? this.connection.getResponseCode() : 200);
            str = String.format(locale, str2, objArr);
        } catch (IOException unused) {
            str = "unknown";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{Response: ");
        sb.append(" responseCode: ");
        sb.append(str);
        sb.append(", graphObject: ");
        sb.append(this.graphObject);
        sb.append(", error: ");
        sb.append(this.error);
        sb.append("}");
        return sb.toString();
    }

    static List<GraphResponse> fromHttpConnection(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            if (httpURLConnection.getResponseCode() >= 400) {
                inputStream = httpURLConnection.getErrorStream();
            } else {
                inputStream = httpURLConnection.getInputStream();
            }
            inputStream2 = inputStream;
            return createResponsesFromStream(inputStream2, httpURLConnection, graphRequestBatch);
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e);
            return constructErrorResponses(graphRequestBatch, httpURLConnection, e);
        } catch (Exception e2) {
            Logger.log(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e2);
            return constructErrorResponses(graphRequestBatch, httpURLConnection, new FacebookException((Throwable) e2));
        } finally {
            Utility.closeQuietly(inputStream2);
        }
    }

    static List<GraphResponse> createResponsesFromStream(InputStream inputStream, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        String readStreamToString = Utility.readStreamToString(inputStream);
        Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(readStreamToString.length()), readStreamToString);
        return createResponsesFromString(readStreamToString, httpURLConnection, graphRequestBatch);
    }

    static List<GraphResponse> createResponsesFromString(String str, HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) throws FacebookException, JSONException, IOException {
        List<GraphResponse> createResponsesFromObject = createResponsesFromObject(httpURLConnection, graphRequestBatch, new JSONTokener(str).nextValue());
        Logger.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", graphRequestBatch.getId(), Integer.valueOf(str.length()), createResponsesFromObject);
        return createResponsesFromObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.facebook.GraphResponse> createResponsesFromObject(java.net.HttpURLConnection r7, java.util.List<com.facebook.GraphRequest> r8, java.lang.Object r9) throws com.facebook.FacebookException, org.json.JSONException {
        /*
            int r0 = r8.size()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L_0x0051
            java.lang.Object r3 = r8.get(r2)
            com.facebook.GraphRequest r3 = (com.facebook.GraphRequest) r3
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            r4.<init>()     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            java.lang.String r5 = "body"
            r4.put(r5, r9)     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            if (r7 == 0) goto L_0x0024
            int r5 = r7.getResponseCode()     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            goto L_0x0026
        L_0x0024:
            r5 = 200(0xc8, float:2.8E-43)
        L_0x0026:
            java.lang.String r6 = "code"
            r4.put(r6, r5)     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            r5.<init>()     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            r5.put(r4)     // Catch:{ JSONException -> 0x0043, IOException -> 0x0034 }
            goto L_0x0052
        L_0x0034:
            r4 = move-exception
            com.facebook.GraphResponse r5 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>(r7, r4)
            r5.<init>(r3, r7, r6)
            r1.add(r5)
            goto L_0x0051
        L_0x0043:
            r4 = move-exception
            com.facebook.GraphResponse r5 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>(r7, r4)
            r5.<init>(r3, r7, r6)
            r1.add(r5)
        L_0x0051:
            r5 = r9
        L_0x0052:
            boolean r3 = r5 instanceof org.json.JSONArray
            if (r3 == 0) goto L_0x0098
            org.json.JSONArray r5 = (org.json.JSONArray) r5
            int r3 = r5.length()
            if (r3 == r0) goto L_0x005f
            goto L_0x0098
        L_0x005f:
            int r0 = r5.length()
            if (r2 >= r0) goto L_0x0097
            java.lang.Object r0 = r8.get(r2)
            com.facebook.GraphRequest r0 = (com.facebook.GraphRequest) r0
            java.lang.Object r3 = r5.get(r2)     // Catch:{ JSONException -> 0x0086, FacebookException -> 0x0077 }
            com.facebook.GraphResponse r3 = createResponseFromObject(r0, r7, r3, r9)     // Catch:{ JSONException -> 0x0086, FacebookException -> 0x0077 }
            r1.add(r3)     // Catch:{ JSONException -> 0x0086, FacebookException -> 0x0077 }
            goto L_0x0094
        L_0x0077:
            r3 = move-exception
            com.facebook.GraphResponse r4 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>(r7, r3)
            r4.<init>(r0, r7, r6)
            r1.add(r4)
            goto L_0x0094
        L_0x0086:
            r3 = move-exception
            com.facebook.GraphResponse r4 = new com.facebook.GraphResponse
            com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
            r6.<init>(r7, r3)
            r4.<init>(r0, r7, r6)
            r1.add(r4)
        L_0x0094:
            int r2 = r2 + 1
            goto L_0x005f
        L_0x0097:
            return r1
        L_0x0098:
            com.facebook.FacebookException r7 = new com.facebook.FacebookException
            java.lang.String r8 = "Unexpected number of results"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.GraphResponse.createResponsesFromObject(java.net.HttpURLConnection, java.util.List, java.lang.Object):java.util.List");
    }

    private static GraphResponse createResponseFromObject(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) throws JSONException {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError checkResponseAndCreateError = FacebookRequestError.checkResponseAndCreateError(jSONObject, obj2, httpURLConnection);
            if (checkResponseAndCreateError != null) {
                if (checkResponseAndCreateError.getErrorCode() == 190 && Utility.isCurrentAccessToken(graphRequest.getAccessToken())) {
                    AccessToken.setCurrentAccessToken(null);
                }
                return new GraphResponse(graphRequest, httpURLConnection, checkResponseAndCreateError);
            }
            Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (stringPropertyAsJSON instanceof JSONObject) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONObject) stringPropertyAsJSON);
            }
            if (stringPropertyAsJSON instanceof JSONArray) {
                return new GraphResponse(graphRequest, httpURLConnection, stringPropertyAsJSON.toString(), (JSONArray) stringPropertyAsJSON);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new GraphResponse(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Got unexpected object type in response, class: ");
        sb.append(obj.getClass().getSimpleName());
        throw new FacebookException(sb.toString());
    }

    static List<GraphResponse> constructErrorResponses(List<GraphRequest> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new GraphResponse((GraphRequest) list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) facebookException)));
        }
        return arrayList;
    }
}
