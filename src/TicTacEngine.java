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
			parent.score.setText("It's a tie");
		}
	}
	
	public String lookWinner() {
		return "";
	}
	
	public void endTheGame() {
	
	}
	
	public void computerMove() {
	
	}

}
