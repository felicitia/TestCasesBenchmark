package com.otaliastudios.cameraview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SizeSelectors {

    private static class AndSelector implements SizeSelector {
        private SizeSelector[] values;

        private AndSelector(SizeSelector... sizeSelectorArr) {
            this.values = sizeSelectorArr;
        }

        public List<Size> select(List<Size> list) {
            for (SizeSelector select : this.values) {
                list = select.select(list);
            }
            return list;
        }
    }

    public interface Filter {
        boolean accepts(Size size);
    }

    private static class FilterSelector implements SizeSelector {
        private Filter constraint;

        private FilterSelector(Filter filter) {
            this.constraint = filter;
        }

        public List<Size> select(List<Size> list) {
            ArrayList arrayList = new ArrayList();
            for (Size size : list) {
                if (this.constraint.accepts(size)) {
                    arrayList.add(size);
                }
            }
            return arrayList;
        }
    }

    private static class OrSelector implements SizeSelector {
        private SizeSelector[] values;

        private OrSelector(SizeSelector... sizeSelectorArr) {
            this.values = sizeSelectorArr;
        }

        public List<Size> select(List<Size> list) {
            List<Size> list2 = null;
            for (SizeSelector select : this.values) {
                list2 = select.select(list);
                if (!list2.isEmpty()) {
                    break;
                }
            }
            return list2 == null ? new ArrayList() : list2;
        }
    }

    public static SizeSelector withFilter(Filter filter) {
        return new FilterSelector(filter);
    }

    public static SizeSelector maxWidth(final int i) {
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                return size.getWidth() <= i;
            }
        });
    }

    public static SizeSelector minWidth(final int i) {
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                return size.getWidth() >= i;
            }
        });
    }

    public static SizeSelector maxHeight(final int i) {
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                return size.getHeight() <= i;
            }
        });
    }

    public static SizeSelector minHeight(final int i) {
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                return size.getHeight() >= i;
            }
        });
    }

    public static SizeSelector aspectRatio(AspectRatio aspectRatio, final float f) {
        final float f2 = aspectRatio.toFloat();
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                float f = AspectRatio.of(size.getWidth(), size.getHeight()).toFloat();
                return f >= f2 - f && f <= f2 + f;
            }
        });
    }

    public static SizeSelector biggest() {
        return new SizeSelector() {
            public List<Size> select(List<Size> list) {
                Collections.sort(list);
                Collections.reverse(list);
                return list;
            }
        };
    }

    public static SizeSelector smallest() {
        return new SizeSelector() {
            public List<Size> select(List<Size> list) {
                Collections.sort(list);
                return list;
            }
        };
    }

    public static SizeSelector maxArea(final int i) {
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                return size.getHeight() * size.getWidth() <= i;
            }
        });
    }

    public static SizeSelector minArea(final int i) {
        return withFilter(new Filter() {
            public boolean accepts(Size size) {
                return size.getHeight() * size.getWidth() >= i;
            }
        });
    }

    public static SizeSelector and(SizeSelector... sizeSelectorArr) {
        return new AndSelector(sizeSelectorArr);
    }

    public static SizeSelector or(SizeSelector... sizeSelectorArr) {
        return new OrSelector(sizeSelectorArr);
    }
}
