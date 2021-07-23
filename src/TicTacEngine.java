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
			parent.emptySquresLeft = 9;
			parent.score.setText("Your turn!");
			newGameButton.setEnabled(false);
			return;
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
		
		if (checkCombination(0,1,2)) {
			theWinner = getText(0);
			highlightWinner(0,1,2);
		} else if (checkCombination(3,4,5)) {
			theWinner = getText(3);
			highlightWinner(3,4,5);
		} else if (checkCombination(6,7,8)) {
			theWinner = getText(6);
			highlightWinner(6,7,8); 
		} else if (checkCombination(0,3,6)) {
			theWinner = getText(0);
			highlightWinner(0,3,6);
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
		parent.squares[a].setBackground(Color.CYAN);
		parent.squares[b].setBackground(Color.CYAN);
		parent.squares[c].setBackground(Color.CYAN);
	}
	
	public void endTheGame() {
		parent.newGameButton.setEnabled(true);
		for (int i = 0; i < parent.squares.length; i++) {
			parent.squares[i].setEnabled(false);
		}
	}
	
	/**
	* Цей метод застосовує набір правил, щоб знайти
	* кращий комп’ютерний хід. Якщо гарний хід
	*/
	public void computerMove() {
		int selectedSquare;
		selectedSquare = findEmptySquare("O");
		
		if (selectedSquare == -1) {
			selectedSquare = findEmptySquare("X");
		}
		if ((selectedSquare == -1) && (getText(4).equals("") )) {
			selectedSquare = 4;
		}
		if (selectedSquare == -1) {
			selectedSquare = getRandomSquare();
		}
		
		parent.squares[selectedSquare].setText("O");
		
	}
	
	public int findEmptySquare(String player) {
		int weight[] = new int[parent.sizePlayingField];
		int twoWeight = player.equals("O") ? -2 : 2;
		
		for (int i = 0; i < weight.length; i++) {
			if(getText(i).equals("O")) {
				weight[i] = -1;
			} else if (getText(i).equals("X")) {
				weight[i] = 1;
			} else {
				weight[i] = 0;
			}
		}
		
		if (weight[0] + weight[1] + weight[2] == twoWeight) {
			if (weight[0] == 0) {
				return 0;
			} else if (weight[1] == 0) {
				return 1;
			} else {
				return 2;
			}
		}
		
		if (weight[3] + weight[4] + weight[5] == twoWeight) {
			if (weight[3] == 0) {
				return 3;
			} else if (weight[4] == 0) {
				return 4;
			} else {
				return 5;
			}
		}
		
		if (weight[6] + weight[7] + weight[8] == twoWeight) {
			if (weight[6] == 0) {
				return 6;
			} else if (weight[7] == 0) {
				return 7;
			} else {
				return 8;
			}
		}
		
		if (weight[0] + weight[3] + weight[6] == twoWeight) {
			if (weight[0] == 0) {
				return 0;
			} else  if (weight[3] == 0) {
				return 3;
			} else {
				return 6;
			}
		}
		
		if (weight[1] + weight[4] + weight[7] == twoWeight) {
			if (weight[1] == 0) {
				return 1;
			} else  if (weight[4] == 0) {
				return 4;
			} else {
				return 7;
			}
		}
		
		
		if (weight[2] + weight[5] + weight[8] == twoWeight) {
			if (weight[2] == 0) {
				return 2;
			} else  if (weight[5] == 0) {
				return 5;
			} else {
				return 8;
			}
		}
		
		
		if (weight[0] + weight[4] + weight[8] == twoWeight) {
			if (weight[0] == 0) {
				return 0;
			} else  if (weight[4] == 0) {
				return 4;
			} else {
				return 8;
			}
		}
		
		
		if (weight[2] + weight[4] + weight[6] == twoWeight) {
			if (weight[2] == 0) {
				return 2;
			} else  if (weight[4] == 0) {
				return 4;
			} else {
				return 6;
			}
		}
		
		return -1;
	}
	
	public int getRandomSquare() {
		boolean gotEmptySquare = false;
		int selectedSquare = -1;
		
		do {
			selectedSquare = (int) (Math.random() * 9);
			if (getText(selectedSquare).equals("")) {
				gotEmptySquare = true;
			}
		} while (!gotEmptySquare);
		
		return selectedSquare;
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
