import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SystemTests {
    private customer testCustomer;
    private admin testAdmin;

    @Before
    public void setUp() {

        testCustomer = new customer("testUser", "password123");
        fileManager.writeCustomer(testCustomer);
        testAdmin = new admin("adminUser", "adminPass123");

        menu.addItem(new item("Pizza",80,10,"Meal"));
        menu.addItem(new item("Burger",40,0,"Snacks")); //Out of stock
    }

    @Test
    public void testOrderOutOfStockItem() {
        order testOrder = new order(testCustomer.getUserid(), 2);

        // Try to order out of stock item (Item 2)
        testOrder.addItem(2, 1);

        // Verify order was not added
        assertTrue(testOrder.isEmpty());
    }

    @Test
    public void testInvalidLogin() {
        // Test invalid username
        assertNull(customer.login("wrongUser", "password123"));

        // Test invalid password
        assertNull(customer.login("testUser", "wrongPassword"));
    }

    @Test
    public void testValidLogin() {
        // Test valid customer login
        assertNotNull(customer.login("testUser", "password123"));

        // Test valid admin login
        assertNotNull(admin.login("adminUser", "adminPass123"));
    }
}
