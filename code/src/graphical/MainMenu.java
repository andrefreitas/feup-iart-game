package graphical;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import logic.Game;

/**
 * Classe que implementa o menu da aplica��o.
 */
public class MainMenu extends JFrame
{
	private static final long serialVersionUID = -3479162357242279459L;

	public static JFrame self;

	private JLabel background = new JLabel(new ImageIcon(
			ClassLoader.getSystemClassLoader().getResource("images/fundo_menu.jpg")));

	//Bot�es de sele��o de modo de jogo
	private JRadioButton rdbtnHumanVsHuman = new JRadioButton("Humano vs Humano");
	private JRadioButton rdbtnHumanVsMinimax = new JRadioButton("Humano vs CPU");
	private JRadioButton rdbtnMinimaxVsMinimax = new JRadioButton("CPU vs CPU");
	private ButtonGroup gameType = new ButtonGroup();

	//Bot�es de sele��o da dificuldade
	private JRadioButton rdbtnEasy = new JRadioButton("F�cil");
	private JRadioButton rdbtnMedium = new JRadioButton("M�dio");
	private JRadioButton rdbtnHard = new JRadioButton("Dif�cil");
	private ButtonGroup gameDifficulty = new ButtonGroup();
	
	public static final int radioButtonWidth = 153;
	public static final int radioButtonHeight = 23;

	/**
	 * Construtor da classe MainMenu. Atribui tamanho e outras propriedades � janela e delega a cria��o de bot�es a outros m�todos.
	 */
	public MainMenu()
	{
		super();
		self=this;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 100, 387, 320);
		this.setResizable(false);
		getContentPane().setLayout(null);

		//M�todos de cria��o de bot�es e labels para intera��o com o utilizador
		initStartExitBtns();
		initGameModes();
		initDifficultyLevels();

		background.setBounds(0, 0, 387, 320);
		getContentPane().add(background);

		this.setVisible(true);
		this.repaint();		
	}
	
	/**
	 * M�todo respons�vel por criar os bot�es start e exit e atribuir funcionalidade aos mesmos.
	 */
	private void initStartExitBtns()
	{
		JButton startButton = new JButton("Start!");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) //Ac��o do bot�o start
			{
				int selected_type, selected_difficulty;				
			
				if(rdbtnHumanVsHuman.isSelected())
					selected_type=0;
				else if (rdbtnHumanVsMinimax.isSelected())
					selected_type=1;
				else
					selected_type=2;

				if(rdbtnEasy.isSelected())
					selected_difficulty=1;
				else if(rdbtnMedium.isSelected())
					selected_difficulty=2;
				else
					selected_difficulty=3;
				
				Game.init(selected_type, selected_difficulty);

				(new Thread(){
					@Override
					public void run()
					{
						new GameWindow();
					}
				}).start();

				self.setVisible(false);
			}
		});
		startButton.setBounds(30, 40, 140, 70);
		getContentPane().add(startButton);

		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ac��o do bot�o exit
				self.dispose();
				System.exit(0);
			}
		});
		btnExit.setBounds(30, 248, 140, 23);
		getContentPane().add(btnExit);
	}
	
	/**
	 * M�todo respons�vel por criar os radio buttons para selecionar o modo de jogo.
	 */
	private void initGameModes() 
	{
		JLabel lblNewLabel = new JLabel("Modo de jogo: ");
		lblNewLabel.setBounds(214, 44, 100, 14);
		getContentPane().add(lblNewLabel);
		
		rdbtnHumanVsHuman.setBounds(192, 64, radioButtonWidth, radioButtonHeight);
		getContentPane().add(rdbtnHumanVsHuman);
		rdbtnHumanVsMinimax.setBounds(192, 90, radioButtonWidth, radioButtonHeight);
		getContentPane().add(rdbtnHumanVsMinimax);
		rdbtnMinimaxVsMinimax.setBounds(192, 116, radioButtonWidth, radioButtonHeight);
		getContentPane().add(rdbtnMinimaxVsMinimax);
		//rdbtnMinimaxVsMinimax.setEnabled(false); //TODO
		
		gameType.add(rdbtnHumanVsHuman);
		gameType.add(rdbtnHumanVsMinimax);
		gameType.add(rdbtnMinimaxVsMinimax);	
		gameType.setSelected(rdbtnHumanVsHuman.getModel(), true);
	}
	
	/**
	 * M�todo respons�vel por criar os radio buttons para selecionar a dificuldade do jogo.
	 */
	private void initDifficultyLevels()
	{
		JLabel lblDificultyLevel = new JLabel("Dificuldade: ");
		lblDificultyLevel.setBounds(214, 165, 128, 14);
		getContentPane().add(lblDificultyLevel);
		
		rdbtnEasy.setBounds(192, 185, radioButtonWidth, radioButtonHeight);
		getContentPane().add(rdbtnEasy);
		rdbtnMedium.setBounds(192, 211, radioButtonWidth, radioButtonHeight);
		getContentPane().add(rdbtnMedium);
		rdbtnHard.setBounds(192, 237, radioButtonWidth, radioButtonHeight);
		getContentPane().add(rdbtnHard);
		
		gameDifficulty.add(rdbtnEasy);
		gameDifficulty.add(rdbtnMedium);
		gameDifficulty.add(rdbtnHard);
		gameDifficulty.setSelected(rdbtnMedium.getModel(), true);
	}
}
