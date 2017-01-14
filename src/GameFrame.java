import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GameFrame extends JFrame implements Serializable {

	TicTacToe ticTacToe;

	public GameFrame(TicTacToe ticTacToe) {
		this.ticTacToe = ticTacToe;

		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");
		JMenu file = new JMenu("File");
		JMenuItem eMenuItem = new JMenuItem("Exit", icon);
		eMenuItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		JMenuItem eMenuItem2 = new JMenuItem("Save", icon);
		eMenuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				File file1 = new File("TicTacToe.ser");
				OutputStream os = null;
				ObjectOutputStream oos = null;
				try {
					os = new FileOutputStream(file1);
					oos = new ObjectOutputStream(os);
					oos.writeObject(ticTacToe.getTicTacToe());
				} catch (FileNotFoundException e1) {
				
					e1.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (oos != null) {
							oos.close();
						}
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					try {
						if (os != null) {
							os.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			};
		});

		JMenuItem eMenuItem3 = new JMenuItem("Load", icon);
		eMenuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				InputStream is = null;
				ObjectInputStream ois = null;
				try {
					is = new FileInputStream("TicTacToe.ser");
					ois = new ObjectInputStream(is);
					Board tmp = (Board) ois.readObject();
					ticTacToe.setTicTacToe(tmp);
					ticTacToe.refreshBoard();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException cnf) {

				} finally {

					try {
						if (ois != null) {
							ois.close();
						}
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					try {
						if (is != null) {
							is.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			};
		});

		file.add(eMenuItem);
		file.add(eMenuItem2);
		file.add(eMenuItem3);
		menubar.add(file);
		setJMenuBar(menubar);
	}
}
