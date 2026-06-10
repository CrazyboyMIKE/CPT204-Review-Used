import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Week10Answers {
    public static void main(String[] args) {
        System.out.println("Week 10 Answers");
        printTrueFalseAnswers();
        printAdjacencyMatrix();
        explainPrimAndDijkstra();
        demoGetPath();
    }

    private static void printTrueFalseAnswers() {
        System.out.println("1. True");
        System.out.println("2. False");
        System.out.println("3. True");
        System.out.println("4. False");
    }

    private static void printAdjacencyMatrix() {
        int vertexCount = 4;
        WeightedEdge[] edges = {
                new WeightedEdge(0, 1, 4),
                new WeightedEdge(0, 2, 6),
                new WeightedEdge(1, 2, 2),
                new WeightedEdge(2, 3, 5)
        };

        Integer[][] matrix = buildUndirectedAdjacencyMatrix(vertexCount, edges);
        System.out.println("Weighted adjacency matrix:");
        for (Integer[] row : matrix) {
            for (Integer value : row) {
                System.out.print((value == null ? "-" : value) + " ");
            }
            System.out.println();
        }
    }

    static class WeightedEdge {
        private final int from;
        private final int to;
        private final int weight;

        public WeightedEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // 无向图的边是双向的。
    // 因此 edge(from, to, weight) 要同时写入 matrix[from][to] 和 matrix[to][from]。
    // null 表示两个顶点之间没有直接边。
    public static Integer[][] buildUndirectedAdjacencyMatrix(int vertexCount, WeightedEdge[] edges) {
        Integer[][] matrix = new Integer[vertexCount][vertexCount];
        for (WeightedEdge edge : edges) {
            matrix[edge.from][edge.to] = edge.weight;
            matrix[edge.to][edge.from] = edge.weight;
        }
        return matrix;
    }

    private static void explainPrimAndDijkstra() {
        System.out.println("Prim cost[v]: cheapest edge connecting v to the current MST set.");
        System.out.println("Dijkstra cost[v]: shortest known total distance from the source to v.");
    }

    private static void demoGetPath() {
        int[] parent = {-1, 0, 1, 2, 2};
        System.out.println("Path to 4: " + getPath(4, parent));
    }

    // parent[target] 保存 target 在搜索树或最短路径树中的父节点。
    // 从 target 不断往 parent 追溯会得到反向路径，所以最后需要 reverse。
    // 当 parent[current] == -1 时，说明 current 是根节点或源点。
    public static List<Integer> getPath(int target, int[] parent) {
        ArrayList<Integer> path = new ArrayList<>();
        int current = target;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);
        return path;
    }
}
