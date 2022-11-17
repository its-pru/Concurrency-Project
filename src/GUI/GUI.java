//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

public class GUI extends JFrame implements ActionListener {
	private static final String TITLE_BAR_START = "Space Invaders";
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private JTextComponent boardBox;
	private Board board;
	private Player player;

	public GUI() {
		this.setTitle("Space Invaders");
		this.setSize(600, 600);
		this.setLocationRelativeTo((Component)null);
		this.setDefaultCloseOperation(3);
		this.boardBox = new JTextArea();
		this.boardBox.setText("Starting");
		this.boardBox.setFont(new Font("Monospaced", 0, 12));
		this.add(this.boardBox);
		this.boardBox.addKeyListener(new KeyProcessor());
		this.startTheTimer();
		this.board = new Board(7);
		this.player = new Player(this.board.getStartingX(), this.board.getStartingY());
	}

	private void startTheTimer() {
		this.timer = new Timer(750, this);
		this.timer.setInitialDelay(750);
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		StringBuffer boardString = new StringBuffer(this.board.toString());
		this.putPlayerOnTheScreen(boardString);
		this.boardBox.setText(boardString.toString());
		this.invalidate();
		this.repaint();
	}

	private void putPlayerOnTheScreen(StringBuffer boardString) {
		int positionInString = this.board.calculateBoardStringPosition(this.player.getX(), this.player.getY());
		boardString.setCharAt(positionInString, this.player.getDisplayChar());
	}

	private class KeyProcessor extends KeyAdapter {
		private KeyProcessor() {
		}

		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == 65) {
				GUI.this.player.move(Player.LEFT);
			}

			if (keyCode == 68) {
				GUI.this.player.move(Player.RIGHT);
			}

			if (keyCode == 32) {
				GUI.this.player.shoot();
			}

		}
	}
}
