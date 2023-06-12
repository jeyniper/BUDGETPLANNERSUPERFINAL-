import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NetworthStatement extends JFrame {

    private static final String SavingsDataFilePath = "savings_data.txt";
    private JTable table;

    NetworthStatement() {
        setResizable(false);
        setTitle("Networth Statement");
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.textHighlight);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_5 = createImageLabel("dd.png", 0, 0, 1500, 50);
        panel.add(lblNewLabel_5);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(83, 200, 1100, 422);
        panel.setBackground(new Color(60, 179, 113));
        panel.add(scrollPane);

        JButton btnNewButton = createButton("BACK", 600, 650, 89, 32);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
            }
        });
        panel.add(btnNewButton);

        JLabel lblNewLabel_4 = createImageLabel("nnn.png", -10, 90, 600, 71);
        panel.add(lblNewLabel_4);
        displaySavedRecords();
    }

    private void displaySavedRecords() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Name");
        model.addColumn("Monthly Salary");
        model.addColumn("Projected Expenses");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(SavingsDataFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                model.addRow(rowData);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NetworthStatement frame = new NetworthStatement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JLabel createImageLabel(String imagePath, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        label.setIcon(imageIcon);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }
}
