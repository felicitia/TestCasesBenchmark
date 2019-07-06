package com.contextlogic.wish.util;

import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

    public interface DataParser<T, S> {
        T parseData(S s) throws JSONException, ParseException;
    }

    public static String optString(JSONObject jSONObject, String str) {
        return optString(jSONObject, str, null);
    }

    public static String optString(JSONObject jSONObject, String str, String str2) {
        if (jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, str2);
    }

    public static <T, S> ArrayList<T> parseArray(JSONObject jSONObject, String str, DataParser<T, S> dataParser) {
        ArrayList<T> arrayList = new ArrayList<>();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(dataParser.parseData(optJSONArray.get(i)));
                } catch (Throwable unused) {
                }
            }
        }
        return arrayList;
    }

    public static boolean hasValue(JSONObject jSONObject, String str) {
        return jSONObject.has(str) && !jSONObject.isNull(str);
    }

    public static void serializeJsonObject(JSONObject jSONObject, JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                if (jSONObject.isNull(str)) {
                    jsonWriter.name(str).nullValue();
                } else {
                    Object obj = jSONObject.get(str);
                    if (obj instanceof JSONObject) {
                        serializeJsonObject((JSONObject) obj, jsonWriter);
                    } else if (obj instanceof JSONArray) {
                        serializeJsonArray((JSONArray) obj, jsonWriter);
                    } else if (obj instanceof String) {
                        jsonWriter.name(str).value((String) obj);
                    } else if (obj instanceof Double) {
                        jsonWriter.name(str).value((Number) (Double) obj);
                    } else if (obj instanceof Number) {
                        jsonWriter.name(str).value((Number) obj);
                    } else if (obj instanceof Integer) {
                        jsonWriter.name(str).value((Number) (Integer) obj);
                    } else if (obj instanceof Long) {
                        jsonWriter.name(str).value((Number) (Long) obj);
                    } else if (obj instanceof Boolean) {
                        jsonWriter.name(str).value((Boolean) obj);
                    }
                }
            } catch (JSONException unused) {
            }
        }
        jsonWriter.endObject();
    }

    public static void serializeJsonArray(JSONArray jSONArray, JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.isNull(i)) {
                    jsonWriter.nullValue();
                } else {
                    Object obj = jSONArray.get(i);
                    if (obj instanceof JSONObject) {
                        serializeJsonObject((JSONObject) obj, jsonWriter);
                    } else if (obj instanceof JSONArray) {
                        serializeJsonArray((JSONArray) obj, jsonWriter);
                    } else if (obj instanceof String) {
                        jsonWriter.value((String) obj);
                    } else if (obj instanceof Double) {
                        jsonWriter.value((Number) (Double) obj);
                    } else if (obj instanceof Number) {
                        jsonWriter.value((Number) obj);
                    } else if (obj instanceof Integer) {
                        jsonWriter.value((Number) (Integer) obj);
                    } else if (obj instanceof Long) {
                        jsonWriter.value((Number) (Long) obj);
                    } else if (obj instanceof Boolean) {
                        jsonWriter.value((Boolean) obj);
                    }
                }
            } catch (JSONException unused) {
            }
        }
        jsonWriter.endArray();
    }
}
