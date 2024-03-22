public class CircularQueue {

    private int front;
    private int rear;
    private Object[] circularqueue;

    CircularQueue(int c){
        this.front = this.rear = 0;
        circularqueue = new Object[c];
    }
    public void enqueue(Object data){
        if(isFull()) return;
        this.circularqueue[this.rear] = data;
        this.rear = (this.rear + 1) % this.circularqueue.length;
    }
    public Object dequeue(){
        if(isEmpty()) return null;
        Object data = this.circularqueue[this.front];
        this.circularqueue[this.front] = null;
        this.front = (this.front + 1) % this.circularqueue.length;
        return data;
    }
    public boolean isFull(){
        if(this.front == this.rear && this.circularqueue[front] != null){
            System.out.println("queue is full");
            return  true;
        }
        return  false;
    }
    public boolean isEmpty(){
        if(this.front == this.rear && this.circularqueue[front]== null){
            System.out.println("queue is empyt");
            return  true;
        }
        return  false;
    }


}
