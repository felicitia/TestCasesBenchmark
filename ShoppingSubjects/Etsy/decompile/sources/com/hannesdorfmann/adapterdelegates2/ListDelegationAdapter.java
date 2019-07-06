package com.hannesdorfmann.adapterdelegates2;

import android.support.annotation.NonNull;
import java.util.List;

public class ListDelegationAdapter<T extends List<?>> extends AbsDelegationAdapter<T> {
    public ListDelegationAdapter() {
    }

    public ListDelegationAdapter(@NonNull c<T> cVar) {
        super(cVar);
    }

    public int getItemCount() {
        if (this.items == null) {
            return 0;
        }
        return ((List) this.items).size();
    }
}
