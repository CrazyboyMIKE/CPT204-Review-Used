import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Binary Search Tree 二叉搜索树。
 *
 * 覆盖 Lecture 11：
 * 1. BST search
 * 2. BST insert
 * 3. preorder / inorder / postorder / breadth-first traversal
 * 4. iterator
 * 5. delete 两种情况
 */
public class BinarySearchTreeAlgorithms {

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        int[] values = {60, 55, 100, 45, 57, 67, 107, 101};

        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("search(101): " + tree.search(101));
        System.out.println("preorder: " + tree.preorder());
        System.out.println("Inorder sorted result: " + tree.inorder());
        System.out.println("postorder: " + tree.postorder());
        System.out.println("breadth-first: " + tree.breadthFirst());

        tree.delete(100);
        System.out.println("Inorder after deleting 100: " + tree.inorder());

        System.out.print("Iterator traversal: ");
        for (int value : tree) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static class BST<E extends Comparable<E>> implements Iterable<E> {
        protected TreeNode<E> root;
        protected int size;

        /** 树节点：一个 element，加左右孩子引用。 */
        protected static class TreeNode<E> {
            E element;
            TreeNode<E> left;
            TreeNode<E> right;

            TreeNode(E element) {
                this.element = element;
            }
        }

        /**
         * 查找。
         *
         * BST 性质：
         * 小于当前节点，去左子树；
         * 大于当前节点，去右子树。
         */
        public boolean search(E element) {
            TreeNode<E> current = root;

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
         * 插入。
         *
         * current 用来向下找位置；
         * parent 保存 current 的父节点，最后要把新节点接到 parent 上。
         */
        public boolean insert(E element) {
            if (root == null) {
                root = new TreeNode<>(element);
                size++;
                return true;
            }

            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                int cmp = element.compareTo(current.element);
                parent = current;

                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    // 默认不允许重复元素。
                    return false;
                }
            }

            if (element.compareTo(parent.element) < 0) {
                parent.left = new TreeNode<>(element);
            } else {
                parent.right = new TreeNode<>(element);
            }

            size++;
            return true;
        }

        /** Preorder: Node -> Left -> Right。 */
        public List<E> preorder() {
            List<E> result = new ArrayList<>();
            preorder(root, result);
            return result;
        }

        private void preorder(TreeNode<E> node, List<E> result) {
            if (node == null) {
                return;
            }

            result.add(node.element);
            preorder(node.left, result);
            preorder(node.right, result);
        }

        /** Inorder: Left -> Node -> Right。对 BST 来说，结果是升序。 */
        public List<E> inorder() {
            List<E> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        private void inorder(TreeNode<E> node, List<E> result) {
            if (node == null) {
                return;
            }

            inorder(node.left, result);
            result.add(node.element);
            inorder(node.right, result);
        }

        /** Postorder: Left -> Right -> Node。 */
        public List<E> postorder() {
            List<E> result = new ArrayList<>();
            postorder(root, result);
            return result;
        }

        private void postorder(TreeNode<E> node, List<E> result) {
            if (node == null) {
                return;
            }

            postorder(node.left, result);
            postorder(node.right, result);
            result.add(node.element);
        }

        /** Breadth-first traversal: 按层访问，使用队列。 */
        public List<E> breadthFirst() {
            List<E> result = new ArrayList<>();
            Queue<TreeNode<E>> queue = new LinkedList<>();

            if (root != null) {
                queue.offer(root);
            }

            while (!queue.isEmpty()) {
                TreeNode<E> current = queue.poll();
                result.add(current.element);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            return result;
        }

        /**
         * 删除。
         *
         * Case 1: current 没有左孩子，直接让 parent 连接 current.right。
         * Case 2: current 有左孩子，用左子树中最大的节点 rightMost 替换 current。
         */
        public boolean delete(E element) {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                int cmp = element.compareTo(current.element);

                if (cmp < 0) {
                    parent = current;
                    current = current.left;
                } else if (cmp > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    break;
                }
            }

            if (current == null) {
                return false;
            }

            if (current.left == null) {
                // Case 1: current 没有左孩子。
                if (parent == null) {
                    root = current.right;
                } else if (element.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            } else {
                // Case 2: current 有左孩子，找左子树最右节点。
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right;
                }

                current.element = rightMost.element;

                if (parentOfRightMost.right == rightMost) {
                    parentOfRightMost.right = rightMost.left;
                } else {
                    parentOfRightMost.left = rightMost.left;
                }
            }

            size--;
            return true;
        }

        public int size() {
            return size;
        }

        /**
         * 让 BST 支持 enhanced for loop。
         *
         * 这里返回 inorder iterator，所以遍历结果是升序。
         */
        @Override
        public Iterator<E> iterator() {
            return inorder().iterator();
        }
    }
}
