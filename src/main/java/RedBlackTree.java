/**
 * @author Javen
 * @create 2021-08-15
 * @Description
 */
public class RedBlackTree {

    public static class Tree {
        private int val;
        private Tree left;
        private Tree right;
        private int color; //0表示红色，1表示黑色

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Tree getLeft() {
            return left;
        }

        public void setLeft(Tree left) {
            this.left = left;
        }

        public Tree getRight() {
            return right;
        }

        public void setRight(Tree right) {
            this.right = right;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public Tree(int val, Tree left, Tree right, int color) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public Tree(int val, int color) {
            this.val = val;
            this.color = color;
        }

        public Tree(int val) {
            this.val = val;
        }

        public Tree() {
        }
    }

    public Tree getNewNode(int key) {
        return new Tree(key, 0);
    }

    public Tree insert(Tree root, int key) {
        if (root == null) return getNewNode(key);
        if (root.val == key) return root;
        if (root.val < key) root.right = insert(root.right, key);
        else root.left = insert(root.left, key);
        return insertMain(root);
    }

    public Tree insertMain(Tree root) {
        if (!hasRedChild(root)) return root;
        // 情况一，叔父结点都是红色
        if (root.left.color == 0 && root.right.color==0){
            root.color = 0;
            root.left.color=root.right.color=1;
            return root;
        }
        return root;

    }

    public boolean hasRedChild(Tree root) {
        if (root == null || root.right == null || root.left == null) return false;
        return root.right.color == 0 || root.left.color == 0;
    }

}
