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
	
	public static final int radioButtonWidth = 153;
	public static final int radioButtonHeight = 23;

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
	 * Método responsável por criar os radio buttons para selecionar a dificuldade do jogo.
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
