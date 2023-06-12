import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CashFlow extends JFrame {

    private static double totalIncome;
    private static double totalExpenses;

    private JLabel incomeLabel;
    private JTextField incomeTextField;
    private JButton addExpenseButton;
    private JButton viewSummaryButton;
    private JTable expenseTable;
    private DefaultTableModel tableModel;

    CashFlow() {

        setTitle("Cash Flow System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);

        JLabel imageLabel = createImageLabel("dd.png", -10, 0, 1500, 50);
        JLabel secondaryImageLabel = createImageLabel("CFP.png", -1, 90, 700, 71);
        JLabel thirdImageLabel = createImageLabel("cashfloww.png", 100, 900, 2000, 200);

        incomeLabel = createLabel("Please enter your income for the month :", 83, 200, 400, 20, Color.WHITE, Font.BOLD | Font.ITALIC, 18);
        incomeTextField = createTextField(10, 450, 200, 450, 30);

        addExpenseButton = createButton("Add Expense", 450, 245, 220, 30);
        viewSummaryButton = createButton("View Summary", 680, 245, 220, 30);
        JButton backButton = createButton("Back", 550, 620, 200, 30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
            }
        });

        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double income = Double.parseDouble(incomeTextField.getText());
                totalIncome += income;

                String expenseString = JOptionPane.showInputDialog("Enter the expense amount:");
                double expense = Double.parseDouble(expenseString);
                totalExpenses += expense;

                String category = JOptionPane.showInputDialog("Enter the expense category:");

                double savings = totalIncome - totalExpenses;
                tableModel.addRow(new Object[]{category, expense, totalIncome, savings});

                System.out.println("Expense added successfully!");
            }
        });

        viewSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double savings = totalIncome - totalExpenses;

                JOptionPane.showMessageDialog(null,
                        "Total Income: \u20B1 " + totalIncome +
                        "\nTotal Expenses: \u20B1 " + totalExpenses +
                        "\nSavings: \u20B1 " + savings,
                        "Financial Summary",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Category");
        tableModel.addColumn("Expense");
        tableModel.addColumn("Total Income");
        tableModel.addColumn("Savings");
        expenseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(expenseTable);
        tableScrollPane.setBounds(83, 300, 1100, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(60, 179, 113));
        mainPanel.add(imageLabel);
        mainPanel.add(secondaryImageLabel);
        mainPanel.add(thirdImageLabel);
        mainPanel.add(incomeLabel);
        mainPanel.add(incomeTextField);
        mainPanel.add(addExpenseButton);
        mainPanel.add(viewSummaryButton);
        mainPanel.add(backButton);
        mainPanel.add(tableScrollPane);

        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CashFlow cashFlow = new CashFlow();
                cashFlow.setVisible(true);
            }
        });
    }

    private JLabel createImageLabel(String imagePath, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon(imagePath);
        label.setIcon(image);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, Color color, int fontStyle, int fontSize) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(color);
        Font labelFont = label.getFont();
        label.setFont(labelFont.deriveFont(fontStyle, fontSize));
        return label;
    }

    private JTextField createTextField(int columns, int x, int y, int width, int height) {
        JTextField textField = new JTextField(columns);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }
}
