/*
 * 클래스 나누기 전  
 */
package java_harshly_part2;
import java.util.Scanner;

class UserData {
    UserData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    String name;
    String phone;
    UserData next;
}

class MyList {
    protected UserData head = new UserData("Dummy", "Dummy");
    MyList() { }

    public boolean addNewNode(String name, String phone) {
        if(findNode(name) != null)
            return false;

        UserData newUser = new UserData(name, phone);
        newUser.next = head.next;
        head.next = newUser;

        return true;
    }

    public UserData findNode(String name) {
        UserData tmp = head.next;
        while(tmp != null) {
            if(tmp.name.compareTo(name) == 0)
                return tmp;

            tmp = tmp.next;
        }

        return null;
    }

    public void printAll() {
        UserData tmp = head.next;
        while(tmp != null) {
            System.out.println(tmp.name + "\t" + tmp.phone);
            tmp = tmp.next;
        }
    }

    public boolean removeNode(String name) {
        UserData prev = head;
        UserData toDelete = null;

        while(prev.next != null) {
            toDelete = prev.next;

            if(toDelete.name.compareTo(name) == 0) {
                prev.next = toDelete.next;
                return true;
            }

            prev = prev.next;
        }

        return false;
    }
}

class UserInterface {
    MyList list;
    UserInterface(MyList list) {
        this.list = list;
    }

    public int printUi() {
        System.out.println("[1] Add\t[2] Search\t[3] Print all\t[4] Remove\t[0] Exit");
        Scanner s = new Scanner(System.in);
        System.out.print(": ");
        int input = s.nextInt();
        return input;
    }

    public void addUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Phone: ");
        String phone = s.nextLine();

        list.addNewNode(name, phone);
    }

    public void searchUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();

        UserData user = list.findNode(name);
        if(user != null) {
            System.out.println("Name: " + user.name);
            System.out.println("Phone: " + user.phone);
        }
        else
            System.out.println("Error: Failed to find " + name);
    }

    public void removeUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();

        if(list.removeNode(name))
            System.out.println("Removed!");
        else
            System.out.println("Error: Failed to remove " + name);
    }

    public int run() {
        int menu = 0;
        while((menu = printUi()) != 0) {
            switch (menu) {
                case 1:
                    addUser();
                    break;

                case 2:
                    searchUser();
                    break;

                case 3:
                    list.printAll();
                    break;

                case 4:
                    removeUser();
                    break;
            }
        }

        return menu;
    }
}

public class Main {
    public static void main(String[] args) {
        MyList db = new MyList();
        UserInterface ui = new UserInterface(db);
        ui.run();
    }
}
