import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryGUI {
    private JFrame frame;
    private JTextField txtId, txtTitle, txtAuthor, txtYear;
    private JButton btnAdd, btnEdit, btnDelete;
    private JTable bookTable;
    private ArrayList<Book> library;
    private DefaultTableModel tableModel;

    public LibraryGUI() {
        library = new ArrayList<>();

        library.add(new Book("01", "Tra Hoa Nu", "Alexandre Dumas", 1848));
        library.add(new Book("02", "Mat Biec", "Nguyen Nhat Anh", 1990));
        library.add(new Book("03", "Doraemon", "F.F.Fujio", 1969));

        frame = new JFrame("Library Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        // Panel for input fields with GridLayout (labels in one row, fields in another)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 10, 10)); // 2 rows, 4 columns (one for each label and text field)

        // Label row
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(new JLabel("Year:"));

        // Input fields row
        txtId = new JTextField(15);
        txtTitle = new JTextField(15);
        txtAuthor = new JTextField(15);
        txtYear = new JTextField(15);

        inputPanel.add(txtId);
        inputPanel.add(txtTitle);
        inputPanel.add(txtAuthor);
        inputPanel.add(txtYear);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add Book");
        btnEdit = new JButton("Edit Book");
        btnDelete = new JButton("Delete Book");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "Year"}, 0);
        bookTable = new JTable(tableModel);

        bookTable.setFont(new Font("Arial", Font.PLAIN, 14));
        bookTable.setRowHeight(25);
        bookTable.setSelectionBackground(new Color(135, 206, 250));
        bookTable.setSelectionForeground(Color.WHITE);
        bookTable.getTableHeader().setBackground(new Color(30, 144, 255));
        bookTable.getTableHeader().setForeground(Color.WHITE);
        bookTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        bookTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        bookTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        bookTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        bookTable.getColumnModel().getColumn(3).setPreferredWidth(100);

        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        loadTableData();

        // Event listeners for buttons
        btnAdd.addActionListener(e -> addBook());
        btnEdit.addActionListener(e -> editBook());
        btnDelete.addActionListener(e -> deleteBook());

        frame.setVisible(true);
    }

    // Load the existing books into the table
    private void loadTableData() {
        for (Book book : library) {
            tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }

    // Add a new book to the list and table
    private void addBook() {
        String id = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        int year = Integer.parseInt(txtYear.getText());

        Book book = new Book(id, title, author, year);
        library.add(book);
        tableModel.addRow(new Object[]{id, title, author, year});
        clearFields();
    }

    // Edit a selected book's information
    private void editBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = txtId.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            int year = Integer.parseInt(txtYear.getText());

            Book updatedBook = new Book(id, title, author, year);
            library.set(selectedRow, updatedBook);

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(title, selectedRow, 1);
            tableModel.setValueAt(author, selectedRow, 2);
            tableModel.setValueAt(year, selectedRow, 3);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to edit.");
        }
    }

    // Delete a selected book from the list and table
    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0) {
            library.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a book to delete.");
        }
    }

    // Clear the input fields after adding or editing
    private void clearFields() {
        txtId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}
