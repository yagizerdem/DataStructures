import java.security.spec.RSAOtherPrimeInfo;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Node insert(int value, Node node) {
        if (this.root == null) {
            Node newNode = new Node(value);
            this.root = newNode;
            return this.root;
        }
        if (node == null) {
            node = new Node(value);
            return node;
        } else if (value < node.value) {
            node.left = insert(value, node.left);
        } else {
            node.right = insert(value, node.right);
        }
        return node;
    }

    public void levelOrderTraverse() {
        Queue<Node> queue = new LinkedList<>();
        if (this.root != null) queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            System.out.println(temp.value + " ");
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
    }

    public Node findMin(Node node) {
        if (node == null) return null;
        if (node.left == null) {
            return node;
        } else return findMin(node.left);
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

    public Node deleteNode(int value, Node node) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = deleteNode(value, node.left);
        } else if (value > node.value) {
            node.right = deleteNode(value, node.right);
        } else {
            if (node.left != null && node.right != null) {
                Node minNode = findMin(node.right);
                node.value = minNode.value;
                node.right = deleteNode(node.value, node.right);
            } else if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                node = null;
            }
        }
        return node;
    }

    public boolean isExist(int value, Node node) {
        if (node == null) {
            System.out.println("not exist");
            return false;
        } else if (value == node.value) {
            System.out.println("exist");
            return true;
        } else if (value < node.value) {
            return isExist(value, node.left);
        } else {
            return isExist(value, node.right);
        }

    }
}
