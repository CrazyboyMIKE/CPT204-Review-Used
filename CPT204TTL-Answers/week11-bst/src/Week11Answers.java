import java.util.ArrayList;
import java.util.List;

public class Week11Answers {
    public static void main(String[] args) {
        System.out.println("Week 11 Answers");

        TreeNode<Integer> invalidTree = buildQuestionOneTree();
        System.out.println("Question 1 tree is valid: " + isValidBst(invalidTree));

        System.out.println("Max comparisons for order A: 6");
        System.out.println("Max comparisons for order B: 3");

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] values = {50, 30, 80, 20, 40, 45, 70, 90};
        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("Search trace for 42: " + tree.searchTrace(42));
        System.out.println("Inorder traversal: " + tree.inorder());
        System.out.println("Preorder traversal: " + tree.preorder());
        System.out.println("Count greater than 40: " + tree.countGreaterThan(40));

        tree.delete(50);
        System.out.println("Inorder after deleting 50: " + tree.inorder());
    }

    private static TreeNode<Integer> buildQuestionOneTree() {
        TreeNode<Integer> root = new TreeNode<>(40);
        root.left = new TreeNode<>(20);
        root.right = new TreeNode<>(70);
        root.left.left = new TreeNode<>(10);
        root.left.right = new TreeNode<>(35);
        root.left.right.left = new TreeNode<>(25);
        root.right.left = new TreeNode<>(50);
        root.right.right = new TreeNode<>(90);
        root.right.right.right = new TreeNode<>(75);
        return root;
    }

    // BST 的合法性不能只检查“当前节点大于左孩子、小于右孩子”。
    // 必须带着上下界递归检查：左子树所有值都要小于祖先上界，右子树所有值都要大于祖先下界。
    public static boolean isValidBst(TreeNode<Integer> root) {
        return isValidBst(root, null, null);
    }

    private static boolean isValidBst(TreeNode<Integer> node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.element <= min) {
            return false;
        }
        if (max != null && node.element >= max) {
            return false;
        }
        return isValidBst(node.left, min, node.element)
                && isValidBst(node.right, node.element, max);
    }

    static class TreeNode<E> {
        private E element;
        private TreeNode<E> left;
        private TreeNode<E> right;

        public TreeNode(E element) {
            this.element = element;
        }
    }

    static class BinarySearchTree<E extends Comparable<E>> {
        private TreeNode<E> root;

        public boolean insert(E element) {
            if (root == null) {
                root = new TreeNode<>(element);
                return true;
            }

            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                parent = current;
                int comparison = element.compareTo(current.element);
                if (comparison < 0) {
                    current = current.left;
                } else if (comparison > 0) {
                    current = current.right;
                } else {
                    return false;
                }
            }

            if (element.compareTo(parent.element) < 0) {
                parent.left = new TreeNode<>(element);
            } else {
                parent.right = new TreeNode<>(element);
            }
            return true;
        }

        // 搜索时每一步都用 BST 性质排除一半方向。
        // 找 42 时，访问 50 后去左边；访问 30 后去右边；访问 40 后去右边；访问 45 后去左边。
        // 如果左边为空，就能确定 42 不存在，不需要搜索其他子树。
        public List<E> searchTrace(E target) {
            ArrayList<E> trace = new ArrayList<>();
            TreeNode<E> current = root;

            while (current != null) {
                trace.add(current.element);
                int comparison = target.compareTo(current.element);
                if (comparison == 0) {
                    return trace;
                } else if (comparison < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return trace;
        }

        public List<E> inorder() {
            ArrayList<E> result = new ArrayList<>();
            inorder(root, result);
            return result;
        }

        // BST 的 inorder traversal 会得到从小到大的有序序列。
        private void inorder(TreeNode<E> node, List<E> result) {
            if (node == null) {
                return;
            }
            inorder(node.left, result);
            result.add(node.element);
            inorder(node.right, result);
        }

        public List<E> preorder() {
            ArrayList<E> result = new ArrayList<>();
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

        public int countGreaterThan(E target) {
            return countGreaterThan(root, target);
        }

        // 如果 node.element <= target，则左子树更小，不可能有大于 target 的值，只需要看右子树。
        // 如果 node.element > target，则当前节点要计数，左右子树都可能还有更大的值。
        private int countGreaterThan(TreeNode<E> node, E target) {
            if (node == null) {
                return 0;
            }

            if (node.element.compareTo(target) <= 0) {
                return countGreaterThan(node.right, target);
            }

            return 1 + countGreaterThan(node.left, target) + countGreaterThan(node.right, target);
        }

        public boolean delete(E element) {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                int comparison = element.compareTo(current.element);
                if (comparison < 0) {
                    parent = current;
                    current = current.left;
                } else if (comparison > 0) {
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
                replaceChild(parent, current, current.right);
            } else {
                deleteNodeWithLeftSubtree(current);
            }
            return true;
        }

        // 删除有左子树的节点时，可以用左子树中最大的节点替换当前节点。
        // 左子树最大节点一定是 current.left 一路向右走到尽头。
        // 替换时要复制 element，而不是让 current 局部变量指向 rightMost。
        private void deleteNodeWithLeftSubtree(TreeNode<E> current) {
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

        private void replaceChild(TreeNode<E> parent, TreeNode<E> current, TreeNode<E> replacement) {
            if (parent == null) {
                root = replacement;
            } else if (parent.left == current) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        }
    }
}
