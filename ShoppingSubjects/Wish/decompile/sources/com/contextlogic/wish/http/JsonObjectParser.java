package com.contextlogic.wish.http;

import com.google.gson.stream.JsonReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectParser {
    public JSONObject parseJsonObject(JsonReader jsonReader) throws IOException, JSONException {
        JSONObject jSONObject = new JSONObject();
        jsonReader.beginObject();
        while (true) {
            String str = null;
            while (jsonReader.hasNext()) {
                switch (jsonReader.peek()) {
                    case BEGIN_ARRAY:
                        if (str != null) {
                            jSONObject.put(str, parseJsonArray(jsonReader));
                            continue;
                        } else {
                            throw new JSONException("Value found without name");
                        }
                    case END_ARRAY:
                        throw new JSONException("Invalid token found: End Array");
                    case BEGIN_OBJECT:
                        if (str != null) {
                            jSONObject.put(str, parseJsonObject(jsonReader));
                            continue;
                        } else {
                            throw new JSONException("Value found without name");
                        }
                    case END_OBJECT:
                        throw new JSONException("Invalid token found: End Object");
                    case NAME:
                        str = jsonReader.nextName();
                        break;
                    case STRING:
                        if (str != null) {
                            jSONObject.put(str, jsonReader.nextString());
                            continue;
                        } else {
                            throw new JSONException("Value found without name");
                        }
                    case NUMBER:
                        if (str != null) {
                            Double valueOf = Double.valueOf(jsonReader.nextDouble());
                            long longValue = valueOf.longValue();
                            if (((double) longValue) != valueOf.doubleValue()) {
                                jSONObject.put(str, valueOf);
                                break;
                            } else {
                                jSONObject.put(str, longValue);
                                continue;
                            }
                        } else {
                            throw new JSONException("Value found without name");
                        }
                    case BOOLEAN:
                        if (str != null) {
                            jSONObject.put(str, jsonReader.nextBoolean());
                            continue;
                        } else {
                            throw new JSONException("Value found without name");
                        }
                    case NULL:
                        jSONObject.put(str, JSONObject.NULL);
                        jsonReader.skipValue();
                        break;
                    case END_DOCUMENT:
                        throw new JSONException("Invalid token found: End Document");
                }
            }
            jsonReader.endObject();
            return jSONObject;
        }
    }

    public JSONArray parseJsonArray(JsonReader jsonReader) throws IOException, JSONException {
        JSONArray jSONArray = new JSONArray();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            switch (jsonReader.peek()) {
                case BEGIN_ARRAY:
                    jSONArray.put(parseJsonArray(jsonReader));
                    break;
                case END_ARRAY:
                    throw new JSONException("Invalid token found: End Array");
                case BEGIN_OBJECT:
                    jSONArray.put(parseJsonObject(jsonReader));
                    break;
                case END_OBJECT:
                    throw new JSONException("Invalid token found: End Object");
                case NAME:
                    throw new JSONException("Invalid token found: Name");
                case STRING:
                    jSONArray.put(jsonReader.nextString());
                    break;
                case NUMBER:
                    Double valueOf = Double.valueOf(jsonReader.nextDouble());
                    long longValue = valueOf.longValue();
                    if (((double) longValue) != valueOf.doubleValue()) {
                        jSONArray.put(valueOf);
                        break;
                    } else {
                        jSONArray.put(Long.valueOf(longValue));
                        break;
                    }
                case BOOLEAN:
                    jSONArray.put(jsonReader.nextBoolean());
                    break;
                case NULL:
                    jSONArray.put(JSONObject.NULL);
                    jsonReader.skipValue();
                    break;
                case END_DOCUMENT:
                    throw new JSONException("Invalid token found: End Document");
            }
        }
        jsonReader.endArray();
        return jSONArray;
    }
}
