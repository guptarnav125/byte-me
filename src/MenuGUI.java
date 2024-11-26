import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {
    private JTable menuTable;
    private DefaultTableModel menuModel;
    private JButton viewOrdersButton;
    private JPanel mainPanel;

    public MenuGUI() {
        setTitle("Byte Me! Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        String[] columns = {"Item ID", "Name", "Price", "Availability", "Category"};
        menuModel = new DefaultTableModel(columns, 0);
        menuTable = new JTable(menuModel);
        JScrollPane scrollPane = new JScrollPane(menuTable);

        JPanel buttonPanel = new JPanel();
        viewOrdersButton = new JButton("View Orders");
        viewOrdersButton.addActionListener(e -> switchToOrders());
        buttonPanel.add(viewOrdersButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        refreshMenu();
    }

    private void refreshMenu() {
        menuModel.setRowCount(0);

        for (item x : menu.getMenu().values()) {
            Object[] row = {
                    x.getItemid(),
                    x.getName(),
                    x.getPrice(),
                    x.getQuantity(),
                    x.getCategory()
            };
            menuModel.addRow(row);
        }
    }

    private void switchToOrders() {
        OrdersGUI ordersGUI = new OrdersGUI(this);
        ordersGUI.setVisible(true);
        this.setVisible(false);
    }
}
