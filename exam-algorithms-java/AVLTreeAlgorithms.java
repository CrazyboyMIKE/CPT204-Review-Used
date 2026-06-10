import java.util.ArrayList;
import java.util.List;

/**
 * AVL Tree 平衡二叉搜索树。
 *
 * 覆盖 Week 12：
 * 1. balance factor 平衡因子
 * 2. LL / RR / LR / RL 四种旋转
 * 3. insert 后 rebalance
 * 4. delete 后 rebalance
 */
public class AVLTreeAlgorithms {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] values = {25, 20, 5, 34, 50, 30, 10};

        for (int value : values) {
            tree.insert(value);
            System.out.println("Preorder after inserting " + value + ": " + tree.preorder());
        }

        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.println("Inorder after deleting 34, 30, 50: " + tree.inorder());
    }

    public static class AVLTree<E extends Comparable<E>> {
        private Node<E> root;
        private int size;

        private static class Node<E> {
            E element;
            Node<E> left;
            Node<E> right;
            int height;

            Node(E element) {
                this.element = element;
                this.height = 0;
            }
        }

        public boolean contains(E element) {
            Node<E> current = root;

            while (current != null) {
                int cmp = element.compareTo(current.element);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    return true;
                }
            }

            return false;
        }

        /**
         * 插入元素。
         *
         * 先按普通 BST 插入，再从递归返回路径上逐层 rebalance。
         */
        public boolean insert(E element) {
            if (contains(element)) {
                return false;
            }

            root = insert(root, element);
            size++;
            return true;
        }

        private Node<E> insert(Node<E> node, E element) {
            if (node == null) {
                return new Node<>(element);
            }

            int cmp = element.compareTo(node.element);
            if (cmp < 0) {
                node.left = insert(node.left, element);
            } else {
                node.right = insert(node.right, element);
            }

            return rebalance(node);
        }

        /**
         * 删除元素。
         *
         * 删除逻辑先按 BST 做：
         * - 没有左孩子/右孩子：直接用另一个孩子顶上
         * - 两个孩子都有：找左子树最大值 predecessor 替换当前节点
         *
         * 然后在递归返回路径上 rebalance。
         */
        public boolean delete(E element) {
            if (!contains(element)) {
                return false;
            }

            root = delete(root, element);
            size--;
            return true;
        }

        private Node<E> delete(Node<E> node, E element) {
            if (node == null) {
                return null;
            }

            int cmp = element.compareTo(node.element);
            if (cmp < 0) {
                node.left = delete(node.left, element);
            } else if (cmp > 0) {
                node.right = delete(node.right, element);
            } else {
                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node<E> predecessor = maxNode(node.left);
                node.element = predecessor.element;
                node.left = delete(node.left, predecessor.element);
            }

            return rebalance(node);
        }

        private Node<E> maxNode(Node<E> node) {
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        /**
         * 对 node 做必要的 AVL 平衡。
         *
         * 课程定义：
         * BF = height(right subtree) - height(left subtree)
         */
        private Node<E> rebalance(Node<E> node) {
            updateHeight(node);
            int bf = balanceFactor(node);

            if (bf < -1) {
                // 左边太高。
                if (balanceFactor(node.left) <= 0) {
                    // LL：左孩子也是左高或平衡，做一次右旋。
                    return rotateRight(node);
                } else {
                    // LR：先把左孩子左旋成 LL，再对 node 右旋。
                    node.left = rotateLeft(node.left);
                    return rotateRight(node);
                }
            }

            if (bf > 1) {
                // 右边太高。
                if (balanceFactor(node.right) >= 0) {
                    // RR：右孩子也是右高或平衡，做一次左旋。
                    return rotateLeft(node);
                } else {
                    // RL：先把右孩子右旋成 RR，再对 node 左旋。
                    node.right = rotateRight(node.right);
                    return rotateLeft(node);
                }
            }

            return node;
        }

        /**
         * LL 对应的右旋。
         *
         *      A             B
         *     /             / \
         *    B      ->     C   A
         *   / \               /
         *  C   E             E
         */
        private Node<E> rotateRight(Node<E> a) {
            Node<E> b = a.left;
            Node<E> e = b.right;

            b.right = a;
            a.left = e;

            updateHeight(a);
            updateHeight(b);
            return b;
        }

        /**
         * RR 对应的左旋。
         *
         *    A                 B
         *     \               / \
         *      B      ->     A   C
         *     /                 \
         *    E                   E
         */
        private Node<E> rotateLeft(Node<E> a) {
            Node<E> b = a.right;
            Node<E> e = b.left;

            b.left = a;
            a.right = e;

            updateHeight(a);
            updateHeight(b);
            return b;
        }

        private void updateHeight(Node<E> node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        /**
         * 空子树高度设为 -1，叶子高度就是 0。
         */
        private int height(Node<E> node) {
            return node == null ? -1 : node.height;
        }

        private int balanceFactor(Node<E> node) {
            if (node == null) {
                return 0;
            }
            return height(node.right) - height(node.left);
        }

        public List<E> inorder() {
            List<E> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(Node<E> node, List<E> result) {
            if (node == null) {
                return;
            }
            inorder(node.left, result);
            result.add(node.element);
            inorder(node.right, result);
        }

        public List<E> preorder() {
            List<E> result = new ArrayList<>();
            preorder(root, result);
            return result;
        }

        private void preorder(Node<E> node, List<E> result) {
            if (node == null) {
                return;
            }
            result.add(node.element);
            preorder(node.left, result);
            preorder(node.right, result);
        }

        public int size() {
            return size;
        }
    }
}
