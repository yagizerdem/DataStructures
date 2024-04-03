import javax.print.DocFlavor;
import javax.swing.text.StyledEditorKit;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.RecursiveTask;

public class AVLTree {

    Node root;

    public int getHeight(Node node) {
        if (node == null) return 0;
        else return node.height;
    }

    public int getBalance(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateRight(Node disbalanced) {
        Node root = disbalanced.left;
        disbalanced.left = disbalanced.left.right;
        root.right = disbalanced;
        disbalanced.height = 1 + Math.max(getHeight(disbalanced.left), getHeight(disbalanced.right));
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        return root;
    }

    private Node rotateLeft(Node disbalanced) {
        Node root = disbalanced.right;
        disbalanced.right = disbalanced.right.left;
        root.left = disbalanced;
        disbalanced.height = 1 + Math.max(getHeight(disbalanced.left), getHeight(disbalanced.right));
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return root;
    }

    private Node insertNode(Node node, int value) {
        if (node == null) {
            Node newNode = new Node();
            newNode.value = value;
            newNode.height = 1 + Math.max(getHeight(newNode.left), getHeight(newNode.right)); // 1
            return newNode;
        }
        if (value < node.value) {
            node.left = insertNode(node.left, value);
        } else if (value > node.value) {
            node.right = insertNode(node.right, value);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right)); // 1
        int balance = getBalance(node);
        if (balance > 1 && value < node.left.value) {
            //LL
            return rotateRight(node);
        }
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && value > node.right.value) {
            //RR
            return rotateLeft(node);
        }
        if (balance < -1 && value < node.right.value) {
            //RL
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public Node findMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return findMin(node.left);
    }

    public Node deleteNode(Node node, int value) {
        if (node == null) {
            System.out.println("Value not found in AVL");
            return node;
        }
        if (value < node.value) {
            node.left = deleteNode(node.left, value);
        } else if (value > node.value) {
            node.right = deleteNode(node.right, value);
        } else {
            if (node.left != null && node.right != null) {
                Node temp = node;
                Node minNodeForRight = findMin(temp.right);
                node.value = minNodeForRight.value;
                node.right = deleteNode(node.right, minNodeForRight.value);
            } else if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                node = null;
            }
        }
        // Case 2 - rotation required

        // System.out.println("1");
        // System.out.println(previous.value);

        // node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;

    }

    public void delete(int value) {
        this.root = deleteNode(this.root, value);
    }

    public void insert(int value) {
        this.root = insertNode(this.root, value);
    }

    public boolean search(int value) {
        if (this.root == null) return false;
        Queue<Node> queue = new LinkedList();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            if (value == temp.value) {
                return true;
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
        }
        return false;
    }

    public void preOrderTraverse(Node node) {
        if (node == null) return;
        System.out.println(node.value);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    public void inOrderTraverse(Node node) {
        if (node == null) return;
        preOrderTraverse(node.left);
        System.out.println(node.value);
        preOrderTraverse(node.right);
    }

    public void postOrderTraverse(Node node) {
        if (node == null) return;
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
        System.out.println(node.value);
    }

    public void levelOrderTraverse() {
        if (this.root == null) return;
        Queue<Node> queue = new LinkedList();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            System.out.println(temp.value + "-->");
            if (temp.right != null) {
                queue.add(temp.right);
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
        }

    }
}
