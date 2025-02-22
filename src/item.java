import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;

public class item implements Serializable {
    private String name;
    private int itemid;
    private double price;
    private int quantity;
    private String category;
    private static String[] categoryList ={"meal","snacks","beverages"};
    private ArrayList<String> reviews = new ArrayList<>();
    private static int counter=1;

    public item(String name, double price, int quantity, String category) {
        this.name = name;
        this.itemid = counter++;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public void showReviews() {
        if(reviews.isEmpty()){
            System.out.println("No reviews found");
            return;
        }
        System.out.println("Reviews:");
        for (String review : reviews) {
            System.out.println(review);
        }
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static boolean checkCategory(String category) {
        if(Arrays.asList(categoryList).contains(category.toLowerCase())){
            return true;
        }
        else{
            System.out.println("Invalid category, try again");
            return false;
        }
    }

    public String[] getCategoryList() {
        return categoryList;
    }
}
