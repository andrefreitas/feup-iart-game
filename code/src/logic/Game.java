package logic;

import graphical.GameWindow;

import java.util.Random;
import java.util.Vector;

import algorithms.MiniMax;

/**
 * Classe que armazena o estado geral do jogo, inicia o tabuleiro e fornece o método para pedir uma jogada ao CPU.
 */
public class Game 
{
	public static Board board;
	public static int gameType=0; //0-human vs human; 1-human vs minimax; 2-minimax vs minimax (TODO)
	public static char botColor='B'; //B - black, W - white, aplicável quando o tipo de jogo é humano contra minimax
	
	public static void init(int selected_type, int selected_difficulty) 
	{
		Game.board=new Board();
		Game.gameType=selected_type;
		Game.board.difficulty=selected_difficulty;		
		System.out.println("Game type "+Game.gameType);
		System.out.println("Game difficulty: "+Game.board.difficulty);	
	}
	
	/**
	 * Método auxiliar que permite pedir uma jogada ao computador. Invoca o minimax com os devidos parâmetros iniciais, escolhe jogadas aleatórias em caso de empate...
	 * @param moves Vector de jogadas possíveis.
	 */
	public static void playBot(Vector<Move> moves) 
	{		
		Move nextMove=null;
		int moveVal=-10000;
		
		for(Move m : moves)
		{			
			int minimaxVal=MiniMax.play(m, 0,false, /*moveVal*/ -10000, 10000, board.turn); //TODO: Na verdade isto trata-se de um nó max (porque é a raiz), por isso ao invocar play alfa deveria ser igual a moveVal e não -10000 sempre, por favor verifica
			//System.out.println("minimax: "+minimaxVal);
			if(minimaxVal==moveVal)
			{
				Random rd= new Random();
				double nrd=((double)1/((double)moves.size()/2));
				
				if(rd.nextDouble()<=nrd)
				{
					//System.out.println("rand: <="+nrd);
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
		
		nextMove.showMove();
		System.out.println("Valor jogada: "+moveVal);
		board.makeMove(nextMove);
		((GameWindow) GameWindow.self).botPlay(nextMove);
		board.getMatrix();
		System.out.println("Turn: "+board.turn);
	}
}
