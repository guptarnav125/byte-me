import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.TreeSet;

public class fileManager {
    public static String filePath="src/customerFiles/";

    public static void writeAllCustomers(){
        try (FileOutputStream fos = new FileOutputStream(filePath+"customers.csv", false)) {
            for(customer customer: customer.getCustomers().values()){
                String customerData = customer.getName() + "," + customer.getPassword() + "\n";
                fos.write(customerData.getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error writing customer to file: " + e.getMessage());
        }
    }

    public static void writeCustomer(customer customer) {
        try (FileOutputStream fos = new FileOutputStream(filePath+"customers.csv", true)) {// true = append mode
            String customerData = customer.getName() + "," + customer.getPassword() + "\n";
            fos.write(customerData.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing customer to file: " + e.getMessage());
        }
    }

    public static customer readCustomer(String name) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath+"customers.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                if(username.equals(name)){
                    return customer.getCustomers().get(username);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customers from file: " + e.getMessage());
        }
        return null;
    }

    public static void readCustomersFromFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath+"customers.csv")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                customer customer = new customer(username, password);
            }
        } catch (IOException e) {
            System.out.println("Error reading customers from file: " + e.getMessage());
        }
    }

    public static void writeAllOrders(String file, TreeSet<order> orders){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath+file))) {
            oos.writeObject(orders);
            System.out.println("Order history written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing order history to file: " + e.getMessage());
        }
    }

    public static TreeSet<order> readOrdersFromFile(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath+file))) {
            return (TreeSet<order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error orders from file: " + e.getMessage());
        }
        return null;
    }

    //for current cart
    public static void newOrder(order order) {
        try (FileOutputStream fos = new FileOutputStream(filePath+"currentCart.csv")) {//overwrite
            String orderData = "Customer id: "+order.getCustomerid() + ", Priority: " + order.getPriority() + "\n";
            fos.write(orderData.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing order to file: " + e.getMessage());
        }
    }

    public static void addItem(int orderid,String name,int quantity) {
        try (FileOutputStream fos = new FileOutputStream(filePath+"currentCart.csv", true)) { // true = append mode
            String orderData = orderid + "," + name+","+quantity + "\n";
            fos.write(orderData.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing order to file: " + e.getMessage());
        }
    }

//    public static void updateQuantity(int orderid,String name,int quantity) {
//        try (FileOutputStream fos = new FileOutputStream(filePath+"currentCart.csv", true)) { // true = append mode
//            String orderData = orderid + "," + name+","+quantity + "\n";
//            fos.write(orderData.getBytes());
//        } catch (IOException e) {
//            System.out.println("Error writing order to file: " + e.getMessage());
//        }
//    }
}
