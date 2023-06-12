import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.SystemColor;
import java.io.File;

public class Savings extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Savings frame = new Savings();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JPanel contentPane;
    private final JLabel lblNewLabel = new JLabel();
    private JTextField name;
    private JTextField contact;
    private JTextField course;
    private DefaultTableModel model;
    private JTable table;

    String SavingsDataFilePath = "savings_data.txt";
    File file = new File(SavingsDataFilePath);

    Savings() {
        setResizable(false);
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 750);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 10, 1300, 750);
        contentPane.add(scrollPane);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setBackground(new Color(60, 179, 113));
        panel.setLayout(null);
        lblNewLabel.setBounds(143, 82, 70, 14);

        lblNewLabel.setForeground(SystemColor.text);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);

        JLabel lblNewLabel = new JLabel("Date");
        lblNewLabel.setBounds(143, 170, 70, 14);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(143, 240, 70, 14);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Monthly Salary");
        lblNewLabel_2.setBounds(129, 300, 100, 14);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.WHITE);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Projected Expenses");
        lblNewLabel_3.setBounds(91, 360, 181, 14);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setForeground(Color.WHITE);
        panel.add(lblNewLabel_3);

        JTextField id = new JTextField();
        id.setBounds(34, 200, 307, 20);
        panel.add(id);
        id.setColumns(10);

        name = new JTextField();
        name.setBounds(34, 260, 307, 20);
        panel.add(name);
        name.setColumns(10);

        contact = new JTextField();
        contact.setBounds(34, 320, 307, 20);
        panel.add(contact);
        contact.setColumns(10);

        course = new JTextField();
        course.setBounds(34, 380, 307, 20);
        panel.add(course);
        course.setColumns(10);

        JButton btnNewButton = new JButton("ADD");
        btnNewButton.setBounds(34, 420, 126, 29);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (id.getText().equals("") || name.getText().equals("") || course.getText().equals("")
                        || contact.getText().equals("") || course.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
                } else {
                    String[] rowData = new String[4];
                    rowData[0] = id.getText();
                    rowData[1] = name.getText();
                    rowData[2] = contact.getText();
                    rowData[3] = course.getText();

                    model.addRow(rowData);

                    id.setText("");
                    name.setText("");
                    contact.setText("");
                    course.setText("");
                    JOptionPane.showMessageDialog(null, "Saved Successfully");
                }
            }
        });
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("UPDATE");
        btnNewButton_1.setBounds(215, 420, 126, 29);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    table.setValueAt(id.getText(), selectedRow, 0);
                    table.setValueAt(name.getText(), selectedRow, 1);
                    table.setValueAt(contact.getText(), selectedRow, 2);
                    table.setValueAt(course.getText(), selectedRow, 3);
                }
            }
        });
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("DELETE");
        btnNewButton_2.setBounds(34, 460, 126, 35);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a Row First");
                }
            }
        });
        panel.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("CLEAR");
        btnNewButton_3.setBounds(215, 460, 126, 35);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                name.setText("");
                contact.setText("");
                course.setText("");
            }
        });
        panel.add(btnNewButton_3);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(364, 90, 880, 570);
        panel.add(scrollPane_1);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    id.setText(model.getValueAt(selectedRow, 0).toString());
                    name.setText(model.getValueAt(selectedRow, 1).toString());
                    contact.setText(model.getValueAt(selectedRow, 2).toString());
                    course.setText(model.getValueAt(selectedRow, 3).toString());
                }
            }
        });
        table.setBackground(new Color(60, 179, 113));
        model = new DefaultTableModel();
        Object[] column = { "Date", "Name", "Monthly Salary", "Projected Expenses" };
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane_1.setViewportView(table);

        ImageIcon imageIcon = new ImageIcon("lol.png");
        JLabel lblNewLabel_4 = new JLabel(imageIcon);
        lblNewLabel_4.setBounds(-1, 90, 350, 71);
        panel.add(lblNewLabel_4);

        ImageIcon imageIcon2 = new ImageIcon("dd.png");
        JLabel lblNewLabel_5 = new JLabel(imageIcon2);
        lblNewLabel_5.setBounds(-100, 0, 1500, 50);
        panel.add(lblNewLabel_5);

        JButton btnSave = new JButton("SAVE");
        btnSave.setBounds(129, 530, 130, 35);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() == 15) {
                    JOptionPane.showMessageDialog(null, "Invalid");
                    dispose();
                } else {
                	 loadDataFromFile();
                }
            }
        });
        panel.add(btnSave);

        JButton btnNewButton_4 = new JButton("BACK");
        btnNewButton_4.setBounds(40, 640, 130, 35);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new MainPage();
                dispose();
            }
        });
        panel.add(btnNewButton_4);
        // Rest of your code...
    }

    protected void btnSave() {
        // TODO Auto-generated method stub
    }

    private void loadDataFromFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SavingsDataFilePath));

            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder rowData = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowData.append(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) {
                        rowData.append(", ");
                    }
                }
                writer.write(rowData.toString() + "\n");
            }

            writer.close();

            JOptionPane.showMessageDialog(null, "Data saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
