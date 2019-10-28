package org.pazurkiewicz;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.pazurkiewicz.database.mysql.ClientDatabase;
import org.pazurkiewicz.database.mysql.InvoiceDatabase;
import org.pazurkiewicz.database.mysql.InvoiceElementDatabase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

class MainSwing {
    JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JComboBox<String> clientComboBox;
    private JLabel clientLabel;
    private JComboBox<String> invoiceComboBox;
    private JButton addInvoiceButton;
    private JLabel invoiceLabel;
    private JButton deleteClientButton;
    private JButton deleteInvoiceButton;
    private JTextPane resultTextPane;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField netField;
    private JComboBox<String> taxComboBox;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JButton addElementToInvoiceButton;
    private JLabel l5;
    private JTextField dateField;
    private JButton addClientButton;
    private JTextField firmName;
    private JTextField NIP;
    private JTextField city;
    private JTextField street;
    private JTextField postcode;
    private JLabel l6;
    private JLabel l7;
    private JLabel l8;
    private JLabel l9;
    private JLabel l10;
    private InvoiceDatabase invoiceDatabase;
    private ClientDatabase clientDatabase;
    private boolean stop = false;

    private MainSwing() {
        taxComboBox.addItem("23%");
        taxComboBox.addItem("8%");
        taxComboBox.addItem("5%");
        taxComboBox.addItem("0%");
        taxComboBox.addItem("zw");


        resultTextPane.setEditable(false);
        invoiceDatabase = new InvoiceDatabase();
        clientDatabase = new ClientDatabase();
        userList();
        changeInvoiceList();
        changeInvoice();
        clientComboBox.addActionListener(e -> {
            changeInvoiceList();
            changeInvoice();
        });
        invoiceComboBox.addActionListener(e -> changeInvoice());
        deleteClientButton.addActionListener(e -> {
            try {
                clientDatabase.deleteClientByName((String) clientComboBox.getSelectedItem());
                clientComboBox.removeAllItems();
                userList();
            } catch (SQLException en) {
                en.printStackTrace();
            }
        });
        deleteInvoiceButton.addActionListener(e -> {
            if (invoiceComboBox.getSelectedItem() != "Brak faktur") {
                try {
                    String a = (String) invoiceComboBox.getSelectedItem();
                    assert a != null;
                    int b = Integer.parseInt(a);
                    invoiceDatabase.deleteInvoice(b);
                    changeInvoiceList();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        addInvoiceButton.addActionListener(e -> {
            try {
                Client c = clientDatabase.getClientByName((String) clientComboBox.getSelectedItem());
                ArrayList<InvoiceElement> a = new ArrayList<>();
                invoiceDatabase.addInvoice(new Invoice(0, a, c.name, c.NIP, c.postcode, c.streetAndNumber, c.city, 0, 0, new SimpleDateFormat("dd/MM/yyyy").parse(dateField.getText())));
                changeInvoiceList();
                changeInvoice();
            } catch (SQLException | ParseException ex) {
                resultTextPane.setText("Błędne dane");
            }
        });
        addElementToInvoiceButton.addActionListener(e -> {
            try {
                InvoiceElementDatabase.addInvoiceElement(new InvoiceElement(new Item(nameField.getText(), Float.parseFloat(netField.getText()), TaxManager.stringToTax((String) Objects.requireNonNull(taxComboBox.getSelectedItem()))), Float.parseFloat(netField.getText())), Integer.parseInt((String) Objects.requireNonNull(invoiceComboBox.getSelectedItem())));
                changeInvoice();
            } catch (Exception ex) {
                resultTextPane.setText("Błędne dane");
            }
        });
        addClientButton.addActionListener(e -> {
            try {
                Client c = new Client(0, firmName.getText(), NIP.getText(), city.getText(), street.getText(), postcode.getText());
                clientDatabase.addClient(c);
                clientComboBox.addItem(c.name);
            } catch (Exception e1) {
                resultTextPane.setText("Błędne dane");
            }
        });
    }

    private void userList() {
        try {
            ArrayList<Client> clients = clientDatabase.getAllClients();
            for (Client client :
                    clients) {
                clientComboBox.addItem(client.name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeInvoice() {
        if (!stop) {
            if (invoiceComboBox.getSelectedItem() != "Brak faktur") {
                try {
                    String a = (String) invoiceComboBox.getSelectedItem();
                    assert a != null;
                    int b = Integer.parseInt(a);
                    resultTextPane.setText(invoiceDatabase.getInvoiceById(b).toString());
                    invoiceDatabase = new InvoiceDatabase();
                } catch (SQLException ex) {
                    resultTextPane.setText("Pusta faktura - dodaj pozycje");
                }
            } else {
                resultTextPane.setText("Brak danych");
            }
        }
    }

    private void changeInvoiceList() {
        stop = true;
        invoiceComboBox.removeAllItems();
        try {
            ArrayList<Integer> invoices = invoiceDatabase.getAllClientInvoiceName((String) clientComboBox.getSelectedItem());
            for (Integer invoice :
                    invoices) {
                invoiceComboBox.addItem(String.valueOf(invoice));
            }
            if (invoices.size() == 0)
                invoiceComboBox.addItem("Brak faktur");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stop = false;
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(20, 20, 20, 0), -1, -1));
        mainPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        clientComboBox = new JComboBox();
        clientComboBox.setAlignmentY(0.5f);
        clientComboBox.setDoubleBuffered(false);
        clientComboBox.setPopupVisible(false);
        panel1.add(clientComboBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clientLabel = new JLabel();
        clientLabel.setText("Wybierz klienta");
        panel1.add(clientLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        invoiceLabel = new JLabel();
        invoiceLabel.setText("Wybierz fakturę");
        panel1.add(invoiceLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        invoiceComboBox = new JComboBox();
        panel1.add(invoiceComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteClientButton = new JButton();
        deleteClientButton.setText("Usuń klienta");
        panel1.add(deleteClientButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteInvoiceButton = new JButton();
        deleteInvoiceButton.setText("Usuń fakturę");
        panel1.add(deleteInvoiceButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addClientButton = new JButton();
        addClientButton.setForeground(new Color(-4249286));
        addClientButton.setHideActionText(true);
        addClientButton.setText("Dodaj nowego klienta");
        panel6.add(addClientButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        l6 = new JLabel();
        l6.setForeground(new Color(-4249286));
        l6.setText("Nazwa firmy ->");
        panel6.add(l6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        l7 = new JLabel();
        l7.setForeground(new Color(-4249286));
        l7.setText("NIP ->");
        panel6.add(l7, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        l8 = new JLabel();
        l8.setForeground(new Color(-4249286));
        l8.setText("Miasto ->");
        panel6.add(l8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        l9 = new JLabel();
        l9.setForeground(new Color(-4249286));
        l9.setText("Ulica i numer ->");
        panel6.add(l9, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        l10 = new JLabel();
        l10.setForeground(new Color(-4249286));
        l10.setText("Kod pocztowy ->");
        panel6.add(l10, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.add(panel7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addInvoiceButton = new JButton();
        addInvoiceButton.setForeground(new Color(-10768579));
        addInvoiceButton.setText("Dodaj fakturę");
        panel7.add(addInvoiceButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firmName = new JTextField();
        firmName.setText("");
        panel7.add(firmName, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        NIP = new JTextField();
        NIP.setText("");
        panel7.add(NIP, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        city = new JTextField();
        city.setText("");
        panel7.add(city, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        street = new JTextField();
        street.setText("");
        panel7.add(street, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        postcode = new JTextField();
        postcode.setText("");
        panel7.add(postcode, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel8, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(3, 3, new Insets(20, 20, 20, 20), -1, -1));
        panel8.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel10, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        l1 = new JLabel();
        l1.setForeground(new Color(-13482531));
        l1.setText("Nazwa produktu/usługi");
        panel10.add(l1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        nameField = new JTextField();
        nameField.setText("");
        panel10.add(nameField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        l2 = new JLabel();
        l2.setForeground(new Color(-13482531));
        l2.setText("Ilość");
        panel10.add(l2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 7, false));
        quantityField = new JTextField();
        panel10.add(quantityField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        l5 = new JLabel();
        l5.setForeground(new Color(-10768579));
        l5.setText("Data wystawienia");
        panel10.add(l5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        dateField = new JTextField();
        panel10.add(dateField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.add(panel11, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.add(panel12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        l3 = new JLabel();
        l3.setForeground(new Color(-13482531));
        l3.setText("Netto");
        panel12.add(l3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 7, false));
        netField = new JTextField();
        netField.setText("");
        panel12.add(netField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        l4 = new JLabel();
        l4.setForeground(new Color(-13482531));
        l4.setText("Podatek");
        panel12.add(l4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 7, false));
        taxComboBox = new JComboBox();
        panel12.add(taxComboBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel9.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultTextPane = new JTextPane();
        resultTextPane.setText("");
        panel9.add(resultTextPane, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        addElementToInvoiceButton = new JButton();
        addElementToInvoiceButton.setForeground(new Color(-13482531));
        addElementToInvoiceButton.setText("Dodaj wiersz");
        panel9.add(addElementToInvoiceButton, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Faktura Generator");
        frame.add(new MainSwing().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setVisible(true);
    }
}
