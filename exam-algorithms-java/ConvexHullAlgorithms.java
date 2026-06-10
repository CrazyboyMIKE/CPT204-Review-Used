import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Convex Hull 凸包算法。
 *
 * 覆盖 Week 8：
 * 1. Gift-Wrapping / Jarvis March，O(hn)
 * 2. Graham Scan，O(n log n)
 */
public class ConvexHullAlgorithms {

    public static void main(String[] args) {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 0),
                new Point(2, 2),
                new Point(0, 2),
                new Point(1, 0.5),
                new Point(1.5, 1.5)
        };

        System.out.println("Gift Wrapping: " + giftWrapping(points));
        System.out.println("Graham Scan: " + grahamScan(points));
    }

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

    /**
     * Gift-Wrapping / Jarvis March。
     *
     * 从一个一定在凸包上的点开始，每次扫描所有点，找下一个最外侧点。
     * 如果凸包上有 h 个点，每次找一个点要扫 n 个点，所以 O(hn)。
     */
    public static List<Point> giftWrapping(Point[] points) {
        List<Point> hull = new ArrayList<>();

        if (points.length < 3) {
            hull.addAll(Arrays.asList(points));
            return hull;
        }

        int start = rightmostLowestPoint(points);
        int current = start;

        do {
            hull.add(points[current]);
            int next = (current + 1) % points.length;

            for (int i = 0; i < points.length; i++) {
                if (i == current) {
                    continue;
                }

                double turn = cross(points[current], points[next], points[i]);

                // turn < 0 表示 points[i] 更在外侧。
                if (turn < 0 || (turn == 0
                        && distanceSquared(points[current], points[i])
                        > distanceSquared(points[current], points[next]))) {
                    next = i;
                }
            }

            current = next;
        } while (current != start);

        return hull;
    }

    /**
     * Graham Scan。
     *
     * 1. 选 anchor。
     * 2. 其余点按极角排序。
     * 3. 用栈维护凸包边界：如果出现右转，就弹出栈顶。
     */
    public static List<Point> grahamScan(Point[] points) {
        if (points.length < 3) {
            return new ArrayList<>(Arrays.asList(points));
        }

        Point anchor = points[rightmostLowestPoint(points)];
        Point[] sorted = Arrays.copyOf(points, points.length);

        Arrays.sort(sorted, (a, b) -> {
            if (a == anchor) {
                return -1;
            }
            if (b == anchor) {
                return 1;
            }

            double turn = cross(anchor, a, b);
            if (turn == 0) {
                return Double.compare(distanceSquared(anchor, a), distanceSquared(anchor, b));
            }

            // 让点按围绕 anchor 的逆时针顺序排列。
            return turn > 0 ? -1 : 1;
        });

        Stack<Point> stack = new Stack<>();
        stack.push(sorted[0]);
        stack.push(sorted[1]);

        for (int i = 2; i < sorted.length; i++) {
            Point top = stack.pop();

            // 如果 top 让边界形成右转或共线内点，就继续弹出。
            while (!stack.isEmpty() && cross(stack.peek(), top, sorted[i]) <= 0) {
                top = stack.pop();
            }

            stack.push(top);
            stack.push(sorted[i]);
        }

        return new ArrayList<>(stack);
    }

    /**
     * 找课件提到的 rightmost lowest point：
     * y 最小；如果 y 相同，x 最大。
     */
    private static int rightmostLowestPoint(Point[] points) {
        int best = 0;

        for (int i = 1; i < points.length; i++) {
            if (points[i].y < points[best].y
                    || (points[i].y == points[best].y && points[i].x > points[best].x)) {
                best = i;
            }
        }

        return best;
    }

    /**
     * 叉积 cross(a,b,c)。
     *
     * > 0: a->b 到 a->c 是左转
     * < 0: 右转
     * = 0: 三点共线
     */
    private static double cross(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    private static double distanceSquared(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return dx * dx + dy * dy;
    }
}
