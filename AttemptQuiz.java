/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author User
 */
public class AttemptQuiz extends JFrame implements ActionListener {

    private List<questionn> questionsList;
    private List<ButtonGroup> buttonGroups;
    private JButton submitButton, backButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining;

    public AttemptQuiz(String selectedQuestionsFile) {
        getContentPane().setForeground(new Color(250, 153, 128));
        setLayout(null);
        setSize(800, 500);
        setLocation(300, 100);
        setVisible(true);
        setResizable(false);

        JLabel heading = new JLabel("ATTEMPT QUIZ");
        heading.setBounds(350, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 20));
        heading.setForeground(new Color(250, 153, 128));
        add(heading);

        this.questionsList = new ArrayList<>();
        buttonGroups = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedQuestionsFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String questionText = line;
                String optionA = reader.readLine();
                String optionB = reader.readLine();
                String optionC = reader.readLine();
                String optionD = reader.readLine();
                String answer = reader.readLine();

                if (questionText != null && optionA != null && optionB != null && optionC != null && optionD != null && answer != null) {
                    questionn question = new questionn(questionText, optionA, optionB, optionC, optionD, answer);
                    questionsList.add(question);
                } else {
                    System.out.println("Invalid question format!");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int yOffset = 100;
        if (questionsList.size() >= 1) {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(100, yOffset, 600, 200);
            add(scrollPane);

            JPanel questionPanel = new JPanel();
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
            scrollPane.setViewportView(questionPanel);

            for (int i = 0; i < questionsList.size(); i++) {
                questionn question = questionsList.get(i);

                JLabel questionLabel = new JLabel((i + 1) + ". " + question.getQuestionText());
                questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                questionPanel.add(questionLabel);

                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroups.add(buttonGroup);

                for (int j = 0; j < 4; j++) {
                    JRadioButton radioButton = new JRadioButton(question.getOption(j));
                    radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                    buttonGroup.add(radioButton);
                }

                // Add the options to the questionPanel after adding the entire buttonGroup
                Enumeration<AbstractButton> buttons = buttonGroup.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    questionPanel.add(button);
                }
            }
        }

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 350, 100, 30);
        submitButton.setBackground(new Color(250, 153, 128));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(350, 350, 100, 30);
        backButton.setBackground(new Color(250, 153, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        timerLabel = new JLabel();
        timerLabel.setBounds(490, 300, 200, 30);
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        timerLabel.setForeground(new Color(250, 153, 128));
        add(timerLabel);

        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the initial time remaining
        timeRemaining = 3600; // 60 minutes

        // Create a timer that updates every sec
        timer = new Timer(600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });

        // Start the timer
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            int totalQuestions = questionsList.size();
            String[] studentAnswers = new String[totalQuestions]; // Array to store student answers
            int correctAnswers = 0;

            for (int i = 0; i < totalQuestions; i++) {
                ButtonGroup buttonGroup = buttonGroups.get(i);
                Enumeration<AbstractButton> buttons = buttonGroup.getElements();

                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();

                    if (button.isSelected()) {
                        studentAnswers[i] = button.getActionCommand(); // Store selected radio button answer as the student's answer
                        break;
                    }
                }

                String correctOption = questionsList.get(i).getAnswer();

                if (studentAnswers[i] != null && studentAnswers[i].equals(correctOption)) {
                    correctAnswers++;
                }
            }

            double percentage = (double) correctAnswers / (double) totalQuestions * 100.0;
            String formattedPercentage = String.format("%.2f", percentage);
            
            StringBuilder resultMessage = new StringBuilder();
            resultMessage.append("Quiz completed!\n");
            for (int i = 0; i < totalQuestions; i++) {
                resultMessage.append("Question ").append(i + 1).append(": ");
                resultMessage.append("Your answer: ");
                if (studentAnswers[i] != null) {
                    resultMessage.append(studentAnswers[i]);
                } else {
                    resultMessage.append("No answer provided");
                }
                resultMessage.append(", ");
                resultMessage.append("Correct answer: ").append(questionsList.get(i).getAnswer()).append("\n");
            }
            resultMessage.append("\nCorrect Answers: ").append(correctAnswers);
            resultMessage.append("\nTotal Questions: ").append(totalQuestions);
             resultMessage.append("\nPercentage: ").append(formattedPercentage).append("%");

            JOptionPane.showMessageDialog(this, resultMessage.toString());
            String Attendance = "Present";

            // Save the result to a file
            saveResultToFile(correctAnswers, totalQuestions, percentage,Attendance);
            new Studentportal();
            setVisible(false);
        } else if (ae.getSource() == backButton) {
            new Studentportal();
            setVisible(false);
        }
    }

    private void updateTimer() {
        timeRemaining--;

        if (timeRemaining <= 0) {
            handleTimeout();
        } else {
            int minutes = timeRemaining / 60;
            int seconds = timeRemaining % 60;
            String timeString = String.format("%02d:%02d", minutes, seconds);
            timerLabel.setText("Time Remaining: " + timeString);
        }
    }

    private void handleTimeout() {
        timer.stop();
        StringBuilder timeoutMessage = new StringBuilder();
        timeoutMessage.append("Time's up! You did not submit your quiz on time.\n");
        timeoutMessage.append("Your quiz will be marked as incomplete with 0% score.");

        JOptionPane.showMessageDialog(this, timeoutMessage.toString());

        // Save the result to a file with 0% score
        saveResultToFile(0, questionsList.size(), 0,"Absent");
        new Studentportal();
        setVisible(false);
    }

    private void saveResultToFile(int correctAnswers, int totalQuestions, double percentage,String Attendance) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Result.txt", true));
            writer.write("Username: " + "faran");
            writer.newLine();
            writer.write("Correct Answers: " + correctAnswers);
            writer.newLine();
            writer.write("Total Questions: " + totalQuestions);
            writer.newLine();
            writer.write("Percentage: " + percentage + "%");
            writer.newLine();
            writer.write("\n \n Attendance: " + Attendance );
            writer.newLine();
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}