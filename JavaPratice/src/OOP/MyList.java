package OOP;

public class MyList {
    protected MyNode head;
    public MyList(MyNode dummyHead) {
        head = dummyHead;
    }

    public boolean addNewNode(MyNode newNode) {
        if(findNode(newNode.getKey()) != null)
            return false;

        newNode.next = head.next;
        head.next = newNode;
        return true;
    }

    public MyNode findNode(String key) {
        MyNode tmp = head.next;
        while(tmp != null) {
            if(tmp.getKey().compareTo(key) == 0)
                return tmp;

            tmp = tmp.next;
        }

        return null;
    }

    public void printAll() {
        MyNode tmp = head.next;
        while(tmp != null) {
            tmp = tmp.next;
        }
    }

    public boolean removeNode(String name) {
        MyNode prev = head;
        MyNode toDelete = null;

        while(prev.next != null) {
            toDelete = prev.next;
            if(toDelete.getKey().compareTo(name) == 0) {
                prev.next = toDelete.next;
                return true;
            }
            prev = prev.next;
        }

        return false;
    }
}

