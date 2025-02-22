import java.util.HashMap;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class orderHistory {
    private TreeSet<order> orders=new TreeSet<>();
    private double customerTotal=0;
    private int customerid;
    private customer customer;
    private int priority;
    private static TreeSet<order> orderQueue=new TreeSet<>();
    private static HashMap<order,String> completedOrders=new HashMap<>();
    private static HashMap<order,String> cancelledOrders=new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static double totalSales=0;

    public orderHistory(customer customer1,int priority) {
        this.customer = customer1;
        this.priority = priority;
        this.customerid=customer.getUserid();
    }

    public void addOrder(order o){
        o.setOrderid(order.counter++);
        o.updateTotal();
        customerTotal+=o.getTotal();
        totalSales+=o.getTotal();
        orders.add(o); //customer's orders
        fileManager.writeAllOrders(this.customer.getOrderAddress(),orders);
        orderQueue.add(o); //all orders
        fileManager.writeAllOrders("orders.dat",orderQueue);
    }

    public static void generateReport(){
        System.out.println();
        System.out.println("Total Sales: "+totalSales); //total updates when order is cancelled
        System.out.println("Total Orders: "+(order.counter-1)); //total orders includes cancelled orders
        System.out.println();

    }

    public void customerHistory(){
        System.out.println();
        TreeSet<order> orders1= fileManager.readOrdersFromFile(this.customer.getOrderAddress());
        if(orders1==null){
            System.out.println("No customer history found");
            return;
        }
        for(order order:orders1){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ this.customerid);
            System.out.println("Order Status: "+order.getStatus());

            order.displayOrder();
            System.out.println();
        }
        if(this.priority==1){
            System.out.println("Cost of VIP membership: 100");
        }
        System.out.println("Total for Customer "+this.customerid+": "+ this.customerTotal);
    }

    public static void fulLHistory(){
        System.out.println();
        TreeSet<order> orders1= fileManager.readOrdersFromFile("orders.dat");
        if(orders1==null){
            System.out.println("No order history found");
            return;
        }
        for(order order:orders1){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Order Status: "+order.getStatus());

            order.displayOrder();
            System.out.println();
        }
    }

    public static void updateStatus(){
        System.out.println();
        System.out.println("To Update Status:");
        System.out.println("1. Pending");
        System.out.println("2. Preparing");
        System.out.println("3. Delivered");
        System.out.println("4. Denied");
        System.out.println();
        int choice1;
        for(order order:orderQueue){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Current Status: "+order.getStatus());
            System.out.println();
            System.out.print("New Status (Enter your choice): ");
            try {
                choice1=scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            switch(choice1){
                case 1:
                    order.setStatus("Pending");
                    break;
                case 2:
                    order.setStatus("Preparing");
                    break;
                case 3:
                    order.setStatus("Delivered");
                    orderQueue.remove(order);
                    completedOrders.put(order,order.getStatus());
                    break;
                case 4:
                    order.setStatus("Denied");
                    orderQueue.remove(order);
                    cancelledOrders.put(order,order.getStatus());
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void processRefunds(){
        System.out.println();
        for(order order: cancelledOrders.keySet()){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Order Status: "+order.getStatus());

            System.out.println("Refund has been initiated");
            System.out.println();
        }
    }

    public void cancelOrder(int orderid){
        boolean found=false;
        System.out.println();
        for(order order:orderQueue){
            if(order.getOrderid()==orderid && order.getCustomerid()==this.customerid){
                found=true;
                if(order.getStatus().equalsIgnoreCase("pending")){
                    orderQueue.remove(order);
                    order.setStatus("Cancelled");
                    this.customerTotal-=order.getTotal();
                    totalSales-=order.getTotal();
                    cancelledOrders.put(order,order.getStatus());
                }
                else{
                    System.out.println("Preparation of order has started. Order cannot be cancelled.");
                }
                break;
            }
        }
        if(!found){
            System.out.println("Order cannot be cancelled");
            System.out.println();
        }
    }

    public static void handleRequests(){
        System.out.println();
        for(order order: orderQueue){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Special Request: "+order.getRequest());

            System.out.println("Special request has been handled");
            System.out.println();
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(double customerTotal) {
        this.customerTotal = customerTotal;
    }

    public static TreeSet<order> getOrderQueue() {
        return orderQueue;
    }

    public TreeSet<order> getOrders() {
        return orders;
    }
}
