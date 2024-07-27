import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JFrame frame;
    private JTextField display;

    private double num1, num2, result;
    private char operator;

    public Main() {
        frame = new JFrame("Calculator");
        display = new JTextField();

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Set up the display field
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        frame.add(display, BorderLayout.NORTH);

        // Set up the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                display.setText(display.getText() + command);
            } else if (command.charAt(0) == 'C') {
                display.setText("");
                num1 = num2 = result = 0;
                operator = '\0';
            } else if (command.charAt(0) == '=') {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num1 / num2; break;
                }
                display.setText(String.valueOf(result));
                num1 = result;
            } else {
                if (!display.getText().isEmpty()) {
                    num1 = Double.parseDouble(display.getText());
                    operator = command.charAt(0);
                    display.setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
