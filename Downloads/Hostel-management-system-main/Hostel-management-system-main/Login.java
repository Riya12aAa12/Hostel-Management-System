
package hostel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JLabel l1,l2, background,welcomeLabel;
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;
      int welcomeIndex = 0;

    Login(){

        super("Login");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login background.PNG"));
        Image i2 = i1.getImage().getScaledInstance(1400,800,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        background = new JLabel(i3);
        background.setBounds(0,0,1400,800);
        add(background);
         
        JPanel login=new JPanel();
        login.setSize(200,300);
        login.setBackground(new Color(0,0,0,80));
        login.setBounds(690,198,335,363);
        
        l1 = new JLabel("Username");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Tahoma", Font.BOLD, 16));
        l1.setBounds(700,270,100,30);
        background.add(l1);
        
        l2 = new JLabel("Password");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("Tahoma", Font.BOLD, 16));
        l2.setBounds(700,360,100,30);
        background.add(l2);
 
        t1=new JTextField();
        t1.setBounds(805,270,180,30);
        background.add(t1);

        t2=new JPasswordField();
        t2.setBounds(805,360,180,30);
        background.add(t2);

         
        b1 = new JButton("Login");
        b1.setBounds(710,440,120,30);
        b1.setFont(new Font("Tahoma", Font.BOLD, 14));
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        background.add(b1);
        

        b2=new JButton("Cancel");
        b2.setBounds(860,440,120,30);
        b2.setFont(new Font("Tahoma", Font.BOLD, 14));
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        background.add(b2);

        b2.addActionListener(this);
        background.add(login);
        
        
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
        setSize(1400,800);
        setLocation(260,170);
        
        welcomeLabel = new JLabel("WELCOME");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        welcomeLabel.setBounds(570, 30, 400, 60);
        background.add(welcomeLabel);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                String welcomeText = "WELCOME";
                welcomeIndex = (welcomeIndex + 1) % welcomeText.length();
                welcomeLabel.setText(welcomeText.substring(0, welcomeIndex + 1));
            }
        });
        timer.start();

    } 
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
        try{
            conn c1 = new conn();
            String u = t1.getText();
            String v = t2.getText();
            
            String q = "select * from login where username='"+u+"' and password='"+v+"'";
            
            ResultSet rs = c1.s.executeQuery(q); 
            if(rs.next()){ 
                new Dashboard().setVisible(true);
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Invalid login");
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        }else if(ae.getSource()==b2){
            System.exit(0);
        }
    }
    public static void main(String[] arg){
        new Login();
    }
}
