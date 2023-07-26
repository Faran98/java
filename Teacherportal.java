
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Teacherportal extends JFrame implements ActionListener {

    JButton createQuiz,questionBankButton,back;
    JCheckBox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6, checkbox7;


    Teacherportal() {
        getContentPane().setForeground(new Color(250, 153, 128));
        setLayout(null);
        setSize(1200, 750);
        setLocation(150, 70);
        setVisible(true);
         setTitle("Teacher Portal");
        setResizable(false);

        JLabel heading = new JLabel("WELCOME TO TEACHER PORTAL");
        heading.setBounds(500, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);

        JLabel heading2 = new JLabel("Select the Course for which you want to create a quiz");
        heading2.setBounds(220, 80, 600, 40);
        heading2.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading2.setForeground(new Color(250, 153, 128));
        add(heading2);

        checkbox1 = new JCheckBox("Database Management System Theory (DBT-2001)");
        checkbox1.setBounds(150, 120, 500, 50);
        checkbox2 = new JCheckBox("Database Management System Lab (DBL-2001)");
        checkbox2.setBounds(150, 160, 500, 50);
        checkbox3 = new JCheckBox("Object Oriented Programming Theory (OOPT-2002)");
        checkbox3.setBounds(150, 200, 500, 50);
        checkbox4 = new JCheckBox("Object Oriented Programming Lab (OOPL-2002)");
        checkbox4.setBounds(150, 240, 500, 50);
        checkbox5 = new JCheckBox("Operating System Lab (OSL-2003)");
        checkbox5.setBounds(150, 280, 500, 50);
        checkbox6 = new JCheckBox("Operating System Theory(OSL-2003)");
        checkbox6.setBounds(150, 320, 500, 50);
        checkbox7 = new JCheckBox("Software Design and Architecture (SDA-2004)");
        checkbox7.setBounds(150, 360, 500, 50);

        ButtonGroup checkboxGroup = new ButtonGroup();
        checkboxGroup.add(checkbox1);
        checkboxGroup.add(checkbox2);
        checkboxGroup.add(checkbox3);
        checkboxGroup.add(checkbox4);
        checkboxGroup.add(checkbox5);
        checkboxGroup.add(checkbox6);
        checkboxGroup.add(checkbox7);

        add(checkbox1);
        add(checkbox2);
        add(checkbox3);
        add(checkbox4);
        add(checkbox5);
        add(checkbox6);
        add(checkbox7);


        createQuiz = new JButton("Create Quiz");
        createQuiz.setBounds(280, 450, 200, 30);
        createQuiz.setBackground(new Color(250, 153, 128));
        createQuiz.setForeground(Color.WHITE);
        createQuiz.addActionListener(this);
        add(createQuiz);
        
        questionBankButton = new JButton("Question Bank");
        questionBankButton.setBounds(280, 500, 200, 30);
        questionBankButton.setBackground(new Color(250, 153, 128));
        questionBankButton.setForeground(Color.WHITE);
        questionBankButton.addActionListener(this);
        add(questionBankButton);

        
        back = new JButton("Back");
        back.setBounds(280, 550, 200, 30);
        back.setBackground(new Color(250, 153, 128));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
 

    }

    public void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
   @Override
      public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == createQuiz) {
        StringBuilder selectedCourses = new StringBuilder();
        boolean isCourseSelected = false; // Flag to check if any checkbox is selected

        if (checkbox1.isSelected()) {
            String fileName = "DBT-2001.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("DBT-2001"), fileName);
            isCourseSelected = true;
        }
        if (checkbox2.isSelected()) {
            String fileName = "DBL-2001.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("DBL-2001"), fileName);
            isCourseSelected = true;
        }
        if (checkbox3.isSelected()) {
            String fileName = "OOPT-2002.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("OOPT-2002"), fileName);
            isCourseSelected = true;
        }
        if (checkbox4.isSelected()) {
            String fileName = "OOPL-2002.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("OOPL-2002"), fileName);
            isCourseSelected = true;
        }
        if (checkbox5.isSelected()) {
            String fileName = "OSL-2003.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("OSL-2003"), fileName);
            isCourseSelected = true;
        }
        if (checkbox6.isSelected()) {
            String fileName = "OST-2003.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("OST-2003"), fileName);
            isCourseSelected = true;
        }
        if (checkbox7.isSelected()) {
            String fileName = "SDA-2004.txt";
            createFile(fileName);
            new Quiz(selectedCourses.append("SDA-2004"), fileName);
            isCourseSelected = true;
        }

        if (!isCourseSelected) {
            JOptionPane.showMessageDialog(this, "Please select a course to create a quiz.", "No Course Selected", JOptionPane.WARNING_MESSAGE);
        } else {
            setVisible(false);
        }
    } else if (ae.getSource() == questionBankButton) {
        setVisible(false); // Hide the TeacherPortal frame
        new Questionbank();
    } else if (ae.getSource() == back) {
        new Login();
        setVisible(false);
    }
}
}