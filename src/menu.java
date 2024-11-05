import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class menu {
    private static HashMap<Integer,item> Menu=new HashMap<>();
    private static TreeMap<item,Integer> MenuByPrice = new TreeMap<>(new priceComparator());
    private static Scanner scanner=new Scanner(System.in);

    public static void addItem(item item){
        MenuByPrice.put(item,item.getItemid());
        Menu.put(item.getItemid(),item);
    }

    public static boolean checkItem(int itemid){
        return Menu.containsKey(itemid);
    }

    public static item getItem(int itemid){
        return Menu.get(itemid);
    }

    public static void deleteItem(int itemid){
        if (!Menu.containsKey(itemid)) {
            System.out.println("Item with ID " + itemid + " does not exist.");
            return;
        }
        item x=Menu.remove(itemid);



        MenuByPrice.remove(x);
    }

    public static void updateItem(int itemid){
        if (!Menu.containsKey(itemid)) {
            System.out.println("Item with ID " + itemid + " does not exist.");
            return;
        }

        System.out.print("New Name: ");
        String name = scanner.nextLine();
        double price;
        do {
            System.out.print("New Price: ");
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
            System.out.print("New Quantity: ");
            try {
                quantity = scanner.nextInt();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input, try again");
            }
        }while(true);
        scanner.nextLine();

        String category;
        do {
            System.out.print("Category: ");
            category = scanner.nextLine();
        } while (!item.checkCategory(category));

        for(item i:MenuByPrice.keySet()){
            if(i.getItemid()==itemid){
                i.setName(name);
                i.setPrice(price);
                i.setQuantity(quantity);
                i.setCategory(category);
            }
        }

        for(item i:Menu.values()){
            if(i.getItemid()==itemid){
                i.setName(name);
                i.setPrice(price);
                i.setQuantity(quantity);
                i.setCategory(category);
            }
        }
    }

    public static void updatePrice(int itemid){
        if (!Menu.containsKey(itemid)) {
            System.out.println("Item with ID " + itemid + " does not exist.");
            return;
        }

        double price;
        do {
            System.out.print("New Price: ");
            try {
                price = scanner.nextDouble();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input, try again");
            }
        }while(true);
        scanner.nextLine();

        for(item i:MenuByPrice.keySet()){
            if(i.getItemid()==itemid){
                i.setPrice(price);
            }
        }

        for(item i:Menu.values()){
            if(i.getItemid()==itemid){
                i.setPrice(price);
            }
        }
    }

    public static void updateQuantity(int itemid){
        if (!Menu.containsKey(itemid)) {
            System.out.println("Item with ID " + itemid + " does not exist.");
            return;
        }

        int quantity;
        do {
            System.out.print("New Quantity: ");
            try {
                quantity = scanner.nextInt();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input, try again");
            }
        }while(true);
        scanner.nextLine();

        for(item i:MenuByPrice.keySet()){
            if(i.getItemid()==itemid){
                i.setQuantity(quantity);
            }
        }

        for(item i:Menu.values()){
            if(i.getItemid()==itemid){
                i.setQuantity(quantity);
            }
        }
    }

    public static void displayMenu(){
        System.out.printf("%-10s %-15s %-10s %-13s %-10s%n", "Item ID", "Name", "Price", "Availability","Category");
        System.out.println("-------------------------------------------------------------");

        for (item x : Menu.values()) {
            System.out.printf("%-10s %-15s %-10.2f %-13s %-10s%n", x.getItemid(), x.getName(), x.getPrice(), x.getQuantity(),x.getCategory());
        }
    }

    public static void displayMenuByPrice(){
        System.out.printf("%-10s %-15s %-10s %-13s %-10s%n", "Item ID", "Name", "Price", "Availability","Category");
        System.out.println("-------------------------------------------------------------");

        for (item x : MenuByPrice.keySet()) {
            System.out.printf("%-10s %-15s %-10.2f %-13s %-10s%n", x.getItemid(), x.getName(), x.getPrice(), x.getQuantity(), x.getCategory());
        }
    }

    public static void search(){
        System.out.print("Enter keyword for search: ");
        String search=scanner.nextLine();
        System.out.printf("%-10s %-15s %-10s %-13s %-10s%n", "Item ID", "Name", "Price", "Availability","Category");
        System.out.println("-------------------------------------------------------------");
        boolean found=false;
        for(item item:Menu.values()) {
            if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                found = true;
                System.out.printf("%-10s %-15s %-10.2f %-13s %-10s%n", item.getItemid(), item.getName(), item.getPrice(), item.getQuantity(), item.getCategory());
            }
        }
        if(!found){
            System.out.println("No items found matching your search");
        }
    }

    public static void filterCategory(){}
}
