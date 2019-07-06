package com.onfido.api.client.data;

import com.google.gson.JsonObject;

public abstract class JsonSerializable {
    public abstract JsonObject serialise();
}
