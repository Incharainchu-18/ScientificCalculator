
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener {

	JTextField textField;

	double num1, num2, result;
	char operator;

	ScientificCalculator() {

		setTitle("Scientific Calculator");
		setSize(1000, 1000);
		setLayout(null);

		textField = new JTextField();
		textField.setBounds(30, 30, 320, 40);
		textField.setFont(new Font("Arial", Font.BOLD, 20));
		add(textField);

		String buttons[] = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+", "sin",
				"cos", "tan", "√", "x²", "log", "ln", "C" };

		int x = 30, y = 100;

		for (String b : buttons) {

			JButton btn = new JButton(b);
			btn.setBounds(x, y, 70, 40);
			btn.addActionListener(this);
			add(btn);

			x += 80;

			if (x > 300) {
				x = 30;
				y += 50;
			}
		}

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		try {

			switch (command) {

			case "+":
			case "-":
			case "*":
			case "/":

				num1 = Double.parseDouble(textField.getText());
				operator = command.charAt(0);
				textField.setText("");
				break;

			case "=":

				num2 = Double.parseDouble(textField.getText());

				switch (operator) {

				case '+':
					result = num1 + num2;
					break;

				case '-':
					result = num1 - num2;
					break;

				case '*':
					result = num1 * num2;
					break;

				case '/':
					result = num1 / num2;
					break;
				}

				textField.setText("" + result);
				break;

			case "sin":
				num1 = Double.parseDouble(textField.getText());
				result = Math.sin(Math.toRadians(num1));
				textField.setText("" + result);
				break;

			case "cos":
				num1 = Double.parseDouble(textField.getText());
				result = Math.cos(Math.toRadians(num1));
				textField.setText("" + result);
				break;

			case "tan":
				num1 = Double.parseDouble(textField.getText());
				result = Math.tan(Math.toRadians(num1));
				textField.setText("" + result);
				break;

			case "√":
				num1 = Double.parseDouble(textField.getText());
				result = Math.sqrt(num1);
				textField.setText("" + result);
				break;

			case "x²":
				num1 = Double.parseDouble(textField.getText());
				result = Math.pow(num1, 2);
				textField.setText("" + result);
				break;

			case "log":
				num1 = Double.parseDouble(textField.getText());
				result = Math.log10(num1);
				textField.setText("" + result);
				break;

			case "ln":
				num1 = Double.parseDouble(textField.getText());
				result = Math.log(num1);
				textField.setText("" + result);
				break;

			case "C":
				textField.setText("");
				break;

			default:
				textField.setText(textField.getText() + command);
			}

		} catch (Exception ex) {
			textField.setText("Error");
		}

	}

	public static void main(String[] args) {

		new ScientificCalculator();
	}
}
