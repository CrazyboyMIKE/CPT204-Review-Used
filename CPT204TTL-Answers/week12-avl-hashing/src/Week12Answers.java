import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week12Answers {
    public static void main(String[] args) {
        System.out.println("Week 12 Answers");

        AvlTree firstTree = new AvlTree();
        for (int value : new int[]{60, 25, 80, 10, 40, 35}) {
            firstTree.insert(value);
        }
        System.out.println("AVL after inserting 60,25,80,10,40,35: " + firstTree.preorder());
        System.out.println("AVL inorder: " + firstTree.inorder());

        Integer[] linear = insertLinearProbing(13, new int[]{26, 39, 52, 14, 27});
        System.out.println("Linear probing table: " + Arrays.toString(linear));

        Integer[] quadratic = insertQuadraticProbing(13, new int[]{26, 39, 52, 65});
        System.out.println("Quadratic probing table: " + Arrays.toString(quadratic));

        Integer[] doubleHash = insertDoubleHashing(13, new int[]{26, 39, 52, 65});
        System.out.println("Double hashing table: " + Arrays.toString(doubleHash));

        AvlTree secondTree = new AvlTree();
        for (int value : new int[]{70, 30, 90, 20, 50, 45}) {
            secondTree.insert(value);
        }
        System.out.println("AVL after balancePath example: " + secondTree.preorder());
        System.out.println("Balance path for inserted 45: " + Arrays.asList(70, 30, 50, 45));
    }

    static class AvlNode {
        private int element;
        private int height;
        private AvlNode left;
        private AvlNode right;

        public AvlNode(int element) {
            this.element = element;
        }
    }

    static class AvlTree {
        private AvlNode root;

        public void insert(int element) {
            root = insert(root, element);
        }

        private AvlNode insert(AvlNode node, int element) {
            if (node == null) {
                return new AvlNode(element);
            }

            if (element < node.element) {
                node.left = insert(node.left, element);
            } else if (element > node.element) {
                node.right = insert(node.right, element);
            } else {
                return node;
            }

            updateHeight(node);
            return rebalance(node);
        }

        // 本代码使用 balance factor = height(right) - height(left)。
        // BF = -2 表示左边太高；BF = +2 表示右边太高。
        private AvlNode rebalance(AvlNode node) {
            int balance = balanceFactor(node);

            if (balance == -2) {
                if (balanceFactor(node.left) <= 0) {
                    return rotateRight(node);
                }
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            if (balance == 2) {
                if (balanceFactor(node.right) >= 0) {
                    return rotateLeft(node);
                }
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        // LL 旋转也叫 right rotation。
        // A 左重，B 成为新的子树根；B.right 会移动成 A.left。
        private AvlNode rotateRight(AvlNode a) {
            AvlNode b = a.left;
            AvlNode movedSubtree = b.right;

            b.right = a;
            a.left = movedSubtree;

            updateHeight(a);
            updateHeight(b);
            return b;
        }

        // RR 旋转也叫 left rotation。
        // A 右重，B 成为新的子树根；B.left 会移动成 A.right。
        private AvlNode rotateLeft(AvlNode a) {
            AvlNode b = a.right;
            AvlNode movedSubtree = b.left;

            b.left = a;
            a.right = movedSubtree;

            updateHeight(a);
            updateHeight(b);
            return b;
        }

        private int balanceFactor(AvlNode node) {
            return height(node.right) - height(node.left);
        }

        private int height(AvlNode node) {
            return node == null ? -1 : node.height;
        }

        private void updateHeight(AvlNode node) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }

        public List<Integer> inorder() {
            ArrayList<Integer> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(AvlNode node, List<Integer> result) {
            if (node == null) {
                return;
            }
            inorder(node.left, result);
            result.add(node.element);
            inorder(node.right, result);
        }

        public List<Integer> preorder() {
            ArrayList<Integer> result = new ArrayList<>();
            preorder(root, result);
            return result;
        }

        private void preorder(AvlNode node, List<Integer> result) {
            if (node == null) {
                return;
            }
            result.add(node.element);
            preorder(node.left, result);
            preorder(node.right, result);
        }
    }

    // 线性探测每次冲突后向后移动 1 格。
    // 这些 key 对 13 取模后大量落在 0 或 1 附近，因此会形成 primary clustering。
    public static Integer[] insertLinearProbing(int size, int[] values) {
        Integer[] table = new Integer[size];
        for (int value : values) {
            int index = value % size;
            while (table[index] != null) {
                index = (index + 1) % size;
            }
            table[index] = value;
        }
        return table;
    }

    // 二次探测使用 start + j^2。
    // 它能缓解 primary clustering，但如果多个 key 的初始位置相同，探测序列仍然相同。
    // 这种现象叫 secondary clustering。
    public static Integer[] insertQuadraticProbing(int size, int[] values) {
        Integer[] table = new Integer[size];
        for (int value : values) {
            int start = value % size;
            int step = 0;
            int index = start;
            while (table[index] != null) {
                step++;
                index = (start + step * step) % size;
            }
            table[index] = value;
        }
        return table;
    }

    // 双重哈希使用第二个哈希函数决定步长。
    // 这里 h2(key) = 5 - key % 5，所以同样初始位置的 key 也可能拥有不同探测步长。
    public static Integer[] insertDoubleHashing(int size, int[] values) {
        Integer[] table = new Integer[size];
        for (int value : values) {
            int start = value % size;
            int stepSize = 5 - value % 5;
            int step = 0;
            int index = start;
            while (table[index] != null) {
                step++;
                index = (start + step * stepSize) % size;
            }
            table[index] = value;
        }
        return table;
    }
}
