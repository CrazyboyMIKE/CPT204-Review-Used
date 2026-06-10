import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图算法复习。
 *
 * 覆盖 Week 10：
 * 1. DFS 深度优先搜索
 * 2. BFS 广度优先搜索
 * 3. Prim 最小生成树
 * 4. Dijkstra 单源最短路径
 */
public class GraphAlgorithms {

    public static void main(String[] args) {
        UnweightedGraph graph = new UnweightedGraph(6);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(4, 5);

        SearchTree dfsTree = graph.dfs(0);
        SearchTree bfsTree = graph.bfs(0);
        System.out.println("DFS search order: " + dfsTree.searchOrder);
        System.out.println("BFS search order: " + bfsTree.searchOrder);
        System.out.println("BFS path from 0 to 5: " + bfsTree.pathTo(5));

        WeightedGraph weightedGraph = new WeightedGraph(5);
        weightedGraph.addUndirectedEdge(0, 1, 2);
        weightedGraph.addUndirectedEdge(0, 3, 6);
        weightedGraph.addUndirectedEdge(1, 2, 3);
        weightedGraph.addUndirectedEdge(1, 3, 8);
        weightedGraph.addUndirectedEdge(1, 4, 5);
        weightedGraph.addUndirectedEdge(2, 4, 7);
        weightedGraph.addUndirectedEdge(3, 4, 9);

        PrimResult mst = weightedGraph.prim(0);
        System.out.println("Prim MST parent: " + Arrays.toString(mst.parent));
        System.out.println("Prim MST total weight: " + mst.totalWeight);

        DijkstraResult shortestPaths = weightedGraph.dijkstra(0);
        System.out.println("Dijkstra cost: " + Arrays.toString(shortestPaths.cost));
        System.out.println("Dijkstra parent: " + Arrays.toString(shortestPaths.parent));
    }

    /** 无权图：只关心顶点之间是否有边。 */
    public static class UnweightedGraph {
        private final List<List<Integer>> neighbors;

        public UnweightedGraph(int numberOfVertices) {
            neighbors = new ArrayList<>();
            for (int i = 0; i < numberOfVertices; i++) {
                neighbors.add(new ArrayList<>());
            }
        }

        public void addUndirectedEdge(int u, int v) {
            neighbors.get(u).add(v);
            neighbors.get(v).add(u);
        }

        /**
         * DFS 深度优先搜索。
         *
         * 思想：从一个点开始，沿着一条路尽可能走深；
         * 走不动时递归返回，继续尝试其他邻居。
         */
        public SearchTree dfs(int start) {
            boolean[] visited = new boolean[neighbors.size()];
            int[] parent = new int[neighbors.size()];
            Arrays.fill(parent, -1);

            List<Integer> searchOrder = new ArrayList<>();
            dfs(start, visited, parent, searchOrder);

            return new SearchTree(start, parent, searchOrder);
        }

        private void dfs(int vertex, boolean[] visited, int[] parent, List<Integer> searchOrder) {
            visited[vertex] = true;
            searchOrder.add(vertex);

            for (int neighbor : neighbors.get(vertex)) {
                if (!visited[neighbor]) {
                    parent[neighbor] = vertex;
                    dfs(neighbor, visited, parent, searchOrder);
                }
            }
        }

        /**
         * BFS 广度优先搜索。
         *
         * 思想：使用 Queue。
         * 先访问起点，再访问距离起点 1 条边的点，再访问距离 2 条边的点。
         */
        public SearchTree bfs(int start) {
            boolean[] visited = new boolean[neighbors.size()];
            int[] parent = new int[neighbors.size()];
            Arrays.fill(parent, -1);

            List<Integer> searchOrder = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();

            visited[start] = true;
            queue.offer(start);

            while (!queue.isEmpty()) {
                int current = queue.poll();
                searchOrder.add(current);

                for (int neighbor : neighbors.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        parent[neighbor] = current;
                        queue.offer(neighbor);
                    }
                }
            }

