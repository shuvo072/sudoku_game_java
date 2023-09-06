package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MyMenu extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private Container c;
	private JMenuBar menubar;
	private JButton startGame , okButton, backButton;
	private JMenu profile, help, score;
	private JMenuItem newProfile, guestProfile, exitItem, scoreBoard, tipsAndTricks, about;
	private ImageIcon img;
	private JLabel userLabel, imgLabel, selectPro, scoreShow;
	private Cursor cursor;
	private JTextField tf1;
	private JComboBox<Object> selectMode;
	private String[] mode = {"Easy","Medium","Hard"};
	private JRadioButton namedPro, guestPro;
	private ButtonGroup grp;
	private Font f, f1;
	
	MyMenu() {
		
		firstComponents();
		initComponents();
		
	}
	
	public void addImage() {
		
		img = new ImageIcon(getClass().getResource("Sudoku.png"));
		
		imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
	}
	
	public void firstComponents() {
		
		c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.BLACK);
		
		addImage();
		c.add(imgLabel);
		
	}
	
	public void initComponents() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(300,100,600,500);
		this.setTitle("Sudoku");
		
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		profile = new JMenu("Profile");
		help = new JMenu("Help");
		score = new JMenu("Score");
		
		menubar.add(profile);
		menubar.add(score);
		menubar.add(help);
		
		newProfile = new JMenuItem("Create New Profile");
		guestProfile = new JMenuItem("Play on Guest Mode");
		exitItem = new JMenuItem("Exit");
		scoreBoard = new JMenuItem("Score Board");
		tipsAndTricks = new JMenuItem("Tips And Tricks");
		about = new JMenuItem("About");
		
		profile.add(newProfile);
		profile.add(guestProfile);
		profile.add(exitItem);
		score.add(scoreBoard);
		help.add(tipsAndTricks);
		help.add(about);
		
		newProfile.addActionListener(this);
		guestProfile.addActionListener(this);
		exitItem.addActionListener(this);
		scoreBoard.addActionListener(this);
		tipsAndTricks.addActionListener(this);
		about.addActionListener(this);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		if(e.getSource()==newProfile) {
			
			JFrame newFrame = new JFrame();
			newFrame.setTitle("Sudoku");
			newFrame.setLayout(null);
			newFrame.setBounds(300, 100, 800, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(false);
			newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f = new Font("Arial",Font.BOLD + Font.ITALIC,14);
			f1 = new Font("Arial",Font.BOLD + Font.ITALIC,20);
			cursor = new Cursor(Cursor.HAND_CURSOR);

			
			userLabel = new JLabel();
			userLabel.setText("Player Name");
			userLabel.setBounds(50, 250, 200, 100);
			userLabel.setFont(f1);
			userLabel.setForeground(Color.BLACK);
			newFrame.add(userLabel);
			
			tf1 = new JTextField("Enter Your Name");
			tf1.setBounds(200, 270, 200, 50);
			tf1.setFont(f);
			tf1.setForeground(Color.BLUE);
			tf1.selectAll();
			//tf1.setBackground(Color.CYAN);
			tf1.setHorizontalAlignment(JTextField.CENTER);
			newFrame.add(tf1);
			
			okButton = new JButton("START");
			newFrame.add(okButton);
			okButton.setBounds(200, 500, 150, 50);
			okButton.setForeground(Color.BLACK);
			okButton.setFont(f);
			okButton.setCursor(cursor);
			
			backButton = new JButton("BACK");
			newFrame.add(backButton);
			backButton.setBounds(350, 500, 150, 50);
			backButton.setForeground(Color.BLACK);
			backButton.setFont(f);
			backButton.setCursor(cursor);
			
			okButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					newFrame.dispose();
					startGame();
				}
				
			});
			
			backButton.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent ae) {
						setVisible(true);
						newFrame.setVisible(false);
					
				}
				
			});
			
			addImage();
			newFrame.add(imgLabel);

		}
		
		else if(e.getSource()==guestProfile) {
			
			JFrame newFrame = new JFrame("Sudoku");
			newFrame.setLayout(null);
			newFrame.setBounds(300, 100, 800, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(false);
			newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f = new Font("Arial",Font.BOLD + Font.ITALIC,14);
			cursor = new Cursor(Cursor.HAND_CURSOR);
			
			startGame = new JButton ("START GAME");
			backButton = new JButton("BACK");
				
			newFrame.add(startGame);
			newFrame.add(backButton);
			
			startGame.setBounds(300, 300, 150, 50);
			backButton.setBounds(300, 360, 150, 50);
			startGame.setForeground(Color.BLACK);
			backButton.setForeground(Color.BLACK);
			startGame.setFont(f);
			backButton.setFont(f);
			startGame.setCursor(cursor);
			backButton.setCursor(cursor);
				
			newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			addImage();
			newFrame.add(imgLabel);
			
			backButton.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent ae) {
						setVisible(true);
						newFrame.setVisible(false);
					
				}
				
			});
			
			startGame.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					newFrame.dispose();
					startGame();
					
				}
				
			});
			
		}
		
		else if(e.getSource()==exitItem) {
			
			//setVisible(true);
			
			int choice = JOptionPane.showConfirmDialog(null,"Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
			
			if(choice == JOptionPane.YES_OPTION) {
				
				System.exit(0);
				
			}
			
			else {
				
				setVisible(true);
				
			}
			
		}
		
		else if(e.getSource()==scoreBoard) {
			
			JFrame newFrame = new JFrame("Sudoku");
			newFrame.setLayout(null);
			newFrame.setBounds(300, 100, 800, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(false);
			newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f = new Font("Arial",Font.BOLD + Font.ITALIC,14);
			f1 = new Font("Arial",Font.BOLD + Font.ITALIC,20);
			cursor = new Cursor(Cursor.HAND_CURSOR);
			
			backButton = new JButton("DONE");
			
			selectPro = new JLabel();
			selectPro.setText("Select Profile");
			selectPro.setBounds(95, 110, 200, 100);
			selectPro.setFont(f1);
			selectPro.setForeground(Color.BLACK);
			newFrame.add(selectPro);
			
			userLabel = new JLabel();
			userLabel.setText("Select Mode");
			userLabel.setBounds(95, 180, 200, 100);
			userLabel.setFont(f1);
			userLabel.setForeground(Color.BLACK);
			newFrame.add(userLabel);
			
			grp = new ButtonGroup();
			
			namedPro = new JRadioButton("Named Profile");
			namedPro.setBounds(250, 140, 150, 50);
			namedPro.setFont(f);
			namedPro.setCursor(cursor);
			namedPro.setSelected(true);
			newFrame.add(namedPro);
			
			guestPro = new JRadioButton("Guest Profile");
			guestPro.setBounds(400, 140, 150, 50);
			guestPro.setFont(f);
			guestPro.setCursor(cursor);
			newFrame.add(guestPro);
			
			grp.add(namedPro);
			grp.add(guestPro);
			
			printScore(newFrame, f1);
			
			/*guestPro.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					
					//System.out.println(12);
					printScore(newFrame, f1);
				
			}
			
		});*/
			
			newFrame.add(backButton);
			backButton.setBounds(300, 500, 150, 50);
			backButton.setForeground(Color.BLACK);
			backButton.setFont(f);
			backButton.setCursor(cursor);
			
			selectMode = new JComboBox<Object>(mode);
			selectMode.setBounds(250, 200, 150, 50);
			selectMode.setFont(f);
			selectMode.setCursor(cursor);
			newFrame.add(selectMode);
			
			backButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					setVisible(true);
					newFrame.setVisible(false);
				
			}
			
		});
			
			addImage();
			newFrame.add(imgLabel);
			
		}
		
		else if(e.getSource()==about) {
			
			JFrame newFrame = new JFrame("Sudoku");
			newFrame.setLayout(null);
			newFrame.setBounds(300, 100, 800, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(false);
			newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f = new Font("Arial",Font.BOLD + Font.ITALIC,14);
			cursor = new Cursor(Cursor.HAND_CURSOR);
			
			backButton = new JButton("BACK");
			newFrame.add(backButton);
			backButton.setBounds(300, 500, 150, 50);
			backButton.setForeground(Color.BLACK);
			backButton.setFont(f);
			backButton.setCursor(cursor);
			
			backButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					setVisible(true);
					newFrame.setVisible(false);
				
			}
			
		});
			
			img = new ImageIcon(getClass().getResource("Sudoku1.png"));
			
			imgLabel = new JLabel(img);
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			newFrame.add(imgLabel);
			
			
		}
		
		else if(e.getSource()==tipsAndTricks) {
			
			JFrame newFrame = new JFrame("Sudoku");
			newFrame.setLayout(null);
			newFrame.setBounds(300, 100, 800, 600);
			newFrame.setVisible(true);
			newFrame.setResizable(false);
			newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			f = new Font("Arial",Font.BOLD + Font.ITALIC,14);
			cursor = new Cursor(Cursor.HAND_CURSOR);
			
			backButton = new JButton("Let's Play");
			newFrame.add(backButton);
			backButton.setBounds(300, 500, 150, 50);
			backButton.setForeground(Color.BLACK);
			backButton.setFont(f);
			backButton.setCursor(cursor);
			
			backButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					setVisible(true);
					newFrame.setVisible(false);
				
			}
			
		});
			
			img = new ImageIcon(getClass().getResource("Sudoku2.png"));
			
			imgLabel = new JLabel(img);
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			newFrame.add(imgLabel);
			
			
		}
		
	}
	
	public void printScore(JFrame newFrame, Font f1) {
		
		for(int i = 1; i <= 5; i++) {
			
			scoreShow = new JLabel();
			scoreShow.setText(i + ". 00:00:00");
			scoreShow.setBounds(250, 225+(i*25), 200, 100);
			scoreShow.setFont(f1);
			scoreShow.setForeground(Color.BLACK);
			newFrame.add(scoreShow);
			
		}
		
	}

	public void startGame() {
		
		SudokuFrame frame = new SudokuFrame(this);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		MyMenu frame = new MyMenu();

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300, 100, 800, 600);

	}

}
