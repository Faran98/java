
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Studentlogin extends JFrame implements ActionListener {

    JButton login, back;
    JLabel username, password;
    JTextField tusername;
    JPasswordField tpassword;
    String userValue;
    String passValue;
    
    
    Studentlogin() {

        getContentPane().setForeground(new Color(250, 153, 128));
        setLayout(null);
        setSize(500, 350);
        setLocation(500, 250);
        setVisible(true);
         setTitle("Student Login");
        setResizable(false);

        JLabel heading = new JLabel("Student Login");
        heading.setBounds(180, 20, 300, 40);
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);

        username = new JLabel("UserName");
        username.setBounds(70, 80, 150, 20);
        username.setFont(new Font("Times New Roman", Font.BOLD, 15));
        username.setForeground(new Color(250, 153, 128));
        add(username);

        tusername = new JTextField();
        tusername.setBounds(210, 80, 200, 20);
        add(tusername);

        password = new JLabel("Password");
        password.setBounds(70, 120, 150, 20);
        password.setFont(new Font("Times New Roman", Font.BOLD, 15));
        password.setForeground(new Color(250, 153, 128));
        add(password);

         tpassword = new JPasswordField();
        tpassword.setBounds(210, 120, 200, 20);
        add(tpassword);

         login = new JButton("Log In");
        login.setBounds(270, 180, 120, 30);
        login.setBackground(new Color(250, 153, 128));
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        back = new JButton("Back");
        back.setBounds(270, 220, 120, 30);
        back.setBackground(new Color(250, 153, 128));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
  userValue = tusername.getText();
        passValue = new String(tpassword.getPassword());
        if (ae.getSource() == login) {
            if (userValue.equals("Faran12@gmail.com") && passValue.equals("Faran12")) {
                new Studentportal();
                setVisible(false);

            } else {
                            JOptionPane.showMessageDialog(this, "Plese Enter correct username and passward", "Invalid ", JOptionPane.ERROR_MESSAGE);

            }

        } else if (ae.getSource() == back) {
            new Login();
            setVisible(false);

        }

    }
}