            return new SearchTree(start, parent, searchOrder);
        }
    }

    /** DFS/BFS 返回的搜索树。 */
    public static class SearchTree {
        final int root;
        final int[] parent;
        final List<Integer> searchOrder;

        SearchTree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        /** 从 root 到 vertex 的路径。 */
        public List<Integer> pathTo(int vertex) {
            List<Integer> path = new ArrayList<>();

            int current = vertex;
            while (current != -1) {
                path.add(current);
                current = parent[current];
            }

            Collections.reverse(path);
            return path;
        }
    }

    /** 带权边：在普通边 u->v 的基础上增加 weight。 */
    public static class WeightedEdge {
        final int u;
        final int v;
        final double weight;

        WeightedEdge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    /** 带权图：邻接表中存 WeightedEdge。 */
    public static class WeightedGraph {
        private final List<List<WeightedEdge>> neighbors;

        public WeightedGraph(int numberOfVertices) {
            neighbors = new ArrayList<>();
            for (int i = 0; i < numberOfVertices; i++) {
                neighbors.add(new ArrayList<>());
            }
        }

        public void addUndirectedEdge(int u, int v, double weight) {
            neighbors.get(u).add(new WeightedEdge(u, v, weight));
            neighbors.get(v).add(new WeightedEdge(v, u, weight));
        }

        /**
         * Prim 最小生成树算法。
         *
         * 目标：用最小总权重把所有顶点连起来。
         *
         * cost[v] 在 Prim 中的含义：
         * v 连接到当前树 T 的最便宜边权重。
         */
        public PrimResult prim(int start) {
            int n = neighbors.size();
            boolean[] inTree = new boolean[n];
            double[] cost = new double[n];
            int[] parent = new int[n];

            Arrays.fill(cost, Double.POSITIVE_INFINITY);
            Arrays.fill(parent, -1);
            cost[start] = 0;

            double totalWeight = 0;

            for (int count = 0; count < n; count++) {
                int u = findMinCostVertex(cost, inTree);
                if (u == -1) {
                    break;
                }

                inTree[u] = true;
                totalWeight += cost[u];

                // 用刚加入的 u 更新它的邻居：如果通过 u 连接更便宜，就更新。
                for (WeightedEdge edge : neighbors.get(u)) {
                    int v = edge.v;
                    if (!inTree[v] && edge.weight < cost[v]) {
                        cost[v] = edge.weight;
                        parent[v] = u;
                    }
                }
            }

            return new PrimResult(parent, totalWeight);
        }

        /**
         * Dijkstra 单源最短路径算法。
         *
         * 目标：从 source 到所有其他顶点的最短路径。
         * 前提：边权不能为负数。
         *
         * cost[v] 在 Dijkstra 中的含义：
         * 当前已知的 source 到 v 的最短距离。
         */
        public DijkstraResult dijkstra(int source) {
            int n = neighbors.size();
            boolean[] finalized = new boolean[n];
            double[] cost = new double[n];
            int[] parent = new int[n];

            Arrays.fill(cost, Double.POSITIVE_INFINITY);
            Arrays.fill(parent, -1);
            cost[source] = 0;

            for (int count = 0; count < n; count++) {
                int u = findMinCostVertex(cost, finalized);
                if (u == -1) {
                    break;
                }

                finalized[u] = true;

                // Relaxation 松弛操作：
                // 如果 source -> u -> v 比当前 source -> v 更短，就更新 v。
                for (WeightedEdge edge : neighbors.get(u)) {
                    int v = edge.v;
                    double newCost = cost[u] + edge.weight;

                    if (!finalized[v] && newCost < cost[v]) {
                        cost[v] = newCost;
                        parent[v] = u;
                    }
                }
            }

            return new DijkstraResult(parent, cost);
        }

        /**
         * 找还没有加入集合的、cost 最小的顶点。
         *
         * 这里用 boolean[] 做 membership test，所以是 O(1) 判断是否已加入。
         * 课件中如果用 ArrayList 的 contains，contains 本身是 O(n)，整体会更慢。
         */
        private int findMinCostVertex(double[] cost, boolean[] selected) {
            int best = -1;
            double bestCost = Double.POSITIVE_INFINITY;

            for (int i = 0; i < cost.length; i++) {
                if (!selected[i] && cost[i] < bestCost) {
                    best = i;
                    bestCost = cost[i];
                }
            }

            return best;
        }
    }

    public static class PrimResult {
        final int[] parent;
        final double totalWeight;

        PrimResult(int[] parent, double totalWeight) {
            this.parent = parent;
            this.totalWeight = totalWeight;
        }
    }

    public static class DijkstraResult {
        final int[] parent;
        final double[] cost;

        DijkstraResult(int[] parent, double[] cost) {
            this.parent = parent;
            this.cost = cost;
        }
    }
}
