import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        admin arnav = new admin("Arnav","123456");
        customer adarsh=new customer("Adarsh","booboo");
        customer prakhar=new customer("Prakhar","shreya");
        customer nakul=new customer("Nakul","nocool");

        menu.addItem(new item("Pizza",80,10));
        menu.addItem(new item("Burger",40,20));
        menu.addItem(new item("Maggi",25,30));
        menu.addItem(new item("Aloo Patty",30,20));
        menu.addItem(new item("Chicken Patty",40,20));
        menu.addItem(new item("Cold Coffee",40,50));

        String username1;
        String password1;
        int choice1;

        do{
            try{
                System.out.println("Welcome to Byte Me!");
                System.out.println("1. Customer Signup");
                System.out.println("2. Customer Login");
                System.out.println("3. Admin Login");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice1 = scanner.nextInt();
            }catch(Exception e){
                System.out.println("Invalid choice, try again");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            if(choice1==4){break;}
            System.out.print("Username: ");
            username1 = scanner.nextLine();
            System.out.print("Password: ");
            password1 = scanner.nextLine();
            switch(choice1){
                case 1:
                    new customer(username1,password1);
                    System.out.println("Customer Signup Successful");
                    break;
                case 2:
                    customer user1=customer.login(username1,password1);
                    if(user1!=null){
                        user1.mainMenu();
                    }
                    break;
                case 3:
                    admin user2=admin.login(username1,password1);
                    if(user2!=null){
                        user2.mainMenu();
                    }
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        }while(true);
    }
}