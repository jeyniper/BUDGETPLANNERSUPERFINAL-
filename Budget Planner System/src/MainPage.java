import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainPage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    MainPage() {
       
        JLabel imageLabel = new JLabel();
        ImageIcon bgImage = new ImageIcon("Homepage.png");
        setSize(1300, 750);
        imageLabel.setIcon(bgImage);
        getContentPane().add(imageLabel);

        // Networth button
        JButton networthButton = createButton("networthb.png", 470, 360, 400, 65);
        networthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                NetworthStatement frame = new NetworthStatement();
                frame.setVisible(true);
                dispose();
            }
        });
        imageLabel.add(networthButton);

        // Savings button
        JButton savingsButton = createButton("savingsb.png", 470, 250, 400, 65);
        savingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Savings frame = new Savings();
                frame.setVisible(true);
                dispose();
            }
        });
        imageLabel.add(savingsButton);

        // Cashflow button
        JButton cashflowButton = createButton("cashflowb.png", 470, 480, 400, 65);
        cashflowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CashFlow cashFlowFrame = new CashFlow();
                cashFlowFrame.setVisible(true);
                dispose();
            }
        });
        imageLabel.add(cashflowButton);

        // System layout
        getContentPane().setLayout(new FlowLayout());
        setTitle("Budget Planner System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 750);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(null);
    }

    private JButton createButton(String imagePath, int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(imagePath));
        button.setBounds(x, y, width, height);
        return button;
    }
}

