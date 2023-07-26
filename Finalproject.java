
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author User
 */
class Login extends JFrame implements ActionListener {

    JButton Teacher, student;


    Login() {
        getContentPane().setBackground(Color.CYAN); //pooran panel select kar ke color change kare ga
        setSize(700, 500);// ye size set karne ke liye hota lenght and widhth
        setLayout(null);//deafult layout ko null karna prhta take apni marzi ka layout daale
        setVisible(true); // frame by default hidden hota so set true dekhne ke liye
        JLabel Logn = new JLabel("LOGIN PAGE"); //label dalne ke liye
        Logn.setBounds(290, 30, 300, 50);//boundarys rakhne ke liye
        Logn.setFont(new Font("SansSerif", Font.BOLD, 20));
        Logn.setForeground(new Color(250, 153, 128));//font ke color ke liye
        setLocation(410, 200);
        setTitle("Login Page");
        setResizable(false);
        add(Logn);//for adding

        JLabel heading = new JLabel("WHICH USER PORTAL YOU WANT TO USE");
        heading.setBounds(160, 130, 500, 50);//boundarys rakhne ke liye
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);//for adding

        Teacher = new JButton("Teacher");
        Teacher.setBounds(260, 190, 200, 50);
        Teacher.setBackground(Color.green);
        Teacher.setForeground(new Color(250, 153, 128));
        Teacher.addActionListener( this);
        add(Teacher);

        student = new JButton("Student");
        student.setBounds(260, 280, 200, 50);
        student.setBackground(Color.green);
        student.setForeground(new Color(250, 153, 128));
        student.addActionListener(this);
        add(student);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Teacher) {
            new Teacherlogin();
            setVisible(false);
        } else if (ae.getSource() == student) {
            new Studentlogin();
            setVisible(false);

        }

    }
}

public class Finalproject {

    public static void main(String[] args) {
        new Login();

    }
}

