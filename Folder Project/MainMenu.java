import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // kita akan pakai posisi manual

        // Background color
        getContentPane().setBackground(new Color(135, 206, 250)); // Sky Blue

        // Title
        JLabel titleLabel = new JLabel("FLAPPY BIRD");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(80, 100, 300, 50);
        add(titleLabel);

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(255, 215, 0)); // Gold
        startButton.setBounds(120, 250, 150, 50);
        add(startButton);

        // Author
        JLabel credit = new JLabel("by Muhammad Alfi Fariz");
        credit.setFont(new Font("Arial", Font.PLAIN, 14));
        credit.setForeground(Color.BLACK);
        credit.setBounds(150, 520, 200, 20);
        add(credit);

        // Action untuk tombol
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new App(); // buka game
                dispose(); // tutup main menu
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}