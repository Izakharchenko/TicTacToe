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
		if (checkCombination(0,1,2)) {
			theWinner = getText(0);
			highlightWinner(0,1,2);
		// check second row - elements 3,4,5
		} else if (checkCombination(3,4,5)) {
			theWinner = getText(3);
			highlightWinner(3,4,5);
		// check three row - elements 6,7,8
		} else if (checkCombination(6,7,8)) {
			theWinner = getText(6);
			highlightWinner(6,7,8);
		// check first column 0,3,6 
		} else if (checkCombination(0,3,6)) {
			theWinner = getText(0);
			highlightWinner(0,3,6);
		//check second column 1,4,7
		} else if (checkCombination(1,4,7)) {
			theWinner = getText(1);
			highlightWinner(1,4,7);
		} else if (checkCombination(2,5,8)) {
			theWinner = getText(2);
			highlightWinner(2,5,8);
		} else if (checkCombination(0,4,8)) {
			theWinner = getText(0);
			highlightWinner(0,4,8);
		} else if (checkCombination(2,4,6)) {
			theWinner = getText(2);
			highlightWinner(0,4,8);
		}
		
		return theWinner;
	}
	
	public void highlightWinner(int a, int b, int c) {
		
	}
	
	public void endTheGame() {
	
	}
	
	public void computerMove() {
	
	}
	protected boolean checkCombination (int a, int b, int c) {
		if (!getText(a).equals("") && getText(a).equals(getText(b)) && getText(a).equals(getText(c)))
			return true;
		
		return false;
	}
	public String getText(int index) {
		return parent.squares[index].getText();
	}

}
