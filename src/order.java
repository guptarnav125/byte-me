import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class order implements Comparable<order>{
    private int orderid;
    public static int counter=1;
    private int customerid;
    private int priority;
    private String status="Pending";
    private String[] statusList={"pending","preparing","delivered","denied"};
    private HashMap<item,Integer> items=new HashMap<>();
    private Scanner scanner=new Scanner(System.in);
    private double total=0;

    public order(int customerid,int priority) {
        this.customerid = customerid;
        this.priority = priority;
    }

    public void addItem(int itemid,int quantity) {
        if(!menu.checkItem(itemid)) {
            System.out.println("Item with ID " + itemid + " does not exist.");
            return;
        }
        if(quantity<=0) {
            System.out.println("Minimum order should be 1");
            return;
        }
        item x=menu.getItem(itemid);
        if(x.getQuantity()==0) {
            System.out.println("Out of stock");
            return;
        }
        if(x.getQuantity()<quantity) {
            System.out.println("Only " + x.getQuantity()+" more "+x.getName()+ " available.");
            return;
        }
        items.put(x,quantity);
        x.setQuantity(x.getQuantity()-quantity);
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void removeItem(int itemid) {
        item x=menu.getItem(itemid);
        if(!items.containsKey(x)) {
            System.out.println("Item with ID " + itemid + " has not been added.");
            return;
        }
        int y=items.remove(x);
        x.setQuantity(x.getQuantity()+y);
    }

    public void updateTotal(){
        for (item x : items.keySet()) {
            total+=items.get(x)*x.getPrice();
        }
    }

    public void displayOrder() {
        System.out.printf("%-10s %-15s %-10s %-10s%n", "Item ID", "Name", "Price", "Quantity");
        System.out.println("-----------------------------------------------");

        for (item x : items.keySet()) {
            System.out.printf("%-10s %-15s %-10.2f %-10s%n", x.getItemid(), x.getName(), x.getPrice(), items.get(x));
        }

        System.out.print("Total: "+this.getTotal());
        System.out.println();
    }

    public void updateStatus(String status) {
        if(Arrays.asList(statusList).contains(status.toLowerCase())){this.status=status;}
        else{System.out.println("Invalid status, try again");}
    }

    public void updateQuantity(int itemid) {
        item x=menu.getItem(itemid);
        if (!items.containsKey(x)) {
            System.out.println("Item with ID " + itemid + " has not been added.");
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

        if(quantity<=0) {
            System.out.println("Minimum order should be 1");
            return;
        }
        x.setQuantity(x.getQuantity()+items.get(x));
        if(x.getQuantity()<quantity) {
            System.out.println("Only " + quantity+" more "+x.getName()+ " available.");
            return;
        }
        x.setQuantity(x.getQuantity()-quantity);
        items.replace(x,quantity);
    }

    public void confirmOrder(){}

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public HashMap<item, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(order other) {
        int priorityComparison = Integer.compare(this.priority, other.priority);
        if (priorityComparison == 0) {
            return Integer.compare(this.orderid, other.orderid);
        }
        return priorityComparison;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
}
