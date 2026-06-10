import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 最近点对 Closest Pair of Points。
 *
 * 覆盖 Week 8 的 Divide-and-Conquer 思想：
 * 1. 暴力法检查所有点对，O(n^2)
 * 2. 分治法按 x 坐标切成左右两半，递归求解，再检查中线附近 strip
 */
public class ClosestPairDivideConquer {

    public static void main(String[] args) {
        Point[] points = {
                new Point(-1, 3),
                new Point(2, 1),
                new Point(4, 4),
                new Point(5, 2),
                new Point(7, 3),
                new Point(8, 8)
        };

        System.out.println("Brute force: " + bruteForceClosestPair(points));
        System.out.println("Divide and conquer: " + divideAndConquerClosestPair(points));
    }

    /** 二维点。 */
    public static class Point {
        final double x;
        final double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    /** 保存一对点以及它们的距离。 */
    public static class Pair {
        final Point p1;
        final Point p2;
        final double distance;

        Pair(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance(p1, p2);
        }

        Pair(Point p1, Point p2, double distance) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return p1 + " and " + p2 + ", distance = " + distance;
        }
    }

    /**
     * 暴力法。
     *
     * 双重循环检查每一对点。
     * 点对数量约为 n(n-1)/2，所以时间复杂度 O(n^2)。
     */
    public static Pair bruteForceClosestPair(Point[] points) {
        if (points.length < 2) {
            return null;
        }

        Pair best = new Pair(points[0], points[1]);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = distance(points[i], points[j]);
                if (d < best.distance) {
                    best = new Pair(points[i], points[j], d);
                }
            }
        }

        return best;
    }

    /**
     * 分治法入口。
     *
     * 为了避免每层重复整体排序，先准备两个数组：
     * pointsByX 按 x 排序；
     * pointsByY 按 y 排序。
     */
    public static Pair divideAndConquerClosestPair(Point[] points) {
        if (points.length < 2) {
            return null;
        }

        Point[] pointsByX = Arrays.copyOf(points, points.length);
        Point[] pointsByY = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsByX, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));

        return closest(pointsByX, pointsByY);
    }

    /**
     * 分治核心。
     *
     * 1. 按 x 中位线分左右两半。
     * 2. 递归求左半最近距离 dl 和右半最近距离 dr。
     * 3. d = min(dl, dr)。
     * 4. 只检查离中线距离小于 d 的 strip，因为更远的点不可能产生更短跨区距离。
     */
    private static Pair closest(Point[] pointsByX, Point[] pointsByY) {
        int n = pointsByX.length;

        if (n <= 3) {
            return bruteForceClosestPair(pointsByX);
        }

        int mid = n / 2;
        Point midPoint = pointsByX[mid];

        Point[] leftX = Arrays.copyOfRange(pointsByX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(pointsByX, mid, n);

        // 用同一个点对象引用判断它属于左半还是右半。
        List<Point> leftXList = Arrays.asList(leftX);
        List<Point> leftYList = new ArrayList<>();
        List<Point> rightYList = new ArrayList<>();

        for (Point p : pointsByY) {
            if (leftXList.contains(p)) {
                leftYList.add(p);
            } else {
                rightYList.add(p);
            }
        }

        Pair leftBest = closest(leftX, leftYList.toArray(new Point[0]));
        Pair rightBest = closest(rightX, rightYList.toArray(new Point[0]));

        Pair best = leftBest.distance <= rightBest.distance ? leftBest : rightBest;
        double d = best.distance;

        // pointsByY 已经按 y 排序，所以 strip 也保持 y 顺序。
        List<Point> strip = new ArrayList<>();
        for (Point p : pointsByY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        // 在 strip 中，每个点只需要检查 y 距离小于 d 的后续点。
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size()
                    && strip.get(j).y - strip.get(i).y < d; j++) {
                double candidateDistance = distance(strip.get(i), strip.get(j));
                if (candidateDistance < best.distance) {
                    best = new Pair(strip.get(i), strip.get(j), candidateDistance);
                    d = candidateDistance;
                }
            }
        }

        return best;
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
