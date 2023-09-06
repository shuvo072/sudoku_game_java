package sudoku;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame {

	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	private JFrame parentFrame;
	private Cursor cursor;
	public JLabel time;
	public Timer timer;
	private SudokuPuzzle generatedPuzzle;

	public SudokuFrame(JFrame xFrame) {

		parentFrame = xFrame;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setLocation(300, 100);
		this.setMinimumSize(new Dimension(800, 600));

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Level");
		JMenu newGame = new JMenu("Select Level");
		JMenuItem sixBySixGame = new JMenuItem("Easy");
		sixBySixGame.addActionListener(NewGameListener(SudokuPuzzleType.SIXBYSIX, 30));
		JMenuItem nineByNineGame = new JMenuItem("Medium");
		nineByNineGame.addActionListener(NewGameListener(SudokuPuzzleType.NINEBYNINE, 26));
		JMenuItem twelveByTwelveGame = new JMenuItem("Hard");
		twelveByTwelveGame.addActionListener(NewGameListener(SudokuPuzzleType.TWELVEBYTWELVE, 20));

		newGame.add(sixBySixGame);
		newGame.add(nineByNineGame);
		newGame.add(twelveByTwelveGame);
		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);

		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setBackground(Color.BLACK);
		windowPanel.setPreferredSize(new Dimension(800, 600));

		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(110, 500));
		buttonSelectionPanel.setBackground(Color.BLACK);

		sPanel = new SudokuPanel();

		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);

		rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);
		startTimer();

	}

	public void rebuildInterface(SudokuPuzzleType puzzleType, int fontSize) {

		generatedPuzzle = new SudokuGenerator().generateRandomSudoku(puzzleType);
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		cursor = new Cursor(Cursor.HAND_CURSOR);

		for (String value : generatedPuzzle.getValidValues()) {

			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(50, 40));
			b.setCursor(cursor);
			b.addKeyListener(sPanel.new NumKeyActionListener());
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);

		}

		JButton back = new JButton("Main Menu");
		back.setPreferredSize(new Dimension(100, 40));
		back.setCursor(cursor);

		back.addActionListener(e -> {

			this.dispose();
			parentFrame.setVisible(true);

		});

		buttonSelectionPanel.add(back);
		time = new JLabel("00:00:00");
		time.setFont(new Font("Serif", Font.BOLD, 26));
		time.setForeground(Color.WHITE);
		time.setPreferredSize(new Dimension(100, 80));
		buttonSelectionPanel.add(time);
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();

	}

	public ActionListener NewGameListener(SudokuPuzzleType puzzleType, int fontSize) {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stopTimer();
				rebuildInterface(puzzleType, fontSize);
				startTimer();
			}
		};
		return actionListener;
	}

	public ActionListener countTime() {
		ActionListener actionListener = new ActionListener() {
			int x = 1;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				time.setText(getTime(x));
				SudokuPuzzle.time = time.getText();
				if(generatedPuzzle.boardFull()) {
					stopTimer();
					time.setText(getTime(x-1));
					dispose();
				}
				x++;
				
			}
		};
		return actionListener;
	}

	static String getTime(int sec) {

		int hours = 0;
		int remainderOfHours = 0;
		int minutes = 0;
		int seconds = 0;

		if (sec >= 3600) {
			hours = sec / 3600;
			remainderOfHours = sec % 3600;

			if (remainderOfHours >= 60) {
				minutes = remainderOfHours / 60;
				seconds = remainderOfHours % 60;
			} else {
				seconds = remainderOfHours;
			}
		} else if (sec >= 60) {
			hours = 0;
			minutes = sec / 60;
			seconds = sec % 60;
		} else if (sec < 60) {
			hours = 0;
			minutes = 0;
			seconds = sec;
		}

		String strHours;
		String strMins;
		String strSecs;

		if (seconds < 10)
			strSecs = "0" + Integer.toString(seconds);
		else
			strSecs = Integer.toString(seconds);

		if (minutes < 10)
			strMins = "0" + Integer.toString(minutes);
		else
			strMins = Integer.toString(minutes);

		if (hours < 10)
			strHours = "0" + Integer.toString(hours);
		else
			strHours = Integer.toString(hours);

		String time = strHours + ":" + strMins + ":" + strSecs;
		return time;
	}

	public void startTimer() {
		timer = new Timer(1000, countTime());
		timer.start();
	}

	public void stopTimer() {
		if (timer != null && timer.isRunning())
			timer.stop();
	}

}