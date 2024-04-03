public class BinaryHeap {
    int arr[];
    int sizeOfTree;

    public BinaryHeap(int size) {
        this.arr = new int[size + 1];
        this.sizeOfTree = 0;
    }

    public boolean isEmpty() {
        return this.sizeOfTree == 0;
    }

    public Integer peek() {
        if (isEmpty()) return null;
        return this.arr[1];
    }

    public void heapifyBottomToTop(int index, String heaptype) {
        int parentindex = index / 2;
        if (index <= 1) return;

        if (heaptype == "max") {
            if (this.arr[index] > this.arr[parentindex]) {
                int temp = this.arr[index];
                this.arr[index] = this.arr[parentindex];
                this.arr[parentindex] = temp;
            }
        } else if (heaptype == "min") {
            if (this.arr[index] < this.arr[parentindex]) {
                int temp = this.arr[index];
                this.arr[index] = this.arr[parentindex];
                this.arr[parentindex] = temp;
            }
        }
        heapifyBottomToTop(parentindex, heaptype);
    }

    public void heapifyTopToBottom(int index, String heaptype) {
        int left = index * 2;
        int right = index * 2 + 1;
        int swipedindex = 0;
        if (left > this.sizeOfTree) return;
        if (heaptype == "min") {
            if (left == this.sizeOfTree) {
                if (this.arr[left] < this.arr[index]) {
                    int temp = this.arr[left];
                    this.arr[left] = this.arr[index];
                    this.arr[index] = temp;
                }
                return;
            } else {
                if (this.arr[left] < this.arr[right]) {
                    swipedindex = left;
                } else if (this.arr[right] < this.arr[left]) {
                    swipedindex = right;
                }
                if (this.arr[swipedindex] < this.arr[index]) {
                    int temp = this.arr[swipedindex];
                    this.arr[swipedindex] = this.arr[index];
                    this.arr[index] = temp;
                }
            }
        } else if (heaptype == "max") {
            if (left == this.sizeOfTree) {
                if (this.arr[left] > this.arr[index]) {
                    int temp = this.arr[left];
                    this.arr[left] = this.arr[index];
                    this.arr[index] = temp;
                }
                return;
            } else {
                if (this.arr[left] > this.arr[right]) {
                    swipedindex = left;
                } else if (this.arr[right] > this.arr[left]) {
                    swipedindex = right;
                }
                if (this.arr[swipedindex] < this.arr[index]) {
                    int temp = this.arr[swipedindex];
                    this.arr[swipedindex] = this.arr[index];
                    this.arr[index] = temp;
                }
            }
        }
        heapifyTopToBottom(swipedindex, heaptype);
    }

    public void insert(int value, String heapttype) {
        this.arr[this.sizeOfTree + 1] = value;
        this.sizeOfTree++;
        this.heapifyBottomToTop(this.sizeOfTree, heapttype);
    }

    public void levelOrderTraverse() {
        if (!isEmpty()) {
            for (int i = 1; i <= this.sizeOfTree; i++) {
                System.out.println(this.arr[i]);
            }
        }
    }

    public Integer extractHead(String heaptype) {
        if (isEmpty()) return null;
        int temp = this.arr[1];
        this.arr[1] = this.arr[this.sizeOfTree];
        this.arr[this.sizeOfTree] = 0;
        this.sizeOfTree--;
        this.heapifyTopToBottom(1, heaptype);
        return temp;
    }
}
