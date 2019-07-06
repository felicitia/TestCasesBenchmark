package com.onfido.api.client.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Challenge extends JsonSerializable {
    private int[] intArrayQuery;
    private String stringQuery;
    private Type type;

    public enum Type {
        MOVEMENT("movement"),
        RECITE("recite");
        
        private String id;

        private Type(String str) {
            this.id = str;
        }

        public String getId() {
            return this.id;
        }
    }

    public Challenge(Type type2, String str) {
        this.type = type2;
        this.stringQuery = str;
    }

    public Challenge(Type type2, int[] iArr) {
        this.type = type2;
        this.intArrayQuery = iArr;
    }

    public Type getType() {
        return this.type;
    }

    public String getStringQuery() {
        return this.stringQuery;
    }

    public int[] getIntArrayQuery() {
        return this.intArrayQuery;
    }

    public JsonObject serialise() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", getType().getId());
        switch (this.type) {
            case MOVEMENT:
                jsonObject.addProperty("query", getStringQuery());
                break;
            case RECITE:
                JsonArray jsonArray = new JsonArray();
                for (int valueOf : getIntArrayQuery()) {
                    jsonArray.add((Number) Integer.valueOf(valueOf));
                }
                jsonObject.add("query", jsonArray);
                break;
        }
        return jsonObject;
    }
}
