import java.util.ArrayList;
import java.util.PriorityQueue;

public class orderHistory {
    private ArrayList<order> orders=new ArrayList<>();
    private double customerTotal=0;
    private int priority;
    private static PriorityQueue<order> orderQueue=new PriorityQueue<>();

    public void addOrder(order o){
        o.setOrderid(order.counter++);
        customerTotal+=o.getTotal();
        orders.add(o);
        orderQueue.add(o);
    }

    public void customerHistory(){
        for(order order:orders){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Order Status: "+order.getStatus());

            order.displayOrder();
            System.out.println();
        }
    }

    public static void fulLHistory(){
        for(order order:orderQueue){
            System.out.println("Order ID: "+order.getOrderid());
            System.out.println("Customer ID: "+ order.getCustomerid());
            System.out.println("Order Status: "+order.getStatus());

            order.displayOrder();
            System.out.println();
        }
    }
}
