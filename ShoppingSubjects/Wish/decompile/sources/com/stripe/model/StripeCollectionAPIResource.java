package com.stripe.model;

import com.stripe.net.APIResource;
import java.util.List;

public abstract class StripeCollectionAPIResource<T> extends APIResource {
    List<T> data;
    Boolean hasMore;
    Integer totalCount;

    public void setData(List<T> list) {
        this.data = list;
    }

    public void setTotalCount(Integer num) {
        this.totalCount = num;
    }

    public void setHasMore(Boolean bool) {
        this.hasMore = bool;
    }
}
