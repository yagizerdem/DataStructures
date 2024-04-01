public class BinaryTreeArr {
    Object[] arr;
    int lastUsedIndex;

    public BinaryTreeArr(int size) {
        this.arr = new Object[size + 1];
        this.lastUsedIndex = 0;
    }

    public void insert(Object item) {
        if (this.lastUsedIndex == this.arr.length - 1) return;
        this.arr[this.lastUsedIndex + 1] = item;
        this.lastUsedIndex++;
    }

    public void levelOrderTraverse() {
        for (int i = 1; i <= this.lastUsedIndex; i++) {
            System.out.println(this.arr[i] + " ");
        }
    }

    public void postOrderTraverse(int index) {
        if (index > this.lastUsedIndex || index <= 0) return;
        postOrderTraverse(index * 2);
        postOrderTraverse(index * 2 + 1);
        System.out.println(this.arr[index]);
    }

    public void preOrderTraversal(int index) {
        if (index > this.lastUsedIndex || index <= 0) return;
        System.out.println(this.arr[index]);
        preOrderTraversal(index * 2);
        preOrderTraversal(index * 2 + 1);
    }

    public void inOrderTraverse(int index) {
        if (index > this.lastUsedIndex || index <= 0) return;
        preOrderTraversal(index * 2);
        System.out.println(this.arr[index]);
        preOrderTraversal(index * 2 + 1);
    }

    public int search(Object item) {
        for (int i = 0; i <= this.lastUsedIndex; i++) {
            if (this.arr[i] == item) return i;
        }
        return -1;
    }

    public void deleteItem(Object item) {
        for (int i = 0; i <= this.lastUsedIndex; i++) {
            if (this.arr[i] == item) {
                this.arr[i] = this.arr[this.lastUsedIndex];
                this.lastUsedIndex--;
            }
        }
    }

    public void deleteTree() {
        this.arr = null;
    }
}
