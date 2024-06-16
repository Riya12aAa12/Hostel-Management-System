package hostel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }

    int welcomeIndex = 0;

    public Dashboard() {
        super("HOSTEL MANAGEMENT SYSTEM");

       setForeground(Color.CYAN);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/2.2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1910, 1090, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(0, 0, 1910, 1090);

        ImageIcon q1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.3.jpg"));
        Image q2 = q1.getImage().getScaledInstance(1910, 1090, Image.SCALE_DEFAULT);
        ImageIcon q3 = new ImageIcon(q2);
        JLabel NewLabel2 = new JLabel(q3);
        NewLabel2.setBounds(0, 1090, 1910, 1090);
        
        ImageIcon w1 = new ImageIcon(ClassLoader.getSystemResource("icons/4.4.jpg"));
        Image w2 = w1.getImage().getScaledInstance(1910, 1090, Image.SCALE_DEFAULT);
        ImageIcon w3 = new ImageIcon(w2);
        JLabel NewLabel3 = new JLabel(w3);
        NewLabel3.setBounds(0, 2180, 1910, 1090);
        
        ImageIcon e1 = new ImageIcon(ClassLoader.getSystemResource("icons/5.5.jpg"));
        Image e2 = e1.getImage().getScaledInstance(1910, 1090, Image.SCALE_DEFAULT);
        ImageIcon e3 = new ImageIcon(e2);
        JLabel NewLabel4 = new JLabel(e3);
        NewLabel4.setBounds(0, 3270, 1910, 1090);

        // Create a JPanel to hold both images
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(1950, 4360));
        contentPanel.add(NewLabel);
        contentPanel.add(NewLabel2);
        contentPanel.add(NewLabel3);
         contentPanel.add(NewLabel4);

        // Create a JScrollPane and add the contentPanel to it
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(0, 0, 1910, 1000);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);


        JLabel HostelManagementSystem = new JLabel("THE HAPPY PLACE");
        HostelManagementSystem.setForeground(Color.WHITE);
        HostelManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 46));
        HostelManagementSystem.setBounds(130, 60, 1000, 85);
        NewLabel.add(HostelManagementSystem);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the welcomeLabel text to show the next letter in the animation
                String welcomeText = "THE HAPPY PLACE";
                welcomeIndex = (welcomeIndex + 1) % welcomeText.length();
                HostelManagementSystem.setText(welcomeText.substring(0, welcomeIndex + 1));
            }
        });
        timer.start();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu HostelSystem = new JMenu("HOSTEL MANAGEMENT");
        HostelSystem.setForeground(Color.BLUE);
        menuBar.add(HostelSystem);

        JMenuItem HostelSystemDetails = new JMenuItem("RECEPTION");
        HostelSystem.add(HostelSystemDetails);

        JMenu HostelSystemHello = new JMenu("ADMIN");
      HostelSystemHello.setForeground(Color.RED);
        menuBar.add(HostelSystemHello);

       JMenuItem HostelSystemDetailshello1 = new JMenuItem("Log Out");
       HostelSystemHello.add(HostelSystemDetailshello1);

       HostelSystemDetailshello1.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
               try {
                   new Login().setVisible(true);
                   setVisible(false);
               } catch (Exception e) {
               }
           }
        });



        HostelSystemDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try{
				RoomAllocationGUI custom = new RoomAllocationGUI();
				custom.setVisible(true);
//                                setVisible(true);
			}catch(Exception e1){
				e1.printStackTrace();
			}
			}
		});


        setSize(1950, 1090);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }
}
