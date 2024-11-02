import java.util.HashMap;
import java.util.Scanner;

public class admin extends user{
    private static HashMap<String,admin> admins=new HashMap<>();
    int choice;
    int choice1;
    int choice2;
    Scanner scanner=new Scanner(System.in);

    public admin(String name, String password) {
        super(name, password);
        admins.put(name,this);
    }

    public static admin login(String username, String password) {
        admin admin = admins.get(username);
        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Admin Login Successful");
            return admin;
        }
        else{
            System.out.println("Admin Login Unsuccessful");
            return null;
        }
    }

    @Override
    public void mainMenu(){
        boolean logout=false;
        do{
            System.out.println("Admin Mode:");
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Generate Report");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            try{
                choice=scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid choice, try again");
                continue;
            }
            scanner.nextLine();
            switch(choice){
                case 1:
                    this.menuManagement();
                    break;
                case 2:
                case 3:
                case 4:
                    logout=true;
                    System.out.println("Logout Successful");
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        }while(!logout);
    }

    public void menuManagement(){
        boolean back=false;
        do {
            System.out.println("Menu Management:");
            System.out.println("1. View Menu");
            System.out.println("2. Add New Item");
            System.out.println("3. Update Existing Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Modify Prices");
            System.out.println("6. Update Availability");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");
            try {
                choice1 = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid choice, try again");
                continue;
            }
            scanner.nextLine();
            switch (choice1) {
                case 1:
                    menu.displayMenu();
                    break;
                case 2:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    double price;
                    do {
                        System.out.print("Price: ");
                        try {
                            price = scanner.nextDouble();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    int quantity;
                    do {
                        System.out.print("Quantity: ");
                        try {
                            quantity = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.addItem(new item(name, price, quantity));
                    break;
                case 3:
                    do {
                        System.out.println("Item ID of item to be updated: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.updateItem(choice2);
                    break;
                case 4:
                    do {
                        System.out.println("Item ID of item to be deleted: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.deleteItem(choice2);
                    break;
                case 5:
                    do {
                        System.out.println("Item ID of item to be updated: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.updatePrice(choice2);
                    break;
                case 6:
                    do {
                        System.out.println("Item ID of item to be updated: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.updateQuantity(choice2);
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } while (!back);
    }


}
