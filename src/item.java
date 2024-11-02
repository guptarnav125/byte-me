public class item {
    private String name;
    private int itemid;
    private double price;
    private int quantity;
    private static int counter=1;

    public item(String name, double price, int quantity) {
        this.name = name;
        this.itemid = counter++;
        this.price = price;
        this.quantity = quantity;
    }

    public String toString() {
        return "Name: "+this.name + " Item ID: " + this.itemid + " Price: " + this.price + " Availability: " + this.quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemid() {
        return itemid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
