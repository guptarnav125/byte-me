import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersGUI extends JFrame {
    private JTable ordersTable;
    private DefaultTableModel ordersModel;
    private JButton backButton;
    private MenuGUI menuGUI;

    public OrdersGUI(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
        setTitle("Pending Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create orders table
        String[] columns = {"Order ID", "Customer ID", "Status", "Total"};
        ordersModel = new DefaultTableModel(columns, 0);
        ordersTable = new JTable(ordersModel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);

        // Create back button
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> switchToMenu());
        buttonPanel.add(backButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshOrders();
    }

    private void refreshOrders() {
        ordersModel.setRowCount(0);
        // Add pending orders from the orderHistory class
        for (order o : orderHistory.getOrderQueue()) {
            Object[] row = {
                    o.getOrderid(),
                    o.getCustomerid(),
                    o.getStatus(),
                    o.getTotal()
            };
            ordersModel.addRow(row);
        }
    }

    private void switchToMenu() {
        menuGUI.setVisible(true);
        this.dispose();
    }
}
