import java.util.LinkedList;
import java.util.Queue;


public class BinaryTreeLL {
    public Node root;

    public BinaryTreeLL() {
        this.root = null;
    }

    public void insert(Node newnode) {
        if (this.root == null) {
            this.root = newnode;
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            if (temp.left == null) {
                temp.left = newnode;
                return;
            } else if (temp.right == null) {
                temp.right = newnode;
                return;
            } else {
                queue.add(temp.left);
                queue.add(temp.right);
            }
        }
    }

    public void levelOrderTraversal() {
        if (this.root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            System.out.println(temp.value);
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
    }

    public void postOrderTraversal(Node root) {
        if (root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.value);
    }

    public void inOrderTraversal(Node root) {
        if (root == null) return;
        inOrderTraversal(root.left);
        System.out.println(root.value);
        inOrderTraversal(root.right);
    }

    public void preOrderTraversal(Node root) {
        if (root == null) return;
        System.out.println(root.value);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void deleteTree() {
        this.root = null;
    }

    public boolean search(Object value) {
        if (this.root == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            if (temp.value == value) return true;
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        return false;
    }

    public Node getDeepest() {
        if (this.root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        Node deepest = null;
        while (!queue.isEmpty()) {
            deepest = queue.remove();
            if (deepest.left != null) queue.add(deepest.left);
            if (deepest.right != null) queue.add(deepest.right);
        }
        return deepest;
    }

    public void deleteDeepest() {
        if (this.root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        Node prev, current = null;
        while (!queue.isEmpty()) {
            prev = current;
            current = queue.remove();

            if (current.left == null) {
                prev.right = null;
                return;
            } else if (current.right == null) {
                current.left = null;
                return;
            }

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public void deleteNode(Object value) {
        if (this.root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            if (temp.value == value) {
                Node deepest = getDeepest();
                this.deleteDeepest();
                temp.value = deepest.value;
                return;
            }
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
    }
}
