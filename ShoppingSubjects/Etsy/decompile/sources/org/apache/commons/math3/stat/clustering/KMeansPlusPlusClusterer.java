package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {
    private final EmptyClusterStrategy emptyStrategy;
    private final Random random;

    public enum EmptyClusterStrategy {
        LARGEST_VARIANCE,
        LARGEST_POINTS_NUMBER,
        FARTHEST_POINT,
        ERROR
    }

    public KMeansPlusPlusClusterer(Random random2) {
        this(random2, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    public KMeansPlusPlusClusterer(Random random2, EmptyClusterStrategy emptyClusterStrategy) {
        this.random = random2;
        this.emptyStrategy = emptyClusterStrategy;
    }

    public List<Cluster<T>> cluster(Collection<T> collection, int i, int i2, int i3) throws MathIllegalArgumentException, ConvergenceException {
        int i4 = 0;
        double d = Double.POSITIVE_INFINITY;
        List<Cluster<T>> list = null;
        int i5 = i2;
        while (i4 < i5) {
            List<Cluster<T>> cluster = cluster(collection, i, i3);
            double d2 = 0.0d;
            for (Cluster cluster2 : cluster) {
                if (!cluster2.getPoints().isEmpty()) {
                    Clusterable center = cluster2.getCenter();
                    Variance variance = new Variance();
                    for (Clusterable distanceFrom : cluster2.getPoints()) {
                        variance.increment(distanceFrom.distanceFrom(center));
                        int i6 = i2;
                        Collection<T> collection2 = collection;
                        int i7 = i;
                    }
                    d2 += variance.getResult();
                }
                int i8 = i2;
                Collection<T> collection3 = collection;
                int i9 = i;
            }
            if (d2 <= d) {
                list = cluster;
                d = d2;
            }
            i4++;
            i5 = i2;
        }
        return list;
    }

    public List<Cluster<T>> cluster(Collection<T> collection, int i, int i2) throws MathIllegalArgumentException, ConvergenceException {
        boolean z;
        Clusterable clusterable;
        MathUtils.checkNotNull(collection);
        if (collection.size() < i) {
            throw new NumberIsTooSmallException(Integer.valueOf(collection.size()), Integer.valueOf(i), false);
        }
        List chooseInitialCenters = chooseInitialCenters(collection, i, this.random);
        int[] iArr = new int[collection.size()];
        assignPointsToClusters(chooseInitialCenters, collection, iArr);
        if (i2 < 0) {
            i2 = Integer.MAX_VALUE;
        }
        List<Cluster> list = chooseInitialCenters;
        int i3 = 0;
        while (i3 < i2) {
            ArrayList arrayList = new ArrayList();
            boolean z2 = false;
            for (Cluster cluster : list) {
                if (cluster.getPoints().isEmpty()) {
                    switch (this.emptyStrategy) {
                        case LARGEST_VARIANCE:
                            clusterable = getPointFromLargestVarianceCluster(list);
                            break;
                        case LARGEST_POINTS_NUMBER:
                            clusterable = getPointFromLargestNumberCluster(list);
                            break;
                        case FARTHEST_POINT:
                            clusterable = getFarthestPoint(list);
                            break;
                        default:
                            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                    }
                    z = true;
                } else {
                    Clusterable clusterable2 = (Clusterable) cluster.getCenter().centroidOf(cluster.getPoints());
                    z = z2;
                    clusterable = clusterable2;
                }
                arrayList.add(new Cluster(clusterable));
                z2 = z;
            }
            if (assignPointsToClusters(arrayList, collection, iArr) == 0 && !z2) {
                return arrayList;
            }
            i3++;
            list = arrayList;
        }
        return list;
    }

    private static <T extends Clusterable<T>> int assignPointsToClusters(List<Cluster<T>> list, Collection<T> collection, int[] iArr) {
        int i = 0;
        int i2 = 0;
        for (T t : collection) {
            int nearestCluster = getNearestCluster(list, t);
            if (nearestCluster != iArr[i]) {
                i2++;
            }
            ((Cluster) list.get(nearestCluster)).addPoint(t);
            int i3 = i + 1;
            iArr[i] = nearestCluster;
            i = i3;
        }
        return i2;
    }

    private static <T extends Clusterable<T>> List<Cluster<T>> chooseInitialCenters(Collection<T> collection, int i, Random random2) {
        int i2 = i;
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(collection));
        int size = unmodifiableList.size();
        boolean[] zArr = new boolean[size];
        ArrayList arrayList = new ArrayList();
        int nextInt = random2.nextInt(size);
        Clusterable clusterable = (Clusterable) unmodifiableList.get(nextInt);
        arrayList.add(new Cluster(clusterable));
        zArr[nextInt] = true;
        double[] dArr = new double[size];
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 != nextInt) {
                double distanceFrom = clusterable.distanceFrom(unmodifiableList.get(i3));
                dArr[i3] = distanceFrom * distanceFrom;
            }
        }
        while (arrayList.size() < i2) {
            double d = 0.0d;
            for (int i4 = 0; i4 < size; i4++) {
                if (!zArr[i4]) {
                    d += dArr[i4];
                }
            }
            double nextDouble = random2.nextDouble() * d;
            double d2 = 0.0d;
            int i5 = 0;
            while (true) {
                if (i5 >= size) {
                    i5 = -1;
                    break;
                }
                if (!zArr[i5]) {
                    d2 += dArr[i5];
                    if (d2 >= nextDouble) {
                        break;
                    }
                }
                i5++;
            }
            if (i5 == -1) {
                int i6 = size - 1;
                while (true) {
                    if (i6 < 0) {
                        break;
                    } else if (!zArr[i6]) {
                        i5 = i6;
                        break;
                    } else {
                        i6--;
                    }
                }
            }
            if (i5 < 0) {
                break;
            }
            Clusterable clusterable2 = (Clusterable) unmodifiableList.get(i5);
            arrayList.add(new Cluster(clusterable2));
            zArr[i5] = true;
            if (arrayList.size() < i2) {
                for (int i7 = 0; i7 < size; i7++) {
                    if (!zArr[i7]) {
                        double distanceFrom2 = clusterable2.distanceFrom(unmodifiableList.get(i7));
                        double d3 = distanceFrom2 * distanceFrom2;
                        if (d3 < dArr[i7]) {
                            dArr[i7] = d3;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private T getPointFromLargestVarianceCluster(Collection<Cluster<T>> collection) throws ConvergenceException {
        double d = Double.NEGATIVE_INFINITY;
        Cluster cluster = null;
        for (Cluster cluster2 : collection) {
            if (!cluster2.getPoints().isEmpty()) {
                Clusterable center = cluster2.getCenter();
                Variance variance = new Variance();
                for (Clusterable distanceFrom : cluster2.getPoints()) {
                    variance.increment(distanceFrom.distanceFrom(center));
                }
                double result = variance.getResult();
                if (result > d) {
                    cluster = cluster2;
                    d = result;
                }
            }
        }
        if (cluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List points = cluster.getPoints();
        return (Clusterable) points.remove(this.random.nextInt(points.size()));
    }

    private T getPointFromLargestNumberCluster(Collection<Cluster<T>> collection) throws ConvergenceException {
        Cluster cluster = null;
        int i = 0;
        for (Cluster cluster2 : collection) {
            int size = cluster2.getPoints().size();
            if (size > i) {
                cluster = cluster2;
                i = size;
            }
        }
        if (cluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List points = cluster.getPoints();
        return (Clusterable) points.remove(this.random.nextInt(points.size()));
    }

    private T getFarthestPoint(Collection<Cluster<T>> collection) throws ConvergenceException {
        Iterator it = collection.iterator();
        double d = Double.NEGATIVE_INFINITY;
        Cluster cluster = null;
        int i = -1;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Cluster cluster2 = (Cluster) it.next();
            Clusterable center = cluster2.getCenter();
            List points = cluster2.getPoints();
            for (int i2 = 0; i2 < points.size(); i2++) {
                double distanceFrom = ((Clusterable) points.get(i2)).distanceFrom(center);
                if (distanceFrom > d) {
                    cluster = cluster2;
                    i = i2;
                    d = distanceFrom;
                }
            }
        }
        if (cluster != null) {
            return (Clusterable) cluster.getPoints().remove(i);
        }
        throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
    }

    private static <T extends Clusterable<T>> int getNearestCluster(Collection<Cluster<T>> collection, T t) {
        int i = 0;
        double d = Double.MAX_VALUE;
        int i2 = 0;
        for (Cluster center : collection) {
            double distanceFrom = t.distanceFrom(center.getCenter());
            if (distanceFrom < d) {
                i = i2;
                d = distanceFrom;
            }
            i2++;
        }
        return i;
    }
}
