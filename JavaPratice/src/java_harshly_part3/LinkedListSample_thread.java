package java_harshly_part3;

class UserData {
    UserData(String name) {
        this.name = name;
    }
    
    String name;
    UserData2 prev;
    UserData2 next;
}

class MyList {
    protected int counter = 0;
    protected UserData2 head = new UserData2("DummyHead");
    protected UserData2 tail = new UserData2("DummyTail");
    MyList() {
        head.next = tail;
        tail.prev = head;
    }

    public int size() {
        return counter;
    }

    public boolean appendNode(String name) {
    	UserData2 newUser = new UserData2(name);
        newUser.prev = tail.prev;
        newUser.next = tail;
        tail.prev.next = newUser;
        tail.prev = newUser;

        ++counter;
        return true;
    }

    public boolean isEmpty() {
        if(head.next == tail)
            return true;

        return false;
    }

    public void printAll() {
        System.out.println("-----------------------");
        System.out.println("Counter: " + counter);
        UserData2 tmp = head.next;
        while(tmp != tail) {
            System.out.println(tmp.name);
            tmp = tmp.next;
        }
        System.out.println("-----------------------");
    }

    public UserData2 removeAtHead() {
        if(isEmpty())
            return null;

        UserData2 node = head.next;
        node.next.prev = head;
        head.next = node.next;

        --counter;
        return node;
    }
}

public class LinkedListSample_thread {
    public static void main(String[] args) {
        MyList db = new MyList();
        
        db.appendNode("tester01");
        db.appendNode("tester02");
        db.appendNode("tester03");
        
        db.printAll();

        db.removeAtHead();
        db.printAll();
        db.removeAtHead();
        db.printAll();
        db.removeAtHead();
        db.printAll();
    }
}

