package com.onfido.api.client.data;

import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.Map;

public class ErrorData {
    private Error error = new Error();

    public static class Error {
        @Expose
        private Map<String, List<String>> fields;
        @Expose
        private String id = "Unexpected";
        @Expose
        private String message = "Unexpected";
        @Expose
        private String type = "Unexpected";

        Error() {
        }

        public String getMessage() {
            return this.message;
        }

        public Map<String, List<String>> getFields() {
            return this.fields;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Error{id='");
            sb.append(this.id);
            sb.append('\'');
            sb.append(", type='");
            sb.append(this.type);
            sb.append('\'');
            sb.append(", message='");
            sb.append(this.message);
            sb.append('\'');
            sb.append(", fields=");
            sb.append(this.fields);
            sb.append('}');
            return sb.toString();
        }
    }

    public Error getError() {
        return this.error;
    }

    public String getMessage() {
        return this.error.getMessage();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorData{error=");
        sb.append(this.error);
        sb.append('}');
        return sb.toString();
    }
}
