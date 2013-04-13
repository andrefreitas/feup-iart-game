package graphical;

import game.Board;
import init.NineMansMorris;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame{

	public static JFrame self;
	
	
	
	private static JRadioButton rdbtnHumanVsMinimax;
	private static JRadioButton rdbtnHumanVsHuman;
	private static ButtonGroup gameType;
	
	private static JRadioButton rdbtnEasy;
	private static JRadioButton rdbtnMedium;
	private static JRadioButton rdbtnHard;
	private static ButtonGroup gameDifficulty;
	
	public MainMenu()
	{
		super();
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 100, 387, 320);
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NineMansMorris.board=new Board();
				if(rdbtnHumanVsHuman.isSelected())
				{
					NineMansMorris.gameType=0;
					System.out.println("Game type 0");
				}else if(rdbtnHumanVsMinimax.isSelected())
				{
					NineMansMorris.gameType=1;
					System.out.println("Game type 1");
				}
				if(rdbtnEasy.isSelected())
				{
					NineMansMorris.board.difficulty=1;
				}else if(rdbtnMedium.isSelected())
				{
					NineMansMorris.board.difficulty=2;
				}else if(rdbtnHard.isSelected())
				{
					NineMansMorris.board.difficulty=3;
				}
				System.out.println("Game difficulty: "+NineMansMorris.board.difficulty);
				Thread a=new Thread(new Runnable(){

					@Override
					public void run() {
						new GameWindow();
						
					}
					
				});
				a.start();
				
				self.setVisible(false);
			}
		});
		btnNewButton.setBounds(30, 40, 140, 70);
		getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
				System.exit(0);
			}
		});
		btnExit.setBounds(30, 248, 140, 23);
		getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Options: ");
		lblNewLabel.setBounds(214, 43, 76, 14);
		getContentPane().add(lblNewLabel);
		
		rdbtnHumanVsHuman = new JRadioButton("Human vs Human");
		rdbtnHumanVsHuman.setSelected(true);
		
		rdbtnHumanVsHuman.setBounds(192, 64, 153, 23);
		getContentPane().add(rdbtnHumanVsHuman);
		
		rdbtnHumanVsMinimax = new JRadioButton("Human vs MiniMax");
				
		rdbtnHumanVsMinimax.setBounds(192, 100, 153, 23);
		getContentPane().add(rdbtnHumanVsMinimax);
		gameType=new ButtonGroup();
		gameType.add(rdbtnHumanVsHuman);
		gameType.add(rdbtnHumanVsMinimax);
		
		
		
		rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.setBounds(192, 185, 109, 23);
		rdbtnEasy.setSelected(true);
		getContentPane().add(rdbtnEasy);
		
		JLabel lblDificultyLevel = new JLabel("Difficulty level: ");
		lblDificultyLevel.setBounds(214, 164, 128, 14);
		getContentPane().add(lblDificultyLevel);
		
		rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.setBounds(192, 211, 109, 23);
		getContentPane().add(rdbtnMedium);
		
		rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.setBounds(192, 237, 109, 23);
		getContentPane().add(rdbtnHard);
		
		gameDifficulty=new ButtonGroup();
		gameDifficulty.add(rdbtnEasy);
		gameDifficulty.add(rdbtnMedium);
		gameDifficulty.add(rdbtnHard);
		
		this.setVisible(true);
		this.repaint();
		
		self=this;
	}
}
