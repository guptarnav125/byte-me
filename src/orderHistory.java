import java.util.TreeSet;

public class orderHistory {
    private TreeSet<order> orders=new TreeSet<>();
    private double customerTotal=0;
    private int customerid;
    private int priority;
    private static TreeSet<order> orderQueue=new TreeSet<>();

    public orderHistory(int customerid,int priority) {
        this.customerid = customerid;
        this.priority = priority;
    }

    public void addOrder(order o){
        o.setOrderid(order.counter++);
        o.updateTotal();
        customerTotal+=o.getTotal();
        orders.add(o);
        orderQueue.add(o);
    }

    public void customerHistory(){
        System.out.println();
        for(order order:orders){
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
        for(order order:orderQueue){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Order Status: "+order.getStatus());

            order.displayOrder();
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
}
