package java_harshly_part3;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;

class UserData2 {
    UserData2(String name) {
        this.name = name;
    }
    String name;
    UserData2 prev;
    UserData2 next;
}

class MyList2 {
    protected ReentrantLock lock = new ReentrantLock();
    protected int counter = 0;
    protected UserData2 head = new UserData2("DummyHead");
    protected UserData2 tail = new UserData2("DummyTail");
    MyList2() {
        head.next = tail;
        tail.prev = head;
    }

    public int size() {
        return counter;
    }

    public boolean appendNode(String name) {
        UserData2 newUser = new UserData2(name);

        lock.lock();
            newUser.prev = tail.prev;
            newUser.next = tail;
            tail.prev.next = newUser;
            tail.prev = newUser;
            ++counter;
        lock.unlock();

        return true;
    }

    public boolean isEmpty() {
        if(head.next == tail)
            return true;

        return false;
    }

    public synchronized void printAll() {
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

        lock.lock();
            UserData2 node = head.next;
            node.next.prev = head;
            head.next = node.next;
            --counter;
        lock.unlock();

        return node;
    }
}

public class LinkedListThread {
    private static MyList db = new MyList();
    public static void main(String[] args) throws InterruptedException {
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Consumer - begin");
                UserData2 node;
                while (true) {
                    node = db.removeAtHead();
                    if(node == null)
                        try { sleep(1); }
                        catch (InterruptedException e) {
                            System.out.println("Consumer - interrupted");
                            break;
                        }
                }
                System.out.println("Consumer - end");
            }
        });

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("producer - begin");
                for(int i = 0; i < 10000000; ++i)
                    db.appendNode("tester" + i);
                System.out.println("producer - end");
            }
        });

        producer.start();
        consumer.start();
        sleep(5000);
        consumer.interrupt();

        db.printAll();
    }
}