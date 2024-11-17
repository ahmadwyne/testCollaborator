/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ahmad
 lets see if we get automated
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AdventureGameInventory extends JFrame {

    private JTextField txtItemName, txtItemPower, txtItemWeight, txtSearch;
    private JComboBox<String> comboItemType;
    private JTable tableInventory;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public AdventureGameInventory() {
        setTitle("Adventure Game Inventory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        getContentPane().setBackground(new Color(60, 63, 65)); // Dark background color
        setLayout(new BorderLayout(10, 10));

        // Custom Font
        Font customFont = new Font("Arial", Font.BOLD, 14);

        System.out.println("I am testing collaborator again");

        // Input Panel with GridBagLayout
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBackground(new Color(43, 47, 51)); // Slightly lighter background for input panel
        panelInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Add New Item"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblItemName = new JLabel("Item Name:");
        lblItemName.setForeground(Color.WHITE);
        lblItemName.setFont(customFont);
        txtItemName = new JTextField(15);
        txtItemName.setFont(customFont);

        JLabel lblItemPower = new JLabel("Item Power:");
        lblItemPower.setForeground(Color.WHITE);
        lblItemPower.setFont(customFont);
        txtItemPower = new JTextField(5);
        txtItemPower.setFont(customFont);

        JLabel lblItemWeight = new JLabel("Item Weight:");
        lblItemWeight.setForeground(Color.WHITE);
        lblItemWeight.setFont(customFont);
        txtItemWeight = new JTextField(5);
        txtItemWeight.setFont(customFont);

        JLabel lblItemType = new JLabel("Item Type:");
        lblItemType.setForeground(Color.WHITE);
        lblItemType.setFont(customFont);
        comboItemType = new JComboBox<>(new String[]{"Weapon", "Potion", "Armor"});
        comboItemType.setFont(customFont);

        // Positioning input fields in the grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInput.add(lblItemName, gbc);
        gbc.gridx = 1;
        panelInput.add(txtItemName, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInput.add(lblItemPower, gbc);
        gbc.gridx = 1;
        panelInput.add(txtItemPower, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelInput.add(lblItemWeight, gbc);
        gbc.gridx = 1;
        panelInput.add(txtItemWeight, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelInput.add(lblItemType, gbc);
        gbc.gridx = 1;
        panelInput.add(comboItemType, gbc);

        // Search Panel with styling
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.setBackground(new Color(43, 47, 51)); // Same as input background
        JLabel lblSearch = new JLabel("Search Inventory:");
        lblSearch.setForeground(Color.WHITE);
        lblSearch.setFont(customFont);
        txtSearch = new JTextField(20);
        txtSearch.setFont(customFont);
        panelSearch.add(lblSearch);
        panelSearch.add(txtSearch);

        // Buttons Panel
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelButtons.setBackground(new Color(43, 47, 51));

        JButton btnAddItem = createCustomButton("Add Item", customFont);
        JButton btnRemoveItem = createCustomButton("Remove Selected Item", customFont);
        JButton btnClearAll = createCustomButton("Clear All", customFont);
        JButton btnEquipRandom = createCustomButton("Equip Random Item", customFont);
        JButton btnCreateCharacter = createCustomButton("Create Powerful Character", customFont);

        panelButtons.add(btnAddItem);
        panelButtons.add(btnRemoveItem);
        panelButtons.add(btnClearAll);
        panelButtons.add(btnEquipRandom);
        panelButtons.add(btnCreateCharacter);

        // Table Panel
        String[] columnNames = {"Item Name", "Type", "Power", "Weight"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableInventory = new JTable(tableModel);
        tableInventory.setFont(new Font("Arial", Font.PLAIN, 14));
        tableInventory.setRowHeight(25);
        tableInventory.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tableInventory.getTableHeader().setBackground(new Color(60, 63, 65));
        tableInventory.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tableInventory);
        scrollPane.setPreferredSize(new Dimension(850, 250));

        // Row sorter for search functionality
        rowSorter = new TableRowSorter<>(tableModel);
        tableInventory.setRowSorter(rowSorter);

        // Adding sections to the layout
        add(panelInput, BorderLayout.NORTH);
        add(panelSearch, BorderLayout.WEST);
        add(panelButtons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Event Handling
        btnAddItem.addActionListener(e -> addItem());
        btnRemoveItem.addActionListener(e -> removeSelectedItem());
        btnClearAll.addActionListener(e -> clearAllFields());
        btnEquipRandom.addActionListener(e -> equipRandomItem());
        btnCreateCharacter.addActionListener(e -> createPowerfulCharacter());

        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterInventory(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterInventory(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterInventory(); }
        });

        setVisible(true);
    }

    // Custom button with styling
    private JButton createCustomButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(new Color(75, 110, 175)); // Blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }

    private void addItem() {
        String itemName = txtItemName.getText();
        String itemPowerStr = txtItemPower.getText();
        String itemWeightStr = txtItemWeight.getText();
        String itemType = (String) comboItemType.getSelectedItem();

        if (itemName.isEmpty() || itemPowerStr.isEmpty() || itemWeightStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int itemPower = Integer.parseInt(itemPowerStr);
            double itemWeight = Double.parseDouble(itemWeightStr);
            tableModel.addRow(new Object[]{itemName, itemType, itemPower, itemWeight});
            JOptionPane.showMessageDialog(this, "Item added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Power and Weight must be numeric!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSelectedItem() {
        int selectedRow = tableInventory.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            tableModel.removeRow(tableInventory.convertRowIndexToModel(selectedRow));
            JOptionPane.showMessageDialog(this, "Item removed successfully!");
        }
    }

    private void clearAllFields() {
        txtItemName.setText("");
        txtItemPower.setText("");
        txtItemWeight.setText("");
        comboItemType.setSelectedIndex(0);
    }

    private void equipRandomItem() {
        int rowCount = tableModel.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "No items to equip!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Random rand = new Random();
            int randomIndex = rand.nextInt(rowCount);
            String itemName = (String) tableModel.getValueAt(randomIndex, 0);
            JOptionPane.showMessageDialog(this, "You have equipped " + itemName + "!");
        }
    }

    private void createPowerfulCharacter() {
        Item bestWeapon = null;
        Item bestArmor = null;
        Item bestPotion = null;

        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String itemName = (String) tableModel.getValueAt(i, 0);
            String itemType = (String) tableModel.getValueAt(i, 1);
            int itemPower = (int) tableModel.getValueAt(i, 2);
            double itemWeight = (double) tableModel.getValueAt(i, 3);

            Item item = new Item(itemName, itemType, itemPower, itemWeight);

            switch (itemType) {
                case "Weapon":
                    if (bestWeapon == null || itemPower > bestWeapon.getPower()) {
                        bestWeapon = item;
                    }
                    break;
                case "Armor":
                    if (bestArmor == null || itemPower > bestArmor.getPower()) {
                        bestArmor = item;
                    }
                    break;
                case "Potion":
                    if (bestPotion == null || itemPower > bestPotion.getPower()) {
                        bestPotion = item;
                    }
                    break;
            }
        }

        if (bestWeapon != null && bestArmor != null && bestPotion != null) {
            JOptionPane.showMessageDialog(this, "Your character is equipped with " +
                    bestWeapon.getName() + " (Weapon), " +
                    bestArmor.getName() + " (Armor), and " +
                    bestPotion.getName() + " (Potion)!");
        } else {
            JOptionPane.showMessageDialog(this, "Unable to create a fully equipped character!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadItemDetailsForEditing() {
        int selectedRow = tableInventory.getSelectedRow();
        if (selectedRow == -1) return;

        selectedRow = tableInventory.convertRowIndexToModel(selectedRow); // Convert row index to model
        String itemName = (String) tableModel.getValueAt(selectedRow, 0);
        String itemType = (String) tableModel.getValueAt(selectedRow, 1);
        int itemPower = (int) tableModel.getValueAt(selectedRow, 2);
        double itemWeight = (double) tableModel.getValueAt(selectedRow, 3);

        txtItemName.setText(itemName);
        comboItemType.setSelectedItem(itemType);
        txtItemPower.setText(String.valueOf(itemPower));
        txtItemWeight.setText(String.valueOf(itemWeight));

        tableModel.removeRow(selectedRow);
    }

    private void filterInventory() {
        String searchText = txtSearch.getText();
        if (searchText.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0, 1));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdventureGameInventory::new);
    }
    
    class Item {
        private String name;
        private String type;
        private int power;
        private double weight;

        public Item(String name, String type, int power, double weight) {
            this.name = name;
            this.type = type;
            this.power = power;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getPower() {
            return power;
        }

        public double getWeight() {
            return weight;
        }
    }
}
