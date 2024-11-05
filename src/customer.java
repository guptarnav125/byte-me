import java.util.HashMap;
import java.util.Scanner;

public class customer extends user {
    private static HashMap<String, customer> customers = new HashMap<>();
    private int choice;
    private int choice1;
    private int choice2;
    private int priority=2;
    private String choice3;
    private Scanner scanner = new Scanner(System.in);
    public orderHistory history = new orderHistory(this.getUserid(),this.priority);

    public customer(String name, String password) {
        super(name, password);
        customers.put(name, this);
    }

    public static customer login(String username, String password) {
        customer customer = customers.get(username);
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Customer Login Successful");
            return customer;
        } else {
            System.out.println("Customer Login Unsuccessful");
            return null;
        }
    }

    @Override
    public void mainMenu() {
        boolean logout = false;
        do {
            System.out.println("Customer Mode:");
            System.out.println("1. Browse Menu");
            System.out.println("2. Go to Cart");
            System.out.println("3. Track Order");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid choice, try again");
                continue;
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    this.browseMenu();
                    break;
                case 2:
                    this.cartOrder();
                    break;
                case 3:
                    this.trackOrder();
                    break;
                case 4:
                    logout = true;
                    System.out.println("Logout Successful");
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } while (!logout);
    }

    public void trackOrder() {
        boolean back = false;
        do {
            System.out.println("Order Tracking:");
            System.out.println("1. View Order Status");
            System.out.println("2. Cancel Order");
            System.out.println("3. Order History");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid choice, try again");
                continue;
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    this.history.customerHistory();
                    break;
                case 2:
                    do {
                        System.out.println("Order ID of order to be cancelled: ");
                        try {
                            choice1 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    this.history.cancelOrder(choice1);
                    break;
                case 3:
                    this.history.customerHistory();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } while (!back);
    }

    public void browseMenu() {
        boolean back = false;
        do {
            System.out.println("Browse Menu:");
            System.out.println("1. View all items");
            System.out.println("2. Search");
            System.out.println("3. Filter by category");
            System.out.println("4. Sort by Price");
            System.out.println("5. View reviews");
            System.out.println("6. Provide reviews");
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
                    menu.search();
                    break;
                case 3:
                    menu.filterCategory();
                    break;
                case 4:
                    menu.displayMenuByPrice();
                    break;
                case 5:
                    do {
                        System.out.println("Item ID of item for review: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.viewReviews(choice2);
                    break;
                case 6:
                    do {
                        System.out.println("Item ID of item for review: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();
                    menu.addReviews(choice2);
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

    public void cartOrder() {
        order order1=new order(this.getUserid(),this.priority);
        boolean back = false;
        do {
            System.out.println("Cart Operations:");
            System.out.println("1. Add item");
            System.out.println("2. Modify quantity");
            System.out.println("3. Remove item");
            System.out.println("4. View total");
            System.out.println("5. Checkout");
            System.out.println("6. Back");
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
                    do {
                        System.out.println("Item ID of item to be added: ");
                        try {
                            choice2 = scanner.nextInt();
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

                    order1.addItem(choice2, quantity);
                    break;
                case 2:
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

                    order1.updateQuantity(choice2);
                    break;
                case 3:
                    do {
                        System.out.println("Item ID of item to be removed: ");
                        try {
                            choice2 = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Invalid input, try again");
                        }
                    }while(true);
                    scanner.nextLine();

                    order1.removeItem(choice2);
                    break;
                case 4:
                    if(order1.isEmpty()){
                        System.out.println("Order is empty");
                        break;
                    }
                    order1.displayOrder();
                    break;
                case 5:
                    if(order1.isEmpty()){
                        System.out.println("Order is empty");
                        break;
                    }
                    if (this.priority==2) {
                        System.out.print("Would you like to purchase VIP membership for 100 rupees? (y/n): ");
                        choice3=scanner.nextLine().toLowerCase();
                        switch (choice3) {
                            case "y":
                                System.out.println("VIP Membership Purchased");
                                history.setCustomerTotal(history.getCustomerTotal()+100);
                                order1.setPriority(1);
                                history.setPriority(1);
                                this.priority=1;
                                break;
                            case "n":
                                System.out.println("VIP membership Not Purchased");
                                break;
                            default:
                                System.out.println("Invalid choice, VIP membership Not Purchased");
                                break;
                        }
                    }
                    System.out.println("Do you have any special requests?");
                    order1.setRequest(scanner.nextLine());
                    history.addOrder(order1);
                    order1.displayOrder();
                    System.out.println("Checkout Successful");
                    back = true;
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice, try again");
                    break;
            }
        } while (!back);
    }
}