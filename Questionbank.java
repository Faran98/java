
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Questionbank extends JFrame implements ActionListener {

    private JCheckBox[] checkBoxes;
    private JButton selectButton, backButton;
    private StringBuilder selectedQuestions;
    private List<Question> questionsList;
    private String[] selectedCourses = {
            "DBT-2001.txt", "DBL-2001.txt", "OOPT-2002.txt", "OOPL-2002.txt", "OSL-2003.txt", "OST-2003.txt", "SDA-2004.txt"
    };

    public Questionbank() {
        getContentPane().setForeground(new Color(250, 153, 128));
        setLayout(null);
        setSize(800, 500);
        setLocation(300, 100);
        setVisible(true);
         setTitle("Question Bank");
        setResizable(false);

        JLabel heading = new JLabel("SELECT QUESTIONS FROM QUESTION BANK");
        heading.setBounds(200, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);

        questionsList = readQuestionsFromFile(selectedCourses);

        checkBoxes = new JCheckBox[questionsList.size()];
        for (int i = 0; i < questionsList.size(); i++) {
            checkBoxes[i] = new JCheckBox(questionsList.get(i).getQuestionText());
            checkBoxes[i].setBounds(100, 100 + 40 * i, 200, 30);
        }

        JScrollPane scrollPane = new JScrollPane(createCheckBoxPanel());
        scrollPane.setBounds(100, 100, 600, 300);
        add(scrollPane);

        selectButton = new JButton("Select");
        selectButton.setBounds(200, 420, 100, 30);
        selectButton.setBackground(new Color(250, 153, 128));
        selectButton.setForeground(Color.WHITE);
        selectButton.addActionListener(this);
        add(selectButton);

        backButton = new JButton("Back");
        backButton.setBounds(350, 420, 100, 30);
        backButton.setBackground(new Color(250, 153, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createCheckBoxPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (JCheckBox checkBox : checkBoxes) {
            panel.add(checkBox);
        }
        return panel;
    }

    private List<Question> readQuestionsFromFile(String[] filenames) {
        List<Question> questions = new ArrayList<>();

        for (String filename : filenames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String questionText = line;
                    String optionA = reader.readLine();
                    String optionB = reader.readLine();
                    String optionC = reader.readLine();
                    String optionD = reader.readLine();
                    String answer = reader.readLine();

                    Question question = new Question(questionText, optionA, optionB, optionC, optionD, answer);
                    questions.add(question);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == selectButton) {
            selectedQuestions = new StringBuilder();

            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    selectedQuestions.append(questionsList.get(i).getQuestionText()).append("\n");
                    selectedQuestions.append(questionsList.get(i).getOptionA()).append("\n");
                    selectedQuestions.append(questionsList.get(i).getOptionB()).append("\n");
                    selectedQuestions.append(questionsList.get(i).getOptionC()).append("\n");
                    selectedQuestions.append(questionsList.get(i).getOptionD()).append("\n");
                    selectedQuestions.append(questionsList.get(i).getAnswer()).append("\n");
                }
            }

            try {
                FileWriter fw = new FileWriter("attempt_quiz.txt");
                PrintWriter pw = new PrintWriter(fw);

                pw.println(selectedQuestions);
                pw.close();
                fw.close();
                JOptionPane.showMessageDialog(this, "Quiz created successfully!", "Quiz Creation", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                new Teacherportal();
                

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            new Teacherportal();
            setVisible(false);
        }
    }
}