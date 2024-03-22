import java.util.Arrays;

public class Queue_ {
    private int rear , front;
    private  Object[] queue;
    public Queue_(int c){
        this.rear = this.front = -1;
        this.queue = new Object[c];
    }
    public void enqueue(Object data){
        if(isFull()) return;
        this.queue[++this.rear] = data;
    }
    public Object dequeue(){
        if(isEmpty()) return null;
        Object data = this.queue[0];
        for(int i = 0 ; i < this.queue.length-1 ; i++){
            this.queue[i] = this.queue[i+1];
        }
        this.queue[rear--] = null;
        return  data;
    }
    public boolean isEmpty(){
        return this.rear == -1;
    }
    public boolean isFull(){
        return  this.rear == this.queue.length -1;
    }
    public Object peek(){
        if(isEmpty()) return null;
        return  this.queue[0];
    }
    public void  Traverse(){
        for(int i = 0 ; i < this.queue.length ; i++){
            if(i <= this.rear)System.out.println(this.peek());
            this.enqueue(this.dequeue());
        }
    }
}
