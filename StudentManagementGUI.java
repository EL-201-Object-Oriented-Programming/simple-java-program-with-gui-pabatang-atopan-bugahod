import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    int age;
    String grade;

    
    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}


public class StudentManagementGUI extends JFrame {

    private JTextField nameField, ageField, gradeField;
    private JTextArea displayArea;
    private ArrayList<Student> students;
    private JButton addButton, removeButton, displayButton;

    public StudentManagementGUI() {
        students = new ArrayList<>();

        setTitle("Student Profile System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 300)); 

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

     
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

 
        addButton = new JButton("Add Student");
        removeButton = new JButton("Remove Student");
        displayButton = new JButton("Display Students");

     
        addButton.setPreferredSize(new Dimension(120, 30));
        removeButton.setPreferredSize(new Dimension(120, 30));
        displayButton.setPreferredSize(new Dimension(120, 30));

       
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

  
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayButton);

        
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void addStudent() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String grade = gradeField.getText();

        if (name.isEmpty() || ageText.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Student student = new Student(name, age, grade);
            students.add(student);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.");
        }
    }

   
    private void removeStudent() {
        String name = JOptionPane.showInputDialog(this, "Enter student name to remove:");
        if (name != null && !name.isEmpty()) {
            boolean removed = false;
            for (Student student : students) {
                if (student.name.equalsIgnoreCase(name)) {
                    students.remove(student);
                    JOptionPane.showMessageDialog(this, "Student removed successfully.");
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        }
    }

  
    private void displayStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students to display.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        gradeField.setText("");
    }

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementGUI().setVisible(true);
            }
        });
    }
}
