import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.ArrayList;

public class SLL<T> {
    public Node<T> head = null;
    public Node<T> tail = null;

    public int size = 0;

    public void AddHead(Node newNode){
        if(head == null){
            this.head = this.tail = newNode;
            this.size++;
            return;
        }
        newNode.next = head;
        this.head = newNode;
        this.size++;
    };
    public void AddTail(Node newNode){
        if(this.tail == null){
            this.tail = this.head = newNode;
            this.size++;
            return;
        }
        this.tail.next = newNode;
        this.tail = newNode;
        this.size++;
    }

    public boolean Set(int index , Node newNode){
        if(index < 0 ||index >= this.size) return false;
        if(index == 0) {
            AddHead(newNode);
        }
        else if(index == this.size -1){
            AddTail(newNode);
        }
        else{
            Node prev = null;
            Node cur = this.head;
            for(int i = 0; i < index;i++){
                prev = cur;
                cur = cur.next;
            }
            prev.next = newNode;
            newNode.next = cur;
        }

        this.size++;
        return  true;
    }

    public Node RemoveHead(){
        if(this.size == 0) return null;
        if(this.size == 1) {
            Node temp = this.head;
            this.head = this.tail = null;
            this.size--;
            return temp;
        }
        else{
            Node temp = this.head;
            this.head = this.head.next ;
            temp.next = null;
            this.size--;
            return temp;
        }
    }

    public Node RemoveTail(){
        if(this.size == 0) return null;
        if(this.size == 1) {
            Node temp = this.tail;
            this.tail = this.head = null;
            this.size--;
            return temp;
        }
        else{
            Node prev = null;
            Node cur = this.head;
            for(int i = 0 ; i < this.size -1 ; i++){
                prev = cur;
                cur = cur.next;
            }
            Node temp = cur;
            prev.next = null;
            this.tail = prev;
            this.size--;
            return  temp;
        }
    }

    public void DeleteAll(){
        this.head = this.tail = null;
        this.size = 0;
    }
    public Node RemoveNode(int index){
        if(index < 0 || index >= this.size) return  null;
        if(index == 0) {
            Node headNode = RemoveHead();
            return headNode;
        }
        else if(index == this.size-1){
            Node tailNode = RemoveTail();
            return  tailNode;
        }
        else{
            Node prev = null;
            Node cur = this.head;
            for(int i = 0 ; i < index;i++){
                prev = cur;
                cur = cur.next;
            }
            Node temp = cur;
            prev.next = cur.next;
            cur.next = null;
            this.size--;
            return  temp;
        }
    }
    public void Traverse(){
        Node node = this.head;
        while (node != null){
            System.out.println(node.val);
            node= node.next;
        }
    }

    public Object getHeadValue(){
        return this.head.val;
    }
    public Object getTailValue(){
        return this.tail.val;
    }
    public Object getByIndex(int index){
        if(index < 0 || index >= this.size) return  null;
        Node prev = null;
        Node cur = this.head;
        for(int i = 0 ; i < index;i++){
            prev = cur;
            cur = cur.next;
        }
        return  cur.val;
    }

    public void swap(int index1 , int index2){
        if(index1 < 0 || index2 < 0 || index1 >= this.size || index2 >= this.size) return;
        Node node1 = null;
        Node node2 = null;
        Node temp = this.head;
        int max = Math.max(index1 , index2);
        int counter = 0;
        while (counter <= max && temp != null){
            if(index1 >= counter){
                node1 = temp;
            }
            if(index2 >= counter){
                node2 = temp;
            }
            temp = temp.next;
            counter++;
        }
        Object value = node1.val;
        node1.val = node2.val;
        node2.val = value;
    }

    public void SortedInsert(Node newNode , boolean asc){
        if(!(newNode.val instanceof  Integer)) return;
        Node cur= this.head;
        Node prev = null;
        while (cur != null){
            if(asc){
                if((Integer)cur.val > (Integer)newNode.val) break;
            }
            else{
                if((Integer)cur.val < (Integer)newNode.val) break;
            }
            prev = cur;
            cur = cur.next;
        }
        if(prev == null){
            AddHead(newNode);
        }
        else if(cur == this.tail){
            AddTail(newNode);
        }
        else{
            newNode.next = prev.next;
            prev.next = newNode;
            this.size++;
        }
    }
    public  void Sort(boolean asc){
        ArrayList<Integer> list = new ArrayList<>();
        Node temp = this.head;
        while (temp != null){
            list.add((Integer) temp.val);
            temp = temp.next;
        }
        for(int i = 0 ; i < list.size() -1 ; i++){
            for(int j = 0 ; j <list.size() -1 -i; j++){
                if((asc && list.get(j) > list.get(j+1)) || (!asc &&list.get(j) < list.get(j+1) )){
                        int value = list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,value);
                }
            }
        }

        temp = this.head;
        for(int i = 0 ; i < this.size-1 ; i++){
            temp.val = list.get(i);
            temp = temp.next;
        }
    }
    public boolean IsContain(T value){
        Node temp = this.head;
        while (temp != null){
            if(temp.val == value){
                return  true;
            }
            temp = temp.next;
        }
        return false;
    }
}
