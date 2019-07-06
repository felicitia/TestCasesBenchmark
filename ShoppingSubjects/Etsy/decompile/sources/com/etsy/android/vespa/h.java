package com.etsy.android.vespa;

import com.etsy.android.lib.models.cardviewelement.PageLink;
import java.util.List;

/* compiled from: IVespaListSection */
public interface h extends k {
    k getHeader();

    List<k> getItems();

    PageLink getPageLink();

    boolean isHorizontal();

    boolean isNested();
}
