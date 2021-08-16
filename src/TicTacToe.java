import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class TicTacToe {

	int emptySquresLeft = 9;
	int x = 3;
	int y = 3;
	int sizePlayingField = x * y;
	
	Font font = new Font(Font.SERIF, Font.PLAIN, 31);
	JPanel windowContent;
	JPanel gamePane;
	JButton newGameButton;
	JLabel score;
	JButton squares[];
	
	BorderLayout bl;
	GridLayout gl;
	
	TicTacEngine ticTacEngine;
	
	TicTacToe() {
		ticTacEngine = new TicTacEngine(this);
		
		newGameButton = new JButton("Start");
		newGameButton.addActionListener(ticTacEngine);
		
		score = new JLabel("Push button to start!");
		
		windowContent = new JPanel();
		windowContent.setBackground(Color.cyan);
		bl = new BorderLayout();

		gamePane = new JPanel();
		
		gl = new GridLayout(x, y);
		gamePane.setLayout(gl);
		
		squares = new JButton[sizePlayingField];
		
		for(int i = 0; i < sizePlayingField; i++) {
			squares[i] = new JButton();
			squares[i].setEnabled(false);
			squares[i].setFocusable(false);
			squares[i].setFont(font);
			squares[i].addActionListener(ticTacEngine);
			gamePane.add(squares[i]);
		}
		
		windowContent.setLayout(bl);
		windowContent.add(BorderLayout.NORTH, newGameButton);
		windowContent.add(BorderLayout.SOUTH, score);
		windowContent.add(BorderLayout.CENTER, gamePane);
		
		
		JFrame frame = new JFrame("Tic-tac toe");
		frame.setContentPane(windowContent);
		frame.setSize(480, 500);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TicTacToe();

	}

}
