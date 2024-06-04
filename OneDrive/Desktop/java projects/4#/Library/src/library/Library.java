package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Library {
    public static Book[] books = new Book[100];
    public static int bookCount = 0;

    public static Member[] members = new Member[100];
    public static int memberCount = 0;

    public static Transaction[] transactions = new Transaction[100];
    public static int transactionCount = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Library::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton addBookButton = new JButton("Add a new Book");
        addBookButton.addActionListener(e -> addBookDialog());

        JButton addMemberButton = new JButton("Add a new Member");
        addMemberButton.addActionListener(e -> addMemberDialog());

        JButton borrowBookButton = new JButton("Borrow a Book");
        borrowBookButton.addActionListener(e -> borrowBookDialog());

        JButton returnBookButton = new JButton("Return a Book");
        returnBookButton.addActionListener(e -> returnBookDialog());

        JButton viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.addActionListener(e -> viewAllBooksDialog());

        JButton viewAllMembersButton = new JButton("View All Members");
        viewAllMembersButton.addActionListener(e -> viewAllMembersDialog());

        JButton viewAllTransactionsButton = new JButton("View All Transactions");
        viewAllTransactionsButton.addActionListener(e -> viewAllTransactionsDialog());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        mainPanel.add(addBookButton);
        mainPanel.add(addMemberButton);
        mainPanel.add(borrowBookButton);
        mainPanel.add(returnBookButton);
        mainPanel.add(viewAllBooksButton);
        mainPanel.add(viewAllMembersButton);
        mainPanel.add(viewAllTransactionsButton);
        mainPanel.add(exitButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void addBookDialog() {
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField genreField = new JTextField();

        Object[] message = {
            "Enter Book ID:", idField,
            "Enter Book Title:", titleField,
            "Enter Book Author:", authorField,
            "Enter Quantity:", quantityField,
            "Enter Genre:", genreField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int bookId = Integer.parseInt(idField.getText());
            int bookQuantity = Integer.parseInt(quantityField.getText());
            books[bookCount++] = new Book(bookId, titleField.getText(), authorField.getText(), bookQuantity, genreField.getText());
            JOptionPane.showMessageDialog(null, "Book added successfully.");
        }
    }

    private static void addMemberDialog() {
    JTextField idField = new JTextField();
    JTextField nameField = new JTextField();

    Object[] message = {
        "Enter Member ID:", idField,
        "Enter Member Name:", nameField
    };

    int option = JOptionPane.showConfirmDialog(null, message, "Add Member", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        int memberId = Integer.parseInt(idField.getText());
        members[memberCount++] = new Member(memberId, nameField.getText());
        JOptionPane.showMessageDialog(null, "Member added successfully.");
    }
}

    private static void borrowBookDialog() {
        JTextField memberIdField = new JTextField();
        JTextField bookIdField = new JTextField();

        Object[] message = {
            "Enter Member ID:", memberIdField,
            "Enter Book ID:", bookIdField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Borrow Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int memberId = Integer.parseInt(memberIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());

            int memberIndex = searchMemberById(memberId);
            int bookIndex = searchBookById(bookId);

            if (memberIndex != -1 && bookIndex != -1 && books[bookIndex].getQuantity() > 0) {
                transactions[transactionCount++] = new Transaction(memberId, bookId, "borrowed");
                books[bookIndex].borrowBook();
                JOptionPane.showMessageDialog(null, "Book borrowed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Member ID or Book ID, or Book not available.");
            }
        }
    }

    private static void returnBookDialog() {
        JTextField memberIdField = new JTextField();
        JTextField bookIdField = new JTextField();

        Object[] message = {
            "Enter Member ID:", memberIdField,
            "Enter Book ID:", bookIdField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Return Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int memberId = Integer.parseInt(memberIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());

            int memberIndex = searchMemberById(memberId);
            int bookIndex = searchBookById(bookId);

            if (memberIndex != -1 && bookIndex != -1) {
                transactions[transactionCount++] = new Transaction(memberId, bookId, "returned");
                books[bookIndex].returnBook();
                JOptionPane.showMessageDialog(null, "Book returned successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Member ID or Book ID.");
            }
        }
    }

    private static void viewAllBooksDialog() {
        String[] columnNames = {"ID", "Title", "Author", "Quantity", "Genre"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < bookCount; i++) {
            Object[] row = {
                books[i].getId(),
                books[i].getTitle(),
                books[i].getAuthor(),
                books[i].getQuantity(),
                books[i].getGenre()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "All Books", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void viewAllMembersDialog() {
    String[] columnNames = {"ID", "Name"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    for (int i = 0; i < memberCount; i++) {
        Object[] row = {
            members[i].getId(),
            members[i].getName()
        };
        model.addRow(row);
    }

    JTable table = new JTable(model);
    JOptionPane.showMessageDialog(null, new JScrollPane(table), "All Members", JOptionPane.INFORMATION_MESSAGE);
}

    private static void viewAllTransactionsDialog() {
        String[] columnNames = {"Member ID", "Book ID", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < transactionCount; i++) {
            Object[] row = {
                transactions[i].getMemberId(),
                transactions[i].getBookId(),
                transactions[i].getStatus()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "All Transactions", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int searchBookById(int bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == bookId) {
                return i;
            }
        }
        return -1;
    }

    public static int searchMemberById(int memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId() == memberId) {
                return i;
            }
        }
        return -1;
    }
}
