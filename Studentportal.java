/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.String;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author User
 */
public class Studentportal extends JFrame implements ActionListener {

    JButton attemptQuizButton, resultButton, backButton;

    public Studentportal() {
        getContentPane().setForeground(new Color(250, 153, 128));
        setLayout(null);
        setSize(1200, 750);
        setLocation(150, 70);
        setVisible(true);
         setTitle("Student Portal");
        setResizable(false);

        JLabel heading = new JLabel("WELCOME TO STUDENT PORTAL");
        heading.setBounds(450, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);

        attemptQuizButton = new JButton("Attempt Quiz");
        attemptQuizButton.setBounds(500, 250, 200, 60);
        attemptQuizButton.setBackground(new Color(250, 153, 128));
        attemptQuizButton.setForeground(Color.WHITE);
        attemptQuizButton.addActionListener(this);
        add(attemptQuizButton);

        resultButton = new JButton("Result");
        resultButton.setBounds(500, 350, 200, 60);
        resultButton.setBackground(new Color(250, 153, 128));
        resultButton.setForeground(Color.WHITE);
        resultButton.addActionListener(this);
        add(resultButton);

        backButton = new JButton("Back");
        backButton.setBounds(500, 450, 200, 60);
        backButton.setBackground(new Color(250, 153, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == attemptQuizButton) {
            AttemptQuiz attemptQuiz = new AttemptQuiz("attempt_quiz.txt");
            setVisible(false);
        } else if (ae.getSource() == resultButton) {
         new Result();
        } else if (ae.getSource() == backButton) {
            new Login();
            setVisible(false);
        }
    }
}