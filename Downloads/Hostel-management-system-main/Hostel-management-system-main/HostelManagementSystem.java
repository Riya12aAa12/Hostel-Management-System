package hostel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HostelManagementSystem extends JFrame implements ActionListener {

    public HostelManagementSystem() {
        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        setSize(screenWidth, screenHeight);
        setLocation(0, 0);
        setLayout(null);
        
        setTitle("Hostel Management System");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/1.1.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, screenWidth, screenHeight);
        add(image);

        JLabel text = new JLabel("HOSTEL MANAGEMENT SYSTEM");
        text.setBounds(320, 230, 1000, 90);
        text.setForeground(Color.black);
        text.setFont(new Font("Serif", Font.PLAIN, 50));
        image.add(text);
        

       JButton next = new JButton("Get Started");
       next.setBounds(screenWidth - 197, 650, 150, 50);
       next.setBackground(new Color(249, 170, 33)); // Set custom background color
       next.setForeground(Color.WHITE);
       next.setFocusPainted(false); // Remove focus border
       next.addActionListener(this);
       next.setFont(new Font("Arial", Font.BOLD, 18)); // Set custom font style

// Apply hover effect
       next.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        next.setBackground(new Color(102, 255, 102)); // Change background color on hover
    }
    public void mouseExited(java.awt.event.MouseEvent evt) {
        next.setBackground(new Color(249, 170, 33)); // Restore background color when not hovering
    }
});

image.add(next);


        setVisible(true);

        while (true) {
            text.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            text.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new HostelManagementSystem();
    }
}