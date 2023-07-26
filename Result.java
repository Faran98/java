/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author User
 */
public class Result extends JFrame {

    private JTextArea resultTextArea;

    public Result() {
        setTitle("Quiz Result");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        loadResultFromFile();

        setVisible(true);
    }

    private void loadResultFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Result.txt"));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            resultTextArea.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
