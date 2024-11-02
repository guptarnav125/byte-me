import java.util.Comparator;

public class priceComparator implements Comparator<item> {

    @Override
    public int compare(item o1, item o2) {
        int i = Double.compare(o1.getPrice(), o2.getPrice());
        if (i != 0) {
            return i;
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
