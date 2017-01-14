import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeDemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		TicTacToe ticTacToe = new TicTacToe(3);

		String[] sizes = new String[3];
		sizes[0] = "3x3";
		sizes[1] = "5x5";
		sizes[2] = "7x7";

		String res = (String) JOptionPane.showInputDialog (null, "Please, choose difficulty?", "Choose  size",
				JOptionPane.QUESTION_MESSAGE, null, sizes, sizes[0]);
		System.out.println(res);
		System.out.println("-------------------------------------");
		GameFrame frame;
		if (res != null) {
			switch (res) {
			case "3x3":
				ticTacToe = new TicTacToe(3);
				frame = new GameFrame(ticTacToe);
				frame.setSize(480, 480);
				break;
			case "5x5":
				ticTacToe = new TicTacToe(5);
				frame = new GameFrame(ticTacToe);
				frame.setSize(800, 800);
				break;
			case "7x7":
				ticTacToe = new TicTacToe(7);
				frame = new GameFrame(ticTacToe);
				frame.setSize(1020, 1020);
				break;
			default:
				ticTacToe = new TicTacToe(3);
				frame = new GameFrame(ticTacToe);
				frame.setSize(480, 480);
				break;
			}

			frame.setSize(1000, 1000);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(ticTacToe);

			frame.setVisible(true);
		}
	}
}
