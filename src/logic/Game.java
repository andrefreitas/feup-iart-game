package logic;

import graphical.GameWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import algorithms.MiniMax;

/**
 * Classe que armazena o estado geral do jogo, inicia o tabuleiro e fornece o método para pedir uma jogada ao CPU.
 */
public class Game 
{
	public static FileOutputStream st;
	public static Board board=null;
	public static int gameType=0; //0-human vs human; 1-human vs minimax; 2-minimax vs minimax 
	public static char botColor='B'; //B - black, W - white, aplicável quando o tipo de jogo é humano contra minimax
	
	public static void init(int selected_type, int selected_difficulty) 
	{
		try {
			st=new FileOutputStream((new File("dados.txt")));
		} catch (FileNotFoundException e) {
			
		}
		if(Game.board!=null)
		{
			
		}
		Game.board=new Board();
		Game.gameType=selected_type;
		Game.board.difficulty=selected_difficulty;		
		
	}
	
	public static void init(int selected_type, int selected_difficulty, int selected_difficulty2)
	{
		try {
			st=new FileOutputStream(new File("dados.txt"));
		} catch (FileNotFoundException e) {
			
		}
		Game.board=new Board();
		Game.gameType=selected_type;
		Game.board.difficulty=selected_difficulty;	
		Game.board.difficulty2=selected_difficulty2;
		
	}
	
	/**
	 * Método auxiliar que permite pedir uma jogada ao computador. Invoca o minimax com os devidos parâmetros iniciais, escolhe jogadas aleatórias em caso de empate...
	 * @param moves Vector de jogadas possíveis.
	 */
	public static void playBot(Vector<Move> moves) 
	{
		long before=System.currentTimeMillis();	
		
		Move nextMove=null;
		int moveVal=-10000;
		
		for(Move m : moves)
		{			
			int minimaxVal=MiniMax.play(m, 0,false, -10000, 10000, board.turn); 
			if(minimaxVal==moveVal)
			{
				Random rd= new Random();
				double nrd=((double)1/((double)moves.size()/2));
				
				if(rd.nextDouble()<=nrd)
				{
					
					moveVal=minimaxVal;
					nextMove=m;
				}
			}
			if(minimaxVal>moveVal)
			{
				moveVal=minimaxVal;
				nextMove=m;
			}			
		}
		
		long after=System.currentTimeMillis();
		
		if(board.turn=='B')
		{
			
			
			try {
				
				
				
				byte[] data = ((""+(after-before)+"\n").getBytes());
				st.write(data);
				
				
			} catch (IOException e) {
				
			}
		}
		
		board.makeMove(nextMove);
		((GameWindow) GameWindow.self).botPlay(nextMove);
	
		
	}
}
