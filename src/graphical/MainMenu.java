package graphical;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Game;

/**
 * Classe que implementa o menu da aplicação.
 */
public class MainMenu extends JFrame
{
	private static final long serialVersionUID = -3479162357242279459L;

	public static JFrame self;

	private JLabel background = new JLabel(new ImageIcon(
			ClassLoader.getSystemClassLoader().getResource("images/fundo_menu.jpg")));

	//Botões de seleção de modo de jogo
	private JRadioButton rdbtnHumanVsHuman = new JRadioButton("Humano vs Humano");
	private JRadioButton rdbtnHumanVsMinimax = new JRadioButton("Humano vs CPU");
	private JRadioButton rdbtnMinimaxVsMinimax = new JRadioButton("CPU vs CPU");
	private ButtonGroup gameType = new ButtonGroup();

	//Botões de seleção da dificuldade
	private JRadioButton rdbtnEasy = new JRadioButton("Fácil");
	private JRadioButton rdbtnMedium = new JRadioButton("Médio");
	private JRadioButton rdbtnHard = new JRadioButton("Difícil");
	private ButtonGroup gameDifficulty = new ButtonGroup();
	
	//Botoes 2º bot
	private JLabel label2bot;
	private JRadioButton radioButton2botF;
	private JRadioButton radioButton2botM;
	private JRadioButton radioButton2botD;
	private ButtonGroup gameDifficulty2bot = new ButtonGroup();
	
	public static final int radioButtonWidth = 153;
	public static final int radioButtonHeight = 23;

	
	public void show2bot()
	{
		label2bot.setVisible(true);
		radioButton2botF.setVisible(true);
		radioButton2botM.setVisible(true);
		radioButton2botD.setVisible(true);
	}
	
	public void hide2bot()
	{
		label2bot.setVisible(false);
		radioButton2botF.setVisible(false);
		radioButton2botM.setVisible(false);
		radioButton2botD.setVisible(false);
	}
	
	/**
	 * Construtor da classe MainMenu. Atribui tamanho e outras propriedades à janela e delega a criação de botões a outros métodos.
	 */
	public MainMenu()
	{
		super();
		self=this;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 100, 387, 320);
		this.setResizable(false);
		getContentPane().setLayout(null);

		//Métodos de criação de botões e labels para interação com o utilizador
		initStartExitBtns();
		initGameModes();
		initDifficultyLevels();
		
		background.setBounds(0, 0, 387, 320);
		getContentPane().add(background);
		
		
	
		
		rdbtnMinimaxVsMinimax.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				if(rdbtnMinimaxVsMinimax.isSelected())
				{
					show2bot();
				}else{
					hide2bot();
				}
			}});

		this.setVisible(true);
		this.repaint();		
	}
	
	/**
	 * Método responsável por criar os botões start e exit e atribuir funcionalidade aos mesmos.
	 */
	private void initStartExitBtns()
	{
		JButton startButton = new JButton("Start!");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) //Acção do botão start
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
				
				int selected_difficulty2=3;
				
				if(radioButton2botF.isSelected())
					selected_difficulty2=1;
				else if(radioButton2botM.isSelected())
					selected_difficulty2=2;
				else
					selected_difficulty2=3;
				
				
				if(selected_type==2)
					Game.init(selected_type, selected_difficulty, selected_difficulty2);
				else
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
		startButton.setBounds(46, 16, 140, 53);
		getContentPane().add(startButton);

		JButton btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Acção do botão exit
				self.dispose();
				System.exit(0);
			}
		});
		btnExit.setBounds(30, 248, 140, 23);
		getContentPane().add(btnExit);
	}
	
	/**
	 * Método responsável por criar os radio buttons para selecionar o modo de jogo.
	 */
	private void initGameModes() 
	{
		JLabel lblNewLabel = new JLabel("Modo de jogo: ");
		lblNewLabel.setBounds(39, 86, 100, 14);
		getContentPane().add(lblNewLabel);
		rdbtnHumanVsHuman.setOpaque(false);
		
		rdbtnHumanVsHuman.setBounds(17, 106, 140, 23);
		
		
		getContentPane().add(rdbtnHumanVsHuman);
		rdbtnHumanVsMinimax.setBounds(17, 132, 140, 23);
		rdbtnHumanVsMinimax.setOpaque(false);
		getContentPane().add(rdbtnHumanVsMinimax);
		rdbtnMinimaxVsMinimax.setBounds(17, 158, 140, 23);
		rdbtnMinimaxVsMinimax.setOpaque(false);
		getContentPane().add(rdbtnMinimaxVsMinimax);
	

		gameType.add(rdbtnHumanVsHuman);
		gameType.add(rdbtnHumanVsMinimax);
		gameType.add(rdbtnMinimaxVsMinimax);	
		gameType.setSelected(rdbtnHumanVsHuman.getModel(), true);
	}
	
	/**
	 * Método responsável por criar os radio buttons para selecionar a dificuldade do jogo.
	 */
	private void initDifficultyLevels()
	{
		JLabel lblDificultyLevel = new JLabel("Dificuldade: ");
		lblDificultyLevel.setBounds(204, 86, 128, 14);
		getContentPane().add(lblDificultyLevel);
		
		rdbtnEasy.setBounds(170, 106, 57, 23);
		rdbtnEasy.setOpaque(false);
		getContentPane().add(rdbtnEasy);
		
		rdbtnMedium.setBounds(229, 106, 63, 23);
		rdbtnMedium.setOpaque(false);
		getContentPane().add(rdbtnMedium);
		rdbtnHard.setBounds(294, 106, 57, 23);
		rdbtnHard.setOpaque(false);
		getContentPane().add(rdbtnHard);
		
		label2bot = new JLabel("Dificuldade do 2º bot: ");
		label2bot.setBounds(204, 138, 128, 14);
		
		getContentPane().add(label2bot);
		
		radioButton2botF = new JRadioButton("F\u00E1cil");
		radioButton2botF.setBounds(170, 158, 57, 23);
		radioButton2botF.setOpaque(false);
		getContentPane().add(radioButton2botF);
		
		radioButton2botM = new JRadioButton("M\u00E9dio");
		radioButton2botM.setBounds(229, 158, 63, 23);
		radioButton2botM.setOpaque(false);
		getContentPane().add(radioButton2botM);
		
		radioButton2botD = new JRadioButton("Dif\u00EDcil");
		radioButton2botD.setBounds(294, 158, 57, 23);
		radioButton2botD.setOpaque(false);
		getContentPane().add(radioButton2botD);
		
		gameDifficulty2bot.add(radioButton2botF);
		gameDifficulty2bot.add(radioButton2botM);
		gameDifficulty2bot.add(radioButton2botD);
		gameDifficulty2bot.setSelected(radioButton2botM.getModel(), true);
		
		
		gameDifficulty.add(rdbtnEasy);
		gameDifficulty.add(rdbtnMedium);
		gameDifficulty.add(rdbtnHard);
		gameDifficulty.setSelected(rdbtnMedium.getModel(), true);
		
		hide2bot();
	}
}
