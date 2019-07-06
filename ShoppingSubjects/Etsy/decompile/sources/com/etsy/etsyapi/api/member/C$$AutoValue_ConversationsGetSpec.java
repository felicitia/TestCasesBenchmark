package com.etsy.etsyapi.api.member;

import android.support.annotation.Nullable;
import org.apache.commons.math3.geometry.VectorFormat;

/* renamed from: com.etsy.etsyapi.api.member.$$AutoValue_ConversationsGetSpec reason: invalid class name */
abstract class C$$AutoValue_ConversationsGetSpec extends ConversationsGetSpec {
    private final Boolean friendly_timestamp;
    private final Boolean in_inbox;
    private final Boolean include_read;
    private final Boolean is_visible;
    private final Integer limit;
    private final Integer offset;
    private final Integer since;
    private final String tag_identifier;

    /* renamed from: com.etsy.etsyapi.api.member.$$AutoValue_ConversationsGetSpec$a */
    /* compiled from: $$AutoValue_ConversationsGetSpec */
    static final class a extends com.etsy.etsyapi.api.member.ConversationsGetSpec.a {
        private Integer a;
        private Boolean b;
        private Boolean c;
        private Integer d;
        private Integer e;
        private Boolean f;
        private Boolean g;
        private String h;

        a() {
        }

        a(ConversationsGetSpec conversationsGetSpec) {
            this.a = conversationsGetSpec.since();
            this.b = conversationsGetSpec.is_visible();
            this.c = conversationsGetSpec.in_inbox();
            this.d = conversationsGetSpec.limit();
            this.e = conversationsGetSpec.offset();
            this.f = conversationsGetSpec.include_read();
            this.g = conversationsGetSpec.friendly_timestamp();
            this.h = conversationsGetSpec.tag_identifier();
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a a(@Nullable Integer num) {
            this.a = num;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a a(@Nullable Boolean bool) {
            this.b = bool;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a b(@Nullable Boolean bool) {
            this.c = bool;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a b(@Nullable Integer num) {
            this.d = num;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a c(@Nullable Integer num) {
            this.e = num;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a c(@Nullable Boolean bool) {
            this.f = bool;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a d(@Nullable Boolean bool) {
            this.g = bool;
            return this;
        }

        public com.etsy.etsyapi.api.member.ConversationsGetSpec.a a(@Nullable String str) {
            this.h = str;
            return this;
        }

        public ConversationsGetSpec a() {
            AutoValue_ConversationsGetSpec autoValue_ConversationsGetSpec = new AutoValue_ConversationsGetSpec(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            return autoValue_ConversationsGetSpec;
        }
    }

    C$$AutoValue_ConversationsGetSpec(@Nullable Integer num, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Integer num2, @Nullable Integer num3, @Nullable Boolean bool3, @Nullable Boolean bool4, @Nullable String str) {
        this.since = num;
        this.is_visible = bool;
        this.in_inbox = bool2;
        this.limit = num2;
        this.offset = num3;
        this.include_read = bool3;
        this.friendly_timestamp = bool4;
        this.tag_identifier = str;
    }

    @Nullable
    public Integer since() {
        return this.since;
    }

    @Nullable
    public Boolean is_visible() {
        return this.is_visible;
    }

    @Nullable
    public Boolean in_inbox() {
        return this.in_inbox;
    }

    @Nullable
    public Integer limit() {
        return this.limit;
    }

    @Nullable
    public Integer offset() {
        return this.offset;
    }

    @Nullable
    public Boolean include_read() {
        return this.include_read;
    }

    @Nullable
    public Boolean friendly_timestamp() {
        return this.friendly_timestamp;
    }

    @Nullable
    public String tag_identifier() {
        return this.tag_identifier;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConversationsGetSpec{since=");
        sb.append(this.since);
        sb.append(", ");
        sb.append("is_visible=");
        sb.append(this.is_visible);
        sb.append(", ");
        sb.append("in_inbox=");
        sb.append(this.in_inbox);
        sb.append(", ");
        sb.append("limit=");
        sb.append(this.limit);
        sb.append(", ");
        sb.append("offset=");
        sb.append(this.offset);
        sb.append(", ");
        sb.append("include_read=");
        sb.append(this.include_read);
        sb.append(", ");
        sb.append("friendly_timestamp=");
        sb.append(this.friendly_timestamp);
        sb.append(", ");
        sb.append("tag_identifier=");
        sb.append(this.tag_identifier);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConversationsGetSpec)) {
            return false;
        }
        ConversationsGetSpec conversationsGetSpec = (ConversationsGetSpec) obj;
        if (this.since != null ? this.since.equals(conversationsGetSpec.since()) : conversationsGetSpec.since() == null) {
            if (this.is_visible != null ? this.is_visible.equals(conversationsGetSpec.is_visible()) : conversationsGetSpec.is_visible() == null) {
                if (this.in_inbox != null ? this.in_inbox.equals(conversationsGetSpec.in_inbox()) : conversationsGetSpec.in_inbox() == null) {
                    if (this.limit != null ? this.limit.equals(conversationsGetSpec.limit()) : conversationsGetSpec.limit() == null) {
                        if (this.offset != null ? this.offset.equals(conversationsGetSpec.offset()) : conversationsGetSpec.offset() == null) {
                            if (this.include_read != null ? this.include_read.equals(conversationsGetSpec.include_read()) : conversationsGetSpec.include_read() == null) {
                                if (this.friendly_timestamp != null ? this.friendly_timestamp.equals(conversationsGetSpec.friendly_timestamp()) : conversationsGetSpec.friendly_timestamp() == null) {
                                    if (this.tag_identifier != null) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((this.since == null ? 0 : this.since.hashCode()) ^ 1000003) * 1000003) ^ (this.is_visible == null ? 0 : this.is_visible.hashCode())) * 1000003) ^ (this.in_inbox == null ? 0 : this.in_inbox.hashCode())) * 1000003) ^ (this.limit == null ? 0 : this.limit.hashCode())) * 1000003) ^ (this.offset == null ? 0 : this.offset.hashCode())) * 1000003) ^ (this.include_read == null ? 0 : this.include_read.hashCode())) * 1000003) ^ (this.friendly_timestamp == null ? 0 : this.friendly_timestamp.hashCode())) * 1000003;
        if (this.tag_identifier != null) {
            i = this.tag_identifier.hashCode();
        }
        return hashCode ^ i;
    }
}
