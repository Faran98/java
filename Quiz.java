
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Quiz extends JFrame implements ActionListener {

    JTextField questionField, optionAField, optionBField, optionCField, optionDField, answerField;
    JButton submitButton, backButton, addQuestionButton;
    StringBuilder selectedCourse;
    String fileName;

    public Quiz(StringBuilder selectedCourse, String fileName) {
        this.selectedCourse = selectedCourse;
        this.fileName = fileName;

        getContentPane().setForeground(new Color(250, 153, 128));
        setLayout(null);
        setSize(1200, 650);
        setLocation(150, 70);
        setVisible(true);
         setTitle("Create Quiz");
        setResizable(false);

        JLabel heading = new JLabel("CREATE QUIZ OF " + selectedCourse);
        heading.setBounds(500, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);

        JLabel questionLabel = new JLabel("Question:");
        questionLabel.setBounds(30, 130, 100, 30);
        add(questionLabel);

        questionField = new JTextField();
        questionField.setBounds(140, 130, 400, 30);
        add(questionField);

        JLabel optionALabel = new JLabel("Option A:");
        optionALabel.setBounds(30, 180, 100, 30);
        add(optionALabel);

        optionAField = new JTextField();
        optionAField.setBounds(140, 180, 400, 30);
        add(optionAField);

        JLabel optionBLabel = new JLabel("Option B:");
        optionBLabel.setBounds(30, 230, 100, 30);
        add(optionBLabel);

        optionBField = new JTextField();
        optionBField.setBounds(140, 230, 400, 30);
        add(optionBField);

        JLabel optionCLabel = new JLabel("Option C:");
        optionCLabel.setBounds(30, 280, 100, 30);
        add(optionCLabel);

        optionCField = new JTextField();
        optionCField.setBounds(140, 280, 400, 30);
        add(optionCField);

        JLabel optionDLabel = new JLabel("Option D:");
        optionDLabel.setBounds(30, 330, 100, 30);
        add(optionDLabel);

        optionDField = new JTextField();
        optionDField.setBounds(140, 330, 400, 30);
        add(optionDField);

        JLabel answerLabel = new JLabel("Answer:");
        answerLabel.setBounds(30, 380, 100, 30);
        add(answerLabel);

        answerField = new JTextField();
        answerField.setBounds(140, 380, 400, 30);
        add(answerField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(240, 430, 100, 30);
        submitButton.setBackground(new Color(250, 153, 128));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        add(submitButton);

        addQuestionButton = new JButton("Add Question");
        addQuestionButton.setBounds(360, 430, 130, 30);
        addQuestionButton.setBackground(new Color(250, 153, 128));
        addQuestionButton.setForeground(Color.WHITE);
        addQuestionButton.addActionListener(this);
        add(addQuestionButton);

        backButton = new JButton("Back");
        backButton.setBounds(500, 430, 100, 30);
        backButton.setBackground(new Color(250, 153, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        getContentPane().setBackground(Color.WHITE);
       
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            try {
                FileWriter fw = new FileWriter(fileName, true);
                PrintWriter pw = new PrintWriter(fw);

                String question = questionField.getText();
                String optionA = optionAField.getText();
                String optionB = optionBField.getText();
                String optionC = optionCField.getText();
                String optionD = optionDField.getText();
                String answer = answerField.getText();

                String questionData ="Quesstion : " + question + "\n" + optionA + "\n" + optionB + "\n" + optionC + "\n"+ optionD + "\n" + answer;
                pw.println(questionData);
                pw.close();
                fw.close();
                JOptionPane.showMessageDialog(this, "Question Submit successfully");
                setVisible(false);
                new Teacherportal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == addQuestionButton) {
            try {
                FileWriter fw = new FileWriter(fileName, true);
                PrintWriter pw = new PrintWriter(fw);

                String question = questionField.getText();
                String optionA = optionAField.getText();
                String optionB = optionBField.getText();
                String optionC = optionCField.getText();
                String optionD = optionDField.getText();
                String answer = answerField.getText();

                String questionData ="Quesstion : " + question + "\n" + optionA + "\n" + optionB + "\n" + optionC + "\n"+ optionD + "\n" + answer;
                pw.println(questionData);
                pw.close();

                // Clear input fields
                questionField.setText("");
                optionAField.setText("");
                optionBField.setText("");
                optionCField.setText("");
                optionDField.setText("");
                answerField.setText("");

                JOptionPane.showMessageDialog(this, "Question added successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            new Teacherportal();
            setVisible(false);
        }
    }
}

