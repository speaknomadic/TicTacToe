import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class TicTacToe extends JPanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Board ticTacToe;
	ArrayList <JButton> arrayList;

	Icon myCrossImage = new ImageIcon("Cross.png");
	Icon myHeartImage = new ImageIcon("Heart.png");

	public TicTacToe(int size) {
		setLayout(new GridLayout(size, size));
		arrayList = new ArrayList<JButton>();

		for (int i = 0; i < size * size; i++) {
			arrayList.add(new JButton());
			add(arrayList.get(i));
		}

		this.ticTacToe = new Board(size);

		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < arrayList.size(); i++) {
					if (e.getSource().equals(arrayList.get(i))) {
						((JButton) e.getSource()).setEnabled(false);
						ticTacToe.addNumberOnPosition(i / size, i % size);

						if (ticTacToe.isFirstOnTurn()) {
							arrayList.get(i).setIcon(myCrossImage);
						} else {
							arrayList.get(i).setIcon(myHeartImage);
						}

						int resultWinner = ticTacToe.checkForWinner();

						if (resultWinner == Player.X_PLAYER) {
							JOptionPane.showMessageDialog(null, "Game over! Player one wins! ");
							ticTacToe.printBoard();
							loadEmptyBoard();
							break;
						} else if (resultWinner == Player.O_PLAYER) {
							JOptionPane.showMessageDialog(null, "Game over! Player two wins");
							ticTacToe.printBoard();
							loadEmptyBoard();
							break;
						} else if (resultWinner == Player.EMPTY) {
							JOptionPane.showMessageDialog(null, "Game over! Draw game! Try again!");
							ticTacToe.printBoard();
							loadEmptyBoard();
							break;
						}
					}

				}
			};
		};
		

		for (int i = 0; i < arrayList.size(); i++) {
			arrayList.get(i).addActionListener(actionListener);
		}
	}

	public void loadEmptyBoard() {
		ticTacToe.emptyBoard();
		for (int i = 0; i < arrayList.size(); i++) {
			arrayList.get(i).setIcon(null);
			arrayList.get(i).setEnabled(true);
		}
	}

	public Board getTicTacToe() {
		return ticTacToe;
	}

	public void setTicTacToe(Board ticTacToe) {
		this.ticTacToe = ticTacToe;
	}

	public void refreshBoard() {
		for (int i = 0; i < ticTacToe.size; i++) {
			for (int j = 0; j < ticTacToe.size; j++) {
				arrayList.set(i, new JButton());
				
				if (ticTacToe.getMatrix()[i][j] == Player.X_PLAYER) {
					arrayList.get(i * ticTacToe.size + j).setIcon(myHeartImage);
					arrayList.get(i * ticTacToe.size + j).setEnabled(false);
				} else if (ticTacToe.getMatrix()[i][j] == Player.O_PLAYER) {
					arrayList.get(i * ticTacToe.size + j).setIcon(myCrossImage);
					arrayList.get(i * ticTacToe.size + j).setEnabled(false);
				}
			}
		}
	}
}
