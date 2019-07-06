package com.contextlogic.wish.api.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* renamed from: com.contextlogic.wish.api.model.$AutoValue_PageItemHolder reason: invalid class name */
abstract class C$AutoValue_PageItemHolder extends C$$AutoValue_PageItemHolder {

    /* renamed from: com.contextlogic.wish.api.model.$AutoValue_PageItemHolder$GsonTypeAdapter */
    public static final class GsonTypeAdapter extends TypeAdapter<PageItemHolder> {
        private final TypeAdapter<String> actionAdapter;
        private final TypeAdapter<String> bodyAdapter;
        private final TypeAdapter<String> imgUrlAdapter;
        private final TypeAdapter<String> titleAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.titleAdapter = gson.getAdapter(String.class);
            this.bodyAdapter = gson.getAdapter(String.class);
            this.imgUrlAdapter = gson.getAdapter(String.class);
            this.actionAdapter = gson.getAdapter(String.class);
        }

        public void write(JsonWriter jsonWriter, PageItemHolder pageItemHolder) throws IOException {
            if (pageItemHolder == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("page_item_title");
            this.titleAdapter.write(jsonWriter, pageItemHolder.getTitle());
            jsonWriter.name("page_item_body");
            this.bodyAdapter.write(jsonWriter, pageItemHolder.getBody());
            jsonWriter.name("page_item_img");
            this.imgUrlAdapter.write(jsonWriter, pageItemHolder.getImgUrl());
            jsonWriter.name("page_item_action");
            this.actionAdapter.write(jsonWriter, pageItemHolder.getAction());
            jsonWriter.endObject();
        }

        public PageItemHolder read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            String str3 = null;
            String str4 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    char c = 65535;
                    int hashCode = nextName.hashCode();
                    if (hashCode != -1318831652) {
                        if (hashCode != -667303865) {
                            if (hashCode != 788210078) {
                                if (hashCode == 1516394290 && nextName.equals("page_item_action")) {
                                    c = 3;
                                }
                            } else if (nextName.equals("page_item_body")) {
                                c = 1;
                            }
                        } else if (nextName.equals("page_item_img")) {
                            c = 2;
                        }
                    } else if (nextName.equals("page_item_title")) {
                        c = 0;
                    }
                    switch (c) {
                        case 0:
                            str = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 1:
                            str2 = (String) this.bodyAdapter.read(jsonReader);
                            break;
                        case 2:
                            str3 = (String) this.imgUrlAdapter.read(jsonReader);
                            break;
                        case 3:
                            str4 = (String) this.actionAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                } else {
                    jsonReader.nextNull();
                }
            }
            jsonReader.endObject();
            return new AutoValue_PageItemHolder(str, str2, str3, str4);
        }
    }

    C$AutoValue_PageItemHolder(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }
}
