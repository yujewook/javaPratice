package OOP;

import java.util.Scanner;

public class UserInterface {
	UserInterface(MyList myList){
		this.myList = myList;
	}
	static MyList myList;
	
	public static void addUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Phone: ");
        String phone = s.nextLine();

        myList.addNewNode(name, phone);
    }
    
    public static void searchUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();

        UserData user = myList.findNode(name);
        if(user != null) {
            System.out.println("Name: " + user.name);
            System.out.println("Phone: " + user.phone);
        }
        else
            System.out.println("Error: Failed to find " + name);
    }
    
    public static void removeUser() {
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String name = s.nextLine();

        if(myList.removeNode(name))
            System.out.println("Removed!");
        else
            System.out.println("Error: Failed to remove " + name);
    }
    
    public static int printUi() {
        System.out.println("[1] Add\t[2] Search\t[3] Print all\t[4] Remove\t[0] Exit");
        Scanner s = new Scanner(System.in);
        System.out.print(": ");
        int input = s.nextInt();
        return input;
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
                	myList.printAll();
                    break;

                case 4:
                    removeUser();
                    break;
            }
        }
    
    	return menu;
    }
}
