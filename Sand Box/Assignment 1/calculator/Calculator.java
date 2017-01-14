package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 *   Calculator.java
 *   GUI for CS310 Fall 2016
 */    



public class Calculator extends JFrame {
	protected JLabel label;
	protected JButton [] buttons;
	protected JPanel buttonPanel;
	protected ExpressionEvaluator evaluator;

	/**
	 * The constructor for the calculator
	 */
	public Calculator() {
		evaluator = new ExpressionEvaluator();
		setWindowAttributes();
		JPanel contentPanel = getContentPanel();
		addComponents(contentPanel);
		setVisible(true);
	}

	private void setWindowAttributes() {
		setLookAndFeel();
		setTitle("Calculator");
		setSize(350,350);
		setLocation(50,50);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void setLookAndFeel() {

		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e) {
			System.out.println("Sorry, LookAndFeel not found.");       
		}
	}

	private JPanel getContentPanel() {
		JPanel panel = (JPanel) getContentPane();
		BorderLayout layout = new BorderLayout();
		layout.setHgap(5);
		layout.setVgap(5);
		panel.setLayout(layout);
		panel.setBorder(new LineBorder(Color.gray,10));
		panel.setBackground(Color.gray);        
		return panel;
	}

	private void addComponents(JPanel panel) {
		label = new JLabel(" ", SwingConstants.RIGHT);
		label.setPreferredSize(new Dimension(250,40));
		label.setFont(new Font("Helvetica", Font.BOLD, 16));
		label.setOpaque(true);
		label.setBackground(Color.white);      
		panel.add(label, BorderLayout.NORTH);
		GridLayout gridLayout = new GridLayout(4,5);
		gridLayout.setHgap(3);
		gridLayout.setVgap(3);
		buttonPanel = new JPanel(gridLayout);
		buttons = new JButton[20];
		buttons[0] = new JButton("7");
		buttons[1] = new JButton("8");
		buttons[2] = new JButton("9");
		buttons[3] = new JButton("+"); 
		buttons[4] = new JButton("^");               
		buttons[5] = new JButton("4"); 
		buttons[6] = new JButton("5");
		buttons[7] = new JButton("6");
		buttons[8] = new JButton("-");
		buttons[9] = new JButton("(");                
		buttons[10] = new JButton("1");
		buttons[11] = new JButton("2");
		buttons[12] = new JButton("3");
		buttons[13] = new JButton("*"); 
		buttons[14] = new JButton(")");               
		buttons[15] = new JButton("0");
		buttons[16] = new JButton(".");
		buttons[17] = new JButton("=");
		buttons[18] = new JButton("/");
		buttons[19] = new JButton("AC");                 

		for(int i=0; i < 20; i++) {
			buttons[i].setFont(new Font("Courier", Font.BOLD, 18));
			buttonPanel.add(buttons[i]);
			buttons[i].addActionListener(new ButtonHandler());
		}

		panel.add(buttonPanel, BorderLayout.CENTER);                              

	}

	public class ButtonHandler implements ActionListener {        
		public void actionPerformed(ActionEvent e) {
			String key = e.getActionCommand();
			if(key.equals("AC"))
				label.setText(" ");
			else if(key.equals("+") || key.equals("-")
					|| key.equals("*") || key.equals("/")
					|| key.equals("^") || key.equals("(")
					|| key.equals(")")) {
				String IOText = label.getText();
				IOText += " " + key + " ";
				label.setText(IOText);
			}
			else if(key.equals("=")) {
				String IOText = label.getText();
				IOText = evaluator.processInput(IOText); 
				label.setText(IOText);
			}
			else {
				String IOText = label.getText();
				IOText += key;
				label.setText(IOText);
			}

		}
	}

	public static void main(String [] args) {
		new Calculator();
	}
}
