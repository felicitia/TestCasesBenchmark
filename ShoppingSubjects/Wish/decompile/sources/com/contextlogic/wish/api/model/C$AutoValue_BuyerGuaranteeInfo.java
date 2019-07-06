package com.contextlogic.wish.api.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

/* renamed from: com.contextlogic.wish.api.model.$AutoValue_BuyerGuaranteeInfo reason: invalid class name */
abstract class C$AutoValue_BuyerGuaranteeInfo extends C$$AutoValue_BuyerGuaranteeInfo {

    /* renamed from: com.contextlogic.wish.api.model.$AutoValue_BuyerGuaranteeInfo$GsonTypeAdapter */
    public static final class GsonTypeAdapter extends TypeAdapter<BuyerGuaranteeInfo> {
        private final TypeAdapter<List<PageItemHolder>> pageItemsAdapter;
        private final TypeAdapter<String> pageSubtitleAdapter;
        private final TypeAdapter<String> pageTitleAdapter;
        private final TypeAdapter<String> sectionBodyAdapter;
        private final TypeAdapter<String> sectionSubtitleAdapter;
        private final TypeAdapter<String> sectionTitleAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.sectionTitleAdapter = gson.getAdapter(String.class);
            this.sectionSubtitleAdapter = gson.getAdapter(String.class);
            this.sectionBodyAdapter = gson.getAdapter(String.class);
            this.pageTitleAdapter = gson.getAdapter(String.class);
            this.pageSubtitleAdapter = gson.getAdapter(String.class);
            this.pageItemsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, PageItemHolder.class));
        }

        public void write(JsonWriter jsonWriter, BuyerGuaranteeInfo buyerGuaranteeInfo) throws IOException {
            if (buyerGuaranteeInfo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("section_title");
            this.sectionTitleAdapter.write(jsonWriter, buyerGuaranteeInfo.getSectionTitle());
            jsonWriter.name("section_subtitle");
            this.sectionSubtitleAdapter.write(jsonWriter, buyerGuaranteeInfo.getSectionSubtitle());
            jsonWriter.name("section_body");
            this.sectionBodyAdapter.write(jsonWriter, buyerGuaranteeInfo.getSectionBody());
            jsonWriter.name("page_title");
            this.pageTitleAdapter.write(jsonWriter, buyerGuaranteeInfo.getPageTitle());
            jsonWriter.name("page_subtitle");
            this.pageSubtitleAdapter.write(jsonWriter, buyerGuaranteeInfo.getPageSubtitle());
            jsonWriter.name("page_items");
            this.pageItemsAdapter.write(jsonWriter, buyerGuaranteeInfo.getPageItems());
            jsonWriter.endObject();
        }

        public BuyerGuaranteeInfo read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            List list = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    char c = 65535;
                    switch (nextName.hashCode()) {
                        case -1308851074:
                            if (nextName.equals("section_title")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 389319624:
                            if (nextName.equals("page_subtitle")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 649984700:
                            if (nextName.equals("section_body")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 1620592144:
                            if (nextName.equals("page_items")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 1630437544:
                            if (nextName.equals("page_title")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1835331634:
                            if (nextName.equals("section_subtitle")) {
                                c = 1;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            str = (String) this.sectionTitleAdapter.read(jsonReader);
                            break;
                        case 1:
                            str2 = (String) this.sectionSubtitleAdapter.read(jsonReader);
                            break;
                        case 2:
                            str3 = (String) this.sectionBodyAdapter.read(jsonReader);
                            break;
                        case 3:
                            str4 = (String) this.pageTitleAdapter.read(jsonReader);
                            break;
                        case 4:
                            str5 = (String) this.pageSubtitleAdapter.read(jsonReader);
                            break;
                        case 5:
                            list = (List) this.pageItemsAdapter.read(jsonReader);
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
            AutoValue_BuyerGuaranteeInfo autoValue_BuyerGuaranteeInfo = new AutoValue_BuyerGuaranteeInfo(str, str2, str3, str4, str5, list);
            return autoValue_BuyerGuaranteeInfo;
        }
    }

    C$AutoValue_BuyerGuaranteeInfo(String str, String str2, String str3, String str4, String str5, List<PageItemHolder> list) {
        super(str, str2, str3, str4, str5, list);
    }
}
