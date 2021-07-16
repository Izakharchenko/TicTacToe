import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class TicTacEngine implements ActionListener {

	TicTacToe parent;
	TicTacEngine(TicTacToe parent) {
		this.parent = parent; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String winner = ""; 
		JButton theButton = (JButton) e.getSource();
		JButton newGameButton = parent.newGameButton;
		
		
		if (theButton == newGameButton) {
			
			for(int i = 0; i < parent.sizePlayingField; i++) {
				parent.squares[i].setEnabled(true);
				parent.squares[i].setText("");
				parent.squares[i].setBackground(Color.orange);
			}
			
			parent.score.setText("Your turn!");
			newGameButton.setEnabled(false);	
		}
		
		for (int i = 0; i < parent.sizePlayingField; i++) {
			if (theButton == parent.squares[i]) {
				parent.squares[i].setText("X");
				winner = lookWinner();
				if (!"".equals(winner)) {
					endTheGame();
				} else {
					computerMove();
					winner = lookWinner();
					if (!"".equals(winner)) {
						endTheGame();
					}
				}
				break;
			}
		}
		
		if (winner.equals("X")) {
			parent.score.setText("You won!");
		} else if (winner.equals("O")) {
			parent.score.setText("You lost!");
		} else if (winner.equals("T")) {
			parent.score.setText("It's a tie!");
		}
	}
	
	public String lookWinner() {
		String theWinner = "";
		parent.emptySquresLeft--;
		
		if (parent.emptySquresLeft == 0) {
			return "T";
		}
		// check first row - elements 0,1,2
		if (!parent.squares[0].getText().equals("") && 
				parent.squares[0].getText().equals(parent.squares[1].getText()) &&
				parent.squares[0].getText().equals(parent.squares[2].getText())) {
			theWinner = parent.squares[0].getText();
			highlightWinner(0,1,2);
		// check second row - elements 3,4,5
		} else if (!parent.squares[3].getText().equals("") &&
				parent.squares[3].getText().equals(parent.squares[4].getText()) &&
				parent.squares[3].getText().equals(parent.squares[5].getText())) {
			theWinner = parent.squares[3].getText();
			highlightWinner(3,4,5);
		// check three row - elements 6,7,8
		} else if (!parent.squares[6].getText().equals("") &&
				parent.squares[6].getText().equals(parent.squares[7].getText()) &&
				parent.squares[6].getText().equals(parent.squares[8].getText())) {
			theWinner = parent.squares[6].getText();
			highlightWinner(6,7,8);
		}
		
		return theWinner;
	}
	
	public void highlightWinner(int a, int b, int c) {
		
	}
	
	public void endTheGame() {
	
	}
	
	public void computerMove() {
	
	}

}
